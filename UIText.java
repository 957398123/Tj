import javax.microedition.lcdui.Graphics;

public class UIText extends UIComponent {
  String sb = null;
  
  String sbMask = null;
  
  private int interval = 0;
  
  private int numChar = 0;
  
  private int keyOld = -1;
  
  private int keyIndex = 0;
  
  private long intervalTime = 1000L;
  
  private long lastTime = 0L;
  
  private long maxNumber = -1L;
  
  long minNumber = 0L;
  
  private byte moneyIndex = 0;
  
  public static final byte TEXT_NORMAL = 0;
  
  public static final byte TEXT_MASK = 1;
  
  public static final byte TEXT_NUMBER = 2;
  
  public static final byte TEXT_MONEY = 3;
  
  private static char maskChar = '*';
  
  private byte style = 0;
  
  public static String[] chars = new String[] { "0", "1,.+_)(*&^%$#@", "abc2", "def3", "ghi4", "jkl5", "mno6", "pqrs7", "tuv8", "wxyz9" };
  
  public static String charNum = "0123456789";
  
  private boolean isRight = false;
  
  private int color = 15653280;
  
  private byte maskCount = 0;
  
  private int cx;
  
  public void setDefaultColor() {
    this.color = 15653280;
  }
  
  public UIText(int x, int y, int w, int h, int maxCharNum, byte style, String text) {
    super(x, y, w, h);
    this.cx = 0;
    if (style == 3)
      maxCharNum = 10; 
    this.sb = text;
    if (w == 0)
      w = font.stringWidth(text) + 6; 
    this.width = w * CURR_W / 176;
    this.height = charH;
    if (style == 0 || style == 1)
      this.height += 3; 
    this.numChar = (maxCharNum > 0) ? maxCharNum : 1;
    this.style = style;
    if (style == 1) {
      this.sbMask = "";
      for (int i = 0; i < this.sb.length(); i++)
        this.sbMask += maskChar; 
    } else if (style == 3 || style == 2) {
      long temp = 0L;
      for (int i = 0; i < this.numChar; i++) {
        temp *= 10L;
        temp++;
      } 
      this.maxNumber = temp * 9L;
      if (style == 3)
        this.maxNumber = 1999999999L; 
    } 
  }
  
  public void setColor(int c) {
    this.color = c;
  }
  
  public void setMoneyImageIndex(byte index) {
    if (index < 0 || index > 1)
      return; 
    this.moneyIndex = index;
  }
  
  public void rightStanderd(boolean flag) {
    this.isRight = flag;
  }
  
  public void draw(Graphics g) {
    int len, start;
    String tempStr;
    int strWidth = 0;
    g.setColor(0);
    g.fillRect(this.positionX, this.positionY, this.width, this.height);
    switch (this.style) {
      case 2:
      case 3:
        UIRim.drawRim(g, this.positionX, this.positionY, this.width, this.height, (byte)0);
        g.setColor(this.color);
        len = this.sb.toString().trim().length();
        start = 0;
        tempStr = this.sb.toString().trim();
        strWidth = font.stringWidth(tempStr);
        while (strWidth + 4 > this.width && 
          start <= len - 1) {
          start++;
          tempStr = this.sb.toString().substring(start).trim();
          strWidth = font.stringWidth(tempStr);
        } 
        if (this.style == 2) {
          if (this.isRight) {
            g.drawString(tempStr, this.positionX + this.width - strWidth, this.positionY, 20);
            break;
          } 
          g.drawString(tempStr, this.positionX + 4, this.positionY, 20);
          break;
        } 
        MainCanvas.mImgUI[8].draw(g, this.positionX + 2, this.positionY + 5, this.moneyIndex, false);
        if (this.isRight) {
          g.drawString(tempStr, this.positionX + this.width - strWidth, this.positionY, 20);
          break;
        } 
        g.drawString(tempStr, this.positionX + 7 + 4, this.positionY, 20);
        break;
      case 0:
      case 1:
        g.setColor(9204806);
        g.drawRect(this.positionX, this.positionY, this.width, this.height);
        g.setColor(11506013);
        g.drawRect(this.positionX + 1, this.positionY + 1, this.width - 1, this.height - 1);
        g.setColor(15718815);
        g.fillRect(this.positionX + 2, this.positionY + 2, this.width - 1, this.height - 1);
        g.setColor(920069);
        if (this.style == 1) {
          if (this.maskCount > 0) {
            this.maskCount = (byte)(this.maskCount - 1);
            if (this.maskCount == 0 && this.sbMask.length() > 0)
              this.sbMask = this.sbMask.substring(0, this.sbMask.length() - 1) + maskChar; 
          } 
          len = this.sbMask.toString().trim().length();
          start = 0;
          tempStr = this.sbMask.toString().trim();
          strWidth = font.stringWidth(tempStr);
          while (strWidth + 5 > this.width && 
            start <= len - 1) {
            start++;
            tempStr = this.sbMask.toString().substring(start).trim();
            strWidth = font.stringWidth(tempStr);
          } 
          g.drawString(tempStr, this.positionX + 4, this.positionY + 3, 20);
          break;
        } 
        len = this.sb.toString().trim().length();
        start = 0;
        tempStr = this.sb.toString().trim();
        strWidth = font.stringWidth(tempStr);
        while (strWidth + 5 > this.width && 
          start <= len - 1) {
          start++;
          tempStr = this.sb.toString().substring(start).trim();
          strWidth = font.stringWidth(tempStr);
        } 
        g.drawString(tempStr, this.positionX + 4, this.positionY + 3, 20);
        break;
    } 
    if (this.focus) {
      if (++this.interval > 5)
        this.interval = 0; 
      if (this.interval < 3) {
        if (this.style == 1) {
          if (strWidth + 4 <= this.width)
            this.cx = this.positionX + 3 + strWidth + 2; 
        } else if (strWidth + 4 <= this.width) {
          this.cx = this.positionX + 3 + strWidth + 2;
        } 
        int offY = 3;
        if (this.style == 2) {
          g.setColor(this.color);
          g.drawLine(this.cx, this.positionY + 3 + offY, this.cx, this.positionY + 12 + offY);
        } else if (this.style == 3) {
          g.setColor(this.color);
          g.drawLine(this.cx + 7, this.positionY + 3 + offY, this.cx + 7, this.positionY + 12 + offY);
        } else {
          g.setColor(920069);
          g.drawLine(this.cx, this.positionY + 4 + offY, this.cx, this.positionY + 14 + offY);
        } 
      } 
    } 
  }
  
