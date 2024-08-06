
import javax.microedition.lcdui.Graphics;

public class PCMarriage {

    public static boolean isMetemOk = false;
    private static PCMarriage m_instance = null;

    public static PCMarriage getInstance() {
        if (m_instance == null) {
            m_instance = new PCMarriage();
        }
        return m_instance;
    }

    public void drawMetempsychosis(Graphics g) {
        if (MainCanvas.mc.baseForm == null) {
            MainCanvas.mc.baseForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "");
            MainCanvas.mc.baseForm.setStyle((byte) 0);
            MainCanvas.mc.rims[0] = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte) 4);
            MainCanvas.mc.rims[1] = new UIRim(5, 5, 164, 25, (byte) 2);
            MainCanvas.mc.labels[0] = new UILabel(60, 12, 112, 0, "转生", 16316576, (byte) 1, (byte) 0);
            UILabel label1 = new UILabel(0, 0, 0, 0, "选择", 15718815, (byte) 0, (byte) 0);
            UILabel label2 = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 0, (byte) 0);
            MainCanvas.mc.menus[0] = new UIMenu(5, 32, 164, 158, null, MetempsyshosisType);
            MainCanvas.mc.menus[0].setRimStyle((byte) 0);
            MainCanvas.mc.menus[0].setFlushType((byte) 1);
            MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.menus[0]);
            byte i;
            for (i = 0; i < 2; i = (byte) (i + 1)) {
                MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.rims[i]);
            }
            MainCanvas.mc.baseForm.addComponentInCenter(MainCanvas.mc.labels[0], (byte) 2);
            MainCanvas.mc.baseForm.addComponentInCenter(label1, (byte) 5);
            MainCanvas.mc.baseForm.addComponentInCenter(label2, (byte) 6);
            MainCanvas.mc.baseForm.setFocus(true);
        }
        MainCanvas.mc.baseForm.draw(g);
    }

    public void keyInMetempsyshosis() {
        if (MainCanvas.mc.baseForm == null) {
            return;
        }
        UIComponent cmd = MainCanvas.mc.baseForm.getCommand();
        if (MainCanvas.isKeyPress(17)
                || MainCanvas.isKeyPress(14)) {
            if (MainCanvas.mc.baseForm.getCurrentFocusForm() == MainCanvas.mc.baseForm) {
                this.selectMetempsyshosis = MainCanvas.mc.menus[0].getCurrentPointer();
                MainCanvas.mc.baseForm.addAboutForm("message", "确认后您将进行" + MetempsyshosisType[this.selectMetempsyshosis] + "，转生相关内容请询问“ " + ObjManager.currentTarget.name + "”。", (byte) 2, 210, 0);
            } else if ("message".equals(MainCanvas.mc.baseForm
                    .getCurrentFocusForm().getName())) {
                MainCanvas.mc.baseForm.setAboutForm(null);
                MainCanvas.ni.send(1073743360);
                MainCanvas.startWait(MainCanvas.mc.baseForm);
            } else if ("msg".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                MainCanvas.mc.baseForm.setAboutForm(null);
            }
        } else if (MainCanvas.isKeyPress(18)) {
            if (MainCanvas.mc.baseForm.getCurrentFocusForm() == MainCanvas.mc.baseForm) {
                MainCanvas.mc.setNPCSubState((byte) 0);
                MainCanvas.mc.releaseUI();
            } else if ("message".equals(MainCanvas.mc.baseForm.getCurrentFocusForm().getName())) {
                MainCanvas.mc.baseForm.setAboutForm(null);
            }
        } else if (MainCanvas.mc.actionInForm(cmd)) {
        }
    }
    static String[] MetempsyshosisType = null;
    byte selectMetempsyshosis = 0;

    public static void parse(int commID, byte[] data) {
        String hexID = Integer.toHexString(commID);
        System.out.println("[处理命令]:命令ID:0x" + hexID + ",命令类型：PCMarriage");
        byte reason;
        String message;
        int id1, id2;
        GameObj o1, o2;
        byte kinds;
        int i;
        byte s;
        ByteArray execDataIn = new ByteArray(data);
        switch (commID) {
            case 1073742208:
                reason = execDataIn.readByte();
                message = "";
                switch (reason) {
                    case 0:
                        message = "你已经结过婚了！";
                        break;
                    case 1:
                        message = "队伍中只能有两个人!";
                        break;
                    case 2:
                        message = "你们得两个人组队才能申请结婚!";
                        break;
                    case 3:
                        message = "你的朋友下线了!";
                        break;
                    case 4:
                        message = "我看不见你的朋友啊!";
                        break;
                    case 5:
                        message = "你们两个性别一样啊!";
                        break;
                    case 6:
                        message = "你没到法定结婚等级!";
                        break;
                    case 7:
                        message = "你朋友没到法定结婚等级!";
                        break;
                    case 8:
                        message = "很遗憾,你朋友已经结过婚了!";
                        break;
                    case 9:
                        message = "想结婚的话需要队长来申请!";
                        break;
                    case 10:
                        message = "你的钱不够手续费，赶快去想辙吧!";
                        break;
                    case 11:
                        message = "你没带结婚申请书啊!";
                        break;
                    case 12:
                        message = "你朋友没带结婚申请书啊!";
                        break;
                    case 13:
                        message = "你需要先把你的朋友加为好友!";
                        break;
                    case 14:
                        message = "你需要让你的朋友加你为好友!";
                        break;
                    case 15:
                        message = "恭喜你们已经成为夫妻了!";
                        break;
                    default:
                        message = "结婚失败!";
                        break;
                }
                if (MainCanvas.mc.baseForm != null) {
                    MainCanvas.mc.baseForm.setMessage(message, true);
                    break;
                }
                PCChat.addChatScreen((byte) 7, message);
                break;
            case 1073742464:
                reason = execDataIn.readByte();
                message = "";
                switch (reason) {
                    case 0:
                        message = "你还没结婚呢!";
                        break;
                    case 1:
                        message = "离婚的话队伍中只能是你们夫妻俩!";
                        break;
                    case 2:
                        message = "离婚的话需要你们夫妻组队!";
                        break;
                    case 3:
                        message = "你的另一半不在线!";
                        break;
                    case 4:
                        message = "我看不见你的另一半!";
                        break;
                    case 5:
                        message = "你们两个人没有婚姻关系!";
                        break;
                    case 6:
                        message = "好了!你们没有任何关系了!";
                        clearWife();
                        break;
                    default:
                        message = "离婚失败!";
                        break;
                }
                if (MainCanvas.mc.baseForm != null) {
                    MainCanvas.mc.baseForm.setMessage(message, true);
                    break;
                }
                PCChat.addChatScreen((byte) 7, message);
                break;
            case 1073742720:
                reason = execDataIn.readByte();
                message = "";
                switch (reason) {
                    case 0:
                        message = "你还没结婚呢";
                        break;
                    case 1:
                        message = "你没有休书不能强制离婚!";
                        break;
                    case 2:
                        message = "强制离婚成功!";
                        clearWife();
                        break;
                    default:
                        message = "强制离婚失败!";
                        break;
                }
                if (MainCanvas.mc.baseForm != null) {
                    MainCanvas.mc.baseForm.setMessage(message, true);
                    break;
                }
                PCChat.addChatScreen((byte) 7, message);
                break;
            case 1073742976:
                id1 = execDataIn.readInt();
                id2 = execDataIn.readInt();
                o1 = null;
                o2 = null;
                o1 = ObjManager.getObj(id1);
                o2 = ObjManager.getObj(id2);
                if (o1 != null) {
                    SpecialManager.getInstance().addSpecial(o1, 40);
                }
                if (o2 != null) {
                    SpecialManager.getInstance().addSpecial(o2, 40);
                }
                if (id1 == (Player.getInstance()).objID) {
                    if (o2 != null) {
                        o2.isPlayerWife = 1;
                    }
                    break;
                }
                if (id2 == (Player.getInstance()).objID
                        && o1 != null) {
                    o1.isPlayerWife = 1;
                }
                break;
            case 1073743232:
                kinds = execDataIn.readByte();
                if (kinds <= 0) {
                    message = "命令请求失败，请重试！";
                    if (MainCanvas.mc.baseForm != null) {
                        MainCanvas.setMessage(MainCanvas.mc.baseForm, message);
                        break;
                    }
                    PCChat.addChatScreen((byte) 7, message);
                    break;
                }
                MetempsyshosisType = new String[kinds];
                for (i = 0; i < 3; i++) {
                    MetempsyshosisType[i] = execDataIn.readUTF();
                }
                MainCanvas.mc.setNPCSubState((byte) 62);
                MainCanvas.mc.releaseUI();
                break;
            case 1073743488:
                s = execDataIn.readByte();
                message = execDataIn.readUTF();
                if (s == 1) {
                    for (int j = 0; j < 9; j++) {
                        Player.defineKey(j, -1);
                    }
                    Util.saveRecord(Player.userDefinedSkills, (Player.getInstance()).objID + "sc");
                    isMetemOk = true;
                    break;
                }
                if (MainCanvas.mc.baseForm != null) {
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, message);
                    break;
                }
                PCChat.addChatScreen((byte) 7, message);
                break;
        }
    }

    public static byte[] compress(int commID) {
        ByteArray execDataOut = new ByteArray();
        switch (commID) {
            case 1073743360:
                execDataOut.writeByte((getInstance()).selectMetempsyshosis + 1);
                break;
        }
        return execDataOut.toByteArray();
    }

    public static void clearWife() {
        for (int i = 0; i < ObjManager.vectorObj.size(); i++) {
            GameObj go = (GameObj) ObjManager.vectorObj.elementAt(i);
            go.isPlayerWife = 0;
        }
    }
}
