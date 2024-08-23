
import java.util.Vector;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Displayable;

/**
 * 拓展能力类，用来增强天劫客户端
 *
 * @author yihua
 */
public class ExpandAbility implements CommandListener {

    private static ExpandAbility instance;
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
     * 设置界面
     */
    private UIForm baseForm;
    /**
     * 组件列表
     */
    private static Vector components;
    /**
     * 设置选项
     */
    private byte[] setup = new byte[]{0, 0, 0, 0, 0};
    /**
     * 世界地图名称
     */
    public static String[] regionName;
    /**
     * 世界地图数据
     */
    public static int[][] regionProps;
    /**
     * 设置界面选中
     */
    private static byte vIndex = 0;
    // 原生组件 确定
    private Command ok;
    // 原生组件 返回
    private Command back;
    // 表单
    private Form form;

    private ExpandAbility() {
        initUIForm();
        initForm();
    }

    public static ExpandAbility getInstance() {
        if (instance == null) {
            instance = new ExpandAbility();
        }
        return instance;
    }

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
            // 根据职业类型路由到对应的挂机逻辑
            int occu = player.imgID;
            switch (occu) {
                case 3: {  // 医生
                    battleApothecary(player);
                    break;
                }
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
    public static void battleApothecary(Player player) {
        if (player.state != 5) {  // 玩家没有死亡
            // 检查是否进战斗
            int hpPer = player.getPercentageHp();
            int mpPer = player.getPercentageMp();
            // 获取当前选中目标
            GameObj target = ObjManager.currentTarget;
            boolean isEnemy = Util.isEnemy(target, player);
            // 血量少于40%使用心疗术，这里不查是不知道是否学了这个技能
            if (hpPer < 50 && player.canCastSkill(2)) {
                player.caskSkill(player, 2);
            } else if (player.isBeAttack() || isEnemy || (hpPer > 70 && mpPer > 70)) {   // 进了战斗，或者状态良好，继续战斗
                // 如果当前未选中，或者不是可攻击对象
                if (!isEnemy) {
                    // 选择最近的可攻击对象作为目标
                    target = player.getNearFightObj();
                }
                if (target != null) {
                    if (ObjManager.canBeSetTarget(target, 80)) {  // 判断当前对象是否可以被选择
                        // 判断当前是否选中地方单位
                        ObjManager manager = ObjManager.getInstance();
                        // 设置为当前选择对象
                        manager.setCurrentTarget(target);
                        // 如果是寻径中，取消寻径
                        if (player.isFollow()) {
                            player.resetAimID();
                        }
                        // 判断是否可以释放技能，这里注意吟唱技能时没有内置CD的
                        if (player.canCastSkill(1)) {  // 释放飓风之牙
                            player.caskSkill(target, 1);
                        } else { // 判断是否可以普通攻击
                            // 判断当前是否能攻击到
                            if (player.canNormalAttack(target)) {
                                if (Player.normalAttackCount > 30) {  // 如果普通攻击CD好了
                                    // 瞬移进行普通攻击
                                    player.setObjPosition(target.col, target.row);
                                    player.normalAttck();
                                }
                            }
                        }
                    } else {  // 寻径，这里必须实时更新
                        player.setAimColRow(target.col, target.row);
                    }
                }
            }
        } else {  //按#键复活
            keyPressed(NUM_11);
        }
    }

    /**
     * 绘制挂机设置界面
     *
     * @param g
     */
    public static void drawHangUpUI(Graphics g) {
        ExpandAbility.getInstance().baseForm.draw(g);
    }

    public static void keyInUiHangUp() {
        if (MainCanvas.isKeyPress(18)) {
            MainCanvas.mc.setGameState((byte) 0);
        } else if (MainCanvas.isKeyPress(11)) {
            if (vIndex > 0) {
                --vIndex;
                ExpandAbility.getInstance().baseForm.setComponentFocus((UIComponent) components.elementAt(vIndex));
            }
        } else if (MainCanvas.isKeyPress(13)) {
            if (vIndex < components.size() - 1) {
                ++vIndex;
                ExpandAbility.getInstance().baseForm.setComponentFocus((UIComponent) components.elementAt(vIndex));
            }
        } else if (MainCanvas.isKeyPress(10)) {
            UIRadioButton ui = (UIRadioButton) components.elementAt(vIndex);
            ui.setChooseItem(0);
        } else if (MainCanvas.isKeyPress(12)) {
            UIRadioButton ui = (UIRadioButton) components.elementAt(vIndex);
            ui.setChooseItem(1);
        }else if(MainCanvas.isKeyPress(14)){  //测试查找地图
            String s = "孤月岛二";
            String e = "城阳谷林二";
            aStar2World(s, e);
        }
    }

    /**
     * 初始化UIForm
     */
    private void initUIForm() {
        baseForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "");
        baseForm.setStyle((byte) 0);
        UIRim rimFrame = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte) 4);
        UIRim rimTitle = new UIRim(0, 10, 160, 17, (byte) 7);
        UILabel lblTitle = new UILabel(0, rimTitle.positionY + 3, 0, 0, "挂机设置", 15718815, (byte) 1, (byte) 0);
        UIRim rimDown = new UIRim(0, 27, 160, 160, (byte) 0);
        UIRim rimDownInner = new UIRim(0, 32, 150, 150, (byte) 0);
        UILabel lblOk = new UILabel(0, 0, 0, 0, "确定", 15718815, (byte) 1, (byte) 0);
        UILabel lblCancel = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 1, (byte) 0);
        baseForm.addComponent(rimFrame);
        baseForm.addComponentInCenter(rimTitle, (byte) 2);
        baseForm.addComponentInCenter(lblTitle, (byte) 2);
        baseForm.addComponentInCenter(rimDown, (byte) 2);
        baseForm.addComponentInCenter(rimDownInner, (byte) 2);
        baseForm.addComponentInCenter(lblOk, (byte) 5);
        baseForm.addComponentInCenter(lblCancel, (byte) 6);
        // 选项 巡逻挂机
        UIRadioButton rb1 = new UIRadioButton(35, 38 + 0 * 14, 0, 0, "巡逻挂机", (byte) 0);
        rb1.addItems("开");
        rb1.addItems("关");
        rb1.setChooseItem(setup[0]);
        // 选项 指定野怪
        UIRadioButton rb2 = new UIRadioButton(35, 38 + 1 * 14, 0, 0, "指定怪物", (byte) 0);
        rb2.addItems("开");
        rb2.addItems("关");
        rb2.setChooseItem(setup[1]);
        // 选项 自动吃药
        UIRadioButton rb3 = new UIRadioButton(35, 38 + 2 * 14, 0, 0, "自动吃药", (byte) 0);
        rb3.addItems("开");
        rb3.addItems("关");
        rb3.setChooseItem(setup[2]);
        // 选项 自动修理
        UIRadioButton rb4 = new UIRadioButton(35, 38 + 3 * 14, 0, 0, "自动修理", (byte) 0);
        rb4.addItems("开");
        rb4.addItems("关");
        rb4.setChooseItem(setup[3]);
        // 选项 自动清理
        UIRadioButton rb5 = new UIRadioButton(35, 38 + 4 * 14, 0, 0, "自动清理", (byte) 0);
        rb5.addItems("开");
        rb5.addItems("关");
        rb5.setChooseItem(setup[4]);
        components = new Vector();
        components.addElement(rb1);
        components.addElement(rb2);
        components.addElement(rb3);
        components.addElement(rb4);
        components.addElement(rb5);
        baseForm.addComponent(rb1);
        baseForm.addComponent(rb2);
        baseForm.addComponent(rb3);
        baseForm.addComponent(rb4);
        baseForm.addComponent(rb5);
        // 指定打怪列表
