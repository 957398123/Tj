import javax.microedition.lcdui.Graphics;

public class UITextArea extends UIComponent {
  private UIScroll scroll = null;
  
  private String str = null;
  
  private short pointer = 0;
  
  private short fullRows = 0;
  
  private short canShowRows = 0;
  
  private String[] textStr = null;
  
  private String[] textStr_1 = null;
  
  private boolean isGreen = true;
  
  private int color = 8349245;
  
  private boolean noRim = false;
  
  public void setShowRim(boolean isShow) {
    this.noRim = !isShow;
  }
  
  public boolean getCanShowAll() {
    if (this.fullRows > this.canShowRows)
      return false; 
    return true;
  }
  
  public UITextArea(int x, int y, int w, int h, String str) {
    super(x, y, w, h);
    setString(str);
  }
  
  public void setString(String _str) {
    this.str = null;
    this.str = _str;
    if (this.str == null)
      this.str = " "; 
    this.textStr = null;
    this.textStr_1 = Util.wrapText(this.str, this.width - 6, font);
    this.textStr = Util.colorChangeLine(this, this.str, this.width - 6, font);
    this.scroll = null;
    if (this.isGreen) {
      this.fullRows = (short)this.textStr.length;
      this.canShowRows = (short)((this.height - 4) / (charH + 1));
    } else {
      this.fullRows = (short)this.textStr_1.length;
      this.canShowRows = (short)((this.height - 4) / (charH + 1));
    } 
    if (this.canShowRows < this.fullRows) {
      this.scroll = new UIScroll(this.positionX + this.width - 4, this.positionY, 4, this.height * 208 / CURR_H, (byte)0, false);
      this.scroll.setBar(this.fullRows, this.canShowRows);
    } 
  }
  
  public void setXY(int x, int y) {
    this.positionX = x;
    this.positionY = y;
    if (this.scroll != null)
      this.scroll.setXY(this.positionX + this.width - 4, this.positionY); 
  }
  
  public void draw(Graphics g) {
    int length;
    if (!this.focus) {
      g.setColor(16773120);
    } else {
      g.setColor(16777215);
    } 
    if (!this.noRim)
      UIRim.drawRim(g, this.positionX, this.positionY, this.width, this.height, (byte)0); 
    if (!this.isGreen) {
      length = this.textStr_1.length;
    } else {
      length = this.textStr.length;
    } 
    if (length > this.canShowRows)
      length = this.pointer + this.canShowRows; 
    if (!this.isGreen) {
      for (int i = this.pointer, j = 0; i < length; i++, j++)
        g.drawString(this.textStr_1[i], this.positionX + 4, this.positionY + 4 + charH * j, 20); 
    } else {
      for (int i = this.pointer, j = 0; i < length; i++, j++) {
        int offSet = 0;
        char[] tmpChars = this.textStr[i].toCharArray();
        for (int m = 0; m < tmpChars.length; m++) {
          if (this.colorSigns[i][m] == 0) {
            g.setColor(this.color);
          } else {
            g.setColor(65280);
          } 
          g.drawChar(tmpChars[m], this.positionX + offSet + 4, this.positionY + 4 + j * (charH + 1), 20);
          offSet += font.charWidth(tmpChars[m]);
        } 
      } 
    } 
    if (this.scroll != null)
      this.scroll.draw(g); 
  }
  
  public UIComponent getAroundComponent(byte direction) {
    switch (direction) {
      case 1:
        up();
        return null;
      case 2:
        down();
        return null;
    } 
    return null;
  }
  
  public void up() {
    if (this.pointer > 0)
      this.pointer = (short)(this.pointer - 1); 
    if (this.scroll != null)
      this.scroll.setScrollPosition(this.pointer); 
  }
  
  public void down() {
    if (this.pointer + this.canShowRows < this.fullRows)
      this.pointer = (short)(this.pointer + 1); 
    if (this.scroll != null)
      this.scroll.setScrollPosition(this.pointer); 
  }
  
  public void setColor(int c) {
    this.color = c;
  }
  
  public void setHeight(int h) {
    this.height = h;
  }
  
  public void setChangeColor(boolean change) {
    this.isGreen = change;
  }
  
  public boolean isEnd() {
    if (this.pointer + this.canShowRows >= this.fullRows)
      return true; 
    return false;
  }
  
  public boolean isTop() {
    if (this.pointer == 0)
      return true; 
    return false;
  }
  
  public short getPointer() {
    return this.pointer;
  }
  
  public void setPointer(short pointer) {
    this.pointer = pointer;
  }
  
  public String getStr() {
    return this.str;
  }
}
