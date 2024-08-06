
import java.util.Vector;
import javax.microedition.lcdui.Item;

public class PCChat {

    public static byte bTrumpetDrawType = 0;
    public static String[] z_strTrumpetMessage;
    public static String strTrumpetMessage;
    public static int nTrumpet_X;
    public static int nTrumpet_Y;
    public static final long L_TRUMPET_TIME = 15000L;
    public static long L_TrumpetStartTime = 0L;
    public static byte isClanNoticeAdd = 0;
    public static final byte CHAT_LENGTH = 20;
    public static Vector chatPlayers = new Vector();
    public static Vector chatIDs = new Vector();
    public static boolean chatBlock = false;
    public static boolean isPrivateChannel = false;
    public static Integer[] chatIDsBak = null;

    public static void pushToChat(String name, int id) {
        if (chatBlock) {
            return;
        }
        if (id == (Player.getInstance()).objID) {
            return;
        }
        if (name == null || name.equals("") || name.equals("系统提示")) {
            return;
        }
        int i;
        for (i = 0; i < Cons.BAD_STRING.length; i++) {
            if (Cons.BAD_STRING[i].equals(name)) {
                return;
            }
        }
        for (i = 0; i < chatPlayers.size(); i++) {
            if (chatPlayers.elementAt(i).equals(name)) {
                return;
            }
        }
        for (i = 0; i < chatIDs.size(); i++) {
            Integer intTemp = (Integer) chatIDs.elementAt(i);
            if (intTemp.intValue() == id) {
                if (i != 0) {
                    chatIDs.removeElementAt(i);
                    chatPlayers.removeElementAt(i);
                    chatIDs.insertElementAt(new Integer(id), 0);
                    chatPlayers.insertElementAt(name, 0);
                }
                return;
            }
        }
        if (chatIDs.size() >= 6) {
            chatIDs.removeElementAt(chatIDs.size() - 1);
            chatPlayers.removeElementAt(chatPlayers.size() - 1);
        }
        chatIDs.insertElementAt(new Integer(id), 0);
        chatPlayers.insertElementAt(name, 0);
    }

    public static void parse(int commID, byte[] data) {
        String hexID = Integer.toHexString(commID);
        System.out.println("[处理命令]:命令ID:0x" + hexID + ",命令类型：PCChat");
        int id;
        String tempChatSenderName, tempChatContent, ss;
        ByteArray execDataIn = new ByteArray(data);
        switch (commID) {
            case 83886720:
                Cons.channelOption = execDataIn.readShort();
                break;
            case 83886464:
                MainCanvas.chatNowChannel = execDataIn.readByte();
                id = execDataIn.readInt();
                tempChatSenderName = execDataIn.readUTF();
                tempChatContent = execDataIn.readUTF();
                if (MainCanvas.chatNowChannel == 1) {
                    byte isExist = execDataIn.readByte();
                    if (isExist == 0) {
                        MainCanvas.chatNowChannel = 7;
                        MainCanvas.chatNowContent = "对方已经离线！";
                        addChatScreen(MainCanvas.chatNowChannel, MainCanvas.chatNowContent);
                        UIGameRun.releaseChat();
                        break;
                    }
                    if (isExist == 2) {
                        MainCanvas.chatNowChannel = 7;
                        MainCanvas.chatNowContent = "对方已将你的发言屏蔽！";
                        addChatScreen(MainCanvas.chatNowChannel, MainCanvas.chatNowContent);
                        UIGameRun.releaseChat();
                        break;
                    }
                }
                if (MainCanvas.chatNowChannel != 6 && MainCanvas.chatNowChannel != 8) {
                    pushToChat(tempChatSenderName, id);
                }
                MainCanvas.chatNowSenderName = tempChatSenderName;
                MainCanvas.chatNowContent = tempChatContent.replace('[', '(').replace(']', ')');
                ss = (id == 1) ? "" : Cons.STR_CHAT_CHANNEL[MainCanvas.chatNowChannel];
                addChatScreen(MainCanvas.chatNowChannel, ss + MainCanvas.chatNowSenderName + ":" + MainCanvas.chatNowContent);
                addChat(MainCanvas.chatNowChannel, MainCanvas.chatNowString);
                if (MainCanvas.chatNowChannel == 8) {
                    L_TrumpetStartTime = System.currentTimeMillis();
                    strTrumpetMessage = MainCanvas.chatNowString;
                    z_strTrumpetMessage = Util.wrapText(MainCanvas.chatNowString, MainCanvas.screenW - 4, MainCanvas.font[1]);
                    bTrumpetDrawType = (byte) ((id == (Player.getInstance()).objID) ? 1 : 2);
                    int fontH = 20;
                    int shortbarH = 25;
                    int firstZi = MainCanvas.screenH - fontH * 3 - shortbarH;
                    if (bTrumpetDrawType == 1) {
                        nTrumpet_X = 2;
                        nTrumpet_Y = firstZi - MainCanvas.font[1].getHeight();
                    } else {
                        nTrumpet_X = 40;
                        nTrumpet_Y = firstZi >> 1;
                    }
                }
                MainCanvas.chatChannel[0] = id;
                if (!isPrivateChannel || (isPrivateChannel && MainCanvas.chatNowChannel == 1)) {
                    UIStringItem uisi = new UIStringItem(MainCanvas.chatContent[0]);
                    if (MainCanvas.localChatChannel == 8) {
                        if (MainCanvas.chatContent[0].startsWith(Cons.STR_CHAT_CHANNEL[8])) {
                            if (MainCanvas.mc.commonForm != null) {
                                MainCanvas.mc.commonForm.insert(0, (Item) uisi);
                            } else {
                                MainCanvas.mc.chatForm.insert(1, (Item) uisi);
                            }
                        }
                    } else {
                        uisi.addCustomCommand(MainCanvas.chatChannel[0], MainCanvas.chatContent[0]);
                        MainCanvas.mc.chatForm.insert(1, (Item) uisi);
                    }
                    if (MainCanvas.mc.chatForm.size() > 19) {
                        MainCanvas.mc.chatForm.delete(19);
                    }
                }
                UIGameRun.releaseChat();
                break;
        }
    }

