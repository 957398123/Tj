
import javax.microedition.lcdui.Image;

public class PCLogin {

    public static String[] serverListArr;
    public static byte serverListCnt = 0;
    public static byte loginState = -1;
    public static String downloadAddress = "";
    public static String[] serverDivArr;
    public static String[] serverDivNameList;
    public static String[] serverUpdateInfo;
    public static int cheatCheckValue;

    public static void parse(int commID, byte[] data) {
        String hexID = Integer.toHexString(commID);
        System.out.println("[处理命令]:命令ID:0x" + hexID + ",命令类型：PCLogin");
        byte tempRead1;
        String mgs;
        int i;
        byte tempRead2, result;
        int roleID;
        byte petImageId;
        short[][] temp;
        byte byEncryptChainLen;
        int n, remain, len;
        byte[] ba;
        Image img;
        ByteArray execDataIn = new ByteArray(data);
        switch (commID) {
            case 16781696:
                MainCanvas.mc.baseForm.setAboutForm(null);
                break;
            case 16781440:
                MainCanvas.mc.payChangeSerser = 1;
                PCIncrementService.changeServrNeedMoney = execDataIn.readInt();
                break;
            case 16780928:
                tempRead1 = execDataIn.readByte();
                mgs = execDataIn.readUTF();
                MainCanvas.mc.baseForm.setAboutForm(null);
                if (MainCanvas.mc.getState() != 12) {
                    MainCanvas.mc.setState((byte) 12);
                }
                if (tempRead1 == 0) {
                    MainCanvas.mc.inputName(mgs);
                    break;
                }
                MainCanvas.mc.baseForm.addAboutForm("msg", mgs, (byte) 1, 175, 82);
                MainCanvas.mc.baseForm.getSubForm().setFormXY((MainCanvas.screenW - 95 - 70) / 2, (MainCanvas.screenH - 82 - 10) / 2);
                break;
            case 16780160:
                Download.bbsUrl = execDataIn.readUTF();
                MainCanvas.mc.baseForm.setAboutForm(null);
                Download.gotoURL(MainCanvas.mc.aMidlet, (byte) 3);
                break;
            case 16779904:
                Download.strGtype = execDataIn.readUTF();
                break;
            case 16777600:
                MainCanvas.rightMenuId = 0;
                tempRead1 = execDataIn.readByte();
                switch (tempRead1) {
                    case 0:
                        MainCanvas.logon3_state = 0;
                        MainCanvas.mc.setState((byte) 12);
                        break;
                    case 1:
                        if (MainCanvas.logon3_state == 5 && MainCanvas.mc.userName.equals("guest")) {
                            return;
                        }
                        MainCanvas.playerNames = null;
                        MainCanvas.playerInfos = (byte[][]) null;
                        MainCanvas.isSelect1 = new boolean[3];
                        for (i = 0; i < 3; i++) {
                            MainCanvas.playerState[i] = execDataIn.readBoolean();
                        }
                        if (MainCanvas.playerState[0]) {
                            MainCanvas.isSelect1[0] = true;
                        }
                        MainCanvas.playerNames = new String[3];
                        MainCanvas.playerInfos = new byte[3][4];
                        MainCanvas.playerInformations = new String[3][4];
                        for (i = 0; i < 3; i++) {
                            if (MainCanvas.playerState[i]) {
                                MainCanvas.playerInfos[i][0] = execDataIn.readByte();
                                MainCanvas.playerInformations[i][1] = execDataIn.readUTF();
                                MainCanvas.playerInfos[i][2] = execDataIn.readByte();
                                MainCanvas.playerInfos[i][3] = execDataIn.readByte();
                                MainCanvas.playerNames[i] = execDataIn.readUTF();
                                byte j;
                                for (j = 0; j < 8; j = (byte) (j + 1)) {
                                    if (MainCanvas.playerInfos[i][2] == Cons.PLAYERS[j][0] && MainCanvas.playerInfos[i][3] == Cons.PLAYERS[j][1]) {
                                        MainCanvas.mc.imgPlayerId[i] = j;
                                    }
                                }
                                initPlayerInfo(i);
                            }
                        }
                        MainCanvas.Announce = "";
                        MainCanvas.Announce = execDataIn.readUTF();
                        MainCanvas.shyTime = null;
                        MainCanvas.shyTime = new int[3];
                        for (i = 0; i < 3; i++) {
                            if (MainCanvas.playerState[i]) {
                                byte time = execDataIn.readByte();
                                if (time < 0) {
                                    MainCanvas.shyTime[i] = -1;
                                } else {
                                    MainCanvas.shyTime[i] = time & 0xFF;
                                }
                            } else {
                                MainCanvas.shyTime[i] = -1;
                            }
                        }
                        MainCanvas.mc.releaseUI();
                        MainCanvas.mc.setState((byte) 12);
                        MainCanvas.logon3_state = 1;
                        try {
                            Cons.jadLogType = execDataIn.readByte();
                            Cons.url_mobile_jad = execDataIn.readUTF();
                            Cons.url_mobile_jar = execDataIn.readUTF();
                            Cons.url_mobile_notify = execDataIn.readUTF();
                            Cons.url_mobile_ip = execDataIn.readUTF();
                            Cons.jadBody = execDataIn.readUTF();
                            Cons.isStartNote = (execDataIn.readByte() == 1);
                        } catch (Exception exception) {
                        }
                        break;
                    case 2:
                        MainCanvas.logon3_state = 2;
                        MainCanvas.mc.setState((byte) 12);
                        break;
                    case 3:
                        downloadAddress = execDataIn.readUTF();
                        downloadAddress += "S60.jar";
                        MainCanvas.logon3_state = 5;
                        MainCanvas.mc.setState((byte) 12);
                        break;
                    case 4:
                        MainCanvas.logon3_state = 6;
                        MainCanvas.mc.setState((byte) 12);
                        break;
                    case 5:
                        MainCanvas.logon3_state = 7;
                        MainCanvas.mc.setState((byte) 12);
                        break;
                    case 6:
                        MainCanvas.logon3_state = 8;
                        MainCanvas.mc.setState((byte) 12);
                        break;
                }
                MainCanvas.mc.releaseUI();
                break;
            case 16778112:
                tempRead2 = execDataIn.readByte();
                if (tempRead2 == 1) {
                    MainCanvas.playerNames[MainCanvas.choose_manID] = MainCanvas.playerAddName;
                    MainCanvas.playerInfos[MainCanvas.choose_manID][0] = 1;
                    MainCanvas.playerInformations[MainCanvas.choose_manID][1] = execDataIn.readUTF();
                    MainCanvas.playerInfos[MainCanvas.choose_manID][2] = MainCanvas.playerAdd[2];
                    MainCanvas.playerInfos[MainCanvas.choose_manID][3] = MainCanvas.playerAdd[3];
                    MainCanvas.playerState[MainCanvas.choose_manID] = true;
                    MainCanvas.isFirst[MainCanvas.choose_manID] = true;
                    MainCanvas.isSelect1[MainCanvas.choose_manID] = true;
                    byte j;
                    for (j = 0; j < 8; j = (byte) (j + 1)) {
                        byte b;
                        for (b = 0; b < 3; b = (byte) (b + 1)) {
                            if (MainCanvas.playerInfos[b][2] == Cons.PLAYERS[j][0] && MainCanvas.playerInfos[b][3] == Cons.PLAYERS[j][1]) {
                                MainCanvas.mc.imgPlayerId[b] = j;
                            }
                        }
                    }
                    initPlayerInfo(MainCanvas.choose_manID);
                    MainCanvas.mc.setState((byte) 12);
                    MainCanvas.logon3_state = 1;
                    MainCanvas.mc.releaseUI();
                    break;
                }
                if (tempRead2 == 0) {
                    MainCanvas.mc.baseForm.addAboutForm("msg", "该用户已经存在，添加失败", (byte) 1, 175, 82);
                    MainCanvas.mc.baseForm.getSubForm().setFormXY(15, 120);
                }
                break;
            case 16778368:
                result = execDataIn.readByte();
                roleID = execDataIn.readInt();
                if (result == 1) {
                    if (MainCanvas.shyTime != null) {
                        if (MainCanvas.shyTime[MainCanvas.choose_manID] < 0) {
                            MainCanvas.playerState[MainCanvas.choose_manID] = true;
                            MainCanvas.shyTime[MainCanvas.choose_manID] = 3;
                        }
                        if (MainCanvas.shyTime[MainCanvas.choose_manID] == 0) {
                            MainCanvas.playerState[MainCanvas.choose_manID] = false;
                            MainCanvas.shyTime[MainCanvas.choose_manID] = -1;
                            Util.deleteRecord(roleID + "sc");
                        }
                    }
                    MainCanvas.mc.baseForm.setAboutForm(null);
                    break;
                }
                if (result == 0) {
                    MainCanvas.mc.setState((byte) 12);
                    MainCanvas.logon3_state = 4;
                    break;
                }
                if (result == -1) {
                    MainCanvas.mc.baseForm.setAboutForm(null);
                    MainCanvas.mc.baseForm.setMessage("氏族族长不可以删号", true);
                    break;
                }
                if (result == -2) {
                    MainCanvas.mc.baseForm.setAboutForm(null);
                    MainCanvas.mc.baseForm.setMessage(execDataIn.readUTF(), true);
                }
                break;
            case 16778624:
                Player.setCurrentXp(execDataIn.readInt());
                Player.setMaxXp(execDataIn.readInt());
                petImageId = execDataIn.readByte();
                if (petImageId != 0) {
                    MainCanvas.pet = new Pets();
                    MainCanvas.pet.imageID = petImageId;
                }
                if (!Cons.isCmobile) {
                    byte firstid = execDataIn.readByte();
                    MainCanvas.mc.NPCIndex = execDataIn.readInt();
                    if (firstid != 0 && MainCanvas.mc.NPCIndex != 0) {
                        MainCanvas.firstLogon = 1;
                    } else {
                        MainCanvas.firstLogon = 0;
                    }
                }
                temp = (short[][]) null;
                switch (MainCanvas.playerInfos[MainCanvas.choose_manID][3]) {
                    case 1:
                        temp = Cons.SKILL_SWORDMAN;
                        break;
                    case 2:
                        temp = Cons.SKILL_TAOIST;
                        break;
                    case 3:
                        temp = Cons.SKILL_APOTHECARY;
                        break;
                    case 4:
                        temp = Cons.SKILL_ASSASSIN;
                        break;
                }
                if (temp != null) {
                    try {
                        for (int j = 0; j < temp.length; j++) {
                            for (int k = 0; k < (temp[j]).length; k++) {
                                temp[j][k] = execDataIn.readShort();
                            }
                        }
                    } catch (Exception exception) {
                    }
                    switch (MainCanvas.playerInfos[MainCanvas.choose_manID][3]) {
                        case 1:
                            Cons.SKILL_SWORDMAN = temp;
                            break;
                        case 2:
                            Cons.SKILL_TAOIST = temp;
                            break;
                        case 3:
                            Cons.SKILL_APOTHECARY = temp;
                            break;
                        case 4:
                            Cons.SKILL_ASSASSIN = temp;
                            break;
                    }
                }
                break;
            case 16779392:
                MainCanvas.byCurEncryptID = execDataIn.readByte();
                byEncryptChainLen = execDataIn.readByte();
                if (MainCanvas.byEncryptChain != null) {
                    MainCanvas.byEncryptChainLast = null;
                    MainCanvas.byEncryptChainLast = MainCanvas.byEncryptChain;
                    MainCanvas.byEncryptChain = null;
                } else {
                    MainCanvas.byEncryptChainPos = 0;
                }
                MainCanvas.byEncryptChain = new byte[byEncryptChainLen];
                for (n = 0; n < byEncryptChainLen; n++) {
                    MainCanvas.byEncryptChain[n] = execDataIn.readByte();
                }
                break;
            case 16780672:
                Download.bbsUrl = execDataIn.readUTF();
                MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm().setAboutForm(null);
                Download.gotoURL(MainCanvas.mc.aMidlet, (byte) 5);
                break;
            case 16782720:
                remain = execDataIn.readInt();
                len = execDataIn.readInt();
                ba = execDataIn.readByteArray(len);
                img = Image.createImage(ba, 0, len);
                MainCanvas.mc.popCheatCheckForm(remain, img);
                break;
        }
    }

