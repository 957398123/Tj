import javax.microedition.lcdui.Graphics;

public class UIButton extends UIComponent {
  public String label = null;
  
  private int interval = 0;
  
  private int changeInterval = 2;
  
  public static final byte BUTTON_NOBOUND = 0;
  
  public static final byte BUTTON_BOUND = 1;
  
  private byte style = 1;
  
  public UIButton(int x, int y, int w, int h, String text, byte style) {
    super(x, y, w, h);
    this.style = style;
    this.label = text;
    this.width = font.stringWidth(text) + 4;
    this.height = charH + 2;
    this.interval = 0;
  }
  
  public void draw(Graphics g) {
    if (this.focus || this.style != 0) {
      if (this.changeInterval < 2) {
        g.setColor(15653280);
      } else {
        g.setColor(0);
      } 
      g.fillRect(this.positionX, this.positionY, this.width + 1, this.height + 1);
    } 
    g.setColor(15653280);
    if (this.style == 1) {
      if (this.focus) {
        if (++this.interval > 3)
          this.interval = 0; 
        if (this.interval < 2 && this.changeInterval >= 2) {
          g.drawRect(this.positionX + 1, this.positionY + 1, this.width, this.height);
        } else {
          g.drawRect(this.positionX, this.positionY, this.width + 2, this.height + 2);
        } 
      } else {
        g.drawRect(this.positionX + 1, this.positionY + 1, this.width, this.height);
      } 
    } else if (this.focus) {
      g.setColor(16573497);
      g.drawRect(this.positionX + 1, this.positionY + 2, this.width, this.height);
    } 
    if (this.changeInterval < 2) {
      g.setColor(8487297);
    } else {
      g.setColor(15653280);
    } 
    g.drawString(this.label, this.positionX + 4, this.positionY + 4, 20);
  }
  
  public void setFocus(boolean flag) {
    super.setFocus(flag);
    if (flag)
      this.interval = 0; 
  }
  
  public void press() {
    this.changeInterval = 0;
  }
  
  public void setStyle(byte style) {
    this.style = style;
  }
}
