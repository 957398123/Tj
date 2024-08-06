
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.TextField;

public class PCArena implements CommandListener {

    public static final int C_ARENA_RENAME = 285219328;
    private final int S_ARENA_RENAME = 285219456;
    private final int C_ARENA_RENAME_OK = 285219584;
    private final int S_ARENA_RENAME_OK = 285219712;
    public static byte isArena = 0;
    private final int S_ARENA_WAKE = 285220992;
    private final int C_ARENA_CREATE_TEAM = 285212928;
    private final int C_ARENA_DISBAND_TEAM = 285213184;
    private final int C_ARENA_QUIT_TEAM = 285213440;
    private final int C_ARENA_INVITE = 285213696;
    private final int C_ARENA_INVITE_RESPONSE = 285213952;
    private final int C_ARENA_VIEW_SCORE = 285214464;
    private final int C_ARENA_VIEW_RANK = 285214720;
    private final int C_ARENA_KICK_TEAM = 285214976;
    private final int C_ARENA_KICK_MEMBER = 285215488;
    private final int C_ARENA_VIEW_TEAM = 285215232;
    private final int C_ARENA_ENTER_LINE = 285217024;
    private final int C_ARENA_ACCEPT_LINE = 285217280;
    private final int C_ARENA_QUIT_LINE = 285217536;
    private final int S_ARENA_CREATE_TEAM = 285213056;
    private final int S_ARENA_DISBAND_TEAM = 285213312;
    private final int S_ARENA_QUIT_TEAM = 285213568;
    private final int S_ARENA_INVITE = 285213824;
    private final int S_ARENA_INVITE_RESPONSE = 285214080;
    private final int S_ARENA_VIEW_SCORE = 285214592;
    private final int S_ARENA_VIEW_RANK = 285214848;
    private final int S_ARENA_KICK_TEAM = 285215104;
    private final int S_ARENA_KICK_MEMBER = 285215616;
    private final int S_ARENA_ENTER_LINE = 285217152;
    private final int S_ARENA_ACCEPT = 285217408;
    private final int S_ARENA_QUIT_LINE = 285217664;
    private final int S_ARENA_VIEW_TEAM = 285215360;
    private final int C_ARENA_GIVE_CAPTAIN = 285217792;
    private final int S_ARENA_GIVE_CAPTAIN = 285217920;
    private final int C_ARENA_GIVE_TEAM_CAPTAIN = 285218048;
    private final int S_ARENA_GIVE_TEAM_CAPTAIN = 285218176;
    private final int C_RETURN_SERVER = 285218304;
    private final int S_RETURN_SERVER = 285218432;
    private final int C_RETURN_SERVER_OK = 285218560;
    private final int S_RETURN_SERVER_OK = 285218688;
    private final int C_RETURN_SERVER_OVER = 285218816;
    private final byte STATE_NONE = -1;
    private final byte STATE_VIEW_RANK = 1;
    private final byte STATE_CREATE_TEAM = 2;
    private final byte STATE_INVITE = 3;
    private final byte STATE_INVITE_RESPONSE = 4;
    private final byte STATE_ACCEPT_LINE = 5;
    private final byte STATE_KICK_TEAM = 6;
    private final byte STATE_SHOW_MESSAGE = 7;
    private final byte STATE_DISBAND_TEAM = 9;
    private final byte STATE_VIEW_TEAM = 10;
    private final byte STATE_GIVE_CAPTAIN = 11;
    private final byte STATE_RETURN_SERVER = 12;
    private final byte STATE_RETURN_SERVER_OK = 13;
    private final byte STATE_RETURN_SERVER_OVER = 14;
    private final byte STATE_ENTER_LINE = 15;
    private final byte STATE_RETURN_CHARGES = 16;
    private final byte ID_ARENA_RENAME = 17;
    private final byte ID_CLAN_RENAME = 18;
    private byte state = -1;
    private static byte returnServerType = 0;
    private final byte ACCEPT = 1;
    private final byte REFUSED = 0;
    private final byte BUSY = 2;
    private String name = "";
    public byte response = -1;
    private byte acceptEnterLine = 1;
    private byte arenaType = 1;
    private static short time = 0;
    private static int maxTime = -1;
    private static boolean needAutoResponse = false;
    private String kickName;
    private boolean isRank = false;
    private static PCArena instance;
    private Form nameForm;
    private TextField textField;
    private Command ok;
    private Command back;
    private UIForm oneSubForm;
    private UIForm twoSubForm;
    UITable oneSubTable;
    UITable twoSubTable;
    private UIRadioButton returnServerRadioButton;
    private int inivetedID;

