import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Random;
import java.util.Vector;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotFoundException;

public abstract class Util {

    public static boolean[] shopSign = new boolean[18];
    static boolean[] isInitRes = new boolean[7];
    private static Random random = new Random();
    private static short ROLE_EDIT_VERSION = 100;
    public final static int NO_TRANSFORM = 0;
    /**
     * 水平图像翻转
     */
    public final static int FLIP_HORIZONTAL = 8192;
    /**
     * 垂直图像翻转
     */
    public final static int FLIP_VERTICAL = 16384;
    public final static int ROTATE_90 = 90;
    public final static int ROTATE_180 = 180;
    public final static int ROTATE_270 = 270;
    public final static int ROTATE_90_FLIP_HORIZ = 8282;
    public final static int ROTATE_90_FLIP_VERT = ROTATE_90 | FLIP_VERTICAL;

    ;

    public Util() {
    }

    public static byte[] parseMapData(byte[] data, int size) {
        byte[] newMapData = null;
        try {
            newMapData = GZIP.inflate(data);
        } catch (Exception e) {
            System.out.println("[解析数据]：解析地图数据出错！");
        }
        return newMapData;
    }

    public static Image loadImage(String path) {
        Image img = null;

        try {
            img = Image.createImage(path);
        } catch (IOException var3) {
            IOException e = var3;
            e.printStackTrace();
        }

        return img;
    }

    public static Image loadImage(byte[] aArray) {
        Image img = Image.createImage(aArray, 0, aArray.length);
        return img;
    }

    public static int getRandom(int num) {
        int tempRandom = random.nextInt() << 1 >>> 1;
        return tempRandom % num;
    }

    public static byte[] rwDOC(boolean isRead, byte[] doc, String rmsName) {
        byte[] buf = null;
        if (doc == null && !isRead) {
            return null;
        } else {
            try {
                if (!isRead) {
                    try {
                        RecordStore.deleteRecordStore(rmsName);
                    } catch (Exception var5) {
                    }
                }

                RecordStore recordStore = RecordStore.openRecordStore(rmsName, true);
                if (recordStore.getNumRecords() == 0) {
                    if (!isRead) {
                        recordStore.addRecord(doc, 0, doc.length);
                    }
                } else if (isRead) {
                    buf = recordStore.getRecord(1);
                } else {
                    recordStore.setRecord(1, doc, 0, doc.length);
                }

                recordStore.closeRecordStore();
            } catch (Exception var6) {
            }

            return buf;
        }
    }

    static void saveRecord(byte[] doc, String rmsName) {
        rwDOC(false, doc, rmsName);
    }

    static void saveRecord(String[] str, String rmsName) {
        if (str != null) {
            byte[] buf = null;
            ByteArray ba = new ByteArray();

            for (int i = 0; i < str.length; ++i) {
                ba.writeUTF(str[i]);
            }

            buf = ba.toByteArray();
            ba.close();
            rwDOC(false, buf, rmsName);
        }
    }

    static byte[] readRecord(String rmsName) {
        return rwDOC(true, (byte[]) null, rmsName);
    }

    static String[] readRecord(String rmsName, int lg) {
        String[] str = new String[lg];
        byte[] buf = rwDOC(true, (byte[]) null, rmsName);
        if (buf == null) {
            return null;
        } else {
            ByteArray ba = new ByteArray(buf);

            for (int i = 0; i < lg; ++i) {
                str[i] = ba.readUTF();
            }

            return str;
        }
    }

    public static String encodeUnicode(String gbString) {
        char[] utfBytes = gbString.toCharArray();
        String unicodeBytes = "";

        for (int byteIndex = 0; byteIndex < utfBytes.length; ++byteIndex) {
            String hexB = Integer.toHexString(utfBytes[byteIndex]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }

            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }

        return unicodeBytes;
    }

    public static void drawImage(int argImageX, int argImageY, int argImageWidth, int argImageHeight, int argDrawX, int argDrawY, Graphics g, Image image) {
        g.setClip(argDrawX, argDrawY, argImageWidth, argImageHeight);
        g.drawImage(image, argDrawX - argImageX, argDrawY - argImageY, 20);
        g.setClip(0, 0, MainCanvas.screenW, MainCanvas.screenH);
    }

