
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
        ByteArray execDataIn = new ByteArray(data);
        String mgs = "";
        byte len;
        int i;
        StringBuffer sb;
        byte[] var10000;
        byte var10001;
        UIForm subForm;
        UIRim rim;
        String str;
        UIRim rimTitle;
        UILabel lblTitle;
        UILabel lblOk;
        UILabel lblCancel;
        String page;
        String message;
        UILabel lblPage;
        UIRim rimUp;
        int color;
        int flag;
        byte commonNumber;
        byte temp;
        byte tempNumber;
        int height;
        String tmpss;
        UIForm addForm;
        UIRim frame;
        byte j;
        Player var120;
        switch (commID) {
            case 150995072:
                commonNumber = execDataIn.readByte();
                byte taskNumber = execDataIn.readByte();
                if (commonNumber == 0 && taskNumber == 0) {
                    MainCanvas.mc.releaseUI();
                    i = ObjManager.currentTarget.x - Map.currentWindowX;
                    int startX = 0;
                    int width = 83;
                    if (i <= MainCanvas.screenW >> 1) {
                        temp = 5;
                    } else {
                        temp = 88;
                    }

                    MainCanvas.mc.baseForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "");
                    MainCanvas.mc.baseForm.setBackGround((byte) 9);
                    tmpss = execDataIn.readUTF();
                    height = (tmpss.length() * MainCanvas.font[1].charWidth('正') / width + 1) * (MainCanvas.font[1].getHeight() + 4);
                    rim = new UIRim(temp, 84, width, height, (byte) 1);
                    UILabel labels = new UILabel(rim.positionX + 2, rim.positionY + 2, width - 2, 0, tmpss, 15653280, (byte) 0, (byte) 0);
                    rim.height = labels.height + 10;
                    MainCanvas.mc.baseForm.addComponent(rim);
                    MainCanvas.mc.baseForm.addComponent(labels);
                    MainCanvas.mc.setGameState((byte) 8);
                    MainCanvas.mc.setOtherSubState((byte) 0);
                } else {
                    String[] tmpStrs = new String[commonNumber + taskNumber];
                    int[] tmpItem = new int[commonNumber + taskNumber];
                    byte[] state = new byte[commonNumber + taskNumber];
                    i = 0;

                    while (i < commonNumber) {
                        tmpItem[i] = execDataIn.readInt();
                        tmpStrs[i] = execDataIn.readUTF();
                        switch (tmpItem[i]) {
                            case 31:
                            case 32:
                            case 33:
                                String tmpSs = execDataIn.readUTF();
                                npcInfo = Util.splitToken(tmpSs, '~');
                                npcActType = (byte) tmpItem[i];
                            default:
                                state[i] = -1;
                                ++i;
                        }
                    }

                    for (i = commonNumber; i < commonNumber + taskNumber; ++i) {
                        tmpItem[i] = execDataIn.readInt() + 120;
                        tmpStrs[i] = execDataIn.readUTF();
                        state[i] = (byte) (execDataIn.readByte() & 15);
                        state[i] |= (byte) (execDataIn.readByte() << 4 & 240);
                        if ((state[i] & 15) == 0) {
                            tmpStrs[i] = tmpStrs[i] + "(任务)";
                        } else if ((state[i] & 15) == 1) {
                            tmpStrs[i] = tmpStrs[i] + "(未完成)";
                        } else if ((state[i] & 15) == 2) {
                            tmpStrs[i] = tmpStrs[i] + "(已完成)";
                        }
                    }

                    MainCanvas.NPCMenu = new UIMenu(5, 48, 164, 142, (String) null, tmpStrs);
                    MainCanvas.NPCMenu.setMenuItems(tmpItem, tmpStrs, state);
                    MainCanvas.NPCMenu.setRimStyle((byte) 0);
                    MainCanvas.NPCMenu.setFlushType((byte) 1);
                    MainCanvas.mc.releaseUI();
                    MainCanvas.mc.setGameState((byte) 3);
                    MainCanvas.mc.setNPCSubState((byte) 0);
                }
                break;
            case 152043648:
                honorSign = execDataIn.readByte();
                if (honorSign == -1) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "您的氏族等级不够,不能使用该氏族商店");
                } else {
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

                    for (i = 0; i < 18; i = (byte) (i + 1)) {
                        MainCanvas.mc.grids[0].setGridDetail((byte) i, execDataIn.readShort(), execDataIn.readByte(), execDataIn.readByte(), execDataIn.readUTF(), execDataIn.readByte(), execDataIn.readShort(), (byte) 1, (byte) 0);
                        if (honorSign == 3) {
                            propsId[i] = execDataIn.readInt();
                            propsName[i] = execDataIn.readUTF();
                            propsNumber[i] = execDataIn.readInt();
                        } else if (honorSign == 4) {
                            propsId[i] = execDataIn.readInt();
                            propsNumber[i] = execDataIn.readInt();
                        } else {
                            if (honorSign == 5) {
                                MainCanvas.mc.grids[0].isLmtstoren[i] = true;
                            }

                            MainCanvas.mc.shopStuffPrice[i] = execDataIn.readInt();
                        }
                    }

                    if (honorSign == 3) {
                        needOne = execDataIn.readInt();
                    } else if (honorSign == 4) {
                        needOne = execDataIn.readInt();
                        needTwo = execDataIn.readInt();
                    } else {
                        Player.getInstance().money = execDataIn.readInt();
                        Player.getInstance().money = Player.getInstance().money > 1999999999 ? 1999999999 : Player.getInstance().money;
                        reputation = execDataIn.readInt();
                        honor = execDataIn.readInt();
                        if (honorSign != 5) {
                            for (i = 0; i < 18; ++i) {
                                if (MainCanvas.mc.grids[0].stuffNumber[i] > 0) {
                                    Util.shopSign[i] = true;
                                } else {
                                    Util.shopSign[i] = false;
                                }
                            }
                        }
                    }

                    MainCanvas.mc.setNPCSubState((byte) 2);
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.mc.baseForm = null;
                }
                break;
            case 152048000:
                MainCanvas.mc.tradePlace = execDataIn.readByte();
                if (MainCanvas.dramatisPackage.stuffId[MainCanvas.mc.tradePlace] != 0) {
                    var10000 = MainCanvas.dramatisPackage.stuffNumber;
                    var10001 = MainCanvas.mc.tradePlace;
                    var10000[var10001] += MainCanvas.mc.tradeNumber;
                } else {
                    MainCanvas.dramatisPackage.setGridDetail(MainCanvas.mc.tradePlace, MainCanvas.mc.grids[0].getCurrentId(), MainCanvas.mc.grids[0].getCurrentImageId(), MainCanvas.mc.tradeNumber, MainCanvas.mc.grids[0].getCurrentName(), MainCanvas.mc.grids[0].getCurrentNameLevel(), MainCanvas.mc.grids[0].getCurrentLittleType(), MainCanvas.mc.grids[0].getCurrentTrade(), (byte) 0);
                }

                MainCanvas.mc.packageStuffPrice[MainCanvas.mc.tradePlace] = execDataIn.readInt();
                MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
                if (honorSign == 3) {
                    i = execDataIn.readByte();
                    if (MainCanvas.dramatisPackage.stuffNumber[i] == 1) {
                        MainCanvas.dramatisPackage.setGridNull(i);
                    } else {
                        --MainCanvas.dramatisPackage.stuffNumber[i];
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
                    i = MainCanvas.mc.grids[0].getCurrentPointer();
                    if (Util.shopSign[i]) {
                        if (MainCanvas.mc.grids[0].getCurrentNumber() == MainCanvas.mc.tradeNumber) {
                            MainCanvas.mc.grids[0].setCurrentNull();
                            MainCanvas.stuffName.setStr(" ");
                        } else {
                            var10000 = MainCanvas.mc.grids[0].stuffNumber;
                            var10000[i] -= MainCanvas.mc.tradeNumber;
                        }
                    }

                    i = MainCanvas.mc.shopStuffPrice[MainCanvas.mc.grids[0].getCurrentPointer()];
                    if (isHonorStuff()) {
                        honor -= i * MainCanvas.mc.tradeNumber;
                        MainCanvas.mc.texts[1].setLabel(honor + "");
                    } else if (i > 1000000000) {
                        i -= 1000000000;
                        reputation -= i * MainCanvas.mc.tradeNumber;
                        MainCanvas.mc.texts[1].setLabel(reputation + "");
                    } else {
                        var120 = Player.getInstance();
                        var120.money -= i * MainCanvas.mc.tradeNumber;
                        MainCanvas.mc.texts[1].setLabel(Player.getInstance().money + "");
                    }
                }

                if (honorSign == 5) {
                    for (i = 0; i < 18; i = (byte) (i + 1)) {
                        MainCanvas.mc.grids[0].setSomeoneNumber((byte) i, execDataIn.readByte());
                    }
                }
                break;
            case 152048256:
                i = 1;
                if (honorSign == 3) {
                    switch (execDataIn.readByte()) {
                        case 0:
                            mgs = "兑换不成功，资金不足或包裹已满!";
                            break;
                        case 1:
                            mgs = "您的兑换操作会降低氏族等级，请积累更多氏族声望再来兑换。";
                    }
                } else if (honorSign == 4) {
                    mgs = execDataIn.readUTF();
                } else {
                    try {
                        i = execDataIn.readByte();
                    } catch (Exception var77) {
                        var77.printStackTrace();
                    }

                    switch (i) {
                        case 0:
                            mgs = "您的氏族已经降级,不能使用该氏族商店";
                            break;
                        case 1:
                            mgs = "购买不成功，资金不足或包裹已满!";
                            break;
                        case 2:
                            mgs = "购买不成功，商店中该类商品已经售空!";
                    }

                    if (honorSign == 5 && i != 0) {
                        for (i = 0; i < 18; i = (byte) (i + 1)) {
                            MainCanvas.mc.grids[0].setSomeoneNumber((byte) i, execDataIn.readByte());
                        }
                    }
                }

                MainCanvas.mc.baseForm.addAboutForm("cannotBuy", mgs, (byte) 1, MainCanvas.screenW - 20, 0);
                break;
            case 152052096:
                var120 = Player.getInstance();
                var120.money += MainCanvas.mc.packageStuffPrice[MainCanvas.dramatisPackage.getCurrentPointer()] * MainCanvas.mc.tradeNumber;
                if (MainCanvas.mc.tradeNumber < MainCanvas.dramatisPackage.getCurrentNumber()) {
                    var10000 = MainCanvas.dramatisPackage.stuffNumber;
                    var10001 = MainCanvas.dramatisPackage.getCurrentPointer();
                    var10000[var10001] -= MainCanvas.mc.tradeNumber;
                } else {
                    MainCanvas.dramatisPackage.setCurrentNull();
                    MainCanvas.mc.texts[0].setLabel("0");
                    MainCanvas.stuffName.setStr("  ");
                    MainCanvas.mc.packageStuffPrice[MainCanvas.dramatisPackage.getCurrentPointer()] = 0;
                }

                if (getHonorSign() == 3 || getHonorSign() == 4) {
                    MainCanvas.mc.labels[3].setStr("玩家金钱");
                }

                MainCanvas.mc.texts[1].setLabel(Player.getInstance().money + "");
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                break;
            case 152052352:
                i = execDataIn.readByte();
                switch (i) {
                    case -1:
                        MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                        MainCanvas.setMessage(MainCanvas.mc.baseForm, "您的氏族等级不够,不能使用该氏族商店");
                        return;
                    case 0:
                        MainCanvas.setMessage(MainCanvas.mc.baseForm, "出售失败");
                        return;
                    case 1:
                        MainCanvas.setMessage(MainCanvas.mc.baseForm, "该物品不能出售");
                        return;
                    default:
                        return;
                }
            case 154140800:
                MainCanvas.fixAllPrice = 0;

                for (temp = 0; temp < 8; ++temp) {
                    MainCanvas.equipStuffId[temp] = execDataIn.readShort();
                    MainCanvas.equipImageId[temp] = execDataIn.readByte();
                    MainCanvas.equipSruffName[temp] = execDataIn.readUTF();
                    MainCanvas.fixPrice[temp] = execDataIn.readInt();
                    MainCanvas.fixAllPrice += MainCanvas.fixPrice[temp];
                    MainCanvas.equipStuffNameLevel[temp] = execDataIn.readByte();
                }

                Player.getInstance().money = execDataIn.readInt();
                if (Player.getInstance().money == -1) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "您的氏族等级不够,不能使用氏族修理所");
                } else {
                    Player.getInstance().money = Player.getInstance().money > 1999999999 ? 1999999999 : Player.getInstance().money;
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.mc.releaseUI();
                    MainCanvas.mc.setNPCSubState((byte) 3);
                }
                break;
            case 154141056:
                temp = execDataIn.readByte();
                Player.getInstance().money = execDataIn.readInt();
                Player.getInstance().money = Player.getInstance().money > 1999999999 ? 1999999999 : Player.getInstance().money;
                MainCanvas.mc.texts[2].setLabel(Player.getInstance().money + "");
                if (temp == 0) {
                    if (MainCanvas.fixPlace < 8) {
                        MainCanvas.fixAllPrice -= MainCanvas.fixPrice[MainCanvas.fixPlace];
                        MainCanvas.fixPrice[MainCanvas.fixPlace] = 0;
                        MainCanvas.mc.texts[0].setLabel(MainCanvas.fixPrice[MainCanvas.fixPlace] + "");
                        MainCanvas.equipImageId[MainCanvas.fixPlace] = 0;
                        MainCanvas.equipStuffId[MainCanvas.fixPlace] = 0;
                        MainCanvas.equipSruffName[MainCanvas.fixPlace] = "";
                    } else {
                        MainCanvas.fixAllPrice = 0;

                        for (tempNumber = 0; tempNumber < 8; ++tempNumber) {
                            MainCanvas.fixPrice[tempNumber] = 0;
                            MainCanvas.equipImageId[tempNumber] = 0;
                            MainCanvas.equipStuffId[tempNumber] = 0;
                            MainCanvas.equipSruffName[tempNumber] = "";
                        }

                        MainCanvas.mc.texts[0].setLabel("0");
                    }

                    MainCanvas.mc.labels[0].setStr("");
                    MainCanvas.mc.texts[1].setLabel(MainCanvas.fixAllPrice + "");
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                } else {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "金钱不足，修理失败");
                }
                break;
            case 155189376:
                MainCanvas.mc.petSkillId = execDataIn.readByte();
                String strDetail = execDataIn.readUTF();
                int learnMoney = execDataIn.readInt();
                String strName = execDataIn.readUTF();
                byte materialNumber = execDataIn.readByte();
                String tempStr1 = null;
                if (materialNumber != 0) {
                    if (learnMoney == 0) {
                        tempStr1 = "学习条件：" + strName + "×" + materialNumber + "。";
                    } else {
                        tempStr1 = "学习条件：" + strName + "×" + materialNumber + "，" + "金钱" + learnMoney + "。";
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
                MainCanvas.mc.labels[9] = new UILabel(MainCanvas.mc.rims[9].positionX + 2, MainCanvas.mc.rims[9].positionY + 2, MainCanvas.mc.rims[9].width, 0, tempStr1, 16316576, (byte) 0, (byte) 0);
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
            case 156237952:
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.setMessage(MainCanvas.mc.baseForm, "恭喜您，领养成功！");
                MainCanvas.pet = new Pets();
                break;
            case 156238208:
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                if (execDataIn.readByte() == 0) {
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "您的等级不够!");
                } else {
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "您已经有一只宠物了！");
                }
                break;
            case 157286528:
                String[] items = null;
                MainCanvas.mc.whichList = execDataIn.readByte();
                if (MainCanvas.mc.whichList == 1) {
                    items = Cons.NPC_EXCHANGE_0;
                } else if (MainCanvas.mc.whichList == 2) {
                    items = Cons.NPC_EXCHANGE_1;
                } else if (MainCanvas.mc.whichList == 3) {
                    items = Cons.NPC_EXCHANGE_2;
                }

                int len1 = items.length;
                MainCanvas.mc.releaseUI();
                exchangeTable = null;
                exchangeTable = new UITable(0, 40, 105, 150, len1, 1, 7, (byte) 0, (byte) 2);
                exchangeTable.setSingleWH((short) 100, (byte) 20, false);

                for (color = 0; color < len1; ++color) {
                    exchangeTable.addItem(items[color], 15718815);
                }

                MainCanvas.mc.setNPCSubState((byte) 10);
                MainCanvas.mc.setNPCExchangeState((byte) 0);
                break;
            case 157286784:
                MainCanvas.mc.changeMax = execDataIn.readShort();
                if (MainCanvas.mc.changeMax < 10000) {
                    MainCanvas.mc.changeMax = MainCanvas.mc.changeMax > 20 ? 20 : MainCanvas.mc.changeMax;
                }

                int[][] p = new int[][]{{25, 60}, {25, 86}, {25, 112}};
                sb = new StringBuffer();
                sb.append(exchangeTable.getCurentItem()).append("需要:");
                MainCanvas.mc.releaseUI();

                for (i = 0; i < 3; ++i) {
                    j = execDataIn.readByte();
                    if (j == 0) {
                        break;
                    }

                    MainCanvas.mc.rims[i + 1] = new UIRim(p[i][0], p[i][1], 17, 17, (byte) 0);
                    MainCanvas.mc.mImages[i] = new UIMImage(MainCanvas.mc.rims[i + 1].positionX + 1, MainCanvas.mc.rims[i + 1].positionY + 1, 0, 0, MainCanvas.mImgStuff, (byte) 0);
                    MainCanvas.mc.mImages[i].setCurrentFrame((byte) (j - 1));
                    MainCanvas.mc.labels[i] = new UILabel(MainCanvas.mc.mImages[i].positionX + 30, MainCanvas.mc.mImages[i].positionY + 3, 0, 0, execDataIn.readUTF() + "× 1", 15718815, (byte) 0, (byte) 0);
                }

                MainCanvas.mc.labels[5] = new UILabel(20, 10, 0, 0, sb.toString(), 15718815, (byte) 0, (byte) 0);
                MainCanvas.mc.setNPCExchangeState((byte) 1);
                break;
            case 157287040:
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.mc.baseForm.addAboutForm("succeed", "您兑换的东西已经放入包裹中，快去看看吧", (byte) 1, MainCanvas.screenW - 30, 0);
                break;
            case 160432256:
                Player.getInstance().money = execDataIn.readInt();
                Player.getInstance().money = Player.getInstance().money > 1999999999 ? 1999999999 : Player.getInstance().money;
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
                        MainCanvas.mc.mImages[9].setCurrentFrame((byte) (execDataIn.readByte() - 1));
                        MainCanvas.mc.labels[9] = new UILabel(60, 52, 0, 0, execDataIn.readUTF() + "×1", 15718815, (byte) 0, (byte) 0);
                        MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.mImages[9]);
                        MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.labels[9]);
                    }

                    MainCanvas.mc.labels[0].setStr("精炼此物品必要素材");
                    MainCanvas.mc.labels[0].setXY(MainCanvas.mc.labels[0].positionX, 12);
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.dramatisPackage.isLock1 = true;
                    MainCanvas.mc.labels[3].setStr("精炼");
                    MainCanvas.mc.labels[4].setStr("取消");
                } else {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "此物品已经达到最大精炼上限");
                }
                break;
            case 160432768:
                byte tp = execDataIn.readByte();
                temp = execDataIn.readByte();
                if (tp == 0) {
                    MainCanvas.mc.texts[0].setLabel(Player.getInstance().money + "");
                    MainCanvas.mc.isHole = execDataIn.readByte();
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    str = null;
                    if (MainCanvas.mc.isHole == 0) {
                        str = "看起来这宝贝变的更厉害了，快试试它的威力吧！";
                    } else {
                        str = "真是意外的惊喜，您的宝贝被我弄出几个洞来，这样就能镶嵌宝石了。";
                    }

                    MainCanvas.mc.baseForm.addAboutForm("msg", str, (byte) 1, 220, 0);
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
                    var120 = Player.getInstance();
                    var120.money -= MainCanvas.mc.smithMoney;
                    Player.getInstance().money = Player.getInstance().money < 0 ? 0 : Player.getInstance().money;
                }

                if (temp >= 0 && tp != 2) {
                    --MainCanvas.dramatisPackage.stuffNumber[temp];
                    if (MainCanvas.dramatisPackage.stuffNumber[temp] == 0) {
                        MainCanvas.dramatisPackage.stuffId[temp] = 0;
                        MainCanvas.dramatisPackage.stuffImageId[temp] = 0;
                        MainCanvas.dramatisPackage.stuffName[temp] = "";
                    }
                }

                MainCanvas.mc.texts[0].setLabel(Player.getInstance().money + "");
                break;
            case 161480832:
                MainCanvas.mc.grids[0] = new UIGrid(0, 18, (byte) 4, (byte) 9, (byte) 3, MainCanvas.mImgStuff);

                for (tempNumber = 0; tempNumber < 36; ++tempNumber) {
                    MainCanvas.mc.grids[0].setGridDetail(tempNumber, execDataIn.readShort(), execDataIn.readByte(), execDataIn.readByte(), execDataIn.readUTF(), execDataIn.readByte(), execDataIn.readShort(), execDataIn.readByte(), execDataIn.readByte());
                }

                Player.getInstance().money = execDataIn.readInt();
                if (Player.getInstance().money == -1) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "您的氏族等级不够,不能使用氏族仓库");
                } else {
                    Player.getInstance().money = Player.getInstance().money > 1999999999 ? 1999999999 : Player.getInstance().money;
                    MainCanvas.mc.storeMoney = execDataIn.readInt();
                    MainCanvas.mc.setNPCSubState((byte) 9);
                    MainCanvas.mc.baseForm = null;
                }
                break;
            case 161484928:
                temp = execDataIn.readByte();
                if (temp == -1) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "您的氏族等级不够,不能使用氏族仓库");
                } else if (temp == 0) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.mc.grids[0].changeStuff((short) MainCanvas.mc.grids[0].stuffPlace, (short) MainCanvas.mc.grids[0].getCurrentPointer());
                    MainCanvas.stuffName.setColor(Cons.STUFF_NAME_COLOR[MainCanvas.mc.grids[0].getCurrentNameLevel()]);
                    MainCanvas.stuffName.setStr(MainCanvas.mc.grids[0].getCurrentName());
                } else {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "请求失败，请重试");
                }
                break;
            case 161489024:
                temp = execDataIn.readByte();
                if (temp == -1) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "此物品不能存入氏族仓库");
                } else if (temp == -2) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "您的氏族等级不够,不能使用氏族仓库");
                } else if (temp == -3) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    tmpss = execDataIn.readUTF();
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, tmpss);
                } else {
                    tempNumber = MainCanvas.mc.grids[0].getSomeoneNumber(temp);
                    if (temp < 36) {
                        if (tempNumber > 0) {
                            MainCanvas.mc.grids[0].setSomeoneNumber(temp, (byte) (tempNumber + MainCanvas.mc.stNumber));
                        } else {
                            MainCanvas.mc.grids[0].setGridDetail(temp, MainCanvas.dramatisPackage.getCurrentId(), MainCanvas.dramatisPackage.getCurrentImageId(), MainCanvas.mc.stNumber, MainCanvas.dramatisPackage.getCurrentName(), MainCanvas.dramatisPackage.getCurrentNameLevel(), MainCanvas.dramatisPackage.getCurrentLittleType(), MainCanvas.dramatisPackage.getCurrentTrade(), (byte) 0);
                        }

                        if (MainCanvas.mc.stNumber == MainCanvas.dramatisPackage.getCurrentNumber()) {
                            MainCanvas.dramatisPackage.setCurrentNull();
                            MainCanvas.stuffName.setStr(" ");
                        } else {
                            var10000 = MainCanvas.dramatisPackage.stuffNumber;
                            var10001 = MainCanvas.mc.stPlace;
                            var10000[var10001] -= MainCanvas.mc.stNumber;
                        }

                        MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    } else {
                        MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                        MainCanvas.setMessage(MainCanvas.mc.baseForm, "您的仓库已满，无法再存入物品");
                    }
                }
                break;
            case 161493120:
                temp = execDataIn.readByte();
                if (temp == -1) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "您的氏族等级不够,不能使用氏族仓库");
                } else {
                    tempNumber = MainCanvas.dramatisPackage.getSomeoneNumber(temp);
                    if (temp < 36) {
                        if (tempNumber > 0) {
                            MainCanvas.dramatisPackage.setSomeoneNumber(temp, (byte) (tempNumber + MainCanvas.mc.stNumber));
                        } else {
                            MainCanvas.dramatisPackage.setGridDetail(temp, MainCanvas.mc.grids[0].getCurrentId(), MainCanvas.mc.grids[0].getCurrentImageId(), MainCanvas.mc.stNumber, MainCanvas.mc.grids[0].getCurrentName(), MainCanvas.mc.grids[0].getCurrentNameLevel(), MainCanvas.mc.grids[0].getCurrentLittleType(), MainCanvas.mc.grids[0].getCurrentTrade(), (byte) 0);
                        }

                        if (MainCanvas.mc.stNumber == MainCanvas.mc.grids[0].getCurrentNumber()) {
                            MainCanvas.mc.grids[0].setCurrentNull();
                            MainCanvas.stuffName.setStr(" ");
                        } else {
                            var10000 = MainCanvas.mc.grids[0].stuffNumber;
                            var10001 = MainCanvas.mc.stPlace;
                            var10000[var10001] -= MainCanvas.mc.stNumber;
                        }

                        MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    } else {
                        MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                        MainCanvas.setMessage(MainCanvas.mc.baseForm, "背包已满不能取出");
                    }
                }
                break;
            case 161497216:
                temp = execDataIn.readByte();
                if (temp == -1) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "您的氏族等级不够,不能使用氏族仓库");
                } else if (temp == 0) {
                    if (MainCanvas.mc.discardPlace == 0) {
                        MainCanvas.dramatisPackage.setCurrentNull();
                        MainCanvas.stuffName.setStr("  ");
                    } else {
                        MainCanvas.mc.grids[0].setCurrentNull();
                        MainCanvas.stuffName.setStr("  ");
                    }

                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.stuffName.setStr((String) null);
                } else {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "请求失败，请重试");
                }
                break;
            case 161501312:
                for (height = 0; height < 36; height = (byte) (height + 1)) {
                    MainCanvas.mc.grids[0].setGridDetail((byte) height, execDataIn.readShort(), execDataIn.readByte(), execDataIn.readByte(), execDataIn.readUTF(), execDataIn.readByte(), execDataIn.readShort(), execDataIn.readByte(), execDataIn.readByte());
                }

                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                if (MainCanvas.mc.baseForm.focusComponent == MainCanvas.mc.grids[0]) {
                    MainCanvas.stuffName.setStr(MainCanvas.mc.grids[0].getCurrentName());
                    MainCanvas.stuffName.setColor(Cons.STUFF_NAME_COLOR[MainCanvas.mc.grids[0].getCurrentNameLevel()]);
                }
                break;
            case 161505408:
            case 161509504:
                temp = execDataIn.readByte();
                if (temp == -1) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "您的氏族等级不够,不能使用氏族仓库");
                } else if (temp == 0) {
                    MainCanvas.mc.texts[1].setLabel(execDataIn.readInt() + "");
                    MainCanvas.mc.texts[0].setLabel(execDataIn.readInt() + "");
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                } else {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "请求失败，请重试");
                }
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
                String sss = "";
                byte st = 0;

                try {
                    st = execDataIn.readByte();
                } catch (Exception var76) {
                }

                if (st == 0) {
                    sss = sss + "(任务)";
                } else if (st == 1) {
                    sss = sss + "(未完成)";
                } else if (st == 2) {
                    sss = sss + "(已完成)";
                }

                MainCanvas.NPCMenu.strs[MainCanvas.NPCMenu.getCurrentPointer()] = sb.toString() + sss;
                MainCanvas.NPCMenu.setCurrnetRealState(st);
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.mc.setNPCSubState((byte) 0);
                MainCanvas.mc.releaseUI();
                break;
            case 162529920:
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                String taskErr = null;
                byte acceptResult = execDataIn.readByte();
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
                    for (i = 0; i < tempNumber; i = (byte) (i + 1)) {
                        MainCanvas.mc.taskId[i] = execDataIn.readInt();
                        s[i] = execDataIn.readUTF();
                        state[i] = execDataIn.readByte();
                        colorSign[i] = execDataIn.readByte();
                        if (state[i] == 0) {
                            s[i] = s[i] + "(任务)";
                        } else if (state[i] == 1) {
                            s[i] = s[i] + "(未完成)";
                        } else if (state[i] == 2) {
                            s[i] = s[i] + "(已完成)";
                        }
                    }

                    MainCanvas.mc.releaseUI();
                    MainCanvas.mc.tables[0] = new UITable(0, 30, 135, 160, tempNumber, 1, tempNumber, (byte) 0, (byte) 2);
                    MainCanvas.mc.tables[0].setSingleWH((short) 120, (byte) 20, false);

                    for (i = 0; i < tempNumber; ++i) {
                        color = 15718814;
                        if (colorSign[i] == 0) {
                            color = 65280;
                        }

                        MainCanvas.mc.tables[0].setItem(s[i], i, color);
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
                UIRim rims0 = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte) 4);
                UILabel labelBack = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 0, (byte) 0);
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

                UITextArea textArea0 = new UITextArea(5, 6, 164, 112, "　" + MainCanvas.mc.taskDetail[0]);
                rim = new UIRim(5, 120, 164, 70, (byte) 0);
                UILabel label = new UILabel(rim.positionX + 4, rim.positionY + 4, 164, 0, "您将得到：", 16316576, (byte) 0, (byte) 0);
                textArea0.setColor(10981736);
                textArea0.setCanFocus(false);
                String ss = null;
                if (MainCanvas.mc.taskStuffId[0] == 0) {
                    ss = "  ";
                } else {
                    ss = "查看";
                }

                UILabel labelOk = new UILabel(0, 0, 0, 0, ss, 15718814, (byte) 0, (byte) 0);
                subForm.addComponent(rims0);
                subForm.addComponent(textArea0);
                subForm.addComponent(rim);
                subForm.addComponent(label);
                subForm.addComponentInCenter(labelBack, (byte) 6);
                subForm.addComponentInCenter(labelOk, (byte) 5);
                int[][] tempXY = new int[][]{{8, 144}, {87, 144}, {8, 170}, {87, 170}};
                if (MainCanvas.mc.taskStuffId[0] != 0) {
                    MainCanvas.mc.mImages[0] = new UIMImage(tempXY[0][0], tempXY[0][1], 18, 18, MainCanvas.mImgStuff, (byte) 0);
                    MainCanvas.mc.mImages[0].setHaveRim(true);
                    MainCanvas.mc.mImages[0].index = 0;
                    MainCanvas.mc.mImages[0].setCurrentFrame(MainCanvas.mc.taskStuffImageId[0]);
                    MainCanvas.mc.mImages[0].setNumber(MainCanvas.mc.taskThingNumber[0]);
                }

                if (MainCanvas.mc.taskStuffId[1] != 0) {
                    MainCanvas.mc.mImages[1] = new UIMImage(tempXY[1][0], tempXY[1][1], 18, 18, MainCanvas.mImgStuff, (byte) 0);
                    MainCanvas.mc.mImages[1].setHaveRim(true);
                    MainCanvas.mc.mImages[1].index = 1;
                    MainCanvas.mc.mImages[1].setCurrentFrame(MainCanvas.mc.taskStuffImageId[1]);
                    MainCanvas.mc.mImages[1].setNumber(MainCanvas.mc.taskThingNumber[1]);
                }

                UILabel[] uilb = new UILabel[6];
                if (MainCanvas.mc.taskStuffId[1] != 0) {
                    uilb[0] = new UILabel(28, 145, 0, 0, MainCanvas.mc.taskDetail[1].length() > 4 ? MainCanvas.mc.taskDetail[1].substring(0, 3) + ".." : MainCanvas.mc.taskDetail[1], 15718814, (byte) 0, (byte) 0);
                    uilb[1] = new UILabel(110, 145, 0, 0, MainCanvas.mc.taskDetail[2].length() > 4 ? MainCanvas.mc.taskDetail[2].substring(0, 3) + ".." : MainCanvas.mc.taskDetail[2], 15718814, (byte) 0, (byte) 0);
                } else {
                    uilb[0] = new UILabel(28, 145, 0, 0, MainCanvas.mc.taskDetail[1], 15718814, (byte) 0, (byte) 0);
                }

                ss = null;
                if (MainCanvas.mc.taskExperiecce <= 0) {
                    ss = "";
                    uilb[2] = null;
                } else {
                    ss = String.valueOf(MainCanvas.mc.taskExperiecce);
                    uilb[2] = new UILabel(tempXY[3][0], tempXY[3][1], 0, 0, "经验：" + ss, 15718814, (byte) 0, (byte) 0);
                }

                if (MainCanvas.mc.taskMoney <= 0) {
                    ss = "";
                    uilb[3] = null;
                } else {
                    ss = (MainCanvas.mc.taskType == 0 ? "金钱：" : "声望：") + String.valueOf(MainCanvas.mc.taskMoney);
                    uilb[3] = new UILabel(tempXY[2][0], tempXY[2][1], 0, 0, ss, 15718814, (byte) 0, (byte) 0);
                }

                if (MainCanvas.mc.mImages[0] != null && MainCanvas.mc.mImages[1] != null) {
                    MainCanvas.mc.mImages[0].setAroundComponent(MainCanvas.mc.mImages[1], (byte) 4);
                }

                ss = null;
                ss = MainCanvas.mc.taskDetail[3] + MainCanvas.mc.taskDetail[4];
                int yy = textArea0.positionY + textArea0.height - MainCanvas.CHARH * 2 - 3;
                uilb[4] = new UILabel(textArea0.positionX + 2, yy, rim.width - 5, 0, MainCanvas.mc.taskDetail[3] + MainCanvas.mc.taskDetail[4], 15718814, (byte) 0, (byte) 0);

                for (i = 0; i < 5; ++i) {
                    if (uilb[i] != null) {
                        subForm.addComponent(uilb[i]);
                    }
                }

                uilb[4].setXY(textArea0.positionX + 2, yy);

                for (i = 0; i < 2; ++i) {
                    if (MainCanvas.mc.mImages[i] != null) {
                        if (MainCanvas.mc.mImages[i].getCurrentFrame() < 33) {
                            MainCanvas.mc.mImages[i].setNumberVisiable(false);
                        }

                        subForm.addComponent(MainCanvas.mc.mImages[i]);
                    }
                }

                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.mc.baseForm.setAboutForm(subForm);
                break;
            case 162530688:
                i = MainCanvas.mc.tables[0].getCurrentPointer();
                MainCanvas.mc.tables[0].deleteItem(i);
                i = (byte) MainCanvas.mc.taskId.length;

                for (i = i; i < i - 1; ++i) {
                    MainCanvas.mc.taskId[i] = MainCanvas.mc.taskId[i + 1];
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
                i = execDataIn.readByte();
                String undoneMsg = null;
                switch (i) {
                    case 1:
                        undoneMsg = "背包已满，无法完成任务";
                        break;
                    case 2:
                        undoneMsg = "每日任务已被重置，无法完成任务";
                        break;
                    default:
                        undoneMsg = "无法完成任务，原因未知";
                }

                MainCanvas.setMessage(MainCanvas.mc.baseForm, undoneMsg);
                break;
            case 163577984:
                temp = 0;
                short[] tempShort = new short[6];

                for (j = 0; j < 6; ++j) {
                    tempShort[j] = execDataIn.readShort();
                    ++temp;
                    if (tempShort[j] == 0) {
                        --temp;
                    }
                }

                MainCanvas.mc.npcHelpId = new short[temp];
                MainCanvas.mc.npcHelpItem = new String[temp];

                for (j = 0; j < temp; ++j) {
                    MainCanvas.mc.npcHelpId[j] = tempShort[j];
                    MainCanvas.mc.npcHelpItem[j] = execDataIn.readUTF();
                }

                MainCanvas.mc.menus[1] = new UIMenu(0, 0, 132, 0, (String) null, MainCanvas.mc.npcHelpItem);
                MainCanvas.mc.menus[1].setFlushType((byte) 1);
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.NPCMenu.setSubMenu(MainCanvas.mc.menus[1]);
                break;
            case 163578240:
                String tempS = execDataIn.readUTF();
                UITextArea textArea11 = new UITextArea(5, 48, 164, 142, tempS);
                textArea11.setColor(15787196);
                subForm = new UIForm(0, 0, 176, 208, "detail");
                subForm.setBackGround((byte) 9);
                subForm.addComponentInCenter(textArea11, (byte) 2);
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.mc.baseForm.setAboutForm(subForm);
                MainCanvas.mc.baseForm.getSubForm().width = 170;
                MainCanvas.mc.labels[5].setStr("  ");
                break;
            case 163643520:
                int index = execDataIn.readByte();
                if (index == 1) {
                    commonNumber = execDataIn.readByte();
                    weeks = new short[commonNumber];
                    alliance = new int[commonNumber];
                    horde = new int[commonNumber];

                    for (i = 0; i < commonNumber; ++i) {
                        weeks[i] = execDataIn.readShort();
                        alliance[i] = execDataIn.readInt();
                        horde[i] = execDataIn.readInt();
                    }

                    MainCanvas.mc.setNPCSubState((byte) 27);
                    MainCanvas.mc.releaseUI();
                } else if (index == 2) {
                    allianceRate = execDataIn.readInt();
                    hordeRate = execDataIn.readInt();
                    MainCanvas.mc.setNPCSubState((byte) 28);
                    MainCanvas.mc.releaseUI();
                }
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
                }

                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.setMessage(MainCanvas.mc.baseForm, mgs);
                break;
            case 164626560:
                byte tempcount = execDataIn.readByte();
                MainCanvas.mc.npcPosXY = new String[tempcount];

                for (flag = 0; flag < tempcount; ++flag) {
                    MainCanvas.mc.npcPosXY[flag] = "  " + execDataIn.readUTF();
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
            case 251658624:
                byte add = execDataIn.readByte();
                byte create = execDataIn.readByte();
                MainCanvas.mc.clanExit = execDataIn.readByte();
                byte disband = execDataIn.readByte();
                MainCanvas.mc.clanConfirmMoney = execDataIn.readInt();
                MainCanvas.mc.clanName = execDataIn.readUTF();
                String[] str2 = new String[]{"加入氏族", "创建氏族", "退出氏族"};
                if (MainCanvas.mc.clanExit == 2) {
                    str2[2] = "取消申请";
                }

                MainCanvas.mc.releaseUI();
                if (add + create + MainCanvas.mc.clanExit + disband > 0) {
                    MainCanvas.mc.menus[0] = new UIMenu(5, 48, 164, 142, null, str2);
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
            case 251658880:
                clanTotal = execDataIn.readInt();
                clanCurrentPage = execDataIn.readInt();
                byte clannum = execDataIn.readByte();
                MainCanvas.mc.clanList = null;
                MainCanvas.mc.clanList = new String[clannum];

                for (i = 0; i < clannum; ++i) {
                    MainCanvas.mc.clanList[i] = execDataIn.readUTF();
                }

                addForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "addclan");
                addForm.setBackGround((byte) 9);
                frame = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte) 4);
                rimTitle = new UIRim(0, 12, 166, 18, (byte) 7);
                lblTitle = new UILabel(0, 15, 176, 0, "氏族列表", 15718814, (byte) 1, (byte) 0);
                lblOk = new UILabel(60, 7, 0, 0, "操作", 15718814, (byte) 0, (byte) 0);
                lblCancel = new UILabel(60, 7, 0, 0, "返回", 15718814, (byte) 0, (byte) 0);
                MainCanvas.mc.tables[0] = null;
                UILabel bclPage = null;
                UIMImage leftImg = null;
                UIMImage rightImg = null;
                if (clannum != 0) {
                    MainCanvas.mc.tables[0] = new UITable(0, 30, 166, 156, clannum, 1, clannum > 10 ? 10 : clannum, (byte) 0, (byte) 3);
                    MainCanvas.mc.tables[0].setSingleWH(MainCanvas.mc.tables[0].singleWidth, (byte) 18, false);

                    for (i = 0; i < clannum; ++i) {
                        MainCanvas.mc.tables[0].addItem(MainCanvas.mc.clanList[i], 65280);
                    }

                    page = clanCurrentPage + 1 + "/" + clanTotal;
                    bclPage = new UILabel(0, 193, 0, 0, page, 15718814, (byte) 1, (byte) 0);
                    leftImg = new UIMImage(55, 196, 4, 5, MainCanvas.mImgUI[22], (byte) 0);
                    leftImg.setCurrentFrame((byte) 0);
                    leftImg.setNumberVisiable(false);
                    rightImg = new UIMImage(MainCanvas.screenW - 55 - 4, 196, 4, 5, MainCanvas.mImgUI[22], (byte) 0);
                    rightImg.setCurrentFrame((byte) 1);
                    rightImg.setNumberVisiable(false);
                }

                addForm.addComponent(frame);
                addForm.addComponentInCenter(rimTitle, (byte) 2);
                addForm.addComponentInCenter(lblTitle, (byte) 2);
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
                    page = "申请加入" + MainCanvas.mc.tables[0].getCurentItem() + "氏族成功！";
                } else {
                    page = MainCanvas.mc.tables[0].getCurentItem() + "氏族人数已满！";
                }

                MainCanvas.mc.baseForm.getSubForm().addAboutForm("addreturn", page, (byte) 1, 150, 50);
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
                }

                MainCanvas.mc.baseForm.getSubForm().addAboutForm("createreturn", message, (byte) 1, 150, 50);
                break;
            case 251659648:
                byte exitState = execDataIn.readByte();
                String exitInfo;
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
            case 251659904:
                if (execDataIn.readByte() == 1) {
                    str = "解散氏族成功！";
                } else {
                    str = "解散氏族失败！";
                }

                MainCanvas.mc.baseForm.addAboutForm("confirm", str, (byte) 1, 140, 50);
                break;
            case 251660160:
                MainCanvas.mc.clanName = execDataIn.readUTF();
                MainCanvas.mc.clanManegeLevel = execDataIn.readByte();
                MainCanvas.mc.setRightMenuSubState(3);
                MainCanvas.mc.setUISocietyState((byte) 4);
                MainCanvas.mc.releaseUI();
                break;
            case 251660416:
                MainCanvas.mc.clanManegeLevel = execDataIn.readByte();
                clanTotal = execDataIn.readInt();
                clanCurrentPage = execDataIn.readShort();
                byte memNum = execDataIn.readByte();
                MainCanvas.mc.clanMemberName = new String[memNum];
                MainCanvas.mc.clanMemberId = new int[memNum];
                MainCanvas.mc.clanMemberLevel = new byte[memNum];

                for (i = 0; i < memNum; ++i) {
                    MainCanvas.mc.clanMemberId[i] = execDataIn.readInt();
                    MainCanvas.mc.clanMemberName[i] = execDataIn.readUTF();
                    MainCanvas.mc.clanMemberLevel[i] = execDataIn.readByte();
                }

                clanTotalPage = clanTotal / 7;
                if (clanTotal % 7 != 0) {
                    ++clanTotalPage;
                }

                page = clanCurrentPage + "/" + clanTotalPage;
                lblPage = new UILabel(0, 195, 0, 0, page, 15718814, (byte) 1, (byte) 0);
                addForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "viewMem");
                addForm.setBackGround((byte) 9);
                frame = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte) 4);
                rimTitle = new UIRim(0, 12, 166, 20, (byte) 7);
                lblTitle = new UILabel(0, 15, 176, 0, "氏族成员列表", 15718814, (byte) 1, (byte) 0);
                rimUp = new UIRim(0, 32, 166, 160, (byte) 0);
                lblCancel = new UILabel(60, 7, 0, 0, "返回", 15718814, (byte) 0, (byte) 0);
                MainCanvas.mc.tables[0] = null;
                if (memNum != 0) {
                    MainCanvas.mc.tables[0] = new UITable(0, 34, 161, 156, memNum, 1, memNum > 7 ? 7 : memNum, (byte) 0, (byte) 3);
                    MainCanvas.mc.tables[0].setSingleWH(MainCanvas.mc.tables[0].singleWidth, (byte) 21, false);

                    for (i = 0; i < memNum; ++i) {
                        MainCanvas.mc.tables[0].setItem(MainCanvas.mc.clanMemberName[i] + "  " + Cons.UNION_NAMES[MainCanvas.mc.clanMemberLevel[i]], i, 65280);
                    }
                }

                addForm.addComponent(frame);
                addForm.addComponentInCenter(rimTitle, (byte) 2);
                addForm.addComponentInCenter(lblTitle, (byte) 2);
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
                } else if (memNum == 0) {
                    if (clanTotal == 0) {
                        MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                        MainCanvas.mc.baseForm.getCurrentFocusForm().addAboutForm("noMem", "氏族不存在！", (byte) 1, 140, 50);
                    } else {
                        --clanCurrentPage;
                        MainCanvas.ni.send(251660288);
                    }
                } else {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    MainCanvas.mc.baseForm.setAboutForm(addForm);
                }
                break;
            case 251660672:
                i = execDataIn.readByte();
                message = "";
                switch (i) {
                    case 1:
                        message = "操作成功！";
                        break;
                    case 2:
                    case 3:
                    case 4:
                    case 6:
                    case 7:
                    default:
                        message = "操作失败！";
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
                }

                MainCanvas.mc.baseForm.getSubForm().setAboutForm((UIForm) null);
                MainCanvas.mc.baseForm.getSubForm().addAboutForm("optRst", message, (byte) 1, 140, 50);
                break;
            case 251660928:
                byte comfirmInvite = execDataIn.readByte();
                String msg = "";
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
                }

                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.mc.baseForm.addAboutForm("inviteRst", msg, (byte) 1, 170, 50);
                break;
            case 251661184:
            case 251661952:
            case 251687040:
                clanTotal = execDataIn.readInt();
                clanCurrentPage = execDataIn.readShort();
                byte comfirmNum = execDataIn.readByte();
                MainCanvas.mc.clanMemberName = null;
                MainCanvas.mc.clanMemberId = null;
                MainCanvas.mc.clanMemberLevel = null;
                MainCanvas.mc.clanMemberName = new String[comfirmNum];
                MainCanvas.mc.clanMemberId = new int[comfirmNum];
                MainCanvas.mc.clanMemberLevel = new byte[comfirmNum];

                for (i = 0; i < comfirmNum; ++i) {
                    MainCanvas.mc.clanMemberId[i] = execDataIn.readInt();
                    MainCanvas.mc.clanMemberName[i] = execDataIn.readUTF();
                    MainCanvas.mc.clanMemberLevel[i] = execDataIn.readByte();
                }

                clanTotalPage = clanTotal / 7;
                if (clanTotal % 7 != 0) {
                    ++clanTotalPage;
                }

                page = clanCurrentPage + "/" + clanTotalPage;
                lblPage = new UILabel(0, 195, 0, 0, page, 15718814, (byte) 1, (byte) 0);
                addForm = new UIForm(0, 0, MainCanvas.mc.baseForm.width, MainCanvas.mc.baseForm.height, "viewConfirm");
                addForm.setBackGround((byte) 9);
                frame = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte) 4);
                rimTitle = new UIRim(0, 12, 166, 20, (byte) 7);
                String titleStr = "";
                switch (ClanWar.m_getJoinMemList) {
                    case 0:
                        titleStr = "申请加入人员表";
                        break;
                    case 1:
                        titleStr = "申请退出人员表";
                        break;
                    case 2:
                        titleStr = "申请进入农场人员表";
                }

                lblTitle = new UILabel(0, rimTitle.positionY + 3, 176, 0, titleStr, 15718814, (byte) 1, (byte) 0);
                rimUp = new UIRim(0, 32, 166, 158, (byte) 0);
                lblOk = new UILabel(60, 7, 0, 0, "操作", 15718814, (byte) 0, (byte) 0);
                lblCancel = new UILabel(60, 7, 0, 0, "返回", 15718814, (byte) 0, (byte) 0);
                MainCanvas.mc.tables[0] = null;
                if (comfirmNum != 0) {
                    MainCanvas.mc.tables[0] = new UITable(0, 34, 161, 154, comfirmNum, 1, comfirmNum > 7 ? 7 : comfirmNum, (byte) 0, (byte) 3);
                    MainCanvas.mc.tables[0].setSingleWH(MainCanvas.mc.tables[0].singleWidth, (byte) 21, false);

                    for (i = 0; i < comfirmNum; ++i) {
                        if (ClanWar.m_getJoinMemList == 2) {
                            boolean isOk = MainCanvas.mc.clanMemberLevel[i] == 1;
                            color = isOk ? '\uff00' : 16777215;
                            MainCanvas.mc.tables[0].setItem(MainCanvas.mc.clanMemberName[i] + (isOk ? " 准" : ""), i, color);
                        } else {
                            MainCanvas.mc.tables[0].setItem(MainCanvas.mc.clanMemberName[i] + "  " + MainCanvas.mc.clanMemberLevel[i] + "级", i, 65280);
                        }
                    }
                }

                addForm.addComponent(frame);
                addForm.addComponentInCenter(rimTitle, (byte) 2);
                addForm.addComponentInCenter(lblTitle, (byte) 2);
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
            case 251661440:
            case 251662208:
            case 251691136:
                MainCanvas.mc.clanExit = execDataIn.readByte();
                switch (MainCanvas.mc.clanExit) {
                    case 1:
                        MainCanvas.mc.baseForm.getSubForm().addAboutForm("rstConfirm", "操作成功！", (byte) 1, 140, 50);
                        return;
                    case 2:
                        MainCanvas.mc.baseForm.getSubForm().addAboutForm("rstConfirm", "自己不能对自己进行操作！", (byte) 1, 140, 50);
                        return;
                    case 3:
                        MainCanvas.mc.baseForm.getSubForm().addAboutForm("rstConfirm", "对方已取消申请！", (byte) 1, 140, 50);
                        return;
                    case 4:
                        MainCanvas.mc.baseForm.getSubForm().addAboutForm("rstConfirm", "不能对自己相同等级的人进行操作！", (byte) 1, 140, 50);
                        return;
                    case 5:
                        MainCanvas.mc.baseForm.getSubForm().addAboutForm("rstConfirm", "不能对族长进行操作！", (byte) 1, 140, 50);
                        return;
                    case 6:
                        MainCanvas.mc.baseForm.getSubForm().addAboutForm("rstConfirm", "该玩家已经退出氏族！", (byte) 1, 140, 50);
                        return;
                    default:
                        MainCanvas.mc.baseForm.getSubForm().addAboutForm("rstConfirm", "操作失败！", (byte) 1, 140, 50);
                        return;
                }
            case 251661696:
                byte success = execDataIn.readByte();
                String description = execDataIn.readUTF();
                description = description.replace('$', '＄');
                if (success == 1) {
                    UIForm sub = new UIForm(0, 0, 100, 150, "clanintroduce");
                    sub.setBackGround((byte) 9);
                    rim = new UIRim(0, 0, 100, 150, (byte) 4);
                    UILabel name = new UILabel(0, 6, 0, 0, MainCanvas.mc.tables[0].getCurentItem(), 15718814, (byte) 1, (byte) 0);
                    UITextArea intro = new UITextArea(4, 20, 92, 128, description);
                    intro.setChangeColor(false);
                    sub.addComponent(rim);
                    sub.addComponentInCenter(name, (byte) 2);
                    sub.addComponent(intro);
                    MainCanvas.mc.baseForm.getSubForm().setAboutForm((UIForm) null);
                    MainCanvas.mc.baseForm.getSubForm().setAboutForm(sub);
                } else {
                    MainCanvas.mc.baseForm.getSubForm().setAboutForm((UIForm) null);
                    MainCanvas.mc.baseForm.getSubForm().addAboutForm("clanintroduce", "该氏族已不存在", (byte) 1, 130, 0);
                }
                break;
            case 251662464:
                MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
                len = execDataIn.readByte();
                if (len == -2) {
                    MainCanvas.setMessage(MainCanvas.mc.baseForm.getCurrentFocusForm(), "您需要先加入一个氏族才能查看！");
                } else if (len == 0) {
                    MainCanvas.setMessage(MainCanvas.mc.baseForm.getCurrentFocusForm(), "氏族现在没有徽章");
                } else if (len == -1) {
                    MainCanvas.setMessage(MainCanvas.mc.baseForm.getCurrentFocusForm(), "您不是族长");
                } else {
                    UIForm form = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "have_badge");
                    form.setBackGround((byte) 9);
                    frame = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte) 4);
                    rimTitle = new UIRim(0, 9, 166, 20, (byte) 7);
                    lblTitle = new UILabel(0, 12, 176, 0, "氏族现有徽章", 15718814, (byte) 1, (byte) 0);
                    UIRim rimDown = new UIRim(0, 29, 166, 160, (byte) 0);
                    form.addComponent(frame);
                    form.addComponentInCenter(rimTitle, (byte) 2);
                    form.addComponentInCenter(lblTitle, (byte) 2);
                    form.addComponentInCenter(rimDown, (byte) 2);
                    if (MainCanvas.isDistributionBadge) {
                        form.addLeftButton("操作");
                    }

                    form.addRightButton("返回");
                    UITable table = new UITable(0, 31, 161, 152, len, 1, len > 7 ? 7 : len, (byte) 0, (byte) 3);
                    table.setSingleWH(table.singleWidth, (byte) 22, false);
                    propsId = null;
                    propsName = null;
                    propsNumber = null;
                    propsId = new int[len];
                    propsName = new String[len];
                    propsNumber = new int[len];

                    for (i = 0; i < len; ++i) {
                        propsId[i] = execDataIn.readInt();
                        propsName[i] = execDataIn.readUTF();
                        propsNumber[i] = execDataIn.readInt();
                        table.setItem(propsName[i] + " 有" + propsNumber[i] + "个", i, 65280);
                    }

                    form.addComponentInCenter(table, (byte) 2);
                    MainCanvas.mc.tables[1] = null;
                    MainCanvas.mc.tables[1] = table;
                    MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
                    MainCanvas.mc.baseForm.getCurrentFocusForm().setAboutForm(form);
                    MainCanvas.mc.baseForm.setFocus(true);
                    MainCanvas.mc.setNPCSubState((byte) 4);
                }
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
                }

                MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
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
                    }

                    MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm.getCurrentFocusForm(), mgs);
                } else {
                    propsId = null;
                    propsName = null;
                    propsNumber = null;
                    propsId = new int[len];
                    propsName = new String[len];
                    propsNumber = new int[len];

                    for (i = 0; i < len; ++i) {
                        propsId[i] = execDataIn.readByte();
                        propsNumber[i] = execDataIn.readByte();
                        propsName[i] = execDataIn.readUTF();
                    }

                    initSilkForm(len);
                }
                break;
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
                }

                MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
                MainCanvas.setMessage(MainCanvas.mc.baseForm.getCurrentFocusForm(), mgs);
                break;
            case 1610613120:
                MainCanvas.topTitle = execDataIn.readUTF();
                MAXTOP = execDataIn.readByte();
                MainCanvas.mc.topPos = new String[MAXTOP];

                for (i = 0; i < MAXTOP; ++i) {
                    String tempstr = execDataIn.readUTF();
                    if (tempstr == null) {
                        MAXTOP = i + 1;
                        break;
                    }

                    MainCanvas.mc.topPos[i] = "  " + (i + 1) + "     " + tempstr;
                }

                MainCanvas.mc.setNPCSubState((byte) 22);
                MainCanvas.mc.releaseUI();
                break;
            case 1879048576:
                flag = execDataIn.readByte();
                message = null;
                String message2 = null;
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
                }

                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                if (MainCanvas.userID != null && flag == 8 && Cons.cmwap && !"".equals(MainCanvas.userID.trim())) {
                    MainCanvas.mc.baseForm.addAboutForm("mss2", message2, (byte) 2, 140, 0);
                } else if (flag == 9) {
                    MainCanvas.mc.baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, MainCanvas.screenW - 30, 0);
                    MainCanvas.ni.send(1879048704);
                } else {
                    MainCanvas.mc.baseForm.addAboutForm("message", message, (byte) 1, 140, 0);
                }
                break;
            case 1879048832:
                message = execDataIn.readUTF();
                MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                MainCanvas.mc.baseForm.addAboutForm("message", message, (byte) 1, 140, 0);
        }

    }

    public static byte[] compress(int commID) {
        String name, password;
        ByteArray execDataOut = new ByteArray();
        switch (commID) {
            case Cmd.C_NPC_RELIVE_MAP: {
                execDataOut.writeInt(MainCanvas.mc.NPCIndex);
                break;
            }
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
            case Cmd.C_NPC_LIST: {
                execDataOut.writeInt(MainCanvas.mc.NPCIndex);
                break;
            }
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
            case Cmd.C_DIALOG_LIST:
                execDataOut.writeInt(MainCanvas.mc.NPCIndex);
                break;
            case Cmd.C_DIALOG_CONTENT:
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
