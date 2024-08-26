
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
public class ExpandAbility {

    private static ExtendClass ec = ExtendClass.getInstance();

    ;
    /**
     * 这是最前面的tick
     */
    public static void startTick() {
        // 更新last tick
        ec.startTick();
    }

    /**
     * 在地图tick之前
     */
    public static void beforemapRunSubTick() {
        ec.beforemapRunSubTick();
    }

    /**
     * 在地图tick之后
     */
    public static void aftermapRunSubTick() {
        ec.aftermapRunSubTick();
    }

    /**
     * 在PCArena的tick以后 这是最后面的tick
     */
    public static void afterPCArenaTick() {
        ec.afterPCArenaTick();
    }

    /**
     * 玩家tick前
     *
     * @param player
     */
    public static void beforePlayerTick(Player player) {
        ec.beforePlayerTick(player);
    }

    /**
     * 玩家tick后
     *
     * @param player
     */
    public static void afterPlayerTick(Player player) {
        ec.afterPlayerTick(player);
    }

    /**
     * 在ObjManager tick前
     *
     * @param manager
     */
    public static void beforeObjManagerTick(ObjManager mg) {
        ec.beforeObjManagerTick(mg);
    }

    /**
     * 在ObjManager tick后
     *
     * @param manager
     */
    public static void afterObjManagerTick(ObjManager mg) {
        ec.afterObjManagerTick(mg);
    }

    /**
     * 在游戏对象tick以前
     *
     * @param obj
     */
    public static void beforeGameObjectTick(GameObj obj) {
        ec.beforeGameObjectTick(obj);
    }

    /**
     * 在游戏对象tick以后
     *
     * @param obj
     */
    public static void afterGameObjectTick(GameObj obj) {
        ec.afterGameObjectTick(obj);
    }

    /**
     * 这是最后面的tick
     */
    public static void endTick() {
        ec.endTick();
    }

    /**
     * 绘制挂机设置界面
     *
     * @param g
     */
    public static void drawHangUpUI(Graphics g) {
        ec.drawHangUpUI(g);
    }

    /**
     * 挂机按键处理
     */
    public static void keyInUiHangUp() {
        ec.keyInUiHangUp();
    }
}

/**
 * 具体实现类
 *
 * @author yihua
 */
class ExtendClass implements CommandListener {

