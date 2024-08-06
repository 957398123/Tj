class Sender implements Runnable {
  private Thread self;
  
  private final GInterface this$0;
  
  Sender(GInterface paramGInterface) {
    this.this$0 = paramGInterface;
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
