import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.StringItem;

public class UIStringItem extends StringItem implements ItemCommandListener {
  public static Command cmd = null;
  
  private int channel = 0;
  
  public UIStringItem(String m) {
    super("", m + "\n");
  }
  
  public void addCustomCommand(int content, String s) {
    if (s.startsWith(Cons.STR_CHAT_CHANNEL[8]) || s.startsWith("系统"))
      return; 
    if (cmd == null)
      cmd = new Command("回复", 8, 2); 
    this.channel = content;
    addCommand(cmd);
    setItemCommandListener(this);
  }
  
  public void commandAction(Command cmd, Item item) {
    if (MainCanvas.mc.chatForm == null)
      return; 
    int channel = ((UIStringItem)item).channel;
    if (channel == (Player.getInstance()).objID)
      return; 
    if (channel > 10) {
      String temp = getText();
      int index = temp.indexOf(":");
      if (index < 0 || 3 > index)
        return; 
      String name = getText().substring(3, index);
      for (int i = 0; i < Cons.BAD_STRING.length; i++) {
        if (Cons.BAD_STRING[i].equals(name))
          return; 
      } 
      MainCanvas.mc.chatText.setLabel("与[" + name + "]聊天:");
      MainCanvas.localChatChannel = 1;
      MainCanvas.chatFriendId = channel;
    } 
  }
}