  public void clear() {
    this.sb = "";
    if (this.style == 1)
      this.sbMask = ""; 
  }
  
  private void addChar(char c) {
    if (this.sb.length() < this.numChar)
      this.sb += c; 
    if (this.style == 1) {
      this.sbMask = "";
      int num = this.sb.length();
      for (int i = 0; i < num - 1; i++)
        this.sbMask += maskChar; 
      if (num > 0)
        this.sbMask += this.sb.charAt(this.sb.length() - 1); 
      this.maskCount = 10;
    } else if (this.style == 2 || this.style == 3) {
      if ("0".equals(this.sb.substring(0, 1)) && this.style == 3)
        this.sb = this.sb.substring(1); 
      try {
        long temp = Long.parseLong(this.sb);
        if (this.maxNumber >= 0L && temp > this.maxNumber)
          this.sb = this.maxNumber + ""; 
      } catch (Exception exception) {}
    } 
  }
  
  public void removeChar() {
    if (this.sb.length() > 0)
      if (this.sb.length() <= 1) {
        this.sb = "";
        if (this.style == 1)
          this.sbMask = ""; 
      } else {
        this.sb = this.sb.substring(0, this.sb.length() - 1);
        if (this.style == 1)
          this.sbMask = this.sbMask.substring(0, this.sbMask.length() - 1); 
      }  
  }
  
  public void setStr(String s) {
    this.sb = s;
  }
  
  public void setLabel(String s) {
    if (s == null || s.length() == 0) {
      clear();
    } else {
      if (this.style == 2)
        for (int i = 0; i < s.length(); i++) {
          if (!Character.isDigit(s.charAt(i)))
            return; 
        }  
      this.sb = s;
    } 
  }
  
  public void setFocus(boolean flag) {
    super.setFocus(flag);
    this.interval = 0;
    this.keyOld = -1;
    this.keyIndex = 0;
  }
  
  public String getLabel() {
    return this.sb.toString();
  }
  
  public void pushKey(int keyValue) {
    for (int i = 0; i < 10; i++) {
      if (i == keyValue) {
        if (this.style == 0 || this.style == 1) {
          if (this.keyOld != keyValue || 
            System.currentTimeMillis() - this.lastTime > this.intervalTime) {
            this.keyIndex = 0;
            if (this.sb.length() < this.numChar) {
              addChar(chars[i].charAt(this.keyIndex));
              this.keyOld = keyValue;
            } else {
              this.keyOld = -1;
            } 
          } else {
            if (this.sb.length() > 0)
              this.sb = this.sb.substring(0, this.sb.length() - 1); 
            if (++this.keyIndex >= chars[i].length())
              this.keyIndex = 0; 
            addChar(chars[i].charAt(this.keyIndex));
          } 
          this.lastTime = System.currentTimeMillis();
          break;
        } 
        addChar(charNum.charAt(i));
        break;
      } 
    } 
  }
  
  public UIComponent getAroundComponent(byte direction) {
    if (direction == 3) {
      removeChar();
      return null;
    } 
    return super.getAroundComponent(direction);
  }
  
  public void setMaxNumber(long max) {
    if (max < 0L)
      return; 
    this.maxNumber = max;
  }
  
  public void setMinNumber(long min) {
    if (min >= this.maxNumber || min >= 9L)
      return; 
    this.minNumber = min;
  }
  
  public int getNumber() {
    try {
      if (this.sb.toString().trim().equals(""))
        return 0; 
      int num = Integer.parseInt(this.sb.toString());
      return num;
    } catch (Exception exception) {
      return 0;
    } 
  }
}
