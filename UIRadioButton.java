import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public class UIRadioButton extends UIComponent {
  private String name = null;
  
  Vector items = new Vector();
  
  public int baseNumber = 0;
  
  public int minNumber = 0;
  
  private byte pressCount = 10;
  
  private byte pressDirection = 0;
  
  public static final byte tToC = (byte)(10 * CURR_W / 176);
  
  public static final byte cToI = (byte)(5 * CURR_W / 176);
  
  public static final byte iToC = (byte)(7 * CURR_W / 176);
  
  private int chooseIndex = 0;
  
  private int interval = 0;
  
  private boolean isChange = false;
  
  private static int freePoint = 0;
  
  private int advanceColor = 15718814;
  
  public static final byte STYLE_NORMAL = 0;
  
  public static final byte STYLE_ACTION = 1;
  
  public static final byte STYLE_CHOOSE = 2;
  
  private boolean advanceStyle = false;
  
  private byte kind = 0;
  
  private static int defaultFreePoint = 0;
  
  private byte advanceCount = 0;
  
  private static byte DY = 2;
  
  public UIRadioButton(int x, int y, int w, int h, String title) {
    super(x, y, w, h);
    this.name = title;
    this.height = charH + 2;
  }
  
  public UIRadioButton(int x, int y, int w, int h, String title, byte style) {
    this(x, y, w, h, title);
    this.kind = style;
  }
  
  public void addItems(String label) {
    if (label != null)
      this.items.addElement(label); 
  }
  
  public void setNumber(int num) {
    this.baseNumber = num;
  }
  
  public void setMinNumber(int min) {
    this.minNumber = min;
    this.baseNumber = min;
  }
  
  public void setAdvanceStyle() {
    if (this.kind == 2)
      this.advanceStyle = true; 
  }
  
  public void setAdvanceColor(int color) {
    if (this.advanceStyle)
      this.advanceColor = color; 
  }
  
  public void draw(Graphics g) {
    int tempx = this.positionX;
    if (++this.interval > 5)
      this.interval = 0; 
    if (this.focus)
      if (!this.advanceStyle)
        MainCanvas.mImgUI[17].draw(g, tempx - 13, this.positionY + 1 + DY, this.interval / 3, false);  
    if (this.focus) {
      g.setColor(16375574);
    } else {
      g.setColor(10321225);
    } 
    if (this.advanceStyle)
      g.setColor(this.advanceColor); 
    g.drawString(this.name, tempx, this.positionY, 20);
    if (this.kind == 0) {
      tempx += font.stringWidth(this.name) + tToC;
      if (this.chooseIndex >= this.items.size())
        this.chooseIndex = this.items.size() - 1; 
      for (int i = 0; i < this.items.size(); i++) {
        String temps = (String)this.items.elementAt(i);
        MainCanvas.mImgUI[11].draw(g, tempx, this.positionY + 1 + DY, 0, false);
        if (i == this.chooseIndex) {
          g.fillRect(tempx + 2, this.positionY + 4 + DY, 5, 3);
          g.fillRect(tempx + 3, this.positionY + 3 + DY, 3, 5);
        } 
        tempx += cToI + 9;
        g.drawString(temps, tempx, this.positionY, 20);
        tempx += font.stringWidth(temps) + iToC;
      } 
    } else if (this.kind == 2) {
      tempx += font.stringWidth(this.name) + tToC + 5;
      if (this.advanceStyle)
        tempx -= tToC; 
      if (this.chooseIndex >= this.items.size())
        this.chooseIndex = this.items.size() - 1; 
      String temp = (String)this.items.elementAt(this.chooseIndex);
      if (this.pressCount < 2 && this.pressDirection == 3) {
        MainCanvas.mImgUI[22].draw(g, tempx - 2, this.positionY + 3 + DY, 0, false);
        this.pressCount = (byte)(this.pressCount + 1);
      } else {
        MainCanvas.mImgUI[22].draw(g, tempx, this.positionY + 3 + DY, 0, false);
      } 
      tempx += 10;
      if (this.advanceStyle && this.focus) {
        this.advanceCount = (byte)(this.advanceCount + 1);
        if (this.advanceCount > 4)
          this.advanceCount = 0; 
        if (this.advanceCount / 2 == 0) {
          g.setColor(this.advanceColor);
        } else {
          g.setColor(16770362);
        } 
      } 
      g.drawString(temp, tempx, this.positionY, 20);
      tempx += font.stringWidth(temp) + 5;
      if (this.pressCount < 2 && this.pressDirection == 4) {
        MainCanvas.mImgUI[22].draw(g, tempx + 2, this.positionY + 3 + DY, 0, true);
        this.pressCount = (byte)(this.pressCount + 1);
      } else {
        MainCanvas.mImgUI[22].draw(g, tempx, this.positionY + 3 + DY, 0, true);
      } 
      if (this.pressCount >= 2)
        this.pressCount = 10; 
    } else {
      tempx += font.stringWidth(this.name) + cToI - 2;
      MainCanvas.mImgUI[22].draw(g, tempx, this.positionY + 3 + DY, 0, false);
      tempx += 7;
      if (this.pressCount < 2 && this.pressDirection == 3) {
        MainCanvas.mImgUI[21].draw(g, tempx, this.positionY + 1 + DY, 2, false);
        this.pressCount = (byte)(this.pressCount + 1);
      } else {
        MainCanvas.mImgUI[21].draw(g, tempx, this.positionY + 1 + DY, 0, false);
      } 
      if (this.baseNumber < 10)
        tempx += font.charWidth('0'); 
      tempx += cToI + 10;
      g.setColor(16375574);
      g.drawString(this.baseNumber + "", tempx, this.positionY, 20);
      tempx += font.stringWidth(this.baseNumber + "") + cToI;
      if (this.pressCount < 2 && this.pressDirection == 4) {
        MainCanvas.mImgUI[21].draw(g, tempx, this.positionY + 1 + DY, 3, false);
        this.pressCount = (byte)(this.pressCount + 1);
      } else {
        MainCanvas.mImgUI[21].draw(g, tempx, this.positionY + 1 + DY, 1, false);
      } 
      tempx += 13;
      MainCanvas.mImgUI[22].draw(g, tempx, this.positionY + 3 + DY, 1, false);
      if (this.pressCount >= 2)
        this.pressCount = 10; 
    } 
  }
  
  public String getChooseItem() {
    if (this.items.size() == 0)
      return null; 
    return (String)this.items.elementAt(this.chooseIndex);
  }
  
  public void setFocus(boolean flag) {
    super.setFocus(flag);
    this.interval = 0;
  }
  
  public UIComponent getAroundComponent(byte direction) {
    switch (direction) {
      case 1:
        return this.upComponent;
      case 2:
        return this.downComponent;
      case 3:
        if (this.kind != 1) {
          if (--this.chooseIndex < 0) {
            this.chooseIndex = 0;
          } else {
            this.isChange = true;
            if (this.kind == 2)
              press(direction); 
          } 
        } else {
          if (this.baseNumber <= this.minNumber)
            return null; 
          this.baseNumber--;
          freePoint++;
          press(direction);
          this.isChange = true;
        } 
        return null;
      case 4:
        if (this.kind != 1) {
          if (++this.chooseIndex >= this.items.size()) {
            this.chooseIndex = this.items.size() - 1;
          } else {
            this.isChange = true;
            if (this.kind == 2)
              press(direction); 
          } 
        } else {
          if (this.baseNumber >= 999 || freePoint <= 0)
            return null; 
          this.baseNumber++;
          freePoint--;
          press(direction);
          this.isChange = true;
        } 
        return null;
    } 
    return null;
  }
  
  public void removeAllItems() {
    this.items.removeAllElements();
  }
  
  public boolean ifChange() {
    boolean temp = this.isChange;
    this.isChange = false;
    return temp;
  }
  
  public byte getPressDirection() {
    return this.pressDirection;
  }
  
  public void press(byte direction) {
    if (this.kind == 1 || this.kind == 2) {
      this.pressCount = 0;
      this.pressDirection = direction;
    } 
  }
  
  public void setFreePoint(int aFreePoint) {
    if (this.kind == 1) {
      freePoint = aFreePoint;
      defaultFreePoint = freePoint;
    } 
  }
  
  public static boolean isFreePointChange() {
    if (freePoint < defaultFreePoint)
      return true; 
    return false;
  }
  
  public int getFreePoint() {
    return freePoint;
  }
  
  public void setChooseItem(int index) {
    if (this.kind == 1)
      return; 
    if (this.items.size() > 0 && this.items.size() > index)
      this.chooseIndex = index; 
  }
  
  public byte getChooseItemIndex() {
    return (byte)this.chooseIndex;
  }
}
