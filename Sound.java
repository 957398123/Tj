import javax.microedition.media.Manager;
import javax.microedition.media.Player;

class Sound {
  static boolean isSoundOpen = true;
  
  public Player[] player = null;
  
  public Player solePlayer = null;
  
  public int[] priority;
  
  public int currentId;
  
  public int volume = 90;
  
  static final byte SOUND_MENU = 0;
  
  static final byte SOUND_RUN = 1;
  
  static final byte SOUND_OVER = 2;
  
  public Sound(String[] str, String[] type, int[] priority) {
    initSound(str, type, priority);
  }
  
  public void initSound(String[] str, String[] type, int[] priority) {
    if (str != null && type != null && str.length != type.length)
      return; 
    this.player = new Player[str.length];
    this.priority = priority;
    for (int i = 0; i < str.length; i++)
      this.player[i] = loadSound(str[i], type[i]); 
  }
  
  public void initSoleSound(String str, String type) {
    this.solePlayer = loadSound(str, type);
  }
  
  public Player loadSound(String name, String type) {
    Player sound = null;
    try {
      sound = Manager.createPlayer("".getClass().getResourceAsStream(name), type);
      sound.realize();
      sound.prefetch();
    } catch (Exception exception) {}
    return sound;
  }
  
  public void playSound(int id, int loop, boolean isInit) {
    try {
      if (!isSoundOpen)
        return; 
      if (this.player == null || this.player[id] == null)
        return; 
      if (this.currentId != -1)
        this.currentId = (this.player[this.currentId].getState() == 400) ? this.currentId : -1; 
      if (this.currentId != -1 && this.priority[id] <= this.priority[this.currentId])
        return; 
      stopSound(this.currentId);
      if (isInit)
        this.player[id].setMediaTime(0L); 
      this.player[id].setLoopCount(loop);
      this.player[id].start();
      this.currentId = id;
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public void playSoleSound(int loop, boolean isInit) {
    try {
      if (!isSoundOpen)
        return; 
      if (this.solePlayer == null)
        return; 
      this.solePlayer.stop();
      if (isInit)
        this.solePlayer.setMediaTime(0L); 
      this.solePlayer.setLoopCount(loop);
      this.solePlayer.start();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public void stopSound(int id) {
    try {
      if (id == -1)
        return; 
      if (this.player[id] != null)
        this.player[id].stop(); 
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public void stopAllSound() {
    try {
      for (int i = 0; i < this.player.length; i++) {
        if (this.player[i] != null)
          this.player[i].stop(); 
      } 
      if (this.solePlayer != null) {
        this.solePlayer.stop();
        this.solePlayer = null;
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
