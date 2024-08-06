import javax.microedition.lcdui.Graphics;

public class Special {
  public int x;
  
  public int y;
  
  public int curMotionIndex;
  
  public byte curFrameIndex;
  
  public GameObj speciaObj;
  
  public Special(int _x, int _y, int _motionID) {
    this.x = _x;
    this.y = _y;
    this.curMotionIndex = _motionID;
    this.curFrameIndex = 0;
  }
  
  public Special(GameObj _obj, int _motionID) {
    this.speciaObj = _obj;
    this.curMotionIndex = _motionID;
    this.curFrameIndex = 0;
  }
  
  public void tick() {
    nextFrame();
  }
  
  public void nextFrame() {
    this.curFrameIndex = (byte)(this.curFrameIndex + 1);
  }
  
  boolean isFinish() {
    if (this.curFrameIndex >= (SpecialManager.motionDataAll[this.curMotionIndex]).length - 1)
      return true; 
    return false;
  }
  
  public void draw(Graphics g) {
    if (Cons.showSpecial)
      if (this.speciaObj == null) {
        Util.drawRoleEditFrame(SpecialManager.imageSpecific, g, SpecialManager.frameDataSpecific[SpecialManager.motionDataAll[this.curMotionIndex][this.curFrameIndex]], SpecialManager.picDatasSpecific, this.x - Map.currentWindowX, this.y - Map.currentWindowY, false);
      } else {
        Util.drawRoleEditFrame(SpecialManager.imageSpecific, g, SpecialManager.frameDataSpecific[SpecialManager.motionDataAll[this.curMotionIndex][this.curFrameIndex]], SpecialManager.picDatasSpecific, this.speciaObj.x - Map.currentWindowX, this.speciaObj.y - Map.currentWindowY, false);
      }  
  }
}
