import javax.microedition.lcdui.Graphics;

public class UIMenu extends UIComponent {
  public static final byte FLUSH_MIDDLE = 0;
  
  public static final byte FLUSH_LEFT = 1;
  
  private byte flushType = 0;
  
  private byte rimStyle = 6;
  
  private UIMenu superMenu = null;
  
  private UIMenu subMenu = null;
  
  private byte pointer = 0;
  
  private String str = null;
  
  public String[] strs = null;
  
  private int[] itemId = null;
  
  private byte[] taskState = null;
  
  private int count = 0;
  
  boolean[] isCanUse = null;
  
  boolean isUp = false;
  
  public static final byte TASK_ID_OFFSET = 120;
  
  public UIMenu(int x, int y, int w, int h, String str, String[] strs) {
    super(x, y, w, h);
    this.str = str;
    if (strs != null) {
      setMenuItems(strs);
      int temp = this.strs.length * (charH + 4) + 10;
      if (temp > this.height)
        this.height = temp; 
    } 
  }
  
  public void setTitle(String s) {
    this.str = s;
  }
  
  public void setMenuItems(String[] strItems) {
    if (strItems != null) {
      int lg = strItems.length;
      this.strs = null;
      this.strs = new String[lg];
      this.isCanUse = new boolean[lg];
      for (int i = 0; i < lg; i++) {
        this.isCanUse[i] = true;
        this.strs[i] = strItems[i];
      } 
      int length = 0;
      for (int j = 0; j < lg; j++) {
        int slg = font.stringWidth(this.strs[j]);
        length = (slg > length) ? slg : length;
      } 
      this.width = (this.width > length + 26) ? this.width : (length + 26);
    } 
  }
  
  public void checkMenu() {
    int lg = this.isCanUse.length;
    int i = 0;
    for (; i < lg && 
      !this.isCanUse[i]; i++);
    if (i == lg)
      this.strs = null; 
  }
  
  public void setMenuItems(int[] id, String[] strItems) {
    this.itemId = null;
    this.strs = null;
    this.itemId = id;
    this.strs = strItems;
  }
  
  public void setMenuItems(int[] id, String[] strItems, byte[] ts) {
    this.itemId = null;
    this.strs = null;
    this.taskState = null;
    this.itemId = id;
    this.strs = strItems;
    this.taskState = ts;
  }
  
  public void setSuperMenu(UIMenu fatherMenu) {
    this.superMenu = fatherMenu;
    if (fatherMenu.getSubMenu() == null || !fatherMenu.getSubMenu().equals(this))
      fatherMenu.setSubMenu(this); 
  }
  
  public void setSubMenu(UIMenu sonMenu) {
    this.subMenu = sonMenu;
    if (this.subMenu == null)
      return; 
    int x1 = 0;
    int x2 = MainCanvas.screenW - this.subMenu.width - 5;
    int y1 = this.positionY + 6 + charH + (charH + 4) * this.pointer;
    int y2 = MainCanvas.screenH - charH - 7 - this.subMenu.height;
    switch (this.flushType) {
      case 0:
        x1 = this.positionX + this.width - 20;
        break;
      case 1:
        x1 = this.positionX + 30;
        break;
    } 
    int tempy = (y1 < y2) ? y1 : y2;
    tempy = (tempy < 0) ? 4 : tempy;
    this.subMenu.setXY((x1 > x2) ? x2 : x1, tempy);
    this.subMenu.setFocus(true);
    if (sonMenu.getSuperMenu() == null || !sonMenu.getSuperMenu().equals(this))
      sonMenu.setSuperMenu(this); 
  }
  
  public UIMenu getSuperMenu() {
    return this.superMenu;
  }
  
  public UIMenu getSubMenu() {
    return this.subMenu;
  }
  
  public byte getCurrentPointer() {
    return this.pointer;
  }
  
  public void setCurrentpointer(byte pointer) {
    this.pointer = pointer;
  }
  
