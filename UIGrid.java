import javax.microedition.lcdui.Graphics;

public class UIGrid extends UIComponent {
  public static final byte OTHER = 6;
  
  public static final byte WEAPEN = 1;
  
  public static final byte CANUSE = 2;
  
  public static final byte PETFOOD = 3;
  
  public static final byte DIAMOND = 4;
  
  public static final byte MATERIAL = 5;
  
  public static final byte NULL = 0;
  
  public static final byte GRID_ZERO = 0;
  
  public static final byte GRID_ONE = 1;
  
  public byte GRID_WIDTH = 18;
  
  public byte GRID_HEIGHT = 18;
  
  private short pointer = 0;
  
  private short gridPointer = 0;
  
  private UIScroll scroll = null;
  
  private UIMenu subMenu = null;
  
  private boolean[] isHaveThings = null;
  
  private boolean startCounter = false;
  
  private byte rows = 0;
  
  private byte cols = 0;
  
  private byte canShowRows = 0;
  
  private MImage mimg = null;
  
  private byte type = 0;
  
  private int count = 0;
  
  public boolean[] isLmtstoren = null;
  
  public short[] stuffId = null;
  
  public byte[] stuffImageId = null;
  
  public byte[] stuffNumber = null;
  
  public String[] stuffName = null;
  
  public byte[] stuffLevel = null;
  
  public short[] stuffType = null;
  
  public boolean[] isCanUse = null;
  
  public byte[] isCanTrade = null;
  
  public byte[] bindType = null;
  
  public byte weaponPlace = 0;
  
  public byte stuffPlace = -1;
  
  public boolean isLock = false;
  
  public boolean isLock1 = false;
  
  public boolean isLock2 = false;
  
  public boolean isLock3 = false;
  
  public boolean isLock4 = false;
  
  public UIGrid(int x, int y, byte rows, byte cols, byte canShowrows, boolean[] isHaveThings, MImage mimg) {
    super(x + 2, y + 2, 10, 10);
    this.type = 1;
    this.GRID_WIDTH = (byte)(40 * CURR_W / 176);
    this.GRID_HEIGHT = (byte)(40 * CURR_H / 208);
    if (this.GRID_WIDTH > 40)
      this.GRID_WIDTH = (byte)(this.GRID_WIDTH + 1); 
    if (this.GRID_HEIGHT > 40)
      this.GRID_HEIGHT = (byte)(this.GRID_HEIGHT + 1); 
    this.rows = rows;
    this.cols = cols;
    this.isHaveThings = isHaveThings;
    this.canShowRows = canShowrows;
    this.width = cols * this.GRID_WIDTH;
    this.height = canShowrows * this.GRID_HEIGHT;
    if (this.canShowRows < rows) {
      this.scroll = new UIScroll(x + this.width + 3 + 3, y, 4, this.height + 4, (byte)0, false);
      this.scroll.setBar(rows, canShowrows);
    } 
    this.mimg = mimg;
  }
  
  public UIGrid(int x, int y, byte rows, byte cols, byte canShowrows, MImage mimg) {
    super(x + 2, y + 2, 10, 10);
    this.type = 0;
    this.rows = rows;
    this.cols = cols;
    this.canShowRows = canShowrows;
    this.width = cols * this.GRID_WIDTH;
    this.height = canShowrows * this.GRID_HEIGHT;
    if (this.canShowRows < rows) {
      this.scroll = new UIScroll(x + this.width - 4, y, 4, this.height + 4, (byte)0, false);
      this.scroll.setBar(rows, canShowrows);
    } 
    this.mimg = mimg;
    int aa = rows * cols;
    this.stuffId = new short[aa];
    this.stuffImageId = new byte[aa];
    this.stuffNumber = new byte[aa];
    this.stuffName = new String[aa];
    this.isCanUse = new boolean[aa];
    this.stuffLevel = new byte[aa];
    this.stuffType = new short[aa];
    this.isCanTrade = new byte[aa];
    this.bindType = new byte[aa];
    this.isLmtstoren = new boolean[aa];
    int i;
    for (i = aa - 1; i >= 0; i--)
      this.isCanUse[i] = !this.isCanUse[i]; 
    for (i = aa - 1; i >= 0; i--)
      this.stuffName[i] = ""; 
    this.GRID_WIDTH = (byte)(18 * CURR_W / 176);
    this.GRID_HEIGHT = (byte)(18 * CURR_H / 208);
    if (this.GRID_WIDTH > 18)
      this.GRID_WIDTH = (byte)(this.GRID_WIDTH + 1); 
    if (this.GRID_HEIGHT > 18)
      this.GRID_HEIGHT = (byte)(this.GRID_HEIGHT + 1); 
    this.width = cols * this.GRID_WIDTH;
    this.height = canShowrows * this.GRID_HEIGHT;
  }
  
