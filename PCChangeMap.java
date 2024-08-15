
import javax.microedition.lcdui.Displayable;

public class PCChangeMap {

    public static byte isReceivedMapData;
    public static byte[] recelveMapData;
    private static boolean isTwoLoding = true;
    public static byte[] bufferMapData;
    public static short bufferMapID = -1;
    public static boolean isReceivedMapInfo = false;
    private static final byte TOTAL_POINT = 8;
    private static final byte TOTAL_IMG_RES = 16;
    public static final byte OFFSET_ENEMY = 40;
    private static final byte OFFSET_NO_MEAN = -1;
    public static byte npcNum;
    public static byte enemyNum;
    public static byte[] arrNpc;
    public static byte[] arrEnemy;
    public static byte[] arrAll = new byte[16];
    public static boolean isParseResMsg;
    public static short nextMapID;
    public static byte nextMapType;
    public static boolean isCounterpart;
    private static String[] cannotPassStr = new String[]{"没有开放此地图！", "您不能进入敌对阵营的领地！", "您的等级不够，不能进入此领地！", "不在攻城时间，不能进入！", "当前人数已满，不能进入！"};
    public static String[] strHelpMenu = null;
    public static String[][] strHelpSubMenu = (String[][]) null;
    public static String strHelpContent = null;
    public static boolean isCol = false;
    public static boolean isRow = false;

