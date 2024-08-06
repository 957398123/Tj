//
// Source code recreated from MainEntry .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import javax.microedition.io.Connector;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.TextField;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

public class PCBindService implements CommandListener {
    public static PCBindService instance;
    public static int serviceType = -1;
    public static final int BIND_ADD = 0;
    public static final int BIND_DEL = 1;
    public static final int BIND_FIN = 2;
    public static String name = "";
    public static String password = "";
    static String address;
    public static boolean isSendOK = false;
    public static String phoneNumber = "";
    public Form commonForm = null;
    public TextField commonTextField = null;
    public TextField commontf = null;
    private Command commonOk = null;
    private Command commonBack = null;
    public static int bindState = 0;
    public static final int BIND_WAIT = 0;
    public static final int BIND_SHOW = 1;
    public static final int BIND_ERR = 2;
    static MessageConnection conn;
    static TextMessage msg;
    public static final String[] EXPLAINBIND = new String[]{"帐号绑定可以提高您帐号的安全性。绑定操作采用短信发送方式认证，如果您的帐号已绑定则不再执行绑定。", "解除绑定可以解除您已经绑定的帐号的绑定状态。本操作采用短信发送方式进行认证，必须使用原绑定的手机号发短信才能执行成功此操作。", "如果您的帐号已经绑定，此操作可以通过短信认证方式帮您找回遗忘的密码。只有原绑定的手机号发短信才能执行成功此操作。"};

    public PCBindService() {
    }

    public static PCBindService getInstance() {
        if (instance == null) {
            instance = new PCBindService();
        }

        return instance;
    }

