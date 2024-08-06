
public class NPC extends GameObj {

    public static final byte EVENT_NONE = 0;
    public static final byte EVENT_CANNT_RECEIVE = 1;
    public static final byte EVENT_RECEIVE = 2;
    public static final byte EVENT_CAN_RECEIVE = 3;
    public static final byte EVENT_COMPLETE = 4;
    private static final int NPC_HP = 100;
    private static final int NPC_MP = 100;

    public NPC() {
        type = 3;
    }

    public void init(ByteArray initIn) {
        curStep = 0;
        objID = initIn.readInt();
        x = initIn.readInt();
        y = initIn.readInt();
        imgID = initIn.readByte();
        level = initIn.readByte();
        name = initIn.readUTF();
        maxHp = 100;
        curHp = 100;
        maxMp = 100;
        curMp = 100;
        col = Map.getCurrentColumn(y, x);
        row = Map.getCurrentRow(y, x);
        setAimRowAndColumn(row, col);
        Map.putInCell(col, row);
        setState((byte) 0);
        initEditorRes();
        setDirection((byte) 2);
    }

    public void setEventState(byte _s) {
        eventState = _s;
    }

    public void tick() {
    }

    public void nextFrame() {
    }

    public void tickBuffer() {
    }

    public void initEditorRes() {
    }
}
