import java.util.Enumeration;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class UIForm extends UIComponent {
  public static final byte FORM_NOBOUND = 0;
  
  public static final byte FORM_BOUND = 1;
  
  public static final byte X_CENTER = 2;
  
  public static final byte Y_CENTER = 3;
  
  public static final byte XY_CENTER = 4;
  
  public static final byte LEFT_BOTTOM = 5;
  
  public static final byte RIGHT_BOTTOM = 6;
  
  public static final byte FROM_BACKGROUND_0 = 7;
  
  public static final byte FROM_BACKGROUND_1 = 8;
  
  public static final byte FORM_BACKGROUND_2 = 9;
  
  private byte bgStyle = 7;
  
  UIComponent focusComponent = null;
  
  Vector components = new Vector();
  
  Enumeration e = null;
  
  private byte style = 1;
  
  private boolean showMessage = false;
  
  private String msg = null;
  
  private int msgCount = 0;
  
  private byte showTimes = 2;
  
  String title;
  
  private UIForm aboutForm = null;
  
  private boolean isShowAboutForm = false;
  
  public static byte state = 0;
  
  private UIForm parent = null;
  
  private boolean cannotInterrupt = false;
  
  public UIForm(int x, int y, int w, int h, String text) {
    super(x, y, w, h);
    this.title = text;
    this.canFocus = false;
  }
  
  public void addComponent(UIComponent uic) {
    resetUICPosition(uic);
    uic.setXY(uic.positionX + this.positionX, uic.positionY + this.positionY);
    this.components.addElement(uic);
  }
  
  public void resetUICPosition(UIComponent uic) {
    if (uic != null) {
      uic.positionX *= CURR_W;
      uic.positionX /= 176;
      uic.positionY *= CURR_H;
      uic.positionY /= 208;
    } 
  }
  
  public void setFocus(boolean flag) {
    UIComponent temp = null;
    if (flag) {
      for (int i = 0; i < this.components.size(); i++) {
        temp = (UIComponent)this.components.elementAt(i);
        if (this.focusComponent == null && temp != null && temp
          .canFocus()) {
          this.focusComponent = temp;
          this.focusComponent.setFocus(true);
          break;
        } 
      } 
      if (this.focusComponent != null && !(this.focusComponent instanceof UIRim)) {
        this.components.removeElement(this.focusComponent);
        this.components.addElement(this.focusComponent);
      } 
    } else if (this.focusComponent != null) {
      this.focusComponent.setFocus(false);
      this.focusComponent = null;
    } 
  }
  
  public void setStyle(byte style) {
    this.style = style;
  }
  
public void draw(Graphics g) {
        if (this.aboutForm == null || !this.isShowAboutForm || this.aboutForm.width != this.width || this.aboutForm.height != this.height) {
            if (this.bgStyle != 9) {
                g.setColor(2037253);
                g.fillRect(this.positionX, this.positionY, this.width, this.height);
                g.setColor(0);
                g.drawRect(this.positionX - 1, this.positionY - 1, this.width + 2, this.height + 2);
                if (this.style == 1) {
                    UIRim.drawRim(g, this.positionX, this.positionY, this.width, this.height, (byte)2);
                } else {
                    g.setClip(0, 0, MainCanvas.screenW, MainCanvas.screenH);
                    switch (this.bgStyle) {
                        case 7:
                            MainCanvas.drawGroundback(g);
                            break;
                        case 8:
                            MainCanvas.drawGroundback(g);
                    }
                }
            }

            this.e = this.components.elements();

            while(this.e.hasMoreElements()) {
                UIComponent uic = (UIComponent)this.e.nextElement();
                if (uic.getVisible()) {
                    uic.draw(g);
                }
            }
        }

        if (this.aboutForm != null && this.isShowAboutForm) {
            this.aboutForm.draw(g);
        }

        if (this.showMessage) {
            g.setClip(0, 0, this.width, this.height);
            g.setColor(0);
            g.fillRect(0, 0, this.width, charH + 2);
            g.setColor(16711680);
            g.drawString(this.msg, this.width - this.msgCount, 1, 20);
            g.setColor(16769185);
            g.drawLine(0, charH + 2, this.width, charH + 2);
            if (this.width - this.msgCount < -font.stringWidth(this.msg)) {
                this.msgCount = 0;
                ++this.showTimes;
                if (this.showTimes > 1) {
                    this.msg = null;
                    this.showMessage = false;
                    this.msgCount = 0;
                    this.showTimes = 0;
                    this.cannotInterrupt = false;
                    return;
                }
            }

            this.msgCount += 6;
        }

    }
  
  public UIComponent getCommand() {
    if (this.aboutForm != null && this.isShowAboutForm)
      return this.aboutForm.getCommand(); 
    return this.focusComponent;
  }
  
  public void setDirectionFocus(int key) {
    UIComponent temp = null;
    if (this.aboutForm != null && this.isShowAboutForm) {
      this.aboutForm.setDirectionFocus(key);
      return;
    } 
    if (this.focusComponent == null)
      return; 
    switch (key) {
      case 11:
        temp = this.focusComponent.getAroundComponent((byte)1);
        break;
      case 13:
        temp = this.focusComponent.getAroundComponent((byte)2);
        break;
      case 10:
        temp = this.focusComponent.getAroundComponent((byte)3);
        break;
      case 12:
        temp = this.focusComponent.getAroundComponent((byte)4);
        break;
    } 
    if (temp == null)
      return; 
    this.focusComponent.setFocus(false);
    this.focusComponent = temp;
    this.focusComponent.setFocus(true);
    if (!(this.focusComponent instanceof UIRim)) {
      this.components.removeElement(this.focusComponent);
      this.components.addElement(this.focusComponent);
    } 
  }
  
  public void setAboutForm(UIForm subForm) {
    if (subForm != null) {
      this.aboutForm = subForm;
      subForm.parent = this;
      this.aboutForm.setXY(this.positionX + (this.width >> 1) - (this.aboutForm.width >> 1), this.positionY + (this.height >> 1) - (this.aboutForm.height >> 1));
      this.e = subForm.components.elements();
      while (this.e.hasMoreElements()) {
        UIComponent uic = (UIComponent)this.e.nextElement();
        uic.setXY(this.aboutForm.positionX + uic.positionX, this.aboutForm.positionY + uic.positionY);
      } 
      this.isShowAboutForm = true;
      if (this.focusComponent != null)
        this.focusComponent.setFocus(false); 
      this.aboutForm.setFocus(true);
    } else {
      this.aboutForm = null;
      this.isShowAboutForm = false;
      if (this.focusComponent != null)
        this.focusComponent.setFocus(true); 
    } 
  }
  
  public void addComponentInCenter(UIComponent uic, byte style) {
    if (uic == null)
      return; 
    int offset = 5;
    switch (style) {
      case 2:
        uic.positionX = this.width - uic.width >> 1;
        uic.positionY -= this.positionY;
        uic.positionY *= CURR_H;
        uic.positionY /= 208;
        break;
      case 3:
        uic.positionX = 0;
        uic.positionY = (this.height - uic.height >> 1) + uic.positionY - this.positionY;
        break;
      case 4:
        uic.positionX = this.width - uic.width >> 1;
        uic.positionY = this.height - uic.height >> 1;
        break;
      case 5:
        uic.positionX = offset;
        uic.positionY = this.height - uic.height - offset + 3;
        break;
      case 6:
        uic.positionX = this.width - uic.width - offset - 3;
        uic.positionY = this.height - uic.height - offset + 3;
        break;
    } 
    uic.setXY(uic.positionX + this.positionX, uic.positionY + this.positionY);
    this.components.addElement(uic);
  }
  
  public UIForm getSubForm() {
    return this.aboutForm;
  }
  
  public boolean isSubForm() {
    return this.isShowAboutForm;
  }
  
  public String getSubFormName() {
    return this.aboutForm.getName();
  }
  
  public String getName() {
    return this.title;
  }
  
  public UIForm getCurrentFocusForm() {
    if (this.isShowAboutForm && this.aboutForm != null)
      return this.aboutForm.getCurrentFocusForm(); 
    return this;
  }
  
  public void setBackGround(byte style) {
    this.bgStyle = style;
  }
  
  public void removeComponent(UIComponent uic) {
    this.e = this.components.elements();
    while (this.e.hasMoreElements()) {
      if (this.e.nextElement() == uic) {
        this.components.removeElement(uic);
        if (this.focusComponent == uic) {
          this.focusComponent = null;
          setFocus(true);
        } 
      } 
    } 
  }
  
  /**
   * 选中某个组件
   * @param uic 
   */
  public void setComponentFocus(UIComponent uic) {
    this.e = this.components.elements();
    while (this.e.hasMoreElements()) {
      if (uic == this.e.nextElement()) {
        if (this.focusComponent != null)
          this.focusComponent.setFocus(false); 
        this.focusComponent = uic;
        this.focusComponent.setFocus(true);
      } 
    } 
  }
  
  public void setFocusComponentFocus(boolean flag) {
    if (this.focusComponent == null)
      return; 
    this.focusComponent.setFocus(flag);
  }
  
  /**
   * 增加一个确认提示框
   * @param formName
   * @param message
   * @param flag 消息类型
   * @param w
   * @param h 
   */
  public void addAboutForm(String formName, String message, byte flag, int w, int h) {
    UIForm aboutForm;
    String left = null;
    if (flag > 0)
      left = "确定"; 
    String right = null;
    if (flag > 1)
      right = "返回"; 
    if (formName.equals("waiting") && MainCanvas.mc.getState() == 5) {
      aboutForm = new UIForm(0, 0, 10, 10, formName);
      aboutForm.setBackGround((byte)9);
    } else {
      aboutForm = makeAboutForm(formName, message, left, right, w);
    } 
    setAboutForm(aboutForm);
  }
  
  public static UIForm makeAboutForm(String formName, String message, String left, String right, int width) {
    if (width == 0) {
      int wMessage = font.stringWidth(message);
      int wButton = charW * 5;
      width = (wButton > wMessage) ? wButton : wMessage;
    } 
    width += 10;
    int w = width - 10;
    w *= 176;
    w /= UIComponent.CURR_W;
    UILabel lblMessage = new UILabel(0, 5, w, 0, message, 16777215, (byte)1, (byte)0);
    int height = 5 + lblMessage.height + 5;
    boolean l = (left != null && !"".equals(left.trim()));
    boolean r = (right != null && !"".equals(right.trim()));
    if (l || r)
      height += MainCanvas.CHARH + 10; 
    UIForm tempForm = new UIForm(0, 0, width, height, formName);
    tempForm.width *= 176;
    tempForm.width /= UIComponent.CURR_W;
    tempForm.height *= 208;
    tempForm.height /= UIComponent.CURR_H;
    lblMessage.positionY = tempForm.positionY + 5;
    UIRim frame = new UIRim(0, 0, width, height, (byte)0);
    frame.width *= 176;
    frame.width /= UIComponent.CURR_W;
    frame.height *= 208;
    frame.height /= UIComponent.CURR_H;
    tempForm.setBackGround((byte)9);
    tempForm.addComponent(frame);
    tempForm.addComponentInCenter(lblMessage, (byte)2);
    if (l) {
      UILabel lblOk4 = new UILabel(0, 0, 0, 0, left, 16777215, (byte)1, (byte)0);
      tempForm.addComponentInCenter(lblOk4, (byte)5);
    } 
    if (r) {
      UILabel lblCancle = new UILabel(0, 0, 0, 0, right, 16777215, (byte)1, (byte)0);
      tempForm.addComponentInCenter(lblCancle, (byte)6);
    } 
    return tempForm;
  }
  
  public UIForm getParentForm() {
    if (this.parent == null)
      return this; 
    return this.parent;
  }
  
  public void addInputForm(String formName, String message, UIText text, int w) {
    addInputForm(formName, message, text, w, (Image)null);
  }
  
  public void addInputForm(String formName, String message, UIText text, int w, Image img) {
    if (text == null)
      return; 
    int wMessage = font.stringWidth(message);
    int wText = text.width;
    int wTemp = (wText > wMessage) ? wText : wMessage;
    if (w < wTemp)
      w = wTemp; 
    if (w > 165)
      w = 165; 
    w += 10;
    UILabel lblConfirm = new UILabel(0, 3, w - 10, 0, message, 16777215, (byte)1, (byte)0);
    UILabel lblOk4 = new UILabel(0, 0, 0, 0, "确定", 16777215, (byte)1, (byte)0);
    UILabel lblCancle = new UILabel(0, 0, 0, 0, "返回", 16777215, (byte)1, (byte)0);
    UIMImage uimimg = null;
    if (img != null) {
      MImage mimg = new MImage(img);
      uimimg = new UIMImage(0, 0, mimg.frame_w, mimg.frame_h, mimg, (byte)0);
      uimimg.canFocus = false;
    } 
    int h = 2 + lblConfirm.height + 2 + text.height + 4 + lblOk4.height + 2 + 10;
    h *= UIComponent.CURR_H;
    h /= 208;
    UIForm aboutForm = new UIForm(0, 0, w, h, formName);
    aboutForm.width *= 176;
    aboutForm.width /= UIComponent.CURR_W;
    aboutForm.height *= 208;
    aboutForm.height /= UIComponent.CURR_H;
    UIRim frame = new UIRim(0, 0, aboutForm.width, aboutForm.height, (byte)4);
    frame.width *= 176;
    frame.width /= UIComponent.CURR_W;
    frame.height *= 208;
    frame.height /= UIComponent.CURR_H;
    aboutForm.addComponent(frame);
    aboutForm.addComponentInCenter(lblCancle, (byte)6);
    aboutForm.addComponentInCenter(lblOk4, (byte)5);
    aboutForm.addComponentInCenter(lblConfirm, (byte)2);
    int dy = 3 + lblConfirm.height;
    if (uimimg != null) {
      uimimg.positionY = dy;
      aboutForm.addComponentInCenter(uimimg, (byte)2);
      dy += uimimg.height;
    } 
    text.positionY = dy;
    aboutForm.addComponentInCenter(text, (byte)2);
    setAboutForm(aboutForm);
  }
  
  public void setMessage(String message, boolean cannotInterrupt) {
    if (message == null) {
      this.showMessage = false;
      return;
    } 
    if (!Cons.newPlayerHelp && message != "你正在被攻击" && !message.startsWith("您的宠物") && 
      !message.startsWith("您宠物的灵魄"))
      return; 
    if (this.showMessage) {
      if (this.cannotInterrupt)
        return; 
      if (this.msg == message)
        return; 
    } 
    this.msg = message;
    this.showMessage = true;
    this.cannotInterrupt = cannotInterrupt;
    this.msgCount = 0;
    this.showTimes = 0;
  }
  
  public void setFormXY(int x, int y) {
    this.e = this.components.elements();
    while (this.e.hasMoreElements()) {
      UIComponent uic = (UIComponent)this.e.nextElement();
      uic.positionX = uic.positionX - this.positionX + x;
      uic.positionY = uic.positionY - this.positionY + y;
    } 
    if (this.aboutForm != null) {
      int px = this.aboutForm.positionX - this.positionX + x;
      int py = this.aboutForm.positionY - this.positionY + y;
      this.aboutForm.setFormXY(px, py);
    } 
    this.positionX = x;
    this.positionY = y;
  }
  
  public void setShowMessage(boolean showMessage) {
    this.showMessage = showMessage;
  }
  
  public UILabel addLeftButton(String text) {
    UILabel button = new UILabel(0, 0, 0, 0, text, 15718814, (byte)1, (byte)0);
    addComponentInCenter(button, (byte)5);
    return button;
  }
  
  public UILabel addRightButton(String text) {
    UILabel button = new UILabel(0, 0, 0, 0, text, 15718814, (byte)1, (byte)0);
    addComponentInCenter(button, (byte)6);
    return button;
  }
}
