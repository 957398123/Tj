
public class Monster extends GameObj {

    private static final int MONSTER_MP = 100;

    public Monster() {
        type = 2;
    }

    public void init(ByteArray initIn) {
        curStep = 0;
        objID = initIn.readInt();
        x = initIn.readInt();
        y = initIn.readInt();
        imgID = initIn.readByte();
        level = initIn.readByte();
        name = initIn.readUTF();
        maxHp = initIn.readInt();
        lastHp = curHp = initIn.readInt();
        eliteType = initIn.readByte();
        maxMp = 100;
        curMp = 100;
        action = initIn.readByte();
        col = Map.getCurrentColumn(y, x);
        row = Map.getCurrentRow(y, x);
        setAimRowAndColumn(row, col);
        Map.putInCell(col, row);
        setState((byte) 0);
        initEditorRes();
        setDirection((byte) 2);
    }

    public void setState(byte s) {
        super.setState(s);
    }

    public void setDirection(byte dir) {
        super.setDirection(dir);
    }

    public void tick() {
        super.tick();
        switch (state) {
            case 1: {
                findPath();
            }
            default: {
                nextFrame();
                Map.putInCell(col, row);
            }
        }
    }
}
