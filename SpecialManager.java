import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

class SpecialManager {
  Vector v_special;
  
  public static short[][] frameDataSpecific;
  
  public static short[] picDatasSpecific;
  
  public static Image imageSpecific;
  
  public static short[][] motionDataAll;
  
  public static SpecialManager instance = null;
  
  public static SpecialManager getInstance() {
    if (instance == null)
      instance = new SpecialManager(); 
    return instance;
  }
  
  private SpecialManager() {
    this.v_special = null;
    this.v_special = new Vector();
    init();
  }
  
  public void init() {
    if (imageSpecific == null) {
      imageSpecific = Util.loadImage(Util.readPKG("/uiuse.pkg", "sp.png"));
      frameDataSpecific = Util.readFdatFile("/sp.fdat", 0);
      picDatasSpecific = Util.readPdatFile("/sp.pdat");
      motionDataAll = Util.readMdatFile("/sp.mdat");
    } 
  }
  
  public void draw(Graphics g) {
    if (this.v_special.size() == 0)
      return; 
    for (int i = 0; i < this.v_special.size(); i++) {
      Special tmpSpecial = (Special)this.v_special.elementAt(i);
      if (!tmpSpecial.isFinish()) {
        tmpSpecial.tick();
        tmpSpecial.draw(g);
      } else if (delSpecial(tmpSpecial)) {
        i--;
      } 
    } 
  }
  
  public boolean addSpecial(int _x, int _y, int _motionID) {
    boolean tmp = false;
    Special tmpSpecial = new Special(_x, _y, _motionID);
    this.v_special.addElement(tmpSpecial);
    tmp = true;
    return tmp;
  }
  
  public boolean addSpecial(GameObj _obj, int _motionID) {
    boolean tmp = false;
    Special tmpSpecial = new Special(ObjManager.getObj(_obj.objID), _motionID);
    this.v_special.addElement(tmpSpecial);
    tmp = true;
    return tmp;
  }
  
  private boolean delSpecial(Special obj) {
    return this.v_special.removeElement(obj);
  }
}