//        UILabel label = new UILabel(34, 80, 60, 17, "怪物列表：", 0xEFD99F, (byte) 0, (byte) 0);
//        UITextArea area = new UITextArea(34, 90, 100, 40, "高级奔波霸;无敌啊");
//        baseForm.addComponent(label);
//        baseForm.addComponent(area);
        baseForm.setFocus(true);
        baseForm.setMessage(Cons.ROLL_MASSAGE[11], false);
    }

    private void initForm() {
        form = new Form("nameForm");
        form.setTitle("设置怪物列表");
        ok = new Command("确定", 4, 2);
        back = new Command("返回", 2, 2);
        form.addCommand(ok);
        form.addCommand(back);
        form.setCommandListener(this);
    }

    /**
     * 原生按钮事件
     *
     * @param c
     * @param d
     */
    public void commandAction(Command c, Displayable d) {
    }

    /**
     * 获取世界地图寻径位置
     *
     * @param sMap 起始地图
     * @param eMap 结束地图
     * @return
     */
    public static int[] aStar2World(String sMap, String eMap) {
        int[] path = null;
        if (regionName != null && regionProps != null) {
            int sIndex = -1;
            int eIndex = -1;
            int length = regionName.length;
            // 首先查找地图所在索引
            for (int i = 0; i < regionName.length; ++i) {
                if (sIndex != -1 && eIndex != -1) {
                    break;
                } else if (sIndex == -1 && sMap.equals(regionName[i])) {
                    sIndex = i;
                } else if (eIndex == -1 && eMap.equals(regionName[i])) {
                    eIndex = i;
                }
            }
            // 当起始地图和目标地图都存在时，才进行查找
            if (sIndex != -1 && eIndex != -1) {
                int wIndex = 0;
                // 这里是从大到小排序 {地图索引, 父地图索引, G值, H值}  F = G + H，G是起点移动，H是当前到终点估算
                int[][] oList = new int[length][];
                // 使用关闭列表索引作为父index，因为关闭列表不需要排序
                int[][] cList = new int[length][];
                // 初始化开放列表，起点父格子为-1
                oList[0] = new int[]{sIndex, -1, 0, Math.abs(eIndex - sIndex)};
                boolean isEnd = false;
                while (wIndex > -1 && !isEnd) {
                    // 取出最小成本，
                    int[] min = oList[wIndex];
                    // 减少计数
                    --wIndex;
                    // 将其加入clsit，这里直接使用地图索引位置
                    cList[min[0]] = min;
                    // 检查四个方向
                    for (int i = 0; i < 4; ++i) {
                        int d = regionProps[min[0]][2+i];
                        // 如果可以行走，并且没有被加入关闭列表
                        if (d == eIndex) {  // 如果找到了
                            ++wIndex;
                            oList[wIndex] = new int[]{d, min[0], 0, 0};
                            isEnd = true;
                            break;
                        } else if (d != -1 && cList[d] == null) {
                            // 检测是否在oList
                            int oIndex = -1;
                            for (int j = 0; j <= wIndex; ++j) {
                                // 如果开放列表找到了了
                                if (oList[j][0] == d) {
                                    oIndex = j;
                                    break;
                                }
                            }
                            int g = min[2] + 1;
                            int h = Math.abs(eIndex - d);
                            if (oIndex == -1) {  // 未找到，加入开放列表
                                // 先自增oList
                                ++wIndex;
                                oList[wIndex] = new int[]{d, min[0], g, h};
                            } else {   // 找到了，更新F
                                int f = oList[oIndex][2] + oList[oIndex][3];
                                int nf = g + h;
                                if (nf < f) {  // 如果更好，更新
                                    oList[oIndex][1] = d;
                                    oList[oIndex][2] = g;
                                    oList[oIndex][3] = h;
                                }
                            }
                        }
                    }
                    // 重新排序开放列表
                    for (int i = 1; i <= wIndex; ++i) {
                        int[] km = oList[i];
                        int k = km[2] + km[3];
                        int j = i - 1;
                        while (j >= 0 && (oList[j][2] + oList[j][3]) < k) {
                            oList[j + 1] = oList[j];
                            j--;
                        }
                        oList[j + 1] = km;
                    }
                }
                // 如果找到了
                if(wIndex >= 0){
                    // 开始回溯
                    int fid = oList[wIndex][1];
                    String mName = regionName[oList[wIndex][0]];
                    Vector v = new Vector();
                    v.addElement(mName);
                    while(fid != -1){
                        mName = regionName[fid];
                        v.addElement(mName);
                        fid = cList[fid][1];
                    }
                    System.out.println("结束！");
                }
            }
        }
        return path;
    }
}