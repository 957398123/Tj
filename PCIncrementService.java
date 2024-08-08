//
// Source code recreated from MainEntry .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import javax.microedition.io.Connector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

public class PCIncrementService {
    boolean isChangeServrCharge = false;
    static byte payType = -1;
    static int changeServrNeedMoney = 0;
    public static String sendMd5 = null;
    public static byte sendMark = 0;
    public static byte isend = -1;
    private static String sendnumber;
    private static String sendtext;
    public final int mammothBeijingRate = 20;
    public static String passportURL = "";
    public static final int MAX_NUMBER = 9;
    public final int MAX_MONEY = 50;
    public final String[] EXPLAIN = new String[]{"人民币充值，您一次最多可以充入50元。", "查询帐户余额", "查询消费明细", "您可以在这获得离线经验"};
    static final String SEND_INFO = "命令发送中";
    static final String RECEIVED_INFO = "信息获取中";
    static int sendWaitTime = 30;
    byte connectMark = 0;
    String connectLoghead = "";
    byte[] connectLogbody = null;
    String baoyueIP = "";
    String baoyueUrl = "";
    static byte bodyMark = 0;
    static int max_BodySend = 0;
    static final int MAX_SEND_LENGTH = 256;
    static String logAddress = "";
    byte conBaoyue = 0;
    private boolean[] isSelect;
    short baoyueItem = 0;
    byte otherBaoyueIndex = 0;
    static byte dingzhiLen = 0;
    static boolean is15Send = false;
    public static final byte SPECIAL_MENU = -1;
    public static final byte G_BUY = 0;
    public static final byte MAMMOTH_BUY = 1;
    public static final byte First_LARGE_CHARGE = 2;
    public static final byte SMS_CHARGE = 3;
    public static final byte HISTORY = 4;
    public static final byte BUY = 15;
    public static final byte MAIN_CHARGE = 16;
    public static final byte STATE_PAY_CHANGE_SERVER = 17;
    public static final byte STATE_MONTHLY = 18;
    public static final byte LARGE_CHARGE = 20;
    public static final byte CHARGE_SELECT = 21;
    public static boolean isCharge = false;
    public static byte chargeWhere = 0;
    private static String toolType;
    private static byte number;
    public static UIGrid specialShop;
    private static byte state = -1;
    private static int needMoney;
    private String historyStr;
    private static byte historyIndex;
    public final int mammothRate = 100;
    private static PCIncrementService instance;
    String[][] chargeData = (String[][])null;
    UITable chargeTable = null;
    int chargeSelectIndex = 0;
    String CardNum = "";
    String CardPass = "";
    String CardValue = "";
    String chargePassport = "";
    String[] shenzhouData = null;
    UITable SzTable = null;
    boolean isSZEnter = false;
    static final int SMS_RATE = 2;
    int is1conFalse = 0;
    private UITextArea explain;
    public static final byte CHARGE_INPUT = 0;
    public static final byte CHARGE_WAIT = 1;
    public static final byte CHARGE_RESULT = 2;
    public static int sum;
    public static byte chargeState;
    public static String phoneNumber = "106633552";
    static String address;
    public static int count;
    public static int processCount;
    static MessageConnection conn;
    static TextMessage msg;
    public static int mammothNumbers = 0;
    String[] chargeMenu = null;
    String chargeEXP = "";
    boolean isShowTwoC = true;
    String chargeTwoTipStr = "";
    public final byte SEND_WAIT = 1;
    public final byte SEND_RESULT = 2;
    public final byte SEND_GET = 3;
    public final byte SEND_FAIL = 4;
    public final byte SEND_SHOW = 0;
    public byte sendState = 0;
    boolean isSucceed = false;
    public static boolean hasAgree = false;
    public static boolean isChargeJar = false;
    public static final byte SEND_TOTAL = 3;
    public static byte sendCount = 0;
    public static byte agreeCount = 0;
    final String SEND_TEXT = "您已成功安装空中网空中猛犸出品《天劫online》，付费后可以正常游戏，一次性付费6元（通过3条短信发送）终身免费，还有神秘礼品赠送，感谢您的使用，祝您游戏愉快！";
    Image imgBg = null;

    public PCIncrementService() {
    }

    public static PCIncrementService getInstance() {
        if (instance == null) {
            instance = new PCIncrementService();
        }

        return instance;
    }

    public static void setState(byte s) {
        if (s == -1) {
            getInstance().isSZEnter = false;
        }

        state = s;
    }

    public void draw(Graphics g) {
        switch (state) {
            case -1:
                this.drawSpecialMenu(g);
            case 0:
            case 1:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 19:
            default:
                break;
            case 2:
                this.drawShenZhouCharge(g);
                break;
            case 3:
                this.drawCharge(g);
                break;
            case 4:
                this.drawHistory(g);
                break;
            case 15:
                this.drawBuy(g);
                break;
            case 16:
                this.drawMainCharge(g);
                break;
            case 17:
                this.initPayChangeServer(g);
                break;
            case 18:
                this.drawMonthly(g);
                break;
            case 20:
                this.drawLargeCharge(g);
                break;
            case 21:
                this.drawChargeSelect(g);
        }

    }

    public void userEvent() {
        switch (state) {
            case -1:
                this.actionToSpecialMenu();
            case 0:
            case 1:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 19:
            default:
                break;
            case 2:
                this.actionToShenZhouCharge();
                break;
            case 3:
                this.actionToCharge();
                break;
            case 4:
                this.actionToHistory();
                break;
            case 15:
                this.actionToBuy();
                break;
            case 16:
                this.actionMainCharge();
                break;
            case 17:
                this.actionToPayChangeServer();
                break;
            case 18:
                this.actionToMonthly();
                break;
            case 20:
                this.actionToLargeCharge();
                break;
            case 21:
                this.actionToChargeSelect();
        }

    }