    public static byte[] compress(int commID) {
        ByteArray execDataOut = new ByteArray();
        switch (commID) {
            case 83886336:
                MainCanvas.chatSendString = MainCanvas.chatSendString.replace('\n', ' ').replace('\r', ' ');
                if (MainCanvas.chatSendString.trim().equals("")) {
                    break;
                }
                execDataOut.writeByte(MainCanvas.localChatChannel);
                execDataOut.writeInt(MainCanvas.chatFriendId);
                execDataOut.writeUTF(MainCanvas.chatSendString);
                if (MainCanvas.localChatChannel == 1) {
                    pushToChat(MainCanvas.chatName, MainCanvas.chatFriendId);
                    break;
                }
                if (MainCanvas.localChatChannel == 8) {
                    execDataOut.writeShort((short) MainCanvas.dramatisPackage.getCurrentPointer());
                }
                break;
            case 83886592:
                execDataOut.writeShort(Cons.channelOption);
                break;
        }
        return execDataOut.toByteArray();
    }

    public static void addChat(byte channel, String str) {
        if (str == null || str.equals("")) {
            return;
        }
        for (int i = MainCanvas.chatContent.length - 1; i > 0; i--) {
            MainCanvas.chatContent[i] = MainCanvas.chatContent[i - 1];
            MainCanvas.chatColor[i] = MainCanvas.chatColor[i - 1];
            MainCanvas.chatChannel[i] = MainCanvas.chatChannel[i - 1];
        }
        MainCanvas.chatContent[0] = str;
        MainCanvas.chatColor[0] = Cons.chatChannalColor[channel];
    }

    public static void addChatScreen(byte channel, String str) {
        MainCanvas.chatNowString = str;
        MainCanvas.chatNowChannel = channel;
        String[] tempStr = Util.wrapText(str, MainCanvas.screenW - 4, MainCanvas.font[1]);
        for (int i = 0; i < tempStr.length; i++) {
            UIGameRun.chatColorVector.addElement(new Byte(channel));
            UIGameRun.chatVector.addElement(tempStr[i]);
        }
    }

    public static void Logic_Trumpet() {
        if (bTrumpetDrawType == 1) {
            nTrumpet_Y -= 2;
        } else {
            nTrumpet_X -= 4;
        }
        if (System.currentTimeMillis() - L_TrumpetStartTime >= 15000L) {
            bTrumpetDrawType = 0;
            L_TrumpetStartTime = 0L;
        }
    }
}