    public static void parse(int commID, byte[] _data) {
        String hexID = Integer.toHexString(commID);
        System.out.println("[handle command]:command id:0x" + hexID + ",type:PCChangeMap");
        try {
            int canPass, mapDataLength;
            UIForm subForm;
            UIRim frame, rimTitle;
            UILabel lblTitle, lblCancel;
            ByteArray execDataIn = new ByteArray(_data);
            switch (commID) {
                case Cmd.S_MAP_CHANGE: {  // 接收服务器地图数据
                    (Player.getInstance()).path = null;
                    canPass = -1;
                    canPass = execDataIn.readByte();
                    if (canPass != 1) {
                        if (canPass != 13) {
                            int tempSetp = 12;
                            isRow = false;
                            isCol = false;
                            if ((Player.getInstance()).direction == 1) {
                                (Player.getInstance()).y += tempSetp * 2;
                            } else if ((Player.getInstance()).direction == 2) {
                                (Player.getInstance()).y -= tempSetp * 2;
                            } else if ((Player.getInstance()).direction == 3) {
                                (Player.getInstance()).x += tempSetp;
                            } else if ((Player.getInstance()).direction == 4) {
                                (Player.getInstance()).x -= tempSetp;
                            }
                            (Player.getInstance()).col = Map.getCurrentColumn((Player.getInstance()).y, (Player.getInstance()).x);
                            (Player.getInstance()).row = Map.getCurrentRow((Player.getInstance()).y, (Player.getInstance()).x);
                            if (isCol || isRow) {
                                if ((Player.getInstance()).direction == 1) {
                                    (Player.getInstance()).y -= tempSetp * 4;
                                } else if ((Player.getInstance()).direction == 2) {
                                    (Player.getInstance()).y += tempSetp * 4;
                                } else if ((Player.getInstance()).direction == 3) {
                                    (Player.getInstance()).x -= tempSetp * 2;
                                } else if ((Player.getInstance()).direction == 4) {
                                    (Player.getInstance()).x += tempSetp * 2;
                                }
                                (Player.getInstance()).col = Map.getCurrentColumn((Player.getInstance()).y, (Player.getInstance()).x);
                                (Player.getInstance()).row = Map.getCurrentRow((Player.getInstance()).y, (Player.getInstance()).x);
                            }
                            MainCanvas.ni.send(Cmd.C_PLAYER_MOVE);
                        }
                        (Player.getInstance()).isSendMoveMsg = true;
                        MainCanvas.resetKey();
                        switch (canPass) {
                            case 0:
                                PCChat.addChatScreen((byte) 7, cannotPassStr[0]);
                                break;
                            case 2:
                                PCChat.addChatScreen((byte) 7, cannotPassStr[1]);
                                break;
                            case 3:
                                PCChat.addChatScreen((byte) 7, cannotPassStr[2]);
                                break;
                            case 4:
                                PCChat.addChatScreen((byte) 7, cannotPassStr[3]);
                                break;
                            case 5:
                                PCChat.addChatScreen((byte) 7, cannotPassStr[4]);
                                break;
                            case 6:
                                PCChat.addChatScreen((byte) 7, "本层守卫者还未击杀，不能进入下一层！");
                                break;
                            case 7:
                                PCChat.addChatScreen((byte) 7, "今生不能进入转世地图！");
                                break;
                            case 8:
                                PCChat.addChatScreen((byte) 7, "转生玩家不允许进入前世地图！");
                                break;
                            case 9:
                                PCChat.addChatScreen((byte) 7, "此副本当前时间不开放！");
                                break;
                            case 10:
                                PCChat.addChatScreen((byte) 7, "您没有当前副本的钥匙！");
                                break;
                            case 11:
                                PCChat.addChatScreen((byte) 7, "您已经超过进入此副本的次数！");
                                break;
                            case 12:
                            case 13:
                                try {
                                    PCChat.addChatScreen((byte) 7, execDataIn.readUTF());
                                } catch (Exception exception) {
                                }
                                break;
                            default:
                                PCChat.addChatScreen((byte) 7, "没有开放此地图！");
                                break;
                        }
                        MainCanvas.mc.setGameState((byte) 0);
                        UIGameRun.releaseChat();
                    }
                    if (canPass == 1) {
                        Map.clearFlags();
                        for (int k = 0; k < MainCanvas.mc.teamMates.size(); k++) {
                            GameObj temp = (GameObj) MainCanvas.mc.teamMates.elementAt(k);
                            temp.aimRow = temp.aimColumn = temp.col = temp.row = -1;
                        }
                        MainCanvas.mc.aMidlet.display.setCurrent((Displayable) MainCanvas.mc);
                        if (MainCanvas.mc.commonForm != null) {
                            MainCanvas.mc.clearAdvanceUIItem();
                        }
                        nextMapID = execDataIn.readShort();
                        nextMapType = execDataIn.readByte();
                        byte tempCounterpart = execDataIn.readByte();
                        switch (tempCounterpart) {
                            case 1:
                                Map.description = Cons.WM_NAMEDESC[0];
                                break;
                            case 2:
                                Map.description = Cons.WM_NAMEDESC[3];
                                break;
                            case 3:
                                Map.description = Cons.WM_NAMEDESC[2];
                                break;
                            case 4:
                                Map.description = Cons.WM_NAMEDESC[4];
                                break;
                            case 5:
                                Map.description = Cons.WM_NAMEDESC[5];
                                break;
                            case 6:
                                Map.description = Cons.WM_NAMEDESC[1];
                                break;
                        }
                        if (tempCounterpart == 3) {
                            isCounterpart = true;
                        } else {
                            isCounterpart = false;
                        }
                        Map.curPointArray = new int[8][3];
                        int j;
                        for (j = 0; j < 8; j++) {
                            Map.curPointArray[j][0] = execDataIn.readByte();
                            Map.curPointArray[j][1] = execDataIn.readByte();
                            Map.curPointArray[j][2] = execDataIn.readShort();
                        }
                        for (j = 0; j < 16; j++) {
                            byte tempA = 0;
                            tempA = execDataIn.readByte();
                            arrAll[j] = tempA;
                            if (tempA != -1) {
                                if (tempA < 40) {
                                    npcNum = (byte) (npcNum + 1);
                                } else if (tempA >= 40) {
                                    enemyNum = (byte) (enemyNum + 1);
                                }
                            }
                        }
                        Map.currentMapName = execDataIn.readUTF();
                        for (j = 0; j < Map.nextMapName.length; j++) {
                            Map.nextMapName[j] = execDataIn.readUTF();
                        }
                        if (MainCanvas.isLogin) {
                            MainCanvas.mc.setState((byte) 23);
                            MainCanvas.loadCount = 0;
                            MainCanvas.waitCnt = 0;
                            if (isReceivedMapData == 3) {
                                isReceivedMapData = 0;
                                isTwoLoding = false;
                            } else if (isReceivedMapData == 1) {
                                isReceivedMapData = 0;
                            }
                        }
                        isParseResMsg = true;
                    }
                    isReceivedMapInfo = true;
                    break;
                }
                case Cmd.S_STORY_RECEIVE: {
                    UIGameRun.loadingTip = execDataIn.readUTF();
                    break;
                }
                case Cmd.S_MAP_DATA_RECEIVE: {
                    mapDataLength = execDataIn.readInt();
                    try {
                        if (bufferMapID != -1) {
                            bufferMapID = Map.currentMapID;
                            bufferMapData = null;
                            bufferMapData = new byte[recelveMapData.length];
                            System.arraycopy(recelveMapData, 0, bufferMapData, 0, bufferMapData.length);
                        }
                        recelveMapData = null;
                        recelveMapData = new byte[mapDataLength];
                        recelveMapData = execDataIn.readByteArray(mapDataLength);
                        if (bufferMapID == -1) {
                            bufferMapID = nextMapID;
                            bufferMapData = null;
                            bufferMapData = new byte[recelveMapData.length];
                            System.arraycopy(recelveMapData, 0, bufferMapData, 0, bufferMapData.length);
                        }
                    } catch (Exception exception) {
                    }
                    if (isReceivedMapData == 3 && isTwoLoding) {
                        isReceivedMapData = 1;
                    }
                    if (!isTwoLoding) {
                        isTwoLoding = true;
                    }
                    break;
                }
                case Cmd.S_WORLDMAP: {  // 接收世界地图
                    Map.currentWMapID = execDataIn.readInt();
                    Map.choosedPlace = --Map.currentWMapID;
                    // 读取地图数量
                    Map.NUMBER_OF_PLACES = execDataIn.readShort();
                    if (Map.regionPos != null) {
                        Map.regionPos = null;
                    }
                    // 区域位置
                    Map.regionPos = new int[Map.NUMBER_OF_PLACES][2];
                    if (Map.regionProps != null) {
                        Map.regionProps = null;
                    }
                    // 区域属性
                    Map.regionProps = new int[Map.NUMBER_OF_PLACES][6];
                    if (Map.regionName != null) {
                        Map.regionName = null;
                    }
                    // 地图名称
                    Map.regionName = new String[Map.NUMBER_OF_PLACES];
                    for (int i = 0; i < Map.NUMBER_OF_PLACES; ++i) {
                        // 设置地图名称
                        Map.regionName[i] = execDataIn.readUTF();
                        // 设置地图位置，移动地图时需要
                        Map.regionPos[i][0] = execDataIn.readShort();
                        Map.regionPos[i][1] = execDataIn.readShort();
                        // 设置地图属性
                        // 地图图片类型
                        Map.regionProps[i][0] = execDataIn.readByte();
                        // 地图类型 0-主城 1-中立领地 2-副本 3-新手村 4-天人领地 5-修罗领地
                        Map.regionProps[i][1] = execDataIn.readByte();
                        //  上方向地图索引 -1为不可行走
                        Map.regionProps[i][2] = execDataIn.readShort();
                        //  下方向地图索引 -1为不可行走
                        Map.regionProps[i][3] = execDataIn.readShort();
                        //  左方向地图索引 -1为不可行走
                        Map.regionProps[i][4] = execDataIn.readShort();
                        //  右方向地图索引 -1为不可行走
                        Map.regionProps[i][5] = execDataIn.readShort();
                    }
                    int nLines = execDataIn.readShort();
                    if (Map.regionLines != null) {
                        Map.regionLines = null;
                    }
                    // 绘制地图方框
                    Map.regionLines = new int[nLines][4];
                    for (int i = 0; i < nLines; i++) {
                        Map.regionLines[i][0] = execDataIn.readShort();
                        Map.regionLines[i][1] = execDataIn.readShort();
                        Map.regionLines[i][2] = execDataIn.readShort();
                        Map.regionLines[i][3] = execDataIn.readShort();
                    }
                    if (MainCanvas.mc.getGameState() == 3) {
                        MainCanvas.mc.setGameState((byte) 1);
                        MainCanvas.mc.baseForm.setAboutForm(null);
                    } else {
                        MainCanvas.mc.setGameState((byte) 1);
                    }
                    MainCanvas.mc.setRightMenuSubState(5);
                    MainCanvas.mc.setUIMApState((byte) 0);
                    MainCanvas.mc.releaseUI();
                    break;
                }
                case Cmd.S_SYSNOTICE: {
                    strHelpContent = execDataIn.readUTF();
                    subForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "announce");
                    frame = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte) 4);
                    subForm.setBackGround((byte) 9);
                    rimTitle = new UIRim(0, 13, 160, 17, (byte) 7);
                    lblTitle = new UILabel(0, rimTitle.positionY + 2, 0, 0, "游戏公告", 15718814, (byte) 1, (byte) 0);
                    lblCancel = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 1, (byte) 0);
                    MainCanvas.mc.textArea[0] = new UITextArea(0, 30, 160, 158, strHelpContent
                            .trim());
                    MainCanvas.mc.textArea[0].setColor(15718814);
                    MainCanvas.mc.textArea[0].setFocus(true);
                    subForm.addComponent(frame);
                    subForm.addComponentInCenter(rimTitle, (byte) 2);
                    subForm.addComponentInCenter(lblTitle, (byte) 2);
                    subForm.addComponentInCenter(MainCanvas.mc.textArea[0], (byte) 2);
                    subForm.addComponentInCenter(lblCancel, (byte) 6);
                    MainCanvas.mc.baseForm.setAboutForm(subForm);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] compress(int _commID) {
        ByteArray execDataOut = new ByteArray();
        switch (_commID) {
            case Cmd.C_MAP_CHANGE: {
                execDataOut.writeByte(Map.changeMapPointIndex);
                break;
            }
            case 536871424:
                execDataOut.writeShort(Map.currentMapID);
                break;
            case 536871680:
                execDataOut.writeByte(0);
                execDataOut.writeShort(nextMapID);
                break;
        }
        return execDataOut.toByteArray();
    }

    public static void creatNpcAndEnemyArray(int argNpcNum, int argEnemyNum) {
        arrNpc = new byte[argNpcNum];
        arrEnemy = new byte[argEnemyNum];
        byte tmpNpcIndex = 0;
        byte tmpEnemyIndex = 0;
        for (int i = 0; i < arrAll.length; i++) {
            if (arrAll[i] != -1) {
                if (arrAll[i] < 40) {
                    arrNpc[tmpNpcIndex] = arrAll[i];
                    tmpNpcIndex = (byte) (tmpNpcIndex + 1);
                } else if (arrAll[i] >= 40) {
                    arrEnemy[tmpEnemyIndex] = (byte) (arrAll[i] - 40);
                    tmpEnemyIndex = (byte) (tmpEnemyIndex + 1);
                }
            }
        }
        npcNum = 0;
        enemyNum = 0;
    }
}
