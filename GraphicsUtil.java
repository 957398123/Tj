
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * 自定义图形工具类
 *
 * @author yihua
 */
public class GraphicsUtil {

    /**
     * 使指定的图像区域原封不动地复制
     */
    public static final int TRANS_NONE = 0;
    /**
     * 使指定的图像区域围绕其垂直中心反射，然后顺时针旋转180度。
     */
    public static final int TRANS_MIRROR_ROT180 = 1;
    /**
     * 使指定的图像区域围绕其垂直中心反射。
     */
    public static final int TRANS_MIRROR = 2;
    /**
     * 使指定的图像区域顺时针旋转180度。
     */
    public static final int TRANS_ROT180 = 3;
    /**
     * 使指定的图像区域围绕其垂直中心反射，然后顺时针旋转270度。
     */
    public static final int TRANS_MIRROR_ROT270 = 4;
    /**
     * 使指定的图像区域顺时针旋转90度。
     */
    public static final int TRANS_ROT90 = 5;
    /**
     * 使指定的图像区域顺时针旋转270度。
     */
    public static final int TRANS_ROT270 = 6;
    /**
     * 使指定的图像区域围绕其垂直中心反射，然后顺时针旋转90度。
     */
    public static final int TRANS_MIRROR_ROT90 = 7;
    // 以下是锚点定义 
    /**
     * 用于将文本和图像的锚点定位在文本或图像的下方的常数。
     */
    public static final int BOTTOM = 32;
    /**
     * 用于将文本和图像水平居中于锚点周围的常数
     */
    public static final int HCENTER = 1;
    /**
     * 用于将文本和图像的锚点定位在文本或图像的左侧的常数。
     */
    public static final int LEFT = 4;
    /**
     * 用于将文本和图像的锚点定位在文本或图像的右侧的常数。
     */
    public static final int RIGHT = 8;
    /**
     * 用于将文本和图像的锚点定位在文本或图像上方的常数。
     */
    public static final int TOP = 16;
    /**
     * 用于将图像垂直居中于锚点周围的常数。
     */
    public static final int VCENTER = 2;
    // 以下是桥接NOKIA的实现
    /**
     * 水平图像翻转
     */
    public static final int FLIP_HORIZONTAL = 8192;
    /**
     * 垂直图像翻转
     */
    public static final int FLIP_VERTICAL = 16384;
    /**
     * 逆时针旋转图像180度
     */
    public static final int ROTATE_180 = 180;
    /**
     * 逆时针旋转图像270度
     */
    public static final int ROTATE_270 = 270;
    /**
     * 逆时针旋转图像90度
     */
    public static final int ROTATE_90 = 90;

    public static void drawImage(Graphics g, Image img, int x, int y, int anchor, int manipulation) {
        int transform;
        if (img == null) {
            throw new NullPointerException();
        }
        switch (manipulation) {
            case TRANS_NONE: {
                transform = TRANS_NONE;
                break;
            }
            case FLIP_HORIZONTAL: {
                transform = TRANS_MIRROR;
                break;
            }
            case FLIP_VERTICAL: {
                transform = TRANS_MIRROR_ROT180;
                break;
            }

            case ROTATE_90: {
                transform = TRANS_ROT270;
                break;
            }
            case ROTATE_180: {
                transform = TRANS_ROT180;
                break;
            }
            case ROTATE_270: {
                transform = TRANS_ROT90;
                break;
            }
            case FLIP_HORIZONTAL | ROTATE_90: {
                transform = TRANS_MIRROR_ROT270;
                break;
            }
            case FLIP_VERTICAL | ROTATE_90: {
                transform = TRANS_MIRROR_ROT90;
                break;
            }
            default: {
                transform = -1;
                break;
            }
        }
        if (anchor >= 64 || transform == -1) {
            throw new IllegalArgumentException();
        }
        int tx = g.getTranslateX();
        int ty = g.getTranslateY();
        int dx = x + tx;
        int dy = y + ty;
        int iw = img.getWidth();
        int ih = img.getHeight();
        g.drawRegion(img, 0, 0, iw, ih, transform, dx, dy, anchor);
    }

    /**
     * 填充封闭区域多边形
     *
     * @param g
     * @param xPoints x坐标组
     * @param xOffset 第一个x坐标偏移
     * @param yPoints y坐标组
     * @param yOffset 第一个x坐标偏移
     * @param nPoints 需要绘制的point数目
     * @param argbColor 填充颜色
     */
    public static void fillPolygon(Graphics g, int[] xPoints, int xOffset, int[] yPoints, int yOffset, int nPoints, int argbColor) {
        if (xPoints == null || yPoints == null) {
            throw new IllegalArgumentException("Invalid polygon data");
        } else if (nPoints < 1) {
            throw new IllegalArgumentException("Invalid polygon data");
        } else if (xPoints.length != yPoints.length || xPoints.length < nPoints || yPoints.length < nPoints) {
            throw new IllegalArgumentException("Invalid polygon data");
        } else {
            // 找到最小和最大的x和y值，以确定填充区域的边界
            int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
            int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
// 处理offset
            xPoints[0] = xPoints[0] + xOffset;
            yPoints[0] = yPoints[0] + yOffset;
            // 设置颜色
            g.setColor(argbColor);
            for (int i = 0; i < nPoints; i++) {
                if (xPoints[i] < minX) {
                    minX = xPoints[i];
                }
                if (xPoints[i] > maxX) {
                    maxX = xPoints[i];
                }
                if (yPoints[i] < minY) {
                    minY = yPoints[i];
                }
                if (yPoints[i] > maxY) {
                    maxY = yPoints[i];
                }
            }

            // 检查多边形的每条边，利用drawLine方法
            for (int i = 0; i < nPoints; i++) {
                int x1 = xPoints[i];
                int y1 = yPoints[i];
                int x2 = xPoints[(i + 1) % nPoints];
                int y2 = yPoints[(i + 1) % nPoints];
                // 绘制多边形的每条边
                g.drawLine(x1, y1, x2, y2);
            }
            // 通过扫描线算法填充多边形
            for (int y = minY; y <= maxY; y++) {
                for (int x = minX; x <= maxX; x++) {
                    if (contains(x, y, xPoints, yPoints, nPoints)) {
                        g.drawLine(x, y, x, y); // 在填充区域内绘制一条垂直线
                    }
                }
            }
        }
    }

    // 判断点(x, y)是否在多边形内部的方法
    private static boolean contains(int x, int y, int[] xPoints, int[] yPoints, int nPoints) {
        int crossings = 0;
        for (int i = 0; i < nPoints; i++) {
            int x1 = xPoints[i];
            int y1 = yPoints[i];
            int x2 = xPoints[(i + 1) % nPoints];
            int y2 = yPoints[(i + 1) % nPoints];
            // 检查线段和水平线y=y0的交点数
            if (((y1 <= y && y < y2) || (y2 <= y && y < y1))
                    && (x < (x2 - x1) * (y - y1) / (y2 - y1) + x1)) {
                crossings++;
            }
        }
        // 奇数个交叉表示点在多边形内
        return (crossings % 2 != 0);
    }
}