    private ExtendClass() {
        initUIForm();
        initForm();
    }
    private static ExtendClass ec;
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
    private long lastTickTime = System.currentTimeMillis();
    /**
     * 插件已运行时间
     */
    private long runTime = 0;
    /**
     * 是否一直按住按键
     */
    private boolean isHoldKey = false;
    /**
     * 是否已经按键
     */
    private boolean isPressedKey = false;
    /**
     * 上一次玩家操作时间
     */
    private long lastPlayTick = 0;
    /**
     * 设置界面
     */
    private UIForm baseForm;
    /**
     * 组件列表
     */
    private Vector components;
    /**
     * 设置选项 0-开 1-关
     */
    private byte[] setup = new byte[]{1, 1, 1, 1, 1};
    /**
     * 角色状态机 0-恢复 1-寻路 2-战斗
     */
    private byte status = 0;
    /**
     * 巡逻挂机位置 列
     */
    private int pCol = -1;
    /**
     * 巡逻挂机位置 行
     */
    private int pRow = -1;
    /**
     * 设置界面选中
     */
    private byte vIndex = 0;
    // 原生组件 确定
    private Command ok;
    // 原生组件 返回
    private Command back;
    // 表单
    private Form form;
    /**
     * 地图名称
     */
    private String[] mapNames = {
        "昌意城", "天籁岛三", "天籁岛二", "迷幻梦境", "天籁岛一", "云中仙城", "孤月岛一", "孤月岛二", "百鬼井二", "百鬼井一", // 9
        "姬水部落", "城阳谷林一", "城阳谷林二", "百鬼井三", "鬼魂牢", "常羊山一", "常羊山二", "迷幻云阶一", "常羊山三", "玉竹林一", // 19
        "玉竹林二", "迷幻云阶二", "昆吾草原", "雷泽一", "雷泽二", "姚墟残骸一", "姚墟残骸二", "雷泽三", "姚墟城堡一", "姚墟城堡二", // 29
        "姚墟城堡三", "姚墟城堡四", "姚墟城堡五", "姚墟城堡六", "魔炎龙穴", "禁锢云海一", "禁锢云海二", "禁锢云海三", "死或生一", "死或生二", // 39
        "死或生三", "台前深渊", "枭山三", "枭山一", "枭山二", "神农秘境", "噬血堂一", "噬血堂二", "妖魂沼泽三", "妖魂沼泽二", // 49
        "妖魂沼泽一", "不周山麓一", "不周山麓二", "猩红海岛一", "猩红海岛二", "猩红海岸一", "猩红海岸二", "地狱一", "地狱二", "地狱三", // 59
        "地狱四", "地狱五", "地狱六", "禁锢天牢", "轮回森林", "影牙都市", "噩梦谷二", "噩梦谷一", "噩梦谷三", "九爪龙洞", // 69
        "断骨石窟二", "断骨石窟一", "紫骨妖城", "妖魂神庙", "万妖冢三", "万妖冢一", "炼狱魔都", "血池崖一", "血池崖二", "万妖冢二", // 79
        "天地心", "紫竹坟", "厄雷池", "清凉世界", "天河界", "风云顶", "怒炎堡垒", "战神擂", "地狱炎城", "暴雷峡谷", // 89
        "氏族领地", "氏族领地", "氏族战场", "炎池", "齐风顶", "禁断天涯", "冤魂水屋", "混沌之心", "火鬼腐宅", "月如刀", //99
        "齐天堡", "血逍遥", "乾坤角", "十里津", "夺魄牌", "怒风台", "归去来", "青玄神殿", "九黎土丘二", "九黎土丘一", // 109
        "残溶洞", "九黎土丘三", "九幽冥地", "堕落之路一", "堕落之路二", "炼狱之海", "堕落之路三", "流波山一", "流波山二", "流波山三", // 119
        "颛顼之墟", "涿鹿荒野二", "涿鹿荒野三", "涿鹿荒野一", "遗忘坟场", "伊洛平原一", "伊洛平原二", "伊洛平原三", "虚空神界", "兰陵幽墓", // 129
        "幻天镜", "死气洞穴", "荒坟草场", "无梦仙乡", "幽魂宅", "梦魇乡", "百花谷一", "百花谷二", "无情海岸一", "无情海岸二", // 139
        "血色古堡", "小池镇", "黑岩洞", "鬼阴山山脚", "鬼阴山山腰", "鬼阴山山顶", "幽魂沼泽一", "幽魂沼泽二", "万魔古窟" // 148
    };
    /**
     * 地图出口
     */
    private short[][] mapPaths = { // {地图类型, 上, 下, 左, 右}
        {4, 13, 15, 1, -1}, // 昌意城
        {4, -1, 2, 4, 0}, // 天籁岛三
        {4, 1, 3, 4, -1}, // 天籁岛二
        {2, 2, -1, -1, -1}, // 迷幻梦境
        {4, -1, 2, 5, 1}, // 天籁岛一
        {0, 6, -1, -1, 4}, // 云中仙城
        {3, -1, 5, 7, -1}, // 孤月岛一
        {3, -1, -1, -1, 6}, // 孤月岛二
        {4, 9, -1, -1, 13}, // 百鬼井二
        {4, -1, 8, 10, 13}, // 百鬼井一  9
        {0, -1, -1, 11, 9}, // 姬水部落
        {3, -1, -1, 12, 10}, // 城阳谷林一
        {3, -1, -1, -1, 11}, // 城阳谷林二
        {4, 9, 0, 8, 14}, // 百鬼井三
        {2, -1, -1, 13, -1}, // 鬼魂牢
        {4, 0, 18, -1, 16}, // 常羊山一
        {4, 103, 17, 15, -1}, // 常羊山二
        {1, 16, 20, 18, 21}, // 迷幻云阶一
        {4, 15, 19, -1, 17}, // 常羊山三
        {1, 18, 35, -1, 20}, // 玉竹林一 19
        {1, 17, -1, 19, -1}, // 玉竹林二
        {1, 83, 22, 17, 23}, // 迷幻云阶二
        {4, 21, -1, -1, -1}, // 昆吾草原
        {1, 27, 24, 21, -1}, // 雷泽一
        {1, 23, 131, -1, 25}, // 雷泽二
        {1, 104, 26, 24, -1}, // 姚墟残骸一
        {1, 25, -1, -1, -1}, // 姚墟残骸二
        {1, -1, 23, -1, 28}, // 雷泽三
        {1, 34, 81, 27, 29}, // 姚墟城堡一
        {1, -1, 30, 28, -1}, // 姚墟城堡二 29
        {1, 29, -1, -1, 31}, // 姚墟城堡三
        {1, 99, -1, 30, 32}, // 姚墟城堡四
        {1, 33, -1, 31, -1}, // 姚墟城堡五
        {1, -1, 32, -1, -1}, // 姚墟城堡六
        {2, -1, 28, -1, -1}, // 魔炎龙穴
        {1, 19, 46, 36, 37}, // 禁锢云海一
        {1, -1, 93, 38, 35}, // 禁锢云海二
        {1, 94, 133, 35, 43}, // 禁锢云海三
        {1, 40, 39, 41, 36}, // 死或生一
        {1, 38, -1, -1, -1}, // 死或生二  39
        {1, -1, 38, -1, 88}, // 死或生三
        {2, -1, -1, 78, -1}, // 台前深渊
        {1, -1, 43, 85, -1}, // 枭山三
        {1, 42, 44, 37, 45}, // 枭山一
        {1, 43, -1, -1, -1}, // 枭山二
        {2, -1, -1, 43, -1}, // 神农秘境
        {1, 35, 47, 48, -1}, // 噬血堂一
        {1, 46, 52, 49, -1}, // 噬血堂二
        {1, -1, 49, -1, 46}, // 妖魂沼泽三  
        {5, 48, 132, 50, 47}, // 妖魂沼泽二 49
        {5, -1, 52, 65, 49}, // 妖魂沼泽一
        {5, 65, -1, 105, 52}, // 不周山麓一
        {1, 50, -1, 51, 53}, // 不周山麓二
        {1, 64, 54, 52, 55}, // 猩红海岛一
        {1, 53, 57, -1, -1}, // 猩红海岛二
        {1, -1, 106, 53, 56}, // 猩红海岸一
        {1, -1, 57, 55, 84}, // 猩红海岸二
        {1, 56, 58, 55, 63}, // 地狱一
        {1, 57, -1, 59, -1}, // 地狱二
        {1, 60, 82, -1, 58}, // 地狱三  59
        {1, 101, 59, 61, -1}, // 地狱四
        {1, -1, 62, -1, 60}, // 地狱五
        {1, 61, -1, 102, -1}, // 地狱六
        {2, -1, -1, 57, -1}, // 禁锢天牢
        {5, -1, 53, -1, -1}, // 轮回森林
        {5, 79, 51, 66, 50}, // 影牙都市
        {5, 67, -1, -1, 65}, // 噩梦谷二
        {5, 68, 66, -1, 72}, // 噩梦谷一
        {5, -1, 67, 69, -1}, // 噩梦谷三
        {2, -1, -1, -1, 68}, // 九爪龙洞 69
        {3, -1, -1, -1, 71}, // 断骨石窟二
        {3, -1, 72, 70, -1}, // 断骨石窟一
        {0, 71, -1, 67, -1}, // 紫骨妖城
        {2, -1, -1, -1, 74}, // 妖魂神庙
        {5, -1, -1, 73, 75}, // 万妖冢三
        {5, 76, 79, 74, -1}, // 万妖冢一
        {0, 77, 75, -1, -1}, // 炼狱魔都
        {3, -1, 76, -1, -1}, // 血池崖一
        {3, -1, -1, 77, -1}, // 血池崖二
        {5, 75, 65, -1, -1}, // 万妖冢二 79
        {1, -1, -1, -1, -1}, // 天地心
        {2, 28, -1, -1, -1}, // 紫竹坟
        {2, 59, -1, -1, -1}, // 厄雷池
        {2, -1, 21, -1, -1}, // 清凉世界
        {2, -1, -1, 56, -1}, // 天河界
        {2, -1, -1, -1, 89}, // 风云顶
        {2, -1, -1, -1, 88}, // 怒炎堡垒
        {1, -1, -1, -1, -1}, // 战神擂
        {2, 40, -1, 86, -1}, // 地狱炎城
        {2, -1, -1, 85, -1}, // 暴雷峡谷 89
        {2, 95, -1, 96, -1}, // 氏族领地
        {2, -1, 98, -1, 97}, // 氏族领地
        {1, -1, -1, -1, -1}, // 氏族战场
        {2, 36, -1, -1, -1}, // 炎池
        {2, -1, 37, -1, -1}, // 齐风顶
        {2, -1, 90, -1, -1}, // 禁断天涯
        {2, -1, 90, -1, -1}, // 冤魂水屋
        {2, -1, -1, 91, -1}, // 混沌之心
        {2, -1, -1, 91, -1}, // 火鬼腐宅
        {2, -1, 31, -1, -1}, // 月如刀  99
        {2, -1, -1, -1, 33}, // 齐天堡
        {2, -1, 60, -1, -1}, // 血逍遥
        {2, -1, -1, -1, 62}, // 乾坤角
        {2, -1, 16, -1, -1}, // 十里津
        {2, -1, 25, -1, -1}, // 夺魄牌
        {2, -1, -1, -1, 51}, // 怒风台
        {2, 55, -1, -1, -1}, // 归去来
        {4, 113, 108, 114, -1}, // 青玄神殿
        {1, 107, -1, -1, 109}, // 九黎土丘二
        {1, 111, -1, 108, 110}, // 九黎土丘一 109
        {2, -1, -1, 109, -1}, // 残溶洞
        {1, 112, 109, -1, -1}, // 九黎土丘三
        {5, 116, 111, 113, -1}, // 九幽冥地
        {1, 115, 107, -1, 112}, // 堕落之路一
        {1, 117, -1, -1, 107}, // 堕落之路二
        {2, -1, 113, -1, -1}, // 炼狱之海
        {1, 117, 112, -1, -1}, // 堕落之路三
        {1, 136, 114, 118, 116}, // 流波山一
        {1, 120, 119, -1, 117}, // 流波山二
        {1, 118, 121, 123, -1}, // 流波山三  119
        {2, -1, 118, -1, -1}, // 颛顼之墟
        {1, 119, -1, 122, -1}, // 涿鹿荒野二
        {1, 123, 125, 124, 121}, // 涿鹿荒野三
        {1, -1, 122, -1, 119}, // 涿鹿荒野一
        {2, -1, -1, -1, 122}, // 遗忘坟场
        {1, 122, -1, 129, 126}, // 伊洛平原一
        {1, -1, 127, 125, -1}, // 伊洛平原二
        {1, 126, 128, -1, 130}, // 伊洛平原三
        {2, 127, -1, -1, -1}, // 虚空神界
        {2, -1, -1, -1, 125}, // 兰陵幽墓  129
        {2, -1, -1, 127, -1}, // 幻天镜
        {2, 24, -1, -1, -1}, // 死气洞穴
        {2, 49, -1, -1, -1}, // 荒坟草场
        {2, 37, -1, -1, -1}, // 无梦仙乡
        {2, -1, -1, -1, -1}, // 幽魂宅
        {2, -1, -1, -1, -1}, // 梦魇乡
        {1, 138, 117, -1, 137}, // 百花谷一
        {1, -1, -1, 136, -1}, // 百花谷二
        {1, 141, 136, 140, 139}, // 无情海岸一
        {1, -1, -1, 138, -1}, // 无情海岸二  139
        {2, -1, -1, -1, 138}, // 血色古堡
        {1, 143, 138, -1, 142}, // 小池镇
        {2, -1, -1, 141, -1}, // 黑岩洞
        {1, 146, 141, -1, 144}, // 鬼阴山山脚
        {1, 145, -1, 143, -1}, // 鬼阴山山腰
        {1, -1, 144, -1, -1}, // 鬼阴山山顶
        {1, -1, 143, 147, -1}, // 幽魂沼泽一
        {1, 148, -1, -1, 146}, // 幽魂沼泽二
        {2, -1, 147, -1, -1} // 万魔古窟
    };