    public static void drawMirrorImage(int argImageX, int argImageY, int argImageWidth, int argImageHeight, int argScreenX, int argScreenY, Graphics g, Image image) {
        g.setClip(argScreenX, argScreenY, argImageWidth, argImageHeight);
        GraphicsUtil.drawImage(g, image, argScreenX - argImageX, argScreenY - argImageY, GraphicsUtil.LEFT | GraphicsUtil.TOP, GraphicsUtil.FLIP_HORIZONTAL);
        g.setClip(0, 0, MainCanvas.screenW, MainCanvas.screenH);
    }

    public static short[] readPdatFile(String fileName) {
        short[] dataArray = new short[0];

        try {
            InputStream is = MainCanvas.mc.getClass().getResourceAsStream(fileName);
            DataInputStream dis = new DataInputStream(is);
            short version = dis.readShort();
            if (version != ROLE_EDIT_VERSION) {
            }

            boolean isShort = dis.readBoolean();
            short tileLength = dis.readShort();
            dataArray = new short[tileLength * 4];
            int i;
            if (isShort) {
                for (i = 0; i < tileLength * 4; ++i) {
                    dataArray[i] = dis.readShort();
                }
            } else {
                for (i = 0; i < tileLength * 4; ++i) {
                    dataArray[i] = (short) (dis.readByte() & 255);
                }
            }

            dis.close();
        } catch (Exception var8) {
            Exception e = var8;
            e.printStackTrace();
        }

        return dataArray;
    }

    public static short[][] readTdatFile(String fileName) {
        short[][] dataArray = new short[0][0];

        try {
            InputStream is = (new Object()).getClass().getResourceAsStream(fileName);
            DataInputStream dis = new DataInputStream(is);
            short version = dis.readShort();
            if (version != ROLE_EDIT_VERSION) {
            }

            boolean isShort = dis.readBoolean();
            short frameLength = dis.readShort();
            dataArray = new short[frameLength][];
            short tileLength = 0;
            int i;
            int j;
            if (isShort) {
                for (i = 0; i < frameLength; ++i) {
                    tileLength = (short) (dis.readByte() & 255);
                    dataArray[i] = new short[tileLength * 5];

                    for (j = 0; j < dataArray[i].length; ++j) {
                        dataArray[i][j] = dis.readShort();
                    }
                }
            } else {
                for (i = 0; i < frameLength; ++i) {
                    tileLength = (short) (dis.readByte() & 255);
                    dataArray[i] = new short[tileLength * 5];

                    for (j = 0; j < dataArray[i].length; j += 5) {
                        dataArray[i][j] = (short) dis.readByte();
                        dataArray[i][j + 1] = (short) (dis.readByte() & 255);
                        dataArray[i][j + 2] = (short) dis.readByte();
                        dataArray[i][j + 3] = (short) dis.readByte();
                        dataArray[i][j + 4] = (short) dis.readByte();
                    }
                }
            }

            dis.close();
        } catch (Exception var10) {
            Exception e = var10;
            e.printStackTrace();
        }

        return dataArray;
    }

    public static short[][] readFdatFile(String fileName, int offset) {
        short[][] dataArray = new short[0][0];

        try {
            InputStream is = (new Object()).getClass().getResourceAsStream(fileName);
            DataInputStream dis = new DataInputStream(is);
            short version = dis.readShort();
            if (version != ROLE_EDIT_VERSION) {
            }

            boolean isShort = dis.readBoolean();
            short frameLength = dis.readShort();
            dataArray = new short[frameLength][];
            short tileLength = 0;
            int i;
            int j;
            if (isShort) {
                for (i = 0; i < frameLength; ++i) {
                    tileLength = (short) (dis.readByte() & 255);
                    dataArray[i] = new short[tileLength * 4];

                    for (j = 0; j < dataArray[i].length; ++j) {
                        if (j == 0) {
                            dataArray[i][j] = (short) (dis.readShort() - offset);
                        } else {
                            dataArray[i][j] = dis.readShort();
                        }
                    }
                }
            } else {
                for (i = 0; i < frameLength; ++i) {
                    tileLength = (short) (dis.readByte() & 255);
                    dataArray[i] = new short[tileLength * 4];

                    for (j = 0; j < dataArray[i].length; j += 4) {
                        dataArray[i][j] = (short) ((dis.readByte() & 255) - offset);
                        dataArray[i][j + 1] = (short) dis.readByte();
                        dataArray[i][j + 2] = (short) dis.readByte();
                        dataArray[i][j + 3] = (short) dis.readByte();
                    }
                }
            }

            dis.close();
        } catch (Exception var11) {
            Exception e = var11;
            e.printStackTrace();
        }

        return dataArray;
    }

