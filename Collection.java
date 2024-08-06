
import javax.microedition.lcdui.Graphics;

class Collection extends GameObj {

    public static final byte TYPE_FLAG = 4;
    private static MImage flagImg = null;
    private byte count = 0;

    Collection() {
        type = 5;
    }

    public String toString() {
        String returnStr = "Collection: ";
        returnStr = returnStr + "[ID]=" + objID;
        returnStr = returnStr + "  [LEVE]=" + level;
        returnStr = returnStr + "  [TYPE]=" + imgID;
        return returnStr;
    }

    public void init(ByteArray initIn) {
        objID = initIn.readInt();
        imgID = initIn.readByte();
        level = initIn.readByte();
        x = initIn.readInt();
        y = initIn.readInt();
        col = Map.getCurrentColumn(y, x);
        row = Map.getCurrentRow(y, x);
        setAimRowAndColumn(row, col);
        Map.putInCell(col, row);
        if (flagImg == null) {
            flagImg = new MImage("uiflag.png", "/uiuse.pkg", 20, 40);
        }

        Map.putInCell(col, row);
        switch (imgID) {
            case 0:
                name = "矿类";
                break;
            case 1:
                name = "木类";
                break;
            case 2:
                name = "草类";
                break;
            case 3:
                name = "皮类";
                break;
            case 4:
                Map.addFlag(col, row);
                name = "旗帜";
        }

        setState((byte) 0);
    }

    public void setState(byte _s) {
        super.setState(_s);
        switch (state) {
            case 0:
            case 5:
            default:
        }
    }

    public void tick() {
    }

    public void tickBuffer() {
    }

    public void draw(Graphics g) {
        byte tempOffset = 6;
        if (state != 5) {
            if (imgID == 4) {
                int dx = x - Map.currentWindowX - 10;
                int dy = y - Map.currentWindowY - 21;
                if (flagImg != null) {
                    flagImg.draw(g, dx, dy - 15, 0, false);
                }

            } else {
                MainCanvas.mImgUI[38].draw(g, x - Map.currentWindowX - (MainCanvas.mImgUI[38].frame_w >> 1), y - Map.currentWindowY - MainCanvas.mImgUI[38].frame_h + tempOffset, imgID, false);
                if (count < 2) {
                    MainCanvas.mImgUI[39].draw(g, x - Map.currentWindowX - (MainCanvas.mImgUI[38].frame_w >> 1), y - Map.currentWindowY - MainCanvas.mImgUI[38].frame_h, 0, 0);
                    MainCanvas.mImgUI[39].draw(g, 9 + x - Map.currentWindowX - (MainCanvas.mImgUI[38].frame_w >> 1), 5 + y - Map.currentWindowY - MainCanvas.mImgUI[38].frame_h, 0, 0);
                    MainCanvas.mImgUI[39].draw(g, 14 + x - Map.currentWindowX - (MainCanvas.mImgUI[38].frame_w >> 1), 1 + y - Map.currentWindowY - MainCanvas.mImgUI[38].frame_h, 0, 0);
                } else {
                    MainCanvas.mImgUI[39].draw(g, 6 + x - Map.currentWindowX - (MainCanvas.mImgUI[38].frame_w >> 1), 1 + y - Map.currentWindowY - MainCanvas.mImgUI[38].frame_h, 0, 0);
                    MainCanvas.mImgUI[39].draw(g, 1 + x - Map.currentWindowX - (MainCanvas.mImgUI[38].frame_w >> 1), 7 + y - Map.currentWindowY - MainCanvas.mImgUI[38].frame_h, 0, 0);
                    MainCanvas.mImgUI[39].draw(g, 13 + x - Map.currentWindowX - (MainCanvas.mImgUI[38].frame_w >> 1), 7 + y - Map.currentWindowY - MainCanvas.mImgUI[38].frame_h, 0, 0);
                }

                ++count;
                if (count > 3) {
                    count = 0;
                }

            }
        }
    }
}
