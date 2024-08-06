import javax.microedition.lcdui.Graphics;

public class UIScroll extends UIComponent {
  public static final byte SCROLL_VERT = 0;
  
  public static final byte SCROLL_HORI = 1;
  
  public static final byte SCROLL_PROCESS = 2;
  
  private byte style;
  
  private int maxLength = 10;
  
  private int perLength = 5;
  
  private int scroll = 0;
  
  private int position = 0;
  
  private int positionOffset = 0;
  
  private boolean isShowNumber = false;
  
  private int processColor = 211066;
  
  private int charColor = 15653280;
  
  private StringBuffer chars = null;
  
  public UIScroll(int x, int y, int w, int h, byte kind, boolean showNumber) {
    super(x, y, w, h);
    if (showNumber) {
      this.positionY += 3;
      this.height -= 6;
    } 
    this.style = kind;
    if (this.style == 0) {
      this.width--;
    } else {
      this.height--;
    } 
    if (this.style == 2)
      this.chars = new StringBuffer(); 
    this.canFocus = false;
    this.isShowNumber = showNumber;
  }
  
  public void setProcessColor(int color) {
    if (this.style == 2)
      this.processColor = color; 
  }
  
  public void draw(Graphics g) {
    int length;
    g.setColor(8349245);
    g.drawRect(this.positionX, this.positionY, this.width, this.height);
    if (this.isShowNumber) {
      Util.drawNumberInCircle(g, this.positionX - 3, this.positionY - 8, 3);
      Util.drawNumberInCircle(g, this.positionX - 3, this.positionY + this.height, 6);
    } 
    g.setColor(11701844);
    switch (this.style) {
      case 0:
        g.fillRect(this.positionX + 1, this.positionY + 1 + this.positionOffset, this.width - 1, this.scroll - 1);
        break;
      case 1:
        g.fillRect(this.positionX + 1 + this.positionOffset, this.positionY + 1, this.scroll - 1, this.height - 1);
        break;
      case 2:
        g.setColor(this.processColor);
        if (this.positionOffset != 0)
          g.fillRect(this.positionX + 2, this.positionY + 2, this.positionOffset, this.height - 3); 
        g.setColor(this.charColor);
        length = font.stringWidth(this.chars.toString());
        g.drawString(this.chars.toString(), this.positionX + (this.width - length >> 1), this.positionY - 1, 20);
        break;
    } 
  }
  
  public void setScrollPosition(int p) {
    if (p < 0) {
      p = 0;
    } else if (p > this.maxLength) {
      p = this.maxLength;
    } 
    this.position = p;
    updatePosition();
  }
  
  public void setBar(int m, int p) {
    this.maxLength = m;
    this.perLength = p;
    if (this.style == 0) {
      this.scroll = this.height * this.perLength / this.maxLength;
    } else {
      this.scroll = this.width * this.perLength / this.maxLength;
    } 
    updatePosition();
  }
  
  private void updatePosition() {
    switch (this.style) {
      case 0:
        this.positionOffset = this.position * (this.height - this.scroll) / (this.maxLength - this.perLength);
        break;
      case 1:
        this.positionOffset = this.position * (this.width - this.scroll) / (this.maxLength - this.perLength);
        break;
      case 2:
        this.positionOffset = this.position * (this.width - 3) / this.maxLength;
        this.chars.delete(0, this.chars.length());
        this.chars.append(this.position + "/" + this.maxLength);
        break;
    } 
  }
  
  public void setXY(int x, int y) {
    super.setXY(x, y);
    if (this.isShowNumber)
      this.positionY += 3; 
  }
}