    public static short[][] readMdatFile(String fileName) {
        short[][] dataArray = new short[0][0];

        try {
            InputStream is = (new Object()).getClass().getResourceAsStream(fileName);
            DataInputStream dis = new DataInputStream(is);
            short version = dis.readShort();
            if (version != ROLE_EDIT_VERSION) {
            }

            boolean isShort = dis.readBoolean();
            short motionLength = dis.readShort();
            dataArray = new short[motionLength][];
            short frameLength = 0;
            int i;
            int j;
            if (isShort) {
                for (i = 0; i < motionLength; ++i) {
                    frameLength = (short) (dis.readByte() & 255);
                    dataArray[i] = new short[frameLength];

                    for (j = 0; j < dataArray[i].length; ++j) {
                        dataArray[i][j] = dis.readShort();
                    }
                }
            } else {
                for (i = 0; i < motionLength; ++i) {
                    frameLength = (short) (dis.readByte() & 255);
                    dataArray[i] = new short[frameLength];

                    for (j = 0; j < dataArray[i].length; ++j) {
                        dataArray[i][j] = (short) (dis.readByte() & 255);
                    }
                }
            }

            dis.close();
        } catch (Exception var10) {
            Exception e = var10;
            e.printStackTrace();
        }

        return dataArray;
    }

    public static void drawRoleEditFrame(Image img, Graphics g, short[] frameDatas, short[] pdatDatas, int x, int y, boolean isMirror) {
        int transform = NO_TRANSFORM;

        for (int i = 0; i < frameDatas.length; i += 4) {
            short attribute = frameDatas[i + 1];
            short picIndex = frameDatas[i];
            if ((attribute & 1) == 0) {
                int flip = (attribute & 8) >> 3;
                int rotate = (attribute & 6) >> 1;
                int type = rotate + (flip == 0 ? 0 : 1 + flip << 1);
                if (type == 8) {
                    type = 4;
                } else if (type == 9) {
                    type = 5;
                }

                if (isMirror) {
                    if (type != 0 && type != 4) {
                        type = 8 - type;
                    } else {
                        type = 4 - type;
                    }
                }

                short pos = (short) (picIndex << 2);
                short picOffsetX = pdatDatas[pos];
                short picOffsetY = pdatDatas[pos + 1];
                short picWidth = pdatDatas[pos + 2];
                short picHeight = pdatDatas[pos + 3];
                short tempPicWidth = picWidth;
                switch (type) {
                    case 0:
                        transform = NO_TRANSFORM;
                        tempPicWidth = picWidth;
                        break;
                    case 1:
                        transform = ROTATE_90;
                        tempPicWidth = picHeight;
                        break;
                    case 2:
                        transform = ROTATE_180;
                        tempPicWidth = picWidth;
                        break;
                    case 3:
                        transform = ROTATE_270;
                        tempPicWidth = picHeight;
                        break;
                    case 4:
                        transform = FLIP_HORIZONTAL;
                        tempPicWidth = picWidth;
                        break;
                    case 5:
                        transform = ROTATE_90_FLIP_VERT;
                        tempPicWidth = picHeight;
                        break;
                    case 6:
                        transform = FLIP_VERTICAL;
                        tempPicWidth = picWidth;
                        break;
                    case 7:
                        transform = ROTATE_90_FLIP_HORIZ;
                        tempPicWidth = picHeight;
                }

                int drawX;
                if (!isMirror) {
                    drawX = x + frameDatas[i + 2];
                } else {
                    drawX = x - (frameDatas[i + 2] + tempPicWidth);
                }

                int drawY = y + frameDatas[i + 3];
                drawRegion(g, img, picOffsetX, picOffsetY, picWidth, picHeight, drawX, drawY, transform);
            }
        }

        g.setClip(0, 0, MainCanvas.screenW, MainCanvas.screenH);
    }

