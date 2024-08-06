import javax.microedition.lcdui.Graphics;

public class UILabel extends UIComponent {
  public static final byte LEFT_STANDARD = 0;
  
  public static final byte MIDDLE_STANDARD = 1;
  
  public static final byte RIGHT_STANDARD = 5;
  
  public static final byte ESPECIAL_STANDARD = 2;
  
  public static final byte GREEN_STYLE = 3;
  
  public static final byte NO_LINE = 0;
  
  public static final byte HAVE_LINE = 1;
  
  private String str = null;
  
  private String[] strsBuffered = null;
  
  private byte layoutType = 0;
  
  private byte lineType = 0;
  
  private int color = 16777215;
  
  private byte rimStyle = 0;
  
  private boolean isHaveRim = false;
  
  private int greenColor = 65280;
  
  public UILabel(int x, int y, int w, int h, String str, int color, byte layoutType, byte lineType) {
    super(x, y, w, h);
    if (this.height == 0)
      this.height = font.getHeight(); 
    this.layoutType = layoutType;
    this.lineType = lineType;
    this.color = color;
    this.canFocus = false;
    if (str == null)
      str = "   "; 
    this.str = str;
    if (this.width != 0) {
      int ww = this.width;
      if (layoutType == 2)
        ww -= ww / 4 + 5; 
      this.strsBuffered = Util.wrapText(str, ww, font);
    } else {
      this.strsBuffered = new String[1];
      this.strsBuffered[0] = str;
    } 
    if (this.width == 0)
      this.width = font.stringWidth(str); 
    this.height = (charH + 2) * this.strsBuffered.length;
  }
  
  public void setStr(String string) {
    if (string == null || string.length() == 0)
      string = ""; 
    this.str = null;
    this.str = string;
    this.strsBuffered = Util.wrapText(this.str, this.width, font);
  }
  
  public void setGreenChars(int start, int end) {
    if (start >= 0 && end <= this.str.length() && end > start) {
      this.strsBuffered = new String[3];
      this.strsBuffered[0] = this.str.substring(0, start);
      this.strsBuffered[1] = this.str.substring(start, end);
      this.strsBuffered[2] = this.str.substring(end);
    } 
  }
  
  public void draw(Graphics g) {
    int i, x;
    if (this.isHaveRim)
      UIRim.drawRim(g, this.positionX, this.positionY, this.width, this.height, this.rimStyle); 
    if (this.strsBuffered == null)
      return; 
    int fullRows = this.strsBuffered.length;
    switch (this.layoutType) {
      case 0:
        switch (this.lineType) {
          case 0:
            for (i = 0; i < fullRows; i++) {
              g.setColor(this.color);
              g.drawString(this.strsBuffered[i], this.positionX, this.positionY + (charH + 2) * i, 20);
            } 
            break;
          case 1:
            for (i = 0; i < fullRows; i++) {
              g.setColor(this.color);
              g.drawString(this.strsBuffered[i], this.positionX, this.positionY + (charH + 6) * i, 20);
              g.setColor(0);
              g.drawRect(this.positionX - 3, this.positionY + (charH + 6) * (i + 1) - 3, this.width + 6, 3);
              g.setColor(9335622);
              g.drawLine(this.positionX - 2, this.positionY + (charH + 6) * (i + 1) - 3, this.positionX + this.width + 4, this.positionY + (charH + 6) * (i + 1) - 2);
            } 
            break;
        } 
        break;
      case 1:
        switch (this.lineType) {
          case 0:
            for (i = 0; i < fullRows; i++) {
              g.setColor(this.color);
              g.drawString(this.strsBuffered[i], this.positionX + 4 + (this.width >> 1), this.positionY + (charH + 4) * i, 17);
            } 
            break;
          case 1:
            for (i = 0; i < fullRows; i++) {
              g.setColor(this.color);
              g.drawString(this.strsBuffered[i], this.positionX + (this.width >> 1), this.positionY + (charH + 6) * i, 17);
              g.setColor(0);
              g.drawRect(this.positionX - 3, this.positionY + (charH + 6) * (i + 1) - 3, this.width + 6, 3);
              g.setColor(9335622);
              g.drawLine(this.positionX - 2, this.positionY + (charH + 6) * (i + 1) - 2, this.positionX + this.width + 4, this.positionY + (charH + 6) * (i + 1) - 2);
            } 
            break;
        } 
        break;
      case 5:
        for (i = 0; i < fullRows; i++) {
          g.setColor(this.color);
          g.drawString(this.strsBuffered[i], this.positionX + this.width, this.positionY + (charH + 2) * i, 24);
        } 
        break;
      case 2:
        this.width = 76 * CURR_W / 176;
        this.height = 85 * CURR_H / 208;
        switch (this.lineType) {
          case 0:
            UIRim.drawRim(g, this.positionX, this.positionY, this.width, this.height, (byte)3);
            g.setColor(this.color);
            for (i = 0; i < fullRows; i++)
              g.drawString(this.strsBuffered[i], this.positionX + this.width / 4 + 5, this.positionY + (charH + 1) * i, 20); 
            break;
        } 
        break;
      case 3:
        x = this.positionX;
        if (this.strsBuffered.length != 3) {
          g.setColor(this.color);
          g.drawString(this.str, x, this.positionY, 20);
          break;
        } 
        g.setColor(this.color);
        g.drawString(this.strsBuffered[0], x, this.positionY, 20);
        x += font.stringWidth(this.strsBuffered[0]) + 2;
        g.setColor(this.greenColor);
        g.drawString(this.strsBuffered[1], x, this.positionY, 20);
        x += font.stringWidth(this.strsBuffered[1]) + 2;
        g.setColor(this.color);
        g.drawString(this.strsBuffered[2], x, this.positionY, 20);
        break;
    } 
  }
  
  public String getCurrentString() {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < this.strsBuffered.length; i++)
      sb.append(this.strsBuffered[i]); 
    return sb.toString();
  }
  
  public void setRimStyle(byte rs) {
    this.isHaveRim = true;
    this.rimStyle = rs;
  }
  
  public void setColor(int c) {
    this.color = c;
  }
  
  public void setGreenColor(int color) {
    this.greenColor = color;
  }
  
  public String getStr() {
    return this.str;
  }
}