    private static void initPlayerInfo(int index) {
        MainCanvas.playerInformations[index][0] = Integer.toString(MainCanvas.playerInfos[index][0]) + "   ";
        MainCanvas.playerInformations[index][2] = Cons.STR_RACE[MainCanvas.playerInfos[index][2] - 1];
        MainCanvas.playerInformations[index][3] = Cons.STR_PROFESSION[MainCanvas.playerInfos[index][3] - 1];
    }

    public static byte[] compress(int commID) {
        String platform, phoneNum;
        ByteArray execDataOut = new ByteArray();
        switch (commID) {
            case 16782592:
                execDataOut.writeInt(cheatCheckValue);
            case 16781312:
                execDataOut.writeUTF((MainCanvas.userID == null) ? "" : MainCanvas.userID);
                execDataOut.writeUTF((MainCanvas.userKey == null) ? "" : MainCanvas.userKey);
                break;
            case 16780800:
                execDataOut.writeByte(MainCanvas.choose_manID);
                execDataOut.writeUTF(MainCanvas.playerNames[MainCanvas.choose_manID]);
                break;
            case 16777472:
                execDataOut.writeByte(Byte.parseByte(MainCanvas.serverListUrl[MainCanvas.selectServerID][3]));
                execDataOut.writeInt(Cons.VER);
                execDataOut.writeByte(1);
                execDataOut.writeUTF(MainCanvas.mc.kongMD5);
                execDataOut.writeUTF(MainCanvas.mc.userName);
                execDataOut.writeUTF(MainCanvas.mc.userPassword);
                platform = "";
                phoneNum = "";
                try {
                    phoneNum = MainCanvas.mc.aMidlet.getAppProperty("jmn").trim();
                } catch (Exception exception) {
                }
                try {
                    if (System.getProperty("microedition.platform") != null) {
                        platform = System.getProperty("microedition.platform");
                    }
                } catch (Exception exception) {
                }
                execDataOut.writeByte(1);
                execDataOut.writeUTF(platform);
                execDataOut.writeUTF(phoneNum);
                execDataOut.writeUTF(MainCanvas.mc.jarFrom);
                execDataOut.writeByte(0);
                execDataOut.writeByte(Cons.isCmobile ? 2 : 1);
                execDataOut.writeUTF(HttpConn.getUserId());
                execDataOut.writeUTF(HttpConn.localPlace);
                execDataOut.writeUTF(HttpConn.vipID);
                execDataOut.writeInt(36578353);
                execDataOut.writeUTF("S60.jar");
                break;
            case 16777728:
                execDataOut.writeByte(MainCanvas.choose_manID);
                execDataOut.writeByte(Cons.isCmobile ? 2 : 1);
                break;
            case 16778240:
                execDataOut.writeByte(MainCanvas.choose_manID);
                break;
            case 16777984:
                execDataOut.writeByte(MainCanvas.choose_manID);
                execDataOut.writeByte(MainCanvas.playerAdd[1]);
                execDataOut.writeByte(MainCanvas.playerAdd[2]);
                execDataOut.writeByte(MainCanvas.playerAdd[3]);
                execDataOut.writeUTF(MainCanvas.playerAddName);
                break;
            case 16781056:
                execDataOut.writeShort(MainCanvas.sendTick);
                MainCanvas.sendTick = 0;
                Player.CDQ_T = 0;
                break;
            case 16779264:
                execDataOut.writeByte(MainCanvas.byCurEncryptID);
                break;
        }
        return execDataOut.toByteArray();
    }
}
