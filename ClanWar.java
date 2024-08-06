
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class ClanWar {

    private int[] m_id;
    private int[] selectId;
    private String[] selectName;
    private static final int SELECT_LEN = 10;
    private byte m_warType = 0;
    private boolean isSubmit = false;
    private UIRadioButton[] rbsSelect;
    private boolean isLeft;
    public static final int[] CLAN_NOTICE_COLOR = new int[]{16711680, 16739328, 16731903};
    private byte[] notice_color = null;
    private int[] notice_id = null;
    private String[] notice_info = null;
    private boolean[] notice_isChange = null;
    private String curr_notice_info = "";
    private static ClanWar m_instance = null;
    private int m_letterCost;
    private int m_clanCurPage;
    private int m_clanTotalPages;
    private String[] m_clanNames;
    private byte[] m_clanLvs;
    private String m_challenger;
    private byte m_teamCurPage;
    private byte m_teamTotalPages;
    private String[] m_teamNames;
    private boolean[] m_teamSelected;
    private String[] m_godNames;
    private boolean[] m_godActivables;
    private int m_altarBuildMoney;
    private int m_altarBuildMedal;
    private String[] m_altarNames;
    private boolean[] m_altarActivables;
    private int m_buildMoney;
    private int m_buildMedal;
    byte m_curPage;
    byte m_totalPages;
    private String[] m_names;
    private int[] m_points;
    private boolean[] m_activables;

    public static ClanWar getInstance() {
        if (m_instance == null) {
            m_instance = new ClanWar();
        }
        return m_instance;
    }
    public static byte m_getJoinMemList = 0;

    public void tick() {
        UIComponent c = MainCanvas.mc.baseForm.getCommand();
        UIForm form = MainCanvas.mc.baseForm.getCurrentFocusForm();
        String formName = form.getName();
        if (formName.equals("warMenu")) {
            tickClanWarMenu(c);
        } else if (formName.equals("noOperation")) {
            tickClanWarNoOperation();
        } else if (formName.equals("confirmAccept")) {
            tickClanWarConfirmAccept();
        } else if (formName.equals("confirmRefuse")) {
            tickClanWarConfirmRefuse();
        } else if (formName.equals("acceptResult")) {
            tickClanWarAcceptResult();
        } else if (formName.equals("refuseResult")) {
            tickClanWarRefuseResult();
        } else if (formName.equals("clanList")) {
            tickClanWarList(form, c);
        } else if (formName.equals("letterConfirm")) {
            tickClanLetterConfirm(form, c);
        } else if (formName.equals("letterResult")) {
            tickClanWarLetterResult();
        } else if (formName.equals("joinResult")) {
            tickClanWarJoinResult();
        } else if (formName.equals("teamList")) {
            tickClanWarTeamList(form, c);
        } else if (formName.equals("selectTeamResult")) {
            tickClanWarSelectTeamResult();
        } else if (formName.equals("godList")) {
            tickClanGodList(form, c);
        } else if (formName.equals("menuGodList")) {
            tickClanGodListMenu(form, c);
        } else if (formName.equals("godInfo")) {
            tickClanGodInfo(form, c);
        } else if (formName.equals("godActiveResult")) {
            tickClanActiveGodResult(form, c);
        } else if (formName.equals("altarMenu")) {
            tickClanAltarMenu(form, c);
        } else if (formName.equals("altarConfirmBuild")) {
            tickClanAltarConfirmBuild(form, c);
        } else if (formName.equals("altarBuildResult")) {
            tickClanAltarBuildResult(form, c);
        } else if (formName.equals("altarList")) {
            tickClanAltarList(form, c);
        } else if (formName.equals("menuAltarList")) {
            tickClanAltarListMenu(form, c);
        } else if (formName.equals("altarInfo")) {
            tickClanAltarInfo(form, c);
        } else if (formName.equals("altarActiveResult")) {
            tickClanAltarActiveResult(form, c);
        } else if (formName.equals("buildingMenu")) {
            tickClanBuildingMenu(form, c);
        } else if (formName.equals("buildingConfirmBuild")) {
            tickClanBuildingConfirmBuild(form, c);
        } else if (formName.equals("buildingBuildResult")) {
            tickClanBuildingBuildResult(form, c);
        } else if (formName.equals("buildingInfo")) {
            tickClanBuildingInfo(form, c);
        } else if (formName.equals("clanTop")) {
            tickClanTop(form, c);
        } else if (formName.equals("notice")) {
            tickClanNotice(form, c);
        } else if (formName.equals("noticeInfo")) {
            tickClanNoticeInfo(form, c);
        } else if (formName.equals("notice_Left_Menu")) {
            tickClanNoticeOperation(form, c);
        } else if (formName.equals("notice_Add_No")) {
            tickClanNoticeNo(form, c);
        } else if (formName.equals("notice_Del_No")) {
            tickAcceptDelNotice();
        } else if (formName.equals("clanMemTopNo") || formName
                .equals("clanEnterAreaNo")) {
            tickClanMemTopNo(form, c);
        } else if (formName.equals("clanWarList_Left_Menu")) {
            tickClanWarListMenu(c);
        } else if (formName.equals("submitGroup")) {
            tickClanWarGroupSubmit(form, c);
        } else if (formName.equals("groupList")) {
            tickClanWarGroupList(form, c);
        }
    }

    private boolean actionInForm(UIComponent c) {
        if (c == null) {
            return false;
        }
        if (MainCanvas.isKeyPress(11)) {
            MainCanvas.mc.baseForm.setDirectionFocus(11);
        } else if (MainCanvas.isKeyPress(13)) {
            MainCanvas.mc.baseForm.setDirectionFocus(13);
        } else if (MainCanvas.isKeyPress(10)) {
            MainCanvas.mc.baseForm.setDirectionFocus(10);
        } else if (MainCanvas.isKeyPress(12)) {
            MainCanvas.mc.baseForm.setDirectionFocus(12);
        }
        return true;
    }

    private void tickClanWarMenu(UIComponent c) {
        if (MainCanvas.isKeyPressOk()) {
            StringBuffer sb1, sb2;
            int index = MainCanvas.mc.menus[0].getCurrentPointer();
            switch (index) {
                case 0:
                    this.m_clanCurPage = 0;
                    MainCanvas.startWait(MainCanvas.mc.baseForm);
                    NetInterface.getInstance().send(301990400);
                    break;
                case 1:
                    sb1 = new StringBuffer();
                    sb1.append("您真的要接受");
                    sb1.append(this.m_challenger);
                    sb1.append("氏族的" + ((this.m_warType == 0) ? "精英" : "团队") + "战书吗？");
                    alertOption(MainCanvas.mc.baseForm, "confirmAccept", sb1
                            .toString(), true, true);
                    break;
                case 2:
                    sb2 = new StringBuffer();
                    sb2.append("您真的要拒绝");
                    sb2.append(this.m_challenger);
                    sb2.append("氏族的" + ((this.m_warType == 0) ? "精英" : "团队") + "战书吗？");
                    alertOption(MainCanvas.mc.baseForm, "confirmRefuse", sb2
                            .toString(), true, true);
                    break;
                case 3:
                    MainCanvas.startWait(MainCanvas.mc.baseForm);
                    NetInterface.getInstance().send(301991424);
                    break;
                case 4:
                    this.selectId = new int[10];
                    this.selectName = new String[10];
                    this.m_teamCurPage = 0;
                    MainCanvas.startWait(MainCanvas.mc.baseForm);
                    NetInterface.getInstance().send(301991680);
                    break;
                case 5:
                    MainCanvas.startWait(MainCanvas.mc.baseForm);
                    NetInterface.getInstance().send(301992192);
                    break;
            }
        } else if (MainCanvas.isKeyPress(18)) {
            returnToNpcMenu();
        } else {
            actionInForm(c);
        }
    }

    private void tickClanAltarMenu(UIForm form, UIComponent c) {
        if (MainCanvas.isKeyPressOk()) {
            StringBuffer sb1;
            int index = MainCanvas.mc.menus[0].getCurrentPointer();
            switch (index) {
                case 0:
                    sb1 = new StringBuffer();
                    sb1.append("创建氏族祭坛需要：\n");
                    sb1.append("钱：");
                    sb1.append(this.m_altarBuildMoney);
                    sb1.append("\n奖牌：");
                    sb1.append(this.m_altarBuildMedal);
                    sb1.append("\n您确定吗？");
                    alertOption(MainCanvas.mc.baseForm, "altarConfirmBuild", sb1
                            .toString(), true, true);
                    break;
                case 1:
                    MainCanvas.startWait(MainCanvas.mc.baseForm);
                    NetInterface.getInstance().send(301993728);
                    break;
            }
        } else if (MainCanvas.isKeyPress(18)) {
            returnToNpcMenu();
        } else {
            actionInForm(c);
        }
    }

    private void tickClanAltarConfirmBuild(UIForm form, UIComponent c) {
        if (MainCanvas.isKeyPressOk()) {
            MainCanvas.startWait(MainCanvas.mc.baseForm);
            NetInterface.getInstance().send(301993472);
        } else if (MainCanvas.isKeyPress(18)) {
            MainCanvas.mc.baseForm.setAboutForm(null);
        }
    }

    private void tickClanAltarBuildResult(UIForm form, UIComponent c) {
        if (MainCanvas.isKeyPressOk()) {
            MainCanvas.mc.baseForm.setAboutForm(null);
        }
    }

    private void tickClanAltarList(UIForm form, UIComponent c) {
        tickListTableForm(c, this.m_altarActivables, new String[]{"查看", "激活"}, "menuAltarList");
    }

    private void tickClanAltarListMenu(UIForm form, UIComponent c) {
        if (MainCanvas.isKeyPressOk()) {
            int index = MainCanvas.mc.menus[1].getCurrentPointer();
            switch (index) {
                case 0:
                    MainCanvas.startWait(MainCanvas.mc.baseForm.getSubForm());
                    NetInterface.getInstance().send(301993984);
                    break;
                case 1:
                    MainCanvas.startWait(MainCanvas.mc.baseForm.getSubForm());
                    NetInterface.getInstance().send(301998080);
                    break;
            }
        } else if (MainCanvas.isKeyPress(18)) {
            MainCanvas.mc.baseForm.getSubForm().setAboutForm(null);
        } else {
            actionInForm(c);
        }
    }

    private void tickClanAltarInfo(UIForm form, UIComponent c) {
        if (MainCanvas.isKeyPress(18)) {
            MainCanvas.mc.baseForm.getSubForm().setAboutForm(null);
        }
    }

    private void tickClanAltarActiveResult(UIForm form, UIComponent c) {
        if (MainCanvas.isKeyPressOk()) {
            MainCanvas.mc.baseForm.getSubForm().setAboutForm(null);
        }
    }

    private void tickClanBuildingMenu(UIForm form, UIComponent c) {
        if (MainCanvas.isKeyPressOk()) {
            StringBuffer sb1;
            int index = MainCanvas.mc.menus[0].getCurrentPointer();
            switch (index) {
                case 0:
                    sb1 = new StringBuffer();
                    sb1.append("如果要创建，需要：\n");
                    sb1.append("钱：");
                    sb1.append(this.m_buildMoney);
                    sb1.append("\n奖牌：");
                    sb1.append(this.m_buildMedal);
                    sb1.append("\n您确定吗？");
                    alertOption(MainCanvas.mc.baseForm, "buildingConfirmBuild", sb1
                            .toString(), true, true);
                    break;
                case 1:
                    MainCanvas.startWait(MainCanvas.mc.baseForm);
                    switch (MainCanvas.mc.getNPCSubState()) {
                        case 48:
                            NetInterface.getInstance().send(302010368);
                            break;
                        case 49:
                            NetInterface.getInstance().send(302022656);
                            break;
                        case 50:
                            NetInterface.getInstance().send(302034944);
                            break;
                        case 51:
                            NetInterface.getInstance().send(302047232);
                            break;
                        case 76:
                            NetInterface.getInstance().send(302776320);
                            break;
                        case 77:
                            NetInterface.getInstance().send(302972928);
                            break;
                        case 78:
                            NetInterface.getInstance().send(305135616);
                            break;
                    }
                    break;
            }
        } else if (MainCanvas.isKeyPress(18)) {
            returnToNpcMenu();
        } else {
            actionInForm(c);
        }
    }

    private void tickClanBuildingConfirmBuild(UIForm form, UIComponent c) {
        if (MainCanvas.isKeyPressOk()) {
            MainCanvas.startWait(MainCanvas.mc.baseForm);
            switch (MainCanvas.mc.getNPCSubState()) {
                case 48:
                    NetInterface.getInstance().send(302006272);
                    break;
                case 49:
                    NetInterface.getInstance().send(302018560);
                    break;
                case 50:
                    NetInterface.getInstance().send(302030848);
                    break;
                case 51:
                    NetInterface.getInstance().send(302043136);
                    break;
                case 76:
                    NetInterface.getInstance().send(302710784);
                    break;
                case 77:
                    NetInterface.getInstance().send(302907392);
                    break;
                case 78:
                    NetInterface.getInstance().send(304087040);
                    break;
            }
        } else if (MainCanvas.isKeyPress(18)) {
            MainCanvas.mc.baseForm.setAboutForm(null);
        }
    }

    private void tickClanBuildingBuildResult(UIForm form, UIComponent c) {
        if (MainCanvas.isKeyPressOk()) {
            MainCanvas.mc.baseForm.setAboutForm(null);
        }
    }

    private void tickClanBuildingInfo(UIForm form, UIComponent c) {
        if (MainCanvas.isKeyPress(18)) {
            MainCanvas.mc.baseForm.setAboutForm(null);
        }
    }

    private void tickClanTop(UIForm form, UIComponent c) {
        if (MainCanvas.isKeyPress(10)) {
            if (this.m_curPage > 0) {
                this.m_curPage = (byte) (this.m_curPage - 1);
                MainCanvas.startWait(form);
                NetInterface.getInstance().send(302051328);
            }
        } else if (MainCanvas.isKeyPress(12)) {
            if (this.m_curPage < this.m_totalPages - 1) {
                this.m_curPage = (byte) (this.m_curPage + 1);
                MainCanvas.startWait(form);
                NetInterface.getInstance().send(302051328);
            }
        } else if (MainCanvas.isKeyPress(18)) {
            returnToNpcMenu();
        } else {
            actionInForm(c);
        }
    }

    private void tickClanMemTopNo(UIForm form, UIComponent c) {
        if (MainCanvas.isKeyPressOk()) {
            returnToNpcMenu();
        }
    }

    private void returnToNpcMenu() {
        MainCanvas.mc.releaseUI();
        MainCanvas.mc.setNPCSubState((byte) 0);
    }

    private void tickClanWarNoOperation() {
        if (MainCanvas.isKeyPressOk()) {
            returnToNpcMenu();
        }
    }

    private void tickClanWarConfirmAccept() {
        if (MainCanvas.isKeyPressOk()) {
            MainCanvas.startWait(MainCanvas.mc.baseForm);
            NetInterface.getInstance().send(301990912);
        } else if (MainCanvas.isKeyPress(18)) {
            MainCanvas.mc.baseForm.setAboutForm(null);
        }
    }

    private void tickClanWarConfirmRefuse() {
        if (MainCanvas.isKeyPressOk()) {
            MainCanvas.startWait(MainCanvas.mc.baseForm);
            NetInterface.getInstance().send(301991168);
        } else if (MainCanvas.isKeyPress(18)) {
            MainCanvas.mc.baseForm.setAboutForm(null);
        }
    }

    private void tickClanWarAcceptResult() {
        if (MainCanvas.isKeyPressOk()) {
            returnToNpcMenu();
        }
    }

    private void tickClanWarRefuseResult() {
        if (MainCanvas.isKeyPressOk()) {
            returnToNpcMenu();
        }
    }

    private void tickClanWarListMenu(UIComponent c) {
        if (MainCanvas.isKeyPressOk()) {
            if (this.m_clanNames != null && this.m_clanNames.length > 0) {
                this.m_warType = MainCanvas.mc.menus[1].getCurrentPointer();
                alertOption(MainCanvas.mc.baseForm.getSubForm(), "letterConfirm", "下战书需要花费" + this.m_letterCost + "，您确定吗？", true, true);
            }
        } else if (MainCanvas.isKeyPress(18)) {
            MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm().setAboutForm(null);
        } else {
            actionInForm(c);
        }
    }

    private void tickClanWarList(UIForm form, UIComponent c) {
        if (MainCanvas.isKeyPressOk()) {
            UIForm subForm = new UIForm(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, "clanWarList_Left_Menu");
            subForm.setBackGround((byte) 9);
            UIMenu menu = new UIMenu(35, 0, 80, 0, null, new String[]{"申请精英战", "申请团队战"});
            subForm.addComponentInCenter(menu, (byte) 5);
            MainCanvas.mc.baseForm.getCurrentFocusForm().setAboutForm(subForm);
            MainCanvas.mc.baseForm.setFocus(true);
            MainCanvas.mc.menus[1] = null;
            MainCanvas.mc.menus[1] = menu;
        } else if (MainCanvas.isKeyPress(18)) {
            MainCanvas.mc.baseForm.setAboutForm(null);
        } else if (MainCanvas.isKeyPress(10)) {
            if (this.m_clanTotalPages > 1) {
                if (this.m_clanCurPage > 0) {
                    this.m_clanCurPage--;
                } else {
                    this.m_clanCurPage = this.m_clanTotalPages - 1;
                }
                MainCanvas.startWait(form);
                NetInterface.getInstance().send(301990400);
            }
        } else if (MainCanvas.isKeyPress(12)) {
            if (this.m_clanTotalPages > 1) {
                if (this.m_clanCurPage < this.m_clanTotalPages - 1) {
                    this.m_clanCurPage++;
                } else {
                    this.m_clanCurPage = 0;
                }
                MainCanvas.startWait(form);
                NetInterface.getInstance().send(301990400);
            }
        } else {
            actionInForm(c);
        }
    }

    private void tickClanLetterConfirm(UIForm form, UIComponent c) {
        if (MainCanvas.isKeyPressOk()) {
            MainCanvas.startWait(form);
            NetInterface.getInstance().send(301990656);
        } else if (MainCanvas.isKeyPress(18)) {
            MainCanvas.mc.baseForm.getSubForm().setAboutForm(null);
        }
    }

    private void tickClanWarLetterResult() {
        if (MainCanvas.isKeyPressOk()) {
            returnToNpcMenu();
        }
    }

    private void tickClanWarJoinResult() {
        if (MainCanvas.isKeyPressOk()) {
            returnToNpcMenu();
        }
    }

    private void tickClanWarTeamList(UIForm form, UIComponent c) {
        if (MainCanvas.isKeyPressOk()) {
            if (this.m_teamNames != null && this.m_teamNames.length > 0) {
                MainCanvas.startWait(form);
                NetInterface.getInstance().send(301991936);
            }
        } else if (MainCanvas.isKeyPress(18)) {
            MainCanvas.mc.baseForm.setAboutForm(null);
        } else if (MainCanvas.isKeyPress(10)) {
            if (this.m_teamCurPage > 0) {
                this.m_teamCurPage = (byte) (this.m_teamCurPage - 1);
                MainCanvas.startWait(form);
                NetInterface.getInstance().send(301991680);
            }
        } else if (MainCanvas.isKeyPress(12)) {
            if (this.m_teamCurPage < this.m_teamTotalPages - 1) {
                this.m_teamCurPage = (byte) (this.m_teamCurPage + 1);
                MainCanvas.startWait(form);
                NetInterface.getInstance().send(301991680);
            }
        } else {
            actionInForm(c);
        }
    }

    private void tickClanWarGroupList(UIForm form, UIComponent c) {
        if (MainCanvas.isKeyPressOk()) {
            UIComponent cmd = form.getCommand();
            if (cmd == MainCanvas.mc.buttons[0]) {
                if (this.m_teamCurPage > 0) {
                    this.m_teamCurPage = (byte) (this.m_teamCurPage - 1);
                    MainCanvas.startWait(form);
                    NetInterface.getInstance().send(301991680);
                }
            } else if (cmd == MainCanvas.mc.buttons[1]) {
                if (this.m_teamCurPage < this.m_totalPages - 1) {
                    this.m_teamCurPage = (byte) (this.m_teamCurPage + 1);
                    MainCanvas.startWait(form);
                    NetInterface.getInstance().send(301991680);
                }
            } else if (!this.isSubmit) {
                StringBuffer msg = new StringBuffer();
                for (int i = 0; i < this.selectName.length; i++) {
                    if (this.selectId[i] != 0) {
                        msg.append(this.selectName[i]);
                        msg.append("，");
                    }
                }
                if ("".equals(msg.toString().trim())) {
                    msg.append("您还未选择团队战成员。");
                    this.isLeft = false;
                    alertOption(MainCanvas.mc.baseForm.getCurrentFocusForm(), "submitGroup", msg
                            .toString(), true, false);
                } else {
                    msg.insert(0, "您选中了");
                    if (checkElement()) {
                        msg.append("团队战成员还未达到十人上限。");
                    }
                    msg.append("是否开始团队战？");
                    this.isLeft = true;
                    UIForm subForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "submitGroup");
                    subForm.setStyle((byte) 0);
                    UIRim rims = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte) 4);
                    subForm.addComponent(rims);
                    UIRim rimName = new UIRim(0, 9, 164, 17, (byte) 7);
                    UILabel title = new UILabel(0, 11, 0, 0, "提交警告", 15718814, (byte) 1, (byte) 0);
                    subForm.addComponentInCenter(rimName, (byte) 2);
                    subForm.addComponentInCenter(title, (byte) 2);
                    UITextArea contentLabel = new UITextArea(0, 28, 164, 160, msg.toString());
                    contentLabel.setColor(16764533);
                    subForm.addComponentInCenter(contentLabel, (byte) 2);
                    UILabel doLabe = new UILabel(80, 8, 0, 0, "确定", 15653280, (byte) 0, (byte) 0);
                    subForm.addComponentInCenter(doLabe, (byte) 5);
                    UILabel backLabel = new UILabel(80, 8, 0, 0, "返回", 15653280, (byte) 0, (byte) 0);
                    subForm.addComponentInCenter(backLabel, (byte) 6);
                    MainCanvas.mc.baseForm.getCurrentFocusForm().setAboutForm(subForm);
                }
            }
        } else if (MainCanvas.isKeyPress(18)) {
            MainCanvas.mc.baseForm.setAboutForm(null);
        } else if (MainCanvas.isKeyPress(10)
                || MainCanvas.isKeyPress(12)) {
            actionInForm(c);
            for (int i = 0; i < this.rbsSelect.length; i++) {
                if ((this.rbsSelect[i]).focus) {
                    if (this.rbsSelect[i].getChooseItemIndex() == 0) {
                        if (checkElement()) {
                            addElement(i);
                            break;
                        }
                        if (checkElementIsSelect(this.m_id[i])) {
                            this.rbsSelect[i].setChooseItem(1);
                            this.isLeft = false;
                            alertOption(MainCanvas.mc.baseForm.getCurrentFocusForm(), "submitGroup", "团队战成员已经达到十人上限。", true, false);
                        }
                        break;
                    }
                    removeElement(this.m_id[i]);
                    break;
                }
            }
        } else {
            actionInForm(c);
        }
    }

    private boolean checkElement() {
        for (int i = 0; i < 10; i++) {
            if (this.selectId[i] == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean checkElementIsSelect(int id) {
        for (int i = 0; i < 10; i++) {
            if (this.selectId[i] == id) {
                return false;
            }
        }
        return true;
    }

    private void addElement(int index) {
        int id = this.m_id[index];
        int j = 0;
        boolean isNull = true;
        for (int i = 0; i < 10; i++) {
            if (isNull && this.selectId[i] == 0) {
                j = i;
                isNull = false;
            } else if (this.selectId[i] == id) {
                return;
            }
        }
        this.selectName[j] = this.m_names[index];
        this.selectId[j] = id;
    }

    private void removeElement(int id) {
        for (int i = 0; i < 10; i++) {
            if (this.selectId[i] == id) {
                this.selectName[i] = "";
                this.selectId[i] = 0;
                break;
            }
        }
    }

    private void tickClanWarGroupSubmit(UIForm form, UIComponent c) {
        if (MainCanvas.isKeyPressOk()) {
            MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm().setAboutForm(null);
            if (this.isLeft) {
                MainCanvas.startWait(form);
                NetInterface.getInstance().send(302579712);
            }
        } else if (MainCanvas.isKeyPress(18)) {
            if (this.isLeft) {
                MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm().setAboutForm(null);
            }
        } else {
            actionInForm(c);
        }
    }

    private void tickClanWarSelectTeamResult() {
        if (MainCanvas.isKeyPressOk()) {
            returnToNpcMenu();
        }
    }

    private void tickClanGodList(UIForm form, UIComponent c) {
        tickListTableForm(c, this.m_godActivables, new String[]{"查看", "激活"}, "menuGodList");
    }

    private void tickListTableForm(UIComponent c, boolean[] activables, String[] menuItems, String menuFormName) {
        if (MainCanvas.isKeyPressOk()) {
            if (activables.length > 0) {
                UIForm subForm = new UIForm(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, menuFormName);
                subForm.setBackGround((byte) 9);
                UIMenu menu = new UIMenu(35, 0, 80, 0, null, menuItems);
                int index = MainCanvas.mc.tables[0].getCurrentPointer();
                if (!activables[index]) {
                    menu.setNoUse((byte) 1);
                }
                subForm.addComponentInCenter(menu, (byte) 5);
                MainCanvas.mc.baseForm.getSubForm().setAboutForm(subForm);
                MainCanvas.mc.menus[1] = null;
                MainCanvas.mc.menus[1] = menu;
            }
        } else if (MainCanvas.isKeyPress(18)) {
            MainCanvas.mc.baseForm.setAboutForm(null);
        } else {
            actionInForm(c);
        }
    }

    private void tickClanGodListMenu(UIForm form, UIComponent c) {
        if (MainCanvas.isKeyPressOk()) {
            int index = MainCanvas.mc.menus[1].getCurrentPointer();
            switch (index) {
                case 0:
                    MainCanvas.startWait(MainCanvas.mc.baseForm.getSubForm());
                    NetInterface.getInstance().send(301992448);
                    break;
                case 1:
                    MainCanvas.startWait(MainCanvas.mc.baseForm.getSubForm());
                    NetInterface.getInstance().send(301992704);
                    break;
            }
        } else if (MainCanvas.isKeyPress(18)) {
            MainCanvas.mc.baseForm.getSubForm().setAboutForm(null);
        } else {
            actionInForm(c);
        }
    }

    private void tickClanGodInfo(UIForm form, UIComponent c) {
        if (MainCanvas.isKeyPress(18)) {
            MainCanvas.mc.baseForm.getSubForm().setAboutForm(null);
        }
    }

    private void tickClanActiveGodResult(UIForm form, UIComponent c) {
        if (MainCanvas.isKeyPressOk()) {
            MainCanvas.mc.baseForm.getSubForm().setAboutForm(null);
        }
    }

    public void draw(Graphics g) {
        MainCanvas.mc.baseForm.draw(g);
    }

    public byte[] compress(int cmd) {
        int i;
        byte temp;
        ByteArray ba = new ByteArray();
        switch (cmd) {
            case 302579712:
                for (i = 0; i < 10; i++) {
                    ba.writeInt(this.selectId[i]);
                }
                break;
            case 301990400:
                sendClanWarList(ba);
                break;
            case 301990656:
                sendClanWarLetter(ba);
                break;
            case 301991680:
                sendClanWarTeamList(ba);
                break;
            case 301991936:
                sendClanWarSelectTeam(ba);
                break;
            case 301992192:
                sendClanGodList(ba);
                break;
            case 301992448:
                sendClanGodInfo(ba);
                break;
            case 301992704:
                sendClanGodActive(ba);
                break;
            case 301993472:
                sendClanAltarBuild(ba);
                break;
            case 301993728:
                sendClanAltarList(ba);
                break;
            case 301993984:
                sendClanAltarInfo(ba);
                break;
            case 301998080:
                sendClanAltarActive(ba);
                break;
            case 302051328:
                ba.writeByte(this.m_curPage);
                break;
            case 302055424:
                ba.writeByte(this.m_curPage);
                break;
            case 302252032:
                ba.writeByte(this.m_curPage);
                temp = (byte) (MainCanvas.mc.tables[0].getCurrentPointer() / 2);
                ba.writeByte(temp);
                break;
            case 302317568:
                ba.writeByte(this.m_curPage);
                break;
            case 302383104:
                ba.writeUTF(this.curr_notice_info);
                break;
            case 302514176:
                ba.writeInt(this.notice_id[MainCanvas.mc.tables[0].getCurrentPointer()]);
                ba.writeUTF(this.curr_notice_info);
                break;
            case 302448640:
                ba.writeInt(this.notice_id[MainCanvas.mc.tables[0].getCurrentPointer()]);
                break;
        }
        return ba.toByteArray();
    }

    public void parse(int commID, byte[] data) {
        String hexID = Integer.toHexString(commID);
        System.out.println("[处理命令]:命令ID:0x" + hexID + ",命令类型：ClanWar");
        ByteArray ba = new ByteArray(data);
        switch (commID) {
            case 306184320:
                receiveGensApplyFarm(ba);
                break;
            case 302645376:
                receiveClanBuildingMenu(ba, new String[]{"扩建氏族任务区", "了解氏族任务区"}, (byte) 76);
                break;
            case 302710912:
                receiveClanBuildingBuildResult(ba, "任务区");
                break;
            case 302776448:
                receiveClanBuildingInfo(ba, "任务区");
                break;
            case 302843904:
                receiveClanBuildingMenu(ba, new String[]{"扩建氏族限量商店", "了解氏族限量商店"}, (byte) 77);
                break;
            case 302909440:
                receiveClanBuildingBuildResult(ba, "限量商店");
                break;
            case 302974976:
                receiveClanBuildingInfo(ba, "限量商店");
                break;
            case 303038592:
                receiveClanBuildingMenu(ba, new String[]{"扩建氏族农场", "了解氏族农场"}, (byte) 78);
                break;
            case 304087168:
                receiveClanBuildingBuildResult(ba, "农场");
                break;
            case 305135744:
                receiveClanBuildingInfo(ba, "农场");
                break;
            case 301990272:
                receiveClanWarMenu(ba);
                break;
            case 301990528:
                receiveClanWarList(ba);
                break;
            case 301990784:
                receiveClanWarLetterResult(ba);
                break;
            case 301991040:
                receiveClanWarAcceptResult(ba);
                break;
            case 301991296:
                receiveClanWarRefuseResult(ba);
                break;
            case 301991552:
                receiveClanWarJoinResult(ba);
                break;
            case 301991808:
                receiveClanWarTeamList(ba);
                break;
            case 302579840:
                receiveClanWarGroupList(ba);
                break;
            case 301992064:
                receiveClanWarSelectTeamResult(ba);
                break;
            case 301992320:
                receiveClanGodList(ba);
                break;
            case 301992576:
                receiveClanGodInfo(ba);
                break;
            case 301992832:
                receiveClanGodActiveResult(ba);
                break;
            case 301993344:
                receiveClanAltarMenu(ba);
                break;
            case 301993600:
                receiveClanAltarBuildResult(ba);
                break;
            case 301993856:
                receiveClanAltarList(ba);
                break;
            case 301994112:
                receiveClanAltarInfo(ba);
                break;
            case 301998208:
                receiveClanAltarActiveResult(ba);
                break;
            case 302002304:
                receiveClanBuildingMenu(ba, new String[]{"扩建氏族修理所", "了解氏族修理所"}, (byte) 48);
                break;
            case 302006400:
                receiveClanBuildingBuildResult(ba, "修理所");
                break;
            case 302010496:
                receiveClanBuildingInfo(ba, "修理所");
                break;
            case 302014592:
                receiveClanBuildingMenu(ba, new String[]{"扩建氏族商店", "了解氏族商店"}, (byte) 49);
                break;
            case 302018688:
                receiveClanBuildingBuildResult(ba, "商店");
                break;
            case 302022784:
                receiveClanBuildingInfo(ba, "商店");
                break;
            case 302026880:
                receiveClanBuildingMenu(ba, new String[]{"扩建氏族仓库", "了解氏族仓库"}, (byte) 50);
                break;
            case 302030976:
                receiveClanBuildingBuildResult(ba, "仓库");
                break;
            case 302035072:
                receiveClanBuildingInfo(ba, "仓库");
                break;
            case 302039168:
                receiveClanBuildingMenu(ba, new String[]{"扩建氏族精炼所", "了解氏族精炼所"}, (byte) 51);
                break;
            case 302043264:
                receiveClanBuildingBuildResult(ba, "精炼所");
                break;
            case 302047360:
                receiveClanBuildingInfo(ba, "精炼所");
                break;
            case 302051456:
                receiveClanTop(ba);
                break;
            case 301993088:
                receiveClanEnterArea(ba);
                break;
            case 302186624:
                receiveClanLevelCredit(ba);
                break;
            case 302055552:
                receiveClanMemTop(ba);
                break;
            case 302252160:
                receiveMemInfo(ba);
                break;
            case 302317696:
                receiveNoticeInfo(ba);
                break;
            case 302383232:
                receiveNoticeAdd(ba, 0);
                break;
            case 302514304:
                receiveNoticeAdd(ba, 1);
                break;
            case 302448768:
                receiveNoticeAdd(ba, 2);
                break;
        }
    }

    private void receiveClanLevelCredit(ByteArray ba) {
        MainCanvas.mc.clanManegeLevel = ba.readByte();
        if (MainCanvas.mc.clanManegeLevel == 0) {
            alertOption(MainCanvas.mc.baseForm, "clanMemTopNo", "您需要先加入一个氏族才能查看", true, false);
        } else {
            UIForm addForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "creditfirm");
            addForm.setBackGround((byte) 9);
            UIRim frame = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte) 4);
            UIRim rimTitle = new UIRim(0, 12, 166, 20, (byte) 7);
            UILabel lblTitle = new UILabel(0, 15, 176, 0, "氏族的详细信息", 15718814, (byte) 1, (byte) 0);
            UIRim rimUp = new UIRim(0, 32, 166, 160, (byte) 0);
            UILabel lblCancel = new UILabel(60, 7, 0, 0, "返回", 15718814, (byte) 0, (byte) 0);
            Vector itemsName = new Vector();
            itemsName.addElement(MainCanvas.mc.clanName + ":  " + ba.readByte() + "级");
            itemsName.addElement("氏族声望:  ");
            itemsName.addElement("  " + ba.readInt() + " + " + ba.readInt());
            itemsName.addElement("奖牌数:  " + ba.readInt());
            itemsName.addElement("氏族最大人数:  " + ba.readShort());
            itemsName.addElement("当前领地:  " + ba.readUTF());
            itemsName.addElement("当前守护神:  " + ba.readUTF() + ba.readUTF());
            itemsName.addElement("当前祭坛:  " + ba.readUTF() + ba.readUTF());
            int nums = itemsName.size();
            UITable table = new UITable(0, 31, 161, 152, nums, 1, (nums > 8) ? 8 : nums, (byte) 0, (byte) 0);
            table.setSingleWH(table.singleWidth, (byte) 19, false);
            for (int i = 0; i < nums; i++) {
                table.setItem(itemsName.elementAt(i).toString(), i, 65280);
            }
            itemsName.removeAllElements();
            itemsName = null;
            addForm.addComponentInCenter(table, (byte) 2);
            MainCanvas.mc.tables[0] = null;
            MainCanvas.mc.tables[0] = table;
            addForm.addComponent(frame);
            addForm.addComponentInCenter(rimTitle, (byte) 2);
            addForm.addComponentInCenter(lblTitle, (byte) 2);
            addForm.addComponentInCenter(rimUp, (byte) 2);
            UILabel lblOk = new UILabel(60, 7, 0, 0, "操作", 15718814, (byte) 0, (byte) 0);
            addForm.addComponentInCenter(lblOk, (byte) 5);
            addForm.addComponentInCenter(lblCancel, (byte) 6);
            MainCanvas.mc.baseForm.setAboutForm(null);
            MainCanvas.mc.baseForm.setAboutForm(addForm);
        }
    }

    private void receiveMemInfo(ByteArray ba) {
        MainCanvas.mc.clanManegeLevel = ba.readByte();
        if (MainCanvas.mc.clanManegeLevel == 0) {
            alertOption(MainCanvas.mc.baseForm, "clanMemTopNo", "您需要先加入一个氏族才能查看", true, false);
        } else {
            String name = "名称: " + ba.readUTF();
            String liXian = (ba.readByte() == 0) ? "离线" : "在线";
            String zhiye = "职业: " + Cons.STR_PLAYERS[ba.readByte()];
            String level = "级别: " + ba.readByte() + "级";
            String mapName = "地图: " + ba.readUTF();
            String Mete = "转生: " + ba.readUTF();
            UIForm addForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "memInfo");
            addForm.setBackGround((byte) 9);
            UIRim frame = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte) 4);
            UIRim rimTitle = new UIRim(0, 12, 166, 20, (byte) 7);
            UILabel lblTitle = new UILabel(0, 15, 176, 0, "氏族成员详细信息", 15718814, (byte) 1, (byte) 0);
            UIRim rimUp = new UIRim(0, 32, 166, 160, (byte) 0);
            UILabel lblCancel = new UILabel(60, 7, 0, 0, "返回", 15718814, (byte) 0, (byte) 0);
            UILabel lblName = new UILabel(10, 34, 0, 0, name, 15718815, (byte) 0, (byte) 0);
            UILabel lblLi = new UILabel(10, 54, 0, 0, liXian, 15718815, (byte) 0, (byte) 0);
            UILabel lblZhiye = new UILabel(10, 74, 0, 0, zhiye, 15718815, (byte) 0, (byte) 0);
            UILabel lblLevel = new UILabel(10, 94, 0, 0, level, 15718815, (byte) 0, (byte) 0);
            UILabel lblMapName = new UILabel(10, 114, 0, 0, mapName, 15718815, (byte) 0, (byte) 0);
            UILabel lblMete = new UILabel(10, 134, 0, 0, Mete, 15718815, (byte) 0, (byte) 0);
            addForm.addComponent(frame);
            addForm.addComponentInCenter(rimTitle, (byte) 2);
            addForm.addComponentInCenter(lblTitle, (byte) 2);
            addForm.addComponentInCenter(rimUp, (byte) 2);
            addForm.addComponentInCenter(lblCancel, (byte) 6);
            addForm.addComponent(lblName);
            addForm.addComponent(lblLi);
            addForm.addComponent(lblZhiye);
            addForm.addComponent(lblLevel);
            addForm.addComponent(lblMapName);
            addForm.addComponent(lblMete);
            MainCanvas.mc.baseForm.getSubForm().getSubForm().setAboutForm(null);
            MainCanvas.mc.baseForm.getSubForm().getSubForm().setAboutForm(addForm);
        }
    }

    private void receiveClanWarMenu(ByteArray ba) {
        boolean[] operatables = new boolean[6];
        operatables[0] = (ba.readByte() == 1);
        if (operatables[0]) {
            this.m_letterCost = ba.readInt();
        }
        operatables[1] = (ba.readByte() == 1);
        operatables[2] = (ba.readByte() == 1);
        boolean hasChallenge = (ba.readByte() == 1);
        if (hasChallenge) {
            this.m_challenger = ba.readUTF();
        }
        operatables[3] = (ba.readByte() == 1);
        operatables[4] = (ba.readByte() == 1);
        operatables[5] = (ba.readByte() == 1);
        this.m_warType = ba.readByte();
        String[] strs = {"下战书", "接受战书", "拒绝战书", "申请参战", "查看申请列表", "启动守护神"};
        if (hasChallenge) {
            String suffix = "(" + this.m_challenger + "氏族)";
            strs[1] = strs[1] + suffix;
            strs[2] = strs[2] + suffix;
        }
        MainCanvas.mc.releaseUI();
        MainCanvas.mc.baseForm = createFormForNpcMenuStyle("warMenu");
        MainCanvas.mc.menus[0] = new UIMenu(5, 48, 164, 142, null, strs);
        MainCanvas.mc.menus[0].setRimStyle((byte) 0);
        MainCanvas.mc.menus[0].setFlushType((byte) 1);
        for (int i = 0; i < operatables.length; i++) {
            if (!operatables[i]) {
                MainCanvas.mc.menus[0].setNoUse((byte) i);
            }
        }
        MainCanvas.mc.baseForm.addComponentInCenter(MainCanvas.mc.menus[0], (byte) 2);
        boolean flag = false;
        for (int j = 0; j < operatables.length; j++) {
            if (operatables[j]) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            alertOption(MainCanvas.mc.baseForm, "noOperation", "您无可用的氏族操作！", true, false);
        } else {
            MainCanvas.mc.baseForm.setFocus(true);
        }
        MainCanvas.mc.setNPCSubState((byte) 46);
    }

    private void receiveClanAltarMenu(ByteArray ba) {
        MainCanvas.mc.releaseUI();
        boolean[] operatables = new boolean[2];
        operatables[0] = (ba.readByte() == 1);
        if (operatables[0]) {
            this.m_altarBuildMoney = ba.readInt();
            this.m_altarBuildMedal = ba.readInt();
        }
        operatables[1] = (ba.readByte() == 1);
        String[] strs = {"扩建氏族祭坛", "查看祭坛列表"};
        MainCanvas.mc.baseForm = createFormForNpcMenuStyle("altarMenu");
        MainCanvas.mc.menus[0] = new UIMenu(5, 48, 164, 142, null, strs);
        MainCanvas.mc.menus[0].setRimStyle((byte) 0);
        MainCanvas.mc.menus[0].setFlushType((byte) 1);
        for (int i = 0; i < operatables.length; i++) {
            if (!operatables[i]) {
                MainCanvas.mc.menus[0].setNoUse((byte) i);
            }
        }
        MainCanvas.mc.baseForm.addComponentInCenter(MainCanvas.mc.menus[0], (byte) 2);
        MainCanvas.mc.baseForm.setFocus(true);
        MainCanvas.mc.setNPCSubState((byte) 47);
    }

    private void sendClanAltarBuild(ByteArray ba) {
    }

    private void receiveClanAltarBuildResult(ByteArray ba) {
        String msg;
        byte result = ba.readByte();
        switch (result) {
            case 0:
                msg = "创建氏族祭坛成功！";
                break;
            case 1:
                msg = "创建氏族祭坛失败！";
                break;
            default:
                msg = "创建氏族祭坛失败，原因未知！";
                break;
        }
        alertOption(MainCanvas.mc.baseForm, "altarBuildResult", msg, true, false);
    }

    private void sendClanAltarList(ByteArray ba) {
    }

    private void receiveClanAltarList(ByteArray ba) {
        byte nums = ba.readByte();
        this.m_altarNames = null;
        this.m_altarActivables = null;
        this.m_altarNames = new String[nums];
        this.m_altarActivables = new boolean[nums];
        for (int i = 0; i < nums; i++) {
            this.m_altarNames[i] = ba.readUTF();
            this.m_altarActivables[i] = (ba.readByte() == 1);
        }
        createListTableForm1("altarList", "祭坛列表", "操作", this.m_altarNames, this.m_altarActivables);
    }

    private void sendClanAltarInfo(ByteArray ba) {
        int index = MainCanvas.mc.tables[0].getCurrentPointer();
        ba.writeUTF(this.m_altarNames[index]);
    }

    private void receiveClanAltarInfo(ByteArray ba) {
        String description = ba.readUTF();
        int needMoney = ba.readInt();
        int needRepute = ba.readInt();
        int haveMoney = ba.readInt();
        int haveRepute = ba.readInt();
        UIForm form = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "altarInfo");
        form.setBackGround((byte) 9);
        int index = MainCanvas.mc.tables[0].getCurrentPointer();
        String title = this.m_altarNames[index];
        UILabel lblTitle = new UILabel(0, 12, 176, 0, title, 15718814, (byte) 1, (byte) 0);
        UILabel label = new UILabel(0, 40, 160, 0, description, 16777215, (byte) 0, (byte) 0);
        UITable table = new UITable(0, 80, 166, 80, 4, 1, 4, (byte) 0, (byte) 0);
        table.setRim(false);
        table.setItem("需要钱数：" + needMoney, 0, 16776960);
        table.setItem("需要奖牌数：" + needRepute, 1, 16776960);
        table.setItem("现有钱数：" + haveMoney, 2, 16777215);
        table.setItem("现有奖牌数：" + haveRepute, 3, 16777215);
        form.addComponentInCenter(label, (byte) 2);
        form.addComponentInCenter(table, (byte) 2);
        form.addComponentInCenter(lblTitle, (byte) 2);
        form.addRightButton("返回");
        MainCanvas.mc.baseForm.getSubForm().setAboutForm(form);
    }

    private void sendClanAltarActive(ByteArray ba) {
        int index = MainCanvas.mc.tables[0].getCurrentPointer() + 1;
        ba.writeByte(index);
    }

    private void receiveClanAltarActiveResult(ByteArray ba) {
        String msg;
        byte result = ba.readByte();
        switch (result) {
            case 0:
                msg = "祭坛激活成功！";
                break;
            case 1:
                msg = "祭坛激活失败！";
                break;
            default:
                msg = "祭坛激活失败，原因未知！";
                break;
        }
        alertOption(MainCanvas.mc.baseForm.getSubForm(), "altarActiveResult", msg, true, false);
    }

    private void sendClanWarList(ByteArray ba) {
        ba.writeInt(this.m_clanCurPage);
    }

    private void receiveClanWarList(ByteArray ba) {
        this.m_clanTotalPages = ba.readInt();
        this.m_clanCurPage = ba.readInt();
        byte nums = ba.readByte();
        this.m_clanNames = null;
        this.m_clanNames = new String[nums];
        this.m_clanLvs = null;
        this.m_clanLvs = new byte[nums];
        for (int i = 0; i < nums; i++) {
            this.m_clanNames[i] = ba.readUTF();
            this.m_clanLvs[i] = ba.readByte();
        }
        UIForm form = createFormForListStyle("clanList", "氏族列表", "选择");
        if (nums > 0) {
            UITable table = createTableForListStyle(nums);
            for (int j = 0; j < nums; j++) {
                table.setItem(this.m_clanNames[j] + "  " + this.m_clanLvs[j] + "级", j, 65280);
            }
            UILabel lblPage = createPageLabel(this.m_clanCurPage, this.m_clanTotalPages);
            form.addComponentInCenter(table, (byte) 2);
            form.addComponentInCenter(lblPage, (byte) 2);
            MainCanvas.mc.tables[0] = null;
            MainCanvas.mc.tables[0] = table;
            drawFormArrow1(form, this.m_clanCurPage, this.m_clanTotalPages);
        }
        MainCanvas.mc.baseForm.setAboutForm(form);
    }

    public static UILabel createPageLabel(int curPage, int totalPages) {
        String page = (curPage + 1) + "/" + totalPages;
        UILabel lblPage = new UILabel(0, 193, 0, 0, page, 15718814, (byte) 1, (byte) 0);
        return lblPage;
    }

    private void receiveClanWarJoinResult(ByteArray ba) {
        String msg;
        byte result = ba.readByte();
        switch (result) {
            case 0:
                msg = "申请参加氏族战争成功！";
                break;
            case 1:
                msg = "申请参加氏族战争失败！";
                break;
            default:
                msg = "申请参加氏族战争失败，原因未知！";
                break;
        }
        alertOption(MainCanvas.mc.baseForm, "joinResult", msg, true, false);
    }

    private void sendClanWarTeamList(ByteArray ba) {
        ba.writeByte(this.m_teamCurPage);
    }

    private void receiveClanWarTeamList(ByteArray ba) {
        this.m_teamTotalPages = ba.readByte();
        this.m_teamCurPage = ba.readByte();
        byte nums = ba.readByte();
        this.m_teamNames = null;
        this.m_teamSelected = null;
        this.m_teamNames = new String[nums];
        this.m_teamSelected = new boolean[nums];
        for (int i = 0; i < nums; i++) {
            this.m_teamNames[i] = ba.readUTF();
            this.m_teamSelected[i] = (ba.readByte() == 1);
        }
        UIForm form = createFormForListStyle("teamList", "精英战队伍列表", "选择");
        if (nums > 0) {
            UITable table = createTableForListStyle(nums);
            for (int j = 0; j < nums; j++) {
                int color = this.m_teamSelected[j] ? 65280 : 16777215;
                table.setItem(this.m_teamNames[j], j, color);
            }
            UILabel lblPage = createPageLabel(this.m_teamCurPage, this.m_teamTotalPages);
            form.addComponentInCenter(table, (byte) 2);
            form.addComponentInCenter(lblPage, (byte) 2);
            MainCanvas.mc.tables[0] = null;
            MainCanvas.mc.tables[0] = table;
            drawFormArrow(form, this.m_teamCurPage, this.m_teamTotalPages);
        }
        MainCanvas.mc.baseForm.setAboutForm(form);
    }

    private void receiveClanWarGroupList(ByteArray ba) {
        this.isSubmit = (ba.readByte() != 0);
        this.m_totalPages = ba.readByte();
        this.m_teamCurPage = ba.readByte();
        byte nums = ba.readByte();
        this.m_names = null;
        this.m_names = new String[nums];
        if (this.isSubmit) {
            this.m_activables = null;
            this.m_activables = new boolean[nums];
            for (int i = 0; i < nums; i++) {
                this.m_names[i] = ba.readUTF() + " " + ba.readByte() + "级";
                this.m_activables[i] = (ba.readByte() == 1);
            }
        } else {
            this.m_id = null;
            this.m_id = new int[nums];
            for (int i = 0; i < nums; i++) {
                this.m_id[i] = ba.readInt();
                this.m_names[i] = ba.readUTF() + " " + ba.readByte() + "级";
            }
        }
        UIForm form = createFormForListStyle("groupList", "团队战成员列表", (this.isSubmit || nums == 0) ? "" : "提交");
        if (nums > 0) {
            this.rbsSelect = new UIRadioButton[nums];
            for (int i = 0; i < nums; i++) {
                this.rbsSelect[i] = new UIRadioButton(25, 31 + i * 20, 0, 0, this.m_names[i], (byte) 2);
                this.rbsSelect[i].addItems("是");
                this.rbsSelect[i].addItems("否");
                if (i != 0) {
                    this.rbsSelect[i].setAroundComponent(this.rbsSelect[i - 1], (byte) 1);
                }
                form.addComponent(this.rbsSelect[i]);
                if (this.isSubmit) {
                    this.rbsSelect[i].setChooseItem(this.m_activables[i] ? 1 : 0);
                } else {
                    this.rbsSelect[i].setChooseItem(1);
                    for (int j = 0; j < 10; j++) {
                        if (this.selectId[j] == this.m_id[i]) {
                            this.rbsSelect[i].setChooseItem(0);
                            break;
                        }
                    }
                }
            }
            UILabel lblPage = createPageLabel(this.m_teamCurPage, this.m_totalPages);
            form.addComponentInCenter(lblPage, (byte) 2);
            MainCanvas.mc.buttons[0] = null;
            MainCanvas.mc.buttons[1] = null;
            MainCanvas.mc.buttons[0] = new UIButton(21, 31 + nums * 20, 0, 0, "上一页", (byte) 0);
            MainCanvas.mc.buttons[1] = new UIButton(104, 31 + nums * 20, 0, 0, "下一页", (byte) 0);
            boolean up = (this.m_teamCurPage > 0), down = (this.m_teamCurPage < this.m_totalPages - 1);
            if (up) {
                form.addComponent(MainCanvas.mc.buttons[0]);
                this.rbsSelect[nums - 1].setAroundComponent(MainCanvas.mc.buttons[0], (byte) 2);
            } else if (down) {
                this.rbsSelect[nums - 1].setAroundComponent(MainCanvas.mc.buttons[1], (byte) 2);
            }
            if (down) {
                form.addComponent(MainCanvas.mc.buttons[1]);
                this.rbsSelect[0].setAroundComponent(MainCanvas.mc.buttons[1], (byte) 1);
            } else if (up) {
                this.rbsSelect[0].setAroundComponent(MainCanvas.mc.buttons[0], (byte) 1);
            }
            if (down && up) {
                MainCanvas.mc.buttons[0].setAroundComponent(MainCanvas.mc.buttons[1], (byte) 4);
                MainCanvas.mc.buttons[0].setAroundComponent(MainCanvas.mc.buttons[1], (byte) 2);
            }
        }
        MainCanvas.mc.baseForm.setAboutForm(form);
    }

    private void sendClanWarSelectTeam(ByteArray ba) {
        int index = MainCanvas.mc.tables[0].getCurrentPointer();
        ba.writeUTF(this.m_teamNames[index]);
    }

    private void receiveClanWarSelectTeamResult(ByteArray ba) {
        String msg;
        byte result = ba.readByte();
        switch (result) {
            case 0:
                msg = "选择参战队伍成功！";
                break;
            case 1:
                msg = "选择参战队伍失败！";
                break;
            case 2:
                msg = ba.readUTF();
                break;
            default:
                msg = "选择参战队伍失败，原因未知！";
                break;
        }
        alertOption(MainCanvas.mc.baseForm.getSubForm(), "selectTeamResult", msg, true, false);
    }

    public static UIForm createFormForNpcMenuStyle(String formName) {
        UIForm form = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, formName);
        form.setStyle((byte) 0);
        UIRim[] rims = new UIRim[3];
        rims[0] = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte) 4);
        rims[1] = new UIRim(8, 6, 39, 36, (byte) 1);
        rims[2] = new UIRim(52, 4, 117, 40, (byte) 4);
        Image img = Image.createImage(18, 30);
        Graphics gg = img.getGraphics();
        gg.setColor(0);
        gg.fillRect(0, 0, 18, 30);
        byte npcId = ObjManager.currentTarget.imgID;
        GameObj.drawNpcForUi(gg, npcId, 9, 30);
        UIMImage mImage = new UIMImage((rims[1]).positionX + 10, (rims[1]).positionY + 3, 0, 0, new MImage(img), (byte) 0);
        mImage.setCanFocus(false);
        UILabel[] labels = new UILabel[2];
        labels[0] = new UILabel((rims[2]).positionX + 8, (rims[2]).positionY + 6, 0, 0, ObjManager.currentTarget.name, 15718814, (byte) 0, (byte) 0);
        labels[1] = new UILabel((labels[0]).positionX, (labels[0]).positionY + 17, 0, 0, "等级 " + ObjManager.currentTarget.level, 10321225, (byte) 0, (byte) 0);
        byte i;
        for (i = 0; i < 3; i = (byte) (i + 1)) {
            form.addComponent(rims[i]);
        }
        for (i = 0; i < 2; i = (byte) (i + 1)) {
            form.addComponent(labels[i]);
        }
        form.addComponent(mImage);
        form.addLeftButton("确定");
        form.addRightButton("返回");
        return form;
    }

    public static UIForm createFormForListStyle(String formName, String titleName, String leftCmdName) {
        UIForm form = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, formName);
        form.setBackGround((byte) 9);
        UIRim frame = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte) 4);
        UIRim rimTitle = new UIRim(0, 9, 166, 20, (byte) 7);
        UILabel lblTitle = new UILabel(0, 12, 176, 0, titleName, 15718814, (byte) 1, (byte) 0);
        UIRim rimDown = new UIRim(0, 29, 166, 160, (byte) 0);
        form.addComponent(frame);
        form.addComponentInCenter(rimTitle, (byte) 2);
        form.addComponentInCenter(lblTitle, (byte) 2);
        form.addComponentInCenter(rimDown, (byte) 2);
        if (leftCmdName != null) {
            form.addLeftButton(leftCmdName);
        }
        form.addRightButton("返回");
        return form;
    }

    public static UITable createTableForListStyle(int nums) {
        UITable table = new UITable(0, 31, 161, 156, nums, 1, (nums > 7) ? 7 : nums, (byte) 0, (byte) 3);
        table.setSingleWH(table.singleWidth, (byte) 22, false);
        return table;
    }

    private void sendClanWarLetter(ByteArray ba) {
        int index = MainCanvas.mc.tables[0].getCurrentPointer();
        ba.writeUTF(this.m_clanNames[index]);
        ba.writeByte(this.m_warType);
    }

    private void receiveClanWarLetterResult(ByteArray ba) {
        String msg;
        byte result = ba.readByte();
        switch (result) {
            case 0:
                msg = "下战书成功！";
                break;
            case 1:
                msg = "氏族名字不存在，下战书失败！";
                break;
            case 2:
                msg = "该氏族正在进行战争，下战书失败！";
                break;
            case 3:
                msg = "您的金钱不足，下战书失败！";
                break;
            case 4:
                msg = "您的声望为0，下战书失败！";
                break;
            case 5:
                msg = "对方的声望为0，下战书失败！";
                break;
            default:
                msg = "下战书失败，原因未知！";
                break;
        }
        alertOption(MainCanvas.mc.baseForm.getSubForm(), "letterResult", msg, true, false);
    }

    private void receiveClanWarAcceptResult(ByteArray ba) {
        String msg;
        byte result = ba.readByte();
        switch (result) {
            case 0:
                msg = "接受战书成功！";
                break;
            case 1:
            case 2:
                msg = "接受战书失败！";
                break;
            default:
                msg = "接受战书失败，原因未知！";
                break;
        }
        alertOption(MainCanvas.mc.baseForm, "acceptResult", msg, true, false);
    }

    private void receiveClanWarRefuseResult(ByteArray ba) {
        String msg;
        byte result = ba.readByte();
        switch (result) {
            case 0:
                msg = "拒绝战书成功！";
                break;
            case 1:
            case 2:
                msg = "拒绝战书失败！";
                break;
            default:
                msg = "拒绝战书失败，原因未知！";
                break;
        }
        alertOption(MainCanvas.mc.baseForm, "refuseResult", msg, true, false);
    }

    private void sendClanGodList(ByteArray ba) {
    }

    private void receiveClanGodList(ByteArray ba) {
        byte nums = ba.readByte();
        this.m_godNames = null;
        this.m_godActivables = null;
        this.m_godNames = new String[nums];
        this.m_godActivables = new boolean[nums];
        for (int i = 0; i < nums; i++) {
            this.m_godNames[i] = ba.readUTF();
            this.m_godActivables[i] = (ba.readByte() == 1);
        }
        createListTableForm1("godList", "守护神列表", "操作", this.m_godNames, this.m_godActivables);
    }

    private void createListTableForm1(String formName, String titleName, String leftCmdName, String[] itemNames, boolean[] activables) {
        UIForm form = createFormForListStyle(formName, titleName, leftCmdName);
        int itemNums = itemNames.length;
        if (itemNums > 0) {
            UITable table = createTableForListStyle(itemNums);
            for (int i = 0; i < itemNums; i++) {
                int color = activables[i] ? 10981737 : 7112335;
                table.setItem(itemNames[i], i, color);
            }
            form.addComponentInCenter(table, (byte) 2);
            MainCanvas.mc.tables[0] = null;
            MainCanvas.mc.tables[0] = table;
        }
        MainCanvas.mc.baseForm.setAboutForm(form);
    }

    private void sendClanGodInfo(ByteArray ba) {
        int index = MainCanvas.mc.tables[0].getCurrentPointer();
        ba.writeUTF(this.m_godNames[index]);
    }

    private void receiveClanGodInfo(ByteArray ba) {
        String description = ba.readUTF();
        int needMoney = ba.readInt();
        int needMedal = ba.readInt();
        int haveMoney = ba.readInt();
        int haveMedal = ba.readInt();
        UIForm form = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "godInfo");
        form.setBackGround((byte) 9);
        int index = MainCanvas.mc.tables[0].getCurrentPointer();
        String title = this.m_godNames[index];
        UILabel lblTitle = new UILabel(0, 12, 176, 0, title, 15718814, (byte) 1, (byte) 0);
        UILabel label = new UILabel(0, 40, 166, 0, description, 16777215, (byte) 0, (byte) 0);
        UITable table = new UITable(0, 80, 166, 80, 4, 1, 4, (byte) 0, (byte) 0);
        table.setRim(false);
        table.setItem("需要钱数：" + needMoney, 0, 16776960);
        table.setItem("需要奖牌数：" + needMedal, 1, 16776960);
        table.setItem("现有钱数：" + haveMoney, 2, 16777215);
        table.setItem("现有奖牌数：" + haveMedal, 3, 16777215);
        form.addComponentInCenter(label, (byte) 2);
        form.addComponentInCenter(table, (byte) 2);
        form.addComponentInCenter(lblTitle, (byte) 2);
        form.addRightButton("返回");
        MainCanvas.mc.baseForm.getSubForm().setAboutForm(form);
    }

    private void sendClanGodActive(ByteArray ba) {
        int index = MainCanvas.mc.tables[0].getCurrentPointer() + 1;
        ba.writeByte(index);
    }

    private void receiveClanGodActiveResult(ByteArray ba) {
        String msg;
        byte result = ba.readByte();
        switch (result) {
            case 0:
                msg = "守护神激活成功！";
                break;
            case 1:
                msg = "守护神激活失败！";
                break;
            default:
                msg = "守护神激活失败，原因未知！";
                break;
        }
        alertOption(MainCanvas.mc.baseForm.getSubForm(), "godActiveResult", msg, true, false);
    }

    private void receiveClanBuildingMenu(ByteArray ba, String[] menuItems, byte npcSubstate) {
        MainCanvas.mc.releaseUI();
        boolean[] operatables = new boolean[2];
        operatables[0] = (ba.readByte() == 1);
        if (operatables[0]) {
            this.m_buildMoney = ba.readInt();
            this.m_buildMedal = ba.readInt();
        }
        operatables[1] = (ba.readByte() == 1);
        MainCanvas.mc.baseForm = createFormForNpcMenuStyle("buildingMenu");
        MainCanvas.mc.menus[0] = new UIMenu(5, 48, 164, 142, null, menuItems);
        MainCanvas.mc.menus[0].setRimStyle((byte) 0);
        MainCanvas.mc.menus[0].setFlushType((byte) 1);
        for (int i = 0; i < operatables.length; i++) {
            if (!operatables[i]) {
                MainCanvas.mc.menus[0].setNoUse((byte) i);
            }
        }
        MainCanvas.mc.baseForm.addComponentInCenter(MainCanvas.mc.menus[0], (byte) 2);
        MainCanvas.mc.baseForm.setFocus(true);
        MainCanvas.mc.setNPCSubState(npcSubstate);
    }

    private void receiveClanBuildingBuildResult(ByteArray ba, String buildingName) {
        byte result = ba.readByte();
        StringBuffer msg = new StringBuffer();
        switch (result) {
            case 0:
                MainCanvas.mc.menus[0].setNoUse((byte) 0);
                msg.append("创建氏族");
                msg.append(buildingName);
                msg.append("成功！");
                break;
            case 1:
                msg.append("创建氏族");
                msg.append(buildingName);
                msg.append("失败！");
                break;
            default:
                msg.append("创建氏族");
                msg.append(buildingName);
                msg.append("失败，原因未知！");
                break;
        }
        alertOption(MainCanvas.mc.baseForm, "buildingBuildResult", msg
                .toString(), true, false);
    }

    private void receiveClanBuildingInfo(ByteArray ba, String title) {
        String description = ba.readUTF();
        UIForm form = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "buildingInfo");
        form.setBackGround((byte) 9);
        UILabel lblTitle = new UILabel(0, 12, 176, 0, title, 15718814, (byte) 1, (byte) 0);
        UILabel label = new UILabel(0, 40, 160, 0, description, 16777215, (byte) 0, (byte) 0);
        form.addComponentInCenter(label, (byte) 2);
        form.addComponentInCenter(lblTitle, (byte) 2);
        form.addRightButton("返回");
        MainCanvas.mc.baseForm.setAboutForm(form);
    }

    private void receiveClanTop(ByteArray ba) {
        this.m_totalPages = ba.readByte();
        this.m_curPage = ba.readByte();
        byte nums = ba.readByte();
        this.m_names = null;
        this.m_points = null;
        this.m_names = new String[nums];
        this.m_points = new int[nums];
        String[] itemNames = new String[nums * 2];
        for (int i = 0; i < nums; i++) {
            this.m_names[i] = ba.readUTF();
            this.m_points[i] = ba.readInt();
            byte order = ba.readByte();
            itemNames[i * 2] = order + "  " + this.m_names[i];
            itemNames[i * 2 + 1] = "  声望: " + this.m_points[i];
        }
        MainCanvas.mc.releaseUI();
        MainCanvas.mc.baseForm = createListTableForm2("clanTop", "氏族排行", null, itemNames, this.m_curPage, this.m_totalPages);
        MainCanvas.mc.baseForm.setFocus(true);
        MainCanvas.mc.setNPCSubState((byte) 52);
    }

    private void receiveClanMemTop(ByteArray ba) {
        byte result = ba.readByte();
        if (result == 0) {
            alertOption(MainCanvas.mc.baseForm, "clanMemTopNo", "您需要先加入一个氏族才能查看", true, false);
            MainCanvas.mc.setNPCSubState((byte) 53);
        } else {
            this.m_totalPages = ba.readByte();
            this.m_curPage = ba.readByte();
            byte nums = ba.readByte();
            this.m_names = null;
            this.m_points = null;
            this.m_names = new String[nums];
            this.m_points = new int[nums];
            String[] itemNames = new String[nums * 2];
            for (int i = 0; i < nums; i++) {
                this.m_names[i] = ba.readUTF();
                this.m_points[i] = ba.readInt();
                itemNames[i * 2] = this.m_names[i];
                itemNames[i * 2 + 1] = "  贡献:" + this.m_points[i] + " + " + ba
                        .readInt();
            }
            MainCanvas.mc.baseForm.getSubForm().setAboutForm(null);
            MainCanvas.mc.baseForm.getSubForm().setAboutForm(
                    createListTableForm2("clanMemTop", "氏族成员贡献榜", "查看", itemNames, this.m_curPage, this.m_totalPages));
            MainCanvas.mc.baseForm.setFocus(true);
            MainCanvas.mc.setNPCSubState((byte) 53);
        }
    }

    private void receiveClanEnterArea(ByteArray ba) {
        byte result = ba.readByte();
        if (result == 0) {
            alertOption(MainCanvas.mc.baseForm, "clanEnterAreaNo", "您需要先加入一个氏族才能进入", true, false);
            MainCanvas.mc.setNPCSubState((byte) 53);
        } else if (result == 2) {
            alertOption(MainCanvas.mc.baseForm, "clanEnterAreaNo", "您所在的氏族等级不够，无法进入", true, false);
            MainCanvas.mc.setNPCSubState((byte) 53);
        }
    }

    private UIForm createListTableForm2(String formName, String titleName, String leftCmdName, String[] itemNames, byte curPage, byte totalPages) {
        UIForm form = createFormForListStyle(formName, titleName, leftCmdName);
        int nums = itemNames.length;
        if (nums > 0) {
            UITable table = new UITable(0, 31, 161, 152, nums, 1, (nums > 8) ? 8 : nums, (byte) 0, (byte) 3);
            table.setSingleWH(table.singleWidth, (byte) 19, false);
            for (int i = 0; i < nums; i++) {
                table.setItem(itemNames[i], i, 65280);
            }
            UILabel lblPage = createPageLabel(curPage, totalPages);
            form.addComponentInCenter(table, (byte) 2);
            form.addComponentInCenter(lblPage, (byte) 2);
            MainCanvas.mc.tables[0] = null;
            MainCanvas.mc.tables[0] = table;
        }
        drawFormArrow(form, curPage, totalPages);
        return form;
    }

    void drawFormArrow(UIForm form, int curPage, int totalPages) {
        UIMImage leftImg = null;
        UIMImage rightImg = null;
        leftImg = new UIMImage(55, 196, 0, 0, MainCanvas.mImgUI[22], (byte) 0);
        leftImg.setCurrentFrame((byte) 0);
        leftImg.setNumberVisiable(false);
        rightImg = new UIMImage(120, 196, 0, 0, MainCanvas.mImgUI[22], (byte) 0);
        rightImg.setCurrentFrame((byte) 1);
        rightImg.setNumberVisiable(false);
        if (curPage > 0) {
            form.addComponent(leftImg);
        }
        if (curPage < totalPages - 1) {
            form.addComponent(rightImg);
        }
    }

    void drawFormArrow1(UIForm form, int curPage, int totalPages) {
        UIMImage leftImg = null;
        UIMImage rightImg = null;
        leftImg = new UIMImage(55, 196, 0, 0, MainCanvas.mImgUI[22], (byte) 0);
        leftImg.setCurrentFrame((byte) 0);
        leftImg.setNumberVisiable(false);
        rightImg = new UIMImage(120, 196, 0, 0, MainCanvas.mImgUI[22], (byte) 0);
        rightImg.setCurrentFrame((byte) 1);
        rightImg.setNumberVisiable(false);
        if (totalPages > 1) {
            form.addComponent(leftImg);
            form.addComponent(rightImg);
        }
    }

    public static void alertOption(UIForm form, String subFormName, String message, boolean left, boolean right) {
        byte flag = 1;
        if (right) {
            flag = 2;
        }
        form.addAboutForm(subFormName, message, flag, 180, 50);
    }

    private void tickClanNotice(UIForm form, UIComponent c) {
        if (MainCanvas.isKeyPress(10)) {
            if (this.m_curPage > 0) {
                this.m_curPage = (byte) (this.m_curPage - 1);
                MainCanvas.startWait(form);
                NetInterface.getInstance().send(302317568);
            }
        } else if (MainCanvas.isKeyPress(12)) {
            if (this.m_curPage < this.m_totalPages - 1) {
                this.m_curPage = (byte) (this.m_curPage + 1);
                MainCanvas.startWait(form);
                NetInterface.getInstance().send(302317568);
            }
        } else if (MainCanvas.isKeyPressOk()) {
            if (MainCanvas.mc.clanManegeLevel != 0) {
                UIForm subForm = new UIForm(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, "notice_Left_Menu");
                subForm.setBackGround((byte) 9);
                UIMenu menu = new UIMenu(35, 0, 80, 0, null, new String[]{"查看公告", "添加公告", "修改公告", "删除公告"});
                if (this.notice_info.length == 0) {
                    menu.setNoUse((byte) 0);
                    menu.setNoUse((byte) 2);
                    menu.setNoUse((byte) 3);
                    if (MainCanvas.mc.clanManegeLevel == 3) {
                        menu.setNoUse((byte) 1);
                        return;
                    }
                } else if (MainCanvas.mc.clanManegeLevel == 3) {
                    menu.setNoUse((byte) 1);
                    menu.setNoUse((byte) 2);
                    menu.setNoUse((byte) 3);
                } else {
                    int index = MainCanvas.mc.tables[0].getCurrentPointer();
                    if (MainCanvas.mc.clanManegeLevel == 2
                            && !this.notice_isChange[index]) {
                        menu.setNoUse((byte) 2);
                        menu.setNoUse((byte) 3);
                    }
                    if (MainCanvas.mc.clanManegeLevel == 1
                            && this.notice_color[index] == 0) {
                        menu.setNoUse((byte) 2);
                    }
                }
                subForm.addComponentInCenter(menu, (byte) 5);
                MainCanvas.mc.baseForm.getCurrentFocusForm().setAboutForm(subForm);
                MainCanvas.mc.baseForm.setFocus(true);
                MainCanvas.mc.menus[1] = null;
                MainCanvas.mc.menus[1] = menu;
            }
        } else if (MainCanvas.isKeyPress(18)) {
            MainCanvas.mc.baseForm.setAboutForm(null);
        } else {
            actionInForm(c);
        }
    }

    private void tickClanNoticeOperation(UIForm form, UIComponent c) {
        if (MainCanvas.isKeyPress(18)) {
            MainCanvas.mc.baseForm.getSubForm().setAboutForm(null);
        } else if (MainCanvas.isKeyPressOk()) {
            int index;
            StringBuffer title;
            UIForm form1;
            UILabel label;
            MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm()
                    .setAboutForm(null);
            switch (MainCanvas.mc.menus[1].getCurrentPointer()) {
                case 0:
                    index = MainCanvas.mc.tables[0].getCurrentPointer();
                    title = new StringBuffer();
                    switch (this.notice_color[index]) {
                        case 0:
                            title.append("系统公告");
                            break;
                        case 1:
                            title.append("族长公告");
                            break;
                        case 2:
                            title.append("长老公告");
                            break;
                    }
                    form1 = createFormForListStyle("noticeInfo", title
                            .toString(), null);
                    label = new UILabel(0, 40, 160, 0, this.notice_info[index], CLAN_NOTICE_COLOR[this.notice_color[index]], (byte) 0, (byte) 0);
                    form1.addComponentInCenter(label, (byte) 2);
                    MainCanvas.mc.baseForm.getSubForm().setAboutForm(null);
                    MainCanvas.mc.baseForm.getSubForm().setAboutForm(form1);
                    MainCanvas.mc.baseForm.setFocus(true);
                    return;
                case 1:
                    PCChat.isClanNoticeAdd = 1;
                    PCChat.isPrivateChannel = false;
                    MainCanvas.localChatChannel = 3;
                    MainCanvas.mc.initChatForm("发送到氏族频道:");
                    break;
                case 2:
                    PCChat.isClanNoticeAdd = 2;
                    PCChat.isPrivateChannel = false;
                    MainCanvas.localChatChannel = 3;
                    MainCanvas.mc.initChatForm("发送到氏族频道:");
                    MainCanvas.mc.chatText
                            .setString(this.notice_info[MainCanvas.mc.tables[0]
                            .getCurrentPointer()]);
                    break;
                case 3:
                    alertOption(MainCanvas.mc.baseForm.getCurrentFocusForm(), "notice_Del_No", "您确定要删除该公告？", true, true);
                    break;
            }
        } else {
            actionInForm(c);
        }
    }

    private void tickAcceptDelNotice() {
        if (MainCanvas.isKeyPressOk()) {
            MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm()
                    .setAboutForm(null);
            NetInterface.getInstance().send(302448640);
            MainCanvas.startWait(MainCanvas.mc.baseForm.getCurrentFocusForm());
        } else if (MainCanvas.isKeyPress(18)) {
            MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm()
                    .setAboutForm(null);
        }
    }

    private void tickClanNoticeNo(UIForm form, UIComponent c) {
        if (MainCanvas.isKeyPressOk()) {
            MainCanvas.startWait(form);
            MainCanvas.ni.send(302317568);
        }
    }

    private void tickClanNoticeInfo(UIForm form, UIComponent c) {
        if (MainCanvas.isKeyPress(18)) {
            MainCanvas.mc.baseForm.getSubForm().setAboutForm(null);
        }
    }

    public void setM_curPage(byte page) {
        this.m_curPage = page;
    }

    public void setCurr_notice_info(String curr_notice_info) {
        this.curr_notice_info = curr_notice_info;
    }

    private void receiveNoticeInfo(ByteArray ba) {
        MainCanvas.mc.clanManegeLevel = ba.readByte();
        if (MainCanvas.mc.clanManegeLevel == 0) {
            alertOption(MainCanvas.mc.baseForm, "clanMemTopNo", "您需要先加入一个氏族才能查看", true, false);
        } else {
            this.m_totalPages = ba.readByte();
            this.m_curPage = ba.readByte();
            byte nums = ba.readByte();
            this.notice_id = null;
            this.notice_info = null;
            this.notice_color = null;
            this.notice_isChange = null;
            this.notice_id = new int[nums];
            this.notice_info = new String[nums];
            this.notice_color = new byte[nums];
            this.notice_isChange = new boolean[nums];
            UIForm form = null;
            if (MainCanvas.mc.clanManegeLevel == 3 && nums == 0) {
                form = createFormForListStyle("notice", "氏族公告信息", null);
            } else {
                form = createFormForListStyle("notice", "氏族公告信息", "操作");
            }
            if (nums > 0) {
                UITable table = new UITable(0, 31, 161, 152, nums, 1, (nums > 7) ? 7 : nums, (byte) 0, (byte) 3);
                table.setSingleWH(table.singleWidth, (byte) 22, false);
                for (int i = 0; i < nums; i++) {
                    this.notice_id[i] = ba.readInt();
                    this.notice_info[i] = ba.readUTF();
                    this.notice_color[i] = ba.readByte();
                    this.notice_isChange[i] = (ba.readByte() == 0);
                    if (this.notice_info[i].length() > 10) {
                        table.setItem(this.notice_info[i].substring(0, 7) + "…", i, CLAN_NOTICE_COLOR[this.notice_color[i]]);
                    } else {
                        table.setItem(this.notice_info[i], i, CLAN_NOTICE_COLOR[this.notice_color[i]]);
                    }
                }
                UILabel lblPage = createPageLabel(this.m_curPage, this.m_totalPages);
                form.addComponentInCenter(table, (byte) 2);
                form.addComponentInCenter(lblPage, (byte) 2);
                MainCanvas.mc.tables[0] = null;
                MainCanvas.mc.tables[0] = table;
            }
            drawFormArrow(form, this.m_curPage, this.m_totalPages);
            MainCanvas.mc.baseForm.setAboutForm(null);
            MainCanvas.mc.baseForm.setAboutForm(form);
            MainCanvas.mc.baseForm.setFocus(true);
        }
    }

    private void receiveNoticeAdd(ByteArray ba, int index) {
        String type = "";
        switch (index) {
            case 0:
                type = "增加";
                break;
            case 1:
                type = "修改";
                break;
            case 2:
                type = "删除";
                break;
        }
        MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm()
                .setAboutForm(null);
        byte tempByte = ba.readByte();
        if (tempByte == 0) {
            alertOption(MainCanvas.mc.baseForm.getSubForm(), "notice_Add_No", type + "氏族公告成功!", true, false);
        } else if (tempByte == 1) {
            alertOption(MainCanvas.mc.baseForm.getSubForm(), "notice_Add_No", type + "氏族公告失败!", true, false);
        } else if (tempByte == 2) {
            switch (MainCanvas.mc.clanManegeLevel) {
                case 1:
                    type = "族长";
                    break;
                case 2:
                    type = "长老";
                    break;
            }
            alertOption(MainCanvas.mc.baseForm.getSubForm(), "notice_Add_No", type + "公告栏已满，请适当删除一些消息才能继续留言!", true, false);
        }
    }

    private void receiveGensApplyFarm(ByteArray ba) {
        String msg = ba.readUTF();
        alertOption(MainCanvas.mc.baseForm, "msg", msg, true, false);
    }
}
