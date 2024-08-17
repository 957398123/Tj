import java.util.Stack;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class GameObj {
    public byte isReincarnation = 0;
    public static final int RNameColor = 1745905;
    private static final byte[][] SELARC_WH = new byte[][]{{29, 16}, {32, 18}, {41, 21}};
    private byte selArcTick = 0;
    public byte eventState = 0;
    public GameObj pkObj = null;
    public boolean isEnemy = false;
    public static final byte STATE_STAND = 0;
    public static final byte STATE_MOVE = 1;
    public static final byte STATE_NORMALFIGHT = 2;
    public static final byte STATE_USEITEM = 3;
    public static final byte STATE_DIE = 4;
    public static final byte STATE_DEAD = 5;
    public static final byte STATE_HURT = 6;
    public static final byte STATE_SKILLPRE = 7;
    public static final byte STATE_USEMAGIC = 8;
    public static final byte STATE_COLLECTION = 9;
    public static final byte STATE_REL_JK1 = 31;
    public static final byte STATE_REL_JK2 = 32;
    public static final byte STATE_REL_JK3 = 33;
    public static final byte STATE_REL_JK4 = 34;
    public static final byte STATE_REL_JK5 = 35;
    public static final byte STATE_REL_JK6 = 36;
    public static final byte STATE_REL_JK7 = 37;
    public static final byte STATE_REL_JK8 = 38;
    public static final byte STATE_REL_JK9 = 39;
    public static final byte STATE_REL_JK10 = 40;
    public static final byte STATE_REL_CK1 = 41;
    public static final byte STATE_REL_CK2 = 42;
    public static final byte STATE_ADD_CK3 = 43;
    public static final byte STATE_ADD_CK4 = 44;
    public static final byte STATE_ADD_CK5 = 45;
    public static final byte R_HUM_F_SOD_P_OFFSET_X = 7;
    public static final byte R_HUM_F_SOD_P_OFFSET_Y = 3;
    public static final byte R_HUM_F_SOD_P_OFFSET_W = 9;
    public static final byte R_HUM_F_SOD_P_OFFSET_H = 3;
    private static final byte FIRST_ROW = 0;
    private static final byte FIRST_COL = 1;
    public int bufferStateFlag = -1;
    public boolean[] bufferState = new boolean[23];
    public static final byte BUFFER_TYPE_SUM = 23;
    public static final byte[][] STEP = new byte[][]{{5, 4, 4, 2}, {2, 2, 2, 1}, {6, 4, 6, 3}, {0, 0, 0, 0}};
    public static final byte SPEED_NOMAL = 0;
    public static final byte SPEED_DOWN = 1;
    public static final byte SPEED_UP = 2;
    public static final byte SPEED_NONE = 3;
    private static final byte FIND_PATH_JUMP = 60;
    private byte countFindPathJump;
    private static final byte TEMP_FOR_TIMES = 20;
    public static final byte OFFSET_ZERO = 0;
    private static final byte[][] OFFSET_EQUMENT_AND_SPECIFIC = new byte[][]{{34, 37}, {34, 37}, {28, 30}, {27, 29}, {32, 34}, {29, 32}, {52, 55}, {49, 53}};
    public boolean bNeedTitle = false;
    public int objID;
    public String name;
    public byte race;
    public byte gender;
    public byte profession;
    /**
     * 1-其它玩家 2-怪物 3-NPC 4-玩家本身 5-其它物品
     */
    public byte type;
    /**
     * 职业类型
     * 0 1 2 3 group1 3-医师
     * 4 5 6 7 group2
     */
    public byte imgID;
    /**
     * 阵营标识
     * 1-天人界
     * 2-修罗界
     */
    public byte group;
    public short accuracy;
    public byte level;
    public int buffer;
    public int maxHp;
    public int maxMp;
    /**
     * 当前Hp
     */
    public int curHp;
    public int lastHp;
    /**
     * 当前Mp
     */
    public int curMp;
    public Stack hpStates;
    public int[][] hpChange = new int[3][3];
    public static final short HP_CHANGE_COUNT = 6;
    public byte action = 0;
    public byte eliteType = 0;
    public byte curWeapon;
    public short attack;
    public short defend;
    public int maxExp;
    public int exp;
    public int money;
    public byte isPlayerWife = 0;
    private static byte marryArrowTick;
    private static final byte[] MARRYARROW = new byte[]{0, 1, 2, 1};
    public byte[] skill = new byte[14];
    public boolean isSkilling = false;
    public boolean beLocked = false;
    public boolean deCodeSuccess = false;
    public boolean enCodeSuccess = false;
    public boolean canAttack = false;
    /**
     * 0是没有释放技能
     */
    public byte skillIndex = 0;
    /**
     * 0-静止 1-移动 2-普通攻击 4- 5-死亡 7-准备释放技能 8-释放技能中
     */
    public byte state;
    public byte curMotionAndSpecificIndex;
    public byte curFrameIndex;
    public byte direction;
    public int x;
    public int y;
    public byte curStep;
    private byte firstRowOrColumn = 0;
    public int row;
    public int col;
    public int aimRow;
    public int aimColumn;
    public int oldRow;
    public int oldCol;
    public short[][] frameDataPeople;
    public short[] picDatasPeople;
    public Image tempImagePeople;
    public short[][] frameDataEqument;
    public short[] picDatasEqument;
    public Image testImageEqument;
    public short[][] frameDataSpecific;
    public short[] picDatasSpecific;
    public Image testImageSpecific;
    public short[][] motionDataAll;
    public static Image npcImage;
    public static short[][] npcFrameData;
    public static short[] npcPicData;
    public static short[][] npcMotionDataAll;
    private static byte OFFSET_NPC = 0;
    public static Image[] mosterImage;
    public static short[][][] mosterFrameData;
    public static short[][] mosterPicData;
    public static short[][][] mosterMotionDataAll;
    public static GameObj instance;
    public static int offsetY = 0;
    public static Image imgShadowImmortal;
    public static short[] picDatasPeopleWhiteDS;
    public static Image imagePeopleWhiteDS;
    public static short[][] motionDataAllWhiteDS;
    public static short[][] frameDataEqumentWandWhiteDS;
    public static short[][] frameDataEqumentPearlWhiteDS;
    public static short[][] frameDataPeopleWhiteDS;
    public static short[][] frameDataSpecificWhiteDS;
    public static short[] picDatasPeopleBlackDS;
    public static Image imagePeopleBlackDS;
    public static short[][] motionDataAllBlackDS;
    public static short[][] frameDataPeopleBlackDS;
    public static short[][] frameDataEqumentWandBlackDS;
    public static short[][] frameDataEqumentPearlBlackDS;
    public static short[][] frameDataSpecificBlackDS;
    public static short[] picDatasEqumentWandDS;
    public static Image imageEqumentWandLowDS;
    public static Image imageEqumentWandNormalDS;
    public static Image imageEqumentWandSuperDS;
    public static Image imageEqumentWandSSuperDS;
    public static Image imageEqumentWandSSSuperDS;
    public static short[] picDatasEqumentPearlDS;
    public static Image imageEqumentPearlLowDS;
    public static Image imageEqumentPearlNormalDS;
    public static Image imageEqumentPearlSuperDS;
    public static Image imageEqumentPearlSSuperDS;
    public static Image imageEqumentPearlSSSuperDS;
    public static short[] picDatasPeopleWhiteYS;
    public static Image imagePeopleWhiteYS;
    public static short[][] motionDataAllWhiteYS;
    public static short[][] frameDataPeopleWhiteYS;
    public static short[][] frameDataEqumentWandWhiteYS;
    public static short[][] frameDataEqumentPearlWhiteYS;
    public static short[][] frameDataSpecificWhiteYS;
    public static short[] picDatasPeopleBlackYS;
    public static Image imagePeopleBlackYS;
    public static short[][] motionDataAllBlackYS;
    public static short[][] frameDataPeopleBlackYS;
    public static short[][] frameDataEqumentWandBlackYS;
    public static short[][] frameDataEqumentPearlBlackYS;
    public static short[][] frameDataSpecificBlackYS;
    public static short[] picDatasEqumentWandYS;
    public static Image imageEqumentWandLowYS;
    public static Image imageEqumentWandNormalYS;
    public static Image imageEqumentWandSuperYS;
    public static Image imageEqumentWandSSuperYS;
    public static Image imageEqumentWandSSSuperYS;
    public static short[] picDatasEqumentPearlYS;
    public static Image imageEqumentPearlLowYS;
    public static Image imageEqumentPearlNormalYS;
    public static Image imageEqumentPearlSuperYS;
    public static Image imageEqumentPearlSSuperYS;
    public static Image imageEqumentPearlSSSuperYS;
    public static short[] picDatasPeopleWhiteJK;
    public static Image imagePeopleWhiteJK;
    public static short[][] motionDataAllWhiteJK;
    public static short[][] frameDataPeopleWhiteJK;
    public static short[][] frameDataEqumentSwordWhiteJK;
    public static short[][] frameDataEqumentAxeWhiteJK;
    public static short[][] frameDataSpecificWhiteJK;
    public static short[] picDatasPeopleBlackJK;
    public static Image imagePeopleBlackJK;
    public static short[][] motionDataAllBlackJK;
    public static short[][] frameDataPeopleBlackJK;
    public static short[][] frameDataEqumentSwordBlackJK;
    public static short[][] frameDataEqumentAxeBlackJK;
    public static short[][] frameDataSpecificBlackJK;
    public static short[] picDatasEqumentSwordJK;
    public static Image imageEqumentSwordLowJK;
    public static Image imageEqumentSwordNormalJK;
    public static Image imageEqumentSwordSuperJK;
    public static Image imageEqumentSwordSSuperJK;
    public static Image imageEqumentSwordSSSuperJK;
    public static short[] picDatasEqumentAxeJK;
    public static Image imageEqumentAxeLowJK;
    public static Image imageEqumentAxeNormalJK;
    public static Image imageEqumentAxeSuperJK;
    public static Image imageEqumentAxeSSuperJK;
    public static Image imageEqumentAxeSSSuperJK;
    public static short[] picDatasPeopleWhiteCK;
    public static Image imagePeopleWhiteCK;
    public static short[][] motionDataAllWhiteCK;
    public static short[][] frameDataPeopleWhiteCK;
    public static short[][] frameDataEqumentSwordWhiteCK;
    public static short[][] frameDataEqumentDaggerWhiteCK;
    public static short[][] frameDataSpecificWhiteCK;
    public static short[] picDatasPeopleBlackCK;
    public static Image imagePeopleBlackCK;
    public static short[][] motionDataAllBlackCK;
    public static short[][] frameDataPeopleBlackCK;
    public static short[][] frameDataEqumentSwordBlackCK;
    public static short[][] frameDataEqumentDaggerBlackCK;
    public static short[][] frameDataSpecificBlackCK;
    private static short[] picDatasEqumentSwordCK;
    private static Image imageEqumentSwordLowCK;
    private static Image imageEqumentSwordNormalCK;
    private static Image imageEqumentSwordSuperCK;
    private static Image imageEqumentSwordSSuperCK;
    private static Image imageEqumentSwordSSSuperCK;
    private static short[] picDatasEqumentDaggerCK;
    private static Image imageEqumentDaggerLowCK;
    private static Image imageEqumentDaggerNormalCK;
    private static Image imageEqumentDaggerSuperCK;
    private static Image imageEqumentDaggerSSuperCK;
    private static Image imageEqumentDaggerSSSuperCK;
    public static short[] picDatasSpecificCK;
    public static Image imageSpecificCK;
    public byte forDeadRockImgID = -1;
    public byte originalImgID;
    public byte currentImgID = -1;
    public int originalWeapon;
    public int currentWeapon;
    private static final byte[][] COL_ROWS = new byte[][]{{0, 0}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    protected boolean isMirror;
    // 被选中时动画索引
    private static final byte[] SELECT_SEQUENCE = new byte[]{0, 1, 2, 1};
    private byte selectTick = 0;
    boolean isBaoJi = false;
    private static final byte[][] ENEMY_MOTION = new byte[][]{{24, 24, 25, 26, 26, 27, 28, 29, 30, 0, 0, 0, 31, 31, 42}, {8, 8, 9, 10, 10, 11, 12, 13, 14, 0, 0, 0, 15, 15, 41}, {0, 0, 1, 2, 2, 3, 4, 5, 6, 0, 0, 0, 7, 7, 40}, {16, 16, 17, 18, 18, 19, 20, 21, 22, 0, 0, 0, 23, 23, 44}, {32, 32, 33, 34, 34, 35, 36, 37, 38, 0, 0, 0, 39, 39, 43}, {0, 1, 2, 3, 4, 5, 6, 7, 8, 0, 0, 0, 9, 10, 11}, {36, 36, 36, 37, 37, 37, 38, 38, 38, 0, 0, 0, 39, 39, 39}, {0, 1, 2, 0, 1, 2, 3, 4, 5, 0, 0, 0, 6, 7, 8}, {9, 10, 11, 9, 10, 11, 12, 13, 14, 0, 0, 0, 15, 16, 17}, {18, 19, 20, 18, 19, 20, 21, 22, 23, 0, 0, 0, 24, 25, 26}, {27, 28, 29, 27, 28, 29, 30, 31, 32, 0, 0, 0, 33, 34, 35}, {0, 1, 2, 0, 1, 2, 3, 4, 5, 0, 0, 0, 6, 7, 8}, {9, 9, 9, 9, 9, 9, 10, 33, 34, 0, 0, 0, 11, 11, 11}, {12, 13, 14, 12, 13, 14, 15, 16, 17, 0, 0, 0, 18, 19, 20}, {21, 21, 21, 21, 21, 21, 30, 31, 32, 0, 0, 0, 22, 22, 22}, {23, 24, 23, 23, 24, 23, 25, 26, 27, 0, 0, 0, 28, 29, 28}, {0, 1, 2, 3, 4, 5, 6, 7, 8, 0, 0, 0, 9, 9, 9}, {10, 11, 12, 13, 14, 15, 16, 17, 18, 0, 0, 0, 19, 19, 19}, {20, 21, 22, 23, 24, 25, 26, 27, 28, 0, 0, 0, 29, 29, 29}, {30, 31, 32, 33, 34, 35, 36, 37, 38, 0, 0, 0, 39, 39, 39}, {0, 1, 2, 0, 1, 2, 3, 4, 5, 0, 0, 0, 6, 7, 8}, {9, 10, 11, 9, 10, 11, 12, 13, 14, 0, 0, 0, 15, 16, 17}, {18, 19, 20, 18, 19, 20, 21, 22, 23, 0, 0, 0, 24, 25, 26}, {27, 28, 29, 27, 28, 29, 30, 31, 32, 0, 0, 0, 33, 34, 35}, {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}, {15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29}, {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}, {15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29}, {0, 1, 2, 3, 4, 5, 6, 7, 8, 0, 0, 0, 9, 11, 12}, {12, 13, 14, 15, 16, 17, 18, 19, 20, 0, 0, 0, 21, 22, 23}, {24, 25, 26, 27, 28, 29, 30, 31, 32, 0, 0, 0, 33, 34, 35}, {0, 0, 1, 0, 0, 1, 2, 3, 4, 0, 0, 0, 5, 5, 18}, {6, 6, 7, 6, 6, 7, 8, 9, 10, 0, 0, 0, 11, 11, 19}, {12, 12, 13, 12, 12, 13, 14, 15, 16, 0, 0, 0, 17, 17, 18}, {0, 1, 2, 0, 1, 2, 3, 4, 5, 0, 0, 0, 6, 7, 8}, {0, 1, 2, 3, 4, 5, 6, 7, 8, 0, 0, 0, 9, 10, 11}, {12, 13, 14, 15, 16, 17, 18, 19, 20, 0, 0, 0, 21, 22, 23}, {0, 1, 2, 0, 1, 2, 3, 4, 5, 0, 0, 0, 6, 7, 8}, {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}, {15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29}, {30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44}, {45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59}, {24, 25, 26, 24, 25, 26, 27, 28, 29, 0, 0, 0, 30, 31, 32}, {33, 34, 35, 33, 34, 35, 36, 37, 38, 0, 0, 0, 39, 40, 41}, {42, 43, 44, 45, 46, 47, 48, 49, 50, 0, 0, 0, 51, 52, 53}, {54, 55, 56, 57, 58, 59, 60, 61, 62, 0, 0, 0, 63, 64, 65}, {66, 66, 67, 66, 66, 67, 68, 68, 69, 0, 0, 0, 70, 70, 71}, {72, 73, 74, 75, 76, 77, 78, 79, 80, 0, 0, 0, 81, 82, 83}, {84, 85, 86, 87, 88, 89, 90, 91, 92, 0, 0, 0, 93, 94, 95}, {40, 41, 42, 40, 41, 42, 43, 44, 45, 0, 0, 0, 46, 47, 48}, {49, 50, 51, 49, 50, 51, 52, 53, 54, 0, 0, 0, 55, 56, 57}, {58, 59, 58, 58, 59, 58, 60, 61, 60, 0, 0, 0, 62, 63, 62}, {64, 65, 66, 64, 65, 66, 67, 68, 69, 0, 0, 0, 70, 71, 72}, {36, 37, 38, 39, 40, 41, 42, 43, 44, 0, 0, 0, 45, 46, 47}, {48, 49, 50, 51, 52, 53, 54, 55, 56, 0, 0, 0, 57, 58, 59}, {36, 37, 38, 39, 40, 41, 42, 43, 44, 0, 0, 0, 45, 46, 47}, {48, 49, 50, 51, 52, 53, 54, 55, 56, 0, 0, 0, 57, 58, 59}, {60, 61, 62, 60, 61, 62, 63, 64, 65, 0, 0, 0, 66, 67, 68}, {35, 36, 37, 38, 39, 40, 41, 42, 43, 0, 0, 0, 44, 45, 46}, {47, 48, 49, 47, 48, 49, 50, 51, 52, 0, 0, 0, 53, 54, 55}, {56, 57, 58, 56, 57, 58, 59, 60, 61, 0, 0, 0, 62, 63, 64}, {9, 10, 9, 9, 10, 9, 13, 12, 11, 0, 0, 0, 14, 15, 14}, {40, 41, 42, 43, 44, 45, 46, 47, 48, 0, 0, 0, 49, 50, 51}, {52, 53, 54, 55, 56, 57, 58, 59, 60, 0, 0, 0, 61, 62, 63}, {9, 9, 9, 9, 9, 9, 10, 11, 12, 0, 0, 0, 13, 13, 13}};
    private final int[][] animIdx = new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {9, 10, 11}, {12, 13, 14}, {15, 16, 17}, {18, 18, 18}, {19, 20, 21}, {22, 23, 24}};
    private String title;
    private String titleDesc;

    public void initEditorRes() {
        int tempImgID = imgID - 40;
        int tempIndex = 0;

        for(int i = 0; i < PCChangeMap.arrEnemy.length; ++i) {
            if (PCChangeMap.arrEnemy[i] == tempImgID) {
                tempIndex = i;
            }
        }

        frameDataPeople = mosterFrameData[tempIndex];
        picDatasPeople = mosterPicData[tempIndex];
        tempImagePeople = mosterImage[tempIndex];
        motionDataAll = mosterMotionDataAll[tempIndex];
    }

    public void setEventState(byte _state) {
    }

    public void setState(byte s) {
        if (state != s) {
            int i;
            int kk;
            label47:
            switch (state) {
                case 0:
                    state = s;
                    curFrameIndex = 0;
                    break;
                case 1:
                    state = s;
                    curFrameIndex = 0;
                    break;
                case 2:
                    if (s == 6 || s == 0 || s == 1 || s == 4 || s == 5) {
                        state = s;
                    }

                    curFrameIndex = 0;
                case 3:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                default:
                    break;
                case 4:
                    if (s != 5) {
                        break;
                    }

                    state = s;
                    hpStates.removeAllElements();
                    i = 0;
                    kk = hpChange.length;

                    while(true) {
                        if (i >= kk) {
                            break label47;
                        }

                        hpChange[i][0] = -1;
                        hpChange[i][1] = 0;
                        hpChange[i][2] = 0;
                        ++i;
                    }
                case 5:
                    if (s != 0) {
                        break;
                    }

                    state = s;
                    hpStates.removeAllElements();
                    i = 0;
                    kk = hpChange.length;

                    while(true) {
                        if (i >= kk) {
                            break label47;
                        }

                        hpChange[i][0] = -1;
                        hpChange[i][1] = 0;
                        hpChange[i][2] = 0;
                        ++i;
                    }
                case 6:
                    state = s;
                    curFrameIndex = 0;
                    break;
                case 7:
                    state = s;
                    break;
                case 8:
                    state = s;
                    curFrameIndex = 0;
                    break;
                case 9:
                    state = s;
                    break;
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                    if (s == 0) {
                        state = s;
                    }
            }

            curFrameIndex = 0;
        }

    }

    public byte getState() {
        return state;
    }

    public void setDirection(byte dir) {
        if (direction != dir) {
            direction = dir;
            curMotionAndSpecificIndex = 0;
            curFrameIndex = 0;
        }

    }

    public int getRow(int x, int y) {
        return Map.getCurrentRow(y, x);
    }

    public int getCol(int x, int y) {
        return Map.getCurrentColumn(y, x);
    }

    public void setRowCol(int r, int c) {
        row = r;
        col = c;
    }
    
    /**
     * 设置游戏对象在地图中位置
     * @param c
     * @param r
     */
    public void setObjPosition(int c, int r){
        x = Map.getCurrentCellCenterX(c, r);
        y = Map.getCurrentCellCenterY(c, r);
        setRowCol(r, c);
    }

    void init(ByteArray ba) {
    }

    public void tick() {
        tickBuffer();
        tickHpChangeVectorPop();
    }

    public void tickHpChangeVectorPop() {
        if (hpStates.size() != 0 || hpChange[0][0] != -1 || hpChange[1][0] != -1 || hpChange[2][0] != -1) {
            if (hpStates.size() != 0) {
                if (hpChange[0][0] == -1) {
                    hpChange[0][0] = 0;
                    hpChange[0][1] = 0;
                    hpChange[0][2] = ((Integer)hpStates.pop()).intValue();
                } else if (hpChange[1][0] == -1) {
                    hpChange[1][0] = 0;
                    hpChange[1][1] = 0;
                    hpChange[1][2] = ((Integer)hpStates.pop()).intValue();
                } else if (hpChange[2][0] == -1) {
                    hpChange[2][0] = 0;
                    hpChange[2][1] = 0;
                    hpChange[2][2] = ((Integer)hpStates.pop()).intValue();
                }
            }

            int i = 0;

            for(int kk = hpChange.length; i < kk; ++i) {
                if (hpChange[i][0] == 0) {
                    int tmp = hpChange[i][2];
                    int var10002;
                    if (tmp == 0) {
                        var10002 = hpChange[i][1]++;
                        if (hpChange[i][1] > 6) {
                            hpChange[i][0] = -1;
                            hpChange[2][1] = 0;
                            hpChange[2][2] = 0;
                        }
                    } else {
                        var10002 = hpChange[i][1]++;
                        if (hpChange[i][1] > 6) {
                            hpChange[i][0] = -1;
                            hpChange[2][1] = 0;
                            hpChange[2][2] = 0;
                        }
                    }
                }
            }

        }
    }

    public void tickHpChange(int hpChg) {
        if (this == ObjManager.currentTarget) {
            if (hpChg < 0) {
                lastHp = curHp;
                return;
            }
        } else if (type == 1 && !Util.isEnemy(this, Player.getInstance()) || hpChg < 0) {
            lastHp = curHp;
            return;
        }

        if (curHp - lastHp < 0) {
            hpStates.push(new Integer(-hpChg));
            if (type == 4 && MainCanvas.mc.baseForm != null) {
                MainCanvas.mc.baseForm.setMessage("你正在被攻击", true);
            }
        } else if (curHp - lastHp > 0) {
            hpStates.push(new Integer(hpChg));
        } else {
            hpStates.push(new Integer(0));
        }

        lastHp = curHp;
        if (hpStates.size() > 12) {
            hpStates.removeAllElements();
            int i = 0;

            for(int kk = hpChange.length; i < kk; ++i) {
                hpChange[i][0] = -1;
                hpChange[i][1] = 0;
                hpChange[i][2] = 0;
            }
        }

    }

    public void tickBuffer() {
        if (bufferState[3]) {
            if (curStep != 3) {
                curStep = 3;
            }
        } else if ((bufferState[1] || bufferState[14]) && (!bufferState[1] || !bufferState[14])) {
            if (bufferState[1] && !bufferState[14]) {
                if (curStep != 1) {
                    curStep = 1;
                }
            } else if (!bufferState[1] && bufferState[14] && curStep != 2) {
                curStep = 2;
            }
        } else if (curStep != 0) {
            curStep = 0;
        }

    }

    public void setPkObj(GameObj obj) {
        pkObj = obj;
    }

    public void setBuffer(int buffer) {
        int i = 0;

        for(int kk = bufferState.length; i < kk; ++i) {
            if ((buffer & Cons.BUFFER_TYPE[i]) != 0) {
                bufferState[i] = true;
            } else {
                bufferState[i] = false;
            }
        }

    }

    protected byte getDirectionIndex(byte direction) {
        isMirror = false;
        switch (direction) {
            case 1:
                return 0;
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
            case 6:
            case 8:
                isMirror = true;
                return 2;
            case 5:
            case 7:
            default:
                return 0;
        }
    }

    public boolean isEnemy(GameObj target) {
        return Util.isEnemy(this, target);
    }

    /**
     * 设置对目标使用技能动画
     * @param targetGameObj
     * @param skillIndex
     * @param direction 
     */
    public void useSkill(GameObj targetGameObj, int skillIndex, byte direction) {
        if (targetGameObj != null) {
            if (skillIndex == 0) {
                setState((byte)2);
                if (type != 4) {
                    setDirection(direction);
                }

                SpecialManager.getInstance().addSpecial(targetGameObj, 39);
                return;
            }

            if (type != 1 && type != 4) {
                if (type == 2) {
                }
            } else {
                switch (profession) {
                    case 1:
                        switch (skillIndex) {
                            case 1:
                                setState((byte)31);
                                return;
                            case 2:
                                SpecialManager.getInstance().addSpecial(this, 28);
                                return;
                            case 3:
                                setState((byte)39);
                                SpecialManager.getInstance().addSpecial(targetGameObj, 0);
                                return;
                            case 4:
                                setState((byte)40);
                                SpecialManager.getInstance().addSpecial(this, 1);
                                return;
                            case 5:
                                setState((byte)40);
                                SpecialManager.getInstance().addSpecial(this, 2);
                                return;
                            case 6:
                                setState((byte)34);
                                SpecialManager.getInstance().addSpecial(targetGameObj, 3);
                                return;
                            case 7:
                            case 9:
                            case 11:
                            case 13:
                            default:
                                return;
                            case 8:
                                setState((byte)40);
                                SpecialManager.getInstance().addSpecial(this, 4);
                                return;
                            case 10:
                                setState((byte)40);
                                SpecialManager.getInstance().addSpecial(this, 10);
                                return;
                            case 12:
                                SpecialManager.getInstance().addSpecial(targetGameObj, 18);
                                return;
                            case 14:
                                setState((byte)34);
                                SpecialManager.getInstance().addSpecial(x, y, 14);
                                return;
                        }
                    case 2:
                        if (type == 1) {
                            setState((byte)8);
                        }

                        switch (skillIndex) {
                            case 1:
                                SpecialManager.getInstance().addSpecial(targetGameObj, 7);
                                return;
                            case 2:
                                SpecialManager.getInstance().addSpecial(targetGameObj, 8);
                                return;
                            case 3:
                                SpecialManager.getInstance().addSpecial(x, y, 9);
                                return;
                            case 4:
                                SpecialManager.getInstance().addSpecial(this, 10);
                                return;
                            case 5:
                            case 10:
                            case 11:
                            default:
                                return;
                            case 6:
                                SpecialManager.getInstance().addSpecial(targetGameObj, 11);
                                return;
                            case 7:
                                SpecialManager.getInstance().addSpecial(targetGameObj, 12);
                                return;
                            case 8:
                                SpecialManager.getInstance().addSpecial(targetGameObj.x, targetGameObj.y, 13);
                                return;
                            case 9:
                                SpecialManager.getInstance().addSpecial(targetGameObj, 14);
                                return;
                            case 12:
                                SpecialManager.getInstance().addSpecial(this, 15);
                                return;
                            case 13:
                                SpecialManager.getInstance().addSpecial(this, 16);
                                return;
                            case 14:
                                SpecialManager.getInstance().addSpecial(targetGameObj, 17);
                                return;
                        }
                    case 3:
                        if (type == 1) {
                            setState((byte)8);
                        }

                        switch (skillIndex) {
                            case 1:
                                SpecialManager.getInstance().addSpecial(targetGameObj, 18);
                                return;
                            case 2:
                                SpecialManager.getInstance().addSpecial(targetGameObj, 19);
                                return;
                            case 3:
                                SpecialManager.getInstance().addSpecial(targetGameObj, 20);
                                return;
                            case 4:
                                SpecialManager.getInstance().addSpecial(targetGameObj, 21);
                                return;
                            case 5:
                                SpecialManager.getInstance().addSpecial(this, 22);
                                return;
                            case 6:
                            default:
                                return;
                            case 7:
                                SpecialManager.getInstance().addSpecial(targetGameObj, 23);
                                return;
                            case 8:
                                SpecialManager.getInstance().addSpecial(targetGameObj, 24);
                                return;
                            case 9:
                                SpecialManager.getInstance().addSpecial(targetGameObj, 25);
                                return;
                            case 10:
                                SpecialManager.getInstance().addSpecial(targetGameObj, 26);
                                return;
                            case 11:
                                SpecialManager.getInstance().addSpecial(targetGameObj, 27);
                                return;
                            case 12:
                                SpecialManager.getInstance().addSpecial(targetGameObj, 28);
                                return;
                            case 13:
                                SpecialManager.getInstance().addSpecial(targetGameObj, 29);
                                return;
                            case 14:
                                SpecialManager.getInstance().addSpecial(targetGameObj, 30);
                                return;
                        }
                    case 4:
                        switch (skillIndex) {
                            case 1:
                            case 3:
                            case 7:
                            case 9:
                            default:
                                break;
                            case 2:
                                setState((byte)43);
                                SpecialManager.getInstance().addSpecial(targetGameObj, 31);
                                break;
                            case 4:
                                setState((byte)41);
                                SpecialManager.getInstance().addSpecial(targetGameObj, 32);
                                break;
                            case 5:
                                setState((byte)41);
                                SpecialManager.getInstance().addSpecial(this, 33);
                                break;
                            case 6:
                                setState((byte)42);
                                SpecialManager.getInstance().addSpecial(x, y, 34);
                                break;
                            case 8:
                                setState((byte)41);
                                SpecialManager.getInstance().addSpecial(targetGameObj, 35);
                                break;
                            case 10:
                                setState((byte)41);
                                SpecialManager.getInstance().addSpecial(targetGameObj, 36);
                                break;
                            case 11:
                                setState((byte)41);
                                SpecialManager.getInstance().addSpecial(targetGameObj, 37);
                                break;
                            case 12:
                                setState((byte)41);
                                SpecialManager.getInstance().addSpecial(targetGameObj, 38);
                                break;
                            case 13:
                                setState((byte)41);
                                SpecialManager.getInstance().addSpecial(this, 26);
                                break;
                            case 14:
                                setState((byte)41);
                                SpecialManager.getInstance().addSpecial(this, 26);
                        }
                }
            }
        }

    }

    public void setAimRowAndColumn(int argAimRow, int argAimColumn) {
        aimRow = argAimRow;
        aimColumn = argAimColumn;
        countFindPathJump = 0;
    }

    public void findPath() {
        if (aimRow == row && aimColumn == col) {
            setState((byte)0);
        } else if (countFindPathJump == 60) {
            x = Map.getCurrentCellCenterX(aimColumn, aimRow);
            y = Map.getCurrentCellCenterY(aimColumn, aimRow);
            setState((byte)0);
            countFindPathJump = 0;
        } else {
            subFindPath();
            tickMove();
            ++countFindPathJump;
        }

        setRowCol(getRow(x, y), getCol(x, y));
        Map.putInCell(col, row);
    }

    public void tickMove() {
        setState((byte)1);
        switch (direction) {
            case 1:
                y -= STEP[curStep][1];
                break;
            case 2:
                y += STEP[curStep][1];
                break;
            case 3:
                x -= STEP[curStep][0];
                break;
            case 4:
                x += STEP[curStep][0];
                break;
            case 5:
                x -= STEP[curStep][2];
                y -= STEP[curStep][3];
                break;
            case 6:
                x += STEP[curStep][2];
                y -= STEP[curStep][3];
                break;
            case 7:
                x -= STEP[curStep][2];
                y += STEP[curStep][3];
                break;
            case 8:
                x += STEP[curStep][2];
                y += STEP[curStep][3];
        }

    }

    private boolean isSlope() {
        if (aimRow == row) {
            if (aimColumn > col) {
                if (Map.getInstance().isFloor(col + 1, row)) {
                    setDirection((byte)8);
                    return true;
                }

                if (getRandomDirection((byte)7, (byte)1, (byte)6, (byte)1)) {
                    return true;
                }
            } else if (aimColumn < col) {
                if (Map.getInstance().isFloor(col - 1, row)) {
                    setDirection((byte)5);
                    return true;
                }

                if (getRandomDirection((byte)7, (byte)1, (byte)6, (byte)1)) {
                    return true;
                }
            }
        } else if (aimColumn == col) {
            if (aimRow > row) {
                if (Map.getInstance().isFloor(col, row + 1)) {
                    setDirection((byte)7);
                    return true;
                }

                if (getRandomDirection((byte)5, (byte)0, (byte)8, (byte)0)) {
                    return true;
                }
            } else if (aimRow < row) {
                if (Map.getInstance().isFloor(col, row - 1)) {
                    setDirection((byte)6);
                    return true;
                }

                if (getRandomDirection((byte)5, (byte)0, (byte)8, (byte)0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isDiagonally(int aRow, int aColumn, int aDirection) {
        switch (aDirection) {
            case 1:
                if (!Map.getInstance().isFloor(aColumn - 1, aRow) && !Map.getInstance().isFloor(aColumn, aRow - 1)) {
                    return true;
                }
                break;
            case 2:
                if (!Map.getInstance().isFloor(aColumn + 1, aRow) && !Map.getInstance().isFloor(aColumn, aRow + 1)) {
                    return true;
                }
                break;
            case 3:
                if (!Map.getInstance().isFloor(aColumn - 1, aRow) && !Map.getInstance().isFloor(aColumn, aRow + 1)) {
                    return true;
                }
                break;
            case 4:
                if (!Map.getInstance().isFloor(aColumn + 1, aRow) && !Map.getInstance().isFloor(aColumn, aRow - 1)) {
                    return true;
                }
        }

        return false;
    }

    private boolean isHV() {
        for(int i = 0; i < 20; ++i) {
            if (aimRow - i == row && aimColumn - i == col) {
                if (findDirect((byte)2)) {
                    if (!isDiagonally(row, col, 2)) {
                        return true;
                    }
                } else if (getRandomDirection((byte)7, (byte)0, (byte)8, (byte)1)) {
                    return true;
                }

                return true;
            }

            if (aimRow + i == row && aimColumn + i == col) {
                if (findDirect((byte)1)) {
                    if (!isDiagonally(row, col, 1)) {
                        return true;
                    }
                } else if (getRandomDirection((byte)5, (byte)1, (byte)6, (byte)0)) {
                    return true;
                }
            } else if (aimRow - i == row && aimColumn + i == col) {
                if (findDirect((byte)3)) {
                    if (!isDiagonally(row, col, 3)) {
                        return true;
                    }
                } else if (getRandomDirection((byte)5, (byte)1, (byte)7, (byte)0)) {
                    return true;
                }
            } else if (aimRow + i == row && aimColumn - i == col) {
                if (findDirect((byte)4)) {
                    if (!isDiagonally(row, col, 4)) {
                        return true;
                    }
                } else if (getRandomDirection((byte)8, (byte)1, (byte)6, (byte)0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private void subFindPath() {
        if (!isHV() && !isSlope()) {
            if (firstRowOrColumn == 1) {
                if (aimColumn > col) {
                    if (!findDirect((byte)8) && !getRandomDirection((byte)6, (byte)1, (byte)6, (byte)1)) {
                        setDirection(autoFindDirect());
                    }
                } else if (aimColumn < col && !findDirect((byte)5) && !getRandomDirection((byte)7, (byte)1, (byte)6, (byte)1)) {
                    setDirection(autoFindDirect());
                }
            } else if (firstRowOrColumn == 0) {
                if (aimRow > row) {
                    if (!findDirect((byte)7) && !getRandomDirection((byte)5, (byte)0, (byte)8, (byte)0)) {
                        setDirection(autoFindDirect());
                    }
                } else if (aimRow < row && !findDirect((byte)6) && !getRandomDirection((byte)5, (byte)0, (byte)8, (byte)0)) {
                    setDirection(autoFindDirect());
                }
            }
        }

    }

    private boolean getDirectionByColRow(byte dir, byte colRow) {
        int tCol = col + COL_ROWS[dir][0];
        int tRow = row + COL_ROWS[dir][1];
        if (Map.getInstance().isFloor(tCol, tRow)) {
            setDirection(dir);
            firstRowOrColumn = colRow;
            return true;
        } else {
            return false;
        }
    }

    private boolean getRandomDirection(byte dir, byte colRow, byte dir2, byte colRow2) {
        if (Util.getRandom(2) == 0 && getDirectionByColRow(dir, colRow)) {
            return true;
        } else if (getDirectionByColRow(dir2, colRow2)) {
            return true;
        } else {
            return getDirectionByColRow(dir, colRow);
        }
    }

    private boolean findDirect(byte dir) {
        if (Map.getInstance().isFloor(col + COL_ROWS[dir][0], row + COL_ROWS[dir][1])) {
            setDirection(dir);
            return true;
        } else {
            return false;
        }
    }

    private byte autoFindDirect() {
        int i = 1;

        for(int kk = COL_ROWS.length; i < kk; ++i) {
            if (Map.getInstance().isFloor(col + COL_ROWS[i][0], row + COL_ROWS[i][1])) {
                return (byte)i;
            }
        }

        return -1;
    }

    public void nextFrame() {
        if (motionDataAll != null) {
            if (type != 2 && imgID < 40) {
                if (type == 1 || type == 4) {
                    setMotionIndex();
                }
            } else {
                setEnemyFrame();
            }

            if (curFrameIndex < motionDataAll[curMotionAndSpecificIndex].length - 1) {
                ++curFrameIndex;
            } else {
                switch (getState()) {
                    case 0:
                        curFrameIndex = 0;
                        break;
                    case 1:
                        curFrameIndex = 0;
                        break;
                    case 2:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                        setState((byte)0);
                        curFrameIndex = 0;
                    case 3:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    default:
                        break;
                    case 4:
                        curFrameIndex = 0;
                        setState((byte)5);
                        break;
                    case 5:
                        curFrameIndex = 0;
                        break;
                    case 6:
                        setState((byte)0);
                        curFrameIndex = 0;
                        break;
                    case 7:
                        curFrameIndex = 0;
                        break;
                    case 8:
                        curFrameIndex = 0;
                        setState((byte)0);
                }
            }

        }
    }

    /**
     * 绘制选中对象（脚底下的选中条）
     * @param g 
     */
    public void drawSelect(Graphics g) {
        ++selectTick;
        if (selectTick >= SELECT_SEQUENCE.length * 2) {
            selectTick = 0;
        }
        // 动画索引
        int frameIndex = SELECT_SEQUENCE[selectTick >> 1];
        // 判断与玩家是否敌对关系
        if (Util.isEnemy(this, Player.getInstance())) {
            frameIndex += 3;
        }
        int tx = x - Map.currentWindowX - 13;
        int ty = y - Map.currentWindowY - 8;
        MainCanvas.mImgSelect.draw(g, tx, ty, frameIndex, false);
        if (selArcTick >= SELARC_WH.length) {
            selArcTick = 0;
        }
        if (Util.isEnemy(this, Player.getInstance()) && type != 2 && type != 5 && (pkObj == null || pkObj != null && pkObj != Player.getInstance())) {
            int no = Math.abs(Player.getInstance().level - level);
            if (no <= 5) {
                g.setColor(16711680);
            }
            if (no <= 15 && no > 5) {
                g.setColor(16740864);
            }
            if (no > 15) {
                g.setColor(16773120);
            }
            int ax = x - Map.currentWindowX - SELARC_WH[selArcTick][0] / 2 - 1;
            int ay = y - Map.currentWindowY - SELARC_WH[selArcTick][1] / 2 - 1;
            g.drawArc(ax, ay, SELARC_WH[selArcTick][0], SELARC_WH[selArcTick][1], 5, 80);
            g.drawArc(ax + 1, ay + 1, SELARC_WH[selArcTick][0] - 2, SELARC_WH[selArcTick][1] - 2, 5, 80);
            g.drawArc(ax, ay, SELARC_WH[selArcTick][0], SELARC_WH[selArcTick][1], 95, 80);
            g.drawArc(ax + 1, ay + 1, SELARC_WH[selArcTick][0] - 2, SELARC_WH[selArcTick][1] - 2, 95, 80);
            g.drawArc(ax, ay, SELARC_WH[selArcTick][0], SELARC_WH[selArcTick][1], 185, 80);
            g.drawArc(ax + 1, ay + 1, SELARC_WH[selArcTick][0] - 2, SELARC_WH[selArcTick][1] - 2, 185, 80);
            g.drawArc(ax, ay, SELARC_WH[selArcTick][0], SELARC_WH[selArcTick][1], 275, 80);
            g.drawArc(ax + 1, ay + 1, SELARC_WH[selArcTick][0] - 2, SELARC_WH[selArcTick][1] - 2, 275, 80);
            ++selArcTick;
        }
    }

    public static void initNpcRes() {
        if (!Util.isInitRes[0]) {
            npcImage = Util.loadImage(Util.readPKG("/uiuse.pkg", "qqnpc.png"));
            npcFrameData = Util.readFdatFile("/qqnpc.fdat", 0);
            npcPicData = Util.readPdatFile("/qqnpc.pdat");
            npcMotionDataAll = Util.readMdatFile("/qqnpc.mdat");
            Util.isInitRes[0] = true;
        }

    }

    public static void initSpecificAndEqument() {
        if (!Util.isInitRes[1]) {
            String[] tempString = new String[]{"CKe0.png", "CKe1.png", "CKe2.png", "CKe3.png", "CKe4.png", "CKe5.png", "CKs0.png", "DSe0.png", "DSe1.png", "DSe2.png", "DSe3.png", "DSe4.png", "DSe5.png", "DSs0.png", "JKe0.png", "JKe1.png", "JKe2.png", "JKe3.png", "JKe4.png", "JKe5.png", "JKs0.png", "CKe6.png", "CKe7.png", "JKe6.png", "JKe7.png", "DSe6.png", "DSe7.png", "CKe8.png", "CKe9.png", "DSe8.png", "DSe9.png", "JKe8.png", "JKe9.png"};
            byte[][] tempByte = new byte[tempString.length][];
            tempByte = Util.readPKG("/qqes.pkg", tempString);
            picDatasEqumentWandDS = Util.readPdatFile("/DSe0.pdat");
            imageEqumentWandLowDS = Util.loadImage(tempByte[7]);
            imageEqumentWandNormalDS = Util.loadImage(tempByte[8]);
            imageEqumentWandSuperDS = Util.loadImage(tempByte[9]);
            imageEqumentWandSSuperDS = Util.loadImage(tempByte[25]);
            imageEqumentWandSSSuperDS = Util.loadImage(tempByte[29]);
            picDatasEqumentPearlDS = Util.readPdatFile("/DSe1.pdat");
            imageEqumentPearlLowDS = Util.loadImage(tempByte[10]);
            imageEqumentPearlNormalDS = Util.loadImage(tempByte[11]);
            imageEqumentPearlSuperDS = Util.loadImage(tempByte[12]);
            imageEqumentPearlSSuperDS = Util.loadImage(tempByte[26]);
            imageEqumentPearlSSSuperDS = Util.loadImage(tempByte[30]);
            picDatasEqumentWandYS = Util.readPdatFile("/DSe0.pdat");
            imageEqumentWandLowYS = Util.loadImage(tempByte[7]);
            imageEqumentWandNormalYS = Util.loadImage(tempByte[8]);
            imageEqumentWandSuperYS = Util.loadImage(tempByte[9]);
            imageEqumentWandSSuperYS = Util.loadImage(tempByte[25]);
            imageEqumentWandSSSuperYS = Util.loadImage(tempByte[29]);
            picDatasEqumentPearlYS = Util.readPdatFile("/DSe1.pdat");
            imageEqumentPearlLowYS = Util.loadImage(tempByte[10]);
            imageEqumentPearlNormalYS = Util.loadImage(tempByte[11]);
            imageEqumentPearlSuperYS = Util.loadImage(tempByte[12]);
            imageEqumentPearlSSuperYS = Util.loadImage(tempByte[26]);
            imageEqumentPearlSSSuperYS = Util.loadImage(tempByte[30]);
            picDatasEqumentAxeJK = Util.readPdatFile("/JKe0.pdat");
            imageEqumentAxeLowJK = Util.loadImage(tempByte[14]);
            imageEqumentAxeNormalJK = Util.loadImage(tempByte[15]);
            imageEqumentAxeSuperJK = Util.loadImage(tempByte[16]);
            imageEqumentAxeSSuperJK = Util.loadImage(tempByte[23]);
            imageEqumentAxeSSSuperJK = Util.loadImage(tempByte[31]);
            picDatasEqumentSwordJK = Util.readPdatFile("/JKe1.pdat");
            imageEqumentSwordLowJK = Util.loadImage(tempByte[17]);
            imageEqumentSwordNormalJK = Util.loadImage(tempByte[18]);
            imageEqumentSwordSuperJK = Util.loadImage(tempByte[19]);
            imageEqumentSwordSSuperJK = Util.loadImage(tempByte[24]);
            imageEqumentSwordSSSuperJK = Util.loadImage(tempByte[32]);
            picDatasEqumentDaggerCK = Util.readPdatFile("/CKe0.pdat");
            imageEqumentDaggerLowCK = Util.loadImage(tempByte[0]);
            imageEqumentDaggerNormalCK = Util.loadImage(tempByte[1]);
            imageEqumentDaggerSuperCK = Util.loadImage(tempByte[2]);
            imageEqumentDaggerSSuperCK = Util.loadImage(tempByte[21]);
            imageEqumentDaggerSSSuperCK = Util.loadImage(tempByte[27]);
            picDatasEqumentSwordCK = Util.readPdatFile("/CKe1.pdat");
            imageEqumentSwordLowCK = Util.loadImage(tempByte[3]);
            imageEqumentSwordNormalCK = Util.loadImage(tempByte[4]);
            imageEqumentSwordSuperCK = Util.loadImage(tempByte[5]);
            imageEqumentSwordSSuperCK = Util.loadImage(tempByte[22]);
            imageEqumentSwordSSSuperCK = Util.loadImage(tempByte[28]);
            picDatasSpecificCK = Util.readPdatFile("/CKs0.pdat");
            imageSpecificCK = Util.loadImage(tempByte[6]);
            Util.isInitRes[1] = true;
        }

    }

    GameObj() {
        int i;
        for(i = 0; i < bufferState.length; ++i) {
            bufferState[i] = false;
        }

        hpStates = new Stack();

        for(i = 0; i < hpChange.length; ++i) {
            hpChange[i][0] = -1;
            hpChange[i][1] = 0;
            hpChange[i][2] = 0;
        }

    }

    public GameObj clone() {
        GameObj result = new GameObj();
        result.type = type;
        result.gender = gender;
        result.profession = profession;
        result.imgID = imgID;
        result.originalImgID = originalImgID;
        result.currentImgID = currentImgID;
        result.originalWeapon = originalWeapon;
        result.currentWeapon = currentWeapon;
        result.objID = objID;
        result.name = name;
        result.level = level;
        result.curHp = curHp;
        result.curMp = curMp;
        result.maxHp = maxHp;
        result.maxMp = maxMp;
        result.aimRow = aimRow;
        result.aimColumn = aimColumn;
        result.row = row;
        result.col = col;
        return result;
    }

    public String toString() {
        String result = "";
        switch (type) {
            case 1:
                result = result + "OthePlayer";
                break;
            case 2:
                result = result + "Monster";
                break;
            case 3:
                result = result + "NPC";
                break;
            case 4:
                result = result + "Player";
        }

        result = result + ":" + objID;
        return result;
    }

    public void draw(Graphics g) {
        switch (type) {
            case 1:
                if (Cons.showOtherPlayer) {
                    if (imgID < 8) {
                        setMotionIndex();
                        drawPES(g);
                    } else {
                        drawEnemy(g);
                    }

                    if (isPlayerWife == 1) {
                        int tx = x - Map.currentWindowX;
                        int ty = y - Map.currentWindowY;
                        tx -= 7;
                        if (Cons.zeroShort <= 0) {
                            ty -= 58;
                        } else if (!Cons.showName && (Cons.showName || this != ObjManager.currentTarget)) {
                            ty -= 45;
                        } else {
                            ty -= 58;
                        }

                        ++marryArrowTick;
                        if (marryArrowTick >= MARRYARROW.length) {
                            marryArrowTick = 0;
                        }

                        if (title != null && !title.equals("")) {
                            ty -= UIComponent.charH;
                        }

                        MainCanvas.imgMarryArrow.draw(g, tx, ty, MARRYARROW[marryArrowTick], false);
                    }
                } else if (Player.getInstance().group != group) {
                    if (imgID < 8) {
                        setMotionIndex();
                        drawPES(g);
                    } else {
                        drawEnemy(g);
                    }
                }
                break;
            case 2:
                drawEnemy(g);
                break;
            case 3:
                drawNpc(g);
                break;
            case 4:
                if (imgID < 8) {
                    if (curHp <= 0) {
                        setState((byte)5);
                    }

                    setMotionIndex();
                    drawPES(g);
                } else {
                    drawEnemy(g);
                }
        }

    }

    public void drawTitle(Graphics g) {
        if (name == null) {
            name = "";
        }

        int eventOffY = 0;
        int curColor = 0;
        int bakColor = 0;
        if (!Cons.showName && (Cons.showName || this != ObjManager.currentTarget)) {
            if (type == 3 && eventState != 0) {
                if (eventState == 3) {
                    if (MainCanvas.countTick % 6 < 3) {
                        ++eventOffY;
                    }
                } else if (eventState == 4) {
                    ++eventOffY;
                    if (MainCanvas.countTick % 6 < 3) {
                        ++eventOffY;
                    }
                }

                MainCanvas.mImgUI[35].draw(g, x - Map.currentWindowX - 3, y - Map.currentWindowY - 46, eventState - 1 + eventOffY, false);
            }
        } else {
            int offX = 0;
            if (type != 4 && (type != 2 || this == ObjManager.currentTarget)) {
                if (type == 3 && eventState != 0) {
                    offX = 9;
                }

                if (type == 2) {
                    if (action == 0) {
                        curColor = 16776960;
                    } else {
                        curColor = 16711680;
                    }
                } else if (type == 3) {
                    curColor = 65535;
                    if (offX != 0) {
                        if (eventState == 3) {
                            if (MainCanvas.countTick % 6 < 3) {
                                ++eventOffY;
                            }
                        } else if (eventState == 4) {
                            ++eventOffY;
                            if (MainCanvas.countTick % 6 < 3) {
                                ++eventOffY;
                            }
                        }

                        MainCanvas.mImgUI[35].draw(g, x - Map.currentWindowX - (MainCanvas.font[1].stringWidth(name) >> 1), y - Map.currentWindowY - 46, eventState - 1 + eventOffY, false);
                    }
                } else if (type == 1) {
                    if (Player.getInstance().group == group) {
                        if (isEnemy) {
                            curColor = 16711680;
                            if (isReincarnation == 1) {
                                curColor = 16711878;
                            }
                        } else {
                            curColor = 65280;
                            if (ObjManager.currentTarget.objID == objID && Cons.showName) {
                                curColor = 5954819;
                            }

                            if (isReincarnation == 1) {
                                curColor = 1745905;
                            }
                        }
                    } else {
                        curColor = 16711680;
                        if (isReincarnation == 1) {
                            curColor = 16711878;
                        }
                    }
                }

                if (Cons.showOtherPlayer) {
                    if (offX == 0) {
                        g.setColor(bakColor);
                        g.drawString(name, x - Map.currentWindowX + 1, y - Map.currentWindowY - 44 + 1, 17);
                        if (title != null && !title.equals("")) {
                            g.drawString(title, x - Map.currentWindowX + 1, y - Map.currentWindowY - 44 + 1 - UIComponent.charH, 17);
                        }

                        g.setColor(curColor);
                        g.drawString(name, x - Map.currentWindowX, y - Map.currentWindowY - 44, 17);
                        if (title != null && !title.equals("")) {
                            g.drawString(title, x - Map.currentWindowX, y - Map.currentWindowY - 44 - UIComponent.charH, 17);
                        }
                    } else {
                        g.setColor(bakColor);
                        g.drawString(name, x - Map.currentWindowX - (MainCanvas.font[1].stringWidth(name) >> 1) + offX + 1, y - Map.currentWindowY - 44 + 1, 20);
                        if (title != null && !title.equals("")) {
                            g.drawString(title, x - Map.currentWindowX - (MainCanvas.font[1].stringWidth(name) >> 1) + offX + 1, y - Map.currentWindowY - 44 + 1 - UIComponent.charH, 20);
                        }

                        g.setColor(curColor);
                        g.drawString(name, x - Map.currentWindowX - (MainCanvas.font[1].stringWidth(name) >> 1) + offX, y - Map.currentWindowY - 44, 20);
                        if (title != null && !title.equals("")) {
                            g.drawString(title, x - Map.currentWindowX - (MainCanvas.font[1].stringWidth(name) >> 1) + offX, y - Map.currentWindowY - 44 - UIComponent.charH, 20);
                        }
                    }
                } else if (type != 1 || type == 1 && Player.getInstance().group != group) {
                    if (offX == 0) {
                        g.setColor(bakColor);
                        g.drawString(name, x - Map.currentWindowX + 1, y - Map.currentWindowY - 44 + 1, 17);
                        if (title != null && !title.equals("")) {
                            g.drawString(title, x - Map.currentWindowX + 1, y - Map.currentWindowY - 44 + 1 - UIComponent.charH, 17);
                        }

                        g.setColor(curColor);
                        g.drawString(name, x - Map.currentWindowX, y - Map.currentWindowY - 44, 17);
                        if (title != null && !title.equals("")) {
                            g.drawString(title, x - Map.currentWindowX, y - Map.currentWindowY - 44 - UIComponent.charH, 17);
                        }
                    } else {
                        g.setColor(bakColor);
                        g.drawString(name, x - Map.currentWindowX - (MainCanvas.font[1].stringWidth(name) >> 1) + offX + 1, y - Map.currentWindowY - 44 + 1, 20);
                        if (title != null && !title.equals("")) {
                            g.drawString(title, x - Map.currentWindowX - (MainCanvas.font[1].stringWidth(name) >> 1) + offX + 1, y - Map.currentWindowY - 44 + 1 - UIComponent.charH, 20);
                        }

                        g.setColor(curColor);
                        g.drawString(name, x - Map.currentWindowX - (MainCanvas.font[1].stringWidth(name) >> 1) + offX, y - Map.currentWindowY - 44, 20);
                        if (title != null && !title.equals("")) {
                            g.drawString(title, x - Map.currentWindowX - (MainCanvas.font[1].stringWidth(name) >> 1) + offX, y - Map.currentWindowY - 44 - UIComponent.charH, 20);
                        }
                    }
                }
            }
        }

    }

    public void drawHpChange(Graphics g) {
        if (Cons.showNum) {
            int i = 0;

            for(int kk = hpChange.length; i < kk; ++i) {
                if (hpChange[i][0] == 0) {
                    int[] tmpNum = null;
                    int tmp = hpChange[i][2];
                    if (tmp == 0) {
                        if (type != 4 && (this != ObjManager.currentTarget || type == 2 || this == Player.getInstance().pkObj)) {
                            MainCanvas.mImgUI[37].draw(g, x - Map.currentWindowX - 13, y - Map.currentWindowY - hpChange[i][1] * 2 - 25, 1, false);
                        } else {
                            MainCanvas.mImgUI[37].draw(g, x - Map.currentWindowX - 13, y - Map.currentWindowY - hpChange[i][1] * 2 - 25, 0, false);
                        }
                    } else {
                        if (tmp < 0) {
                            tmp = -tmp;
                        }

                        int length = 1;
                        int tt = tmp;

                        do {
                            ++length;
                            tt /= 10;
                        } while(tt != 0);

                        tmpNum = new int[length];
                        tmpNum[0] = 0;

                        for(int j = length - 1; j > 0; --j) {
                            tmpNum[j] = tmp % 10 + 1;
                            tmp /= 10;
                        }

                        MImage tmpImg = null;
                        if (type == 4 || this == ObjManager.currentTarget && type != 2 && Player.getInstance().pkObj != null && this != Player.getInstance().pkObj) {
                            if (hpChange[i][2] > 0) {
                                tmpImg = MainCanvas.imgGreenNum;
                            } else {
                                tmpImg = MainCanvas.imgRedNum;
                            }
                        } else if (hpChange[i][2] < 0) {
                            if (isBaoJi) {
                                tmpImg = MainCanvas.imgYellowNum;
                            } else {
                                tmpImg = MainCanvas.imgWhiteNum;
                            }
                        } else {
                            tmpImg = MainCanvas.imgGreenNum;
                        }

                        if (tmpImg != null) {
                            int j = 0;

                            for(int kk1 = tmpNum.length; j < kk1; ++j) {
                                tmpImg.draw(g, x - tmpImg.frame_w * (tmpNum.length >> 1) - Map.currentWindowX + tmpImg.frame_w * j, y - Map.currentWindowY - (hpChange[i][1] << 1) - 15, tmpNum[j], false);
                            }
                        }
                    }
                }
            }
        }

    }

    private void drawPES(Graphics g) {
        int frameIndex = 0;
        int tx = x - Map.currentWindowX;
        int ty = y - Map.currentWindowY;

        try {
            frameIndex = motionDataAll[curMotionAndSpecificIndex][curFrameIndex];
            Util.drawRoleEditFrame(tempImagePeople, g, frameDataPeople[frameIndex], picDatasPeople, tx, ty, isMirror);
            if (curWeapon != -1) {
                Util.drawRoleEditFrame(testImageEqument, g, frameDataEqument[frameIndex], picDatasEqument, tx, ty, isMirror);
            }

            Util.drawRoleEditFrame(testImageSpecific, g, frameDataSpecific[frameIndex], picDatasSpecific, tx, ty, isMirror);
        } catch (Exception var6) {
            Exception e = var6;
            e.printStackTrace();
        }

    }

    public static void initMosterRES(byte[] mosterArr) {
        mosterImage = new Image[mosterArr.length];
        mosterFrameData = new short[mosterArr.length][][];
        mosterPicData = new short[mosterArr.length][];
        mosterMotionDataAll = new short[mosterArr.length][][];

        for(int i = 0; i < mosterArr.length; ++i) {
            int j = mosterArr[i];
            if (mosterArr[i] <= 4 && mosterArr[i] >= 0) {
                j = 0;
            } else if (mosterArr[i] == 5) {
                j = 5;
            } else if (mosterArr[i] <= 10 && mosterArr[i] >= 6 || mosterArr[i] <= 52 && mosterArr[i] >= 49) {
                j = 6;
            } else if ((mosterArr[i] > 15 || mosterArr[i] < 11) && (mosterArr[i] > 60 || mosterArr[i] < 58)) {
                if ((mosterArr[i] > 19 || mosterArr[i] < 16) && (mosterArr[i] > 63 || mosterArr[i] < 62)) {
                    if (mosterArr[i] <= 23 && mosterArr[i] >= 20 || mosterArr[i] <= 54 && mosterArr[i] >= 53) {
                        j = 20;
                    } else if (mosterArr[i] <= 25 && mosterArr[i] >= 24) {
                        j = 24;
                    } else if (mosterArr[i] <= 27 && mosterArr[i] >= 26) {
                        j = 26;
                    } else if ((mosterArr[i] > 30 || mosterArr[i] < 28) && (mosterArr[i] > 57 || mosterArr[i] < 55)) {
                        if (mosterArr[i] <= 33 && mosterArr[i] >= 31) {
                            j = 31;
                        } else if (mosterArr[i] != 34 && mosterArr[i] != 64) {
                            if ((mosterArr[i] > 36 || mosterArr[i] < 35) && (mosterArr[i] > 48 || mosterArr[i] < 42)) {
                                if (mosterArr[i] != 37 && mosterArr[i] != 61) {
                                    if (mosterArr[i] <= 41 && mosterArr[i] >= 38) {
                                        j = 38;
                                    }
                                } else {
                                    j = 37;
                                }
                            } else {
                                j = 35;
                            }
                        } else {
                            j = 34;
                        }
                    } else {
                        j = 28;
                    }
                } else {
                    j = 16;
                }
            } else {
                j = 11;
            }

            mosterImage[i] = Util.loadImage(Util.readPKG("/qqe.pkg", "qqm" + j + ".png"));
            mosterFrameData[i] = Util.readFdatFile("/qqm" + j + ".fdat", 0);
            mosterPicData[i] = Util.readPdatFile("/qqm" + j + ".pdat");
            mosterMotionDataAll[i] = Util.readMdatFile("/qqm" + j + ".mdat");
        }

    }

    private void drawNpc(Graphics g) {
        Util.drawRoleEditFrame(npcImage, g, npcFrameData[npcMotionDataAll[0][imgID - OFFSET_NPC]], npcPicData, x - Map.currentWindowX, y - Map.currentWindowY, false);
    }

    public static void drawNpcForUi(Graphics g, int argImgID, int argX, int argY) {
        short[] frameDatas = npcFrameData[npcMotionDataAll[0][argImgID - OFFSET_NPC]];
        Util.drawRoleEditFrame(npcImage, g, frameDatas, npcPicData, argX, argY, false);
    }

    public void setCurWeapon(int argWeapon) {
        curWeapon = (byte)argWeapon;
    }

    public static void releaseMonsterRes() {
    }

    public static void releaseWhiteRes() {
    }

    public static void releaseBlackRes() {
    }

    private void setEnemyFrame() {
        int tempImgID = imgID - 40;
        switch (direction) {
            case 1:
            case 5:
                setMirror(false);
                switch (state) {
                    case 0:
                        curMotionAndSpecificIndex = ENEMY_MOTION[tempImgID][2];
                        return;
                    case 1:
                        curMotionAndSpecificIndex = ENEMY_MOTION[tempImgID][5];
                        return;
                    case 2:
                        curMotionAndSpecificIndex = ENEMY_MOTION[tempImgID][8];
                        return;
                    case 3:
                    case 4:
                    default:
                        curMotionAndSpecificIndex = ENEMY_MOTION[tempImgID][2];
                        return;
                    case 5:
                        curMotionAndSpecificIndex = ENEMY_MOTION[tempImgID][14];
                        return;
                }
            case 2:
            case 8:
                setMirror(false);
                switch (state) {
                    case 0:
                        curMotionAndSpecificIndex = ENEMY_MOTION[tempImgID][0];
                        return;
                    case 1:
                        curMotionAndSpecificIndex = ENEMY_MOTION[tempImgID][3];
                        return;
                    case 2:
                        curMotionAndSpecificIndex = ENEMY_MOTION[tempImgID][6];
                        return;
                    case 3:
                    case 4:
                    default:
                        curMotionAndSpecificIndex = ENEMY_MOTION[tempImgID][0];
                        return;
                    case 5:
                        curMotionAndSpecificIndex = ENEMY_MOTION[tempImgID][12];
                        return;
                }
            case 3:
            case 4:
            case 6:
            case 7:
                if (direction != 3 && direction != 7) {
                    if (direction == 4 || direction == 6) {
                        setMirror(true);
                    }
                } else {
                    setMirror(false);
                }

                switch (state) {
                    case 0:
                        curMotionAndSpecificIndex = ENEMY_MOTION[tempImgID][1];
                        break;
                    case 1:
                        curMotionAndSpecificIndex = ENEMY_MOTION[tempImgID][4];
                        break;
                    case 2:
                        curMotionAndSpecificIndex = ENEMY_MOTION[tempImgID][7];
                        break;
                    case 3:
                    case 4:
                    default:
                        curMotionAndSpecificIndex = ENEMY_MOTION[tempImgID][1];
                        break;
                    case 5:
                        curMotionAndSpecificIndex = ENEMY_MOTION[tempImgID][13];
                }
        }

    }

    private void drawEnemy(Graphics g) {
        if (imgID >= 60 && imgID <= 63) {
            g.drawImage(imgShadowImmortal, x - (imgShadowImmortal.getWidth() >> 1) - Map.currentWindowX, y - (imgShadowImmortal.getHeight() >> 1) - Map.currentWindowY, 20);
        }

        setEnemyFrame();
        Util.drawRoleEditFrame(tempImagePeople, g, frameDataPeople[motionDataAll[curMotionAndSpecificIndex][curFrameIndex]], picDatasPeople, x - Map.currentWindowX, y - Map.currentWindowY, isMirror);
    }

    private void setMirror(boolean argIsMirror) {
        isMirror = argIsMirror;
    }

    public static final void initWhitePeopleRes() {
        if (!Util.isInitRes[3]) {
            byte[][] tempByte = new byte[4][];
            String[] tempString = new String[]{"DSpW.png", "JKpW.png", "YSpW.png", "CKpW.png"};
            tempByte = Util.readPKG("/qqwp.pkg", tempString);
            imagePeopleWhiteDS = Util.loadImage(tempByte[0]);
            imagePeopleWhiteJK = Util.loadImage(tempByte[1]);
            imagePeopleWhiteYS = Util.loadImage(tempByte[2]);
            imagePeopleWhiteCK = Util.loadImage(tempByte[3]);
            picDatasPeopleWhiteDS = Util.readPdatFile("/DSpW.pdat");
            motionDataAllWhiteDS = Util.readMdatFile("/DSaW.mdat");
            frameDataPeopleWhiteDS = Util.readFdatFile("/DSpW.fdat", 0);
            frameDataEqumentWandWhiteDS = Util.readFdatFile("/DSeWw.fdat", OFFSET_EQUMENT_AND_SPECIFIC[2][0]);
            frameDataEqumentPearlWhiteDS = Util.readFdatFile("/DSeWp.fdat", OFFSET_EQUMENT_AND_SPECIFIC[2][0]);
            frameDataSpecificWhiteDS = Util.readFdatFile("/DSsW.fdat", OFFSET_EQUMENT_AND_SPECIFIC[2][1]);
            picDatasPeopleWhiteJK = Util.readPdatFile("/JKpW.pdat");
            motionDataAllWhiteJK = Util.readMdatFile("/JKaW.mdat");
            frameDataPeopleWhiteJK = Util.readFdatFile("/JKpW.fdat", 0);
            frameDataEqumentSwordWhiteJK = Util.readFdatFile("/JKeWs.fdat", OFFSET_EQUMENT_AND_SPECIFIC[1][0]);
            frameDataEqumentAxeWhiteJK = Util.readFdatFile("/JKeWa.fdat", OFFSET_EQUMENT_AND_SPECIFIC[1][0]);
            frameDataSpecificWhiteJK = Util.readFdatFile("/JKsW.fdat", OFFSET_EQUMENT_AND_SPECIFIC[1][1]);
            picDatasPeopleWhiteYS = Util.readPdatFile("/YSpW.pdat");
            motionDataAllWhiteYS = Util.readMdatFile("/YSaW.mdat");
            frameDataPeopleWhiteYS = Util.readFdatFile("/YSpW.fdat", 0);
            frameDataEqumentWandWhiteYS = Util.readFdatFile("/YSeWw.fdat", OFFSET_EQUMENT_AND_SPECIFIC[3][0]);
            frameDataEqumentPearlWhiteYS = Util.readFdatFile("/YSeWp.fdat", OFFSET_EQUMENT_AND_SPECIFIC[3][0]);
            frameDataSpecificWhiteYS = Util.readFdatFile("/YSsW.fdat", OFFSET_EQUMENT_AND_SPECIFIC[3][1]);
            picDatasPeopleWhiteCK = Util.readPdatFile("/CKpW.pdat");
            motionDataAllWhiteCK = Util.readMdatFile("/CKaW.mdat");
            frameDataPeopleWhiteCK = Util.readFdatFile("/CKpW.fdat", 0);
            frameDataEqumentSwordWhiteCK = Util.readFdatFile("/CKeWs.fdat", OFFSET_EQUMENT_AND_SPECIFIC[0][0]);
            frameDataEqumentDaggerWhiteCK = Util.readFdatFile("/CKeWd.fdat", OFFSET_EQUMENT_AND_SPECIFIC[0][0]);
            frameDataSpecificWhiteCK = Util.readFdatFile("/CKsW.fdat", OFFSET_EQUMENT_AND_SPECIFIC[0][1]);
            Util.isInitRes[3] = true;
        }

    }

    public static final void initBlackPeopleRes() {
        if (!Util.isInitRes[4]) {
            byte[][] tempByte = new byte[4][];
            String[] tempString = new String[]{"DSpB.png", "JKpB.png", "YSpB.png", "CKpB.png"};
            tempByte = Util.readPKG("/qqbp.pkg", tempString);
            imagePeopleBlackDS = Util.loadImage(tempByte[0]);
            imagePeopleBlackJK = Util.loadImage(tempByte[1]);
            imagePeopleBlackYS = Util.loadImage(tempByte[2]);
            imagePeopleBlackCK = Util.loadImage(tempByte[3]);
            picDatasPeopleBlackDS = Util.readPdatFile("/DSpB.pdat");
            motionDataAllBlackDS = Util.readMdatFile("/DSaB.mdat");
            frameDataPeopleBlackDS = Util.readFdatFile("/DSpB.fdat", 0);
            frameDataEqumentWandBlackDS = Util.readFdatFile("/DSeBw.fdat", OFFSET_EQUMENT_AND_SPECIFIC[7][0]);
            frameDataEqumentPearlBlackDS = Util.readFdatFile("/DSeBp.fdat", OFFSET_EQUMENT_AND_SPECIFIC[7][0]);
            frameDataSpecificBlackDS = Util.readFdatFile("/DSsB.fdat", OFFSET_EQUMENT_AND_SPECIFIC[7][1]);
            picDatasPeopleBlackJK = Util.readPdatFile("/JKpB.pdat");
            motionDataAllBlackJK = Util.readMdatFile("/JKaB.mdat");
            frameDataPeopleBlackJK = Util.readFdatFile("/JKpB.fdat", 0);
            frameDataEqumentSwordBlackJK = Util.readFdatFile("/JKeBs.fdat", OFFSET_EQUMENT_AND_SPECIFIC[6][0]);
            frameDataEqumentAxeBlackJK = Util.readFdatFile("/JKeBa.fdat", OFFSET_EQUMENT_AND_SPECIFIC[6][0]);
            frameDataSpecificBlackJK = Util.readFdatFile("/JKsB.fdat", OFFSET_EQUMENT_AND_SPECIFIC[6][1]);
            picDatasPeopleBlackYS = Util.readPdatFile("/YSpB.pdat");
            motionDataAllBlackYS = Util.readMdatFile("/YSaB.mdat");
            frameDataPeopleBlackYS = Util.readFdatFile("/YSpB.fdat", 0);
            frameDataEqumentWandBlackYS = Util.readFdatFile("/YSeBw.fdat", OFFSET_EQUMENT_AND_SPECIFIC[4][0]);
            frameDataEqumentPearlBlackYS = Util.readFdatFile("/YSeBp.fdat", OFFSET_EQUMENT_AND_SPECIFIC[4][0]);
            frameDataSpecificBlackYS = Util.readFdatFile("/YSsB.fdat", OFFSET_EQUMENT_AND_SPECIFIC[4][1]);
            picDatasPeopleBlackCK = Util.readPdatFile("/CKpB.pdat");
            motionDataAllBlackCK = Util.readMdatFile("/CKaB.mdat");
            frameDataPeopleBlackCK = Util.readFdatFile("/CKpB.fdat", 0);
            frameDataEqumentSwordBlackCK = Util.readFdatFile("/CKeBs.fdat", OFFSET_EQUMENT_AND_SPECIFIC[5][0]);
            frameDataEqumentDaggerBlackCK = Util.readFdatFile("/CKeBd.fdat", OFFSET_EQUMENT_AND_SPECIFIC[5][0]);
            frameDataSpecificBlackCK = Util.readFdatFile("/CKsB.fdat", OFFSET_EQUMENT_AND_SPECIFIC[5][1]);
            Util.isInitRes[4] = true;
        }

    }

    private void setMotionIndex() {
        int idx2 = -1;
        int idx1 = state;
        switch (direction) {
            case 1:
            case 5:
                idx2 = 2;
                break;
            case 2:
            case 8:
                idx2 = 0;
                break;
            case 3:
            case 4:
            case 6:
            case 7:
                idx2 = 1;
        }

        switch (state) {
            case 0:
            case 1:
            case 2:
                if (curWeapon != -1) {
                    idx1 += 3;
                }
                break;
            case 3:
                idx1 = 8;
                break;
            case 4:
            case 5:
                idx1 = 6;
                break;
            case 6:
                idx1 = -1;
                break;
            case 7:
                idx1 = 7;
                break;
            case 8:
                idx1 = 8;
                break;
            case 9:
                idx1 = 0;
                if (curWeapon != -1) {
                    idx1 += 3;
                }
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            default:
                break;
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
                idx1 = 2;
                if (curWeapon != -1) {
                    idx1 += 3;
                }
                break;
            case 41:
            case 42:
                idx1 = 8;
                break;
            case 43:
            case 44:
            case 45:
                idx1 = 2;
                if (curWeapon != -1) {
                    idx1 += 3;
                }
        }

        if (direction != 4 && direction != 6) {
            setMirror(false);
        } else {
            setMirror(true);
        }

        if (curWeapon != 1 && curWeapon != 2 && curWeapon != 3 && curWeapon != 16 && curWeapon != 21) {
            if (curWeapon != 4 && curWeapon != 5 && curWeapon != 6 && curWeapon != 17 && curWeapon != 22) {
                if (curWeapon != 7 && curWeapon != 8 && curWeapon != 9 && curWeapon != 18 && curWeapon != 23) {
                    if (curWeapon != 10 && curWeapon != 11 && curWeapon != 12 && curWeapon != 19 && curWeapon != 24) {
                        if (curWeapon == 13 || curWeapon == 14 || curWeapon == 15 || curWeapon == 20 || curWeapon == 25) {
                            if (imgID == 4) {
                                frameDataEqument = frameDataEqumentPearlBlackYS;
                            } else if (imgID == 7) {
                                frameDataEqument = frameDataEqumentPearlBlackDS;
                            } else if (imgID == 3) {
                                frameDataEqument = frameDataEqumentPearlWhiteYS;
                            } else if (imgID == 2) {
                                frameDataEqument = frameDataEqumentPearlWhiteDS;
                            }

                            picDatasEqument = picDatasEqumentPearlDS;
                            if (curWeapon == 13) {
                                testImageEqument = imageEqumentPearlLowDS;
                            } else if (curWeapon == 14) {
                                testImageEqument = imageEqumentPearlNormalDS;
                            } else if (curWeapon == 15) {
                                testImageEqument = imageEqumentPearlSuperDS;
                            } else if (curWeapon == 20) {
                                testImageEqument = imageEqumentPearlSSuperDS;
                            } else if (curWeapon == 25) {
                                testImageEqument = imageEqumentPearlSSSuperDS;
                            }
                        }
                    } else {
                        if (imgID == 4) {
                            frameDataEqument = frameDataEqumentWandBlackYS;
                        } else if (imgID == 7) {
                            frameDataEqument = frameDataEqumentWandBlackDS;
                        } else if (imgID == 3) {
                            frameDataEqument = frameDataEqumentWandWhiteYS;
                        } else if (imgID == 2) {
                            frameDataEqument = frameDataEqumentWandWhiteDS;
                        }

                        picDatasEqument = picDatasEqumentWandDS;
                        if (curWeapon == 10) {
                            testImageEqument = imageEqumentWandLowDS;
                        } else if (curWeapon == 11) {
                            testImageEqument = imageEqumentWandNormalDS;
                        } else if (curWeapon == 12) {
                            testImageEqument = imageEqumentWandSuperDS;
                        } else if (curWeapon == 19) {
                            testImageEqument = imageEqumentWandSSuperDS;
                        } else if (curWeapon == 24) {
                            testImageEqument = imageEqumentWandSSSuperDS;
                        }
                    }
                } else {
                    if (imgID == 1) {
                        frameDataEqument = frameDataEqumentAxeWhiteJK;
                    } else if (imgID == 6) {
                        frameDataEqument = frameDataEqumentAxeBlackJK;
                    }

                    picDatasEqument = picDatasEqumentAxeJK;
                    if (curWeapon == 7) {
                        testImageEqument = imageEqumentAxeLowJK;
                    } else if (curWeapon == 8) {
                        testImageEqument = imageEqumentAxeNormalJK;
                    } else if (curWeapon == 9) {
                        testImageEqument = imageEqumentAxeSuperJK;
                    } else if (curWeapon == 18) {
                        testImageEqument = imageEqumentAxeSSuperJK;
                    } else if (curWeapon == 23) {
                        testImageEqument = imageEqumentAxeSSSuperJK;
                    }
                }
            } else {
                if (imgID == 0) {
                    frameDataEqument = frameDataEqumentDaggerWhiteCK;
                } else if (imgID == 5) {
                    frameDataEqument = frameDataEqumentDaggerBlackCK;
                }

                picDatasEqument = picDatasEqumentDaggerCK;
                if (curWeapon == 4) {
                    testImageEqument = imageEqumentDaggerLowCK;
                } else if (curWeapon == 5) {
                    testImageEqument = imageEqumentDaggerNormalCK;
                } else if (curWeapon == 6) {
                    testImageEqument = imageEqumentDaggerSuperCK;
                } else if (curWeapon == 17) {
                    testImageEqument = imageEqumentDaggerSSuperCK;
                } else if (curWeapon == 22) {
                    testImageEqument = imageEqumentDaggerSSSuperCK;
                }
            }
        } else {
            if (imgID == 0) {
                frameDataEqument = frameDataEqumentSwordWhiteCK;
            } else if (imgID == 1) {
                frameDataEqument = frameDataEqumentSwordWhiteJK;
            } else if (imgID == 6) {
                frameDataEqument = frameDataEqumentSwordBlackJK;
            } else if (imgID == 5) {
                frameDataEqument = frameDataEqumentSwordBlackCK;
            }

            picDatasEqument = picDatasEqumentSwordCK;
            if (curWeapon == 1) {
                testImageEqument = imageEqumentSwordLowCK;
            } else if (curWeapon == 2) {
                testImageEqument = imageEqumentSwordNormalCK;
            } else if (curWeapon == 3) {
                testImageEqument = imageEqumentSwordSuperCK;
            } else if (curWeapon == 16) {
                testImageEqument = imageEqumentSwordSSuperCK;
            } else if (curWeapon == 21) {
                testImageEqument = imageEqumentSwordSSSuperCK;
            }
        }

        curMotionAndSpecificIndex = (byte)animIdx[idx1][idx2];
    }

    public String getTitleDesc() {
        return titleDesc;
    }

    public void setTitleDesc(String titleDesc) {
        titleDesc = titleDesc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        title = title;
        if (title == null) {
            title = "";
        }
    }
    
    /**
     * 判断游戏对象距离
     * @param playerX
     * @param playerY
     * @param targetX
     * @param targetY
     * @param distance
     * @return 
     */
    public static boolean inDistance(int playerX, int playerY, int targetX, int targetY, int distance) {
        int dis = (playerX - targetX) * (playerX - targetX);
        dis += (playerY - targetY) * (playerY - targetY);
        int dis2 = distance * distance;
        if (dis < dis2) {
            return true;
        }
        return false;
    }
}
