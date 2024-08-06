import javax.microedition.lcdui.Graphics;

public class UIRim extends UIComponent {
  public static final byte RIM_SINGLE = 0;
  
  public static final byte RIM_DOUBLE = 1;
  
  public static final byte RIM_DRAGON = 2;
  
  public static final byte RIM_ESPECIAL = 3;
  
  public static final byte RIM_DOUBLE_VEINS = 4;
  
  public static final byte RIM_MULTI_VEINS = 5;
  
  public static final byte RIM_SINGEL_VEINS = 6;
  
  public static final byte RIM_DRAGON_1 = 7;
  
  public static final byte RIM_NULL = 8;
  
  private byte type = 0;
  
  private int color = 920326;
  
  private int count = 0;
  
  public UIRim(int x, int y, int w, int h, byte type) {
    super(x, y, w, h);
    this.type = type;
    this.canFocus = false;
  }
  
  public void draw(Graphics g) {
    drawRim(g, this.positionX, this.positionY, this.width, this.height, this.type, true);
    if (this.type == 1) {
      g.setColor(this.color);
      g.fillRect(this.positionX + 1, this.positionY + 1, this.width - 1, this.height - 1);
      if (this.focus) {
        if (++this.count >= 3)
          this.count = 0; 
        MainCanvas.mImgUI[29].draw(g, this.positionX - 2 + 2 * this.count, this.positionY - 2 + 2 * this.count, 0, false);
        MainCanvas.mImgUI[29].draw(g, this.positionX - 2 + 2 * this.count, this.positionY + this.height - 3 - 2 * this.count, 1, false);
        MainCanvas.mImgUI[29].draw(g, this.positionX + this.width - 3 - 2 * this.count, this.positionY - 2 + 2 * this.count, 0, true);
        MainCanvas.mImgUI[29].draw(g, this.positionX + this.width - 3 - 2 * this.count, this.positionY + this.height - 3 - 2 * this.count, 1, true);
      } 
    } 
  }
  
  public static void drawRim(Graphics g, int x, int y, int w, int h, byte type) {
    switch (type) {
      case 0:
        drawSingleRim(g, x, y, w, h);
        break;
      case 1:
        g.setColor(920326);
        g.fillRect(x - 2, y - 2, w + 4, h + 4);
        g.setColor(6311729);
        g.drawRect(x - 2, y - 2, w + 4, h + 4);
        g.drawRect(x, y, w, h);
        MainCanvas.mImgUI[2].draw(g, x - 2, y - 2, 0, false);
        MainCanvas.mImgUI[2].draw(g, x - 2, y + h - 7, 1, false);
        MainCanvas.mImgUI[2].draw(g, x + w - 7, y - 2, 2, false);
        MainCanvas.mImgUI[2].draw(g, x + w - 7, y + h - 7, 3, false);
        break;
      case 2:
        g.setColor(920069);
        g.fillRect(x - 1, y - 1, w + 3, h + 3);
        g.setColor(9466695);
        g.drawRect(x, y, w, h);
        MainCanvas.mImgUI[3].draw(g, x, y, 0, false);
        MainCanvas.mImgUI[3].draw(g, x + w - 11, y, 2, false);
        MainCanvas.mImgUI[4].draw(g, x, y + h - 17, 0, false);
        MainCanvas.mImgUI[4].draw(g, x + w - 37, y + h - 17, 0, true);
        break;
      case 7:
        g.setColor(920069);
        g.fillRect(x - 1, y - 1, w + 3, h + 3);
        MainCanvas.mImgUI[4].draw(g, x, y + h - 17, 0, false);
        MainCanvas.mImgUI[4].draw(g, x + w - 37, y + h - 17, 0, true);
        g.setColor(6377522);
        g.drawRect(x, y, w, h);
        MainCanvas.mImgUI[1].draw(g, x, y, 0, false);
        MainCanvas.mImgUI[1].draw(g, x, y + h - 6, 1, false);
        MainCanvas.mImgUI[1].draw(g, x + w - 6, y, 2, false);
        MainCanvas.mImgUI[1].draw(g, x + w - 6, y + h - 6, 3, false);
        break;
      case 3:
        drawSingleRim(g, x, y, w, h);
        drawSingleRim(g, x, y, w / 4, h / 4);
        drawSingleRim(g, x + w / 4 + 2, y, w - w / 4 - 2, h / 3 - 3);
        break;
      case 4:
        g.setColor(920326);
        g.fillRect(x, y, w, h);
        g.setColor(7494450);
        g.drawRect(x, y, w, h);
        g.setColor(6377524);
        g.drawRect(x + 2, y + 2, w - 4, h - 4);
        MainCanvas.mImgUI[15].draw(g, x, y, 0, false);
        MainCanvas.mImgUI[15].draw(g, x, y + h - 16, 1, false);
        MainCanvas.mImgUI[15].draw(g, x + w - 16, y, 2, false);
        MainCanvas.mImgUI[15].draw(g, x + w - 16, y + h - 16, 3, false);
        break;
      case 6:
        g.setColor(2037253);
        g.fillRect(x - 1, y - 1, w + 2, h + 2);
        g.setColor(8415039);
        g.drawRect(x, y, w - 1, h - 1);
        MainCanvas.mImgUI[3].draw(g, x, y, 0, false);
        MainCanvas.mImgUI[3].draw(g, x, y + h - 12, 1, false);
        MainCanvas.mImgUI[3].draw(g, x + w - 12, y, 2, false);
        MainCanvas.mImgUI[3].draw(g, x + w - 12, y + h - 12, 3, false);
        break;
    } 
    g.setClip(0, 0, MainCanvas.screenW, MainCanvas.screenH);
  }
  
  public void drawRim(Graphics g, int x, int y, int w, int h, byte type, boolean noStatic) {
    drawRim(g, x, y, w, h, type);
  }
  
  public void setColor(int c) {
    this.color = c;
  }
  
  public static void drawRim(Graphics g, int x, int y, int r, int c, int sw, int sh) {
    g.setColor(0);
    g.fillRect(x, y, c * sw, r * sh);
    drawRim(g, x, y, c * sw, r * sh, (byte)0);
    g.setColor(6377522);
    for (int i = 1; i < r; i++)
      g.drawLine(x, y + i * sh, x + c * sw, y + i * sh); 
    for (int j = 1; j < c; j++)
      g.drawLine(x + j * sw, y, x + j * sw, y + r * sh); 
  }
  
  public static void drawClarity(Graphics g, int x, int y, int w, int h, int color) {
    int[] xPoints = { x, x + w, x + w, x };
    int[] yPoints = { y, y, y + h, y + h };
    int xOffset = 0;
    int yOffset = 0;
    int nPoints = 4;
    int argbColor = color;
    GraphicsUtil.fillPolygon(g, xPoints, xOffset, yPoints, yOffset, nPoints, argbColor);
  }
  
  public static void drawSingleRim(Graphics g, int x, int y, int w, int h) {
    g.setColor(2037253);
    g.fillRect(x - 1, y - 1, w + 3, h + 3);
    g.setColor(8415039);
    g.drawRect(x, y, w, h);
    MainCanvas.mImgUI[1].draw(g, x, y, 0, false);
    MainCanvas.mImgUI[1].draw(g, x, y + h - 6, 1, false);
    MainCanvas.mImgUI[1].draw(g, x + w - 6, y, 2, false);
    MainCanvas.mImgUI[1].draw(g, x + w - 6, y + h - 6, 3, false);
  }
}
