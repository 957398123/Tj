
import java.util.Vector;

public class ObjManager {

    /**
     * 当前选取的对象
     */
    public static GameObj currentTarget;
    public static GameObj showTarget;
    public static Vector vectorObj;
    private static ObjManager instance = null;

    private ObjManager() {
        vectorObj = new Vector();
        currentTarget = null;
        showTarget = null;
    }

    public static ObjManager getInstance() {
        if (instance == null) {
            instance = new ObjManager();
        }
        return instance;
    }

    public void tick() {
        if (currentTarget == null) {
            currentTarget = Player.getInstance();
        }
        for (int i = 0; i < vectorObj.size(); i++) {
            ((GameObj) vectorObj.elementAt(i)).tick();
            if (((GameObj) vectorObj.elementAt(i)).type == 1) {
                if (((GameObj) vectorObj.elementAt(i)).curHp <= 0) {
                    ((GameObj) vectorObj.elementAt(i)).setState((byte) 5);
                }
            }
        }
        if (currentTarget != null && !canBeSetTarget(currentTarget, 90)) {
            setCurrentTarget(Player.getInstance());
        }
    }

    public static void addObj(GameObj obj) {
        getInstance().addObjToList(obj);
    }

    public void addObjToList(GameObj obj) {
        if (obj != null && obj.type == 1) {
            OtherPlayer target = (OtherPlayer) obj;
            for (int i = 0; i < MainCanvas.mc.teamMates.size(); i++) {
                if (((GameObj) MainCanvas.mc.teamMates.elementAt(i)).objID == target.objID) {
                    MainCanvas.mc.teamMates.setElementAt(target, i);
                    break;
                }
            }
        }
        if (!vectorObj.contains(obj)) {
            vectorObj.addElement(obj);
        }
    }

    public void delObj(int objID, byte type) {
        getInstance().delObjFromList(objID, type);
    }

    public void delObjFromList(int objID, byte type) {
        GameObj tmpObj = getObjFromList(objID, type);
        if (tmpObj == null) {
            return;
        }
        if (tmpObj == currentTarget) {
            currentTarget = null;
        }
        if (tmpObj == showTarget) {
            showTarget = null;
        }
        if (tmpObj.type == 1) {
            for (int i = 0; i < MainCanvas.mc.teamMates.size(); i++) {
                if (tmpObj == MainCanvas.mc.teamMates.elementAt(i)) {
                    MainCanvas.mc.teamMates.setElementAt(tmpObj.clone(), i);
                    break;
                }
            }
        }
        Map.getInstance().delGO(tmpObj);
        vectorObj.removeElement(tmpObj);
    }

    public boolean removeAll() {
        boolean isAllMove = false;
        if (vectorObj.isEmpty()) {
            isAllMove = true;
        } else {
            for (int i = 0; i < vectorObj.size(); i++) {
                Map.getInstance().delGO((GameObj) vectorObj.elementAt(i));
            }
            vectorObj.removeAllElements();
            isAllMove = true;
        }
        return isAllMove;
    }

    private static boolean canBeSetTarget(GameObj target, int size) {
        return (target.x - Map.currentWindowX >= 0 && target.x - Map.currentWindowX <= MainCanvas.screenW && target.y - Map.currentWindowY >= 0 && target.y - Map.currentWindowY <= MainCanvas.screenH
                && Math.abs(target.x - (Player.getInstance()).x)
                * Math.abs(target.x - (Player.getInstance()).x)
                + Math.abs(target.y - (Player.getInstance()).y)
                * Math.abs(target.y - (Player.getInstance()).y) < size * size);
    }

    public static GameObj getObj(int objID) {
        return getInstance().getObjFromList(objID, 0);
    }
    
    public static OtherPlayer getOtherPlayer(int objID){
        return (OtherPlayer)getInstance().getObjFromList(objID, 0);
    }

    private GameObj getObjFromList(int objID, int type) {
        for (int i = 0; i < vectorObj.size(); i++) {
            GameObj obj = (GameObj) vectorObj.elementAt(i);
            if ((type == 0 || type == obj.type)
                    && objID == obj.objID) {
                return obj;
            }
        }
        return null;
    }

    public void attackPlayer(GameObj attacker) {
        if (!vectorObj.contains(attacker)) {
            addObj(attacker);
        }
        if (currentTarget == null) {
            currentTarget = attacker;
        } else if (!Util.isEnemy(Player.getInstance(), currentTarget)) {
            currentTarget = attacker;
        }
    }

    public void setCurrentTarget(GameObj obj) {
        if (obj == null) {
            return;
        }
        if (obj != Player.getInstance()) {
            if (obj.isEnemy) {
                Player.getInstance().setPkObj(obj);
            } else if (obj == (Player.getInstance()).pkObj && !PCGameObj.isPK) {
                Player.getInstance().setPkObj(null);
            } else if (PCGameObj.isPK) {
                Player.getInstance().setPkObj(PCGameObj.PEObj);
                PCGameObj.PEObj.setPkObj(Player.getInstance());
            }
            showTarget = obj;
            currentTarget = obj;
        } else {
            currentTarget = obj;
            showTarget = null;
        }
    }

    public void changeTarget() {
        if (currentTarget == null) {
            for (int i = 0; i < vectorObj.size(); i++) {
                GameObj ob = (GameObj) vectorObj.elementAt(i);
                if (ob.type != 5) {
                    if (canBeSetTarget(ob, 80)) {
                        setCurrentTarget(ob);
                    }
                }
            }
        } else {
            int index = vectorObj.indexOf(currentTarget);
            for (int i = 0; i < vectorObj.size(); i++) {
                int t = i + index;
                t %= vectorObj.size();
                GameObj ob = (GameObj) vectorObj.elementAt(t);
                if (ob.type != 5) {
                    if (currentTarget.objID != ob.objID) {
                        if (canBeSetTarget(ob, 80)) {
                            setCurrentTarget(ob);
                            return;
                        }
                        ob = null;
                    }
                }
            }
        }
    }

    public int getVectorSize() {
        if (vectorObj == null) {
            return 0;
        }
        return vectorObj.size();
    }

    public void releaseObj() {
        currentTarget = null;
        showTarget = null;
        vectorObj = null;
        Player.getInstance().releaseObj();
        instance = null;
        System.gc();
    }
}
