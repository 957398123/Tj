
/**
 * 拓展能力类，用来增强天劫客户端
 *
 * @author yihua
 */
public class ExpandAbility {

    /**
     * 按键按下映射
     */
    private static int[] keyPressedMap = new int[]{0x1, 0x2, 0x4, 0x8, 0x10, 0x20, 0x40, 0x80, 0x100, 0x200, 0x400, 0x800, 0x1000, 0x2000, 0x4000, 0x8000, 0x10000, 0x20000, 0x40000};
    /**
     * 数字按键0
     */
    private final static int NUM_0 = 0;
    /**
     * 数字按键1
     */
    private final static int NUM_1 = 1;
    /**
     * 数字按键2
     */
    private final static int NUM_2 = 2;
    /**
     * 数字按键3
     */
    private final static int NUM_3 = 3;
    /**
     * 数字按键4
     */
    private final static int NUM_4 = 4;
    /**
     * 数字按键5
     */
    private final static int NUM_5 = 5;
    /**
     * 数字按键6
     */
    private final static int NUM_6 = 6;
    /**
     * 数字按键7
     */
    private final static int NUM_7 = 7;
    /**
     * 数字按键8
     */
    private final static int NUM_8 = 8;
    /**
     * 数字按键9
     */
    private final static int NUM_9 = 9;
    /**
     * 方向键-左
     */
    private final static int ARROW_LEFT = 10;
    /**
     * 方向键-上
     */
    private final static int ARROW_TOP = 11;
    /**
     * 方向键-右
     */
    private final static int ARROW_RIGHT = 12;
    /**
     * 方向键-下
     */
    private final static int ARROW_BOTTOM = 13;
    /**
     * OK键
     */
    private final static int SOFT_OK = 14;
    /**
     * 数字按键*
     */
    private final static int NUM_10 = 15;
    /**
     * 数字按键#
     */
    private final static int NUM_11 = 16;
    /**
     * 左软键
     */
    private final static int SOFT_LEFT = 17;
    /**
     * 右软键
     */
    private final static int SOFT_RIGHT = 18;
    /**
     * 上一次tick时间
     */
    private static long lastTickTime = System.currentTimeMillis();
    /**
     * 插件已运行时间
     */
    private static long runTime = 0;
    /**
     * 是否一直按住按键
     */
    private static boolean isHoldKey = false;
    /**
     * 是否已经按键
     */
    private static boolean isPressedKey = false;
    /**
     * 上一次玩家操作时间
     */
    private static long lastPlayTick = 0;

    /**
     * 这是最前面的tick
     */
    public static void startTick() {
        // 更新last tick
    }

    /**
     * 在地图tick之前
     */
    public static void beforemapRunSubTick() {
    }

    /**
     * 在地图tick之后
     */
    public static void aftermapRunSubTick() {
    }

    /**
     * 在PCArena的tick以后 这是最后面的tick
     */
    public static void afterPCArenaTick() {
    }

    /**
     * 玩家tick前
     *
     * @param player
     */
    public static void beforePlayerTick(Player player) {
        // 获取当前已运行时间
        long curTime = getRunTime();
        if (lastPlayTick == 0) {
            lastPlayTick = curTime;
        }
        // 判断当前是不是挂机中
        if (player.isHangup) {
            if (player.state != 5) {  // 玩家没有死亡
                // 判断当前是否选中地方单位
                ObjManager manager = ObjManager.getInstance();
                GameObj target = ObjManager.currentTarget;
                // 如果当前未选中，或者不是可攻击对象
                if (target == null || !Util.isEnemy(player, target)) {
                    // 选择最近的可攻击对象作为目标
                    target = player.getNearFightObj();
                }
                if (target != null && target != player) {
                    // 判断当前对象是否可以被选择
                    if (ObjManager.canBeSetTarget(target, 80)) {
                        // 设置为当前选择对象
                        manager.setCurrentTarget(target);
                        // 如果是寻径中，取消寻径
                        if (player.isFollow()) {
                            player.resetAimID();
                        }
                        // 如果选取的角色死亡
                        if (target.state == 5) {
                            player.setState((byte) 0);
                        }
                        // 根据职业类型路由到对应的挂机逻辑
                        int occu = player.imgID;
                        switch (occu) {
                            case 3: {  // 医生
                                battleApothecary(player, target);
                                break;
                            }
                        }
                    } else {  // 设置寻径，这里必须实时寻径
                        player.setAimColRow(target.col, target.row);
                    }
                }
            }else {  //按#键复活
                keyPressed(NUM_11);
            }
        }
    }

    /**
     * 玩家tick后
     *
     * @param player
     */
    public static void afterPlayerTick(Player player) {
    }

    /**
     * 在ObjManager tick前
     *
     * @param manager
     */
    public static void beforeObjManagerTick(ObjManager manager) {
    }

    /**
     * 在ObjManager tick后
     *
     * @param manager
     */
    public static void afterObjManagerTick(ObjManager manager) {
    }

    /**
     * 在游戏对象tick以前
     *
     * @param obj
     */
    public static void beforeGameObjectTick(GameObj obj) {
    }

    /**
     * 在游戏对象tick以后
     *
     * @param obj
     */
    public static void afterGameObjectTick(GameObj obj) {
    }

    /**
     * 这是最后面的tick
     */
    public static void endTick() {
        if (isPressedKey && !isHoldKey) {
            keyReleased();
        }
    }

    /**
     * 按下按键
     *
     * @param keyCode
     */
    public static void keyPressed(int keyCode) {
        if (keyCode < keyPressedMap.length) {
            int keyFlag = keyPressedMap[keyCode];
            isPressedKey = true;
            isHoldKey = false;
            MainCanvas.mc.setKeyValue(keyFlag);
            MainCanvas.mc.handKeyPress();
        }
    }

    /**
     * 一直按住按键
     *
     * @param keyCode
     */
    public static void keyHoldPressed(int keyCode) {
        if (keyCode < keyPressedMap.length) {
            int keyFlag = keyPressedMap[keyCode];
            isPressedKey = true;
            isHoldKey = true;
            MainCanvas.mc.setKeyValue(keyFlag);
            MainCanvas.mc.handKeyPress();
        }
    }

    /**
     * 释放按键
     *
     * @param keyCode
     */
    public static void keyReleased() {
        isPressedKey = false;
        MainCanvas.mc.restKeyFlag();
    }

    /**
     * 清除按键
     */
    public static void resetKey() {
        MainCanvas.resetKey();
    }

    /**
     * 获取插件已运行时间
     *
     * @return
     */
    public static long getRunTime() {
        long currTime = System.currentTimeMillis();
        runTime += (currTime - lastTickTime);
        lastTickTime = currTime;
        return runTime;
    }

    /**
     * 医生战斗逻辑
     *
     * @param player
     * @param target
     */
    public static void battleApothecary(Player player, GameObj target) {
        // 首先判断是否已经攻击目标，或者角色至少有一半蓝，才继续打怪
        if (target.curHp > 0 && target.curHp < target.maxHp || (player.curHp > (player.maxHp / 2) && player.curMp > (player.maxMp / 10) * 7)) {
            // 判断是否可以释放技能，这里注意吟唱技能时没有内置CD的
            if (player.canCastSkill(1)) {
                player.oldSkillTargetId = target.objID;
                player.skillIndex = 1;
                player.setState((byte) 7);
            }else{ // 普通攻击
                // 这里直接瞬移
                player.setObjPosition(target.col, target.row);
                // 按普通攻击键
                keyPressed(SOFT_OK);
            }
        }
    }
}
