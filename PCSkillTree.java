public class PCSkillTree {
  public static boolean canUseNoCDRed = false;
  
  public static boolean canUseRed = true;
  
  public static boolean canUseBlue = true;
  
  public static boolean canUsePurple = true;
  
  public static void parse(int commID, byte[] data) {
      String hexID = Integer.toHexString(commID);
        System.out.println("[处理命令]:命令ID:0x" + hexID + ",命令类型：PCSkillTree");
    byte kind;
    int i;
    byte levels[], temp, res;
    String msg;
    ByteArray execDataIn = new ByteArray(data);
    switch (commID) {
      case Cmd.S_SKILL_SEND_INFOR:
        kind = (Player.getInstance()).profession;
        kind = (byte)(kind - 1);
        MainCanvas.mc.tree = new UISkillTree(0, 0, 160, 160, (Player.getInstance()).level);
        for (i = 0; i < 14; i++)
          MainCanvas.mc.tree.addCell(i, UISkillTree.SKILL_IMAGE[kind][i], execDataIn
              .readByte(), execDataIn
              
              .readByte(), execDataIn
              .readByte(), execDataIn
              .readByte(), UISkillTree.SKILL_TREE[kind][i], execDataIn
              
              .readByte()); 
        levels = MainCanvas.mc.tree.getAllSkillsLevel();
        if (levels.length == 14)
          Player.skillLevels = levels; 
        MainCanvas.mc.tree.setSkillPoints(execDataIn.readShort());
        MainCanvas.mc.tree.setArrowImage(MainCanvas.mImgUI[28]);
        MainCanvas.mc.tree.setSkillImage(MainCanvas.mImgUI[25]);
        MainCanvas.mc.tree.setBaseSkillPoint();
        MainCanvas.mc.releaseUI();
        temp = execDataIn.readByte();
        canUseRed = !((temp & 0x4) == 0);
        canUseBlue = !((temp & 0x2) == 0);
        canUsePurple = !((temp & 0x1) == 0);
        if (MainCanvas.mc.skillTreeFlag) {
          MainCanvas.mc.setGameState((byte)1);
          MainCanvas.mc.setRightMenuSubState(20);
          break;
        } 
        MainCanvas.mc.setGameState((byte)1);
        MainCanvas.mc.setRightMenuSubState(7);
        MainCanvas.mc.setUISetupState((byte)1);
        break;
      case 117441152:
        res = execDataIn.readByte();
        msg = null;
        if (res == 1) {
          msg = "升级技能成功！";
          MainCanvas.mc.tree.updateLevels();
        } else {
          msg = "升级技能失败！";
          MainCanvas.mc.tree.resetLevels();
        } 
        MainCanvas.mc.baseForm.setAboutForm(null);
        MainCanvas.mc.baseForm.addAboutForm("result", msg, (byte)1, 140, 50);
        break;
    } 
  }
  
  public static byte[] compress(int commID) {
    byte[] temp;
    int i;
    ByteArray execDataOut = new ByteArray();
    switch (commID) {
      case 117441024:
        temp = MainCanvas.mc.tree.getAllSkillsLevel();
        for (i = 0; i < temp.length; i++)
          execDataOut.writeByte(temp[i]); 
        execDataOut.writeShort(MainCanvas.mc.tree.getSkillPoints());
        break;
    } 
    return execDataOut.toByteArray();
  }
}
