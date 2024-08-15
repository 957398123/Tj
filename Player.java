
import javax.microedition.lcdui.Graphics;

public class Player extends OtherPlayer {

    public static int CDQ = 0;
    public static short CDQ_T = 0;
    public byte collectionCount = 0;
    public boolean isSendMoveMsg = true;
    private static final byte COLLECTION_MAX = 20;
    public static long[] skillCD = new long[15];
    public static byte normalAttackCount = 30;
    private static final byte NORMAL_ATTACK_COUNT_MAX = 30;
    public static byte[] skillLevels = new byte[14];
    private int[][] projectionAcme = new int[8][2];
    public static final int FOLLOW_NO_AIM = -1;
    /**
     * 寻径目标
     */
    public int followAimID = -1;
    public static byte[] userDefinedSkills;
    public static short itemCount = 0;
    public static boolean[] canUseSkill;
    private byte currentPressKey = 0;
    public static final byte INVALIDATION = -1;
    public static final byte SHORT_CUT_NUM = 9;
    private int castLength = 11;
    private long castTick = 0L;
    static int mommathMoney = -1;
    private static Player instance = null;
    long st;

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    public void init(ByteArray initIn) {
        try {
            curStep = 0;
            objID = initIn.readInt();
            x = initIn.readInt();
            y = initIn.readInt();
            imgID = initIn.readByte();
            originalImgID = imgID;
            initData();
            initIn.readByte();
            int tempWeapon = initIn.readByte();
            originalWeapon = tempWeapon;
            if (currentImgID == -1) {
                setCurWeapon(tempWeapon);
            }
            level = initIn.readByte();
            name = initIn.readUTF();
            maxHp = initIn.readInt();
            lastHp = curHp = initIn.readInt();
            maxMp = initIn.readInt();
            curMp = initIn.readInt();
            col = Map.getCurrentColumn(y, x);
            row = Map.getCurrentRow(y, x);
            setDirection((byte) 2);
            setAimRowAndColumn(row, col);
            Map.putInCell(col, row);
            setState((byte) 0);
            initEditorRes(imgID);
            if (imgID <= 3) {
                group = 1;
            } else {
                group = 2;
            }
            for (int i = 0; i < MainCanvas.popRecord.length; i++) {
                MainCanvas.popRecord[i] = 0;
            }
            loadDefinedKeyRecord();
            byte[] tmp = Util.readRecord(name);
            if (tmp != null) {
                MainCanvas.popRecord = tmp;
            }
            if (MainCanvas.isLogin) {
                MainCanvas.mc.setState((byte) 5);
            } else {
                MainCanvas.mc.setState((byte) 17);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setCurrentXp(int xp) {
        Player p = getInstance();
        p.exp = xp;
    }

    public static int getXp() {
        return (getInstance()).exp;
    }

    public static void setMaxXp(int xp) {
        Player p = getInstance();
        p.maxExp = xp;
    }

    public void setState(byte s) {
        super.setState(s);
        if (state == 7) {
            short[] skillData = getSkillData(profession, skillIndex);
            castLength = skillData[4] * 1000;
            castTick = System.currentTimeMillis();
            MainCanvas.ni.send(33557760);
        }
        if (state == 4) {
            if (hpStates.size() != 0) {
                hpStates.removeAllElements();
            }
            if (collectionCount != 0) {
                collectionCount = 0;
            }
        }
        if (state == 2
                && Cons.audioOpen) {
            MainCanvas.sound.playSound(2, 1, true);
        }
        if (state == 9) {
            collectionCount = 0;
        }
    }

    private Player() {
        st = 0L;
        arrowTick = 0;
        canMoveIndex = new int[][]{{1, 0, 4}, {2, 3, 6}, {0, 3, 7}, {1, 2, 5}};
        stepIndex = new int[][]{{-1, -1, -1, -1, 1, 1, 1, -1, -1, 1}, {-1, 1, -1, 1, 1, -1, 1, 1, -1, -1}, {-1, -1, -1, 1, 1, -1, -1, -1, 1, 1}, {1, -1, 1, 1, -1, -1, 1, -1, -1, 1}};
        findpath = true;
        type = 4;
    }
    static int cheatTime = 0;
    static int totalTime = 0;
    private byte arrowTick;
    int[][] canMoveIndex;
    int[][] stepIndex;
    private int oldSkillTargetId;
    /**
     * 玩家寻径路径（A星算法）
     */
    public int[][] path;
    public boolean findpath;

    /**
     * 玩家tick
     */
    public void tick() {
        ExpandAbility.beforePlayerTick(this);
        // 这里是如果太长时间未通讯，退出
        if (totalTime <= 100) {
            long l = System.currentTimeMillis() - st;
            st = System.currentTimeMillis();
            totalTime++;
            if (l < 92L) {
                cheatTime++;
                if (cheatTime > 60) {
                    MainCanvas.mc.aMidlet.exitMIDlet();
                }
            }
        }
        // 如果玩家移动了，每隔固定时间发送移动消息
        if (isSendMoveMsg && MainCanvas.countTick % 5 == 0 && (oldRow != row || oldCol != col)) {
            MainCanvas.ni.send(Cmd.C_PLAYER_MOVE);
            oldRow = row;
            oldCol = col;
        }
        // 如果玩家血量为0，设置玩家死亡
        if (curHp <= 0) {
            setState((byte) 5);
        }
        // 更新技能CD
        skillCDAdd(this);
        // 更新普通攻击计数
        if (normalAttackCount != 30) {
            normalAttackCount = (byte) (normalAttackCount + 1);
        }
        // 更新技能使用CD
        if (itemCount > 0) {
            itemCount = (short) (itemCount - 1);
            if (itemCount == 0 && userDefinedSkills != null) {
                for (int i = 0; i < userDefinedSkills.length; i++) {
                    if (userDefinedSkills[i] > 14) {
                        canUseSkill[i] = true;
                    }
                }
            }
        }
        // 更新buffer
        tickBuffer();
        tickHpChangeVectorPop();
        checkSkill();
        // 未实现
        follow();
        // 如果有寻径，进行寻径
        if (path != null && path.length > 0) {
            movePath();
        }
        // 如果有退出菜单
        if (MainCanvas.mc.topForm != null && "leaveForm".equals(MainCanvas.mc.topForm.getName())) {
            return;
        }
        // 根据玩家状态处理
        switch (state) {
            case 0: {  // 静止
                keyInStand();
                setRowCol(getRow(x, y), getCol(x, y));
                getCollideRectAcmeColumnAndRow();
                break;
            }
            case 1: {  // 行走
                keyInMove();
                setRowCol(getRow(x, y), getCol(x, y));
                getCollideRectAcmeColumnAndRow();
                checkChangeMap();
                break;
            }
            case 2: {  // 普通攻击
                keyInNomalFight();
                break;
            }
            case 5: {  // 死亡
                resetAimID();
                MainCanvas.cancelBusiness();
                MainCanvas.mc.setGameState((byte) 8);
                if (MainCanvas.mc.getOtherSubState() != 4 && MainCanvas.mc.getOtherSubState() != 3) {
                    MainCanvas.mc.setOtherSubState((byte) 1);
                }
                break;
            }
            case 7: {  // 释放技能状态
                keyInSkillPre();
                if (castLength == 0) {
                    if (!checkSkillObj(this, ObjManager.currentTarget, skillIndex, true)) {
                        castTick = 0L;
                        setState((byte) 0);
                        return;
                    }
                    if (Cons.audioOpen) {
                        MainCanvas.sound.playSound(3, 1, true);
                    }
                    setState((byte) 8);
                    useSkill(ObjManager.currentTarget, skillIndex, direction);
                    curMp -= getSkillMP(skillIndex);
                    MainCanvas.ni.send(Cmd.C_PLAYER_FIGHT_START);
                    break;
                }
                if (System.currentTimeMillis() - castTick >= castLength) {
                    if (!checkSkillObj(this, ObjManager.currentTarget, skillIndex, true)) {
                        castTick = 0L;
                        setState((byte) 0);
                        return;
                    }
                    setState((byte) 8);
                    useSkill(ObjManager.currentTarget, skillIndex, direction);
                    curMp -= getSkillMP(skillIndex);
                    MainCanvas.ni.send(Cmd.C_PLAYER_FIGHT_START);
                }
                break;
            }
            case 9: {
                collectionCount = (byte) (collectionCount + 1);
                keyInCollection();
                if (collectionCount > 20) {
                    MainCanvas.ni.send(Cmd.C_PET_COLLECTION);
                    MainCanvas.resetKey();
                    setState((byte) 0);
                    collectionCount = 0;
                }
                break;
            }
        }
        // 更新下一帧动作
        nextFrame();
        // 设置当前可行走
        Map.putInCell(col, row);
        if (UIGameRun.encryptImg != null) {
            CDQ = UIGameRun.getWait();
        }
        ExpandAbility.afterPlayerTick(this);
    }

    private void keyInCollection() {
        if (MainCanvas.isPop) {
            pressPop();
            return;
        }
        if (MainCanvas.isMenu) {
            return;
        }
        if (pressArrowKey()) {
            return;
        }
    }

    private void keyInSkillPre() {
        if (MainCanvas.isPop) {
            pressPop();
            return;
        }
        if (MainCanvas.isMenu) {
            return;
        }
        if (pressArrowKey()) {
            return;
        }
    }

    private void keyInNomalFight() {
        if (MainCanvas.isPop) {
            pressPop();
            return;
        }
        if (MainCanvas.isMenu) {
            return;
        }
        if (pressArrowKey()) {
            return;
        }
        if (pressSkillKey()) {
            setState((byte) 7);
        }
    }

    private void keyInMove() {
        if (MainCanvas.isPop) {
            pressPop();
            return;
        }
        if (MainCanvas.isMenu) {
            return;
        }
        if (pressArrowKey()) {
            path = null;
            return;
        }
        if (MainCanvas.isKeyPress(14) || (Cons.use5 && MainCanvas.isKeyPress(5))) {
            path = null;
            return;
        }
        if (pressSkillKey()) {
            setState((byte) 7);
        } else if (path != null && path.length > 0) {
            setState((byte) 1);
        } else if (followAimID == -1) {
            setState((byte) 0);
        }
    }

    private void keyInStand() {
        if (MainCanvas.isPop) {
            pressPop();
        } else if (MainCanvas.isMenu) {
        } else if (pressNomalFightOrGetInfo()) {
        } else if (pressArrowKey()) {
        } else if (MainCanvas.isKeyPress1(16) && ObjManager.currentTarget != null) {
            // 按了#键更改选中目标
            ObjManager.getInstance().changeTarget();
            MainCanvas.resetKey();
        } else if (Cons.nineShort == 1 && MainCanvas.shortcut_9[Cons.nineShort]) {
            ObjManager.getInstance().setCurrentTarget(getInstance());
            MainCanvas.shortcut_9[Cons.nineShort] = false;
        } else if (pressSkillKey()) {  // 如果按下技能键
            setState((byte) 7);
        }
    }

    private void pressPop() {
        if (MainCanvas.isKeyPress(12) && MainCanvas.strPop.length > 1 && MainCanvas.popPointer != MainCanvas.strPop.length - 1) {
            MainCanvas.popPointer = (byte) (MainCanvas.popPointer + 1);
            MainCanvas.contentPointer = 0;
        }
        if (MainCanvas.isKeyPress(10) && MainCanvas.strPop.length > 1 && MainCanvas.popPointer != 0) {
            MainCanvas.popPointer = (byte) (MainCanvas.popPointer - 1);
            MainCanvas.contentPointer = 0;
        }
        if (MainCanvas.isKeyPress(11) && (MainCanvas.strPop[MainCanvas.popPointer]).length > 5 && MainCanvas.contentPointer > 0) {
            MainCanvas.contentPointer = (byte) (MainCanvas.contentPointer - 1);
        }
        if (MainCanvas.isKeyPress(13) && (MainCanvas.strPop[MainCanvas.popPointer]).length > 5 && MainCanvas.contentPointer < (MainCanvas.strPop[MainCanvas.popPointer]).length - 5) {
            MainCanvas.contentPointer = (byte) (MainCanvas.contentPointer + 1);
        }
        if (MainCanvas.isKeyPress1(18)) {
            switch (MainCanvas.bindPopState) {
                case 1:
                    if (MainCanvas.popRecord[0] == 0 && !getInstance().isDead()) {
                        MainCanvas.setPop((byte) 0);
                        MainCanvas.bindPopState = 2;
                    } else if (MainCanvas.isHaveFinishedTask && !getInstance().isDead()) {
                        MainCanvas.setPop((byte) 7);
                        MainCanvas.bindPopState = 4;
                    } else {
                        disposePop();
                    }
                    return;
                case 2:
                    if (MainCanvas.isHaveFinishedTask && !getInstance().isDead()) {
                        MainCanvas.setPop((byte) 7);
                        MainCanvas.bindPopState = 4;
                    } else {
                        disposePop();
                    }
                    return;
                case 3:
                    if (MainCanvas.templevel == 1) {
                        MainCanvas.ni.send(134217984);
                    } else if (MainCanvas.templevel == 2) {
                        MainCanvas.mc.skillTreeFlag = true;
                        MainCanvas.ni.send(Cmd.C_SKILL_REQUEST_INFOR);
                    }
                    disposePop();
                    return;
                case 4:
                    disposePop();
                    return;
                case 9:
                    if (MainCanvas.firstLogon == 1) {
                        GameObj tmp = null;
                        tmp = ObjManager.getObj(MainCanvas.mc.NPCIndex);
                        ObjManager.getInstance().setCurrentTarget(tmp);
                        MainCanvas.mc.setGameState((byte) 3);
                        MainCanvas.mc.setNPCSubState((byte) 100);
                        MainCanvas.waitCnt = 0;
                        MainCanvas.mc.releaseUI();
                        MainCanvas.ni.send(150994944);
                    }
                    disposePop();
                    return;
            }
            disposePop();
        }
    }

    private void disposePop() {
        MainCanvas.bindPopState = 0;
        MainCanvas.isPop = false;
        MainCanvas.popPointer = 0;
        MainCanvas.contentPointer = 0;
    }

    private boolean pressNomalFightOrGetInfo() {
        if (MainCanvas.isKeyPress(14) || (Cons.use5 && MainCanvas.isKeyPress(5))) {
            MainCanvas.resetKey();
            if (MainCanvas.mc.getGameState() == 2 || normalAttackCount < 30) {
                return false;
            }
            GameObj oldTarget = ObjManager.currentTarget;
            if (ObjManager.currentTarget == this || ObjManager.currentTarget == null || ObjManager.currentTarget.type == 3 || (ObjManager.currentTarget.type == 1 && ObjManager.currentTarget.group == group && ObjManager.currentTarget != pkObj)) {
                GameObj tmpTargetObj = getNomalFightObj(this, 25, 24);
                if (tmpTargetObj != null) {
                    ObjManager.getInstance().setCurrentTarget(tmpTargetObj);
                }
            }
            GameObj newTarget = ObjManager.currentTarget;
            if (newTarget == oldTarget) {
                if (newTarget == null || newTarget == this) {
                    skillIndex = 0;
                    setState((byte) 2);
                } else if (newTarget.type == 3) {
                    MainCanvas.isMenu = true;
                    MainCanvas.mc.NPCIndex = ObjManager.currentTarget.objID;
                    MainCanvas.mc.setGameState((byte) 3);
                    MainCanvas.mc.setNPCSubState((byte) 100);
                    MainCanvas.waitCnt = 0;
                    MainCanvas.mc.releaseUI();
                    MainCanvas.ni.send(150994944);
                } else if (newTarget.type == 2) {
                    skillIndex = 0;
                    if (theSecendCheck(this, 25, 24, ObjManager.currentTarget)) {
                        MainCanvas.ni.send(Cmd.C_PLAYER_FIGHT_START);
                    }
                    setState((byte) 2);
                } else if (newTarget.type == 1) {
                    if (newTarget.group == group) {
                        if (pkObj == newTarget) {
                            skillIndex = 0;
                            if (theSecendCheck(this, 25, 24, ObjManager.currentTarget)) {
                                MainCanvas.ni.send(Cmd.C_PLAYER_FIGHT_START);
                            }
                            setState((byte) 2);
                        } else {
                            MainCanvas.isMenu = true;
                            MainCanvas.mc.releaseUI();
                            MainCanvas.mc.setGameState((byte) 2);
                        }
                    } else {
                        skillIndex = 0;
                        if (theSecendCheck(this, 25, 24, ObjManager.currentTarget)) {
                            MainCanvas.ni.send(Cmd.C_PLAYER_FIGHT_START);
                        }
                        setState((byte) 2);
                    }
                } else if (newTarget.type == 5) {
                    int dx = newTarget.col - col;
                    int dy = newTarget.row - row;
                    if (dx * dx + dy * dy < 4) {
                        setState((byte) 9);
                        return true;
                    }
                    PCChat.addChatScreen((byte) 7, "距离当前目标太远，无法采集");
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean pressSkillKey() {
        if (MainCanvas.isMenu) {
            return false;
        }
        if (state == 7) {
            return false;
        }
        boolean result = false;
        if (MainCanvas.isKeyPress(1)) {
            currentPressKey = 1;
            result = true;
        } else if (MainCanvas.isKeyPress(2)) {
            currentPressKey = 2;
            result = true;
        } else if (MainCanvas.isKeyPress(3)) {
            result = true;
            currentPressKey = 3;
        } else if (MainCanvas.isKeyPress(4)) {
            result = true;
            currentPressKey = 4;
        } else if (MainCanvas.isKeyPress(5)) {
            result = true;
            currentPressKey = 5;
        } else if (MainCanvas.isKeyPress(6)) {
            result = true;
            currentPressKey = 6;
        } else if (MainCanvas.isKeyPress(7)) {
            result = true;
            currentPressKey = 7;
        } else if (MainCanvas.isKeyPress(8)) {
            result = true;
            currentPressKey = 8;
        }
        if (result) {
            if (canUseSkill[currentPressKey]) {
                if (userDefinedSkills[currentPressKey] > 14) {
                    skillIndex = -1;
                    for (int i = 0; i < userDefinedSkills.length; i++) {
                        if (userDefinedSkills[i] > 14) {
                            canUseSkill[i] = false;
                        }
                    }
                    itemCount = 300;
                    PCPackage.itemID = (short) (userDefinedSkills[currentPressKey] - 14);
                    if (Cons.audioOpen) {
                        MainCanvas.sound.playSound(1, 1, true);
                    }
                    MainCanvas.useStuffPlace = 0;
                    MainCanvas.ni.send(67110144);
                    return false;
                }
                skillIndex = userDefinedSkills[currentPressKey];
                oldSkillTargetId = ObjManager.currentTarget.objID;
                if (skillIndex == 0) {
                    GameObj oldTarget = ObjManager.currentTarget;
                    if (ObjManager.currentTarget == this || ObjManager.currentTarget == null || ObjManager.currentTarget.type == 3 || (ObjManager.currentTarget.type == 1 && ObjManager.currentTarget.group == group && ObjManager.currentTarget != pkObj)) {
                        GameObj tmpTargetObj = getNomalFightObj(this, 25, 24);
                        if (tmpTargetObj != null) {
                            ObjManager.getInstance().setCurrentTarget(tmpTargetObj);
                        }
                    }
                    GameObj newTarget = ObjManager.currentTarget;
                    if (newTarget == oldTarget) {
                        if (newTarget == null || newTarget == this) {
                            skillIndex = 0;
                            setState((byte) 2);
                        } else if (newTarget.type == 3) {
                            MainCanvas.isMenu = true;
                            MainCanvas.mc.NPCIndex = ObjManager.currentTarget.objID;
                            MainCanvas.mc.setGameState((byte) 3);
                            MainCanvas.mc.setNPCSubState((byte) 100);
                            MainCanvas.waitCnt = 0;
                            MainCanvas.mc.releaseUI();
                            MainCanvas.ni.send(150994944);
                        } else if (newTarget.type == 2) {
                            skillIndex = 0;
                            if (theSecendCheck(this, 25, 24, ObjManager.currentTarget)) {
                                MainCanvas.ni.send(Cmd.C_PLAYER_FIGHT_START);
                            }
                            setState((byte) 2);
                        } else if (newTarget.type == 1) {
                            if (newTarget.group == group) {
                                if (pkObj == newTarget) {
                                    skillIndex = 0;
                                    if (theSecendCheck(this, 25, 24, ObjManager.currentTarget)) {
                                        MainCanvas.ni.send(Cmd.C_PLAYER_FIGHT_START);
                                    }
                                    setState((byte) 2);
                                } else {
                                    MainCanvas.isMenu = true;
                                    MainCanvas.mc.releaseUI();
                                    MainCanvas.mc.setGameState((byte) 2);
                                }
                            } else {
                                skillIndex = 0;
                                if (theSecendCheck(this, 25, 24, ObjManager.currentTarget)) {
                                    MainCanvas.ni.send(Cmd.C_PLAYER_FIGHT_START);
                                }
                                setState((byte) 2);
                            }
                        } else if (newTarget.type == 5) {
                            setState((byte) 9);
                        }
                    }
                }
            } else {
                skillIndex = 0;
                return false;
            }
        }
        return result;
    }

    private boolean pressArrowKey() {
        boolean isPressArrowKey = false;
        switch (state) {
            case 0:
                isPressArrowKey = true;
                if (MainCanvas.isKeyPress(11) || (Cons.use2468 && MainCanvas.isKeyPress(2))) {
                    if (direction == 1) {
                        setState((byte) 1);
                        break;
                    }
                    setDirection((byte) 1);
                    break;
                }
                if (MainCanvas.isKeyPress(13) || (Cons.use2468 && MainCanvas.isKeyPress(8))) {
                    if (direction == 2) {
                        setState((byte) 1);
                        move((byte) 2);
                        break;
                    }
                    setDirection((byte) 2);
                    break;
                }
                if (MainCanvas.isKeyPress(10) || (Cons.use2468 && MainCanvas.isKeyPress(4))) {
                    if (direction == 3) {
                        setState((byte) 1);
                        move((byte) 3);
                        break;
                    }
                    setDirection((byte) 3);
                    break;
                }
                if (MainCanvas.isKeyPress(12) || (Cons.use2468 && MainCanvas.isKeyPress(6))) {
                    if (direction == 4) {
                        setState((byte) 1);
                        move((byte) 4);
                        break;
                    }
                    setDirection((byte) 4);
                    break;
                }
                isPressArrowKey = false;
                break;
            case 1:
                isPressArrowKey = true;
                if (MainCanvas.isKeyPress(11) || (Cons.use2468 && MainCanvas.isKeyPress(2))) {
                    move((byte) 1);
                    break;
                }
                if (MainCanvas.isKeyPress(13) || (Cons.use2468 && MainCanvas.isKeyPress(8))) {
                    move((byte) 2);
                    break;
                }
                if (MainCanvas.isKeyPress(10) || (Cons.use2468 && MainCanvas.isKeyPress(4))) {
                    move((byte) 3);
                    break;
                }
                if (MainCanvas.isKeyPress(12) || (Cons.use2468 && MainCanvas.isKeyPress(6))) {
                    move((byte) 4);
                    break;
                }
                isPressArrowKey = false;
                break;
            case 2:
                isPressArrowKey = true;
                if (MainCanvas.isKeyPress(11) || MainCanvas.isKeyPress(13) || MainCanvas.isKeyPress(10) || MainCanvas.isKeyPress(12) || (Cons.use2468 && MainCanvas.isKeyPress(2)) || (Cons.use2468 && MainCanvas.isKeyPress(4)) || (Cons.use2468 && MainCanvas.isKeyPress(6)) || (Cons.use2468 && MainCanvas.isKeyPress(8))) {
                    setState((byte) 1);
                    break;
                }
                isPressArrowKey = false;
                break;
            case 7:
                isPressArrowKey = true;
                if (MainCanvas.isKeyPress(11) || (Cons.use2468 && MainCanvas.isKeyPress(2))) {
                    if (direction == 1) {
                        setState((byte) 1);
                        break;
                    }
                    setDirection((byte) 1);
                    break;
                }
                if (MainCanvas.isKeyPress(13) || (Cons.use2468 && MainCanvas.isKeyPress(8))) {
                    if (direction == 2) {
                        setState((byte) 1);
                        break;
                    }
                    setDirection((byte) 2);
                    break;
                }
                if (MainCanvas.isKeyPress(10) || (Cons.use2468 && MainCanvas.isKeyPress(4))) {
                    if (direction == 3) {
                        setState((byte) 1);
                        break;
                    }
                    setDirection((byte) 3);
                    break;
                }
                if (MainCanvas.isKeyPress(12) || (Cons.use2468 && MainCanvas.isKeyPress(6))) {
                    if (direction == 4) {
                        setState((byte) 1);
                        break;
                    }
                    setDirection((byte) 4);
                    break;
                }
                isPressArrowKey = false;
                break;
            case 9:
                isPressArrowKey = true;
                if (MainCanvas.isKeyPress(11) || (Cons.use2468 && MainCanvas.isKeyPress(2))) {
                    move((byte) 1);
                    setState((byte) 0);
                    collectionCount = 0;
                    break;
                }
                if (MainCanvas.isKeyPress(13) || (Cons.use2468 && MainCanvas.isKeyPress(8))) {
                    move((byte) 2);
                    setState((byte) 0);
                    collectionCount = 0;
                    break;
                }
                if (MainCanvas.isKeyPress(10) || (Cons.use2468 && MainCanvas.isKeyPress(4))) {
                    move((byte) 3);
                    setState((byte) 0);
                    collectionCount = 0;
                    break;
                }
                if (MainCanvas.isKeyPress(12) || (Cons.use2468 && MainCanvas.isKeyPress(6))) {
                    move((byte) 4);
                    setState((byte) 0);
                    collectionCount = 0;
                    break;
                }
                isPressArrowKey = false;
                break;
        }
        return isPressArrowKey;
    }

    private GameObj getNomalFightObj(GameObj obj, int w, int h) {
        GameObj[] returnObj = new GameObj[ObjManager.vectorObj.size()];
        for (int i = 0; i < returnObj.length; i++) {
            returnObj[i] = null;
        }
        int tmpX = obj.x;
        int tmpY = obj.y;
        int tmpNum = 0;
        switch (obj.direction) {
            case 1:
                tmpNum = w;
                w = h;
                h = tmpNum;
                tmpX -= w >> 1;
                tmpY -= h;
                break;
            case 2:
                tmpNum = w;
                w = h;
                h = tmpNum;
                tmpX -= w >> 1;
                break;
            case 3:
                tmpX -= w;
                tmpY -= h >> 1;
                break;
            case 4:
                tmpY -= h >> 1;
                break;
        }
        byte objIndex = 0;
        for (int j = 0; j < ObjManager.vectorObj.size(); j++) {
            GameObj tmpObj = (GameObj) ObjManager.vectorObj.elementAt(j);
            if (tmpObj != this) {
                if (collidesREC(tmpObj.x - 1, tmpObj.y - 1, 2, 2, tmpX, tmpY, w, h)) {
                    returnObj[objIndex] = tmpObj;
                    objIndex = (byte) (objIndex + 1);
                }
            }
        }
        GameObj selectReturnObj = null;
        for (int k = 0; k < returnObj.length; k++) {
            if (returnObj[k] != null) {
                if (selectReturnObj == null) {
                    selectReturnObj = returnObj[k];
                } else {
                    if ((returnObj[k]).type == 2 || (returnObj[k]).group != group) {
                        selectReturnObj = returnObj[k];
                        break;
                    }
                    if ((returnObj[k]).type == 3 && selectReturnObj.type != 2) {
                        selectReturnObj = returnObj[k];
                    } else if ((selectReturnObj.x - obj.x) * (selectReturnObj.x - obj.x) + (selectReturnObj.y - obj.y) * (selectReturnObj.y - obj.y) > ((returnObj[k]).x - obj.x) * ((returnObj[k]).x - obj.x) + ((returnObj[k]).y - obj.y) * ((returnObj[k]).y - obj.y) && selectReturnObj.type != 3) {
                        selectReturnObj = returnObj[k];
                    }
                }
            }
        }
        return selectReturnObj;
    }

    /**
     * 碰撞检测
     *
     * @param obj
     * @param w
     * @param h
     * @param objTarget
     * @return
     */
    private boolean theSecendCheck(GameObj obj, int w, int h, GameObj objTarget) {
        boolean result = false;
        int tmpX = obj.x;
        int tmpY = obj.y;
        int tmpNum;
        switch (obj.direction) {
            case 1:
                tmpNum = w;
                w = h;
                h = tmpNum;
                tmpX -= w >> 1;
                tmpY -= h;
                break;
            case 2:
                tmpNum = w;
                w = h;
                h = tmpNum;
                tmpX -= w >> 1;
                break;
            case 3:
                tmpX -= w;
                tmpY -= h >> 1;
                break;
            case 4:
                tmpY -= h >> 1;
        }
        if (this.collidesREC(objTarget.x, objTarget.y, 1, 1, tmpX, tmpY, w, h)) {
            result = true;
        }
        return result;
    }

    /**
     * 判断两个矩形是否发生碰撞
     *
     * @param ax 矩形a的x坐标
     * @param ay 矩形a的y坐标
     * @param aw 矩形a的宽
     * @param ah 矩形a的高
     * @param bx 矩形b的x坐标
     * @param by 矩形b的y坐标
     * @param bw 矩形b的宽
     * @param bh 矩形b的高
     * @return
     */
    private boolean collidesREC(int ax, int ay, int aw, int ah, int bx, int by, int bw, int bh) {
        return ax < bx + bw
                && ax + aw > bx
                && ay < by + bh
                && ay + ah > by;
    }

    private static boolean inDistance(int playerX, int playerY, int targetX, int targetY, int distance) {
        int dis = (playerX - targetX) * (playerX - targetX);
        dis += (playerY - targetY) * (playerY - targetY);
        int dis2 = distance * distance;
        if (dis < dis2) {
            return true;
        }
        return false;
    }

    public byte getSkillIndex(int index) {
        byte result = -1;
        result = (byte) index;
        if (result != -1) {
            castLength = Cons.SKILL_TAOIST[result][4] * 1000;
        } else {
            castLength = 1000;
        }
        return result;
    }

    public boolean canUseSkill(int skillIndex, GameObj caster, GameObj target) {
        if (skillIndex == 0) {
            if (normalAttackCount < 30) {
                return false;
            }
            return true;
        }
        if (skillIndex < 0 || skillIndex > 14) {
            return false;
        }
        if (caster == null) {
            return false;
        }
        short[] skillData = getSkillData(caster.profession, skillIndex);
        if (skillData == null) {
            return false;
        }
        if (getSkillMP(skillIndex) > caster.curMp) {
            return false;
        }
        short skillType = skillData[1];
        boolean result1 = checkSkillObj(caster, target, skillIndex, false);
        if (!result1) {
            return false;
        }
        if (skillType != 6 && skillType != 4) {
            int distance = skillData[2];
            if (distance != 0) {
                if (!inDistance(caster.x, caster.y, target.x, target.y, distance)) {
                    return false;
                }
            }
        }
        boolean result = skillCDCheck(this, skillIndex);
        if (!result) {
            return false;
        }
        return true;
    }

    /**
     * 获取玩家技能
     *
     * @param profession 职业
     * @param skillIndex 技能编号
     * @return
     */
    public static short[] getSkillData(byte profession, int skillIndex) {
        switch (profession) {
            case 4:
                return Cons.SKILL_ASSASSIN[skillIndex];
            case 1:
                return Cons.SKILL_SWORDMAN[skillIndex];
            case 2:
                return Cons.SKILL_TAOIST[skillIndex];
            case 3:
                return Cons.SKILL_APOTHECARY[skillIndex];
        }
        return null;
    }

    public boolean canUseSkill(int skillIndex) {
        return canUseSkill(skillIndex, this, ObjManager.currentTarget);
    }

    public void checkSkill() {
        for (int i = 0; i < userDefinedSkills.length; i++) {
            if (bufferState[2] || bufferState[3]) {
                canUseSkill[i] = false;
            } else {
                canUseSkill[i] = canUseSkill(userDefinedSkills[i]);
            }
            if (userDefinedSkills[i] > 14) {
                if (itemCount != 0) {
                    canUseSkill[i] = false;
                } else {
                    switch (userDefinedSkills[i]) {
                        case 15:
                            if (PCSkillTree.canUseRed) {
                                canUseSkill[i] = true;
                            }
                            break;
                        case 16:
                            if (PCSkillTree.canUseBlue) {
                                canUseSkill[i] = true;
                            }
                            break;
                        case 17:
                            if (PCSkillTree.canUsePurple) {
                                canUseSkill[i] = true;
                            }
                            break;
                    }
                }
            }
        }
    }

    public void drawCollection(Graphics g) {
        if (collectionCount == 0) {
            return;
        }
        if (state != 9) {
            return;
        }
        int rectW = 72;
        int rectH = 5;
        int startX = MainCanvas.screenW - 72 >>> 1;
        int startY = 220;
        g.setColor(2037253);
        g.fillRect(-1 + startX, 219, 75, 8);
        g.setColor(10125138);
        g.drawRect(startX, 220, 72, 5);
        g.setColor(2037253);
        g.drawRect(1 + startX, 221, 70, 3);
        g.setColor(16768512);
        int len = 68 * collectionCount * 1000;
        len /= 20;
        len /= 1000;
        g.fillRect(startX + 2, 222, len + 1, 2);
    }

    public void drawSkillPre(Graphics g) {
        if (state != 7) {
            return;
        }
        if (castLength == 0) {
            return;
        }
        int skillW = 72;
        int skillH = 5;
        int skillStartX = MainCanvas.screenW - 72 >>> 1;
        int skillStartY = 220;
        g.setColor(2037253);
        g.fillRect(-1 + skillStartX, 219, 75, 8);
        g.setColor(10125138);
        g.drawRect(skillStartX, 220, 72, 5);
        g.setColor(2037253);
        g.drawRect(1 + skillStartX, 221, 70, 3);
        g.setColor(16768512);
        long len = 68L * (System.currentTimeMillis() - castTick);
        if (castLength <= 0) {
            len = 68L;
        } else {
            len /= castLength;
        }
        if (len > 68L) {
            len = 68L;
        }
        g.fillRect(2 + skillStartX, 222, (int) (len + 1L), 2);
    }

    public void drawPlayerArraw(Graphics g) {
        if (state != 7) {
            int tx = x - Map.currentWindowX;
            int ty = y - Map.currentWindowY;
            tx -= 7;
            ty -= 55;
            arrowTick = (byte) (arrowTick + 1);
            if (arrowTick >= 4) {
                arrowTick = 0;
            }
            ty += arrowTick & 0x1;
            MainCanvas.imgPlayerArrow.draw(g, tx, ty + (arrowTick >> 1) * 2, 0, false);
            return;
        }
    }

    private void getCollideRectAcmeColumnAndRow() {
        byte adjustMiddleHorizontal = STEP[curStep][0];
        byte adjustMiddleVertical = STEP[curStep][1];
        projectionAcme[0][0] = Map.getCurrentRow(y - 3, x - 4);
        projectionAcme[0][1] = Map.getCurrentColumn(y - 3, x - 4);
        projectionAcme[1][0] = Map.getCurrentRow(y - 3, x + 4);
        projectionAcme[1][1] = Map.getCurrentColumn(y - 3, x + 4);
        projectionAcme[2][0] = Map.getCurrentRow(y, x + 4);
        projectionAcme[2][1] = Map.getCurrentColumn(y, x + 4);
        projectionAcme[3][0] = Map.getCurrentRow(y, x - 4);
        projectionAcme[3][1] = Map.getCurrentColumn(y, x - 4);
        projectionAcme[4][0] = Map.getCurrentRow(y - adjustMiddleHorizontal - 3, x);
        projectionAcme[4][1] = Map.getCurrentColumn(y - adjustMiddleHorizontal - 3, x);
        projectionAcme[5][0] = Map.getCurrentRow(y - 1, x + 4 + adjustMiddleVertical);
        projectionAcme[5][1] = Map.getCurrentColumn(y - 1, x + 4 + adjustMiddleVertical);
        projectionAcme[6][0] = Map.getCurrentRow(y + adjustMiddleHorizontal, x);
        projectionAcme[6][1] = Map.getCurrentColumn(y + adjustMiddleHorizontal, x);
        projectionAcme[7][0] = Map.getCurrentRow(y - 1, x - 4 - adjustMiddleVertical);
        projectionAcme[7][1] = Map.getCurrentColumn(y - 1, x - 4 - adjustMiddleVertical);
    }

    private void move(byte dir) {
        if (direction != dir) {
            setDirection(dir);
        }
        int preX = x;
        int preY = y;
        switch (dir) {
            case 1:
                y -= STEP[curStep][1];
                break;
            case 2:
                y += STEP[curStep][1];
                break;
            case 3:
                x -= STEP[curStep][0];
                break;
            case 4:
                x += STEP[curStep][0];
                break;
            case 5:
                x -= STEP[curStep][2];
                y -= STEP[curStep][3];
                break;
            case 6:
                x += STEP[curStep][2];
                y -= STEP[curStep][3];
                break;
            case 7:
                x -= STEP[curStep][2];
                y += STEP[curStep][3];
                break;
            case 8:
                x += STEP[curStep][2];
                y += STEP[curStep][3];
                break;
        }
        getCollideRectAcmeColumnAndRow();
        if (!canMove()) {
            switch (direction) {
                case 1:
                    y += STEP[curStep][1];
                    break;
                case 2:
                    y -= STEP[curStep][1];
                    break;
                case 3:
                    x += STEP[curStep][0];
                    break;
                case 4:
                    x -= STEP[curStep][0];
                    break;
            }
        }
        int tmpCol = getCol(x, y);
        int tmpRow = getRow(x, y);
        if (!Map.getInstance().isFloor(tmpCol, tmpRow)) {
            x = preX;
            y = preY;
        }
    }

    /**
     * 玩家是否能够移动
     *
     * @return
     */
    private boolean canMove() {
        switch (direction) {
            case 3:
                if (Map.getInstance().isFloor(projectionAcme[0][1], projectionAcme[0][0])) {
                    if (Map.getInstance().isFloor(projectionAcme[3][1], projectionAcme[3][0])) {
                        if (Map.getInstance().isFloor(projectionAcme[7][1], projectionAcme[7][0])) {
                            return true;
                        }
                        x -= STEP[curStep][2];
                        y -= STEP[curStep][3];
                        getCollideRectAcmeColumnAndRow();
                        if (Map.getInstance().isFloor(projectionAcme[0][1], projectionAcme[0][0])) {
                            break;
                        }
                        x += STEP[curStep][2];
                        y += STEP[curStep][3];
                        x -= STEP[curStep][2];
                        y += STEP[curStep][3];
                        getCollideRectAcmeColumnAndRow();
                        if (Map.getInstance().isFloor(projectionAcme[3][1], projectionAcme[3][0])) {
                            break;
                        }
                        x += STEP[curStep][2];
                        y -= STEP[curStep][3];
                        return false;
                    }
                }
                if (Map.getInstance().isFloor(projectionAcme[0][1], projectionAcme[0][0])) {
                    if (!Map.getInstance().isFloor(projectionAcme[3][1], projectionAcme[3][0])) {
                        x -= STEP[curStep][2];
                        y -= STEP[curStep][3];
                        getCollideRectAcmeColumnAndRow();
                        if (Map.getInstance().isFloor(projectionAcme[0][1], projectionAcme[0][0])) {
                            break;
                        }
                        x += STEP[curStep][2];
                        y += STEP[curStep][3];
                        return false;
                    }
                }
                if (!Map.getInstance().isFloor(projectionAcme[0][1], projectionAcme[0][0])) {
                    if (Map.getInstance().isFloor(projectionAcme[3][1], projectionAcme[3][0])) {
                        x -= STEP[curStep][2];
                        y += STEP[curStep][3];
                        getCollideRectAcmeColumnAndRow();
                        if (Map.getInstance().isFloor(projectionAcme[3][1], projectionAcme[3][0])) {
                            break;
                        }
                        x += STEP[curStep][2];
                        y -= STEP[curStep][3];
                        return false;
                    }
                }
                break;
            case 4:
                if (Map.getInstance().isFloor(projectionAcme[1][1], projectionAcme[1][0])) {
                    if (Map.getInstance().isFloor(projectionAcme[2][1], projectionAcme[2][0])) {
                        if (Map.getInstance().isFloor(projectionAcme[5][1], projectionAcme[5][0])) {
                            return true;
                        }
                        x += STEP[curStep][2];
                        y -= STEP[curStep][3];
                        getCollideRectAcmeColumnAndRow();
                        if (Map.getInstance().isFloor(projectionAcme[1][1], projectionAcme[1][0])) {
                            break;
                        }
                        x -= STEP[curStep][2];
                        y += STEP[curStep][3];
                        x += STEP[curStep][2];
                        y += STEP[curStep][3];
                        getCollideRectAcmeColumnAndRow();
                        if (Map.getInstance().isFloor(projectionAcme[2][1], projectionAcme[2][0])) {
                            break;
                        }
                        x -= STEP[curStep][2];
                        y -= STEP[curStep][3];
                        return false;
                    }
                }
                if (Map.getInstance().isFloor(projectionAcme[1][1], projectionAcme[1][0])) {
                    if (!Map.getInstance().isFloor(projectionAcme[2][1], projectionAcme[2][0])) {
                        x += STEP[curStep][2];
                        y -= STEP[curStep][3];
                        getCollideRectAcmeColumnAndRow();
                        if (Map.getInstance().isFloor(projectionAcme[1][1], projectionAcme[1][0])) {
                            break;
                        }
                        x -= STEP[curStep][2];
                        y += STEP[curStep][3];
                        return false;
                    }
                }
                if (!Map.getInstance().isFloor(projectionAcme[1][1], projectionAcme[1][0])) {
                    if (Map.getInstance().isFloor(projectionAcme[2][1], projectionAcme[2][0])) {
                        x += STEP[curStep][2];
                        y += STEP[curStep][3];
                        getCollideRectAcmeColumnAndRow();
                        if (Map.getInstance().isFloor(projectionAcme[2][1], projectionAcme[2][0])) {
                            break;
                        }
                        x -= STEP[curStep][2];
                        y -= STEP[curStep][3];
                        return false;
                    }
                }
                break;
            case 1:
                if (Map.getInstance().isFloor(projectionAcme[1][1], projectionAcme[1][0])) {
                    if (Map.getInstance().isFloor(projectionAcme[0][1], projectionAcme[0][0])) {
                        if (Map.getInstance().isFloor(projectionAcme[4][1], projectionAcme[4][0])) {
                            return true;
                        }
                        x -= STEP[curStep][2];
                        y -= STEP[curStep][3];
                        getCollideRectAcmeColumnAndRow();
                        if (Map.getInstance().isFloor(projectionAcme[0][1], projectionAcme[0][0])) {
                            break;
                        }
                        x += STEP[curStep][2];
                        y += STEP[curStep][3];
                        x += STEP[curStep][2];
                        y -= STEP[curStep][3];
                        getCollideRectAcmeColumnAndRow();
                        if (Map.getInstance().isFloor(projectionAcme[1][1], projectionAcme[1][0])) {
                            break;
                        }
                        x -= STEP[curStep][2];
                        y += STEP[curStep][3];
                        return false;
                    }
                }
                if (!Map.getInstance().isFloor(projectionAcme[1][1], projectionAcme[1][0])) {
                    if (Map.getInstance().isFloor(projectionAcme[0][1], projectionAcme[0][0])) {
                        x -= STEP[curStep][2];
                        y -= STEP[curStep][3];
                        getCollideRectAcmeColumnAndRow();
                        if (Map.getInstance().isFloor(projectionAcme[0][1], projectionAcme[0][0])) {
                            break;
                        }
                        x += STEP[curStep][2];
                        y += STEP[curStep][3];
                        return false;
                    }
                }
                if (Map.getInstance().isFloor(projectionAcme[1][1], projectionAcme[1][0])) {
                    if (!Map.getInstance().isFloor(projectionAcme[0][1], projectionAcme[0][0])) {
                        x += STEP[curStep][2];
                        y -= STEP[curStep][3];
                        getCollideRectAcmeColumnAndRow();
                        if (Map.getInstance().isFloor(projectionAcme[1][1], projectionAcme[1][0])) {
                            break;
                        }
                        x -= STEP[curStep][2];
                        y += STEP[curStep][3];
                        return false;
                    }
                }
                break;
            case 2:
                if (Map.getInstance().isFloor(projectionAcme[2][1], projectionAcme[2][0])) {
                    if (Map.getInstance().isFloor(projectionAcme[3][1], projectionAcme[3][0])) {
                        if (Map.getInstance().isFloor(projectionAcme[6][1], projectionAcme[6][0])) {
                            return true;
                        }
                        x -= STEP[curStep][2];
                        y += STEP[curStep][3];
                        getCollideRectAcmeColumnAndRow();
                        if (Map.getInstance().isFloor(projectionAcme[3][1], projectionAcme[3][0])) {
                            break;
                        }
                        x += STEP[curStep][2];
                        y -= STEP[curStep][3];
                        x += STEP[curStep][2];
                        y += STEP[curStep][3];
                        getCollideRectAcmeColumnAndRow();
                        if (Map.getInstance().isFloor(projectionAcme[2][1], projectionAcme[2][0])) {
                            break;
                        }
                        x -= STEP[curStep][2];
                        y -= STEP[curStep][3];
                        return false;
                    }
                }
                if (!Map.getInstance().isFloor(projectionAcme[2][1], projectionAcme[2][0])) {
                    if (Map.getInstance().isFloor(projectionAcme[3][1], projectionAcme[3][0])) {
                        x -= STEP[curStep][2];
                        y += STEP[curStep][3];
                        getCollideRectAcmeColumnAndRow();
                        if (Map.getInstance().isFloor(projectionAcme[3][1], projectionAcme[3][0])) {
                            break;
                        }
                        x += STEP[curStep][2];
                        y -= STEP[curStep][3];
                        return false;
                    }
                }
                if (Map.getInstance().isFloor(projectionAcme[2][1], projectionAcme[2][0])) {
                    if (!Map.getInstance().isFloor(projectionAcme[3][1], projectionAcme[3][0])) {
                        x += STEP[curStep][2];
                        y += STEP[curStep][3];
                        getCollideRectAcmeColumnAndRow();
                        if (Map.getInstance().isFloor(projectionAcme[2][1], projectionAcme[2][0])) {
                            break;
                        }
                        x -= STEP[curStep][2];
                        y -= STEP[curStep][3];
                        return false;
                    }
                }
                break;
        }
        getCollideRectAcmeColumnAndRow();
        return false;
    }

    public static void defineKey(int index, int skillIndex) {
        if ((((index < 0) ? 1 : 0) | ((index >= 9) ? 1 : 0)) != 0) {
            System.err.println("Error!! Player: defineKey() index in Player.defineKey:" + index);
            return;
        }
        if ((((skillIndex < -1) ? 1 : 0) | ((skillIndex > 17) ? 1 : 0)) != 0) {
            System.err.println("Error!! Player: defineKey() skillIndex in Player.defineKey:" + index);
            return;
        }
        userDefinedSkills[index] = (byte) skillIndex;
        canUseSkill[index] = false;
    }

    public static void loadDefinedKeyRecord() {
        userDefinedSkills = null;
        canUseSkill = null;
        userDefinedSkills = Util.readRecord((getInstance()).objID + "sc");
        if (userDefinedSkills == null || userDefinedSkills.length != 9) {
            userDefinedSkills = new byte[9];
            userDefinedSkills[0] = 0;
            for (int i = 1; i < 9; i++) {
                userDefinedSkills[i] = -1;
            }
        }
        canUseSkill = new boolean[9];
        canUseSkill[0] = true;
    }

    /**
     * 检测是否更换地图
     */
    private void checkChangeMap() {
        if (state == 5 || state == 4) {
            return;
        }
        for (int i = 0; i < Map.curPointArray.length && Map.curPointArray[i][0] != -1; i++) {
            if (Map.curPointArray[i][1] == row && Map.curPointArray[i][0] == col) {
                isSendMoveMsg = false;
                Map.changeMapPointIndex = (byte) i;
                setState((byte) 0);
                MainCanvas.ni.send(Cmd.C_PLAYER_MOVE);
                MainCanvas.ni.send(Cmd.C_MAP_CHANGE);
                MainCanvas.mc.setGameState((byte) 8);
                MainCanvas.mc.setOtherSubState((byte) 3);
                break;
            }
        }
    }

    public void releaseObj() {
        instance = null;
    }

    public void full() {
        Player p = getInstance();
        p.curHp = p.maxHp;
        p.curMp = p.maxMp;
        p.lastHp = p.maxHp;
    }

    public static void levelUp() {
        Player p = getInstance();
        p.full();
    }

    /**
     * 更新技能CD
     * @param player 
     */
    private void skillCDAdd(GameObj player) {
        for (int i = 0; i < userDefinedSkills.length; i++) {
            int skillIndex = userDefinedSkills[i];
            if (skillIndex >= 0 && skillIndex <= 14) {
                short[] skillData = getSkillData(player.profession, skillIndex);
                int cdTime = skillData[5];
                if (skillIndex != 0 && cdTime != 0 && skillCD[skillIndex] != 0L) {
                    if (System.currentTimeMillis() - skillCD[skillIndex] < 0L) {
                        MainCanvas.closeConnection();
                    } else if ((System.currentTimeMillis() - skillCD[skillIndex]) / 1000L >= cdTime) {
                        canUseSkill[i] = true;
                        skillCD[skillIndex] = 0L;
                    }
                }
            }
        }
    }

    private boolean skillCDCheck(GameObj player, int skillIndex) {
        if (skillIndex > 14 || skillIndex < 0) {
            return false;
        }
        short[] skillData = getSkillData(player.profession, skillIndex);
        int cdTime = skillData[5];
        if (skillIndex != 0 && cdTime != 0) {
            if (skillCD[skillIndex] == 0L) {
                return true;
            }
            if ((System.currentTimeMillis() - skillCD[skillIndex]) / 1000L < cdTime) {
                return false;
            }
            return false;
        }
        return true;
    }

    public void skillCDUse(GameObj player, int skillIndex) {
        if (skillIndex > 14 || skillIndex < 0) {
            return;
        }
        short[] skillData = getSkillData(player.profession, skillIndex);
        int cdTime = skillData[5];
        if (skillIndex != 0 && cdTime != 0) {
            if (skillCD[skillIndex] == 0L) {
                skillCD[skillIndex] = System.currentTimeMillis();
            }
        }
    }

    private boolean checkSkillObj(GameObj player, GameObj target, int skillIndex, boolean ifcheckoldtarget) {
        short[] skillData = getSkillData(player.profession, skillIndex);
        short skillType = skillData[1];
        if (ifcheckoldtarget && oldSkillTargetId != ObjManager.currentTarget.objID) {
            return false;
        }
        if ((target == null || target.type == 3 || target.type == 5) && skillType != 6 && skillType != 4) {
            return false;
        }
        if (skillData[0] != 3) {
            return false;
        }
        switch (skillType) {
            case 2:
                if (Util.isEnemy(player, target)) {
                    return false;
                }
                if (target.getState() == 5 || target.getState() == 4) {
                    return false;
                }
                break;
            case 3:
                if (player == target) {
                    return false;
                }
                if (Util.isEnemy(player, target)) {
                    return false;
                }
                if (target.getState() == 5 || target.getState() == 4) {
                    if ((player.profession == 3 || player.profession == 4) && skillIndex == 13) {
                        break;
                    }
                    return false;
                }
                if ((player.profession == 3 || player.profession == 4) && skillIndex == 13) {
                    return false;
                }
                break;
            case 5:
                if (Util.isEnemy(player, target)) {
                    return false;
                }
                if (target.getState() == 5 || target.getState() == 4) {
                    return false;
                }
                break;
            case 7:
                if (!Util.isEnemy(player, target)) {
                    return false;
                }
                break;
            case 8:
                return false;
        }
        return true;
    }

    public int getSkillMP(int skillIndex) {
        if (skillIndex < 1 || skillIndex > 14) {
            return 0;
        }
        short[] skillData = getSkillData(profession, skillIndex);
        if (skillData == null) {
            return 0;
        }
        int temp = skillData[3];
        temp += skillLevels[skillIndex - 1] * skillData[6];
        return temp;
    }

    /**
     * 返回当前玩家是否寻径中
     *
     * @return
     */
    public boolean isFollow() {
        return !(followAimID == -1);
    }

    /**
     * 取消寻径
     */
    public void resetAimID() {
        followAimID = -1;
        if (getState() != 5 && getState() != 4) {
            setState((byte) 0);
        }
    }

    public void setFollowAimID(int id) {
        followAimID = id;
        setState((byte) 1);
    }

    private void follow() {
    }

    public boolean isDead() {
        return (getState() == 4);
    }

    public void setCastTick(long castTick) {
        castTick = castTick;
    }

    public int getCastLength() {
        return castLength;
    }

    public void setAimColRow(int aimCol, int aimRow) {
        if (aimCol != col || aimRow != row) {
            path = AStarTree.getInstance().findPath(col, row, aimCol, aimRow);
        }
    }

    private final boolean getDirect() {
        while (path != null && path.length > 0 && findpath) {
            setState((byte) 1);
            int dCol = path[0][0] - col;
            int dRow = path[0][1] - row;
            if (dCol == 0 && dRow == 0) {
                path = AStarTree.resizeArray(path, 0, -1);
                continue;
            }
            if (dCol > 0) {
                if (dRow > 0) {
                    setDirection((byte) 2);
                } else if (dRow < 0) {
                    setDirection((byte) 4);
                } else {
                    setDirection((byte) 8);
                }
            } else if (dCol < 0) {
                if (dRow > 0) {
                    setDirection((byte) 3);
                } else if (dRow < 0) {
                    setDirection((byte) 1);
                } else {
                    setDirection((byte) 5);
                }
            } else if (dRow > 0) {
                setDirection((byte) 7);
            } else if (dRow < 0) {
                setDirection((byte) 6);
            }
            return true;
        }
        setState((byte) 0);
        return false;
    }

    public void movePath() {
        if (getDirect()) {
            move(getDirection());
        }
        setRowCol(getRow(x, y), getCol(x, y));
        Map.putInCell(getRow(x, y), getCol(x, y));
    }

    public byte getDirection() {
        return direction;
    }
}