    public void draw(Graphics g) {
        if (MainCanvas.mc.baseForm == null) {
            MainCanvas.mc.baseForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "");
            MainCanvas.mc.baseForm.setStyle((byte)0);
            MainCanvas.mc.rims[0] = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte)4);
            MainCanvas.mc.rims[1] = new UIRim(5, 5, 164, 25, (byte)2);

            for(byte i = 0; i < 2; ++i) {
                MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.rims[i]);
            }

            MainCanvas.mc.labels[0] = new UILabel(60, 12, 112, 0, "帐号绑定", 16316576, (byte)1, (byte)0);
            MainCanvas.mc.baseForm.addComponentInCenter(MainCanvas.mc.labels[0], (byte)2);
            UILabel label2;
            UILabel label3;
            switch (bindState) {
                case 0:
                    label3 = new UILabel(20, 50, 140, 0, "连接中，请稍候...", 15718815, (byte)0, (byte)0);
                    MainCanvas.mc.baseForm.addComponent(label3);
                    break;
                case 2:
                    label3 = new UILabel(20, 50, 140, 0, "请求失败或确认网络连接正常，请重试", 15718815, (byte)0, (byte)0);
                    MainCanvas.mc.baseForm.addComponent(label3);
                    label2 = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte)0, (byte)0);
                    MainCanvas.mc.baseForm.addComponentInCenter(label2, (byte)6);
                    break;
                default:
                    UILabel label1 = new UILabel(0, 0, 0, 0, "选择", 15718815, (byte)0, (byte)0);
                    String[] menu = new String[]{"帐号绑定", "解除绑定", "找回密码"};
                    MainCanvas.mc.menus[0] = new UIMenu(5, 32, 164, 58, (String)null, menu);
                    MainCanvas.mc.menus[0].setRimStyle((byte)0);
                    MainCanvas.mc.menus[0].setFlushType((byte)1);
                    label2 = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte)0, (byte)0);
                    MainCanvas.mc.baseForm.addComponentInCenter(label2, (byte)6);
                    MainCanvas.mc.textArea[0] = new UITextArea(5, 92, 164, 96, EXPLAINBIND[MainCanvas.mc.menus[0].getCurrentPointer()]);
                    MainCanvas.mc.textArea[0].setColor(15849885);
                    MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.menus[0]);
                    MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.textArea[0]);
                    MainCanvas.mc.baseForm.addComponentInCenter(label1, (byte)5);
            }

            MainCanvas.mc.baseForm.setFocus(true);
        }

        MainCanvas.mc.baseForm.draw(g);
    }

    public void action() {
        if (MainCanvas.mc.baseForm != null) {
            if (bindState != 0) {
                UIComponent cmd = MainCanvas.mc.baseForm.getCommand();
                if ("msg".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm)null);
                } else if ("err".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                    MainCanvas.mc.setState((byte)4);
                    MainCanvas.mc.releaseUI();
                } else if (!MainCanvas.isKeyPress(17) && !MainCanvas.isKeyPress(14) && !MainCanvas.isKeyPress(53)) {
                    if (MainCanvas.isKeyPress(18)) {
                        if (MainCanvas.mc.baseForm.getCurrentFocusForm() == MainCanvas.mc.baseForm) {
                            if (MainCanvas.isInGame) {
                                MainCanvas.mc.setRightMenuSubState(-1);
                                MainCanvas.mc.releaseUI();
                            } else {
                                MainCanvas.mc.setState((byte)4);
                                MainCanvas.mc.releaseUI();
                            }
                        }
                    } else if (MainCanvas.mc.actionInFormMenu(cmd)) {
                        MainCanvas.mc.textArea[0].setString(EXPLAINBIND[MainCanvas.mc.menus[0].getCurrentPointer()]);
                    }
                } else if (HttpConn.sms_coin != null && !"".equals(HttpConn.sms_coin.trim())) {
                    serviceType = MainCanvas.mc.menus[0].getCurrentPointer();
                    if (MainCanvas.isInGame && serviceType == 0 && HttpConn.getBindDetail()) {
                        MainCanvas.mc.baseForm.addAboutForm("msg", "您的帐号已经绑定", (byte)1, 210, 0);
                    } else {
                        this.initBindForm(serviceType);
                    }
                } else {
                    MainCanvas.mc.baseForm.addAboutForm("err", "您没有获取到绑定信息，请返回主菜单", (byte)1, 210, 0);
                }

            }
        }
    }

    public static void initSend(String content, String phoneNumber) {
        try {
            if (conn == null) {
                address = "sms://" + phoneNumber;
                conn = (MessageConnection)Connector.open(address);
                msg = (TextMessage)conn.newMessage("text");
            }

            msg.setPayloadText(content);
            conn.send(msg);
            isSendOK = true;
        } catch (Exception var3) {
            Exception e = var3;
            e.printStackTrace();
            isSendOK = false;
        }

    }

    public void initBindForm(int type) {
        this.commonForm = null;
        this.commonOk = null;
        this.commonBack = null;
        this.commonTextField = null;
        this.commontf = null;
        switch (type) {
            case 0:
                this.commonForm = new Form("帐号绑定");
                break;
            case 1:
                this.commonForm = new Form("解除绑定");
                break;
            case 2:
                this.commonForm = new Form("找回密码");
        }

        String tmpStr = "此操作将以短信形式发送";
        this.commonOk = new Command("发送", 4, 2);
        this.commonBack = new Command("返回", 2, 2);
        if (tmpStr != null && !"".equals(tmpStr.trim())) {
            this.commonForm.append(tmpStr);
        }

        this.commonTextField = new TextField("请输入您的帐号", "", 16, 0);
        this.commonForm.append(this.commonTextField);
        if (type < 2) {
            this.commontf = new TextField("请输入您的密码", "", 16, 0);
            this.commonForm.append(this.commontf);
        }

        this.commonForm.addCommand(this.commonOk);
        this.commonForm.addCommand(this.commonBack);
        this.commonForm.setCommandListener(this);
        MainCanvas.mc.aMidlet.display.setCurrent(this.commonForm);
        this.exitForm();
    }

    public void exitForm() {
        if (MainCanvas.isInGame) {
            MainCanvas.mc.setRightMenuSubState(8);
            MainCanvas.mc.setUIHelpState((byte)2);
        } else {
            MainCanvas.mc.setState((byte)29);
        }

    }

    public void commandAction(Command command, Displayable displayable) {
        if (command == this.commonOk) {
            name = this.commonTextField.getString();
            if (this.commontf == null) {
                password = "1";
            } else {
                password = this.commontf.getString();
            }

            if (!name.trim().equals("") && !password.trim().equals("")) {
                Thread initsend = new Thread() {
                    public void run() {
                        PCBindService.initSend(PCBindService.createContent(PCBindService.serviceType), PCBindService.phoneNumber);
                        MainCanvas.mc.baseForm.addAboutForm("msg", PCBindService.isSendOK ? "绑定命令已下发，请及时查收短信查看结果" : "发送失败", (byte)1, 210, 0);
                        if (PCBindService.isSendOK) {
                            HttpConn.setBindDetail("true");
                        }

                    }
                };
                this.exitForm();
                MainCanvas.mc.baseForm.addAboutForm("waiting", "请稍候…", (byte)0, MainCanvas.screenW - 30, 0);
                MainCanvas.mc.aMidlet.display.setCurrent(MainCanvas.mc);
                initsend.start();
                this.releaseForm();
            }
        } else if (command == this.commonBack) {
            this.exitForm();
            MainCanvas.mc.baseForm.setAboutForm((UIForm)null);
            MainCanvas.mc.aMidlet.display.setCurrent(MainCanvas.mc);
            this.releaseForm();
        }

    }

    public void releaseForm() {
        isSendOK = false;
        this.commontf = null;
        this.commonTextField = null;
        this.commonForm = null;
    }

    public static String createContent(int type) {
        String content = null;
        switch (serviceType) {
            case 0:
                content = getSMSBond(name, password);
                break;
            case 1:
                content = getSMSDel(name, password);
                break;
            case 2:
                content = getSMSSeek(name);
        }

        return content;
    }

    private static String getSMSBond(String name, String psw) {
        if (HttpConn.sms_bond != null) {
            String bond = Util.replaceFirstStr(HttpConn.sms_bond, "[name]", name);
            bond = Util.replaceFirstStr(bond, "[password]", psw);
            if (bond.indexOf(64) > 0) {
                bond = bond.substring(0, bond.indexOf(64));
            }

            phoneNumber = HttpConn.getSMSNumber(HttpConn.sms_bond);
            return bond;
        } else {
            return createContent("AGB", 0);
        }
    }

    private static String getSMSSeek(String name) {
        if (HttpConn.sms_seek != null) {
            String seek = Util.replaceFirstStr(HttpConn.sms_seek, "[name]", name);
            if (seek.indexOf(64) > 0) {
                seek = seek.substring(0, seek.indexOf(64));
            }

            phoneNumber = HttpConn.getSMSNumber(HttpConn.sms_seek);
            return seek;
        } else {
            return createContent("AGC", 2);
        }
    }

    private static String getSMSDel(String name, String psw) {
        if (HttpConn.sms_del != null) {
            String del = Util.replaceFirstStr(HttpConn.sms_del, "[name]", name);
            del = Util.replaceFirstStr(del, "[password]", psw);
            if (del.indexOf(64) > 0) {
                del = del.substring(0, del.indexOf(64));
            }

            phoneNumber = HttpConn.getSMSNumber(HttpConn.sms_del);
            return del;
        } else {
            return createContent("AGD", 1);
        }
    }

    public static String createContent(String serviceCode, int type) {
        StringBuffer sb = new StringBuffer();
        if (type < 2) {
            sb.append(serviceCode).append(":").append(name).append(":").append(password);
        } else {
            sb.append(serviceCode).append(":").append(name);
        }

        return sb.toString().trim();
    }
}
