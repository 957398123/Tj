
public class PCMail {

    public static final String SYSTEM_SENDER = "System";
    public static boolean fromSystem = false;
    public static boolean responseFlag = false;
    public static String responseName = null;

    public static boolean canResponse(String senderName) {
        if ("System".equals(senderName.trim())) {
            return false;
        }
        return true;
    }

    public static void initMailState() {
        MainCanvas.postage = 0;
        MainCanvas.postageAd = 0;
        MainCanvas.mailAddStuffNum = -1;
        MainCanvas.mailAddStuffId = -1;
        MainCanvas.mailAddMoney = 0;
        MainCanvas.mailAddStuff = -1;
        MainCanvas.mailSendTo = -1;
        MainCanvas.mailSelectWhich = 0;
        MainCanvas.mailName = null;
        MainCanvas.mc.commonTextField = null;
        MainCanvas.mc.commontf = null;
    }

    public static void parse(int commID, byte[] data) {
        String hexID = Integer.toHexString(commID);
        System.out.println("[处理命令]:命令ID:0x" + hexID + ",命令类型：PCMail");
        byte delState;
        String[][] temp;
        byte i, count;
        int j;
        byte allMailCnt;
        int k;
        String tempMailName, tempMailDetail;
        byte tempMailAddStuffImage;
        String tempMailAddStuffName;
        int tempColor;
        byte tempMailAddStuffCnt;
        UIForm viewForm;
        UIRim rimOut, rimMiddle;
        UILabel lblTitle, lblFrom, lblOperation, lblReturn, lblAdd;
        UIRim rimImg;
        UILabel lblAsset;
        byte rcvItemState, rcvMoneyState, tempState;
        ByteArray execDataIn = new ByteArray(data);
        switch (commID) {
            case 184551296:
                delState = execDataIn.readByte();
                switch (delState) {
                    case 0:
                        MainCanvas.mc.baseForm.addAboutForm("return", "删除失败！", (byte) 1, 120, 50);
                        break;
                    case 1:
                        if (MainCanvas.mailSelectWhich != MainCanvas.mailList.length - 1) {
                            for (int m = MainCanvas.mailSelectWhich; m < MainCanvas.mailList.length - 1; m++) {
                                MainCanvas.mailList[m][0] = MainCanvas.mailList[m + 1][0];
                                MainCanvas.mailList[m][1] = MainCanvas.mailList[m + 1][1];
                                MainCanvas.mailList[m][2] = MainCanvas.mailList[m + 1][2];
                                MainCanvas.mailList[m][3] = MainCanvas.mailList[m + 1][3];
                            }
                        }
                        MainCanvas.isHaveNewMail = false;
                        MainCanvas.isLookMailnew = false;
                        temp = new String[MainCanvas.mailList.length - 1][4];
                        for (j = 0; j < MainCanvas.mailList.length - 1; j++) {
                            temp[j][0] = MainCanvas.mailList[j][0];
                            temp[j][1] = MainCanvas.mailList[j][1];
                            temp[j][2] = MainCanvas.mailList[j][2];
                            temp[j][3] = MainCanvas.mailList[j][3];
                            if (temp[j][1].equals("0")) {
                                MainCanvas.isHaveNewMail = true;
                            }
                        }
                        MainCanvas.mailList = (String[][]) null;
                        MainCanvas.mailList = temp;
                        MainCanvas.mc.tables[0].deleteItem(MainCanvas.mailSelectWhich);
                        MainCanvas.mc.baseForm.addAboutForm("return", "删除成功！", (byte) 1, 120, 50);
                        break;
                }
                break;
            case 184550528:
                (Player.getInstance()).money = execDataIn.readInt();
                (Player.getInstance()).money = ((Player.getInstance()).money > 1999999999) ? 1999999999 : (Player.getInstance()).money;
                MainCanvas.postage = execDataIn.readShort();
                MainCanvas.postageAd = execDataIn.readShort();
                for (i = 0; i < 36; i = (byte) (i + 1)) {
                    if (MainCanvas.dramatisPackage == null) {
                        MainCanvas.dramatisPackage = new UIGrid(0, 113, (byte) 4, (byte) 9, (byte) 4, MainCanvas.mImgStuff);
                    }
                    MainCanvas.dramatisPackage.setXY(0, 0);
                    MainCanvas.dramatisPackage.setGridDetail(i, execDataIn
                            .readShort(), execDataIn
                            .readByte(), execDataIn
                            .readByte(), execDataIn
                            .readUTF(), execDataIn
                            .readByte(), execDataIn
                            .readShort(), execDataIn
                            .readByte(), execDataIn
                            .readByte());
                }
                count = execDataIn.readByte();
                MainCanvas.friendList = new String[count][3];
                MainCanvas.friendListID = new int[count];
                for (j = 0; j < count; j++) {
                    MainCanvas.friendListID[j] = execDataIn.readInt();
                    MainCanvas.friendList[j][0] = execDataIn.readUTF();
                }
                MainCanvas.mc.setNPCSubState((byte) 11);
                MainCanvas.mc.setNPCMailState((byte) 1);
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.mc.releaseUI();
                break;
            case 184549760:
                allMailCnt = execDataIn.readByte();
                MainCanvas.mailList = (String[][]) null;
                MainCanvas.mailList = new String[allMailCnt][4];
                for (k = 0; k < allMailCnt; k++) {
                    MainCanvas.mailList[k][0] = execDataIn.readUTF();
                    MainCanvas.mailList[k][1] = (execDataIn.readByte() == 0) ? "0" : "1";
                    MainCanvas.mailList[k][2] = (execDataIn.readByte() == 0) ? "0" : "1";
                }
                for (k = 0; k < allMailCnt; k++) {
                    MainCanvas.mailList[k][3] = execDataIn.readInt() + "";
                }
                MainCanvas.mc.setNPCSubState((byte) 11);
                MainCanvas.mc.setNPCMailState((byte) 0);
                MainCanvas.mc.releaseUI();
                break;
            case 184550016:
                MainCanvas.mc.selectedId = 0;
                responseName = execDataIn.readUTF();
                fromSystem = !canResponse(responseName);
                tempMailName = MainCanvas.mailList[MainCanvas.mailSelectWhich][0];
                tempMailDetail = execDataIn.readUTF();
                MainCanvas.mailAddMoney = execDataIn.readInt();
                tempMailAddStuffImage = execDataIn.readByte();
                tempMailAddStuffName = "";
                tempColor = 0;
                tempMailAddStuffCnt = 0;
                if (tempMailAddStuffImage >= 0) {
                    tempMailAddStuffName = execDataIn.readUTF();
                    tempMailAddStuffCnt = execDataIn.readByte();
                    tempColor = execDataIn.readByte();
                    try {
                        MainCanvas.mc.selectedId = execDataIn.readShort();
                    } catch (Exception exception) {
                    }
                    if (tempMailAddStuffCnt == 0) {
                        tempMailAddStuffImage = -1;
                        tempMailAddStuffName = "";
                    }
                }
                viewForm = new UIForm(0, 0, MainCanvas.mc.baseForm.width, MainCanvas.mc.baseForm.height, "view");
                rimOut = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte) 4);
                rimMiddle = new UIRim(0, 12, 160, 130, (byte) 0);
                lblTitle = new UILabel(0, 18, 146, 0, "主题:" + tempMailName, 15718814, (byte) 0, (byte) 0);
                lblTitle.setRimStyle((byte) 0);
                lblFrom = new UILabel(0, 40, 146, 0, "来自:" + responseName, 15718814, (byte) 0, (byte) 0);
                lblFrom.setRimStyle((byte) 0);
                lblOperation = new UILabel(0, 0, 0, 0, "操作", 15718814, (byte) 1, (byte) 0);
                lblReturn = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 1, (byte) 0);
                MainCanvas.mc.textArea[0] = new UITextArea(0, 61, 152, 77, tempMailDetail);
                lblAdd = new UILabel(8, 150, 0, 0, "附件", 15718814, (byte) 0, (byte) 0);
                rimImg = new UIRim(40, 150, 17, 17, (byte) 0);
                MainCanvas.mc.mImages[0] = new UIMImage(rimImg.positionX + 1, rimImg.positionY + 1, rimImg.width - 1, rimImg.height - 1, MainCanvas.mImgStuff, (byte) 0);
                MainCanvas.mc.mImages[0].setVisible(false);
                lblAsset = new UILabel(8, 172, 0, 0, "邮件中的汇款", 15718814, (byte) 0, (byte) 0);
                MainCanvas.mc.texts[0] = new UIText(88, 172, 70, 20, 6, (byte) 3, MainCanvas.mailAddMoney + "");
                MainCanvas.mc.labels[0] = new UILabel(65, 150, 0, 0, tempMailAddStuffName, Cons.STUFF_NAME_COLOR[tempColor], (byte) 0, (byte) 0);
                viewForm.addComponent(rimOut);
                viewForm.addComponentInCenter(rimMiddle, (byte) 2);
                viewForm.addComponent(rimImg);
                viewForm.addComponentInCenter(lblTitle, (byte) 2);
                viewForm.addComponentInCenter(lblFrom, (byte) 2);
                viewForm.addComponentInCenter(lblOperation, (byte) 5);
                viewForm.addComponentInCenter(lblReturn, (byte) 6);
                viewForm.addComponent(lblAdd);
                viewForm.addComponent(lblAsset);
                viewForm.addComponentInCenter(MainCanvas.mc.textArea[0], (byte) 2);
                viewForm.addComponent(MainCanvas.mc.texts[0]);
                viewForm.addComponent(MainCanvas.mc.mImages[0]);
                viewForm.addComponent(MainCanvas.mc.labels[0]);
                if (tempMailAddStuffImage >= 0) {
                    byte imgId = (byte) (tempMailAddStuffImage - 1);
                    MainCanvas.mc.mImages[0].setCurrentFrame(imgId);
                    MainCanvas.mc.mImages[0].setVisible(true);
                    MainCanvas.mc.mImages[0].setNumber(tempMailAddStuffCnt);
                    if (imgId > 32) {
                        MainCanvas.mc.mImages[0].setNumberVisiable(true);
                    } else {
                        MainCanvas.mc.mImages[0].setNumberVisiable(false);
                    }
                }
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.mc.baseForm.setAboutForm(viewForm);
                MainCanvas.mc.tables[0].setReadingState(MainCanvas.mailSelectWhich, true);
                MainCanvas.mailList[MainCanvas.mailSelectWhich][1] = "1";
                break;
            case 184550784:
                MainCanvas.mc.isNoItem = false;
                rcvItemState = execDataIn.readByte();
                switch (rcvItemState) {
                    case 0:
                        MainCanvas.mc.baseForm.getSubForm().addAboutForm("information", "收取附件失败!", (byte) 1, 100, 50);
                        break;
                    case 1:
                        MainCanvas.mc.isNoItem = true;
                        MainCanvas.mailList[execDataIn.readByte()][2] = "0";
                        MainCanvas.mc.baseForm.getSubForm().addAboutForm("information", "收取附件成功!", (byte) 1, 140, 50);
                        MainCanvas.mc.mImages[0].setVisible(false);
                        MainCanvas.mc.mImages[0].setNumber((byte) 0);
                        MainCanvas.mc.labels[0].setStr(" ");
                        break;
                }
                break;
            case 184551040:
                rcvMoneyState = execDataIn.readByte();
                switch (rcvMoneyState) {
                    case 0:
                        MainCanvas.mc.baseForm.getSubForm().addAboutForm("information", "收取汇款失败!", (byte) 1, 100, 50);
                        break;
                    case 1:
                        MainCanvas.mc.baseForm.getSubForm().addAboutForm("information", "收取汇款成功!", (byte) 1, 160, 50);
                        MainCanvas.mc.texts[0].setLabel("0");
                        break;
                }
                break;
            case 184550272:
                tempState = execDataIn.readByte();
                if (MainCanvas.mc.baseForm == null) {
                    break;
                }
                switch (tempState) {
                    case 0:
                        break;
                    case 1:
                        MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                        MainCanvas.mc.baseForm.addAboutForm("return", "发送成功！", (byte) 1, 120, 50);
                        MainCanvas.mailName = "";
                        MainCanvas.mailDetail = "";
                        break;
                    case 2:
                        MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                        MainCanvas.mc.baseForm.addAboutForm("return", "对方收件箱已满！", (byte) 1, 120, 50);
                        break;
                    case 3:
                        MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                        MainCanvas.mc.baseForm.addAboutForm("return", "邮费不足！", (byte) 1, 120, 50);
                        break;
                    case 4:
                        MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                        MainCanvas.mc.baseForm.addAboutForm("return", "收件人不存在！", (byte) 1, 130, 50);
                        break;
                    case 5:
                        MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                        MainCanvas.mc.baseForm.addAboutForm("return", "不在同一阵营不能通信！", (byte) 1, 120, 50);
                        break;
                }
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.mc.baseForm.addAboutForm("return", "unknow error！", (byte) 1, 120, 50);
                break;
            case 184551552:
                switch (execDataIn.readByte()) {
                    case 0:
                        MainCanvas.isHaveNewMail = false;
                        MainCanvas.isLookMailnew = false;
                        break;
                    case 1:
                        MainCanvas.isHaveNewMail = true;
                        MainCanvas.isLookMailnew = true;
                        break;
                    case 2:
                        PCChat.addChatScreen((byte) 7, "邮箱已满，请及时删除!");
                        UIGameRun.releaseChat();
                        break;
                }
                break;
        }
    }

    public static byte[] compress(int commID) {
        String name;
        ByteArray execDataOut = new ByteArray();
        switch (commID) {
            case 184549632:
                name = "gm";
                if (MainCanvas.mc.labels[0] != null) {
                    name = MainCanvas.mc.labels[0].getCurrentString().trim();
                }
                execDataOut.writeUTF(name);
                if (!MainCanvas.mc.gm_mail_on) {
                    execDataOut.writeUTF(MainCanvas.mailName);
                } else {
                    execDataOut.writeUTF(Cons.MENU_CS_QUIZ[MainCanvas.mc.table0curPointer]);
                }
                execDataOut.writeUTF(MainCanvas.mailDetail);
                execDataOut.writeInt(MainCanvas.mailAddMoney);
                execDataOut.writeByte(MainCanvas.mailAddStuff);
                execDataOut.writeByte(MainCanvas.mailAddStuffNum);
                execDataOut.writeShort(MainCanvas.mailAddStuffId);
                MainCanvas.mailDetail = null;
                MainCanvas.mc.commontf = null;
                break;
            case 184550144:
            case 184550400:
            case 184550656:
            case 184550912:
                execDataOut.writeByte(MainCanvas.mailSelectWhich);
                execDataOut.writeInt(Integer.parseInt(MainCanvas.mailList[MainCanvas.mailSelectWhich][3]));
                break;
            case 184551936:
                execDataOut.writeByte(MainCanvas.mailSelectWhich);
                execDataOut.writeInt(Integer.parseInt(MainCanvas.mailList[MainCanvas.mailSelectWhich][3]));
                break;
        }
        return execDataOut.toByteArray();
    }
}
