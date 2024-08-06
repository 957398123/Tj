import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class KmkBM {
  private static KmkBM bm;
  
  private Image buf_img = null;
  
  private Map map;
  
  private Graphics bufImg_g;
  
  private int oldWinX;
  
  private int oldWinY;
  
  private short quartered_X = 0;
  
  private short quartered_Y = 0;
  
  private boolean isOneDraw = true;
  
  private short buf_W;
  
  private short buf_H;
  
  private byte station;
  
  private boolean isRepaint = false;
  
  static boolean isBufMap = false;
  
  public static void releaseInstance() {
    bm = null;
  }
  
  static KmkBM createBufMap(Map map, short w, short h) {
    if (bm == null) {
      bm = new KmkBM();
      bm.buf_img = Image.createImage(w, h);
      bm.map = map;
      bm.buf_W = w;
      bm.buf_H = h;
      bm.bufImg_g = bm.buf_img.getGraphics();
      bm.isOneDraw = true;
    } 
    return bm;
  }
  
  static KmkBM getInstance() {
    return bm;
  }
  
  public void draw(int posX, int posY) {
    if (this.isOneDraw) {
      repaint(posX, posY);
      this.isOneDraw = false;
    } else {
      if (posX == this.oldWinX && posY == this.oldWinY)
        return; 
      int distanceX = this.oldWinX - posX;
      int distanceY = this.oldWinY - posY;
      if (Abs(distanceX) > this.buf_W || Abs(distanceY) > this.buf_H) {
        repaint(posX, posY);
        return;
      } 
      this.quartered_X = updateQuartered(this.quartered_X, distanceX, this.buf_W);
      this.quartered_Y = updateQuartered(this.quartered_Y, distanceY, this.buf_H);
      if (this.isRepaint) {
        repaint(posX, posY);
        this.isRepaint = false;
        return;
      } 
      isBufMap = true;
      if (distanceX < 0) {
        this.bufImg_g.setClip(this.quartered_X + distanceX, 0, -distanceX, this.buf_H);
        this.bufImg_g.fillRect(this.quartered_X + distanceX, 0, -distanceX, this.buf_H);
        this.station = 11;
        this.map.drawArea(this.bufImg_g, posX + this.buf_W + distanceX, posY, -distanceX, this.buf_H - this.quartered_Y);
        this.station = 12;
        this.map.drawArea(this.bufImg_g, posX + this.buf_W + distanceX, posY + this.buf_H - this.quartered_Y, -distanceX, this.quartered_Y);
      } else if (distanceX > 0) {
        this.bufImg_g.setClip(this.quartered_X, 0, distanceX, this.buf_H);
        this.bufImg_g.fillRect(this.quartered_X, 0, distanceX, this.buf_H);
        this.station = 21;
        this.map.drawArea(this.bufImg_g, posX, posY, distanceX, this.buf_H - this.quartered_Y);
        this.station = 22;
        this.map.drawArea(this.bufImg_g, posX, posY + this.buf_H - this.quartered_Y, distanceX, this.quartered_Y);
      } 
      if (distanceY < 0) {
        this.bufImg_g.setClip(0, this.quartered_Y + distanceY, this.buf_W, -distanceY);
        this.bufImg_g.fillRect(0, this.quartered_Y + distanceY, this.buf_W, -distanceY);
        this.station = 31;
        this.map.drawArea(this.bufImg_g, posX, posY + this.buf_H + distanceY, this.buf_W - this.quartered_X, -distanceY);
        this.station = 32;
        this.map.drawArea(this.bufImg_g, posX + this.buf_W - this.quartered_X, posY + this.buf_H + distanceY, this.quartered_X, -distanceY);
      } else if (distanceY > 0) {
        this.bufImg_g.setClip(0, this.quartered_Y, this.buf_W, distanceY);
        this.bufImg_g.fillRect(0, this.quartered_Y, this.buf_W, distanceY);
        this.station = 41;
        this.map.drawArea(this.bufImg_g, posX, posY, this.buf_W - this.quartered_X, distanceY);
        this.station = 42;
        this.map.drawArea(this.bufImg_g, posX + this.buf_W - this.quartered_X, posY, this.quartered_X, distanceY);
      } 
      isBufMap = false;
      this.oldWinX = posX;
      this.oldWinY = posY;
    } 
  }
  
  private void repaint(int posX, int posY) {
    this.quartered_X = this.quartered_Y = 0;
    this.bufImg_g.setClip(0, 0, this.buf_W, this.buf_H);
    this.bufImg_g.fillRect(0, 0, this.buf_W, this.buf_H);
    this.map.drawArea(this.bufImg_g, posX, posY, this.buf_W, this.buf_H);
    this.oldWinX = posX;
    this.oldWinY = posY;
  }
  
  private short updateQuartered(short sum, int offset, short max) {
    sum = (short)(sum - offset);
    if (sum > max) {
      if (sum + offset == max) {
        sum = (short)(sum - max);
      } else {
        this.isRepaint = true;
      } 
    } else if (sum < 0) {
      if (sum + offset == 0) {
        sum = (short)(sum + max);
      } else {
        this.isRepaint = true;
      } 
    } 
    return sum;
  }
  
  public boolean isOne_draw() {
    return this.isOneDraw;
  }
  
  public static int Abs(int x) {
    return (x < 0) ? -x : x;
  }
  
  public void drawScreen(Graphics g) {
    int widthR = this.buf_W - this.quartered_X;
    int heightB = this.buf_H - this.quartered_Y;
    setClipImage(g, 0, 0, this.quartered_X, this.quartered_Y, widthR, heightB);
    setClipImage(g, this.quartered_X, 0, widthR, this.quartered_Y, 0, heightB);
    setClipImage(g, 0, this.quartered_Y, this.quartered_X, heightB, widthR, 0);
    setClipImage(g, this.quartered_X, this.quartered_Y, widthR, heightB, 0, 0);
  }
  
  public void setClipImage(Graphics g, int x0, int y0, int w, int h, int x1, int y1) {
    g.setClip(x1, y1, w, h);
    g.drawImage(this.buf_img, x1 - x0, y1 - y0, 20);
  }
  
  public void updatePosition(Graphics g, Image img, int x, int y, int translate, short frame_w, short frame_h) {
    int offsetX = this.buf_W - this.quartered_X;
    int offsetY = this.buf_H - this.quartered_Y;
    int tempCx = x, tempCy = y, tempCw = frame_w, tempCh = frame_h;
    switch (this.station) {
      case 11:
        tempCx = x -= offsetX;
        if (tempCx + frame_w > this.quartered_X)
          tempCw = this.quartered_X - tempCx; 
        tempCy = y += this.quartered_Y;
        if (tempCy < this.quartered_Y) {
          tempCy = this.quartered_Y;
          tempCh -= this.quartered_Y - tempCy;
        } 
        break;
      case 12:
        tempCx = x -= offsetX;
        if (tempCx + frame_w > this.quartered_X)
          tempCw = this.quartered_X - tempCx; 
        tempCy = y -= offsetY;
        if (tempCy + frame_h > this.quartered_Y)
          tempCh = this.quartered_Y - tempCy; 
        break;
      case 21:
        tempCx = x += this.quartered_X;
        if (tempCx < this.quartered_X) {
          tempCx = this.quartered_X;
          tempCw -= this.quartered_X - tempCx;
        } 
        tempCy = y += this.quartered_Y;
        if (tempCy < this.quartered_Y) {
          tempCy = this.quartered_Y;
          tempCh -= this.quartered_Y - tempCy;
        } 
        break;
      case 22:
        tempCx = x += this.quartered_X;
        if (tempCx < this.quartered_X) {
          tempCx = this.quartered_X;
          tempCw -= this.quartered_X - tempCx;
        } 
        tempCy = y -= offsetY;
        if (tempCy + frame_h > this.quartered_Y)
          tempCh = this.quartered_Y - tempCy; 
        break;
      case 31:
        tempCy = y -= offsetY;
        if (tempCy + frame_h > this.quartered_Y)
          tempCh = this.quartered_Y - tempCy; 
        tempCx = x += this.quartered_X;
        if (tempCx < this.quartered_X) {
          tempCx = this.quartered_X;
          tempCw -= this.quartered_X - tempCx;
        } 
        break;
      case 32:
        tempCy = y -= offsetY;
        if (tempCy + frame_h > this.quartered_Y)
          tempCh = this.quartered_Y - tempCy; 
        tempCx = x -= offsetX;
        if (tempCx + frame_w > this.quartered_X)
          tempCw = this.quartered_X - tempCx; 
        break;
      case 41:
        tempCy = y += this.quartered_Y;
        if (tempCy < this.quartered_Y) {
          tempCy = this.quartered_Y;
          tempCh -= this.quartered_Y - tempCy;
        } 
        tempCx = x += this.quartered_X;
        if (tempCx < this.quartered_X) {
          tempCx = this.quartered_X;
          tempCw -= this.quartered_X - tempCx;
        } 
        break;
      case 42:
        tempCy = y += this.quartered_Y;
        if (tempCy < this.quartered_Y) {
          tempCy = this.quartered_Y;
          tempCh -= this.quartered_Y - tempCy;
        } 
        tempCx = x -= offsetX;
        if (tempCx + frame_w > this.quartered_X)
          tempCw = this.quartered_X - tempCx; 
        break;
    } 
    g.setClip(tempCx, tempCy, tempCw, tempCh);
    if (translate == 0) {
      g.drawImage(img, x, y, 20);
    } else if (translate == 1) {
      GraphicsUtil.drawImage(g, img, x, y, GraphicsUtil.LEFT | GraphicsUtil.TOP, GraphicsUtil.FLIP_HORIZONTAL);
    } else if (translate == 2) {
      GraphicsUtil.drawImage(g, img, x, y, GraphicsUtil.LEFT | GraphicsUtil.TOP, GraphicsUtil.FLIP_VERTICAL);
    } else if (translate == 3) {
      GraphicsUtil.drawImage(g, img, x, y, GraphicsUtil.LEFT | GraphicsUtil.TOP, GraphicsUtil.ROTATE_180);
    } 
  }
}
