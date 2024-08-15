
/**
 * 拓展能力类，用来增强天劫客户端
 *
 * @author yihua
 */
public class ExpandAbility {

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
     * 在PCArena的tick以后
     */
    public static void afterPCArenaTick() {
    }

    /**
     * 玩家tick前
     *
     * @param player
     */
    public static void beforePlayerTick(Player player) {
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
     * @param obj 
     */
    public static void beforeGameObjectTick(GameObj obj) {
    }

    /**
     * 在游戏对象tick以后
     * @param obj
     */
    public static void afterGameObjectTick(GameObj obj) {
    }
}
