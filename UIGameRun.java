import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class UIGameRun {
  private static Image[] creatImage;
  
  private static short[][][] creatFrameData;
  
  private static short[][] creatPicData;
  
  private static short[][][] creatMotionDataAll;
  
  public static int creatFrameIndex;
  
  public static Image encryptImg = null;
  
  public static final int getWait() {
    try {
      if (encryptImg == null)
        encryptImg = Util.loadImage(Util.readPKG("/uimenu.pkg", "test.png")); 
      return encryptImg.getWidth() + 110;
    } catch (Exception e) {
      MainCanvas.mc.aMidlet.destroyApp(true);
      return encryptImg.getWidth() + 110;
    } 
  }
  
  private static UIGameRun instance = null;
  
  public static Image imgSkillCannotUse;
  
  public static UIGameRun getInstance() {
    if (instance == null)
      instance = new UIGameRun(); 
    return instance;
  }
  
  public static void releaseSource() {
    instance = null;
  }
  
  public static void releaseChat() {
    beginChat();
  }
  
    public void drawHPMPBar(Graphics g, GameObj obj, int startX, int startY, boolean isTeam) {
        if (obj.type == 5) {
            String tmpStr = "";
            switch (obj.imgID) {
                case 0:
                    tmpStr = tmpStr + "矿类";
                    break;
                case 1:
                    tmpStr = tmpStr + "木类";
                    break;
                case 2:
                    tmpStr = tmpStr + "草类";
                    break;
                case 3:
                    tmpStr = tmpStr + "皮类";
            }

            tmpStr = tmpStr + obj.level + "级";
            g.setColor(0);
            g.drawString(tmpStr, MainCanvas.screenW - g.getFont().stringWidth(tmpStr) - 11, 12, 0);
            g.drawString(tmpStr, MainCanvas.screenW - g.getFont().stringWidth(tmpStr) - 10, 13, 0);
            g.setColor(16777173);
            g.drawString(tmpStr, MainCanvas.screenW - g.getFont().stringWidth(tmpStr) - 10, 12, 0);
        } else {
            byte barLen = 44;
            if (isTeam) {
                barLen = 19;
            }

            int hpBarY = startY + 3;
            int mpBarY = startY + 7;
            int maxHp = obj.maxHp;
            int hp = obj.curHp;
            int mp = obj.curMp;
            int maxMp = obj.maxMp;
            if (isTeam) {
                Util.drawRegion(g, MainCanvas.mImgUI[24].img, 0, 0, 40, MainCanvas.mImgUI[24].img.getHeight(), startX, startY, 0);
                Util.drawRegion(g, MainCanvas.mImgUI[24].img, MainCanvas.mImgUI[24].img.getWidth() - 6, 0, 6, MainCanvas.mImgUI[24].img.getHeight(), startX + 40, startY, 0);
            } else {
                MainCanvas.mImgUI[24].draw(g, startX, startY, 20, 0);
            }

            if (obj.type == 2) {
                MainCanvas.mImgUI[32].draw(g, startX + 3, startY + 4, 9, false);
                switch (obj.eliteType) {
                    case 0:
                        MainCanvas.mImgUI[32].draw(g, startX + 3, startY + 4, 9, false);
                        break;
                    case 1:
                        MainCanvas.mImgUI[32].draw(g, startX + 3, startY + 4, 10, false);
                        break;
                    case 2:
                        MainCanvas.mImgUI[32].draw(g, startX + 3, startY + 4, 11, false);
                        break;
                    case 3:
                        MainCanvas.mImgUI[32].draw(g, startX + 3, startY + 4, 12, false);
                        break;
                    default:
                        MainCanvas.mImgUI[32].draw(g, startX + 3, startY + 4, 9, false);
                }
            } else if (obj.type == 3) {
                MainCanvas.mImgUI[32].draw(g, startX + 3, startY + 4, 13, false);
            } else {
                MainCanvas.mImgUI[32].draw(g, startX + 3, startY + 4, obj.originalImgID, false);
            }

            if (obj.objID == MainCanvas.mc.teamLeaderId) {
                MainCanvas.mImgUI[34].draw(g, startX, startY, 0, false);
            }

            if (obj != Player.getInstance() && obj.type != 3 && obj.type != 1) {
                if (obj.type == 2 || obj.type == 1 && obj.group != 0 && Player.getInstance().group != obj.group) {
                    if (obj.level - Player.getInstance().level < 8 && obj.level >= 0) {
                        if (obj.level >= 10) {
                            MainCanvas.mImgUI[12].draw(g, startX + 23, startY + 11, obj.level / 10, false);
                            MainCanvas.mImgUI[12].draw(g, startX + 28, startY + 11, obj.level % 10, false);
                        } else {
                            MainCanvas.mImgUI[12].draw(g, startX + 25, startY + 11, obj.level % 10, false);
                        }
                    } else {
                        MainCanvas.mImgUI[36].draw(g, startX + 22, startY + 10, 0, false);
                    }
                }
            } else if (obj.level >= 10) {
                MainCanvas.mImgUI[12].draw(g, startX + 23, startY + 11, obj.level / 10, false);
                MainCanvas.mImgUI[12].draw(g, startX + 28, startY + 11, obj.level % 10, false);
            } else {
                MainCanvas.mImgUI[12].draw(g, startX + 25, startY + 11, obj.level % 10, false);
            }

            int disHpBarLen;
            if (maxHp == 0) {
                disHpBarLen = barLen;
            } else {
                disHpBarLen = (maxHp - hp) * 100 / maxHp * barLen / 100;
            }

            int disMpBarLen;
            if (maxMp == 0) {
                disMpBarLen = barLen;
            } else {
                disMpBarLen = (maxMp - mp) * 100 / maxMp * barLen / 100;
            }

            g.setColor(3879194);
            g.fillRect(startX + 24 + barLen - disHpBarLen, hpBarY, disHpBarLen, 3);
            if (obj.type == 4 || obj.type == 1) {
                g.fillRect(startX + 24 + barLen - disMpBarLen, mpBarY - 1, disMpBarLen, 3);
            }

            if (!isTeam) {
                this.drawBuffer(g, obj, startX + 22, startY + 24);
            }

        }
    }
  
  public void drawLetterInfo(Graphics g) {
    if (MainCanvas.isHaveNewMail)
      MainCanvas.mImgUI[13].draw(g, 2, 221, 0, false); 
  }
  
  public void drawFinishedTask(Graphics g) {
    if (MainCanvas.isHaveFinishedTask)
      MainCanvas.mImgUI[13].draw(g, 19, 221, 4, false); 
  }
  
  public void drawAttributes(Graphics g) {
    if (MainCanvas.isHaveAttributes)
      MainCanvas.mImgUI[13].draw(g, 36, 221, 3, false); 
  }
  
  public void drawArenaStatus(Graphics g) {
    switch (MainCanvas.mc.arenaStatus) {
      case 1:
        MainCanvas.mImgUI[13].draw(g, 53, 221, 5, false);
        break;
      case 2:
        MainCanvas.mImgUI[13].draw(g, 53, 221, 6, false);
        break;
    } 
  }
  
  public void drawBuffer(Graphics g, GameObj obj, int startX, int startY) {
    int bufferNum = 0;
    for (int i = 0, kk = obj.bufferState.length; i < kk; i++) {
      if (obj.bufferState[i]) {
        MainCanvas.mImgUI[33].draw(g, startX + (MainCanvas.mImgUI[33]).frame_w * bufferNum % 4, startY + ((MainCanvas.mImgUI[33]).frame_w + 1) * (bufferNum >> 2), Cons.BUFFER_SKETCH_MAP[i], false);
        bufferNum++;
      } 
    } 
  }
  
  public void drawExpBar(Graphics g) {
    if (!Cons.showExpBar)
      return; 
    int XP_START_Y = MainCanvas.screenH - 25;
    short XP_START_X = 0;
    short XP_BAR_H = 4;
    short XP_BAR_W = (short)MainCanvas.screenW;
    short XP_BLANK_W = 2;
    g.setColor(0);
    g.fillRect(0, XP_START_Y, XP_BAR_W, 4);
    g.setColor(8415039);
    g.drawRect(0, XP_START_Y, XP_BAR_W - 1, 4);
    g.setColor(16777215);
    long w = 0L;
    int exp = (Player.getInstance()).exp;
    int maxXp = (Player.getInstance()).maxExp;
    if (maxXp == 0) {
      w = 0L;
    } else {
      w = exp;
      w *= (XP_BAR_W - 4);
      w /= maxXp;
    } 
    g.fillRect(2, XP_START_Y + 2, (int)w, 1);
    g.setColor(8415039);
    for (int i = 1; i < 10; i++)
      g.fillRect(-2 + XP_BAR_W / 10 * i, XP_START_Y + 1, 1, 3); 
  }
  
  /**
   * 绘制技能条
   * @param g
   * @param dy
   * @param drawCanUse 
   */
  public static void drawShortcutBar(Graphics g, int dy, boolean drawCanUse) {
    int barH = 21;
    if (Cons.showShortCut || MainCanvas.mc
      .getRightMenuSubState() == 7) {
      UIRim.drawRim(g, 0, dy - 21, MainCanvas.screenW, 21, (byte)6);
      g.setColor(16716593);
      for (int i = 1; i < 9; i++) {
        int imgId, w = 20 * UIComponent.CURR_W / 176;
        if (w > 20)
          w++; 
        int startX = 11 * UIComponent.CURR_W / 176;
        int x = startX + w * (i - 1);
        int y = dy - 21 + 2;
        int skillIndex = Player.userDefinedSkills[i];
        int profession = (Player.getInstance()).profession - 1;
        if (skillIndex == 0) {
          imgId = 36;
        } else if (skillIndex == -1) {
          imgId = -1;
        } else if (skillIndex > 14) {
          imgId = 33 + skillIndex - 15;
        } else {
          imgId = UISkillTree.SKILL_IMAGE[profession][skillIndex - 1];
        } 
        if (imgId != -1)
          if (skillIndex > 14) {
            MainCanvas.mImgStuff.draw(g, x, y, imgId, false);
          } else {
            MainCanvas.mImgUI[25].draw(g, x, y, imgId, false);
          }  
        MainCanvas.mImgUI[12].draw(g, x + 1, y + 1, i, false);
        if (drawCanUse)
          if (!Player.canUseSkill[i]) {
            if (imgSkillCannotUse == null)
              imgSkillCannotUse = Util.loadImage(Util.readPKG("/uiuse.pkg", "skillpanle.png")); 
            g.drawImage(imgSkillCannotUse, x, y + 1, 20);
          }  
      } 
    } else {
      g.setColor(0);
      g.fillRect(0, MainCanvas.screenH - 21, MainCanvas.screenW, 21);
    } 
  }
  
  public void drawTeamMate(Graphics g) {
    if (!Cons.showTeamMate)
      return; 
    if (MainCanvas.mc.teamMates != null) {
      String tempName = "";
      if (MainCanvas.mc.teamMates.size() > 0)
        for (int i = 0; i < MainCanvas.mc.teamMates.size(); i++) {
          GameObj ob = (GameObj)MainCanvas.mc.teamMates.elementAt(i);
          drawHPMPBar(g, ob, 0, 47 * (i + 1), true);
          tempName = ob.name;
          if (ob.name.length() > 3)
            tempName = ob.name.substring(0, 2) + "..."; 
          g.setColor(0);
          g.drawString(tempName, 5, 33 + 47 * i, 20);
          g.setColor(16777173);
          g.drawString(tempName, 4, 32 + 47 * i, 20);
        }  
    } 
  }
  
  public static void draw(Graphics g) {
    getInstance().drawAll(g);
  }
  
  public void drawAll(Graphics g) {
    for (int i = 0; i < ObjManager.vectorObj.size(); i++)
      ((GameObj)ObjManager.vectorObj.elementAt(i)).drawHpChange(g); 
    drawHPMPBar(g, Player.getInstance(), 25, 0, false);
    drawLetterInfo(g);
    drawFinishedTask(g);
    drawAttributes(g);
    drawArenaStatus(g);
    if (Player.getInstance() != null && ObjManager.showTarget == null) {
      String tmpStr = Map.currentMapName + " (" + (Player.getInstance()).col + ", " + (Player.getInstance()).row + ")";
      g.setColor(0);
      g.drawString(tmpStr, MainCanvas.screenW - g
          .getFont().stringWidth(tmpStr) - 2 + 1, 13, 0);
      g.drawString(tmpStr, MainCanvas.screenW - g
          .getFont().stringWidth(tmpStr) - 2, 13, 0);
      g.setColor(16777173);
      g.drawString(tmpStr, MainCanvas.screenW - g
          .getFont().stringWidth(tmpStr) - 2, 12, 0);
    } 
    if (ObjManager.showTarget != null)
      drawHPMPBar(g, ObjManager.showTarget, 162, 0, false); 
    drawTeamMate(g);
    drawTrumpetMessage(g);
    UIRim.drawRim(g, 0, MainCanvas.screenH - 21, MainCanvas.screenW, 21, (byte)6);
    drawSmallMap(g);
    drawChatBar(g);
    drawShortcutBar(g, MainCanvas.screenH + 2, true);
    drawExpBar(g);
    if (Cons.zeroShortShow >= 0) {
      String show1 = "";
      String show2 = "";
      String show3 = "";
      switch (Cons.zeroShort) {
        case 0:
          show1 = "< 高画质 >";
          show2 = "显示玩家，组队信息和小地图";
          break;
        case 1:
          show1 = "< 中画质 >";
          show2 = "不显示周围玩家的名字";
          break;
        case 2:
          show1 = "< 低画质 >";
          show2 = "不显示玩家，组队信息等";
          break;
      } 
      g.setColor(0);
      g.drawString(show1, (MainCanvas.screenW >> 1) + 1, 21, 17);
      g.drawString(show2, (MainCanvas.screenW >> 1) + 1, 37, 17);
      if (show3 != "")
        g.drawString(show3, (MainCanvas.screenW >> 1) + 1, 53, 17); 
      g.setColor(16777215);
      g.drawString(show1, MainCanvas.screenW >> 1, 20, 17);
      g.drawString(show2, MainCanvas.screenW >> 1, 36, 17);
      if (show3 != "")
        g.drawString(show3, MainCanvas.screenW >> 1, 52, 17); 
      Cons.zeroShortShow = (short)(Cons.zeroShortShow + 1);
      if (Cons.zeroShortShow > 30)
        Cons.zeroShortShow = -1; 
    } 
  }
  
  public char[] chatCharArray = null;
  
  public static int chatCounter = 10;
  
  public static boolean isInit = true;
  
  public byte rows = 0;
  
  public static Vector chatVector = new Vector();
  
  public static Vector chatColorVector = new Vector();
  
  public static final int CHAT_DELAY_TIME = 10;
  
  public void drawChatString(Graphics g, String str, byte color, int x, int y) {
    g.setColor(Cons.chatChannalColor[color]);
    int offsetX = x;
    this.chatCharArray = str.toCharArray();
    int lg = this.chatCharArray.length;
    for (int i = 0; i < lg; i++) {
      if (i != lg - 1 && this.chatCharArray[i] == '#' && this.chatCharArray[i + 1] >= '0' && this.chatCharArray[i + 1] <= '9') {
        MainCanvas.mImgUI[31].draw(g, offsetX, y, this.chatCharArray[i + 1] - 48, false);
        i++;
        offsetX += (MainCanvas.mImgUI[31]).frame_w;
      } else {
        g.drawChar(this.chatCharArray[i], offsetX, y, 20);
        offsetX += g.getFont().charWidth(this.chatCharArray[i]);
      } 
    } 
  }
  
  public static void tickChatBar() {
    chatCounter--;
    if (chatCounter < 0) {
      chatCounter = 10;
      if (chatVector.size() > 3) {
        chatVector.removeElementAt(0);
        chatColorVector.removeElementAt(0);
      } 
    } 
  }
  
  public void drawChatBar(Graphics g) {
    int fontH = 20;
    int shortbarH = 25;
    int firstZi = MainCanvas.screenH - fontH * 3 - shortbarH;
    g.setColor(0);
    g.fillRect(0, firstZi, MainCanvas.screenW, shortbarH * 3);
    g.setColor(8415039);
    g.drawRect(0, firstZi, MainCanvas.screenW - 1, fontH * 3 + 1);
    g.setColor(3681047);
    g.drawLine(0, firstZi + fontH, MainCanvas.screenW, firstZi + fontH);
    g.drawLine(0, firstZi + fontH * 2, MainCanvas.screenW, firstZi + fontH * 2);
    if (chatVector.size() > 0) {
      drawChatString(g, (String)chatVector.elementAt(0), ((Byte)chatColorVector
          .elementAt(0)).byteValue(), 2, firstZi + 2);
      if (chatVector.size() > 1) {
        drawChatString(g, (String)chatVector.elementAt(1), ((Byte)chatColorVector
            .elementAt(1)).byteValue(), 2, firstZi + fontH + 2);
        if (chatVector.size() > 2)
          drawChatString(g, (String)chatVector.elementAt(2), ((Byte)chatColorVector
              .elementAt(2)).byteValue(), 2, firstZi + fontH * 2 + 2); 
      } 
    } 
  }
  
  public static void beginChat() {
    chatCounter = 10;
    isInit = true;
  }
  
  private static final int MAP_START_X = MainCanvas.screenW - 31;
  
  private static final int MAP_START_Y = MainCanvas.screenH - 105;
  
  private static final int MAP_WIDTH = 50;
  
  private static final int MAP_HEIGHT = 25;
  
  int startX = MAP_START_X;
  
  int startY = MAP_START_Y;
  
  int tileWidth = 0;
  
  int tileHeight = 0;
  
  int mapWidth = 0;
  
  int mapHeight = 0;
  
  int mapCols = 0;
  
  int mapRows = 0;
  
  public static void init() {
    getInstance().initSmallMap();
  }
  
  public void initSmallMap() {
    this.mapCols = Map.currentTotalColumn;
    this.mapRows = Map.currentTotalRow;
    if (this.mapCols == 0)
      return; 
    if (this.mapRows == 0)
      return; 
    if (this.mapCols > this.mapRows) {
      this.mapWidth = 50;
      this.tileWidth = this.mapWidth * 1000;
      this.tileWidth /= this.mapCols;
      this.tileHeight = this.tileWidth >> 1;
      this.mapHeight = this.tileHeight * this.mapRows;
      this.mapHeight /= 1000;
    } else {
      this.mapHeight = 25;
      this.tileHeight = this.mapHeight * 1000;
      this.tileHeight /= this.mapRows;
      this.tileWidth = this.tileHeight * 2;
      this.mapWidth = this.tileWidth * this.mapCols;
      this.mapWidth /= 1000;
    } 
    this.startX = MAP_START_X - (this.mapWidth >> 1);
    this.startY = MAP_START_Y - (this.mapHeight >> 1);
  }
  
  private int getMapX(int col, int row) {
    return (col - row) * this.tileWidth / 2000 + (this.mapWidth >> 1) + this.startX;
  }
  
  private int getMapY(int col, int row) {
    return (col + row) * this.tileHeight / 2000 + this.startY;
  }
  
  private void drawSmallMap(Graphics g) {
    if (!Cons.showSmallMap)
      return; 
    int[][] points = { { 0, 0 }, { this.mapCols, 0 }, { this.mapCols, this.mapRows }, { 0, this.mapRows } };
    int[] xPoints = new int[4];
    int[] yPoints = new int[4];
    for (int i = 0; i < 4; i++) {
      xPoints[i] = getMapX(points[i][0], points[i][1]);
      yPoints[i] = getMapY(points[i][0], points[i][1]);
    } 
    g.setColor(16777173);
    g.drawLine(xPoints[0], yPoints[0] + 1, xPoints[1] - 1, yPoints[1]);
    g.drawLine(xPoints[1] - 1, yPoints[1], xPoints[2], yPoints[2] - 1);
    g.drawLine(xPoints[2], yPoints[2] - 1, xPoints[3] + 1, yPoints[3]);
    g.drawLine(xPoints[0], yPoints[0] + 1, xPoints[3] + 1, yPoints[3]);
    if (MainCanvas.isLogin)
      drawDoors(g); 
    drawFlags(g);
    drawTeamMates(g);
  }
  
  private void drawDoors(Graphics g) {
    g.setColor(16524288);
    for (int i = 0, kk = Map.curPointArray.length; i < kk; i++) {
      int col = Map.curPointArray[i][0];
      int row = Map.curPointArray[i][1];
      if (col != -1 || row != -1)
        g.fillRect(getMapX(col, row) - 1, getMapY(col, row) - 1, 3, 3); 
    } 
  }
  
  private void drawTeamMates(Graphics g) {
    g.setColor(130823);
    int col = (Player.getInstance()).col;
    int row = (Player.getInstance()).row;
    g.fillRect(getMapX(col, row), getMapY(col, row), 3, 3);
    g.setColor(87551);
    if (MainCanvas.mc.teamMates != null)
      for (int i = 0; i < MainCanvas.mc.teamMates.size(); i++) {
        GameObj ob = (GameObj)MainCanvas.mc.teamMates.elementAt(i);
        drawOneTeamMate(g, ob);
      }  
  }
  
  private void drawOneTeamMate(Graphics g, GameObj player) {
    if (player == null)
      return; 
    int col = player.col;
    int row = player.row;
    if (col < 0 || row < 0)
      return; 
    g.fillRect(getMapX(col, row), getMapY(col, row), 2, 2);
  }
  
  byte msgNum = 0;
  
  public void drawWaitResInit(Graphics g) {
    this.msgNum = 3;
    MainCanvas.drawGroundback(g);
    drawLoadingTip(g);
    drawTJ(g, this.msgNum, MainCanvas.loadCount);
  }
  
  boolean isExitTip = false;
  
  public void updateMapCount() {
    switch (MainCanvas.loadCount) {
      case 0:
        MainCanvas.templevel = 0;
        if (MainCanvas.mc.topForm != null)
          MainCanvas.mc.topForm = null; 
        if (this.tempTip != null && !this.tempTip.equals(""))
          if (PCChangeMap.isCounterpart && loadingTip != null && 
            !"".equals(loadingTip)) {
            this.drawTip = loadingTip;
            this.isExitTip = true;
          } else if (this.isExitTip) {
            if (loadingTip != null && !"".equals(loadingTip)) {
              this.drawTip = loadingTip;
            } else {
              this.drawTip = this.tempTip = "欢迎来到天劫！";
            } 
            this.isExitTip = false;
          } else {
            this.drawTip = this.tempTip;
          }  
        MainCanvas.mc.setNPCSubState((byte)0);
        break;
      case 21:
        if (PCChangeMap.isReceivedMapInfo) {
          PCChangeMap.isReceivedMapInfo = false;
          MainCanvas.mc.setOtherSubState((byte)2);
          PCChangeMap.creatNpcAndEnemyArray(PCChangeMap.npcNum, PCChangeMap.enemyNum);
          break;
        } 
        return;
      case 23:
        if (PCChangeMap.isReceivedMapData == 0) {
          PCChangeMap.isReceivedMapData = 3;
          MainCanvas.ni.send(536871680);
        } 
        break;
      case 25:
        if (PCChangeMap.nextMapType == 1) {
          if (Map.curMapType == -1) {
            GameObj.initWhitePeopleRes();
            break;
          } 
          if (Map.curMapType == 1)
            break; 
          if (Map.curMapType == 2)
            break; 
          if (Map.curMapType == 3)
            GameObj.releaseBlackRes(); 
          break;
        } 
        if (PCChangeMap.nextMapType == 2) {
          if (Map.curMapType == -1) {
            GameObj.initBlackPeopleRes();
            break;
          } 
          if (Map.curMapType == 1)
            break; 
          if (Map.curMapType == 2)
            break; 
          if (Map.curMapType == 3)
            GameObj.releaseWhiteRes(); 
          break;
        } 
        if (PCChangeMap.nextMapType == 3) {
          if (Map.curMapType == -1) {
            GameObj.initWhitePeopleRes();
            GameObj.initBlackPeopleRes();
            break;
          } 
          if (Map.curMapType == 1) {
            GameObj.initBlackPeopleRes();
            break;
          } 
          if (Map.curMapType == 2) {
            GameObj.initWhitePeopleRes();
            break;
          } 
          if (Map.curMapType == 3);
        } 
        break;
      case 28:
        if (PCChangeMap.isReceivedMapData == 1 || PCChangeMap.isReceivedMapData == 2) {
          Map.getInstance().initMap(PCChangeMap.nextMapID, PCChangeMap.isReceivedMapData);
          PCChangeMap.isReceivedMapData = 0;
          break;
        } 
        MainCanvas.loadCount--;
        break;
      case 29:
        GameObj.initMosterRES(PCChangeMap.arrEnemy);
        break;
      case 32:
        Map.curMapType = PCChangeMap.nextMapType;
        MainCanvas.ni.send(536871424);
        break;
      case 33:
        if (!MainCanvas.isFirst[MainCanvas.choose_manID])
          Map.isDrawPlaceName = true; 
        loadingTip = null;
        PCChangeMap.isParseResMsg = false;
        Map.drawPlaceNameCount = 0;
        Map.colorIndex = 0;
        MainCanvas.loadRequestCount = 0;
        MainCanvas.isDeadLoad = false;
        break;
    } 
    if (MainCanvas.loadCount <= this.msgNum) {
      MainCanvas.loadCount++;
    } else if (PCChangeMap.isParseResMsg) {
      MainCanvas.loadCount++;
    } 
  }
  
  private void drawTJ(Graphics g, int aOffset, int aWaitCnt) {
    short tjPicX = (short)(MainCanvas.screenW >> 1);
    short tjPicY = 33;
    short tjStringY = (short)(MainCanvas.screenH - 28);
    g.setColor(11898450);
    g.drawString("载入中... " + (aWaitCnt * 3 + 1) + "%", MainCanvas.screenW >> 1, tjStringY, 17);
    if (MainCanvas.loadCount - aOffset >= 0)
      if (MainCanvas.loadCount - aOffset < (MainCanvas.tjMotionDataAll[0]).length - 1) {
        if (!PCChangeMap.isCounterpart)
          Util.drawRoleEditFrame(MainCanvas.tjImage, g, MainCanvas.tjFrameData[MainCanvas.tjMotionDataAll[0][MainCanvas.loadCount - aOffset]], MainCanvas.tjPicData, tjPicX, tjPicY, false); 
      } else if (!PCChangeMap.isCounterpart) {
        Util.drawRoleEditFrame(MainCanvas.tjImage, g, MainCanvas.tjFrameData[MainCanvas.tjMotionDataAll[0][(MainCanvas.tjMotionDataAll[0]).length - 1]], MainCanvas.tjPicData, tjPicX, tjPicY, false);
      }  
  }
  
  public void drawWaitFirstResInit(Graphics g) {
    int i;
    g.setColor(0);
    g.fillRect(0, 0, MainCanvas.screenW, MainCanvas.screenH);
    switch (MainCanvas.loadCount) {
      case 0:
        MainCanvas.mc.loadResource();
        break;
      case 2:
        GameObj.imgShadowImmortal = Util.loadImage(Util.readPKG("/uiuse.pkg", "qqsado.png"));
        break;
      case 4:
        GameObj.initSpecificAndEqument();
        break;
      case 5:
        try {
          MainCanvas.waitImg = Image.createImage("/circle.png");
        } catch (IOException iOException) {}
        break;
      case 6:
        GameObj.initNpcRes();
        break;
      case 8:
        MainCanvas.mImgUI[1] = new MImage("ui1.png", "/uiuse.pkg", 7, 7);
        MainCanvas.mImgUI[2] = new MImage("ui2.png", "/uiuse.pkg", 10, 10);
        MainCanvas.mImgUI[3] = new MImage("ui3.png", "/uiuse.pkg", 12, 12);
        MainCanvas.mImgUI[4] = new MImage("ui4.png", "/uiuse.pkg", 38, 18);
        MainCanvas.mImgUI[5] = null;
        break;
      case 10:
        MainCanvas.mImgUI[6] = new MImage("ui6.png", "/uiuse.pkg", 104, 1);
        MainCanvas.mImgUI[7] = new MImage("ui7.png", "/uiuse.pkg", 1, 74);
        MainCanvas.mImageTitle = new MImage("uititle.png", "/uimenu.pkg", 240, 146);
        MainCanvas.mImageC = new MImage("uic.png", "/uimenu.pkg", 84, 9);
        break;
      case 11:
        MainCanvas.mImgUI[8] = new MImage("ui8.png", "/uiuse.pkg", 7, 6);
        MainCanvas.mImgUI[9] = new MImage("ui9.png", "/uiuse.pkg", 79, 24);
        MainCanvas.mImgUI[10] = new MImage("ui10.png", "/uiuse.pkg", 14, 14);
        MainCanvas.mImgUI[11] = new MImage("ui11.png", "/uiuse.pkg", 9, 9);
        MainCanvas.mImgUI[12] = new MImage("ui12.png", "/uiuse.pkg", 5, 7);
        MainCanvas.mImgUI[13] = new MImage("ui13.png", "/uiuse.pkg", 15, 12);
        break;
      case 14:
        MainCanvas.mImgUI[14] = new MImage("s.png", "/uiuse.pkg", 9, 9);
        MainCanvas.mImgUI[15] = new MImage("ui15.png", "/uiuse.pkg", 17, 17);
        MainCanvas.mImgUI[16] = null;
        MainCanvas.mImgUI[17] = new MImage("ui17.png", "/uiuse.pkg", 10, 9);
        MainCanvas.mImgUI[19] = new MImage("ui19.png", "/uiuse.pkg", 45, 35);
        break;
      case 16:
        getInstance().initCreatRes();
        break;
      case 17:
        MainCanvas.mImgUI[20] = new MImage("ui20.png", "/uiuse.pkg", 16, 16);
        MainCanvas.mImgUI[21] = new MImage("ui21.png", "/uiuse.pkg", 10, 10);
        MainCanvas.mImgUI[22] = new MImage("ui22.png", "/uiuse.pkg", 4, 5);
        MainCanvas.mImgUI[23] = new MImage("ui23.png", "/uiuse.pkg", 5, 4);
        MainCanvas.mImgUI[24] = new MImage("ui24.png", "/uiuse.pkg", 79, 24);
        MainCanvas.mImgUI[25] = new MImage("ui25.png", "/uiuse.pkg", 16, 16);
        break;
      case 19:
        MainCanvas.mImgUI[26] = new MImage("ui26.png", "/uiuse.pkg", 14, 14);
        MainCanvas.mImgUI[27] = new MImage("ui27.png", "/uiuse.pkg", 7, 7);
        MainCanvas.mImgUI[28] = new MImage("ui28.png", "/uiuse.pkg", 10, 10);
        MainCanvas.mImgUI[29] = new MImage("ui29.png", "/uiuse.pkg", 5, 5);
        break;
      case 22:
        MainCanvas.mImgUI[31] = new MImage("ui31.png", "/uiuse.pkg", 12, 12);
        MainCanvas.mImgUI[32] = new MImage("ui32.png", "/uiuse.pkg", 17, 16);
        MainCanvas.mImgUI[33] = new MImage("ui33.png", "/uiuse.pkg", 12, 12);
        MainCanvas.mImgUI[34] = new MImage("ui34.png", "/uiuse.pkg", 9, 7);
        MainCanvas.mImgUI[35] = new MImage("ui35.png", "/uiuse.pkg", 8, 15);
        MainCanvas.mImgUI[36] = new MImage("ui36.png", "/uiuse.pkg", 11, 11);
        MainCanvas.mImgUI[37] = new MImage("ui37.png", "/uiuse.pkg", 27, 10);
        break;
      case 24:
        MainCanvas.mImgUI[38] = new MImage("ui38.png", "/uiuse.pkg", 20, 18);
        MainCanvas.mImgUI[39] = new MImage("ui39.png", "/uiuse.pkg", 5, 5);
        MainCanvas.mImgStuff = new MImage("stuff.png", "/uiuse.pkg", 16, 16);
        MainCanvas.mImgSelect = new MImage("select.png", "/uiuse.pkg", 25, 15);
        MainCanvas.imgRedNum = new MImage("redNum.png", "/uiuse.pkg", 7, 10);
        MainCanvas.imgWhiteNum = new MImage("whiteNum.png", "/uiuse.pkg", 10, 9);
        break;
      case 26:
        MainCanvas.imgGreenNum = new MImage("greenNum.png", "/uiuse.pkg", 7, 10);
        MainCanvas.imgYellowNum = new MImage("yellowNum.png", "/uiuse.pkg", 13, 18);
        MainCanvas.imgPlayerArrow = new MImage("playerArrow.png", "/uiuse.pkg", 16, 21);
        MainCanvas.imgMarryArrow = new MImage("marry.png", "/uiuse.pkg", 14, 13);
        break;
      case 28:
        MainCanvas.mImageMenu = null;
        MainCanvas.mImageArrow = new MImage("uiarrow.png", "/uimenu.pkg", 11, 9);
        break;
      case 30:
        for (i = 0; i < MainCanvas.star.length; i++) {
          MainCanvas.star[i][0] = 5 + 
            Util.getRandom(MainCanvas.screenW - 10);
          MainCanvas.star[i][1] = 10 + Util.getRandom(20);
          MainCanvas.star[i][2] = Util.getRandom(4) + 2;
          MainCanvas.star[i][3] = MainCanvas.fireColor[Util.getRandom(4)];
          MainCanvas.star[i][4] = Util.getRandom(20);
        } 
        break;
      case 31:
        MainCanvas.dramatisPackage = new UIGrid(0, 113, (byte)4, (byte)9, (byte)4, MainCanvas.mImgStuff);
        break;
      case 32:
        if (PCIncrementService.isChargeJar) {
          if (PCIncrementService.hasAgree) {
            MainCanvas.mc.setState((byte)26);
            break;
          } 
          MainCanvas.mc.getSMS_Content(16);
          MainCanvas.mc.setState((byte)31);
          PCIncrementService.getInstance().getClass();
          (PCIncrementService.getInstance()).sendState = 3;
          break;
        } 
        MainCanvas.mc.setState((byte)26);
        break;
      case 33:
        MainCanvas.loadRequestCount = 0;
        break;
    } 
    MainCanvas.loadCount++;
    drawTJ2(g, MainCanvas.loadCount);
  }
  
  private void drawTJ2(Graphics g, int aWaitCnt) {
    g.setColor(11898450);
    g.drawString("天劫ONLINE为免费手机", 25, 80, 20);
    g.drawString("网络游戏,下载、安装不", 25, 110, 20);
    g.drawString("会有其他付费捆绑业务。", 25, 140, 20);
    g.drawString("载入中... " + (aWaitCnt * 3 + 1) + "%", MainCanvas.screenW >> 1, MainCanvas.screenH - 50, 17);
    int w = 72;
    int h = 6;
    g.setColor(8415039);
    g.drawRect(MainCanvas.screenW - w >> 1, MainCanvas.screenH - 30, 71, h - 1);
    g.setColor(2037253);
    g.fillRect((MainCanvas.screenW - w >> 1) + 2, MainCanvas.screenH - 28, w - 4, h - 4);
    g.setColor(15718814);
    g.fillRect((MainCanvas.screenW - w >> 1) + 2, MainCanvas.screenH - 28, (w - 4) * (aWaitCnt * 3 + 1) / 100, h - 4);
  }
  
  public static String loadingTip = null;
  
  String drawTip = "";
  
  String tempTip = "欢迎来到天劫！";
  
  private static final int COLOR_FLAG = 16711935;
  
  private void drawLoadingTip(Graphics g) {
    byte stringY = 120;
    g.setColor(15718815);
    String[] strs = null;
    if (loadingTip != null && !loadingTip.equals(""))
      this.tempTip = loadingTip; 
    strs = Util.wrapText(this.drawTip, MainCanvas.screenW - 30, MainCanvas.font[1]);
    byte lg = (byte)strs.length;
    byte i;
    for (i = 0; i < lg; i = (byte)(i + 1))
      g.drawString(strs[i], MainCanvas.screenW >> 1, stringY + i * (MainCanvas.CHARH + 5), 17); 
  }
  
  public void drawMenuPeople(Graphics g, int argX, int argY, byte argType) {
    short[][] frameDataPeople = (short[][])null;
    short[] picDatasPeople = null;
    Image tempImagePeople = null;
    short[][] motionDataAll = (short[][])null;
    switch (argType) {
      case 0:
        tempImagePeople = GameObj.imagePeopleWhiteCK;
        frameDataPeople = GameObj.frameDataPeopleWhiteCK;
        picDatasPeople = GameObj.picDatasPeopleWhiteCK;
        motionDataAll = GameObj.motionDataAllWhiteCK;
        break;
      case 1:
        tempImagePeople = GameObj.imagePeopleWhiteJK;
        frameDataPeople = GameObj.frameDataPeopleWhiteJK;
        picDatasPeople = GameObj.picDatasPeopleWhiteJK;
        motionDataAll = GameObj.motionDataAllWhiteJK;
        break;
      case 2:
        tempImagePeople = GameObj.imagePeopleWhiteDS;
        frameDataPeople = GameObj.frameDataPeopleWhiteDS;
        picDatasPeople = GameObj.picDatasPeopleWhiteDS;
        motionDataAll = GameObj.motionDataAllWhiteDS;
        break;
      case 3:
        tempImagePeople = GameObj.imagePeopleWhiteYS;
        frameDataPeople = GameObj.frameDataPeopleWhiteYS;
        picDatasPeople = GameObj.picDatasPeopleWhiteYS;
        motionDataAll = GameObj.motionDataAllWhiteYS;
        break;
      case 4:
        tempImagePeople = GameObj.imagePeopleBlackYS;
        frameDataPeople = GameObj.frameDataPeopleBlackYS;
        picDatasPeople = GameObj.picDatasPeopleBlackYS;
        motionDataAll = GameObj.motionDataAllBlackYS;
        break;
      case 5:
        tempImagePeople = GameObj.imagePeopleBlackCK;
        frameDataPeople = GameObj.frameDataPeopleBlackCK;
        picDatasPeople = GameObj.picDatasPeopleBlackCK;
        motionDataAll = GameObj.motionDataAllBlackCK;
        break;
      case 6:
        tempImagePeople = GameObj.imagePeopleBlackJK;
        frameDataPeople = GameObj.frameDataPeopleBlackJK;
        picDatasPeople = GameObj.picDatasPeopleBlackJK;
        motionDataAll = GameObj.motionDataAllBlackJK;
        break;
      case 7:
        tempImagePeople = GameObj.imagePeopleBlackDS;
        frameDataPeople = GameObj.frameDataPeopleBlackDS;
        picDatasPeople = GameObj.picDatasPeopleBlackDS;
        motionDataAll = GameObj.motionDataAllBlackDS;
        break;
    } 
    Util.drawRoleEditFrame(tempImagePeople, g, frameDataPeople[motionDataAll[0][0]], picDatasPeople, argX, argY, false);
  }
  
  public void drawCreat(Graphics g, int[] argX, int[] argY, boolean[] isMove, int maxN, byte[] imgId, boolean[] isDraw) {
    int moveIndex = 0;
    int standIndex = 0;
    int maxNum = maxN;
    for (int i = 0; i < maxNum; i++) {
      try {
        if (isDraw[i])
          if (isMove[i]) {
            moveIndex = 3;
            Util.drawRoleEditFrame(creatImage[imgId[i]], g, creatFrameData[imgId[i]][creatMotionDataAll[imgId[i]][moveIndex][creatFrameIndex]], creatPicData[imgId[i]], argX[i] + 18, argY[i] + 34, false);
            if (creatFrameIndex < (creatMotionDataAll[imgId[i]][moveIndex]).length - 1) {
              creatFrameIndex++;
            } else {
              creatFrameIndex = 0;
            } 
          } else {
            Util.drawRoleEditFrame(creatImage[imgId[i]], g, creatFrameData[imgId[i]][creatMotionDataAll[imgId[i]][standIndex][0]], creatPicData[imgId[i]], argX[i] + 18, argY[i] + 34, false);
          }  
      } catch (RuntimeException e) {
        e.printStackTrace();
      } 
    } 
  }
  
  public void initCreatRes() {
    if (!Util.isInitRes[6]) {
      creatImage = null;
      creatFrameData = (short[][][])null;
      creatPicData = (short[][])null;
      creatMotionDataAll = (short[][][])null;
      creatImage = new Image[8];
      creatFrameData = new short[8][][];
      creatPicData = new short[8][];
      creatMotionDataAll = new short[8][][];
      GameObj.initWhitePeopleRes();
      GameObj.initBlackPeopleRes();
      try {
        creatImage[0] = GameObj.imagePeopleWhiteCK;
        creatFrameData[0] = GameObj.frameDataPeopleWhiteCK;
        creatPicData[0] = GameObj.picDatasPeopleWhiteCK;
        creatMotionDataAll[0] = GameObj.motionDataAllWhiteCK;
        creatImage[1] = GameObj.imagePeopleWhiteJK;
        creatFrameData[1] = GameObj.frameDataPeopleWhiteJK;
        creatPicData[1] = GameObj.picDatasPeopleWhiteJK;
        creatMotionDataAll[1] = GameObj.motionDataAllWhiteJK;
        creatImage[2] = GameObj.imagePeopleWhiteDS;
        creatFrameData[2] = GameObj.frameDataPeopleWhiteDS;
        creatPicData[2] = GameObj.picDatasPeopleWhiteDS;
        creatMotionDataAll[2] = GameObj.motionDataAllWhiteDS;
        creatImage[3] = GameObj.imagePeopleWhiteYS;
        creatFrameData[3] = GameObj.frameDataPeopleWhiteYS;
        creatPicData[3] = GameObj.picDatasPeopleWhiteYS;
        creatMotionDataAll[3] = GameObj.motionDataAllWhiteYS;
        creatImage[4] = GameObj.imagePeopleBlackYS;
        creatFrameData[4] = GameObj.frameDataPeopleBlackYS;
        creatPicData[4] = GameObj.picDatasPeopleBlackYS;
        creatMotionDataAll[4] = GameObj.motionDataAllBlackYS;
        creatImage[5] = GameObj.imagePeopleBlackCK;
        creatFrameData[5] = GameObj.frameDataPeopleBlackCK;
        creatPicData[5] = GameObj.picDatasPeopleBlackCK;
        creatMotionDataAll[5] = GameObj.motionDataAllBlackCK;
        creatImage[6] = GameObj.imagePeopleBlackJK;
        creatFrameData[6] = GameObj.frameDataPeopleBlackJK;
        creatPicData[6] = GameObj.picDatasPeopleBlackJK;
        creatMotionDataAll[6] = GameObj.motionDataAllBlackJK;
        creatImage[7] = GameObj.imagePeopleBlackDS;
        creatFrameData[7] = GameObj.frameDataPeopleBlackDS;
        creatPicData[7] = GameObj.picDatasPeopleBlackDS;
        creatMotionDataAll[7] = GameObj.motionDataAllBlackDS;
      } catch (RuntimeException e) {
        e.printStackTrace();
      } 
      Util.isInitRes[6] = true;
    } 
  }
  
  public void drawSoundClew(Graphics g) {
    g.setColor(15718814);
    g.drawString("是否开启声音?", MainCanvas.screenW >> 1, (MainCanvas.screenH >> 1) - MainCanvas.CHARH, 17);
    g.drawString("打开", 3, MainCanvas.screenH - 3, 36);
    g.drawString("关闭", MainCanvas.screenW - 3, MainCanvas.screenH - 3, 40);
    keyInSoundClew();
  }
  
  public void keyInSoundClew() {
    if (MainCanvas.isKeyPress(17) || 
      !MainCanvas.isKeyPress(14));
    MainCanvas.mc.setState((byte)4);
  }
  
  private void drawFlags(Graphics g) {
    g.setColor(16711935);
    for (int i = 0; i < Map.FLAGS.length; i++) {
      int col = Map.FLAGS[i][0];
      int row = Map.FLAGS[i][1];
      if (col != -1 || row != -1)
        g.fillRect(getMapX(col, row) - 1, getMapY(col, row) - 1, 3, 3); 
    } 
  }
  
  public void drawTrumpetMessage(Graphics g) {
    if (PCChat.bTrumpetDrawType == 0)
      return; 
    if (PCChat.bTrumpetDrawType == 1) {
      g.setColor(0);
      int i, j;
      for (i = 0, j = PCChat.z_strTrumpetMessage.length; i < j; i++)
        g.drawString(PCChat.z_strTrumpetMessage[i], PCChat.nTrumpet_X + 1, PCChat.nTrumpet_Y + (MainCanvas.CHARH + 1) * i + 1, 0); 
      g.setColor(16777215);
      for (i = 0, j = PCChat.z_strTrumpetMessage.length; i < j; i++)
        g.drawString(PCChat.z_strTrumpetMessage[i], PCChat.nTrumpet_X, PCChat.nTrumpet_Y + (MainCanvas.CHARH + 1) * i, 0); 
    } else {
      g.setColor(0);
      g.drawString(PCChat.strTrumpetMessage, PCChat.nTrumpet_X + 1, PCChat.nTrumpet_Y + 1, 0);
      g.setColor(16777215);
      g.drawString(PCChat.strTrumpetMessage, PCChat.nTrumpet_X, PCChat.nTrumpet_Y, 0);
    } 
    PCChat.Logic_Trumpet();
  }
  
  public void drawTitleList(Graphics g) {
    if (MainCanvas.mc.baseForm == null) {
      MainCanvas.mc.titleTotalPage = MainCanvas.mc.titleTotal / 8;
      if (MainCanvas.mc.titleTotal % 8 != 0)
        MainCanvas.mc.titleTotalPage++; 
      String page = (MainCanvas.mc.titleCurrentPage + 1) + "/" + MainCanvas.mc.titleTotalPage;
      UILabel lblPage = new UILabel(0, 189, 0, 0, page, 15718814, (byte)1, (byte)0);
      MainCanvas.mc.baseForm = new UIForm(0, 0, (short)MainCanvas.screenW, (short)MainCanvas.screenH, "");
      UIRim frame = new UIRim(0, 0, (short)(MainCanvas.screenW - 1), (short)(MainCanvas.screenH - 1), (byte)4);
      UIRim rimTitle = new UIRim(0, 10, 160, 21, (byte)7);
      UILabel lblTitle = new UILabel(0, (short)(rimTitle.positionY + 2), 0, 0, "称号列表", 15718814, (byte)1, (byte)0);
      UIRim rimDown = new UIRim(0, 30, 160, 158, (byte)0);
      int tempcount = MainCanvas.mc.titleName.length;
      MainCanvas.mc.tables[0] = new UITable(0, 31, 160, 158, tempcount, 1, (tempcount > 8) ? 8 : tempcount, (byte)0, (byte)3);
      MainCanvas.mc.tables[0].setSingleWH((MainCanvas.mc.tables[0]).singleWidth, (byte)19, false);
      UILabel label1 = new UILabel(0, 0, 0, 0, "操作", 15718815, (byte)0, (byte)0);
      UILabel label2 = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte)0, (byte)0);
      for (int i = 0; i < tempcount; i++) {
        if (MainCanvas.mc.titleName[i].equals(
            Player.getInstance().getTitle())) {
          MainCanvas.mc.tables[0].addItem(MainCanvas.mc.titleName[i] + "(使用中)", 15718815);
        } else {
          MainCanvas.mc.tables[0].addItem(MainCanvas.mc.titleName[i], 15718815);
        } 
      } 
      MainCanvas.mc.baseForm.addComponent(frame);
      MainCanvas.mc.baseForm.addComponentInCenter(rimDown, (byte)2);
      MainCanvas.mc.baseForm.addComponentInCenter(rimTitle, (byte)2);
      MainCanvas.mc.baseForm.addComponentInCenter(lblTitle, (byte)2);
      if (tempcount > 0)
        MainCanvas.mc.baseForm.addComponentInCenter(label1, (byte)5); 
      MainCanvas.mc.baseForm.addComponentInCenter(label2, (byte)6);
      MainCanvas.mc.baseForm.addComponentInCenter(MainCanvas.mc.tables[0], (byte)2);
      MainCanvas.mc.baseForm.addComponentInCenter(lblPage, (byte)2);
      MainCanvas.mc.tables[0].setXY((MainCanvas.mc.tables[0]).positionX, (MainCanvas.mc.tables[0]).positionY);
      ClanWar.getInstance().drawFormArrow(MainCanvas.mc.baseForm, MainCanvas.mc.titleCurrentPage, MainCanvas.mc.titleTotalPage);
      MainCanvas.mc.tables[0].setOldFocus(true);
      MainCanvas.mc.baseForm.setFocus(true);
    } 
    MainCanvas.mc.baseForm.draw(g);
  }
  
  public void drawNPCTitleList(Graphics g) {
    if (MainCanvas.mc.baseForm == null) {
      MainCanvas.mc.baseForm = new UIForm(0, 0, (short)MainCanvas.screenW, (short)MainCanvas.screenH, "");
      UIRim frame = new UIRim(0, 0, (short)(MainCanvas.screenW - 1), (short)(MainCanvas.screenH - 1), (byte)4);
      UIRim rimTitle = new UIRim(0, 10, 160, 21, (byte)7);
      UILabel lblTitle = new UILabel(0, (short)(rimTitle.positionY + 2), 0, 0, "NPC称号列表", 15718814, (byte)1, (byte)0);
      UIRim rimDown = new UIRim(0, 30, 160, 158, (byte)0);
      int tempcount = MainCanvas.mc.titleName.length;
      MainCanvas.mc.tables[0] = new UITable(0, 31, 160, 158, tempcount, 1, (tempcount > 8) ? 8 : tempcount, (byte)0, (byte)3);
      MainCanvas.mc.tables[0].setAutoHeight(true);
      UILabel label1 = new UILabel(0, 0, 0, 0, "操作", 15718815, (byte)0, (byte)0);
      UILabel label2 = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte)0, (byte)0);
      for (int i = 0; i < tempcount; i++)
        MainCanvas.mc.tables[0].addItem(MainCanvas.mc.titleName[i], 15718815); 
      MainCanvas.mc.baseForm.addComponent(frame);
      MainCanvas.mc.baseForm.addComponentInCenter(rimDown, (byte)2);
      MainCanvas.mc.baseForm.addComponentInCenter(rimTitle, (byte)2);
      MainCanvas.mc.baseForm.addComponentInCenter(lblTitle, (byte)2);
      if (tempcount > 0)
        MainCanvas.mc.baseForm.addComponentInCenter(label1, (byte)5); 
      MainCanvas.mc.baseForm.addComponentInCenter(label2, (byte)6);
      MainCanvas.mc.baseForm.addComponentInCenter(MainCanvas.mc.tables[0], (byte)2);
      MainCanvas.mc.tables[0].setXY((MainCanvas.mc.tables[0]).positionX, (MainCanvas.mc.tables[0]).positionY);
      MainCanvas.mc.baseForm.setFocus(true);
    } 
    MainCanvas.mc.baseForm.draw(g);
  }
}
