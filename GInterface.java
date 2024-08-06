import java.io.DataOutputStream;
import java.util.Random;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;

public class GInterface {
  Random rand = new Random();
  
  private static final long SENDER_WAIT_TIME = 100L;
  
  static int firstResult;
  
  public static SocketConnection socket = null;
  
  private DataOutputStream socketOutputStream = null;
  
  private Sender sender = null;
  
  private Vector senderPool = new Vector();
  
  public byte checkoutID = -1;
  
  public boolean exit = false;
  
  private String gIP;
  
  private String gPort;
  
  private String gJumpId;
  
  private static GInterface instance = null;
  
  public int[] commands = new int[0];
  
  public boolean isSendingCommands = false;
  
  private GInterface() {
    this.rand.setSeed(System.currentTimeMillis());
  }
  
  public static GInterface getInstance() {
    if (instance == null)
      instance = new GInterface(); 
    return instance;
  }
  
  public int getSendNum() {
    return this.senderPool.size();
  }
  
  private synchronized boolean buildConn() {
    try {
      String url = "socket://" + this.gIP + ":" + this.gPort;
      socket = (SocketConnection)Connector.open(url, 3, false);
      socket.setSocketOption((byte)2, 0);
      socket.setSocketOption((byte)0, 10);
      socket.setSocketOption((byte)1, 5);
      this.socketOutputStream = socket.openDataOutputStream();
      this.sender = new Sender(this);
    } catch (Exception e) {
      if (this.senderPool != null)
        this.senderPool = new Vector(); 
      e.printStackTrace();
      return false;
    } 
    this.exit = false;
    return true;
  }
  
  public void closeConn() {
    try {
      if (this.sender != null) {
        this.sender.stop();
        this.sender = null;
      } 
      if (socket != null) {
        socket.close();
        socket = null;
      } 
      socket = null;
      if (this.socketOutputStream != null) {
        this.socketOutputStream.close();
        this.socketOutputStream = null;
      } 
      this.checkoutID = -1;
    } catch (Exception exception) {}
    this.exit = true;
  }
  
  public void send(int commID) {
    byte[] buf = null;
    System.out.println("gi send " + commID);
    buf = enCode(commID);
    this.senderPool.addElement(buf);
    synchronized (this) {
      if (socket == null)
        try {
          buildConn();
        } catch (Exception e) {
          e.printStackTrace();
        }  
    } 
  }
  
  public boolean sendInfo() {
    if (this.socketOutputStream == null)
      return true; 
    try {
      if (this.senderPool.size() != 0)
        while (this.senderPool.size() != 0) {
          byte[] tmpBuf = (byte[])senderPool.elementAt(0);
          if (tmpBuf != null)
            this.socketOutputStream.write(tmpBuf); 
          this.senderPool.removeElementAt(0);
        }  
      this.socketOutputStream.flush();
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    } 
    return true;
  }
  
  private byte[] enCode(int commID) {
    ByteArray dos = new ByteArray();
    if (commID == 1) {
      byte[] data = new byte[10];
      for (int i = 0; i < data.length; i++) {
        try {
          data[i] = (byte)Integer.parseInt(this.gJumpId);
        } catch (NumberFormatException e) {
          data[i] = 32;
          e.printStackTrace();
        } 
      } 
      dos.writeByteArray(data);
    } else {
      String needId = MainCanvas.userID;
      byte[] b = new byte[10];
      for (int i = 0; i < b.length; i++)
        b[i] = 32; 
      if (needId != null) {
        int userIDLen = (needId.getBytes()).length;
        if (userIDLen < b.length) {
          System.arraycopy(needId.getBytes(), 0, b, 0, userIDLen);
        } else {
          b = needId.getBytes();
        } 
        String s = "CMCCGAME_userId=" + new String(b);
        System.out.println("s = " + s);
        dos.writeByteArray(s.getBytes());
      } 
    } 
    return dos.toByteArray();
  }
  
  private class Sender implements Runnable {
    private Thread self;
    
    private final GInterface this$0;
    
    Sender(GInterface this$0) {
      this.this$0 = this$0;
      this.self = new Thread(this);
      this.self.start();
    }
    
    public void stop() {
      this.self = null;
    }
    
    public void run() {
      Thread currentThread = Thread.currentThread();
      while (this.self == currentThread) {
        try {
          if (!this.this$0.sendInfo())
            stop(); 
          synchronized (this) {
            wait(100L);
          } 
          Thread.yield();
        } catch (Exception e) {
          stop();
          e.printStackTrace();
        } 
      } 
      GInterface.getInstance().closeConn();
    }
  }
  
  public String getGIP() {
    return this.gIP;
  }
  
  public void setGIP(String ip) {
    this.gIP = ip;
  }
  
  public String getGPort() {
    return this.gPort;
  }
  
  public void setGPort(String port) {
    this.gPort = port;
  }
  
  public String getGJumpId() {
    return this.gJumpId;
  }
  
  public void setGJumpId(String jumpId) {
    this.gJumpId = jumpId;
  }
}
