import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public class UIComponent {
  byte[][] colorSigns;
  
  int width;
  
  int height;
  
  int positionX;
  
  int positionY;
  
  boolean focus = false;
  
  boolean canFocus = true;
  
  boolean isVisible = true;
  
  static Font font = null;
  
  static int charH = Cons.FONT_SIZE[MainCanvas.MOBILE_TELEPHONE_TYPE][0];
  
  static int charW = Cons.FONT_SIZE[MainCanvas.MOBILE_TELEPHONE_TYPE][1];
  
  UIComponent upComponent = null;
  
  UIComponent downComponent = null;
  
  UIComponent leftComponent = null;
  
  UIComponent rightComponent = null;
  
  public static final int BASE_W = 176;
  
  public static final int BASE_H = 208;
  
  public static final int CURR_W = Cons.PHONE_TYPE[MainCanvas.MOBILE_TELEPHONE_TYPE][0];
  
  public static final int CURR_H = Cons.PHONE_TYPE[MainCanvas.MOBILE_TELEPHONE_TYPE][1];
  
  boolean oldfocus;
  
  public UIComponent(int x, int y, int w, int h) {
    this.oldfocus = false;
    if (font == null)
      font = MainCanvas.font[1]; 
    this.positionX = x;
    this.positionY = y;
    this.width = w;
    this.height = h;
    if (CURR_W - this.width >= 2) {
      this.width *= CURR_W;
      this.width /= 176;
    } 
    if (CURR_H - this.height >= 2) {
      this.height *= CURR_H;
      this.height /= 208;
    } 
  }
  
  public void setOldFocus(boolean flag) {
    this.oldfocus = flag;
  }
  
  public void setCanFocus(boolean flag) {
    this.canFocus = flag;
  }
  
  public boolean canFocus() {
    return this.canFocus;
  }
  
  public void setFocus(boolean flag) {
    this.focus = flag;
  }
  
  public void setXY(int x, int y) {
    this.positionX = x;
    this.positionY = y;
  }
  
  public void setVisible(boolean isV) {
    this.isVisible = isV;
  }
  
  public boolean getVisible() {
    return this.isVisible;
  }
  
  public void draw(Graphics g) {}
  
  public UIComponent getComponent() {
    return this;
  }
  
  public void setAroundComponent(UIComponent uic, byte direction) {
    switch (direction) {
      case 1:
        this.upComponent = uic;
        if (uic != null && uic.downComponent != this)
          uic.downComponent = this; 
        break;
      case 2:
        this.downComponent = uic;
        if (uic != null && uic.upComponent != this)
          uic.upComponent = this; 
        break;
      case 3:
        this.leftComponent = uic;
        if (uic != null && uic.rightComponent != this)
          uic.rightComponent = this; 
        break;
      case 4:
        this.rightComponent = uic;
        if (uic != null && uic.leftComponent != this)
          uic.leftComponent = this; 
        break;
    } 
  }
  
  public UIComponent getAroundComponent(byte direction) {
    switch (direction) {
      case 1:
        return this.upComponent;
      case 2:
        return this.downComponent;
      case 3:
        return this.leftComponent;
      case 4:
        return this.rightComponent;
    } 
    return null;
  }
}