  public void draw(Graphics g) {
    if (++this.count >= 10000)
      this.count = 0; 
    UIRim.drawRim(g, this.positionX, this.positionY, this.width, this.height, this.rimStyle);
    int ch = 0;
    if (this.str != null) {
      g.setColor(10981737);
      g.drawString(this.str, this.positionX + (this.width >> 1), this.positionY + 4, 17);
      ch = charH + 10;
    } 
    if (this.strs == null)
      return; 
    byte leg = (byte)this.strs.length;
    int dx = 0;
    byte anchor = 0;
    switch (this.flushType) {
      case 0:
        dx = this.positionX + (this.width >> 1);
        anchor = 17;
        break;
      case 1:
        dx = this.positionX + 17;
        anchor = 20;
        break;
    } 
    byte w;
    for (w = 0; w < leg; w = (byte)(w + 1)) {
      if (!this.isCanUse[this.pointer])
        if (this.isUp) {
          this.pointer = (byte)(this.pointer - 1);
          if (this.pointer < 0)
            this.pointer = (byte)(this.strs.length - 1); 
        } else {
          this.pointer = (byte)(this.pointer + 1);
          if (this.pointer > this.strs.length - 1)
            this.pointer = 0; 
        }  
    } 
    byte i;
    for (i = 0; i < leg; i = (byte)(i + 1)) {
      if (this.focus) {
        if (i == this.pointer) {
          if (MainCanvas.mImgUI[27] != null)
            MainCanvas.mImgUI[27].draw(g, this.positionX + 4, this.positionY + 9 + ch + (charH + 4) * i, this.count / 3 % 2, false); 
          if (this.strs[this.pointer] != null) {
            g.setColor(16316576);
            g.drawString(this.strs[this.pointer], dx, this.positionY + 6 + ch + (charH + 4) * i, anchor);
          } 
        } else if (this.strs[i] != null) {
          if (this.isCanUse[i]) {
            g.setColor(10981737);
          } else {
            g.setColor(7112335);
          } 
          g.drawString(this.strs[i], dx, this.positionY + 6 + ch + (charH + 4) * i, anchor);
        } 
      } else if (this.strs[i] != null) {
        if (this.isCanUse[i]) {
          g.setColor(10981737);
        } else {
          g.setColor(7112335);
        } 
        g.drawString(this.strs[i], dx, this.positionY + 6 + ch + (charH + 4) * i, anchor);
      } 
    } 
    if (this.subMenu != null)
      this.subMenu.draw(g); 
  }
  
  public UIComponent getAroundComponent(byte direction) {
    switch (direction) {
      case 1:
        if (this.subMenu == null) {
          up();
        } else {
          this.subMenu.getAroundComponent(direction);
        } 
        return null;
      case 2:
        if (this.subMenu == null) {
          down();
        } else {
          this.subMenu.getAroundComponent(direction);
        } 
        return null;
      case 3:
        if (this.subMenu != null) {
          this.subMenu = null;
          return null;
        } 
        return this.leftComponent;
      case 4:
        return this.rightComponent;
    } 
    return null;
  }
  
  public void up() {
    if (this.strs == null)
      return; 
    if (this.pointer > 0) {
      this.pointer = (byte)(this.pointer - 1);
    } else {
      this.pointer = (byte)(this.strs.length - 1);
    } 
    while (!this.isCanUse[this.pointer]) {
      this.pointer = (byte)(this.pointer - 1);
      if (this.pointer < 0)
        this.pointer = (byte)(this.strs.length - 1); 
    } 
    this.isUp = true;
  }
  
  public void down() {
    if (this.strs == null)
      return; 
    if (this.pointer < this.strs.length - 1) {
      this.pointer = (byte)(this.pointer + 1);
    } else {
      this.pointer = 0;
    } 
    while (!this.isCanUse[this.pointer]) {
      this.pointer = (byte)(this.pointer + 1);
      if (this.pointer > this.strs.length - 1)
        this.pointer = 0; 
    } 
    this.isUp = false;
  }
  
  public String getCurrentItem() {
    return this.strs[getCurrentPointer()];
  }
  
  public void setFlushType(byte ft) {
    this.flushType = ft;
  }
  
  public void setRimStyle(byte rs) {
    this.rimStyle = rs;
  }
  
  public int getMappingPointer() {
    int temp = itemId[getCurrentPointer()];
    int mappingState = 0;
    if (temp > 0 && temp < 120) {
      mappingState = temp;
    } else if (temp >= 120) {
      mappingState = 120;
    } 
    return mappingState;
  }
  
  public void setNoUse(byte index) {
    if (index >= this.isCanUse.length)
      return; 
    this.isCanUse[index] = false;
    for (byte i = 0; i < this.isCanUse.length; i = (byte)(i + 1)) {
      if (this.isCanUse[i]) {
        this.pointer = i;
        break;
      } 
    } 
  }
  
  public void setCanUse(int index) {
    this.isCanUse[index] = true;
  }
  
  public int getCurrentRealId() {
    return this.itemId[getCurrentPointer()];
  }
  
  public int getCurrentRealTaskId() {
    return this.itemId[getCurrentPointer()] - 120;
  }
  
  public byte getCurrentRealState() {
    return (byte)(this.taskState[getCurrentPointer()] & 0xF);
  }
  
  public boolean isCurrentCanResponse() {
    return (this.taskState[getCurrentPointer()] >>> 4 == 1);
  }
  
  public void setCurrnetRealState(byte state) {
    this.taskState[getCurrentPointer()] = state;
  }
  
  public void setItem(byte index, String sr) {
    this.strs[index] = sr;
  }
  
  public boolean isMenuNull() {
    if (this.strs == null || this.strs[0] == null || this.strs[0].length() == 0)
      return true; 
    return false;
  }
}