    public void drawSpecialMenu(Graphics g) {
        if (MainCanvas.mc.baseForm == null) {
            MainCanvas.mc.baseForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "");
            MainCanvas.mc.baseForm.setStyle((byte)0);
            MainCanvas.mc.rims[0] = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte)4);
            MainCanvas.mc.rims[1] = new UIRim(5, 5, 164, 25, (byte)2);
            MainCanvas.mc.labels[0] = new UILabel(60, 12, 112, 0, "商城", 16316576, (byte)1, (byte)0);
            UILabel label1 = new UILabel(0, 0, 0, 0, "选择", 15718815, (byte)0, (byte)0);
            UILabel label2 = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte)0, (byte)0);
            String[] menu;
            if (Cons.isStartNote) {
                menu = new String[]{"话费快速购买", "猛犸币购买", "猛犸币大额充值", "猛犸币短信充值", "消费明细"};
                MainCanvas.mc.menus[0] = new UIMenu(5, 32, 164, 120, (String)null, menu);
            } else {
                menu = new String[]{"话费快速购买", "猛犸币购买", "猛犸币大额充值", "消费明细"};
                MainCanvas.mc.menus[0] = new UIMenu(5, 32, 164, 120, (String)null, menu);
            }

            MainCanvas.mc.menus[0].setRimStyle((byte)0);
            MainCanvas.mc.menus[0].setFlushType((byte)1);
            MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.menus[0]);

            for(byte i = 0; i < 2; ++i) {
                MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.rims[i]);
            }

            MainCanvas.mc.labels[1] = new UILabel(5, 154, 200, 0, "猛犸币:" + Player.mommathMoney, 16316576, (byte)1, (byte)0);
            if (Cons.isCmobile) {
                MainCanvas.mc.textArea[0] = new UITextArea(5, 100, 164, 88, this.EXPLAIN[MainCanvas.mc.menus[0].getCurrentPointer()]);
                MainCanvas.mc.textArea[0].setColor(15849885);
                MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.textArea[0]);
            }

            MainCanvas.mc.baseForm.addComponentInCenter(MainCanvas.mc.labels[0], (byte)2);
            MainCanvas.mc.baseForm.addComponentInCenter(MainCanvas.mc.labels[1], (byte)2);
            MainCanvas.mc.baseForm.addComponentInCenter(label1, (byte)5);
            MainCanvas.mc.baseForm.addComponentInCenter(label2, (byte)6);
            MainCanvas.mc.baseForm.setMessage(Cons.ROLL_MASSAGE[12], false);
            MainCanvas.mc.baseForm.setFocus(true);
        }

        MainCanvas.mc.baseForm.draw(g);
    }

    public void drawCharge(Graphics g) {
        if (MainCanvas.mc.baseForm == null) {
            MainCanvas.mc.baseForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "");
            MainCanvas.mc.baseForm.setStyle((byte)0);
            MainCanvas.mc.rims[0] = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte)4);
            MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.rims[0]);
            MainCanvas.mc.rims[1] = new UIRim(5, 5, 164, 25, (byte)2);
            MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.rims[1]);
            UILabel contentLabel = new UILabel(0, 13, MainCanvas.screenW, 0, "充值", 15653280, (byte)1, (byte)0);
            MainCanvas.mc.baseForm.addComponentInCenter(contentLabel, (byte)2);
            MainCanvas.mc.menus[0] = new UIMenu(5, 32, 164, 100, (String)null, this.chargeMenu);
            MainCanvas.mc.menus[0].setRimStyle((byte)0);
            MainCanvas.mc.menus[0].setFlushType((byte)1);
            MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.menus[0]);
            MainCanvas.mc.textArea[0] = new UITextArea(5, 134, 164, 55, this.chargeEXP);
            MainCanvas.mc.baseForm.addComponentInCenter(MainCanvas.mc.textArea[0], (byte)2);
            UILabel doLabe = new UILabel(80, 8, 0, 0, "发送", 15653280, (byte)0, (byte)0);
            MainCanvas.mc.baseForm.addComponentInCenter(doLabe, (byte)5);
            UILabel backLabel = new UILabel(80, 8, 0, 0, "返回", 15653280, (byte)0, (byte)0);
            MainCanvas.mc.baseForm.addComponentInCenter(backLabel, (byte)6);
            MainCanvas.mc.baseForm.setFocus(true);
        }

        MainCanvas.mc.baseForm.draw(g);
        isCharge = true;
    }

    public void drawBuy(Graphics g) {
        if (MainCanvas.mc.baseForm == null) {
            MainCanvas.mc.baseForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "");
            MainCanvas.mc.baseForm.setStyle((byte)0);
            MainCanvas.mc.rims[0] = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte)4);
            MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.rims[0]);
            MainCanvas.mc.rims[1] = new UIRim(7, 9, 168, 23, (byte)2);
            MainCanvas.mc.baseForm.addComponentInCenter(MainCanvas.mc.rims[1], (byte)2);
            MainCanvas.mc.rims[2] = new UIRim(7, 108, 168, 70, (byte)0);
            MainCanvas.mc.baseForm.addComponentInCenter(MainCanvas.mc.rims[2], (byte)2);
            MainCanvas.mc.baseForm.addComponentInCenter(specialShop, (byte)2);
            UILabel[] label = new UILabel[6];
            label[0] = new UILabel(0, 13, MainCanvas.screenW, 0, toolType, 15653280, (byte)1, (byte)0);
            MainCanvas.mc.baseForm.addComponent(label[0]);
            label[1] = new UILabel(12, 115, 0, 0, "名称：", 15653280, (byte)0, (byte)0);
            MainCanvas.mc.baseForm.addComponent(label[1]);
            MainCanvas.mc.labels[0] = new UILabel(label[1].positionX + label[1].width - 8, 115, 120, 0, specialShop.getCurrentName(), Cons.STUFF_NAME_COLOR[specialShop.getCurrentNameLevel()], (byte)0, (byte)0);
            MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.labels[0]);
            label[2] = new UILabel(12, 135, 0, 0, "物品价格", 15653280, (byte)0, (byte)0);
            MainCanvas.mc.baseForm.addComponent(label[2]);
            label[3] = new UILabel(12, 155, 0, 0, Cons.isCmobile ? "点数" : "猛犸币", 15653280, (byte)0, (byte)0);
            MainCanvas.mc.baseForm.addComponent(label[3]);
            label[4] = new UILabel(80, 8, 0, 0, "操作", 15653280, (byte)0, (byte)0);
            MainCanvas.mc.baseForm.addComponentInCenter(label[4], (byte)5);
            label[5] = new UILabel(80, 8, 0, 0, "返回", 15653280, (byte)0, (byte)0);
            MainCanvas.mc.baseForm.addComponentInCenter(label[5], (byte)6);
            MainCanvas.mc.texts[0] = new UIText(83, 136, 80, 0, 9, (byte)3, MainCanvas.mc.shopStuffPrice[specialShop.getCurrentPointer()] + "");
            MainCanvas.mc.texts[0].setMoneyImageIndex((byte)1);
            MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.texts[0]);
            MainCanvas.mc.texts[1] = new UIText(83, 156, 80, 0, 9, (byte)3, Player.mommathMoney + "");
            MainCanvas.mc.texts[1].setMoneyImageIndex((byte)1);
            MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.texts[1]);
            MainCanvas.mc.baseForm.setFocus(true);
        }

        MainCanvas.mc.baseForm.draw(g);
    }

    public void drawCheck_out(Graphics g) {
        if (MainCanvas.mc.baseForm == null) {
            MainCanvas.mc.baseForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "");
            MainCanvas.mc.baseForm.setStyle((byte)0);
            MainCanvas.mc.rims[0] = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte)4);
            MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.rims[0]);
            UILabel contentLabel = new UILabel(0, 13, MainCanvas.screenW, 0, "您的余额为" + Player.mommathMoney + (Cons.isCmobile ? "个点数。" : "猛犸币。"), 15653280, (byte)1, (byte)0);
            MainCanvas.mc.baseForm.addComponentInCenter(contentLabel, (byte)2);
            UILabel backLabel = new UILabel(80, 8, 0, 0, "返回", 15653280, (byte)0, (byte)0);
            MainCanvas.mc.baseForm.addComponentInCenter(backLabel, (byte)6);
            MainCanvas.mc.baseForm.setFocus(true);
        }

        MainCanvas.mc.baseForm.draw(g);
    }

    public void drawHistory(Graphics g) {
        if (MainCanvas.mc.baseForm == null) {
            MainCanvas.mc.baseForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "");
            MainCanvas.mc.baseForm.setStyle((byte)0);
            MainCanvas.mc.rims[0] = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte)4);
            MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.rims[0]);
            MainCanvas.mc.labels[0] = new UILabel(60, 6, 112, 0, "消费明细", 15653280, (byte)1, (byte)0);
            MainCanvas.mc.baseForm.addComponentInCenter(MainCanvas.mc.labels[0], (byte)2);
            MainCanvas.mc.textArea[0] = new UITextArea(0, 30, 160, 158, this.historyStr);
            MainCanvas.mc.textArea[0].setColor(15718814);
            MainCanvas.mc.baseForm.addComponentInCenter(MainCanvas.mc.textArea[0], (byte)2);
            UILabel backLabel = new UILabel(80, 8, 0, 0, "返回", 15653280, (byte)0, (byte)0);
            MainCanvas.mc.baseForm.addComponentInCenter(backLabel, (byte)6);
            MainCanvas.mc.baseForm.setFocus(true);
        }

        MainCanvas.mc.baseForm.draw(g);
    }

    private void drawMainCharge(Graphics g) {
        if (MainCanvas.mc.baseForm == null) {
            MainCanvas.mc.baseForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "");
            MainCanvas.mc.baseForm.setStyle((byte)0);
            MainCanvas.mc.rims[0] = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte)4);
            MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.rims[0]);
            UIRim rim1 = new UIRim(5, 10, 160, 20, (byte)0);
            UIRim rim2 = new UIRim(5, 31, 160, 70, (byte)0);
            UIRim rim3 = new UIRim(5, 102, 160, 80, (byte)0);
            MainCanvas.mc.baseForm.addComponentInCenter(rim1, (byte)2);
            MainCanvas.mc.baseForm.addComponentInCenter(rim2, (byte)2);
            MainCanvas.mc.baseForm.addComponentInCenter(rim3, (byte)2);
            UILabel contentLabel = new UILabel(0, 13, MainCanvas.screenW, 0, "充值", 15653280, (byte)1, (byte)0);
            MainCanvas.mc.baseForm.addComponentInCenter(contentLabel, (byte)2);
            UILabel sign = new UILabel(7, 45, 160, 0, "请输入您要充入的金额：", 15653280, (byte)1, (byte)0);
            MainCanvas.mc.baseForm.addComponent(sign);
            UILabel detail = new UILabel(10, 103, 160, 0, "您充入的每1元人民币可以换取" + String.valueOf(100) + "点数。一次最多可以充入" + 50 + "元。", 15653280, (byte)0, (byte)0);
            MainCanvas.mc.baseForm.addComponent(detail);
            MainCanvas.mc.texts[0] = new UIText(10, 70, 92, 0, 6, (byte)2, "0");
            MainCanvas.mc.texts[0].setMaxNumber(Cons.isCmobile ? 50L : 9L);
            MainCanvas.mc.baseForm.addComponentInCenter(MainCanvas.mc.texts[0], (byte)2);
            UILabel doLabe = new UILabel(80, 8, 0, 0, "确定", 15653280, (byte)0, (byte)0);
            MainCanvas.mc.baseForm.addComponentInCenter(doLabe, (byte)5);
            UILabel backLabel = new UILabel(80, 8, 0, 0, "返回", 15653280, (byte)0, (byte)0);
            MainCanvas.mc.baseForm.addComponentInCenter(backLabel, (byte)6);
            MainCanvas.mc.baseForm.setFocus(true);
        }

        MainCanvas.mc.baseForm.draw(g);
    }

    public void actionToSpecialMenu() {
        if (MainCanvas.mc.baseForm != null) {
            UIComponent cmd = MainCanvas.mc.baseForm.getCommand();
            if (!MainCanvas.isKeyPress(17) && !MainCanvas.isKeyPress(14)) {
                if (MainCanvas.isKeyPress(18)) {
                    if (MainCanvas.mc.baseForm.getCurrentFocusForm() == MainCanvas.mc.baseForm) {
                        if (MainCanvas.mc.menus[0].getSubMenu() != null) {
                            MainCanvas.mc.menus[0].setSubMenu((UIMenu)null);
                        } else {
                            MainCanvas.mc.setRightMenuSubState(-1);
                            MainCanvas.mc.releaseUI();
                        }
                    }
                } else if (MainCanvas.mc.actionInForm(cmd) && Cons.isCmobile) {
                    MainCanvas.mc.textArea[0].setString(this.EXPLAIN[MainCanvas.mc.menus[0].getCurrentPointer()]);
                }
            } else if (MainCanvas.mc.baseForm.getCurrentFocusForm() == MainCanvas.mc.baseForm) {
                int s = MainCanvas.mc.menus[0].getCurrentPointer();
                if (s >= 3 && !Cons.isStartNote) {
                    ++s;
                }

                switch (s) {
                    case 0:
                    case 1:
                        chargeWhere = 2;
                        this.addWaitInterface();
                        sendMommoth();
                        break;
                    case 2:
                        MainCanvas.ni.send(1342210048);
                        this.addWaitInterface();
                        break;
                    case 3:
                        MainCanvas.ni.send(1342222336);
                        this.addWaitInterface();
                        break;
                    case 4:
                        if (MainCanvas.mc.menus[0].getSubMenu() == null) {
                            MainCanvas.mc.menus[0].setSubMenu(new UIMenu(0, 0, 80, 0, (String)null, new String[]{"上月明细", "本月明细"}));
                        } else {
                            historyIndex = MainCanvas.mc.menus[0].getSubMenu().getCurrentPointer();
                            MainCanvas.ni.send(1342178816);
                            this.addWaitInterface();
                        }
                }
            } else if ("msg".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                MainCanvas.mc.baseForm.setAboutForm((UIForm)null);
            }

        }
    }

    public void actionToCharge() {
        if (MainCanvas.mc.baseForm != null) {
            UIComponent cmd = MainCanvas.mc.baseForm.getCommand();
            switch (chargeState) {
                case 0:
                    if (!MainCanvas.isKeyPress(17) && !MainCanvas.isKeyPress(14)) {
                        if (MainCanvas.isKeyPress(18)) {
                            if (MainCanvas.mc.baseForm.getCurrentFocusForm() == MainCanvas.mc.baseForm) {
                                state = -1;
                                MainCanvas.mc.releaseUI();
                                isCharge = false;
                            } else if ("sure".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                                MainCanvas.mc.baseForm.setAboutForm((UIForm)null);
                            }
                        } else if (MainCanvas.mc.actionInForm(cmd)) {
                            if (MainCanvas.isKeyPress(13)) {
                                if (MainCanvas.mc.menus[0].getCurrentPointer() == 0) {
                                    MainCanvas.mc.menus[0].setCurrentpointer((byte)(MainCanvas.mc.menus[0].strs.length - 1));
                                    MainCanvas.mc.baseForm.setComponentFocus(MainCanvas.mc.textArea[0]);
                                    MainCanvas.mc.textArea[0].setColor(15718814);
                                }
                            } else if (MainCanvas.isKeyPress(11) && MainCanvas.mc.textArea[0].isTop()) {
                                MainCanvas.mc.textArea[0].setColor(8349245);
                                MainCanvas.mc.baseForm.setComponentFocus(MainCanvas.mc.menus[0]);
                            }
                        }
                    } else if (MainCanvas.mc.baseForm.getCurrentFocusForm() == MainCanvas.mc.baseForm) {
                        String tmpNum = MainCanvas.mc.menus[0].getCurrentItem().substring(0, MainCanvas.mc.menus[0].getCurrentItem().indexOf("元"));

                        try {
                            sum = Integer.parseInt(tmpNum) / 2;
                        } catch (Exception var4) {
                        }

                        if (this.isShowTwoC) {
                            MainCanvas.mc.baseForm.addAboutForm("sure", this.chargeTwoTipStr, (byte)2, 120, 0);
                        } else {
                            MainCanvas.mc.baseForm.addAboutForm("waiting", "请稍候…", (byte)0, MainCanvas.screenW - 30, 0);
                            MainCanvas.ni.send(1342181120);
                        }
                    } else if ("sure".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                        MainCanvas.mc.baseForm.addAboutForm("waiting", "请稍候…", (byte)0, MainCanvas.screenW - 30, 0);
                        MainCanvas.ni.send(1342181120);
                    }
                case 1:
                default:
                    break;
                case 2:
                    if (MainCanvas.isKeyPress(17) || MainCanvas.isKeyPress(14)) {
                        MainCanvas.mc.baseForm.setAboutForm((UIForm)null);
                        chargeState = 0;
                    }
            }

        }
    }

    void charge(byte tempState) {
        chargeState = 0;
        state = tempState;
        MainCanvas.mc.releaseUI();
    }

    void sendChargeStatc(final int tempInt) {
        if (isend == 0) {
            MainCanvas.ni.send(1342178560);
            this.addWaitInterface();
        } else {
            chargeState = 1;
            init();
            Thread send = new Thread() {
                public void run() {
                    PCIncrementService.sends(tempInt, false);
                }
            };
            send.start();
        }

    }

    public void actionToBuy() {
        if (MainCanvas.mc.baseForm != null) {
            UIComponent cmd = MainCanvas.mc.baseForm.getCommand();
            if (!MainCanvas.isKeyPress(17) && !MainCanvas.isKeyPress(14)) {
                if (MainCanvas.isKeyPress(18)) {
                    if (MainCanvas.mc.baseForm.getCurrentFocusForm() == MainCanvas.mc.baseForm) {
                        if (MainCanvas.mc.getRightMenuSubState() != 8) {
                            MainCanvas.mc.baseForm.addAboutForm("waiting", "请稍候…", (byte)0, MainCanvas.screenW - 30, 0);
                            chargeWhere = 2;
                            sendMommoth();
                        } else {
                            state = -1;
                            MainCanvas.mc.releaseUI();
                        }
                    } else if ("menu".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                        MainCanvas.mc.baseForm.setAboutForm((UIForm)null);
                    } else if ("detail".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                        MainCanvas.mc.baseForm.setAboutForm((UIForm)null);
                    } else if ("input".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                        MainCanvas.mc.baseForm.setAboutForm((UIForm)null);
                    } else if ("buy".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                        MainCanvas.mc.baseForm.setAboutForm((UIForm)null);
                    } else if ("conf1".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                        MainCanvas.mc.baseForm.setAboutForm((UIForm)null);
                    } else if ("bybuy".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                        MainCanvas.mc.baseForm.setAboutForm((UIForm)null);
                    } else if ("toCharge".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                        MainCanvas.mc.baseForm.setAboutForm((UIForm)null);
                    }
                } else if (MainCanvas.mc.actionInForm(cmd)) {
                    MainCanvas.mc.labels[0].setStr(specialShop.getCurrentName());
                    MainCanvas.mc.labels[0].setColor(Cons.STUFF_NAME_COLOR[specialShop.getCurrentNameLevel()]);
                    MainCanvas.mc.texts[0].setLabel(String.valueOf(MainCanvas.mc.shopStuffPrice[specialShop.getCurrentPointer()]));
                }
            } else {
                if (specialShop.getCurrentId() == 0) {
                    return;
                }

                if (MainCanvas.mc.baseForm.getCurrentFocusForm() == MainCanvas.mc.baseForm) {
                    String[] menu = new String[]{specialShop.getCurrentLittleType() != 368 && specialShop.getCurrentLittleType() != 369 ? "购买" : "租用", "查看"};
                    UIForm menuForm = new UIForm(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, "menu");
                    menuForm.setBackGround((byte)9);
                    MainCanvas.mc.menus[0] = new UIMenu(30, 0, 60, 0, (String)null, menu);
                    MainCanvas.mc.menus[0].positionY = MainCanvas.screenH - MainCanvas.mc.menus[0].height - 100;
                    menuForm.addComponent(MainCanvas.mc.menus[0]);
                    MainCanvas.mc.baseForm.setAboutForm(menuForm);
                } else if ("menu".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                    switch (MainCanvas.mc.menus[0].getCurrentPointer()) {
                        case 0:
                            needMoney = MainCanvas.mc.shopStuffPrice[specialShop.getCurrentPointer()];
                            if (specialShop.getCurrentLittleType() == 368 || specialShop.getCurrentLittleType() == 369) {
                                if (MainCanvas.userID != null && Cons.cmwap && !"".equals(MainCanvas.userID.trim())) {
                                    MainCanvas.mc.baseForm.addAboutForm("bybuy", "您将租用" + specialShop.getCurrentName() + MainCanvas.mc.shopStuffPrice[specialShop.getCurrentPointer()] * 2 / 20 + "元/月，是否确认租用", (byte)2, 160, 0);
                                } else {
                                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "您当前连接设置错误，请使用cmwap连接方式！");
                                }

                                return;
                            }

                            if (isend == 0) {
                                MainCanvas.ni.send(1342179584);
                                MainCanvas.mc.baseForm.addAboutForm("waiting", "请稍候…", (byte)0, MainCanvas.screenW - 30, 0);
                            } else {
                                if (Player.mommathMoney < needMoney) {
                                    if (MainCanvas.userID != null && Cons.cmwap && !"".equals(MainCanvas.userID.trim())) {
                                        MainCanvas.ni.send(1342180608);
                                        this.addWaitInterface();
                                    } else {
                                        MainCanvas.mc.baseForm.addAboutForm("toCharge", "余额不足，是否充值？", (byte)2, 160, 0);
                                    }

                                    return;
                                }

                                if (!specialShop.isCurStuffCanRepeat()) {
                                    number = 1;
                                    MainCanvas.ni.send(1342178048);
                                    this.addWaitInterface();
                                } else {
                                    MainCanvas.mc.texts[9] = new UIText(0, 0, 92, 0, 6, (byte)2, "0");
                                    MainCanvas.mc.texts[9].setMaxNumber(20L);
                                    MainCanvas.mc.baseForm.addInputForm("input", "请输入购买数量：", MainCanvas.mc.texts[9], 100);
                                    number = (byte)MainCanvas.mc.texts[9].getNumber();
                                }
                            }
                            break;
                        case 1:
                            MainCanvas.mc.selectedId = specialShop.getCurrentId();
                            MainCanvas.ni.send(67111936);
                            this.addWaitInterface();
                    }
                } else if ("input".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                    number = (byte)MainCanvas.mc.texts[9].getNumber();
                    if (number == 0) {
                        MainCanvas.mc.baseForm.setAboutForm((UIForm)null);
                    } else {
                        if (!Cons.isCmobile) {
                            needMoney = MainCanvas.mc.shopStuffPrice[specialShop.getCurrentPointer()];
                            needMoney *= number;
                            if (Player.mommathMoney < needMoney) {
                                MainCanvas.mc.baseForm.addAboutForm("toCharge", "余额不足，是否充值？", (byte)2, 160, 0);
                                return;
                            }
                        }

                        MainCanvas.ni.send(1342178048);
                        this.addWaitInterface();
                    }
                } else if ("msg".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm)null);
                } else if ("buy".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm)null);
                    number = 1;
                    sum = 1;
                    MainCanvas.ni.send(1342178048);
                    this.addWaitInterface();
                } else if ("conf1".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                    number = 1;
                    MainCanvas.ni.send(1342178048);
                    this.addWaitInterface();
                } else if ("bybuy".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                    this.setBaoyue(0, specialShop.getCurrentId());
                    MainCanvas.ni.send(16781312);
                    MainCanvas.ni.send(Cmd.C_VALUEADDED_RENT);
                    MainCanvas.mc.baseForm.getCurrentFocusForm().setAboutForm(UIForm.makeAboutForm("waitInfo", "命令发送中，预计需要等待" + sendWaitTime + "秒", "", "", MainCanvas.screenW - 60));
                } else if ("toCharge".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                    MainCanvas.mc.setRightMenuSubState(0);
                    MainCanvas.ni.send(1342210048);
                    this.addWaitInterface();
                }
            }

        }
    }

    public void actionToCheck_out() {
        if (MainCanvas.mc.baseForm != null) {
            if (MainCanvas.isKeyPress(18)) {
                setState((byte)-1);
                MainCanvas.mc.releaseUI();
            }

        }
    }

    public void actionToHistory() {
        if (MainCanvas.mc.baseForm != null) {
            UIComponent cmd = MainCanvas.mc.baseForm.getCommand();
            if (MainCanvas.isKeyPress(18)) {
                setState((byte)-1);
                MainCanvas.mc.releaseUI();
            } else if (MainCanvas.mc.actionInForm(cmd)) {
            }

        }
    }

    public void actionMainCharge() {
        if (MainCanvas.mc.baseForm != null) {
            UIComponent cmd = MainCanvas.mc.baseForm.getCommand();
            if (!MainCanvas.isKeyPress(17) && !MainCanvas.isKeyPress(14)) {
                if (MainCanvas.isKeyPress(18)) {
                    if (MainCanvas.mc.baseForm.getCurrentFocusForm() == MainCanvas.mc.baseForm) {
                        if (MainCanvas.mc.getState() == 5) {
                            MainCanvas.mc.setGameState((byte)1);
                            MainCanvas.mc.releaseUI();
                        } else {
                            MainCanvas.mc.setState((byte)4);
                            MainCanvas.mc.releaseUI();
                        }
                    } else if ("sure".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                        MainCanvas.mc.baseForm.setAboutForm((UIForm)null);
                    }
                } else if (MainCanvas.mc.actionInForm(cmd)) {
                }
            } else if (MainCanvas.mc.baseForm.getCurrentFocusForm() == MainCanvas.mc.baseForm) {
                sum = MainCanvas.mc.texts[0].getNumber();
                if (sum == 0) {
                    MainCanvas.mc.baseForm.addAboutForm("msg", "请输入要充入的金额", (byte)1, 120, 0);
                } else {
                    MainCanvas.mc.baseForm.addAboutForm("sure", "您确实要消费" + sum + "元购买" + 100 * sum + "点数吗？", (byte)2, 120, 0);
                }
            } else if ("msg".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                MainCanvas.mc.baseForm.setAboutForm((UIForm)null);
            } else if ("sure".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                MainCanvas.mc.baseForm.setAboutForm((UIForm)null);
                this.chargeLogin();
                this.addWaitInterface();
            }

        }
    }

    public void chargeLogin() {
        MainCanvas.mc.httpConn = null;
        MainCanvas.mc.httpConn = new HttpConn("gmp.i139.cn", "/bizcontrol/LoginOnlineGame?sender=202&cpId=C00002&cpServiceId=120120438000&channelId=1000&fid=" + MainCanvas.fidCode, Cons.cmwap, 3, 1);
        MainCanvas.mc.httpConn.start();
    }

    private void actionToPayChangeServer() {
        if (!MainCanvas.isKeyPress(17) && !MainCanvas.isKeyPress(14)) {
            if (MainCanvas.isKeyPress(18) && MainCanvas.mc.payChangeSerser > 2 && !"waiting".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName()) && !"msg".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                MainCanvas.ni.closeConn();
                MainCanvas.mc.exitGame((byte)1);
            }
        } else if ("msg".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
            if (payType == 0) {
                MainCanvas.mc.releaseUI();
                MainCanvas.mc.setGameState((byte)0);
                MainCanvas.mc.stratGameForm();
            } else {
                MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm)null);
            }
        } else {
            switch (MainCanvas.mc.payChangeSerser) {
                case 3:
                    payType = 1;
                    MainCanvas.ni.send(1342193664);
                    MainCanvas.startWait(MainCanvas.mc.baseForm);
                    break;
                case 4:
                    MainCanvas.mc.payChangeSerser = 6;
                    setState((byte)17);
                    MainCanvas.mc.releaseUI();
                    break;
                case 5:
                    payType = 0;
                    MainCanvas.ni.send(1342193664);
                    MainCanvas.startWait(MainCanvas.mc.baseForm);
            }
        }

    }

    public void initPayChangeServer(Graphics g) {
        if (MainCanvas.mc.baseForm == null) {
            UIForm payForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "payServer");
            payForm.setBackGround((byte)9);
            UIRim frame = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte)4);
            UIRim rimTitle = new UIRim(0, 9, 166, 20, (byte)7);
            UILabel lblTitle = new UILabel(0, 12, 176, 0, "付费转服", 15718814, (byte)1, (byte)0);
            UIRim rimDown = new UIRim(0, 29, 166, 160, (byte)0);
            payForm.addComponent(frame);
            payForm.addComponentInCenter(rimTitle, (byte)2);
            payForm.addComponentInCenter(lblTitle, (byte)2);
            payForm.addComponentInCenter(rimDown, (byte)2);
            String msg = "";
            String leftButton = "";
            String rightButton = "退出游戏";
            switch (MainCanvas.mc.payChangeSerser) {
                case 1:
                    msg = "正在请求您账户余额，请稍候…";
                    rightButton = "";
                    MainCanvas.startWait(payForm);
                    break;
                case 2:
                    if (Player.mommathMoney < changeServrNeedMoney) {
                        if (MainCanvas.userID != null && !"".equals(MainCanvas.userID.trim())) {
                            msg = "付费转服需要支付" + changeServrNeedMoney + "猛犸币才能正常游戏。" + "您账户余额不足，系统将自动为您补足金额。您要支付吗？";
                            leftButton = "付费";
                            MainCanvas.mc.payChangeSerser = 3;
                            break;
                        }

                        int yue = changeServrNeedMoney - Player.mommathMoney;
                        sum = yue % 20 == 0 ? yue / 20 : yue / 20 + 1;
                        msg = "付费转服需要支付" + changeServrNeedMoney + "猛犸币才能正常游戏。" + "您账户余额不足，您还差" + yue + "猛犸币，需要发送" + sum + "条短信。如果您上次登录已经充值，可能充值结果仍未返回，" + "请您退出游戏耐心等待。";
                        leftButton = "充值";
                        MainCanvas.mc.payChangeSerser = 4;
                        break;
                    }

                    msg = "付费转服需要支付" + changeServrNeedMoney + "猛犸币才能正常游戏。" + "您要支付吗？";
                    leftButton = "付费";
                    MainCanvas.mc.payChangeSerser = 5;
                    break;
                case 6:
                    msg = "充值有延时，请退出游戏一段时间再登录游戏。如果再次登录游戏仍要求您充值，可能充值结果仍未返回，请您退出游戏耐心等待。";
                    MainCanvas.startWait(payForm);
                    this.isChangeServrCharge = true;
                    MainCanvas.ni.send(1342181120);
            }

            this.explain = new UITextArea(5, 31, 162, 155, msg);
            payForm.addComponentInCenter(this.explain, (byte)2);
            payForm.addLeftButton(leftButton);
            payForm.addRightButton(rightButton);
            payForm.setComponentFocus(this.explain);
            payForm.setCanFocus(true);
            MainCanvas.mc.baseForm = payForm;
        }

        MainCanvas.mc.baseForm.draw(g);
    }

    public void tick() {
        switch (chargeState) {
            case 0:
            default:
                break;
            case 1:
                if (this.isSendMessageEnd(sum)) {
                    chargeState = 2;
                    MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm)null);
                } else if (!"waiting".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                    MainCanvas.mc.baseForm.getCurrentFocusForm().addAboutForm("waiting", "正在充值，请稍候…", (byte)0, 120, 0);
                }
                break;
            case 2:
                String result;
                if (count == 0 && isend == 0) {
                    result = "充值失败";
                } else if (isend == 1) {
                    if (count == 0) {
                        result = "金额补足失败，您购买" + specialShop.getCurrentName() + "物品未成功";
                    } else {
                        result = "金额补足成功，您成功购买" + specialShop.getCurrentName() + "物品";
                    }

                    sum = count;
                    MainCanvas.ni.send(1342180352);
                    MainCanvas.mc.baseForm.addAboutForm("waiting", "请稍候…", (byte)0, MainCanvas.screenW - 30, 0);
                    isend = 0;
                } else {
                    result = "您已成功发送" + count + "条短信，得到猛犸币" + mammothNumbers * count + "个。充值有些许延迟，请充值查询到账后再购买。";
                }

                MainCanvas.mc.baseForm.getCurrentFocusForm().addAboutForm("result", result, (byte)1, 120, 0);
                isCharge = false;
        }

    }

    static void send(boolean isAgree) {
        boolean var6 = false;

        byte[] agree;
        label68: {
            try {
                var6 = true;
                ++count;
                conn.send(msg);
                var6 = false;
                break label68;
            } catch (Exception var7) {
                --count;
                var6 = false;
            } finally {
                if (var6) {
                    ++processCount;
                    if (isAgree) {
                        sendCount = (byte)(count + agreeCount);
                        byte[] agree1 = new byte[]{0, (byte)(count + agreeCount)};
                        Util.saveRecord(agree1, "agreeOp");
                    }

                }
            }

            ++processCount;
            if (isAgree) {
                sendCount = (byte)(count + agreeCount);
                agree = new byte[]{0, (byte)(count + agreeCount)};
                Util.saveRecord(agree, "agreeOp");
            }

            return;
        }

        ++processCount;
        if (isAgree) {
            sendCount = (byte)(count + agreeCount);
            agree = new byte[]{0, (byte)(count + agreeCount)};
            Util.saveRecord(agree, "agreeOp");
        }

    }

    public static void sends(int number, boolean isAgree) {
        for(int i = 0; i < number; ++i) {
            send(isAgree);
        }

    }

    public static void init() {
        count = 0;
        processCount = 0;
    }

    public static void initSend(String content, String phone) {
        try {
            if (conn == null) {
                address = "sms://" + phone;
                conn = (MessageConnection)Connector.open(address);
                msg = (TextMessage)conn.newMessage("text");
            }

            msg.setPayloadText(content);
        } catch (Exception var3) {
            Exception e = var3;
            e.printStackTrace();
        }

    }

    public boolean isSendMessageEnd(int tt) {
        return processCount >= tt;
    }

    public String getMoney(int cell, int number) {
        int m = cell * number;
        return m % 10 == 0 ? String.valueOf(m / 10) : m / 10 + "." + m % 10;
    }

    public void addWaitInterface() {
        MainCanvas.mc.baseForm.addAboutForm("waiting", "请稍候…", (byte)0, MainCanvas.screenW - 30, 0);
    }

    public static byte getState() {
        return state;
    }

    public static void parse(int commID, byte[] data) {
        String hexID = Integer.toHexString(commID);
        System.out.println("[处理命令]:命令ID:0x" + hexID + ",命令类型：PCIncrementService");
        ByteArray execDataIn = new ByteArray(data);
        byte total;
        int type;
        int number;
        int i;
        switch (commID) {
            case 1342177664:
                byte num = execDataIn.readByte();
                String[] specialToolType = new String[num];

                for(number = 0; number < num; ++number) {
                    specialToolType[number] = execDataIn.readUTF() + "类";
                }

                MainCanvas.mc.specialToolItems = specialToolType;
                MainCanvas.mc.baseForm = null;
                MainCanvas.mc.setGameState((byte)1);
                MainCanvas.mc.setRightMenuSubState(80);
                break;
            case 1342177920:
                number = execDataIn.readByte();
                int maxnum = 0;
                if (number <= 36) {
                    maxnum = 4;
                } else {
                    maxnum = number / 9;
                    if (number % 9 != 0) {
                        ++maxnum;
                    }
                }

                specialShop = null;
                specialShop = new UIGrid(7, 32, (byte)maxnum, (byte)9, (byte)4, MainCanvas.mImgStuff);
                MainCanvas.mc.shopStuffPrice = new int[maxnum * 9];
                i = 0;

                for(int n = maxnum * 9; i < n; ++i) {
                    if (i < number) {
                        specialShop.setGridDetail((byte)i, execDataIn.readShort(), execDataIn.readByte(), (byte)0, execDataIn.readUTF(), execDataIn.readByte(), execDataIn.readShort(), (byte)1, (byte)0);
                        MainCanvas.mc.shopStuffPrice[i] = execDataIn.readInt();
                    } else {
                        specialShop.setGridDetail((byte)i, (short)0, (byte)0, (byte)0, "", (byte)0, (short)0, (byte)0, (byte)0);
                        MainCanvas.mc.shopStuffPrice[i] = 0;
                    }
                }

                toolType = MainCanvas.mc.specialToolTable.getCurentItem();
                MainCanvas.mc.releaseUI();
                if (MainCanvas.mc.getRightMenuSubState() != 8) {
                    MainCanvas.mc.setRightMenuSubState(10);
                    setState((byte)15);
                }
                break;
            case 1342178176:
                i = execDataIn.readByte();

                try {
                    if (isend == 0) {
                        isend = execDataIn.readByte();
                    }
                } catch (Exception var19) {
                }

                if (i == 1 || i == 2) {
                    Player.mommathMoney -= needMoney;
                    if (Player.mommathMoney < 0) {
                        Player.mommathMoney = 0;
                    }

                    MainCanvas.mc.texts[1].setStr(String.valueOf(Player.mommathMoney));
                }

                MainCanvas.mc.baseForm.setAboutForm((UIForm)null);
                String message = "";
                switch (i) {
                    case 0:
                        if (isend == 1) {
                            getInstance().charge((byte)3);
                            getInstance().sendChargeStatc(sum);
                            isCharge = true;
                            return;
                        }

                        isend = -1;
                        sendMark = 0;
                        MainCanvas.mc.baseForm.addAboutForm("toCharge", "购买失败,余额不足，是否充值？", (byte)2, 160, 0);
                        return;
                    case 1:
                        message = "购买成功，您的背包已没有空间，我们将以邮件的形式发给您，请注意查收";
                        break;
                    case 2:
                        message = "购买成功，请到背包中查看";
                        break;
                    case 3:
                        message = "很抱歉，彩票已售完";
                        break;
                    case 4:
                        message = "购买成功，不足猛犸币已自动充值并购买。但您的背包已没有空间，我们将以邮件的形式发给您，请注意查收";
                        break;
                    case 5:
                        message = "购买成功，请到背包中查看，不足猛犸币已自动充值并购买";
                }

                isend = -1;
                sendMark = 0;
                MainCanvas.mc.baseForm.addAboutForm("msg", message, (byte)1, MainCanvas.screenW - 30, 0);
                break;
            case 1342178432:
                Player.mommathMoney = execDataIn.readInt();
                switch (chargeWhere) {
                    case 1:
                    case 5:
                    case 6:
                    default:
                        break;
                    case 2:
                        MainCanvas.mc.baseForm.addAboutForm("waiting", "请稍候…", (byte)0, MainCanvas.screenW - 30, 0);
                        MainCanvas.ni.send(1342177536);
                        break;
                    case 3:
                        MainCanvas.mc.setRightMenuSubState(0);
                        setState((byte)-1);
                        MainCanvas.mc.releaseUI();
                        break;
                    case 4:
                        if (Cons.isCmobile && !Cons.cmwap) {
                            MainCanvas.setMessage(MainCanvas.mc.baseForm, "查询失败，请确认cmwap连接再重试");
                        }
                        break;
                    case 7:
                        if (MainCanvas.mc.isSelectList == 0) {
                            if (Player.mommathMoney < 3) {
                                PCFriend.gplus = 1;
                            } else {
                                PCFriend.gplus = 0;
                            }
                        } else if (MainCanvas.mc.isSelectList == 1 || MainCanvas.mc.isSelectList == 2) {
                            if (Player.mommathMoney < MainCanvas.mc.mcount * 3) {
                                PCFriend.gplus = 1;
                            } else {
                                PCFriend.gplus = 0;
                            }
                        }

                        MainCanvas.ni.send(201329408);
                        break;
                    case 8:
                        MainCanvas.mc.payChangeSerser = 2;
                        setState((byte)17);
                        MainCanvas.mc.releaseUI();
                }

                chargeWhere = 0;
                break;
            case 1342178688:
                byte result = execDataIn.readByte();
                String addMoneyResult;
                switch (1 - result) {
                    case 0:
                        addMoneyResult = "充值失败" + (Cons.isCmobile && !Cons.cmwap ? "，请确认cmwap连接再重试" : "");
                        break;
                    case 1:
                        int var10000 = Player.mommathMoney;
                        getInstance().getClass();
                        Player.mommathMoney = var10000 + 100 * sum;
                        StringBuffer var31 = (new StringBuffer()).append("您已经成功充入").append(sum).append("元，可以得到");
                        getInstance().getClass();
                        addMoneyResult = var31.append(100 * sum).append("个点数").toString();
                        break;
                    default:
                        addMoneyResult = "不明错误，请重试";
                }

                if (Cons.isCmobile) {
                    MainCanvas.mc.releaseUI();
                    MainCanvas.mc.topForm = null;
                    UIForm aboutForm = UIForm.makeAboutForm("msg", addMoneyResult, "确定", "", 170);
                    MainCanvas.mc.setTopForm(aboutForm, MainCanvas.screenH - aboutForm.height >> 1);
                } else {
                    MainCanvas.mc.baseForm.addAboutForm("msg", addMoneyResult, (byte)1, 120, 0);
                }
                break;
            case 1342178944:
                if (execDataIn.readByte() == 0) {
                    getInstance().historyStr = execDataIn.readUTF();
                    setState((byte)4);
                    MainCanvas.mc.releaseUI();
                } else {
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "查询失败，" + (Cons.isCmobile && !Cons.cmwap ? "请确认cmwap连接再重试" : "请重试"));
                }
                break;
            case 1342179712:
                MainCanvas.mc.baseForm.setAboutForm((UIForm)null);
                sendnumber = execDataIn.readUTF();
                sendtext = execDataIn.readUTF();
                String tips;
                if (!"0".equals(sendnumber) && !"0".equals(sendtext)) {
                    tips = "如果您账户余额不足，系统将自动为您补足金额。";
                } else {
                    tips = "";
                }

                MainCanvas.mc.baseForm.addAboutForm("buy", "购买" + specialShop.getCurrentName() + "？\n需要" + needMoney + "点数。\n" + tips, (byte)2, 220, 0);
                break;
            case 1342179968:
                byte type1 = execDataIn.readByte();
                MainCanvas.mc.baseForm.setAboutForm((UIForm)null);
                if (type1== 0) {
                    getInstance().is1conFalse = 0;
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, execDataIn.readUTF());
                } else if (type1 == 1) {
                    getInstance().is1conFalse = 0;
                    int l = 160;
                    int h = 180;
                    MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm)null);
                    UIForm luckyPssForm = new UIForm(0, 0, l, h, "lucky");
                    luckyPssForm.setBackGround((byte)9);
                    UIRim frame = new UIRim(0, 0, l - 1, h - 1, (byte)4);
                    MainCanvas.mc.labels[5] = new UILabel(0, 0, 0, 0, "我要抽奖", 15718815, (byte)0, (byte)0);
                    MainCanvas.mc.labels[6] = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte)0, (byte)0);
                    UITextArea tmpmessage = new UITextArea(5, 5, l - 10, 95, execDataIn.readUTF());
                    tmpmessage.setColor(15849885);
                    UILabel lblMessage = new UILabel(0, 102, l - 10, 0, "抽奖百分百中奖！一等奖=充值额的100％，二等奖=充值额的50％，三等奖=充值额的10％", 16777215, (byte)1, (byte)0);
                    luckyPssForm.addComponent(frame);
                    luckyPssForm.addComponent(tmpmessage);
                    luckyPssForm.addComponentInCenter(lblMessage, (byte)2);
                    luckyPssForm.addComponentInCenter(MainCanvas.mc.labels[5], (byte)5);
                    luckyPssForm.addComponentInCenter(MainCanvas.mc.labels[6], (byte)6);
                    luckyPssForm.setComponentFocus(tmpmessage);
                    luckyPssForm.setFocus(true);
                    MainCanvas.mc.baseForm.getCurrentFocusForm().setAboutForm(luckyPssForm);
                    MainCanvas.mc.baseForm.setComponentFocus(luckyPssForm);
                } else if (type1 == 2) {
                    if (getInstance().is1conFalse == 0) {
                        ++getInstance().is1conFalse;
                        MainCanvas.ni.send(1342179840);
                    } else {
                        getInstance().is1conFalse = 0;
                        Download.gotoURL(MainCanvas.mc.aMidlet, (byte)14);
                    }
                }
                break;
            case 1342180224:
                total = execDataIn.readByte();
                String[] tmpS = new String[total];

                for(type = 0; type < total; ++type) {
                    tmpS[type] = execDataIn.readUTF();
                }

                getInstance().parseChargeData(total, tmpS);
                setState((byte)20);
                MainCanvas.mc.releaseUI();
                break;
            case 1342180736:
                try {
                    sendMark = execDataIn.readByte();
                } catch (Exception var18) {
                }

                if (sendMark == 1) {
                    PCIncrementService.number = 1;
                    MainCanvas.ni.send(1342178048);
                    MainCanvas.mc.baseForm.addAboutForm("waiting", "请稍候…", (byte)0, MainCanvas.screenW - 30, 0);
                } else if (sendMark == 2) {
                    MainCanvas.mc.baseForm.addAboutForm("conf1", "您的账户余额不足，系统将自动为您补足金额。", (byte)2, 160, 0);
                } else {
                    MainCanvas.mc.baseForm.addAboutForm("msg", "购买失败，余额不足", (byte)1, MainCanvas.screenW - 30, 0);
                }
                break;
            case 1342181248:
                if ("waiting".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                    MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm)null);
                }

                sendMd5 = execDataIn.readUTF();
                if (isend == 1) {
                    initSend(sendtext, sendnumber);
                } else {
                    initSend(getSMSCoin(MainCanvas.mc.userName, "tianjie", sendMd5), phoneNumber);
                }

                getInstance().sendChargeStatc(sum);
                break;
            case 1342193792:
                type = execDataIn.readByte();
                if (type == 0) {
                    payType = 0;
                } else {
                    payType = -1;
                }

                String msg = execDataIn.readUTF();
                MainCanvas.mc.baseForm.setAboutForm((UIForm)null);
                MainCanvas.mc.baseForm.addAboutForm("msg", msg, (byte)1, MainCanvas.screenW - 30, 0);
                break;
            case 1342197888:
                type = execDataIn.readByte();
                if (type == 1) {
                    MainCanvas.ni.send(1342218240);
                } else {
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "请求失败，请重试");
                }
                break;
            case 1342201984:
                type = execDataIn.readByte();
                switch (type) {
                    case 1:
                        getInstance().connectMark = execDataIn.readByte();
                        getInstance().baoyueIP = execDataIn.readUTF();
                        getInstance().baoyueUrl = execDataIn.readUTF();
                        getInstance().addWaitInterface();
                        logAddress = "http://" + getInstance().baoyueIP + getInstance().baoyueUrl;
                        getInstance().baoyueURl(getInstance().baoyueIP, getInstance().baoyueUrl);
                        if (sendWaitTime > 4) {
                            sendWaitTime -= 4;
                        }

                        MainCanvas.mc.baseForm.getCurrentFocusForm().setAboutForm(UIForm.makeAboutForm("waitInfo", "信息获取中，预计需要等待" + sendWaitTime + "秒", "", "", MainCanvas.screenW - 60));
                        return;
                    case 2:
                        MainCanvas.mc.isInBaoyueWait = false;
                        return;
                    case 3:
                        MainCanvas.mc.isInBaoyueWait = false;
                        MainCanvas.setMessage(MainCanvas.mc.baseForm, execDataIn.readUTF());
                        return;
                    case 4:
                        if (!is15Send) {
                            for(i = getInstance().otherBaoyueIndex; i < dingzhiLen; ++i) {
                                if (getInstance().isSelect[i]) {
                                    getInstance().setBaoyue(1, MainCanvas.mc.monthlyItemID[i]);
                                    MainCanvas.ni.send(Cmd.C_VALUEADDED_RENT);
                                    return;
                                }
                            }
                        }

                        return;
                    case 5:
                        ++bodyMark;
                        MainCanvas.ni.send(1342201856);
                        return;
                    default:
                        MainCanvas.setMessage(MainCanvas.mc.baseForm, "请求失败，请重试");
                        return;
                }
            case 1342206080:
                MainCanvas.mc.isMonthly = false;
                type = execDataIn.readByte();
                if (type == 1) {
                    MainCanvas.mc.loginRewardUesrId();
                    MainCanvas.mc.monthlyTextInfo = execDataIn.readUTF();
                    i = execDataIn.readByte();
                    if (i > 0) {
                        MainCanvas.mc.isMonthly = true;
                        MainCanvas.mc.monthlyItemID = null;
                        MainCanvas.mc.monthlyItemName = null;
                        MainCanvas.mc.monthlyItemLocation = null;
                        MainCanvas.mc.monthlyItemMark = null;
                        MainCanvas.mc.monthlyItemID = new short[i];
                        MainCanvas.mc.monthlyItemName = new String[i];
                        MainCanvas.mc.monthlyItemLocation = new int[i];
                        MainCanvas.mc.monthlyItemMark = new byte[i];

                        for(i = 0; i < i; ++i) {
                            MainCanvas.mc.monthlyItemID[i] = execDataIn.readShort();
                            MainCanvas.mc.monthlyItemName[i] = execDataIn.readUTF();
                            MainCanvas.mc.monthlyItemLocation[i] = execDataIn.readInt();
                            MainCanvas.mc.monthlyItemMark[i] = execDataIn.readByte();
                        }
                    }
                }
                break;
            case 1342210176:
                type = execDataIn.readByte();
                if (type == 0) {
                    total = execDataIn.readByte();
                    getInstance().shenzhouData = new String[total];

                    for(int iz = 0; iz < total; ++iz) {
                        getInstance().shenzhouData[iz] = execDataIn.readUTF();
                    }

                    setState((byte)2);
                    MainCanvas.mc.releaseUI();
                } else if (type == 1) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm)null);
                    Download.gotoURL(MainCanvas.mc.aMidlet, (byte)4);
                }
                break;
            case 1342214272:
                MainCanvas.mc.baseForm.setAboutForm((UIForm)null);
                MainCanvas.mc.baseForm.getCurrentFocusForm().addAboutForm("lshow", execDataIn.readUTF(), (byte)1, 140, 0);
                break;
            case 1342218368:
                type = execDataIn.readByte();
                if (type == 1) {
                    bodyMark = 1;
                    MainCanvas.ni.send(1342201856);
                } else {
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "请求失败，请重试");
                }
                break;
            case 1342222464:
                i = execDataIn.readByte();
                getInstance().chargeMenu = new String[i];

                for(i = 0; i < i; ++i) {
                    getInstance().chargeMenu[i] = execDataIn.readUTF();
                }

                getInstance().chargeEXP = execDataIn.readUTF();
                getInstance().isShowTwoC = execDataIn.readByte() == 2;
                getInstance().chargeTwoTipStr = execDataIn.readUTF();
                getInstance().charge((byte)3);
        }

    }

    public static byte[] compress(int commID) {
        ByteArray execDataOut = new ByteArray();
        switch (commID) {
            case 1342177536:
            case 1342180096:
            case 1342181120:
            case 1342210048:
            case 1342214144:
            default:
                break;
            case 1342177792:
                execDataOut.writeByte(MainCanvas.mc.specialToolTable.getCurrentPointer());
                break;
            case 1342178048:
                execDataOut.writeShort(specialShop.getCurrentId());
                execDataOut.writeByte(number);
                execDataOut.writeByte(sendMark);
                break;
            case 1342178304:
                if (MainCanvas.userID != null) {
                    execDataOut.writeUTF(MainCanvas.userID);
                } else {
                    execDataOut.writeUTF("");
                }
                break;
            case 1342178560:
                execDataOut.writeByte(sum);
                break;
            case 1342178816:
                execDataOut.writeByte(historyIndex);
                break;
            case 1342179584:
                execDataOut.writeShort(specialShop.getCurrentId());
                break;
            case 1342179840:
                byte tmpIndex = 0;
                if (!getInstance().isSZEnter) {
                    tmpIndex = (byte)Integer.parseInt(getInstance().chargeData[getInstance().chargeSelectIndex][6]);
                }

                execDataOut.writeByte(tmpIndex);
                execDataOut.writeUTF(getInstance().CardNum);
                execDataOut.writeUTF(getInstance().CardPass);
                execDataOut.writeUTF(getInstance().CardValue);
                execDataOut.writeUTF(getInstance().chargePassport);
                break;
            case 1342180352:
                execDataOut.writeShort(specialShop.getCurrentId());
                execDataOut.writeByte(sum);
                break;
            case 1342180608:
                execDataOut.writeShort(specialShop.getCurrentId());
                break;
            case 1342193664:
                execDataOut.writeByte(payType);
                break;
            case Cmd.C_VALUEADDED_RENT:
                execDataOut.writeByte(getInstance().connectMark);
                execDataOut.writeShort(getInstance().baoyueItem);
                execDataOut.writeByte(getInstance().conBaoyue);
                execDataOut.writeUTF(logAddress);
                execDataOut.writeByte(max_BodySend);
                if (sendWaitTime > 4) {
                    sendWaitTime -= 4;
                }

                MainCanvas.mc.baseForm.getCurrentFocusForm().setAboutForm(UIForm.makeAboutForm("waitInfo", "命令发送中，预计需要等待" + sendWaitTime + "秒", "", "", MainCanvas.screenW - 60));
                break;
            case 1342201856:
                execDataOut.writeByte(bodyMark);
                int length = 0;
                if (max_BodySend > 0) {
                    if (max_BodySend == 1) {
                        length = MainCanvas.mc.httpConn.getContents().length;
                    } else if (bodyMark == max_BodySend) {
                        length = MainCanvas.mc.httpConn.getContents().length - (bodyMark - 1) * 256;
                    } else {
                        length = 256;
                    }

                    byte[] s = new byte[length];

                    for(int i = 0; i < length; ++i) {
                        s[i] = MainCanvas.mc.httpConn.getContents()[i + (bodyMark - 1) * 256];
                    }

                    getInstance().connectLogbody = s;
                }

                execDataOut.writeShort((short)length);
                execDataOut.writeByteArray(getInstance().connectLogbody);
                break;
            case 1342205952:
                dingzhiLen = (byte)MainCanvas.mc.monthlyItemName.length;
                getInstance().otherBaoyueIndex = 0;
                is15Send = false;
                execDataOut.writeByte(dingzhiLen);

                int i;
                for(i = 0; i < dingzhiLen; ++i) {
                    execDataOut.writeByte(getInstance().isSelect[i] ? 0 : 1);
                    execDataOut.writeInt(MainCanvas.mc.monthlyItemID[i]);
                    execDataOut.writeInt(MainCanvas.mc.monthlyItemLocation[i]);
                    if (MainCanvas.mc.monthlyItemMark[i] == 1) {
                        getInstance().otherBaoyueIndex = (byte)i;
                    }
                }

                MainCanvas.ni.send(16781312);

                for(i = 0; i < getInstance().otherBaoyueIndex; ++i) {
                    if (getInstance().isSelect[i]) {
                        getInstance().setBaoyue(1, MainCanvas.mc.monthlyItemID[i]);
                        break;
                    }
                }

                for(i = getInstance().otherBaoyueIndex; i < dingzhiLen; ++i) {
                    if (getInstance().isSelect[i]) {
                        getInstance().setBaoyue(1, MainCanvas.mc.monthlyItemID[i]);
                        is15Send = true;
                        return execDataOut.toByteArray();
                    }
                }

                return execDataOut.toByteArray();
            case 1342218240:
                execDataOut.writeUTF(getInstance().connectLoghead);
                break;
            case 1342222336:
                try {
                    int s = Integer.parseInt(HttpConn.valueDetail[0]);
                    execDataOut.writeInt(s);
                } catch (Exception var6) {
                    execDataOut.writeInt(2);
                }

                execDataOut.writeInt(mammothNumbers);
        }

        return execDataOut.toByteArray();
    }

    public void drawAgree(Graphics g) {
        if (this.imgBg == null) {
            try {
                this.imgBg = Image.createImage("/dxbg.png");
            } catch (Exception var6) {
            }
        }

        g.drawImage(this.imgBg, 0, 0, 20);
        g.setColor(15718814);
        switch (this.sendState) {
            case 1:
                if (this.isSendMessageEnd(3 - agreeCount)) {
                    this.sendState = 2;
                } else {
                    g.drawString("您正在发送第" + count + "条充值短信", MainCanvas.screenW >> 1, (MainCanvas.screenH >> 1) - MainCanvas.CHARH, 17);
                }
                break;
            case 2:
                String result = "";
                switch (sendCount) {
                    case 0:
                        result = "付费充值失败";
                        break;
                    case 3:
                        result = "您的短信已经发送成功";
                        this.isSucceed = true;
                        break;
                    default:
                        result = "您有" + (3 - sendCount) + "条短信没有发送";
                }

                g.drawString(result, MainCanvas.screenW >> 1, (MainCanvas.screenH >> 1) - MainCanvas.CHARH, 17);
                if (this.isSucceed) {
                    g.drawString("点击进入", 3, MainCanvas.screenH - 3, 36);
                } else {
                    g.drawString("重新发送", 3, MainCanvas.screenH - 3, 36);
                }

                g.drawString("退出游戏", MainCanvas.screenW - 3, MainCanvas.screenH - 3, 40);
                break;
            case 3:
                System.out.println("22222");
                g.drawString("正在获取中...", MainCanvas.screenW >> 1, (MainCanvas.screenH >> 1) - MainCanvas.CHARH, 17);
                break;
            case 4:
                System.out.println("111111");
                g.drawString("获取失败", MainCanvas.screenW >> 1, (MainCanvas.screenH >> 1) - MainCanvas.CHARH, 17);
                g.drawString("重新获取", 3, MainCanvas.screenH - 3, 36);
                g.drawString("退出游戏", MainCanvas.screenW - 3, MainCanvas.screenH - 3, 40);
                break;
            default:
                String[] strs = null;
                strs = Util.wrapText(sendCount == 0 ? "您已成功安装空中网空中猛犸出品《天劫online》，付费后可以正常游戏，一次性付费6元（通过3条短信发送）终身免费，还有神秘礼品赠送，感谢您的使用，祝您游戏愉快！" : "您已成功安装空中网空中猛犸出品《天劫online》，付费后可以正常游戏，您还有" + (3 - sendCount) + "条短信发送后终身免费（总共付费6元），还有神秘礼品赠送，感谢您的使用，祝您游戏愉快！", MainCanvas.screenW - 30, MainCanvas.font[1]);
                byte lg = (byte)strs.length;

                for(byte i = 0; i < lg; ++i) {
                    g.drawString(strs[i], 15, 60 + i * (MainCanvas.CHARH + 5), 20);
                }

                g.drawString("发送短信", 3, MainCanvas.screenH - 3, 36);
                g.drawString("退出游戏", MainCanvas.screenW - 3, MainCanvas.screenH - 3, 40);
        }

    }

    public void keyInAgree() {
        byte[] agree;
        if (!MainCanvas.isKeyPress(17) && !MainCanvas.isKeyPress(14)) {
            if (MainCanvas.isKeyPress(18)) {
                agree = new byte[2];
                if (sendCount == 3) {
                    agree[0] = 1;
                } else {
                    agree[0] = 0;
                }

                agree[1] = sendCount;
                Util.saveRecord(agree, "agreeOp");
                MainCanvas.mc.stop();
                MainCanvas.mc.aMidlet.exitMIDlet();
            }
        } else {
            switch (this.sendState) {
                case 1:
                case 3:
                    break;
                case 2:
                    if (this.isSucceed) {
                        this.isSucceed = false;
                        agree = new byte[]{1, 3};
                        Util.saveRecord(agree, "agreeOp");
                        this.imgBg = null;
                        MainCanvas.mc.setState((byte)26);
                    } else {
                        agreeCount = sendCount;
                        this.sendAgree();
                        this.sendState = 1;
                    }
                    break;
                case 4:
                    MainCanvas.mc.getSMS_Content(16);
                    this.sendState = 3;
                    break;
                default:
                    this.sendAgree();
                    this.sendState = 1;
            }
        }

    }

    void sendAgree() {
        phoneNumber = HttpConn.getSMSNumber(HttpConn.sms_coin);

        try {
            if (conn == null) {
                address = "sms://" + phoneNumber;
                conn = (MessageConnection)Connector.open(address);
                msg = (TextMessage)conn.newMessage("text");
            }

            msg.setPayloadText("AGCMOB.tianjie");
        } catch (Exception var2) {
            Exception e = var2;
            e.printStackTrace();
        }

        init();
        Thread send = new Thread() {
            public void run() {
                PCIncrementService.sends(3 - PCIncrementService.agreeCount, true);
            }
        };
        send.start();
    }

    static void sendMommoth() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                MainCanvas.mc.loginRewardUesrId();
                HttpConn.loginRewardJad(12);

                while(MainCanvas.mc.isGetingUserID) {
                    try {
                        Thread.sleep(100L);
                    } catch (Exception var2) {
                        Exception e = var2;
                        e.printStackTrace();
                    }
                }

                MainCanvas.ni.send(1342178304);
            }
        });
        t.start();
    }

    void setBaoyue(int type, short itemId) {
        sendWaitTime = 45;
        this.connectMark = 0;
        max_BodySend = 0;
        logAddress = "";
        this.conBaoyue = (byte)type;
        this.baoyueItem = itemId;
        MainCanvas.mc.isInBaoyueWait = true;
        this.addWaitInterface();
    }

    void baoyueURl(String ip, String url) {
        MainCanvas.mc.httpConn = null;
        MainCanvas.mc.httpConn = new HttpConn(ip, url, Cons.cmwap, 20, 0);
        MainCanvas.mc.httpConn.start();
    }

    public void drawMonthly(Graphics g) {
        if (MainCanvas.mc.baseForm == null) {
            UIForm form = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "monthly");
            form.setBackGround((byte)9);
            UIRim frame = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte)4);
            UIRim rimTitle = new UIRim(0, 9, 166, 20, (byte)7);
            UILabel lblTitle = new UILabel(0, 12, 176, 0, "包月武器续费", 15718814, (byte)1, (byte)0);
            UIRim rimDown = new UIRim(0, 29, 166, 160, (byte)0);
            form.addComponent(frame);
            form.addComponentInCenter(rimTitle, (byte)2);
            form.addComponentInCenter(lblTitle, (byte)2);
            form.addComponentInCenter(rimDown, (byte)2);
            form.addLeftButton("操作");
            form.addRightButton("取消");
            UITextArea contentLabel = new UITextArea(0, 31, 160, 55, MainCanvas.mc.monthlyTextInfo);
            form.addComponentInCenter(contentLabel, (byte)2);
            MainCanvas.mc.textArea[0] = null;
            MainCanvas.mc.textArea[0] = contentLabel;
            if (MainCanvas.mc.monthlyItemName != null) {
                int monthlyLen = MainCanvas.mc.monthlyItemName.length;
                if (monthlyLen > 0) {
                    UITable table = new UITable(0, 87, 160, 100, monthlyLen, 1, monthlyLen > 7 ? 7 : monthlyLen, (byte)0, (byte)7);

                    for(int i = 0; i < monthlyLen; ++i) {
                        table.setItem(MainCanvas.mc.monthlyItemName[i], i, 15718814);
                    }

                    form.addComponentInCenter(table, (byte)2);
                    table.setSelectAll(true);
                    if (MainCanvas.mc.textArea[0].isEnd()) {
                        form.setComponentFocus(table);
                    } else {
                        contentLabel.setColor(15718814);
                    }

                    MainCanvas.mc.tables[0] = null;
                    MainCanvas.mc.tables[0] = table;
                }
            }

            MainCanvas.mc.baseForm = form;
            MainCanvas.mc.baseForm.setFocus(true);
        }

        MainCanvas.mc.baseForm.draw(g);
    }

    private void actionToMonthly() {
        if (MainCanvas.mc.baseForm != null) {
            UIComponent cmd = MainCanvas.mc.baseForm.getCommand();
            if (!MainCanvas.isKeyPress(17) && !MainCanvas.isKeyPress(14)) {
                if (MainCanvas.isKeyPress(18)) {
                    if (MainCanvas.mc.baseForm.getCurrentFocusForm() == MainCanvas.mc.baseForm) {
                        MainCanvas.mc.tables[0].setSelectAll(false);
                        this.isSelect = null;
                        this.isSelect = MainCanvas.mc.tables[0].getSelectAll();
                        MainCanvas.ni.send(1342205952);
                        setState((byte)-1);
                        MainCanvas.mc.releaseUI();
                        MainCanvas.mc.setGameState((byte)0);
                        MainCanvas.mc.stratGameForm();
                    } else if ("menu".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                        MainCanvas.mc.baseForm.setAboutForm((UIForm)null);
                    }
                } else if (MainCanvas.mc.actionInForm(cmd) && !"menu".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                    if (MainCanvas.isKeyPress(13)) {
                        if (MainCanvas.mc.textArea[0].isEnd()) {
                            MainCanvas.mc.textArea[0].setColor(8349245);
                            MainCanvas.mc.baseForm.setComponentFocus(MainCanvas.mc.tables[0]);
                        }
                    } else if (MainCanvas.isKeyPress(11) && MainCanvas.mc.tables[0].getCurrentPointer() == 0) {
                        MainCanvas.mc.baseForm.setComponentFocus(MainCanvas.mc.textArea[0]);
                        MainCanvas.mc.textArea[0].setColor(15718814);
                    }
                }
            } else if (MainCanvas.mc.baseForm.getCurrentFocusForm() == MainCanvas.mc.baseForm) {
                if (!MainCanvas.mc.tables[0].isNull()) {
                    String[] strs = new String[]{"选择", "取消选择", "全选", "全不选", "续费定制"};
                    UIForm menuForm = new UIForm(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, "menu");
                    menuForm.setBackGround((byte)9);
                    MainCanvas.mc.menus[0] = new UIMenu(35, 0, 80, 0, (String)null, strs);
                    menuForm.addComponent(MainCanvas.mc.menus[0]);
                    MainCanvas.mc.menus[0].setXY(MainCanvas.mc.menus[0].positionX, MainCanvas.screenH - MainCanvas.mc.menus[0].height - 4);
                    MainCanvas.mc.baseForm.setAboutForm(menuForm);
                    MainCanvas.mc.baseForm.focusComponent.setFocus(true);
                } else {
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "名单为空");
                }
            } else if ("menu".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                switch (MainCanvas.mc.menus[0].getCurrentPointer()) {
                    case 0:
                        MainCanvas.mc.tables[0].setSelect(MainCanvas.mc.tables[0].getCurrentPointer(), true);
                        break;
                    case 1:
                        MainCanvas.mc.tables[0].setSelect(MainCanvas.mc.tables[0].getCurrentPointer(), false);
                        break;
                    case 2:
                        MainCanvas.mc.tables[0].setSelectAll(true);
                        break;
                    case 3:
                        MainCanvas.mc.tables[0].setSelectAll(false);
                        break;
                    case 4:
                        this.isSelect = null;
                        this.isSelect = MainCanvas.mc.tables[0].getSelectAll();

                        for(int i = 0; i < this.isSelect.length && !this.isSelect[i]; ++i) {
                            if (i == this.isSelect.length - 1) {
                                MainCanvas.mc.baseForm.addAboutForm("err", "请选择要续订的道具", (byte)1, 140, 0);
                                return;
                            }
                        }

                        this.addWaitInterface();
                        MainCanvas.ni.send(1342205952);
                }
            } else if ("msg".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                MainCanvas.mc.releaseUI();
                setState((byte)-1);
                MainCanvas.mc.setGameState((byte)0);
                MainCanvas.mc.stratGameForm();
            } else if ("err".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                MainCanvas.mc.baseForm.setAboutForm((UIForm)null);
            }

        }
    }

    public void drawShenZhouCharge(Graphics g) {
        if (MainCanvas.mc.baseForm == null) {
            MainCanvas.mc.baseForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "");
            UIRim frame = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte)4);
            UIRim rimTitle = new UIRim(0, 13, 159, 17, (byte)7);
            UILabel lblTitle = new UILabel(0, rimTitle.positionY + 3, 0, 0, "猛犸币大额充值列表", 15718814, (byte)1, (byte)0);
            UIRim rimDown = new UIRim(0, 32, 159, 160, (byte)0);
            int tempcount = this.shenzhouData.length;
            this.SzTable = new UITable(0, 33, 159, 158, tempcount, 1, tempcount > 10 ? 10 : tempcount, (byte)0, (byte)3);
            this.SzTable.setAutoHeight(true);
            UILabel label1 = new UILabel(0, 0, 0, 0, "确定", 15718815, (byte)0, (byte)0);
            UILabel label2 = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte)0, (byte)0);

            for(int i = 0; i < tempcount; ++i) {
                this.SzTable.addItem(this.shenzhouData[i], 15718815);
            }

            MainCanvas.mc.baseForm.addComponent(frame);
            MainCanvas.mc.baseForm.addComponentInCenter(rimDown, (byte)2);
            MainCanvas.mc.baseForm.addComponentInCenter(rimTitle, (byte)2);
            MainCanvas.mc.baseForm.addComponentInCenter(lblTitle, (byte)2);
            MainCanvas.mc.baseForm.addComponentInCenter(label1, (byte)5);
            MainCanvas.mc.baseForm.addComponentInCenter(label2, (byte)6);
            MainCanvas.mc.baseForm.addComponentInCenter(this.SzTable, (byte)2);
            this.SzTable.setXY(this.SzTable.positionX, this.SzTable.positionY);
            MainCanvas.mc.baseForm.setFocus(true);
        }

        MainCanvas.mc.baseForm.draw(g);
    }

    public void actionToShenZhouCharge() {
        if (MainCanvas.mc.baseForm != null) {
            UIComponent cmd = MainCanvas.mc.baseForm.getCommand();
            if (!MainCanvas.isKeyPress(17) && !MainCanvas.isKeyPress(14)) {
                if (MainCanvas.isKeyPress(18)) {
                    setState((byte)-1);
                    MainCanvas.mc.releaseUI();
                } else if (MainCanvas.mc.actionInForm(cmd)) {
                }
            } else {
                int s = this.SzTable.getCurrentPointer();
                if (s == this.shenzhouData.length - 1) {
                    this.isSZEnter = false;
                    MainCanvas.ni.send(1342180096);
                    this.addWaitInterface();
                } else {
                    this.chargeSelectIndex = 0;
                    this.isSZEnter = true;
                    this.CardValue = this.shenzhouData[s].substring(0, this.shenzhouData[s].indexOf("元"));
                    setState((byte)21);
                    MainCanvas.mc.releaseUI();
                }
            }

        }
    }

    public void drawLargeCharge(Graphics g) {
        if (MainCanvas.mc.baseForm == null) {
            MainCanvas.mc.baseForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "");
            UIRim frame = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte)4);
            UIRim rimTitle = new UIRim(0, 13, 159, 17, (byte)7);
            UILabel lblTitle = new UILabel(0, rimTitle.positionY + 3, 0, 0, "其他充值列表", 15718814, (byte)1, (byte)0);
            UIRim rimDown = new UIRim(0, 32, 159, 160, (byte)0);
            int tempcount = this.chargeData.length;
            this.chargeTable = new UITable(0, 33, 159, 158, tempcount, 1, tempcount > 10 ? 10 : tempcount, (byte)0, (byte)3);
            this.chargeTable.setAutoHeight(true);
            UILabel label1 = new UILabel(0, 0, 0, 0, "确定", 15718815, (byte)0, (byte)0);
            UILabel label2 = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte)0, (byte)0);

            for(int i = 0; i < tempcount; ++i) {
                this.chargeTable.addItem(this.chargeData[i][0], 15718815);
            }

            MainCanvas.mc.baseForm.addComponent(frame);
            MainCanvas.mc.baseForm.addComponentInCenter(rimDown, (byte)2);
            MainCanvas.mc.baseForm.addComponentInCenter(rimTitle, (byte)2);
            MainCanvas.mc.baseForm.addComponentInCenter(lblTitle, (byte)2);
            MainCanvas.mc.baseForm.addComponentInCenter(label1, (byte)5);
            MainCanvas.mc.baseForm.addComponentInCenter(label2, (byte)6);
            MainCanvas.mc.baseForm.addComponentInCenter(this.chargeTable, (byte)2);
            this.chargeTable.setXY(this.chargeTable.positionX, this.chargeTable.positionY);
            MainCanvas.mc.baseForm.setFocus(true);
        }

        MainCanvas.mc.baseForm.draw(g);
    }

    public void actionToLargeCharge() {
        if (MainCanvas.mc.baseForm != null) {
            UIComponent cmd = MainCanvas.mc.baseForm.getCommand();
            if (!MainCanvas.isKeyPress(17) && !MainCanvas.isKeyPress(14)) {
                if (MainCanvas.isKeyPress(18)) {
                    setState((byte)2);
                    MainCanvas.mc.releaseUI();
                } else if (MainCanvas.mc.actionInForm(cmd)) {
                }
            } else {
                this.chargeSelectIndex = this.chargeTable.getCurrentPointer();
                if ("1".equals(this.chargeData[this.chargeSelectIndex][7].trim())) {
                    Download.gotoURL(MainCanvas.mc.aMidlet, (byte)4);
                } else {
                    setState((byte)21);
                    MainCanvas.mc.releaseUI();
                }
            }

        }
    }

    void drawChargeSelect(Graphics g) {
        if (MainCanvas.mc.baseForm == null) {
            MainCanvas.mc.baseForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "");
            UIRim frame = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte)4);
            UIRim rimTitle = new UIRim(0, 13, 159, 17, (byte)7);
            UILabel lblTitle = new UILabel(0, rimTitle.positionY + 3, 0, 0, this.isSZEnter ? "猛犸币大额充值" : this.chargeData[this.chargeSelectIndex][0].trim(), 15718814, (byte)1, (byte)0);
            UIRim rimDown = new UIRim(0, 32, 159, 160, (byte)0);
            if (!this.isSZEnter) {
                if (!"选择面值".equals(this.chargeData[this.chargeSelectIndex][1].trim())) {
                    if ("输入面值".equals(this.chargeData[this.chargeSelectIndex][1].trim())) {
                        MainCanvas.mc.texts[2] = new UIText(50, 58, 50, 0, 5, (byte)2, this.CardValue);
                        MainCanvas.mc.labels[4] = new UILabel(24, 38, 200, 17, this.chargeData[this.chargeSelectIndex][1] + ":", 15718815, (byte)0, (byte)0);
                    }
                } else {
                    MainCanvas.mc.rbs[0] = new UIRadioButton(24, 50, 0, 0, this.chargeData[this.chargeSelectIndex][1].trim(), (byte)2);
                    String[] tmps = Util.splitToken(this.chargeData[this.chargeSelectIndex][2], ',');
                    int which30 = 0;

                    for(int i = 0; i < tmps.length; ++i) {
                        MainCanvas.mc.rbs[0].addItems(tmps[i]);
                        if ("30".equals(tmps[i])) {
                            which30 = i;
                        }
                    }

                    MainCanvas.mc.rbs[0].setChooseItem(which30);
                }
            }

            MainCanvas.mc.texts[0] = new UIText(50, 98, 92, 0, 17, (byte)2, "");
            MainCanvas.mc.texts[1] = new UIText(50, 138, 92, 0, 18, (byte)2, "");
            MainCanvas.mc.labels[0] = new UILabel(0, 0, 0, 0, "操作", 15718815, (byte)0, (byte)0);
            MainCanvas.mc.labels[1] = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte)0, (byte)0);
            MainCanvas.mc.labels[2] = new UILabel(24, 78, 200, 17, this.isSZEnter ? "输入充值卡卡号" : this.chargeData[this.chargeSelectIndex][3] + ":", 15718815, (byte)0, (byte)0);
            MainCanvas.mc.labels[3] = new UILabel(24, 118, 200, 17, this.isSZEnter ? "输入充值卡密码" : this.chargeData[this.chargeSelectIndex][4] + ":", 15718815, (byte)0, (byte)0);
            MainCanvas.mc.baseForm.addComponent(frame);
            MainCanvas.mc.baseForm.addComponentInCenter(rimDown, (byte)2);
            MainCanvas.mc.baseForm.addComponentInCenter(rimTitle, (byte)2);
            MainCanvas.mc.baseForm.addComponentInCenter(lblTitle, (byte)2);
            MainCanvas.mc.baseForm.addComponentInCenter(MainCanvas.mc.labels[0], (byte)5);
            MainCanvas.mc.baseForm.addComponentInCenter(MainCanvas.mc.labels[1], (byte)6);
            MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.labels[2]);
            MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.labels[3]);
            MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.texts[0]);
            MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.texts[1]);
            MainCanvas.mc.texts[0].setAroundComponent(MainCanvas.mc.texts[1], (byte)2);
            if (!this.isSZEnter) {
                if (MainCanvas.mc.texts[2] != null) {
                    MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.labels[4]);
                    MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.texts[2]);
                    MainCanvas.mc.texts[2].setAroundComponent(MainCanvas.mc.texts[0], (byte)2);
                    MainCanvas.mc.baseForm.setComponentFocus(MainCanvas.mc.texts[2]);
                } else {
                    MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.rbs[0]);
                    MainCanvas.mc.rbs[0].setAroundComponent(MainCanvas.mc.texts[0], (byte)2);
                    MainCanvas.mc.baseForm.setComponentFocus(MainCanvas.mc.rbs[0]);
                }
            } else {
                MainCanvas.mc.baseForm.setComponentFocus(MainCanvas.mc.texts[0]);
                UILabel detail = new UILabel(20, 48, 140, 0, "    支持中国移动全国充值卡与部分地方充值卡", 15653280, (byte)0, (byte)0);
                MainCanvas.mc.baseForm.addComponent(detail);
            }

            MainCanvas.mc.baseForm.setFocus(true);
            if (!this.isSZEnter && !"".equals(this.chargeData[this.chargeSelectIndex][5].trim())) {
                MainCanvas.mc.baseForm.setMessage(this.chargeData[this.chargeSelectIndex][5].trim(), false);
            }
        }

        MainCanvas.mc.baseForm.draw(g);
    }

    void actionToChargeSelect() {
        if (MainCanvas.mc.baseForm != null) {
            UIComponent cmd = MainCanvas.mc.baseForm.getCommand();
            if (MainCanvas.isKeyPress(17)) {
                if (MainCanvas.mc.baseForm.getCurrentFocusForm() == MainCanvas.mc.baseForm) {
                    UIForm menuForm = new UIForm(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, "menu");
                    menuForm.setBackGround((byte)9);
                    MainCanvas.mc.menus[0] = new UIMenu(0, 0, 80, 0, (String)null, new String[]{"充值到我的账号", "充值到其他账号"});
                    menuForm.addComponentInCenter(MainCanvas.mc.menus[0], (byte)5);
                    MainCanvas.mc.baseForm.getCurrentFocusForm().setAboutForm(menuForm);
                    MainCanvas.mc.baseForm.setComponentFocus(menuForm);
                } else if ("menu".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                    if (MainCanvas.mc.texts[0].getLabel().trim().length() == 0) {
                        MainCanvas.setMessage(MainCanvas.mc.baseForm, "卡号不能为空");
                    } else if (MainCanvas.mc.texts[1].getLabel().trim().length() == 0) {
                        MainCanvas.setMessage(MainCanvas.mc.baseForm, "密码不能为空");
                    } else if (MainCanvas.mc.texts[2] != null && MainCanvas.mc.texts[2].getLabel().trim().length() == 0) {
                        MainCanvas.setMessage(MainCanvas.mc.baseForm, "请输入金额");
                    } else {
                        this.CardNum = MainCanvas.mc.texts[0].getLabel().trim();
                        this.CardPass = MainCanvas.mc.texts[1].getLabel().trim();
                        if (!this.isSZEnter) {
                            if (MainCanvas.mc.texts[2] != null) {
                                this.CardValue = MainCanvas.mc.texts[2].getLabel().trim();
                            } else {
                                this.CardValue = MainCanvas.mc.rbs[0].getChooseItem();
                            }
                        }

                        byte ss = MainCanvas.mc.menus[0].getCurrentPointer();
                        if (ss == 0) {
                            MainCanvas.mc.baseForm.getCurrentFocusForm().addAboutForm("pass", "是否要为自己的账号充值", (byte)2, 140, 0);
                        } else {
                            int l = 160;
                            int h = 180;
                            int x = 18;
                            MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm)null);
                            UIForm otherPssForm = new UIForm(0, 0, l, h, "other");
                            otherPssForm.setBackGround((byte)9);
                            UIRim frame = new UIRim(0, 0, l - 1, h - 1, (byte)4);
                            MainCanvas.mc.labels[5] = new UILabel(0, 0, 0, 0, "确定", 15718815, (byte)0, (byte)0);
                            MainCanvas.mc.labels[6] = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte)0, (byte)0);
                            MainCanvas.mc.labels[7] = new UILabel(14, x + 20, 200, 17, "您要充值的通行证:", 15718815, (byte)0, (byte)0);
                            MainCanvas.mc.labels[8] = new UILabel(14, x + 60, 200, 17, "确认您要充值的通行证:", 15718815, (byte)0, (byte)0);
                            MainCanvas.mc.labels[9] = new UILabel(14, x, 200, 17, "请输入:", 15718815, (byte)0, (byte)0);
                            MainCanvas.mc.texts[3] = new UIText(40, x + 40, 92, 0, 16, (byte)0, "");
                            MainCanvas.mc.texts[4] = new UIText(40, x + 80, 92, 0, 16, (byte)0, "");
                            otherPssForm.addComponent(frame);
                            otherPssForm.addComponent(MainCanvas.mc.texts[3]);
                            otherPssForm.addComponent(MainCanvas.mc.texts[4]);
                            otherPssForm.addComponentInCenter(MainCanvas.mc.labels[5], (byte)5);
                            otherPssForm.addComponentInCenter(MainCanvas.mc.labels[6], (byte)6);
                            otherPssForm.addComponent(MainCanvas.mc.labels[7]);
                            otherPssForm.addComponent(MainCanvas.mc.labels[8]);
                            otherPssForm.addComponent(MainCanvas.mc.labels[9]);
                            MainCanvas.mc.texts[3].setAroundComponent(MainCanvas.mc.texts[4], (byte)2);
                            otherPssForm.setComponentFocus(MainCanvas.mc.texts[3]);
                            MainCanvas.mc.baseForm.getCurrentFocusForm().setAboutForm(otherPssForm);
                            MainCanvas.mc.baseForm.setComponentFocus(otherPssForm);
                        }
                    }
                } else if ("msg".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                    MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm)null);
                } else if ("err".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                    MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm)null);
                } else if ("other".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                    if (!MainCanvas.mc.texts[3].getLabel().trim().equals(MainCanvas.mc.texts[4].getLabel().trim())) {
                        MainCanvas.mc.baseForm.getCurrentFocusForm().addAboutForm("err", "两次输入的账号不一致", (byte)1, 140, 0);
                    } else if (!"".equals(MainCanvas.mc.texts[3].getLabel().trim()) && !"".equals(MainCanvas.mc.texts[4].getLabel().trim())) {
                        this.chargePassport = MainCanvas.mc.texts[3].getLabel().trim();
                        this.addWaitInterface();
                        MainCanvas.ni.send(1342179840);
                    } else {
                        MainCanvas.mc.baseForm.getCurrentFocusForm().addAboutForm("err", "输入信息不能为空", (byte)1, 140, 0);
                    }
                } else if ("pass".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                    this.chargePassport = "";
                    this.addWaitInterface();
                    MainCanvas.ni.send(1342179840);
                } else if ("lucky".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                    this.addWaitInterface();
                    MainCanvas.ni.send(1342214144);
                } else if ("lshow".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                    MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm)null);
                    MainCanvas.mc.baseForm.getCurrentFocusForm().addAboutForm("buy", "是否要进入购买界面", (byte)2, 140, 0);
                } else if ("buy".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                    this.addWaitInterface();
                    chargeWhere = 2;
                    sendMommoth();
                }
            } else if (MainCanvas.isKeyPress(18)) {
                if ("menu".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                    MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm)null);
                } else if ("other".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                    MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm)null);
                } else if ("pass".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                    MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm)null);
                } else if ("lucky".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                    MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm)null);
                    MainCanvas.mc.baseForm.getCurrentFocusForm().addAboutForm("buy", "是否要进入购买界面", (byte)2, 140, 0);
                } else if ("buy".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                    this.CardValue = this.CardNum = this.CardPass = this.chargePassport = "";
                    setState((byte)-1);
                    MainCanvas.mc.releaseUI();
                } else if (MainCanvas.mc.baseForm.getCurrentFocusForm() == MainCanvas.mc.baseForm) {
                    this.CardValue = this.CardNum = this.CardPass = this.chargePassport = "";
                    if (this.isSZEnter) {
                        setState((byte)2);
                    } else {
                        setState((byte)20);
                    }

                    MainCanvas.mc.releaseUI();
                }
            } else if (MainCanvas.mc.actionInForm(cmd)) {
            }

        }
    }

    void parseChargeData(byte total, String[] tmp) {
        this.chargeData = new String[total][8];

        for(int i = 0; i < total; ++i) {
            this.chargeData[i] = Util.splitToken(tmp[i], '|');
        }

    }

    private static String getSMSCoin(String name, String game, String check) {
        if (HttpConn.sms_coin != null) {
            String coin = Util.replaceFirstStr(HttpConn.sms_coin, "[name]", name);
            coin = Util.replaceFirstStr(coin, "[game]", game);
            coin = Util.replaceFirstStr(coin, "[check]", check);
            if (coin.indexOf(64) > 0) {
                coin = coin.substring(0, coin.indexOf(64));
            }

            phoneNumber = HttpConn.getSMSNumber(HttpConn.sms_coin);
            return coin;
        } else {
            StringBuffer sb = new StringBuffer();
            return sb.append("AG").append(MainCanvas.mc.userName).append(".tianjie").append("." + sendMd5).toString();
        }
    }
}