    public static PCArena getInstance() {
        if (instance == null) {
            instance = new PCArena();
        }
        return instance;
    }

    public static void releaseInstance() {
        instance = null;
    }

    public void keyPressed() {
        UIComponent cmd;
        switch (this.state) {
            case 10:
                if (MainCanvas.isKeyPress(11)) {
                    this.twoSubForm.setDirectionFocus(11);
                    break;
                }
                if (MainCanvas.isKeyPress(13)) {
                    this.twoSubForm.setDirectionFocus(13);
                    break;
                }
                if (MainCanvas.isKeyPress(18)) {
                    this.twoSubForm = null;
                    this.twoSubTable = null;
                    this.isRank = true;
                    this.state = 1;
                }
                break;
            case 1:
                if (MainCanvas.isKeyPress(11)) {
                    this.oneSubForm.setDirectionFocus(11);
                    break;
                }
                if (MainCanvas.isKeyPress(13)) {
                    this.oneSubForm.setDirectionFocus(13);
                    break;
                }
                if (MainCanvas.isKeyPress(18)) {
                    clear();
                    break;
                }
                if (MainCanvas.isKeyPressOk()
                        && this.isRank) {
                    MainCanvas.startWait(MainCanvas.mc.baseForm
                            .getCurrentFocusForm());
                    MainCanvas.ni.send(285215232);
                }
                break;
            case 3:
                if (MainCanvas.isKeyPress(14)
                        || MainCanvas.isKeyPress(17)) {
                    if (MainCanvas.mc.tables[0].getCurrentPointer() != 0) {
                        this.name = MainCanvas.mc.tables[0].getCurentItem();
                        clear();
                        MainCanvas.ni.send(285213696);
                        break;
                    }
                    createForm((byte) 3);
                    break;
                }
                if (MainCanvas.isKeyPress(18)) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    clear();
                    break;
                }
                if (MainCanvas.isKeyPress(11)) {
                    int count = MainCanvas.mc.tables[0].getCurrentPointer() - 1;
                    int max = MainCanvas.mc.tables[0].getItemNumber();
                    if (count < 0) {
                        count = max - 1;
                    }
                    MainCanvas.mc.tables[0].setCurrentPointer(count);
                    break;
                }
                if (MainCanvas.isKeyPress(13)) {
                    int count = MainCanvas.mc.tables[0].getCurrentPointer() + 1;
                    int max = MainCanvas.mc.tables[0].getItemNumber();
                    if (count == max) {
                        count = 0;
                    }
                    MainCanvas.mc.tables[0].setCurrentPointer(count);
                }
                break;
            case 6:
            case 11:
                if (MainCanvas.isKeyPress(14)
                        || MainCanvas.isKeyPress(17)) {
                    this.kickName = MainCanvas.mc.tables[0].getCurentItem();
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    if (this.state == 6) {
                        MainCanvas.ni.send(285214976);
                    } else if (this.state == 11) {
                        MainCanvas.ni.send(285218048);
                    }
                    clear();
                    MainCanvas.startWait(MainCanvas.mc.baseForm);
                    break;
                }
                if (MainCanvas.isKeyPress(18)) {
                    MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
                    clear();
                    break;
                }
                if (MainCanvas.isKeyPress(11)) {
                    int count = MainCanvas.mc.tables[0].getCurrentPointer() - 1;
                    int max = MainCanvas.mc.tables[0].getItemNumber();
                    if (count < 0) {
                        count = max - 1;
                    }
                    MainCanvas.mc.tables[0].setCurrentPointer(count);
                    break;
                }
                if (MainCanvas.isKeyPress(13)) {
                    int count = MainCanvas.mc.tables[0].getCurrentPointer() + 1;
                    int max = MainCanvas.mc.tables[0].getItemNumber();
                    if (count == max) {
                        count = 0;
                    }
                    MainCanvas.mc.tables[0].setCurrentPointer(count);
                }
                break;
            case 9:
                if (MainCanvas.isKeyPress(18)) {
                    clear();
                    break;
                }
                if (MainCanvas.isKeyPress(17)
                        || MainCanvas.isKeyPress(14)) {
                    clear();
                    MainCanvas.startWait(MainCanvas.mc.baseForm
                            .getCurrentFocusForm());
                    MainCanvas.ni.send(285213184);
                }
                break;
            case 4:
                if (MainCanvas.isKeyPress(17)
                        || MainCanvas.isKeyPress(14)) {
                    MainCanvas.mc.topForm = null;
                    this.response = 1;
                    getInstance().getClass();
                    MainCanvas.ni.send(285213952);
                    break;
                }
                if (MainCanvas.isKeyPress(18)) {
                    MainCanvas.mc.topForm = null;
                    this.response = 0;
                    getInstance().getClass();
                    MainCanvas.ni.send(285213952);
                }
                break;
            case 7:
                if (MainCanvas.isKeyPress(17)
                        || MainCanvas.isKeyPress(14)) {
                    if (MainCanvas.mc.topForm != null) {
                        MainCanvas.mc.topForm = null;
                        break;
                    }
                    clear();
                }
                break;
            case 12:
                cmd = MainCanvas.mc.baseForm.getCommand();
                if (MainCanvas.isKeyPressOk()) {
                    this.state = 13;
                    UIForm form = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "");
                    UIRim rimFrame = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte) 4);
                    UIRim rimTitle = new UIRim(0, 10, 163, 20, (byte) 7);
                    UILabel lblTitle = new UILabel(0, 12, 0, 0, "转服申请", 15718814, (byte) 1, (byte) 0);
                    UIRim rimDown = new UIRim(0, 32, 163, 158, (byte) 0);
                    UILabel info1 = new UILabel(0, 34, 160, 0, "您要把该角色转到" + this.twoSubTable.getCurentItem() + "服务器的", 15718814, (byte) 1, (byte) 0);
                    this.returnServerRadioButton = new UIRadioButton(60, info1.positionY + info1.height, 0, 0, "", (byte) 2);
                    String[] tempStr = {"第1个", "第2个", "第3个"};
                    for (int i = 0; i < tempStr.length; i++) {
                        this.returnServerRadioButton.addItems(tempStr[i]);
                    }
                    this.returnServerRadioButton.setAdvanceStyle();
                    this.returnServerRadioButton.setAdvanceColor(15718814);
                    UILabel info2 = new UILabel(0, this.returnServerRadioButton.positionY + this.returnServerRadioButton.height, 160, 0, "角色位置", 15718814, (byte) 1, (byte) 0);
                    form.addComponent(rimFrame);
                    form.addComponentInCenter(rimTitle, (byte) 2);
                    form.addComponentInCenter(rimDown, (byte) 2);
                    form.addComponentInCenter(lblTitle, (byte) 2);
                    form.addComponentInCenter(info1, (byte) 2);
                    form.addComponent(this.returnServerRadioButton);
                    form.addComponentInCenter(info2, (byte) 2);
                    UILabel lblCheck = new UILabel(0, 0, 0, 0, "确定", 15718814, (byte) 1, (byte) 0);
                    UILabel lblCancel = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 1, (byte) 0);
                    form.addComponentInCenter(lblCheck, (byte) 5);
                    form.addComponentInCenter(lblCancel, (byte) 6);
                    form.setFocus(true);
                    MainCanvas.mc.baseForm.getCurrentFocusForm().setAboutForm(form);
                    break;
                }
                if (MainCanvas.isKeyPress(18)) {
                    this.state = 16;
                    MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm()
                            .setAboutForm((UIForm) null);
                    break;
                }
                if (MainCanvas.mc.actionInForm(cmd));
                break;
            case 13:
                cmd = MainCanvas.mc.baseForm.getCommand();
                if (MainCanvas.isKeyPressOk()) {
                    MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm()
                            .setAboutForm((UIForm) null);
                    MainCanvas.startWait(MainCanvas.mc.baseForm
                            .getCurrentFocusForm());
                    MainCanvas.ni.send(285218560);
                    break;
                }
                if (MainCanvas.isKeyPress(18)) {
                    MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm()
                            .setAboutForm((UIForm) null);
                    this.state = 12;
                    break;
                }
                if (MainCanvas.mc.actionInForm(cmd));
                break;
            case 14:
                if ("true".equals(MainCanvas.mc.baseForm.getCurrentFocusForm()
                        .getName())) {
                    if (MainCanvas.isKeyPressOk()) {
                        MainCanvas.startWait(MainCanvas.mc.baseForm
                                .getCurrentFocusForm());
                        MainCanvas.ni.send(285218816);
                        break;
                    }
                    if (MainCanvas.isKeyPress(18)) {
                        MainCanvas.mc.baseForm.getCurrentFocusForm()
                                .getParentForm().setAboutForm((UIForm) null);
                        this.state = 12;
                    }
                    break;
                }
                if (MainCanvas.isKeyPressOk()) {
                    MainCanvas.mc.baseForm.getCurrentFocusForm()
                            .getParentForm().setAboutForm((UIForm) null);
                    this.state = 12;
                }
                break;
            case 16:
                cmd = MainCanvas.mc.baseForm.getCommand();
                if (MainCanvas.isKeyPress(14)
                        || MainCanvas.isKeyPress(17)) {
                    returnServerType = (byte) this.oneSubTable.getCurrentPointer();
                    MainCanvas.startWait(MainCanvas.mc.baseForm
                            .getCurrentFocusForm());
                    MainCanvas.ni.send(285218304);
                    break;
                }
                if (MainCanvas.isKeyPress(18)) {
                    clear();
                    break;
                }
                if (MainCanvas.mc.actionInForm(cmd));
                break;
            case 15:
                if (MainCanvas.isKeyPress(11)) {
                    this.oneSubForm.setDirectionFocus(11);
                    break;
                }
                if (MainCanvas.isKeyPress(13)) {
                    this.oneSubForm.setDirectionFocus(13);
                    break;
                }
                if (MainCanvas.isKeyPress(14)
                        || MainCanvas.isKeyPress(17)) {
                    this.arenaType = (byte) this.oneSubTable.getCurrentPointer();
                    MainCanvas.startWait(MainCanvas.mc.baseForm
                            .getCurrentFocusForm());
                    MainCanvas.ni.send(285217024);
                    break;
                }
                if (MainCanvas.isKeyPress(18)) {
                    clear();
                }
                break;
        }
    }

    public void draw(Graphics g) {
        if (this.state == 1 || this.state == 15) {
            this.oneSubForm.draw(g);
        } else if (this.state == 10) {
            this.twoSubForm.draw(g);
        } else {
            MainCanvas.mc.baseForm.draw(g);
        }
    }

    private void createForm(byte state) {
        this.state = state;
        if (this.nameForm == null) {
            String tmpName = this.name;
            if (this.name.length() > 6) {
                tmpName = this.name.substring(0, 6);
            }
            if (state == 2) {
                this.nameForm = new Form("战队名称");
                this.textField = new TextField("输入战队名称", "", 6, 0);
            } else if (state == 3) {
                this.nameForm = new Form("玩家名字");
                this.textField = new TextField("输入您要邀请加入战队的玩家的名字", "", 6, 0);
            } else if (state == 17) {
                this.nameForm = new Form("竞技场改名");
                this.textField = new TextField("输入您要修改后的战队名字", tmpName, 6, 0);
            } else if (state == 18) {
                this.nameForm = new Form("氏族改名");
                this.textField = new TextField("输入您要修改后的氏族名字", tmpName, 6, 0);
            }
            this.ok = new Command("确定", 4, 2);
            this.back = new Command("返回", 2, 2);
            this.nameForm.append((Item) this.textField);
            this.nameForm.addCommand(this.ok);
            this.nameForm.addCommand(this.back);
            this.nameForm.setCommandListener(this);
            MainCanvas.mc.aMidlet.display.setCurrent((Displayable) this.nameForm);
        }
    }

    public void commandAction(Command command, Displayable display) {
        if (command == this.ok) {
            this.name = this.textField.getString().trim();
            if (this.name == null || "".equals(this.name)) {
                showMessage("名字不能为空");
            } else if (Util.checkLegal(this.name, (byte) 6, this.nameForm, false)) {
                if (this.state == 2) {
                    MainCanvas.ni.send(285212928);
                } else if (this.state == 3) {
                    MainCanvas.ni.send(285213696);
                } else if (this.state == 17) {
                    MainCanvas.startWait(MainCanvas.mc.baseForm.getCurrentFocusForm());
                    MainCanvas.ni.send(285219584);
                } else if (this.state == 18) {
                    MainCanvas.startWait(MainCanvas.mc.baseForm.getCurrentFocusForm());
                    MainCanvas.ni.send(285219584);
                }
            } else {
                showMessage("名字不合法");
            }
        } else if (command == this.back
                && this.state != 3) {
            stopWait();
            clear();
        }
        this.nameForm = null;
        this.textField = null;
        this.ok = null;
        this.back = null;
        MainCanvas.mc.aMidlet.display.setCurrent((Displayable) MainCanvas.mc);
    }

    private void quitTeam() {
        MainCanvas.ni.send(285213440);
    }

    private void createResponseForm(String msg) {
        this.state = 4;
        UIForm tempFrom = UIForm.makeAboutForm("arenaInvite", msg, "接受", "拒绝", MainCanvas.screenW - 22);
        setArenaForm(tempFrom);
    }

    private void createEnterConfirmForm(String message, byte arenaType, short arenaTime) {
        this.arenaType = arenaType;
        if (arenaTime > 0) {
            maxTime = arenaTime;
            time = 0;
            needAutoResponse = true;
        }
        this.state = 5;
        UIForm tempFrom = UIForm.makeAboutForm("arena", message, "", "", MainCanvas.screenW - 22);
        setArenaForm(tempFrom);
    }

    private void showMessage(String msg) {
        stopWait();
        this.state = 7;
        UIForm tempFrom = UIForm.makeAboutForm("arena", msg, "确定", "", MainCanvas.screenW - 22);
        setArenaForm(tempFrom);
    }

    private void setArenaForm(UIForm aboutForm) {
        if (MainCanvas.mc.baseForm != null) {
            MainCanvas.mc.setNPCSubState((byte) 34);
            MainCanvas.mc.baseForm.setAboutForm(aboutForm);
        } else {
            if (MainCanvas.mc.topForm != null) {
                MainCanvas.mc.topForm = null;
            }
            MainCanvas.mc.topForm = aboutForm;
            aboutForm.setFormXY(MainCanvas.screenW - aboutForm.width >> 1, MainCanvas.screenH - aboutForm.height >> 1);
        }
    }

    private UIForm createRankForm(String title, String[] str, boolean isRank, byte numTable) {
        UIForm formRank = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "");
        UIRim rimFrame = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte) 4);
        UIRim rimTitle = new UIRim(0, 13, 163, 20, (byte) 7);
        UILabel lblTitle = new UILabel(0, rimTitle.positionY + 8, 0, 0, title, 15718814, (byte) 1, (byte) 0);
        UIRim rimDown = new UIRim(0, 42, 163, 146, (byte) 0);
        byte count = (byte) str.length;
        byte n = count;
        if (n < 1) {
            n = 1;
        }
        UITable table = new UITable(0, 44, 159, 128, n, 1, (n > 8) ? 8 : n, (byte) 0, (byte) 3);
        table.setSingleWH((short) -97, (byte) 18, false);
        for (int i = 0; i < str.length; i++) {
            table.addItem(str[i], 15718814);
        }
        UILabel lblCancel = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 1, (byte) 0);
        this.isRank = isRank;
        formRank.addComponent(rimFrame);
        formRank.addComponent(rimTitle);
        formRank.addComponentInCenter(rimDown, (byte) 2);
        formRank.addComponentInCenter(rimTitle, (byte) 2);
        formRank.addComponentInCenter(lblTitle, (byte) 2);
        if (isRank) {
            String leftName = "查看";
            if (this.state == 12 || this.state == 15) {
                leftName = "确定";
            }
            UILabel lblCheck = new UILabel(0, 0, 0, 0, leftName, 15718814, (byte) 1, (byte) 0);
            formRank.addComponentInCenter(lblCheck, (byte) 5);
        }
        formRank.addComponentInCenter(lblCancel, (byte) 6);
        formRank.addComponentInCenter(table, (byte) 2);
        table.setXY(table.positionX - 1, table.positionY);
        formRank.setFocus(true);
        table.setFocus(true);
        switch (numTable) {
            case 1:
                this.oneSubTable = null;
                this.oneSubTable = table;
                break;
            case 2:
                this.twoSubTable = null;
                this.twoSubTable = table;
                break;
        }
        return formRank;
    }

    public static void tick() {
        if (maxTime < 0) {
            return;
        }
        if (needAutoResponse) {
            time = (short) (time + 1);
            if (time >= maxTime) {
                time = 0;
                for (int i = 0; i < Player.userDefinedSkills.length; i++) {
                    int skillIndex = Player.userDefinedSkills[i];
                    if (skillIndex >= 0 && skillIndex <= 14) {
                        if (skillIndex != 0) {
                            Player.canUseSkill[i] = true;
                            Player.skillCD[skillIndex] = 0L;
                        }
                    }
                }
                PCArena arena = getInstance();
                arena.getClass();
                arena.acceptEnterLine = 1;
                arena.getClass();
                MainCanvas.ni.send(285217280);
                arena.clear();
                MainCanvas.mc.topForm = null;
                releaseInstance();
                MainCanvas.mc.releaseUI();
                MainCanvas.mc.setGameState((byte) 0);
                maxTime = -1;
            }
        }
    }

    private PCArena() {
        this.inivetedID = 0;
    }

    public void parse(int commID, byte[] data) {
        String hexID = Integer.toHexString(commID);
        System.out.println("[处理命令]:命令ID:0x" + hexID + ",命令类型：PCArena");
        byte number;
        String formName, rightame;
        UIForm tempFrom;
        String[] scores;
        int i;
        byte bool, len;
        String[] nameList;
        byte b1;
        ByteArray ba = new ByteArray(data);
        PCArena arena = getInstance();
        String mgs = "";
        switch (commID) {
            case 285218688:
                MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm()
                        .setAboutForm((UIForm) null);
                number = ba.readByte();
                mgs = ba.readUTF();
                formName = "false";
                rightame = "";
                if (number == 1) {
                    formName = "true";
                    rightame = "取消";
                }
                this.state = 14;
                tempFrom = UIForm.makeAboutForm(formName, mgs, "确定", rightame, MainCanvas.screenW - 22);
                MainCanvas.mc.baseForm.getCurrentFocusForm().setAboutForm(tempFrom);
                MainCanvas.mc.baseForm.getCurrentFocusForm().setFormXY(MainCanvas.screenW - tempFrom.width >> 1, MainCanvas.screenH - tempFrom.height >> 1);
                break;
            case 285218432:
                MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm()
                        .setAboutForm((UIForm) null);
                number = ba.readByte();
                scores = null;
                if (number > 0) {
                    scores = new String[number];
                    for (int j = 0; j < number; j++) {
                        scores[j] = ba.readUTF();
                    }
                    this.state = 12;
                    MainCanvas.mc.baseForm.getCurrentFocusForm().setAboutForm(arena
                            .createRankForm("转服申请", scores, true, (byte) 2));
                    break;
                }
                arena.showMessage("服务器列表为空");
                break;
            case 285213056:
                mgs = ba.readUTF();
                arena.showMessage(mgs);
                break;
            case 285213312:
                stopWait();
                mgs = ba.readUTF();
                arena.viewMessage(mgs, true);
                break;
            case 285213568:
                mgs = ba.readUTF();
                arena.viewMessage(mgs, true);
                break;
            case 285213824:
                switch (ba.readByte()) {
                    case 0:
                        arena.showMessage(ba.readUTF());
                        break;
                    case 1:
                        arena.inivetedID = ba.readInt();
                        if (arena.isPopUp() == 0) {
                            stopWait();
                            arena.createResponseForm(ba.readUTF());
                            break;
                        }
                        arena.getClass();
                        arena.response = 2;
                        MainCanvas.ni.send(285213952);
                        break;
                }
                break;
            case 285214080:
                arena.viewMessage(ba.readUTF(), true);
                break;
            case 285214592:
                stopWait();
                number = ba.readByte();
                scores = new String[number];
                for (i = 0; i < number; i++) {
                    scores[i] = ba.readUTF();
                }
                this.oneSubForm = null;
                this.oneSubForm = arena.createRankForm("战队成绩", scores, false, (byte) 1);
                this.state = 1;
                break;
            case 285214848:
                stopWait();
                number = ba.readByte();
                scores = new String[number];
                for (i = 0; i < number; i++) {
                    scores[i] = ba.readUTF();
                }
                this.oneSubForm = null;
                this.oneSubForm = arena.createRankForm("排行榜", scores, true, (byte) 1);
                this.state = 1;
                break;
            case 285217152:
                arena.viewMessage(ba.readUTF(), false);
                break;
            case 285217408:
                stopWait();
                arena.createEnterConfirmForm(ba.readUTF(), ba.readByte(), ba
                        .readShort());
                break;
            case 285217664:
                arena.viewMessage("收到了退出排队的回复消息", false);
                break;
            case 285215360:
                stopWait();
                number = ba.readByte();
                scores = new String[number];
                for (i = 0; i < number; i++) {
                    scores[i] = ba.readUTF();
                }
                this.twoSubForm = null;
                this.twoSubForm = arena.createRankForm("战队详情", scores, false, (byte) 2);
                this.state = 10;
                break;
            case 285215616:
            case 285217920:
                bool = ba.readByte();
                if (bool == 0) {
                    arena.showMessage(ba.readUTF());
                    break;
                }
                len = ba.readByte();
                nameList = new String[len];
                for (b1 = 0; b1 < len; b1 = (byte) (b1 + 1)) {
                    try {
                        nameList[b1] = ba.readUTF();
                    } catch (Exception e) {
                        nameList[b1] = "没有读到数据";
                    }
                }
                byte state = (commID == 285215616) ? (byte) 6 : 11;
                getInstance().createKickMember(state, nameList);
                break;
            case 285215104:
            case 285218176:
                mgs = ba.readUTF();
                try {
                    arena.viewMessage(mgs, true);
                } catch (Exception e) {
                    PCChat.addChat((byte) 7, mgs);
                }
                break;
            case 285220992:
                MainCanvas.mc.arenaStatus = ba.readByte();
                break;
            case 285219712:
                MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm()
                        .setAboutForm((UIForm) null);
                mgs = ba.readUTF();
                arena.showMessage(mgs);
                break;
            case 285219456:
                MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm()
                        .setAboutForm((UIForm) null);
                number = ba.readByte();
                mgs = ba.readUTF();
                if (number == 0) {
                    arena.showMessage(mgs);
                    break;
                }
                this.name = mgs;
                if (isArena == 0) {
                    createForm((byte) 17);
                    break;
                }
                createForm((byte) 18);
                break;
        }
    }

    public byte[] compress(int command) {
        PCArena arena = getInstance();
        ByteArray ba = new ByteArray();
        switch (command) {
            case 285212928:
                ba.writeUTF(arena.name);
                break;
            case 285213440:
                ba.writeByte(0);
                break;
            case 285213696:
                ba.writeUTF(arena.name);
                break;
            case 285213952:
                ba.writeInt(arena.inivetedID);
                ba.writeByte(arena.response);
                break;
            case 285217024:
                ba.writeByte(arena.arenaType);
                break;
            case 285217280:
                ba.writeByte(arena.acceptEnterLine);
                ba.writeByte(arena.arenaType);
                needAutoResponse = false;
                break;
            case 285217536:
                ba.writeByte(0);
                break;
            case 285215232:
                ba.writeByte(arena.oneSubTable.getCurrentPointer());
                break;
            case 285214976:
            case 285218048:
                ba.writeUTF(arena.kickName);
                break;
            case 285218560:
                ba.writeByte(arena.twoSubTable.getCurrentPointer());
                ba.writeByte(arena.returnServerRadioButton.getChooseItemIndex());
                ba.writeByte(returnServerType);
                break;
            case 285218304:
                ba.writeByte(returnServerType);
                break;
            case 285219584:
                ba.writeByte(isArena);
                ba.writeUTF(this.name);
                break;
            case 285219328:
                ba.writeByte(isArena);
                break;
        }
        return ba.toByteArray();
    }

    public void sendCommand(int index) {
        switch (index) {
            case 36:
                getInstance().createForm((byte) 2);
                break;
            case 37:
                getInstance().createDisbandTeamForm();
                break;
            case 38:
                getInstance().quitTeam();
                break;
            case 39:
                MainCanvas.requestFriendPlace = 3;
                MainCanvas.ni.send(201327360);
                break;
            case 40:
                MainCanvas.ni.send(285215488);
                break;
            case 41:
                MainCanvas.ni.send(285214464);
                break;
            case 42:
                MainCanvas.ni.send(285214720);
                break;
            case 43:
                stopWait();
                this.state = 15;
                this.oneSubForm = null;
                this.oneSubForm = createRankForm("竞技场排队", new String[]{"1对1竞技场", "2对2竞技场"}, true, (byte) 1);
                break;
            case 64:
                MainCanvas.ni.send(285217792);
                break;
            case 69:
                stopWait();
                this.state = 16;
                MainCanvas.mc.baseForm.setAboutForm(createRankForm("请求服务器列表", new String[]{"推荐服务器列表", "收费服务器列表"}, true, (byte) 1));
                break;
        }
        MainCanvas.mc.setNPCSubState((byte) 34);
    }

    private void clear() {
        this.oneSubForm = null;
        this.oneSubTable = null;
        this.state = -1;
        stopWait();
        MainCanvas.mc.setNPCSubState((byte) 0);
    }

    private static void stopWait() {
        if (MainCanvas.mc.baseForm != null) {
            MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
        }
    }

    private void createKickMember(byte state, String[] nameList) {
        this.state = state;
        UIForm subForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "invite");
        subForm.setBackGround((byte) 9);
        MainCanvas.mc.tables[0] = new UITable(0, 0, 110, 0, nameList.length, 1, (nameList.length > 5) ? 5 : nameList.length, (byte) 0, (byte) 3);
        for (int i = 0; i < nameList.length; i++) {
            MainCanvas.mc.tables[0].setItem(nameList[i], i, 65280);
        }
        MainCanvas.mc.tables[0].setAutoHeight(true);
        subForm.addComponentInCenter(MainCanvas.mc.tables[0], (byte) 4);
        MainCanvas.mc.baseForm.getCurrentFocusForm().setAboutForm(subForm);
    }

    void selectReceiver(byte state) {
        this.state = state;
        String[] strFriend = null;
        if (MainCanvas.friendList == null) {
            strFriend = new String[1];
        } else {
            strFriend = new String[MainCanvas.friendList.length + 1];
        }
        strFriend[0] = "[输入玩家的名字]";
        for (int i = 1; i < strFriend.length; i++) {
            strFriend[i] = MainCanvas.friendList[i - 1][0];
        }
        UIForm subForm = new UIForm(0, 0, 0, 0, "invite");
        subForm.setBackGround((byte) 9);
        MainCanvas.mc.tables[0] = new UITable(0, 0, 160, 0, strFriend.length, 1, (strFriend.length > 5) ? 5 : strFriend.length, (byte) 0, (byte) 3);
        for (int j = 0; j < strFriend.length; j++) {
            MainCanvas.mc.tables[0].setItem(strFriend[j], j, 65280);
        }
        MainCanvas.mc.tables[0].setAutoHeight(true);
        subForm.addComponentInCenter(MainCanvas.mc.tables[0], (byte) 4);
        UIRim rim0 = new UIRim(0, (MainCanvas.mc.tables[0]).positionY - 24, 160, 24, (byte) 0);
        UILabel title = new UILabel(0, rim0.positionY + 2, 0, 0, "好友列表", 15587742, (byte) 1, (byte) 0);
        subForm.addComponentInCenter(rim0, (byte) 2);
        subForm.addComponentInCenter(title, (byte) 2);
        MainCanvas.mc.baseForm.setAboutForm((UIForm) null);
        MainCanvas.mc.baseForm.setAboutForm(subForm);
        rim0.positionY = (short) ((MainCanvas.mc.tables[0]).positionY - 24);
        title.positionY = (short) (rim0.positionY + 2);
    }

    private void viewMessage(String mgs, boolean bool) {
        UIForm tempForm;
        switch (isPopUp()) {
            case 0:
                showMessage(mgs);
                break;
            case 1:
                PCChat.addChat((byte) 7, mgs);
                break;
            case 2:
                if (MainCanvas.NPCMenu != null) {
                    try {
                        int index = MainCanvas.NPCMenu.getMappingPointer();
                        if (bool) {
                            if ((index >= 36 && index <= 40) || index == 64) {
                                showMessage(mgs);
                                return;
                            }
                        } else if (index >= 41 && index <= 43) {
                            showMessage(mgs);
                            return;
                        }
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    }
                }
                tempForm = MainCanvas.mc.baseForm.getCurrentFocusForm();
                MainCanvas.mc.baseForm.setShowMessage(false);
                tempForm.setMessage(mgs, false);
                break;
        }
    }

    private byte isPopUp() {
        if (MainCanvas.mc.getGameState() == 0 && MainCanvas.mc.baseForm == null) {
            if (MainCanvas.mc.topForm != null
                    && !"arena".equals(MainCanvas.mc.topForm.getName())) {
                return 1;
            }
            return 0;
        }
        if (MainCanvas.mc.baseForm != null) {
            return 2;
        }
        return 1;
    }

    private void createDisbandTeamForm() {
        stopWait();
        this.state = 9;
        UIForm tempFrom = UIForm.makeAboutForm("arena", "您确定要解散战队么", "解散", "取消", MainCanvas.screenW - 22);
        setArenaForm(tempFrom);
    }

    public byte getState() {
        return this.state;
    }
}