    public static void drawRegion(Graphics g, Image img, int xSrc, int ySrc, int width, int height, int xDest, int yDest, int transform) {
        if (transform != NO_TRANSFORM && transform != FLIP_HORIZONTAL && transform != FLIP_VERTICAL && transform != ROTATE_180) {
            if (transform == ROTATE_90 || transform == ROTATE_270 || transform == ROTATE_90_FLIP_HORIZ || transform == ROTATE_90_FLIP_VERT) {
                g.setClip(xDest, yDest, height, width);
            }
        } else {
            g.setClip(xDest, yDest, width, height);
        }

        int xOffset = 0;
        int yOffset = 0;
        if (transform == NO_TRANSFORM) {
            xOffset = xSrc;
            yOffset = ySrc;
            g.drawImage(img, xDest - xOffset, yDest - yOffset, 20);
        } else {
            if (transform == FLIP_HORIZONTAL) {
                xOffset = img.getWidth() - xSrc - width;
                yOffset = ySrc;
            } else if (transform == FLIP_VERTICAL) {
                xOffset = xSrc;
                yOffset = img.getHeight() - ySrc - height;
            } else if (transform == ROTATE_90) {
                xOffset = ySrc;
                yOffset = img.getWidth() - xSrc - width;
            } else if (transform == ROTATE_180) {
                xOffset = img.getWidth() - xSrc - width;
                yOffset = img.getHeight() - ySrc - height;
            } else if (transform == ROTATE_270) {
                xOffset = img.getHeight() - ySrc - height;
                yOffset = xSrc;
            } else if (transform == ROTATE_90_FLIP_HORIZ) {
                xOffset = img.getHeight() - ySrc - height;
                yOffset = img.getWidth() - xSrc - width;
            } else if (transform == ROTATE_90_FLIP_VERT) {
                xOffset = ySrc;
                yOffset = xSrc;
            }
            GraphicsUtil.drawImage(g, img, xDest - xOffset, yDest - yOffset, GraphicsUtil.LEFT | GraphicsUtil.TOP, transform);
        }

    }

    public static String[] wrapText(String s, int usable, Font font) {
        if (usable == 0) {
            String[] temp = new String[]{s};
            return temp;
        } else {
            String ss = null;
            ss = new String(s + "  ");
            int advance = 0;
            int idxBegin = 0;
            int idxEnd = 0;
            int len = ss.length();
            Vector v = null;
            v = new Vector(font.stringWidth(ss) / usable, 4);

            while (idxBegin < len) {
                char tmp;
                for (tmp = 0; idxEnd < len; ++idxEnd) {
                    char chr = ss.charAt(idxEnd);
                    if (chr == '\n') {
                        break;
                    }

                    int charSize = font.charWidth(chr);
                    if (advance + charSize > usable) {
                        break;
                    }

                    advance += charSize;
                }

                boolean truncate = false;
                if (idxEnd < len) {
                    switch (ss.charAt(idxEnd)) {
                        case '\n':
                        case ' ':
                            break;
                        default:
                            truncate = true;
                            tmp = ss.charAt(idxEnd);
                    }
                }

                v.addElement(idxEnd > idxBegin ? ss.substring(idxBegin, idxEnd) : "");
                ++idxEnd;
                idxBegin = idxEnd;
                if (truncate) {
                    --idxBegin;
                    advance = font.charWidth(tmp);
                } else {
                    advance = 0;
                }
            }

            String[] list = new String[v.size()];

            for (idxBegin = 0; idxBegin < list.length; ++idxBegin) {
                list[idxBegin] = (String) v.elementAt(idxBegin);
            }

            return list;
        }
    }

