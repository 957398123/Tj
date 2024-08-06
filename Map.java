import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Map {
    public static int MAP_H;
    public static byte changeMapPointIndex;
    private static KmkBM curBufMap;
    private static final byte ADD_ROW_COLUMN = 0;
    public static final byte TYPE_NULL = -1;
    public static final byte TYPE_WHITE = 1;
    public static final byte TYPE_BLACK = 2;
    public static final byte TYPE_ALL = 3;
    private static final short MAP_DATA = 2047;
    private static final short PUT_ROAD_DATA = 2048;
    private static final short TRANSLATE_AND = 15;
    private static final byte TRANSLATE_SHIFT_RIGHT = 12;
    static final byte TRANS_NONE = 0;
    static final byte TRANS_H = 1;
    static final byte TRANS_V = 2;
    static final byte TRANS_ROTATE_180 = 3;
    private static final byte TILE_SIGN = 1;
    public static final byte TILE_WIDTH = 20;
    public static final byte TILE_HEIGHT = 10;
    public static final byte CURRENT_POINT_ARRAYDD = 3;
    public static int[][] curPointArray;
    public static byte curMapType;
    public static short currentMapID;
    public static int currentWindowX;
    public static int currentWindowY;
    public static String description;
    private static Image[] imageCurrentMapTile;
    public static int currentTotalColumn;
    public static int currentTotalRow;
    private static int currentTopX;
    private static short[][] currentMapData;
    private static short[][] iconRefe;
    private static int columnLeft;
    private static int columnRight;
    private static int rowTop;
    private static int rowBottom;
    public static String currentMapName;
    public static String[] nextMapName;
    public byte type;
    private static Map instance;
    private static int MAP_X;
    private static int MAP_Y;
    private static int MAP_TX;
    private static int MAP_TY;
    public static int NUMBER_OF_PLACES;
    public static int[][] regionPos;
    public static int[][] regionProps;
    public static String[] regionName;
    public static int[][] regionLines;
    public static int currentWMapID;
    private static final int COLOR_BLACK = 0;
    private static final int COLOR_SQUARE = 8415039;
    private static final int COLOR_GRAY = 2631720;
    private static final int COLOR_GREEN = 65280;
    private static final byte SQUARE_SIZE = 17;
    private static final byte SQUARE_SIZE2 = 13;
    private static byte CHOOSED_PLACE_SIZE;
    public static int choosedPlace;
    private static MImage mImgWorldMap;
    private static byte nowTick;
    public static boolean isDrawPlaceName;
    public static byte drawPlaceNameCount;
    public static byte colorIndex;
    private static int[][] bufWin;
    public static int[][] FLAGS;

    private Map() {
    }

    public static Map getInstance() {
        if (instance == null) {
            instance = new Map();
        }

        return instance;
    }

    /**
     * 地图绘制
     * @param g 
     */
    public void draw(Graphics g) {
        // 绘制地面
        drawTileToBuffer(g);
        // 绘制当前选择对象
        if (ObjManager.currentTarget != null) {
            ObjManager.currentTarget.drawSelect(g);
        }
        // 绘制地面上的障碍物
        drawBigObj(g, columnLeft, rowTop, columnRight + 0, rowBottom + 0);
        // 绘制玩家头顶的箭头
        Player.getInstance().drawPlayerArraw(g);
        Player.getInstance().drawCollection(g);
        for(int k = 0; k < ObjManager.vectorObj.size(); ++k) {
            GameObj tmpgo = (GameObj)ObjManager.vectorObj.elementAt(k);
            if (tmpgo != null && tmpgo.bNeedTitle) {
                tmpgo.drawTitle(g);
                tmpgo.bNeedTitle = false;
            }
        }
        // 绘制地图出口提示
        drawExitName(g);
        // 绘制过图的时候，门口出现的当前地图和提示
        drawPlaceName(g);
    }

    private void drawExitName(Graphics g) {
        int[] rectX = new int[4];
        int[] rectY = new int[4];
        int lozengeX = 0;
        int lozengeY = 0;
        byte rectH = (byte)(Cons.FONT_SIZE[MainCanvas.MOBILE_TELEPHONE_TYPE][1] + 3);
        byte offsetRectY = 75;
        byte offsetRectColumnX = 3;
        byte offsetRectRowX = -8;
        boolean isRow = false;
        int[] rectW = new int[4];
        String[] myName = new String[4];
        int doorCount = 0;
        int i = 0;

        for(int kk = curPointArray.length; i < kk && curPointArray[i][0] != -1; ++i) {
            if (i % 2 == 0) {
                if (curPointArray[i][0] > curPointArray[i + 1][0]) {
                    lozengeX = getCurrentCellCenterX(curPointArray[i + 1][0], curPointArray[i + 1][1]);
                    lozengeY = getCurrentCellCenterY(curPointArray[i + 1][0], curPointArray[i + 1][1]);
                } else if (curPointArray[i][0] < curPointArray[i + 1][0]) {
                    lozengeX = getCurrentCellCenterX(curPointArray[i][0], curPointArray[i][1]);
                    lozengeY = getCurrentCellCenterY(curPointArray[i][0], curPointArray[i][1]);
                    isRow = false;
                } else {
                    if (curPointArray[i][1] > curPointArray[i + 1][1]) {
                        lozengeX = getCurrentCellCenterX(curPointArray[i + 1][0], curPointArray[i + 1][1]);
                        lozengeY = getCurrentCellCenterY(curPointArray[i + 1][0], curPointArray[i + 1][1]);
                    } else if (curPointArray[i][1] < curPointArray[i + 1][1]) {
                        lozengeX = getCurrentCellCenterX(curPointArray[i][0], curPointArray[i][1]);
                        lozengeY = getCurrentCellCenterY(curPointArray[i][0], curPointArray[i][1]);
                    }

                    isRow = true;
                }

                myName[doorCount] = nextMapName[doorCount << 1];
                int len = myName[doorCount].length();
                if (isRow) {
                    rectX[doorCount] = lozengeX + offsetRectRowX - currentWindowX - (len * 12 >> 1);
                } else {
                    rectX[doorCount] = lozengeX + offsetRectColumnX - currentWindowX - (len * 12 >> 1);
                }

                rectY[doorCount] = lozengeY - currentWindowY - offsetRectY;
                ++doorCount;
            }
        }

        for(i = 0; i < doorCount; ++i) {
            rectW[i] = myName[i].length() * Cons.FONT_SIZE[MainCanvas.MOBILE_TELEPHONE_TYPE][0] + 6;
            int[] xPoints = new int[]{rectX[i] - 2, rectX[i] - 2 + rectW[i] + 4, rectX[i] - 2 + rectW[i] + 4, rectX[i] - 2};
            int[] yPoints = new int[]{rectY[i] - 2, rectY[i] - 2, rectY[i] - 2 + rectH + 4, rectY[i] - 2 + rectH + 4};
            int xOffset = 0;
            int yOffset = 0;
            int nPoints = 4;
            int argbColor = 0x662D1E00;
            GraphicsUtil.fillPolygon(g, xPoints, xOffset, yPoints, yOffset, nPoints, argbColor);
            g.setColor(2956800);
            g.drawRect(rectX[i] - 2, rectY[i] - 2, rectW[i] + 4, rectH + 4);
            g.setColor(16316576);
            g.drawString(myName[i], rectX[i] + (rectW[i] >> 1), rectY[i] + 1 + 2, 17);
        }

    }

    public static int getCurrentRow(int argCurrentY, int argCurrentX) {
        int row = (argCurrentY - (argCurrentX - currentTopX) * 10 / 20) / 10;
        if (row < 0 || row >= currentTotalRow) {
            PCChangeMap.isRow = true;
        }

        return row;
    }

    public static int getCurrentColumn(int argCurrentY, int argCurrentX) {
        int col = (argCurrentY + (argCurrentX - currentTopX) * 10 / 20) / 10;
        if (col < 0 || col >= currentTotalColumn) {
            PCChangeMap.isCol = true;
        }

        return col;
    }

    public void drawArea(Graphics g, int aCurrentWindowX, int aCurrentWindowY, int aScreenWidth, int aScreenHeight) {
        int beginCol = (aCurrentWindowY + (aCurrentWindowX - currentTopX) * 10 / 20) / 10;
        int beginRow = (aCurrentWindowY - (aCurrentWindowX - currentTopX) * 10 / 20) / 10;
        --beginCol;
        --beginRow;
        int drawX = (20 * (beginCol - beginRow) >> 1) + currentTopX - currentWindowX;
        int drawY = (10 * (beginCol + beginRow) >> 1) + 10 - currentWindowY;
        int i = -1;

        for(int kk = aScreenHeight / 10 + 2; i < kk; ++i) {
            int j = -1;

            for(int kk1 = aScreenWidth / 20 + 2; j < kk1; ++j) {
                for(int k = 1; k >= 0; --k) {
                    int cCol = beginCol + j;
                    int cRow = beginRow - j - k;
                    if (cCol >= 0 && cCol < currentTotalColumn && cRow >= 0 && cRow < currentTotalRow) {
                        int picIndex = currentMapData[cCol][cRow] & 2047;
                        int translate = currentMapData[cCol][cRow] >> 12 & 15;
                        int dx = drawX + j * 20 - iconRefe[picIndex][2] + k * 10;
                        int dy = drawY - iconRefe[picIndex][3] - k * 5;
                        this.drawBufferTileTemp(g, picIndex, dx, dy, translate);
                    }
                }
            }

            drawY += 10;
            ++beginCol;
            ++beginRow;
        }

    }

    private void drawTileToBuffer(Graphics argObjGraphics) {
        columnLeft = (currentWindowY + (currentWindowX - currentTopX) * 10 / 20) / 10;
        columnRight = (currentWindowY + MAP_H + (currentWindowX + MainCanvas.screenW - currentTopX) * 10 / 20) / 10 + 1;
        rowTop = (currentWindowY - (currentWindowX + MainCanvas.screenW - currentTopX) * 10 / 20) / 10;
        rowBottom = (currentWindowY + MAP_H - (currentWindowX - currentTopX) * 10 / 20) / 10 + 1;
        if (columnLeft < 0) {
            columnLeft = 0;
        }

        if (rowTop < 0) {
            rowTop = 0;
        }

        if (columnRight >= currentTotalColumn) {
            columnRight = currentTotalColumn;
        }

        if (rowBottom >= currentTotalRow) {
            rowBottom = currentTotalRow;
        }

        try {
            if (curBufMap == null) {
                curBufMap = KmkBM.createBufMap(this, (short)MainCanvas.screenW, (short)MAP_H);
            }

            curBufMap.draw(currentWindowX, currentWindowY);
            curBufMap.drawScreen(argObjGraphics);
            argObjGraphics.setClip(0, 0, MainCanvas.screenW, MAP_H);
        } catch (Exception var3) {
            Exception e = var3;
            e.printStackTrace();
        }

    }

    public static int getCurrentCellCenterX(int argColumn, int argRow) {
        return 20 * (argColumn - argRow >> 1) + currentTopX;
    }

    public static int getCurrentCellCenterY(int argColumn, int argRow) {
        return 10 * (argColumn + argRow >> 1) + 5;
    }

    public void initMap(short _mapID, byte loadfromwhere) {
        try {
            if (iconRefe == null) {
                this.loadIconRefe("/r0.dat");
            }

            if (loadfromwhere == 1) {
                this.loadMap(Util.parseMapData(PCChangeMap.recelveMapData, PCChangeMap.recelveMapData.length));
            } else if (loadfromwhere == 2) {
                this.loadMap(Util.parseMapData(PCChangeMap.bufferMapData, PCChangeMap.bufferMapData.length));
                PCChangeMap.bufferMapID = currentMapID;
                byte[] temp = new byte[PCChangeMap.bufferMapData.length];
                System.arraycopy(PCChangeMap.bufferMapData, 0, temp, 0, temp.length);
                PCChangeMap.bufferMapData = null;
                PCChangeMap.bufferMapData = new byte[PCChangeMap.recelveMapData.length];
                System.arraycopy(PCChangeMap.recelveMapData, 0, PCChangeMap.bufferMapData, 0, PCChangeMap.bufferMapData.length);
                PCChangeMap.recelveMapData = null;
                PCChangeMap.recelveMapData = new byte[temp.length];
                System.arraycopy(temp, 0, PCChangeMap.recelveMapData, 0, PCChangeMap.recelveMapData.length);
            }

            currentMapID = _mapID;
            currentTopX = 20 * (currentTotalRow >> 1);
            KmkBM.releaseInstance();
            curBufMap = null;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void adjustWindow(int x, int y) {
        currentWindowX = x - (MainCanvas.screenW >> 1);
        currentWindowY = y - (MAP_H >> 1);
    }

    private boolean loadMap(byte[] _data) {
        try {
            if (imageCurrentMapTile == null) {
                imageCurrentMapTile = new Image[iconRefe.length];
            }

            ByteArrayInputStream byteArrayIn = new ByteArrayInputStream(_data);
            DataInputStream dis = new DataInputStream(byteArrayIn);
            dis.readUTF();
            byte backIndex = dis.readByte();
            if (backIndex < 0) {
                backIndex = 0;
            }

            currentTotalColumn = dis.readInt();
            currentTotalRow = dis.readInt();
            dis.readInt();
            dis.readInt();
            currentMapData = new short[currentTotalColumn][currentTotalRow];

            for(int i = 0; i < currentTotalColumn; ++i) {
                for(int j = 0; j < currentTotalRow; ++j) {
                    currentMapData[i][j] = dis.readShort();
                }
            }

            Vector tempVector = new Vector();
            int eof = -1;

            while(true) {
                short tempA;
                if ((tempA = dis.readShort()) == eof) {
                    String[] mapTileName = new String[tempVector.size()];

                    for(int i = 0; i < mapTileName.length; ++i) {
                        mapTileName[i] = "f-" + (Integer)tempVector.elementAt(i) + ".png";
                    }

                    byte[][] tempPkg = new byte[mapTileName.length][];
                    tempPkg = Util.readPKG("/qqmap.pkg", mapTileName);

                    for(int i = 0; i < tempPkg.length; ++i) {
                        int tempNum = ((Integer)tempVector.elementAt(i)).intValue();
                        imageCurrentMapTile[tempNum - 1] = Util.loadImage(tempPkg[i]);
                    }

                    dis.close();
                    break;
                }

                if (tempA != 0 && imageCurrentMapTile[tempA - 1] == null) {
                    tempVector.addElement(new Integer(tempA));
                }
            }
        } catch (Exception var12) {
            Exception e = var12;
            e.printStackTrace();
            return false;
        }

        UIGameRun.init();
        return true;
    }

    private void loadIconRefe(String path) {
        int iconCount = 0;
        try {
            InputStream is = this.getClass().getResourceAsStream(path);
            DataInputStream dis = new DataInputStream(is);
            iconCount = dis.readInt();
            int refeCount = dis.readInt();
            iconRefe = new short[iconCount][refeCount];

            for(int i = 0; i < iconCount; ++i) {
                for(int j = 0; j < refeCount; ++j) {
                    iconRefe[i][j] = (short)dis.readInt();
                }
            }

            dis.close();
        } catch (Exception var8) {
            Exception e = var8;
            e.printStackTrace();
        }

    }

    private void drawBigObj(Graphics g, int leftColumn, int topRow, int rightColumn, int bottomRow) {
        if (leftColumn < 0) {
            leftColumn = 0;
        }

        if (topRow < 0) {
            topRow = 0;
        }

        if (rightColumn > currentTotalColumn) {
            rightColumn = currentTotalColumn;
        }

        if (bottomRow > currentTotalRow) {
            bottomRow = currentTotalRow;
        }

        for(int i = leftColumn; i < rightColumn; ++i) {
            for(int j = topRow; j < bottomRow; ++j) {
                int picIndex = currentMapData[i][j] & 2047;
                int translate = currentMapData[i][j] >> 12 & 15;
                int drawX;
                if ((currentMapData[i][j] >> 11 & 1) != 0) {
                    for(drawX = 0; drawX < ObjManager.vectorObj.size(); ++drawX) {
                        GameObj tmpgo = (GameObj)ObjManager.vectorObj.elementAt(drawX);
                        if (tmpgo != null && i == tmpgo.col && j == tmpgo.row && tmpgo.y - currentWindowY < MAP_H + 10) {
                            tmpgo.draw(g);
                            tmpgo.bNeedTitle = true;
                        }
                    }
                }

                if (iconRefe[picIndex][4] != 1 || picIndex == 0) {
                    int effectCols;
                    int m;
                    int picIndex_n;
                    if (picIndex == 0) {
                        drawX = j + 1;
                        if (drawX >= bottomRow) {
                            break;
                        }

                        picIndex_n = currentMapData[i][drawX] & 2047;
                        if (picIndex_n == 0) {
                            continue;
                        }

                        if (iconRefe[picIndex_n][4] == 0) {
                            if (((currentMapData[i][drawX] >>> 12 & 15) == 0 ? iconRefe[picIndex_n][1] : iconRefe[picIndex_n][0]) < 2) {
                                break;
                            }
                        } else {
                            effectCols = 0;

                            for(m = i; m < rightColumn; ++m) {
                                picIndex_n = currentMapData[m][drawX - 1] & 2047;
                                if (picIndex_n != 0) {
                                    effectCols = translate == 1 ? iconRefe[picIndex_n][1] : iconRefe[picIndex_n][0];
                                    if (effectCols > 1) {
                                        break;
                                    }
                                }
                            }

                            if (effectCols > 1) {
                                break;
                            }
                        }
                    }

                    if (iconRefe[picIndex][4] == 0) {
                        if (translate == 1) {
                            drawX = (20 * (i - j) >> 1) + currentTopX - currentWindowX - imageCurrentMapTile[picIndex - 1].getWidth() + iconRefe[picIndex][2];
                        } else {
                            drawX = (20 * (i - j) >> 1) + currentTopX - currentWindowX - iconRefe[picIndex][2];
                        }

                        picIndex_n = (10 * (i + j) >> 1) + 10 - currentWindowY - iconRefe[picIndex][3];
                        effectCols = drawX + imageCurrentMapTile[picIndex - 1].getWidth();
                        m = picIndex_n + imageCurrentMapTile[picIndex - 1].getHeight();
                        if (effectCols > 0 && drawX < MainCanvas.screenW && m > 0 && picIndex_n < MAP_H) {
                            Image mt = imageCurrentMapTile[picIndex - 1];
                            if (translate == 1) {
                                GraphicsUtil.drawImage(g, mt, drawX, picIndex_n, GraphicsUtil.LEFT | GraphicsUtil.TOP, GraphicsUtil.FLIP_HORIZONTAL);
                            } else {
                                g.drawImage(imageCurrentMapTile[picIndex - 1], drawX, picIndex_n, 20);
                            }
                        }

                        effectCols = translate == 1 ? iconRefe[picIndex][1] : iconRefe[picIndex][0];
                        if (effectCols > 1) {
                            this.drawBigObj(g, i - effectCols + 1, j + 1, i + 0, bottomRow + 0);
                        }
                    }
                }
            }
        }

    }

    public void delGO(GameObj go) {
        if (ObjManager.vectorObj.contains(go) && go.type == 5) {
            deleteFlag(go.col, go.row);
        }

    }

    public static void putInCell(int col, int row) {
        if (col < currentTotalColumn && row < currentTotalRow && col >= 0 && row >= 0) {
            if (currentMapData != null) {
                currentMapData[col][row] = (short)(currentMapData[col][row] | 2048);
            }
        }
    }

    public boolean isFloor(int col, int row) {
        if (col < currentTotalColumn && row < currentTotalRow && col >= 0 && row >= 0) {
            int tmpb = currentMapData[col][row] & 2047;
            return tmpb != 0 && iconRefe[tmpb][4] == 1;
        } else {
            return false;
        }
    }

    private void drawBufferTileTemp(Graphics g, int argMapIndex, int argDrawX, int argDrawY, int translate) {
        if (argMapIndex != 0 && iconRefe[argMapIndex][4] == 1) {
            if (KmkBM.isBufMap) {
                int imageW = imageCurrentMapTile[argMapIndex - 1].getWidth();
                int imageH = imageCurrentMapTile[argMapIndex - 1].getHeight();
                KmkBM.getInstance().updatePosition(g, imageCurrentMapTile[argMapIndex - 1], argDrawX, argDrawY, translate, (short)imageW, (short)imageH);
            } else if (translate == 0) {
                g.drawImage(imageCurrentMapTile[argMapIndex - 1], argDrawX, argDrawY, 20);
            } else {
                Image img = imageCurrentMapTile[argMapIndex - 1];
                if (translate == 1) {
                    GraphicsUtil.drawImage(g, img, argDrawX, argDrawY, GraphicsUtil.LEFT | GraphicsUtil.TOP, GraphicsUtil.FLIP_HORIZONTAL);
                } else if (translate == 2) {
                    GraphicsUtil.drawImage(g, img, argDrawX, argDrawY, GraphicsUtil.LEFT | GraphicsUtil.TOP, GraphicsUtil.FLIP_VERTICAL);
                } else if (translate == 3) {
                    GraphicsUtil.drawImage(g, img, argDrawX, argDrawY, GraphicsUtil.LEFT | GraphicsUtil.TOP, GraphicsUtil.ROTATE_180);
                }
            }
        }

    }

    public static void drawWorldMap(Graphics g) {
        autoChangeMapX(choosedPlace);
        MainCanvas.drawGroundback(g);
        g.setClip(5, 5, MainCanvas.screenW - 10, MainCanvas.screenH - 10);
        g.setColor(65280);
        drawLines(g);

        for(int i = 0; i < NUMBER_OF_PLACES; ++i) {
            g.setClip(5, 5, MainCanvas.screenW - 10, MainCanvas.screenH - 10);
            drawOne(g, i);
        }

        g.setClip(0, 0, MainCanvas.screenW, MainCanvas.screenH);
        drawBGofWorldMap(g, 2, 2, MainCanvas.screenW - 4, MainCanvas.screenH - 4);
        drawNowPlace(g, currentWMapID);
        drawChoosed(g, choosedPlace);
        g.setColor(10255690);
        g.drawString("返回", MainCanvas.screenW - 36 - 8, MainCanvas.screenH - 22, 20);
    }

    private static void drawBGofWorldMap(Graphics g, int x, int y, int w, int h) {
        g.setColor(920069);
        g.drawRect(x - 1, y - 1, w + 3, h + 3);
        g.setColor(9466695);
        g.drawRect(x, y, w, h);
        MainCanvas.mImgUI[3].draw(g, x, y, 0, false);
        MainCanvas.mImgUI[3].draw(g, x, y + h - 12, 1, false);
        MainCanvas.mImgUI[3].draw(g, x + w - 11, y, 2, false);
        MainCanvas.mImgUI[3].draw(g, x + w - 11, y + h - 12, 3, false);
    }

    private static void drawLines(Graphics g) {
        g.setColor(6431768);

        for(int i = 0; i < regionLines.length; ++i) {
            g.fillRect(MAP_X + regionLines[i][0], MAP_Y + regionLines[i][1], regionLines[i][2], regionLines[i][3]);
        }

    }

    public static void goNearByPlace(byte direction) {
        --direction;
        int result;
        if (direction >= 0 && direction < 4) {
            int neighborIndex = regionProps[choosedPlace][direction + 2];
            if (neighborIndex != -1) {
                result = neighborIndex;
            } else {
                result = choosedPlace;
            }
        } else {
            result = choosedPlace;
        }

        autoChangeMapX(result);
        choosedPlace = result;
    }

    private static void autoChangeMapX(int index) {
        int x = regionPos[index][0];
        int y = regionPos[index][1];
        MAP_TX = x - (MainCanvas.screenW - 13) / 2;
        MAP_TX *= -1;
        MAP_TY = y - (MainCanvas.screenH - 13) / 2;
        MAP_TY *= -1;
        adjustWin(MAP_TX, MAP_TY);
    }

    private static void drawOne(Graphics g, int index) {
        if (mImgWorldMap == null) {
            mImgWorldMap = new MImage("op.png", "/uiuse.pkg", 10, 10);
        }

        int x = regionPos[index][0];
        int y = regionPos[index][1];
        int dx = x - 8 - 1 + MAP_X;
        int dy = y - 8 - 1 + MAP_Y;
        g.setColor(0);
        g.fillRect(dx, dy, 17, 17);
        g.setColor(8415039);
        g.drawRect(dx, dy, 17, 17);
        dx = x - 6 - 1 + MAP_X;
        dy = y - 6 - 1 + MAP_Y;
        g.drawRect(dx, dy, 13, 13);
        g.setColor(2631720);
        dx = x - 5 + MAP_X;
        dy = y - 5 + MAP_Y;
        mImgWorldMap.draw(g, dx, dy, regionProps[index][0], false);
    }

    private static void drawNowPlace(Graphics g, int index) {
        int x = regionPos[index][0] + MAP_X - 9;
        int y = regionPos[index][1] + MAP_Y - 8;
        GameObj obj = Player.getInstance();
        if (obj == null) {
            System.err.println("error");
        } else {
            MainCanvas.mImgUI[32].draw(g, x, y, obj.originalImgID, false);
        }
    }

    private static void drawChoosed(Graphics g, int index) {
        if ((++nowTick & 15) == 15) {
            nowTick = 0;
        }

        if (nowTick % 4 < 2) {
            CHOOSED_PLACE_SIZE = 15;
        } else {
            CHOOSED_PLACE_SIZE = 17;
        }

        int x = regionPos[index][0];
        int y = regionPos[index][1];
        g.setColor(16770873);
        int dx = x - (CHOOSED_PLACE_SIZE >> 1) - 1 + MAP_X;
        int dy = y - (CHOOSED_PLACE_SIZE >> 1) - 1 + MAP_Y;
        g.drawRect(dx, dy, CHOOSED_PLACE_SIZE, CHOOSED_PLACE_SIZE);
        drawMapName(g, index);
    }

    private static void drawMapName(Graphics g, int index) {
        String name = regionName[index];
        name = name + "(" + Cons.WM_NAMEDESC[regionProps[index][1]] + ")";
        g.setColor(0);
        g.fillRect(32, 7, 180, 26);
        g.setColor(8415039);
        g.drawRect(32, 7, 180, 26);
        g.drawRect(34, 9, 176, 22);
        g.setColor(15182943);
        g.drawString(name, 122, 12, 17);
    }

    private void drawPlaceName(Graphics g) {
        byte rectY = 30;
        byte rectWidth = description.length() > currentMapName.length() ? (byte)(Cons.FONT_SIZE[1][1] * description.length() + 10) : (byte)(Cons.FONT_SIZE[1][1] * currentMapName.length() + 10);
        byte rectHeight = 45;
        int placeY = rectY + 6;
        int whoY = rectY + 6 + 14 + 4;
        byte totalCount = 16;
        byte times = 8;
        byte colorType = 0;
        int[][] color = new int[][]{{4866613, 8023640, 13740650, 16765320, 16765320, 13740650, 8023640, 4866613}, {1461506, 2392068, 3455750, 4718344, 4718344, 3455750, 2392068, 1461506}, {5046786, 8389892, 12257286, 16714504, 16714504, 12257286, 8389892, 5046786}, {5064450, 8418820, 12299782, 16772360, 16772360, 12299782, 8418820, 5064450}};
        if (isDrawPlaceName) {
            ++drawPlaceNameCount;
            if (drawPlaceNameCount == totalCount) {
                drawPlaceNameCount = 0;
                isDrawPlaceName = false;
                colorIndex = 0;
            } else if (drawPlaceNameCount % (totalCount / times) == 0) {
                ++colorIndex;
            }

            UIRim.drawRim(g, MainCanvas.screenW - rectWidth >> 1, rectY, rectWidth, rectHeight, (byte)4);
            if (PCChangeMap.isCounterpart) {
                colorType = 2;
            }

            g.setColor(color[colorType][colorIndex]);
            g.drawString(currentMapName, MainCanvas.screenW >> 1, placeY, 17);
            g.setColor(color[colorType][colorIndex]);
            g.drawString(description, MainCanvas.screenW >> 1, whoY, 17);
        }

    }

    public void resetObjGameObjArray() {
    }

    private static final void adjustWin(int sx, int sy) {
        int bufWinIndex = bufWin.length - 1;

        int dx;
        for(dx = 0; dx < bufWinIndex; ++dx) {
            bufWin[dx][0] = bufWin[dx + 1][0];
            bufWin[dx][1] = bufWin[dx + 1][1];
        }

        bufWin[bufWinIndex][0] = sx;
        bufWin[bufWinIndex][1] = sy;
        dx = 0;
        int dy = 0;

        for(int i = 0; i <= bufWinIndex; ++i) {
            dx += bufWin[i][0];
            dy += bufWin[i][1];
        }

        dx /= bufWinIndex + 1;
        dy /= bufWinIndex + 1;
        MAP_X = dx + MAP_X >> 1;
        MAP_Y = dy + MAP_Y >> 1;
    }

    public static void clearFlags() {
        FLAGS = (int[][])null;
        FLAGS = new int[15][2];

        for(int i = 0; i < FLAGS.length; ++i) {
            FLAGS[i][0] = -1;
            FLAGS[i][1] = -1;
        }

    }

    public static void addFlag(int x, int y) {
        for(int i = 0; i < FLAGS.length; ++i) {
            if (FLAGS[i][0] == -1) {
                FLAGS[i][0] = x;
                FLAGS[i][1] = y;
                break;
            }
        }

    }

    public static void deleteFlag(int x, int y) {
        for(int i = 0; i < FLAGS.length; ++i) {
            if (FLAGS[i][0] == x && FLAGS[i][1] == y) {
                FLAGS[i][0] = -1;
                FLAGS[i][1] = -1;
                return;
            }
        }

    }

    public static void curBufMapNull() {
        imageCurrentMapTile = null;
        curBufMap = null;
    }

    static {
        MAP_H = MainCanvas.screenH - 85;
        curPointArray = new int[8][3];
        curMapType = -1;
        currentMapID = 0;
        nextMapName = new String[8];
        instance = null;
        MAP_X = 0;
        MAP_Y = 0;
        MAP_TX = 0;
        MAP_TY = 0;
        NUMBER_OF_PLACES = 0;
        regionPos = (int[][])null;
        regionProps = (int[][])null;
        regionName = null;
        regionLines = (int[][])null;
        currentWMapID = 0;
        CHOOSED_PLACE_SIZE = 15;
        choosedPlace = -1;
        mImgWorldMap = null;
        nowTick = 0;
        bufWin = new int[4][2];
        FLAGS = new int[15][2];
    }
}