  public void setXY(int x, int y) {
    this.positionX = x;
    this.positionY = y;
    if (this.scroll != null)
      this.scroll.setXY(this.positionX + this.width, this.positionY - 2); 
  }
  
  public void setHaveThings(boolean[] ht) {
    this.isHaveThings = ht;
  }
  
  public void setGridDetail(byte index, short sd, byte sid, byte sr, String sn, byte level, short st, byte ic, byte _bindType) {
    try {
      this.stuffId[index] = 0;
      this.stuffImageId[index] = 0;
      this.stuffNumber[index] = 0;
      this.stuffName[index] = null;
      this.stuffLevel[index] = 0;
      this.stuffType[index] = 0;
      this.isCanTrade[index] = 0;
      this.bindType[index] = 0;
      this.stuffId[index] = sd;
      this.stuffImageId[index] = sid;
      this.stuffNumber[index] = sr;
      this.stuffName[index] = sn;
      this.stuffLevel[index] = level;
      this.stuffType[index] = st;
      this.isCanTrade[index] = ic;
      this.bindType[index] = _bindType;
      this.isCanUse[index] = true;
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public void draw(Graphics g) {
    if (++this.count >= 10000)
      this.count = 0; 
    switch (this.type) {
      case 0:
        UIRim.drawRim(g, this.positionX, this.positionY, this.width, this.height, (byte)1);
        break;
      case 1:
        UIRim.drawRim(g, this.positionX, this.positionY, this.width, this.height, (byte)1);
        break;
    } 
    int n = 0;
    for (n = 1; n < this.canShowRows; n++)
      g.drawLine(this.positionX + 1, this.positionY + n * this.GRID_HEIGHT, this.positionX - 1 + this.width, this.positionY + n * this.GRID_HEIGHT); 
    for (n = 1; n < this.cols; n++)
      g.drawLine(this.positionX + n * this.GRID_WIDTH, this.positionY + 1, this.positionX + n * this.GRID_WIDTH, this.positionY - 1 + this.height); 
    switch (this.type) {
      case 0:
        if (this.mimg != null && this.stuffId != null && this.stuffNumber != null) {
          int w = this.pointer * this.cols;
          for (; w < (this.pointer + this.canShowRows) * this.cols && w < this.stuffId.length; w++) {
            if (this.stuffId[w] > 0 && this.stuffImageId[w] > 0) {
              int space = (this.GRID_WIDTH - this.mimg.frame_w >> 1) + 1;
              int tmX = this.positionX + space + w % this.cols * this.GRID_WIDTH;
              int tmY = this.positionY + space + w / this.cols * this.GRID_HEIGHT - this.pointer * this.GRID_HEIGHT;
              this.mimg.draw(g, tmX, tmY, this.stuffImageId[w] - 1, false);
              if (this.isLmtstoren[w] || (isCurStuffCanRepeat(w) && this.stuffNumber[w] > 0))
                Util.drawNumber(g, this.stuffNumber[w], tmX - space + this.GRID_WIDTH - 9, tmY - space + this.GRID_HEIGHT - 7); 
              if (!this.isCanUse[w]) {
                if (UIGameRun.imgSkillCannotUse == null)
                  UIGameRun.imgSkillCannotUse = Util.loadImage(Util.readPKG("/uiuse.pkg", "skillpanle.png")); 
                g.drawImage(UIGameRun.imgSkillCannotUse, tmX + 1, tmY + 1, 20);
              } 
            } 
          } 
        } 
        break;
      case 1:
        if (this.mimg != null)
          for (int m = 0; m < this.isHaveThings.length; m++) {
            if (this.isHaveThings[m])
              this.mimg.draw(g, this.positionX + 2 + m % this.cols * this.GRID_WIDTH, this.positionY + 2 + m / this.cols * this.GRID_HEIGHT - this.pointer * this.GRID_HEIGHT, 0, false); 
          }  
        break;
    } 
    g.setClip(0, 0, MainCanvas.screenW, MainCanvas.screenH);
    if (((this.stuffPlace != -1 && this.isLock) || this.isLock2 || this.isLock3 || this.isLock4) && 
      this.stuffPlace / this.cols >= this.pointer) {
      g.setColor(15396515);
      g.drawRect(this.positionX + this.stuffPlace % this.cols * this.GRID_WIDTH + 1, this.positionY + (this.stuffPlace / this.cols - this.pointer) * this.GRID_HEIGHT + 1, this.GRID_WIDTH - 2, this.GRID_HEIGHT - 2);
    } 
    if (this.focus) {
      g.setColor(16770362);
      if (this.count % 2 == 0) {
        g.drawRect(this.positionX + this.gridPointer % this.cols * this.GRID_WIDTH, this.positionY + this.gridPointer / this.cols * this.GRID_HEIGHT, this.GRID_WIDTH, this.GRID_HEIGHT);
      } else {
        g.drawRect(this.positionX + this.gridPointer % this.cols * this.GRID_WIDTH + 1, this.positionY + this.gridPointer / this.cols * this.GRID_HEIGHT + 1, this.GRID_WIDTH - 2, this.GRID_HEIGHT - 2);
      } 
    } 
    if (this.scroll != null)
      this.scroll.draw(g); 
    if (this.subMenu != null)
      this.subMenu.draw(g); 
  }
  
  public UIComponent getAroundComponent(byte direction) {
    if (this.isLock1)
      return null; 
    switch (direction) {
      case 1:
        if (this.subMenu != null) {
          this.subMenu.up();
        } else {
          if (this.gridPointer / this.cols > 0) {
            this.gridPointer = (short)(this.gridPointer - this.cols);
          } else if (this.pointer > 0) {
            this.pointer = (short)(this.pointer - 1);
          } else if (!this.isLock) {
            return this.upComponent;
          } 
          if (this.scroll != null)
            this.scroll.setScrollPosition(this.pointer); 
        } 
        return null;
      case 2:
        if (this.subMenu != null) {
          this.subMenu.down();
        } else {
          if (this.gridPointer / this.cols < this.canShowRows - 1) {
            if (this.gridPointer / this.cols < this.rows - 1)
              this.gridPointer = (short)(this.gridPointer + this.cols); 
          } else if (this.pointer + this.canShowRows < this.rows) {
            this.pointer = (short)(this.pointer + 1);
          } else if (!this.isLock) {
            return this.downComponent;
          } 
          if (this.scroll != null)
            this.scroll.setScrollPosition(this.pointer); 
        } 
        return null;
      case 3:
        if (this.subMenu != null)
          return null; 
        if (this.gridPointer > 0) {
          this.gridPointer = (short)(this.gridPointer - 1);
        } else if (this.pointer > 0) {
          this.pointer = (short)(this.pointer - 1);
          this.gridPointer = (short)(this.gridPointer + this.cols - 1);
        } else {
          return this.leftComponent;
        } 
        if (this.scroll != null)
          this.scroll.setScrollPosition(this.pointer); 
        return null;
      case 4:
        if (this.subMenu != null)
          return null; 
        if (this.gridPointer < this.cols * this.canShowRows - 1) {
          this.gridPointer = (short)(this.gridPointer + 1);
        } else if (this.pointer + this.canShowRows < this.rows) {
          this.pointer = (short)(this.pointer + 1);
          this.gridPointer = (short)(this.gridPointer - this.cols - 1);
        } else {
          return this.rightComponent;
        } 
        if (this.scroll != null)
          this.scroll.setScrollPosition(this.pointer); 
        return null;
    } 
    return null;
  }
  
  public byte getCurrentPointer() {
    return (byte)(this.pointer * this.cols + this.gridPointer);
  }
  
  public void setCurrentPointer(byte index) {
    this.pointer = (short)(byte)(index / this.cols);
    this.gridPointer = (short)(byte)(index % this.cols);
  }
  
  public short[] getStuffId() {
    return this.stuffId;
  }
  
  public byte[] getStuffNumber() {
    return this.stuffNumber;
  }
  
  public String[] getStuffName() {
    return this.stuffName;
  }
  
  public byte[] getStuffImageId() {
    return this.stuffImageId;
  }
  
  public short[] getStuffType() {
    return this.stuffType;
  }
  
  public String getCurrentName() {
    try {
      if (this.stuffName == null)
        return "  "; 
      if (this.stuffName[getCurrentPointer()] != null) {
        if (this.stuffName[getCurrentPointer()].length() == 0)
          this.stuffName[getCurrentPointer()] = "   "; 
        return this.stuffName[getCurrentPointer()];
      } 
      return "  ";
    } catch (ArrayIndexOutOfBoundsException e) {
      e.printStackTrace();
      return "";
    } 
  }
  
  public byte getCurrentNumber() {
    if (this.stuffNumber == null)
      return 0; 
    return this.stuffNumber[getCurrentPointer()];
  }
  
  public short getCurrentId() {
    if (this.stuffId == null)
      return 0; 
    return this.stuffId[getCurrentPointer()];
  }
  
  public byte getCurrentImageId() {
    if (this.stuffImageId == null)
      return 0; 
    return this.stuffImageId[getCurrentPointer()];
  }
  
  public byte getCurrentbindType() {
    if (this.stuffType == null)
      return 0; 
    if ((this.bindType[getCurrentPointer()] & 0xF) == 1)
      return 1; 
    return (byte)((this.bindType[getCurrentPointer()] & 0xF0) >> 4);
  }
  
  public void setCurrentBindType(byte bt) {
    if (this.stuffType == null)
      return; 
    this.bindType[getCurrentPointer()] = bt;
  }
  
  public boolean getCurrentUseState() {
    return this.isCanUse[getCurrentPointer()];
  }
  
  public void changeStuff(short id1, short id2) {
    short sId = 0;
    byte imageId = 0;
    String sName = null;
    byte sNumber = 0;
    byte levelColor = 0;
    short sType = 0;
    byte sIc = 0;
    byte _bindType = 0;
    sId = this.stuffId[id1];
    imageId = this.stuffImageId[id1];
    sName = this.stuffName[id1];
    sNumber = this.stuffNumber[id1];
    levelColor = this.stuffLevel[id1];
    sType = this.stuffType[id1];
    sIc = this.isCanTrade[id1];
    _bindType = this.bindType[id1];
    this.stuffId[id1] = this.stuffId[id2];
    this.stuffImageId[id1] = this.stuffImageId[id2];
    this.stuffName[id1] = this.stuffName[id2];
    this.stuffNumber[id1] = this.stuffNumber[id2];
    this.stuffLevel[id1] = this.stuffLevel[id2];
    this.stuffType[id1] = this.stuffType[id2];
    this.isCanTrade[id1] = this.isCanTrade[id2];
    this.bindType[id1] = this.bindType[id2];
    this.stuffId[id2] = sId;
    this.stuffImageId[id2] = imageId;
    this.stuffName[id2] = sName;
    this.stuffNumber[id2] = sNumber;
    this.stuffLevel[id2] = levelColor;
    this.stuffType[id2] = sType;
    this.isCanTrade[id2] = sIc;
    this.bindType[id2] = _bindType;
  }
  
  public void splitStuff(short id1, short id2, byte sp1, byte sp2) {
    this.stuffId[id2] = this.stuffId[id1];
    this.stuffImageId[id2] = this.stuffImageId[id1];
    this.stuffName[id2] = this.stuffName[id1];
    this.stuffNumber[id2] = sp2;
    this.stuffLevel[id2] = this.stuffLevel[id1];
    this.stuffType[id2] = this.stuffType[id1];
    this.isCanTrade[id2] = this.isCanTrade[id1];
    this.bindType[id2] = this.bindType[id1];
    if (sp1 == 0) {
      setGridNull(id1);
    } else {
      this.stuffNumber[id1] = sp1;
    } 
  }
  
  public void setSubMenu(UIMenu sonMenu) {
    this.subMenu = sonMenu;
    if (this.subMenu == null)
      return; 
    int x1 = this.positionX + 2 + (getCurrentPointer() % this.cols + 1) * this.GRID_WIDTH;
    int x2 = x1 - 2 - this.GRID_WIDTH - this.subMenu.width;
    int y1 = this.positionY + 2 + getCurrentPointer() / this.cols * this.GRID_HEIGHT - this.pointer * this.GRID_HEIGHT;
    int y2 = MainCanvas.screenH - charH - 7 - this.subMenu.height;
    this.subMenu.setXY((x1 + this.subMenu.width > MainCanvas.screenW) ? x2 : x1, (y1 < y2) ? y1 : y2);
    this.subMenu.setFocus(true);
  }
  
  public UIMenu getSubMenu() {
    return this.subMenu;
  }
  
  public byte getCurrentNullPlace() {
    for (byte i = 0; i < this.stuffId.length; i = (byte)(i + 1)) {
      if (this.stuffId[i] == 0)
        return i; 
    } 
    return -1;
  }
  
  public byte getSomeoneNumber(byte index) {
    if (index < this.rows * this.cols)
      return this.stuffNumber[index]; 
    return 0;
  }
  
  public void setSomeoneNumber(byte index, byte number) {
    if (index < this.rows * this.cols)
      this.stuffNumber[index] = number; 
  }
  
  public void setCanUse(byte index, boolean isCU) {
    this.isCanUse[index] = isCU;
  }
  
  public void setStartCounter(boolean state) {
    this.startCounter = true;
  }
  
  public boolean getUseState() {
    return this.startCounter;
  }
  
  public byte getCurrentNameLevel() {
    if (this.stuffLevel != null) {
      byte tmp = getCurrentPointer();
      if (this.stuffLevel[tmp] <= 4 && this.stuffLevel[tmp] >= 0)
        return this.stuffLevel[tmp]; 
      return 0;
    } 
    return 0;
  }
  
  public short getCurrentLittleType() {
    if (this.stuffType == null)
      return 0; 
    return this.stuffType[getCurrentPointer()];
  }
  
  public byte getCurrentTrade() {
    return this.isCanTrade[getCurrentPointer()];
  }
  
  public byte getNameLevel(byte index) {
    return this.stuffLevel[index];
  }
  
  public boolean getCurrentCanTrade() {
    return (this.isCanTrade[getCurrentPointer()] == 1);
  }
  
  public boolean isCanToOther() {
    if (getCurrentbindType() == 1)
      return false; 
    return true;
  }
  
  public void setCurrentNull() {
    setGridNull(getCurrentPointer());
  }
  
  public void setGridNull(int index) {
    this.stuffId[index] = 0;
    this.stuffNumber[index] = 0;
    this.stuffName[index] = null;
    this.stuffLevel[index] = 0;
    this.isCanUse[index] = true;
    this.stuffType[index] = 0;
    this.isCanUse[index] = true;
    this.isCanTrade[index] = 0;
    this.bindType[index] = 0;
  }
  
  public byte getCurrentBigType() {
    return (byte)getStuffType(this.stuffType[getCurrentPointer()]);
  }
  
  public static short getStuffType(short _stuffType) {
    if ((_stuffType >= 101 && _stuffType <= 122) || (_stuffType >= 311 && _stuffType <= 321) || _stuffType == 368 || _stuffType == 369)
      return 1; 
    if (_stuffType == 201 || _stuffType == 308)
      return 3; 
    if ((_stuffType >= 221 && _stuffType <= 232) || _stuffType == 301 || _stuffType == 302 || _stuffType == 304 || _stuffType == 307 || _stuffType == 310 || _stuffType == 324 || _stuffType == 303 || _stuffType == 325 || _stuffType == 326 || (_stuffType >= 329 && _stuffType <= 335) || (_stuffType >= 338 && _stuffType <= 348) || _stuffType == 353 || _stuffType == 354 || _stuffType == 355 || _stuffType == 350 || _stuffType == 351 || _stuffType == 357 || _stuffType == 365 || _stuffType == 364 || _stuffType == 367)
      return 2; 
    if (_stuffType == 211 || _stuffType == 323)
      return 4; 
    if (_stuffType == 0)
      return 0; 
    return 5;
  }
  
  public boolean isCurStuffCanRepeat() {
    return isCurStuffCanRepeat(getCurrentPointer());
  }
  
  public boolean isCurStuffCanRepeat(int index) {
    if ((this.stuffType[index] >= 301 && this.stuffType[index] <= 303) || (this.stuffType[index] >= 305 && this.stuffType[index] <= 307) || this.stuffType[index] == 322 || this.stuffType[index] == 324 || this.stuffType[index] == 325 || this.stuffType[index] == 327 || this.stuffType[index] == 328 || (this.stuffType[index] >= 331 && this.stuffType[index] <= 335) || (this.stuffType[index] >= 338 && this.stuffType[index] <= 340) || (this.stuffType[index] >= 342 && this.stuffType[index] <= 348) || 
      
      getStuffType(this.stuffType[index]) == 1 || this.stuffType[index] == 350 || this.stuffType[index] == 351 || this.stuffType[index] == 353 || this.stuffType[index] == 354 || this.stuffType[index] == 355 || this.stuffType[index] == 238 || this.stuffType[index] == 239 || this.stuffType[index] == 357 || this.stuffType[index] == 365 || this.stuffType[index] == 363 || this.stuffType[index] == 367 || this.stuffType[index] == 368 || this.stuffType[index] == 369)
      return false; 
    return true;
  }
}