    public static String[] colorChangeLine(UIComponent component, String s, int useWidth, Font font) {
    char[] tempChars = s.toCharArray();
    int lg = tempChars.length;
    int[] sign = new int[34];
    int number = 0;
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < lg; i++) {
      if (tempChars[i] == '$') {
        sign[number] = i;
        number++;
      } else {
        sb.append(tempChars[i]);
      } 
    } 
    String[] strs = wrapText(sb.toString(), useWidth, font);
    lg = strs.length;
    component.colorSigns = new byte[lg][];
    int[][] start_end = new int[lg][2];
    for (int j = 0; j < lg; j++) {
      int i1 = strs[j].length();
      component.colorSigns[j] = new byte[i1];
      if (j == 0) {
        start_end[j][0] = 0;
        start_end[j][1] = i1 - 1;
      } else {
        start_end[j][0] = start_end[j - 1][1] + 1;
        start_end[j][1] = start_end[j][0] + i1 - 1;
      } 
    } 
    Vector vector = new Vector(4);
    for (int k = 0; k < number; k += 2) {
      sign[k] = sign[k] - k;
      sign[k + 1] = sign[k + 1] - k + 1 + 1;
      int a = getColorPlace(sign[k], start_end);
      int b = getColorPlace(sign[k + 1], start_end);
      if (a == b) {
        int[] cs = new int[3];
        cs[0] = a;
        cs[1] = sign[k] - start_end[a][0];
        cs[2] = sign[k + 1] - start_end[a][0];
        vector.addElement(cs);
      } else {
        int temp = b - a;
        if (temp > 1)
          for (int i1 = a + 1; i1 < b; i1++) {
            int[] arrayOfInt = new int[3];
            arrayOfInt[0] = i1;
            arrayOfInt[1] = 0;
            arrayOfInt[2] = strs[i1].length() - 1;
            vector.addElement(arrayOfInt);
          }  
        int[] cs = new int[3];
        cs[0] = a;
        cs[1] = sign[k] - start_end[a][0];
        cs[2] = start_end[a][1] - start_end[a][0];
        vector.addElement(cs);
        cs = new int[3];
        cs[0] = b;
        cs[1] = 0;
        cs[2] = sign[k + 1] - start_end[b][0];
        vector.addElement(cs);
      } 
    } 
    lg = vector.size();
    int[][] signs = new int[lg][];
    Enumeration e = vector.elements();
    int m = 0;
    while (e.hasMoreElements()) {
      signs[m] = (int[])e.nextElement();
      m++;
    } 
    for (int n = 0; n < lg; n++) {
      for (int i1 = signs[n][1]; i1 <= signs[n][2]; i1++)
        component.colorSigns[signs[n][0]][i1] = 1; 
    } 
    return strs;
  }

    public static final int getColorPlace(int place, int[][] colorPlace) {
        int lg = colorPlace.length;
        int lg1 = -1;

        for (int i = 0; i < lg; ++i) {
            if (place >= colorPlace[i][0] && place <= colorPlace[i][1]) {
                lg1 = i;
                break;
            }
        }

        return lg1;
    }

    public static void drawNumberInCircle(Graphics g, int x, int y, int number) {
        if (number >= 0 && number <= 9) {
            MainCanvas.mImgUI[11].draw(g, x, y, 0, false);
            MainCanvas.mImgUI[12].draw(g, x + 2, y + 1, number, false);
        }
    }

    public static void drawStr(Graphics g, String s, int x, int y, int a, int color1, int color2, int type) {
        if (type == 0) {
            g.setColor(color1);
            g.drawString(s, x - 2, y, a);
            g.drawString(s, x + 2, y, a);
            g.drawString(s, x, y - 2, a);
            g.drawString(s, x, y + 2, a);
            g.drawString(s, x - 1, y - 1, a);
            g.drawString(s, x - 1, y + 1, a);
            g.drawString(s, x + 1, y - 1, a);
            g.drawString(s, x + 1, y + 1, a);
        }

        g.setColor(color2);
        g.drawString(s, x, y, a);
    }

    public static void drawNumber(Graphics g, byte number, int x, int y) {
        byte[] fig = new byte[2];
        byte nb = number;
        fig[0] = (byte) (nb / 10);
        fig[1] = (byte) (nb % 10);
        if (fig[0] != 0) {
            MainCanvas.mImgUI[12].draw(g, x, y, fig[0], false);
        }

        MainCanvas.mImgUI[12].draw(g, x + 4, y, fig[1], false);
    }

    public static int abs(int num) {
        return num > 0 ? num : -num;
    }

    /**
     * 判断两个游戏对象是否敌对关系
     * @param obj
     * @param target
     * @return 
     */
    public static boolean isEnemy(GameObj obj, GameObj target) {
        if (obj == null || target == null) {
            return false;
        }else if (obj.isEnemy || target.isEnemy) {
            return true;
        } else if (obj.type == 2 || target.type == 2) {
            return true;
        } else if (obj.type != 5 && target.type != 5) {
            if (target.group != obj.group) {
                return true;
            } else {
                return obj.pkObj == target;
            }
        } else {
            return false;
        }
    }

    public static final byte[][] readPKG(String pkgPath, String[] filenames) {
        byte[][] tmpArr = new byte[filenames.length][];
        int fileCount = 0;

        try {
            InputStream is = (new Object()).getClass().getResourceAsStream(pkgPath);
            DataInputStream dis = new DataInputStream(is);
            String fileType = dis.readUTF();
            if (0 != fileType.compareTo("PKG0")) {
                return (byte[][]) null;
            }

            fileCount = dis.readInt();
            short[] fsize = new short[fileCount];
            String[] fname = new String[fileCount];

            int i;
            for (i = 0; i < fileCount; ++i) {
                fname[i] = dis.readUTF();
                fsize[i] = (short) dis.readInt();
            }

            for (i = 0; i < fileCount; ++i) {
                int j;
                for (j = 0; j < filenames.length && filenames[j].compareTo(fname[i]) != 0; ++j) {
                }

                if (j >= filenames.length) {
                    dis.skip((long) fsize[i]);
                } else {
                    tmpArr[j] = new byte[fsize[i]];
                    dis.readFully(tmpArr[j]);
                }
            }

            dis.close();
            dis = null;
            is = null;
        } catch (Exception var11) {
        }

        return tmpArr;
    }

    public static byte[] readPKG(String pkgPath, String filename) {
        byte[] tmpArr = null;
        int fileCount = 0;
        int fsize = 0;
        int thisFileSize = -1;
        String fname = null;

        try {
            InputStream is = (new Object()).getClass().getResourceAsStream(pkgPath);
            DataInputStream dis = new DataInputStream(is);
            String fileType = dis.readUTF();
            if (0 != fileType.compareTo("PKG0")) {
                return null;
            }

            fileCount = dis.readInt();
            int preFileSize = 0;

            for (int i = 0; i < fileCount; ++i) {
                fname = dis.readUTF();
                fsize = dis.readInt();
                if (filename.compareTo(fname) == 0) {
                    thisFileSize = fsize;
                }

                if (thisFileSize == -1) {
                    preFileSize += fsize;
                }
            }

            if (thisFileSize == -1) {
                return null;
            }

            tmpArr = new byte[thisFileSize];
            dis.skip((long) preFileSize);
            dis.readFully(tmpArr);
            dis.close();
            dis = null;
            is = null;
        } catch (Exception var12) {
        }

        return tmpArr;
    }

    public static void deleteRecord(String recordStoreName) {
        if (recordStoreName != null && !recordStoreName.trim().equals("")) {
            try {
                RecordStore.deleteRecordStore(recordStoreName);
            } catch (RecordStoreNotFoundException var2) {
            } catch (RecordStoreException var3) {
            }

        }
    }

    public static boolean isAcceptedChars(char c) {
        for (int i = Cons.acceptChars.length - 1; i >= 0; --i) {
            if (c == Cons.acceptChars[i]) {
                return true;
            }
        }

        return false;
    }

    public static boolean isLegal(char ch) {
        int ti = ch;
        return ti >= 19968 && ti <= '龤' || ti >= '_' && ti <= 'z' || ti >= '0' && ti <= '9' || ti >= 'A' && ti <= 'Z';
    }

    public static boolean checkLegal(String s, byte n, Form form, boolean forMail) {
        int lg = s.length();
        char illegalChar = ' ';
        StringBuffer sb = new StringBuffer(s);
        boolean isLegal = true;
        if (sb.length() > n) {
            isLegal = false;
        } else {
            for (int i = 0; i < lg; ++i) {
                if (!isLegal(sb.charAt(i)) && (!forMail || !isAcceptedChars(sb.charAt(i)))) {
                    isLegal = false;
                    illegalChar = sb.charAt(i);
                    break;
                }
            }
        }

        if (form != null && !isLegal) {
            Alert alert = new Alert("", "您输入的内容中有非法字符" + illegalChar + ",请重新输入", (Image) null, AlertType.ALARM);
            alert.setTimeout(-2);
            MainCanvas.mc.aMidlet.display.setCurrent(alert, form);
        }

        return isLegal;
    }

    public static boolean checkLogin(String s) {
        int lg = s.trim().length();
        return lg >= 6 && lg <= 16;
    }

    public static String[] split(String str, int width, char token) {
        return split(str, width, token, true);
    }

    public static String[] split(String str, int width, char token, boolean flag) {
        int length = str.length();
        Vector tmp = new Vector();

        for (int i = 0; i < length; ++i) {
            String string = "";

            for (int j = 0; j < width; ++j) {
                char c = str.charAt(i);
                string = string + c;
                if (i == length - 1) {
                    tmp.addElement(string);
                    break;
                }

                if (c == '\n' && flag || c == token) {
                    tmp.addElement(string);
                    break;
                }

                if (j == width - 1) {
                    if (flag) {
                        string = string + "\n";
                    }

                    tmp.addElement(string);
                    break;
                }

                ++i;
            }
        }

        String[] result = new String[tmp.size()];

        for (int i = 0; i < result.length; ++i) {
            result[i] = ((String) tmp.elementAt(i)).replace(token, ' ').trim();
        }

        return result;
    }

    public static String[] splitToken(String str, char token) {
        int length = str.length();
        Vector tmp = new Vector();

        for (int i = 0; i < length; ++i) {
            String string = "";

            for (int j = 0; j < length; ++j) {
                char c = str.charAt(i);
                string = string + c;
                if (i == length - 1) {
                    tmp.addElement(string);
                    break;
                }

                if (c == token) {
                    tmp.addElement(string);
                    break;
                }

                ++i;
            }
        }

        String[] result = new String[tmp.size()];

        for (int i = 0; i < result.length; ++i) {
            result[i] = ((String) tmp.elementAt(i)).replace(token, ' ').trim();
        }

        return result;
    }

    public static byte[] rmsString4Byte(String strTemp) {
        byte[] arrayTemp = null;
        if (strTemp == null) {
            return null;
        } else {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);

            try {
                dos.writeUTF(strTemp);
                arrayTemp = baos.toByteArray();
                baos.close();
                dos.close();
            } catch (IOException var5) {
            }

            return arrayTemp;
        }
    }

    public static String rmsByte4String(byte[] arrayTemp) {
        String strTemp = null;
        if (arrayTemp == null) {
            return null;
        } else {
            try {
                ByteArrayInputStream bais = new ByteArrayInputStream(arrayTemp);
                DataInputStream dis = new DataInputStream(bais);
                strTemp = dis.readUTF();
                dis.close();
                bais.close();
            } catch (Exception var4) {
            }

            return strTemp;
        }
    }

    public static String replaceFirstStr(String srcStr, String regex, String replacement) {
        int index = srcStr.indexOf(regex);
        if (index != -1 && replacement != null) {
            int endIndex = srcStr.indexOf(regex) + regex.length();
            StringBuffer objSB = new StringBuffer();
            objSB.append(srcStr.substring(0, index)).append(replacement).append(srcStr.substring(endIndex, srcStr.length()));
            return objSB.toString();
        } else {
            return srcStr;
        }
    }
}
