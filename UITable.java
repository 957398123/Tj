import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public class UITable extends UIComponent {
  public static final byte SPACE = (byte)(5 * CURR_W / 176);
  
  public static final byte TABLE_TYPE_0 = 0;
  
  public static final byte TABLE_TYPE_1 = 1;
  
  public static final byte TABLE_TYPE_2 = 2;
  
  public static final byte TABLE_TYPE_3 = 3;
  
  public static final byte TABLE_TYPE_4 = 4;
  
  public static final byte TABLE_TYPE_5 = 5;
  
  public static final byte TABLE_TYPE_6 = 6;
  
  public byte singleHeight = 22;
  
  public short singleWidth = 35;
  
  private String[] strs = null;
  
  private int rows = 0;
  
  private int cols = 0;
  
  private byte type = 0;
  
  private byte typeRim = 0;
  
  private int[] color = null;
  
  private int canShowRows = 0;
  
  private UIScroll scroll = null;
  
  private int pointer = 0;
  
  private int tablePointer = 0;
  
  private String strTitle = null;
  
  private MImage[] mimg = null;
  
  private byte interspace = 5;
  
  private byte[] imageFrame = null;
  
  private boolean isHaveRim = true;
  
  private int count = 0;
  
  private boolean isAutoHeight = true;
  
  private boolean isMiddle = false;
  
  private boolean[] isReaded;
  
  private boolean[] isHaveAccessory;
  
  public static final byte TABLE_TYPE_7 = 7;
  
  private boolean[] isSelect = null;
  
  public boolean isDown = false;
  
  int[] deFocus;
  
  private int[] moveX;
  
  public UITable(int x, int y, int w, int h, int rows, int cols, int canShowRows, byte typeRim, byte type) {
    super(x, y, w, h);
    this.deFocus = null;
    if (rows != 0 && cols != 0) {
      this.moveX = null;
      this.moveX = new int[rows * cols];
    } 
    this.rows = rows;
    this.cols = cols;
    this.canShowRows = canShowRows;
    if (rows != 0) {
      this.strs = new String[rows * cols];
    } else {
      this.strs = null;
    } 
    this.color = new int[rows * cols];
    this.mimg = new MImage[rows];
    this.imageFrame = new byte[rows];
    this.type = type;
    this.typeRim = typeRim;
    if (canShowRows < rows) {
      int h2 = this.height;
      h2 *= 208;
      h2 /= UIComponent.CURR_H;
      this.scroll = new UIScroll(x + this.width - 4, y, 4, h2, (byte)0, false);
      this.scroll.setBar(rows, canShowRows);
    } 
    this.isReaded = new boolean[rows];
    this.isHaveAccessory = new boolean[rows];
    this.isDown = false;
    if (type == 7)
      this.isSelect = new boolean[rows * cols]; 
  }
  
  public void setXY(int x, int y) {
    this.positionX = x;
    this.positionY = y;
    if (this.scroll != null)
      this.scroll.setXY(this.positionX + this.width - 4, this.positionY); 
  }
  
  public void setTitle(String string) {
    this.strTitle = string;
  }
  
  public void setSingleWH(short sw, byte sh, boolean haveNumber) {
    this.singleWidth = (short)(sw * CURR_W / 176);
    this.singleHeight = (byte)(sh * CURR_H / 208);
    switch (this.type) {
      case 0:
        this.width = (this.cols * this.singleWidth > this.width) ? (this.cols * this.singleWidth) : this.width;
        if (this.isAutoHeight)
          this.height = (this.canShowRows * this.singleHeight > this.height) ? (this.canShowRows * this.singleHeight) : this.height; 
        break;
      case 2:
      case 3:
      case 7:
        this.width = (this.cols * this.singleWidth > this.width) ? (this.cols * this.singleWidth) : this.width;
        if (this.isAutoHeight)
          this.height = (this.canShowRows * this.singleHeight > this.height) ? (this.canShowRows * this.singleHeight) : this.height; 
        break;
    } 
    int w = this.width * 176 / CURR_W;
    int h = this.height * 208 / CURR_H;
    if (this.canShowRows < this.rows) {
      this.scroll = new UIScroll(this.positionX + w - 8, this.positionY, 4, h, (byte)0, haveNumber);
      this.scroll.setBar(this.rows, this.canShowRows);
    } 
  }
  
  public void setItem(String str, int index, int color) {
    if (index < this.strs.length && index >= 0) {
      this.strs[index] = str;
      this.color[index] = color;
    } 
  }
  
  public void setItemColor(int index, int color) {
    if (index < this.strs.length && index >= 0)
      this.color[index] = color; 
  }
  
  public void setItem(MImage mimg, byte imageFrame, String str, int index, int color) {
    this.mimg[index] = mimg;
    this.color[index] = color;
    this.strs[index] = str;
    this.imageFrame[index] = imageFrame;
  }
  
  public void addItem(String str, int color) {
    for (int i = 0; i < this.strs.length; i++) {
      if (this.strs[i] == null) {
        this.strs[i] = str;
        this.color[i] = color;
        break;
      } 
    } 
  }
  
  public void addItems(String[] ss, int[] cr) {
    if (ss == null || cr == null || ss.length != cr.length || ss[0] == null)
      return; 
    Vector v = new Vector();
    int lg = ss.length;
    String[][] tempStr = new String[ss.length][];
    Vector crs = new Vector();
    for (int i = 0; i < lg; i++) {
      tempStr[i] = Util.wrapText(ss[i], this.width - charW, font);
      for (int k = 0; k < (tempStr[i]).length; k++) {
        crs.addElement(new Integer(cr[i]));
        v.addElement(tempStr[i][k]);
      } 
    } 
    int[] colors = new int[v.size()];
    for (int j = 0; j < crs.size(); j++)
      colors[j] = ((Integer)crs.elementAt(j)).intValue(); 
    this.strs = null;
    this.strs = new String[v.size()];
    this.color = null;
    this.color = colors;
    v.copyInto((Object[])this.strs);
    this.rows = this.strs.length;
    if (this.canShowRows < this.rows) {
      this.scroll = new UIScroll(this.positionX + this.width + 3, this.positionY, 4, this.height, (byte)0, false);
      this.scroll.setBar(this.rows, this.canShowRows);
    } else {
      this.scroll = null;
    } 
  }
  
  public void draw(Graphics g) {
    if (this.canShowRows <= 0 && (this.type == 3 || this.type == 5 || this.type == 7))
      return; 
    if (++this.count > 10000)
      this.count = 0; 
    if (this.isHaveRim)
      UIRim.drawRim(g, this.positionX, this.positionY, this.width, this.height, this.typeRim); 
    if (this.strs != null) {
      int length, i, max, j, k, h, n, m, wid, i2, i1;
      g.setColor(15718815);
      int clipX = g.getClipX();
      int clipY = g.getClipY();
      int clipW = g.getClipWidth();
      int clipH = g.getClipHeight();
      switch (this.type) {
        case 0:
          length = this.canShowRows * this.cols;
          i = this.pointer;
          j = 0;
          for (; i < length + this.pointer && this.pointer + j < this.strs.length; i++, j++) {
            if (this.strs[i] != null) {
              g.setColor(this.color[i]);
              if (!this.isMiddle) {
                g.drawString(this.strs[i], this.positionX + 5 + j % this.cols * this.singleWidth, this.positionY + 2 + j / this.cols * this.singleHeight, 20);
              } else {
                g.drawString(this.strs[i], this.positionX + j % this.cols * this.singleWidth + (this.singleWidth >> 1), this.positionY + 2 + j / this.cols * this.singleHeight, 17);
              } 
            } 
          } 
          break;
        case 6:
          max = (this.canShowRows <= this.rows - this.pointer) ? (this.canShowRows + this.pointer) : this.rows;
          for (k = this.pointer, n = 0; k < max; k++, n++) {
            if (this.strs[k] != null) {
              g.setColor(this.color[k]);
              if (!this.isMiddle) {
                g.drawString(this.strs[k], this.positionX + 5 + n % this.cols * this.singleWidth, this.positionY + 5 + n / this.cols * this.singleHeight, 20);
              } else {
                g.drawString(this.strs[k], this.positionX + n % this.cols * this.singleWidth + (this.singleWidth >> 1), this.positionY + 3 + n / this.cols * this.singleHeight, 17);
              } 
            } 
          } 
          break;
        case 1:
          h = 0;
          if (this.strTitle != null) {
            g.setColor(15653280);
            g.drawString(this.strTitle, this.positionX + (this.width >> 1), this.positionY + 4, 17);
            h = 20 * CURR_H / 208;
          } 
          for (m = this.pointer, i2 = 0; m < this.canShowRows + this.pointer; m++, i2++) {
            if (this.mimg[m] != null)
              this.mimg[m].draw(g, this.positionX + this.interspace, this.positionY + 10 + i2 * this.singleHeight + h, this.imageFrame[m], false); 
            g.setColor(this.color[m]);
            if (this.strs[m] != null)
              g.drawString(this.strs[m], this.positionX + this.interspace + 16 + this.interspace, this.positionY + 4 + i2 * this.singleHeight + h, 20); 
          } 
          break;
        case 2:
          if (this.rows <= this.canShowRows) {
            for (int i3 = 0; i3 < this.strs.length; i3++) {
              if (this.strs[i3] != null) {
                g.setColor(this.color[i3]);
                g.drawString(this.strs[i3], this.positionX + 10, this.positionY + this.singleHeight - UIComponent.charH + i3 * this.singleHeight, 20);
              } 
            } 
          } else {
            for (int i3 = this.pointer, p = 0; i3 < this.pointer + this.canShowRows; i3++, p++) {
              if (this.strs[i3] != null) {
                g.setColor(this.color[i3]);
                g.drawString(this.strs[i3], this.positionX + 10, this.positionY + this.singleHeight - UIComponent.charH + p * this.singleHeight, 20);
              } 
            } 
          } 
          if (this.focus) {
            g.setColor(16770362);
            g.drawRect(this.positionX + 8, this.positionY + this.singleHeight - UIComponent.charH - 1 + this.tablePointer * this.singleHeight, font.stringWidth(this.strs[getCurrentPointer()]) + 3, UIComponent.charH + 3);
          } 
          break;
        case 3:
          g.setColor(6377522);
          for (m = 0; m < this.canShowRows; m++)
            g.drawLine(this.positionX, this.positionY + this.singleHeight * (m + 1), this.positionX + this.width, this.positionY + this.singleHeight * (m + 1)); 
          if (this.focus || this.oldfocus) {
            if (MainCanvas.mImgUI[27] != null)
              MainCanvas.mImgUI[27].draw(g, this.positionX + 4, this.positionY + 2 + (this.singleHeight - (MainCanvas.mImgUI[27]).frame_h >> 1) + this.singleHeight * this.tablePointer, this.count / 3 % 2, false); 
            if (this.strs[this.pointer + this.tablePointer] != null) {
              int[] cor = new int[3];
              int c = this.color[this.pointer + this.tablePointer];
              for (int i3 = 0; i3 < cor.length; i3++) {
                int t = i3 << 3;
                cor[i3] = c & 255 << t;
                cor[i3] = cor[i3] + (80 << t);
                cor[i3] = (cor[i3] > 255 << t) ? (255 << t) : cor[i3];
              } 
              g.setColor(cor[0] | cor[1] | cor[2]);
              move(g, this.pointer + this.tablePointer, this.positionX + 14, this.positionY + this.singleHeight - UIComponent.charH + this.tablePointer * this.singleHeight, this.width - 16, this.singleHeight, 20);
            } 
          } 
          g.setClip(clipX, clipY, clipW, clipH);
          if (this.rows <= this.canShowRows) {
            for (int i3 = 0; i3 < this.strs.length; i3++) {
              if (i3 != this.pointer + this.tablePointer || (!this.focus && !this.oldfocus))
                if (this.strs[i3] != null) {
                  g.setColor(this.color[i3]);
                  g.setClip(this.positionX + 14, this.positionY + this.singleHeight - UIComponent.charH + i3 * this.singleHeight, this.width - 16, this.singleHeight);
                  g.drawString(this.strs[i3], this.positionX + 14, this.positionY + this.singleHeight - UIComponent.charH + i3 * this.singleHeight, 20);
                }  
            } 
          } else {
            for (int i3 = this.pointer, p = 0; i3 < this.pointer + this.canShowRows; i3++, p++) {
              if (i3 != this.pointer + this.tablePointer || !this.focus)
                if (this.strs[i3] != null) {
                  if (this.tablePointer == p) {
                    g.setColor(16316576);
                  } else {
                    g.setColor(this.color[i3]);
                  } 
                  g.setClip(this.positionX + 14, this.positionY + this.singleHeight - UIComponent.charH + p * this.singleHeight, this.width - 16, this.singleHeight);
                  g.drawString(this.strs[i3], this.positionX + 14, this.positionY + this.singleHeight - UIComponent.charH + p * this.singleHeight, 20);
                }  
            } 
          } 
          g.setClip(clipX, clipY, clipW, clipH);
          break;
        case 5:
          g.setColor(6377522);
          wid = this.width;
          if (this.scroll != null)
            wid -= 4; 
          for (i1 = 0; i1 < this.canShowRows; i1++)
            g.drawLine(this.positionX, this.positionY + this.singleHeight * (i1 + 1), this.positionX + wid, this.positionY + this.singleHeight * (i1 + 1)); 
          if (this.rows <= this.canShowRows) {
            for (int i3 = 0; i3 < this.strs.length; i3++) {
              if (this.strs[i3] != null) {
                int yy = this.positionY + this.singleHeight - UIComponent.charH + i3 * this.singleHeight;
                byte sign = 0;
                if (this.isReaded[i3])
                  sign = 1; 
                this.mimg[0].draw(g, this.positionX + 10 + SPACE, yy, sign, false);
                if (this.isHaveAccessory[i3])
                  this.mimg[0].draw(g, this.positionX + 10 + SPACE + (this.mimg[0]).frame_w + SPACE, yy, 2, false); 
                g.setColor(this.color[i3]);
                g.drawString(this.strs[i3], this.positionX + 10 + SPACE + (this.mimg[0]).frame_w + SPACE + (this.mimg[0]).frame_w + SPACE, yy, 20);
              } 
            } 
          } else {
            for (int i3 = this.pointer, p = 0; i3 < this.pointer + this.canShowRows; i3++, p++) {
              if (this.strs[i3] != null) {
                if (this.tablePointer == p) {
                  g.setColor(16316576);
                } else {
                  g.setColor(this.color[i3]);
                } 
                int yy = this.positionY + this.singleHeight - UIComponent.charH + p * this.singleHeight;
                byte sign = 0;
                if (this.isReaded[i3])
                  sign = 1; 
                this.mimg[0].draw(g, this.positionX + 10 + SPACE, yy, sign, false);
                if (this.isHaveAccessory[i3])
                  this.mimg[0].draw(g, this.positionX + 10 + SPACE + (this.mimg[0]).frame_w + SPACE, yy, 2, false); 
                g.drawString(this.strs[i3], this.positionX + 10 + SPACE + (this.mimg[0]).frame_w + SPACE + (this.mimg[0]).frame_w + SPACE, yy, 20);
              } 
            } 
          } 
          if (this.focus) {
            if (MainCanvas.mImgUI[27] != null)
              MainCanvas.mImgUI[27].draw(g, this.positionX + SPACE, this.positionY + 6 + this.singleHeight * this.tablePointer, this.count / 3 % 2, false); 
            if (this.strs[this.pointer + this.tablePointer] != null) {
              g.setColor(16316576);
              g.drawString(this.strs[this.pointer + this.tablePointer], this.positionX + 10 + SPACE + (this.mimg[0]).frame_w + SPACE + (this.mimg[0]).frame_w + SPACE, this.positionY + this.singleHeight - UIComponent.charH + this.tablePointer * this.singleHeight, 20);
            } 
          } 
          break;
        case 7:
          g.setColor(6377522);
          for (i1 = 0; i1 < this.canShowRows; i1++)
            g.drawLine(this.positionX, this.positionY + this.singleHeight * (i1 + 1), this.positionX + this.width, this.positionY + this.singleHeight * (i1 + 1)); 
          if (this.rows <= this.canShowRows) {
            for (int i3 = 0; i3 < this.strs.length; i3++) {
              if (this.strs[i3] != null) {
                g.setColor(this.color[i3]);
                g.drawString(this.strs[i3], this.positionX + 14, this.positionY + this.singleHeight - UIComponent.charH + i3 * this.singleHeight, 20);
                if (this.isSelect[i3]) {
                  g.drawRect(this.positionX + 2, this.positionY + this.singleHeight - UIComponent.charH + i3 * this.singleHeight + 6, 8, 8);
                  g.fillRect(this.positionX + 4, this.positionY + this.singleHeight - UIComponent.charH + i3 * this.singleHeight + 8, 5, 5);
                } else {
                  g.drawRect(this.positionX + 2, this.positionY + this.singleHeight - UIComponent.charH + i3 * this.singleHeight + 6, 8, 8);
                } 
              } 
            } 
          } else {
            for (int i3 = this.pointer, p = 0; i3 < this.pointer + this.canShowRows; i3++, p++) {
              if (this.strs[i3] != null) {
                g.setColor(this.color[i3]);
                g.drawString(this.strs[i3], this.positionX + 14, this.positionY + this.singleHeight - UIComponent.charH + p * this.singleHeight, 20);
                if (this.isSelect[i3]) {
                  g.drawRect(this.positionX + 2, this.positionY + this.singleHeight - UIComponent.charH + p * this.singleHeight + 6, 8, 8);
                  g.fillRect(this.positionX + 4, this.positionY + this.singleHeight - UIComponent.charH + p * this.singleHeight + 8, 5, 5);
                } else {
                  g.drawRect(this.positionX + 2, this.positionY + this.singleHeight - UIComponent.charH + p * this.singleHeight + 6, 8, 8);
                } 
              } 
            } 
          } 
          if (this.focus && this.strs[this.pointer + this.tablePointer] != null) {
            int[] cor = new int[3];
            int c = this.color[this.pointer + this.tablePointer];
            for (int i3 = 0; i3 < cor.length; i3++) {
              int t = i3 << 3;
              cor[i3] = c & 255 << t;
              cor[i3] = cor[i3] + (80 << t);
              cor[i3] = (cor[i3] > 255 << t) ? (255 << t) : cor[i3];
            } 
            g.setColor(cor[0] | cor[1] | cor[2]);
            if (this.isSelect[this.pointer + this.tablePointer]) {
              g.drawRect(this.positionX + 2, this.positionY + this.singleHeight - UIComponent.charH + this.tablePointer * this.singleHeight + 6, 8, 8);
              g.fillRect(this.positionX + 4, this.positionY + this.singleHeight - UIComponent.charH + this.tablePointer * this.singleHeight + 8, 5, 5);
            } else {
              g.drawRect(this.positionX + 2, this.positionY + this.singleHeight - UIComponent.charH + this.tablePointer * this.singleHeight + 6, 8, 8);
            } 
            g.drawString(this.strs[this.pointer + this.tablePointer], this.positionX + 14, this.positionY + this.singleHeight - UIComponent.charH + this.tablePointer * this.singleHeight, 20);
          } 
          break;
      } 
      if (this.scroll != null && this.scroll.isVisible)
        this.scroll.draw(g); 
    } 
  }
  
  public void up() {
    if (this.type != 2) {
      if (this.pointer > 0)
        this.pointer -= this.cols; 
    } else if (this.tablePointer > 0) {
      this.tablePointer--;
    } else if (this.pointer > 0) {
      this.pointer--;
    } 
    if (this.scroll != null)
      this.scroll.setScrollPosition((short)(this.pointer / this.cols)); 
  }
  
  public void down() {
    if (this.type != 2) {
      if (this.pointer + this.canShowRows < this.rows)
        this.pointer += this.cols; 
    } else if (this.tablePointer < this.canShowRows - 1) {
      this.tablePointer++;
    } else if (this.pointer + this.canShowRows < this.rows) {
      this.pointer++;
    } 
    if (this.scroll != null)
      this.scroll.setScrollPosition((short)(this.pointer / this.cols)); 
  }
  
  public int getCurrentPointer() {
    return this.pointer + this.tablePointer;
  }
  
  public void setRim(boolean isHave) {
    this.isHaveRim = isHave;
  }
  
  public void deleteItem(int index) {
    int lg = this.strs.length;
    if (lg != 1) {
      if (index == lg - 1) {
        this.strs[lg - 1] = null;
      } else {
        for (int j = index; j < lg - 1; j++) {
          this.strs[j] = this.strs[j + 1];
          this.color[j] = this.color[j + 1];
        } 
        this.strs[lg - 1] = null;
      } 
      String[] temp = new String[this.strs.length - 1];
      int i;
      for (i = 0; i < this.strs.length - 1; i++)
        temp[i] = this.strs[i]; 
      this.strs = null;
      this.strs = temp;
      this.rows--;
      if (this.rows >= this.canShowRows) {
        this.pointer--;
        this.pointer = (this.pointer < 0) ? 0 : this.pointer;
      } else {
        this.tablePointer--;
        if (this.type == 3 || this.type == 5) {
          this.height -= this.singleHeight;
          this.canShowRows--;
        } 
      } 
      if (this.tablePointer < 0)
        this.tablePointer = 0; 
      for (i = index; i < this.isReaded.length - 1; i++) {
        this.isReaded[i] = this.isReaded[i + 1];
        this.isHaveAccessory[i] = this.isHaveAccessory[i + 1];
      } 
    } else {
      this.strs = null;
      this.isReaded = null;
      this.isHaveAccessory = null;
      if (this.type == 3 || this.type == 5) {
        this.height -= this.singleHeight;
        this.canShowRows--;
      } 
    } 
    if (this.canShowRows < this.rows) {
      int h2 = this.height;
      h2 *= 208;
      h2 /= UIComponent.CURR_H;
      this.scroll = new UIScroll(this.positionX + this.width - 4, this.positionY, 4, h2, (byte)0, false);
      this.scroll.setBar(this.rows, this.canShowRows);
    } else {
      this.scroll = null;
    } 
  }
  
  public int getItemNumber() {
    if (this.strs == null)
      return 0; 
    return this.strs.length;
  }
  
  public String getCurentItem() {
    return this.strs[getCurrentPointer()];
  }
  
  public boolean isNull() {
    if (this.strs == null)
      return true; 
    return false;
  }
  
  public void setAutoHeight(boolean is) {
    this.isAutoHeight = is;
    if (this.isAutoHeight)
      this.height = this.singleHeight * this.canShowRows; 
    this.scroll = null;
    if (this.canShowRows < this.rows) {
      int h2 = this.height;
      h2 *= 208;
      h2 /= UIComponent.CURR_H;
      this.scroll = new UIScroll(this.positionX + this.width + 3, this.positionY, 4, h2, (byte)0, false);
      this.scroll.setBar(this.rows, this.canShowRows);
    } 
  }
  
  public int getCurrentColor() {
    return this.color[getCurrentPointer()];
  }
  
  public void setAutoWidth() {
    int w = 0;
    for (int i = 0; i < this.strs.length; i++) {
      int temp = font.stringWidth(this.strs[i]);
      if (temp > w)
        w = temp; 
    } 
    this.width = w + 16;
  }
  
  public void setInterspace(byte inter) {
    this.interspace = inter;
  }
  
  public void setIsMiddle(boolean ism) {
    this.isMiddle = ism;
  }
  
  public void setImage(int index, MImage _mimg) {
    this.mimg[index] = _mimg;
  }
  
  public void setReadingState(int index, boolean state) {
    if (this.isReaded != null)
      this.isReaded[index] = state; 
  }
  
  public void setAccessoryState(int index, boolean state) {
    if (this.isHaveAccessory != null)
      this.isHaveAccessory[index] = state; 
  }
  
  public void setFocusColorChange(boolean isChangeColor) {}
  
  public void setScrollVisible(boolean isv) {
    if (this.scroll != null)
      this.scroll.isVisible = isv; 
  }
  
  public void setCurrentPointer(int newP) {
    if (newP < this.canShowRows) {
      this.pointer = 0;
      this.tablePointer = newP;
    } else {
      this.tablePointer = this.canShowRows - 1;
      this.pointer = newP - this.tablePointer;
    } 
    if (this.canShowRows < this.rows && this.scroll != null)
      this.scroll.setScrollPosition((short)(this.pointer / this.cols)); 
  }
  
  public void setSelect(int p, boolean b) {
    this.isSelect[p] = b;
  }
  
  public boolean getSelect(int p) {
    return this.isSelect[p];
  }
  
  public void setSelectAll(boolean b) {
    for (int i = 0; i < this.isSelect.length; i++)
      this.isSelect[i] = b; 
  }
  
  public boolean[] getSelectAll() {
    return this.isSelect;
  }
  
  public void setOverPointer(int[] index) {
    this.deFocus = new int[index.length];
    for (int i = 0; i < index.length; i++) {
      this.deFocus[i] = -1;
      if (getCurrentPointer() == index[i])
        setCurrentPointer(index[i] + 1); 
      this.deFocus[i] = index[i];
    } 
  }
  
  public UIComponent getAroundComponent(byte direction) {
    if (this.strs != null)
      switch (direction) {
        case 1:
          this.isDown = false;
          if (this.type != 2 && this.type != 3 && this.type != 5 && this.type != 7) {
            if (this.pointer > 0)
              this.pointer -= this.cols; 
          } else if (this.tablePointer > 0) {
            if (this.pointer == 0) {
              if (this.deFocus != null)
                for (int i = 0; i < this.deFocus.length; i++) {
                  if (this.tablePointer == this.deFocus[i] + 1) {
                    if (this.tablePointer > 2)
                      this.tablePointer = this.deFocus[i] - 1; 
                    return null;
                  } 
                }  
            } else if (this.deFocus != null) {
              for (int i = 0; i < this.deFocus.length; i++) {
                if (this.tablePointer == this.deFocus[i] + 1 - this.pointer) {
                  if (this.tablePointer > 2) {
                    this.tablePointer = this.deFocus[i] - 1 - this.pointer;
                  } else if (this.tablePointer == 2) {
                    this.tablePointer = 0;
                  } else {
                    this.tablePointer = 0;
                    this.pointer--;
                  } 
                  return null;
                } 
              } 
            } 
            this.tablePointer--;
          } else if (this.pointer > 0) {
            if (this.deFocus != null)
              for (int i = 0; i < this.deFocus.length; i++) {
                if (this.pointer == this.deFocus[i] + 1) {
                  if (this.pointer > 2) {
                    this.pointer = this.deFocus[i] - 1;
                  } else {
                    this.pointer = 0;
                    setCurrentPointer(1);
                  } 
                  return null;
                } 
              }  
            this.pointer--;
          } else {
            return this.upComponent;
          } 
          if (this.scroll != null)
            this.scroll.setScrollPosition((short)(this.pointer / this.cols)); 
          this.moveX[this.pointer + this.tablePointer] = 0;
          return null;
        case 2:
          if (this.type != 2 && this.type != 3 && this.type != 5 && this.type != 7) {
            if (this.pointer / this.cols < this.rows - this.canShowRows)
              this.pointer += this.cols; 
          } else if (this.rows < this.canShowRows) {
            if (this.tablePointer < this.rows - 1) {
              if (this.deFocus != null)
                for (int i = 0; i < this.deFocus.length; i++) {
                  if (this.tablePointer == this.deFocus[i] - 1) {
                    if (this.tablePointer < this.rows - 2)
                      this.tablePointer = this.deFocus[i] + 1; 
                    return null;
                  } 
                }  
              this.tablePointer++;
            } 
          } else if (this.tablePointer < this.canShowRows - 1) {
            if (this.pointer == 0) {
              if (this.deFocus != null)
                for (int i = 0; i < this.deFocus.length; i++) {
                  if (this.tablePointer == this.deFocus[i] - 1) {
                    if (this.tablePointer < this.canShowRows - 2) {
                      this.tablePointer = this.deFocus[i] + 1;
                    } else {
                      this.tablePointer = this.canShowRows - 1;
                      this.pointer = 1;
                    } 
                    return null;
                  } 
                }  
            } else if (this.deFocus != null) {
              for (int i = 0; i < this.deFocus.length; i++) {
                if (this.tablePointer == this.deFocus[i] - 1 - this.pointer) {
                  this.tablePointer = this.deFocus[i] + 1 - this.pointer;
                  return null;
                } 
              } 
            } 
            this.tablePointer++;
          } else if (this.pointer < this.rows - this.canShowRows) {
            if (this.deFocus != null)
              for (int i = 0; i < this.deFocus.length; i++) {
                if (this.pointer == this.deFocus[i] - this.canShowRows) {
                  if (this.pointer < this.rows - this.canShowRows - 1)
                    this.pointer = this.deFocus[i] - this.canShowRows + 2; 
                  return null;
                } 
              }  
            this.pointer++;
          } else {
            this.isDown = true;
            return this.downComponent;
          } 
          if (this.scroll != null)
            this.scroll.setScrollPosition((short)(this.pointer / this.cols)); 
          this.moveX[this.pointer + this.tablePointer] = 0;
          return null;
        case 3:
          return this.leftComponent;
        case 4:
          return this.rightComponent;
      }  
    return null;
  }
  
  private void move(Graphics g, int index, int x, int y, int singleWidth, int singleHeight, int translate) {
    int stringWidth = MainCanvas.font[1].stringWidth(this.strs[index]);
    if (stringWidth > singleWidth) {
      if (stringWidth + this.moveX[index] < 0)
        this.moveX[index] = singleWidth; 
      this.moveX[index] = this.moveX[index] - 4;
    } else {
      this.moveX[index] = 0;
    } 
    g.setClip(x, y, singleWidth, singleHeight);
    g.drawString(this.strs[index], x + this.moveX[index], y, translate);
  }
}
