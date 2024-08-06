
public class PCNPC {

    static int needOne = 0;
    static int needTwo = 0;
    static String[] propsName = null;
    static int[] propsId = null;
    static int[] propsNumber = null;
    static boolean isGold = false;
    static final int C_IN_RANK = 163708928;
    private static final int S_IN_RANK = 163709056;
    public static final int BASE_REPUTATION = 1000000000;
    public static int reputation = 0;
    public static UITable exchangeTable;
    public static int clanCurrentPage = 1;
    public static int clanTotal = 0;
    public static int clanTotalPage = 0;
    public static int MAXTOP = 0;
    public static int battleGroundIndex = 0;
    public static short[] weeks;
    public static int[] alliance;
    public static int[] horde;
    public static int allianceRate = 0;
    public static int hordeRate = 0;
    public static byte npcActType = 0;
    public static String[] npcInfo = null;
    public static int honor = 0;
    private static byte honorSign = 0;
    static boolean isArena;

    public static boolean isHonorStuff() {
        return (honorSign == 1);
    }
    static byte arenaMember = 0;

    public static void parse(int commID, byte[] data) {
        String hexID = Integer.toHexString(commID);
        System.out.println("[处理命令]:命令ID:0x" + hexID + ",命令类型：PCNPC");
        byte len;
        int i;
        UIForm form;
        int index;
        UIRim frame;
        byte commonNumber;
        UIRim rimTitle;
        byte taskNumber;
        UILabel lblTitle;
        int xx;
        byte b1, type;
        UIRim rimDown;
        int width;
        byte tb;
        UITable table;
        int startX;
        byte b2, temp;
        int k;
        String ss;
        byte b3;
        int tempNumber, height;
        byte b4;
        StringBuffer sb;
        UIRim rim;
        String sss;
        UILabel labels;
        byte st;
        String taskErr;
        byte acceptResult;
        UIForm subForm;
        UIRim rims0;
        UILabel labelBack;
        UITextArea textArea0;
        UIRim uIRim1;
        UILabel label;
        String str1;
        UILabel labelOk;
        int[][] tempXY;
        UILabel[] uilb;
        int yy;
        byte b5, number;
        int m;
        byte undoneResult;
        String undoneMsg, strDetail;
        int learnMoney;
        String strName;
        byte materialNumber;
        String tempStr1;
        byte tp;
        String title;
        byte add, create, disband;
        String[] str;
        byte success;
        String description;
        byte clannum;
        int n;
        UIForm addForm;
        UIRim uIRim2, uIRim3;
        UILabel uILabel1, lblOk, lblCancel, bclPage;
        UIMImage leftImg, rightImg;
        String addmessage, message;
        byte exitState;
        String exitInfo;
        byte memNum;
        int i1;
        String page;
        UILabel lblPage;
        UIRim rimUp;
        byte optResult, comfirmInvite;
        String msg;
        byte comfirmNum;
        int i2;
        String titleStr, items[];
        int len1, i3, p[][], i4;
        short[] tempShort;
        byte b6, j;
        String tempS;
        UITextArea textArea11;
        int i5;
        byte tempcount;
        int i6;
        byte flag;
        String message2;
        ByteArray execDataIn = new ByteArray(data);
        String mgs = "";
        switch (commID) {
            case 251682944:
                switch (execDataIn.readByte()) {
                    case 0:
                        mgs = "缠丝成功!";
                        break;
                    case 1:
                        mgs = "缠丝失败!";
                        break;
                    default:
                        mgs = "未知错误!";
                        break;
                }
                MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm()
                        .setAboutForm((UIForm) null);
                MainCanvas.setMessage(MainCanvas.mc.baseForm.getCurrentFocusForm(), mgs);
                break;
            case 251678848:
                len = execDataIn.readByte();
                if (len == 0) {
                    switch (execDataIn.readByte()) {
                        case 1:
                            mgs = "您没有金制秘线，不能合成装备";
                            break;
                        case 2:
                            mgs = "您没有银制秘线，不能合成装备";
                            break;
                        case 3:
                            mgs = "您没有符合条件的装备";
                            break;
                        default:
                            mgs = "未知错误";
                            break;
                    }
                    MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm()
                            .setAboutForm((UIForm) null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm
                            .getCurrentFocusForm(), mgs);
                    break;
                }
                propsId = null;
                propsName = null;
                propsNumber = null;
                propsId = new int[len];
                propsName = new String[len];
                propsNumber = new int[len];
                for (i = 0; i < len; i++) {
                    propsId[i] = execDataIn.readByte();
                    propsNumber[i] = execDataIn.readByte();
                    propsName[i] = execDataIn.readUTF();
                }
                initSilkForm(len);
                break;
            case 251662464:
                MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
                len = execDataIn.readByte();
                if (len == -2) {
                    MainCanvas.setMessage(MainCanvas.mc.baseForm.getCurrentFocusForm(), "您需要先加入一个氏族才能查看！");
                    break;
                }
                if (len == 0) {
                    MainCanvas.setMessage(MainCanvas.mc.baseForm.getCurrentFocusForm(), "氏族现在没有徽章");
                    break;
                }
                if (len == -1) {
                    MainCanvas.setMessage(MainCanvas.mc.baseForm.getCurrentFocusForm(), "您不是族长");
                    break;
                }
                form = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "have_badge");
                form.setBackGround((byte) 9);
                frame = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte) 4);
                rimTitle = new UIRim(0, 9, 166, 20, (byte) 7);
                lblTitle = new UILabel(0, 12, 176, 0, "氏族现有徽章", 15718814, (byte) 1, (byte) 0);
                rimDown = new UIRim(0, 29, 166, 160, (byte) 0);
                form.addComponent(frame);
                form.addComponentInCenter(rimTitle, (byte) 2);
                form.addComponentInCenter(lblTitle, (byte) 2);
                form.addComponentInCenter(rimDown, (byte) 2);
                if (MainCanvas.isDistributionBadge) {
                    form.addLeftButton("操作");
                }
                form.addRightButton("返回");
                table = new UITable(0, 31, 161, 152, len, 1, (len > 7) ? 7 : len, (byte) 0, (byte) 3);
                table.setSingleWH(table.singleWidth, (byte) 22, false);
                propsId = null;
                propsName = null;
                propsNumber = null;
                propsId = new int[len];
                propsName = new String[len];
                propsNumber = new int[len];
                for (k = 0; k < len; k++) {
                    propsId[k] = execDataIn.readInt();
                    propsName[k] = execDataIn.readUTF();
                    propsNumber[k] = execDataIn.readInt();
                    table.setItem(propsName[k] + " 有" + propsNumber[k] + "个", k, 65280);
                }
                form.addComponentInCenter(table, (byte) 2);
                MainCanvas.mc.tables[1] = null;
                MainCanvas.mc.tables[1] = table;
                MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
                MainCanvas.mc.baseForm.getCurrentFocusForm().setAboutForm(form);
                MainCanvas.mc.baseForm.setFocus(true);
                MainCanvas.mc.setNPCSubState((byte) 4);
                break;
            case 251666560:
                MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
                switch (execDataIn.readByte()) {
                    case 0:
                        mgs = "分配徽章成功!";
                        break;
                    case 1:
                        mgs = "分配徽章失败!";
                        break;
                    default:
                        mgs = "未知错误!";
                        break;
                }
                MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm()
                        .setAboutForm((UIForm) null);
                MainCanvas.setMessage(MainCanvas.mc.baseForm.getCurrentFocusForm(), mgs);
                break;
            case 163709056:
                switch (execDataIn.readByte()) {
                    case 0:
                        mgs = "您已经进入攻城队列，稍候会将您传送至天地心。";
                        break;
                    case 1:
                        mgs = "您已经排队。请不要重新申请排队。";
                        break;
                    case 2:
                        mgs = "不在攻城时间，不能参加攻城。";
                        break;
                    case 3:
                        mgs = "攻城已经开始，您将传送至天地心。";
                        break;
                    case 4:
                        mgs = "您的等级不够，不能参加攻城。";
                        break;
                    default:
                        mgs = "未知错误。";
                        break;
                }
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.setMessage(MainCanvas.mc.baseForm, mgs);
                break;
            case 163643520:
                index = execDataIn.readByte();
                if (index == 1) {
                    int i7 = execDataIn.readByte();
                    weeks = new short[i7];
                    alliance = new int[i7];
                    horde = new int[i7];
                    for (int i8 = 0; i8 < i7; i8++) {
                        weeks[i8] = execDataIn.readShort();
                        alliance[i8] = execDataIn.readInt();
                        horde[i8] = execDataIn.readInt();
                    }
                    MainCanvas.mc
                            .setNPCSubState((byte) 27);
                    MainCanvas.mc.releaseUI();
                    break;
                }
                if (index == 2) {
                    allianceRate = execDataIn.readInt();
                    hordeRate = execDataIn.readInt();
                    MainCanvas.mc
                            .setNPCSubState((byte) 28);
                    MainCanvas.mc.releaseUI();
                }
                break;
            case 150995072:
                commonNumber = 0;
                taskNumber = 0;
                commonNumber = execDataIn.readByte();
                taskNumber = execDataIn.readByte();
                if (commonNumber != 0 || taskNumber != 0) {
                    String[] tmpStrs = new String[commonNumber + taskNumber];
                    int[] tmpItem = new int[commonNumber + taskNumber];
                    byte[] state = new byte[commonNumber + taskNumber];
                    for (k = 0; k < commonNumber; k++) {
                        String tmpSs;
                        tmpItem[k] = execDataIn.readInt();
                        tmpStrs[k] = execDataIn.readUTF();
                        switch (tmpItem[k]) {
                            case 31:
                            case 32:
                            case 33:
                                tmpSs = execDataIn.readUTF();
                                npcInfo = Util.splitToken(tmpSs, '~');
                                npcActType = (byte) tmpItem[k];
                                break;
                        }
                        state[k] = -1;
                    }
                    for (k = commonNumber; k < commonNumber + taskNumber; k++) {
                        tmpItem[k] = execDataIn.readInt() + 120;
                        tmpStrs[k] = execDataIn.readUTF();
                        state[k] = (byte) (execDataIn.readByte() & 0xF);
                        state[k] = (byte) (state[k] | (byte) (execDataIn.readByte() << 4 & 0xF0));
                        if ((state[k] & 0xF) == 0) {
                            tmpStrs[k] = tmpStrs[k] + "(任务)";
                        } else if ((state[k] & 0xF) == 1) {
                            tmpStrs[k] = tmpStrs[k] + "(未完成)";
                        } else if ((state[k] & 0xF) == 2) {
                            tmpStrs[k] = tmpStrs[k] + "(已完成)";
                        }
                    }
                    MainCanvas.NPCMenu = new UIMenu(5, 48, 164, 142, null, tmpStrs);
                    MainCanvas.NPCMenu.setMenuItems(tmpItem, tmpStrs, state);
                    MainCanvas.NPCMenu.setRimStyle((byte) 0);
                    MainCanvas.NPCMenu.setFlushType((byte) 1);
                    MainCanvas.mc.releaseUI();
                    MainCanvas.mc.setGameState((byte) 3);
                    MainCanvas.mc.setNPCSubState((byte) 0);
                    break;
                }
                MainCanvas.mc.releaseUI();
                xx = ObjManager.currentTarget.x - Map.currentWindowX;
                width = 0;
                startX = 0;
                width = 83;
                if (xx <= MainCanvas.screenW >> 1) {
                    startX = 5;
                } else {
                    startX = 88;
                }
                MainCanvas.mc.baseForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "");
                MainCanvas.mc.baseForm.setBackGround((byte) 9);
                ss = execDataIn.readUTF();
                height = (ss.length() * MainCanvas.font[1].charWidth('正') / width + 1) * (MainCanvas.font[1].getHeight() + 4);
                rim = new UIRim(startX, 84, width, height, (byte) 1);
                labels = new UILabel(rim.positionX + 2, rim.positionY + 2, width - 2, 0, ss, 15653280, (byte) 0, (byte) 0);
                rim.height = labels.height + 10;
                MainCanvas.mc.baseForm.addComponent(rim);
                MainCanvas.mc.baseForm.addComponent(labels);
                MainCanvas.mc.setGameState((byte) 8);
                MainCanvas.mc.setOtherSubState((byte) 0);
                break;
            case 152043648:
                honorSign = execDataIn.readByte();
                if (honorSign == -1) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "您的氏族等级不够,不能使用该氏族商店");
                    break;
                }
                MainCanvas.mc.shopStuffPrice = null;
                MainCanvas.mc.shopStuffPrice = new int[18];
                MainCanvas.mc.grids[0] = new UIGrid(0, 21, (byte) 2, (byte) 9, (byte) 2, MainCanvas.mImgStuff);
                if (honorSign == 3 || honorSign == 4) {
                    propsId = null;
                    propsName = null;
                    propsNumber = null;
                    propsId = new int[18];
                    propsName = new String[18];
                    propsNumber = new int[18];
                }
                for (b1 = 0; b1 < 18; b1 = (byte) (b1 + 1)) {
                    MainCanvas.mc.grids[0].setGridDetail(b1, execDataIn
                            .readShort(), execDataIn
                            .readByte(), execDataIn
                            .readByte(), execDataIn
                            .readUTF(), execDataIn
                            .readByte(), execDataIn.readShort(), (byte) 1, (byte) 0);
                    if (honorSign == 3) {
                        propsId[b1] = execDataIn.readInt();
                        propsName[b1] = execDataIn.readUTF();
                        propsNumber[b1] = execDataIn.readInt();
                    } else if (honorSign == 4) {
                        propsId[b1] = execDataIn.readInt();
                        propsNumber[b1] = execDataIn.readInt();
                    } else {
                        if (honorSign == 5) {
                            (MainCanvas.mc.grids[0]).isLmtstoren[b1] = true;
                        }
                        MainCanvas.mc.shopStuffPrice[b1] = execDataIn.readInt();
                    }
                }
                if (honorSign == 3) {
                    needOne = execDataIn.readInt();
                } else if (honorSign == 4) {
                    needOne = execDataIn.readInt();
                    needTwo = execDataIn.readInt();
                } else {
                    (Player.getInstance()).money = execDataIn.readInt();
                    (Player.getInstance()).money = ((Player.getInstance()).money > 1999999999) ? 1999999999 : (Player.getInstance()).money;
                    reputation = execDataIn.readInt();
                    honor = execDataIn.readInt();
                    if (honorSign != 5) {
                        for (int i7 = 0; i7 < 18; i7++) {
                            if ((MainCanvas.mc.grids[0]).stuffNumber[i7] > 0) {
                                Util.shopSign[i7] = true;
                            } else {
                                Util.shopSign[i7] = false;
                            }
                        }
                    }
                }
                MainCanvas.mc.setNPCSubState((byte) 2);
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.mc.baseForm = null;
                break;
            case 152048000:
                MainCanvas.mc.tradePlace = execDataIn.readByte();
                if (MainCanvas.dramatisPackage.stuffId[MainCanvas.mc.tradePlace] != 0) {
                    MainCanvas.dramatisPackage.stuffNumber[MainCanvas.mc.tradePlace] = (byte) (MainCanvas.dramatisPackage.stuffNumber[MainCanvas.mc.tradePlace] + MainCanvas.mc.tradeNumber);
                } else {
                    MainCanvas.dramatisPackage.setGridDetail(MainCanvas.mc.tradePlace, MainCanvas.mc.grids[0]
                            .getCurrentId(), MainCanvas.mc.grids[0]
                            .getCurrentImageId(), MainCanvas.mc.tradeNumber, MainCanvas.mc.grids[0]
                            .getCurrentName(), MainCanvas.mc.grids[0]
                            .getCurrentNameLevel(), MainCanvas.mc.grids[0]
                            .getCurrentLittleType(), MainCanvas.mc.grids[0]
                            .getCurrentTrade(), (byte) 0);
                }
                MainCanvas.mc.packageStuffPrice[MainCanvas.mc.tradePlace] = execDataIn
                        .readInt();
                MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
                if (honorSign == 3) {
                    byte point = execDataIn.readByte();
                    if (MainCanvas.dramatisPackage.stuffNumber[point] == 1) {
                        MainCanvas.dramatisPackage.setGridNull(point);
                    } else {
                        MainCanvas.dramatisPackage.stuffNumber[point] = (byte) (MainCanvas.dramatisPackage.stuffNumber[point] - 1);
                    }
                    mgs = "徽章换取装备成功!";
                    needOne -= propsNumber[MainCanvas.mc.grids[0].getCurrentPointer()];
                    MainCanvas.setMessage(MainCanvas.mc.baseForm.getCurrentFocusForm(), mgs);
                } else if (honorSign == 4) {
                    mgs = "兑换成功!";
                    needOne -= propsId[MainCanvas.mc.grids[0].getCurrentPointer()];
                    needTwo -= propsNumber[MainCanvas.mc.grids[0].getCurrentPointer()];
                    MainCanvas.setMessage(MainCanvas.mc.baseForm.getCurrentFocusForm(), mgs);
                } else {
                    byte b = MainCanvas.mc.grids[0].getCurrentPointer();
                    if (Util.shopSign[b]) {
                        if (MainCanvas.mc.grids[0].getCurrentNumber() == MainCanvas.mc.tradeNumber) {
                            MainCanvas.mc.grids[0].setCurrentNull();
                            MainCanvas.stuffName.setStr(" ");
                        } else {
                            (MainCanvas.mc.grids[0]).stuffNumber[b] = (byte) ((MainCanvas.mc.grids[0]).stuffNumber[b] - MainCanvas.mc.tradeNumber);
                        }
                    }
                    int price = MainCanvas.mc.shopStuffPrice[MainCanvas.mc.grids[0].getCurrentPointer()];
                    if (isHonorStuff()) {
                        honor -= price * MainCanvas.mc.tradeNumber;
                        MainCanvas.mc.texts[1].setLabel(honor + "");
                    } else if (price > 1000000000) {
                        price -= 1000000000;
                        reputation -= price * MainCanvas.mc.tradeNumber;
                        MainCanvas.mc.texts[1].setLabel(reputation + "");
                    } else {
                        (Player.getInstance()).money -= price * MainCanvas.mc.tradeNumber;
                        MainCanvas.mc.texts[1].setLabel((Player.getInstance()).money + "");
                    }
                }
                if (honorSign == 5) {
                    for (b1 = 0; b1 < 18; b1 = (byte) (b1 + 1)) {
                        MainCanvas.mc.grids[0].setSomeoneNumber(b1, execDataIn.readByte());
                    }
                }
                break;
            case 152048256:
                type = 1;
                if (honorSign == 3) {
                    switch (execDataIn.readByte()) {
                        case 0:
                            mgs = "兑换不成功，资金不足或包裹已满!";
                            break;
                        case 1:
                            mgs = "您的兑换操作会降低氏族等级，请积累更多氏族声望再来兑换。";
                            break;
                    }
                } else if (honorSign == 4) {
                    mgs = execDataIn.readUTF();
                } else {
                    try {
                        type = execDataIn.readByte();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    switch (type) {
                        case 0:
                            mgs = "您的氏族已经降级,不能使用该氏族商店";
                            break;
                        case 1:
                            mgs = "购买不成功，资金不足或包裹已满!";
                            break;
                        case 2:
                            mgs = "购买不成功，商店中该类商品已经售空!";
                            break;
                    }
                    if (honorSign == 5 && type != 0) {
                        byte b;
                        for (b = 0; b < 18; b = (byte) (b + 1)) {
                            MainCanvas.mc.grids[0].setSomeoneNumber(b, execDataIn.readByte());
                        }
                    }
                }
                MainCanvas.mc.baseForm.addAboutForm("cannotBuy", mgs, (byte) 1, MainCanvas.screenW - 20, 0);
                break;
            case 152052096:
                (Player.getInstance()).money += MainCanvas.mc.packageStuffPrice[MainCanvas.dramatisPackage
                        .getCurrentPointer()] * MainCanvas.mc.tradeNumber;
                if (MainCanvas.mc.tradeNumber < MainCanvas.dramatisPackage
                        .getCurrentNumber()) {
                    MainCanvas.dramatisPackage.stuffNumber[MainCanvas.dramatisPackage
                            .getCurrentPointer()] = (byte) (MainCanvas.dramatisPackage.stuffNumber[MainCanvas.dramatisPackage.getCurrentPointer()] - MainCanvas.mc.tradeNumber);
                } else {
                    MainCanvas.dramatisPackage.setCurrentNull();
                    MainCanvas.mc.texts[0].setLabel("0");
                    MainCanvas.stuffName.setStr("  ");
                    MainCanvas.mc.packageStuffPrice[MainCanvas.dramatisPackage
                            .getCurrentPointer()] = 0;
                }
                if (getHonorSign() == 3 || getHonorSign() == 4) {
                    MainCanvas.mc.labels[3].setStr("玩家金钱");
                }
                MainCanvas.mc.texts[1].setLabel((Player.getInstance()).money + "");
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                break;
            case 152052352:
                tb = execDataIn.readByte();
                switch (tb) {
                    case -1:
                        MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                        MainCanvas.setMessage(MainCanvas.mc.baseForm, "您的氏族等级不够,不能使用该氏族商店");
                        break;
                    case 0:
                        MainCanvas.setMessage(MainCanvas.mc.baseForm, "出售失败");
                        break;
                    case 1:
                        MainCanvas.setMessage(MainCanvas.mc.baseForm, "该物品不能出售");
                        break;
                }
                break;
            case 154140800:
                MainCanvas.fixAllPrice = 0;
                for (b2 = 0; b2 < 8; b2 = (byte) (b2 + 1)) {
                    MainCanvas.equipStuffId[b2] = execDataIn.readShort();
                    MainCanvas.equipImageId[b2] = execDataIn.readByte();
                    MainCanvas.equipSruffName[b2] = execDataIn.readUTF();
                    MainCanvas.fixPrice[b2] = execDataIn.readInt();
                    MainCanvas.fixAllPrice += MainCanvas.fixPrice[b2];
                    MainCanvas.equipStuffNameLevel[b2] = execDataIn.readByte();
                }
                (Player.getInstance()).money = execDataIn.readInt();
                if ((Player.getInstance()).money == -1) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "您的氏族等级不够,不能使用氏族修理所");
                    break;
                }
                (Player.getInstance()).money = ((Player.getInstance()).money > 1999999999) ? 1999999999 : (Player.getInstance()).money;
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.mc.releaseUI();
                MainCanvas.mc.setNPCSubState((byte) 3);
                break;
            case 154141056:
                temp = execDataIn.readByte();
                (Player.getInstance()).money = execDataIn.readInt();
                (Player.getInstance()).money = ((Player.getInstance()).money > 1999999999) ? 1999999999 : (Player.getInstance()).money;
                MainCanvas.mc.texts[2].setLabel((Player.getInstance()).money + "");
                if (temp == 0) {
                    if (MainCanvas.fixPlace < 8) {
                        MainCanvas.fixAllPrice -= MainCanvas.fixPrice[MainCanvas.fixPlace];
                        MainCanvas.fixPrice[MainCanvas.fixPlace] = 0;
                        MainCanvas.mc.texts[0]
                                .setLabel(MainCanvas.fixPrice[MainCanvas.fixPlace] + "");
                        MainCanvas.equipImageId[MainCanvas.fixPlace] = 0;
                        MainCanvas.equipStuffId[MainCanvas.fixPlace] = 0;
                        MainCanvas.equipSruffName[MainCanvas.fixPlace] = "";
                    } else {
                        MainCanvas.fixAllPrice = 0;
                        byte b;
                        for (b = 0; b < 8; b = (byte) (b + 1)) {
                            MainCanvas.fixPrice[b] = 0;
                            MainCanvas.equipImageId[b] = 0;
                            MainCanvas.equipStuffId[b] = 0;
                            MainCanvas.equipSruffName[b] = "";
                        }
                        MainCanvas.mc.texts[0].setLabel("0");
                    }
                    MainCanvas.mc.labels[0].setStr("");
                    MainCanvas.mc.texts[1].setLabel(MainCanvas.fixAllPrice + "");
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    break;
                }
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.setMessage(MainCanvas.mc.baseForm, "金钱不足，修理失败");
                break;
            case 161480832:
                MainCanvas.mc.grids[0] = new UIGrid(0, 18, (byte) 4, (byte) 9, (byte) 3, MainCanvas.mImgStuff);
                for (b3 = 0; b3 < 36; b3 = (byte) (b3 + 1)) {
                    MainCanvas.mc.grids[0].setGridDetail(b3, execDataIn.readShort(), execDataIn
                            .readByte(), execDataIn.readByte(), execDataIn
                            .readUTF(), execDataIn.readByte(), execDataIn
                            .readShort(), execDataIn.readByte(), execDataIn
                            .readByte());
                }
                (Player.getInstance()).money = execDataIn.readInt();
                if ((Player.getInstance()).money == -1) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "您的氏族等级不够,不能使用氏族仓库");
                    break;
                }
                (Player.getInstance()).money = ((Player.getInstance()).money > 1999999999) ? 1999999999 : (Player.getInstance()).money;
                MainCanvas.mc.storeMoney = execDataIn.readInt();
                MainCanvas.mc.setNPCSubState((byte) 9);
                MainCanvas.mc.baseForm = null;
                break;
            case 161484928:
                temp = execDataIn.readByte();
                if (temp == -1) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "您的氏族等级不够,不能使用氏族仓库");
                    break;
                }
                if (temp == 0) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.mc.grids[0].changeStuff((short) (MainCanvas.mc.grids[0]).stuffPlace,
                            (short) MainCanvas.mc.grids[0].getCurrentPointer());
                    MainCanvas.stuffName
                            .setColor(Cons.STUFF_NAME_COLOR[MainCanvas.mc.grids[0]
                            .getCurrentNameLevel()]);
                    MainCanvas.stuffName.setStr(MainCanvas.mc.grids[0]
                            .getCurrentName());
                    break;
                }
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.setMessage(MainCanvas.mc.baseForm, "请求失败，请重试");
                break;
            case 161489024:
                temp = execDataIn.readByte();
                if (temp == -1) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "此物品不能存入氏族仓库");
                    break;
                }
                if (temp == -2) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "您的氏族等级不够,不能使用氏族仓库");
                    break;
                }
                if (temp == -3) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    String tmpss = execDataIn.readUTF();
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, tmpss);
                    break;
                }
                tempNumber = MainCanvas.mc.grids[0].getSomeoneNumber(temp);
                if (temp < 36) {
                    if (tempNumber > 0) {
                        MainCanvas.mc.grids[0].setSomeoneNumber(temp, (byte) (tempNumber + MainCanvas.mc.stNumber));
                    } else {
                        MainCanvas.mc.grids[0].setGridDetail(temp, MainCanvas.dramatisPackage
                                .getCurrentId(), MainCanvas.dramatisPackage
                                .getCurrentImageId(), MainCanvas.mc.stNumber, MainCanvas.dramatisPackage
                                .getCurrentName(), MainCanvas.dramatisPackage
                                .getCurrentNameLevel(), MainCanvas.dramatisPackage
                                .getCurrentLittleType(), MainCanvas.dramatisPackage
                                .getCurrentTrade(), (byte) 0);
                    }
                    if (MainCanvas.mc.stNumber == MainCanvas.dramatisPackage
                            .getCurrentNumber()) {
                        MainCanvas.dramatisPackage.setCurrentNull();
                        MainCanvas.stuffName.setStr(" ");
                    } else {
                        MainCanvas.dramatisPackage.stuffNumber[MainCanvas.mc.stPlace] = (byte) (MainCanvas.dramatisPackage.stuffNumber[MainCanvas.mc.stPlace] - MainCanvas.mc.stNumber);
                    }
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    break;
                }
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.setMessage(MainCanvas.mc.baseForm, "您的仓库已满，无法再存入物品");
                break;
            case 161493120:
                temp = execDataIn.readByte();
                if (temp == -1) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "您的氏族等级不够,不能使用氏族仓库");
                    break;
                }
                tempNumber = MainCanvas.dramatisPackage.getSomeoneNumber(temp);
                if (temp < 36) {
                    if (tempNumber > 0) {
                        MainCanvas.dramatisPackage.setSomeoneNumber(temp, (byte) (tempNumber + MainCanvas.mc.stNumber));
                    } else {
                        MainCanvas.dramatisPackage.setGridDetail(temp, MainCanvas.mc.grids[0]
                                .getCurrentId(), MainCanvas.mc.grids[0]
                                .getCurrentImageId(), MainCanvas.mc.stNumber, MainCanvas.mc.grids[0]
                                .getCurrentName(), MainCanvas.mc.grids[0]
                                .getCurrentNameLevel(), MainCanvas.mc.grids[0]
                                .getCurrentLittleType(), MainCanvas.mc.grids[0]
                                .getCurrentTrade(), (byte) 0);
                    }
                    if (MainCanvas.mc.stNumber == MainCanvas.mc.grids[0]
                            .getCurrentNumber()) {
                        MainCanvas.mc.grids[0].setCurrentNull();
                        MainCanvas.stuffName.setStr(" ");
                    } else {
                        (MainCanvas.mc.grids[0]).stuffNumber[MainCanvas.mc.stPlace] = (byte) ((MainCanvas.mc.grids[0]).stuffNumber[MainCanvas.mc.stPlace] - MainCanvas.mc.stNumber);
                    }
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    break;
                }
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.setMessage(MainCanvas.mc.baseForm, "背包已满不能取出");
                break;
            case 161497216:
                temp = execDataIn.readByte();
                if (temp == -1) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "您的氏族等级不够,不能使用氏族仓库");
                    break;
                }
                if (temp == 0) {
                    if (MainCanvas.mc.discardPlace == 0) {
                        MainCanvas.dramatisPackage.setCurrentNull();
                        MainCanvas.stuffName.setStr("  ");
                    } else {
                        MainCanvas.mc.grids[0].setCurrentNull();
                        MainCanvas.stuffName.setStr("  ");
                    }
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.stuffName.setStr((String) null);
                    break;
                }
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.setMessage(MainCanvas.mc.baseForm, "请求失败，请重试");
                break;
            case 161501312:
                for (b4 = 0; b4 < 36; b4 = (byte) (b4 + 1)) {
                    MainCanvas.mc.grids[0].setGridDetail(b4, execDataIn.readShort(), execDataIn
                            .readByte(), execDataIn.readByte(), execDataIn
                            .readUTF(), execDataIn.readByte(), execDataIn
                            .readShort(), execDataIn.readByte(), execDataIn
                            .readByte());
                }
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                if (MainCanvas.mc.baseForm.focusComponent == MainCanvas.mc.grids[0]) {
                    MainCanvas.stuffName.setStr(MainCanvas.mc.grids[0]
                            .getCurrentName());
                    MainCanvas.stuffName
                            .setColor(Cons.STUFF_NAME_COLOR[MainCanvas.mc.grids[0]
                            .getCurrentNameLevel()]);
                }
                break;
            case 161505408:
            case 161509504:
                temp = execDataIn.readByte();
                if (temp == -1) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "您的氏族等级不够,不能使用氏族仓库");
                    break;
                }
                if (temp == 0) {
                    MainCanvas.mc.texts[1].setLabel(execDataIn.readInt() + "");
                    MainCanvas.mc.texts[0].setLabel(execDataIn.readInt() + "");
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    break;
                }
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.setMessage(MainCanvas.mc.baseForm, "请求失败，请重试");
                break;
            case 162529408:
                MainCanvas.mc.mImages[0] = null;
                MainCanvas.mc.mImages[1] = null;
                MainCanvas.mc.mImages[2] = null;
                MainCanvas.mc.taskType = execDataIn.readByte();
                MainCanvas.mc.taskDetail[3] = execDataIn.readUTF();
                MainCanvas.mc.taskDetail[4] = execDataIn.readUTF();
                MainCanvas.mc.taskDetail[0] = execDataIn.readUTF();
                MainCanvas.mc.taskStuffId[0] = execDataIn.readShort();
                MainCanvas.mc.taskStuffImageId[0] = (byte) (execDataIn.readByte() - 1);
                MainCanvas.mc.taskDetail[1] = execDataIn.readUTF();
                MainCanvas.mc.taskThingNumber[0] = execDataIn.readByte();
                MainCanvas.mc.taskStuffId[1] = execDataIn.readShort();
                MainCanvas.mc.taskStuffImageId[1] = (byte) (execDataIn.readByte() - 1);
                MainCanvas.mc.taskDetail[2] = execDataIn.readUTF();
                MainCanvas.mc.taskThingNumber[1] = execDataIn.readByte();
                MainCanvas.mc.taskMoney = execDataIn.readInt();
                MainCanvas.mc.taskExperiecce = execDataIn.readInt();
                if (MainCanvas.mc.taskStuffId[0] == 0 && MainCanvas.mc.taskStuffId[1] != 0) {
                    MainCanvas.mc.taskStuffId[0] = MainCanvas.mc.taskStuffId[1];
                    MainCanvas.mc.taskStuffId[1] = 0;
                    MainCanvas.mc.taskStuffImageId[0] = MainCanvas.mc.taskStuffImageId[1];
                    MainCanvas.mc.taskDetail[1] = MainCanvas.mc.taskDetail[2];
                    MainCanvas.mc.taskThingNumber[0] = MainCanvas.mc.taskThingNumber[1];
                }
                MainCanvas.mc.setNPCSubState((byte) 120);
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.mc.releaseUI();
                break;
            case 162529664:
                sb = new StringBuffer(MainCanvas.NPCMenu.strs[MainCanvas.NPCMenu.getCurrentPointer()]);
                sb.delete(sb.length() - 4, sb.length());
                sss = "";
                st = 0;
                try {
                    st = execDataIn.readByte();
                } catch (Exception exception) {
                }
                if (st == 0) {
                    sss = sss + "(任务)";
                } else if (st == 1) {
                    sss = sss + "(未完成)";
                } else if (st == 2) {
                    sss = sss + "(已完成)";
                }
                MainCanvas.NPCMenu.strs[MainCanvas.NPCMenu.getCurrentPointer()] = sb
                        .toString() + sss;
                MainCanvas.NPCMenu.setCurrnetRealState(st);
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.mc.setNPCSubState((byte) 0);
                MainCanvas.mc.releaseUI();
                break;
            case 162529920:
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                taskErr = null;
                acceptResult = execDataIn.readByte();
                switch (acceptResult) {
                    case 0:
                        taskErr = "接受失败，任务已满";
                        break;
                    case 1:
                        taskErr = "接受失败，背包已满";
                        break;
                    case 2:
                        taskErr = "接受失败，该任务已不存在";
                        break;
                    default:
                        taskErr = "接受失败，原因未知";
                        break;
                }
                MainCanvas.mc.baseForm.addAboutForm("msg", taskErr, (byte) 1, MainCanvas.screenW - 30, 0);
                break;
            case 162530176:
                tempNumber = execDataIn.readByte();
                if (!MainCanvas.mc.transferstatusQuest) {
                    MainCanvas.mc.releaseUI();
                }
                if (tempNumber == 0) {
                    if (MainCanvas.mc.transferstatusQuest) {
                        MainCanvas.setMessage(MainCanvas.mc.baseForm, "您还没有任务");
                        MainCanvas.mc.transferstatusQuest = false;
                        return;
                    }
                    MainCanvas.mc.tables[0] = new UITable(0, 30, 145, 160, 0, 0, 0, (byte) 0, (byte) 2);
                    MainCanvas.mc.tables[0].setAutoHeight(false);
                } else {
                    String[] s = new String[tempNumber];
                    byte[] state = new byte[tempNumber];
                    byte[] colorSign = new byte[tempNumber];
                    MainCanvas.mc.taskId = new int[tempNumber];
                    byte b;
                    for (b = 0; b < tempNumber; b = (byte) (b + 1)) {
                        MainCanvas.mc.taskId[b] = execDataIn.readInt();
                        s[b] = execDataIn.readUTF();
                        state[b] = execDataIn.readByte();
                        colorSign[b] = execDataIn.readByte();
                        if (state[b] == 0) {
                            s[b] = s[b] + "(任务)";
                        } else if (state[b] == 1) {
                            s[b] = s[b] + "(未完成)";
                        } else if (state[b] == 2) {
                            s[b] = s[b] + "(已完成)";
                        }
                    }
                    MainCanvas.mc.releaseUI();
                    MainCanvas.mc.tables[0] = new UITable(0, 30, 135, 160, tempNumber, 1, tempNumber, (byte) 0, (byte) 2);
                    MainCanvas.mc.tables[0].setSingleWH((short) 120, (byte) 20, false);
                    for (int i7 = 0; i7 < tempNumber; i7++) {
                        int color = 15718814;
                        if (colorSign[i7] == 0) {
                            color = 65280;
                        }
                        MainCanvas.mc.tables[0].setItem(s[i7], i7, color);
                    }
                }
                MainCanvas.mc.setGameState((byte) 1);
                MainCanvas.mc.setRightMenuSubState(4);
                break;
            case 162530432:
                MainCanvas.mc.mImages[0] = null;
                MainCanvas.mc.mImages[1] = null;
                MainCanvas.mc.mImages[2] = null;
                subForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "detail0");
                subForm.setStyle((byte) 0);
                rims0 = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte) 4);
                labelBack = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 0, (byte) 0);
                MainCanvas.mc.taskType = execDataIn.readByte();
                MainCanvas.mc.taskDetail[3] = execDataIn.readUTF();
                MainCanvas.mc.taskDetail[4] = execDataIn.readUTF();
                MainCanvas.mc.taskDetail[0] = execDataIn.readUTF();
                MainCanvas.mc.taskStuffId[0] = execDataIn.readShort();
                MainCanvas.mc.taskStuffImageId[0] = (byte) (execDataIn.readByte() - 1);
                MainCanvas.mc.taskDetail[1] = execDataIn.readUTF();
                MainCanvas.mc.taskThingNumber[0] = execDataIn.readByte();
                MainCanvas.mc.taskStuffId[1] = execDataIn.readShort();
                MainCanvas.mc.taskStuffImageId[1] = (byte) (execDataIn.readByte() - 1);
                MainCanvas.mc.taskDetail[2] = execDataIn.readUTF();
                MainCanvas.mc.taskThingNumber[1] = execDataIn.readByte();
                MainCanvas.mc.taskMoney = execDataIn.readInt();
                MainCanvas.mc.taskExperiecce = execDataIn.readInt();
                if (MainCanvas.mc.taskStuffId[0] == 0 && MainCanvas.mc.taskStuffId[1] != 0) {
                    MainCanvas.mc.taskStuffId[0] = MainCanvas.mc.taskStuffId[1];
                    MainCanvas.mc.taskStuffId[1] = 0;
                    MainCanvas.mc.taskStuffImageId[0] = MainCanvas.mc.taskStuffImageId[1];
                    MainCanvas.mc.taskDetail[1] = MainCanvas.mc.taskDetail[2];
                    MainCanvas.mc.taskThingNumber[0] = MainCanvas.mc.taskThingNumber[1];
                }
                textArea0 = new UITextArea(5, 6, 164, 112, "　" + MainCanvas.mc.taskDetail[0]);
                uIRim1 = new UIRim(5, 120, 164, 70, (byte) 0);
                label = new UILabel(uIRim1.positionX + 4, uIRim1.positionY + 4, 164, 0, "您将得到：", 16316576, (byte) 0, (byte) 0);
                textArea0.setColor(10981736);
                textArea0.setCanFocus(false);
                str1 = null;
                if (MainCanvas.mc.taskStuffId[0] == 0) {
                    str1 = "  ";
                } else {
                    str1 = "查看";
                }
                labelOk = new UILabel(0, 0, 0, 0, str1, 15718814, (byte) 0, (byte) 0);
                subForm.addComponent(rims0);
                subForm.addComponent(textArea0);
                subForm.addComponent(uIRim1);
                subForm.addComponent(label);
                subForm.addComponentInCenter(labelBack, (byte) 6);
                subForm.addComponentInCenter(labelOk, (byte) 5);
                tempXY = new int[][]{{8, 144}, {87, 144}, {8, 170}, {87, 170}};
                if (MainCanvas.mc.taskStuffId[0] != 0) {
                    MainCanvas.mc.mImages[0] = new UIMImage(tempXY[0][0], tempXY[0][1], 18, 18, MainCanvas.mImgStuff, (byte) 0);
                    MainCanvas.mc.mImages[0].setHaveRim(true);
                    (MainCanvas.mc.mImages[0]).index = 0;
                    MainCanvas.mc.mImages[0]
                            .setCurrentFrame(MainCanvas.mc.taskStuffImageId[0]);
                    MainCanvas.mc.mImages[0]
                            .setNumber(MainCanvas.mc.taskThingNumber[0]);
                }
                if (MainCanvas.mc.taskStuffId[1] != 0) {
                    MainCanvas.mc.mImages[1] = new UIMImage(tempXY[1][0], tempXY[1][1], 18, 18, MainCanvas.mImgStuff, (byte) 0);
                    MainCanvas.mc.mImages[1].setHaveRim(true);
                    (MainCanvas.mc.mImages[1]).index = 1;
                    MainCanvas.mc.mImages[1]
                            .setCurrentFrame(MainCanvas.mc.taskStuffImageId[1]);
                    MainCanvas.mc.mImages[1]
                            .setNumber(MainCanvas.mc.taskThingNumber[1]);
                }
                uilb = new UILabel[6];
                if (MainCanvas.mc.taskStuffId[1] != 0) {
                    uilb[0] = new UILabel(28, 145, 0, 0,
                            (MainCanvas.mc.taskDetail[1].length() > 4) ? (MainCanvas.mc.taskDetail[1]
                            .substring(0, 3) + "..") : MainCanvas.mc.taskDetail[1], 15718814, (byte) 0, (byte) 0);
                    uilb[1] = new UILabel(110, 145, 0, 0,
                            (MainCanvas.mc.taskDetail[2].length() > 4) ? (MainCanvas.mc.taskDetail[2]
                            .substring(0, 3) + "..") : MainCanvas.mc.taskDetail[2], 15718814, (byte) 0, (byte) 0);
                } else {
                    uilb[0] = new UILabel(28, 145, 0, 0, MainCanvas.mc.taskDetail[1], 15718814, (byte) 0, (byte) 0);
                }
                str1 = null;
                if (MainCanvas.mc.taskExperiecce <= 0) {
                    str1 = "";
                    uilb[2] = null;
                } else {
                    str1 = String.valueOf(MainCanvas.mc.taskExperiecce);
                    uilb[2] = new UILabel(tempXY[3][0], tempXY[3][1], 0, 0, "经验：" + str1, 15718814, (byte) 0, (byte) 0);
                }
                if (MainCanvas.mc.taskMoney <= 0) {
                    str1 = "";
                    uilb[3] = null;
                } else {
                    str1 = ((MainCanvas.mc.taskType == 0) ? "金钱：" : "声望：") + String.valueOf(MainCanvas.mc.taskMoney);
                    uilb[3] = new UILabel(tempXY[2][0], tempXY[2][1], 0, 0, str1, 15718814, (byte) 0, (byte) 0);
                }
                if (MainCanvas.mc.mImages[0] != null && MainCanvas.mc.mImages[1] != null) {
                    MainCanvas.mc.mImages[0].setAroundComponent(MainCanvas.mc.mImages[1], (byte) 4);
                }
                str1 = null;
                str1 = MainCanvas.mc.taskDetail[3] + MainCanvas.mc.taskDetail[4];
                yy = textArea0.positionY + textArea0.height - MainCanvas.CHARH * 2 - 3;
                uilb[4] = new UILabel(textArea0.positionX + 2, yy, uIRim1.width - 5, 0, MainCanvas.mc.taskDetail[3] + MainCanvas.mc.taskDetail[4], 15718814, (byte) 0, (byte) 0);
                for (b5 = 0; b5 < 5; b5 = (byte) (b5 + 1)) {
                    if (uilb[b5] != null) {
                        subForm.addComponent(uilb[b5]);
                    }
                }
                uilb[4].setXY(textArea0.positionX + 2, yy);
                for (b5 = 0; b5 < 2; b5 = (byte) (b5 + 1)) {
                    if (MainCanvas.mc.mImages[b5] != null) {
                        if (MainCanvas.mc.mImages[b5].getCurrentFrame() < 33) {
                            MainCanvas.mc.mImages[b5].setNumberVisiable(false);
                        }
                        subForm.addComponent(MainCanvas.mc.mImages[b5]);
                    }
                }
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.mc.baseForm.setAboutForm(subForm);
                break;
            case 162530688:
                tempNumber = MainCanvas.mc.tables[0].getCurrentPointer();
                MainCanvas.mc.tables[0].deleteItem(tempNumber);
                number = (byte) MainCanvas.mc.taskId.length;
                for (m = tempNumber; m < number - 1; m++) {
                    MainCanvas.mc.taskId[m] = MainCanvas.mc.taskId[m + 1];
                }
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                break;
            case 162530944:
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.mc.releaseUI();
                MainCanvas.mc.setNPCSubState((byte) 0);
                MainCanvas.mc.setGameState((byte) 0);
                ObjManager.currentTarget = Player.getInstance();
                break;
            case 162531200:
                undoneResult = execDataIn.readByte();
                undoneMsg = null;
                switch (undoneResult) {
                    case 1:
                        undoneMsg = "背包已满，无法完成任务";
                        break;
                    case 2:
                        undoneMsg = "每日任务已被重置，无法完成任务";
                        break;
                    default:
                        undoneMsg = "无法完成任务，原因未知";
                        break;
                }
                MainCanvas.setMessage(MainCanvas.mc.baseForm, undoneMsg);
                break;
            case 156237952:
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.setMessage(MainCanvas.mc.baseForm, "恭喜您，领养成功！");
                MainCanvas.pet = new Pets();
                break;
            case 156238208:
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                if (execDataIn.readByte() == 0) {
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "您的等级不够!");
                    break;
                }
                MainCanvas.setMessage(MainCanvas.mc.baseForm, "您已经有一只宠物了！");
                break;
            case 155189376:
                MainCanvas.mc.petSkillId = execDataIn.readByte();
                strDetail = execDataIn.readUTF();
                learnMoney = execDataIn.readInt();
                strName = execDataIn.readUTF();
                materialNumber = execDataIn.readByte();
                tempStr1 = null;
                if (materialNumber != 0) {
                    if (learnMoney == 0) {
                        tempStr1 = "学习条件：" + strName + "×" + materialNumber + "。";
                    } else {
                        tempStr1 = "学习条件：" + strName + "×" + materialNumber + "，金钱" + learnMoney + "。";
                    }
                } else if (learnMoney == 0) {
                    tempStr1 = "学习条件：无。";
                } else {
                    tempStr1 = "学习条件：金钱" + learnMoney + "。";
                }
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.mc.releaseUI();
                MainCanvas.mc.textArea[0] = new UITextArea(5, 48, 164, 100, strDetail);
                MainCanvas.mc.textArea[0].setColor(10981736);
                MainCanvas.mc.rims[9] = new UIRim(5, 150, 164, 40, (byte) 0);
                MainCanvas.mc.labels[9] = new UILabel((MainCanvas.mc.rims[9]).positionX + 2, (MainCanvas.mc.rims[9]).positionY + 2, (MainCanvas.mc.rims[9]).width, 0, tempStr1, 16316576, (byte) 0, (byte) 0);
                MainCanvas.mc.setNPCSubState((byte) 4);
                break;
            case 155189632:
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.setMessage(MainCanvas.mc.baseForm, "宠物学习技能成功！");
                break;
            case 155189888:
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.setMessage(MainCanvas.mc.baseForm, "条件不足，学习失败！");
                break;
            case 160432256:
                (Player.getInstance()).money = execDataIn.readInt();
                (Player.getInstance()).money = ((Player.getInstance()).money > 1999999999) ? 1999999999 : (Player.getInstance()).money;
                MainCanvas.mc.setNPCSubState((byte) 8);
                MainCanvas.mc.releaseUI();
                break;
            case 160432512:
                temp = execDataIn.readByte();
                if (temp == 0) {
                    MainCanvas.mc.smithMoney = execDataIn.readInt();
                    MainCanvas.mc.smithId = execDataIn.readShort();
                    MainCanvas.mc.labels[8] = new UILabel(37, 33, 0, 0, "金钱×" + MainCanvas.mc.smithMoney, 15718815, (byte) 0, (byte) 0);
                    MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.labels[8]);
                    if (MainCanvas.mc.smithId != 0) {
                        MainCanvas.mc.mImages[9] = new UIMImage(37, 50, 16, 16, MainCanvas.mImgStuff, (byte) 0);
                        MainCanvas.mc.mImages[9].setCurrentFrame(
                                (byte) (execDataIn.readByte() - 1));
                        MainCanvas.mc.labels[9] = new UILabel(60, 52, 0, 0, execDataIn
                                .readUTF() + "×1", 15718815, (byte) 0, (byte) 0);
                        MainCanvas.mc.baseForm
                                .addComponent(MainCanvas.mc.mImages[9]);
                        MainCanvas.mc.baseForm
                                .addComponent(MainCanvas.mc.labels[9]);
                    }
                    MainCanvas.mc.labels[0].setStr("精炼此物品必要素材");
                    MainCanvas.mc.labels[0].setXY((MainCanvas.mc.labels[0]).positionX, 12);
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.dramatisPackage.isLock1 = true;
                    MainCanvas.mc.labels[3].setStr("精炼");
                    MainCanvas.mc.labels[4].setStr("取消");
                    break;
                }
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.setMessage(MainCanvas.mc.baseForm, "此物品已经达到最大精炼上限");
                break;
            case 160432768:
                tp = execDataIn.readByte();
                temp = execDataIn.readByte();
                if (tp == 0) {
                    MainCanvas.mc.texts[0]
                            .setLabel((Player.getInstance()).money + "");
                    MainCanvas.mc.isHole = execDataIn.readByte();
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    String str2 = null;
                    if (MainCanvas.mc.isHole == 0) {
                        str2 = "看起来这宝贝变的更厉害了，快试试它的威力吧！";
                    } else {
                        str2 = "真是意外的惊喜，您的宝贝被我弄出几个洞来，这样就能镶嵌宝石了。";
                    }
                    MainCanvas.mc.baseForm.addAboutForm("msg", str2, (byte) 1, 220, 0);
                } else if (tp == 1) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    execDataIn.readByte();
                    MainCanvas.mc.baseForm.addAboutForm("msg", "很遗憾，失败了，耐久减少了。", (byte) 1, MainCanvas.screenW - 20, 30);
                } else if (tp == 2) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "您的装备已经损坏，不能再精炼了");
                } else if (tp == -1) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "您的等级不够，不能使用精炼所");
                }
                if (tp != 2 && tp >= 0) {
                    (Player.getInstance()).money -= MainCanvas.mc.smithMoney;
                    (Player.getInstance()).money = ((Player.getInstance()).money < 0) ? 0 : (Player.getInstance()).money;
                }
                if (temp >= 0 && tp != 2) {
                    MainCanvas.dramatisPackage.stuffNumber[temp] = (byte) (MainCanvas.dramatisPackage.stuffNumber[temp] - 1);
                    if (MainCanvas.dramatisPackage.stuffNumber[temp] == 0) {
                        MainCanvas.dramatisPackage.stuffId[temp] = 0;
                        MainCanvas.dramatisPackage.stuffImageId[temp] = 0;
                        MainCanvas.dramatisPackage.stuffName[temp] = "";
                    }
                }
                MainCanvas.mc.texts[0].setLabel((Player.getInstance()).money + "");
                break;
            case 251659904:
                if (execDataIn.readByte() == 1) {
                    title = "解散氏族成功！";
                } else {
                    title = "解散氏族失败！";
                }
                MainCanvas.mc.baseForm.addAboutForm("confirm", title, (byte) 1, 140, 50);
                break;
            case 251658624:
                add = execDataIn.readByte();
                create = execDataIn.readByte();
                MainCanvas.mc.clanExit = execDataIn.readByte();
                disband = execDataIn.readByte();
                MainCanvas.mc.clanConfirmMoney = execDataIn.readInt();
                MainCanvas.mc.clanName = execDataIn.readUTF();
                str = new String[]{"加入氏族", "创建氏族", "退出氏族"};
                if (MainCanvas.mc.clanExit == 2) {
                    str[2] = "取消申请";
                }
                MainCanvas.mc.releaseUI();
                if (add + create + MainCanvas.mc.clanExit + disband > 0) {
                    MainCanvas.mc.menus[0] = new UIMenu(5, 48, 164, 142, null, str);
                    MainCanvas.mc.menus[0].setRimStyle((byte) 0);
                    MainCanvas.mc.menus[0].setFlushType((byte) 1);
                    if (add < 1) {
                        MainCanvas.mc.menus[0].setNoUse((byte) 0);
                    }
                    if (create < 1) {
                        MainCanvas.mc.menus[0].setNoUse((byte) 1);
                    }
                    if (MainCanvas.mc.clanExit < 1) {
                        MainCanvas.mc.menus[0].setNoUse((byte) 2);
                    }
                    if (disband < 1) {
                        MainCanvas.mc.menus[0].setNoUse((byte) 3);
                    }
                }
                MainCanvas.mc.setNPCSubState((byte) 6);
                break;
            case 251661696:
                success = execDataIn.readByte();
                description = execDataIn.readUTF();
                description = description.replace('$', '＄');
                if (success == 1) {
                    UIForm sub = new UIForm(0, 0, 100, 150, "clanintroduce");
                    sub.setBackGround((byte) 9);
                    uIRim1 = new UIRim(0, 0, 100, 150, (byte) 4);
                    UILabel name = new UILabel(0, 6, 0, 0, MainCanvas.mc.tables[0].getCurentItem(), 15718814, (byte) 1, (byte) 0);
                    UITextArea intro = new UITextArea(4, 20, 92, 128, description);
                    intro.setChangeColor(false);
                    sub.addComponent(uIRim1);
                    sub.addComponentInCenter(name, (byte) 2);
                    sub.addComponent(intro);
                    MainCanvas.mc.baseForm.getSubForm().setAboutForm((UIForm) null);
                    MainCanvas.mc.baseForm.getSubForm().setAboutForm(sub);
                    break;
                }
                MainCanvas.mc.baseForm.getSubForm().setAboutForm((UIForm) null);
                MainCanvas.mc.baseForm.getSubForm().addAboutForm("clanintroduce", "该氏族已不存在", (byte) 1, 130, 0);
                break;
            case 251658880:
                clanTotal = execDataIn.readInt();
                clanCurrentPage = execDataIn.readInt();
                clannum = execDataIn.readByte();
                MainCanvas.mc.clanList = null;
                MainCanvas.mc.clanList = new String[clannum];
                for (n = 0; n < clannum; n++) {
                    MainCanvas.mc.clanList[n] = execDataIn.readUTF();
                }
                addForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "addclan");
                addForm.setBackGround((byte) 9);
                uIRim2 = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte) 4);
                uIRim3 = new UIRim(0, 12, 166, 18, (byte) 7);
                uILabel1 = new UILabel(0, 15, 176, 0, "氏族列表", 15718814, (byte) 1, (byte) 0);
                lblOk = new UILabel(60, 7, 0, 0, "操作", 15718814, (byte) 0, (byte) 0);
                lblCancel = new UILabel(60, 7, 0, 0, "返回", 15718814, (byte) 0, (byte) 0);
                MainCanvas.mc.tables[0] = null;
                bclPage = null;
                leftImg = null;
                rightImg = null;
                if (clannum != 0) {
                    MainCanvas.mc.tables[0] = new UITable(0, 30, 166, 156, clannum, 1, (clannum > 10) ? 10 : clannum, (byte) 0, (byte) 3);
                    MainCanvas.mc.tables[0].setSingleWH((MainCanvas.mc.tables[0]).singleWidth, (byte) 18, false);
                    for (int i7 = 0; i7 < clannum; i7++) {
                        MainCanvas.mc.tables[0].addItem(MainCanvas.mc.clanList[i7], 65280);
                    }
                    String str2 = (clanCurrentPage + 1) + "/" + clanTotal;
                    bclPage = new UILabel(0, 193, 0, 0, str2, 15718814, (byte) 1, (byte) 0);
                    leftImg = new UIMImage(55, 196, 4, 5, MainCanvas.mImgUI[22], (byte) 0);
                    leftImg.setCurrentFrame((byte) 0);
                    leftImg.setNumberVisiable(false);
                    rightImg = new UIMImage(MainCanvas.screenW - 55 - 4, 196, 4, 5, MainCanvas.mImgUI[22], (byte) 0);
                    rightImg.setCurrentFrame((byte) 1);
                    rightImg.setNumberVisiable(false);
                }
                addForm.addComponent(uIRim2);
                addForm.addComponentInCenter(uIRim3, (byte) 2);
                addForm.addComponentInCenter(uILabel1, (byte) 2);
                addForm.addComponentInCenter(lblCancel, (byte) 6);
                if (clannum != 0) {
                    addForm.addComponentInCenter(MainCanvas.mc.tables[0], (byte) 2);
                    addForm.addComponentInCenter(lblOk, (byte) 5);
                    addForm.addComponentInCenter(bclPage, (byte) 2);
                    bclPage.positionY -= bclPage.height >> -2;
                    rightImg.positionX = 120;
                }
                if (clanTotal > 1) {
                    addForm.addComponent(leftImg);
                    addForm.addComponent(rightImg);
                }
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.mc.baseForm.setAboutForm(addForm);
                if (clannum == 0) {
                    MainCanvas.mc.baseForm.getSubForm().addAboutForm("noclan", "没有任何氏族！", (byte) 1, 140, 50);
                }
                break;
            case 251659136:
                MainCanvas.mc.addResult = execDataIn.readByte();
                if (MainCanvas.mc.addResult == 1) {
                    addmessage = "申请加入" + MainCanvas.mc.tables[0].getCurentItem() + "氏族成功！";
                } else {
                    addmessage = MainCanvas.mc.tables[0].getCurentItem() + "氏族人数已满！";
                }
                MainCanvas.mc.baseForm.getSubForm().addAboutForm("addreturn", addmessage, (byte) 1, 150, 50);
                break;
            case 251659392:
                switch (execDataIn.readByte()) {
                    case 1:
                        message = "申请创建成功!";
                        break;
                    case 2:
                        message = "该氏族名已经存在!";
                        break;
                    case 3:
                        message = "氏族保证金不足!";
                        break;
                    case 4:
                        message = "你已有氏族!";
                        break;
                    default:
                        message = "未知错误!";
                        break;
                }
                MainCanvas.mc.baseForm.getSubForm().addAboutForm("createreturn", message, (byte) 1, 150, 50);
                break;
            case 251659648:
                exitState = execDataIn.readByte();
                if (MainCanvas.mc.clanExit == 1) {
                    exitInfo = "发送申请退出" + MainCanvas.mc.clanName + "氏族";
                } else {
                    exitInfo = "取消申请加入" + MainCanvas.mc.clanName + "氏族";
                }
                if (exitState == 0) {
                    exitInfo = exitInfo + "失败！";
                } else if (exitState == 1) {
                    exitInfo = exitInfo + "成功！";
                } else if (exitState == 2) {
                    exitInfo = "族长需传位给长老后方可退出氏族";
                }
                MainCanvas.mc.baseForm.addAboutForm("confirm", exitInfo, (byte) 1, 140, 50);
                break;
            case 251661440:
            case 251662208:
            case 251691136:
                MainCanvas.mc.clanExit = execDataIn.readByte();
                switch (MainCanvas.mc.clanExit) {
                    case 1:
                        MainCanvas.mc.baseForm.getSubForm().addAboutForm("rstConfirm", "操作成功！", (byte) 1, 140, 50);
                        break;
                    case 2:
                        MainCanvas.mc.baseForm.getSubForm().addAboutForm("rstConfirm", "自己不能对自己进行操作！", (byte) 1, 140, 50);
                        break;
                    case 3:
                        MainCanvas.mc.baseForm.getSubForm().addAboutForm("rstConfirm", "对方已取消申请！", (byte) 1, 140, 50);
                        break;
                    case 4:
                        MainCanvas.mc.baseForm.getSubForm().addAboutForm("rstConfirm", "不能对自己相同等级的人进行操作！", (byte) 1, 140, 50);
                        break;
                    case 5:
                        MainCanvas.mc.baseForm.getSubForm().addAboutForm("rstConfirm", "不能对族长进行操作！", (byte) 1, 140, 50);
                        break;
                    case 6:
                        MainCanvas.mc.baseForm.getSubForm().addAboutForm("rstConfirm", "该玩家已经退出氏族！", (byte) 1, 140, 50);
                        break;
                }
                MainCanvas.mc.baseForm.getSubForm().addAboutForm("rstConfirm", "操作失败！", (byte) 1, 140, 50);
                break;
            case 251660160:
                MainCanvas.mc.clanName = execDataIn.readUTF();
                MainCanvas.mc.clanManegeLevel = execDataIn.readByte();
                MainCanvas.mc
                        .setRightMenuSubState(3);
                MainCanvas.mc.setUISocietyState((byte) 4);
                MainCanvas.mc.releaseUI();
                break;
            case 251660416:
                MainCanvas.mc.clanManegeLevel = execDataIn.readByte();
                clanTotal = execDataIn.readInt();
                clanCurrentPage = execDataIn.readShort();
                memNum = execDataIn.readByte();
                MainCanvas.mc.clanMemberName = new String[memNum];
                MainCanvas.mc.clanMemberId = new int[memNum];
                MainCanvas.mc.clanMemberLevel = new byte[memNum];
                for (i1 = 0; i1 < memNum; i1++) {
                    MainCanvas.mc.clanMemberId[i1] = execDataIn.readInt();
                    MainCanvas.mc.clanMemberName[i1] = execDataIn.readUTF();
                    MainCanvas.mc.clanMemberLevel[i1] = execDataIn.readByte();
                }
                clanTotalPage = clanTotal / 7;
                if (clanTotal % 7 != 0) {
                    clanTotalPage++;
                }
                page = clanCurrentPage + "/" + clanTotalPage;
                lblPage = new UILabel(0, 195, 0, 0, page, 15718814, (byte) 1, (byte) 0);
                addForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "viewMem");
                addForm.setBackGround((byte) 9);
                uIRim2 = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte) 4);
                uIRim3 = new UIRim(0, 12, 166, 20, (byte) 7);
                uILabel1 = new UILabel(0, 15, 176, 0, "氏族成员列表", 15718814, (byte) 1, (byte) 0);
                rimUp = new UIRim(0, 32, 166, 160, (byte) 0);
                lblCancel = new UILabel(60, 7, 0, 0, "返回", 15718814, (byte) 0, (byte) 0);
                MainCanvas.mc.tables[0] = null;
                if (memNum != 0) {
                    MainCanvas.mc.tables[0] = new UITable(0, 34, 161, 156, memNum, 1, (memNum > 7) ? 7 : memNum, (byte) 0, (byte) 3);
                    MainCanvas.mc.tables[0].setSingleWH((MainCanvas.mc.tables[0]).singleWidth, (byte) 21, false);
                    for (int i7 = 0; i7 < memNum; i7++) {
                        MainCanvas.mc.tables[0]
                                .setItem(MainCanvas.mc.clanMemberName[i7] + "  " + Cons.UNION_NAMES[MainCanvas.mc.clanMemberLevel[i7]], i7, 65280);
                    }
                }
                addForm.addComponent(uIRim2);
                addForm.addComponentInCenter(uIRim3, (byte) 2);
                addForm.addComponentInCenter(uILabel1, (byte) 2);
                addForm.addComponentInCenter(rimUp, (byte) 2);
                lblOk = new UILabel(60, 7, 0, 0, "操作", 15718814, (byte) 0, (byte) 0);
                addForm.addComponentInCenter(lblOk, (byte) 5);
                addForm.addComponentInCenter(lblCancel, (byte) 6);
                if (memNum != 0) {
                    addForm.addComponentInCenter(MainCanvas.mc.tables[0], (byte) 2);
                    addForm.addComponentInCenter(lblPage, (byte) 2);
                }
                if (MainCanvas.mc.clanManegeLevel == 0) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.mc.baseForm.getCurrentFocusForm().addAboutForm("noMem", "您需要先加入一个氏族才能查看！", (byte) 1, 140, 50);
                    break;
                }
                if (memNum == 0) {
                    if (clanTotal == 0) {
                        MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                        MainCanvas.mc.baseForm.getCurrentFocusForm().addAboutForm("noMem", "氏族不存在！", (byte) 1, 140, 50);
                        break;
                    }
                    clanCurrentPage--;
                    MainCanvas.ni.send(251660288);
                    break;
                }
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.mc.baseForm.setAboutForm(addForm);
                break;
            case 251660672:
                optResult = execDataIn.readByte();
                message = "";
                switch (optResult) {
                    case 1:
                        message = "操作成功！";
                        break;
                    case 5:
                        message = "职位人数已满！";
                        break;
                    case 8:
                        message = "你不是族长不能传位!";
                        break;
                    case 9:
                        message = "传位失败!";
                        break;
                    case 10:
                        message = "传位成功!";
                        break;
                    case 11:
                        message = "只能传位给长老!";
                        break;
                    case 12:
                        message = "该成员已在退出列表中，无法传位";
                        break;
                    default:
                        message = "操作失败！";
                        break;
                }
                MainCanvas.mc.baseForm.getSubForm().setAboutForm((UIForm) null);
                MainCanvas.mc.baseForm.getSubForm().addAboutForm("optRst", message, (byte) 1, 140, 50);
                break;
            case 251660928:
                comfirmInvite = execDataIn.readByte();
                msg = "";
                switch (comfirmInvite) {
                    case 0:
                        msg = "失败!";
                        break;
                    case 1:
                        msg = "邀请已发送!";
                        break;
                    case 2:
                        msg = "玩家已加入氏族!";
                        break;
                    case 3:
                        msg = "玩家不在线!";
                        break;
                    case 4:
                        msg = "氏族人数已满!";
                        break;
                    case 5:
                        msg = "玩家不符合条件!";
                        break;
                    case 6:
                        msg = "玩家已申请加入氏族!";
                        break;
                }
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.mc.baseForm.addAboutForm("inviteRst", msg, (byte) 1, 170, 50);
                break;
            case 251661184:
            case 251661952:
            case 251687040:
                clanTotal = execDataIn.readInt();
                clanCurrentPage = execDataIn.readShort();
                comfirmNum = execDataIn.readByte();
                MainCanvas.mc.clanMemberName = null;
                MainCanvas.mc.clanMemberId = null;
                MainCanvas.mc.clanMemberLevel = null;
                MainCanvas.mc.clanMemberName = new String[comfirmNum];
                MainCanvas.mc.clanMemberId = new int[comfirmNum];
                MainCanvas.mc.clanMemberLevel = new byte[comfirmNum];
                for (i2 = 0; i2 < comfirmNum; i2++) {
                    MainCanvas.mc.clanMemberId[i2] = execDataIn.readInt();
                    MainCanvas.mc.clanMemberName[i2] = execDataIn.readUTF();
                    MainCanvas.mc.clanMemberLevel[i2] = execDataIn.readByte();
                }
                clanTotalPage = clanTotal / 7;
                if (clanTotal % 7 != 0) {
                    clanTotalPage++;
                }
                page = clanCurrentPage + "/" + clanTotalPage;
                lblPage = new UILabel(0, 195, 0, 0, page, 15718814, (byte) 1, (byte) 0);
                addForm = new UIForm(0, 0, MainCanvas.mc.baseForm.width, MainCanvas.mc.baseForm.height, "viewConfirm");
                addForm.setBackGround((byte) 9);
                uIRim2 = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte) 4);
                uIRim3 = new UIRim(0, 12, 166, 20, (byte) 7);
                titleStr = "";
                switch (ClanWar.m_getJoinMemList) {
                    case 0:
                        titleStr = "申请加入人员表";
                        break;
                    case 1:
                        titleStr = "申请退出人员表";
                        break;
                    case 2:
                        titleStr = "申请进入农场人员表";
                        break;
                }
                uILabel1 = new UILabel(0, uIRim3.positionY + 3, 176, 0, titleStr, 15718814, (byte) 1, (byte) 0);
                rimUp = new UIRim(0, 32, 166, 158, (byte) 0);
                lblOk = new UILabel(60, 7, 0, 0, "操作", 15718814, (byte) 0, (byte) 0);
                lblCancel = new UILabel(60, 7, 0, 0, "返回", 15718814, (byte) 0, (byte) 0);
                MainCanvas.mc.tables[0] = null;
                if (comfirmNum != 0) {
                    MainCanvas.mc.tables[0] = new UITable(0, 34, 161, 154, comfirmNum, 1, (comfirmNum > 7) ? 7 : comfirmNum, (byte) 0, (byte) 3);
                    MainCanvas.mc.tables[0].setSingleWH((MainCanvas.mc.tables[0]).singleWidth, (byte) 21, false);
                    for (int i7 = 0; i7 < comfirmNum; i7++) {
                        if (ClanWar.m_getJoinMemList == 2) {
                            boolean isOk = (MainCanvas.mc.clanMemberLevel[i7] == 1);
                            int color = isOk ? 65280 : 16777215;
                            MainCanvas.mc.tables[0].setItem(MainCanvas.mc.clanMemberName[i7] + (isOk ? " 准" : ""), i7, color);
                        } else {
                            MainCanvas.mc.tables[0].setItem(MainCanvas.mc.clanMemberName[i7] + "  " + MainCanvas.mc.clanMemberLevel[i7] + "级", i7, 65280);
                        }
                    }
                }
                addForm.addComponent(uIRim2);
                addForm.addComponentInCenter(uIRim3, (byte) 2);
                addForm.addComponentInCenter(uILabel1, (byte) 2);
                addForm.addComponentInCenter(rimUp, (byte) 2);
                addForm.addComponentInCenter(lblOk, (byte) 5);
                addForm.addComponentInCenter(lblCancel, (byte) 6);
                if (comfirmNum != 0) {
                    addForm.addComponentInCenter(MainCanvas.mc.tables[0], (byte) 2);
                    addForm.addComponentInCenter(lblPage, (byte) 2);
                }
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.mc.baseForm.setAboutForm(addForm);
                if (comfirmNum == 0) {
                    MainCanvas.mc.baseForm.getSubForm().addAboutForm("noConfirm", "没有任何待批准人员！", (byte) 1, 140, 50);
                }
                break;
            case 157286528:
                items = null;
                MainCanvas.mc.whichList = execDataIn.readByte();
                if (MainCanvas.mc.whichList == 1) {
                    items = Cons.NPC_EXCHANGE_0;
                } else if (MainCanvas.mc.whichList == 2) {
                    items = Cons.NPC_EXCHANGE_1;
                } else if (MainCanvas.mc.whichList == 3) {
                    items = Cons.NPC_EXCHANGE_2;
                }
                len1 = items.length;
                MainCanvas.mc.releaseUI();
                exchangeTable = null;
                exchangeTable = new UITable(0, 40, 105, 150, len1, 1, 7, (byte) 0, (byte) 2);
                exchangeTable.setSingleWH((short) 100, (byte) 20, false);
                for (i3 = 0; i3 < len1; i3++) {
                    exchangeTable.addItem(items[i3], 15718815);
                }
                MainCanvas.mc.setNPCSubState((byte) 10);
                MainCanvas.mc.setNPCExchangeState((byte) 0);
                break;
            case 157286784:
                MainCanvas.mc.changeMax = execDataIn.readShort();
                if (MainCanvas.mc.changeMax < 10000) {
                    MainCanvas.mc.changeMax = (MainCanvas.mc.changeMax > 20) ? 20 : MainCanvas.mc.changeMax;
                }
                p = new int[][]{{25, 60}, {25, 86}, {25, 112}};
                sb = new StringBuffer();
                sb.append(exchangeTable.getCurentItem()).append("需要:");
                MainCanvas.mc.releaseUI();
                for (i4 = 0; i4 < 3; i4++) {
                    byte aa = execDataIn.readByte();
                    if (aa == 0) {
                        break;
                    }
                    MainCanvas.mc.rims[i4 + 1] = new UIRim(p[i4][0], p[i4][1], 17, 17, (byte) 0);
                    MainCanvas.mc.mImages[i4] = new UIMImage((MainCanvas.mc.rims[i4 + 1]).positionX + 1, (MainCanvas.mc.rims[i4 + 1]).positionY + 1, 0, 0, MainCanvas.mImgStuff, (byte) 0);
                    MainCanvas.mc.mImages[i4].setCurrentFrame((byte) (aa - 1));
                    MainCanvas.mc.labels[i4] = new UILabel((MainCanvas.mc.mImages[i4]).positionX + 30, (MainCanvas.mc.mImages[i4]).positionY + 3, 0, 0, execDataIn
                            .readUTF() + "× 1", 15718815, (byte) 0, (byte) 0);
                }
                MainCanvas.mc.labels[5] = new UILabel(20, 10, 0, 0, sb.toString(), 15718815, (byte) 0, (byte) 0);
                MainCanvas.mc.setNPCExchangeState((byte) 1);
                break;
            case 157287040:
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.mc.baseForm
                        .addAboutForm("succeed", "您兑换的东西已经放入包裹中，快去看看吧", (byte) 1, MainCanvas.screenW - 30, 0);
                break;
            case 163577984:
                temp = 0;
                tempShort = new short[6];
                for (b6 = 0; b6 < 6; b6 = (byte) (b6 + 1)) {
                    tempShort[b6] = execDataIn.readShort();
                    temp = (byte) (temp + 1);
                    if (tempShort[b6] == 0) {
                        temp = (byte) (temp - 1);
                    }
                }
                MainCanvas.mc.npcHelpId = new short[temp];
                MainCanvas.mc.npcHelpItem = new String[temp];
                for (j = 0; j < temp; j = (byte) (j + 1)) {
                    MainCanvas.mc.npcHelpId[j] = tempShort[j];
                    MainCanvas.mc.npcHelpItem[j] = execDataIn.readUTF();
                }
                MainCanvas.mc.menus[1] = new UIMenu(0, 0, 132, 0, null, MainCanvas.mc.npcHelpItem);
                MainCanvas.mc.menus[1].setFlushType((byte) 1);
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.NPCMenu.setSubMenu(MainCanvas.mc.menus[1]);
                break;
            case 163578240:
                tempS = execDataIn.readUTF();
                textArea11 = new UITextArea(5, 48, 164, 142, tempS);
                textArea11.setColor(15787196);
                subForm = new UIForm(0, 0, 176, 208, "detail");
                subForm.setBackGround((byte) 9);
                subForm.addComponentInCenter(textArea11, (byte) 2);
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.mc.baseForm.setAboutForm(subForm);
                (MainCanvas.mc.baseForm.getSubForm()).width = 170;
                MainCanvas.mc.labels[5].setStr("  ");
                break;
            case 1610613120:
                MainCanvas.topTitle = execDataIn.readUTF();
                MAXTOP = execDataIn.readByte();
                MainCanvas.mc.topPos = new String[MAXTOP];
                for (i5 = 0; i5 < MAXTOP; i5++) {
                    String tempstr = execDataIn.readUTF();
                    if (tempstr != null) {
                        MainCanvas.mc.topPos[i5] = "  " + (i5 + 1) + "     " + tempstr;
                    } else {
                        MAXTOP = i5 + 1;
                        break;
                    }
                }
                MainCanvas.mc.setNPCSubState((byte) 22);
                MainCanvas.mc.releaseUI();
                break;
            case 164626560:
                tempcount = execDataIn.readByte();
                MainCanvas.mc.npcPosXY = new String[tempcount];
                for (i6 = 0; i6 < tempcount; i6++) {
                    MainCanvas.mc.npcPosXY[i6] = "  " + execDataIn.readUTF();
                }
                if (MainCanvas.mc.getGameState() == 1) {
                    MainCanvas.mc.setRightMenuSubState(5);
                    MainCanvas.mc.setUIMApState((byte) 1);
                } else {
                    MainCanvas.mc.setGameState((byte) 1);
                    MainCanvas.mc.setRightMenuSubState(5);
                    MainCanvas.mc.setUIMApState((byte) 1);
                }
                MainCanvas.mc.releaseUI();
                break;
            case 1879048576:
                flag = execDataIn.readByte();
                message = null;
                message2 = null;
                switch (flag) {
                    case 0:
                        message = "";
                        break;
                    case 1:
                        message = "恭喜你,我们已将奖品通过邮箱形式发送^_^";
                        break;
                    case 2:
                        message = "输入信息不匹配";
                        break;
                    case 3:
                        message = "已经领取过奖品!";
                        break;
                    case 4:
                        message = "不满足活动条件";
                        break;
                    case 5:
                        message = "恭喜您，您获得了相应的游戏奖励!";
                        break;
                    case 6:
                        message = "未知错误!";
                        break;
                    case 7:
                        message = execDataIn.readUTF();
                        break;
                    case 8:
                        message = execDataIn.readUTF();
                        message2 = execDataIn.readUTF();
                        break;
                    default:
                        message = "未知错误!";
                        break;
                }
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                if (MainCanvas.userID != null && flag == 8 && Cons.cmwap
                        && !"".equals(MainCanvas.userID.trim())) {
                    MainCanvas.mc.baseForm.addAboutForm("mss2", message2, (byte) 2, 140, 0);
                    break;
                }
                if (flag == 9) {
                    MainCanvas.mc.baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, MainCanvas.screenW - 30, 0);
                    MainCanvas.ni.send(1879048704);
                    break;
                }
                MainCanvas.mc.baseForm.addAboutForm("message", message, (byte) 1, 140, 0);
                break;
            case 1879048832:
                message = execDataIn.readUTF();
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.mc.baseForm.addAboutForm("message", message, (byte) 1, 140, 0);
                break;
        }
    }

    public static byte[] compress(int commID) {
        String name, password;
        ByteArray execDataOut = new ByteArray();
        switch (commID) {
            case 163578880:
                execDataOut.writeInt(MainCanvas.mc.NPCIndex);
                break;
            case 164757504:
                execDataOut.writeInt(MainCanvas.mc.tradeStuffId);
                break;
            case 164691968:
                execDataOut.writeInt(MainCanvas.mc.NPCIndex);
                break;
            case 251682816:
                execDataOut.writeInt(MainCanvas.mc.tables[1].getCurrentPointer());
                break;
            case 251678720:
                execDataOut.writeByte(isGold ? 0 : 1);
                break;
            case 251670528:
                execDataOut.writeInt(MainCanvas.mc.NPCIndex);
                break;
            case 251674624:
                execDataOut.writeInt(MainCanvas.mc.tradeStuffId);
                break;
            case 251666432:
                execDataOut.writeInt(MainCanvas.mc.clanMemberId[MainCanvas.mc.clanTargerMemPointer]);
                execDataOut.writeInt(propsId[MainCanvas.mc.tables[1].getCurrentPointer()]);
                break;
            case 251662336:
                execDataOut.writeByte(MainCanvas.isDistributionBadge ? 0 : 1);
                break;
            case 163643392:
                execDataOut.writeByte(battleGroundIndex);
                break;
            case 150994944:
                execDataOut.writeInt(MainCanvas.mc.NPCIndex);
                break;
            case 152043520:
                execDataOut.writeInt(MainCanvas.mc.NPCIndex);
                break;
            case 152047616:
                execDataOut.writeInt(ObjManager.currentTarget.objID);
                execDataOut.writeByte(MainCanvas.mc.tradePlace);
                execDataOut.writeByte(MainCanvas.mc.tradeNumber);
                break;
            case 152051712:
                execDataOut.writeByte(MainCanvas.dramatisPackage
                        .getCurrentPointer());
                execDataOut.writeByte(MainCanvas.mc.tradeNumber);
                break;
            case 154140928:
                execDataOut.writeByte(MainCanvas.fixPlace);
                break;
            case 161484800:
                execDataOut.writeByte((MainCanvas.mc.grids[0]).stuffPlace);
                execDataOut.writeByte(MainCanvas.mc.grids[0].getCurrentPointer());
                break;
            case 161488896:
                execDataOut.writeByte(MainCanvas.mc.stPlace);
                execDataOut.writeByte(MainCanvas.mc.stNumber);
                break;
            case 161492992:
                execDataOut.writeByte(MainCanvas.mc.stPlace);
                execDataOut.writeByte(MainCanvas.mc.stNumber);
                break;
            case 161497088:
                execDataOut.writeByte(MainCanvas.mc.discardPlace);
                execDataOut.writeByte(MainCanvas.mc.stPlace);
                break;
            case 161505280:
                execDataOut.writeInt(MainCanvas.mc.stMoney);
                break;
            case 161509376:
                execDataOut.writeInt(MainCanvas.mc.stMoney);
                break;
            case 162529280:
                execDataOut.writeInt(MainCanvas.NPCMenu.getCurrentRealTaskId());
                execDataOut.writeByte((byte) 0);
                execDataOut.writeInt(ObjManager.currentTarget.objID);
                break;
            case 162529536:
                execDataOut.writeInt(MainCanvas.NPCMenu.getCurrentRealTaskId());
                execDataOut.writeShort(Map.currentMapID);
                execDataOut.writeInt(MainCanvas.mc.NPCIndex);
                break;
            case 162529792:
                execDataOut.writeInt(MainCanvas.mc.taskId[MainCanvas.mc.tables[0]
                        .getCurrentPointer()]);
                break;
            case 162530304:
                execDataOut.writeInt(MainCanvas.mc.taskId[MainCanvas.mc.tables[0]
                        .getCurrentPointer()]);
                execDataOut.writeByte((byte) 1);
                execDataOut.writeInt(0);
                break;
            case 162530816:
                execDataOut.writeInt(MainCanvas.NPCMenu.getCurrentRealTaskId());
                execDataOut.writeShort(Map.currentMapID);
                execDataOut.writeInt(MainCanvas.mc.NPCIndex);
                break;
            case 156237824:
                execDataOut.writeByte(MainCanvas.mc.dramatisPetId);
                execDataOut
                        .writeUTF(Cons.PET_NAME[MainCanvas.mc.dramatisPetId - 1]);
                break;
            case 155189248:
                execDataOut.writeInt(MainCanvas.mc.NPCIndex);
                execDataOut.writeByte(MainCanvas.NPCMenu.getCurrentRealId());
                break;
            case 155189504:
                execDataOut.writeByte(MainCanvas.mc.petSkillId);
                break;
            case 160432384:
                execDataOut.writeByte(MainCanvas.dramatisPackage
                        .getCurrentPointer());
                break;
            case 160432640:
                execDataOut.writeByte(MainCanvas.dramatisPackage
                        .getCurrentPointer());
                break;
            case 251658752:
                execDataOut.writeInt(clanCurrentPage);
                break;
            case 251661568:
                execDataOut.writeUTF(MainCanvas.mc.tables[0].getCurentItem());
                break;
            case 251659008:
                execDataOut.writeUTF(MainCanvas.mc.tables[0].getCurentItem());
                break;
            case 251659264:
                execDataOut.writeUTF(MainCanvas.mc.clanRequestName);
                execDataOut.writeUTF(MainCanvas.mc.clanRequestInfo);
                break;
            case 251660288:
                execDataOut.writeShort(clanCurrentPage);
                break;
            case 251660544:
                execDataOut
                        .writeByte(MainCanvas.mc.menus[1].getCurrentPointer() + 1);
                execDataOut
                        .writeInt(MainCanvas.mc.clanMemberId[MainCanvas.mc.clanTargerMemPointer]);
                execDataOut.writeByte(MainCanvas.mc.clanTargerMemLevel);
                break;
            case 251660800:
                execDataOut.writeUTF(MainCanvas.mc.clanRequestName);
                break;
            case 251661056:
            case 251661824:
            case 251686912:
                execDataOut.writeShort(clanCurrentPage);
                break;
            case 251661312:
            case 251662080:
            case 251691008:
                execDataOut
                        .writeInt(MainCanvas.mc.clanMemberId[MainCanvas.mc.clanTargerMemPointer]);
                execDataOut.writeByte(
                        (byte) (MainCanvas.mc.menus[2].getCurrentPointer() + 1));
                break;
            case 157286400:
                execDataOut.writeInt(MainCanvas.mc.NPCIndex);
                break;
            case 157286656:
                if (MainCanvas.mc.whichList == 1) {
                    execDataOut.writeByte(MainCanvas.mc.changeIndex + 1);
                    break;
                }
                if (MainCanvas.mc.whichList == 2) {
                    execDataOut.writeByte(MainCanvas.mc.changeIndex + Cons.NPC_EXCHANGE_0.length + 1);
                    break;
                }
                if (MainCanvas.mc.whichList == 3) {
                    execDataOut.writeByte(MainCanvas.mc.changeIndex + Cons.NPC_EXCHANGE_0.length + Cons.NPC_EXCHANGE_1.length + 1);
                }
                break;
            case 157286912:
                if (MainCanvas.mc.whichList == 1) {
                    execDataOut.writeByte(MainCanvas.mc.changeIndex + 1);
                } else if (MainCanvas.mc.whichList == 2) {
                    execDataOut.writeByte(MainCanvas.mc.changeIndex + Cons.NPC_EXCHANGE_0.length + 1);
                } else if (MainCanvas.mc.whichList == 3) {
                    execDataOut.writeByte(MainCanvas.mc.changeIndex + Cons.NPC_EXCHANGE_0.length + Cons.NPC_EXCHANGE_1.length + 1);
                }
                execDataOut.writeByte(MainCanvas.mc.texts[1].getNumber());
                break;
            case 163577856:
                execDataOut.writeInt(MainCanvas.mc.NPCIndex);
                break;
            case 163578112:
                execDataOut
                        .writeShort(MainCanvas.mc.npcHelpId[MainCanvas.mc.menus[1]
                        .getCurrentPointer()]);
                break;
            case 1610612992:
                execDataOut.writeByte(MainCanvas.NPCMenu.getMappingPointer());
                break;
            case 1879048448:
                name = "";
                password = "";
                switch (npcActType) {
                    case 32:
                        name = MainCanvas.mc.commonTextField.getString();
                        break;
                    case 33:
                        name = MainCanvas.mc.commonTextField.getString();
                        password = MainCanvas.mc.commontf.getString();
                        break;
                }
                execDataOut.writeUTF(name);
                execDataOut.writeUTF(password);
                execDataOut.writeInt(MainCanvas.mc.NPCIndex);
                break;
            case 1879048704:
                MainCanvas.mc.loginRewardUesrId();
                while (MainCanvas.mc.isGetingUserID) {
                    try {
                        Thread.sleep(100L);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (MainCanvas.userID != null) {
                    execDataOut.writeUTF(MainCanvas.userID);
                    break;
                }
                execDataOut.writeUTF("");
                break;
            case 163579136:
                execDataOut.writeInt(MainCanvas.mc.NPCIndex);
                break;
        }
        return execDataOut.toByteArray();
    }

    public static byte getHonorSign() {
        return honorSign;
    }

    static void initSilkForm(byte len) {
        UIForm form = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "silk");
        form.setBackGround((byte) 9);
        UIRim frame = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte) 4);
        UIRim rimTitle = new UIRim(0, 9, 166, 20, (byte) 7);
        UILabel lblTitle = new UILabel(0, 12, 176, 0, "可以缠丝的装备", 15718814, (byte) 1, (byte) 0);
        UIRim rimDown = new UIRim(0, 29, 166, 160, (byte) 0);
        form.addComponent(frame);
        form.addComponentInCenter(rimTitle, (byte) 2);
        form.addComponentInCenter(lblTitle, (byte) 2);
        form.addComponentInCenter(rimDown, (byte) 2);
        form.addLeftButton("选择");
        form.addRightButton("返回");
        UITable table = new UITable(0, 31, 161, 152, len, 1, (len > 8) ? 8 : len, (byte) 0, (byte) 3);
        table.setSingleWH(table.singleWidth, (byte) 19, false);
        for (int i = 0; i < len; i++) {
            table.setItem(propsName[i], i, 65280);
        }
        form.addComponentInCenter(table, (byte) 2);
        MainCanvas.mc.tables[1] = null;
        MainCanvas.mc.tables[1] = table;
        MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm()
                .setAboutForm((UIForm) null);
        MainCanvas.mc.baseForm.getCurrentFocusForm().setAboutForm(form);
        MainCanvas.mc.baseForm.setFocus(true);
        MainCanvas.mc.setNPCSubState((byte) 56);
    }
}