    public static ExtendClass getInstance() {
        if (ec == null) {
            ec = new ExtendClass();
        }
        return ec;
    }

    /**
     * 这是最前面的tick
     */
    public void startTick() {
        // 更新last tick
    }

    /**
     * 在地图tick之前
     */
    public void beforemapRunSubTick() {
    }

    /**
     * 在地图tick之后
     */
    public void aftermapRunSubTick() {
    }

    /**
     * 在PCArena的tick以后 这是最后面的tick
     */
    public void afterPCArenaTick() {
    }

    /**
     * 玩家tick前
     *
     * @param player
     */
    public void beforePlayerTick(Player player) {
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
    public void afterPlayerTick(Player player) {
    }

    /**
     * 在ObjManager tick前
     *
     * @param manager
     */
    public void beforeObjManagerTick(ObjManager manager) {
    }

    /**
     * 在ObjManager tick后
     *
     * @param manager
     */
    public void afterObjManagerTick(ObjManager manager) {
    }

    /**
     * 在游戏对象tick以前
     *
     * @param obj
     */
    public void beforeGameObjectTick(GameObj obj) {
    }

    /**
     * 在游戏对象tick以后
     *
     * @param obj
     */
    public void afterGameObjectTick(GameObj obj) {
    }

    /**
     * 这是最后面的tick
     */
    public void endTick() {
        if (isPressedKey && !isHoldKey) {
            keyReleased();
        }
    }

    /**
     * 按下按键
     *
     * @param keyCode
     */
    public void keyPressed(int keyCode) {
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
    public void keyHoldPressed(int keyCode) {
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
    public void keyReleased() {
        isPressedKey = false;
        MainCanvas.mc.restKeyFlag();
    }

    /**
     * 清除按键
     */
    public void resetKey() {
        MainCanvas.resetKey();
    }

    /**
     * 获取插件已运行时间
     *
     * @return
     */
    public long getRunTime() {
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
    public void battleApothecary(Player player) {
        if (player.state != 5) {  // 玩家没有死亡
            // 获取当前选中目标
            GameObj target = ObjManager.currentTarget;
            ObjManager manager = ObjManager.getInstance();
            // 检查是否进战斗
            int hpPer = player.getPercentageHp();
            if (status == 0) {   // 恢复中
                int mpPer = player.getPercentageMp();
                // 如果状态满了，或者被攻击
                if ((hpPer > 70 && mpPer > 70 || player.isBeAttack)) {
                    // 寻找下一个可攻击目标
                    // 选择最近的可攻击对象作为目标
                    target = player.getNearFightObj();
                    if (target != null) {
                        // 设置为当前选择对象
                        manager.setCurrentTarget(target);
                        // 设置寻径
                        player.setAimColRow(target.col, target.row);
                        status = 1;
                    }
                } else if (setup[0] == 0 && !player.isColRow(pCol, pRow)) {  // 检测是否定点打怪
                    if(!player.isAimColRow(pCol, pRow)){
                        // 设置回原点
                        player.setAimColRow(pCol, pRow);
                    }
                } else if (hpPer < 100 && player.canCastSkill(2)) {
                    manager.setCurrentTarget(player);
                    player.caskSkill(player, 2);
                }
            } else if (status == 1) {  // 寻径中
                // 如果是寻径，到目标范围切换战斗状态
                if (GameObj.inDistance(player.x, player.y, target.x, target.y, 70)) {
                    player.resetFindPath();
                    // 进入战斗
                    status = 2;
                } else {
                    // 这里需要实时更新目标路径
                    player.setAimColRow(target.col, target.row);
                }
            } else if (status == 2) {  // 进入战斗
                if (target != null && target != player) {
                    // 这里怪物在受到攻击后，寻径找玩家的时候，可能会大于这个值，所以这里需要进行判断
                    if (GameObj.inDistance(player.x, player.y, target.x, target.y, 80)) {  // 如果在施法范围
                        // 判断是否可以释放技能，这里注意吟唱技能时没有内置CD的
                        if (hpPer < 40 && player.canCastSkill(2)) {
                            // 这里必须设置为当前选中对象
                            player.caskSkill(target, 2);
                        } else if (player.canCastSkill(1)) {  // 释放飓风之牙
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
                    } else {  // 寻径
                        status = 1;
                    }
                } else {   // 怪物已死亡
                    status = 0;
                }
            }
        } else {  //按#键复活
            // 设置为回复状态
            status = 0;
            keyPressed(NUM_11);
        }
    }

    /**
     * 绘制挂机设置界面
     *
     * @param g
     */
    public void drawHangUpUI(Graphics g) {
        baseForm.draw(g);
    }

    public void keyInUiHangUp() {
        String fouceFormName = baseForm.getCurrentFocusForm().getName();
        if (baseForm.getCurrentFocusForm() == baseForm) {
            if (MainCanvas.isKeyPress(18)) {  // 返回
                MainCanvas.mc.setGameState((byte) 0);
            } else if (MainCanvas.isKeyPress(11)) {  // 上
                if (vIndex > 0) {
                    --vIndex;
                    baseForm.setComponentFocus((UIComponent) components.elementAt(vIndex));
                }
            } else if (MainCanvas.isKeyPress(13)) { // 下
                if (vIndex < components.size() - 1) {
                    ++vIndex;
                    baseForm.setComponentFocus((UIComponent) components.elementAt(vIndex));
                }
            } else if (MainCanvas.isKeyPress(10)) { // 左
                UIRadioButton ui = (UIRadioButton) components.elementAt(vIndex);
                setup[vIndex] = 0;
                ui.setChooseItem(0);
            } else if (MainCanvas.isKeyPress(12)) { // 右
                UIRadioButton ui = (UIRadioButton) components.elementAt(vIndex);
                setup[vIndex] = 1;
                ui.setChooseItem(1);
            } else if (MainCanvas.isKeyPress(14)) {  // ok
            } else if (MainCanvas.isKeyPress(17)) {  // 左软键
                baseForm.addAboutForm("hangup", "确实要挂机吗？", (byte) 2, 140, 0);
            }
        } else if ("hangup".equals(fouceFormName)) {  // 处理挂机选项
            if (MainCanvas.isKeyPress(14) || MainCanvas.isKeyPress(17) || MainCanvas.isKeyPress(18)) {
                Player player = Player.getInstance();
                baseForm.setAboutForm(null);
                if (MainCanvas.isKeyPress(14) || MainCanvas.isKeyPress(17)) {
                    // 如果设置了巡逻挂机
                    if (setup[0] == 0) {
                        pCol = player.col;
                        pRow = player.row;
                    }
                    player.isHangup = true;
                    // 设置游戏中
                    MainCanvas.mc.setGameState((byte) 0);
                    MainCanvas.mc.releaseUI();
                } else {
                    player.isHangup = false;
                }
            }
        }
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
        UILabel lblOk = new UILabel(0, 0, 0, 0, "挂机", 15718815, (byte) 1, (byte) 0);
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
     * 获取世界地图寻径位置
     *
     * @param sMap 起始地图
     * @param eMap 结束地图
     * @return
     */
    public String[] aStar2World(String sMap, String eMap) {
        String[] path = null;
        int sIndex = -1;
        int eIndex = -1;
        int length = mapNames.length;
        // 首先查找地图所在索引
        for (int i = 0; i < mapNames.length; ++i) {
            if (sIndex != -1 && eIndex != -1) {
                break;
            } else if (sIndex == -1 && sMap.equals(mapNames[i])) {
                sIndex = i;
            } else if (eIndex == -1 && eMap.equals(mapNames[i])) {
                eIndex = i;
            }
        }
        // 当起始地图和目标地图都存在时，才进行查找
        if (sIndex != -1 && eIndex != -1) {
            int wIndex = 0;
            // 这里是从大到小排序 {地图索引, 父地图索引, G值, H值}  F = G + H，G是起点移动，H是当前到终点估算
            short[][] oList = new short[length][];
            // 使用关闭列表索引作为父index，因为关闭列表不需要排序
            short[][] cList = new short[length][];
            // 初始化开放列表，起点父格子为-1
            oList[0] = new short[]{(short) sIndex, -1, 0, (short) Math.abs(eIndex - sIndex)};
            boolean isEnd = false;
            while (wIndex > -1 && !isEnd) {
                // 取出最小成本，
                short[] min = oList[wIndex];
                // 减少计数
                --wIndex;
                // 将其加入clsit，这里直接使用地图索引位置
                cList[min[0]] = min;
                // 检查四个方向
                for (int i = 0; i < 4; ++i) {
                    short d = mapPaths[min[0]][1 + i];
                    // 如果可以行走，并且没有被加入关闭列表
                    if (d == eIndex) {  // 如果找到了
                        ++wIndex;
                        oList[wIndex] = new short[]{d, min[0], 0, 0};
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
                        short g = (short) (min[2] + 1);
                        short h = (short) Math.abs(eIndex - d);
                        if (oIndex == -1) {  // 未找到，加入开放列表
                            // 先自增oList
                            ++wIndex;
                            oList[wIndex] = new short[]{d, min[0], g, h};
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
                for (short i = 1; i <= wIndex; ++i) {
                    short[] km = oList[i];
                    short k = (short) (km[2] + km[3]);
                    short j = (short) (i - 1);
                    while (j >= 0 && (oList[j][2] + oList[j][3]) < k) {
                        oList[j + 1] = oList[j];
                        j--;
                    }
                    oList[j + 1] = km;
                }
            }
            // 如果找到了
            if (wIndex >= 0) {
                // 开始回溯
                short fid = oList[wIndex][1];
                String mName = mapNames[oList[wIndex][0]];
                Vector v = new Vector();
                v.addElement(mName);
                while (fid != -1) {
                    mName = mapNames[fid];
                    v.addElement(mName);
                    fid = cList[fid][1];
                }
                path = new String[v.size()];
                for (short i = 0; i < v.size(); ++i) {
                    path[i] = (String) v.elementAt(i);
                }
            }
        }
        return path;
    }
}