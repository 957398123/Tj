
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * 图片管理类
 *
 * @author yihua
 */
public class MImage {

    public Image img;
    public short frame_w = 0;
    public short frame_h = 0;
    public byte cols = 0;
    public byte rows = 0;
    public byte frames = 0;

    /**
     * 从PKG资源包中加载图片
     * @param imagePath
     * @param pkgPath
     * @param f_w
     * @param f_h 
     */
    public MImage(String imagePath, String pkgPath, int f_w, int f_h) {
        if (f_w != 0 && f_h != 0) {
            frame_w = (short) f_w;
            frame_h = (short) f_h;
            if (!"".equals(imagePath)) {
                img = Util.loadImage(Util.readPKG(pkgPath, imagePath));
            }
            cols = (byte) (img.getWidth() / frame_w);
            rows = (byte) (img.getHeight() / frame_h);
            frames = (byte) (cols * rows);
        }
    }

    /**
     * 加载Image
     *
     * @param img
     */
    public MImage(Image img) {
        if (img != null) {
            this.img = img;
            this.frame_w = (short)img.getWidth();
            this.frame_h = (short)img.getHeight();
            this.cols = this.rows = this.frames = 1;
        }
    }

    /**
     * 从资源地址中加载图片
     *
     * @param s
     */
    public MImage(String s) {
        if (!"".equals(s)) {
            img = Util.loadImage(s);
            frame_w = (short) img.getWidth();
            frame_h = (short) img.getHeight();
            cols = rows = frames = 1;
        }
    }

    /**
     * 绘制图片
     *
     * @param g
     * @param x
     * @param y
     * @param frameIndex frame索引
     * @param ifMir 是否镜像
     */
    public void draw(Graphics g, int x, int y, int frameIndex, boolean ifMir) {
        if (img != null) {
            if (ifMir && cols != 1) {
                frameIndex = cols * rows - 1 - frameIndex;
            }
            int dx = frame_w * (frameIndex % cols);
            int dy = frame_h * (frameIndex / cols);
            g.setClip(x, y, frame_w, frame_h);
            if (!ifMir) {
                g.drawImage(img, x - dx, y - dy, 20);
            } else {
                draw(g, x - dx, y - dy, GraphicsUtil.TOP | GraphicsUtil.LEFT, GraphicsUtil.FLIP_HORIZONTAL);
            }
            g.setClip(0, 0, MainCanvas.screenW, MainCanvas.screenH);
        }
    }

    /**
     * 绘制图片
     *
     * @param g
     * @param x
     * @param y
     * @param anchor
     * @param transform
     */
    public void draw(Graphics g, int x, int y, int anchor, int transform) {
        GraphicsUtil.drawImage(g, img, x, y, anchor, transform);
    }
}
