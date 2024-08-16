
import com.nokia.mid.ui.FullCanvas;
import java.util.Random;
import java.util.Vector;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.TextField;

public class MainCanvas extends FullCanvas implements Runnable, CommandListener, ItemCommandListener {

    short[] monthlyItemID = null;
    String[] monthlyItemName = null;
    int[] monthlyItemLocation = null;
    byte[] monthlyItemMark = null;
    String monthlyTextInfo = null;
    boolean isMonthly = false;
    boolean isInBaoyueWait = false;
    private String localUN = "";
    private String localUP = "";
    boolean isUserRMS = false;
    public static String quickName = "";
    public static String quickPSW = "";
    public byte payChangeSerser = 0;
    boolean isNoItem = false;
    public boolean gm_mail_on = false;
    public byte valueAddRecommandPlayersCnt = 0;
    public byte arenaStatus = 0;
    static String oldRoleName = "";
    public byte worldmapplace = 0;
    public static byte firstLogon = 0;
    String repassportid = "";
    public static byte templevel = 0;
    public static boolean isHaveFinishedTask = false;
    public static boolean isHaveAttributes = false;
    public static byte packageSend = 0;
    public boolean transferstatusQuest = false;
    public boolean questNoPlace = false;
    public int questTargetID = 0;
    public byte questTargetPlace = -1;
    public byte questStoneStatus = -1;
    public static byte isDeadRock = 2;
    public static short sendTick = 0;
    public boolean transferstatus = false;
    public int friendTransferTarget = 0;
    byte isTestNum = 0;
    UIText txtMoney = null;
    public static int[] shyTime = null;
    public static final long SEND_TIME = 30000L;
    static boolean isDistributionBadge = true;
    public static byte MOBILE_TELEPHONE_TYPE = 1;
    private static final byte TOTAL_CREAT = 3;
    public static boolean[] isFirst = new boolean[3];
    public static byte[] isFirstRace = new byte[3];
    public static String[][] strPop = (String[][]) null;
    public static byte popPointer = 0;
    public static byte contentPointer = 0;
    public static final byte POP_ROW_NUM = 5;
    public static int pop_content_w = 100;
    public static final byte POP_RECT_X = 32;
    public static byte[] popRecord = new byte[]{0, 0, 0};
    public static MainCanvas mc;
    public static NetInterface ni;
    public static GInterface gi;
    public static Pets pet = null;
    public static UIGrid dramatisPackage = null;
    public static UIMenu NPCMenu = null;
    public MainEntry aMidlet = null;
    public static int screenW = 0;
    public static int screenH = 0;
    public static byte CHARW = Cons.FONT_SIZE[MOBILE_TELEPHONE_TYPE][0];
    public static byte CHARH = Cons.FONT_SIZE[MOBILE_TELEPHONE_TYPE][1];
    public static Font[] font;
    /**
     * 当前游戏界面 5-游戏中 23-加载地图中
     */
    private static byte curState = 0;
    private static byte oldState = 0;
    private long timeTaken;
    private long paintTaken;
    private volatile Thread self;
    private long[] curTime = new long[1];
    /**
     * 0-游戏中 1-右菜单 2-左键菜单 3-NPC对话面板 7-快速聊天 8-切换状态
     */
    public static byte gameState = 0;
    public static byte oldGameState = 0;
    public static byte gameState_gameRunState = 0;
    public static byte oldGameState_gameRunState = 0;
    /**
     * 5-世界地图
     */
    public static int gameState_rightMenuSubState = -1;
    public static int oldGameState_rightMenuSubState = -1;
    public static byte gameState_menuManState = 0;
    public static byte oldGameState_menuManState = 0;
    public static byte gameState_menuChatState = 0;
    public static byte oldGameState_menuChatState = 0;
    public static byte gameState_menuPetState = 0;
    public static byte oldGameState_menuPetState = 0;
    public static byte gameState_menuTaskState = 0;
    public static byte oldGameState_menuTaskState = 0;
    public static byte gameState_menuFriendState = 0;
    public static byte oldGameState_menuFriendState = 0;
    public static byte gameState_menuSetupState = 0;
    public static byte oldGameState_menuSetupState = 0;
    public static byte gameState_NPCMailState = 0;
    public static byte oldGameState_NPCMailState = 0;
    public static byte gameState_menuArmyState = 0;
    public static byte oldGameState_menuSrmyState = 0;
    public static byte gameState_menuHelpState = 0;
    public static byte oldGameState_menuHelpState = 0;
    public static byte gameState_menuMapState = 0;
    public static byte oldGameState_menuMapState = 0;
    public static int gameState_leftMenuSubState = -1;
    public static int oldGameState_leftMenuSubState = -1;
    public static byte gameState_menuSeeState = 0;
    public static byte oldGameState_menuSeeState = 0;
    public static byte gameState_menuAFState = 0;
    public static byte oldGameState_menuAFState = 0;
    public static byte gameState_menuGIState = 0;
    public static byte oldGameState_menuGIState = 0;
    public static byte gameState_menuPCState = 0;
    public static byte oldGameState_menuPCState = 0;
    public static byte gameState_menuBusinessState = 0;
    public static byte oldGameState_menuBusinessState = 0;
    public static byte gameState_menuPKState = 0;
    public static byte oldGameState_menuPKState = 0;
    public static byte gameState_NPCSubState = 0;
    public static byte oldGameState_NPCSubState = 0;
    public static byte gameState_NPCTaskState = 0;
    public static byte oldGameState_NPCTaskState = 0;
    public static byte gameState_NPCFixState = 0;
    public static byte oldGameState_NPCFixState = 0;
    public static byte gameState_NPCAdoptPetState = 0;
    public static byte oldGameState_NPCAdoptPetState = 0;
    public static byte gameState_NPCLabourUnionState = 0;
    public static byte oldGameState_NPCLabourUnionState = 0;
    public static byte gameState_NPCVendueState = 0;
    public static byte oldGameState_NPCVendueState = 0;
    public static byte gameState_NPCExchangeState = 0;
    public static byte oldGameState_NPCExchangeState = 0;
    public static byte gameState_OtherState = 0;
    public static byte oldGameState_OtherState = 1;
    public static final byte STATE_NONE = 0;
    public static final byte STATE_ERROR = 1;
    public static final byte STATE_LOADING = 2;
    public static final byte STATE_SPLASH = 3;
    public static final byte STATE_MAINMENU = 4;
    public static final byte STATE_GAMERUN = 5;
    public static final byte STATE_EXIT = 6;
    public static final byte STATE_SETUP = 7;
    public static final byte STATE_OPTION = 8;
    public static final byte STATE_ABOUT = 9;
    public static final byte STATE_LOGON_1 = 10;
    public static final byte STATE_LOGON_2 = 11;
    public static final byte STATE_LOGON_3 = 12;
    public static final byte STATE_LOGIN_4 = 13;
    public static final byte STATE_LOGIN_1 = 14;
    public static final byte STATE_LOGIN_2 = 15;
    public static final byte STATE_LOAD_INITGAME = 17;
    public static final byte STATE_WAITNET = 18;
    public static final byte STATE_IFBACKTO_MAINMENU = 19;
    public static final byte STATE_CHANGE_PASSWORD = 22;
    public static final byte STATE_WAIT_RES = 23;
    public static final byte STATE_FIRST_LOAD = 24;
    public static final byte STATE_SOUND_CLEW = 26;
    public static final byte STATE_HELP = 27;
    public static final byte STATE_SUB_HELP = 28;
    public static final byte STATE_BIND = 29;
    public static final byte STATE_CHARGE = 30;
    public static final byte STATE_AGREE = 31;
    public static final byte STATE_ADVERTISE = 33;
    public static final byte GAMESTATE_RUN = 0;
    public static final byte GAMESTATE_MENU_RIGHT = 1;
    public static final byte GAMESTATE_MENU_LEFT = 2;
    public static final byte GAMESTATE_NPC = 3;
    public static final byte GAMESTATE_LOADING = 4;
    public static final byte GAMESTATE_EXIT = 5;
    public static final byte GAMESTATE_LOADMAP = 6;
    public static final byte GAMESTATE_FAST_CHAT = 7;
    public static final byte GAMESTATE_OTHER_TEMPLATE = 8;
    public static final byte GAMESTATE_PAY_CHANGE_SERVER = 10;
    public static final byte OTHER_NPC_EMPTY = 0;
    public static final byte OTHER_DRAMATIS_DEATH = 1;
    public static final byte OTHER_SHORTCUT_9 = 2;
    public static final byte OTHER_CHANGE_MAP = 3;
    public static final byte OTHER_SEND_CHANGEMAP = 4;
    public static final byte MENU_RIGHT = -1;
    public static final byte MENU_DRAMATIS_CHARGE = 0;
    public static final byte MENU_DRAMATIS_BUY = 10;
    public static final byte MENU_DRAMATIS_ATTRIBUTE = 1;
    public static final byte MENU_DRAMATIS_PACKAGE = 2;
    public static final byte MENU_DRAMATIS_SOCIETY = 3;
    public static final byte MENU_DRAMATIS_TASK = 4;
    public static final byte MENU_WORLD_MAP = 5;
    public static final byte MENU_DRMATIS_ARMY = 6;
    public static final byte MENU_SETUP = 7;
    public static final byte MENU_HELP = 8;
    public static final byte MENU_LEAVE_GAME = 9;
    public static final byte MENU_DRAMATIS_SKILL = 20;
    public static final byte MENU_DRAMATIS_PET = 22;
    public static final byte MENU_COMPOSE = 23;
    public static final byte MENU_DRAMATIS_TITLE = 24;
    public static final byte MENU_VALUEADDED_CATLIST = 80;
    public static final byte MENU_RIGHT_WAIT = 100;
    public static boolean isPop = false;
    public static boolean isFristLevelUp = false;
    public static boolean isFristGame = false;
    public static boolean isTenLevel = false;
    public static boolean isFristInputNumber = false;
    public static boolean isFristEvent = false;
    public static final byte UI_HELP_NOTICE = 0;
    public static final byte UI_HELP_SERVER_CENTER = 1;
    public static final byte UI_HELP_BIND = 2;
    public static final byte UI_HELP_ANNOUNCE = 3;
    public static byte specialState = -1;
    public static final byte MENU_LEFT = -1;
    public static final byte MENU_SEE = 0;
    public static final byte MENU_ADD_FRIEND = 1;
    public static final byte MENU_GROUP_INVITE = 2;
    public static final byte MENU_PRIVATE_CHAT = 3;
    public static final byte MENU_BUSINESS = 4;
    public static final byte MENU_PK_INVITE = 5;
    public static final byte MENU_FOLLOW = 6;
    public static final byte MENU_CHEAT_CHECK = 7;
    public static final byte MENU_LEFT_WAIT = 100;
    public static final byte UI_SKILL_TREE = 0;
    public static final byte UI_SKILL_KEY = 1;
    public static final byte UI_MAN_SKILL = 2;
    public static final byte UI_MAP_WORLD = 0;
    public static final byte UI_MAP_NPCPOS = 1;
    public static final byte UI_FRIEND_LOOKUP = 0;
    public static final byte UI_FRIEND_ADD = 1;
    public static final byte UI_FRIEND_LIST = 2;
    public static final byte UI_BLACK_LIST = 3;
    public static final byte UI_UNION_MANAGE = 4;
    public static final byte UI_SETUP_DIRECTION = 0;
    public static final byte UI_SETUP_SHORTCUT_KEY = 1;
    public static final byte UI_SETUP_SHOW = 2;
    public static final byte UI_SETUP_FACTION = 3;
    public static final byte UI_PET_THINGS_LIST = 2;
    public static final byte UI_PET_COMPOSE_RESULT = 5;
    public static final byte UI_COMPOSE_SKILL = 6;
    public static final byte UI_COMPOSE_WEAPON = 7;
    public static final byte UI_COMPOSE_STUFF = 8;
    public static final byte UI_PET_WAIT = 100;
    public static final byte UI_MAIL_LOOKUP = 0;
    public static final byte UI_MAIL_WRITE = 1;
    public static final byte UI_NPC_MENU = 0;
    public static final byte UI_NPC_TALK = 1;
    public static final byte UI_NPC_TRADE = 2;
    public static final byte UI_NPC_FIX = 3;
    public static final byte UI_NPC_LEARN_SKILL = 4;
    public static final byte UI_NPC_ADOPT_PET = 5;
    public static final byte UI_NPC_LABOUR_UNION = 6;
    public static final byte UI_NPC_VENDUE = 7;
    public static final byte UI_NPC_SMITH = 8;
    public static final byte UI_NPC_STOREHOUSE = 9;
    public static final byte UI_NPC_EXCHANGE = 10;
    public static final byte UI_NPC_MAIL = 11;
    public static final byte UI_NPC_PK = 12;
    public static final byte UI_NPC_LEARN_SKILL_1 = 13;
    public static final byte UI_NPC_LEARN_SKILL_2 = 14;
    public static final byte UI_NPC_LEARN_SKILL_3 = 15;
    public static final byte UI_NPC_LEARN_SKILL_4 = 16;
    public static final byte UI_NPC_LEARN_SKILL_5 = 17;
    public static final byte UI_NPC_LEARN_SKILL_6 = 18;
    public static final byte UI_NPC_LEARN_SKILL_7 = 19;
    public static final byte UI_NPC_MARRIAGE = 20;
    public static final byte UI_NPC_MARRIAGE_DIVORCE = 21;
    public static final byte UI_NPC_TOP1 = 22;
    public static final byte UI_NPC_TOP2 = 23;
    public static final byte UI_NPC_TOP3 = 24;
    public static final byte UI_NPC_TOP4 = 25;
    public static final byte UI_NPC_BATTLEGROUND_RANK = 27;
    public static final byte UI_NPC_BATTLEGROUND_RATE = 28;
    public static final byte UI_NPC_AWARD0 = 31;
    public static final byte UI_NPC_AWARD1 = 32;
    public static final byte UI_NPC_AWARD2 = 33;
    public static final byte UI_NPC_ARENA = 34;
    public static final byte UI_NPC_TASK = 120;
    public static final byte UI_NPC_CLAN_WAR = 46;
    public static final byte UI_NPC_CLAN_ALTAR = 47;
    public static final byte UI_NPC_CLAN_REPAIR = 48;
    public static final byte UI_NPC_CLAN_SHOP = 49;
    public static final byte UI_NPC_CLAN_STORE = 50;
    public static final byte UI_NPC_CLAN_REFINE = 51;
    public static final byte UI_NPC_CLAN_TOP = 52;
    public static final byte UI_NPC_CLAN_MEM_TOP = 53;
    public static final byte UI_NPC_CLAN_EXITAREA = 54;
    public static final byte UI_NPC_CLAN_ENTERAREA = 55;
    public static final byte UI_NPC_SILK = 56;
    public static final byte UI_NPC_METEMPSYCHOSIS = 62;
    public static final byte UI_NPC_LEARN_SKILL_8 = 66;
    public static final byte UI_NPC_LEARN_SKILL_9 = 67;
    public static final byte UI_NPC_LEARN_SKILL_10 = 68;
    public static final byte UI_NPC_WAITING = 100;
    public static final byte UI_NPC_CLAN_QUESTION = 76;
    public static final byte UI_NPC_CLAN_LMTSTORE = 77;
    public static final byte UI_NPC_CLAN_FARM = 78;
    public static final byte UI_NPC_TITLE = 83;
    public static final byte UI_NPC_EXCHANGE_LIST = 0;
    public static final byte UI_NPC_EXCHANGE_SURE = 1;
    public static final byte UI_NPC_VENDUE_SALE = 0;
    public static final byte UI_NPC_VENDUE_LOOKUP = 1;
    public static final byte UI_NPC_VENDUE_BUY = 2;
    public static final byte UI_NPC_VENDUE_WAINTING = 100;
    public static final byte SPECIAL_MENU = -1;
    public static final byte SPECIAL_CHARGE = 0;
    public static final byte SPECIAL_CHECK = 1;
    public static final byte SPECIAL_BUY = 2;
    public static final byte KEY_0 = 0;
    public static final byte KEY_1 = 1;
    public static final byte KEY_2 = 2;
    public static final byte KEY_3 = 3;
    public static final byte KEY_4 = 4;
    public static final byte KEY_5 = 5;
    public static final byte KEY_6 = 6;
    public static final byte KEY_7 = 7;
    public static final byte KEY_8 = 8;
    public static final byte KEY_9 = 9;
    public static final byte KEY_LEFT = 10;
    public static final byte KEY_UP = 11;
    public static final byte KEY_RIGHT = 12;
    public static final byte KEY_DOWN = 13;
    public static final byte KEY_FIRE = 14;
    public static final byte KEY_STAR = 15;
    public static final byte KEY_POUND = 16;
    public static final byte KEY_SOFT1 = 17;
    public static final byte KEY_SOFT2 = 18;
    public static final byte KEY_SOFT3 = 19;
    public static final byte KEY_CANCLE = 20;
    public static final int KEY_CC = -8;
    public static int keyFlag = 0;
    private static int keyFlagIm = 0;
    private static final byte DELAY_TYPE_SPLASH = 0;
    private Image splashImage = null;
    private static byte splashIndex = 0;
    private static byte mainItemID = 0;
    public static int waitCnt = 0;
    public static int loadCount;
    public static int loadRequestCount;
    public static int countTick;
    public static int serverTick;
    public static byte rightMenuId = 0;
    public UITable topTables = null;
    public String[] topPos = null;
    public UITable npcPOSTables = null;
    public String[] npcPosXY = null;
    public static int[][] star = new int[20][5];
    public static final int[] fireColor = new int[]{16178818, 14468982, 12627300, 9338953};
    public static MImage mImageTitle;
    public static MImage mImageMenu;
    public static MImage mImageC;
    public static int titlecount = 0;
    public UIForm baseForm = null;
    /**
     * NPC面板
     */
    public UIForm npcForm = null;
    public UIScroll[] scrolls = new UIScroll[10];
    public UITable[] tables = new UITable[10];
    public UITextArea[] textArea = new UITextArea[10];
    public UIText[] texts = new UIText[10];
    public UIButton[] buttons = new UIButton[10];
    public UILabel[] labels = new UILabel[10];
    public UIRim[] rims = new UIRim[10];
    public UIGrid[] grids = new UIGrid[10];
    public UIMImage[] mImages = new UIMImage[10];
    public UIRadioButton[] rbs = new UIRadioButton[10];
    public UIMenu[] menus = new UIMenu[10];
    public UISkillTree tree = null;
    public Form commonForm = null;
    public TextField commonTextField = null;
    public TextField commontf = null;
    public Command commonOk = null;
    public Command commonBack = null;
    private Command commonClear = null;
    public Form chatForm = new Form("聊天");
    public TextField chatText = new TextField("", "", 30, 0);
    public Command chatOk = new Command("发送", 4, 2);
    public Command chatBack = new Command("返回", 2, 2);
    ChoiceGroup cg = null;
    public String kongMD5 = "";
    public String kong_url = "";
    public HttpConn httpConn;
    public static String userID = "";
    public static String userKey = "";
    public String jarFrom = "kong";
    public final String SUBGAME = "tianjie1";
    public String userName = "";
    public String userPassword = "";
    public static String[] playerNames;
    public static byte[][] playerInfos;
    public static String[][] playerInformations;
    public static boolean[] playerState = new boolean[3];
    public static byte[] playerAdd;
    public static String playerAddName;
    public static String Announce = "";
    public static byte choose_manID = 0;
    public static byte logon3_state = 0;
    public static byte manIndex = 0;
    public static String serverUrl = "";
    public static final int[] manX = new int[]{49, 91, 132, 132, 91, 49, 8, 8};
    public static final int[] manY = new int[]{7, 7, 40, 87, 121, 121, 87, 40};
    byte[] imgPlayerId = new byte[]{0, 1, 2, 3, 4, 5, 6, 7};
    public static final int[] manX1 = new int[]{28, 70, 110};
    public static final int[] manY1 = new int[]{29, 29, 29};
    public static final boolean[] isDraw = new boolean[]{true, true, true, true, true, true, true, true};
    public static boolean[] isSelect = new boolean[8];
    public static boolean[] isSelect1 = null;
    public static short[] equipStuffId = new short[8];
    public static byte[] equipImageId = new byte[8];
    public static String[] equipSruffName = new String[8];
    public static byte[] equipStuffNameLevel = new byte[8];
    public static short[] equipStuffType = new short[8];
    public byte equipPlace = 0;
    public byte isPackage = 0;
    public static short lookStuffPlace = 0;
    public static byte lookType = 0;
    public static byte[] compare = new byte[4];
    public static final byte MAX_NUMBER = 20;
    public int[] packageStuffPrice = new int[36];
    public int[] shopStuffPrice = new int[18];
    public byte tradeNumber = 0;
    public short tradeStuffId = 0;
    public byte tradePlace = 0;
    public static int selectManId = 0;
    public byte exchange = 0;
    public int storeMoney = 0;
    public byte stPlace = 0;
    public byte stNumber = 0;
    public byte discardPlace = 0;
    public int stMoney = 0;
    String[] taskDetail = new String[5];
    short[] taskStuffId = new short[2];
    byte taskSelectedId = 0;
    short selectedId = 0;
    byte[] taskStuffImageId = new byte[2];
    byte[] taskThingNumber = new byte[2];
    int[] taskId = null;
    int taskMoney = 0;
    int taskExperiecce = 0;
    int NPCIndex = 1;
    public static String chatName;
    public static int[] chatChannel = new int[20];
    public static String chatNowTarget = null;
    public static String[] chatContent = new String[20];
    public static int[] chatColor = new int[20];
    public static String chatNowSenderName;
    public static String chatNowContent;
    public static String chatNowString = "欢迎来到天劫世界#0";
    public static String chatSendString = "";
    public static byte chatNowChannel = 0;
    public static byte localChatChannel = 4;
    public static int chatFriendId = 0;
    public static int chatFrame = 0;
    public static String chatLocalInfo;
    public static byte chatSetup = 31;
    public static short postage = 0;
    public static short postageAd = 0;
    public static byte mailAddStuffNum = -1;
    public static short mailAddStuffId = -1;
    public static String mailName;
    public static String mailDetail;
    public static int mailAddMoney = 0;
    public static byte mailAddStuff = -1;
    public static int mailSendTo = -1;
    public static String[][] mailList;
    public static int mailSelectWhich;
    public static boolean isHaveNewMail = true;
    public byte dramatisPetId = 0;
    public byte petSkillId = 0;
    public String composeListTitle = null;
    public static byte auctionCanSell = -1;
    public static int auctionMoney = 0;
    public static byte auctionIndex = -1;
    public String[] auctionNames = null;
    public int[][] auctionParams = (int[][]) null;
    public UITable tblAuction = null;
    public int auctionItemIndex = 0;
    public long[] auctionId = null;
    public byte typeID;
    public static byte subAuction;
    public static byte page;
    public static final byte AUCTION_VIEW_SELF = 5;
    public static final byte AUCTION_VIEW_OTHER = 6;
    public int clanConfirmMoney = 0;
    public String clanName;
    public byte clanExit = 0;
    public String clanRequestName;
    public String clanRequestInfo;
    public byte addResult = 0;
    public String[] clanList;
    public byte clanManegeLevel = 0;
    public String[] clanMemberName;
    public int[] clanMemberId;
    public byte[] clanMemberLevel;
    public int clanTargerMemPointer;
    public byte clanTargerMemLevel;
    public byte whichList = 0;
    public short changeMax = 0;
    public int changeIndex = 0;
    public short smithId = 0;
    public int smithMoney = 0;
    public byte isHole = 1;
    public static int[] fixPrice = new int[8];
    public static int fixAllPrice = 0;
    public static byte fixPlace = 0;
    public boolean skillTreeFlag = false;
    public boolean skillSub = false;
    public static byte requestFriendPlace = 0;
    public static int friendSelectWhich;
    public static byte deleteFriendType = 0;
    public static String[][] friendList;
    public static int[] friendListID;
    public static String[][] blackList;
    public static int[] blackListID;
    public static byte friendAddType;
    public static boolean isMenu = false;
    public byte teamJob = 0;
    public Vector teamMates = new Vector();
    public int teamLeaderId = 0;
    public byte teamAutoConfirm = 0;
    public byte teamLeaderOperat = 0;
    public int teamTargetId = 0;
    public String TeamTargetName = null;
    public short[] npcHelpId = null;
    public String[] npcHelpItem = null;
    public static byte useStuffPlace = 0;
    public static String[] tempName = new String[2];
    public static byte remindIndex = 0;
    private static byte fastChatIndex = 0;
    public static int selectedManId = -1;
    public static final int[] KEYS = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    public static boolean[] shortcut_9 = new boolean[5];
    public static boolean isLogin = false;
    public static boolean isSendTP;
    public static UILabel stuffName;
    public static Image waitImg;
    public static Sound sound = new Sound(Cons.SOUND[0], Cons.SOUND[1], Cons.SOUNDPriority);
    public int table0curPointer = 0;
    public String content = null;
    public String gm_content = null;
    public int gm_id = 0;
    public static boolean isWapFirst = true;
    public static byte[] byEncryptChain = null;
    public static byte[] byEncryptChainLast = null;
    public static byte byEncryptChainPos = -1;
    public static byte byEncryptChainPosLast = -1;
    public static byte byLastEncryptID = 0;
    public static byte byCurEncryptID = 0;
    public static String topTitle;
    public static boolean isInGame = false;
    public static byte bindPopState = 0;
    String url_ip = "";
    public static final byte ATTRIBUTE_MENU = -1;
    public static final byte ATTRIBUTE_SKILL = 0;
    public static final byte ATTRIBUTE_MAN = 1;
    public static final byte ATTRIBUTE_COMPOSITE = 2;
    public static final byte ATTRIBUTE_TITLE = 3;
    boolean isAuto = false;
    boolean twoSelect = false;
    final String[] connectExplain = new String[]{"手机中网络设置接入点写入CMWAP，用户名密码均无，代理服务器为10.0.0.172，端口80。", "手机中网络设置接入点写入CMNET，用户名密码均无。", "系统自动选择手机适配的网络类型。部分手机可能不支持，则请选择其他联网方式。"};
    public byte chargedByte = 0;
    String imei = "";
    private String serverlist;
    public static String[][] updateListInfo;
    public static String[] updateServer;
    boolean isUpdateInfo = false;

    public synchronized void begin() {
        self = new Thread(this);
        self.start();
    }

    public synchronized void stop() {
        self = null;
    }
    public static boolean startTickUser = false;
    long userTime;
    long ut;
    final int waitUser;
    public boolean timeCheck;
    boolean haveGetServer;

    public MainCanvas(MainEntry midlet) {
        waitUser = 6000000;
        timeCheck = false;
        haveGetServer = false;
        imgSplashBg = null;
        imgSplash = new Image[3];
        frameCount = 0;
        isQuickLog = false;
        isInHelp = false;
        chatAlertStr = null;
        decompose = new byte[2];
        unionName = "";
        serverTable = null;
        divTable = null;
        getURLConnect = null;
        hasFirst = false;
        mainHelpIndex = 0;
        taskType = 0;
        keys = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        subBsnsState = 0;
        bsnsMyPackage = new byte[]{100, 100, 100, 100, 100, 100};
        bsnsSinalNumber = new byte[6];
        bsnsMyPackageNUM = new byte[6];
        bsnsOtherID = -1;
        bsOtherImID = 0;
        topForm = null;
        fireW = 1;
        fireH = 1;
        matrix = null;
        color = null;
        level = 0;
        count = 0;
        fireStartY = 83;
        specialToolTable = null;
        specialToolItems = null;
        start = 40;
        offsetf = 20;
        offset = 0;
        isSelectList = 0;
        SHOWALERT = "\n\n    1、 功能描述：\n角色A对角色B可开通上线提醒，角色B每次上线都会由系统给角色A对应的通行证账号绑定的手机号发送一条通知短信，(您与好友必须为绑定账号)同时扣除角色A对应的通行证账号3猛犸币，上线提醒的最少间隔时间为15分钟。（即B在t时刻上线，A会收到短信提醒，若B在t+5时刻再次上线，则A不会收到短信提醒，直到t+15时刻之后，若B上线，则A会再次收到短信提醒。）\n\n2、 扣费方式：\n开通短信提醒一方每收到一条提醒短信，则扣除该用户3猛犸币。\n\n3、 业务取消：\n在好友列表里点击‘取消上线提醒’即可。";
        SHOWEXPLAIN = "注：\n    1、 功能描述：\n玩家可以在游戏中给指定的好友玩家发短信。(您与好友必须为绑定账号)包括1对1发短信和群发功能。玩家在游戏中可以给好友（离线、在线均可）发送短信，也可在氏族中进行氏族成员的群发，在好友列表中进行好友成员的群发。短信内容为玩家自定义内容。\n\n    2、 扣费方式：\n每发送一条短信，成功后即扣除发送方3猛犸币；若群发，则每成功发送一人扣除猛犸币（如玩家群发成功发送给10人，则扣除该玩家30猛犸币）。";
        showLogonAlert = false;
        mcount = 0;
        selectForm = null;
        selectOk = null;
        selectBack = null;
        selectAll = null;
        selectDall = null;
        isGetingUserID = false;
        sound = new Sound(Cons.SOUND[0], Cons.SOUND[1], Cons.SOUNDPriority);
        try {
            mc = this;
            ni = NetInterface.getInstance();
            gi = GInterface.getInstance();
            ObjManager.getInstance();
            aMidlet = midlet;
            initSysValue();
            Cons.isCmobile = false;
            Cons.isTestC = false;
            Cons.isWapCharge = false;
            PCIncrementService.hasAgree = false;
            PCIncrementService.isChargeJar = false;
            isUserRMS = false;
            try {
                String cMobile = null;
                cMobile = aMidlet.getAppProperty("Cmobile");
                if (cMobile != null) {
                    cMobile.toLowerCase();
                    if (cMobile.equals("yes")) {
                        Cons.isCmobile = true;
                    }
                }
                String beiTest = null;
                beiTest = aMidlet.getAppProperty("CmobileTest");
                if (beiTest != null) {
                    beiTest.toLowerCase();
                    if (beiTest.equals("yes")) {
                        Cons.isTestC = true;
                    }
                }
                String wapCharge = null;
                wapCharge = aMidlet.getAppProperty("TestCharge");
                if (wapCharge != null) {
                    wapCharge.toLowerCase();
                    if (wapCharge.equals("yes")) {
                        Cons.isWapCharge = true;
                    }
                }
                String ver = null;
                ver = aMidlet.getAppProperty("ChangeVER");
                if (ver != null) {
                    Cons.VER = Integer.parseInt(ver.trim());
                }
                String chargeJar = null;
                chargeJar = aMidlet.getAppProperty("ChargeJar");
                if (chargeJar != null) {
                    chargeJar.toLowerCase();
                    if (chargeJar.equals("yes")) {
                        PCIncrementService.isChargeJar = true;
                        chargedByte = 1;
                    }
                }
                String sohu_wap = null;
                sohu_wap = aMidlet.getAppProperty("sohu");
                if (sohu_wap != null && !"".equals(sohu_wap.trim())) {
                    sohu_wap.toLowerCase();
                    (Download.getInstance()).WAP_SOHU = sohu_wap;
                }
                imei = System.getProperty("com.nokia.mid.imei");
                if (imei == null || "".equals(imei.trim())) {
                    imei = String.valueOf(System.currentTimeMillis());
                } else {
                    StringBuffer tmp = new StringBuffer();
                    for (int j = 0; j < imei.length(); j++) {
                        if ((imei.charAt(j) >= '0' && imei.charAt(j) <= '9') || (imei.charAt(j) >= 'a' && imei.charAt(j) <= 'z') || (imei.charAt(j) >= 'A' && imei.charAt(j) <= 'Z')) {
                            tmp.append(imei.charAt(j));
                        }
                    }
                    imei = tmp.toString();
                }
                String istest = null;
                istest = aMidlet.getAppProperty("isTest");
                if (istest != null && !"".equals(istest.trim())) {
                    isTestNum = (byte) Integer.parseInt(istest.trim());
                }
                repassportid = aMidlet.getAppProperty("suggest");
                if (repassportid == null || "".equals(repassportid.trim())) {
                    repassportid = "";
                }
                String sms = aMidlet.getAppProperty("testsms");
                if (sms != null && "1".equals(sms.trim())) {
                    Cons.URL_KONG_IP = "202.108.44.149:9081";
                    Cons.URL_KONG_IP2 = "/judgeserver/judge?ver=2&";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            url_ip = Cons.isTestC ? "passport.ebsee.com" : "passport.ebsee.com";
            try {
                Cons.phoneType = System.getProperty("microedition.platform");
            } catch (Exception e) {
                e.printStackTrace();
            }
            pop_content_w = screenW - 64 - 14;
            tempName = Util.readRecord("userNW", 2);
            if (tempName == null) {
                tempName = new String[2];
            }
            byte[] dataSetup = Util.readRecord("directionOp");
            if (dataSetup != null && dataSetup.length == 2) {
                Cons.use2468 = (dataSetup[0] == 1);
                Cons.use5 = (dataSetup[1] == 1);
            }
            byte[] temp = Util.readRecord("channelOp");
            if (temp != null && temp.length == 4) {
                Cons.nineShort = temp[2];
                Cons.autoDistribute = (temp[3] == 1);
            }
            byte[] hasAgree = Util.readRecord("agreeOp");
            if (hasAgree != null && hasAgree.length == 2) {
                PCIncrementService.hasAgree = (hasAgree[0] == 1);
                PCIncrementService.agreeCount = PCIncrementService.sendCount = hasAgree[1];
            }
            if (Cons.isCmobile) {
                PCIncrementService.isend = 0;
            }
            byte[] data = Util.readRecord("connect");
            if (data != null && data.length == 1) {
                if (data[0] == 2) {
                    isAuto = true;
                    Cons.cmwap = true;
                } else {
                    isAuto = false;
                    if (data[0] == 0) {
                        Cons.cmwap = true;
                    } else {
                        Cons.cmwap = false;
                    }
                }
            } else {
                isAuto = true;
                Cons.cmwap = true;
            }
            byte[] serID = Util.readRecord("firstSelectedServer");
            if (serID != null) {
                if (serID.length == 1) {
                    firstSelectedServerID = null;
                } else {
                    firstSelectedServerID = Util.rmsByte4String(serID);
                }
            }
            try {
                localUN = aMidlet.getAppProperty("username");
                if (localUN == null || "".equals(localUN.trim())) {
                    localUN = "";
                }
                localUP = aMidlet.getAppProperty("userpsw");
                if (localUP == null || "".equals(localUP.trim())) {
                    localUP = "";
                }
            } catch (Exception exception) {
            }
            byte[] localUser = Util.readRecord("localuser");
            if (localUser != null) {
                isUserRMS = (localUser[0] == 1);
            }
            setState((byte) 3);
            for (int i = 0; i < 20; i++) {
                chatContent[i] = "";
            }
            NPCMenu = new UIMenu(5, 48, 164, 138, null, new String[0]);
            NPCMenu.setRimStyle((byte) 0);
            NPCMenu.setFlushType((byte) 1);
            initTjRes();
            try {
                Cons.exiturl = aMidlet.getAppProperty("exiturl").trim();
                Cons.exittitle = aMidlet.getAppProperty("exittitle").trim();
                Cons.exitinfo = aMidlet.getAppProperty("exitinfo").trim();
            } catch (Exception exception) {
            }
            try {
                jarFrom = aMidlet.getAppProperty("JarSrc").trim();
                if (jarFrom.equals("SOHU") || jarFrom.equals("SOH2") || jarFrom.equals("SOH3") || jarFrom.equals("SOH4") || jarFrom.equals("SOH5")) {
                    Cons.isSohu = true;
                }
                if ("dj".equals(jarFrom) || "1djo".equals(jarFrom) || "1dj3".equals(jarFrom) || "1dj4".equals(jarFrom) || "1dj5".equals(jarFrom) || "1dj6".equals(jarFrom)) {
                    Cons.isDL = true;
                }
            } catch (Exception exception) {
            }
            chatText.addCommand(chatOk);
            chatText.setItemCommandListener(this);
            chatForm.addCommand(chatBack);
            chatForm.append((Item) chatText);
            chatForm.setCommandListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        // 获取当前线程
        Thread currentThread = Thread.currentThread();
        while (currentThread == self) {
            try {
                // 获取当前系统时间
                long startTime = System.currentTimeMillis();
                // 解码收到的服务器数据
                synchronized (this) {
                    ni.deCode();
                }
                paintTaken += 120L;
                if (!startTickUser) {
                    ut = startTime;
                } else {
                    userTime = System.currentTimeMillis() - ut;
                    if (userTime >= 6000000L) {
                        userID = "";
                        startTickUser = false;
                        timeCheck = true;
                        loginRewardUesrId();
                    }
                }
                // 逻辑tick处理
                tick();
                // 重新渲染画面
                repaint();
                serviceRepaints();
                // 计算diff
                timeTaken = System.currentTimeMillis() - startTime;
                synchronized (this) {
                    wait(Math.max(1L, UIGameRun.getWait() - timeTaken));
                }
                if (UIGameRun.encryptImg == null) {
                    aMidlet.exitMIDlet();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void tick() {
        ExpandAbility.startTick();
        countTick++;
        if (countTick >= 600) {
            countTick = 0;
        }
        switch (getState()) {
            case 5: {  // 更新游戏中tick
                ExpandAbility.beforemapRunSubTick();
                mapRunSubTick();
                ExpandAbility.aftermapRunSubTick();
                PCArena.tick();
                ExpandAbility.afterPCArenaTick();
                UIGameRun.tickChatBar();
                if (PCMarriage.isMetemOk) {
                    UIForm aboutForm = UIForm.makeAboutForm("meteConfirm", "恭喜您，转生成功！", "确定", "", 170);
                    setTopForm(aboutForm, screenH - aboutForm.height >> 1);
                    PCMarriage.isMetemOk = false;
                }
                if ((PCIncrementService.isCharge && !Cons.isCmobile) || (PCIncrementService.isCharge && PCIncrementService.isend == 1)) {
                    PCIncrementService.getInstance().tick();
                }
                break;
            }
            case 23: {  // 更新地图计数
                UIGameRun.getInstance().updateMapCount();
                break;
            }
        }
        ExpandAbility.endTick();
    }

    /**
     * 绘制界面逻辑
     *
     * @param g
     */
    public void paint(Graphics g) {
        // 设置黑色背景
        g.setColor(0);
        g.fillRect(0, 0, screenW, screenH);
        // 设置字体
        g.setFont(font[1]);
        switch (getState()) {
            case 33: {
                AdvertiseSplash.getInstance().moreGamePaint(g);
                AdvertiseSplash.getInstance().moreGameTick();
                break;
            }
            case 18:
                if ((Player.getInstance()).state == 5) {
                    setState((byte) 5);
                } else {
                    drawWait(g);
                    break;
                }
            case 5:
                drawGameRun(g);
                if (topForm != null) {
                    topForm.draw(g);
                    break;
                }
                if (isPop && Cons.newPlayerHelp) {
                    drawPop(g);
                    break;
                }
                if (popRecord[0] == 1) {
                    if (ObjManager.vectorObj == null) {
                        return;
                    }
                    for (int i = 0; i < ObjManager.vectorObj.size(); i++) {
                        GameObj tmp = (GameObj) ObjManager.vectorObj.elementAt(i);
                        if (tmp.type == 3 && tmp.eventState != 0 && popRecord[1] == 0) {
                            setPop((byte) 2);
                            break;
                        }
                    }
                }
                break;
            case 24:
                UIGameRun.getInstance().drawWaitFirstResInit(g);
                break;
            case 3:
                drawSplash(g);
                break;
            case 26:
                UIGameRun.getInstance().drawSoundClew(g);
                break;
            case 31:
                PCIncrementService.getInstance().drawAgree(g);
                break;
            case 4:
                drawMainMenu(g);
                break;
            case 10:
                drawLogon(g);
                break;
            case 14:
                drawLogin(g);
                break;
            case 29:
                PCBindService.getInstance().draw(g);
                break;
            case 27:
                drawHelp(g);
                break;
            case 28:
                drawSubHelp(g);
                break;
            case 15:
                drawLoginCreatMan(g);
                break;
            case 13:
                if (baseForm == null) {
                    baseForm = new UIForm(0, 0, screenW, screenH, "");
                    baseForm.setStyle((byte) 0);
                    baseForm.setBackGround((byte) 8);
                    labels[0] = new UILabel(0, 70, 75, 0, "连接服务器，请稍候...", 15719326, (byte) 1, (byte) 0);
                    rims[0] = new UIRim(0, 60, 120, 50, (byte) 4);
                    baseForm.addComponentInCenter(rims[0], (byte) 2);
                    baseForm.addComponentInCenter(labels[0], (byte) 2);
                }
                baseForm.draw(g);
                break;
            case 11:
                drawLoginSelectServer(g);
                break;
            case 12:
                drawLoginSelectMan(g);
                break;
            case 19:
                drawIfBackToMainMenu(g);
                break;
            case 17:
                drawLoadInitGame(g);
                break;
            case 23:
                UIGameRun.getInstance().drawWaitResInit(g);
                break;
            case 22:
                drawChangePassWord(g);
                break;
            case 6:
                if (Cons.isCmobile) {
                    Download.getInstance().drawExit(g);
                }
                break;
            case 8:
                break;
            case 9:
                drawAbout(g);
                break;
            case 7:
                drawSetup(g);
                break;
            case 30:
                PCIncrementService.setState((byte) 16);
                PCIncrementService.getInstance().draw(g);
                break;
            default:
                g.setColor(16777215);
                g.drawString("请稍候...", screenW >> 1, screenH >> 1, 17);
                break;
        }
        processOvertime(g, false);
    }
    
    /**
     * 处理键盘按下
     *
     * @param keyCode
     */
    public void keyPressed(int keyCode) {
        switch (keyCode) {
            case 48:
                keyFlag |= 0x1;
                break;
            case 49:
                keyFlag |= 0x2;
                break;
            case 50:
                keyFlag |= 0x4;
                break;
            case 51:
                keyFlag |= 0x8;
                break;
            case 52:
                keyFlag |= 0x10;
                break;
            case 53:
                keyFlag |= 0x20;
                break;
            case 54:
                keyFlag |= 0x40;
                break;
            case 55:
                keyFlag |= 0x80;
                break;
            case 56:
                keyFlag |= 0x100;
                break;
            case 57:
                keyFlag |= 0x200;
                break;
            case 42:
                keyFlag |= 0x8000;
                break;
            case 35:
                keyFlag |= 0x10000;
                break;
            case -8:
                keyFlag |= 0x100000;
                break;
            case -6:
                keyFlag |= 0x20000;
                break;
            case -7:
                keyFlag |= 0x40000;
                break;
        }
        if (keyCode != -6 && keyCode != -7 && keyCode != 50 && keyCode != 52 && keyCode != 54 && keyCode != 56 && keyCode != 53) {
            switch (getGameAction(keyCode)) {
                case 1:
                    keyFlag |= 0x800;
                    break;
                case 6:
                    keyFlag |= 0x2000;
                    break;
                case 2:
                    keyFlag |= 0x400;
                    break;
                case 5:
                    keyFlag |= 0x1000;
                    break;
                case 8:
                    keyFlag |= 0x4000;
                    break;
            }
        }
        keyFlagIm = keyFlag;
        handKeyPress();
    }
    
    public void setKeyValue(int keyCode){
        keyFlagIm = keyFlag = keyCode;
    }
    
    public void restKeyFlag(){
        this.keyFlag = 0;
    }
    
    public void handKeyPress(){
        switch (getState()) {
            case 4: {
                keyInMenu();
                resetKey();
                break;
            }
            case 5: {  // 处理游戏中按键
                keyInGameRun();
                break;
            }
            case 6: {
                if (Cons.isCmobile) {
                    Download.getInstance().keyInExit();
                }
                break;
            }
            case 7: {
                keyInNetSetup();
                break;
            }
            case 9: {
                keyInAbout();
                break;
            }
            case 10: {
                keyInLogon();
                resetKey();
                break;
            }
            case 11: {
                keyInSelectServer();
                resetKey();
                break;
            }
            case 12: {
                keyInSelectMan();
                resetKey();
                break;
            }
            case 14: {
                keyInLogin();
                resetKey();
                break;
            }
            case 15: {
                keyInCreatMan();
                resetKey();
                break;
            }
            case 17: {
                keyInInitgame();
                resetKey();
                break;
            }
            case 19: {
                keyInBackToMainMenu();
                resetKey();
                break;
            }
            case 22: {
                keyInLogin();
                resetKey();
                break;
            }
            case 26: {
                UIGameRun.getInstance().keyInSoundClew();
                resetKey();
                break;
            }
            case 27: {
                keyInMainHelp();
                break;
            }
            case 28: {
                keyInMainSubHelp();
                break;
            }
            case 29: {
                PCBindService.getInstance().action();
                break;
            }
            case 30: {
                PCIncrementService.setState((byte) 16);
                PCIncrementService.getInstance().userEvent();
                break;
            }
            case 31: {  // 增值服务同意
                PCIncrementService.getInstance().keyInAgree();
                resetKey();
                break;
            }
        }
    }

    /**
     * 按键释放
     *
     * @param keyCode
     */
    public void keyReleased(int keyCode) {
        switch (keyCode) {
            case 48:
                keyFlag &= 0xFFFFFFFE;
                break;
            case 49:
                keyFlag &= 0xFFFFFFFD;
                break;
            case 50:
                keyFlag &= 0xFFFFFFFB;
                break;
            case 51:
                keyFlag &= 0xFFFFFFF7;
                break;
            case 52:
                keyFlag &= 0xFFFFFFEF;
                break;
            case 53:
                keyFlag &= 0xFFFFFFDF;
                break;
            case 54:
                keyFlag &= 0xFFFFFFBF;
                break;
            case 55:
                keyFlag &= 0xFFFFFF7F;
                break;
            case 56:
                keyFlag &= 0xFFFFFEFF;
                break;
            case 57:
                keyFlag &= 0xFFFFFDFF;
                break;
            case 42:
                keyFlag &= 0xFFFF7FFF;
                break;
            case 35:
                keyFlag &= 0xFFFEFFFF;
                break;
            case -8:
                keyFlag &= 0xFFEFFFFF;
                break;
            case -6:
                keyFlag &= 0xFFFDFFFF;
                break;
            case -7:
                keyFlag &= 0xFFFBFFFF;
                break;
        }
        switch (getGameAction(keyCode)) {
            case 1:
                keyFlag &= 0xFFFFF7FF;
                break;
            case 6:
                keyFlag &= 0xFFFFDFFF;
                break;
            case 2:
                keyFlag &= 0xFFFFFBFF;
                break;
            case 5:
                keyFlag &= 0xFFFFEFFF;
                break;
            case 8:
                keyFlag &= 0xFFFFBFFF;
                break;
        }
    }

    /**
     * 判断是否按下了某个按键 0-9 对应0-9数字按键 10-左 11-上 12-右 13-下 14-ok键 16-#键 17-左软键 18-右软键
     *
     * @param keyLabel
     * @return
     */
    public static boolean isKeyPress(int keyLabel) {
        if (mc.getNPCSubState() == 7) {
            return isKeyPress1(keyLabel);
        }
        if ((keyFlagIm >>> keyLabel & 0x1) == 1) {
            keyFlagIm &= 1 << keyLabel ^ 0xFFFFFFFF;
            return true;
        }
        if ((keyFlag >>> keyLabel & 0x1) == 1) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否按下了某个按键 0-9 对应0-9数字按键 10-左 11-上 12-右 13-下 14-ok键 16-#键 17-左软键 18-右软键
     *
     * @param keyLabel
     * @return
     */
    public static boolean isKeyPress1(int keyLabel) {
        if ((keyFlagIm >>> keyLabel & 0x1) == 1) {
            keyFlagIm &= 1 << keyLabel ^ 0xFFFFFFFF;
            keyFlagIm = keyFlag = 0;
            return true;
        }
        if ((keyFlag >>> keyLabel & 0x1) == 1) {
            keyFlag = keyFlagIm = 0;
            return true;
        }
        return false;
    }

    public static void resetKey() {
        keyFlagIm = 0;
        keyFlag = 0;
    }
    public static long[] chatStartTime = new long[9];
    public static final short[] chatIntervalTime = new short[]{0, 5000, 30000, 5000, 5000, 5000, 5000, 5000, 5000};
    Image imgSplashBg;
    Image[] imgSplash;
    byte frameCount;
    public int mainMenuStep;
    boolean isQuickLog;

    public void checkTicker() {
    }

    public void commandAction(Command cmd, Item item) {
        if (cmd == chatOk && !"".equals(chatText.getString().trim())) {
            chatSendString = chatText.getString();
            if (System.currentTimeMillis() - chatStartTime[localChatChannel] < chatIntervalTime[localChatChannel]) {
                if (chatForm != null) {
                    UIStringItem uisi = new UIStringItem("[系]您的发言太频繁!");
                    uisi.addCustomCommand(6, "");
                    chatForm.insert(1, (Item) uisi);
                }
                return;
            }
            if (localChatChannel == 8) {
                useStuffPlace = 1;
                ni.send(67110144);
            }
            ni.send(83886336);
            String tempString = chatText.getString();
            chatText.setString("");
            chatStartTime[localChatChannel] = System.currentTimeMillis();
            if (localChatChannel == 3 && PCChat.isClanNoticeAdd != 0) {
                ClanWar.getInstance().setCurr_notice_info(tempString);
                switch (PCChat.isClanNoticeAdd) {
                    case 1:
                        startWait(baseForm.getCurrentFocusForm());
                        ni.send(302383104);
                        break;
                    case 2:
                        startWait(baseForm.getCurrentFocusForm());
                        ni.send(302514176);
                        break;
                }
                PCChat.isClanNoticeAdd = 0;
                aMidlet.display.setCurrent((Displayable) this);
                clearAdvanceUIItem();
            }
            if (localChatChannel == 8) {
                localChatChannel = 0;
                dramatisPackage.setSubMenu((UIMenu) null);
                clearAdvanceUIItem();
                aMidlet.display.setCurrent((Displayable) mc);
            }
        }
    }

    public void clearAdvanceUIItem() {
        isInHelp = false;
        commonForm = null;
        commonOk = null;
        commonBack = null;
        commonTextField = null;
        commontf = null;
        commonClear = null;
        cg = null;
        System.gc();
    }

    public void commandAction(Command command, Displayable displayable) {
        switch (getState()) {
            case 5: {  // 游戏中
                switch (getGameState()) {
                    case 1: {  // 右菜单
                        switch (getRightMenuSubState()) {
                            case -1:
                                if (command == commonOk) {
                                    if (!"".equals(commonTextField.getString().trim())) {
                                        aMidlet.display.setCurrent((Displayable) this);
                                        PCFriend.friendName = commonTextField.getString().trim();
                                        friendAddType = 3;
                                        ni.send(201326848);
                                        clearAdvanceUIItem();
                                        setGameState((byte) 1);
                                        break;
                                    }
                                    commonTextField.setString("");
                                    break;
                                }
                                if (command == commonBack) {
                                    aMidlet.display.setCurrent((Displayable) this);
                                    clearAdvanceUIItem();
                                    setGameState((byte) 1);
                                }
                                break;
                            case 20:
                                if (command == commonOk) {
                                    mailName = commonTextField.getString();
                                    clanRequestName = mailName;
                                    aMidlet.display.setCurrent((Displayable) this);
                                    clearAdvanceUIItem();
                                    if (clanRequestName == null || clanRequestName.trim().equals("")) {
                                        setMessage(baseForm, "请输入人物姓名!");
                                        break;
                                    }
                                    baseForm.addAboutForm("pullConfirm", "你要让此人进入氏族吗?", (byte) 2, 170, 50);
                                    break;
                                }
                                if (command == commonBack) {
                                    aMidlet.display.setCurrent((Displayable) this);
                                    clearAdvanceUIItem();
                                    baseForm.setAboutForm((UIForm) null);
                                }
                                break;
                            case 8:
                                if (command == commonOk) {
                                    if ("".equals(commontf.getString().trim())) {
                                        break;
                                    }
                                    mailName = cg.getString(cg.getSelectedIndex());
                                    mailDetail = commontf.getString();
                                    String ver = "[1][";
                                    for (int i = 3; i >= 0; i--) {
                                        ver = ver + (Cons.VER >>> i << 3 & 0xFF);
                                        if (i != 0) {
                                            ver = ver + ".";
                                        }
                                    }
                                    ver = ver + "]";
                                    mailDetail = ver + mailDetail;
                                    aMidlet.display.setCurrent((Displayable) this);
                                    labels[0] = null;
                                    ni.send(184549632);
                                    setRightMenuSubState(-1);
                                    setGameState((byte) 0);
                                    clearAdvanceUIItem();
                                    break;
                                }
                                if (command == commonBack) {
                                    aMidlet.display.setCurrent((Displayable) this);
                                    setRightMenuSubState(-1);
                                    clearAdvanceUIItem();
                                    break;
                                }
                                if (command == commonClear) {
                                    commontf.setString("");
                                }
                                break;
                            case 3:
                                if (command == chatBack) {
                                    aMidlet.display.setCurrent((Displayable) this);
                                    PCChat.isClanNoticeAdd = 0;
                                    clearAdvanceUIItem();
                                    break;
                                }
                                if (command == commonOk) {
                                    if (!"".equals(commonTextField.getString().trim())) {
                                        aMidlet.display.setCurrent((Displayable) this);
                                        PCFriend.sendMessage = commonTextField.getString().trim();
                                        clearAdvanceUIItem();
                                        if (isSelectList != 2) {
                                            baseForm.setAboutForm((UIForm) null);
                                        }
                                        PCIncrementService.chargeWhere = 7;
                                        baseForm.getCurrentFocusForm().addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                        PCIncrementService.sendMommoth();
                                    }
                                    break;
                                }
                                if (command == commonBack) {
                                    if (isSelectList == 0) {
                                        baseForm.setAboutForm((UIForm) null);
                                        aMidlet.display.setCurrent((Displayable) this);
                                        clearAdvanceUIItem();
                                        break;
                                    }
                                    if (isSelectList == 1) {
                                        isSelectList = 3;
                                    } else if (isSelectList == 2) {
                                        isSelectList = 4;
                                    }
                                    aMidlet.display.setCurrent((Displayable) selectForm);
                                    break;
                                }
                                if (command == selectAll) {
                                    for (int i = 0; i < cg.size(); i++) {
                                        cg.setSelectedIndex(i, true);
                                    }
                                    break;
                                }
                                if (command == selectDall) {
                                    for (int i = 0; i < cg.size(); i++) {
                                        cg.setSelectedIndex(i, false);
                                    }
                                    break;
                                }
                                if (command == selectOk) {
                                    mcount = 0;
                                    if (isSelectList == 3) {
                                        for (int i = 0; i < cg.size(); i++) {
                                            if (cg.isSelected(i)) {
                                                SENDID[mcount] = friendListID[i];
                                                mcount = (byte) (mcount + 1);
                                            }
                                        }
                                    } else if (isSelectList == 4) {
                                        for (int i = 0; i < cg.size(); i++) {
                                            if (cg.isSelected(i)) {
                                                SENDID[mcount] = clanMemberId[i];
                                                mcount = (byte) (mcount + 1);
                                            }
                                        }
                                    }
                                    if (mcount > 0) {
                                        if (isSelectList == 3) {
                                            isSelectList = 1;
                                        } else if (isSelectList == 4) {
                                            isSelectList = 2;
                                        }
                                        initMessageForm();
                                    }
                                    break;
                                }
                                if (command == selectBack) {
                                    if (isSelectList == 3) {
                                        baseForm.setAboutForm((UIForm) null);
                                        setUISocietyState((byte) 2);
                                    } else if (isSelectList == 4) {
                                        setUISocietyState((byte) 4);
                                    }
                                    aMidlet.display.setCurrent((Displayable) this);
                                    clearAdvanceUIItem();
                                    selectForm = null;
                                    selectOk = null;
                                    selectAll = null;
                                    selectBack = null;
                                    selectDall = null;
                                }
                                break;
                        }
                        if (command == chatBack) {
                            aMidlet.display.setCurrent((Displayable) this);
                            dramatisPackage.setSubMenu((UIMenu) null);
                        }
                        break;
                    }
                    case 2: {
                        if (command == chatBack) {
                            aMidlet.display.setCurrent((Displayable) this);
                            clearAdvanceUIItem();
                            releaseUI();
                            setGameState((byte) 0);
                        }
                        break;
                    }
                    case 3: {
                        switch (getNPCSubState()) {
                            case 11:
                                switch (getNPCMailState()) {
                                    case 1:
                                        if (commonTextField != null) {
                                            if (command == commonOk) {
                                                boolean rightName = false;
                                                boolean rightDetail = false;
                                                if (Util.checkLegal(commonTextField.getString(), (byte) commonTextField.getMaxSize(), commonForm, false)) {
                                                    mailName = commonTextField.getString();
                                                    labels[1].setStr(mailName);
                                                    labels[1].setColor(16316576);
                                                    rightName = true;
                                                }
                                                if (Util.checkLegal(commontf.getString(), (byte) commontf.getMaxSize(), commonForm, true)) {
                                                    mailDetail = commontf.getString();
                                                    textArea[0].setString(mailDetail);
                                                    rightDetail = true;
                                                }
                                                if (rightName && rightDetail) {
                                                    aMidlet.display.setCurrent((Displayable) this);
                                                    clearAdvanceUIItem();
                                                    baseForm.setAboutForm((UIForm) null);
                                                }
                                                break;
                                            }
                                            if (command == commonBack) {
                                                aMidlet.display.setCurrent((Displayable) this);
                                                clearAdvanceUIItem();
                                                baseForm.setAboutForm((UIForm) null);
                                            }
                                            break;
                                        }
                                        if (command == commonOk) {
                                            if (Util.checkLegal(commontf.getString().trim(), (byte) 6, commonForm, false)) {
                                                String temp = commontf.getString().trim();
                                                if ("".equals(temp)) {
                                                    mailSendTo = -1;
                                                } else {
                                                    mailSendTo = 0;
                                                }
                                                labels[0].setStr(temp);
                                                labels[0].setColor(10981736);
                                                aMidlet.display.setCurrent((Displayable) this);
                                                clearAdvanceUIItem();
                                                baseForm.setAboutForm((UIForm) null);
                                            }
                                            break;
                                        }
                                        if (command == commonBack) {
                                            aMidlet.display.setCurrent((Displayable) this);
                                            clearAdvanceUIItem();
                                            baseForm.setAboutForm((UIForm) null);
                                        }
                                        break;
                                }
                                break;
                            case 6:
                                if (command == commonOk) {
                                    clanRequestName = commonTextField.getString();
                                    if (Util.checkLegal(clanRequestName, (byte) 6, commonForm, false)) {
                                        labels[0].setStr(clanRequestName);
                                        labels[0].setColor(16316576);
                                        clanRequestInfo = commontf.getString().replace('$', '＄');
                                        textArea[0].setString(clanRequestInfo);
                                        aMidlet.display.setCurrent((Displayable) this);
                                        clearAdvanceUIItem();
                                        baseForm.getSubForm().setAboutForm((UIForm) null);
                                    }
                                    break;
                                }
                                if (command == commonBack) {
                                    aMidlet.display.setCurrent((Displayable) this);
                                    clearAdvanceUIItem();
                                    baseForm.getSubForm().setAboutForm((UIForm) null);
                                }
                                break;
                            case 31:
                                if (command == commonOk) {
                                    ni.send(1879048448);
                                    setNPCSubState((byte) 0);
                                    aMidlet.display.setCurrent((Displayable) this);
                                    commontf = null;
                                    commonTextField = null;
                                    commonForm = null;
                                    break;
                                }
                                if (command == commonBack) {
                                    setNPCSubState((byte) 0);
                                    baseForm.setAboutForm((UIForm) null);
                                    aMidlet.display.setCurrent((Displayable) this);
                                    commontf = null;
                                    commonTextField = null;
                                    commonForm = null;
                                }
                                break;
                            case 32:
                                if (command == commonOk) {
                                    String name = commonTextField.getString();
                                    if (!name.trim().equals("")) {
                                        ni.send(1879048448);
                                        setNPCSubState((byte) 0);
                                        aMidlet.display.setCurrent((Displayable) this);
                                        commontf = null;
                                        commonTextField = null;
                                        commonForm = null;
                                    }
                                    break;
                                }
                                if (command == commonBack) {
                                    setNPCSubState((byte) 0);
                                    baseForm.setAboutForm((UIForm) null);
                                    aMidlet.display.setCurrent((Displayable) this);
                                    commontf = null;
                                    commonTextField = null;
                                    commonForm = null;
                                }
                                break;
                            case 33:
                                if (command == commonOk) {
                                    String name = commonTextField.getString();
                                    String password = commontf.getString();
                                    if (!name.trim().equals("") && !password.trim().equals("")) {
                                        ni.send(1879048448);
                                        setNPCSubState((byte) 0);
                                        aMidlet.display.setCurrent((Displayable) this);
                                        commontf = null;
                                        commonTextField = null;
                                        commonForm = null;
                                    }
                                    break;
                                }
                                if (command == commonBack) {
                                    setNPCSubState((byte) 0);
                                    baseForm.setAboutForm((UIForm) null);
                                    aMidlet.display.setCurrent((Displayable) this);
                                    commontf = null;
                                    commonTextField = null;
                                    commonForm = null;
                                }
                                break;
                        }
                        break;
                    }
                    case 7: {
                        if (command == commonOk) {
                            break;
                        }
                        if (command == chatBack || command == commonBack) {
                            localChatChannel = 0;
                            aMidlet.display.setCurrent((Displayable) this);
                            clearAdvanceUIItem();
                            releaseUI();
                            setGameState((byte) 0);
                        }
                        break;
                    }
                }
                if (command == commonBack || command == chatBack) {
                    aMidlet.display.setCurrent((Displayable) this);
                    clearAdvanceUIItem();
                }
                break;
            }
            case 12: {
                if (displayable == commonForm) {
                    if (command == commonOk) {
                        String s = commonTextField.getString();
                        if (Util.checkLegal(s, (byte) 6, commonForm, false)) {
                            playerNames[choose_manID] = s;
                            labels[1].setStr("姓名：" + s);
                            aMidlet.display.setCurrent((Displayable) this);
                            clearAdvanceUIItem();
                            ni.send(16780800);
                            startWait(baseForm.getCurrentFocusForm());
                        }
                        break;
                    }
                    if (command == commonBack) {
                        labels[1].setStr("姓名：" + oldRoleName);
                        playerNames[choose_manID] = oldRoleName;
                        aMidlet.display.setCurrent((Displayable) this);
                        clearAdvanceUIItem();
                    }
                }
                break;
            }
            case 15: {
                if (displayable == commonForm) {
                    if (command == commonOk) {
                        String s = commonTextField.getString();
                        if (Util.checkLegal(s, (byte) 6, commonForm, false)) {
                            texts[0].setLabel(s);
                            aMidlet.display.setCurrent((Displayable) this);
                            setState((byte) 15);
                            clearAdvanceUIItem();
                        }
                        break;
                    }
                    if (command == commonBack) {
                        aMidlet.display.setCurrent((Displayable) this);
                        setState((byte) 15);
                        clearAdvanceUIItem();
                    }
                }
                break;
            }
            default: {
                if (command == commonBack || command == chatBack) {
                    aMidlet.display.setCurrent((Displayable) this);
                    clearAdvanceUIItem();
                }
                break;
            }
        }
        resetKey();
    }

    protected void showNotify() {
        (Player.getInstance()).findpath = true;
    }

    protected void hideNotify() {
        resetKey();
        (Player.getInstance()).findpath = false;
        if (Cons.audioOpen && sound != null) {
            sound.stopAllSound();
        }
    }

    public void setState(byte s) {
        oldState = curState;
        curState = s;
        if (s == 23) {
            if (Cons.audioOpen) {
                sound.stopAllSound();
            }
        } else if (s == 5) {
            setGameState((byte) 0);
        } else if (s == 4) {
            loseMark = 0;
            userID = userKey = null;
            byEncryptChain = null;
            byEncryptChainLast = null;
            byEncryptChainPos = -1;
            byEncryptChainPosLast = -1;
            byLastEncryptID = 0;
            byCurEncryptID = 0;
            baseForm = null;
            topForm = null;
            isPop = false;
            twoSelect = false;
            isGetingUserID = false;
            if (isAuto) {
                twoSelect = true;
            }
            dramatisPackage.isLock = false;
            dramatisPackage.isLock1 = false;
            dramatisPackage.isLock2 = false;
            dramatisPackage.isLock3 = false;
            dramatisPackage.isLock4 = false;
            aMidlet.display.setCurrent((Displayable) this);
            clearAdvanceUIItem();
            releaseUI();
        } else if (s == 11) {
            isFirst[choose_manID] = false;
            Map.isDrawPlaceName = true;
        } else if (s == 10) {
            HttpConn.kongFirstResult = -1;
            kongMD5 = null;
            userID = userKey = "";
        }
    }

    public byte getState() {
        return curState;
    }

    public void setGameState(byte s) {
        if (s != 0) {
            isMenu = true;
        } else {
            baseForm = null;
            isMenu = false;
            selectedManId = -1;
        }
        oldGameState = gameState;
        gameState = s;
        switch (s) {
            case 1:
                setRightMenuSubState(-1);
                break;
            case 2:
                setLeftMenuSubState(-1);
                break;
            case 3:
                setNPCSubState((byte) 0);
                break;
        }
    }

    /**
     * 获取游戏中状态
     *
     * @return
     */
    public byte getGameState() {
        return gameState;
    }

    public void setGameRunState(byte s) {
        oldGameState_gameRunState = gameState_gameRunState;
        gameState_gameRunState = s;
    }

    public byte getGameRunState() {
        return gameState_gameRunState;
    }

    public void setOtherSubState(byte s) {
        oldGameState_OtherState = gameState_OtherState;
        gameState_OtherState = s;
        if (s == 1) {
            aMidlet.display.setCurrent((Displayable) this);
            clearAdvanceUIItem();
            releaseUI();
        }
    }

    public byte getOtherSubState() {
        return gameState_OtherState;
    }

    public void setRightMenuSubState(int s) {
        oldGameState_rightMenuSubState = gameState_rightMenuSubState;
        gameState_rightMenuSubState = s;
    }

    /**
     * 获取右键菜单状态
     *
     * @return
     */
    public int getRightMenuSubState() {
        return gameState_rightMenuSubState;
    }

    public void setUIManState(byte s) {
        oldGameState_menuManState = gameState_menuManState;
        gameState_menuManState = s;
    }

    public byte getUIManState() {
        return gameState_menuManState;
    }

    public void setUIChatState(byte s) {
        oldGameState_menuChatState = gameState_menuChatState;
        gameState_menuChatState = s;
    }

    public byte getUIChatState() {
        return gameState_menuChatState;
    }

    public void setUIPetState(byte s) {
        oldGameState_menuPetState = gameState_menuPetState;
        gameState_menuPetState = s;
    }

    public byte getUIPetState() {
        return gameState_menuPetState;
    }

    public void setUITaskState(byte s) {
        oldGameState_menuTaskState = gameState_menuTaskState;
        gameState_menuTaskState = s;
    }

    public void setUISocietyState(byte s) {
        oldGameState_menuFriendState = gameState_menuFriendState;
        gameState_menuFriendState = s;
    }

    public byte getUIFriendState() {
        return gameState_menuFriendState;
    }

    public void setUISetupState(byte s) {
        oldGameState_menuSetupState = gameState_menuSetupState;
        gameState_menuSetupState = s;
    }

    public byte getUISetupState() {
        return gameState_menuSetupState;
    }

    public byte getUITaskState() {
        return gameState_menuTaskState;
    }

    public void setUIHelpState(byte s) {
        oldGameState_menuHelpState = gameState_menuHelpState;
        gameState_menuHelpState = s;
    }

    public byte getUIHelpState() {
        return gameState_menuHelpState;
    }

    public void setUIMApState(byte s) {
        oldGameState_menuMapState = gameState_menuMapState;
        gameState_menuMapState = s;
    }

    public byte getUIMapState() {
        return gameState_menuMapState;
    }

    public void setLeftMenuSubState(int s) {
        oldGameState_rightMenuSubState = gameState_leftMenuSubState;
        gameState_leftMenuSubState = s;
    }

    public int getLeftMenuSubState() {
        return gameState_leftMenuSubState;
    }

    public void setNPCSubState(byte s) {
        oldGameState_NPCSubState = gameState_NPCSubState;
        gameState_NPCSubState = s;
    }

    public byte getNPCSubState() {
        return gameState_NPCSubState;
    }

    public void setNPCVendueState(byte s) {
        oldGameState_NPCVendueState = gameState_NPCVendueState;
        gameState_NPCVendueState = s;
    }

    public byte getNPCVendueState() {
        return gameState_NPCVendueState;
    }

    public void setNPCExchangeState(byte s) {
        oldGameState_NPCExchangeState = gameState_NPCExchangeState;
        gameState_NPCExchangeState = s;
    }

    public byte getNPCExchangeState() {
        return gameState_NPCExchangeState;
    }

    public void setNPCMailState(byte s) {
        oldGameState_NPCMailState = gameState_NPCMailState;
        gameState_NPCMailState = s;
    }

    public byte getNPCMailState() {
        return gameState_NPCMailState;
    }

    public void setNPCFixState(byte s) {
        oldGameState_NPCFixState = gameState_NPCFixState;
        gameState_NPCFixState = s;
    }

    public byte getNPCFixState() {
        return gameState_NPCFixState;
    }

    public void setNPCAdoptPetState(byte s) {
        oldGameState_NPCAdoptPetState = gameState_NPCAdoptPetState;
        gameState_NPCAdoptPetState = s;
    }

    public byte getNPCAdoptPetState() {
        return gameState_NPCAdoptPetState;
    }

    public void setNPCLabourUnionState(byte s) {
        oldGameState_NPCLabourUnionState = gameState_NPCLabourUnionState;
        gameState_NPCLabourUnionState = s;
    }

    public byte getNPCLabourUnionState() {
        return gameState_NPCLabourUnionState;
    }

    public void initSysValue() {
        screenW = getWidth();
        screenH = getHeight();
        int i;
        for (i = 0; i < Cons.PHONE_TYPE.length; i++) {
            if (screenW == Cons.PHONE_TYPE[i][0] && screenH == Cons.PHONE_TYPE[i][1]) {
                MOBILE_TELEPHONE_TYPE = (byte) i;
                break;
            }
        }
        font = new Font[4];
        font[0] = Font.getDefaultFont();
        font[1] = Font.getFont(0, 0, 8);
        font[2] = Font.getFont(0, 0, 0);
        font[3] = Font.getFont(0, 0, 16);
        CHARW = Cons.FONT_SIZE[MOBILE_TELEPHONE_TYPE][0];
        CHARH = Cons.FONT_SIZE[MOBILE_TELEPHONE_TYPE][1];
        Cons.SINAL_ROW_HEIGHT = (byte) (Cons.FONT_SIZE[MOBILE_TELEPHONE_TYPE][1] + 3);
        for (i = 0; i < manX.length; i++) {
            manX[i] = manX[i] * UIComponent.CURR_W / 176 + (32 * UIComponent.CURR_W / 176 - 32 >> 1);
            manY[i] = manY[i] * UIComponent.CURR_H / 208 + (40 * UIComponent.CURR_H / 208 - 40 >> 1);
        }
        for (i = 0; i < manX1.length; i++) {
            manX1[i] = manX1[i] * UIComponent.CURR_W / 176 + (32 * UIComponent.CURR_W / 176 - 32 >> 1);
            manY1[i] = manY1[i] * UIComponent.CURR_H / 208 + (40 * UIComponent.CURR_H / 208 - 40 >> 1);
        }
    }

    private void drawSplash(Graphics g) {
        if (!Cons.isCmobile) {
            g.setColor(16777215);
            g.fillRect(0, 0, screenW, screenH);
            if (splashImage != null) {
                g.drawImage(splashImage, screenW >> 1, screenH >> 1, 3);
            }
            if (isDelayed((splashIndex == 0) ? 0 : ((splashIndex == 1) ? 500 : 500), (byte) 0)) {
                if (splashIndex < (Cons.isSohu ? 3 : 2)) {
                    beginDelay((byte) 0);
                    splashImage = Util.loadImage("/logo_" + splashIndex + ".png");
                    splashIndex = (byte) (splashIndex + 1);
                } else {
                    splashImage = null;
                    splashIndex = 0;
                    setState((byte) 24);
                    System.gc();
                }
            }
        } else {
            g.setColor(0);
            g.fillRect(0, 0, screenW, screenH);
            if (imgSplashBg == null) {
                imgSplashBg = Util.loadImage("/logobg.png");
                for (int i = 0; i < imgSplash.length; i++) {
                    imgSplash[i] = Util.loadImage("/logof" + i + ".png");
                }
            }
            g.drawImage(imgSplashBg, screenW >> 1, screenH >> 1, 3);
            if (splashImage != null) {
                g.drawImage(splashImage, (screenW >> 1) - 1, (screenH >> 1) - 3, 3);
            }
            if (splashIndex == 1) {
                splashImage = imgSplash[frameCount];
                frameCount = (byte) (frameCount + 1);
                if (frameCount > 2) {
                    frameCount = 0;
                }
            }
            if (isDelayed((splashIndex == 0) ? 1000 : ((splashIndex == 1) ? 1000 : 3000), (byte) 0)) {
                if (splashIndex < 2) {
                    beginDelay((byte) 0);
                    if (splashIndex == 1) {
                        splashImage = Util.loadImage("/logo_" + (splashIndex + 2) + ".png");
                    }
                    splashIndex = (byte) (splashIndex + 1);
                } else {
                    splashImage = null;
                    imgSplashBg = null;
                    for (int i = 0; i < imgSplash.length; i++) {
                        imgSplash[i] = null;
                    }
                    frameCount = 0;
                    splashIndex = 0;
                    setState((byte) 24);
                    System.gc();
                }
            }
        }
    }

    private void drawMainMenu(Graphics g) {
        ni.checkoutID = -1;
        titlecount++;
        titlecount %= 6;
        g.setColor(0);
        g.setClip(0, 0, screenW, screenH);
        g.fillRect(0, 0, screenW, screenH);
        mImageTitle.draw(g, screenW >> 1, 0, 17, 0);
        drawFire(g);
        mImageC.draw(g, screenW - mImageC.frame_w >> 1, screenH - mImageC.frame_h - 2, 0, false);
        for (int i = 0, kk = star.length; i < kk; i++) {
            star[i][1] = star[i][1] - star[i][2];
            if (star[i][4] < 0) {
                star[i][0] = star[i][0] - 1;
                star[i][4] = star[i][4] + 1;
            } else if (star[i][4] > 0) {
                star[i][0] = star[i][0] + 1;
                star[i][4] = star[i][4] - 1;
            } else {
                star[i][4] = Util.getRandom(20) - 10;
            }
            if (star[i][1] < 0) {
                star[i][0] = Util.getRandom(screenW);
                star[i][1] = screenH - 20;
                star[i][2] = Util.getRandom(4) + 2;
                star[i][3] = fireColor[Util.getRandom(4)];
                star[i][4] = Util.getRandom(20) - 10;
            } else {
                g.setColor(8788493);
                g.drawLine(star[i][0] - 1, star[i][1], star[i][0] + 1, star[i][1]);
                g.drawLine(star[i][0], star[i][1] - 1, star[i][0], star[i][1] + 1);
                g.setColor(star[i][3]);
                g.fillRect(star[i][0], star[i][1], 1, 1);
            }
        }
        int centerX = screenW >> 1;
        int VIEW_SIZE = 5;
        int TEXT_MARGIN = 2;
        int ARROW_TEXT_MARGIN = 3;
        int BORDER_ARROW_MARGIN = 3;
        int w = 70;
        int h = 30 + (CHARH + 2) * 5 - 2;
        int x = screenW - w >> 1;
        int y = 105 * UIComponent.CURR_H / 208;
        UIRim.drawRim(g, x, y, w, h, (byte) 1);
        int totalSize = Cons.isCmobile ? Cons.STR_MAINMENU2.length : (Cons.isSohu ? Cons.STR_MAINMENU_SH.length : (Cons.isDL ? Cons.STR_MAINMENU_DL.length : Cons.STR_MAINMENU.length));
        for (int j = 0; j < 5; j++) {
            int k, index = mainItemID + j - 2;
            if (index < 0) {
                index += totalSize;
            } else if (index >= totalSize) {
                index -= totalSize;
            }
            if (j == 2) {
                k = 16121750;
            } else if (j == 1 || j == 3) {
                k = 15852032;
            } else {
                k = 11443968;
            }
            if (Cons.isCmobile && Cons.STR_MAINMENU2[7].equals(Cons.STR_MAINMENU2[index])) {
                if (j == 2) {
                    k = 16711680;
                } else if (j == 1 || j == 3) {
                    k = 16728356;
                } else {
                    k = 16740157;
                }
            }
            g.setColor(k);
            g.drawString(Cons.isCmobile ? Cons.STR_MAINMENU2[index] : (Cons.isSohu ? Cons.STR_MAINMENU_SH[index] : (Cons.isDL ? Cons.STR_MAINMENU_DL[index] : Cons.STR_MAINMENU[index])), centerX, y + 3 + 9 + 3 + j * (CHARH + 2), 17);
        }
        mImageArrow.draw(g, centerX, y + 3, 17, GraphicsUtil.TRANS_NONE);
        mImageArrow.draw(g, centerX, y + h - 3, 33, GraphicsUtil.FLIP_VERTICAL);
        g.setColor(9380352);
        StringBuffer sb = new StringBuffer(4);
        sb.append("版本  ");
        int tmpV = Cons.VER;
        sb.append(String.valueOf(tmpV >>> 24)).append(".");
        tmpV = Cons.VER;
        sb.append(String.valueOf((tmpV & 0xFF0000) >>> 16)).append(".");
        tmpV = Cons.VER;
        sb.append(String.valueOf((tmpV & 0xFF00) >>> 8)).append(".");
        tmpV = Cons.VER;
        sb.append(String.valueOf(tmpV & 0xFF));
        g.drawString(sb.toString(), (screenW >> 1) + 1, 2, 17);
        g.drawString(sb.toString(), (screenW >> 1) - 1, 2, 17);
        g.drawString(sb.toString(), screenW >> 1, 3, 17);
        g.drawString(sb.toString(), screenW >> 1, 1, 17);
        g.setColor(15852032);
        g.drawString(sb.toString(), screenW >> 1, 2, 17);
        if (mainMenuStep < screenW / 20) {
            g.setColor(0);
            for (int k = 0; k < screenH; k++) {
                if (k % 2 == 0) {
                    g.drawLine(0, k, screenW - mainMenuStep * 20, k);
                } else {
                    g.drawLine(0 + mainMenuStep * 20, k, screenW, k);
                }
            }
            mainMenuStep++;
        }
    }

    private String VerString(int ver) {
        StringBuffer sb = new StringBuffer(4);
        int tmpV = ver;
        sb.append(String.valueOf(tmpV >>> 24)).append(".");
        tmpV = ver;
        sb.append(String.valueOf((tmpV & 0xFF0000) >>> 16)).append(".");
        tmpV = ver;
        sb.append(String.valueOf((tmpV & 0xFF00) >>> 8)).append(".");
        tmpV = ver;
        sb.append(String.valueOf(tmpV & 0xFF));
        return sb.toString();
    }

    private void keyInAbout() {
        if (isKeyPress(18)) {
            countProduct = 0;
            releaseUI();
            setState((byte) 4);
        }
    }

    private void keyInMainHelp() {
        if (baseForm == null) {
            return;
        }
        UIComponent cmd = baseForm.getCommand();
        if (isKeyPress(17) || isKeyPress(14) || isKeyPress(5)) {
            mainHelpIndex = menus[0].getCurrentPointer();
            releaseUI();
            setState((byte) 28);
        } else if (isKeyPress(18)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                mainHelpIndex = 0;
                setState((byte) 4);
                releaseUI();
            }
        } else if (actionInFormMenu(cmd)) {
        }
    }

    private void keyInMainSubHelp() {
        if (baseForm == null) {
            return;
        }
        UIComponent cmd = baseForm.getCommand();
        if (isKeyPress(18)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                setState((byte) 27);
                releaseUI();
            }
        } else if (actionInFormMenu(cmd)) {
        }
    }

    private void drawSetup(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setBackGround((byte) 9);
            UIRim frame = new UIRim(0, 0, screenW - 1, screenH, (byte) 4);
            baseForm.addComponent(frame);
            UILabel lblOk = new UILabel(0, 5, 0, 0, "确定", 15587742, (byte) 1, (byte) 0);
            UILabel lblCancel = new UILabel(0, 5, 0, 0, "取消", 15587742, (byte) 1, (byte) 0);
            baseForm.addComponentInCenter(lblOk, (byte) 5);
            baseForm.addComponentInCenter(lblCancel, (byte) 6);
            UIRim rimTitle = new UIRim(0, 10, 160, 17, (byte) 7);
            baseForm.addComponentInCenter(rimTitle, (byte) 2);
            UILabel lblTitle = new UILabel(0, 13, 0, 0, "网络设置", 15587742, (byte) 1, (byte) 0);
            baseForm.addComponentInCenter(lblTitle, (byte) 2);
            UIRim rimOut = new UIRim(0, 27, 160, 160, (byte) 0);
            baseForm.addComponentInCenter(rimOut, (byte) 2);
            UIRim rimIn = new UIRim(0, 32, 150, 150, (byte) 0);
            baseForm.addComponentInCenter(rimIn, (byte) 2);
            rbs[0] = new UIRadioButton(35, 38, 0, 0, "网络连接", (byte) 2);
            rbs[0].addItems("CMWAP");
            rbs[0].addItems("CMNET");
            rbs[0].addItems("自动选择");
            rbs[0].setChooseItem(isAuto ? 2 : (Cons.cmwap ? 0 : 1));
            baseForm.addComponent(rbs[0]);
            textArea[0] = new UITextArea(0, 70, 146, 110, connectExplain[rbs[0].getChooseItemIndex()]);
            textArea[0].setColor(15849885);
            baseForm.addComponentInCenter(textArea[0], (byte) 2);
            baseForm.setFocus(true);
        }
        baseForm.draw(g);
    }

    private void keyInNetSetup() {
        if (baseForm == null) {
            return;
        }
        UIComponent cmd = baseForm.getCommand();
        if (isKeyPress(17)) {
            byte[] data = new byte[1];
            data[0] = rbs[0].getChooseItemIndex();
            if (data[0] == 2) {
                isAuto = true;
                Cons.cmwap = true;
            } else {
                isAuto = false;
                if (data[0] == 0) {
                    Cons.cmwap = true;
                } else {
                    Cons.cmwap = false;
                }
            }
            Util.saveRecord(data, "connect");
            setState((byte) 4);
        } else if (isKeyPress(18)) {
            setState((byte) 4);
        } else if (actionInForm(cmd)) {
            textArea[0].setString(connectExplain[rbs[0].getChooseItemIndex()]);
        }
    }

    private void keyInMenu() {
        if (isKeyPress(14) || isKeyPress(17) || isKeyPress(5)) {
            if (mainMenuStep >= screenW / 20) {
                mainMenuSelected();
            }
        } else if (isKeyPress(11) || isKeyPress(2)) {
            mainItemID = (byte) (mainItemID - 1);
            if (mainItemID < 0) {
                mainItemID = Cons.isCmobile ? (byte) (Cons.STR_MAINMENU2.length - 1) : (Cons.isSohu ? (byte) (Cons.STR_MAINMENU_SH.length - 1) : (Cons.isDL ? (byte) (Cons.STR_MAINMENU_DL.length - 1) : (byte) (Cons.STR_MAINMENU.length - 1)));
            }
            titlecount = 0;
        } else if (isKeyPress(13) || isKeyPress(8)) {
            mainItemID = (byte) (mainItemID + 1);
            if (mainItemID == (Cons.isCmobile ? Cons.STR_MAINMENU2.length : (Cons.isSohu ? Cons.STR_MAINMENU_SH.length : (Cons.isDL ? Cons.STR_MAINMENU_DL.length : Cons.STR_MAINMENU.length)))) {
                mainItemID = 0;
            }
            titlecount = 0;
        }
    }

    void getSMS_Content(int type) {
        if (HttpConn.sms_coin != null && !"".equals(HttpConn.sms_coin.trim())) {
            if (type == 15) {
                PCBindService.bindState = 1;
            }
            return;
        }
        if (type == 15) {
            PCBindService.bindState = 0;
        }
        httpConn = null;
    }

    private void mainMenuSelected() {
        String server;
        int i;
        isQuickLog = false;
        int type = mainItemID;
        if (type >= 2 && Cons.isDL) {
            type += 2;
        }
        if (type >= 3 && !Cons.isDL && !Cons.isSohu && !Cons.isCmobile) {
            type++;
        }
        switch (type) {
            case 0:
                setState((byte) 10);
                break;
            case 1:
                setState((byte) 10);
                isQuickLog = true;
                break;
            case 2:
                if (Cons.isCmobile || Cons.isSohu) {
                    setState((byte) 14);
                    break;
                }
                setState((byte) 33);
                AdvertiseSplash.getInstance().getClass();
                AdvertiseSplash.getInstance().setMoreGameState(2);
                break;
            case 3:
                if (Cons.isCmobile) {
                    setState((byte) 30);
                    break;
                }
                if (Cons.isSohu) {
                    getSMS_Content(15);
                    isInGame = false;
                    setState((byte) 29);
                }
                break;
            case 4:
                if (Cons.isCmobile || Cons.isSohu) {
                    setState((byte) 27);
                    break;
                }
                setState((byte) 14);
                break;
            case 5:
                if (Cons.isCmobile || Cons.isSohu) {
                    setState((byte) 9);
                    break;
                }
                getSMS_Content(15);
                isInGame = false;
                setState((byte) 29);
                break;
            case 6:
                if (Cons.isCmobile) {
                    setState((byte) 6);
                    break;
                }
                if (Cons.isSohu) {
                    Download.gotoURL(aMidlet, (byte) 9);
                    break;
                }
                setState((byte) 27);
                break;
            case 7:
                if (Cons.isCmobile) {
                    try {
                        aMidlet.platformRequest("http://go.i139.cn/gcomm1/portal/spchannel.do?url=http://gamepie.i139.cn/wap/s.do?j=3channel");
                        aMidlet.destroyApp(true);
                    } catch (Exception exception) {
                    }
                    break;
                }
                if (Cons.isSohu) {
                    setState((byte) 7);
                    break;
                }
                setState((byte) 9);
                break;
            case 8:
                if (Cons.isSohu) {
                    kongMD5 = "";
                    userName = "guest";
                    userPassword = "";
                    String str = aMidlet.getAppProperty("LocalServer");
                    str = " 游客,g1.ebsee.com,30000,6";
                    PCLogin.serverListArr = Util.split(str, str.length(), ',');
                    PCLogin.serverListCnt = (byte) (PCLogin.serverListArr.length / 4);
                    serverListUrl = new String[PCLogin.serverListCnt][4];
                    for (int j = 0; j < 4; j++) {
                        serverListUrl[0][j] = PCLogin.serverListArr[j];
                    }
                    selectServerID = 0;
                    serverUrl = "socket://" + serverListUrl[selectServerID][1] + ":" + serverListUrl[selectServerID][2];
                    str = null;
                    bindPopState = 2;
                    ni.send(16777472);
                    setState((byte) 13);
                    break;
                }
                if (Cons.isCmobile) {
                    setState((byte) 7);
                    break;
                }
                try {
                    if ("dj".equals(jarFrom) || "1djo".equals(jarFrom) || "1dj3".equals(jarFrom) || "1dj4".equals(jarFrom) || "1dj5".equals(jarFrom) || "1dj6".equals(jarFrom) || "1sna".equals(jarFrom) || "2sna".equals(jarFrom) || "1you".equals(jarFrom) || "1yo3".equals(jarFrom) || "17sy".equals(jarFrom)) {
                        aMidlet.platformRequest("http://g.ko.cn/gk/mtwap/push/index.jsp");
                        aMidlet.destroyApp(true);
                        break;
                    }
                    Download.gotoURL(aMidlet, (byte) 13);
                } catch (Exception e) {
                    aMidlet.destroyApp(true);
                }
                break;
            case 9:
                if (Cons.isSohu) {
                    Download.gotoURL(aMidlet, (byte) 9);
                    break;
                }
                setState((byte) 7);
                break;
            case 10:
                kongMD5 = "";
                userName = "guest";
                userPassword = "";
                server = aMidlet.getAppProperty("LocalServer");
                server = " 游客,g1.ebsee.com,30000,6";
                PCLogin.serverListArr = Util.split(server, server.length(), ',');
                PCLogin.serverListCnt = (byte) (PCLogin.serverListArr.length / 4);
                serverListUrl = new String[PCLogin.serverListCnt][4];
                for (i = 0; i < 4; i++) {
                    serverListUrl[0][i] = PCLogin.serverListArr[i];
                }
                selectServerID = 0;
                serverUrl = "socket://" + serverListUrl[selectServerID][1] + ":" + serverListUrl[selectServerID][2];
                server = null;
                bindPopState = 2;
                ni.send(16777472);
                setState((byte) 13);
                break;
        }
    }
    public static int countProduct = 0;
    public static String[] strForPop;
    public static int[][] colorChange;
    public static int[][] colorChange2;
    boolean isInHelp;

    private final void drawAbout(Graphics g) {
        drawGroundback(g);
        UIRim.drawRim(g, 0, 0, screenW - 1, screenH - 1, (byte) 4);
        UIRim.drawRim(g, 0, 0, screenW - 1, 23, (byte) 4);
        String title = "天劫Online";
        int y = 3;
        g.setColor(16764533);
        g.drawString(title, screenW >> 1, y, 17);
        g.drawString("返回", screenW - 40, screenH - 25, 20);
        int topy = 25;
        g.setClip(0, topy, screenW, screenH - topy - 17);
        countProduct++;
        int py = screenH - countProduct * 5;
        if (countProduct > 260) {
            countProduct = 0;
        }
        topy = py;
        int color = 0;
        for (int i = 0; i < Cons.PRODUCT.length; i++) {
            py = topy + i * 23;
            if (py >= 10) {
                if (py > screenH - 5) {
                    break;
                }
                if (i == 15 || i == 17 || (i > 18 && Cons.PRODUCT[i].length() == 2)) {
                    color = 16764533;
                } else {
                    color = 16774294;
                }
                g.setColor(color);
                g.drawString(Cons.PRODUCT[i], screenW >> 1, py, 17);
            }
        }
    }

    public static void setPop(byte type) {
        if (!Cons.newPlayerHelp) {
            return;
        }
        strPop = (String[][]) null;
        colorChange2 = (int[][]) null;
        if (strForPop == null) {
            strForPop = new String[Cons.STR_POP.length];
            colorChange = new int[Cons.STR_POP.length][20];
            int i;
            for (i = 0; i < colorChange.length; i++) {
                for (int j = 0; j < (colorChange[i]).length; j++) {
                    colorChange[i][j] = -1;
                }
            }
            for (i = 0; i < Cons.STR_POP.length; i++) {
                if (Cons.STR_POP[i].startsWith("@PLAYERNAME")) {
                    strForPop[i] = "" + (Player.getInstance()).name + Cons.STR_POP[i].substring(11);
                } else {
                    strForPop[i] = Cons.STR_POP[i];
                }
            }
            for (i = 0; i < strForPop.length; i++) {
                int flagNum = 0;
                char[] tmp = strForPop[i].toCharArray();
                for (int j = 0; j < tmp.length; j++) {
                    if (tmp[j] == '$' && flagNum < (colorChange[i]).length) {
                        colorChange[i][flagNum] = j - flagNum - 1;
                        flagNum++;
                    }
                }
            }
            for (i = 0; i < Cons.STR_POP.length; i++) {
                if (Cons.STR_POP[i].startsWith("@PLAYERNAME")) {
                    strForPop[i] = "" + (Player.getInstance()).name + Cons.STR_POP[i].substring(11);
                } else {
                    strForPop[i] = Cons.STR_POP[i];
                }
                char[] tempChars = strForPop[i].toCharArray();
                StringBuffer sb = new StringBuffer();
                for (int j = 0; j < tempChars.length; j++) {
                    if (tempChars[j] != '$') {
                        sb.append(tempChars[j]);
                    }
                }
                strForPop[i] = sb.toString();
            }
        }
        switch (type) {
            case 0:
                strPop = new String[1][];
                colorChange2 = new int[1][];
                colorChange2[0] = colorChange[0];
                strPop[0] = Util.wrapText(strForPop[0], pop_content_w, font[1]);
                popRecord[0] = 1;
                Util.saveRecord(popRecord, (Player.getInstance()).name);
                break;
            case 1:
                strPop = new String[1][];
                colorChange2 = new int[1][];
                colorChange2[0] = colorChange[3];
                strPop[0] = Util.wrapText(strForPop[3], pop_content_w, font[1]);
                bindPopState = 3;
                break;
            case 6:
                strPop = new String[1][];
                colorChange2 = new int[1][];
                colorChange2[0] = colorChange[8];
                strPop[0] = Util.wrapText(strForPop[8], pop_content_w, font[1]);
                bindPopState = 3;
                break;
            case 7:
                strPop = new String[1][];
                colorChange2 = new int[1][];
                colorChange2[0] = colorChange[9];
                strPop[0] = Util.wrapText(strForPop[9], pop_content_w, font[1]);
                bindPopState = 4;
                break;
            case 2:
                if (popRecord[1] == 1) {
                    return;
                }
                strPop = new String[1][];
                colorChange2 = new int[1][];
                colorChange2[0] = colorChange[4];
                strPop[0] = Util.wrapText(strForPop[4], pop_content_w, font[1]);
                popRecord[1] = 1;
                Util.saveRecord(popRecord, (Player.getInstance()).name);
                bindPopState = 9;
                break;
            case 3:
                if (popRecord[2] == 1) {
                    return;
                }
                strPop = new String[1][];
                colorChange2 = new int[1][];
                colorChange2[0] = colorChange[5];
                strPop[0] = Util.wrapText(strForPop[5], pop_content_w, font[1]);
                popRecord[2] = 1;
                Util.saveRecord(popRecord, (Player.getInstance()).name);
                break;
            case 4:
                strPop = new String[1][];
                colorChange2 = new int[1][];
                colorChange2[0] = colorChange[6];
                strPop[0] = Util.wrapText(strForPop[6], pop_content_w, font[1]);
                break;
            case 5:
                strPop = new String[1][];
                colorChange2 = new int[1][];
                colorChange2[0] = colorChange[7];
                strPop[0] = Util.wrapText(strForPop[7], pop_content_w, font[1]);
                break;
        }
        isPop = true;
        popPointer = 0;
        contentPointer = 0;
    }

    public void drawPop(Graphics g) {
        int rectBigStateX = 32;
        int rectBigW = screenW - 64 + 3;
        int rectBigH = (CHARH + 3) * 7 + 8 + 6;
        int rectBigStateY = (screenH - rectBigH) / 2 - 5;
        int rectTilteStateX = rectBigStateX + 2;
        int rectTilteStateY = rectBigStateY + 2;
        int rectTilteW = rectBigW - 4;
        int rectTilteH = CHARH + 3;
        int rectContentStateX = rectTilteStateX;
        int rectContentStateY = rectTilteStateY + rectTilteH + 2;
        int rectContentW = rectBigW - 4;
        int rectContentH = (CHARH + 3) * 5 + 6;
        int rectComStateX = rectTilteStateX;
        int rectComStateY = rectContentStateY + rectContentH + 2;
        int rectComW = rectBigW - 4;
        int rectComH = CHARH + 3;
        int barArrowUpStateX = rectContentStateX + rectContentW - 6;
        int barArrowUpStateY = rectContentStateY;
        int barArrowUpWH = 6;
        int barArrowDownStateX = barArrowUpStateX;
        int barArrowDownStateY = rectContentStateY + rectContentH - 6;
        int barArrowDownWH = 6;
        int barLineStateX = barArrowUpStateX;
        int barLineStateY = barArrowUpStateY + barArrowUpWH;
        int barLineEndX = barArrowDownStateX;
        int barLineEndY = barArrowDownStateY;
        int barFillW = 3;
        int barFillH = rectContentH - barArrowDownWH - barArrowUpWH - 2;
        if ((strPop[popPointer]).length > 5) {
            barFillH /= (strPop[popPointer]).length - 5 + 1;
        }
        g.setColor(1970957);
        g.fillRect(rectBigStateX, rectBigStateY, rectBigW, rectBigH);
        g.setColor(10321225);
        g.drawRect(rectBigStateX, rectBigStateY, rectBigW, rectBigH);
        g.setColor(8415039);
        g.drawRect(rectTilteStateX, rectTilteStateY, rectTilteW, rectTilteH);
        g.setColor(14202231);
        g.fillRect(rectTilteStateX + 1, rectTilteStateY + 1, rectTilteW - 1, rectTilteH - 1);
        g.setColor(0);
        g.drawString("新手看看", screenW >>> 1, rectTilteStateY + 2, 17);
        g.setColor(8415039);
        g.drawRect(rectContentStateX, rectContentStateY, rectContentW, rectContentH);
        g.drawRect(barArrowUpStateX, barArrowUpStateY, barArrowUpWH, barArrowUpWH);
        mImgUI[23].draw(g, barArrowUpStateX + 1, barArrowUpStateY + 1, 0, 16384);
        g.drawRect(barArrowDownStateX, barArrowDownStateY, barArrowDownWH, barArrowDownWH);
        mImgUI[23].draw(g, barArrowDownStateX + 1, barArrowDownStateY + 2, 0, false);
        g.drawLine(barLineStateX, barLineStateY, barLineEndX, barLineEndY);
        g.setColor(11701843);
        if (contentPointer == (strPop[popPointer]).length - 5) {
            g.fillRect(barLineStateX + 2, barArrowDownStateY - 2 - barFillH, barFillW, barFillH);
        } else {
            g.fillRect(barLineStateX + 2, rectContentStateY + barArrowUpWH + 2 + barFillH * contentPointer, barFillW, barFillH);
        }
        g.setColor(15583626);
        for (int i = 0; i < 5 && i + contentPointer < (strPop[popPointer]).length; i++) {
            char[] chrTmp = strPop[popPointer][i + contentPointer].toCharArray();
            int curCharIndex = 0;
            for (int k = 0; k < i + contentPointer; k++) {
                curCharIndex += strPop[popPointer][k].length();
            }
            int off = 0;
            for (int m = 0; m < chrTmp.length; m++) {
                for (int n = 0; n < (colorChange2[popPointer]).length; n++) {
                    if (colorChange2[popPointer][n] == curCharIndex + m - 1) {
                        if (n % 2 == 0) {
                            g.setColor(13041408);
                        } else {
                            g.setColor(15583626);
                        }
                    }
                }
                g.drawChar(chrTmp[m], rectContentStateX + 5 + off, rectContentStateY + 2 + i * g.getFont().getHeight(), 0);
                off += font[1].charWidth(chrTmp[m]);
            }
        }
        g.setColor(8415039);
        g.drawRect(rectComStateX, rectComStateY, rectComW, rectComH);
        if (strPop.length > 1) {
            if (popPointer != 0) {
                mImgUI[22].draw(g, rectComStateX + (rectComW - (CHARW + 2) * 2) / 2 + 0, rectComStateY + 6, 0, false);
            }
            if (popPointer != strPop.length - 1) {
                mImgUI[22].draw(g, rectComStateX + (rectComW - (CHARW + 2) * 2) / 2 + 34, rectComStateY + 6, 0, true);
            }
        }
        g.drawString("关闭", rectComStateX + rectComW - (CHARW + 2) * 2, rectComStateY + 2, 0);
    }

    private void drawGameRun(Graphics g) {
        switch (getGameState()) {
            case 0: {
                showGame(g);
                break;
            }
            case 1: {  // 有菜单
                switch (getRightMenuSubState()) {
                    case 80: {
                        drawUIValueAddedCatalogList(g);
                        break;
                    }
                    case 100: {
                        drawRightMenuWait(g);
                        break;
                    }
                    case -1: {
                        showGame(g);
                        if (baseForm == null) {
                            baseForm = new UIForm(0, 0, screenW, screenH, "");
                            baseForm.setBackGround((byte) 9);
                            // 设置菜单类型
                            menus[0] = new UIMenu(0, 0, 66, 120, null, Cons.STR_RIGHT_MENU);
                            menus[0].setCurrentpointer(rightMenuId);
                            labels[0] = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 0, (byte) 0);
                            baseForm.addComponentInCenter(labels[0], (byte) 6);
                            baseForm.addComponentInCenter(menus[0], (byte) 4);
                            baseForm.setFocus(true);
                        }
                        baseForm.draw(g);
                        break;
                    }
                    case 0: {
                        PCIncrementService.getInstance().draw(g);
                        break;
                    }
                    case 10: {
                        PCIncrementService.getInstance().draw(g);
                        break;
                    }
                    case 1: {
                        drawUIManAttribute(g);
                        break;
                    }
                    case 24: {
                        UIGameRun.getInstance().drawTitleList(g);
                        break;
                    }
                    case 2: {
                        drawUIManPackage(g);
                        break;
                    }
                    case 20: {
                        drawUIManSkill(g);
                        break;
                    }
                    case 3: {
                        switch (getUIFriendState()) {
                            case 0:
                                drawUIFriendLookup(g);
                                break;
                            case 2:
                                drawUIFriendList(g);
                                break;
                            case 3:
                                drawUIBlackList(g);
                                break;
                            case 4:
                                drawDramatisUnion(g);
                                break;
                        }
                        break;
                    }
                    case 4: {
                        drawUITask(g);
                        break;
                    }
                    case 22: {
                        switch (getUIPetState()) {
                            case 2:
                                drawUIPetThingslist(g);
                                break;
                            case 8:
                                drawUIPetComposeStuff(g);
                                break;
                            case 7:
                                drawUIPetComposeWeapon(g);
                                break;
                            case 5:
                                drawUIPetComposeResult(g);
                                break;
                            case 100:
                                drawUIPetWait(g);
                                break;
                        }
                        break;
                    }
                    case 23: {
                        drawUICompose(g);
                        break;
                    }
                    case 7: {
                        switch (getUISetupState()) {
                            case 0:
                                drawUISetupDirection(g);
                                break;
                            case 3:
                                drawUISetupFunction(g);
                                break;
                            case 2:
                                drawUIShowItem(g);
                                break;
                            case 1:
                                drawUIShortcut(g);
                                break;
                        }
                        break;
                    }
                    case 5: {
                        switch (getUIMapState()) {
                            case 0:
                                Map.drawWorldMap(g);
                                break;
                            case 1:
                                drawUINPCPos(g);
                                break;
                        }
                        break;
                    }
                    case 8: {
                        switch (getUIHelpState()) {
                            case 2:
                                PCBindService.getInstance().draw(g);
                                break;
                        }
                        break;
                    }
                    case 9: {
                        showGame(g);
                        if (baseForm != null) {
                            baseForm.draw(g);
                        }
                        break;
                    }
                }
                break;
            }
            case 2: {  // 左菜单
                switch (getLeftMenuSubState()) {
                    case -1:
                        showGame(g);
                        if (baseForm == null) {
                            baseForm = new UIForm(0, 0, screenW, screenH, "");
                            baseForm.setBackGround((byte) 9);
                            menus[0] = new UIMenu(0, 0, 80, 0, null, Cons.STR_LEFT_MENU);
                            if (ObjManager.currentTarget.state == 5) {
                                menus[0].setNoUse((byte) 5);
                            }
                            labels[0] = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 0, (byte) 0);
                            baseForm.addComponentInCenter(labels[0], (byte) 6);
                            baseForm.addComponentInCenter(menus[0], (byte) 4);
                            baseForm.setFocus(true);
                        }
                        baseForm.draw(g);
                        resetKey();
                        break;
                    case 0:
                        drawOtherInfor(g);
                        break;
                    case 1:
                        drawUIAddFriend(g);
                        break;
                    case 2:
                        drawUIGroupInvite(g);
                        break;
                    case 3:
                        drawUIPrivateChat(g);
                        break;
                    case 4:
                        tickBusiness();
                        drawUIBusiness(g);
                        resetKey();
                        break;
                    case 5:
                        drawUIPKInvite(g);
                        break;
                    case 100:
                        drawLeftMenuWait(g);
                        break;
                    case 7:
                        showGame(g);
                        baseForm.draw(g);
                        break;
                }
                break;
            }
            case 3: {  // NPC菜单
                if (getNPCSubState() == 7) {
                    tickAuction();
                }
                switch (getNPCSubState()) {
                    case 0:
                        drawUINPCMenu(g);
                        break;
                    case 120:
                        drawUINPCTask(g);
                        break;
                    case 2:
                        drawUINPCTrade(g);
                        break;
                    case 3:
                        drawUINPCFix(g);
                        break;
                    case 4:
                        drawUIPetLearnSkills(g);
                        break;
                    case 5:
                        drawUINPCAdoptPet(g);
                        break;
                    case 6:
                        drawUINPCLabourUnion(g);
                        break;
                    case 7:
                        drawAuction(g);
                        break;
                    case 8:
                        drawUINPCSmith(g);
                        break;
                    case 9:
                        drawUINPCStorehouse(g);
                        break;
                    case 62:
                        PCMarriage.getInstance().drawMetempsychosis(g);
                        break;
                    case 10:
                        drawUINPCExchange(g);
                        break;
                    case 11:
                        switch (getNPCMailState()) {
                            case 0:
                                drawUIMailLookup(g);
                                break;
                            case 1:
                                drawUIMailWrite(g);
                                break;
                        }
                        break;
                    case 100:
                        showGame(g);
                        if (baseForm == null) {
                            baseForm = new UIForm(0, 0, screenW, screenH, "");
                            baseForm.setBackGround((byte) 9);
                            baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        }
                        baseForm.draw(g);
                        break;
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                        drawUINPCTop(g);
                        break;
                    case 27:
                        drawBattleGroundRank(g);
                        break;
                    case 28:
                        drawBattleGroundRate(g);
                        break;
                    case 34:
                        PCArena.getInstance().draw(g);
                        break;
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                    case 76:
                    case 77:
                    case 78:
                        ClanWar.getInstance().draw(g);
                        break;
                    case 56:
                        drawUINPCMenu(g);
                        break;
                    case 83:
                        UIGameRun.getInstance().drawNPCTitleList(g);
                        break;
                }
                break;
            }
            case 7: {  // 快速聊天
                showGame(g);
                drawFastChat(g);
                break;
            }
            case 8: {
                showGame(g);
                switch (getOtherSubState()) {
                    case 0:
                        if (baseForm != null) {
                            baseForm.draw(g);
                        }
                        break;
                    case 1:
                        closePop();
                        drawDead(g);
                        break;
                    case 2:
                    case 3:
                        if (baseForm == null) {
                            baseForm = new UIForm(0, 0, screenW, screenH, "");
                            baseForm.setBackGround((byte) 9);
                            baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        }
                        baseForm.draw(g);
                        break;
                    case 4:
                        setOtherSubState((byte) 3);
                        ni.send(33557504);
                        loadCount = 0;
                        waitCnt = 0;
                        break;
                }
                break;
            }
            case 10: {  // 增值服务
                PCIncrementService.getInstance().draw(g);
                break;
            }
        }
    }

    private void closePop() {
        isPop = false;
        popPointer = 0;
        contentPointer = 0;
    }

    public boolean keyFilterInFollowing() {
        int keyFilter = 212991;
        if ((keyFlagIm & keyFilter) == 0) {
            return false;
        }
        if ((keyFlagIm >>> 17 & 0x1) == 1) {
            if (topForm != null) {
                return true;
            }
            if (getGameState() == 0) {
                return false;
            }
            return true;
        }
        if ((keyFlagIm >>> 18 & 0x1) == 1) {
            if (getGameState() == 0) {
                return false;
            }
            return true;
        }
        return true;
    }

    private void keyInGameRun() {
        // 如果玩家是寻径状态，并且按了方向键，取消寻径
        if (Player.getInstance().isFollow() && keyFilterInFollowing()) {
            Player.getInstance().resetAimID();
        }
        if (keyInTopForm()) {
            return;
        }
        if (isPop) {
            return;
        }
        switch (getGameState()) {  // 游戏中状态
            case 0: {  // 游戏中
                if (isKeyPress(18)) {  // 如果按了右软键，显示右键菜单
                    releaseUI();
                    setGameState((byte) 1);
                    setRightMenuSubState(-1);
                    resetKey();
                    break;
                }
                if (isKeyPress(17)) {  // 如果按了左软键
                    if (ObjManager.currentTarget == null) {
                        return;
                    }
                    if (ObjManager.currentTarget.type == 1) {  // 如果当前选择对象的是玩家
                        if ((Player.getInstance()).group != ObjManager.currentTarget.group || ObjManager.currentTarget.isEnemy) {
                            return;
                        }
                        releaseUI();
                        setGameState((byte) 2);
                        chatNowTarget = ObjManager.currentTarget.name;
                        selectedManId = chatFriendId = ObjManager.currentTarget.objID;
                        localChatChannel = 1;
                    } else if (ObjManager.currentTarget.type == 3) {  // 如果当前选择对象的是NPC
                        NPCIndex = ObjManager.currentTarget.objID;
                        setGameState((byte) 3);
                        setNPCSubState((byte) 100);
                        waitCnt = 0;
                        releaseUI();
                        ni.send(150994944);
                    }
                    resetKey();
                    break;
                }
                if (isKeyPress(15)) {  // 快速聊天
                    releaseUI();
                    setGameState((byte) 7);
                    resetKey();
                    break;
                }
                if (isKeyPress(9)) { // 9键盘
                    switch (Cons.nineShort) {
                        case 0:
                            Cons.use2468 = !Cons.use2468;
                            Cons.use5 = !Cons.use5;
                            shortcut_9[1] = false;
                            break;
                        case 1:
                            shortcut_9[Cons.nineShort] = true;
                            Cons.use2468 = false;
                            Cons.use5 = false;
                            break;
                    }
                    resetKey();
                    break;
                }
                if (isKeyPress(0)) {
                    Cons.zeroShort = (byte) (Cons.zeroShort + 1);
                    if (Cons.zeroShort > 2) {
                        Cons.zeroShort = 0;
                    }
                    configShow(Cons.zeroShort);
                    Cons.zeroShortShow = 0;
                }
                break;
            }
            case 1: {   // 右键菜单栏状态
                switch (getRightMenuSubState()) {
                    case -1: {
                        keyInRightMenu();
                        break;
                    }
                    case 0: {
                        PCIncrementService.getInstance().userEvent();
                        break;
                    }
                    case 1: {
                        keyInUIManAttribute();
                        break;
                    }

                    case 2: {
                        keyInUIManPackage();
                        break;
                    }
                    case 3: {
                        keyInSociety();
                        break;
                    }
                    case 4: {
                        keyInTask();
                        break;
                    }
                    case 5: {
                        keyInAllMap();
                        break;
                    }
                    case 7: {
                        keyInSetup();
                        break;
                    }
                    case 8: {
                        keyInHelp();
                        break;
                    }
                    case 10: {
                        PCIncrementService.getInstance().actionToBuy();
                        break;
                    }
                    case 20: {
                        keyInUIManSkill();
                        break;
                    }
                    case 22:
                    case 23: {
                        keyInPet();
                        break;
                    }
                    case 24: {
                        keyInUIManTitle();
                        break;
                    }
                    case 80: {
                        keyInValueAddedCatalogList();
                        break;
                    }
                }
                resetKey();
                break;
            }
            case 2: {  // 左键菜单栏
                switch (getLeftMenuSubState()) {
                    case -1: {
                        keyInLeftMenu();
                        resetKey();
                        break;
                    }
                    case 0: {  // 其他玩家菜单
                        keyInOtherInfor();
                        break;
                    }
                    case 7: {
                        keyInCheatCheck();
                        break;
                    }
                }
                break;
            }
            case 3: {    //  NPC面板
                switch (getNPCSubState()) {
                    case 0: {
                        keyInNPCMenu();
                        break;
                    }
                    case 2: {
                        keyInNPCTrade();
                        break;
                    }
                    case 3: {
                        keyInNPCFix();
                        break;
                    }
                    case 4: {
                        keyInNPCPetLearnSkills();
                        break;
                    }
                    case 5: {
                        keyInNPCAdoptPet();
                        break;
                    }
                    case 6: {
                        keyInNPCUnion();
                        break;
                    }
                    case 8: {
                        keyInNPCSmith();
                        break;
                    }
                    case 9: {
                        keyInNPCStore();
                        break;
                    }
                    case 10: {
                        keyInNPCExchange();
                        break;
                    }
                    case 11: {
                        keyInMail();
                        break;
                    }
                    case 22:
                    case 23:
                    case 24:
                    case 25: {
                        keyInNPCTop();
                        break;
                    }
                    case 27: {
                        keyInBattleGroundRank();
                        break;
                    }
                    case 28: {
                        keyInBattleGroundRate();
                        break;
                    }
                    case 34: {
                        PCArena.getInstance().keyPressed();
                        break;
                    }
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                    case 76:
                    case 77:
                    case 78: {
                        ClanWar.getInstance().tick();
                        break;
                    }
                    case 56: {
                        keyInSilk();
                        break;
                    }
                    case 62: {
                        PCMarriage.getInstance().keyInMetempsyshosis();
                        break;
                    }
                    case 83: {
                        keyInNPCTitle();
                        break;
                    }
                    case 120: {
                        keyInNPCTask();
                        break;
                    }
                }
                break;
            }
            case 7: {  // 快速聊天
                keyInFastChat();
                break;
            }
            case 8: {  // 切换状态，比如切换地图
                switch (getOtherSubState()) {
                    case 0:
                        releaseUI();
                        setGameState((byte) 0);
                        break;
                    case 1:
                        if (isKeyPress(17) && isDeadRock == 1) {
                            PCGameObj.isRock = 1;
                            isDeadRock = 2;
                            ni.send(33557888);
                            break;
                        }
                        if (isKeyPress(18) && isDeadRock == 1) {
                            isDeadRock = 0;
                            setOtherSubState((byte) 1);
                            break;
                        }
                        if (isKeyPress(16) && isDeadRock == 0) {
                            isDeadRock = 2;
                            if (isDeadLoad) {
                                return;
                            }
                            isDeadLoad = true;
                            if (dramatisPackage != null) {
                                dramatisPackage.setSubMenu((UIMenu) null);
                            }
                            setOtherSubState((byte) 4);
                        }
                        break;
                }
                break;
            }
            case 10: {   // 增值服务面板
                PCIncrementService.getInstance().userEvent();
                resetKey();
                break;
            }
        }
    }
    public static boolean isDeadLoad = false;
    String chatAlertStr;
    byte[] decompose;

    public void keyInFastChat() {
        if (baseForm == null) {
            return;
        }
        UIComponent cmd = baseForm.getCommand();
        if (isKeyPress(17) && "nofriends".equals(baseForm.getCurrentFocusForm().getName())) {
            baseForm.setAboutForm((UIForm) null);
        } else if (isKeyPress(18) && "friends".equals(baseForm.getCurrentFocusForm().getName())) {
            baseForm.setAboutForm((UIForm) null);
        } else if (isKeyPress(17) && "outline".equals(baseForm.getCurrentFocusForm().getName())) {
            baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
        } else if ("message".equals(baseForm.getCurrentFocusForm().getName())) {
            if (isKeyPress(17)) {
                baseForm.setAboutForm((UIForm) null);
                menus[0].setSubMenu((UIMenu) null);
            }
        } else if (isKeyPress(18) && baseForm.getCurrentFocusForm() == baseForm) {
            if (menus[0].getSubMenu() != null) {
                menus[0].setSubMenu((UIMenu) null);
            } else {
                setGameState((byte) 0);
                releaseUI();
            }
        } else if (isKeyPress(15)) {
            setGameState((byte) 0);
            releaseUI();
        } else if (!actionInForm(cmd)) {
            if (cmd == menus[0] && cmd != null) {
                if (isKeyPress(14) || isKeyPress(17)) {
                    fastChatIndex = menus[0].getCurrentPointer();
                    if (chatNowContent == null) {
                        chatNowContent = new String();
                    }
                    if (menus[0].getSubMenu() == null) {
                        localChatChannel = (byte) (menus[0].getCurrentPointer() + 1);
                        if (localChatChannel == 1) {
                            PCChat.chatBlock = true;
                            String[] items = new String[PCChat.chatPlayers.size() + 1];
                            if (PCChat.chatPlayers.size() != 0) {
                                PCChat.chatPlayers.copyInto((Object[]) items);
                            }
                            PCChat.chatIDsBak = new Integer[PCChat.chatIDs.size()];
                            PCChat.chatIDs.copyInto((Object[]) PCChat.chatIDsBak);
                            PCChat.chatBlock = false;
                            items[items.length - 1] = "[好友]";
                            int width = 0;
                            for (int i = 0; i < items.length; i++) {
                                if (font[0].stringWidth(items[i]) > width) {
                                    width = font[0].stringWidth(items[i]);
                                }
                            }
                            menus[1] = new UIMenu(0, 0, width + 20, 0, null, items);
                            menus[0].setSubMenu(menus[1]);
                            chatFriendId = (Player.getInstance()).objID;
                        } else if (menus[0].getCurrentPointer() + 3 == 8) {
                            localChatChannel = 8;
                            PCChat.isPrivateChannel = false;
                            initTrumpetShowDisplay();
                        } else {
                            if (teamJob == 0 && localChatChannel == 5) {
                                baseForm.addAboutForm("message", "您没有组队!", (byte) 1, 160, 50);
                                return;
                            }
                            PCChat.isPrivateChannel = false;
                            chatAlertStr = null;
                            switch (menus[0].getCurrentPointer()) {
                                case 1:
                                    if ((Cons.channelOption >> 1 & 0x1) == 1) {
                                        chatAlertStr = "注意:世界频道已设置为关闭";
                                    }
                                    break;
                                case 2:
                                    if ((Cons.channelOption >> 2 & 0x1) == 1) {
                                        chatAlertStr = "注意:氏族频道已设置为关闭";
                                    }
                                    break;
                                case 3:
                                    if ((Cons.channelOption >> 3 & 0x1) == 1) {
                                        chatAlertStr = "注意:场景频道已设置为关闭";
                                    }
                                    break;
                                case 4:
                                    if ((Cons.channelOption >> 4 & 0x1) == 1) {
                                        chatAlertStr = "注意:组队频道已设置为关闭";
                                    }
                                    break;
                            }
                            initChatForm("发送到" + menus[0].getCurrentItem() + ":");
                        }
                    } else {
                        int index = menus[1].getCurrentPointer();
                        if (index != PCChat.chatIDsBak.length) {
                            chatFriendId = PCChat.chatIDsBak[index].intValue();
                            chatName = menus[1].getCurrentItem();
                            PCChat.isPrivateChannel = true;
                            chatAlertStr = null;
                            if ((Cons.channelOption & 0x1) == 1) {
                                chatAlertStr = "注意:私聊频道已设置为关闭";
                            }
                            initChatForm("与[" + menus[1].getCurrentItem() + "]聊天:");
                        } else {
                            requestFriendPlace = 2;
                            ni.send(201327360);
                            baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        }
                    }
                }
            } else if (cmd == tables[1] && cmd != null && (isKeyPress(14) || isKeyPress(17))) {
                int index = tables[1].getCurrentPointer();
                if (tables[1].getCurrentColor() == 8092539) {
                    baseForm.getCurrentFocusForm().addAboutForm("outline", "好友已经下线!", (byte) 1, screenW - 20, 30);
                    return;
                }
                chatFriendId = friendListID[index];
                chatName = friendList[tables[1].getCurrentPointer()][0];
                PCChat.isPrivateChannel = true;
                chatAlertStr = null;
                if ((Cons.channelOption & 0x1) == 1) {
                    chatAlertStr = "注意:私聊频道已设置为关闭";
                }
                initChatForm("与[" + chatName + "]聊天: ");
            }
        }
    }

    public void initTrumpetShowDisplay() {
        commonForm = null;
        commonOk = null;
        commonBack = null;
        commonTextField = null;
        commontf = null;
        if (commonOk == null) {
            commonBack = new Command("返回", 2, 2);
        }
        if (commonForm == null) {
            commonForm = new Form("聊天");
            commonForm.addCommand(commonBack);
            if ((Cons.channelOption >> 5 & 0x1) == 1) {
                commonForm.append("注意:喇叭频道已设置为关闭");
            }
            commonForm.setCommandListener(this);
            for (int i = 0; i < chatContent.length && !chatContent[i].equals(""); i++) {
                if (chatContent[i].startsWith(Cons.STR_CHAT_CHANNEL[8])) {
                    UIStringItem uisi = new UIStringItem(chatContent[i]);
                    commonForm.append((Item) uisi);
                }
            }
        }
        aMidlet.display.setCurrent((Displayable) commonForm);
    }

    void initChatForm(String textLabel) {
        for (int i = chatForm.size() - 1; i > 0; i--) {
            chatForm.delete(i);
        }
        int num = chatContent.length;
        if (localChatChannel == 8) {
            chatText.setMaxSize(20);
        } else {
            chatText.setMaxSize(30);
        }
        if (chatAlertStr != null && !"".equals(chatAlertStr.trim())) {
            chatForm.append(chatAlertStr);
        }
        for (int j = 0; j < num && !chatContent[j].equals(""); j++) {
            if (PCChat.isPrivateChannel) {
                if (chatContent[j].length() < 3) {
                    continue;
                }
                if (!chatContent[j].startsWith(Cons.STR_CHAT_CHANNEL[1])) {
                    continue;
                }
            } else if (localChatChannel == 8 && !chatContent[j].startsWith(Cons.STR_CHAT_CHANNEL[8])) {
                continue;
            }
            UIStringItem uisi = new UIStringItem(chatContent[j]);
            uisi.addCustomCommand(chatChannel[j], chatContent[j]);
            chatForm.append((Item) uisi);
            continue;
        }
        chatText.setLabel(textLabel);
        aMidlet.display.setCurrent((Displayable) chatForm);
    }

    public void keyInLeftMenu() {
        if (baseForm == null) {
            return;
        }
        UIComponent cmd = baseForm.getCommand();
        if (isKeyPress(14) || isKeyPress(17)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                if (ObjManager.currentTarget.name == (Player.getInstance()).name) {
                    setMessage(baseForm, "您的目标已经离开视野！");
                } else {
                    boolean flag;
                    int i;
                    switch (menus[0].getCurrentPointer()) {
                        case 3:
                            if (ObjManager.currentTarget == null) {
                                return;
                            }
                            chatName = chatNowTarget = ObjManager.currentTarget.name;
                            chatFriendId = ObjManager.currentTarget.objID;
                            localChatChannel = 1;
                            PCChat.isPrivateChannel = true;
                            chatAlertStr = null;
                            if ((Cons.channelOption & 0x1) == 1) {
                                chatAlertStr = "注意:私聊频道已设置为关闭";
                            }
                            initChatForm("与[" + chatNowTarget + "]聊天:");
                            break;
                        case 0:
                            if (ObjManager.currentTarget != null) {
                                ni.send(167772416);
                                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                            }
                            break;
                        case 1:
                            friendAddType = 1;
                            baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                            ni.send(201326848);
                            break;
                        case 2:
                            if (teamJob != 0 && teamJob != 1) {
                                setMessage(baseForm, "你不是队长不能邀请！");
                                break;
                            }
                            flag = false;
                            for (i = 0; i < teamMates.size(); i++) {
                                if (ObjManager.currentTarget.objID == ((GameObj) teamMates.elementAt(i)).objID) {
                                    flag = true;
                                }
                            }
                            if (teamMates.size() >= 3) {
                                setMessage(baseForm, "队伍人数已满!");
                                break;
                            }
                            if (flag) {
                                setMessage(baseForm, "已是队伍成员!");
                                break;
                            }
                            PCFriend.addTeamMateKind = 0;
                            teamLeaderOperat = 1;
                            teamTargetId = ObjManager.currentTarget.objID;
                            if ((Player.getInstance()).objID == teamTargetId) {
                                setMessage(baseForm, "不能和自己组队!");
                                teamLeaderOperat = 0;
                                teamTargetId = 0;
                                break;
                            }
                            ni.send(100663808);
                            PCChat.addChatScreen((byte) 7, "已发送邀请!");
                            UIGameRun.releaseChat();
                            setGameState((byte) 0);
                            releaseUI();
                            break;
                        case 4:
                            selectManId = ObjManager.currentTarget.objID;
                            releaseUI();
                            setLeftMenuSubState(4);
                            setBusinessState(9);
                            break;
                        case 5:
                            releaseUI();
                            setGameState((byte) 0);
                            PCGameObj.targetId = ObjManager.currentTarget.objID;
                            ni.send(268435712);
                            PCChat.addChatScreen((byte) 7, "已发送切磋邀请!");
                            UIGameRun.releaseChat();
                            break;
                        case 6:
                            releaseUI();
                            setGameState((byte) 0);
                            Player.getInstance().setFollowAimID(ObjManager.currentTarget.objID);
                            Player.getInstance().setAimRowAndColumn(ObjManager.currentTarget.aimRow, ObjManager.currentTarget.aimColumn);
                            break;
                    }
                }
            } else if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            }
        } else if (isKeyPress(18) && baseForm.getCurrentFocusForm() == baseForm) {
            releaseUI();
            setGameState((byte) 0);
        }
        if (actionInForm(cmd));
    }

    public void keyInOtherInfor() {
        if (baseForm == null) {
            return;
        }
        UIComponent cmd = baseForm.getCommand();
        if (isKeyPress(17) || isKeyPress(14)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                if (ObjManager.currentTarget.objID == (Player.getInstance()).objID) {
                    setGameState((byte) 0);
                    releaseUI();
                    PCChat.addChatScreen((byte) 7, "您选择的玩家已经离开您的视野");
                    UIGameRun.releaseChat();
                } else if (equipStuffId[((UIMImage) cmd).index] != 0) {
                    baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                    lookStuffPlace = (short) (UIMImage.sign = ((UIMImage) cmd).index);
                    lookType = 0;
                    ni.send(67110656);
                }
            } else if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            }
        } else if (isKeyPress(18)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                if (!(tables[0]).isVisible) {
                    (tables[0]).isVisible = true;
                    labels[9].setStr("返回");
                } else {
                    releaseUI();
                    setLeftMenuSubState(-1);
                }
            } else if ("detail".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            }
        } else if (actionInForm(cmd)) {
        }
    }

    /**
     * 处理右键菜单按键
     */
    public void keyInRightMenu() {
        if (baseForm == null) {
            return;
        }
        UIComponent cmd = baseForm.getCommand();
        String fouceFormName = baseForm.getCurrentFocusForm().getName();
        // 如果没有弹窗
        if (baseForm.getCurrentFocusForm() == baseForm) {
            if (menus[0] != null && menus[0].getSubMenu() == null) {
                for (int i = 0; i < keys.length; i++) {
                    // 按了0-9j键
                    if (isKeyPress(keys[i])) {
                        if (i == 0) {
                            menus[0].setCurrentpointer((byte) (Cons.STR_RIGHT_MENU.length - 2));
                        } else {
                            menus[0].setCurrentpointer((byte) (i - 1));
                        }
                        keyFlag |= 0x4000; //按下确认键
                    }
                }
                // 按了#键
                if (isKeyPress(16)) {
                    menus[0].setCurrentpointer((byte) (Cons.STR_RIGHT_MENU.length - 1));
                    keyFlag |= 0x4000;  //按下确认键
                }
                menus[0].setSubMenu(null);
                menus[1] = null;
                menus[2] = null;
            }
            if (isKeyPress(17) || isKeyPress(14)) {
                byte s;
                String confirm, name;
                if ("arena".equals(fouceFormName)) {
                    baseForm.setAboutForm((UIForm) null);
                }
                rightMenuId = menus[0].getCurrentPointer();
                switch (menus[0].getCurrentPointer()) {
                    case 0: {  // 挂机选项
                        baseForm.addAboutForm("hangup", "确实要挂机吗？", (byte) 2, 140, 0);
                        break;
                    }
                    case 1: {
                        if (menus[0].getSubMenu() == null) {
                            String[] attri = {"技能", "属性", "合成", "称号"};
                            menus[1] = new UIMenu((menus[0]).positionX + (menus[0]).width - 20, (menus[0]).positionY - 20 + 18, 80, 0, null, attri);
                            menus[0].setSubMenu(menus[1]);
                            break;
                        }
                        if (menus[0].getSubMenu().getSubMenu() == null) {
                            int id = menus[1].getCurrentPointer();
                            String[] attr = null;
                            switch (id) {
                                case 0:
                                    attr = Cons.SKILL_MENU;
                                    break;
                                case 1:
                                    baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                    ni.send(134217984);
                                    break;
                                case 2:
                                    pet.material = 0;
                                    baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                    ni.send(50334976);
                                    break;
                                case 3:
                                    titleCurrentPage = 0;
                                    baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                    ni.send(134219008);
                                    break;
                            }
                            if (id == 0) {
                                menus[2] = new UIMenu((menus[1]).positionX + (menus[1]).width - 20, (menus[1]).positionY - 20 + id * 18, 80, 0, null, attr);
                                menus[0].getSubMenu().setSubMenu(menus[2]);
                            }
                            break;
                        }
                        if (menus[0].getSubMenu().getSubMenu() != null) {
                            switch (menus[0].getSubMenu().getCurrentPointer()) {
                                case 0: {
                                    switch (menus[0].getSubMenu().getSubMenu().getCurrentPointer()) {
                                        case 0:
                                            baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                            skillTreeFlag = true;
                                            ni.send(117440768);
                                            break;
                                        case 1:
                                            baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                            skillTreeFlag = false;
                                            ni.send(117440768);
                                            break;
                                    }
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case 2:
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        ni.send(67109376);
                        break;
                    case 4:
                        ni.send(162530048);
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        break;
                    case 3:
                        if (menus[0].getSubMenu() == null) {
                            menus[1] = new UIMenu(0, 0, 80, 0, null, Cons.STR_GOOD_FRIENDS);
                            menus[0].setSubMenu(menus[1]);
                            break;
                        }
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        s = menus[0].getSubMenu().getCurrentPointer();
                        switch (s) {
                            case 0:
                                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                ni.send(201328128);
                                break;
                            case 1:
                                releaseUI();
                                clearAdvanceUIItem();
                                if (commonOk == null) {
                                    commonOk = new Command("确定", 4, 2);
                                    commonBack = new Command("返回", 2, 2);
                                }
                                if (commonTextField == null) {
                                    commonTextField = new TextField("请输入好友称呼：", "", 6, 0);
                                }
                                if (commonForm == null) {
                                    commonForm = new Form("nameForm");
                                    commonForm.setTitle("添加好友");
                                    commonForm.addCommand(commonOk);
                                    commonForm.addCommand(commonBack);
                                    commonForm.append((Item) commonTextField);
                                    commonForm.setCommandListener(this);
                                }
                                aMidlet.display.setCurrent((Displayable) commonForm);
                                break;
                            case 2:
                                requestFriendPlace = 0;
                                ni.send(201327360);
                                break;
                            case 3:
                                ni.send(201327616);
                                break;
                            case 4:
                                ni.send(251660032);
                                break;
                            case 5:
                                releaseUI();
                                setGameState((byte) 7);
                                resetKey();
                                break;
                        }
                        break;
                    case 6:
                        if (menus[0].getSubMenu() == null) {
                            if (teamJob == 0) {
                                baseForm.addAboutForm("message", "您没有组队!", (byte) 1, 160, 50);
                                break;
                            }
                            menus[1] = new UIMenu(0, 0, 80, 0, null, Cons.STR_TEAM_OPERATE);
                            menus[0].setSubMenu(menus[1]);
                            if (teamJob != 1) {
                                menus[1].setNoUse((byte) 0);
                                menus[1].setNoUse((byte) 1);
                            }
                            break;
                        }
                        if (menus[0].getSubMenu().getSubMenu() == null) {
                            String[] names;
                            int i;
                            if (teamJob == 0) {
                                baseForm.addAboutForm("message", "您已经先被逐出队伍", (byte) 1, 160, 50);
                                break;
                            }
                            switch (menus[1].getCurrentPointer()) {
                                case 0:
                                case 1:
                                    names = new String[teamMates.size()];
                                    for (i = 0; i < teamMates.size(); i++) {
                                        names[i] = ((GameObj) teamMates.elementAt(i)).name;
                                    }
                                    menus[2] = new UIMenu(0, 0, 80, 0, null, names);
                                    menus[1].setSubMenu(menus[2]);
                                    break;
                                case 2:
                                    teamLeaderOperat = 4;
                                    if (teamJob == 2 || teamJob == 1) {
                                        baseForm.addAboutForm("confirm", "确定退出队伍吗?", (byte) 2, 160, 50);
                                    }
                                    break;
                            }
                            break;
                        }
                        PCFriend.addTeamMateKind = 0;
                        teamTargetId = ((GameObj) teamMates.elementAt(menus[2].getCurrentPointer())).objID;
                        confirm = null;
                        name = ((GameObj) teamMates.elementAt(menus[2].getCurrentPointer())).name;
                        switch (menus[1].getCurrentPointer()) {
                            case 0:
                                confirm = "要开除" + name + "吗?";
                                teamLeaderOperat = 2;
                                break;
                            case 1:
                                confirm = "要选" + name + "为队长吗?";
                                teamLeaderOperat = 3;
                                break;
                        }
                        baseForm.addAboutForm("confirm", confirm, (byte) 2, 160, 50);
                        break;
                    case 7:
                        if (menus[0].getSubMenu() == null) {
                            menus[1] = new UIMenu(0, 0, 80, 0, null, Cons.STR_MENU_SETUP);
                            menus[0].setSubMenu(menus[1]);
                            break;
                        }
                        switch (menus[0].getSubMenu().getCurrentPointer()) {
                            case 0:
                                setRightMenuSubState(7);
                                setUISetupState((byte) 0);
                                releaseUI();
                                break;
                            case 3:
                                setRightMenuSubState(7);
                                setUISetupState((byte) 3);
                                releaseUI();
                                break;
                            case 1:
                                skillTreeFlag = false;
                                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                ni.send(117440768);
                                waitCnt = 0;
                                break;
                            case 2:
                                setRightMenuSubState(7);
                                setUISetupState((byte) 2);
                                releaseUI();
                                break;
                        }
                        break;
                    case 5:
                        if (menus[0].getSubMenu() == null) {
                            menus[1] = new UIMenu(0, 0, 80, 0, null, Cons.MAP_MENU);
                            menus[0].setSubMenu(menus[1]);
                            break;
                        }
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        switch (menus[0].getSubMenu().getCurrentPointer()) {
                            case 0:
                                worldmapplace = 0;
                                ni.send(536871936);
                                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                break;
                            case 1:
                                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                ni.send(164626432);
                                break;
                        }
                        break;
                    case 9:
                        if ("guest".equals(userName) || menus[0].getSubMenu() != null) {
                            baseForm.addAboutForm("sure", "您确定要离开游戏吗？", (byte) 2, 220, 0);
                            break;
                        }
                        menus[1] = new UIMenu(0, 0, 80, 0, null, Cons.LEAVE_MENU);
                        menus[0].setSubMenu(menus[1]);
                        break;
                }
            } else if (isKeyPress(18)) {
                if (menus[0].getSubMenu() == null) {
                    setGameState((byte) 0);
                    releaseUI();
                } else if (menus[0].getSubMenu().getSubMenu() != null) {
                    menus[0].getSubMenu().setSubMenu((UIMenu) null);
                } else {
                    menus[0].setSubMenu((UIMenu) null);
                }
            } else if (actionInForm(cmd)) {    // 处理当前菜单上下按键
            }
        } else if ("confirm".equals(fouceFormName)) {
            if (isKeyPress(17)) {
                switch (teamLeaderOperat) {
                    case 2:
                    case 3:
                        menus[0].getSubMenu().setSubMenu(null);
                        if (teamMates.size() <= 1) {
                            menus[0].setSubMenu(null);
                        }
                        ni.send(100663808);
                        baseForm.setAboutForm(null);
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        return;
                    case 4:
                        if (teamJob == 2) {
                            teamLeaderOperat = 2;
                        } else if (teamJob == 1) {
                            teamLeaderOperat = 1;
                        }
                        menus[0].setSubMenu(null);
                        baseForm.setAboutForm(null);
                        startWait(baseForm);
                        ni.send(100664064);
                        return;
                }
                baseForm.setAboutForm(null);
            } else if (isKeyPress(18)) {
                baseForm.setAboutForm(null);
            }
        } else if ("message".equals(fouceFormName)) {
            if (isKeyPress(17)) {
                baseForm.setAboutForm(null);
                menus[0].setSubMenu(null);
            }
        } else if ("msg".equals(fouceFormName)) {
            if (isKeyPress(17)) {
                baseForm.setAboutForm(null);
            }
        } else if ("sure".equals(fouceFormName)) {  // 处理是否退出游戏按键
            if (isKeyPress(17)) {
                if ((Player.getInstance()).hpStates.size() != 0) {
                    (Player.getInstance()).hpStates.removeAllElements();
                }
                ObjManager.getInstance().setCurrentTarget(Player.getInstance());
                pet = null;
                if ("guest".equals(userName)) {
                    closeConnection();
                } else {
                    switch (menus[0].getSubMenu().getCurrentPointer()) {
                        case 0:
                            exitGame((byte) 0);
                            break;
                        case 1:
                            closeConnection();
                            break;
                    }
                }
            } else if (isKeyPress(18)) {
                baseForm.setAboutForm(null);
            }
        } else if ("specTools".equals(fouceFormName)) {
            if (isKeyPress(17)) {
                baseForm.setAboutForm(null);
            }
        } else if ("return".equals(fouceFormName)) {
            if (isKeyPress(17)) {
                baseForm.setAboutForm(null);
            }
        } else if ("announce".equals(fouceFormName)) {
            if (isKeyPress(18)) {
                baseForm.setAboutForm(null);
            } else if (actionInForm(cmd)) {
            }
        } else if ("hangup".equals(fouceFormName)) {  // 处理挂机选项
            if (isKeyPress(14) || isKeyPress(17) || isKeyPress(18)) {
                if(isKeyPress(14) || isKeyPress(17)){
                    Player.getInstance().isHangup = true;
                }else {
                    Player.getInstance().isHangup = false;
                }
                baseForm.setAboutForm(null);
                // 设置游戏中
                setGameState((byte) 0);
                releaseUI();
            }
        }
    }

    public void keyInUIManAttribute() {
        if (baseForm == null) {
            return;
        }
        UIComponent cmd = baseForm.getCommand();
        if (isKeyPress(17) || isKeyPress(14)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                if (UIRadioButton.isFreePointChange()) {
                    baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                    ni.send(134218240);
                } else {
                    baseForm.addAboutForm("message", "没有进行加点操作!", (byte) 1, screenW - 30, 0);
                }
            } else if ("npage".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
                labels[8].setStr("加点");
                labels[9].setStr("下页");
            } else if ("message".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            }
        } else if (isKeyPress(18)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                UIForm subForm = new UIForm(0, 0, screenW, screenH - 1, "npage");
                subForm.setBackGround((byte) 9);
                tables[2].setXY(6, 54);
                subForm.addComponent(tables[2]);
                baseForm.setAboutForm(subForm);
                labels[8].setStr("上页");
                labels[9].setStr("返回");
            } else if ("npage".equals(baseForm.getCurrentFocusForm().getName())) {
                releaseUI();
                if (shortcut_9[Cons.nineShort] || templevel == 1) {
                    setGameState((byte) 0);
                    templevel = 0;
                } else {
                    setGameState((byte) 1);
                    setRightMenuSubState(-1);
                }
                for (int i = 0; i < shortcut_9.length; i++) {
                    shortcut_9[i] = false;
                }
            }
        } else if (actionInForm(cmd) && cmd instanceof UIRadioButton) {
            UIRadioButton rb = (UIRadioButton) baseForm.focusComponent;
            texts[0].setLabel(rb.getFreePoint() + "");
            if (rb.equals(rbs[0])) {
                baseForm.setMessage(Cons.ROLL_MASSAGE[0], false);
            } else if (rb.equals(rbs[1])) {
                baseForm.setMessage(Cons.ROLL_MASSAGE[1], false);
            } else if (rb.equals(rbs[2])) {
                baseForm.setMessage(Cons.ROLL_MASSAGE[2], false);
            } else if (rb.equals(rbs[3])) {
                baseForm.setMessage(Cons.ROLL_MASSAGE[3], false);
            } else if (rb.equals(rbs[4])) {
                baseForm.setMessage(Cons.ROLL_MASSAGE[4], false);
            } else if (rb.equals(rbs[5])) {
                baseForm.setMessage(Cons.ROLL_MASSAGE[5], false);
            }
        }
    }

    public void keyInUIManPackage() {
        if (baseForm == null) {
            return;
        }
        UIComponent cmd = baseForm.getCommand();
        if (isKeyPress(17) || isKeyPress(14)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                if (cmd == dramatisPackage) {
                    if ((mImages[UIMImage.sign1]).isLock3 || dramatisPackage.isLock3) {
                        if (dramatisPackage.getCurrentBigType() == 1) {
                            baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                            compare[2] = 1;
                            compare[3] = dramatisPackage.getCurrentPointer();
                            ni.send(67112192);
                            (mImages[UIMImage.sign1]).isLock3 = false;
                            dramatisPackage.isLock3 = false;
                        }
                    } else if (dramatisPackage.isLock2) {
                        if (dramatisPackage.getCurrentId() == 0) {
                            setMessage(baseForm, "此处没有物品！");
                            return;
                        }
                        if (dramatisPackage.getCurrentBigType() != 1) {
                            setMessage(baseForm, "此物品不能被镶嵌");
                        } else {
                            baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                            isPackage = 1;
                            equipPlace = dramatisPackage.getCurrentPointer();
                            ni.send(67111680);
                        }
                    } else if (dramatisPackage.isLock4) {
                        if (dramatisPackage.getCurrentId() == 0) {
                            setMessage(baseForm, "此处没有物品！");
                            return;
                        }
                        short oneIndex = dramatisPackage.getStuffType()[decompose[0]];
                        if (oneIndex == 325) {
                            if (dramatisPackage.getCurrentBigType() != 1 || dramatisPackage.getCurrentNameLevel() == 0 || dramatisPackage.getNameLevel(decompose[0]) < dramatisPackage.getCurrentNameLevel()) {
                                setMessage(baseForm, "此物品不能被该道具分解");
                            } else {
                                baseForm.addAboutForm("de_sure", "您确实要分解" + dramatisPackage.getCurrentName() + "吗？", (byte) 2, screenW - 30, 0);
                            }
                        } else if (oneIndex == 326) {
                            if (dramatisPackage.getCurrentLittleType() != 327) {
                                setMessage(baseForm, "此物品不能使用钥匙打开");
                            } else {
                                baseForm.addAboutForm("open_box", "您确实要打开" + dramatisPackage.getCurrentName() + "吗？", (byte) 2, screenW - 30, 0);
                            }
                        } else if (oneIndex == 329 || oneIndex == 330) {
                            decompose[1] = dramatisPackage.getCurrentPointer();
                            if (dramatisPackage.getCurrentLittleType() != 328) {
                                setMessage(baseForm, "此物品不能使用钥匙打开");
                            } else {
                                switch (dramatisPackage.getNameLevel(decompose[0])) {
                                    case 0:
                                        baseForm.addAboutForm("open_box", "使用白钥匙只能得到少量金钱，您确实要打开" + dramatisPackage.getCurrentName() + "吗？", (byte) 2, screenW - 30, 0);
                                        break;
                                    case 1:
                                        if (dramatisPackage.getNameLevel(decompose[1]) != 1) {
                                            setMessage(baseForm, "钥匙比物品的级别低,不能打开此物品");
                                            break;
                                        }
                                        baseForm.addAboutForm("open_box", "您是否使用" + dramatisPackage.getStuffName()[decompose[0]] + "打开" + dramatisPackage.getCurrentName() + "？", (byte) 2, screenW - 30, 0);
                                        break;
                                    case 2:
                                        if (dramatisPackage.getNameLevel(decompose[1]) > 2) {
                                            setMessage(baseForm, "钥匙比物品的级别低,不能打开此物品");
                                            break;
                                        }
                                        baseForm.addAboutForm("open_box", "您是否使用" + dramatisPackage.getStuffName()[decompose[0]] + "打开" + dramatisPackage.getCurrentName() + "？", (byte) 2, screenW - 30, 0);
                                        break;
                                    case 3:
                                        baseForm.addAboutForm("open_box", "您是否使用" + dramatisPackage.getStuffName()[decompose[0]] + "打开" + dramatisPackage.getCurrentName() + "？", (byte) 2, screenW - 30, 0);
                                        break;
                                }
                            }
                        }
                    } else if (dramatisPackage.isLock) {
                        if (dramatisPackage.stuffPlace != dramatisPackage.getCurrentPointer()) {
                            baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 30);
                            ni.send(67109632);
                        } else {
                            dramatisPackage.isLock = false;
                        }
                    } else if (dramatisPackage.getSubMenu() == null) {
                        if (dramatisPackage.getCurrentBigType() == 1) {
                            dramatisPackage.setSubMenu(new UIMenu(0, 0, 60, 0, null, Cons.STUFF_MENU_0));
                        } else if (dramatisPackage.getCurrentBigType() == 3) {
                            dramatisPackage.setSubMenu(new UIMenu(0, 0, 60, 0, null, Cons.STUFF_MENU_1));
                            dramatisPackage.getSubMenu().setNoUse((byte) 0);
                        } else if (dramatisPackage.getCurrentBigType() == 2) {
                            dramatisPackage.setSubMenu(new UIMenu(0, 0, 60, 0, null, Cons.STUFF_MENU_2));
                            if (dramatisPackage.getCurrentbindType() == 1 && dramatisPackage.getCurrentLittleType() != 354 && dramatisPackage.getCurrentLittleType() != 353 && dramatisPackage.getCurrentLittleType() != 348 && dramatisPackage.getCurrentLittleType() != 350 && dramatisPackage.getCurrentLittleType() != 351) {
                                dramatisPackage.getSubMenu().setNoUse((byte) 0);
                            }
                        } else if (dramatisPackage.getCurrentBigType() == 4) {
                            dramatisPackage.setSubMenu(new UIMenu(0, 0, 60, 0, null, Cons.STUFF_MENU_4));
                        } else if (dramatisPackage.getCurrentBigType() == 5) {
                            dramatisPackage.setSubMenu(new UIMenu(0, 0, 60, 0, null, Cons.STUFF_MENU_2));
                            dramatisPackage.getSubMenu().setNoUse((byte) 0);
                        } else if (dramatisPackage.getCurrentBigType() == 0) {
                            dramatisPackage.setSubMenu(new UIMenu(0, 0, 60, 0, null, Cons.STUFF_MENU_2));
                            dramatisPackage.getSubMenu().setNoUse((byte) 0);
                            dramatisPackage.getSubMenu().setNoUse((byte) 1);
                            dramatisPackage.getSubMenu().setNoUse((byte) 2);
                            dramatisPackage.getSubMenu().setNoUse((byte) 3);
                        }
                    } else {
                        switch (dramatisPackage.getSubMenu().getCurrentPointer()) {
                            case 3:
                                if (dramatisPackage.getCurrentNumber() == 1) {
                                    stNumber = dramatisPackage.getCurrentNumber();
                                    dramatisPackage.isLock = true;
                                    dramatisPackage.stuffPlace = dramatisPackage.getCurrentPointer();
                                    stuffName.setStr("请选择移动位置");
                                    stuffName.setColor(16777215);
                                    dramatisPackage.setSubMenu((UIMenu) null);
                                    break;
                                }
                                texts[2] = null;
                                texts[2] = new UIText(0, 25, 80, 0, 10, (byte) 2, dramatisPackage.getCurrentNumber() + "");
                                texts[2].setMaxNumber(dramatisPackage.getCurrentNumber());
                                baseForm.setAboutForm((UIForm) null);
                                baseForm.addInputForm("move_item", "请输入移动数量", texts[2], 100);
                                break;
                            case 0:
                                if ("装备".equals(dramatisPackage.getSubMenu().getCurrentItem())) {
                                    if (dramatisPackage.getCurrentbindType() == 2) {
                                        baseForm.addAboutForm("bind", "装备完后，此装备将被绑定，是否继续？", (byte) 2, 140, 0);
                                        break;
                                    }
                                    equip();
                                    break;
                                }
                                if ("使用".equals(dramatisPackage.getSubMenu().getCurrentItem())) {
                                    short oneIndex = dramatisPackage.getCurrentLittleType();
                                    if (oneIndex == 310 || oneIndex == 301) {
                                        dramatisPackage.setSubMenu((UIMenu) null);
                                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                        useStuffPlace = 1;
                                        ni.send(67110144);
                                        break;
                                    }
                                    if (oneIndex == 325) {
                                        dramatisPackage.stuffPlace = decompose[0] = dramatisPackage.getCurrentPointer();
                                        dramatisPackage.setSubMenu((UIMenu) null);
                                        baseForm.addAboutForm("decompose", "请选择要分解的装备", (byte) 2, 150, 0);
                                        break;
                                    }
                                    if (oneIndex == 326) {
                                        findBox((short) 327);
                                        break;
                                    }
                                    if (oneIndex == 329 || oneIndex == 330) {
                                        findBox((short) 328);
                                        break;
                                    }
                                    if (oneIndex == 353) {
                                        dramatisPackage.setSubMenu((UIMenu) null);
                                        transferstatus = true;
                                        useStuffPlace = 1;
                                        requestFriendPlace = 4;
                                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                        ni.send(201327360);
                                        break;
                                    }
                                    if (oneIndex == 354) {
                                        dramatisPackage.setSubMenu((UIMenu) null);
                                        transferstatusQuest = true;
                                        useStuffPlace = 1;
                                        questStoneStatus = 1;
                                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                        ni.send(162530048);
                                        break;
                                    }
                                    if (oneIndex == 303) {
                                        if (dramatisPackage.getCurrentNullPlace() >= 36) {
                                            setMessage(baseForm, "背包已满，不能使用");
                                            break;
                                        }
                                        dramatisPackage.setSubMenu((UIMenu) null);
                                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                        useStuffPlace = 1;
                                        ni.send(67110144);
                                        break;
                                    }
                                    if (oneIndex == 348) {
                                        localChatChannel = 8;
                                        PCChat.isPrivateChannel = false;
                                        chatAlertStr = null;
                                        if ((Cons.channelOption >> 5 & 0x1) == 1) {
                                            chatAlertStr = "注意:喇叭频道已设置为关闭";
                                        }
                                        initChatForm("发送到喇叭频道:");
                                        break;
                                    }
                                    if (dramatisPackage.getCurrentUseState()) {
                                        dramatisPackage.setSubMenu((UIMenu) null);
                                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                        useStuffPlace = 1;
                                        ni.send(67110144);
                                        break;
                                    }
                                    dramatisPackage.setSubMenu((UIMenu) null);
                                    baseForm.setMessage("冷冻时间", false);
                                    break;
                                }
                                if ("镶嵌".equals(dramatisPackage.getSubMenu().getCurrentItem())) {
                                    dramatisPackage.isLock2 = true;
                                    stuffName.setStr("请选择要镶嵌的装备");
                                    dramatisPackage.stuffPlace = dramatisPackage.getCurrentPointer();
                                    dramatisPackage.setSubMenu((UIMenu) null);
                                }
                                break;
                            case 1:
                                dramatisPackage.setSubMenu((UIMenu) null);
                                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                isPackage = 1;
                                lookStuffPlace = (short) dramatisPackage.getCurrentPointer();
                                lookType = 1;
                                ni.send(67110656);
                                break;
                            case 2:
                                dramatisPackage.setSubMenu((UIMenu) null);
                                baseForm.addAboutForm("discard", "您确定丢弃该物品？", (byte) 2, screenW - 30, 0);
                                break;
                            case 4:
                                dramatisPackage.setSubMenu((UIMenu) null);
                                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                ni.send(67111424);
                                break;
                            case 5:
                                dramatisPackage.setSubMenu((UIMenu) null);
                                compare[0] = 1;
                                dramatisPackage.stuffPlace = compare[1] = dramatisPackage.getCurrentPointer();
                                dramatisPackage.isLock3 = true;
                                stuffName.setStr("选择比较装备");
                                break;
                        }
                    }
                } else if (cmd instanceof UIMImage) {
                    if (dramatisPackage.isLock3 || (mImages[UIMImage.sign1]).isLock3) {
                        if (equipStuffId[UIMImage.sign] > 0 && equipStuffId[UIMImage.sign] / 10000 == 0) {
                            baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                            compare[2] = 0;
                            compare[3] = UIMImage.sign;
                            ni.send(67112192);
                            (mImages[UIMImage.sign1]).isLock3 = false;
                            dramatisPackage.isLock3 = false;
                        }
                    } else if (dramatisPackage.isLock2) {
                        equipPlace = ((UIMImage) cmd).index;
                        if (UIGrid.getStuffType(equipStuffType[equipPlace]) == 0) {
                            setMessage(baseForm, "此处没有物品！");
                            return;
                        }
                        if (UIGrid.getStuffType(equipStuffType[equipPlace]) != 1) {
                            setMessage(baseForm, "此物品不能被镶嵌");
                        } else {
                            baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                            isPackage = 0;
                            ni.send(67111680);
                        }
                    } else if (((UIMImage) cmd).isLock) {
                        equipPlace = ((UIMImage) cmd).index;
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        ni.send(67109888);
                    } else if (((UIMImage) cmd).getSubMenu() == null) {
                        equipPlace = ((UIMImage) cmd).index;
                        if (((UIMImage) cmd).getSubMenu() == null) {
                            if (!((UIMImage) cmd).isLock && equipStuffId[equipPlace] > 0) {
                                ((UIMImage) cmd).setSubMenu(new UIMenu(0, 0, 55, 0, null, Cons.STUFF_MENU_3));
                            } else if (((UIMImage) cmd).isLock) {
                                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                ni.send(67109888);
                            }
                        }
                    } else {
                        byte nullPlace;
                        switch (((UIMImage) cmd).getSubMenu().getCurrentPointer()) {
                            case 1:
                                nullPlace = dramatisPackage.getCurrentNullPlace();
                                if (nullPlace < 36) {
                                    ((UIMImage) cmd).setSubMenu((UIMenu) null);
                                    baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                    ni.send(67110400);
                                    break;
                                }
                                baseForm.addAboutForm("remove", "物品栏已满", (byte) 1, screenW - 50, 0);
                                break;
                            case 0:
                                ((UIMImage) cmd).setSubMenu((UIMenu) null);
                                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                lookStuffPlace = (short) (UIMImage.sign = ((UIMImage) cmd).index);
                                isPackage = 0;
                                lookType = 1;
                                ni.send(67110656);
                                break;
                            case 2:
                                baseForm.addAboutForm("discard", "您确定丢弃该物品？", (byte) 2, screenW - 50, 0);
                                ((UIMImage) cmd).setSubMenu((UIMenu) null);
                                break;
                            case 3:
                                mImages[UIMImage.sign].setSubMenu((UIMenu) null);
                                compare[0] = 0;
                                UIMImage.sign1 = compare[1] = UIMImage.sign;
                                (mImages[UIMImage.sign1]).isLock3 = true;
                                stuffName.setStr("选择比较装备");
                                break;
                        }
                    }
                }
            } else if ("discard".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
                cmd = baseForm.getCommand();
                if (cmd == dramatisPackage) {
                    dramatisPackage.setSubMenu((UIMenu) null);
                    isPackage = 1;
                    baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                    ni.send(67110912);
                } else if (cmd instanceof UIMImage) {
                    UIMImage.sign = ((UIMImage) cmd).index;
                    isPackage = 0;
                    baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                    ni.send(67110912);
                }
            } else if ("remove".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("succeed".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
                dramatisPackage.isLock2 = false;
            } else if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
                dramatisPackage.isLock2 = false;
            } else if ("bind".equals(baseForm.getCurrentFocusForm().getName())) {
                equip();
            } else if ("decompose".equals(baseForm.getCurrentFocusForm().getName())) {
                dramatisPackage.setSubMenu((UIMenu) null);
                dramatisPackage.isLock = true;
                dramatisPackage.isLock4 = true;
                baseForm.setAboutForm((UIForm) null);
            } else if ("de_sure".equals(baseForm.getCurrentFocusForm().getName())) {
                decompose[1] = dramatisPackage.getCurrentPointer();
                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                ni.send(67112704);
            } else if ("no_box".equals(baseForm.getCurrentFocusForm().getName())) {
                dramatisPackage.setSubMenu((UIMenu) null);
                dramatisPackage.isLock = false;
                dramatisPackage.isLock4 = false;
                baseForm.setAboutForm((UIForm) null);
            } else if ("open_box".equals(baseForm.getCurrentFocusForm().getName())) {
                decompose[1] = dramatisPackage.getCurrentPointer();
                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                ni.send(67112960);
            } else if ("move_item".equals(baseForm.getCurrentFocusForm().getName())) {
                stNumber = (byte) texts[2].getNumber();
                baseForm.setAboutForm((UIForm) null);
                if (stNumber == 0) {
                    return;
                }
                dramatisPackage.isLock = true;
                dramatisPackage.stuffPlace = dramatisPackage.getCurrentPointer();
                stuffName.setStr("请选择移动位置");
                stuffName.setColor(16777215);
                dramatisPackage.setSubMenu((UIMenu) null);
            }
        } else if (isKeyPress(18)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                if (cmd == dramatisPackage && dramatisPackage.getSubMenu() != null) {
                    dramatisPackage.setSubMenu((UIMenu) null);
                } else if (cmd instanceof UIMImage && ((UIMImage) cmd).getSubMenu() != null) {
                    ((UIMImage) cmd).setSubMenu((UIMenu) null);
                } else if (dramatisPackage.isLock4) {
                    dramatisPackage.isLock4 = false;
                    dramatisPackage.isLock = false;
                    stuffName.setColor(Cons.STUFF_NAME_COLOR[dramatisPackage.getCurrentNameLevel()]);
                    stuffName.setStr(dramatisPackage.getCurrentName());
                } else if (dramatisPackage.isLock) {
                    dramatisPackage.isLock = false;
                    stuffName.setColor(Cons.STUFF_NAME_COLOR[dramatisPackage.getCurrentNameLevel()]);
                    stuffName.setStr(dramatisPackage.getCurrentName());
                    for (int i = 0; i < 8; i++) {
                        (mImages[i]).isLock = false;
                    }
                    mImages[6].setAroundComponent(mImages[2], (byte) 3);
                    mImages[7].setAroundComponent(mImages[5], (byte) 4);
                    baseForm.setComponentFocus(dramatisPackage);
                } else if (dramatisPackage.isLock2) {
                    dramatisPackage.isLock2 = false;
                    stuffName.setColor(Cons.STUFF_NAME_COLOR[dramatisPackage.getCurrentNameLevel()]);
                    stuffName.setStr(dramatisPackage.getCurrentName());
                } else if (dramatisPackage.isLock3 || (mImages[UIMImage.sign1]).isLock3) {
                    dramatisPackage.isLock3 = false;
                    (mImages[UIMImage.sign1]).isLock3 = false;
                    if (cmd instanceof UIMImage) {
                        stuffName.setStr(equipSruffName[UIMImage.sign]);
                    } else if (cmd == dramatisPackage) {
                        stuffName.setStr(dramatisPackage.getCurrentName());
                    }
                } else {
                    releaseUI();
                    if (shortcut_9[Cons.nineShort]) {
                        setGameState((byte) 0);
                    } else {
                        setGameState((byte) 1);
                        setRightMenuSubState(-1);
                    }
                    for (int i = 0; i < shortcut_9.length; i++) {
                        shortcut_9[i] = false;
                    }
                }
            } else if ("detail".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("discard".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("compare".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
                if (compare[2] != 0) {
                    stuffName.setStr(dramatisPackage.getCurrentName());
                } else {
                    stuffName.setStr(equipSruffName[UIMImage.sign]);
                }
            } else if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
                dramatisPackage.isLock2 = false;
            } else if ("bind".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("decompose".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("de_sure".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("open_box".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("move_item".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            }
        } else if (actionInForm(cmd)) {
            if (baseForm.focusComponent == dramatisPackage) {
                stuffName.setColor(Cons.STUFF_NAME_COLOR[dramatisPackage.getCurrentNameLevel()]);
                stuffName.setStr(dramatisPackage.getCurrentName());
            } else if (baseForm.focusComponent instanceof UIMImage) {
                UIMImage.sign = ((UIMImage) baseForm.focusComponent).index;
                if (equipStuffId[UIMImage.sign] > 0) {
                    stuffName.setColor(Cons.STUFF_NAME_COLOR[equipStuffNameLevel[UIMImage.sign]]);
                    stuffName.setStr(equipSruffName[UIMImage.sign]);
                } else {
                    stuffName.setStr("     ");
                }
            }
        } else if ("compare".equals(baseForm.getCurrentFocusForm().getName())) {
            if (isKeyPress(11)) {
                if (tables[0] != null) {
                    tables[0].up();
                }
                if (tables[1] != null) {
                    tables[1].up();
                }
                if (tables[2] != null) {
                    tables[2].up();
                }
            } else if (isKeyPress(13)) {
                if (tables[0] != null) {
                    tables[0].down();
                }
                if (tables[1] != null) {
                    tables[1].down();
                }
                if (tables[2] != null) {
                    tables[2].down();
                }
            }
        }
    }

    public void equip() {
        short tempType = dramatisPackage.getCurrentLittleType();
        if (tempType == 122 || tempType == 321) {
            equipPlace = 6;
            for (int i = 0; i < 8; i++) {
                (mImages[i]).isLock = true;
            }
            stuffName.setStr("请选择装备位置");
            baseForm.setComponentFocus(mImages[6]);
            mImages[6].setAroundComponent(null, (byte) 3);
            mImages[7].setAroundComponent(null, (byte) 4);
            dramatisPackage.setSubMenu((UIMenu) null);
            dramatisPackage.isLock = true;
            dramatisPackage.stuffPlace = dramatisPackage.getCurrentPointer();
            baseForm.setAboutForm((UIForm) null);
        } else {
            if ((tempType >= 101 && tempType <= 105) || (tempType >= 311 && tempType <= 315)) {
                equipPlace = 4;
            } else if (tempType == 112 || tempType == 317) {
                equipPlace = 1;
            } else if (tempType == 113 || tempType == 318) {
                equipPlace = 2;
            } else if (tempType == 111 || tempType == 316) {
                equipPlace = 0;
            } else if (tempType == 114 || tempType == 319) {
                equipPlace = 5;
            } else if (tempType == 121 || tempType == 320) {
                equipPlace = 3;
            }
            dramatisPackage.stuffPlace = dramatisPackage.getCurrentPointer();
            dramatisPackage.setSubMenu((UIMenu) null);
            baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
            dramatisPackage.stuffPlace = dramatisPackage.getCurrentPointer();
            ni.send(67109888);
        }
    }

    public void keyInUIManSkill() {
        if (baseForm == null) {
            return;
        }
        UIComponent cmd = baseForm.getCommand();
        if (baseForm.getCurrentFocusForm() == baseForm) {
            if (isKeyPress(17) || (isKeyPress(14) && tree.getSkillPoints() == 0)) {
                String[] strs = {"确认升级", "技能详情", "技能键位"};
                menus[0] = null;
                menus[0] = new UIMenu(35, 146, 80, 0, null, strs);
                (menus[0]).positionY = screenH - (menus[0]).height - 4;
                UIForm subForm = new UIForm(0, 0, screenW - 1, screenH - 1, "menu");
                subForm.setBackGround((byte) 9);
                subForm.addComponent(menus[0]);
                (menus[0]).positionY = screenH - (menus[0]).height - 4;
                baseForm.setAboutForm(subForm);
                baseForm.setFocusComponentFocus(true);
                if (!tree.isChange()) {
                    menus[0].setNoUse((byte) 0);
                }
                if (isKeyPress(14) && tree.getSkillPoints() == 0) {
                    menus[0].setCurrentpointer((byte) 1);
                }
            }
            if (isKeyPress(14)) {
                tree.addCurrentSkillLevel();
                labels[1].setStr("剩余技能点数 ：" + tree.getSkillPoints());
            } else if (isKeyPress(0)) {
                tree.removeCurrentSkillLevel();
                labels[1].setStr("剩余技能点数 ：" + tree.getSkillPoints());
            } else if (actionInForm(cmd)) {
                String skillName = null;
                switch ((Player.getInstance()).profession) {
                    case 1:
                        skillName = Cons.STR_SWORDMAN_SKILL[tree.getCurrSkillId()];
                        break;
                    case 2:
                        skillName = Cons.STR_TAOIST_SKILL[tree.getCurrSkillId()];
                        break;
                    case 3:
                        skillName = Cons.STR_APOTHECARY_SKILL[tree.getCurrSkillId()];
                        break;
                    case 4:
                        skillName = Cons.STR_ASSASSIN_SKILL[tree.getCurrSkillId()];
                        break;
                    default:
                        skillName = new String("");
                        break;
                }
                labels[0].setStr(skillName + tree.getCurrSkillLevel() + "/" + tree.getMaxSkillLevel() + "级");
            }
            if (isKeyPress(18)) {
                if (tree.isChange()) {
                    baseForm.addAboutForm("exit", "修改没有确认,退出吗?", (byte) 2, 160, 30);
                } else {
                    releaseUI();
                    if (shortcut_9[Cons.nineShort] || templevel == 2) {
                        setGameState((byte) 0);
                        templevel = 0;
                    } else if (templevel == 0) {
                        setGameState((byte) 1);
                        setRightMenuSubState(-1);
                    }
                    for (int i = 0; i < shortcut_9.length; i++) {
                        shortcut_9[i] = false;
                    }
                }
            }
        } else if ("menu".equals(baseForm.getCurrentFocusForm().getName())) {
            if (isKeyPress(14) || isKeyPress(17)) {
                UIForm subForm;
                UIRim tempRim;
                UILabel[] label;
                StringBuffer bs;
                short[][] tempSkill;
                String[] tempStrSkill;
                UIMImage skillImage;
                switch (menus[0].getCurrentPointer()) {
                    case 1:
                        subForm = new UIForm(0, 0, 162, 112, "detail");
                        subForm.setBackGround((byte) 9);
                        tempRim = new UIRim(0, 0, 162, 124, (byte) 6);
                        label = new UILabel[3];
                        bs = new StringBuffer();
                        tempSkill = (short[][]) null;
                        tempStrSkill = null;
                        switch ((Player.getInstance()).profession) {
                            case 1:
                                tempStrSkill = Cons.STR_SWORDMAN_SKILL;
                                tempSkill = Cons.SKILL_SWORDMAN;
                                break;
                            case 2:
                                tempStrSkill = Cons.STR_TAOIST_SKILL;
                                tempSkill = Cons.SKILL_TAOIST;
                                break;
                            case 3:
                                tempStrSkill = Cons.STR_APOTHECARY_SKILL;
                                tempSkill = Cons.SKILL_APOTHECARY;
                                break;
                            case 4:
                                tempStrSkill = Cons.STR_ASSASSIN_SKILL;
                                tempSkill = Cons.SKILL_ASSASSIN;
                                break;
                        }
                        bs.append(tempStrSkill[tree.getCurrSkillId()]);
                        label[0] = new UILabel(0, 32, 68, 0, bs.toString(), 16316576, (byte) 1, (byte) 0);
                        bs = new StringBuffer();
                        if (tempSkill[tree.getCurrSkillId()][3] != 0) {
                            int tmpId = tree.getCurrSkillId();
                            bs.append("消耗MP");
                            bs.append(Player.getInstance().getSkillMP(tmpId));
                        }
                        bs.append(tree.getCurrentSkillCondition());
                        if (bs.length() == 0) {
                            bs.append("不需要条件");
                        }
                        if (bs.toString().startsWith("\n")) {
                            bs.deleteCharAt(0);
                        }
                        label[1] = new UILabel(67, 6, 98, 0, bs.toString(), 10981736, (byte) 0, (byte) 0);
                        label[2] = new UILabel(8, 50, 144, 0, Cons.SKILL_DETAIL[(Player.getInstance()).profession - 1][tree.getCurrSkillId()][0] + Cons.SKILL_DETAIL[(Player.getInstance()).profession - 1][tree.getCurrSkillId()][1], 65280, (byte) 0, (byte) 0);
                        skillImage = new UIMImage(20, 10, 18, 18, mImgUI[25], (byte) 0);
                        skillImage.setCurrentFrame(UISkillTree.SKILL_IMAGE[(Player.getInstance()).profession - 1][tree.getCurrSkillId() - 1]);
                        skillImage.setHaveRim(true);
                        skillImage.setCanFocus(false);
                        subForm.addComponent(tempRim);
                        subForm.addComponent(label[0]);
                        subForm.addComponent(label[1]);
                        subForm.addComponent(label[2]);
                        subForm.addComponent(skillImage);
                        baseForm.setAboutForm((UIForm) null);
                        baseForm.setAboutForm(subForm);
                        break;
                    case 0:
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        ni.send(117441024);
                        break;
                    case 2:
                        skillSub = true;
                        tree.resetLevels();
                        setRightMenuSubState(7);
                        setUISetupState((byte) 1);
                        releaseUI();
                        break;
                }
            } else if (isKeyPress(18)) {
                baseForm.setAboutForm((UIForm) null);
            } else if (actionInForm(cmd)) {
            }
        } else if ("exit".equals(baseForm.getCurrentFocusForm().getName())) {
            if (isKeyPress(14) || isKeyPress(17)) {
                setRightMenuSubState(-1);
                releaseUI();
            } else if (isKeyPress(18)) {
                baseForm.setAboutForm((UIForm) null);
            }
        } else if ("result".equals(baseForm.getCurrentFocusForm().getName())) {
            if (isKeyPress(14) || isKeyPress(17)) {
                baseForm.setAboutForm((UIForm) null);
            }
        } else if ("detail".equals(baseForm.getCurrentFocusForm().getName()) && isKeyPress(18)) {
            baseForm.setAboutForm((UIForm) null);
        }
    }

    public void keyInChat() {
        if (baseForm == null) {
            return;
        }
        UIComponent cmd = baseForm.getCommand();
        if (isKeyPress(18)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                setRightMenuSubState(-1);
                releaseUI();
            } else if ("menu".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("friends".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getSubForm().setAboutForm((UIForm) null);
            } else if ("config".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
                labels[0].setStr("操作");
            } else if ("succeed".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            }
        } else if (!actionInForm(cmd) && cmd != null && cmd == menus[0] && (isKeyPress(14) || isKeyPress(17))) {
            UIForm configForm;
            UIRim rimTitle;
            UILabel lblTitle;
            UIRim rimDown, rimDownInner;
            byte[] channel;
            String[] channelsTemp;
            int i;
            localChatChannel = (byte) (menus[0].getCurrentPointer() + 1);
            switch (menus[0].getCurrentPointer()) {
                case 0:
                    requestFriendPlace = 1;
                    baseForm.getCurrentFocusForm().addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                    ni.send(201327360);
                    break;
                case 5:
                    baseForm.setAboutForm((UIForm) null);
                    configForm = new UIForm(0, 0, screenW - 1, screenH - 1, "config");
                    configForm.setBackGround((byte) 9);
                    rimTitle = new UIRim(0, 12, 160, 17, (byte) 7);
                    lblTitle = new UILabel(0, rimTitle.positionY + 3, 0, 0, "聊天模式设置", 15718815, (byte) 1, (byte) 0);
                    rimDown = new UIRim(rimTitle.positionX, rimTitle.positionY + rimTitle.height, rimTitle.width, 159, (byte) 0);
                    rimDownInner = new UIRim(rimDown.positionX + 5, rimDown.positionY + 5, rimDown.width - 10, rimDown.height - 10, (byte) 0);
                    configForm.addComponentInCenter(rimTitle, (byte) 2);
                    configForm.addComponentInCenter(lblTitle, (byte) 2);
                    configForm.addComponentInCenter(rimDown, (byte) 2);
                    configForm.addComponentInCenter(rimDownInner, (byte) 2);
                    channel = Util.readRecord("CHANNEL");
                    channelsTemp = new String[]{"", "[私聊]", "[世界]", "[氏族]", "[场景]", "[组队]", "[系统]", ""};
                    for (i = 0; i < Cons.STR_CHAT_CHANNEL.length - 3; i++) {
                        rbs[i] = new UIRadioButton(35, rimDownInner.positionY + 10 + i * 28, 0, 0, channelsTemp[i + 1], (byte) 0);
                        rbs[i].addItems("开");
                        rbs[i].addItems("关");
                        if (i != 0) {
                            rbs[i].setAroundComponent(rbs[i - 1], (byte) 1);
                        }
                        if (channel != null) {
                            rbs[i].setChooseItem(channel[i]);
                        }
                        configForm.addComponent(rbs[i]);
                    }
                    baseForm.getCurrentFocusForm().setAboutForm(configForm);
                    labels[0].setStr("确定");
                    break;
            }
        }
    }

    public void keyInPet() {
        UIComponent cmd;
        switch (getUIPetState()) {
            case 2:
                if (baseForm == null) {
                    return;
                }
                cmd = baseForm.getCommand();
                if (isKeyPress(17) || isKeyPress(14)) {
                    if (baseForm.getCurrentFocusForm() == baseForm) {
                        UIForm menuForm = new UIForm(0, 0, screenW - 1, screenH - 1, "menu");
                        menuForm.setBackGround((byte) 9);
                        String[] ss = {"查看", "选择"};
                        menus[0] = new UIMenu(35, 0, 60, 0, null, ss);
                        menuForm.addComponent(menus[0]);
                        (menus[0]).positionY = screenH - (menus[0]).height - 4;
                        baseForm.setAboutForm(menuForm);
                        baseForm.focusComponent.setFocus(true);
                        break;
                    }
                    if ("menu".equals(baseForm.getCurrentFocusForm().getName())) {
                        pet.curPetSkill.curItemId = pet.curPetSkill.itemIDs[tables[0].getCurrentPointer()];
                        baseForm.setAboutForm((UIForm) null);
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        switch (menus[0].getCurrentPointer()) {
                            case 0:
                                ni.send(50334720);
                                break;
                            case 1:
                                table0curPointer = tables[0].getCurrentPointer();
                                packageSend = 6;
                                ni.send(67109120);
                                break;
                        }
                    }
                    break;
                }
                if (isKeyPress(18)) {
                    if (baseForm.getCurrentFocusForm() == baseForm) {
                        table0curPointer = 0;
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        if (pet.material != 1) {
                            ni.send(50334976);
                        } else {
                            ni.send(150994944);
                        }
                        waitCnt = 0;
                        break;
                    }
                    if ("menu".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if ("detail".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                    }
                    break;
                }
                if (actionInForm(cmd));
                break;
            case 5:
                if (isKeyPress(17) || isKeyPress(14)) {
                    table0curPointer = 0;
                    setGameState((byte) 0);
                    releaseUI();
                    break;
                }
                if (isKeyPress(18)) {
                    baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                    packageSend = 6;
                    ni.send(67109120);
                }
                break;
            case 6:
                if (baseForm == null) {
                    return;
                }
                cmd = baseForm.getCommand();
                if (isKeyPress(14) || isKeyPress(17)) {
                    if (baseForm.getCurrentFocusForm() == baseForm) {
                        if (menus[0].isMenuNull()) {
                            setMessage(baseForm, "您还没有学习技能。");
                            break;
                        }
                        if (menus[0].getSubMenu() == null) {
                            UIForm menuForm = new UIForm(0, 0, screenW - 1, screenH - 1, "menu");
                            menuForm.setBackGround((byte) 9);
                            String[] ss = {"选择", "遗忘"};
                            menus[2] = new UIMenu(35, 0, 60, 0, null, ss);
                            menuForm.addComponent(menus[2]);
                            (menus[2]).positionY = screenH - (menus[2]).height - 4;
                            baseForm.setAboutForm(menuForm);
                            baseForm.focusComponent.setFocus(true);
                            if (Cons.COMPOSITE_SKILL[1].equals(menus[0].getCurrentItem()) || Cons.COMPOSITE_SKILL[2].equals(menus[0].getCurrentItem()) || Cons.COMPOSITE_SKILL[8].equals(menus[0].getCurrentItem())) {
                                menus[2].setNoUse((byte) 0);
                            }
                            break;
                        }
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        composeListTitle = "合成列表";
                        pet.setSkillIndexAndLevelIndex(menus[0].getCurrentPointer(), menus[1].getCurrentPointer() + 1);
                        ni.send(50332928);
                        break;
                    }
                    if ("menu".equals(baseForm.getCurrentFocusForm().getName())) {
                        switch (menus[2].getCurrentPointer()) {
                            case 0:
                                if (menus[0].getSubMenu() == null) {
                                    if (menus[0].getCurrentItem().startsWith(Cons.PET_SKILL[1]) || menus[0].getCurrentItem().startsWith(Cons.PET_SKILL[2])) {
                                        return;
                                    }
                                    String num = "一二三四五六七八九十";
                                    pet.curPetSkill = pet.skills[menus[0].getCurrentPointer()];
                                    if (pet.curPetSkill.skillID == 0) {
                                        return;
                                    }
                                    String[] levels = new String[pet.curPetSkill.skillLevel];
                                    for (int i = 0, kk = levels.length; i < kk; i++) {
                                        levels[i] = num.charAt(i) + "级技能";
                                    }
                                    menus[1] = new UIMenu((menus[0]).positionX + (menus[0]).width - 20, (menus[0]).positionY - 20 + 54, 100, 40, null, levels);
                                    menus[0].setSubMenu(menus[1]);
                                    baseForm.setAboutForm((UIForm) null);
                                }
                                break;
                            case 1:
                                texts[9] = new UIText(0, 0, 92, 0, 6, (byte) 0, "");
                                baseForm.addInputForm("confirm", "请输入del遗忘技能", texts[9], 100);
                                baseForm.setFocusComponentFocus(true);
                                pet.forgetId = menus[0].getCurrentPointer();
                                if (!(menus[0]).isCanUse[0]) {
                                    pet.forgetId = 0;
                                }
                                break;
                        }
                        break;
                    }
                    if ("confirm".equals(baseForm.getCurrentFocusForm().getName())) {
                        if ("del".equals(texts[9].getLabel())) {
                            baseForm.setAboutForm((UIForm) null);
                            menus[0].setNoUse(menus[0].getCurrentPointer());
                            menus[0].checkMenu();
                            if ((menus[0]).strs != null) {
                                labels[1].setStr("熟练度：" + (pet.skills[menus[0].getCurrentPointer()]).curSkillExp + "/" + (pet.skills[menus[0].getCurrentPointer()]).maxSkillExp);
                                labels[4].setStr("等级：" + (pet.skills[menus[0].getCurrentPointer()]).skillLevel);
                            } else {
                                labels[1].setStr("熟练度：0/0");
                                labels[4].setStr("等级：0");
                            }
                            ni.send(50334464);
                            break;
                        }
                        baseForm.getSubForm().addAboutForm("deldel", "输入不正确", (byte) 1, 150, 0);
                        break;
                    }
                    if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if ("deldel".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.getSubForm().setAboutForm((UIForm) null);
                    }
                    break;
                }
                if (isKeyPress(18)) {
                    if (baseForm.getCurrentFocusForm() == baseForm) {
                        if (menus[0].getSubMenu() != null) {
                            menus[0].setSubMenu((UIMenu) null);
                            break;
                        }
                        setGameState((byte) 1);
                        setRightMenuSubState(-1);
                        releaseUI();
                        break;
                    }
                    if ("menu".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if ("confirm".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                    }
                    break;
                }
                if (actionInForm(cmd)) {
                    try {
                        if ((menus[0]).strs != null) {
                            labels[1].setStr("熟练度：" + (pet.skills[menus[0].getCurrentPointer()]).curSkillExp + "/" + (pet.skills[menus[0].getCurrentPointer()]).maxSkillExp);
                            labels[4].setStr("等级：" + (pet.skills[menus[0].getCurrentPointer()]).skillLevel);
                            break;
                        }
                        labels[1].setStr("熟练度：0/0");
                        labels[4].setStr("等级：0");
                    } catch (Exception exception) {
                    }
                }
                break;
            case 7:
                if (baseForm == null) {
                    return;
                }
                cmd = baseForm.getCommand();
                if (isKeyPress(14)) {
                    if (pet.quantity / 10 > 0 && cmd == dramatisPackage && dramatisPackage.getCurrentBigType() == 1) {
                        mImages[0].setImage(mImgStuff);
                        mImages[0].setCurrentFrame((byte) (dramatisPackage.getCurrentImageId() - 1));
                        dramatisPackage.weaponPlace = dramatisPackage.getCurrentPointer();
                    }
                    break;
                }
                if (isKeyPress(17)) {
                    if (baseForm.getCurrentFocusForm() == baseForm) {
                        if (pet.quantity / 10 > 0 && (mImages[0]).mImg == null) {
                            setMessage(baseForm, "请先选择合适的装备");
                            return;
                        }
                        if (Pets.needvires > Pets.curvires) {
                            setMessage(baseForm, "当前活力不够，无法合成");
                            break;
                        }
                        if (pet.quantity % 10 == 0) {
                            setMessage(baseForm, "素材不足，无法合成");
                            break;
                        }
                        if (dramatisPackage.getCurrentNullPlace() == -1) {
                            setMessage(baseForm, "背包已满，没有空位");
                            break;
                        }
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        ni.send(50333440);
                        break;
                    }
                    if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if ("errorMsg".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                        pet.quantity = 1;
                    }
                    break;
                }
                if (isKeyPress(18)) {
                    if (baseForm.getCurrentFocusForm() == baseForm) {
                        setUIPetState((byte) 2);
                        releaseUI();
                        break;
                    }
                    if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                    }
                    break;
                }
                if (actionInForm(cmd) && cmd == dramatisPackage) {
                    labels[4].setStr(dramatisPackage.getCurrentName());
                    labels[4].setColor(Cons.STUFF_NAME_COLOR[dramatisPackage.getCurrentNameLevel()]);
                }
                break;
            case 8:
                if (baseForm == null) {
                    return;
                }
                cmd = baseForm.getCommand();
                if (isKeyPress(17) || isKeyPress(14)) {
                    if (baseForm.getCurrentFocusForm() == baseForm) {
                        if (texts[1].getNumber() == 0) {
                            setMessage(baseForm, "请输入合成数量");
                            break;
                        }
                        if (texts[1].getNumber() * Pets.needvires > Pets.curvires) {
                            setMessage(baseForm, "当前活力不足，无法合成");
                            break;
                        }
                        if (texts[1].getNumber() > pet.quantity) {
                            setMessage(baseForm, "素材不足，无法合成");
                            break;
                        }
                        if (dramatisPackage.getCurrentNullPlace() == -1) {
                            setMessage(baseForm, "背包已满，没有空位");
                            break;
                        }
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        pet.quantity = (short) (byte) texts[1].getNumber();
                        ni.send(50333440);
                        break;
                    }
                    if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if ("errorMsg".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                        pet.quantity = 1;
                    }
                    break;
                }
                if (isKeyPress(18)) {
                    if (baseForm.getCurrentFocusForm() == baseForm) {
                        setUIPetState((byte) 2);
                        releaseUI();
                        break;
                    }
                    if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                    }
                    break;
                }
                if (actionInForm(cmd)) {
                    if (cmd == dramatisPackage) {
                        labels[4].setStr(dramatisPackage.getCurrentName());
                        labels[4].setColor(Cons.STUFF_NAME_COLOR[dramatisPackage.getCurrentNameLevel()]);
                        break;
                    }
                    labels[4].setStr("  ");
                }
                break;
        }
    }

    public void keyInTask() {
        if (baseForm == null) {
            return;
        }
        UIComponent cmd = baseForm.getCommand();
        if (isKeyPress(17) || isKeyPress(14)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                if (!tables[0].isNull()) {
                    String[] strs = {"查看", "放弃"};
                    if (transferstatusQuest) {
                        strs = new String[]{"查看", "放弃", "传送至接任务地图", "传送至做任务地图", "传送至交任务地图"};
                    }
                    menus[0] = null;
                    menus[0] = new UIMenu(35, 164, 60, 0, null, strs);
                    UIForm subForm = new UIForm(0, 0, screenW - 1, screenH - 1, "menu");
                    subForm.setBackGround((byte) 9);
                    subForm.addComponent(menus[0]);
                    (menus[0]).positionY = screenH - (menus[0]).height - 4;
                    baseForm.setAboutForm(subForm);
                    baseForm.setFocusComponentFocus(true);
                } else {
                    questNoPlace = true;
                    setMessage(baseForm, "您还没有任务");
                }
            } else if ("sure".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                ni.send(162529792);
            } else if ("detail0".startsWith(baseForm.getCurrentFocusForm().getName())) {
                if (cmd instanceof UIMImage) {
                    taskSelectedId = ((UIMImage) cmd).index;
                    if (taskStuffId[taskSelectedId] != 0) {
                        baseForm.getSubForm().addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        selectedId = taskStuffId[taskSelectedId];
                        ni.send(67111936);
                    }
                }
            } else if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                if (transferstatusQuest && !questNoPlace) {
                    questStoneStatus = 0;
                    baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                    ni.send(67110144);
                }
                questNoPlace = false;
                baseForm.setAboutForm((UIForm) null);
            } else if ("menu".equals(baseForm.getCurrentFocusForm().getName())) {
                switch (menus[0].getCurrentPointer()) {
                    case 0:
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        ni.send(162530304);
                        break;
                    case 1: {
                        baseForm.addAboutForm("sure", "确实要放弃吗？", (byte) 2, 140, 0);
                        break;
                    }
                    case 2:
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        questTargetID = taskId[tables[0].getCurrentPointer()];
                        questTargetPlace = 1;
                        questStoneStatus = 1;
                        ni.send(67110144);
                        break;
                    case 3:
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        questTargetID = taskId[tables[0].getCurrentPointer()];
                        questTargetPlace = 3;
                        questStoneStatus = 1;
                        ni.send(67110144);
                        break;
                    case 4:
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        questTargetID = taskId[tables[0].getCurrentPointer()];
                        questTargetPlace = 2;
                        questStoneStatus = 1;
                        ni.send(67110144);
                        break;
                }
            }
        } else if (isKeyPress(18)) {
            if (transferstatusQuest && baseForm.getCurrentFocusForm() == baseForm) {
                setGameState((byte) 1);
                mc.setRightMenuSubState(2);
                releaseUI();
                dramatisPackage.setXY(0, 113);
                transferstatusQuest = false;
                questStoneStatus = -1;
            } else if (baseForm.getCurrentFocusForm() == baseForm) {
                releaseUI();
                if (shortcut_9[Cons.nineShort]) {
                    setGameState((byte) 0);
                } else {
                    setGameState((byte) 1);
                    setRightMenuSubState(-1);
                }
                for (int i = 0; i < shortcut_9.length; i++) {
                    shortcut_9[i] = false;
                }
            } else if ("sure".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("menu".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("detail0".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("detail".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getSubForm().setAboutForm((UIForm) null);
            } else if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            }
        } else if (actionInForm(cmd)) {
        }
    }

    public void keyInSociety() {
        UIComponent cmd;
        switch (getUIFriendState()) {
            case 0:
                if (baseForm == null) {
                    return;
                }
                cmd = baseForm.getCommand();
                if (isKeyPress(17) || isKeyPress(14)) {
                    if (baseForm.getCurrentFocusForm() == baseForm) {
                        if (!tables[0].isNull()) {
                            friendAddType = 2;
                            friendSelectWhich = tables[0].getCurrentPointer();
                            baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                            ni.send(201326848);
                            break;
                        }
                        setMessage(baseForm, "名单为空");
                        break;
                    }
                    if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                    }
                    break;
                }
                if (isKeyPress(18)) {
                    if (baseForm.getCurrentFocusForm() == baseForm) {
                        releaseUI();
                        setRightMenuSubState(-1);
                        break;
                    }
                    if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                    }
                    break;
                }
                if (actionInForm(cmd));
                break;
            case 2:
                if (baseForm == null) {
                    return;
                }
                cmd = baseForm.getCommand();
                if (isKeyPress(17) || isKeyPress(14)) {
                    if (baseForm.getCurrentFocusForm() == baseForm) {
                        if (!tables[0].isNull()) {
                            String[] strs = {"查看详情", "组队作战", "删除好友", "屏蔽此人"};
                            if (transferstatus) {
                                strs = new String[]{"查看详情", "组队作战", "删除好友", "屏蔽此人", "好友传送"};
                            }
                            UIForm menuForm = new UIForm(0, 0, screenW - 1, screenH - 1, "menu");
                            menuForm.setBackGround((byte) 9);
                            menus[0] = new UIMenu(35, 0, 80, 0, null, strs);
                            menuForm.addComponent(menus[0]);
                            menus[0].setXY((menus[0]).positionX, screenH - (menus[0]).height - 4);
                            baseForm.setAboutForm(menuForm);
                            baseForm.focusComponent.setFocus(true);
                            break;
                        }
                        setMessage(baseForm, "名单为空");
                        break;
                    }
                    if ("confirm".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        switch (menus[0].getCurrentPointer()) {
                            case 1:
                                baseForm.setAboutForm((UIForm) null);
                                break;
                            case 2:
                                deleteFriendType = 0;
                                friendSelectWhich = tables[0].getCurrentPointer();
                                ni.send(201327872);
                                break;
                            case 3:
                                friendSelectWhich = tables[0].getCurrentPointer();
                                ni.send(201327104);
                                break;
                        }
                        break;
                    }
                    if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if ("menu".equals(baseForm.getCurrentFocusForm().getName())) {
                        UIForm subForm;
                        UIRim rimFrame;
                        UIRim rimTitle;
                        UILabel lblTitle;
                        UIRim rimDown;
                        UIRim rimDownInner;
                        UILabel lblCancel;
                        UILabel lblName;
                        UILabel lblLevel;
                        UILabel lblProfession;
                        UILabel lblMap;
                        UILabel lblOnLine;
                        UILabel lbllocal;
                        UILabel lblMete;
                        String message;
                        boolean flag;
                        int i;
                        switch (menus[0].getCurrentPointer()) {
                            case 0:
                                subForm = new UIForm(0, 0, screenW, screenH, "detail");
                                subForm.setStyle((byte) 0);
                                rimFrame = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
                                rimTitle = new UIRim(0, 12, 160, 17, (byte) 7);
                                lblTitle = new UILabel(0, 15, 0, 0, "好友信息", 15718815, (byte) 1, (byte) 0);
                                rimDown = new UIRim(0, 29, 160, 159, (byte) 0);
                                rimDownInner = new UIRim(5, 34, 150, 149, (byte) 0);
                                lblCancel = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 1, (byte) 0);
                                lblName = new UILabel(24, 40, 0, 0, "名称：" + friendList[tables[0].getCurrentPointer()][0], 15718815, (byte) 0, (byte) 0);
                                lblLevel = new UILabel(24, 60, 0, 0, "等级：" + friendList[tables[0].getCurrentPointer()][3], 15718815, (byte) 0, (byte) 0);
                                lblProfession = new UILabel(24, 80, 0, 0, "职业：" + friendList[tables[0].getCurrentPointer()][2], 15718815, (byte) 0, (byte) 0);
                                lblMap = new UILabel(24, 100, 0, 0, "地图：" + friendList[tables[0].getCurrentPointer()][4], 15718815, (byte) 0, (byte) 0);
                                lblOnLine = new UILabel(24, 120, 0, 0, "氏族：" + friendList[tables[0].getCurrentPointer()][5], 15718815, (byte) 0, (byte) 0);
                                lbllocal = new UILabel(24, 140, 0, 0, "省市：" + friendList[tables[0].getCurrentPointer()][6], 15718815, (byte) 0, (byte) 0);
                                lblMete = new UILabel(24, 160, 0, 0, "转生：" + friendList[tables[0].getCurrentPointer()][8], 15718815, (byte) 0, (byte) 0);
                                subForm.addComponent(rimFrame);
                                subForm.addComponentInCenter(rimTitle, (byte) 2);
                                subForm.addComponentInCenter(lblTitle, (byte) 2);
                                subForm.addComponentInCenter(rimDown, (byte) 2);
                                subForm.addComponentInCenter(rimDownInner, (byte) 2);
                                subForm.addComponentInCenter(lblCancel, (byte) 6);
                                subForm.addComponent(lblName);
                                subForm.addComponent(lblLevel);
                                subForm.addComponent(lblProfession);
                                subForm.addComponent(lblMap);
                                subForm.addComponent(lblOnLine);
                                subForm.addComponent(lbllocal);
                                subForm.addComponent(lblMete);
                                baseForm.setAboutForm((UIForm) null);
                                baseForm.setAboutForm(subForm);
                                break;
                            case 1:
                                message = null;
                                if (teamJob != 0 && teamJob != 1) {
                                    message = "你不是队长不能邀请！";
                                }
                                flag = false;
                                for (i = 0; i < teamMates.size(); i++) {
                                    if (friendListID[tables[0].getCurrentPointer()] == ((GameObj) teamMates.elementAt(i)).objID) {
                                        flag = true;
                                    }
                                }
                                if (teamMates.size() >= 3) {
                                    message = "队伍人数已满!";
                                } else if (flag) {
                                    message = "已是队伍成员!";
                                } else if (friendList[tables[0].getCurrentPointer()][1].equals("离线")) {
                                    message = "好友已离线!";
                                }
                                if (message == null) {
                                    message = "已发出邀请!";
                                    teamLeaderOperat = 1;
                                    PCFriend.addTeamMateKind = 1;
                                    TeamTargetName = friendList[tables[0].getCurrentPointer()][0];
                                    ni.send(100663808);
                                }
                                baseForm.getSubForm().setAboutForm((UIForm) null);
                                baseForm.getSubForm().addAboutForm("confirm", message, (byte) 1, 140, 50);
                                break;
                            case 2:
                                baseForm.getSubForm().setAboutForm((UIForm) null);
                                baseForm.getSubForm().addAboutForm("confirm", "确实要删除吗?", (byte) 2, 140, 50);
                                break;
                            case 3:
                                baseForm.getSubForm().setAboutForm((UIForm) null);
                                baseForm.getSubForm().addAboutForm("confirm", "确实要加入黑名单吗?", (byte) 2, 140, 50);
                                break;
                            case 4:
                                if (transferstatus) {
                                    friendTransferTarget = friendListID[tables[0].getCurrentPointer()];
                                    baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                    ni.send(67110144);
                                }
                                break;
                        }
                    }
                    break;
                }
                if (isKeyPress(18)) {
                    if (transferstatus && baseForm.getCurrentFocusForm() == baseForm) {
                        setGameState((byte) 1);
                        setRightMenuSubState(2);
                        releaseUI();
                        dramatisPackage.setXY(0, 113);
                        transferstatus = false;
                        break;
                    }
                    if (baseForm.getCurrentFocusForm() == baseForm) {
                        releaseUI();
                        setRightMenuSubState(-1);
                        break;
                    }
                    if ("menu".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if ("confirm".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.getSubForm().setAboutForm((UIForm) null);
                        break;
                    }
                    if ("detail".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if ("overline".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if ("sendgplus".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                    }
                    break;
                }
                if (actionInForm(cmd) && !"menu".equals(baseForm.getCurrentFocusForm().getName())) {
                    if (isKeyPress(13)) {
                        if ((tables[0]).isDown) {
                            baseForm.setComponentFocus(texts[2]);
                        }
                        break;
                    }
                    if (isKeyPress(11)) {
                        baseForm.setComponentFocus(tables[0]);
                    }
                }
                break;
            case 3:
                if (baseForm == null) {
                    return;
                }
                cmd = baseForm.getCommand();
                if (isKeyPress(17) || isKeyPress(14)) {
                    if (baseForm.getCurrentFocusForm() == baseForm) {
                        if (!tables[0].isNull()) {
                            String[] strs = {"删除此人", "加为好友"};
                            UIForm menuForm = new UIForm(0, 0, screenW - 1, screenH - 1, "menu");
                            menuForm.setBackGround((byte) 9);
                            menus[0] = new UIMenu(35, 204, 70, 0, null, strs);
                            menuForm.addComponent(menus[0]);
                            (menus[0]).positionY = screenH - (menus[0]).height - 4;
                            baseForm.setAboutForm(menuForm);
                            baseForm.focusComponent.setFocus(true);
                            break;
                        }
                        setMessage(baseForm, "名单为空");
                        break;
                    }
                    if ("confirm".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        switch (menus[0].getCurrentPointer()) {
                            case 0:
                                deleteFriendType = 1;
                                friendSelectWhich = tables[0].getCurrentPointer();
                                ni.send(201327872);
                                break;
                            case 1:
                                friendAddType = 0;
                                friendSelectWhich = tables[0].getCurrentPointer();
                                ni.send(201326848);
                                break;
                        }
                        break;
                    }
                    if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if ("menu".equals(baseForm.getCurrentFocusForm().getName())) {
                        String tempStr = null;
                        switch (menus[0].getCurrentPointer()) {
                            case 0:
                                tempStr = "确实要删除吗?";
                                break;
                            case 1:
                                tempStr = "确实要加入好友吗?";
                                break;
                        }
                        baseForm.getSubForm().setAboutForm((UIForm) null);
                        baseForm.getSubForm().addAboutForm("confirm", tempStr, (byte) 2, 140, 50);
                    }
                    break;
                }
                if (isKeyPress(18)) {
                    if (baseForm.getCurrentFocusForm() == baseForm) {
                        releaseUI();
                        setRightMenuSubState(-1);
                        break;
                    }
                    if ("menu".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if ("confirm".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.getSubForm().setAboutForm((UIForm) null);
                        break;
                    }
                    if ("deletefail".equals(baseForm.getSubFormName())) {
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                    }
                    break;
                }
                if (actionInForm(cmd));
                break;
            case 4:
                keyInUnion();
                break;
        }
    }
    public static boolean isLookMailnew = false;
    public String unionName;

    public void keyInMail() {
        UIComponent cmd;
        switch (getNPCMailState()) {
            case 0:
                if (baseForm == null) {
                    return;
                }
                cmd = baseForm.getCommand();
                if (baseForm.getCurrentFocusForm() == baseForm && isKeyPress(20)) {
                    if (tables[0] != null && mailList != null && mailList.length != 0) {
                        int index = tables[0].getCurrentPointer();
                        if ("1".equals(mailList[index][2])) {
                            baseForm.addAboutForm("confirm", "有附件确定删除吗?", (byte) 2, 160, 40);
                            break;
                        }
                        baseForm.addAboutForm("confirm", "确定删除吗?", (byte) 2, 160, 40);
                        break;
                    }
                    baseForm.addAboutForm("return", "没有任何邮件!", (byte) 1, 120, 50);
                    break;
                }
                if (isKeyPress(17) || isKeyPress(14)) {
                    if (baseForm.getCurrentFocusForm() == baseForm) {
                        if (tables[0] != null && mailList != null && mailList.length != 0) {
                            String[] strs = {"查看邮件", "删除单个", "删除全部"};
                            int h = strs.length * 22;
                            menus[0] = null;
                            menus[0] = new UIMenu(35, 208 - h, 40, 40, null, strs);
                            UIForm subForm = new UIForm(0, 0, screenW - 1, screenH - 1, "menu");
                            subForm.setBackGround((byte) 9);
                            subForm.addComponent(menus[0]);
                            baseForm.setAboutForm(subForm);
                            break;
                        }
                        baseForm.addAboutForm("return", "没有任何邮件!", (byte) 1, 120, 50);
                        break;
                    }
                    if (baseForm.getCurrentFocusForm().getName().equals("return")) {
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if (baseForm.getCurrentFocusForm().getName().equals("information")) {
                        baseForm.getSubForm().setAboutForm((UIForm) null);
                        break;
                    }
                    if (baseForm.getCurrentFocusForm().getName().equals("nothing")) {
                        baseForm.getSubForm().setAboutForm((UIForm) null);
                        break;
                    }
                    if (baseForm.getCurrentFocusForm().getName().equals("confirm")) {
                        baseForm.setAboutForm((UIForm) null);
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        mailSelectWhich = tables[0].getCurrentPointer();
                        ni.send(184550912);
                        break;
                    }
                    if (baseForm.getCurrentFocusForm().getName().equals("confirmall")) {
                        baseForm.setAboutForm((UIForm) null);
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        ni.send(184551680);
                        break;
                    }
                    if (baseForm.getCurrentFocusForm().getName().equals("view")) {
                        String[] strs = {"接收物品", "接收金钱", "回复邮件", "查看物品"};
                        menus[0] = null;
                        int h = strs.length * 17;
                        menus[0] = new UIMenu(35, 200 - h, 75, h, null, strs);
                        UIForm subForm = new UIForm(0, 0, screenW - 1, screenH - 1, "viewmenu");
                        subForm.setBackGround((byte) 9);
                        subForm.addComponent(menus[0]);
                        baseForm.getCurrentFocusForm().setAboutForm(subForm);
                        if (PCMail.fromSystem) {
                            menus[0].setNoUse((byte) 2);
                        }
                        if (selectedId == 0) {
                            menus[0].setNoUse((byte) 3);
                        }
                        if (isNoItem) {
                            menus[0].setNoUse((byte) 3);
                        }
                        break;
                    }
                    if (baseForm.getCurrentFocusForm().getName().equals("menu")) {
                        int index;
                        switch (menus[0].getCurrentPointer()) {
                            case 0:
                                mailSelectWhich = tables[0].getCurrentPointer();
                                baseForm.setAboutForm((UIForm) null);
                                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                ni.send(184550144);
                                break;
                            case 1:
                                index = tables[0].getCurrentPointer();
                                if ("1".equals(mailList[index][2])) {
                                    baseForm.addAboutForm("confirm", "有附件确定删除吗?", (byte) 2, 160, 40);
                                    break;
                                }
                                baseForm.addAboutForm("confirm", "确定删除吗?", (byte) 2, 160, 40);
                                break;
                            case 2:
                                baseForm.addAboutForm("confirmall", "确定删除全部邮件吗?", (byte) 2, 120, 40);
                                break;
                        }
                        break;
                    }
                    if (baseForm.getCurrentFocusForm().getName().equals("viewmenu")) {
                        switch (menus[0].getCurrentPointer()) {
                            case 0:
                                if (mImages[0] == null || mImages[0].getNumber() == 0) {
                                    baseForm.getSubForm().setAboutForm((UIForm) null);
                                    setMessage(baseForm.getCurrentFocusForm(), "没有任何附件！");
                                    break;
                                }
                                baseForm.getSubForm().addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                ni.send(184550656);
                                break;
                            case 1:
                                if (texts[0] == null || texts[0].getNumber() == 0) {
                                    baseForm.getSubForm().setAboutForm((UIForm) null);
                                    baseForm.getCurrentFocusForm().addAboutForm("nothing", "没有汇款！", (byte) 1, 120, 40);
                                    break;
                                }
                                baseForm.getSubForm().addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                ni.send(184550400);
                                break;
                            case 2:
                                if ((mImages[0] == null || !(mImages[0]).isVisible) && texts[0].getNumber() == 0) {
                                    mailList[mailSelectWhich][2] = "0";
                                    tables[0].setAccessoryState(mailSelectWhich, false);
                                }
                                PCMail.responseFlag = true;
                                PCMail.initMailState();
                                ni.send(184551168);
                                baseForm.getCurrentFocusForm().addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                break;
                            case 3:
                                baseForm.getCurrentFocusForm().addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                ni.send(184551936);
                                break;
                        }
                        break;
                    }
                    if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.getSubForm().setAboutForm((UIForm) null);
                    }
                    break;
                }
                if (isKeyPress(18)) {
                    if (baseForm.getCurrentFocusForm() != baseForm) {
                        if (baseForm.getCurrentFocusForm().getName().equals("menu")) {
                            baseForm.setAboutForm((UIForm) null);
                            break;
                        }
                        if (baseForm.getCurrentFocusForm().getName().equals("confirm")) {
                            baseForm.setAboutForm((UIForm) null);
                            break;
                        }
                        if (baseForm.getCurrentFocusForm().getName().equals("confirmall")) {
                            baseForm.setAboutForm((UIForm) null);
                            break;
                        }
                        if ("view".equals(baseForm.getCurrentFocusForm().getName())) {
                            isNoItem = false;
                            boolean flag = false;
                            for (int i = 0; i < mailList.length; i++) {
                                if (mailList[i][1].equals("0")) {
                                    flag = true;
                                    break;
                                }
                            }
                            if (flag) {
                                isHaveNewMail = true;
                            } else if (!isLookMailnew) {
                                isHaveNewMail = false;
                            }
                            if ((mImages[0] == null || !(mImages[0]).isVisible) && texts[0].getNumber() == 0) {
                                tables[0].setAccessoryState(mailSelectWhich, false);
                            }
                            tables[0].setReadingState(mailSelectWhich, true);
                            baseForm.setAboutForm((UIForm) null);
                            waitCnt = 0;
                            setNPCSubState((byte) 11);
                            setNPCMailState((byte) 0);
                            break;
                        }
                        if ("viewmenu".equals(baseForm.getCurrentFocusForm().getName())) {
                            baseForm.getSubForm().setAboutForm((UIForm) null);
                            break;
                        }
                        if ("detail".equals(baseForm.getCurrentFocusForm().getName())) {
                            baseForm.getSubForm().setAboutForm((UIForm) null);
                        }
                        break;
                    }
                    if (baseForm.getCurrentFocusForm() == baseForm) {
                        setNPCSubState((byte) 0);
                        baseForm = null;
                        break;
                    }
                    if ("view".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                    }
                    break;
                }
                if (actionInForm(cmd));
                break;
            case 1:
                if (baseForm == null) {
                    return;
                }
                cmd = baseForm.getCommand();
                if (isKeyPress(14) || isKeyPress(17)) {
                    if (baseForm.getCurrentFocusForm() == baseForm) {
                        String[] strs = {"选收件人", "撰写邮件", "添加附件", "删除附件", "添加汇款", "删除汇款", "发送邮件"};
                        int h = strs.length * 17;
                        menus[0] = new UIMenu(35, 200 - h, 75, h, null, strs);
                        UIForm subForm = new UIForm(0, 0, screenW - 1, screenH - 1, "menu");
                        subForm.setBackGround((byte) 9);
                        subForm.addComponent(menus[0]);
                        baseForm.setAboutForm(subForm);
                        if (mailSendTo < 0 || mailName == null || mailName.trim().equals("")) {
                            menus[0].setNoUse((byte) (strs.length - 1));
                        }
                        break;
                    }
                    if (baseForm.getCurrentFocusForm().getName() == "nottrade") {
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if (baseForm.getCurrentFocusForm().getName() == "return") {
                        PCMail.initMailState();
                        baseForm.setAboutForm((UIForm) null);
                        releaseUI();
                        if (PCMail.responseFlag) {
                            setNPCSubState((byte) 11);
                            mc.setNPCMailState((byte) 0);
                            PCMail.responseFlag = false;
                            break;
                        }
                        setNPCSubState((byte) 0);
                        break;
                    }
                    if (baseForm.getCurrentFocusForm().getName() == "noFrends") {
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if (baseForm.getCurrentFocusForm().getName() == "confirm") {
                        int sendm = Integer.parseInt(texts[0].getLabel());
                        int post = Integer.parseInt(labels[2].getCurrentString().trim());
                        boolean flag = true;
                        String message = null;
                        if (sendm + post > (Player.getInstance()).money) {
                            message = "金钱不足!";
                            flag = false;
                        } else if (mailSendTo < 0) {
                            message = "请输入收件人!";
                            flag = false;
                        } else if (labels[0].getCurrentString().trim().equals("gm")) {
                            message = "收件人姓名不合法!";
                            flag = false;
                        } else if (mailName == null || mailName.trim().equals("")) {
                            message = "请输入标题!";
                            flag = false;
                        } else if ((Player.getInstance()).name.equals(labels[0].getCurrentString().trim())) {
                            message = "不能给自己发信!";
                            flag = false;
                        }
                        if (flag) {
                            mailAddStuffNum = mImages[0].getNumber();
                            gm_mail_on = false;
                            ni.send(184549632);
                            baseForm.setAboutForm((UIForm) null);
                            baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                            break;
                        }
                        baseForm.addAboutForm("check", message, (byte) 1, 130, 45);
                        break;
                    }
                    if (baseForm.getCurrentFocusForm().getName() == "check") {
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if (baseForm.getCurrentFocusForm().getName() == "itemnuminput") {
                        byte number;
                        try {
                            number = Byte.parseByte(texts[2].getLabel());
                        } catch (NumberFormatException e) {
                            number = 0;
                        }
                        if (number > 0) {
                            int color = Cons.STUFF_NAME_COLOR[dramatisPackage.getCurrentNameLevel()];
                            labels[4].setColor(color);
                            mImages[0].setCurrentFrame((byte) (dramatisPackage.getCurrentImageId() - 1));
                            mImages[0].setVisible(true);
                            labels[2].setStr(postageAd + "");
                            labels[4].setStr(dramatisPackage.getCurrentName());
                            mImages[0].setNumber(number);
                            mailAddStuff = dramatisPackage.getCurrentPointer();
                            mailAddStuffId = dramatisPackage.getCurrentId();
                        }
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if (baseForm.getCurrentFocusForm().getName().equals("menu")) {
                        String[] strFriend;
                        int i;
                        UIForm subForm;
                        int j;
                        UIRim rim0;
                        UILabel title;
                        UIForm subForm2;
                        UIRim rim2;
                        String name;
                        UIForm subForm3;
                        UILabel money;
                        UILabel lblOk;
                        UILabel lblCancel;
                        switch (menus[0].getCurrentPointer()) {
                            case 0:
                                strFriend = null;
                                if (friendList == null) {
                                    strFriend = new String[1];
                                } else {
                                    strFriend = new String[friendList.length + 1];
                                }
                                strFriend[0] = "[输入姓名]";
                                for (i = 1; i < strFriend.length; i++) {
                                    strFriend[i] = friendList[i - 1][0];
                                }
                                subForm = new UIForm(0, 0, screenW, screenH, "friends");
                                subForm.setBackGround((byte) 9);
                                tables[0] = new UITable(0, 0, 110, 0, strFriend.length, 1, (strFriend.length > 5) ? 5 : strFriend.length, (byte) 0, (byte) 3);
                                for (j = 0; j < strFriend.length; j++) {
                                    tables[0].setItem(strFriend[j], j, 65280);
                                }
                                tables[0].setAutoHeight(true);
                                subForm.addComponentInCenter(tables[0], (byte) 4);
                                rim0 = new UIRim(0, (tables[0]).positionY - 24, 110, 24, (byte) 0);
                                title = new UILabel(0, rim0.positionY + 2, 0, 0, "好友列表", 15587742, (byte) 1, (byte) 0);
                                subForm.addComponentInCenter(rim0, (byte) 2);
                                subForm.addComponentInCenter(title, (byte) 2);
                                baseForm.getCurrentFocusForm().setAboutForm(subForm);
                                rim0.positionY = (tables[0]).positionY - 24;
                                title.positionY = rim0.positionY + 2;
                                break;
                            case 1:
                                clearAdvanceUIItem();
                                if (commonOk == null) {
                                    commonOk = new Command("确定", 4, 2);
                                    commonBack = new Command("返回", 2, 2);
                                }
                                if (commonTextField == null) {
                                    commonTextField = new TextField("请输入邮件主题：", mailName, 6, 0);
                                    commontf = new TextField("请输入邮件内容：", mailDetail, 90, 0);
                                }
                                if (commonForm == null) {
                                    commonForm = new Form("nameForm");
                                    commonForm.setTitle("编写邮件");
                                    commonForm.addCommand(commonOk);
                                    commonForm.addCommand(commonBack);
                                    commonForm.append((Item) commonTextField);
                                    commonForm.append((Item) commontf);
                                    commonForm.setCommandListener(this);
                                }
                                aMidlet.display.setCurrent((Displayable) commonForm);
                                break;
                            case 2:
                                subForm2 = new UIForm(0, 0, screenW, screenH, "items");
                                subForm2.setBackGround((byte) 9);
                                dramatisPackage.setCurrentPointer((byte) 0);
                                dramatisPackage.positionY = 95;
                                rim2 = new UIRim(0, dramatisPackage.positionY - 22, 166, 22, (byte) 7);
                                name = dramatisPackage.getCurrentName();
                                labels[3] = new UILabel(0, rim2.positionY + 3, rim2.width, 0, name, 65280, (byte) 1, (byte) 0);
                                labels[3].setColor(Cons.STUFF_NAME_COLOR[dramatisPackage.getCurrentNameLevel()]);
                                subForm2.addComponentInCenter(rim2, (byte) 2);
                                subForm2.addComponentInCenter(labels[3], (byte) 2);
                                subForm2.addComponentInCenter(dramatisPackage, (byte) 2);
                                baseForm.getSubForm().setAboutForm(subForm2);
                                break;
                            case 3:
                                mImages[0].setVisible(false);
                                mImages[0].setNumber((byte) 0);
                                baseForm.setAboutForm((UIForm) null);
                                labels[2].setStr(postage + "");
                                labels[4].setStr("");
                                mailAddStuff = -1;
                                break;
                            case 4:
                                subForm3 = new UIForm(0, 0, 120, 75, "money");
                                money = new UILabel(0, 5, 0, 0, "拥有金钱:" + (Player.getInstance()).money, 15587742, (byte) 1, (byte) 0);
                                texts[1] = new UIText(0, 28, 70, 0, 8, (byte) 3, "");
                                texts[1].setMinNumber(0L);
                                texts[1].setMaxNumber((Player.getInstance()).money);
                                lblOk = new UILabel(0, 5, 0, 0, "确定", 15587742, (byte) 1, (byte) 0);
                                lblCancel = new UILabel(0, 5, 0, 0, "取消", 15587742, (byte) 1, (byte) 0);
                                subForm3.addComponentInCenter(money, (byte) 2);
                                subForm3.addComponentInCenter(texts[1], (byte) 2);
                                subForm3.addComponentInCenter(lblOk, (byte) 5);
                                subForm3.addComponentInCenter(lblCancel, (byte) 6);
                                lblOk.positionY -= 10;
                                lblCancel.positionY -= 10;
                                baseForm.getSubForm().setAboutForm(subForm3);
                                break;
                            case 5:
                                baseForm.setAboutForm((UIForm) null);
                                texts[0].setLabel("0");
                                mailAddMoney = 0;
                                break;
                            case 6:
                                baseForm.getSubForm().addAboutForm("confirm", "确定发送吗?", (byte) 2, 100, 30);
                                break;
                        }
                        break;
                    }
                    if (baseForm.getCurrentFocusForm().getName().equals("friends")) {
                        if (isKeyPress(14) || isKeyPress(17)) {
                            if (tables[0].getCurrentPointer() != 0) {
                                labels[0].setStr(tables[0].getCurentItem());
                                labels[0].setColor(10981736);
                                mailSendTo = friendListID[tables[0].getCurrentPointer() - 1];
                                baseForm.setAboutForm((UIForm) null);
                                break;
                            }
                            clearAdvanceUIItem();
                            if (commonOk == null) {
                                commonOk = new Command("确定", 4, 2);
                                commonBack = new Command("返回", 2, 2);
                            }
                            if (commontf == null) {
                                commontf = new TextField("请输入收件人姓名：", "", 6, 0);
                            }
                            if (commonTextField != null) {
                                commonTextField = null;
                            }
                            if (commonForm == null) {
                                commonForm = new Form("inputName");
                                commonForm.setTitle("收件人");
                                commonForm.addCommand(commonOk);
                                commonForm.addCommand(commonBack);
                                commonForm.append((Item) commontf);
                                commonForm.setCommandListener(this);
                            }
                            aMidlet.display.setCurrent((Displayable) commonForm);
                            baseForm.setAboutForm((UIForm) null);
                        }
                        break;
                    }
                    if (baseForm.getCurrentFocusForm().getName().equals("items")) {
                        if (dramatisPackage.getCurrentId() != 0) {
                            if (!dramatisPackage.getCurrentCanTrade() || !dramatisPackage.isCanToOther()) {
                                baseForm.addAboutForm("nottrade", "此物品已绑定，不能邮寄", (byte) 1, 100, 30);
                                return;
                            }
                            if (dramatisPackage.getCurrentNumber() <= 1) {
                                mImages[0].setCurrentFrame((byte) (dramatisPackage.getCurrentImageId() - 1));
                                mImages[0].setVisible(true);
                                labels[2].setStr(postageAd + "");
                                labels[4].setStr(dramatisPackage.getCurrentName());
                                labels[4].setColor(Cons.STUFF_NAME_COLOR[dramatisPackage.getCurrentNameLevel()]);
                                mImages[0].setNumber((byte) 1);
                                mailAddStuff = dramatisPackage.getCurrentPointer();
                                mailAddStuffId = dramatisPackage.getCurrentId();
                                baseForm.setAboutForm((UIForm) null);
                                break;
                            }
                            UIForm formNumInput = new UIForm(0, 0, 130, 75, "itemnuminput");
                            UILabel money = new UILabel(0, 5, 0, 0, "最大邮寄物品数量:" + dramatisPackage.getCurrentNumber(), 15587742, (byte) 1, (byte) 0);
                            texts[2] = new UIText(0, 28, 70, 0, 8, (byte) 2, "");
                            texts[2].setMinNumber(0L);
                            texts[2].setMaxNumber(dramatisPackage.getCurrentNumber());
                            UILabel lblOk = new UILabel(0, 5, 0, 0, "确定", 15587742, (byte) 1, (byte) 0);
                            UILabel lblCancel = new UILabel(0, 5, 0, 0, "取消", 15587742, (byte) 1, (byte) 0);
                            formNumInput.addComponentInCenter(money, (byte) 2);
                            formNumInput.addComponentInCenter(texts[2], (byte) 2);
                            formNumInput.addComponentInCenter(lblOk, (byte) 5);
                            formNumInput.addComponentInCenter(lblCancel, (byte) 6);
                            lblOk.positionY -= 10;
                            lblCancel.positionY -= 10;
                            baseForm.getCurrentFocusForm().setAboutForm(formNumInput);
                        }
                        break;
                    }
                    if (baseForm.getCurrentFocusForm().getName().equals("money")) {
                        texts[0].setLabel(texts[1].getLabel());
                        mailAddMoney = Integer.parseInt(texts[1].getLabel());
                        baseForm.setAboutForm((UIForm) null);
                    }
                    break;
                }
                if (isKeyPress(18)) {
                    if (baseForm.getCurrentFocusForm() != baseForm) {
                        if (baseForm.getCurrentFocusForm().getName() == "menu") {
                            baseForm.setAboutForm((UIForm) null);
                            break;
                        }
                        baseForm.getSubForm().setAboutForm((UIForm) null);
                        break;
                    }
                    commontf = null;
                    mailDetail = null;
                    if (PCMail.responseFlag) {
                        setNPCSubState((byte) 11);
                        mc.setNPCMailState((byte) 0);
                        PCMail.responseFlag = false;
                    } else {
                        setNPCSubState((byte) 0);
                        dramatisPackage.positionY = 113;
                    }
                    releaseUI();
                    break;
                }
                if (actionInForm(cmd) && cmd == dramatisPackage) {
                    String name = dramatisPackage.getCurrentName();
                    labels[3].setStr(name);
                    labels[3].setColor(Cons.STUFF_NAME_COLOR[dramatisPackage.getCurrentNameLevel()]);
                }
                break;
        }
    }

    public void keyInUnion() {
        if (baseForm == null) {
            return;
        }
        UIComponent cmd = baseForm.getCommand();
        if ("notice".equals(baseForm.getCurrentFocusForm().getName()) || "notice_Left_Menu".equals(baseForm.getCurrentFocusForm().getName()) || "notice_Add_No".equals(baseForm.getCurrentFocusForm().getName()) || "notice_Del_No".equals(baseForm.getCurrentFocusForm().getName()) || "noticeInfo".equals(baseForm.getCurrentFocusForm().getName()) || "clanMemTopNo".equals(baseForm.getCurrentFocusForm().getName())) {
            ClanWar.getInstance().tick();
            return;
        }
        if (isKeyPress(17) || (isKeyPress(14) && !(cmd instanceof UIMenu))) {
            if ("use_badge".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
                startWait(baseForm.getCurrentFocusForm());
                ni.send(251666432);
            } else if (isDistributionBadge && "have_badge".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getCurrentFocusForm().addAboutForm("use_badge", "您是否要将1个" + PCNPC.propsName[tables[1].getCurrentPointer()] + "分配给" + clanMemberName[clanTargerMemPointer], (byte) 2, 140, 50);
            }
            if ("clanMemTop".equals(baseForm.getCurrentFocusForm().getName())) {
                startWait(baseForm.getCurrentFocusForm());
                NetInterface.getInstance().send(302252032);
            } else if ("creditfirm".equals(baseForm.getCurrentFocusForm().getName())) {
                String[] strs = {"查看成员信息", "查看氏族徽章"};
                menus[1] = new UIMenu(35, 0, 80, 0, null, strs);
                UIForm subForm = new UIForm(0, 0, screenW - 1, screenH - 1, "creditfirmMenu");
                subForm.setBackGround((byte) 9);
                subForm.addComponent(menus[1]);
                menus[1].setXY((menus[1]).positionX, screenH - (menus[1]).height - 4);
                baseForm.getCurrentFocusForm().setAboutForm(subForm);
            } else if ("viewMem".equals(baseForm.getCurrentFocusForm().getName())) {
                if (tables[0] != null) {
                    String[] strs = {"提升职位", "降低职位", "开除此人", "族长传位", "加为好友", "分配徽章", "本页群发短信"};
                    menus[1] = new UIMenu(35, 0, 80, 0, null, strs);
                    UIForm subForm = new UIForm(0, 0, screenW - 1, screenH - 1, "menuMem");
                    subForm.setBackGround((byte) 9);
                    subForm.addComponent(menus[1]);
                    menus[1].setXY((menus[1]).positionX, screenH - (menus[1]).height - 4);
                    baseForm.getSubForm().setAboutForm(subForm);
                    if (clanManegeLevel != 1) {
                        menus[1].setNoUse((byte) 5);
                    }
                    if (clanManegeLevel >= 2) {
                        menus[1].setNoUse((byte) 0);
                        menus[1].setNoUse((byte) 1);
                        menus[1].setNoUse((byte) 2);
                        menus[1].setNoUse((byte) 3);
                    }
                }
            } else if ("noMem".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getCurrentFocusForm().setAboutForm((UIForm) null);
                releaseUI();
                setNPCSubState((byte) 0);
            } else if ("addFriend".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getSubForm().setAboutForm((UIForm) null);
            } else if ("confirm".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getSubForm().addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                ni.send(251660544);
            } else if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
            } else if ("optRst".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getSubForm().addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                ni.send(251660288);
            } else if ("cannotclan".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("viewConfirm".equals(baseForm.getCurrentFocusForm().getName())) {
                if (tables[0] != null) {
                    String[] strs = {"批准申请", "拒绝申请"};
                    int h = strs.length * 17;
                    menus[2] = new UIMenu(35, 190 - h, 80, h, null, strs);
                    UIForm subForm = new UIForm(0, 0, screenW - 1, screenH - 1, "menuConfirm");
                    subForm.setBackGround((byte) 9);
                    subForm.addComponent(menus[2]);
                    baseForm.getSubForm().setAboutForm(subForm);
                }
            } else if ("noConfirm".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getSubForm().setAboutForm((UIForm) null);
            } else if ("conConfirm".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getSubForm().addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                int titleStr = 0;
                switch (ClanWar.m_getJoinMemList) {
                    case 0:
                        titleStr = 251661312;
                        break;
                    case 1:
                        titleStr = 251662080;
                        break;
                    case 2:
                        titleStr = 251691008;
                        break;
                }
                ni.send(titleStr);
            } else if ("pullConfirm".equals(baseForm.getCurrentFocusForm().getName())) {
                ni.send(251660800);
                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
            } else if ("inviteRst".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("rstConfirm".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getSubForm().addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                int titleStr = 0;
                switch (ClanWar.m_getJoinMemList) {
                    case 0:
                        titleStr = 251661056;
                        break;
                    case 1:
                        titleStr = 251661824;
                        break;
                    case 2:
                        titleStr = 251686912;
                        break;
                }
                ni.send(titleStr);
            } else if ("remsg".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
            } else if ("sendgplus".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
                baseForm.getCurrentFocusForm().addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                ni.send(201328384);
            }
        }
        if (isKeyPress(18)) {
            if ("use_badge".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
            } else if ("have_badge".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
            } else if (baseForm.getCurrentFocusForm() == baseForm) {
                releaseUI();
                setRightMenuSubState(-1);
            } else if ("memInfo".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getSubForm().getSubForm().setAboutForm((UIForm) null);
            } else if ("clanMemTop".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getSubForm().setAboutForm((UIForm) null);
            } else if ("creditfirm".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("creditfirmMenu".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
            } else if (baseForm.getCurrentFocusForm() == baseForm) {
                releaseUI();
                setRightMenuSubState(-1);
            } else if ("viewMem".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("menuMem".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getSubForm().setAboutForm((UIForm) null);
            } else if ("menuConfirm".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getSubForm().setAboutForm((UIForm) null);
            } else if ("confirm".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getSubForm().setAboutForm((UIForm) null);
            } else if ("viewConfirm".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("conConfirm".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getSubForm().setAboutForm((UIForm) null);
            } else if ("pullConfirm".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if (!"".equals(baseForm.getCurrentFocusForm().getName())) {
                if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                    baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
                } else if ("sendgplus".equals(baseForm.getCurrentFocusForm().getName())) {
                    baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
                }
            }
        } else if (isKeyPress(10)) {
            if ("clanMemTop".equals(baseForm.getCurrentFocusForm().getName())) {
                if ((ClanWar.getInstance()).m_curPage > 0) {
                    (ClanWar.getInstance()).m_curPage = (byte) ((ClanWar.getInstance()).m_curPage - 1);
                    startWait(baseForm.getCurrentFocusForm());
                    NetInterface.getInstance().send(302055424);
                }
            } else if ("viewMem".equals(baseForm.getCurrentFocusForm().getName())) {
                if (PCNPC.clanCurrentPage > 1) {
                    PCNPC.clanCurrentPage--;
                    ni.send(251660288);
                    baseForm.getCurrentFocusForm().addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                }
            } else if ("viewConfirm".equals(baseForm.getCurrentFocusForm().getName()) && PCNPC.clanCurrentPage > 1) {
                PCNPC.clanCurrentPage--;
                int titleStr = 0;
                switch (ClanWar.m_getJoinMemList) {
                    case 0:
                        titleStr = 251661056;
                        break;
                    case 1:
                        titleStr = 251661824;
                        break;
                    case 2:
                        titleStr = 251686912;
                        break;
                }
                ni.send(titleStr);
                baseForm.getCurrentFocusForm().addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
            }
        } else if (isKeyPress(12)) {
            if ("clanMemTop".equals(baseForm.getCurrentFocusForm().getName())) {
                if ((ClanWar.getInstance()).m_curPage < (ClanWar.getInstance()).m_totalPages - 1) {
                    (ClanWar.getInstance()).m_curPage = (byte) ((ClanWar.getInstance()).m_curPage + 1);
                    startWait(baseForm.getCurrentFocusForm());
                    NetInterface.getInstance().send(302055424);
                }
            } else if ("viewMem".equals(baseForm.getCurrentFocusForm().getName())) {
                if (PCNPC.clanCurrentPage < PCNPC.clanTotalPage) {
                    PCNPC.clanCurrentPage++;
                    ni.send(251660288);
                    baseForm.getCurrentFocusForm().addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                }
            } else if ("viewConfirm".equals(baseForm.getCurrentFocusForm().getName()) && PCNPC.clanCurrentPage < PCNPC.clanTotalPage) {
                PCNPC.clanCurrentPage++;
                int titleStr = 0;
                switch (ClanWar.m_getJoinMemList) {
                    case 0:
                        titleStr = 251661056;
                        break;
                    case 1:
                        titleStr = 251661824;
                        break;
                    case 2:
                        titleStr = 251686912;
                        break;
                }
                ni.send(titleStr);
                baseForm.getCurrentFocusForm().addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
            }
        } else if (!actionInForm(cmd)) {
            if (cmd == menus[0] && menus[0] != null) {
                if (isKeyPress(14) || isKeyPress(17)) {
                    switch (menus[0].getCurrentPointer()) {
                        case 0:
                            PCNPC.clanCurrentPage = 1;
                            ni.send(251660288);
                            baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                            break;
                        case 1:
                            PCNPC.clanCurrentPage = 1;
                            ni.send(251661056);
                            ClanWar.m_getJoinMemList = 0;
                            startWait(baseForm);
                            break;
                        case 2:
                            PCNPC.clanCurrentPage = 1;
                            ni.send(251661824);
                            ClanWar.m_getJoinMemList = 1;
                            startWait(baseForm);
                            break;
                        case 3:
                            PCNPC.clanCurrentPage = 1;
                            ni.send(251686912);
                            ClanWar.m_getJoinMemList = 2;
                            startWait(baseForm);
                            break;
                        case 4:
                            ni.send(302186496);
                            startWait(baseForm);
                            break;
                        case 5:
                            ClanWar.getInstance().setM_curPage((byte) 0);
                            ni.send(302317568);
                            startWait(baseForm);
                            break;
                    }
                }
            } else if (cmd == menus[1] && menus[1] != null && tables[0] != null) {
                if (isKeyPress(14) || isKeyPress(17)) {
                    int index = menus[1].getCurrentPointer();
                    if ("creditfirmMenu".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
                        switch (index) {
                            case 0:
                                (ClanWar.getInstance()).m_curPage = 0;
                                startWait(baseForm.getCurrentFocusForm());
                                ni.send(302055424);
                                break;
                            case 1:
                                isDistributionBadge = false;
                                startWait(baseForm.getCurrentFocusForm());
                                ni.send(251662336);
                                break;
                        }
                        return;
                    }
                    int pointer = tables[0].getCurrentPointer();
                    byte tgtLevel = clanMemberLevel[tables[0].getCurrentPointer()];
                    clanTargerMemPointer = tables[0].getCurrentPointer();
                    switch (menus[1].getCurrentPointer()) {
                        case 0:
                            if (clanManegeLevel < clanMemberLevel[pointer] - 1) {
                                clanTargerMemLevel = (byte) (clanMemberLevel[pointer] - 1);
                                baseForm.getCurrentFocusForm().addAboutForm("confirm", "确认提升为" + Cons.UNION_NAMES[tgtLevel - 1] + "?", (byte) 2, 150, 50);
                                break;
                            }
                            setMessage(baseForm.getCurrentFocusForm(), "无法提升此人!");
                            break;
                        case 1:
                            if (clanManegeLevel < clanMemberLevel[pointer] && clanMemberLevel[pointer] + 1 <= 3) {
                                clanTargerMemLevel = (byte) (clanMemberLevel[pointer] + 1);
                                baseForm.getCurrentFocusForm().addAboutForm("confirm", "确认降低为" + Cons.UNION_NAMES[tgtLevel + 1] + "?", (byte) 2, 150, 50);
                                break;
                            }
                            setMessage(baseForm.getCurrentFocusForm(), "无法降低此人!");
                            break;
                        case 2:
                            if (clanManegeLevel < clanMemberLevel[pointer]) {
                                baseForm.getCurrentFocusForm().addAboutForm("confirm", "确认开除此人?", (byte) 2, 150, 50);
                                break;
                            }
                            setMessage(baseForm.getCurrentFocusForm(), "无法开除此人!");
                            break;
                        case 3:
                            if (tgtLevel == 2) {
                                baseForm.getCurrentFocusForm().addAboutForm("confirm", "确认传位吗?", (byte) 2, 150, 50);
                                break;
                            }
                            setMessage(baseForm.getCurrentFocusForm(), "无法传位此人!");
                            break;
                        case 4:
                            baseForm.getSubForm().addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                            PCFriend.friendName = clanMemberName[clanTargerMemPointer];
                            friendAddType = 4;
                            ni.send(201326848);
                            break;
                        case 5:
                            isDistributionBadge = true;
                            ni.send(251662336);
                            startWait(baseForm.getCurrentFocusForm());
                            break;
                        case 6:
                            ni.send(251660288);
                            isSelectList = 4;
                            friendListSelectForm(clanMemberName);
                            break;
                    }
                }
            } else if (cmd == menus[2] && menus[2] != null && tables[0] != null && (isKeyPress(14) || isKeyPress(17))) {
                clanTargerMemPointer = tables[0].getCurrentPointer();
                String text = "";
                switch (menus[2].getCurrentPointer()) {
                    case 0:
                        switch (ClanWar.m_getJoinMemList) {
                            case 0:
                                text = "批准此人加入氏族吗?";
                                break;
                            case 1:
                                text = "批准此人退出氏族吗？";
                                break;
                            case 2:
                                text = "批准此人进入农场吗？";
                                break;
                        }
                        baseForm.getCurrentFocusForm().addAboutForm("conConfirm", text, (byte) 2, 150, 50);
                        break;
                    case 1:
                        switch (ClanWar.m_getJoinMemList) {
                            case 0:
                                text = "拒绝此人加入氏族吗?";
                                break;
                            case 1:
                                text = "拒绝此人加入氏族吗?";
                                break;
                            case 2:
                                text = "拒绝此人进入农场吗？";
                                break;
                        }
                        baseForm.getCurrentFocusForm().addAboutForm("conConfirm", text, (byte) 2, 150, 50);
                        break;
                }
            }
        }
    }

    public void keyInSetup() {
        if (baseForm == null) {
            return;
        }
        UIComponent cmd = baseForm.getCommand();
        switch (getUISetupState()) {
            case 0:
                if (isKeyPress(17)) {
                    byte[] data = new byte[2];
                    data[0] = rbs[0].getChooseItemIndex();
                    data[1] = rbs[1].getChooseItemIndex();
                    Cons.use2468 = (data[0] == 1);
                    Cons.use5 = (data[1] == 1);
                    Util.saveRecord(data, "directionOp");
                    setRightMenuSubState(-1);
                    releaseUI();
                    break;
                }
                if (isKeyPress(18)) {
                    setRightMenuSubState(-1);
                    releaseUI();
                    break;
                }
                if (actionInForm(cmd));
                break;
            case 3:
                if (isKeyPress(17)) {
                    Cons.channelOption = (short) (Cons.channelOption >> 5);
                    Cons.channelOption = (short) (Cons.channelOption << 5);
                    for (int i = 0; i < 5; i++) {
                        Cons.channelOption = (short) (Cons.channelOption | rbs[i].getChooseItemIndex() << i);
                    }
                    byte audio = rbs[5].getChooseItemIndex();
                    if (!Cons.audioOpen) {
                        sound.stopAllSound();
                        Sound.isSoundOpen = false;
                    } else {
                        Sound.isSoundOpen = true;
                        switch (Map.currentMapID) {
                            case 1:
                            case 4:
                            case 6:
                            case 10:
                                if (Util.getRandom(10) % 2 == 0) {
                                    sound.initSoleSound("/hum1.mid", "audio/midi");
                                    sound.playSoleSound(1, true);
                                    break;
                                }
                                sound.initSoleSound("/hum2.mid", "audio/midi");
                                sound.playSoleSound(1, true);
                                break;
                            case 16:
                            case 18:
                            case 22:
                            case 93:
                                if (Util.getRandom(10) % 2 == 0) {
                                    sound.initSoleSound("/evil1.mid", "audio/midi");
                                    sound.playSoleSound(1, true);
                                    break;
                                }
                                sound.initSoleSound("/evil2.mid", "audio/midi");
                                sound.playSoleSound(1, true);
                                break;
                        }
                    }
                    Cons.nineShort = rbs[6].getChooseItemIndex();
                    Cons.autoDistribute = (rbs[7].getChooseItemIndex() == 1);
                    byte[] data = new byte[4];
                    data[0] = 0;
                    data[1] = audio;
                    data[2] = Cons.nineShort;
                    data[3] = rbs[7].getChooseItemIndex();
                    Util.saveRecord(data, "channelOp");
                    setRightMenuSubState(-1);
                    releaseUI();
                    ni.send(83886592);
                    break;
                }
                if (isKeyPress(18)) {
                    setRightMenuSubState(-1);
                    releaseUI();
                    break;
                }
                if (actionInForm(cmd));
                break;
            case 2:
                if (isKeyPress(17)) {
                    byte[] temp = new byte[Cons.STR_CHAT_OPERATION.length];
                    for (int i = 0; i < temp.length; i++) {
                        temp[i] = rbs[i].getChooseItemIndex();
                    }
                    Cons.showOtherPlayer = (rbs[0] != null && rbs[0].getChooseItemIndex() == 0);
                    Cons.showName = (rbs[1] != null && rbs[1].getChooseItemIndex() == 0);
                    Cons.newPlayerHelp = (rbs[2] != null && rbs[2].getChooseItemIndex() == 0);
                    Cons.showEffect = false;
                    Cons.channelOption = (short) (Cons.channelOption & 0x1F);
                    Cons.channelOption = (short) (Cons.channelOption | rbs[3].getChooseItemIndex() << 5);
                    Util.saveRecord(temp, (Player.getInstance()).name + "op");
                    setGameState((byte) 1);
                    setRightMenuSubState(-1);
                    releaseUI();
                    ni.send(83886592);
                    break;
                }
                if (isKeyPress(18)) {
                    setGameState((byte) 1);
                    setRightMenuSubState(-1);
                    releaseUI();
                    break;
                }
                if (actionInForm(cmd));
                break;
            case 1:
                if (baseForm == null) {
                    return;
                }
                cmd = baseForm.getCommand();
                if (isKeyPress(17)) {
                    if (baseForm.getCurrentFocusForm() == baseForm) {
                        baseForm.addAboutForm("confirm", "要清空所有快捷键吗?", (byte) 2, 160, 30);
                        break;
                    }
                    if ("confirm".equals(baseForm.getCurrentFocusForm().getName())) {
                        for (int i = 0; i < 9; i++) {
                            Player.defineKey(i, -1);
                        }
                        baseForm.setAboutForm((UIForm) null);
                    }
                    break;
                }
                if (isKeyPress(18)) {
                    if (baseForm.getCurrentFocusForm() == baseForm) {
                        Util.saveRecord(Player.userDefinedSkills, (Player.getInstance()).objID + "sc");
                        if (skillSub) {
                            setRightMenuSubState(20);
                            skillSub = false;
                            releaseUI();
                            break;
                        }
                        setGameState((byte) 1);
                        setRightMenuSubState(-1);
                        releaseUI();
                        break;
                    }
                    if ("confirm".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                    }
                    break;
                }
                if (actionInForm(cmd)) {
                    if (cmd == tree) {
                        if ((Player.getInstance()).profession > 0 && tree.getCurrSkillId() < 15 && tree.getCurrSkillId() > 0) {
                            StringBuffer bs = new StringBuffer();
                            String skillName = "";
                            switch ((Player.getInstance()).profession) {
                                case 1:
                                    skillName = Cons.STR_SWORDMAN_SKILL[tree.getCurrSkillId()];
                                    bs.append(skillName);
                                    if (tree.getCurrSkillLevel() == 0) {
                                        bs.append(" 未学 ");
                                        break;
                                    }
                                    bs.append(" " + tree.getCurrSkillLevel() + "级");
                                    break;
                                case 2:
                                    skillName = Cons.STR_TAOIST_SKILL[tree.getCurrSkillId()];
                                    bs.append(skillName);
                                    if (tree.getCurrSkillLevel() == 0) {
                                        bs.append(" 未学 ");
                                        break;
                                    }
                                    bs.append(" " + tree.getCurrSkillLevel() + "级");
                                    break;
                                case 3:
                                    skillName = Cons.STR_APOTHECARY_SKILL[tree.getCurrSkillId()];
                                    bs.append(skillName);
                                    if (tree.getCurrSkillLevel() == 0) {
                                        bs.append(" 未学 ");
                                        break;
                                    }
                                    bs.append(" " + tree.getCurrSkillLevel() + "级");
                                    break;
                                case 4:
                                    skillName = Cons.STR_ASSASSIN_SKILL[tree.getCurrSkillId()];
                                    bs.append(skillName);
                                    if (tree.getCurrSkillLevel() == 0) {
                                        bs.append(" 未学 ");
                                        break;
                                    }
                                    bs.append(" " + tree.getCurrSkillLevel() + "级");
                                    break;
                            }
                            labels[0].setStr(bs.toString());
                            int color = 65280;
                            if (tree.getCurrSkillLevel() == 0) {
                                color = 16711680;
                            }
                            labels[0].setGreenColor(color);
                            labels[0].setGreenChars(skillName.length() + 1, skillName.length() + 3);
                            labels[1].setStr(Cons.SKILL_DETAIL[(Player.getInstance()).profession - 1][tree.getCurrSkillId()][0] + Cons.SKILL_DETAIL[(Player.getInstance()).profession - 1][tree.getCurrSkillId()][1]);
                            break;
                        }
                        if (tree.getCurrSkillId() >= 15) {
                            labels[0].setStr(Cons.SKILL_DETAIL_EX[tree.getCurrSkillId() - 15][0]);
                            labels[1].setStr(Cons.SKILL_DETAIL_EX[tree.getCurrSkillId() - 15][1]);
                            break;
                        }
                        labels[0].setStr(Cons.SKILL_DETAIL_EX[3][0]);
                        labels[1].setStr(Cons.SKILL_DETAIL_EX[3][1]);
                    }
                    break;
                }
                if (cmd == tree) {
                    for (byte i = 1; i <= 9; i = (byte) (i + 1)) {
                        if (isKeyPress(KEYS[i])) {
                            if (tree.isCurrSkillValid()) {
                                Player.defineKey(i, tree.getCurrSkillId());
                            }
                            break;
                        }
                    }
                }
                break;
        }
    }

    /**
     * 处理地图选项菜单按钮
     */
    public void keyInAllMap() {
        UIComponent cmd;
        switch (getUIMapState()) {
            case 0: {  // 世界地图界面按钮处理
                if (isKeyPress(18)) {
                    if (shortcut_9[Cons.nineShort]) {
                        switch (worldmapplace) {
                            case 0:
                                setGameState((byte) 0);
                                break;
                        }
                    } else {
                        switch (worldmapplace) {
                            case 0:
                                setGameState((byte) 1);
                                setRightMenuSubState(-1);
                                break;
                        }
                    }
                    for (int i = 0; i < shortcut_9.length; i++) {
                        shortcut_9[i] = false;
                    }
                    Map.regionLines = null;
                    Map.regionName = null;
                    Map.regionPos = null;
                    Map.regionProps = null;
                }
                if (isKeyPress(11) || isKeyPress(2)) {
                    Map.goNearByPlace((byte) 1);
                } else if (isKeyPress(13) || isKeyPress(8)) {
                    Map.goNearByPlace((byte) 2);
                } else if (isKeyPress(10) || isKeyPress(4)) {
                    Map.goNearByPlace((byte) 3);
                } else if (isKeyPress(12) || isKeyPress(6)) {
                    Map.goNearByPlace((byte) 4);
                }
                break;
            }
            case 1: {  // NPC位置界面按钮处理
                cmd = baseForm.getCommand();
                if (isKeyPress(18)) {
                    setGameState((byte) 1);
                    setRightMenuSubState(-1);
                    baseForm = null;
                    break;
                }
                if (!actionInForm(cmd) && (isKeyPress(17) || isKeyPress(14))) {
                    ObjManager.currentTarget = null;
                    String pos = npcPOSTables.getCurentItem().substring(npcPOSTables.getCurentItem().indexOf(":") + 1);
                    int npcx = Integer.parseInt(pos.substring(0, pos.indexOf(",")));
                    int npcy = Integer.parseInt(pos.substring(pos.indexOf(",") + 1));
                    setGameState((byte) 0);
                    releaseUI();
                    Player.getInstance().setAimColRow(npcx, npcy);
                    Player.getInstance().setState((byte) 1);
                }
                break;
            }
        }
    }

    public void keyInHelp() {
        if (baseForm == null) {
            return;
        }
        switch (getUIHelpState()) {
            case 2:
                PCBindService.getInstance().action();
                break;
        }
    }

    public void keyInNPCMenu() {
        if (this.baseForm != null) {
            UIComponent cmd = this.baseForm.getCommand();
            if (!isKeyPress(17) && !isKeyPress(14)) {
                if (isKeyPress(18)) {
                    if (this.baseForm.getCurrentFocusForm() == this.baseForm) {
                        if (NPCMenu.getSubMenu() == null) {
                            if (firstLogon == 1) {
                                setMessage(this.baseForm, "请按选择键查看任务详情，按操作键接受任务。");
                            } else {
                                NPCMenu = null;
                                PCArena.releaseInstance();
                                this.releaseUI();
                                this.setGameState((byte) 0);
                                ObjManager.currentTarget = Player.getInstance();
                                ObjManager.showTarget = null;
                            }
                        } else {
                            NPCMenu.setSubMenu((UIMenu) null);
                        }
                    } else if ("detail".startsWith(this.baseForm.getCurrentFocusForm().getName())) {
                        this.baseForm.setAboutForm((UIForm) null);
                        this.labels[5].setStr("选择");
                    } else if ("mss2".equals(this.baseForm.getCurrentFocusForm().getName())) {
                        this.baseForm.setAboutForm((UIForm) null);
                    }
                } else if (this.actionInForm(cmd)) {
                }
            } else if (this.baseForm.getSubForm() == null) {
                this.baseForm.setAboutForm((UIForm) null);
                this.baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                switch (NPCMenu.getMappingPointer()) {
                    case 1:
                        if (NPCMenu.getSubMenu() == null) {
                            ni.send(163577856);
                        } else {
                            ni.send(163578112);
                        }
                        break;
                    case 2:
                        packageSend = 1;
                        ni.send(67109120);
                        break;
                    case 3:
                        ni.send(154140672);
                        break;
                    case 4:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 66:
                    case 67:
                        ni.send(155189248);
                        break;
                    case 5:
                        this.setNPCSubState((byte) 5);
                        this.releaseUI();
                        break;
                    case 6:
                        ni.send(251658496);
                        this.baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        break;
                    case 7:
                        this.baseForm.setAboutForm((UIForm) null);
                        this.setNPCSubState((byte) 7);
                        this.setAuctionState((byte) -10);
                        break;
                    case 8:
                        packageSend = 2;
                        ni.send(67109120);
                        break;
                    case 9:
                        packageSend = 3;
                        ni.send(67109120);
                        break;
                    case 10:
                        ni.send(157286400);
                        break;
                    case 11:
                        if (NPCMenu.getSubMenu() == null) {
                            this.menus[1] = new UIMenu(0, 0, 80, 0, (String) null, Cons.MAIL_MENU);
                            NPCMenu.setSubMenu(this.menus[1]);
                            this.baseForm.setAboutForm((UIForm) null);
                        } else {
                            PCMail.initMailState();
                            switch (NPCMenu.getSubMenu().getCurrentPointer()) {
                                case 0:
                                    ni.send(184549888);
                                    waitCnt = 0;
                                    break;
                                case 1:
                                    ni.send(184551168);
                            }
                        }
                    case 12:
                        break;
                    case 20:
                        ni.send(1073742080);
                        this.releaseUI();
                        break;
                    case 21:
                        if (NPCMenu.getSubMenu() == null) {
                            this.menus[1] = new UIMenu(0, 0, 80, 0, (String) null, Cons.MENU_DIVORCE);
                            NPCMenu.setSubMenu(this.menus[1]);
                            this.baseForm.setAboutForm((UIForm) null);
                        } else {
                            switch (NPCMenu.getSubMenu().getCurrentPointer()) {
                                case 0:
                                    ni.send(1073742336);
                                    NPCMenu.setSubMenu((UIMenu) null);
                                    this.releaseUI();
                                    return;
                                case 1:
                                    ni.send(1073742592);
                                    NPCMenu.setSubMenu((UIMenu) null);
                                    this.releaseUI();
                            }
                        }
                        break;
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                        this.baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        ni.send(1610612992);
                        break;
                    case 26:
                    case 30:
                    case 34:
                    case 35:
                    case 45:
                    case 59:
                    case 60:
                    case 61:
                    case 63:
                    case 75:
                    case 77:
                    case 78:
                    case 79:
                    case 80:
                    case 81:
                    case 82:
                    case 86:
                    case 87:
                    case 88:
                    case 89:
                    case 90:
                    case 91:
                    case 92:
                    case 93:
                    case 94:
                    case 95:
                    case 96:
                    case 97:
                    case 98:
                    case 99:
                    case 100:
                    case 101:
                    case 102:
                    case 103:
                    case 104:
                    case 105:
                    case 106:
                    case 107:
                    case 108:
                    case 109:
                    case 110:
                    case 111:
                    case 112:
                    case 113:
                    case 114:
                    case 115:
                    case 116:
                    case 117:
                    case 118:
                    case 119:
                    default:
                        if (NPCMenu.getMappingPointer() < 120) {
                            setMessage(this.baseForm, "此功能还未开放");
                        } else {
                            this.baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                            ni.send(1610612992);
                        }
                        break;
                    case 27:
                        PCNPC.battleGroundIndex = 1;
                        ni.send(163643392);
                        break;
                    case 28:
                        PCNPC.battleGroundIndex = 2;
                        ni.send(163643392);
                        break;
                    case 29:
                        ni.send(163708928);
                        break;
                    case 31:
                        this.initAwardForm((byte) 31);
                        break;
                    case 32:
                        this.initAwardForm((byte) 32);
                        this.loginRewardUesrId();

                        while (this.isGetingUserID) {
                            try {
                                Thread.sleep(100L);
                            } catch (Exception var3) {
                            }
                        }

                        return;
                    case 33:
                        this.initAwardForm((byte) 33);
                        break;
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 64:
                    case 69:
                        PCArena.getInstance().sendCommand(NPCMenu.getMappingPointer());
                        break;
                    case 44:
                        packageSend = 4;
                        ni.send(67109120);
                        startWait(this.baseForm);
                        break;
                    case 46:
                        ni.send(301990144);
                        startWait(this.baseForm);
                        break;
                    case 47:
                        ni.send(301993216);
                        startWait(this.baseForm);
                        break;
                    case 48:
                        ni.send(302002176);
                        startWait(this.baseForm);
                        break;
                    case 49:
                        ni.send(302014464);
                        startWait(this.baseForm);
                        break;
                    case 50:
                        ni.send(302026752);
                        startWait(this.baseForm);
                        break;
                    case 51:
                        ni.send(302039040);
                        startWait(this.baseForm);
                        break;
                    case 52:
                        ClanWar.getInstance().m_curPage = 0;
                        ni.send(302051328);
                        startWait(this.baseForm);
                        break;
                    case 53:
                        ClanWar.getInstance().m_curPage = 0;
                        ni.send(302055424);
                        startWait(this.baseForm);
                        break;
                    case 54:
                        ni.send(302120960);
                        startWait(this.baseForm);
                        break;
                    case 55:
                        ni.send(301992960);
                        startWait(this.baseForm);
                        break;
                    case 56:
                        PCNPC.isGold = true;
                        ni.send(251678720);
                        startWait(this.baseForm);
                        break;
                    case 57:
                        PCNPC.isGold = false;
                        ni.send(251678720);
                        startWait(this.baseForm);
                        break;
                    case 58:
                        packageSend = 5;
                        ni.send(67109120);
                        startWait(this.baseForm);
                        break;
                    case 62:
                        ni.send(1073743104);
                        startWait(this.baseForm);
                        break;
                    case 65:
                    case 71:
                    case 74:
                        this.releaseUI();
                        ni.send(163578880);
                        this.setGameState((byte) 8);
                        this.setOtherSubState((byte) 3);
                        break;
                    case 68:
                        pet.addComposite((byte) 0, (byte) 9, Cons.COMPOSITE_SKILL[9], (byte) 1, 0, 0, Cons.PET_SKILL_IMAGE_ID[9]);
                        this.composeListTitle = "材料合成列表";
                        pet.setSkillIndexAndLevelIndex(0, 1);
                        pet.material = 1;
                        ni.send(163579136);
                        break;
                    case 70:
                        startWait(this.baseForm);
                        ni.send(302645248);
                        break;
                    case 72:
                        startWait(this.baseForm);
                        ni.send(302841856);
                        break;
                    case 73:
                        startWait(this.baseForm);
                        ni.send(303038464);
                        break;
                    case 76:
                        startWait(this.baseForm);
                        ni.send(306184192);
                        break;
                    case 83:
                        startWait(this.baseForm);
                        ni.send(134219264);
                        break;
                    case 84:
                        PCArena.isArena = 0;
                        startWait(this.baseForm);
                        ni.send(285219328);
                        break;
                    case 85:
                        PCArena.isArena = 1;
                        startWait(this.baseForm);
                        ni.send(285219328);
                        break;
                    case 120:
                        for (byte i = 0; i < 2; ++i) {
                            this.taskStuffId[i] = 0;
                            this.taskStuffImageId[i] = 0;
                            this.taskDetail[i] = null;
                        }

                        ni.send(162529280);
                }
            } else if ("message".equals(this.baseForm.getSubForm().getName())) {
                this.baseForm.setAboutForm((UIForm) null);
            } else if ("msg".equals(this.baseForm.getSubForm().getName())) {
                this.baseForm.setAboutForm((UIForm) null);
            } else if ("arena".equals(this.baseForm.getSubForm().getName())) {
                this.baseForm.setAboutForm((UIForm) null);
            } else if ("msge".equals(this.baseForm.getCurrentFocusForm().getName())) {
                this.baseForm.setAboutForm((UIForm) null);
            } else if ("mss2".equals(this.baseForm.getCurrentFocusForm().getName())) {
                this.baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                ni.send(1879048704);
            }

        }
    }

    public static boolean isKeyPressOk() {
        return (isKeyPress(14) || isKeyPress(17));
    }

    public void keyInNPCTrade() {
        if (baseForm == null) {
            return;
        }
        UIComponent cmd = baseForm.getCommand();
        if (isKeyPress(14) || isKeyPress(17)) {
            if (baseForm.getSubForm() == null) {
                if (cmd == dramatisPackage) {
                    if (dramatisPackage.getSubMenu() == null) {
                        if (dramatisPackage.getCurrentBigType() != 0) {
                            menus[0] = null;
                            menus[0] = new UIMenu(0, 0, 60, 0, null, Cons.BUY_MENU);
                            menus[0].setNoUse((byte) 0);
                            if (PCNPC.getHonorSign() == 3) {
                                menus[0].setItem((byte) 0, "兑换");
                            } else if (PCNPC.getHonorSign() == 4) {
                                menus[0].setItem((byte) 0, "兑换");
                                menus[0].setNoUse((byte) 2);
                            }
                            dramatisPackage.setSubMenu(menus[0]);
                        }
                    } else {
                        switch (dramatisPackage.getSubMenu().getCurrentPointer()) {
                            case 1:
                                isPackage = 1;
                                lookStuffPlace = (short) dramatisPackage.getCurrentPointer();
                                lookType = 1;
                                ni.send(67110656);
                                dramatisPackage.setSubMenu((UIMenu) null);
                                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                break;
                            case 2:
                                if (dramatisPackage.getCurrentCanTrade()) {
                                    if (dramatisPackage.getCurrentNumber() == 1) {
                                        tradeNumber = 1;
                                        tradeStuffId = dramatisPackage.getCurrentId();
                                        dramatisPackage.setSubMenu((UIMenu) null);
                                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                        ni.send(152051712);
                                        break;
                                    }
                                    dramatisPackage.setSubMenu((UIMenu) null);
                                    texts[9] = new UIText(0, 0, 92, 0, 6, (byte) 2, "1");
                                    texts[9].setMaxNumber(dramatisPackage.getCurrentNumber());
                                    baseForm.addInputForm("inputNumber0", "请输入数量：", texts[9], 100);
                                    break;
                                }
                                dramatisPackage.setSubMenu((UIMenu) null);
                                setMessage(baseForm, "此物品不可交易");
                                break;
                            case 3:
                                dramatisPackage.setSubMenu((UIMenu) null);
                                baseForm.addAboutForm("confirm", "确定丢弃该物品吗？", (byte) 2, 120, 0);
                                break;
                        }
                    }
                } else if (cmd == grids[0]) {
                    if (grids[0].getSubMenu() == null) {
                        if (grids[0].getCurrentId() != 0) {
                            menus[0] = null;
                            menus[0] = new UIMenu(0, 0, 60, 0, null, Cons.BUY_MENU);
                            if (PCNPC.getHonorSign() == 3 || PCNPC.getHonorSign() == 4) {
                                menus[0].setItem((byte) 0, "兑换");
                            }
                            menus[0].setNoUse((byte) 2);
                            menus[0].setNoUse((byte) 3);
                            grids[0].setSubMenu(menus[0]);
                        }
                    } else {
                        boolean commonStuff;
                        int price;
                        int canBuyNum;
                        String msg;
                        switch (grids[0].getSubMenu().getCurrentPointer()) {
                            case 1:
                                selectedId = grids[0].getCurrentId();
                                ni.send(67111936);
                                grids[0].setSubMenu((UIMenu) null);
                                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                break;
                            case 0:
                                if (PCNPC.getHonorSign() == 3) {
                                    menus[0] = null;
                                    grids[0].setSubMenu((UIMenu) null);
                                    tradeStuffId = grids[0].getCurrentId();
                                    ni.send(251674624);
                                    startWait(baseForm.getCurrentFocusForm());
                                    break;
                                }
                                if (PCNPC.getHonorSign() == 4) {
                                    menus[0] = null;
                                    grids[0].setSubMenu((UIMenu) null);
                                    tradeStuffId = grids[0].getCurrentId();
                                    ni.send(164757504);
                                    startWait(baseForm.getCurrentFocusForm());
                                    break;
                                }
                                if (!grids[0].isCurStuffCanRepeat()) {
                                    tradeNumber = 1;
                                    boolean bool = true;
                                    int i = shopStuffPrice[grids[0].getCurrentPointer()];
                                    if (i >= 1000000000) {
                                        bool = false;
                                    }
                                    if (PCNPC.isHonorStuff()) {
                                        if (PCNPC.honor >= i) {
                                            tradeNumber = 1;
                                            tradeStuffId = grids[0].getCurrentId();
                                            tradePlace = grids[0].getCurrentPointer();
                                            grids[0].setSubMenu((UIMenu) null);
                                            baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                            ni.send(152047616);
                                            break;
                                        }
                                        setMessage(baseForm, "荣誉不够");
                                        break;
                                    }
                                    if (bool) {
                                        if ((Player.getInstance()).money >= i) {
                                            tradeNumber = 1;
                                            tradeStuffId = grids[0].getCurrentId();
                                            tradePlace = grids[0].getCurrentPointer();
                                            grids[0].setSubMenu((UIMenu) null);
                                            baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                            ni.send(152047616);
                                            break;
                                        }
                                        baseForm.addAboutForm("msg", "金钱不够", (byte) 1, screenW - 50, 0);
                                        setMessage(baseForm, "金钱不够");
                                        break;
                                    }
                                    if (PCNPC.reputation >= i - 1000000000) {
                                        tradeNumber = 1;
                                        tradeStuffId = grids[0].getCurrentId();
                                        tradePlace = grids[0].getCurrentPointer();
                                        grids[0].setSubMenu((UIMenu) null);
                                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                        ni.send(152047616);
                                        break;
                                    }
                                    setMessage(baseForm, "声望不够");
                                    break;
                                }
                                commonStuff = true;
                                price = shopStuffPrice[grids[0].getCurrentPointer()];
                                if (price >= 1000000000) {
                                    commonStuff = false;
                                }
                                grids[0].setSubMenu((UIMenu) null);
                                texts[9] = new UIText(0, 0, 92, 0, 6, (byte) 2, "1");
                                canBuyNum = 0;
                                if (PCNPC.isHonorStuff()) {
                                    canBuyNum = PCNPC.honor / price;
                                } else if (commonStuff) {
                                    canBuyNum = (Player.getInstance()).money / price;
                                } else {
                                    price -= 1000000000;
                                    canBuyNum = PCNPC.reputation / price;
                                }
                                if (canBuyNum != 0) {
                                    if (Util.shopSign[grids[0].getCurrentPointer()]) {
                                        canBuyNum = (canBuyNum > grids[0].getCurrentNumber()) ? grids[0].getCurrentNumber() : canBuyNum;
                                        texts[9].setMaxNumber(canBuyNum);
                                    } else {
                                        canBuyNum = (canBuyNum > 20) ? 20 : canBuyNum;
                                        texts[9].setMaxNumber(canBuyNum);
                                    }
                                    baseForm.addInputForm("inputNumber1", "请输入数量：", texts[9], 100);
                                    break;
                                }
                                msg = null;
                                if (PCNPC.isHonorStuff()) {
                                    msg = "荣誉不够";
                                } else if (commonStuff) {
                                    msg = "金钱不够";
                                } else {
                                    msg = "声望不够";
                                }
                                setMessage(baseForm, msg);
                                break;
                        }
                    }
                }
            } else if ("inputNumber1".equals(baseForm.getSubFormName())) {
                tradeNumber = (byte) texts[9].getNumber();
                if (tradeNumber > 0) {
                    tradeStuffId = grids[0].getCurrentId();
                    tradePlace = grids[0].getCurrentPointer();
                    baseForm.setAboutForm((UIForm) null);
                    baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                    ni.send(152047616);
                } else {
                    baseForm.setAboutForm((UIForm) null);
                }
            } else if ("cannotBuy".equals(baseForm.getSubFormName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("cannotSale".equals(baseForm.getSubFormName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("inputNumber0".equals(baseForm.getSubFormName())) {
                tradeNumber = (byte) texts[9].getNumber();
                if (tradeNumber > 0) {
                    tradeStuffId = dramatisPackage.getCurrentId();
                    baseForm.setAboutForm((UIForm) null);
                    baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                    ni.send(152051712);
                } else {
                    baseForm.setAboutForm((UIForm) null);
                }
            } else if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("confirm".equals(baseForm.getCurrentFocusForm().getName())) {
                isPackage = 1;
                baseForm.setAboutForm((UIForm) null);
                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                ni.send(67110912);
            }
        } else if (isKeyPress(18)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                if (cmd == dramatisPackage && dramatisPackage.getSubMenu() != null) {
                    dramatisPackage.setSubMenu((UIMenu) null);
                } else if (cmd == grids[0] && grids[0].getSubMenu() != null) {
                    grids[0].setSubMenu((UIMenu) null);
                } else {
                    releaseUI();
                    setNPCSubState((byte) 0);
                }
            } else {
                if ("inputNumber0".equals(baseForm.getSubFormName())) {
                    baseForm.setAboutForm((UIForm) null);
                    return;
                }
                if ("inputNumber1".equals(baseForm.getSubFormName())) {
                    baseForm.setAboutForm((UIForm) null);
                    return;
                }
                if ("detail".equals(baseForm.getSubFormName())) {
                    baseForm.setAboutForm((UIForm) null);
                    return;
                }
                if ("confirm".equals(baseForm.getCurrentFocusForm().getName())) {
                    baseForm.setAboutForm((UIForm) null);
                } else if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                    baseForm.setAboutForm((UIForm) null);
                }
            }
        } else if (actionInForm(cmd)) {
            if (baseForm.focusComponent == grids[0]) {
                labels[1].setColor(Cons.STUFF_NAME_COLOR[grids[0].getCurrentNameLevel()]);
                labels[1].setStr(grids[0].getCurrentName());
                if (PCNPC.getHonorSign() == 3) {
                    labels[2].setStr("氏族声望");
                    labels[3].setStr("需要徽章");
                    texts[0].setLabel(PCNPC.propsNumber[grids[0].getCurrentPointer()] + "");
                    texts[1].setLabel(PCNPC.propsName[grids[0].getCurrentPointer()]);
                } else if (PCNPC.getHonorSign() == 4) {
                    labels[2].setStr("竞技点数");
                    labels[3].setStr("声誉击杀");
                    texts[0].setLabel(PCNPC.propsId[grids[0].getCurrentPointer()] + "");
                    texts[1].setLabel(PCNPC.propsNumber[grids[0].getCurrentPointer()] + "");
                } else if (PCNPC.isHonorStuff()) {
                    int price = shopStuffPrice[grids[0].getCurrentPointer()];
                    labels[2].setStr("需要荣誉");
                    labels[3].setStr("玩家荣誉");
                    texts[0].setLabel(price + "");
                    texts[1].setLabel(PCNPC.honor + "");
                } else {
                    int price = shopStuffPrice[grids[0].getCurrentPointer()];
                    if (price >= 1000000000) {
                        labels[2].setStr("需要声望");
                        labels[3].setStr("玩家声望");
                        price -= 1000000000;
                        texts[1].setLabel(PCNPC.reputation + "");
                    } else {
                        labels[2].setStr("物品价格");
                        labels[3].setStr("玩家金钱");
                        texts[1].setLabel((Player.getInstance()).money + "");
                    }
                    texts[0].setLabel(price + "");
                }
                stuffName.setStr("   ");
            } else if (baseForm.focusComponent == dramatisPackage) {
                if (PCNPC.getHonorSign() == 3) {
                    labels[2].setStr("物品价格");
                    labels[3].setStr("氏族声望");
                    texts[0].setLabel(packageStuffPrice[dramatisPackage.getCurrentPointer()] + "");
                    texts[1].setLabel(PCNPC.needOne + "");
                } else if (PCNPC.getHonorSign() == 4) {
                    labels[2].setStr("玩家竞技");
                    labels[3].setStr("玩家声誉");
                    texts[0].setLabel(PCNPC.needOne + "");
                    texts[1].setLabel(PCNPC.needTwo + "");
                } else {
                    labels[2].setStr("物品价格");
                    labels[3].setStr("玩家金钱");
                    texts[0].setLabel(packageStuffPrice[dramatisPackage.getCurrentPointer()] + "");
                    texts[1].setLabel((Player.getInstance()).money + "");
                }
                stuffName.setColor(Cons.STUFF_NAME_COLOR[dramatisPackage.getCurrentNameLevel()]);
                stuffName.setStr(dramatisPackage.getCurrentName());
                labels[1].setStr("   ");
            }
        }
    }

    public void keyInNPCFix() {
        if (baseForm == null) {
            return;
        }
        UIComponent cmd = baseForm.getCommand();
        if (isKeyPress(17) || isKeyPress(14)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                String[] strs = {"修理单个", "修理全部"};
                UIForm menuForm = new UIForm(0, 0, screenW - 1, screenH - 1, "menu");
                menuForm.setBackGround((byte) 9);
                menus[0] = new UIMenu(30, 0, 80, 0, null, strs);
                menuForm.addComponent(menus[0]);
                (menus[0]).positionY = screenH - (menus[0]).height - 4;
                baseForm.setAboutForm(menuForm);
                baseForm.focusComponent.setFocus(true);
            } else if ("conf".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getSubForm().setAboutForm((UIForm) null);
                baseForm.setAboutForm((UIForm) null);
                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                fixPlace = UIMImage.sign;
                ni.send(154140928);
            } else if ("confAll".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getSubForm().setAboutForm((UIForm) null);
                baseForm.setAboutForm((UIForm) null);
                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                fixPlace = 8;
                ni.send(154140928);
            } else if ("menu".equals(baseForm.getCurrentFocusForm().getName())) {
                switch (menus[0].getCurrentPointer()) {
                    case 0:
                        if (equipStuffId[UIMImage.sign] == 0) {
                            baseForm.setAboutForm((UIForm) null);
                            setMessage(baseForm, "没有装备可修理");
                            break;
                        }
                        if (texts[0].getNumber() == 0) {
                            setMessage(baseForm, "不需要修理");
                            break;
                        }
                        baseForm.getSubForm().addAboutForm("conf", "确实要修理吗?", (byte) 2, screenW - 30, 0);
                        break;
                    case 1:
                        if (texts[1].getNumber() == 0) {
                            setMessage(baseForm, "不需要修理");
                            break;
                        }
                        baseForm.getSubForm().addAboutForm("confAll", "确实要全部修理吗?", (byte) 2, screenW - 30, 0);
                        break;
                }
            } else if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            }
        } else if (isKeyPress(18)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                releaseUI();
                fixPlace = 0;
                UIMImage.sign = 0;
                setNPCSubState((byte) 0);
            } else if ("menu".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("conf".endsWith(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getSubForm().setAboutForm((UIForm) null);
            } else if ("confAll".endsWith(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("mag".endsWith(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            }
        } else if (actionInForm(cmd) && cmd instanceof UIMImage) {
            UIComponent cmd1 = baseForm.getCommand();
            UIMImage.sign = ((UIMImage) cmd1).index;
            texts[0].setLabel(fixPrice[UIMImage.sign] + "");
            labels[0].setStr(equipSruffName[UIMImage.sign]);
            labels[0].setColor(Cons.STUFF_NAME_COLOR[equipStuffNameLevel[UIMImage.sign]]);
        }
    }

    public void keyInNPCAdoptPet() {
        if (baseForm == null) {
            return;
        }
        UIComponent cmd = baseForm.getCommand();
        if (isKeyPress(17) || isKeyPress(14)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                dramatisPetId = 0;
                dramatisPetId = (byte) (dramatisPetId + menus[0].getCurrentPointer() + 1);
                baseForm.addAboutForm("confirm", "确认领养“" + Cons.PET_NAME[dramatisPetId - 1] + "”?", (byte) 2, 200, 0);
            } else if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("confirm".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                ni.send(156237824);
            }
        } else if (isKeyPress(18)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                setNPCSubState((byte) 0);
                releaseUI();
            } else if ("confirm".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            }
        }
        if (actionInForm(cmd) && cmd == menus[0]) {
            byte tIndex = menus[0].getCurrentPointer();
            labels[0].setStr(Cons.PET_NAME[tIndex]);
            mImages[0].setCurrentFrame(tIndex);
            textArea[0].setString(Cons.PET_dETAIL[tIndex]);
        }
    }

    public void keyInNPCPetLearnSkills() {
        if (baseForm == null) {
            return;
        }
        UIComponent cmd = baseForm.getCommand();
        if (isKeyPress(17) || isKeyPress(14)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                ni.send(50331904);
            } else if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            }
        } else if (isKeyPress(18)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                setNPCSubState((byte) 0);
                releaseUI();
            } else if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            }
        }
        if (actionInForm(cmd));
    }

    public void keyInNPCSmith() {
        if (baseForm == null) {
            return;
        }
        UIComponent cmd = baseForm.getCommand();
        if (isKeyPress(17) || isKeyPress(14)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                if (dramatisPackage.getCurrentId() == 0) {
                    setMessage(baseForm, "没有物品");
                } else if (dramatisPackage.isLock1) {
                    boolean canSmith = true;
                    short[] tmpPackage = dramatisPackage.getStuffId();
                    if (smithId != 0) {
                        for (int i = 0; i < 36; i++) {
                            if (smithId == tmpPackage[i]) {
                                canSmith = true;
                                break;
                            }
                            canSmith = false;
                        }
                    }
                    if ((Player.getInstance()).money < smithMoney) {
                        canSmith = false;
                    }
                    if (canSmith) {
                        baseForm.setAboutForm((UIForm) null);
                        baseForm.addAboutForm("confirm", "这个我不敢保证一定成功,如果打坏了你可不要怪我.是否继续?", (byte) 2, 140, 70);
                    } else {
                        setMessage(baseForm, "金钱或素材不够，不能被精炼");
                    }
                } else {
                    UIForm menuForm = new UIForm(0, 0, screenW - 1, screenH - 1, "menu");
                    menuForm.setBackGround((byte) 9);
                    menus[0] = new UIMenu(30, 0, 60, 0, null, Cons.NPC_SMITH);
                    (menus[0]).positionY = screenH - (menus[0]).height - 4;
                    (menus[0]).positionY -= 100;
                    menuForm.addComponent(menus[0]);
                    baseForm.setAboutForm(menuForm);
                    baseForm.focusComponent.setFocus(true);
                }
            } else if ("confirm".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                ni.send(160432640);
            } else if ("discard".equals(baseForm.getCurrentFocusForm().getName())) {
                isPackage = 1;
                baseForm.setAboutForm((UIForm) null);
                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                ni.send(67110912);
            } else if ("succeed".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
                dramatisPackage.isLock1 = false;
                labels[3].setStr("操作");
                labels[4].setStr("返回");
                labels[0].setStr("请选择想要进行精炼的武器或防具");
                if (labels[8] != null) {
                    baseForm.removeComponent(labels[8]);
                }
                if (labels[9] != null) {
                    baseForm.removeComponent(labels[9]);
                }
                if (mImages[9] != null) {
                    baseForm.removeComponent(mImages[9]);
                }
            } else if ("fail".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
                if (dramatisPackage.isLock1) {
                    dramatisPackage.isLock1 = false;
                    labels[3].setStr("操作");
                    labels[4].setStr("返回");
                    labels[0].setStr("请选择想要进行精炼的武器或防具");
                    if (labels[8] != null) {
                        baseForm.removeComponent(labels[8]);
                    }
                    if (labels[9] != null) {
                        baseForm.removeComponent(labels[9]);
                    }
                    if (mImages[9] != null) {
                        baseForm.removeComponent(mImages[9]);
                    }
                }
            } else if ("menu".equals(baseForm.getCurrentFocusForm().getName())) {
                switch (menus[0].getCurrentPointer()) {
                    case 0:
                        baseForm.setAboutForm((UIForm) null);
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        isPackage = 1;
                        lookStuffPlace = (short) dramatisPackage.getCurrentPointer();
                        lookType = 1;
                        ni.send(67110656);
                        break;
                    case 1:
                        baseForm.setAboutForm((UIForm) null);
                        if ((dramatisPackage.getCurrentLittleType() >= 101 && dramatisPackage.getCurrentLittleType() <= 114) || (dramatisPackage.getCurrentLittleType() >= 311 && dramatisPackage.getCurrentLittleType() <= 319)) {
                            baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                            ni.send(160432384);
                            break;
                        }
                        setMessage(baseForm, "此物品不能被精炼");
                        break;
                    case 2:
                        baseForm.setAboutForm((UIForm) null);
                        baseForm.addAboutForm("discard", "确定要丢弃这个物品吗?", (byte) 2, 140, 0);
                        break;
                }
            }
        } else if (isKeyPress(18)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                if (dramatisPackage.isLock1) {
                    dramatisPackage.isLock1 = false;
                    labels[3].setStr("操作");
                    labels[4].setStr("返回");
                    labels[0].setStr("请选择想要进行精炼的武器或防具");
                    if (labels[8] != null) {
                        baseForm.removeComponent(labels[8]);
                    }
                    if (labels[9] != null) {
                        baseForm.removeComponent(labels[9]);
                    }
                    if (mImages[9] != null) {
                        baseForm.removeComponent(mImages[9]);
                    }
                } else {
                    setNPCSubState((byte) 0);
                    releaseUI();
                }
            } else if ("menu".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("confirm".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("discard".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("succeed".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
                labels[3].setStr("操作");
                labels[4].setStr("返回");
                if (labels[8] != null) {
                    baseForm.removeComponent(labels[8]);
                }
                if (labels[9] != null) {
                    baseForm.removeComponent(labels[9]);
                }
                if (mImages[9] != null) {
                    baseForm.removeComponent(mImages[9]);
                }
                dramatisPackage.isLock1 = false;
            } else if ("detail".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("fail".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            }
        } else if (actionInForm(cmd) && cmd == dramatisPackage) {
            stuffName.setStr(dramatisPackage.getCurrentName());
            stuffName.setColor(Cons.STUFF_NAME_COLOR[dramatisPackage.getCurrentNameLevel()]);
        }
    }

    public void keyInNPCStore() {
        if (baseForm == null) {
            return;
        }
        UIComponent cmd = baseForm.getCommand();
        if (isKeyPress(17) || isKeyPress(14)) {
            if (baseForm.getCurrentFocusForm() == baseForm && !dramatisPackage.isLock && !(grids[0]).isLock) {
                UIForm menuForm = new UIForm(0, 0, screenW - 1, screenH - 1, "menu");
                menuForm.setBackGround((byte) 9);
                menus[0] = new UIMenu(35, 0, 80, 0, null, Cons.DRAMATIS_STORE);
                menuForm.addComponent(menus[0]);
                menus[0].setXY((menus[0]).positionX, screenH - (menus[0]).height - 4);
                baseForm.setAboutForm(menuForm);
                baseForm.focusComponent.setFocus(true);
                if (baseForm.focusComponent instanceof UIGrid) {
                    UIGrid gdTemp = (UIGrid) baseForm.focusComponent;
                    if (gdTemp.getCurrentId() == 0) {
                        byte i;
                        for (i = 0; i < 5; i = (byte) (i + 1)) {
                            menus[0].setNoUse(i);
                        }
                    } else if (baseForm.focusComponent == dramatisPackage) {
                        menus[0].setNoUse((byte) 3);
                    } else if (baseForm.focusComponent == grids[0]) {
                        menus[0].setNoUse((byte) 2);
                    }
                }
            } else if (baseForm.getCurrentFocusForm() == baseForm && (dramatisPackage.isLock || (grids[0]).isLock)) {
                if (baseForm.focusComponent == dramatisPackage) {
                    if (dramatisPackage.stuffPlace != dramatisPackage.getCurrentPointer()) {
                        dramatisPackage.isLock = false;
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        ni.send(67109632);
                    } else {
                        dramatisPackage.isLock = false;
                    }
                } else if (baseForm.focusComponent == grids[0]) {
                    (grids[0]).isLock = false;
                    baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                    ni.send(161484800);
                }
            } else if ("accumulate".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                stMoney = texts[2].getNumber();
                ni.send(161505280);
            } else if ("get".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                stMoney = texts[2].getNumber();
                ni.send(161509376);
            } else if ("menu".equals(baseForm.getCurrentFocusForm().getName())) {
                switch (menus[0].getCurrentPointer()) {
                    case 0:
                        lookType = 1;
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        if (baseForm.focusComponent == dramatisPackage) {
                            isPackage = 1;
                            lookStuffPlace = (short) dramatisPackage.getCurrentPointer();
                        } else {
                            isPackage = 2;
                            lookStuffPlace = (short) grids[0].getCurrentPointer();
                        }
                        ni.send(67110656);
                        break;
                    case 1:
                        baseForm.setAboutForm((UIForm) null);
                        if (baseForm.focusComponent == dramatisPackage) {
                            dramatisPackage.isLock = true;
                            dramatisPackage.stuffPlace = dramatisPackage.getCurrentPointer();
                            stNumber = dramatisPackage.getCurrentNumber();
                            break;
                        }
                        if (baseForm.focusComponent == grids[0]) {
                            (grids[0]).isLock = true;
                            (grids[0]).stuffPlace = grids[0].getCurrentPointer();
                        }
                        break;
                    case 2:
                        if (dramatisPackage.getCurrentNumber() == 1) {
                            stPlace = dramatisPackage.getCurrentPointer();
                            stNumber = dramatisPackage.getCurrentNumber();
                            baseForm.setAboutForm((UIForm) null);
                            baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                            ni.send(161488896);
                            break;
                        }
                        texts[2] = null;
                        texts[2] = new UIText(0, 25, 80, 0, 10, (byte) 2, dramatisPackage.getCurrentNumber() + "");
                        texts[2].setMaxNumber(dramatisPackage.getCurrentNumber());
                        baseForm.setAboutForm((UIForm) null);
                        baseForm.addInputForm("take_in", "请输入存入数量", texts[2], 100);
                        break;
                    case 3:
                        if (grids[0].getCurrentNumber() == 1) {
                            stPlace = grids[0].getCurrentPointer();
                            stNumber = grids[0].getCurrentNumber();
                            baseForm.setAboutForm((UIForm) null);
                            baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                            ni.send(161492992);
                            break;
                        }
                        texts[2] = null;
                        texts[2] = new UIText(0, 25, 80, 0, 10, (byte) 2, grids[0].getCurrentNumber() + "");
                        texts[2].setMaxNumber(grids[0].getCurrentNumber());
                        baseForm.setAboutForm((UIForm) null);
                        baseForm.addInputForm("take_out", "请输入取出数量", texts[2], 100);
                        break;
                    case 4:
                        if (baseForm.focusComponent == dramatisPackage) {
                            stPlace = dramatisPackage.getCurrentPointer();
                            discardPlace = 0;
                        } else if (baseForm.focusComponent == grids[0]) {
                            stPlace = grids[0].getCurrentPointer();
                            discardPlace = 1;
                        }
                        baseForm.setAboutForm((UIForm) null);
                        baseForm.addAboutForm("confirm", "确实要丢弃吗？", (byte) 2, 160, 0);
                        break;
                    case 5:
                        baseForm.setAboutForm((UIForm) null);
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 20);
                        ni.send(67111424);
                        break;
                    case 6:
                        baseForm.setAboutForm((UIForm) null);
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        ni.send(161501184);
                        break;
                    case 7:
                        texts[2] = null;
                        texts[2] = new UIText(0, 25, 80, 0, 10, (byte) 3, "");
                        texts[2].setMaxNumber(texts[1].getNumber());
                        baseForm.setAboutForm((UIForm) null);
                        baseForm.addInputForm("accumulate", "请输入存入金额", texts[2], 100);
                        baseForm.getSubForm().setFormXY(screenW - (baseForm.getSubForm()).width >> 1, screenH >> 1);
                        break;
                    case 8:
                        texts[2] = null;
                        texts[2] = new UIText(0, 25, 80, 0, 10, (byte) 3, "");
                        texts[2].setMaxNumber(texts[0].getNumber());
                        baseForm.setAboutForm((UIForm) null);
                        baseForm.addInputForm("get", "请输入取出金额", texts[2], 100);
                        break;
                }
            } else if ("confirm".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                ni.send(161497088);
            } else if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("take_in".equals(baseForm.getCurrentFocusForm().getName())) {
                stPlace = dramatisPackage.getCurrentPointer();
                stNumber = (byte) texts[2].getNumber();
                baseForm.setAboutForm((UIForm) null);
                if (stNumber == 0) {
                    return;
                }
                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                ni.send(161488896);
            } else if ("take_out".equals(baseForm.getCurrentFocusForm().getName())) {
                stPlace = grids[0].getCurrentPointer();
                stNumber = (byte) texts[2].getNumber();
                baseForm.setAboutForm((UIForm) null);
                if (stNumber == 0) {
                    return;
                }
                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                ni.send(161492992);
            }
        } else if (isKeyPress(18)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                if (dramatisPackage.isLock || (grids[0]).isLock) {
                    dramatisPackage.isLock = false;
                    (grids[0]).isLock = false;
                } else {
                    setNPCSubState((byte) 0);
                    releaseUI();
                }
            } else if ("menu".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("accumulate".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("get".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("confirm".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("detail".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("take_in".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("take_out".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            }
        } else if (actionInForm(cmd)) {
            if (baseForm.focusComponent == grids[0]) {
                String name = grids[0].getCurrentName();
                stuffName.setStr(name);
                stuffName.setColor(Cons.STUFF_NAME_COLOR[grids[0].getCurrentNameLevel()]);
            } else if (baseForm.focusComponent == dramatisPackage) {
                String name = dramatisPackage.getCurrentName();
                stuffName.setStr(name);
                stuffName.setColor(Cons.STUFF_NAME_COLOR[dramatisPackage.getCurrentNameLevel()]);
            }
        }
    }

    public void keyInNPCTask() {
        if (baseForm == null) {
            return;
        }
        UIComponent cmd = baseForm.getCommand();
        if (isKeyPress(17) || isKeyPress(14)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                if (!NPCMenu.isCurrentCanResponse()) {
                    if (NPCMenu.getCurrentRealState() == 2 && taskStuffId[taskSelectedId] == 0) {
                        return;
                    }
                    String[] strs = {"接受任务", "查看物品"};
                    menus[0] = null;
                    menus[0] = new UIMenu(35, 0, 80, 0, null, strs);
                    UIForm subForm = new UIForm(0, 0, screenW - 1, screenH - 1, "menu");
                    subForm.setBackGround((byte) 9);
                    subForm.addComponent(menus[0]);
                    menus[0].setXY((menus[0]).positionX, screenH - (menus[0]).height - 4);
                    if (NPCMenu.getCurrentRealState() == 2) {
                        menus[0].setNoUse((byte) 0);
                    }
                    baseForm.setAboutForm(subForm);
                    baseForm.setFocusComponentFocus(true);
                    if (cmd instanceof UIMImage) {
                        taskSelectedId = ((UIMImage) cmd).index;
                    }
                    if (NPCMenu.getCurrentRealState() == 0) {
                        if (taskStuffId[taskSelectedId] == 0) {
                            menus[0].setNoUse((byte) 1);
                        }
                    } else if (NPCMenu.getCurrentRealState() == 1) {
                        menus[0].setNoUse((byte) 0);
                        if (taskStuffId[taskSelectedId] == 0) {
                            baseForm.setAboutForm((UIForm) null);
                        }
                    }
                } else {
                    baseForm.setAboutForm((UIForm) null);
                    baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                    ni.send(162530816);
                }
            } else if ("menu".equals(baseForm.getCurrentFocusForm().getName())) {
                switch (menus[0].getCurrentPointer()) {
                    case 0:
                        baseForm.setAboutForm((UIForm) null);
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        if (NPCMenu.getCurrentRealState() == 0) {
                            ni.send(162529536);
                            firstLogon = 0;
                        }
                        break;
                    case 1:
                        baseForm.setAboutForm((UIForm) null);
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        selectedId = taskStuffId[taskSelectedId];
                        ni.send(67111936);
                        break;
                }
            } else if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            }
        } else if (isKeyPress(18)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                setNPCSubState((byte) 0);
                releaseUI();
            } else if ("fail".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("menu".startsWith(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("msg".startsWith(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("detail".startsWith(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            }
        } else if (baseForm.getCurrentFocusForm() == baseForm) {
            if (actionInForm(cmd));
            if (isKeyPress(11)) {
                if (textArea[0] != null) {
                    textArea[0].up();
                }
            } else if (isKeyPress(13) && textArea[0] != null) {
                textArea[0].down();
            }
        } else if (actionInForm(cmd)) {
        }
    }

    public static void startWait(UIForm form) {
        form.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
    }

    public void keyInNPCUnion() {
        if (baseForm == null) {
            return;
        }
        UIComponent cmd = baseForm.getCommand();
        if (isKeyPress(17) && !(cmd instanceof UIMenu)) {
            if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("noclan".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getSubForm().setAboutForm((UIForm) null);
            } else if ("createclan".equals(baseForm.getCurrentFocusForm().getName())) {
                String[] strs = {"输入氏族信息", "发送氏族申请"};
                int h = strs.length * 22;
                menus[1] = null;
                menus[1] = new UIMenu(35, 204 - h, 110, h, null, strs);
                UIForm subForm = new UIForm(0, 0, screenW - 1, screenH - 1, "creatmenu");
                subForm.setBackGround((byte) 9);
                subForm.addComponent(menus[1]);
                baseForm.getSubForm().setAboutForm(subForm);
            } else if ("exitclan".equals(baseForm.getCurrentFocusForm().getName())) {
                ni.send(251659520);
                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
            } else if ("disbandclan".equals(baseForm.getCurrentFocusForm().getName())) {
                if (texts[0] != null && "del".equals(texts[0].getLabel())) {
                    ni.send(251659776);
                    baseForm.setAboutForm((UIForm) null);
                    baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                } else {
                    baseForm.getCurrentFocusForm().addAboutForm("delerr", "输入不正确！", (byte) 1, 140, 50);
                }
            } else if ("delerr".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getSubForm().setAboutForm((UIForm) null);
            } else if ("confirm".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                ni.send(251658496);
            } else if ("createerr".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getSubForm().setAboutForm((UIForm) null);
            } else if ("createreturn".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getSubForm().setAboutForm((UIForm) null);
            } else if ("addclan".equals(baseForm.getCurrentFocusForm().getName())) {
                if (tables[0] != null && tables[0].getItemNumber() != 0) {
                    String[] strs = {"加入氏族", "察看介绍"};
                    int h = strs.length * 22;
                    menus[2] = null;
                    menus[2] = new UIMenu(35, 204 - h, 80, h, null, strs);
                    UIForm subForm = new UIForm(0, 0, screenW - 1, screenH - 1, "addmenu");
                    subForm.setBackGround((byte) 9);
                    subForm.addComponent(menus[2]);
                    baseForm.getSubForm().setAboutForm(subForm);
                }
            } else if ("addreturn".equals(baseForm.getCurrentFocusForm().getName())) {
                if (addResult == 1) {
                    baseForm.getSubForm().addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                    ni.send(251658496);
                } else if (addResult == 2) {
                    baseForm.getSubForm().setAboutForm((UIForm) null);
                }
            }
        } else if (isKeyPress(18)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                setNPCSubState((byte) 0);
                releaseUI();
            } else if ("addmenu".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getSubForm().setAboutForm((UIForm) null);
            } else if ("clanintroduce".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getSubForm().setAboutForm((UIForm) null);
            } else if ("createclan".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getSubForm().addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                ni.send(251658496);
            } else if ("exitclan".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("disbandclan".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("creatmenu".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getSubForm().setAboutForm((UIForm) null);
            } else if ("addclan".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getSubForm().addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                ni.send(251658496);
            } else if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            }
        } else if (isKeyPress(10) && !(cmd instanceof UIMenu)) {
            if ("addclan".equals(baseForm.getCurrentFocusForm().getName()) && PCNPC.clanTotal > 1) {
                if (PCNPC.clanCurrentPage > 0) {
                    PCNPC.clanCurrentPage--;
                } else {
                    PCNPC.clanCurrentPage = PCNPC.clanTotal - 1;
                }
                startWait(baseForm.getCurrentFocusForm());
                NetInterface.getInstance().send(251658752);
            }
        } else if (isKeyPress(12) && !(cmd instanceof UIMenu)) {
            if ("addclan".equals(baseForm.getCurrentFocusForm().getName()) && PCNPC.clanTotal > 1) {
                if (PCNPC.clanCurrentPage < PCNPC.clanTotal - 1) {
                    PCNPC.clanCurrentPage++;
                } else {
                    PCNPC.clanCurrentPage = 0;
                }
                startWait(baseForm.getCurrentFocusForm());
                NetInterface.getInstance().send(251658752);
            }
        } else if (!actionInForm(cmd)) {
            if (cmd == menus[1]) {
                if (isKeyPress(14) || isKeyPress(17)) {
                    String err;
                    if ("noclan".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.getSubForm().setAboutForm((UIForm) null);
                        return;
                    }
                    if (menus[1] == null) {
                        return;
                    }
                    switch (menus[1].getCurrentPointer()) {
                        case 0:
                            clearAdvanceUIItem();
                            if (commonOk == null) {
                                commonOk = new Command("确定", 4, 2);
                                commonBack = new Command("返回", 2, 2);
                            }
                            if (commonTextField == null) {
                                commonTextField = new TextField("请输入氏族名称：", (unionName == null) ? "" : unionName, 6, 0);
                                if (clanRequestName != null) {
                                    commonTextField.setString(clanRequestName);
                                }
                                commontf = new TextField("请输入氏族介绍：", (mailDetail == null) ? "" : mailDetail, 70, 0);
                                if (clanRequestInfo != null) {
                                    commontf.setString(clanRequestInfo);
                                }
                            }
                            if (commonForm == null) {
                                commonForm = new Form("nameForm");
                                commonForm.setTitle("创建氏族");
                                commonForm.addCommand(commonOk);
                                commonForm.addCommand(commonBack);
                                commonForm.append((Item) commonTextField);
                                commonForm.append((Item) commontf);
                                commonForm.setCommandListener(this);
                            }
                            aMidlet.display.setCurrent((Displayable) commonForm);
                            break;
                        case 1:
                            err = null;
                            if (clanRequestInfo == null || clanRequestInfo.trim().equals("")) {
                                err = "请填写氏族介绍!";
                            } else if (clanRequestName == null || clanRequestName.trim().equals("")) {
                                err = "请填写氏族名称!";
                            }
                            if (err == null) {
                                ni.send(251659264);
                                baseForm.getSubForm().addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                break;
                            }
                            baseForm.getSubForm().addAboutForm("createerr", err, (byte) 1, 140, 50);
                            break;
                    }
                }
            } else if (cmd == menus[2] && cmd != null) {
                if (isKeyPress(14) || isKeyPress(17)) {
                    if (tables[0] == null || tables[0].getItemNumber() == 0) {
                        return;
                    }
                    switch (menus[2].getCurrentPointer()) {
                        case 0:
                            baseForm.getSubForm().addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                            ni.send(251659008);
                            break;
                        case 1:
                            baseForm.getSubForm().addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                            ni.send(251661568);
                            break;
                    }
                }
            } else if (cmd == menus[0] && cmd != null && (isKeyPress(14) || isKeyPress(17))) {
                UIForm createForm;
                UIRim frame;
                UIRim rimTitle;
                UILabel lblTitle;
                UIRim rimUp;
                UIRim rimDown;
                UILabel lblOk;
                UILabel lblCancel;
                UILabel lblName;
                UILabel lblMoney;
                UIRim rimText;
                String temp;
                switch (menus[0].getCurrentPointer()) {
                    case 0:
                        PCNPC.clanCurrentPage = 0;
                        ni.send(251658752);
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        break;
                    case 1:
                        clanRequestName = null;
                        clanRequestInfo = null;
                        createForm = new UIForm(0, 0, baseForm.width, baseForm.height, "createclan");
                        createForm.setBackGround((byte) 9);
                        frame = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
                        rimTitle = new UIRim(0, 12, 166, 20, (byte) 7);
                        lblTitle = new UILabel(0, 15, 176, 0, "创建氏族", 15718814, (byte) 1, (byte) 0);
                        rimUp = new UIRim(0, 32, 166, 25, (byte) 0);
                        rimDown = new UIRim(0, 57, 166, 133, (byte) 0);
                        lblOk = new UILabel(60, 7, 0, 0, "操作", 15718814, (byte) 0, (byte) 0);
                        lblCancel = new UILabel(60, 7, 0, 0, "返回", 15718814, (byte) 0, (byte) 0);
                        lblName = new UILabel(7, 35, 0, 0, "氏族名称:", 15718814, (byte) 0, (byte) 0);
                        lblMoney = new UILabel(7, 175, 0, 0, "交纳保证金 " + clanConfirmMoney, 15718814, (byte) 0, (byte) 0);
                        rimText = new UIRim(80, 35, 85, 20, (byte) 0);
                        labels[0] = new UILabel(83, 36, 0, 0, "输入氏族名称", 8355711, (byte) 0, (byte) 0);
                        textArea[0] = new UITextArea(2, 59, 162, 109, "输入氏族介绍");
                        textArea[0].setChangeColor(false);
                        createForm.addComponent(frame);
                        createForm.addComponentInCenter(rimTitle, (byte) 2);
                        createForm.addComponentInCenter(lblTitle, (byte) 2);
                        createForm.addComponentInCenter(rimUp, (byte) 2);
                        createForm.addComponentInCenter(rimDown, (byte) 2);
                        createForm.addComponentInCenter(lblOk, (byte) 5);
                        createForm.addComponentInCenter(lblCancel, (byte) 6);
                        createForm.addComponent(rimText);
                        createForm.addComponent(labels[0]);
                        createForm.addComponent(lblName);
                        createForm.addComponent(lblMoney);
                        createForm.addComponentInCenter(textArea[0], (byte) 2);
                        baseForm.setAboutForm(createForm);
                        break;
                    case 2:
                        if (clanExit == 1) {
                            temp = "你要退出" + clanName + "氏族吗？";
                        } else if (clanExit == 2) {
                            temp = "你要取消对" + clanName + "氏族的申请？";
                        } else {
                            temp = "";
                        }
                        baseForm.addAboutForm("exitclan", temp, (byte) 2, 140, 50);
                        break;
                }
            }
        }
    }

    public void keyInNPCExchange() {
        if (baseForm == null) {
            return;
        }
        UIComponent cmd = baseForm.getCommand();
        if (actionInForm(cmd));
        switch (getNPCExchangeState()) {
            case 0:
                if (isKeyPress(17) || isKeyPress(14)) {
                    if (baseForm.getCurrentFocusForm() == baseForm) {
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        changeIndex = PCNPC.exchangeTable.getCurrentPointer();
                        ni.send(157286656);
                    }
                    break;
                }
                if (isKeyPress(18)) {
                    if (baseForm.getCurrentFocusForm() == baseForm) {
                        setNPCSubState((byte) 0);
                        releaseUI();
                        break;
                    }
                    if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                    }
                }
                break;
            case 1:
                if (isKeyPress(17) || isKeyPress(14)) {
                    if (baseForm.getCurrentFocusForm() == baseForm) {
                        if (texts[1].getNumber() == 0) {
                            setMessage(baseForm, "请输入兑换数量");
                        } else if (changeMax == 10000) {
                            setMessage(baseForm, "背包已满，请及时清理！");
                        } else if (changeMax == 10001) {
                            setMessage(baseForm, "材料不足，无法兑换");
                        } else if (texts[1].getNumber() > 0) {
                            baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                            ni.send(157286912);
                        }
                    } else if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                    } else if ("succeed".equals(baseForm.getCurrentFocusForm().getName())) {
                        backToChangeList();
                    }
                }
                if (isKeyPress(18)) {
                    if (baseForm.getCurrentFocusForm() == baseForm) {
                        backToChangeList();
                        break;
                    }
                    if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                    }
                }
                break;
        }
    }

    public void keyInNPCTop() {
        if (baseForm == null) {
            return;
        }
        UIComponent cmd = baseForm.getCommand();
        if (!isKeyPress(17) && !isKeyPress(14) && isKeyPress(18)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                setNPCSubState((byte) 0);
                releaseUI();
            } else if ("confirm".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            }
        }
        if (actionInForm(cmd));
    }

    private void backToChangeList() {
        setNPCExchangeState((byte) 0);
        releaseUI();
    }

    private final boolean isDelayed(int waitFor, byte type) {
        if (curTime[type] + waitFor > paintTaken) {
            return false;
        }
        curTime[type] = 0L;
        return true;
    }

    private final void beginDelay(byte type) {
        curTime[type] = paintTaken;
    }

    private void drawLoginSelectMan(Graphics g) {
        if (logon3_state == 1) {
            if (baseForm == null) {
                baseForm = new UIForm(0, 0, screenW, screenH, "");
                baseForm.setStyle((byte) 0);
                baseForm.setBackGround((byte) 8);
                labels[0] = new UILabel(0, 8, screenW, 0, "请选择人物", 15587742, (byte) 1, (byte) 0);
                int x = 29;
                labels[1] = new UILabel(x, 75, screenW, 0, "姓名：" + playerNames[choose_manID], 15587742, (byte) 0, (byte) 0);
                labels[2] = new UILabel(x, 135, screenW, 0, "场景：" + playerInformations[choose_manID][1], 15587742, (byte) 0, (byte) 0);
                labels[3] = new UILabel(x, 90, screenW, 0, "等级：" + playerInformations[choose_manID][0], 15587742, (byte) 0, (byte) 0);
                labels[4] = new UILabel(x, 120, screenW, 0, "职业：" + playerInformations[choose_manID][3], 15587742, (byte) 0, (byte) 0);
                labels[5] = new UILabel(x, 105, screenW, 0, "种族：" + playerInformations[choose_manID][2], 15587742, (byte) 0, (byte) 0);
                labels[6] = new UILabel(50, 170, 0, 0, "按0键删除角色", 15587742, (byte) 3, (byte) 0);
                labels[6].setGreenChars(1, 2);
                labels[7] = new UILabel(0, 0, 0, 0, "进入游戏", 15587742, (byte) 0, (byte) 0);
                labels[8] = new UILabel(0, 0, 0, 0, "返回", 15587742, (byte) 0, (byte) 0);
                labels[9] = new UILabel(50, 150, 0, 0, "" + shyTime[0] + "天后可以删除", 15587742, (byte) 0, (byte) 0);
                rims[0] = new UIRim(25, 71, 120, 112, (byte) 1);
                grids[0] = new UIGrid(25, 27, (byte) 1, (byte) 3, (byte) 1, playerState, null);
                grids[0].setCurrentPointer(choose_manID);
                baseForm.addComponentInCenter(rims[0], (byte) 2);
                baseForm.addComponentInCenter(grids[0], (byte) 2);
                baseForm.addComponentInCenter(labels[0], (byte) 2);
                baseForm.addComponent(labels[1]);
                baseForm.addComponent(labels[2]);
                baseForm.addComponent(labels[3]);
                baseForm.addComponent(labels[4]);
                baseForm.addComponent(labels[5]);
                baseForm.addComponentInCenter(labels[6], (byte) 2);
                baseForm.addComponentInCenter(labels[7], (byte) 5);
                baseForm.addComponentInCenter(labels[8], (byte) 6);
                baseForm.addComponentInCenter(labels[9], (byte) 2);
                baseForm.setFocus(true);
            }
            grids[0].setHaveThings(playerState);
            if (!playerState[grids[0].getCurrentPointer()]) {
                for (byte i = 1; i < 7; i = (byte) (i + 1)) {
                    labels[i].setVisible(false);
                }
                labels[9].setVisible(false);
                labels[7].setStr("创建角色");
            } else {
                for (byte i = 1; i < 7; i = (byte) (i + 1)) {
                    labels[i].setVisible(true);
                }
                if (shyTime != null && shyTime[grids[0].getCurrentPointer()] > -1) {
                    int tmp = shyTime[grids[0].getCurrentPointer()];
                    baseForm.removeComponent(labels[9]);
                    labels[9] = new UILabel(50, 170, 0, 0, "" + ((tmp > 0) ? (tmp + "天后可以删除") : "按0键永久删除角色"), 15587742, (byte) 3, (byte) 0);
                    baseForm.addComponentInCenter(labels[9], (byte) 2);
                    labels[6].setVisible(false);
                    labels[9].setVisible(true);
                } else {
                    labels[6].setVisible(true);
                    labels[9].setVisible(false);
                }
                labels[7].setStr("进入游戏");
            }
            baseForm.draw(g);
            UIGameRun.getInstance().drawCreat(g, manX1, manY1, isSelect1, 3, imgPlayerId, playerState);
        } else if (logon3_state == 0) {
            drawErrorInfor(g, "连接服务器失败，请重新连接", "确定");
        } else if (logon3_state == 2) {
            drawErrorInfor(g, "用户已经在线，请30秒后重试", "确定");
        } else if (logon3_state == 3) {
            drawErrorInfor(g, "该用户名已经存在，请重新注册", "确定");
        } else if (logon3_state == 4) {
            drawErrorInfor(g, "删除用户失败，连接服务器失败，请重新登录", "确定");
        } else if (logon3_state == 5) {
            drawErrorInfor1(g, "您好，游戏正在更新最新内容，需要下载最新客户端，欢迎您提前体验！", "下载", "返回");
        } else if (logon3_state == 6) {
            drawErrorInfor(g, "对不起，您使用的手机无法识别，不能登录", "确定");
        } else if (logon3_state == 7) {
            drawErrorInfor(g, "验证码识别错误！请重新登录", "确定");
        } else if (logon3_state == 8) {
            drawErrorInfor(g, "非内测用户，请您到移动梦网申请内测帐号", "确定");
        } else if (logon3_state == 9) {
            drawErrorInfor1(g, "版本号已不是最新，但仍能使用，请选择是否更新", "下载", "返回");
        }
    }
    public static String firstSelectedServerID = null;
    public UITable serverTable;
    public UITable divTable;

    private void drawLoginSelectServer(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setBackGround((byte) 9);
            UIRim bigRim = new UIRim(0, 0, screenW - 1, 207, (byte) 4);
            baseForm.addComponent(bigRim);
            UILabel label1 = new UILabel(0, 0, 0, 0, "选择", 15718815, (byte) 0, (byte) 0);
            UILabel label2 = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 0, (byte) 0);
            UILabel label4 = new UILabel(10, 10, screenW, 0, "请选择分区", 15718815, (byte) 1, (byte) 0);
            baseForm.addComponentInCenter(label1, (byte) 5);
            baseForm.addComponentInCenter(label2, (byte) 6);
            baseForm.addComponentInCenter(label4, (byte) 2);
            if (PCLogin.serverDivNameList == null);
            boolean hasFirst = false;
            if (firstSelectedServerID != null && !firstSelectedServerID.trim().equals("")) {
                processServerList(firstSelectedServerID);
                hasFirst = true;
            }
            int count = PCLogin.serverDivNameList.length + (hasFirst ? 1 : 0);
            divTable = new UITable(0, 31, 160, 158, count + 1, 1, (count + 1 > 12) ? 12 : (count + 1), (byte) 0, (byte) 3);
            for (int i = 0; i < count + 1; i++) {
                if (i == count) {
                    divTable.addItem("修改密码", 10981736);
                } else if (hasFirst) {
                    if (i == 0) {
                        divTable.addItem(serverListUrl[0][0], 10981736);
                    } else if (i - 1 < PCLogin.serverDivNameList.length) {
                        divTable.addItem(PCLogin.serverDivNameList[i - 1], 10981736);
                    }
                } else {
                    divTable.addItem(PCLogin.serverDivNameList[i], 10981736);
                }
            }
            baseForm.addComponentInCenter(divTable, (byte) 2);
            divTable.setXY(divTable.positionX - 1, divTable.positionY);
            baseForm.setFocus(true);
        }
        baseForm.draw(g);
    }

    private void drawLoginCreatMan(Graphics g) {
        if (logon3_state == 1) {
            if (baseForm == null) {
                for (int i = 0; i < 8; i++) {
                    isSelect[i] = false;
                }
                isSelect[0] = true;
                baseForm = new UIForm(0, 0, screenW, screenH, "");
                int width = 34;
                int height = 40;
                int[][] p = {{49, 7}, {91, 7}, {132, 40}, {132, 87}, {91, 121}, {49, 121}, {8, 87}, {8, 40}};
                int[] c = {11264, 3080248, 4521984, 85};
                UIRim frame = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
                baseForm.addComponent(frame);
                byte b;
                for (b = 0; b < p.length; b = (byte) (b + 1)) {
                    rims[b] = new UIRim(p[b][0], p[b][1], width, height, (byte) 1);
                    rims[b].setColor(c[b / 2]);
                    rims[b].setCanFocus(true);
                    baseForm.addComponent(rims[b]);
                }
                rims[0].setAroundComponent(rims[1], (byte) 4);
                rims[0].setAroundComponent(rims[7], (byte) 3);
                rims[0].setAroundComponent(rims[7], (byte) 2);
                rims[7].setAroundComponent(rims[6], (byte) 2);
                rims[6].setAroundComponent(rims[5], (byte) 4);
                rims[6].setAroundComponent(rims[5], (byte) 2);
                rims[5].setAroundComponent(rims[4], (byte) 4);
                rims[4].setAroundComponent(rims[3], (byte) 4);
                rims[4].setAroundComponent(rims[3], (byte) 1);
                rims[3].setAroundComponent(rims[2], (byte) 1);
                rims[2].setAroundComponent(rims[1], (byte) 3);
                rims[2].setAroundComponent(rims[1], (byte) 1);
                labels[0] = new UILabel(0, 55, UIComponent.charW * 4 + 14, 0, "阵营：" + Cons.STR_CAMP[0] + "\n种族：" + Cons.STR_RACE[0] + "\n职业：" + Cons.STR_PROFESSION[3] + "\n性别：" + Cons.STR_GENDER[0], 15718814, (byte) 0, (byte) 0);
                UILabel lblConfirm = new UILabel(0, 0, 0, 0, "确定", 15718814, (byte) 0, (byte) 0);
                UILabel lblCancel = new UILabel(0, 0, 0, 0, "取消", 15718814, (byte) 0, (byte) 0);
                UILabel lblName = new UILabel(4, 172, 0, 0, "#键输入昵称", 15718814, (byte) 0, (byte) 0);
                texts[0] = new UIText(lblName.positionX + lblName.width - 22, lblName.positionY - 2, 90, 0, 6, (byte) 0, "");
                baseForm.addComponentInCenter(labels[0], (byte) 2);
                baseForm.addComponentInCenter(lblConfirm, (byte) 5);
                baseForm.addComponentInCenter(lblCancel, (byte) 6);
                baseForm.addComponent(lblName);
                baseForm.addComponent(texts[0]);
                baseForm.setFocus(true);
            }
            baseForm.draw(g);
            UIGameRun.getInstance().drawCreat(g, manX, manY, isSelect, 8, imgPlayerId, isDraw);
            g.setClip((rims[7]).positionX - 2, (rims[7]).positionY - 19, 37, 17);
            mImgUI[4].draw(g, (rims[7]).positionX - 3, (rims[7]).positionY - 19, 20, 0);
            g.setClip((rims[2]).positionX + 4 + (rims[2]).width - 38, (rims[2]).positionY - 19, 37, 17);
            mImgUI[4].draw(g, (rims[2]).positionX + 4 + (rims[2]).width - 38, (rims[2]).positionY - 19, 20, 8192);
            g.setClip((rims[6]).positionX - 2, (rims[6]).positionY + (rims[6]).height + 3, 37, 17);
            mImgUI[4].draw(g, (rims[6]).positionX - 3, (rims[6]).positionY + (rims[6]).height + 2, 20, 16384);
            g.setClip((rims[3]).positionX + 4 + (rims[3]).width - 38, (rims[3]).positionY + (rims[3]).height + 3, 37, 17);
            mImgUI[4].draw(g, (rims[3]).positionX + 4 + (rims[3]).width - 38, (rims[3]).positionY + (rims[3]).height + 2, 20, 180);
            if (baseForm.getSubForm() != null) {
                UIRim.drawRim(g, (baseForm.getSubForm()).positionX, (baseForm.getSubForm()).positionY, (baseForm.getSubForm()).width, (baseForm.getSubForm()).height, (byte) 2);
                baseForm.getSubForm().draw(g);
            }
        } else if (logon3_state == 0) {
            drawErrorInfor(g, "连接游戏服务器失败,请再试", "确定");
        } else if (logon3_state == 2) {
            drawErrorInfor(g, "用户已经在线,请稍候再登录", "确定");
        } else if (logon3_state == 3) {
            drawErrorInfor(g, "该用户已经存在，添加失败，请重新登录！", "确定");
        } else if (logon3_state == 4) {
            drawErrorInfor(g, "删除用户失败，服务器错误，请重新登录！", "确定");
        }
    }

    private void drawRightMenuWait(Graphics g) {
        drawGroundback(g);
        g.setColor(15718815);
        if ((waitCnt & 0xF) == 15);
        waitCnt++;
        if (waitCnt >= 400) {
            waitCnt = 0;
            setRightMenuSubState(oldGameState_rightMenuSubState);
        } else if (waitCnt >= 380) {
            g.drawString("载入超时,请重试", screenW >> 1, (screenH >> 1) - 20, 17);
        } else {
            g.drawString("载入中,请稍候...", screenW >> 1, (screenH >> 1) - 20, 17);
        }
    }

    private void drawLeftMenuWait(Graphics g) {
        drawGroundback(g);
        g.setColor(15718815);
        if ((waitCnt & 0xF) == 15);
        waitCnt++;
        if (waitCnt >= 400) {
            waitCnt = 0;
            setLeftMenuSubState(oldGameState_leftMenuSubState);
        } else if (waitCnt >= 380) {
            g.drawString("载入超时,请重试", screenW >> 1, (screenH >> 1) - 20, 17);
        } else {
            g.drawString("载入中,请稍候...", screenW >> 1, (screenH >> 1) - 20, 17);
        }
    }

    private void drawUIPetWait(Graphics g) {
        drawGroundback(g);
        g.setColor(15718815);
        if ((waitCnt & 0xF) == 15);
        waitCnt++;
        if (waitCnt >= 400) {
            waitCnt = 0;
            setUIPetState(oldGameState_menuPetState);
        } else if (waitCnt >= 380) {
            g.drawString("载入超时,请重试", screenW >> 1, (screenH >> 1) - 20, 17);
        } else {
            g.drawString("载入中,请稍候...", screenW >> 1, (screenH >> 1) - 20, 17);
        }
    }

    private void drawWait(Graphics g) {
        drawGroundback(g);
        g.setColor(15718815);
        if ((waitCnt & 0xF) == 15);
        waitCnt++;
        if (waitCnt >= 400) {
            waitCnt = 0;
            switch (oldState) {
                case 11:
                    if (loseMark == 2) {
                        loseLog(loseMark);
                        loseMark = 0;
                    }
                    setState((byte) 11);
                    break;
                case 12:
                    setState((byte) 12);
                    break;
            }
        } else if (waitCnt >= 380) {
            g.drawString("载入超时，请重试", screenW >> 1, (screenH >> 1) - 20, 17);
        } else {
            StringBuffer sb = new StringBuffer("载入中，请稍候.");
            for (int i = 0; i < waitCnt % 12 / 4; i++) {
                sb.append('.');
            }
            g.drawString(sb.toString(), (screenW >> 1) - 85, (screenH >> 1) - 20, 20);
        }
    }
    public static String fidCode = "1000";
    byte[] getURLConnect;
    public static String[][] serverListUrl;

    String getURL(boolean isCmobile, byte mark) {
        String url = "";
        if (isCmobile) {
            if (mark == 5) {
                url = "/judgeserver/judge?cmd=k&game=tianjie&subgame=tianjie1&from=" + jarFrom + "&userId=" + userID + "&key=" + userKey + "&cver=" + VerString(Cons.VER) + "&jar=" + "S60.jar";
            } else {
                url = "/passport?ver=1&cmd=" + ((mark == 0) ? "r" : "l") + "&usr=" + userName + "&pwd=" + userPassword + "&game=tianjie&subgame=" + "tianjie1" + "&from=" + jarFrom + "&userId=" + userID + "&key=" + userKey + "&cver=" + VerString(Cons.VER) + "&jar=" + "S60.jar";
            }
        } else {
            switch (mark) {
                case 0:
                    url = Cons.URL_KONG_IP2 + "usr=" + userName + "&pwd=" + userPassword + "&cmd=r&game=tianjie&subgame=" + "tianjie1" + "&from=" + jarFrom + "&cver=" + VerString(Cons.VER) + "&jar=" + "S60.jar" + "&imei=" + imei + "&charged=" + ((PCIncrementService.sendCount == 3) ? chargedByte : 0) + "&istest=" + isTestNum + "&reuid=" + repassportid;
                    break;
                case 1:
                    url = Cons.URL_KONG_IP2 + "usr=" + userName + "&pwd=" + userPassword + "&cmd=l&game=tianjie&subgame=" + "tianjie1" + "&from=" + jarFrom + "&cver=" + VerString(Cons.VER) + "&jar=" + "S60.jar" + "&imei=" + imei + "&charged=" + ((PCIncrementService.sendCount == 3) ? chargedByte : 0) + "&istest=" + isTestNum;
                    break;
                case 2:
                    url = "/reportip/r.jsp?usr=" + userName + "&game=tianjie&from=" + jarFrom + "&cver=" + VerString(Cons.VER) + "&jar=" + "S60.jar" + "&dest=";
                    break;
                case 3:
                    url = "/mammoth/judgeserver/activity/gprs?userid=";
                    break;
                case 4:
                    url = "/mammoth/judgeserver/activity/gprscallback?userid=";
                    break;
                case 5:
                    url = Cons.URL_KONG_IP2 + "cmd=k&game=tianjie&subgame=" + "tianjie1" + "&from=" + jarFrom + "&cver=" + VerString(Cons.VER) + "&jar=" + "S60.jar" + "&imei=" + imei + "&reuid=" + repassportid;
                    break;
                case 6:
                    url = Cons.URL_KONG_IP2 + "cmd=s&game=tianjie";
                    break;
            }
        }
        return url;
    }

    void ADUrlConnect(int connectType) {
    }
    public static byte selectServerID = 0;

    public void processServerList(String serverList) {
        PCLogin.serverListArr = Util.split(serverList, serverList.length(), ',');
        PCLogin.serverListCnt = (byte) (PCLogin.serverListArr.length / 4);
        serverListUrl = new String[PCLogin.serverListCnt][4];
        for (int i = 0; i < PCLogin.serverListCnt; i++) {
            serverListUrl[i][0] = PCLogin.serverListArr[i * 4];
            serverListUrl[i][1] = PCLogin.serverListArr[i * 4 + 1];
            serverListUrl[i][2] = PCLogin.serverListArr[i * 4 + 2];
            serverListUrl[i][3] = PCLogin.serverListArr[i * 4 + 3];
        }
    }

    private void drawIfBackToMainMenu(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setStyle((byte) 0);
            baseForm.setBackGround((byte) 8);
            labels[0] = new UILabel(0, 52, 105, 0, "是否确认退出并返回主菜单", 15719326, (byte) 1, (byte) 0);
            labels[1] = new UILabel(0, 0, 0, 0, "是", 15719326, (byte) 1, (byte) 0);
            labels[2] = new UILabel(0, 0, 0, 0, "否", 15719326, (byte) 1, (byte) 0);
            rims[0] = new UIRim(0, 40, 120, 50, (byte) 4);
            baseForm.addComponentInCenter(rims[0], (byte) 2);
            baseForm.addComponentInCenter(labels[0], (byte) 2);
            baseForm.addComponentInCenter(labels[1], (byte) 5);
            baseForm.addComponentInCenter(labels[2], (byte) 6);
        }
        baseForm.draw(g);
    }

    public void keyInInitgame() {
        if (isKeyPress(14)) {
            PCIncrementService.sendMark = 0;
            try {
                Cons.zeroShort = 0;
                Cons.showOtherPlayer = true;
                Cons.showName = true;
                Cons.showTeamMate = true;
                Cons.showSmallMap = true;
                Cons.showNum = true;
                Cons.showSpecial = true;
                Cons.showShortCut = true;
                Cons.showExpBar = true;
                Cons.newPlayerHelp = true;
                byte[] data = Util.readRecord((Player.getInstance()).name + "op");
                if (data != null && data.length == Cons.STR_CHAT_OPERATION.length) {
                    Cons.showOtherPlayer = (data[0] == 0);
                    Cons.showName = (data[1] == 0);
                    Cons.newPlayerHelp = (data[2] == 0);
                }
            } catch (Exception exception) {
            }
            sendTick = 0;
            int offsetX = 88;
            int offsetY = 110;
            int[][] cgStarPosition = {{822 - offsetX - 200, 140 - offsetY - 40}, {764 - offsetX - 360, 133 - offsetY + 120}, {655 - offsetX + 480, 320 - offsetY}, {636 - offsetX - 120, 387 - offsetY + 40}, {243 - offsetX + 480, 339 - offsetY - 240}, {284 - offsetX + 480, 336 - offsetY + 240}, {650 - offsetX + 480, 588 - offsetY - 240}, {690 - offsetX + 480, 600 - offsetY - 240}};
            System.gc();
            if (isFirst[choose_manID]) {
                Map.currentWindowX = cgStarPosition[(Player.getInstance()).originalImgID][0];
                Map.currentWindowY = cgStarPosition[(Player.getInstance()).originalImgID][1];
                isFirst[choose_manID] = false;
                Map.isDrawPlaceName = true;
                setState((byte) 5);
                setGameState((byte) 0);
            } else {
                Map.isDrawPlaceName = true;
                setState((byte) 5);
                setGameState((byte) 0);
            }
            stratGameForm();
        }
    }

    void stratGameForm() {
        if (payChangeSerser == 1) {
            PCIncrementService.chargeWhere = 8;
            PCIncrementService.sendMommoth();
            PCIncrementService.setState((byte) 17);
            setGameState((byte) 10);
        } else if (isMonthly) {
            loginRewardUesrId();
            isMonthly = false;
            PCIncrementService.setState((byte) 18);
            setGameState((byte) 10);
        } else {
            if (!HttpConn.getBindDetail() && !Cons.isCmobile) {
                if (!Player.getInstance().isDead() && bindPopState < 2 && Cons.newPlayerHelp) {
                    setPop((byte) 5);
                    bindPopState = 1;
                    return;
                }
            }
            if (popRecord[0] == 0 && !Player.getInstance().isDead() && Cons.newPlayerHelp) {
                setPop((byte) 0);
                bindPopState = 2;
            } else if (isHaveFinishedTask && !Player.getInstance().isDead() && Cons.newPlayerHelp && (Player.getInstance()).level <= 15) {
                setPop((byte) 7);
                bindPopState = 4;
            }
        }
    }

    private void keyInBackToMainMenu() {
        if (isKeyPress(17) || isKeyPress(14)) {
            setState((byte) 4);
            mainItemID = 0;
            closeConnection();
        } else if (isKeyPress(18)) {
            setState((byte) 12);
            releaseUI();
        }
    }

    private void drawLogon(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setStyle((byte) 0);
            UIRim bigRim = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            rims[0] = new UIRim(13, 42, 156, 121, (byte) 2);
            String ss = "";
            String ss1 = "";
            if (!"".equals(localUN) && !isUserRMS) {
                ss = localUN;
                ss1 = localUP;
            } else if (tempName[0] != null) {
                ss = tempName[0];
                ss1 = tempName[1];
            }
            texts[0] = new UIText(59, 76, 92, 0, 16, (byte) 0, ss);
            texts[1] = new UIText(59, 102, 92, 0, 16, (byte) 1, ss1);
            buttons[0] = new UIButton(21, 129, 0, 0, "新用户注册", (byte) 0);
            buttons[1] = new UIButton(104, 129, 0, 0, "快速注册", (byte) 0);
            buttons[1].setAroundComponent(texts[1], (byte) 1);
            buttons[0].setAroundComponent(buttons[1], (byte) 4);
            baseForm.addComponent(buttons[1]);
            UILabel label1 = new UILabel(0, 0, 0, 0, "确定", 15718815, (byte) 0, (byte) 0);
            UILabel label2 = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 0, (byte) 0);
            labels[0] = new UILabel(10, 10, 0, 0, "空中网游登录", 15718815, (byte) 1, (byte) 1);
            labels[1] = new UILabel(-2, 50, 0, 0, "请输入帐号和密码", 15718815, (byte) 1, (byte) 0);
            labels[2] = new UILabel(24, 78, 40, 17, "帐号：", 15718815, (byte) 0, (byte) 0);
            labels[3] = new UILabel(24, 104, 40, 17, "密码：", 15718815, (byte) 0, (byte) 0);
            labels[4] = new UILabel(0, screenH - 42, 0, 0, "原空中猛犸帐号", 15718815, (byte) 1, (byte) 0);
            labels[5] = new UILabel(0, screenH - 29, 0, 0, "可直接登录", 15718815, (byte) 1, (byte) 0);
            texts[0].setAroundComponent(texts[1], (byte) 2);
            buttons[0].setAroundComponent(texts[1], (byte) 1);
            baseForm.addComponent(bigRim);
            baseForm.addComponentInCenter(rims[0], (byte) 2);
            baseForm.addComponent(texts[0]);
            baseForm.addComponent(texts[1]);
            baseForm.addComponent(buttons[0]);
            baseForm.addComponentInCenter(label1, (byte) 5);
            baseForm.addComponentInCenter(label2, (byte) 6);
            baseForm.addComponentInCenter(labels[0], (byte) 2);
            baseForm.addComponentInCenter(labels[1], (byte) 2);
            baseForm.addComponent(labels[2]);
            baseForm.addComponent(labels[3]);
            baseForm.addComponentInCenter(labels[4], (byte) 2);
            baseForm.addComponentInCenter(labels[5], (byte) 2);
            baseForm.setFocus(true);
            baseForm.setComponentFocus(texts[0]);
            if (isQuickLog) {
                quickLogin();
                isQuickLog = false;
            }
        }
        baseForm.draw(g);
    }

    private void drawLogin(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setStyle((byte) 0);
            UIRim borderRim = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            int offseX = 15;
            baseForm.addComponent(borderRim);
            rims[0] = new UIRim(13, 42, 158, 116 + offseX, (byte) 2);
            texts[0] = new UIText(71, 97 + offseX, 90, 0, 16, (byte) 0, "");
            texts[1] = new UIText(71, 128 + offseX, 90, 0, 16, (byte) 0, "");
            labels[0] = new UILabel(10, 15, 0, 20, "空中网游注册", 15718815, (byte) 1, (byte) 1);
            labels[1] = new UILabel(0, 50, 132, 20, "请输入您所希望的帐号和密码,长度请控制在6~16位", 15718815, (byte) 1, (byte) 0);
            int x = 12;
            int w = 0;
            labels[2] = new UILabel(x, 98 + offseX, w, 17, "帐    号：", 15718815, (byte) 0, (byte) 0);
            labels[3] = new UILabel(x, 130 + offseX, w, 17, "密    码：", 15718815, (byte) 0, (byte) 0);
            UILabel label1 = new UILabel(0, 0, 0, 0, "注册", 15718815, (byte) 0, (byte) 0);
            UILabel label2 = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 0, (byte) 0);
            texts[0].setAroundComponent(texts[1], (byte) 2);
            baseForm.addComponentInCenter(rims[0], (byte) 2);
            baseForm.addComponent(texts[0]);
            baseForm.addComponent(texts[1]);
            baseForm.addComponentInCenter(label1, (byte) 5);
            baseForm.addComponentInCenter(label2, (byte) 6);
            baseForm.addComponentInCenter(labels[0], (byte) 2);
            baseForm.addComponentInCenter(labels[1], (byte) 2);
            baseForm.addComponent(labels[2]);
            baseForm.addComponent(labels[3]);
            baseForm.setFocus(true);
        }
        baseForm.draw(g);
    }

    private void drawChangePassWord(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setStyle((byte) 0);
            UIRim bigRim = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            baseForm.addComponent(bigRim);
            rims[0] = new UIRim(13, 42, 158, 116, (byte) 2);
            texts[0] = new UIText(71, 87, 90, 0, 16, (byte) 1, "");
            texts[1] = new UIText(71, 108, 90, 0, 16, (byte) 1, "");
            texts[2] = new UIText(71, 128, 90, 0, 16, (byte) 1, "");
            labels[0] = new UILabel(0, 10, 0, 0, "修改密码", 15718815, (byte) 1, (byte) 1);
            labels[1] = new UILabel(0, 50, 132, 20, "请输入您的密码", 15718815, (byte) 1, (byte) 0);
            int x = 12;
            int w = 0;
            labels[2] = new UILabel(x, 88, w, 17, "旧密码：", 15718815, (byte) 0, (byte) 0);
            labels[3] = new UILabel(x, 110, w, 17, "新密码：", 15718815, (byte) 0, (byte) 0);
            labels[4] = new UILabel(x, 130, w, 17, "密码确认：", 15718815, (byte) 0, (byte) 0);
            UILabel label1 = new UILabel(0, 0, 0, 0, "修改", 15718815, (byte) 0, (byte) 0);
            UILabel label2 = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 0, (byte) 0);
            texts[0].setAroundComponent(texts[1], (byte) 2);
            texts[1].setAroundComponent(texts[2], (byte) 2);
            baseForm.addComponentInCenter(rims[0], (byte) 2);
            baseForm.addComponent(texts[0]);
            baseForm.addComponent(texts[1]);
            baseForm.addComponent(texts[2]);
            baseForm.addComponentInCenter(label1, (byte) 5);
            baseForm.addComponentInCenter(label2, (byte) 6);
            baseForm.addComponentInCenter(labels[0], (byte) 2);
            baseForm.addComponentInCenter(labels[1], (byte) 2);
            baseForm.addComponent(labels[2]);
            baseForm.addComponent(labels[3]);
            baseForm.addComponent(labels[4]);
            baseForm.setFocus(true);
        }
        baseForm.draw(g);
    }

    private void drawLoadInitGame(Graphics g) {
        httpConn = null;
        System.gc();
        drawGroundback(g);
        UIRim.drawRim(g, 0, 0, screenW - 2, screenH - 2, (byte) 4);
        g.setColor(16776960);
        g.drawString("公　　告", screenW >> 1, 10, 17);
        g.setColor(15718815);
        String[] strs = null;
        strs = Util.wrapText(Announce, screenW - 30, font[1]);
        byte lg = (byte) strs.length;
        byte i;
        for (i = 0; i < lg; i = (byte) (i + 1)) {
            g.drawString(strs[i], 15, 34 + i * (CHARH + 5), 20);
        }
        g.setColor(16776960);
        g.drawString("按中间键进入游戏", screenW >> 1, screenH - 22, 17);
    }

    private void keyInLogon() {
        if (baseForm == null) {
            return;
        }
        UIComponent cmd = baseForm.getCommand();
        if (isKeyPress(17) || isKeyPress(14)) {
            if (sound != null) {
                sound.stopAllSound();
            }
            if (baseForm.getCurrentFocusForm() == baseForm) {
                if (cmd == buttons[0]) {
                    setState((byte) 14);
                    releaseUI();
                } else if (cmd == buttons[1]) {
                    quickLogin();
                } else if (texts[0].getLabel().trim().length() == 0) {
                    setMessage(baseForm, "用户名不能为空");
                } else if (texts[1].getLabel().trim().length() == 0) {
                    setMessage(baseForm, "密码不能为空");
                } else {
                    tempName[0] = userName = texts[0].getLabel().trim();
                    tempName[1] = userPassword = texts[1].getLabel().trim();
                    Util.saveRecord(tempName, "userNW");
                    Logon();
                }
            } else if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
                if (httpConn != null && httpConn.validateType == 11) {
                    setState((byte) 4);
                }
            } else if ("err".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
                if (httpConn.validateType == 11) {
                    setState((byte) 4);
                }
            } else if ("log".equals(baseForm.getCurrentFocusForm().getName())) {
                tempName[0] = userName = quickName;
                tempName[1] = userPassword = quickPSW;
                Util.saveRecord(tempName, "userNW");
                Logon();
            }
        } else if (isKeyPress(18)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                releaseUI();
                setState((byte) 4);
            } else if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("infor".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            }
        } else if (actionInForm(cmd)) {
        }
    }

    public void loseLog(byte mark) {
        repaint();
        serviceRepaints();
        httpConn = null;
        if (!Cons.isCmobile) {
            String s = "";
            switch (mark) {
                case 1:
                    s = "passport";
                    break;
                case 2:
                    s = serverListUrl[selectServerID][3];
                    break;
            }
        }
    }

    void Logon() {
        if (!isUserRMS) {
            byte[] data = new byte[1];
            isUserRMS = true;
            data[0] = 1;
            Util.saveRecord(data, "localuser");
        }
        baseForm.addAboutForm("waiting", "等待认证中...", (byte) 0, 160, 0);
        repaint();
        serviceRepaints();
        httpConn = null;
        if (!Cons.isCmobile) {
            loseMark = 1;
            kong_url = getURL(false, (byte) 1);
            httpConn = new HttpConn(Cons.URL_KONG_IP, kong_url, Cons.cmwap, 1, 2);
        }
        httpConn.start();
    }

    void Login() {
        if (baseForm != null) {
            baseForm.addAboutForm("waiting", "等待认证中...", (byte) 0, 160, 0);
        }
        repaint();
        serviceRepaints();
        httpConn = null;
        if (!Cons.isCmobile) {
            kong_url = getURL(false, (byte) 0);
            httpConn = new HttpConn(Cons.URL_KONG_IP, kong_url, Cons.cmwap, 0, 2);
        }
        httpConn.start();
    }

    void quickLogin() {
        if (baseForm != null) {
            baseForm.addAboutForm("waiting", "等待认证中...", (byte) 0, 160, 0);
        }
        repaint();
        serviceRepaints();
        httpConn = null;
        if (!Cons.isCmobile) {
            kong_url = getURL(false, (byte) 5);
            httpConn = new HttpConn(Cons.URL_KONG_IP, kong_url, Cons.cmwap, 11, 2);
        }
        if (isTestNum == 1) {
            baseForm.addAboutForm("err", "测试人员不能注册", (byte) 1, 160, 0);
            return;
        }
        httpConn.start();
    }

    private void keyInLogin() {
        if (baseForm == null) {
            return;
        }
        UIComponent cmd = baseForm.getCommand();
        if (isKeyPress(17) || isKeyPress(14)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                if (texts[0].getLabel().trim().equals("") || texts[1].getLabel().trim().equals("") || (getState() == 22 && texts[2].getLabel().trim().equals(""))) {
                    setMessage(baseForm, "请输入完整信息");
                } else if (getState() == 22 && !texts[1].getLabel().trim().equals(texts[2].getLabel().trim())) {
                    setMessage(baseForm, "两次输入的密码不一致");
                } else if (getState() == 14) {
                    if (isTestNum == 1) {
                        setMessage(baseForm, "测试人员不能注册");
                        return;
                    }
                    baseForm.addAboutForm("con", "请确定要按输入信息注册", (byte) 2, 160, 0);
                } else {
                    baseForm.addAboutForm("waiting", "等待认证中...", (byte) 0, 160, 0);
                    repaint();
                    serviceRepaints();
                    userPassword = texts[0].getLabel().trim();
                    String topd = texts[1].getLabel().trim();
                    httpConn = null;
                    if (!Cons.isCmobile) {
                        kong_url = Cons.URL_KONG_IP2 + "usr=" + userName + "&pwd=" + userPassword + "&topd=" + topd + "&cmd=c";
                        httpConn = new HttpConn(Cons.URL_KONG_IP, kong_url, Cons.cmwap, 2, 2);
                    }
                    httpConn.start();
                }
            } else if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("succeed".equals(baseForm.getCurrentFocusForm().getName())) {
                tempName[0] = userName;
                tempName[1] = userPassword;
                Util.saveRecord(tempName, "userNW");
                Logon();
            } else if ("succeed1".equals(baseForm.getCurrentFocusForm().getName())) {
                setState((byte) 4);
                releaseUI();
            } else if ("succeed2".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
                userName = texts[0].getLabel().trim();
                userPassword = texts[1].getLabel().trim();
            } else if ("err".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("con".equals(baseForm.getCurrentFocusForm().getName())) {
                userName = texts[0].getLabel().trim();
                userPassword = texts[1].getLabel().trim();
                Login();
            }
        } else if (isKeyPress(18)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                if (oldState == 10) {
                    setState((byte) 10);
                } else if (getState() == 22) {
                    setState((byte) 11);
                } else {
                    setState((byte) 4);
                }
                releaseUI();
            } else if ("con".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            }
        } else if (actionInForm(cmd)) {
        }
    }

    public int processServerDiv(String serverList) {
        PCLogin.serverDivArr = Util.split(serverList, serverList.length(), ';');
        int num = PCLogin.serverDivArr.length;
        PCLogin.serverDivNameList = new String[num];
        for (int i = 0; i < num; i++) {
            serverListUrl = (String[][]) null;
            processServerList(PCLogin.serverDivArr[i]);
            PCLogin.serverDivNameList[i] = serverListUrl[0][0];
        }
        return num;
    }
    public static byte loseMark = 0;
    boolean hasFirst;
    private int mainHelpIndex;
    byte taskType;

    private void keyInSelectServer() {
        if (baseForm == null) {
            return;
        }
        UIComponent cmd = baseForm.getCommand();
        if (cmd == null) {
            return;
        }
        if (isKeyPress(17) || isKeyPress(14)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                if (firstSelectedServerID != null && !firstSelectedServerID.trim().equals("")) {
                    processServerList(firstSelectedServerID);
                    hasFirst = true;
                } else {
                    hasFirst = false;
                }
                String tempStr = divTable.getCurentItem().toString().trim();
                if (hasFirst && tempStr.equals(serverListUrl[0][0].toString())) {
                    int id = 0;
                    serverUrl = "socket://" + serverListUrl[id][1] + ":" + serverListUrl[id][2];
                    selectServerID = (byte) id;
                    int findex = 0;
                    if (serverListUrl[id][0].indexOf("(") < serverListUrl[id][0].length()) {
                        findex = serverListUrl[id][0].indexOf("(");
                    } else if (serverListUrl[id][0].indexOf("（") < serverListUrl[id][0].length()) {
                        findex = serverListUrl[id][0].indexOf("（");
                    }
                    if (findex > 0) {
                        serverListUrl[id][0] = serverListUrl[id][0].substring(0, findex) + "(上次登录)";
                    } else {
                        serverListUrl[id][0] = serverListUrl[id][0] + "(上次登录)";
                    }
                    firstSelectedServerID = serverListUrl[id][0] + "," + serverListUrl[id][1] + "," + serverListUrl[id][2] + "," + serverListUrl[id][3];
                    byte[] data = Util.rmsString4Byte(firstSelectedServerID);
                    Util.rwDOC(false, data, "firstSelectedServer");
                    serverlist = serverListUrl[id][1] + ":" + serverListUrl[id][2] + ":" + serverListUrl[id][3];
                    if (HttpConn.servercontent != null && !"".equals(HttpConn.servercontent.trim())) {
                        processUpdateInfo(HttpConn.servercontent);
                        for (int t = 0; t < updateServer.length; t++) {
                            if (serverlist.equals(updateServer[t])) {
                                drawUpdateInfo(updateListInfo[t][1], updateListInfo[t][2]);
                                isUpdateInfo = true;
                                return;
                            }
                        }
                        if (!isUpdateInfo) {
                            loseMark = 2;
                            setState((byte) 18);
                            waitCnt = 0;
                            repaint();
                            serviceRepaints();
                            ni.send(16777472);
                            logon3_state = 0;
                        }
                    } else {
                        loseMark = 2;
                        setState((byte) 18);
                        waitCnt = 0;
                        repaint();
                        serviceRepaints();
                        ni.send(16777472);
                        logon3_state = 0;
                    }
                    return;
                }
                if (tempStr.equals("修改密码")) {
                    setState((byte) 22);
                    releaseUI();
                    return;
                }
                for (int i = 0; i < PCLogin.serverDivNameList.length; i++) {
                    if (tempStr.equals(PCLogin.serverDivNameList[i])) {
                        processServerList(PCLogin.serverDivArr[i]);
                        int arrLength = serverListUrl.length;
                        if (arrLength > 1) {
                            UIForm subForm = new UIForm(0, 0, screenW, screenH - 1, "next");
                            UILabel label1 = new UILabel(0, 0, 0, 0, "选择", 15718815, (byte) 0, (byte) 0);
                            UILabel label2 = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 0, (byte) 0);
                            subForm.setBackGround((byte) 9);
                            serverTable = new UITable(0, 31, 140, 158, arrLength - 1, 1, (arrLength - 1 > 6) ? 6 : (arrLength - 1), (byte) 1, (byte) 3);
                            serverTable.setAutoHeight(true);
                            int color = 10981736;
                            for (int ij = 1; ij < serverListUrl.length; ij++) {
                                serverTable.addItem(serverListUrl[ij][0], color);
                            }
                            subForm.addComponentInCenter(serverTable, (byte) 4);
                            serverTable.setCanFocus(true);
                            subForm.addComponentInCenter(label1, (byte) 5);
                            subForm.addComponentInCenter(label2, (byte) 6);
                            subForm.setFocus(true);
                            serverTable.isVisible = true;
                            baseForm.setAboutForm(subForm);
                        } else {
                            int id = 0;
                            serverUrl = "socket://" + serverListUrl[id][1] + ":" + serverListUrl[id][2];
                            selectServerID = (byte) id;
                            int findex = 0;
                            if (serverListUrl[id][0].indexOf("(") < serverListUrl[id][0].length()) {
                                findex = serverListUrl[id][0].indexOf("(");
                            } else if (serverListUrl[id][0].indexOf("（") < serverListUrl[id][0].length()) {
                                findex = serverListUrl[id][0].indexOf("（");
                            }
                            if (findex > 0) {
                                serverListUrl[id][0] = serverListUrl[id][0].substring(0, findex) + "(上次登录)";
                            } else {
                                serverListUrl[id][0] = serverListUrl[id][0] + "(上次登录)";
                            }
                            firstSelectedServerID = serverListUrl[id][0] + "," + serverListUrl[id][1] + "," + serverListUrl[id][2] + "," + serverListUrl[id][3];
                            byte[] data = Util.rmsString4Byte(firstSelectedServerID);
                            Util.rwDOC(false, data, "firstSelectedServer");
                            serverlist = serverListUrl[id][1] + ":" + serverListUrl[id][2] + ":" + serverListUrl[id][3];
                            if (HttpConn.servercontent != null && !"".equals(HttpConn.servercontent.trim())) {
                                processUpdateInfo(HttpConn.servercontent);
                                for (int t = 0; t < updateServer.length; t++) {
                                    if (serverlist.equals(updateServer[t])) {
                                        drawUpdateInfo(updateListInfo[t][1], updateListInfo[t][2]);
                                        if (hasFirst) {
                                            divTable.setItem(serverListUrl[id][0].toString(), 0, 10981736);
                                        }
                                        isUpdateInfo = true;
                                        return;
                                    }
                                }
                                if (!isUpdateInfo) {
                                    loseMark = 2;
                                    setState((byte) 18);
                                    waitCnt = 0;
                                    repaint();
                                    serviceRepaints();
                                    ni.send(16777472);
                                    logon3_state = 0;
                                }
                            } else {
                                loseMark = 2;
                                setState((byte) 18);
                                waitCnt = 0;
                                repaint();
                                serviceRepaints();
                                ni.send(16777472);
                                logon3_state = 0;
                            }
                            return;
                        }
                    }
                }
            } else if ("next".equals(baseForm.getCurrentFocusForm().getName())) {
                int id = serverTable.getCurrentPointer();
                id++;
                serverUrl = "socket://" + serverListUrl[id][1] + ":" + serverListUrl[id][2];
                selectServerID = (byte) id;
                int findex = 0;
                if (serverListUrl[id][0].indexOf("(") < serverListUrl[id][0].length()) {
                    findex = serverListUrl[id][0].indexOf("(");
                } else if (serverListUrl[id][0].indexOf("（") < serverListUrl[id][0].length()) {
                    findex = serverListUrl[id][0].indexOf("（");
                }
                if (findex > 0) {
                    serverListUrl[id][0] = serverListUrl[id][0].substring(0, findex) + "(上次登录)";
                } else {
                    serverListUrl[id][0] = serverListUrl[id][0] + "(上次登录)";
                }
                firstSelectedServerID = serverListUrl[id][0] + "," + serverListUrl[id][1] + "," + serverListUrl[id][2] + "," + serverListUrl[id][3];
                byte[] data = Util.rmsString4Byte(firstSelectedServerID);
                Util.rwDOC(false, data, "firstSelectedServer");
                serverlist = serverListUrl[id][1] + ":" + serverListUrl[id][2] + ":" + serverListUrl[id][3];
                if (HttpConn.servercontent != null && !"".equals(HttpConn.servercontent.trim())) {
                    processUpdateInfo(HttpConn.servercontent);
                    for (int t = 0; t < updateServer.length; t++) {
                        if (serverlist.equals(updateServer[t])) {
                            drawUpdateInfo(updateListInfo[t][1], updateListInfo[t][2]);
                            if (hasFirst) {
                                divTable.setItem(serverListUrl[id][0].toString(), 0, 10981736);
                            }
                            isUpdateInfo = true;
                            return;
                        }
                    }
                    if (!isUpdateInfo) {
                        loseMark = 2;
                        setState((byte) 18);
                        waitCnt = 0;
                        repaint();
                        serviceRepaints();
                        ni.send(16777472);
                        logon3_state = 0;
                    }
                } else {
                    loseMark = 2;
                    setState((byte) 18);
                    waitCnt = 0;
                    repaint();
                    serviceRepaints();
                    ni.send(16777472);
                    logon3_state = 0;
                }
                return;
            }
        } else if (isKeyPress(18)) {
            if (baseForm.getCurrentFocusForm() == baseForm) {
                serverUrl = "";
                closeConnection();
            } else if ("next".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.setAboutForm((UIForm) null);
            } else if ("update".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
                isUpdateInfo = false;
            }
        } else if (actionInForm(cmd)) {
        }
    }

    public void inputName() {
        commonForm = null;
        commonTextField = null;
        if (baseForm.getCurrentFocusForm() == baseForm) {
            if (commonOk == null) {
                commonOk = new Command("确定", 4, 2);
                commonBack = new Command("返回", 2, 2);
            }
            if (commonTextField == null) {
                commonTextField = new TextField("输入昵称,最多输入六个汉字或字母(包括数字)", texts[0].getLabel(), 6, 0);
            }
            if (commonForm == null) {
                commonForm = new Form("nameForm");
                commonForm.setTitle("输入昵称：");
                commonForm.addCommand(commonOk);
                commonForm.addCommand(commonBack);
                commonForm.append((Item) commonTextField);
                commonForm.append(Cons.STR_DESCRIPTION[Cons.PLAYERS[manIndex][1] - 1]);
                commonForm.setCommandListener(this);
            }
            if (aMidlet.display.getCurrent() != commonForm) {
                aMidlet.display.setCurrent((Displayable) commonForm);
            }
        }
    }

    public void inputName(String mgs) {
        commonForm = null;
        commonTextField = null;
        if (baseForm.getCurrentFocusForm() == baseForm) {
            if (commonOk == null) {
                commonOk = new Command("确定", 4, 2);
                commonBack = new Command("返回", 2, 2);
            }
            if (commonTextField == null) {
                String nameStr = playerNames[choose_manID];
                if (nameStr.length() > 6) {
                    nameStr = playerNames[choose_manID].substring(0, 6);
                }
                commonTextField = new TextField("输入昵称,最多输入六个汉字或字母(包括数字)", nameStr, 6, 0);
            }
            if (commonForm == null) {
                commonForm = new Form("nameForm");
                commonForm.setTitle("输入昵称：");
                commonForm.addCommand(commonOk);
                commonForm.addCommand(commonBack);
                commonForm.append((Item) commonTextField);
                commonForm.append(mgs);
                commonForm.setCommandListener(this);
            }
            if (aMidlet.display.getCurrent() != commonForm) {
                aMidlet.display.setCurrent((Displayable) commonForm);
            }
        }
    }

    private void keyInCreatMan() {
        if (logon3_state == 1) {
            if (baseForm == null) {
                return;
            }
            UIComponent cmd = baseForm.getCommand();
            if (isKeyPress(17) || isKeyPress(14)) {
                if (baseForm.getCurrentFocusForm() == baseForm) {
                    if (texts[0].getLabel().trim().length() > 0) {
                        playerAdd = new byte[4];
                        playerAdd[0] = 1;
                        playerAdd[1] = Cons.PLAYERS[manIndex][2];
                        playerAdd[2] = Cons.PLAYERS[manIndex][0];
                        playerAdd[3] = Cons.PLAYERS[manIndex][1];
                        playerAddName = texts[0].getLabel();
                        ni.send(16777984);
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, 145, 92);
                        baseForm.getSubForm().setFormXY((screenW - 95 - 70) / 2, (screenH - 82 - 10) / 2);
                        waitCnt = 0;
                    } else {
                        inputName();
                    }
                } else if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                    baseForm.setAboutForm((UIForm) null);
                }
            } else if (isKeyPress(16)) {
                inputName();
            } else if (isKeyPress(18)) {
                if (baseForm.getCurrentFocusForm() == baseForm) {
                    for (byte j = 0; j < 8; j = (byte) (j + 1)) {
                        for (byte i = 0; i < 3; i = (byte) (i + 1)) {
                            if (playerInfos[i][2] == Cons.PLAYERS[j][0] && playerInfos[i][3] == Cons.PLAYERS[j][1]) {
                                imgPlayerId[i] = j;
                            }
                        }
                    }
                    setState((byte) 12);
                    releaseUI();
                } else if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                    baseForm.setAboutForm((UIForm) null);
                }
            } else if (actionInForm(cmd)) {
                if (baseForm.focusComponent instanceof UIRim) {
                    String[] detail = {"阵营：" + Cons.STR_CAMP[0] + "\n种族：" + Cons.STR_RACE[0] + "\n职业：" + Cons.STR_PROFESSION[3] + "\n性别：" + Cons.STR_GENDER[0], "阵营：" + Cons.STR_CAMP[0] + "\n种族：" + Cons.STR_RACE[0] + "\n职业：" + Cons.STR_PROFESSION[0] + "\n性别：" + Cons.STR_GENDER[1], "阵营：" + Cons.STR_CAMP[0] + "\n种族：" + Cons.STR_RACE[1] + "\n职业：" + Cons.STR_PROFESSION[1] + "\n性别：" + Cons.STR_GENDER[0], "阵营：" + Cons.STR_CAMP[0] + "\n种族：" + Cons.STR_RACE[1] + "\n职业：" + Cons.STR_PROFESSION[2] + "\n性别：" + Cons.STR_GENDER[1], "阵营：" + Cons.STR_CAMP[1] + "\n种族：" + Cons.STR_RACE[2] + "\n职业：" + Cons.STR_PROFESSION[2] + "\n性别：" + Cons.STR_GENDER[0], "阵营：" + Cons.STR_CAMP[1] + "\n种族：" + Cons.STR_RACE[2] + "\n职业：" + Cons.STR_PROFESSION[3] + "\n性别：" + Cons.STR_GENDER[1], "阵营：" + Cons.STR_CAMP[1] + "\n种族：" + Cons.STR_RACE[3] + "\n职业：" + Cons.STR_PROFESSION[0] + "\n性别：" + Cons.STR_GENDER[0], "阵营：" + Cons.STR_CAMP[1] + "\n种族：" + Cons.STR_RACE[3] + "\n职业：" + Cons.STR_PROFESSION[1] + "\n性别：" + Cons.STR_GENDER[1]};
                    int index = -1;
                    for (int i = 0; i < detail.length; i++) {
                        if (baseForm.focusComponent == rims[i]) {
                            index = i;
                            break;
                        }
                    }
                    if (index < 0) {
                        return;
                    }
                    labels[0].setStr(detail[index]);
                }
            }
        } else if (logon3_state == 0 || logon3_state == 2 || logon3_state == 3 || logon3_state == 4) {
            if (isKeyPress(17) || isKeyPress(14)) {
                setState((byte) 4);
                mainItemID = 0;
            }
        }
    }

    private void keyInSelectMan() {
        if (logon3_state == 1) {
            if (baseForm == null) {
                return;
            }
            UIComponent cmd = baseForm.getCommand();
            if (isKeyPress(17) || isKeyPress(14)) {
                if (baseForm.getCurrentFocusForm() == baseForm) {
                    if (playerState[choose_manID]) {
                        releaseCreateRes();
                        shyTime[choose_manID] = -1;
                        ni.send(16777728);
                        setState((byte) 23);
                        loadCount = 0;
                        waitCnt = 0;
                        UIGameRun.chatVector.removeAllElements();
                        UIGameRun.chatColorVector.removeAllElements();
                        for (int i = 0; i < 20; i++) {
                            chatContent[i] = "";
                        }
                        PCChat.chatIDs.removeAllElements();
                        PCChat.chatPlayers.removeAllElements();
                        PCChat.addChatScreen((byte) 7, "欢迎来到天劫世界#0");
                        UIGameRun.releaseChat();
                        loginRewardUesrId();
                        oldRoleName = playerNames[choose_manID];
                    } else {
                        for (byte i = 0; i < 8; i = (byte) (i + 1)) {
                            imgPlayerId[i] = i;
                        }
                        manIndex = 0;
                        choose_manID = grids[0].getCurrentPointer();
                        releaseUI();
                        setState((byte) 15);
                    }
                } else if ("input".equals(baseForm.getCurrentFocusForm().getName())) {
                    if ("del".equals(texts[9].getLabel())) {
                        baseForm.setAboutForm((UIForm) null);
                        baseForm.addAboutForm("deldel", "删除后，昵称将不可用", (byte) 2, 152, 60);
                    } else {
                        baseForm.getSubForm().addAboutForm("msg", "输入不正确", (byte) 1, 150, 0);
                    }
                } else if ("deldel".equals(baseForm.getCurrentFocusForm().getName())) {
                    baseForm.setAboutForm((UIForm) null);
                    choose_manID = grids[0].getCurrentPointer();
                    baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                    ni.send(16778240);
                } else if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                    baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
                }
            } else if (isKeyPress(18)) {
                if (baseForm.getCurrentFocusForm() == baseForm) {
                    setState((byte) 19);
                    releaseUI();
                } else if ("input".equals(baseForm.getCurrentFocusForm().getName())) {
                    baseForm.setAboutForm((UIForm) null);
                } else if ("deldel".equals(baseForm.getCurrentFocusForm().getName())) {
                    baseForm.setAboutForm((UIForm) null);
                }
            } else if (isKeyPress(0)) {
                if (shyTime == null || shyTime[choose_manID] <= 0) {
                    if (baseForm.getCurrentFocusForm() == baseForm) {
                        if (playerState[choose_manID]) {
                            texts[9] = new UIText(0, 0, 92, 0, 6, (byte) 0, "");
                            baseForm.addInputForm("input", "请输入字母del确认", texts[9], 100);
                            baseForm.setFocusComponentFocus(true);
                        }
                    }
                }
            } else if (actionInForm(cmd)) {
                if (cmd instanceof UIGrid) {
                    int temp = grids[0].getCurrentPointer();
                    choose_manID = (byte) temp;
                    if (playerState[choose_manID]) {
                        StringBuffer sb = new StringBuffer();
                        sb.append("姓名：").append(playerNames[temp]);
                        labels[1].setStr(sb.toString());
                        sb.delete(0, sb.length());
                        sb.append("等级：").append(playerInformations[temp][0]);
                        labels[3].setStr(sb.toString());
                        sb.delete(0, sb.length());
                        sb.append("种族：").append(playerInformations[temp][2]);
                        labels[5].setStr(sb.toString());
                        sb.delete(0, sb.length());
                        sb.append("职业：").append(playerInformations[temp][3]);
                        labels[4].setStr(sb.toString());
                        sb.delete(0, sb.length());
                        sb.append("场景：").append(playerInformations[temp][1]);
                        labels[2].setStr(sb.toString());
                        sb.delete(0, sb.length());
                        if (shyTime != null && shyTime[choose_manID] > -1) {
                            int tmp = shyTime[choose_manID];
                            baseForm.removeComponent(labels[9]);
                            labels[9] = new UILabel(50, 170, 0, 0, "" + ((tmp > 0) ? (tmp + "天后可以删除") : "按0键永久删除角色"), 15587742, (byte) 3, (byte) 0);
                            baseForm.addComponentInCenter(labels[9], (byte) 2);
                            labels[9].setVisible(true);
                            labels[6].setVisible(false);
                        } else {
                            labels[6].setVisible(true);
                            labels[9].setVisible(false);
                        }
                    } else {
                        labels[9].setVisible(false);
                        labels[6].setVisible(false);
                    }
                }
            }
        } else if (logon3_state == 0 || logon3_state == 2 || logon3_state == 3 || logon3_state == 4 || logon3_state == 6 || logon3_state == 7) {
            if (isKeyPress(17) || isKeyPress(14)) {
                closeConnection();
                mainItemID = 0;
            }
        } else if (logon3_state == 5) {
            if (isKeyPress(17) || isKeyPress(14)) {
                mainItemID = 0;
                Download.gotoURL(aMidlet, (byte) 0);
            } else if (isKeyPress(18)) {
                closeConnection();
                setState((byte) 4);
                PCLogin.downloadAddress = null;
            }
        } else if (logon3_state == 8) {
            if (isKeyPress(17) || isKeyPress(14)) {
                mainItemID = 0;
                Download.gotoURL(aMidlet, (byte) 1);
            } else if (isKeyPress(18)) {
                closeConnection();
            }
        } else if (logon3_state == 9) {
            if (isKeyPress(17) || isKeyPress(14)) {
                mainItemID = 0;
                Download.gotoURL(aMidlet, (byte) 0);
            } else if (isKeyPress(18)) {
                setState((byte) 11);
                releaseUI();
                NetInterface.getInstance().closeConn();
            }
        }
    }

    private void showGame(Graphics g) {
        // 绘制地图
        Map.getInstance().draw(g);
        // 绘制技能释放动画
        SpecialManager.getInstance().draw(g);
        // 绘制技能释放进度
        Player.getInstance().drawSkillPre(g);
        // 绘制经验条、血量、聊天等
        UIGameRun.draw(g);
    }

    public void drawUIManAttribute(Graphics g) {
        if (baseForm != null) {
            baseForm.draw(g);
        }
    }

    public void drawUIManPackage(Graphics g) {
        if (baseForm == null) {
            Image img = Image.createImage(18, 30);
            Graphics gg = img.getGraphics();
            gg.setColor(0);
            gg.fillRect(0, 0, 18, 30);
            UIGameRun.getInstance().drawMenuPeople(gg, 9, 30, (Player.getInstance()).originalImgID);
            int[][] p = {{33, 10}, {33, 32}, {33, 54}, {123, 10}, {123, 32}, {123, 54}, {63, 54}, {93, 54}};
            byte[] ic = {0, 1, 2, 3, 4, 5, 6, 6};
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            rims[0] = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            rims[1] = new UIRim(0, 95, 166, 18, (byte) 7);
            UILabel label = new UILabel(15, 78, 0, 0, "金钱", 15718815, (byte) 0, (byte) 0);
            texts[0] = new UIText(0, 78, 67, 0, 9, (byte) 3, (Player.getInstance()).money + "");
            labels[0] = new UILabel(0, 0, 0, 0, "操作", 15718815, (byte) 0, (byte) 0);
            labels[1] = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 0, (byte) 0);
            stuffName = new UILabel(0, (rims[1]).positionY + 3, screenW, 0, dramatisPackage.getCurrentName(), Cons.STUFF_NAME_COLOR[dramatisPackage.getCurrentNameLevel()], (byte) 1, (byte) 0);
            byte b;
            for (b = 0; b < 8; b = (byte) (b + 1)) {
                mImages[b] = new UIMImage(p[b][0], p[b][1], 25, 25, mImgStuff, (byte) 0);
                mImages[b].setHaveRim(true);
                mImages[b].setBackVeins(mImgUI[26], ic[b]);
                (mImages[b]).index = b;
            }
            mImages[8] = new UIMImage(0, 13, 0, 0, new MImage(img), (byte) 0);
            mImages[0].setAroundComponent(mImages[3], (byte) 4);
            mImages[0].setAroundComponent(mImages[1], (byte) 2);
            mImages[3].setAroundComponent(mImages[4], (byte) 2);
            mImages[1].setAroundComponent(mImages[2], (byte) 2);
            mImages[4].setAroundComponent(mImages[5], (byte) 2);
            mImages[1].setAroundComponent(mImages[4], (byte) 4);
            mImages[2].setAroundComponent(mImages[6], (byte) 4);
            mImages[6].setAroundComponent(mImages[7], (byte) 4);
            mImages[7].setAroundComponent(mImages[5], (byte) 4);
            mImages[6].setAroundComponent(dramatisPackage, (byte) 2);
            mImages[7].setAroundComponent(dramatisPackage, (byte) 2);
            mImages[5].setAroundComponent(dramatisPackage, (byte) 2);
            mImages[2].setAroundComponent(dramatisPackage, (byte) 2);
            baseForm.addComponent(rims[0]);
            baseForm.addComponentInCenter(rims[1], (byte) 2);
            baseForm.addComponentInCenter(stuffName, (byte) 2);
            baseForm.addComponentInCenter(labels[0], (byte) 5);
            baseForm.addComponentInCenter(labels[1], (byte) 6);
            baseForm.addComponentInCenter(dramatisPackage, (byte) 2);
            baseForm.addComponentInCenter(texts[0], (byte) 2);
            baseForm.addComponent(label);
            for (int i = 0; i < 8; i++) {
                baseForm.addComponent(mImages[i]);
            }
            baseForm.addComponentInCenter(mImages[8], (byte) 2);
            labels[0].setXY((labels[0]).positionX + 4, (labels[0]).positionY);
            labels[1].setXY((labels[1]).positionX - 2, (labels[1]).positionY);
            baseForm.setFocus(true);
            baseForm.setComponentFocus(dramatisPackage);
            baseForm.setMessage(Cons.ROLL_MASSAGE[6], false);
        }
        baseForm.draw(g);
        if (Player.itemCount <= 0) {
            for (byte i = 35; i >= 0; i = (byte) (i - 1)) {
                dramatisPackage.setCanUse(i, true);
            }
        }
    }

    public void drawUIManSkill(Graphics g) {
        if (baseForm == null) {
            int[] SKILL_UI_P = {0, 41, 7, 7, 7};
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setStyle((byte) 0);
            baseForm.setBackGround((byte) 9);
            rims[0] = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            UILabel lblOperation = new UILabel(0, 0, 0, 0, "操作", 15718815, (byte) 0, (byte) 0);
            UILabel lblCancel = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 0, (byte) 0);
            UIRim rimTitle = new UIRim(8, 15, 94, 17, (byte) 1);
            labels[1] = new UILabel(12, 12, 0, 0, "剩余技能点数 ：" + tree.getSkillPoints() + "  ", 15718815, (byte) 0, (byte) 0);
            tree.setStyle((byte) 0);
            tree.positionX = 8;
            tree.positionY = SKILL_UI_P[(Player.getInstance()).profession];
            baseForm.addComponent(rims[0]);
            baseForm.addComponent(rimTitle);
            baseForm.addComponent(tree);
            String skillName = null;
            switch ((Player.getInstance()).profession) {
                case 1:
                    skillName = Cons.STR_SWORDMAN_SKILL[tree.getCurrSkillId()];
                    break;
                case 2:
                    skillName = Cons.STR_TAOIST_SKILL[tree.getCurrSkillId()];
                    break;
                case 3:
                    skillName = Cons.STR_APOTHECARY_SKILL[tree.getCurrSkillId()];
                    break;
                case 4:
                    skillName = Cons.STR_ASSASSIN_SKILL[tree.getCurrSkillId()];
                    break;
                default:
                    skillName = new String("");
                    break;
            }
            labels[0] = new UILabel(12, 18, 86, 0, skillName + tree.getCurrSkillLevel() + "/" + tree.getMaxSkillLevel() + "级", 15718815, (byte) 1, (byte) 0);
            baseForm.addComponentInCenter(lblOperation, (byte) 5);
            baseForm.addComponentInCenter(lblCancel, (byte) 6);
            baseForm.addComponent(labels[0]);
            baseForm.addComponentInCenter(labels[1], (byte) 6);
            (labels[1]).positionY -= 20;
            baseForm.setFocus(true);
            baseForm.setMessage(Cons.ROLL_MASSAGE[5], false);
        }
        baseForm.draw(g);
    }

    public void drawUIFriendLookup(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            UIRim frame = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            UIRim rimTitle = new UIRim(0, 13, 159, 17, (byte) 7);
            UILabel lblTitle = new UILabel(0, 16, 0, 0, "同场景玩家", 15718814, (byte) 1, (byte) 0);
            UIRim rimDown = new UIRim(0, 30, 160, 158, (byte) 0);
            int lg = PCFriend.tempFriendsList.size();
            friendList = new String[lg / 3][2];
            friendListID = new int[lg / 3];
            for (int i = 0; i < PCFriend.tempFriendsList.size(); i += 3) {
                friendListID[i / 3] = ((Integer) PCFriend.tempFriendsList.elementAt(i)).intValue();
                friendList[i / 3][0] = PCFriend.tempFriendsList.elementAt(i + 1).toString();
                friendList[i / 3][1] = PCFriend.tempFriendsList.elementAt(i + 2).toString();
            }
            PCFriend.tempFriendsList.removeAllElements();
            byte count = (byte) friendListID.length;
            tables[0] = new UITable(0, 31, 160, 0, count, 1, (count > 10) ? 10 : count, (byte) 0, (byte) 3);
            tables[0].setAutoHeight(true);
            for (int j = 0; j < count; j++) {
                tables[0].addItem(friendList[j][0] + "  " + friendList[j][1], 10981736);
            }
            UILabel lblOperation = new UILabel(0, 0, 0, 0, "添加好友", 15718814, (byte) 1, (byte) 0);
            UILabel lblCancel = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 1, (byte) 0);
            baseForm.addComponent(frame);
            baseForm.addComponentInCenter(rimDown, (byte) 2);
            baseForm.addComponentInCenter(rimTitle, (byte) 2);
            baseForm.addComponentInCenter(lblTitle, (byte) 2);
            baseForm.addComponentInCenter(lblOperation, (byte) 5);
            baseForm.addComponentInCenter(lblCancel, (byte) 6);
            baseForm.addComponentInCenter(tables[0], (byte) 2);
            tables[0].setXY((tables[0]).positionX - 1, (tables[0]).positionY);
            baseForm.setFocus(true);
        }
        baseForm.draw(g);
    }

    public void drawUIFriendList(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            UIRim frame = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            UIRim rimTitle = new UIRim(0, 13, 166, 17, (byte) 7);
            UILabel lblTitle = new UILabel(0, 16, 0, 0, "好友列表", 15718814, (byte) 1, (byte) 0);
            UIRim rimDown = new UIRim(0, 30, 166, 158, (byte) 0);
            byte count = (byte) friendListID.length;
            int color = 0;
            if (requestFriendPlace == 7) {
                tables[0] = new UITable(0, 32, 164, 158, count, 1, (count > 8) ? 8 : count, (byte) 0, (byte) 7);
            } else if (requestFriendPlace == 6 || requestFriendPlace == 8) {
                tables[0] = new UITable(0, 32, 164, 158, count, 1, (count > 10) ? 10 : count, (byte) 0, (byte) 7);
            } else {
                tables[0] = new UITable(0, 32, 164, 0, count, 1, (count > 10) ? 10 : count, (byte) 0, (byte) 3);
            }
            tables[0].setAutoHeight(true);
            for (int i = 0; i < count; i++) {
                if (friendList[i][1].equals("在线")) {
                    color = 10981736;
                } else {
                    color = 8092539;
                }
                if ("在线".equals(friendList[i][1])) {
                    if ("是".equals(friendList[i][8])) {
                        tables[0].addItem(friendList[i][0] + " * " + friendList[i][2], color);
                    } else {
                        tables[0].addItem(friendList[i][0] + "  " + friendList[i][2], color);
                    }
                } else {
                    tables[0].addItem(friendList[i][0] + "  " + friendList[i][1], color);
                }
            }
            UILabel lblOperation = new UILabel(0, 0, 0, 0, "操作", 15718814, (byte) 1, (byte) 0);
            UILabel lblCancel = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 1, (byte) 0);
            baseForm.addComponent(frame);
            baseForm.addComponentInCenter(rimDown, (byte) 2);
            baseForm.addComponentInCenter(rimTitle, (byte) 2);
            baseForm.addComponentInCenter(lblTitle, (byte) 2);
            baseForm.addComponentInCenter(lblOperation, (byte) 5);
            baseForm.addComponentInCenter(lblCancel, (byte) 6);
            baseForm.addComponentInCenter(tables[0], (byte) 2);
            if (requestFriendPlace == 7) {
                UILabel lblRecommand = new UILabel(5, 135, 162, 10, "  赠送给每个好友数量：", 15718814, (byte) 0, (byte) 0);
                texts[2] = new UIText(5, 155, 92, 0, 6, (byte) 2, "1");
                texts[2].setMaxNumber(20L);
                baseForm.addComponentInCenter(lblRecommand, (byte) 2);
                baseForm.addComponentInCenter(texts[2], (byte) 2);
            }
            tables[0].setXY((tables[0]).positionX - 1, (tables[0]).positionY);
            baseForm.setFocus(true);
        }
        baseForm.draw(g);
    }

    public void drawUIBlackList(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            UIRim frame = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            UIRim rimTitle = new UIRim(0, 13, 159, 17, (byte) 7);
            UILabel lblTitle = new UILabel(0, 16, 0, 0, "黑名单", 15718814, (byte) 1, (byte) 0);
            UIRim rimDown = new UIRim(0, 30, 160, 158, (byte) 0);
            byte count = (byte) blackListID.length;
            tables[0] = new UITable(0, 31, 160, 0, count, 1, (count > 10) ? 10 : count, (byte) 0, (byte) 3);
            tables[0].setAutoHeight(true);
            int color = 0;
            for (int i = 0; i < count; i++) {
                if (blackList[i][1].equals("在线")) {
                    color = 10981736;
                } else {
                    color = 8092539;
                }
                tables[0].addItem(blackList[i][0] + "  " + blackList[i][2], color);
            }
            UILabel lblOperation = new UILabel(0, 0, 0, 0, "操作", 15718814, (byte) 1, (byte) 0);
            UILabel lblCancel = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 1, (byte) 0);
            baseForm.addComponent(frame);
            baseForm.addComponentInCenter(rimDown, (byte) 2);
            baseForm.addComponentInCenter(rimTitle, (byte) 2);
            baseForm.addComponentInCenter(lblTitle, (byte) 2);
            baseForm.addComponentInCenter(lblOperation, (byte) 5);
            baseForm.addComponentInCenter(lblCancel, (byte) 6);
            baseForm.addComponentInCenter(tables[0], (byte) 2);
            tables[0].setXY((tables[0]).positionX - 1, (tables[0]).positionY);
            baseForm.setFocus(true);
        }
        baseForm.draw(g);
    }

    public void drawUIChat(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            UIRim frame = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            UILabel lblCancel = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 1, (byte) 0);
            UIRim rimTitle = new UIRim(0, 12, 160, 17, (byte) 7);
            UIRim rimDown = new UIRim(rimTitle.positionX, rimTitle.positionY + rimTitle.height, rimTitle.width, 160, (byte) 0);
            UILabel lblTitle = new UILabel(0, rimTitle.positionY + 3, 0, 0, "聊天记录", 15718815, (byte) 1, (byte) 0);
            tables[0] = new UITable(0, 34, 150, 150, 20, 1, 10, (byte) 0, (byte) 0);
            tables[0].setSingleWH((short) (byte) ((tables[0]).width / 2), (tables[0]).singleHeight, false);
            tables[0].addItems(chatContent, chatColor);
            baseForm.addComponent(frame);
            baseForm.addComponentInCenter(rimTitle, (byte) 2);
            baseForm.addComponentInCenter(lblTitle, (byte) 2);
            baseForm.addComponentInCenter(lblCancel, (byte) 6);
            baseForm.addComponentInCenter(rimDown, (byte) 2);
            baseForm.addComponentInCenter(tables[0], (byte) 2);
            baseForm.setFocus(true);
        }
        baseForm.draw(g);
    }

    public void drawUITask(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setStyle((byte) 0);
            baseForm.setBackGround((byte) 9);
            rims[0] = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            labels[0] = new UILabel(0, 10, 0, 0, "任务列表", 15718814, (byte) 1, (byte) 0);
            labels[1] = new UILabel(0, 0, 0, 0, "操作", 15718814, (byte) 0, (byte) 0);
            labels[2] = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 0, (byte) 0);
            baseForm.addComponent(rims[0]);
            baseForm.addComponentInCenter(labels[0], (byte) 2);
            baseForm.addComponentInCenter(labels[1], (byte) 5);
            baseForm.addComponentInCenter(labels[2], (byte) 6);
            baseForm.addComponentInCenter(tables[0], (byte) 2);
            baseForm.setFocus(true);
            baseForm.setComponentFocus(tables[0]);
        }
        baseForm.draw(g);
    }

    public void drawSpecialTool(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setStyle((byte) 0);
            rims[0] = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            rims[1] = new UIRim(5, 5, 164, 25, (byte) 2);
            labels[0] = new UILabel(60, 12, 112, 0, "特殊道具", 16316576, (byte) 1, (byte) 0);
            UILabel label1 = new UILabel(0, 0, 0, 0, "选择", 15718815, (byte) 0, (byte) 0);
            UILabel label2 = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 0, (byte) 0);
            String[] menu = {"充值", "查询明细", "购买道具"};
            menus[0] = new UIMenu(5, 32, 164, 98, null, menu);
            menus[0].setRimStyle((byte) 0);
            menus[0].setFlushType((byte) 1);
            textArea[0] = new UITextArea(5, 132, 164, 56, "您可以在这买到很好的装备");
            textArea[0].setColor(15849885);
            baseForm.addComponent(menus[0]);
            byte i;
            for (i = 0; i < 2; i = (byte) (i + 1)) {
                baseForm.addComponent(rims[i]);
            }
            baseForm.addComponentInCenter(labels[0], (byte) 2);
            baseForm.addComponent(textArea[0]);
            baseForm.addComponentInCenter(label1, (byte) 5);
            baseForm.addComponentInCenter(label2, (byte) 6);
            baseForm.setFocus(true);
        }
        baseForm.draw(g);
    }

    public void drawSpecialShop(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setStyle((byte) 0);
            rims[0] = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            baseForm.addComponent(rims[0]);
            grids[0] = new UIGrid(7, 32, (byte) 4, (byte) 9, (byte) 4, mImgStuff);
            rims[1] = new UIRim(7, 9, 168, 23, (byte) 2);
            baseForm.addComponentInCenter(rims[1], (byte) 2);
            rims[2] = new UIRim(7, 108, 168, 70, (byte) 0);
            baseForm.addComponentInCenter(rims[2], (byte) 2);
            baseForm.addComponentInCenter(grids[0], (byte) 2);
            UILabel[] label = new UILabel[6];
            label[0] = new UILabel(0, 13, screenW, 0, "特殊道具", 15653280, (byte) 1, (byte) 0);
            baseForm.addComponent(label[0]);
            label[1] = new UILabel(12, 115, 0, 0, "物品名称", 15653280, (byte) 0, (byte) 0);
            baseForm.addComponent(label[1]);
            label[2] = new UILabel(12, 135, 0, 0, "物品价格", 15653280, (byte) 0, (byte) 0);
            baseForm.addComponent(label[2]);
            label[3] = new UILabel(12, 155, 0, 0, "玩家猛犸币", 15653280, (byte) 0, (byte) 0);
            baseForm.addComponent(label[3]);
            label[4] = new UILabel(80, 8, 0, 0, "操作", 15653280, (byte) 0, (byte) 0);
            baseForm.addComponentInCenter(label[4], (byte) 5);
            label[5] = new UILabel(80, 8, 0, 0, "返回", 15653280, (byte) 0, (byte) 0);
            baseForm.addComponentInCenter(label[5], (byte) 6);
            texts[0] = new UIText(83, 136, 80, 0, 9, (byte) 3, "66");
            baseForm.addComponent(texts[0]);
            texts[1] = new UIText(83, 156, 80, 0, 9, (byte) 3, "66");
            baseForm.addComponent(texts[1]);
        }
        baseForm.draw(g);
    }

    public void drawSpecialCharge(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setStyle((byte) 0);
            rims[0] = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            baseForm.addComponent(rims[0]);
            UILabel contentLabel = new UILabel(0, 13, screenW, 0, "正在建设中。。。", 15653280, (byte) 1, (byte) 0);
            baseForm.addComponentInCenter(contentLabel, (byte) 4);
            UILabel doLabe = new UILabel(80, 8, 0, 0, "操作", 15653280, (byte) 0, (byte) 0);
            baseForm.addComponentInCenter(doLabe, (byte) 5);
            UILabel backLabel = new UILabel(80, 8, 0, 0, "返回", 15653280, (byte) 0, (byte) 0);
            baseForm.addComponentInCenter(backLabel, (byte) 6);
        }
        baseForm.draw(g);
    }

    public void drawSpecialCheckDetail(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setStyle((byte) 0);
            rims[0] = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            baseForm.addComponent(rims[0]);
            UILabel contentLabel = new UILabel(0, 13, screenW, 0, "正在建设中。。。", 15653280, (byte) 1, (byte) 0);
            baseForm.addComponentInCenter(contentLabel, (byte) 4);
            UILabel doLabe = new UILabel(80, 8, 0, 0, "操作", 15653280, (byte) 0, (byte) 0);
            baseForm.addComponentInCenter(doLabe, (byte) 5);
            UILabel backLabel = new UILabel(80, 8, 0, 0, "返回", 15653280, (byte) 0, (byte) 0);
            baseForm.addComponentInCenter(backLabel, (byte) 6);
        }
        baseForm.draw(g);
    }

    public void drawHelp(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            UIRim frame = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            UIRim rimTitle = new UIRim(0, 13, 160, 17, (byte) 7);
            UILabel lblTitle = new UILabel(0, rimTitle.positionY + 2, 0, 0, "帮助", 15718814, (byte) 1, (byte) 0);
            UILabel lblOperation = new UILabel(0, 0, 0, 0, "选择", 15718814, (byte) 1, (byte) 0);
            UILabel lblCancel = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 1, (byte) 0);
            UIRim rimMenus = new UIRim(0, 30, 160, 158, (byte) 0);
            menus[0] = new UIMenu(0, 30, 80, 158, null, Cons.strHelpMainMenu);
            menus[0].setRimStyle((byte) 8);
            menus[0].setFlushType((byte) 0);
            baseForm.addComponent(frame);
            baseForm.addComponentInCenter(rimMenus, (byte) 2);
            baseForm.addComponentInCenter(menus[0], (byte) 2);
            baseForm.addComponentInCenter(rimTitle, (byte) 2);
            baseForm.addComponentInCenter(lblTitle, (byte) 2);
            baseForm.addComponentInCenter(lblOperation, (byte) 5);
            baseForm.addComponentInCenter(lblCancel, (byte) 6);
            baseForm.setFocus(true);
        }
        baseForm.draw(g);
    }

    public void drawSubHelp(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setBackGround((byte) 9);
            UIRim frame = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            UIRim rimTitle = new UIRim(0, 13, 160, 17, (byte) 7);
            UILabel lblTitle = new UILabel(0, rimTitle.positionY + 3, 0, 0, Cons.strHelpMainMenu[mainHelpIndex], 15718814, (byte) 1, (byte) 0);
            UIRim rimDown = new UIRim(0, 30, 160, 158, (byte) 0);
            textArea[0] = new UITextArea(0, 30, 160, 158, Cons.strHelpContentMainMenu[mainHelpIndex]);
            textArea[0].setColor(15718814);
            UILabel lblCancel = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 1, (byte) 0);
            baseForm.addComponent(frame);
            baseForm.addComponentInCenter(rimDown, (byte) 2);
            baseForm.addComponentInCenter(rimTitle, (byte) 2);
            baseForm.addComponentInCenter(lblTitle, (byte) 2);
            baseForm.addComponentInCenter(lblCancel, (byte) 6);
            baseForm.addComponentInCenter(textArea[0], (byte) 2);
            baseForm.setFocus(true);
        }
        baseForm.draw(g);
    }

    public void drawUIPetInfor(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setStyle((byte) 0);
            rims[0] = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            UIRim rimImage = new UIRim(8, 6, 45, 36, (byte) 1);
            UIRim rimName = new UIRim(58, 4, 112, 40, (byte) 4);
            UIRim rimProperty = new UIRim(6, 49, 163, 58, (byte) 0);
            UIRim rimDetail = new UIRim(6, 110, 163, 17, (byte) 7);
            labels[0] = new UILabel(80, 8, 0, 0, pet.petName, 15653280, (byte) 0, (byte) 0);
            labels[1] = new UILabel(80, 24, 0, 0, "等级：" + pet.petLevel, 15653280, (byte) 0, (byte) 0);
            labels[2] = new UILabel(15, 50, 0, 0, "经验值", 10321225, (byte) 0, (byte) 0);
            labels[3] = new UILabel(15, 64, 0, 0, "灵  力", 10321225, (byte) 0, (byte) 0);
            labels[4] = new UILabel(15, 78, 0, 0, "忠诚度", 10321225, (byte) 0, (byte) 0);
            labels[7] = new UILabel(15, 92, 0, 0, "灵  魄", 10321225, (byte) 0, (byte) 0);
            labels[5] = new UILabel(0, 0, 0, 0, "返回", 15653280, (byte) 0, (byte) 0);
            labels[6] = new UILabel(0, 114, 0, 0, "宠物已学技能", 15718814, (byte) 1, (byte) 0);
            scrolls[0] = new UIScroll(56, 52, 104, 12, (byte) 2, false);
            scrolls[0].setBar(pet.maxPetExp, 0);
            scrolls[0].setScrollPosition(pet.curPetExp);
            scrolls[0].setProcessColor(3628216);
            scrolls[1] = new UIScroll(56, 66, 104, 12, (byte) 2, false);
            scrolls[1].setBar(pet.maxPetMp, 0);
            scrolls[1].setScrollPosition(pet.curPetMp);
            scrolls[1].setProcessColor(6696081);
            scrolls[2] = new UIScroll(56, 80, 104, 12, (byte) 2, false);
            scrolls[2].setBar(100, 0);
            scrolls[2].setScrollPosition(pet.loyalty);
            scrolls[2].setProcessColor(13323008);
            scrolls[3] = new UIScroll(56, 94, 104, 12, (byte) 2, false);
            scrolls[3].setBar(100, 0);
            scrolls[3].setScrollPosition(pet.hunger);
            scrolls[3].setProcessColor(39240);
            tables[0] = new UITable(6, 128, 163, 63, 4, 1, 4, (byte) 0, (byte) 1);
            tables[0].setInterspace((byte) 10);
            int i = 0;
            for (; i < pet.skills.length && pet.skills[i] != null && (pet.skills[i]).skillID != 0; i++) {
                tables[0].setItem(mImgStuff, (pet.skills[i]).imageID, (pet.skills[i]).skillName + "  等级 " + (pet.skills[i]).skillLevel, i * 2, 10321225);
                tables[0].setItem((MImage) null, (byte) 0, "熟练度 " + (pet.skills[i]).curSkillExp + "/" + (pet.skills[i]).maxSkillExp, i * 2 + 1, 10321225);
            }
            tables[0].setSingleWH((short) 80, (byte) 15, false);
            mImages[0] = new UIMImage(rimImage.positionX + 1, rimImage.positionY + 1, 0, 0, mImgUI[19], (byte) 0);
            mImages[0].setCurrentFrame(pet.imageID);
            baseForm.addComponent(rims[0]);
            baseForm.addComponent(rimImage);
            baseForm.addComponent(rimName);
            baseForm.addComponent(rimProperty);
            baseForm.addComponent(rimDetail);
            baseForm.addComponentInCenter(labels[6], (byte) 2);
            baseForm.addComponent(labels[0]);
            baseForm.addComponent(labels[1]);
            baseForm.addComponent(labels[2]);
            baseForm.addComponent(labels[3]);
            baseForm.addComponent(labels[4]);
            baseForm.addComponent(labels[7]);
            baseForm.addComponent(scrolls[0]);
            baseForm.addComponent(scrolls[1]);
            baseForm.addComponent(scrolls[2]);
            baseForm.addComponent(scrolls[3]);
            baseForm.addComponent(tables[0]);
            baseForm.addComponent(mImages[0]);
            baseForm.addComponentInCenter(labels[5], (byte) 6);
            baseForm.setFocus(true);
            baseForm.setMessage(Cons.ROLL_MASSAGE[8], false);
        }
        baseForm.draw(g);
    }

    public void drawUIPetskillList(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setStyle((byte) 0);
            rims[0] = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            UIRim rimImage = new UIRim(8, 6, 45, 36, (byte) 1);
            UIRim rimName = new UIRim(58, 4, 112, 40, (byte) 4);
            labels[0] = new UILabel(80, 8, 0, 0, pet.petName, 15653280, (byte) 0, (byte) 0);
            labels[1] = new UILabel(80, 24, 0, 0, "等级：" + pet.petLevel, 15653280, (byte) 0, (byte) 0);
            menus[0] = new UIMenu(6, 48, 165, 140, "宠物技能", pet.getSkillNames());
            menus[0].setFlushType((byte) 1);
            if ((menus[0]).strs != null) {
                byte i;
                for (i = 0; i < (menus[0]).strs.length; i = (byte) (i + 1)) {
                    if ((menus[0]).strs[i] == null || (menus[0]).strs[i].length() == 0) {
                        menus[0].setNoUse(i);
                    }
                }
            }
            labels[2] = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 0, (byte) 0);
            labels[3] = new UILabel(0, 0, 0, 0, "操作", 15718815, (byte) 0, (byte) 0);
            mImages[0] = new UIMImage(rimImage.positionX + 1, rimImage.positionY + 1, 0, 0, mImgUI[19], (byte) 0);
            mImages[0].setCurrentFrame((byte) (pet.imageID - 1));
            baseForm.addComponent(rims[0]);
            baseForm.addComponent(rimImage);
            baseForm.addComponent(rimName);
            baseForm.addComponent(labels[0]);
            baseForm.addComponent(labels[1]);
            baseForm.addComponent(menus[0]);
            baseForm.addComponent(mImages[0]);
            baseForm.addComponentInCenter(labels[2], (byte) 6);
            baseForm.addComponentInCenter(labels[3], (byte) 5);
            if (!menus[0].isMenuNull()) {
                baseForm.setFocus(true);
            }
        }
        baseForm.draw(g);
    }

    public void drawUIPetThingslist(Graphics g) {
        if (baseForm == null) {
            String[] items = pet.getSkillDetail();
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setStyle((byte) 0);
            rims[0] = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            labels[0] = new UILabel(0, 12, 0, 0, composeListTitle, 15718815, (byte) 0, (byte) 0);
            labels[1] = new UILabel(0, 0, 0, 0, "操作", 15718815, (byte) 0, (byte) 0);
            labels[2] = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 0, (byte) 0);
            int len = pet.curPetSkill.curIndex;
            tables[0] = new UITable(0, 40, 100, 150, len, 1, 7, (byte) 0, (byte) 2);
            tables[0].setSingleWH((short) 100, (byte) 20, false);
            for (int i = 0; i < len; i++) {
                tables[0].addItem(items[i], 15718815);
            }
            tables[0].setCurrentPointer((byte) table0curPointer);
            baseForm.addComponent(rims[0]);
            baseForm.addComponentInCenter(labels[0], (byte) 2);
            baseForm.addComponentInCenter(labels[1], (byte) 5);
            baseForm.addComponentInCenter(labels[2], (byte) 6);
            baseForm.addComponentInCenter(tables[0], (byte) 2);
            baseForm.setFocus(true);
        }
        baseForm.draw(g);
    }

    public void drawUIPetComposeStuff(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setStyle((byte) 0);
            UIRim rim1 = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            baseForm.addComponent(rim1);
            UIRim rim2 = new UIRim(0, 2, 166, 90, (byte) 0);
            baseForm.addComponentInCenter(rim2, (byte) 2);
            int[][] p = {{9, 17}, {9, 36}, {9, 55}, {9, 74}};
            for (int i = 0; i < 4; i++) {
                rims[i] = new UIRim(p[i][0], p[i][1], 17, 17, (byte) 0);
                mImages[i] = new UIMImage((rims[i]).positionX, (rims[i]).positionY, 0, 0, mImgStuff, (byte) 0);
                mImages[i].setCurrentFrame(((Byte) pet.compose.elementAt(2 * i + 1)).byteValue());
                mImages[i].setNumber(Pets.material_num[i]);
                String name = (String) pet.compose.elementAt(i * 2);
                labels[i] = new UILabel((mImages[i]).positionX + 24, (mImages[i]).positionY, 0, 0, name, 15718815, (byte) 0, (byte) 0);
                baseForm.addComponent(rims[i]);
                baseForm.addComponent(mImages[i]);
                baseForm.addComponent(labels[i]);
                if (i != 3 && ((Byte) pet.compose.elementAt((i + 1) * 2 + 1)).byteValue() == 0) {
                    break;
                }
            }
            labels[5] = new UILabel(5, 3, 0, 0, "合成需要素材", 16776960, (byte) 0, (byte) 0);
            UIRim rimName = new UIRim(8, dramatisPackage.positionY - 20, 166, 17, (byte) 7);
            labels[4] = new UILabel(0, rimName.positionY + 2, screenW, 0, dramatisPackage.getCurrentName(), Cons.STUFF_NAME_COLOR[dramatisPackage.getCurrentNameLevel()], (byte) 1, (byte) 0);
            labels[6] = new UILabel(35, 192, 0, 0, "合成数量:", 65280, (byte) 0, (byte) 0);
            texts[1] = new UIText(90, 192, 50, 12, 8, (byte) 2, "1");
            texts[1].setMaxNumber(20L);
            texts[1].setAroundComponent(dramatisPackage, (byte) 1);
            UILabel label1 = new UILabel(0, 0, 0, 0, "合成", 15718815, (byte) 0, (byte) 0);
            UILabel label2 = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 0, (byte) 0);
            baseForm.addComponentInCenter(labels[5], (byte) 2);
            baseForm.addComponentInCenter(label1, (byte) 5);
            baseForm.addComponentInCenter(label2, (byte) 6);
            baseForm.addComponent(labels[6]);
            baseForm.addComponent(texts[1]);
            baseForm.addComponentInCenter(dramatisPackage, (byte) 2);
            baseForm.addComponentInCenter(rimName, (byte) 2);
            baseForm.addComponentInCenter(labels[4], (byte) 2);
            baseForm.setFocus(true);
            baseForm.setComponentFocus(dramatisPackage);
        }
        baseForm.draw(g);
    }

    public void drawUIPetComposeWeapon(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setStyle((byte) 0);
            UIRim rim1 = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            baseForm.addComponent(rim1);
            UIRim rim2 = new UIRim(0, 2, 166, 90, (byte) 0);
            baseForm.addComponentInCenter(rim2, (byte) 2);
            int[][] p = {{9, 17}, {9, 36}, {9, 55}, {9, 74}};
            for (int i = 0; i < 4; i++) {
                rims[i] = new UIRim(p[i][0], p[i][1], 17, 17, (byte) 0);
                mImages[i] = new UIMImage((rims[i]).positionX, (rims[i]).positionY, 0, 0, mImgStuff, (byte) 0);
                mImages[i].setCurrentFrame(((Byte) pet.compose.elementAt(2 * i + 1)).byteValue());
                mImages[i].setNumber(Pets.material_num[i]);
                String name = (String) pet.compose.elementAt(i * 2);
                labels[i] = new UILabel((mImages[i]).positionX + 24, (mImages[i]).positionY, 0, 0, name, 15718815, (byte) 0, (byte) 0);
                baseForm.addComponent(rims[i]);
                baseForm.addComponent(mImages[i]);
                baseForm.addComponent(labels[i]);
                if (i != 3 && ((Byte) pet.compose.elementAt((i + 1) * 2 + 1)).byteValue() == 0) {
                    break;
                }
            }
            if (pet.quantity / 10 == 1) {
                mImages[0].setImage((MImage) null);
            }
            labels[5] = new UILabel(5, 3, 0, 0, "合成需要素材", 16776960, (byte) 0, (byte) 0);
            UIRim rimName = new UIRim(8, dramatisPackage.positionY - 20, 166, 17, (byte) 7);
            labels[4] = new UILabel(0, rimName.positionY + 2, screenW, 0, dramatisPackage.getCurrentName(), Cons.STUFF_NAME_COLOR[dramatisPackage.getCurrentNameLevel()], (byte) 1, (byte) 0);
            String ss = null;
            if (pet.quantity / 10 == 0) {
                ss = null;
            } else {
                ss = "中间键选择需要装备";
            }
            if (ss != null) {
                labels[6] = new UILabel(5, 192, 0, 0, ss, 65280, (byte) 0, (byte) 0);
            }
            UILabel label1 = new UILabel(0, 0, 0, 0, "合成", 15718815, (byte) 0, (byte) 0);
            UILabel label2 = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 0, (byte) 0);
            baseForm.addComponentInCenter(labels[5], (byte) 2);
            if (labels[6] != null) {
                baseForm.addComponentInCenter(labels[6], (byte) 2);
            }
            baseForm.addComponentInCenter(rimName, (byte) 2);
            baseForm.addComponentInCenter(dramatisPackage, (byte) 2);
            baseForm.addComponentInCenter(labels[4], (byte) 2);
            baseForm.addComponentInCenter(label1, (byte) 5);
            baseForm.addComponentInCenter(label2, (byte) 6);
            baseForm.setFocus(true);
            baseForm.setComponentFocus(dramatisPackage);
        }
        baseForm.draw(g);
    }

    public void drawUIPetComposeResult(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setStyle((byte) 0);
            rims[0] = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            rims[1] = new UIRim(20, 20, 130, 160, (byte) 1);
            labels[0] = new UILabel(10, 32, 120, 0, "您得到：" + pet.stuffName, 15718815, (byte) 0, (byte) 0);
            mImages[0] = new UIMImage(63, 55, 16, 16, mImgStuff, (byte) 0);
            mImages[0].setCurrentFrame((byte) (pet.stuffImageId - 1));
            mImages[0].setCanFocus(false);
            mImages[0].setHaveRim(true);
            labels[1] = new UILabel(89, 58, 0, 0, "× " + pet.quantity, 15718815, (byte) 0, (byte) 0);
            if (pet.material != 1) {
                labels[4] = new UILabel(10, 80, 120, 0, "获得熟练度：" + Pets.tempdex, 15718815, (byte) 0, (byte) 0);
                labels[5] = new UILabel(10, 100, 120, 0, "减少活力：" + Pets.tempvires, 15718815, (byte) 0, (byte) 0);
                labels[6] = new UILabel(10, 120, 120, 0, "减少金钱：" + Pets.tempmoney, 15718815, (byte) 0, (byte) 0);
            } else {
                labels[6] = new UILabel(10, 80, 120, 0, "减少金钱：" + Pets.tempmoney, 15718815, (byte) 0, (byte) 0);
            }
            labels[2] = new UILabel(0, 0, 0, 0, "退出", 15718815, (byte) 0, (byte) 0);
            labels[3] = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 0, (byte) 0);
            baseForm.addComponent(rims[0]);
            baseForm.addComponentInCenter(rims[1], (byte) 2);
            baseForm.addComponentInCenter(labels[0], (byte) 2);
            baseForm.addComponent(labels[1]);
            if (pet.material != 1) {
                baseForm.addComponentInCenter(labels[4], (byte) 2);
                baseForm.addComponentInCenter(labels[5], (byte) 2);
            }
            baseForm.addComponentInCenter(labels[6], (byte) 2);
            baseForm.addComponent(mImages[0]);
            baseForm.addComponentInCenter(labels[2], (byte) 5);
            baseForm.addComponentInCenter(labels[3], (byte) 6);
            baseForm.setFocus(true);
        }
        baseForm.draw(g);
    }

    public void drawUIMailLookup(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            UIRim rimFrame = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            UIRim rimDown = new UIRim(0, 29, 166, 160, (byte) 0);
            UIRim rimTitle = new UIRim(0, 12, 166, 17, (byte) 7);
            UILabel lblTitle = new UILabel(0, rimTitle.positionY + 2, 0, 0, "收件箱", 15718814, (byte) 1, (byte) 0);
            UILabel lblOperation = new UILabel(0, 0, 0, 0, "操作", 15718814, (byte) 0, (byte) 0);
            UILabel lblCancel = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 0, (byte) 0);
            baseForm.addComponent(rimFrame);
            baseForm.addComponentInCenter(rimDown, (byte) 2);
            baseForm.addComponentInCenter(rimTitle, (byte) 2);
            baseForm.addComponentInCenter(lblTitle, (byte) 2);
            baseForm.addComponentInCenter(lblOperation, (byte) 5);
            baseForm.addComponentInCenter(lblCancel, (byte) 6);
            if (mailList != null && mailList.length != 0) {
                isHaveNewMail = false;
                isLookMailnew = false;
                tables[0] = new UITable(0, 29, 166, 160, mailList.length, 1, (mailList.length > 10) ? 10 : mailList.length, (byte) 0, (byte) 5);
                tables[0].setImage(0, mImgUI[13]);
                tables[0].setSingleWH((tables[0]).singleWidth, (byte) 16, false);
                tables[0].setAutoHeight(true);
                for (int i = 0; i < mailList.length; i++) {
                    tables[0].setItem(mailList[i][0], i, 10981736);
                    tables[0].setReadingState(i, !mailList[i][1].equals("0"));
                    tables[0].setAccessoryState(i, !mailList[i][2].equals("0"));
                    if (mailList[i][1].equals("0")) {
                        isHaveNewMail = true;
                    }
                }
                baseForm.addComponentInCenter(tables[0], (byte) 2);
            }
            baseForm.setFocus(true);
            baseForm.setMessage(Cons.ROLL_MASSAGE[9], false);
        }
        baseForm.draw(g);
    }

    public void drawUIMailWrite(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            UIRim rimOut = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            UIRim rimName = new UIRim(0, 9, 160, 17, (byte) 7);
            UILabel title = new UILabel(0, 11, 0, 0, "撰写邮件", 15718814, (byte) 1, (byte) 0);
            UIRim rimMiddle = new UIRim(0, 26, 160, 24, (byte) 0);
            UIRim rimDown = new UIRim(0, 50, 160, 95, (byte) 0);
            UIRim rimBottom = new UIRim(0, 145, 160, 44, (byte) 0);
            UILabel lblReceiver = new UILabel(12, 31, 0, 0, "收件人: ", 15718814, (byte) 0, (byte) 0);
            UILabel lblSubject = new UILabel(12, 55, 0, 0, "主  题: ", 15718814, (byte) 0, (byte) 0);
            UILabel lblOperation = new UILabel(0, 0, 0, 0, "操作", 15718814, (byte) 1, (byte) 0);
            UILabel lblCancel = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 1, (byte) 0);
            String sendTo = "选择收件人";
            int color = 8092539;
            if (PCMail.responseFlag) {
                sendTo = PCMail.responseName;
                color = 10981736;
                mailSendTo = 0;
            }
            labels[0] = new UILabel(61, 32, 100, 0, sendTo, color, (byte) 0, (byte) 0);
            labels[1] = new UILabel(61, 56, 100, 0, "编写邮件主题", 8092539, (byte) 0, (byte) 0);
            textArea[0] = new UITextArea(18, 78, 140, 60, " ");
            UILabel lblAdd = new UILabel(12, 152, 0, 0, "附件: ", 15718814, (byte) 0, (byte) 0);
            UIRim rimImg = new UIRim(44, 150, 17, 17, (byte) 0);
            mImages[0] = new UIMImage(rimImg.positionX + 1, rimImg.positionY + 1, rimImg.width - 1, rimImg.height - 1, mImgStuff, (byte) 0);
            mImages[0].setVisible(false);
            UILabel lblAsset = new UILabel(12, 172, 0, 0, "汇款: ", 15718814, (byte) 0, (byte) 0);
            texts[0] = new UIText(44, 172, 60, 20, 6, (byte) 3, "0");
            UILabel lblMoney = new UILabel(114, 172, 0, 0, "邮费: ", 15718814, (byte) 0, (byte) 0);
            labels[2] = new UILabel(lblMoney.positionX + 35, 172, 50, 0, postage + "", 65280, (byte) 0, (byte) 0);
            labels[4] = new UILabel(70, 152, 100, 0, "", 65280, (byte) 0, (byte) 0);
            baseForm.addComponent(rimOut);
            baseForm.addComponentInCenter(rimName, (byte) 2);
            baseForm.addComponentInCenter(rimMiddle, (byte) 2);
            baseForm.addComponentInCenter(rimDown, (byte) 2);
            baseForm.addComponentInCenter(title, (byte) 2);
            baseForm.addComponentInCenter(rimBottom, (byte) 2);
            baseForm.addComponent(rimImg);
            baseForm.addComponent(lblReceiver);
            baseForm.addComponent(lblSubject);
            baseForm.addComponentInCenter(lblOperation, (byte) 5);
            baseForm.addComponentInCenter(lblCancel, (byte) 6);
            baseForm.addComponent(lblMoney);
            baseForm.addComponent(lblAdd);
            baseForm.addComponent(lblAsset);
            baseForm.addComponent(textArea[0]);
            baseForm.addComponent(texts[0]);
            baseForm.addComponent(labels[0]);
            baseForm.addComponent(labels[1]);
            baseForm.addComponent(labels[2]);
            baseForm.addComponent(labels[4]);
            baseForm.addComponent(mImages[0]);
            baseForm.setFocus(true);
        }
        baseForm.draw(g);
    }

    public void drawDramatisUnion(Graphics g) {
        if (baseForm == null) {
            String temp;
            Image img = Image.createImage(18, 30);
            Graphics gg = img.getGraphics();
            gg.setColor(0);
            gg.fillRect(0, 0, 18, 30);
            UIGameRun.getInstance().drawMenuPeople(gg, 9, 30, (Player.getInstance()).originalImgID);
            clanRequestName = null;
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setStyle((byte) 0);
            UIRim frame = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            int rimOffW = 8;
            UIRim rimImage = new UIRim(8, 6, 39 - rimOffW, 36, (byte) 1);
            UIRim rimRight = new UIRim(52 - rimOffW, 4, 117 + rimOffW, 40, (byte) 4);
            UILabel lblOk = new UILabel(60, 7, 0, 0, "确定", 15718814, (byte) 0, (byte) 0);
            UILabel lblCancel = new UILabel(60, 7, 0, 0, "返回", 15718814, (byte) 0, (byte) 0);
            mImages[0] = new UIMImage(rimImage.positionX + 10, rimImage.positionY + 3, 0, 0, new MImage(img), (byte) 0);
            mImages[0].setCanFocus(false);
            int offX = 8;
            UILabel lblName = new UILabel(rimRight.positionX + 12 - offX, 12, 0, 0, (Player.getInstance()).name, 15718814, (byte) 0, (byte) 0);
            if (clanManegeLevel == 0) {
                temp = "没有加入氏族";
            } else {
                temp = clanName + " " + Cons.UNION_NAMES[clanManegeLevel];
            }
            UILabel lblProfession = new UILabel(lblName.positionX, 26, 0, 0, temp, 10321225, (byte) 0, (byte) 0);
            UILabel lblLevelNum = new UILabel(lblName.positionX + 108 + 3, 12, 0, 0, (Player.getInstance()).level + "级", 15132098, (byte) 0, (byte) 0);
            lblLevelNum.setXY(screenW - 110, lblName.positionY);
            menus[0] = null;
            if (clanManegeLevel > 0) {
                String[] str = {"查看氏族成员列表", "查看申请加入人员", "查看申请退出人员", "查看申请农场人员", "查看氏族详细信息", "查看氏族公告信息"};
                menus[0] = new UIMenu(5, 48, 164, 142, null, str);
                menus[0].setRimStyle((byte) 0);
                menus[0].setFlushType((byte) 1);
                if (clanManegeLevel > 2) {
                    menus[0].setNoUse((byte) 1);
                    menus[0].setNoUse((byte) 2);
                    menus[0].setNoUse((byte) 3);
                }
            }
            baseForm.addComponent(frame);
            baseForm.addComponent(rimImage);
            baseForm.addComponent(rimRight);
            baseForm.addComponent(lblName);
            baseForm.addComponent(lblProfession);
            baseForm.addComponent(lblLevelNum);
            baseForm.addComponentInCenter(lblCancel, (byte) 6);
            baseForm.addComponent(mImages[0]);
            if (menus[0] != null) {
                baseForm.addComponentInCenter(lblOk, (byte) 5);
                baseForm.addComponentInCenter(menus[0], (byte) 2);
            }
            baseForm.setFocus(true);
            if (menus[0] == null) {
                menus[0] = new UIMenu(5, 48, 164, 142, null, null);
                baseForm.addComponentInCenter(menus[0], (byte) 2);
                baseForm.addAboutForm("cannotclan", "你没有加入氏族！", (byte) 1, 180, 0);
            }
        }
        baseForm.draw(g);
    }

    public void drawUISetupFunction(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setBackGround((byte) 9);
            UIRim frame = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            baseForm.addComponent(frame);
            UILabel lblOk = new UILabel(0, 5, 0, 0, "确定", 15587742, (byte) 1, (byte) 0);
            UILabel lblCancel = new UILabel(0, 5, 0, 0, "取消", 15587742, (byte) 1, (byte) 0);
            baseForm.addComponentInCenter(lblOk, (byte) 5);
            baseForm.addComponentInCenter(lblCancel, (byte) 6);
            UIRim rimTitle = new UIRim(0, 10, 160, 17, (byte) 7);
            baseForm.addComponentInCenter(rimTitle, (byte) 2);
            UILabel lblTitle = new UILabel(0, 13, 0, 0, "游戏设置", 15587742, (byte) 1, (byte) 0);
            baseForm.addComponentInCenter(lblTitle, (byte) 2);
            UIRim rimOut = new UIRim(0, 27, 160, 160, (byte) 0);
            baseForm.addComponentInCenter(rimOut, (byte) 2);
            UIRim rimIn = new UIRim(0, 32, 150, 150, (byte) 0);
            baseForm.addComponentInCenter(rimIn, (byte) 2);
            String[] titles = {"私聊频道", "世界频道", "氏族频道", "场景频道", "组队频道", "声音设置", "９键功能", "属性分配"};
            int starty = 38;
            int perHeight = 18;
            rbs[titles.length - 2] = new UIRadioButton(30, starty + (titles.length - 2) * perHeight, 0, 0, titles[titles.length - 2], (byte) 2);
            rbs[titles.length - 2].addItems("方向切换");
            rbs[titles.length - 2].addItems("选中自己");
            rbs[titles.length - 2].setChooseItem(Cons.nineShort);
            rbs[titles.length - 1] = new UIRadioButton(30, starty + (titles.length - 1) * perHeight, 0, 0, titles[titles.length - 1], (byte) 2);
            rbs[titles.length - 1].addItems("手动");
            rbs[titles.length - 1].addItems("自动");
            rbs[titles.length - 1].setChooseItem(Cons.autoDistribute ? 1 : 0);
            int i;
            for (i = 0; i < titles.length - 2; i++) {
                rbs[i] = new UIRadioButton(30, starty + i * perHeight, 0, 0, titles[i], (byte) 0);
                rbs[i].addItems("开");
                rbs[i].addItems("关");
                baseForm.addComponent(rbs[i]);
                if (i == titles.length - 3) {
                    rbs[i].setChooseItem(Cons.audioOpen ? 0 : 1);
                } else {
                    rbs[i].setChooseItem(Cons.channelOption >> i & 0x1);
                }
            }
            for (i = 0; i < titles.length - 1; i++) {
                rbs[i].setAroundComponent(rbs[i + 1], (byte) 2);
            }
            rbs[titles.length - 1].setAroundComponent(rbs[0], (byte) 2);
            baseForm.addComponent(rbs[titles.length - 2]);
            baseForm.addComponent(rbs[titles.length - 1]);
            baseForm.setFocus(true);
        }
        baseForm.draw(g);
    }

    private void drawUISetupDirection(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setBackGround((byte) 9);
            UIRim frame = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            baseForm.addComponent(frame);
            UILabel lblOk = new UILabel(0, 5, 0, 0, "确定", 15587742, (byte) 1, (byte) 0);
            UILabel lblCancel = new UILabel(0, 5, 0, 0, "取消", 15587742, (byte) 1, (byte) 0);
            baseForm.addComponentInCenter(lblOk, (byte) 5);
            baseForm.addComponentInCenter(lblCancel, (byte) 6);
            UIRim rimTitle = new UIRim(0, 10, 160, 17, (byte) 7);
            baseForm.addComponentInCenter(rimTitle, (byte) 2);
            UILabel lblTitle = new UILabel(0, 13, 0, 0, "方向键位", 15587742, (byte) 1, (byte) 0);
            baseForm.addComponentInCenter(lblTitle, (byte) 2);
            UIRim rimOut = new UIRim(0, 27, 160, 160, (byte) 0);
            baseForm.addComponentInCenter(rimOut, (byte) 2);
            UIRim rimIn = new UIRim(0, 32, 150, 150, (byte) 0);
            baseForm.addComponentInCenter(rimIn, (byte) 2);
            int starty = 38;
            int perHeight = 18;
            UILabel tipLabel = new UILabel(0, starty + perHeight * 2, 145, 0, "请注意，如果设置“2468键”为玩家移动的方向键或“5键”为攻击/确认键的话，原“2468键”或“5键”的技能键位就无法使用了。", 15587742, (byte) 0, (byte) 0);
            baseForm.addComponentInCenter(tipLabel, (byte) 2);
            rbs[0] = new UIRadioButton(35, starty, 0, 0, "方向键位", (byte) 2);
            rbs[0].addItems("导航键");
            rbs[0].addItems("2468键");
            rbs[0].setChooseItem(Cons.use2468 ? 1 : 0);
            rbs[1] = new UIRadioButton(35, starty + perHeight, 0, 0, "中间键位", (byte) 2);
            rbs[1].addItems("中间键");
            rbs[1].addItems("5键");
            rbs[1].setChooseItem(Cons.use5 ? 1 : 0);
            rbs[0].setAroundComponent(rbs[1], (byte) 2);
            baseForm.addComponent(rbs[0]);
            baseForm.addComponent(rbs[1]);
            baseForm.setFocus(true);
        }
        baseForm.draw(g);
    }

    public void drawUIShowItem(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setStyle((byte) 0);
            UIRim rimFrame = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            UIRim rimTitle = new UIRim(0, 10, 160, 17, (byte) 7);
            UILabel lblTitle = new UILabel(0, rimTitle.positionY + 3, 0, 0, "显示设置", 15718815, (byte) 1, (byte) 0);
            UIRim rimDown = new UIRim(0, 27, 160, 160, (byte) 0);
            UIRim rimDownInner = new UIRim(0, 32, 150, 150, (byte) 0);
            UILabel lblOk = new UILabel(0, 0, 0, 0, "确定", 15718815, (byte) 1, (byte) 0);
            UILabel lblCancel = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 1, (byte) 0);
            baseForm.addComponent(rimFrame);
            baseForm.addComponentInCenter(rimTitle, (byte) 2);
            baseForm.addComponentInCenter(lblTitle, (byte) 2);
            baseForm.addComponentInCenter(rimDown, (byte) 2);
            baseForm.addComponentInCenter(rimDownInner, (byte) 2);
            for (int i = 0; i < Cons.STR_CHAT_OPERATION.length; i++) {
                rbs[i] = new UIRadioButton(35, 38 + i * 30, 0, 0, Cons.STR_CHAT_OPERATION[i], (byte) 0);
                rbs[i].addItems("开");
                rbs[i].addItems("关");
                if (i != 0) {
                    rbs[i].setAroundComponent(rbs[i - 1], (byte) 1);
                }
                baseForm.addComponent(rbs[i]);
            }
            rbs[0].setChooseItem(Cons.showOtherPlayer ? 0 : 1);
            rbs[1].setChooseItem(Cons.showName ? 0 : 1);
            rbs[2].setChooseItem(Cons.newPlayerHelp ? 0 : 1);
            rbs[3].setChooseItem(Cons.channelOption >> 5 & 0x1);
            baseForm.addComponentInCenter(lblOk, (byte) 5);
            baseForm.addComponentInCenter(lblCancel, (byte) 6);
            baseForm.setFocus(true);
            baseForm.setMessage(Cons.ROLL_MASSAGE[11], false);
        }
        baseForm.draw(g);
    }

    public void drawUIShortcut(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            UIRim rim = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            UILabel title = new UILabel(0, 5, 0, 0, "自定义快捷键", 15718814, (byte) 0, (byte) 0);
            UILabel operation = new UILabel(0, 0, 0, 0, "清空", 15718814, (byte) 0, (byte) 0);
            UILabel cancel = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 0, (byte) 0);
            tree.addCell(14, (byte) 33, 0, 0, 0, 0, 14);
            tree.addCell(15, (byte) 34, 0, 0, 0, 0, 15);
            tree.addCell(16, (byte) 35, 0, 0, 0, 0, 16);
            tree.insertCell(0, -1, (byte) 36, 0, 0, 0, 0, 0);
            tree.setStyle((byte) 1);
            tree.positionX = 5;
            tree.positionY = 48;
            baseForm.addComponent(rim);
            baseForm.addComponent(tree);
            UIRim rimTitle = new UIRim(0, 91, 166, 17, (byte) 1);
            baseForm.addComponentInCenter(rimTitle, (byte) 2);
            labels[0] = new UILabel(8, 94, 160, 0, Cons.SKILL_DETAIL_EX[3][0], 16316576, (byte) 3, (byte) 0);
            labels[1] = new UILabel(8, 113, 160, 0, Cons.SKILL_DETAIL_EX[3][1], 10981736, (byte) 0, (byte) 0);
            baseForm.addComponent(labels[0]);
            baseForm.addComponent(labels[1]);
            baseForm.addComponentInCenter(title, (byte) 2);
            baseForm.addComponentInCenter(operation, (byte) 5);
            baseForm.addComponentInCenter(cancel, (byte) 6);
            baseForm.setFocus(true);
            baseForm.setMessage(Cons.ROLL_MASSAGE[10], false);
        }
        baseForm.draw(g);
        UIGameRun.drawShortcutBar(g, 52, false);
    }

    public void drawOtherInfor(Graphics g) {
        if (baseForm != null) {
            baseForm.draw(g);
            if (baseForm.getCurrentFocusForm() == baseForm && !(tables[0]).isVisible) {
                mImgUI[20].draw(g, 50 * UIComponent.CURR_W / 176, 82 * UIComponent.CURR_H / 208, 0, false);
                mImgUI[20].draw(g, 90 * UIComponent.CURR_W / 176, 82 * UIComponent.CURR_H / 208, 1, false);
                mImgUI[20].draw(g, 130 * UIComponent.CURR_W / 176, 82 * UIComponent.CURR_H / 208, 2, false);
            }
        }
    }

    public void drawUIAddFriend(Graphics g) {
    }

    public void drawUIGroupInvite(Graphics g) {
    }

    public void drawUIPrivateChat(Graphics g) {
    }

    public void drawUIBusiness(Graphics g) {
        switch (subBsnsState) {
            case 100:
                showGame(g);
                if (baseForm == null) {
                    baseForm = new UIForm(0, 0, screenW, screenH, "");
                    baseForm.setBackGround((byte) 9);
                    baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                }
                baseForm.draw(g);
                break;
            case 4:
                showGame(g);
                if (baseForm == null) {
                    baseForm = new UIForm(8, screenH - 70 >> 1, 160, 35, "");
                    baseForm.setBackGround((byte) 9);
                    rims[8] = new UIRim(0, 0, 160, 35, (byte) 1);
                    labels[7] = new UILabel(0, baseForm.positionY + 2, (rims[8]).width, 0, bsnsOtherName + " 向您发起交易", 16770362, (byte) 1, (byte) 0);
                    labels[8] = new UILabel(0, 12, 0, 0, "接受", 16770362, (byte) 0, (byte) 0);
                    labels[9] = new UILabel(0, 12, 0, 0, "拒绝", 16770362, (byte) 0, (byte) 0);
                    baseForm.addComponentInCenter(rims[8], (byte) 4);
                    baseForm.addComponentInCenter(labels[7], (byte) 2);
                    baseForm.addComponentInCenter(labels[8], (byte) 5);
                    baseForm.addComponentInCenter(labels[9], (byte) 6);
                }
                baseForm.draw(g);
                break;
            case 0:
                if (baseForm == null) {
                    baseForm = new UIForm(0, 0, screenW, screenH, "");
                    baseForm.setStyle((byte) 0);
                    rims[0] = new UIRim(0, 95, 166, 18, (byte) 7);
                    rims[1] = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
                    labels[0] = new UILabel(8, 6, 75, 85, (Player.getInstance()).name, 15718815, (byte) 2, (byte) 0);
                    if (bsnsOtherID == -1) {
                        labels[1] = new UILabel(93, 6, 75, 85, bsnsOtherName, 15718815, (byte) 2, (byte) 0);
                    } else {
                        labels[1] = new UILabel(93, 6, 75, 85, bsnsOtherName, 15718815, (byte) 2, (byte) 0);
                    }
                    labels[2] = new UILabel(0, 98, screenW, 0, dramatisPackage.getCurrentName(), 65280, (byte) 1, (byte) 0);
                    labels[2].setColor(Cons.STUFF_NAME_COLOR[dramatisPackage.getCurrentNameLevel()]);
                    texts[0] = new UIText(11, 34, 68, 13, 8, (byte) 3, "0");
                    texts[0].setMinNumber(0L);
                    texts[0].setMaxNumber((Player.getInstance()).money);
                    texts[1] = new UIText(96, 34, 68, 13, 8, (byte) 3, "0");
                    texts[1].setMinNumber(0L);
                    texts[2] = new UIText(0, 191, 68, 13, 8, (byte) 3, (Player.getInstance()).money + "");
                    grids[0] = new UIGrid(16, 48, (byte) 2, (byte) 3, (byte) 2, mImgStuff);
                    grids[1] = new UIGrid(100, 48, (byte) 2, (byte) 3, (byte) 2, mImgStuff);
                    UILabel label1 = new UILabel(0, 0, 0, 0, "操作", 15718815, (byte) 0, (byte) 0);
                    UILabel label2 = new UILabel(0, 0, 0, 0, "取消", 15718815, (byte) 0, (byte) 0);
                    mImages[0] = new UIMImage(81, 50, 0, 0, mImgUI[10], (byte) 0);
                    mImages[0].setMirror(false);
                    mImages[0].setCurrentFrame((byte) 1);
                    mImages[1] = new UIMImage(81, 75, 0, 0, mImgUI[10], (byte) 0);
                    mImages[1].setMirror(true);
                    mImages[1].setCurrentFrame((byte) 1);
                    mImages[2] = new UIMImage(8, 6, 0, 0, mImgUI[32], (byte) 0);
                    mImages[2].setCurrentFrame((Player.getInstance()).originalImgID);
                    mImages[3] = new UIMImage(93, 6, 0, 0, mImgUI[32], (byte) 0);
                    if (bsnsOtherID == -1) {
                        mImages[3].setCurrentFrame(ObjManager.currentTarget.originalImgID);
                    } else {
                        mImages[3].setCurrentFrame(bsOtherImID);
                    }
                    texts[0].setAroundComponent(grids[0], (byte) 2);
                    grids[0].setAroundComponent(dramatisPackage, (byte) 2);
                    grids[0].setAroundComponent(grids[1], (byte) 4);
                    baseForm.addComponentInCenter(dramatisPackage, (byte) 2);
                    baseForm.addComponent(rims[1]);
                    baseForm.addComponent(labels[0]);
                    baseForm.addComponent(labels[1]);
                    baseForm.addComponent(texts[0]);
                    baseForm.addComponent(texts[1]);
                    baseForm.addComponent(grids[0]);
                    baseForm.addComponent(grids[1]);
                    baseForm.addComponentInCenter(rims[0], (byte) 2);
                    baseForm.addComponentInCenter(label1, (byte) 5);
                    baseForm.addComponentInCenter(label2, (byte) 6);
                    byte i;
                    for (i = 0; i < 4; i = (byte) (i + 1)) {
                        baseForm.addComponent(mImages[i]);
                    }
                    baseForm.addComponentInCenter(labels[2], (byte) 2);
                    baseForm.addComponentInCenter(texts[2], (byte) 2);
                    baseForm.setFocus(true);
                    baseForm.setComponentFocus(dramatisPackage);
                    baseForm.setMessage(Cons.ROLL_MASSAGE[7], false);
                }
                baseForm.draw(g);
                break;
            case 2:
                showGame(g);
                if (baseForm == null) {
                    baseForm = new UIForm(0, 0, screenW, screenH, "");
                    baseForm.setBackGround((byte) 9);
                    baseForm.setAboutForm(UIForm.makeAboutForm("w", "等待对方同意交易", (String) null, "取消", screenW - 30));
                }
                baseForm.draw(g);
                break;
            case -1:
                showGame(g);
                break;
        }
    }

    public void drawUIPKInvite(Graphics g) {
    }

    public void drawUINPCMenu(Graphics g) {
        if (baseForm == null) {
            Image img = Image.createImage(18, 30);
            Graphics gg = img.getGraphics();
            gg.setColor(0);
            gg.fillRect(0, 0, 18, 30);
            byte npcId = ObjManager.currentTarget.imgID;
            GameObj.drawNpcForUi(gg, npcId, 9, 30);
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setStyle((byte) 0);
            rims[0] = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            rims[1] = new UIRim(8, 6, 39, 36, (byte) 1);
            rims[2] = new UIRim(52, 4, 117, 40, (byte) 4);
            labels[5] = new UILabel(0, 0, 0, 0, "选择", 15718814, (byte) 0, (byte) 0);
            labels[6] = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 0, (byte) 0);
            mImages[0] = new UIMImage((rims[1]).positionX + 10, (rims[1]).positionY + 3, 0, 0, new MImage(img), (byte) 0);
            labels[0] = new UILabel((rims[2]).positionX + 8, (rims[2]).positionY + 6, 0, 0, ObjManager.currentTarget.name, 15718814, (byte) 0, (byte) 0);
            labels[1] = new UILabel((labels[0]).positionX, (labels[0]).positionY + 17, 0, 0, "等级 " + ObjManager.currentTarget.level, 10321225, (byte) 0, (byte) 0);
            byte i;
            for (i = 0; i < 3; i = (byte) (i + 1)) {
                baseForm.addComponent(rims[i]);
            }
            for (i = 0; i < 2; i = (byte) (i + 1)) {
                baseForm.addComponent(labels[i]);
            }
            baseForm.addComponentInCenter(labels[5], (byte) 5);
            baseForm.addComponentInCenter(labels[6], (byte) 6);
            baseForm.addComponent(mImages[0]);
            NPCMenu.setXY(5, 48);
            baseForm.addComponentInCenter(NPCMenu, (byte) 2);
            baseForm.setFocus(true);
            baseForm.setComponentFocus(NPCMenu);
        }
        baseForm.draw(g);
    }

    public void drawUINPCTask(Graphics g) {
        if (baseForm == null) {
            Image img = Image.createImage(18, 30);
            Graphics gg = img.getGraphics();
            gg.setColor(0);
            gg.fillRect(0, 0, 18, 30);
            byte npcId = ObjManager.currentTarget.imgID;
            GameObj.drawNpcForUi(gg, npcId, 9, 30);
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setStyle((byte) 0);
            rims[0] = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            rims[1] = new UIRim(8, 6, 39, 36, (byte) 1);
            rims[2] = new UIRim(52, 4, 117, 40, (byte) 4);
            mImages[3] = new UIMImage((rims[1]).positionX + 10, (rims[1]).positionY + 3, 0, 0, new MImage(img), (byte) 0);
            mImages[3].setCanFocus(false);
            labels[0] = new UILabel((rims[2]).positionX + 8, (rims[2]).positionY + 6, 0, 0, ObjManager.currentTarget.name, 15718814, (byte) 0, (byte) 0);
            labels[1] = new UILabel((labels[0]).positionX, (labels[0]).positionY + 17, 0, 0, "等级 " + ObjManager.currentTarget.level, 10321225, (byte) 0, (byte) 0);
            UILabel labelOk = null;
            String ss = null;
            if (NPCMenu.getCurrentRealState() == 2) {
                if (NPCMenu.isCurrentCanResponse()) {
                    ss = "确定";
                } else if (taskStuffId[taskSelectedId] == 0) {
                    ss = "";
                } else {
                    ss = "操作";
                }
                labelOk = new UILabel(0, 0, 0, 0, ss, 15718814, (byte) 0, (byte) 0);
            } else {
                if (NPCMenu.getCurrentRealState() == 1 && taskStuffId[0] == 0) {
                    ss = "  ";
                } else {
                    ss = "操作";
                }
                labelOk = new UILabel(0, 0, 0, 0, ss, 15718814, (byte) 0, (byte) 0);
            }
            UILabel labelBack = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 0, (byte) 0);
            textArea[0] = new UITextArea(5, 48, 164, 70, taskDetail[0]);
            textArea[0].setColor(10981736);
            textArea[0].setCanFocus(false);
            UIRim rim = new UIRim(5, 120, 164, 70, (byte) 0);
            UILabel label = new UILabel(rim.positionX + 4, rim.positionY + 4, 164, 0, "您将得到：", 16316576, (byte) 0, (byte) 0);
            byte i;
            for (i = 0; i < 3; i = (byte) (i + 1)) {
                baseForm.addComponent(rims[i]);
            }
            for (i = 0; i < 2; i = (byte) (i + 1)) {
                baseForm.addComponent(labels[i]);
            }
            baseForm.addComponent(textArea[0]);
            baseForm.addComponent(rim);
            baseForm.addComponent(label);
            baseForm.addComponentInCenter(labelBack, (byte) 6);
            baseForm.addComponentInCenter(labelOk, (byte) 5);
            int[][] tempXY = {{8, 144}, {87, 144}, {8, 170}, {87, 170}};
            if (taskStuffId[0] != 0) {
                mImages[0] = new UIMImage(tempXY[0][0], tempXY[0][1], 18, 18, mImgStuff, (byte) 0);
                mImages[0].setHaveRim(true);
                (mImages[0]).index = 0;
                mImages[0].setCurrentFrame(taskStuffImageId[0]);
                mImages[0].setNumber(taskThingNumber[0]);
            }
            if (taskStuffId[1] != 0) {
                mImages[1] = new UIMImage(tempXY[1][0], tempXY[1][1], 18, 18, mImgStuff, (byte) 0);
                mImages[1].setHaveRim(true);
                (mImages[1]).index = 1;
                mImages[1].setCurrentFrame(taskStuffImageId[1]);
                mImages[1].setNumber(taskThingNumber[1]);
            }
            UILabel[] uilb = new UILabel[4];
            if (taskStuffId[1] != 0) {
                uilb[0] = new UILabel(28, 145, 0, 0, (taskDetail[1].length() > 4) ? (taskDetail[1].substring(0, 3) + "..") : taskDetail[1], 15718814, (byte) 0, (byte) 0);
                uilb[1] = new UILabel(110, 145, 0, 0, (taskDetail[2].length() > 4) ? (taskDetail[2].substring(0, 3) + "..") : taskDetail[2], 15718814, (byte) 0, (byte) 0);
            } else {
                uilb[0] = new UILabel(28, 145, 0, 0, taskDetail[1], 15718814, (byte) 0, (byte) 0);
            }
            if (taskStuffId[1] != 0) {
                uilb[0] = new UILabel(28, 145, 0, 0, (taskDetail[1].length() > 4) ? (taskDetail[1].substring(0, 3) + "..") : taskDetail[1], 15718814, (byte) 0, (byte) 0);
                uilb[1] = new UILabel(110, 145, 0, 0, (taskDetail[2].length() > 4) ? (taskDetail[2].substring(0, 3) + "..") : taskDetail[2], 15718814, (byte) 0, (byte) 0);
            } else {
                uilb[0] = new UILabel(28, 145, 0, 0, taskDetail[1], 15718814, (byte) 0, (byte) 0);
            }
            ss = null;
            if (taskExperiecce <= 0) {
                ss = "";
                uilb[2] = null;
            } else {
                ss = String.valueOf(taskExperiecce);
                uilb[2] = new UILabel(tempXY[3][0], tempXY[3][1], 0, 0, "经验：" + ss, 15718814, (byte) 0, (byte) 0);
            }
            if (taskMoney <= 0) {
                ss = "";
                uilb[3] = null;
            } else {
                ss = ((taskType == 0) ? "金钱：" : "声望：") + String.valueOf(taskMoney);
                uilb[3] = new UILabel(tempXY[2][0], tempXY[2][1], 0, 0, ss, 15718814, (byte) 0, (byte) 0);
            }
            if (taskStuffId[0] != 0 && taskStuffId[1] != 0) {
                mImages[0].setAroundComponent(mImages[1], (byte) 4);
            }
            byte b1;
            for (b1 = 0; b1 < 4; b1 = (byte) (b1 + 1)) {
                if (uilb[b1] != null) {
                    baseForm.addComponent(uilb[b1]);
                }
                if (mImages[b1] != null) {
                    if (mImages[b1].getCurrentFrame() < 33) {
                        mImages[b1].setNumberVisiable(false);
                    }
                    baseForm.addComponent(mImages[b1]);
                }
            }
            baseForm.setFocus(true);
        }
        baseForm.draw(g);
    }

    public void drawUINPCTrade(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setStyle((byte) 0);
            rims[0] = new UIRim(0, 95, 166, 18, (byte) 7);
            rims[1] = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            baseForm.addComponent(rims[1]);
            baseForm.addComponentInCenter(grids[0], (byte) 2);
            baseForm.addComponentInCenter(rims[0], (byte) 2);
            labels[0] = new UILabel(0, 6, 0, 0, "　商店", 15718814, (byte) 1, (byte) 0);
            baseForm.addComponent(labels[0]);
            labels[1] = new UILabel((labels[0]).positionX + (labels[0]).width, (labels[0]).positionY, screenW, 0, "   ", Cons.STUFF_NAME_COLOR[grids[0].getCurrentNameLevel()], (byte) 0, (byte) 0);
            baseForm.addComponent(labels[1]);
            labels[1].setXY((labels[0]).positionX + (labels[0]).width + 25, (labels[0]).positionY);
            labels[2] = new UILabel(20, (grids[0]).positionY + (grids[0]).height + 6, 0, 0, "物品价格", 15718814, (byte) 0, (byte) 0);
            baseForm.addComponent(labels[2]);
            labels[2].setXY(20, (grids[0]).positionY + (grids[0]).height + 6);
            labels[3] = new UILabel(20, (labels[2]).positionY + (labels[2]).height + 2, 0, 0, "玩家金钱", 15718814, (byte) 0, (byte) 0);
            baseForm.addComponent(labels[3]);
            labels[3].setXY(20, (labels[2]).positionY + (labels[2]).height + 2);
            stuffName = new UILabel(0, (rims[0]).positionY + 2, screenW, 0, dramatisPackage.getCurrentName(), Cons.STUFF_NAME_COLOR[dramatisPackage.getCurrentNameLevel()], (byte) 1, (byte) 0);
            baseForm.addComponent(stuffName);
            stuffName.setXY(0, (rims[0]).positionY + 2);
            texts[0] = new UIText((labels[2]).positionX + (labels[2]).width, (labels[2]).positionY, 90, 13, 8, (byte) 3, packageStuffPrice[dramatisPackage.getCurrentPointer()] + "");
            texts[1] = new UIText((labels[3]).positionX + (labels[3]).width, (labels[3]).positionY, 90, 13, 8, (byte) 3, (Player.getInstance()).money + "");
            UILabel label1 = new UILabel(0, 0, 0, 0, "操作", 15718815, (byte) 0, (byte) 0);
            UILabel label2 = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 0, (byte) 0);
            grids[0].setAroundComponent(dramatisPackage, (byte) 2);
            baseForm.addComponentInCenter(dramatisPackage, (byte) 2);
            baseForm.addComponent(texts[0]);
            texts[0].setXY((labels[2]).positionX + (labels[2]).width, (labels[2]).positionY);
            baseForm.addComponent(texts[1]);
            texts[1].setXY((labels[3]).positionX + (labels[3]).width, (labels[3]).positionY);
            baseForm.addComponentInCenter(label1, (byte) 5);
            baseForm.addComponentInCenter(label2, (byte) 6);
            baseForm.setFocus(true);
            baseForm.setComponentFocus(dramatisPackage);
            if (PCNPC.getHonorSign() == 3) {
                labels[3].setStr("氏族声望");
                texts[1].setLabel(PCNPC.needOne + "");
            } else if (PCNPC.getHonorSign() == 4) {
                labels[2].setStr("玩家竞技");
                labels[3].setStr("玩家声誉");
                texts[0].setLabel(PCNPC.needOne + "");
                texts[1].setLabel(PCNPC.needTwo + "");
            }
        }
        baseForm.draw(g);
    }

    public void drawUINPCSale(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW - 2, screenH, "");
            baseForm.setStyle((byte) 0);
            rims[0] = new UIRim(0, 95, 166, 18, (byte) 7);
            rims[1] = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            labels[0] = new UILabel(6, 6, 0, 0, "商店：卖", 15718814, (byte) 0, (byte) 0);
            labels[1] = new UILabel(58, 6, 0, 0, "价格：", 15718814, (byte) 0, (byte) 0);
            labels[2] = new UILabel(6, 80, 0, 0, "玩家金钱：", 15718814, (byte) 0, (byte) 0);
            texts[0] = new UIText((labels[1]).positionX + (labels[1]).width + 4, 6, 68, 13, 8, (byte) 3, "0");
            texts[1] = new UIText((labels[2]).positionX + (labels[2]).width + 4, 80, 68, 13, 8, (byte) 3, "1000");
            grids[0] = new UIGrid(0, 21, (byte) 3, (byte) 9, (byte) 3, mImgStuff);
            UILabel label1 = new UILabel(0, 0, 0, 0, "操作", 15718815, (byte) 0, (byte) 0);
            UILabel label2 = new UILabel(0, 0, 0, 0, "取消", 15718815, (byte) 0, (byte) 0);
            baseForm.addComponent(rims[1]);
            baseForm.addComponent(labels[0]);
            baseForm.addComponent(labels[1]);
            baseForm.addComponent(labels[2]);
            baseForm.addComponent(texts[0]);
            baseForm.addComponent(texts[1]);
            baseForm.addComponentInCenter(rims[0], (byte) 2);
            baseForm.addComponentInCenter(dramatisPackage, (byte) 2);
            baseForm.addComponentInCenter(grids[0], (byte) 2);
            baseForm.addComponentInCenter(label1, (byte) 5);
            baseForm.addComponentInCenter(label2, (byte) 6);
            baseForm.setFocus(true);
            baseForm.setComponentFocus(dramatisPackage);
        }
        baseForm.draw(g);
    }

    public void drawUINPCFix(Graphics g) {
        if (baseForm == null) {
            Image img = Image.createImage(18, 30);
            Graphics gg = img.getGraphics();
            gg.setColor(0);
            gg.fillRect(0, 0, 18, 30);
            UIGameRun.getInstance().drawMenuPeople(gg, 9, 30, (Player.getInstance()).originalImgID);
            int[][] p = {{33, 10}, {33, 32}, {33, 54}, {123, 10}, {123, 32}, {123, 54}, {63, 54}, {93, 54}};
            byte[] ic = {0, 1, 2, 3, 4, 5, 6, 6};
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            UIRim frame = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            UIRim rimTitle = new UIRim(8, 12, 160, 30, (byte) 6);
            UIRim rimDown = new UIRim(8, 44, 159, 140, (byte) 4);
            UILabel lblOperation = new UILabel(0, 0, 0, 0, "修理", 15718814, (byte) 0, (byte) 0);
            UILabel lblCancel = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 0, (byte) 0);
            UILabel lblName = new UILabel(0, 14, screenW, 0, (Player.getInstance()).name, 15718814, (byte) 1, (byte) 0);
            StringBuffer sb = new StringBuffer();
            sb.append(Cons.STR_PLAYERS[(Player.getInstance()).originalImgID]).append(" ");
            if ((Player.getInstance()).originalImgID < 4) {
                sb.append(Cons.STR_CAMP[0]);
            } else {
                sb.append(Cons.STR_CAMP[1]);
            }
            UILabel lblVocation = new UILabel(19, 27, 0, 0, sb.toString(), 10321225, (byte) 0, (byte) 0);
            sb.delete(0, sb.length());
            sb.append((Player.getInstance()).level).append("级");
            UILabel lblLevel = new UILabel(135, 27, 0, 0, sb.toString(), 15132098, (byte) 0, (byte) 0);
            labels[0] = new UILabel(115, rimDown.positionY + 6, screenW, 0, equipSruffName[0], 65280, (byte) 1, (byte) 0);
            labels[0].setColor(Cons.STUFF_NAME_COLOR[equipStuffNameLevel[0]]);
            UILabel lblRepaire = new UILabel(27, 136, 0, 0, "修理费用", 15718814, (byte) 0, (byte) 0);
            UILabel lblRepaireAll = new UILabel(27, 151, 0, 0, "全部修理", 15718814, (byte) 0, (byte) 0);
            UILabel draMoney = new UILabel(27, 166, 0, 0, "玩家金钱", 15718814, (byte) 0, (byte) 0);
            texts[0] = new UIText(84, 136, 60, 0, 8, (byte) 3, fixPrice[0] + "");
            texts[1] = new UIText(84, 151, 60, 0, 8, (byte) 3, fixAllPrice + "");
            texts[2] = new UIText(84, 166, 60, 0, 8, (byte) 3, (Player.getInstance()).money + "");
            byte b;
            for (b = 0; b < 8; b = (byte) (b + 1)) {
                mImages[b] = new UIMImage(p[b][0], p[b][1] + 60, 18, 18, mImgStuff, (byte) 0);
                mImages[b].setCurrentFrame(equipImageId[b]);
                mImages[b].setHaveRim(true);
                mImages[b].setBackVeins(mImgUI[26], ic[b]);
                (mImages[b]).index = b;
            }
            mImages[8] = new UIMImage(0, 50, 0, 0, new MImage(img), (byte) 0);
            mImages[0].setAroundComponent(mImages[3], (byte) 4);
            mImages[0].setAroundComponent(mImages[1], (byte) 2);
            mImages[3].setAroundComponent(mImages[4], (byte) 2);
            mImages[1].setAroundComponent(mImages[2], (byte) 2);
            mImages[4].setAroundComponent(mImages[5], (byte) 2);
            mImages[1].setAroundComponent(mImages[4], (byte) 4);
            mImages[2].setAroundComponent(mImages[6], (byte) 4);
            mImages[6].setAroundComponent(mImages[7], (byte) 4);
            mImages[7].setAroundComponent(mImages[5], (byte) 4);
            baseForm.addComponent(frame);
            baseForm.addComponentInCenter(rimTitle, (byte) 2);
            baseForm.addComponentInCenter(rimDown, (byte) 2);
            baseForm.addComponentInCenter(lblOperation, (byte) 5);
            baseForm.addComponentInCenter(lblCancel, (byte) 6);
            baseForm.addComponent(lblName);
            baseForm.addComponent(lblVocation);
            baseForm.addComponent(lblLevel);
            baseForm.addComponent(lblRepaire);
            baseForm.addComponent(lblRepaireAll);
            baseForm.addComponent(draMoney);
            for (int i = 0; i < 9; i++) {
                baseForm.addComponent(mImages[i]);
            }
            baseForm.addComponentInCenter(mImages[8], (byte) 2);
            baseForm.addComponent(texts[0]);
            baseForm.addComponent(texts[1]);
            baseForm.addComponent(texts[2]);
            baseForm.addComponentInCenter(labels[0], (byte) 2);
            baseForm.setFocus(true);
            fixPlace = 0;
            UIMImage.sign = 0;
        }
        baseForm.draw(g);
    }

    public void drawUINPCAdoptPet(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setStyle((byte) 0);
            rims[0] = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            rims[1] = new UIRim(8, 6, 45, 36, (byte) 1);
            rims[2] = new UIRim(58, 4, 112, 40, (byte) 4);
            mImages[0] = new UIMImage((rims[1]).positionX + 1, (rims[1]).positionY + 1, 0, 0, mImgUI[19], (byte) 0);
            labels[0] = new UILabel(60, 18, 112, 0, Cons.PET_NAME[0], 16316576, (byte) 1, (byte) 0);
            UILabel label1 = new UILabel(0, 0, 0, 0, "领养", 15718815, (byte) 0, (byte) 0);
            UILabel label2 = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 0, (byte) 0);
            menus[0] = new UIMenu(5, 48, 164, 74, null, Cons.PET_NAME);
            menus[0].setRimStyle((byte) 0);
            menus[0].setFlushType((byte) 1);
            textArea[0] = new UITextArea(5, 126, 164, 64, Cons.PET_dETAIL[0]);
            textArea[0].setColor(15849885);
            baseForm.addComponent(menus[0]);
            byte i;
            for (i = 0; i < 3; i = (byte) (i + 1)) {
                baseForm.addComponent(rims[i]);
            }
            baseForm.addComponent(labels[0]);
            baseForm.addComponent(mImages[0]);
            baseForm.addComponent(textArea[0]);
            baseForm.addComponentInCenter(label1, (byte) 5);
            baseForm.addComponentInCenter(label2, (byte) 6);
            baseForm.setFocus(true);
        }
        baseForm.draw(g);
    }

    public void drawUIPetLearnSkills(Graphics g) {
        if (baseForm == null) {
            Image img = Image.createImage(18, 30);
            Graphics gg = img.getGraphics();
            gg.setColor(0);
            gg.fillRect(0, 0, 18, 30);
            byte npcId = ObjManager.currentTarget.imgID;
            GameObj.drawNpcForUi(gg, npcId, 9, 30);
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setStyle((byte) 0);
            rims[0] = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            rims[1] = new UIRim(8, 6, 39, 36, (byte) 1);
            rims[2] = new UIRim(52, 4, 117, 40, (byte) 4);
            mImages[0] = new UIMImage((rims[1]).positionX + 10, (rims[1]).positionY + 3, 0, 0, new MImage(img), (byte) 0);
            labels[0] = new UILabel((rims[2]).positionX + 8, (rims[2]).positionY + 6, 0, 0, ObjManager.currentTarget.name, 15718814, (byte) 0, (byte) 0);
            labels[1] = new UILabel((labels[0]).positionX, (labels[0]).positionY + 17, 0, 0, "等级 " + ObjManager.currentTarget.level, 10321225, (byte) 0, (byte) 0);
            UILabel label1 = new UILabel(0, 0, 0, 0, "学习", 15718815, (byte) 0, (byte) 0);
            UILabel label2 = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 0, (byte) 0);
            byte i;
            for (i = 0; i < 3; i = (byte) (i + 1)) {
                baseForm.addComponent(rims[i]);
            }
            for (i = 0; i < 2; i = (byte) (i + 1)) {
                baseForm.addComponent(labels[i]);
            }
            baseForm.addComponent(mImages[0]);
            baseForm.addComponent(textArea[0]);
            baseForm.addComponent(rims[9]);
            baseForm.addComponent(labels[9]);
            baseForm.addComponentInCenter(label1, (byte) 5);
            baseForm.addComponentInCenter(label2, (byte) 6);
        }
        baseForm.draw(g);
    }

    public void drawUINPCLabourUnion(Graphics g) {
        if (baseForm == null) {
            Image img = Image.createImage(18, 30);
            Graphics gg = img.getGraphics();
            gg.setColor(0);
            gg.fillRect(0, 0, 18, 30);
            byte npcId = ObjManager.currentTarget.imgID;
            GameObj.drawNpcForUi(gg, npcId, 9, 30);
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setStyle((byte) 0);
            UIRim frame = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            UIRim rimImage = new UIRim(8, 6, 39, 36, (byte) 1);
            UIRim rimRight = new UIRim(52, 4, 117, 40, (byte) 4);
            UILabel lblOk = new UILabel(60, 7, 0, 0, "确定", 15718814, (byte) 0, (byte) 0);
            UILabel lblCancel = new UILabel(60, 7, 0, 0, "返回", 15718814, (byte) 0, (byte) 0);
            mImages[0] = new UIMImage(rimImage.positionX + 10, rimImage.positionY + 3, 0, 0, new MImage(img), (byte) 0);
            mImages[0].setCanFocus(false);
            UILabel lblName = new UILabel(60, 10, 0, 0, ObjManager.currentTarget.name, 15718814, (byte) 0, (byte) 0);
            UILabel lblProfession = new UILabel(lblName.positionX, lblName.positionY + 17, 0, 0, ObjManager.currentTarget.level + "级", 10321225, (byte) 0, (byte) 0);
            baseForm.addComponent(frame);
            baseForm.addComponent(rimImage);
            baseForm.addComponent(rimRight);
            baseForm.addComponent(lblName);
            baseForm.addComponent(lblProfession);
            baseForm.addComponentInCenter(lblCancel, (byte) 6);
            baseForm.addComponent(mImages[0]);
            if (menus[0] != null) {
                baseForm.addComponentInCenter(lblOk, (byte) 5);
                baseForm.addComponentInCenter(menus[0], (byte) 2);
            }
            baseForm.setFocus(true);
            if (menus[0] == null) {
                setMessage(baseForm, "你不符合任何氏族条件！");
            }
        }
        baseForm.draw(g);
    }

    public void drawUINPCSmith(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setStyle((byte) 0);
            UIRim rim = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            UILabel lblMoney = new UILabel(5, 80, 0, 0, "玩家金钱", 15718814, (byte) 1, (byte) 0);
            labels[3] = new UILabel(0, 0, 0, 0, "操作", 15718814, (byte) 1, (byte) 0);
            labels[4] = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 1, (byte) 0);
            UIRim rimUp = new UIRim(0, 5, 160, 70, (byte) 4);
            UIRim rimTitle = new UIRim(0, 95, 166, 17, (byte) 7);
            labels[0] = new UILabel(0, 12, UIComponent.charW * 7, 0, "请选择想要进行精炼的武器或防具", 15653022, (byte) 0, (byte) 0);
            stuffName = new UILabel(0, rimTitle.positionY + 2, screenW, 0, dramatisPackage.getCurrentName(), 65280, (byte) 1, (byte) 0);
            stuffName.setColor(Cons.STUFF_NAME_COLOR[dramatisPackage.getCurrentNameLevel()]);
            texts[0] = new UIText(70, 78, 80, 0, 10, (byte) 3, (Player.getInstance()).money + "");
            texts[0].setCanFocus(false);
            baseForm.addComponent(rim);
            baseForm.addComponent(lblMoney);
            baseForm.addComponentInCenter(labels[3], (byte) 5);
            baseForm.addComponentInCenter(labels[4], (byte) 6);
            baseForm.addComponent(texts[0]);
            baseForm.addComponentInCenter(rimTitle, (byte) 2);
            baseForm.addComponentInCenter(rimUp, (byte) 2);
            baseForm.addComponentInCenter(labels[0], (byte) 2);
            baseForm.addComponentInCenter(stuffName, (byte) 2);
            baseForm.addComponentInCenter(dramatisPackage, (byte) 2);
            baseForm.setFocus(true);
            baseForm.setComponentFocus(dramatisPackage);
        }
        baseForm.draw(g);
    }

    public void drawUINPCStorehouse(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setStyle((byte) 0);
            UIRim rim = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            UILabel lblStore = new UILabel(20, 3, 0, 0, "存款", 15718814, (byte) 1, (byte) 0);
            UILabel lblMoney = new UILabel(5, 80, 0, 0, "玩家金钱", 15718814, (byte) 1, (byte) 0);
            UILabel lblConfirm = new UILabel(0, 0, 0, 0, "操作", 15718814, (byte) 1, (byte) 0);
            UILabel lblCancel = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 1, (byte) 0);
            UIRim rimTitle = new UIRim(0, 95, 166, 17, (byte) 7);
            texts[0] = new UIText(55, 3, 80, 0, 10, (byte) 3, storeMoney + "");
            texts[1] = new UIText(65, 77, 80, 0, 10, (byte) 3, (Player.getInstance()).money + "");
            stuffName = new UILabel(0, rimTitle.positionY + 2, screenW, 0, dramatisPackage.getCurrentName(), 65280, (byte) 1, (byte) 0);
            stuffName.setColor(Cons.STUFF_NAME_COLOR[dramatisPackage.getCurrentNameLevel()]);
            grids[0].setAroundComponent(dramatisPackage, (byte) 2);
            baseForm.addComponent(rim);
            baseForm.addComponent(lblStore);
            baseForm.addComponent(lblMoney);
            baseForm.addComponentInCenter(lblConfirm, (byte) 5);
            baseForm.addComponentInCenter(lblCancel, (byte) 6);
            baseForm.addComponent(texts[0]);
            baseForm.addComponent(texts[1]);
            baseForm.addComponentInCenter(rimTitle, (byte) 2);
            baseForm.addComponentInCenter(stuffName, (byte) 2);
            baseForm.addComponentInCenter(grids[0], (byte) 2);
            baseForm.addComponentInCenter(dramatisPackage, (byte) 2);
            baseForm.setFocus(true);
            baseForm.setComponentFocus(dramatisPackage);
        }
        baseForm.draw(g);
    }

    public void drawUINPCExchange(Graphics g) {
        switch (getNPCExchangeState()) {
            case 0:
                if (baseForm == null) {
                    baseForm = new UIForm(0, 0, screenW, screenH, "");
                    baseForm.setStyle((byte) 0);
                    rims[0] = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
                    labels[0] = new UILabel(0, 12, 0, 0, "兑换物品列表", 15718815, (byte) 1, (byte) 1);
                    labels[1] = new UILabel(0, 0, 0, 0, "选择", 15718815, (byte) 0, (byte) 0);
                    labels[2] = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 0, (byte) 0);
                    baseForm.addComponent(rims[0]);
                    baseForm.addComponentInCenter(labels[0], (byte) 2);
                    baseForm.addComponentInCenter(labels[1], (byte) 5);
                    baseForm.addComponentInCenter(labels[2], (byte) 6);
                    PCNPC.exchangeTable.positionY = 40;
                    baseForm.addComponentInCenter(PCNPC.exchangeTable, (byte) 2);
                    baseForm.setFocus(true);
                }
                baseForm.draw(g);
                break;
            case 1:
                if (baseForm == null) {
                    baseForm = new UIForm(0, 0, screenW, screenH, "");
                    baseForm.setStyle((byte) 0);
                    rims[0] = new UIRim(0, 30, 160, 130, (byte) 4);
                    rims[5] = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
                    baseForm.addComponentInCenter(rims[5], (byte) 2);
                    baseForm.addComponentInCenter(rims[0], (byte) 2);
                    for (int i = 0; i < 3; i++) {
                        if (rims[i + 1] != null) {
                            baseForm.addComponent(rims[i + 1]);
                            baseForm.addComponent(mImages[i]);
                            baseForm.addComponent(labels[i]);
                        }
                    }
                    labels[6] = new UILabel(5, 170, 0, 0, "输入兑换数量", 15718815, (byte) 0, (byte) 0);
                    texts[1] = new UIText(95, 170, 75, 12, 8, (byte) 2, "1");
                    if (changeMax < 10000) {
                        texts[1].setMaxNumber(changeMax);
                    } else {
                        texts[1].setMaxNumber(20L);
                    }
                    UILabel label1 = new UILabel(0, 0, 0, 0, "兑换", 15718815, (byte) 0, (byte) 0);
                    UILabel label2 = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 0, (byte) 0);
                    baseForm.addComponentInCenter(label1, (byte) 5);
                    baseForm.addComponentInCenter(label2, (byte) 6);
                    baseForm.addComponent(labels[5]);
                    baseForm.addComponent(labels[6]);
                    baseForm.addComponent(texts[1]);
                    baseForm.setFocus(true);
                    baseForm.setComponentFocus(texts[1]);
                }
                baseForm.draw(g);
                break;
        }
    }

    public void drawUINPCTop(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            UIRim frame = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            UIRim rimTitle = new UIRim(0, 13, 159, 17, (byte) 7);
            UILabel lblTitle = new UILabel(0, rimTitle.positionY + 3, 0, 0, topTitle, 15718814, (byte) 1, (byte) 0);
            UIRim rimDown = new UIRim(0, 30, 160, 100, (byte) 0);
            topTables = new UITable(0, 31, 160, 10, PCNPC.MAXTOP, 1, (PCNPC.MAXTOP > 10) ? 10 : PCNPC.MAXTOP, (byte) 0, (byte) 3);
            topTables.setAutoHeight(true);
            labels[2] = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 0, (byte) 0);
            for (int i = 0; i < PCNPC.MAXTOP; i++) {
                topTables.addItem(topPos[i], 15718815);
            }
            baseForm.addComponent(frame);
            baseForm.addComponentInCenter(rimDown, (byte) 2);
            baseForm.addComponentInCenter(rimTitle, (byte) 2);
            baseForm.addComponentInCenter(lblTitle, (byte) 2);
            baseForm.addComponentInCenter(topTables, (byte) 2);
            baseForm.addComponentInCenter(labels[2], (byte) 6);
            baseForm.setFocus(true);
        }
        baseForm.draw(g);
    }

    /**
     * 绘制NPC位置面板（寻径用）
     *
     * @param g
     */
    public void drawUINPCPos(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            UIRim frame = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            UIRim rimTitle = new UIRim(0, 13, 159, 17, (byte) 7);
            UILabel lblTitle = new UILabel(0, rimTitle.positionY + 3, 0, 0, "NPC位置", 15718814, (byte) 1, (byte) 0);
            UIRim rimDown = new UIRim(0, 30, 160, 153, (byte) 0);
            int tempcount = npcPosXY.length;
            npcPOSTables = new UITable(0, 31, 160, 158, tempcount, 1, (tempcount > 10) ? 10 : tempcount, (byte) 0, (byte) 3);
            npcPOSTables.setAutoHeight(true);
            UILabel label2 = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 0, (byte) 0);
            UILabel label1 = new UILabel(0, 0, 0, 0, "移动", 15718815, (byte) 0, (byte) 0);
            for (int i = 0; i < tempcount; i++) {
                npcPOSTables.addItem(npcPosXY[i], 15718815);
            }
            baseForm.addComponent(frame);
            baseForm.addComponentInCenter(rimDown, (byte) 2);
            baseForm.addComponentInCenter(rimTitle, (byte) 2);
            baseForm.addComponentInCenter(lblTitle, (byte) 2);
            baseForm.addComponentInCenter(label2, (byte) 6);
            baseForm.addComponentInCenter(label1, (byte) 5);
            baseForm.addComponentInCenter(npcPOSTables, (byte) 2);
            npcPOSTables.setXY(npcPOSTables.positionX - 1, npcPOSTables.positionY);
            baseForm.setFocus(true);
        }
        baseForm.draw(g);
    }

    private void mapRunSubTick() {
        Player player = Player.getInstance();
        Map.getInstance().adjustWindow(player.x, player.y);
        ObjManager.getInstance().tick();
    }
    
    public static MImage[] mImgUI = new MImage[40];
    public static MImage mImgStuff;
    public static MImage mImgSelect;
    public static MImage imgRedNum;
    public static MImage imgWhiteNum;
    public static MImage imgGreenNum;
    public static MImage imgYellowNum;
    public static MImage imgPlayerArrow;
    public static MImage imgMarryArrow;
    int[] keys;
    public static final byte PACKAGE_CELL_NUM = 6;
    public static final byte SUB_BSNS_SEND_BEGIN_MSG = 9;
    public static final byte SUB_BSNS_MAIN = 0;
    public static final byte SUB_BSNS_WAIT_OTHER = 2;
    public static final byte SUB_BSNS_EXIT = 3;
    public static final byte SUB_BSNS_CHOICE = 4;
    public static final byte SUB_BSNS_WAIT = 100;
    public static final byte SUB_BSNS_REFUSE = 50;
    public byte subBsnsState;
    public boolean isLock;
    public boolean isOtherLock;
    public byte[] bsnsMyPackage;
    public byte[] bsnsSinalNumber;
    public byte[] bsnsMyPackageNUM;
    public String bsnsOtherName;
    public int bsnsOtherID;
    public boolean bsnsIsInitiate;
    public int bznsMyMoney;
    public int bznsOtherMoney;
    public byte bsOtherImID;
    public static final byte AUCTION_CAN_SELL = -10;
    public static final byte AUCTION_CAN_SELL_WAIT = -11;
    public static final byte AUCTION_FIRST_CHOICE = -1;
    public static final byte AUCTION_SHOW_HANG = -2;
    public static final byte AUCTION_WAIT_HANG = -3;
    public static final byte AUCTION_SELL = 0;
    public static final byte AUCTION_VIEW_CHOICE_TYPE = 1;
    public static final byte AUCTION_BUY_SHOW_TYPE = 11;
    public static final byte AUCTION_BUY_FIND_TYPE_WAIT = 10;
    public static final byte AUCTION_BUY = 2;
    public static final byte AUCTION_BUY_WAIT_RESPOND = 3;
    public static final byte AUCTION_EXIT = 4;
    public static final byte TYPE_ALL = 0;
    public static final byte TYPE_BLADE = 1;
    public static final byte TYPE_SWORD = 2;
    public static final byte TYPE_AXE = 3;
    public static final byte TYPE_STAFF = 4;
    public static final byte TYPE_PIG = 5;
    public static final byte TYPE_ARMENT = 6;
    public static final byte TYPE_LORICAE = 7;
    public static final byte TYPE_CUISSE = 8;
    public static final byte TYPE_SHOES = 9;
    public static final byte TYPE_NECK = 10;
    public static final byte TYPE_STONE = 11;
    public static final byte TYPE_MINE = 12;
    public static final byte TYPE_ORE = 13;
    public static final byte TYPE_TEXTITLE = 14;
    public static final byte TYPE_MATERIAL = 15;
    public static final byte TYPE_MEDICINE = 16;
    public static final byte TYPE_MY_ITEM = 17;

    public void loadResource() {
    }

    public boolean actionInForm(UIComponent cmd) {
        if (cmd == null) {
            return false;
        }
        if (isKeyPress(13)) {
            baseForm.setDirectionFocus(13);
            for (byte i = 0; i < 8; i = (byte) (i + 1)) {
                if (baseForm.getCommand() == rims[i]) {
                    manIndex = i;
                    break;
                }
            }
            if (isSelect != null) {
                for (int j = 0; j < 8; j++) {
                    if (j == manIndex) {
                        isSelect[j] = true;
                    } else {
                        isSelect[j] = false;
                    }
                }
                UIGameRun.creatFrameIndex = 0;
            }
            return true;
        }
        if (isKeyPress(11)) {
            baseForm.setDirectionFocus(11);
            for (byte i = 0; i < 8; i = (byte) (i + 1)) {
                if (baseForm.getCommand() == rims[i]) {
                    manIndex = i;
                    break;
                }
            }
            if (isSelect != null) {
                for (int j = 0; j < 8; j++) {
                    if (j == manIndex) {
                        isSelect[j] = true;
                    } else {
                        isSelect[j] = false;
                    }
                }
                UIGameRun.creatFrameIndex = 0;
            }
            return true;
        }
        if (isKeyPress(10)) {
            baseForm.setDirectionFocus(10);
            for (byte i = 0; i < 8; i = (byte) (i + 1)) {
                if (baseForm.getCommand() == rims[i]) {
                    manIndex = i;
                    break;
                }
            }
            if (isSelect != null) {
                for (int j = 0; j < 8; j++) {
                    if (j == manIndex) {
                        isSelect[j] = true;
                    } else {
                        isSelect[j] = false;
                    }
                }
                UIGameRun.creatFrameIndex = 0;
            }
            if (isSelect1 != null && grids[0] != null) {
                for (int j = 0; j < 3; j++) {
                    if (j == grids[0].getCurrentPointer()) {
                        isSelect1[j] = true;
                    } else {
                        isSelect1[j] = false;
                    }
                }
                UIGameRun.creatFrameIndex = 0;
            }
            return true;
        }
        if (isKeyPress(12)) {
            baseForm.setDirectionFocus(12);
            for (byte i = 0; i < 8; i = (byte) (i + 1)) {
                if (baseForm.getCommand() == rims[i]) {
                    manIndex = i;
                    break;
                }
            }
            if (isSelect != null) {
                for (int j = 0; j < 8; j++) {
                    if (j == manIndex) {
                        isSelect[j] = true;
                    } else {
                        isSelect[j] = false;
                    }
                }
                UIGameRun.creatFrameIndex = 0;
            }
            if (isSelect1 != null && grids[0] != null) {
                for (int j = 0; j < 3; j++) {
                    if (j == grids[0].getCurrentPointer()) {
                        isSelect1[j] = true;
                    } else {
                        isSelect1[j] = false;
                    }
                }
                UIGameRun.creatFrameIndex = 0;
            }
            return true;
        }
        if (cmd instanceof UIText) {
            UIText temp = (UIText) cmd;
            for (int i = 0; i < keys.length; i++) {
                if (isKeyPress(keys[i])) {
                    temp.pushKey(keys[i]);
                    return true;
                }
                if (isKeyPress(20)) {
                    temp.removeChar();
                    resetKey();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean actionInForm(UIComponent cmd, UIForm baseForm) {
        if (cmd == null) {
            return false;
        }
        if (isKeyPress(13)) {
            baseForm.setDirectionFocus(13);
            for (byte i = 0; i < 8; i = (byte) (i + 1)) {
                if (baseForm.getCommand() == rims[i]) {
                    manIndex = i;
                    break;
                }
            }
            if (isSelect != null) {
                for (int j = 0; j < 8; j++) {
                    if (j == manIndex) {
                        isSelect[j] = true;
                    } else {
                        isSelect[j] = false;
                    }
                }
                UIGameRun.creatFrameIndex = 0;
            }
            return true;
        }
        if (isKeyPress(11)) {
            baseForm.setDirectionFocus(11);
            for (byte i = 0; i < 8; i = (byte) (i + 1)) {
                if (baseForm.getCommand() == rims[i]) {
                    manIndex = i;
                    break;
                }
            }
            if (isSelect != null) {
                for (int j = 0; j < 8; j++) {
                    if (j == manIndex) {
                        isSelect[j] = true;
                    } else {
                        isSelect[j] = false;
                    }
                }
                UIGameRun.creatFrameIndex = 0;
            }
            return true;
        }
        if (isKeyPress(10)) {
            baseForm.setDirectionFocus(10);
            for (byte i = 0; i < 8; i = (byte) (i + 1)) {
                if (baseForm.getCommand() == rims[i]) {
                    manIndex = i;
                    break;
                }
            }
            if (isSelect != null) {
                for (int j = 0; j < 8; j++) {
                    if (j == manIndex) {
                        isSelect[j] = true;
                    } else {
                        isSelect[j] = false;
                    }
                }
                UIGameRun.creatFrameIndex = 0;
            }
            if (isSelect1 != null && grids[0] != null) {
                for (int j = 0; j < 3; j++) {
                    if (j == grids[0].getCurrentPointer()) {
                        isSelect1[j] = true;
                    } else {
                        isSelect1[j] = false;
                    }
                }
                UIGameRun.creatFrameIndex = 0;
            }
            return true;
        }
        if (isKeyPress(12)) {
            baseForm.setDirectionFocus(12);
            for (byte i = 0; i < 8; i = (byte) (i + 1)) {
                if (baseForm.getCommand() == rims[i]) {
                    manIndex = i;
                    break;
                }
            }
            if (isSelect != null) {
                for (int j = 0; j < 8; j++) {
                    if (j == manIndex) {
                        isSelect[j] = true;
                    } else {
                        isSelect[j] = false;
                    }
                }
                UIGameRun.creatFrameIndex = 0;
            }
            if (isSelect1 != null && grids[0] != null) {
                for (int j = 0; j < 3; j++) {
                    if (j == grids[0].getCurrentPointer()) {
                        isSelect1[j] = true;
                    } else {
                        isSelect1[j] = false;
                    }
                }
                UIGameRun.creatFrameIndex = 0;
            }
            return true;
        }
        if (cmd instanceof UIText) {
            UIText temp = (UIText) cmd;
            for (int i = 0; i < keys.length; i++) {
                if (isKeyPress(keys[i])) {
                    temp.pushKey(keys[i]);
                    return true;
                }
                if (isKeyPress(20)) {
                    temp.removeChar();
                    resetKey();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean actionInFormMenu(UIComponent cmd) {
        if (cmd == null) {
            return false;
        }
        if (isKeyPress(13) || isKeyPress(8)) {
            baseForm.setDirectionFocus(13);
            for (byte i = 0; i < 8; i = (byte) (i + 1)) {
                if (baseForm.getCommand() == rims[i]) {
                    manIndex = i;
                    break;
                }
            }
            if (isSelect != null) {
                for (int j = 0; j < 8; j++) {
                    if (j == manIndex) {
                        isSelect[j] = true;
                    } else {
                        isSelect[j] = false;
                    }
                }
                UIGameRun.creatFrameIndex = 0;
            }
            return true;
        }
        if (isKeyPress(11) || isKeyPress(2)) {
            baseForm.setDirectionFocus(11);
            for (byte i = 0; i < 8; i = (byte) (i + 1)) {
                if (baseForm.getCommand() == rims[i]) {
                    manIndex = i;
                    break;
                }
            }
            if (isSelect != null) {
                for (int j = 0; j < 8; j++) {
                    if (j == manIndex) {
                        isSelect[j] = true;
                    } else {
                        isSelect[j] = false;
                    }
                }
                UIGameRun.creatFrameIndex = 0;
            }
            return true;
        }
        if (isKeyPress(10) || isKeyPress(4)) {
            baseForm.setDirectionFocus(10);
            for (byte i = 0; i < 8; i = (byte) (i + 1)) {
                if (baseForm.getCommand() == rims[i]) {
                    manIndex = i;
                    break;
                }
            }
            if (isSelect != null) {
                for (int j = 0; j < 8; j++) {
                    if (j == manIndex) {
                        isSelect[j] = true;
                    } else {
                        isSelect[j] = false;
                    }
                }
                UIGameRun.creatFrameIndex = 0;
            }
            if (isSelect1 != null && grids[0] != null) {
                for (int j = 0; j < 3; j++) {
                    if (j == grids[0].getCurrentPointer()) {
                        isSelect1[j] = true;
                    } else {
                        isSelect1[j] = false;
                    }
                }
                UIGameRun.creatFrameIndex = 0;
            }
            return true;
        }
        if (isKeyPress(12) || isKeyPress(6)) {
            baseForm.setDirectionFocus(12);
            for (byte i = 0; i < 8; i = (byte) (i + 1)) {
                if (baseForm.getCommand() == rims[i]) {
                    manIndex = i;
                    break;
                }
            }
            if (isSelect != null) {
                for (int j = 0; j < 8; j++) {
                    if (j == manIndex) {
                        isSelect[j] = true;
                    } else {
                        isSelect[j] = false;
                    }
                }
                UIGameRun.creatFrameIndex = 0;
            }
            if (isSelect1 != null && grids[0] != null) {
                for (int j = 0; j < 3; j++) {
                    if (j == grids[0].getCurrentPointer()) {
                        isSelect1[j] = true;
                    } else {
                        isSelect1[j] = false;
                    }
                }
                UIGameRun.creatFrameIndex = 0;
            }
            return true;
        }
        if (cmd instanceof UIText) {
            UIText temp = (UIText) cmd;
            for (int i = 0; i < keys.length; i++) {
                if (isKeyPress(keys[i])) {
                    temp.pushKey(keys[i]);
                    return true;
                }
                if (isKeyPress(20)) {
                    temp.removeChar();
                    resetKey();
                    return true;
                }
            }
        }
        return false;
    }

    private void drawErrorInfor(Graphics g, String str, String leftString) {
        if (baseForm == null) {
            String string = str;
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setStyle((byte) 0);
            baseForm.setBackGround((byte) 8);
            labels[0] = new UILabel(0, 50, 100, 0, string, 15719326, (byte) 1, (byte) 0);
            labels[1] = new UILabel(0, 0, 0, 0, leftString, 15719326, (byte) 1, (byte) 0);
            rims[0] = new UIRim(0, 40, 120, 80, (byte) 4);
            UIRim bigRim = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            baseForm.addComponent(bigRim);
            baseForm.addComponentInCenter(rims[0], (byte) 2);
            baseForm.addComponentInCenter(labels[0], (byte) 2);
            baseForm.addComponentInCenter(labels[1], (byte) 5);
        }
        baseForm.draw(g);
    }

    public void releaseUI() {
        baseForm = null;
        int i;
        for (i = 0; i < buttons.length; i++) {
            buttons[i] = null;
        }
        for (i = 0; i < grids.length; i++) {
            grids[i] = null;
        }
        for (i = 0; i < labels.length; i++) {
            labels[i] = null;
        }
        for (i = 0; i < menus.length; i++) {
            menus[i] = null;
        }
        for (i = 0; i < mImages.length; i++) {
            mImages[i] = null;
        }
        for (i = 0; i < rbs.length; i++) {
            rbs[i] = null;
        }
        for (i = 0; i < rims.length; i++) {
            rims[i] = null;
        }
        for (i = 0; i < scrolls.length; i++) {
            scrolls[i] = null;
        }
        for (i = 0; i < tables.length; i++) {
            tables[i] = null;
        }
        for (i = 0; i < texts.length; i++) {
            texts[i] = null;
        }
        for (i = 0; i < texts.length; i++) {
            texts[i] = null;
        }
        for (i = 0; i < textArea.length; i++) {
            textArea[i] = null;
        }
        dramatisPackage.leftComponent = null;
        dramatisPackage.rightComponent = null;
        dramatisPackage.upComponent = null;
        dramatisPackage.downComponent = null;
        System.gc();
    }

    private void tickBusiness() {
        UIComponent cmd;
        if (topForm != null || isPop) {
            return;
        }
        switch (subBsnsState) {
            case 4:
                if (isKeyPress1(17)) {
                    ni.send(218104064);
                    setBusinessState(100);
                    releaseUI();
                }
                bsnsCancel();
                break;
            case 9:
                bsnsIsInitiate = true;
                ni.send(218104064);
                setBusinessState(2);
                break;
            case 2:
                bsnsCancel();
                break;
            case 0:
                if (baseForm == null) {
                    return;
                }
                cmd = baseForm.getCommand();
                if (isKeyPress1(17) || isKeyPress1(14)) {
                    if (baseForm.getCurrentFocusForm() == baseForm) {
                        if (dramatisPackage.isLock) {
                            if (dramatisPackage.stuffPlace != dramatisPackage.getCurrentPointer()) {
                                for (int i = 0; i < bsnsMyPackage.length; i++) {
                                    if (dramatisPackage.getCurrentPointer() == bsnsMyPackage[i]) {
                                        setMessage(baseForm.getCurrentFocusForm(), "您不能向已放置交易的空格内拆分");
                                        dramatisPackage.isLock = false;
                                        return;
                                    }
                                }
                                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 30);
                                ni.send(67109632);
                                break;
                            }
                            dramatisPackage.isLock = false;
                            break;
                        }
                        UIForm subForm = new UIForm(0, 0, screenW - 1, screenH - 1, "menu");
                        subForm.setBackGround((byte) 9);
                        menus[0] = null;
                        menus[0] = new UIMenu(34, 110, 60, 0, null, Cons.BUSINESS_MENU);
                        subForm.addComponent(menus[0]);
                        (menus[0]).positionY = screenH - (menus[0]).height - 4;
                        baseForm.setAboutForm(subForm);
                        baseForm.setFocusComponentFocus(true);
                        if (cmd == texts[0]) {
                            if (isLock) {
                                menus[0].setNoUse((byte) 0);
                                menus[0].setNoUse((byte) 1);
                                menus[0].setNoUse((byte) 2);
                                menus[0].setNoUse((byte) 3);
                                menus[0].setNoUse((byte) 6);
                                menus[0].setNoUse((byte) 7);
                                break;
                            }
                            menus[0].setNoUse((byte) 0);
                            menus[0].setNoUse((byte) 1);
                            menus[0].setNoUse((byte) 6);
                            menus[0].setNoUse((byte) 7);
                            break;
                        }
                        if (cmd == grids[0]) {
                            if (isLock) {
                                menus[0].setNoUse((byte) 1);
                                menus[0].setNoUse((byte) 2);
                                menus[0].setNoUse((byte) 3);
                                menus[0].setNoUse((byte) 4);
                                menus[0].setNoUse((byte) 6);
                                menus[0].setNoUse((byte) 7);
                                if (grids[0].getCurrentNumber() == 0) {
                                    menus[0].setNoUse((byte) 0);
                                }
                                break;
                            }
                            menus[0].setNoUse((byte) 1);
                            menus[0].setNoUse((byte) 6);
                            menus[0].setNoUse((byte) 7);
                            if (grids[0].getCurrentId() == 0) {
                                menus[0].setNoUse((byte) 0);
                                menus[0].setNoUse((byte) 2);
                            }
                            break;
                        }
                        if (cmd == grids[1]) {
                            if (isLock) {
                                menus[0].setNoUse((byte) 1);
                                menus[0].setNoUse((byte) 2);
                                menus[0].setNoUse((byte) 3);
                                menus[0].setNoUse((byte) 4);
                                menus[0].setNoUse((byte) 6);
                                menus[0].setNoUse((byte) 7);
                                if (grids[1].getCurrentNumber() == 0) {
                                    menus[0].setNoUse((byte) 0);
                                }
                                break;
                            }
                            menus[0].setNoUse((byte) 1);
                            menus[0].setNoUse((byte) 6);
                            menus[0].setNoUse((byte) 2);
                            menus[0].setNoUse((byte) 7);
                            if (grids[1].getCurrentNumber() == 0) {
                                menus[0].setNoUse((byte) 0);
                            }
                            break;
                        }
                        if (cmd == dramatisPackage) {
                            if (isLock) {
                                menus[0].setNoUse((byte) 1);
                                menus[0].setNoUse((byte) 2);
                                menus[0].setNoUse((byte) 3);
                                menus[0].setNoUse((byte) 4);
                                menus[0].setNoUse((byte) 7);
                                if (dramatisPackage.getCurrentId() == 0) {
                                    menus[0].setNoUse((byte) 0);
                                    menus[0].setNoUse((byte) 6);
                                }
                                break;
                            }
                            menus[0].setNoUse((byte) 2);
                            if (dramatisPackage.getCurrentId() == 0) {
                                menus[0].setNoUse((byte) 0);
                                menus[0].setNoUse((byte) 1);
                                menus[0].setNoUse((byte) 6);
                                menus[0].setNoUse((byte) 7);
                                break;
                            }
                            if (dramatisPackage.getCurrentNumber() == 1) {
                                menus[0].setNoUse((byte) 7);
                            }
                        }
                        break;
                    }
                    if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if ("discard".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                        isPackage = 1;
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        ni.send(67110912);
                        break;
                    }
                    if ("menu".equals(baseForm.getCurrentFocusForm().getName())) {
                        byte i;
                        switch (menus[0].getCurrentPointer()) {
                            case 0:
                                if (baseForm.focusComponent == dramatisPackage) {
                                    baseForm.setAboutForm((UIForm) null);
                                    baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                    lookStuffPlace = (short) dramatisPackage.getCurrentPointer();
                                    isPackage = 1;
                                    lookType = 1;
                                    ni.send(67110656);
                                    break;
                                }
                                if (baseForm.focusComponent == grids[0]) {
                                    baseForm.setAboutForm((UIForm) null);
                                    baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                    if (isLock) {
                                        lookStuffPlace = (short) (byte) (grids[0].getCurrentPointer() + 10);
                                        ni.send(218105088);
                                        break;
                                    }
                                    lookStuffPlace = (short) bsnsMyPackage[grids[0].getCurrentPointer()];
                                    isPackage = 1;
                                    lookType = 1;
                                    ni.send(67110656);
                                    break;
                                }
                                if (baseForm.focusComponent == grids[1]) {
                                    baseForm.setAboutForm((UIForm) null);
                                    baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                    lookStuffPlace = (short) grids[1].getCurrentPointer();
                                    ni.send(218105088);
                                }
                                break;
                            case 1:
                                baseForm.setAboutForm((UIForm) null);
                                if (!dramatisPackage.isCanToOther()) {
                                    setMessage(baseForm, "此物品已绑定，不能交易");
                                } else if (dramatisPackage.getCurrentCanTrade()) {
                                    if (grids[0].getCurrentNullPlace() == -1) {
                                        setMessage(baseForm, "不能再放入更多的物品");
                                    } else {
                                        byte temp = grids[0].getCurrentNullPlace();
                                        bsnsMyPackage[temp] = dramatisPackage.getCurrentPointer();
                                        bsnsSinalNumber[temp] = dramatisPackage.getCurrentNumber();
                                        grids[0].setGridDetail(temp, dramatisPackage.getCurrentId(), dramatisPackage.getCurrentImageId(), dramatisPackage.getCurrentNumber(), dramatisPackage.getCurrentName(), dramatisPackage.getCurrentNameLevel(), dramatisPackage.getCurrentLittleType(), dramatisPackage.getCurrentTrade(), (byte) 0);
                                        for (int j = 0; j < 6; j++) {
                                            if ((grids[0]).stuffId[j] == 0) {
                                                bsnsMyPackage[j] = 100;
                                            }
                                        }
                                        dramatisPackage.setCurrentNull();
                                        labels[2].setStr(" ");
                                    }
                                } else {
                                    setMessage(baseForm, "此物品已绑定，不能交易");
                                }
                                unLock();
                                break;
                            case 2:
                                if (baseForm.focusComponent == grids[0]) {
                                    if (dramatisPackage.stuffNumber[bsnsMyPackage[grids[0].getCurrentPointer()]] > 0) {
                                        dramatisPackage.stuffNumber[bsnsMyPackage[grids[0].getCurrentPointer()]] = (byte) (dramatisPackage.stuffNumber[bsnsMyPackage[grids[0].getCurrentPointer()]] + grids[0].getCurrentNumber());
                                    } else {
                                        dramatisPackage.setGridDetail(bsnsMyPackage[grids[0].getCurrentPointer()], grids[0].getCurrentId(), grids[0].getCurrentImageId(), grids[0].getCurrentNumber(), grids[0].getCurrentName(), grids[0].getCurrentNameLevel(), grids[0].getCurrentLittleType(), grids[0].getCurrentTrade(), (byte) 0);
                                    }
                                    grids[0].setCurrentNull();
                                    labels[2].setStr(" ");
                                    bsnsMyPackage[grids[0].getCurrentPointer()] = 100;
                                    baseForm.setAboutForm((UIForm) null);
                                    unLock();
                                    break;
                                }
                                if (baseForm.focusComponent == texts[0]) {
                                    texts[0].setLabel("0");
                                    texts[2].setLabel("" + (Player.getInstance()).money);
                                    baseForm.setAboutForm((UIForm) null);
                                }
                                break;
                            case 3:
                                mImages[0].setCurrentFrame((byte) 0);
                                bznsMyMoney = texts[0].getNumber();
                                ni.send(218104320);
                                baseForm.setAboutForm((UIForm) null);
                                (grids[0]).upComponent = null;
                                if (baseForm.focusComponent == texts[0]) {
                                    baseForm.setComponentFocus(grids[0]);
                                }
                                texts[2].setLabel(((Player.getInstance()).money - texts[0].getNumber()) + "");
                                break;
                            case 4:
                                texts[0].setLabel("0");
                                for (i = 0; i < 6; i = (byte) (i + 1)) {
                                    if ((grids[0]).stuffId[i] == 0) {
                                        bsnsMyPackage[i] = 100;
                                    } else {
                                        if (dramatisPackage.stuffId[bsnsMyPackage[i]] == 0) {
                                            dramatisPackage.setGridDetail(bsnsMyPackage[i], (grids[0]).stuffId[i], (grids[0]).stuffImageId[i], (grids[0]).stuffNumber[i], (grids[0]).stuffName[i], (grids[0]).stuffLevel[i], (grids[0]).stuffType[i], (grids[0]).isCanTrade[i], (byte) 0);
                                            bsnsMyPackage[i] = 100;
                                        } else {
                                            dramatisPackage.stuffNumber[bsnsMyPackage[i]] = (byte) (dramatisPackage.stuffNumber[bsnsMyPackage[i]] + (grids[0]).stuffNumber[i]);
                                        }
                                        (grids[0]).stuffId[i] = 0;
                                        (grids[0]).stuffImageId[i] = 0;
                                        (grids[0]).stuffNumber[i] = 0;
                                        (grids[0]).stuffName[i] = "";
                                        bsnsMyPackage[i] = 100;
                                    }
                                }
                                texts[2].setLabel("" + (Player.getInstance()).money);
                                baseForm.setAboutForm((UIForm) null);
                                unLock();
                                break;
                            case 5:
                                if (!isLock) {
                                    baseForm.setAboutForm((UIForm) null);
                                    setMessage(baseForm, "还未锁定！");
                                    break;
                                }
                                if (!isOtherLock) {
                                    baseForm.setAboutForm((UIForm) null);
                                    setMessage(baseForm, "对方还未锁定！");
                                    break;
                                }
                                ni.send(218104576);
                                baseForm.setAboutForm((UIForm) null);
                                baseForm.addAboutForm("waiting", "等待对方交易...", (byte) 0, screenW - 50, 0);
                                break;
                            case 6:
                                baseForm.addAboutForm("discard", "确定要丢弃该物品吗？", (byte) 2, screenW - 30, 0);
                                break;
                            case 7:
                                if (dramatisPackage.getCurrentNumber() == 1) {
                                    stNumber = dramatisPackage.getCurrentNumber();
                                    dramatisPackage.isLock = true;
                                    dramatisPackage.stuffPlace = dramatisPackage.getCurrentPointer();
                                    labels[2].setStr("请选择移动位置");
                                    labels[2].setColor(16777215);
                                    baseForm.setAboutForm((UIForm) null);
                                    break;
                                }
                                texts[2] = null;
                                texts[2] = new UIText(0, 25, 80, 0, 10, (byte) 2, dramatisPackage.getCurrentNumber() + "");
                                texts[2].setMaxNumber(dramatisPackage.getCurrentNumber());
                                baseForm.setAboutForm((UIForm) null);
                                baseForm.addInputForm("move_item", "请输入移动数量", texts[2], 100);
                                break;
                        }
                        break;
                    }
                    if (baseForm.getCurrentFocusForm().getName().equals("exit")) {
                        setBusinessState(3);
                        break;
                    }
                    if ("move_item".equals(baseForm.getCurrentFocusForm().getName())) {
                        stNumber = (byte) texts[2].getNumber();
                        baseForm.setAboutForm((UIForm) null);
                        if (stNumber == 0) {
                            return;
                        }
                        dramatisPackage.isLock = true;
                        dramatisPackage.stuffPlace = dramatisPackage.getCurrentPointer();
                        labels[2].setStr("请选择移动位置");
                        labels[2].setColor(16777215);
                        dramatisPackage.setSubMenu((UIMenu) null);
                    }
                    break;
                }
                if (isKeyPress1(18)) {
                    if (baseForm.getSubForm() == null) {
                        baseForm = null;
                        ni.send(218104832);
                        setBusinessState(3);
                        setGameState((byte) 0);
                        break;
                    }
                    if ("menu".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if ("input".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if ("discard".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if ("detail".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if ("move_item".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                    }
                    break;
                }
                if (actionInForm(cmd)) {
                    if (baseForm.focusComponent == grids[0]) {
                        labels[2].setColor(Cons.STUFF_NAME_COLOR[grids[0].getCurrentNameLevel()]);
                        labels[2].setStr(grids[0].getCurrentName());
                        break;
                    }
                    if (baseForm.focusComponent == dramatisPackage) {
                        labels[2].setColor(Cons.STUFF_NAME_COLOR[dramatisPackage.getCurrentNameLevel()]);
                        labels[2].setStr(dramatisPackage.getCurrentName());
                        break;
                    }
                    if (baseForm.focusComponent == texts[0]) {
                        texts[2].setLabel(((Player.getInstance()).money - texts[0].getNumber()) + "");
                    }
                }
                break;
        }
    }

    private void bsnsCancel() {
        if (isKeyPress1(18)) {
            ni.send(218104832);
            setBusinessState(3);
        }
    }

    private void unLock() {
    }

    public void setBusinessState(int state) {
        int i;
        subBsnsState = (byte) state;
        switch (subBsnsState) {
            case 100:
                ni.send(67109120);
                break;
            case 3:
                subBsnsState = -1;
                isLock = false;
                isOtherLock = false;
                bsnsMyPackageNUM = new byte[0];
                bsnsOtherName = null;
                bsnsOtherID = 0;
                bsnsIsInitiate = false;
                bznsMyMoney = 0;
                bznsOtherMoney = 0;
                bsnsOtherID = -1;
                for (i = bsnsMyPackage.length - 1; i >= 0; i--) {
                    bsnsMyPackage[i] = 100;
                }
                setGameState((byte) 0);
                setLeftMenuSubState(-1);
                break;
        }
    }
    public static boolean haveFormula = false;
    public static int auctionPrice = 0;
    public static Image tjImage;
    public static short[][] tjFrameData;
    public static short[] tjPicData;
    public static short[][] tjMotionDataAll;

    private void tickAuction() {
        if (topForm != null) {
            return;
        }
        if (baseForm == null) {
            return;
        }
        UIComponent cmd = baseForm.getCommand();
        switch (subAuction) {
            case -10:
                packageSend = 7;
                ni.send(67109120);
                setAuctionState((byte) -11);
                break;
            case 11:
                if (isKeyPress1(18)) {
                    if (menus[0].getSubMenu() == null) {
                        setNPCSubState((byte) 0);
                        NPCMenu.setSubMenu((UIMenu) null);
                        releaseUI();
                        break;
                    }
                    menus[0].setSubMenu((UIMenu) null);
                    break;
                }
                if (!actionInForm(cmd) && cmd == menus[0] && cmd != null && (isKeyPress1(14) || isKeyPress1(17))) {
                    if (menus[0].getSubMenu() == null) {
                        switch (menus[0].getCurrentPointer()) {
                            case 0:
                                typeID = 0;
                                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                ni.send(234881536);
                                break;
                            case 1:
                                if (menus[0].getSubMenu() == null) {
                                    String[] ss = {"刀", "剑", "斧", "杖", "珠"};
                                    menus[1] = new UIMenu(0, 0, 50, 0, null, ss);
                                    menus[0].setSubMenu(menus[1]);
                                }
                                break;
                            case 2:
                                if (menus[0].getSubMenu() == null) {
                                    String[] ss = {"头盔", "铠甲", "护腿", "鞋", "饰品"};
                                    menus[1] = new UIMenu(0, 0, 60, 0, null, ss);
                                    menus[0].setSubMenu(menus[1]);
                                }
                                break;
                            case 3:
                                typeID = 15;
                                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                ni.send(234881536);
                                break;
                            case 4:
                                typeID = 16;
                                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                ni.send(234881536);
                                break;
                        }
                        break;
                    }
                    switch (menus[0].getCurrentPointer()) {
                        case 1:
                            typeID = (byte) (1 + menus[1].getCurrentPointer());
                            break;
                        case 2:
                            typeID = (byte) (6 + menus[1].getCurrentPointer());
                            break;
                        case 3:
                            typeID = (byte) (11 + menus[1].getCurrentPointer());
                            break;
                        case 4:
                            typeID = (byte) (15 + menus[1].getCurrentPointer());
                            break;
                    }
                    baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                    ni.send(234881536);
                }
                break;
            case -1:
                if (isKeyPress(18)) {
                    setNPCSubState((byte) 0);
                    NPCMenu.setSubMenu((UIMenu) null);
                    releaseUI();
                    break;
                }
                if (!actionInForm(cmd) && cmd == NPCMenu && NPCMenu.getSubMenu() != null && (isKeyPress1(14) || isKeyPress1(17))) {
                    switch (menus[1].getCurrentPointer()) {
                        case 1:
                            if (!haveFormula) {
                                baseForm.getCurrentFocusForm().addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                                ni.send(234883072);
                                haveFormula = true;
                            }
                            setAuctionState((byte) 0);
                            releaseUI();
                            break;
                        case 2:
                            baseForm.getCurrentFocusForm().addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                            typeID = 17;
                            ni.send(234881536);
                            break;
                        case 0:
                            releaseUI();
                            setAuctionState((byte) 11);
                            break;
                    }
                }
                break;
            case 10:
                setAuctionState((byte) 1);
                break;
            case 0:
                if (baseForm.getCurrentFocusForm() == baseForm && (isKeyPress1(14) || isKeyPress1(17))) {
                    String[] strs = {"查看属性", "选择物品", "确定拍卖", "取回物品"};
                    menus[0] = null;
                    int h = strs.length * 17;
                    h -= 5;
                    menus[0] = new UIMenu(32, 190 - h, 75, h, null, strs);
                    UIForm subForm = new UIForm(0, 0, screenW - 1, screenH - 1, "menu");
                    subForm.setBackGround((byte) 9);
                    subForm.addComponent(menus[0]);
                    baseForm.setAboutForm(subForm);
                    if (cmd != dramatisPackage || dramatisPackage.getCurrentId() == 0) {
                        menus[0].setNoUse((byte) 0);
                        menus[0].setNoUse((byte) 1);
                    }
                    if (mImages[0].getCurrentFrame() < 0) {
                        menus[0].setNoUse((byte) 3);
                    }
                    break;
                }
                if ("confirm".equals(baseForm.getCurrentFocusForm().getName()) && (isKeyPress1(14) || isKeyPress1(17))) {
                    baseForm.setAboutForm((UIForm) null);
                    String errMsg = null;
                    if (auctionIndex < 0 || mImages[0].getNumber() < 1) {
                        errMsg = "请选择拍卖物品！";
                    } else if (texts[0].getNumber() == 0) {
                        errMsg = "竞拍价格不能为零！";
                    } else if (texts[1].getNumber() == 0) {
                        errMsg = "一口价不能为零！";
                    } else if (texts[0].getNumber() > texts[1].getNumber()) {
                        errMsg = "一口价不能小于竞拍价！";
                    }
                    if (errMsg != null) {
                        setMessage(baseForm, errMsg);
                        break;
                    }
                    baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                    ni.send(234881280);
                    break;
                }
                if ("msg".equals(baseForm.getCurrentFocusForm().getName()) && (isKeyPress1(14) || isKeyPress1(17))) {
                    baseForm.setAboutForm((UIForm) null);
                    break;
                }
                if ("quantity".equals(baseForm.getCurrentFocusForm().getName()) && (isKeyPress1(14) || isKeyPress1(17))) {
                    baseForm.setAboutForm((UIForm) null);
                    if (texts[3].getNumber() > 0) {
                        labels[0].setStr(dramatisPackage.getCurrentName());
                        labels[0].setColor(Cons.STUFF_NAME_COLOR[dramatisPackage.getCurrentNameLevel()]);
                        mImages[0].setCurrentFrame((byte) (dramatisPackage.getCurrentImageId() - 1));
                        mImages[0].setNumber((byte) texts[3].getNumber());
                        mImages[0].setNumberVisiable(true);
                        baseForm.setComponentFocus(mImages[0]);
                        auctionIndex = dramatisPackage.getCurrentPointer();
                        rbs[0].setAroundComponent(dramatisPackage, (byte) 2);
                        texts[0].setLabel("0");
                        texts[1].setLabel("0");
                        rbs[0].setChooseItem(0);
                    }
                    break;
                }
                if ("result".equals(baseForm.getCurrentFocusForm().getName()) && (isKeyPress1(14) || isKeyPress1(17))) {
                    ni.send(234882048);
                    baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
                    baseForm.setComponentFocus(dramatisPackage);
                    rbs[0].setAroundComponent(null, (byte) 2);
                    dramatisPackage.setAroundComponent(null, (byte) 1);
                    labels[1].setStr(dramatisPackage.getCurrentName());
                    labels[1].setColor(Cons.STUFF_NAME_COLOR[dramatisPackage.getCurrentNameLevel()]);
                    mImages[0].setCurrentFrame((byte) -1);
                    mImages[0].setNumber((byte) 0);
                    labels[0].setStr((String) null);
                    texts[0].setLabel("0");
                    texts[1].setLabel("0");
                    rbs[0].setChooseItem(0);
                    break;
                }
                if (isKeyPress1(18)) {
                    if (baseForm.getCurrentFocusForm() == baseForm) {
                        txtMoney = null;
                        setNPCSubState((byte) 0);
                        NPCMenu.setSubMenu((UIMenu) null);
                        releaseUI();
                        break;
                    }
                    if ("menu".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if ("confirm".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
                        break;
                    }
                    if ("quantity".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if ("detail".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                    }
                    break;
                }
                if (actionInForm(cmd)) {
                    if (cmd == dramatisPackage) {
                        labels[1].setStr(dramatisPackage.getCurrentName());
                        labels[1].setColor(Cons.STUFF_NAME_COLOR[dramatisPackage.getCurrentNameLevel()]);
                    }
                    break;
                }
                if (baseForm.getCurrentFocusForm().getName().equals("menu") && (isKeyPress1(14) || isKeyPress1(17))) {
                    switch (menus[0].getCurrentPointer()) {
                        case 0:
                            baseForm.setAboutForm((UIForm) null);
                            baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                            lookType = 1;
                            isPackage = 1;
                            lookStuffPlace = (short) dramatisPackage.getCurrentPointer();
                            ni.send(67110656);
                            break;
                        case 1:
                            if (dramatisPackage.getCurrentId() != 0 && dramatisPackage.getCurrentNumber() >= 1) {
                                if (!dramatisPackage.getCurrentCanTrade() || !dramatisPackage.isCanToOther()) {
                                    baseForm.addAboutForm("msg", "此物品已绑定，不能拍卖", (byte) 1, 100, 30);
                                    setMessage(baseForm, "此物品已绑定，不能拍卖");
                                    return;
                                }
                                if (dramatisPackage.getCurrentBigType() == 1 || dramatisPackage.getCurrentNumber() == 1) {
                                    labels[0].setStr(dramatisPackage.getCurrentName());
                                    labels[0].setColor(Cons.STUFF_NAME_COLOR[dramatisPackage.getCurrentNameLevel()]);
                                    mImages[0].setCurrentFrame((byte) (dramatisPackage.getCurrentImageId() - 1));
                                    mImages[0].setNumber((byte) 1);
                                    mImages[0].setNumberVisiable(false);
                                    baseForm.setComponentFocus(mImages[0]);
                                    auctionIndex = dramatisPackage.getCurrentPointer();
                                    rbs[0].setAroundComponent(dramatisPackage, (byte) 2);
                                    texts[0].setLabel("0");
                                    texts[1].setLabel("0");
                                    rbs[0].setChooseItem(0);
                                    baseForm.setAboutForm((UIForm) null);
                                    break;
                                }
                                UIForm qForm = new UIForm(0, 0, 100, 56, "quantity");
                                qForm.setBackGround((byte) 9);
                                UIRim frame = new UIRim(0, 0, 100, 56, (byte) 4);
                                qForm.setBackGround((byte) 1);
                                UILabel lblTitle = new UILabel(0, 3, 0, 0, "请输入物品个数", 15718814, (byte) 1, (byte) 0);
                                texts[3] = new UIText(0, 23, 80, 12, 7, (byte) 2, "0");
                                texts[3].setMaxNumber(dramatisPackage.getCurrentNumber());
                                UILabel lblok = new UILabel(0, 0, 0, 0, "确定", 15718814, (byte) 1, (byte) 0);
                                UILabel lblcancel = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 1, (byte) 0);
                                qForm.addComponent(frame);
                                qForm.addComponentInCenter(lblTitle, (byte) 2);
                                qForm.addComponentInCenter(texts[3], (byte) 2);
                                qForm.addComponentInCenter(lblok, (byte) 5);
                                qForm.addComponentInCenter(lblcancel, (byte) 6);
                                baseForm.setAboutForm((UIForm) null);
                                baseForm.setAboutForm(qForm);
                            }
                            break;
                        case 2:
                            if (mImages[0].getNumber() == 0) {
                                setMessage(baseForm, "请选择拍卖物品！");
                                break;
                            }
                            if (texts[0].getNumber() == 0) {
                                setMessage(baseForm, "竞拍价格不能为零！");
                                break;
                            }
                            if (texts[1].getNumber() == 0) {
                                setMessage(baseForm, "一口价不能为零！");
                                break;
                            }
                            if (texts[0].getNumber() > texts[1].getNumber()) {
                                setMessage(baseForm, "一口价不能小于竞拍价！");
                                break;
                            }
                            auctionPrice = PCAuction.getAuctionPrice();
                            baseForm.getCurrentFocusForm().addAboutForm("confirm", "确定拍卖这个物品吗？需保证金" + auctionPrice, (byte) 2, 180, 60);
                            break;
                        case 3:
                            if (mImages[0].getCurrentFrame() >= 0) {
                                mImages[0].setCurrentFrame((byte) -1);
                                mImages[0].setNumber((byte) 0);
                                labels[0].setStr((String) null);
                                texts[0].setLabel("0");
                                texts[1].setLabel("0");
                                rbs[0].setChooseItem(0);
                                baseForm.setComponentFocus(dramatisPackage);
                                rbs[0].setAroundComponent(null, (byte) 2);
                                dramatisPackage.setAroundComponent(null, (byte) 1);
                            }
                            baseForm.setAboutForm((UIForm) null);
                            break;
                    }
                }
                break;
            case -2:
            case 1:
                if (isKeyPress1(16) && baseForm.getCurrentFocusForm() == baseForm) {
                    baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                    ni.send(234882560);
                    break;
                }
                if (baseForm.getCurrentFocusForm() == baseForm && (isKeyPress1(14) || isKeyPress1(17))) {
                    String[] strs = {"查看信息", "出价"};
                    menus[0] = null;
                    int h = strs.length * 17;
                    menus[0] = new UIMenu(32, 190 - h, 75, h, null, strs);
                    UIForm subForm = new UIForm(0, 0, screenW - 1, screenH - 1, "menu");
                    subForm.setBackGround((byte) 9);
                    subForm.addComponent(menus[0]);
                    baseForm.setAboutForm(subForm);
                    if (subAuction == -2) {
                        menus[0].setNoUse((byte) 1);
                    }
                    break;
                }
                if ("nothing".equals(baseForm.getCurrentFocusForm().getName()) && (isKeyPress1(14) || isKeyPress1(17))) {
                    setNPCSubState((byte) 0);
                    NPCMenu.setSubMenu((UIMenu) null);
                    releaseUI();
                    break;
                }
                if ("msg".equals(baseForm.getCurrentFocusForm().getName()) && (isKeyPress1(14) || isKeyPress1(17))) {
                    baseForm.setAboutForm((UIForm) null);
                    break;
                }
                if ("message".equals(baseForm.getCurrentFocusForm().getName()) && (isKeyPress1(14) || isKeyPress1(17))) {
                    baseForm.setAboutForm((UIForm) null);
                    break;
                }
                if ("input".equals(baseForm.getCurrentFocusForm().getName()) && (isKeyPress1(14) || isKeyPress1(17))) {
                    baseForm.setAboutForm((UIForm) null);
                    baseForm.addAboutForm("confirm", "你确定要竞买吗？", (byte) 2, 180, 50);
                    break;
                }
                if ("confirm".equals(baseForm.getCurrentFocusForm().getName()) && (isKeyPress1(14) || isKeyPress1(17))) {
                    String msg = null;
                    int num = texts[4].getNumber();
                    int index = tblAuction.getCurrentPointer();
                    if (auctionParams[index][2] == auctionParams[index][3]) {
                        if (num < auctionParams[index][2]) {
                            msg = "竞买价格低于当前最高价";
                        }
                    } else if (num <= auctionParams[index][2]) {
                        msg = "竞买价格应高于当前最高价";
                    }
                    if (msg != null) {
                        baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
                        baseForm.addAboutForm("msg", msg, (byte) 1, 220, 50);
                        break;
                    }
                    baseForm.setAboutForm((UIForm) null);
                    baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                    ni.send(234881792);
                    break;
                }
                if (isKeyPress1(18)) {
                    if (baseForm.getCurrentFocusForm() == baseForm) {
                        setNPCSubState((byte) 0);
                        NPCMenu.setSubMenu((UIMenu) null);
                        releaseUI();
                        break;
                    }
                    if ("menu".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if ("detail".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if ("input".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                        break;
                    }
                    if ("confirm".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
                        break;
                    }
                    if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
                        baseForm.setAboutForm((UIForm) null);
                    }
                    break;
                }
                if (baseForm.getCurrentFocusForm() == baseForm && isKeyPress1(10)) {
                    if (PCAuction.currentPage > 1) {
                        PCAuction.pageDirect = 2;
                        baseForm.setAboutForm((UIForm) null);
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        ni.send(234882304);
                    }
                    break;
                }
                if (baseForm.getCurrentFocusForm() == baseForm && isKeyPress1(12)) {
                    if (PCAuction.currentPage < PCAuction.totalPage) {
                        PCAuction.pageDirect = 1;
                        baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                        ni.send(234882304);
                    }
                    break;
                }
                if (actionInForm(cmd)) {
                    if (cmd != null && cmd == tblAuction && !tblAuction.isNull()) {
                        int index = tblAuction.getCurrentPointer();
                        byte imgId = (byte) (auctionParams[index][0] - 1);
                        mImages[0].setCurrentFrame(imgId);
                        mImages[0].setNumber((byte) auctionParams[index][1]);
                        if (imgId > 32) {
                            mImages[0].setNumberVisiable(true);
                        } else {
                            mImages[0].setNumberVisiable(false);
                        }
                        mImages[0].setNumber((byte) auctionParams[index][1]);
                        texts[0].setLabel(auctionParams[index][2] + "");
                        texts[1].setLabel(auctionParams[index][3] + "");
                        if (auctionParams[index][2] > 99999) {
                            texts[0].setColor(16739328);
                        } else {
                            texts[0].setDefaultColor();
                        }
                        if (auctionParams[index][3] > 99999) {
                            texts[1].setColor(16739328);
                        } else {
                            texts[1].setDefaultColor();
                        }
                        String s = PCAuction.getTime(auctionParams[index][4]);
                        texts[2].setStr(s);
                    }
                    break;
                }
                if (baseForm.getCurrentFocusForm().getName().equals("menu") && (isKeyPress1(14) || isKeyPress1(17))) {
                    int index;
                    UIForm inputForm;
                    UIRim rim;
                    UILabel lblOpt;
                    UILabel lblCancle;
                    UILabel lblMax;
                    UILabel lblOne;
                    UILabel lblTitle;
                    switch (menus[0].getCurrentPointer()) {
                        case 0:
                            baseForm.setAboutForm((UIForm) null);
                            baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                            ni.send(234882816);
                            break;
                        case 1:
                            index = tblAuction.getCurrentPointer();
                            inputForm = new UIForm(0, 0, 140, 100, "input");
                            inputForm.setBackGround((byte) 9);
                            rim = new UIRim(0, 0, 140, 100, (byte) 4);
                            lblOpt = new UILabel(0, 0, 0, 0, "确定", 15718814, (byte) 1, (byte) 0);
                            lblCancle = new UILabel(0, 0, 0, 0, "取消", 15718814, (byte) 1, (byte) 0);
                            lblMax = new UILabel(0, 3, 0, 0, "当前最高价 : " + auctionParams[index][2], 15718814, (byte) 1, (byte) 0);
                            lblOne = new UILabel(0, lblMax.positionY + 16, 0, 0, "一口价 : " + auctionParams[index][3], 15718814, (byte) 1, (byte) 0);
                            lblTitle = new UILabel(0, lblOne.positionY + 16, 0, 0, "请输入你的出价", 15718814, (byte) 1, (byte) 0);
                            texts[4] = new UIText(0, 4 + lblTitle.positionY + 16, 60, 0, 8, (byte) 2, "0");
                            texts[4].setMaxNumber((auctionMoney > auctionParams[index][3]) ? auctionParams[index][3] : auctionMoney);
                            inputForm.addComponentInCenter(rim, (byte) 4);
                            inputForm.addComponentInCenter(lblOpt, (byte) 5);
                            inputForm.addComponentInCenter(lblCancle, (byte) 6);
                            inputForm.addComponentInCenter(lblMax, (byte) 2);
                            inputForm.addComponentInCenter(lblOne, (byte) 2);
                            inputForm.addComponentInCenter(lblTitle, (byte) 2);
                            inputForm.addComponentInCenter(texts[4], (byte) 2);
                            baseForm.setAboutForm(inputForm);
                            break;
                    }
                }
                break;
        }
    }

    private void drawAuction(Graphics g) {
        switch (subAuction) {
            case -11:
                if (baseForm.getSubForm() == null) {
                    baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                }
                break;
            case -1:
                if (NPCMenu.getSubMenu() == null) {
                    menus[1] = new UIMenu(0, 0, 120, 0, null, Cons.VENDUE_MENU);
                    NPCMenu.setSubMenu(menus[1]);
                    baseForm.setAboutForm((UIForm) null);
                    if (auctionCanSell != 1) {
                        menus[1].setNoUse((byte) 1);
                    }
                }
                break;
            case 0:
                if (baseForm == null) {
                    baseForm = new UIForm(0, 0, screenW, screenH, "");
                    baseForm.setStyle((byte) 0);
                    baseForm.setBackGround((byte) 9);
                    UIRim frame = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
                    mImages[0] = new UIMImage(15, 5, 18, 18, null, (byte) 0);
                    mImages[0].setHaveRim(true);
                    mImages[0].setImage(mImgStuff);
                    mImages[0].setCurrentFrame((byte) -1);
                    mImages[0].setNumber((byte) 0);
                    labels[0] = new UILabel(37, (mImages[0]).positionY + 3, 0, 0, "", 65280, (byte) 0, (byte) 0);
                    UILabel lblJPrice = new UILabel(15, 26, 0, 0, "竞拍价格:", 15718814, (byte) 0, (byte) 0);
                    UILabel lblOPrice = new UILabel(15, lblJPrice.positionY + 17, 0, 0, "一口价:", 15718814, (byte) 0, (byte) 0);
                    texts[0] = new UIText(85, lblJPrice.positionY, 80, 12, 8, (byte) 3, "0");
                    texts[0].setMaxNumber(99999999L);
                    texts[1] = new UIText(85, lblOPrice.positionY, 80, 12, 8, (byte) 3, "0");
                    texts[1].setMaxNumber(99999999L);
                    rbs[0] = new UIRadioButton(15, lblOPrice.positionY + 17, 20, 12, "拍卖持续时间:", (byte) 2);
                    for (int i = 0; i < PCAuction.TIME_PERSISTANCE.length; i++) {
                        rbs[0].addItems(PCAuction.TIME_PERSISTANCE[i] + "小时");
                    }
                    rbs[0].setAdvanceStyle();
                    rbs[0].setAdvanceColor(15718814);
                    UILabel lblMoney = new UILabel(15, (rbs[0]).positionY + 17, 0, 0, "玩家金钱:", 15718814, (byte) 0, (byte) 0);
                    txtMoney = null;
                    txtMoney = new UIText(85, lblMoney.positionY, 80, 12, 6, (byte) 3, auctionMoney + "");
                    txtMoney.setCanFocus(false);
                    UILabel lblOperate = new UILabel(0, 0, 0, 0, "操作", 15718814, (byte) 0, (byte) 0);
                    UILabel lblCancel = new UILabel(0, 0, 0, 0, "取消", 15718814, (byte) 0, (byte) 0);
                    UIRim rimName = new UIRim(0, 95, 166, 18, (byte) 7);
                    labels[1] = new UILabel(0, rimName.positionY + 3, screenW, 0, dramatisPackage.getCurrentName(), Cons.STUFF_NAME_COLOR[dramatisPackage.getCurrentNameLevel()], (byte) 1, (byte) 0);
                    mImages[0].setAroundComponent(texts[0], (byte) 2);
                    texts[0].setAroundComponent(texts[1], (byte) 2);
                    texts[1].setAroundComponent(rbs[0], (byte) 2);
                    baseForm.addComponent(frame);
                    baseForm.addComponent(mImages[0]);
                    baseForm.addComponent(labels[0]);
                    baseForm.addComponent(lblJPrice);
                    baseForm.addComponent(lblOPrice);
                    baseForm.addComponent(texts[0]);
                    baseForm.addComponent(texts[1]);
                    baseForm.addComponent(rbs[0]);
                    baseForm.addComponent(lblMoney);
                    baseForm.addComponent(txtMoney);
                    baseForm.addComponentInCenter(lblOperate, (byte) 5);
                    baseForm.addComponentInCenter(lblCancel, (byte) 6);
                    baseForm.addComponentInCenter(rimName, (byte) 2);
                    baseForm.addComponentInCenter(labels[1], (byte) 2);
                    baseForm.addComponentInCenter(dramatisPackage, (byte) 2);
                    baseForm.setFocus(true);
                    baseForm.setComponentFocus(dramatisPackage);
                }
                break;
            case -2:
            case 1:
                if (baseForm == null) {
                    baseForm = new UIForm(0, 0, screenW, screenH, "");
                    baseForm.setStyle((byte) 0);
                    baseForm.setBackGround((byte) 9);
                    UIRim frame = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
                    int num = 0;
                    if (auctionNames == null) {
                        auctionNames = new String[7];
                    }
                    for (int i = 0; i < auctionNames.length && auctionNames[i] != null && !"".equals(auctionNames[i]); i++) {
                        num++;
                    }
                    UIRim rimPage = new UIRim(5, 33, 80, 14, (byte) 0);
                    UIRim rimRight = new UIRim(88, 33, 82, 145, (byte) 0);
                    tblAuction = new UITable(5, 48, 80, 150, num, 1, num, (byte) 0, (byte) 3);
                    tblAuction.setAutoHeight(true);
                    for (int j = 0; j < num; j++) {
                        tblAuction.addItem(auctionNames[j], 15718814);
                        tblAuction.setItemColor(j, Cons.STUFF_NAME_COLOR[auctionParams[j][5]]);
                    }
                    UILabel lblOpt = new UILabel(0, 0, 0, 0, "操作", 15718814, (byte) 0, (byte) 0);
                    UILabel lblCel = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 0, (byte) 0);
                    UIRim rimName = new UIRim(0, 6, 166, 18, (byte) 7);
                    UILabel lblTitle = new UILabel(0, 8, 0, 0, "已悬挂的物品", 15718814, (byte) 0, (byte) 0);
                    baseForm.addComponent(frame);
                    if (num != 0) {
                        baseForm.addComponent(tblAuction);
                    }
                    baseForm.addComponentInCenter(lblOpt, (byte) 5);
                    baseForm.addComponentInCenter(lblCel, (byte) 6);
                    baseForm.addComponentInCenter(rimName, (byte) 2);
                    baseForm.addComponentInCenter(lblTitle, (byte) 2);
                    baseForm.addComponent(rimPage);
                    baseForm.addComponent(rimRight);
                    int startx = 93;
                    int starty = 60;
                    int perH = 14;
                    int ww = 73;
                    mImages[0] = new UIMImage(startx + (ww - 18 >> 1), starty - 22, 18, 18, null, (byte) 0);
                    mImages[0].setHaveRim(true);
                    mImages[0].setImage(mImgStuff);
                    mImages[0].setCurrentFrame((byte) -1);
                    baseForm.addComponent(mImages[0]);
                    UILabel lblMaxPrice = new UILabel(startx, starty, ww, 0, "当前最高价", 15718814, (byte) 1, (byte) 0);
                    UILabel lblOnePrice = new UILabel(startx, starty + 2 * perH + 1, ww, 0, "一口价", 15718814, (byte) 1, (byte) 0);
                    UILabel lblTime = new UILabel(startx, starty + 4 * perH + 1, ww, 0, "剩余时间", 15718814, (byte) 1, (byte) 0);
                    UILabel lblOwnMoney = new UILabel(startx, starty + 6 * perH + 3, ww, 0, "玩家金钱", 15718814, (byte) 1, (byte) 0);
                    texts[0] = new UIText(startx, starty + perH, ww, 0, 0, (byte) 3, "");
                    texts[0].rightStanderd(true);
                    texts[1] = new UIText(startx, starty + 3 * perH, ww, 0, 0, (byte) 3, "");
                    texts[1].rightStanderd(true);
                    texts[2] = new UIText(startx, starty + 5 * perH, ww, 0, 0, (byte) 2, "");
                    texts[2].rightStanderd(true);
                    (texts[2]).height += 2;
                    texts[3] = new UIText(startx, starty + 7 * perH + 2, ww, 0, 0, (byte) 3, (Player.getInstance()).money + "");
                    texts[3].rightStanderd(true);
                    baseForm.addComponent(lblMaxPrice);
                    baseForm.addComponent(lblOnePrice);
                    baseForm.addComponent(lblTime);
                    baseForm.addComponent(lblOwnMoney);
                    baseForm.addComponent(texts[0]);
                    baseForm.addComponent(texts[1]);
                    baseForm.addComponent(texts[2]);
                    baseForm.addComponent(texts[3]);
                    UILabel lblRefresh = new UILabel(0, 160, 100, 0, "按#键刷新", 65280, (byte) 1, (byte) 0);
                    baseForm.addComponent(lblRefresh);
                    UILabel lblPage = new UILabel(14, 35, 60, 0, PCAuction.currentPage + "/" + PCAuction.totalPage, 15718814, (byte) 1, (byte) 0);
                    baseForm.addComponent(lblPage);
                    baseForm.setFocus(true);
                    if (num == 0) {
                        baseForm.addAboutForm("nothing", "没有任何物品", (byte) 1, 140, 50);
                        break;
                    }
                    int index = tblAuction.getCurrentPointer();
                    byte imgId = (byte) (auctionParams[index][0] - 1);
                    mImages[0].setCurrentFrame(imgId);
                    mImages[0].setNumber((byte) auctionParams[index][1]);
                    if (imgId > 32) {
                        mImages[0].setNumberVisiable(true);
                    } else {
                        mImages[0].setNumberVisiable(false);
                    }
                    mImages[0].setNumber((byte) auctionParams[index][1]);
                    texts[0].setLabel(auctionParams[index][2] + "");
                    texts[1].setLabel(auctionParams[index][3] + "");
                    if (auctionParams[index][2] > 99999) {
                        texts[0].setColor(16739328);
                    } else {
                        texts[0].setDefaultColor();
                    }
                    if (auctionParams[index][3] > 99999) {
                        texts[1].setColor(16739328);
                    } else {
                        texts[1].setDefaultColor();
                    }
                    String s = PCAuction.getTime(auctionParams[index][4]);
                    texts[2].setStr(s);
                }
                break;
            case 2:
                g.drawString("AUCTION_BUY", 50, 20, 20);
                break;
            case 11:
                if (baseForm == null) {
                    baseForm = new UIForm(0, 0, screenW, screenH, "");
                    baseForm.setStyle((byte) 0);
                    rims[0] = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
                    UILabel label1 = new UILabel(0, 0, 0, 0, "选择", 15718815, (byte) 0, (byte) 0);
                    UILabel label2 = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 0, (byte) 0);
                    labels[0] = new UILabel(0, 18, 0, 0, "请选择您要查看的物品种类", 15718814, (byte) 0, (byte) 0);
                    String[] str = {"所有物品", "武器类", "防具类", "素材类", "消耗品"};
                    menus[0] = new UIMenu(0, 0, 80, 0, null, str);
                    baseForm.addComponent(rims[0]);
                    baseForm.addComponentInCenter(labels[0], (byte) 2);
                    baseForm.addComponentInCenter(label1, (byte) 5);
                    baseForm.addComponentInCenter(label2, (byte) 6);
                    baseForm.addComponentInCenter(menus[0], (byte) 4);
                    baseForm.setFocus(true);
                    baseForm.setComponentFocus(menus[0]);
                }
                break;
        }
        if (baseForm != null) {
            baseForm.draw(g);
            if (subAuction == 1 || subAuction == -2) {
                if (baseForm.getCurrentFocusForm().getName().equals("detail")) {
                    return;
                }
                if (PCAuction.currentPage > 1) {
                    if (PCAuction.currentPage > 9) {
                        mImgUI[22].draw(g, 22, 56, 0, false);
                    } else {
                        mImgUI[22].draw(g, 30, 56, 0, false);
                    }
                }
                if (PCAuction.currentPage < PCAuction.totalPage) {
                    mImgUI[22].draw(g, 90, 56, 0, true);
                }
            }
        }
    }

    public void setAuctionState(byte state) {
        subAuction = state;
        switch (subAuction) {
            case 4:
                setGameState((byte) 0);
                break;
        }
        resetKey();
    }

    public void drawFastChat(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setBackGround((byte) 9);
            menus[0] = new UIMenu(0, 0, 80, Cons.STR_CHAT_FAST.length * 16, null, Cons.STR_CHAT_FAST);
            menus[0].setCurrentpointer(fastChatIndex);
            UILabel lblCancel = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 0, (byte) 0);
            baseForm.addComponentInCenter(lblCancel, (byte) 6);
            baseForm.addComponentInCenter(menus[0], (byte) 4);
            baseForm.setFocus(true);
        }
        baseForm.draw(g);
    }

    private void releaseCreateRes() {
        GameObj.releaseBlackRes();
        GameObj.releaseWhiteRes();
        Map.curMapType = -1;
    }

    public static void drawGroundback(Graphics g) {
    }

    public static void closeConnection() {
        try {
            ni.closeConn();
            mc.exitGame((byte) 1);
        } catch (Exception exception) {
        }
    }

    public static void initTjRes() {
        tjImage = Util.loadImage(Util.readPKG("/uiuse.pkg", "qqtj.png"));
        tjFrameData = Util.readFdatFile("/qqtj.fdat", 0);
        tjPicData = Util.readPdatFile("/qqtj.pdat");
        tjMotionDataAll = Util.readMdatFile("/qqtj.mdat");
    }

    private void processOvertime(Graphics g, boolean isShow) {
        if (baseForm != null && "waiting".equals(baseForm.getCurrentFocusForm().getName()) && getState() == 5) {
            int wid = waitImg.getWidth() >> 2;
            int drawX = screenW - wid - 3;
            int hei = waitImg.getHeight();
            g.setClip(drawX, 15, wid, hei);
            g.drawImage(waitImg, drawX - wid * loadCounter, 15, 20);
            g.setClip(0, 0, screenW, screenH);
            loadCounter++;
            if (loadCounter > 3) {
                loadCounter = 0;
            }
        }
        if (isInBaoyueWait) {
            return;
        }
        switch (getState()) {
            case 5:
                if (baseForm != null) {
                    if ("waiting".equals(baseForm.getCurrentFocusForm().getName())) {
                        waitCnt++;
                        if (waitCnt >= 400) {
                            closeConnection();
                            exitGame((byte) 1);
                        } else if (waitCnt >= 380) {
                            UIRim.drawRim(g, screenW - 210 >> 1, (screenH - 60 >> 1) - 10, 210, 60, (byte) 1);
                            g.setColor(16777215);
                            g.drawString("载入超时，请重新登录", screenW >> 1, (screenH >> 1) - 20, 17);
                        }
                    } else if (!"swait".equals(baseForm.getCurrentFocusForm().getName())) {
                        waitCnt = 0;
                    }
                } else {
                    waitCnt = 0;
                }
            case 23:
                if (!PCChangeMap.isParseResMsg) {
                    waitCnt++;
                    if (waitCnt >= 400) {
                        setState((byte) 4);
                        isLogin = false;
                        releaseUI();
                        waitCnt = 0;
                        loadCount = 0;
                    } else if (waitCnt >= 380) {
                        UIRim.drawRim(g, screenW - 210 >> 1, (screenH - 60 >> 1) - 10, 210, 60, (byte) 1);
                        g.setColor(16777215);
                        g.drawString("载入超时，请重新登录", screenW >> 1, (screenH >> 1) - 20, 17);
                    } else if (isShow) {
                        UIRim.drawRim(g, screenW - 150 >> 1, (screenH - 40 >> 1) - 20, 150, 40, (byte) 1);
                        g.setColor(16777215);
                        g.drawString("载入中，请稍候...", screenW >> 1, (screenH >> 1) - 30, 17);
                    }
                } else {
                    waitCnt = 0;
                }
            case 18:
                return;
        }
        waitCnt = 0;
    }
    public static int loadCounter = 0;
    public UIForm topForm;
    int fireW;
    int fireH;
    int[] matrix;
    int[] color;
    byte[] data_0;
    byte[] data_1;
    int level;
    int count;
    int fireStartY;
    /**
     * 箭头
     */
    public static MImage mImageArrow;
    public UITable specialToolTable;
    public String[] specialToolItems;
    final int start;
    final int offsetf;
    byte offset;
    byte isSelectList;
    final String SHOWALERT;
    final String SHOWEXPLAIN;

    private void drawDead(Graphics g) {
        if (topForm != null) {
            topForm = null;
        }
        byte rectY = 50;
        int rectWidth = 160;
        int rectHeight = 50;
        int remindStringY = rectY + 8;
        if ((Player.getInstance()).state == 5 && isDeadRock == 1) {
            UIRim.drawRim(g, screenW - rectWidth >> 1, rectY, rectWidth, rectHeight + 30, (byte) 4);
            g.setColor(16768323);
            g.drawString("你已经死亡", screenW >> 1, remindStringY, 17);
            g.drawString("是否使用复活石复活", screenW >> 1, remindStringY + 19, 17);
            g.drawString("确定", (screenW >> 1) - 44, remindStringY + 42, 17);
            g.drawString("取消", (screenW >> 1) + 44, remindStringY + 42, 17);
        } else if ((Player.getInstance()).state == 5 && isDeadRock == 0) {
            UIRim.drawRim(g, screenW - rectWidth >> 1, rectY, rectWidth, rectHeight, (byte) 4);
            g.setColor(16768323);
            g.drawString("你已经死亡", screenW >> 1, remindStringY, 17);
            g.drawString("按#键返回复活点", screenW >> 1, remindStringY + 19, 17);
        }
    }

    public void setTopForm(UIForm aboutForm) {
        if (aboutForm == null) {
            return;
        }
        topForm = aboutForm;
        topForm.setFormXY(screenW - topForm.width >> 1, screenH - topForm.height - 26);
    }

    public void setTopForm(UIForm aboutForm, int y) {
        if (aboutForm == null) {
            return;
        }
        topForm = aboutForm;
        topForm.setFormXY(screenW - topForm.width >> 1, y);
    }

    public boolean canTopForm() {
        return !(topForm != null);
    }

    public byte canPk() {
        if (getState() == 17 || getState() == 23) {
            return 2;
        }
        if (topForm != null || baseForm != null) {
            return 2;
        }
        return 1;
    }

    public byte canGroup() {
        if (getState() == 17 || getState() == 23) {
            return 2;
        }
        if (topForm != null || baseForm != null) {
            return 2;
        }
        return 1;
    }

    public boolean keyInTopForm() {
        if (topForm == null) {
            return false;
        }
        if ("topConfirm".equals(topForm.getName())) {
            if (isKeyPress(17)) {
                resetKey();
                teamAutoConfirm = 1;
                topForm = null;
                ni.send(100663552);
            } else if (isKeyPress(18)) {
                resetKey();
                teamAutoConfirm = 0;
                topForm = null;
                ni.send(100663552);
            } else if (isKeyPress(14)) {
                keyFlag &= 0xFFFFBFFF;
            }
        } else if ("pkConfirm".equals(topForm.getName())) {
            if (isKeyPress(17)) {
                resetKey();
                PCGameObj.pkResponse = 1;
                topForm = null;
                ni.send(268435968);
            } else if (isKeyPress(18)) {
                resetKey();
                PCGameObj.pkResponse = 2;
                topForm = null;
                ni.send(268435968);
            } else if (isKeyPress(14)) {
                keyFlag &= 0xFFFFBFFF;
            }
        } else if ("arena".equals(topForm.getName()) || "arenaInvite".equals(topForm.getName())) {
            PCArena.getInstance().keyPressed();
        } else if ("msg".equals(topForm.getName())) {
            UIComponent cmd = topForm.getCommand();
            if (isKeyPressOk()) {
                topForm = null;
            } else if (!isKeyPress1(18) && actionInForm(cmd, topForm)) {
            }
        } else if ("meteConfirm".equals(topForm.getName())) {
            if (isKeyPress(17) || isKeyPress(-7)) {
                resetKey();
                topForm = null;
            } else if (isKeyPress(14)) {
                keyFlag &= 0xFFFFBFFF;
            }
        }
        return true;
    }

    public void exitGame(byte type) {
        if (sound != null) {
            sound.stopAllSound();
        }
        if (baseForm != null) {
            baseForm.setAboutForm((UIForm) null);
        }
        isLogin = false;
        strForPop = null;
        arenaStatus = 0;
        for (byte i = 0; i < playerState.length; i = (byte) (i + 1)) {
            if (playerState[i]) {
                choose_manID = i;
                break;
            }
        }
        payChangeSerser = 0;
        ObjManager.getInstance().removeAll();
        Map.getInstance().resetObjGameObjArray();
        teamJob = 0;
        teamMates = new Vector();
        teamLeaderId = 0;
        UIGameRun.creatFrameIndex = 0;
        waitCnt = 0;
        loadCount = 0;
        PCChangeMap.isCounterpart = false;
        switch (type) {
            case 0:
                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                ni.send(16778496);
                break;
            case 1:
                setState((byte) 4);
                ObjManager.getInstance().releaseObj();
                GameObj.releaseWhiteRes();
                GameObj.releaseBlackRes();
                GameObj.releaseMonsterRes();
                releaseUI();
                isWapFirst = true;
                break;
        }
    }

    public static void setMessage(UIForm form, String message) {
        form.addAboutForm("msg", message, (byte) 1, 210, 0);
    }

    public void drawFire(Graphics g) {
        if (color == null) {
            fireW = screenW;
            fireH = 63;
            fireStartY = screenH - fireH;
            color = new int[fireH << 1];
            data_0 = new byte[(fireH + 2) * fireW];
            data_1 = new byte[(fireH + 2) * fireW];
            int height_2 = fireH << 1;
            Random rd = new Random(System.currentTimeMillis());
            int i;
            for (i = 0; i < fireW * fireH; i++) {
                data_0[i] = (byte) Math.abs(rd.nextInt() % height_2);
            }
            setSegment();
            matrix = new int[fireW * (fireH + 2)];
            for (i = 0; i < 25; i++) {
                ChangData();
            }
        } else {
            ChangData();
            int index = 0;
            for (int h = 0; h < fireH; h++) {
                for (int w = 0; w < fireW; w++) {
                    matrix[index] = color[data_1[index]];
                    index++;
                }
            }
            g.drawRGB(matrix, 0, fireW, screenW - fireW >> 1, fireStartY, fireW, fireH, false);
        }
    }

    public void clearFireMem() {
        matrix = null;
        color = null;
        data_0 = null;
        data_1 = null;
        level = 0;
    }

    public void ChangData() {
        level = (level + 1) % fireH;
        int area = fireW * fireH;
        int temp_1 = level * fireW;
        System.arraycopy(data_0, temp_1, data_1, area, fireW << 1);
        for (int point = fireW; point < area; point++) {
            int pointdata = data_1[point + (fireW << 1)] + data_1[point - 1 + fireW] + data_1[point + fireW] + data_1[point + 1 + fireW] >> 2;
            data_1[point] = (pointdata > 0) ? (byte) (pointdata - 1) : 0;
        }
    }

    private final void setColor(int[] color, int startPos, int endPos, int red_0, int red_1, int green_0, int green_1, int blue_0, int blue_1) {
        int len = endPos - startPos;
        int red = red_1 - red_0;
        int green = green_1 - green_0;
        int blue = blue_1 - blue_0;
        for (int i = 0; i < len; i++) {
            color[startPos + i] = 0xFF000000 | red_0 + i * red / len << 16 | green_0 + i * green / len << 8 | blue_0 + i * blue / len;
        }
    }

    private void setSegment() {
        int max = 220;
        int height_2 = fireH << 1;
        int height_4 = fireH;
        int height_8 = fireH >> 1;
        setColor(color, 0, height_8, 0, max, 0, 0, 0, 0);
        setColor(color, height_8, height_4, max, max, 0, max, 0, 0);
        setColor(color, height_4, height_2, max, max, max, max, 128, 255);
    }

    public static void cancelBusiness() {
        if (mc.getGameState() == 2 && mc.getLeftMenuSubState() == 4) {
            ni.send(218104832);
            mc.setBusinessState(3);
        }
    }

    public void initAwardForm(byte award) {
        commonForm = null;
        commonOk = null;
        commonBack = null;
        commonTextField = null;
        commontf = null;
        String tmpStr = null;
        commonForm = new Form("活动资讯");
        tmpStr = PCNPC.npcInfo[0];
        commonOk = new Command("确定", 4, 2);
        commonBack = new Command("返回", 2, 2);
        if (tmpStr != null && !tmpStr.trim().equals("")) {
            commonForm.append(tmpStr);
        }
        switch (award) {
            case 32:
                commonTextField = new TextField(PCNPC.npcInfo[1], "", 16, 0);
                commonForm.append((Item) commonTextField);
                break;
            case 33:
                commonTextField = new TextField(PCNPC.npcInfo[1], "", 16, 0);
                commontf = new TextField(PCNPC.npcInfo[2], "", 16, 0);
                commonForm.append((Item) commonTextField);
                commonForm.append((Item) commontf);
                break;
        }
        commonForm.addCommand(commonOk);
        commonForm.addCommand(commonBack);
        commonForm.setCommandListener(this);
        aMidlet.display.setCurrent((Displayable) commonForm);
        setNPCSubState(award);
    }

    private void drawBattleGroundRank(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, (short) screenW, (short) screenH, "");
            UIRim frame = new UIRim(0, 0, (short) (screenW - 1), (short) (screenH - 1), (byte) 4);
            UIRim rimTitle = new UIRim(0, 13, 159, 17, (byte) 7);
            UILabel lblTitle = new UILabel(0, (short) (rimTitle.positionY + 3), 0, 0, "攻城排行榜", 15718814, (byte) 1, (byte) 0);
            UILabel label = new UILabel(0, (short) (rimTitle.positionY + 23), 0, 0, "天人：修罗", 15718814, (byte) 1, (byte) 0);
            UIRim rimDown = new UIRim(0, 50, 163, 128, (byte) 0);
            byte count = (byte) PCNPC.weeks.length;
            byte n = count;
            if (n < 1) {
                n = 1;
            }
            tables[0] = new UITable(0, 51, 163, 128, n, 1, (n > 8) ? 8 : n, (byte) 0, (byte) 3);
            if (count == 0) {
                tables[0].addItem("当前无数据", 15718814);
            } else {
                for (int i = 0; i < count; i++) {
                    String str = "第" + PCNPC.weeks[i] + "次： " + PCNPC.alliance[i] + "：" + PCNPC.horde[i];
                    tables[0].addItem(str, 15718814);
                }
            }
            UILabel lblCancel = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 1, (byte) 0);
            baseForm.addComponent(frame);
            baseForm.addComponentInCenter(rimDown, (byte) 2);
            baseForm.addComponentInCenter(rimTitle, (byte) 2);
            baseForm.addComponentInCenter(lblTitle, (byte) 2);
            baseForm.addComponentInCenter(label, (byte) 2);
            baseForm.addComponentInCenter(lblCancel, (byte) 6);
            baseForm.addComponentInCenter(tables[0], (byte) 2);
            tables[0].setXY((short) ((tables[0]).positionX - 1), (short) (tables[0]).positionY);
            baseForm.setFocus(true);
            tables[0].setFocus(true);
        }
        baseForm.draw(g);
    }

    private void drawBattleGroundRate(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, (short) screenW, (short) screenH, "");
            UIRim frame = new UIRim(0, 0, 175, 207, (byte) 4);
            UIRim rimTitle = new UIRim(0, 13, 159, 17, (byte) 7);
            UILabel lblTitle = new UILabel(0, (short) (rimTitle.positionY + 3), 0, 0, "攻城胜率", 15718814, (byte) 1, (byte) 0);
            UILabel lblAlliance = new UILabel(0, rimTitle.positionY + 33, 0, 0, "天人场次：" + PCNPC.allianceRate, 15718814, (byte) 1, (byte) 0);
            UILabel lblHorde = new UILabel(0, rimTitle.positionY + 63, 0, 0, "修罗场次：" + PCNPC.hordeRate, 15718814, (byte) 1, (byte) 0);
            UIRim rimDown = new UIRim(0, 32, 159, 156, (byte) 0);
            UILabel lblCancel = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 1, (byte) 0);
            baseForm.addComponent(frame);
            baseForm.addComponentInCenter(rimDown, (byte) 2);
            baseForm.addComponentInCenter(rimTitle, (byte) 2);
            baseForm.addComponentInCenter(lblTitle, (byte) 2);
            baseForm.addComponentInCenter(lblAlliance, (byte) 2);
            baseForm.addComponentInCenter(lblHorde, (byte) 2);
            baseForm.addComponentInCenter(lblCancel, (byte) 6);
            baseForm.setFocus(true);
        }
        baseForm.draw(g);
    }

    public void keyInBattleGroundRank() {
        UIComponent cmd = baseForm.getCommand();
        if (actionInForm(cmd));
        if (isKeyPress(18) && baseForm.getCurrentFocusForm() == baseForm) {
            setNPCSubState((byte) 0);
            releaseUI();
        }
    }

    public void keyInBattleGroundRate() {
        if (isKeyPress(18) && baseForm.getCurrentFocusForm() == baseForm) {
            setNPCSubState((byte) 0);
            releaseUI();
        }
    }

    private void drawErrorInfor1(Graphics g, String str, String leftString, String rightString) {
        if (baseForm == null) {
            String string = str;
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setStyle((byte) 0);
            baseForm.setBackGround((byte) 8);
            labels[0] = new UILabel(0, 50, 100, 0, string, 15719326, (byte) 1, (byte) 0);
            labels[1] = new UILabel(0, 0, 0, 0, leftString, 15719326, (byte) 1, (byte) 0);
            labels[2] = new UILabel(0, 0, 0, 0, rightString, 15719326, (byte) 1, (byte) 0);
            rims[0] = new UIRim(0, 40, 120, 100, (byte) 4);
            UIRim bigRim = new UIRim(0, 0, screenW - 1, 207, (byte) 4);
            baseForm.addComponent(bigRim);
            baseForm.addComponentInCenter(rims[0], (byte) 2);
            baseForm.addComponentInCenter(labels[0], (byte) 2);
            baseForm.addComponentInCenter(labels[1], (byte) 5);
            baseForm.addComponentInCenter(labels[2], (byte) 6);
        }
        baseForm.draw(g);
    }

    private void findBox(short type) {
        boolean pb = false;
        for (byte i = 0; i < 36; i = (byte) (i + 1)) {
            if (dramatisPackage.getStuffType()[i] == type) {
                pb = true;
                break;
            }
        }
        if (pb) {
            dramatisPackage.stuffPlace = decompose[0] = dramatisPackage.getCurrentPointer();
            dramatisPackage.setSubMenu((UIMenu) null);
            baseForm.addAboutForm("decompose", "请选择要打开的物品", (byte) 2, 150, 0);
        } else {
            dramatisPackage.setSubMenu((UIMenu) null);
            baseForm.addAboutForm("no_box", "您还没有可打开的物品", (byte) 1, 150, 0);
        }
    }

    public void keyInSilk() {
        UIComponent cmd = baseForm.getCommand();
        if ("msg".equals(baseForm.getCurrentFocusForm().getName())) {
            baseForm.setAboutForm((UIForm) null);
            setNPCSubState((byte) 0);
            return;
        }
        if (isKeyPress(17) || isKeyPress(14)) {
            if ("use_silk".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
                startWait(baseForm.getCurrentFocusForm());
                ni.send(251682816);
            } else if ("silk_Left_Menu".equals(baseForm.getCurrentFocusForm().getName())) {
                int index = menus[0].getCurrentPointer();
                switch (index) {
                    case 0:
                        lookType = -1;
                        baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
                        startWait(baseForm.getCurrentFocusForm());
                        ni.send(67110656);
                        break;
                    case 1:
                        baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
                        baseForm.getCurrentFocusForm().addAboutForm("use_silk", "您是否要将" + PCNPC.propsName[tables[1].getCurrentPointer()] + "缠" + (PCNPC.isGold ? "金丝" : "银丝"), (byte) 2, 140, 50);
                        break;
                }
            } else if ("silk".equals(baseForm.getCurrentFocusForm().getName())) {
                UIForm subForm = new UIForm(0, 0, screenW - 1, screenH - 1, "silk_Left_Menu");
                subForm.setBackGround((byte) 9);
                UIMenu menu = new UIMenu(35, 0, 80, 0, null, new String[]{"查看", "缠丝"});
                subForm.addComponentInCenter(menu, (byte) 5);
                baseForm.getCurrentFocusForm().setAboutForm(subForm);
                baseForm.setFocus(true);
                menus[0] = null;
                menus[0] = menu;
            }
        } else if (isKeyPress(18)) {
            if ("use_silk".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
            } else if ("silk_Left_Menu".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
            } else if ("silk".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
                setNPCSubState((byte) 0);
            } else if ("detail".equals(baseForm.getCurrentFocusForm().getName())) {
                baseForm.getCurrentFocusForm().getParentForm().setAboutForm((UIForm) null);
                PCNPC.initSilkForm((byte) PCNPC.propsName.length);
            }
        } else if (actionInForm(cmd)) {
        }
    }

    public void keyInValueAddedCatalogList() {
        UIComponent cmd = baseForm.getCommand();
        if (!"waiting".equals(baseForm.getCurrentFocusForm().getName())) {
            if (isKeyPress(18)) {
                setGameState((byte) 1);
                setRightMenuSubState(0);
                PCIncrementService.setState((byte) -1);
                releaseUI();
            } else if (!actionInForm(cmd) && (isKeyPress(17) || isKeyPress(14))) {
                baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                ni.send(1342177792);
            }
        }
    }

    public void drawUIValueAddedCatalogList(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            UIRim frame = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            UIRim rimTitle = new UIRim(0, 13, 159, 17, (byte) 7);
            UILabel lblTitle = new UILabel(0, rimTitle.positionY + 3, 0, 0, "增值道具分类", 15718814, (byte) 1, (byte) 0);
            UIRim rimDown = new UIRim(0, 30, 160, 153, (byte) 0);
            int tempcount = specialToolItems.length;
            specialToolTable = new UITable(0, 31, 160, 158, tempcount, 1, (tempcount > 10) ? 10 : tempcount, (byte) 0, (byte) 3);
            specialToolTable.setAutoHeight(true);
            UILabel label1 = new UILabel(0, 0, 0, 0, "确定", 15718815, (byte) 0, (byte) 0);
            UILabel label2 = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 0, (byte) 0);
            for (int i = 0; i < tempcount; i++) {
                specialToolTable.addItem(specialToolItems[i], 15718815);
            }
            baseForm.addComponent(frame);
            baseForm.addComponentInCenter(rimDown, (byte) 2);
            baseForm.addComponentInCenter(rimTitle, (byte) 2);
            baseForm.addComponentInCenter(lblTitle, (byte) 2);
            baseForm.addComponentInCenter(label1, (byte) 5);
            baseForm.addComponentInCenter(label2, (byte) 6);
            baseForm.addComponentInCenter(specialToolTable, (byte) 2);
            specialToolTable.setXY(specialToolTable.positionX, specialToolTable.positionY);
            baseForm.setFocus(true);
        }
        baseForm.draw(g);
    }

    private void configShow(int index) {
        switch (index) {
            case 0:
                Cons.showOtherPlayer = true;
                Cons.showName = true;
                Cons.showTeamMate = true;
                Cons.showSmallMap = true;
                Cons.showNum = true;
                Cons.showSpecial = true;
                Cons.showShortCut = true;
                Cons.showExpBar = true;
                break;
            case 1:
                Cons.showOtherPlayer = true;
                Cons.showName = false;
                Cons.showTeamMate = true;
                Cons.showSmallMap = true;
                Cons.showNum = true;
                Cons.showSpecial = true;
                Cons.showShortCut = true;
                Cons.showExpBar = true;
                break;
            case 2:
                Cons.showOtherPlayer = false;
                Cons.showName = false;
                Cons.showTeamMate = false;
                Cons.showSmallMap = true;
                Cons.showNum = true;
                Cons.showSpecial = false;
                Cons.showShortCut = false;
                Cons.showExpBar = false;
                break;
        }
    }

    public void processUpdateInfo(String servercontent) {
        PCLogin.serverUpdateInfo = Util.split(servercontent, servercontent.length(), '|', false);
        int num = PCLogin.serverUpdateInfo.length / 3;
        updateListInfo = new String[num][3];
        updateServer = new String[num];
        for (int i = 0; i < num; i++) {
            updateListInfo[i][0] = PCLogin.serverUpdateInfo[i * 3];
            updateListInfo[i][1] = PCLogin.serverUpdateInfo[i * 3 + 1];
            updateListInfo[i][2] = PCLogin.serverUpdateInfo[i * 3 + 2];
            updateServer[i] = updateListInfo[i][0];
        }
    }

    private void drawUpdateInfo(String title, String text) {
        if (!"update".equals(baseForm.getCurrentFocusForm().getName())) {
            UIForm form = new UIForm(0, 0, screenW, screenH, "update");
            form.setBackGround((byte) 9);
            UIRim bigRim = new UIRim(0, 0, screenW - 1, 207, (byte) 4);
            form.addComponent(bigRim);
            UILabel label1 = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 0, (byte) 0);
            UIRim titleRim = new UIRim(0, 12, 160, 20, (byte) 7);
            UILabel label2 = new UILabel(10, 14, screenW, 0, title, 15718815, (byte) 1, (byte) 0);
            form.addComponentInCenter(label1, (byte) 6);
            form.addComponentInCenter(titleRim, (byte) 2);
            form.addComponentInCenter(label2, (byte) 2);
            UITextArea ta = new UITextArea(0, 32, 160, 156, text);
            form.addComponentInCenter(ta, (byte) 2);
            baseForm.getCurrentFocusForm().setAboutForm(form);
        }
    }

    void initMessageForm() {
        commonForm = null;
        commonOk = null;
        commonBack = null;
        commonTextField = null;
        commonForm = new Form("编辑短信");
        commonOk = new Command("发送", 4, 2);
        commonBack = new Command("返回", 2, 2);
        commonTextField = new TextField("自定义：", "", 60, 0);
        commonForm.append((Item) commonTextField);
        commonForm.append("注：\n    1、 功能描述：\n玩家可以在游戏中给指定的好友玩家发短信。(您与好友必须为绑定账号)包括1对1发短信和群发功能。玩家在游戏中可以给好友（离线、在线均可）发送短信，也可在氏族中进行氏族成员的群发，在好友列表中进行好友成员的群发。短信内容为玩家自定义内容。\n\n    2、 扣费方式：\n每发送一条短信，成功后即扣除发送方3猛犸币；若群发，则每成功发送一人扣除猛犸币（如玩家群发成功发送给10人，则扣除该玩家30猛犸币）。");
        commonForm.addCommand(commonOk);
        commonForm.addCommand(commonBack);
        commonForm.setCommandListener(this);
        aMidlet.display.setCurrent((Displayable) commonForm);
    }
    public static int[] SENDID = new int[16];
    boolean showLogonAlert;
    byte mcount;
    Form selectForm;
    Command selectOk;
    Command selectBack;
    Command selectAll;
    Command selectDall;
    boolean isGetingUserID;
    public short[] titleID;
    public String[] titleName;
    public String[] titleDesc;
    public short curTitleID;
    public int titleTotal;
    public int titleCurrentPage;
    public int titleTotalPage;

    void friendListSelectForm(String[] ss) {
        selectForm = null;
        selectOk = null;
        selectBack = null;
        cg = null;
        selectAll = null;
        selectForm = new Form("好友选择");
        selectOk = new Command("发送", 4, 1);
        selectAll = new Command("全选", 4, 1);
        selectDall = new Command("全消", 4, 1);
        selectBack = new Command("返回", 2, 2);
        cg = new ChoiceGroup("请选择您要发送的角色：", 2, ss, null);
        selectForm.append((Item) cg);
        selectForm.addCommand(selectOk);
        selectForm.addCommand(selectAll);
        selectForm.addCommand(selectDall);
        selectForm.addCommand(selectBack);
        selectForm.setCommandListener(this);
        aMidlet.display.setCurrent((Displayable) selectForm);
    }

    public void loginRewardUesrId() {
        if (!Cons.cmwap || isGetingUserID) {
            return;
        }
        if (userID != null && !"".equals(userID.trim())) {
            return;
        }
        isGetingUserID = true;
    }

    void drawUICompose(Graphics g) {
        if (baseForm == null) {
            baseForm = new UIForm(0, 0, screenW, screenH, "");
            baseForm.setStyle((byte) 0);
            rims[0] = new UIRim(0, 0, screenW - 1, screenH - 1, (byte) 4);
            UIRim rimName = new UIRim(6, 4, 165, 65, (byte) 4);
            labels[0] = new UILabel(16, 8, 0, 0, "活力值：" + Pets.curvires + "/" + Pets.maxvires, 15653280, (byte) 0, (byte) 0);
            menus[0] = new UIMenu(6, 70, 165, 116, "合成技能", pet.getSkillNames());
            menus[0].setFlushType((byte) 1);
            if ((menus[0]).strs != null) {
                int kk = (menus[0]).strs.length;
                byte i;
                for (i = 0; i < kk; i = (byte) (i + 1)) {
                    if ((menus[0]).strs[i] == null || (menus[0]).strs[i]
                            .length() == 0) {
                        menus[0].setNoUse(i);
                    }
                }
            }
            labels[1] = new UILabel(16, 24, 190, 0, "熟练度：" + (pet.skills[menus[0].getCurrentPointer()]).curSkillExp + "/" + (pet.skills[menus[0].getCurrentPointer()]).maxSkillExp, 15653280, (byte) 0, (byte) 0);
            labels[4] = new UILabel(16, 40, 165, 0, "等级：" + (pet.skills[menus[0].getCurrentPointer()]).skillLevel, 15653280, (byte) 0, (byte) 0);
            labels[2] = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte) 0, (byte) 0);
            labels[3] = new UILabel(0, 0, 0, 0, "操作", 15718815, (byte) 0, (byte) 0);
            baseForm.addComponent(rims[0]);
            baseForm.addComponent(rimName);
            baseForm.addComponent(labels[0]);
            baseForm.addComponent(labels[1]);
            baseForm.addComponent(labels[4]);
            baseForm.addComponent(menus[0]);
            baseForm.addComponentInCenter(labels[2], (byte) 6);
            baseForm.addComponentInCenter(labels[3], (byte) 5);
            if (!menus[0].isMenuNull()) {
                baseForm.setFocus(true);
            }
        }
        baseForm.draw(g);
    }

    private void keyInNPCTitle() {
        UIComponent cmd = baseForm.getCommand();
        if (!"waiting".equals(baseForm.getCurrentFocusForm().getName())) {
            if (isKeyPress(18)) {
                if (baseForm == baseForm.getCurrentFocusForm()) {
                    releaseUI();
                    setGameState((byte) 3);
                    setNPCSubState((byte) 0);
                } else if (baseForm.getCurrentFocusForm().getName().equals("menu")) {
                    baseForm.setAboutForm((UIForm) null);
                } else if (baseForm.getCurrentFocusForm().getName().equals("detail")) {
                    baseForm.setAboutForm((UIForm) null);
                }
            } else if (!actionInForm(cmd) && (isKeyPress(17) || isKeyPress(14))) {
                if (baseForm == baseForm.getCurrentFocusForm()) {
                    if (titleID.length > 0) {
                        String[] strs = {"获得称号", "查看称号"};
                        menus[0] = new UIMenu(35, 0, 80, 0, null, strs);
                        UIForm subForm = new UIForm(0, 0, screenW - 1, screenH - 1, "menu");
                        subForm.setBackGround((byte) 9);
                        subForm.addComponent(menus[0]);
                        menus[0].setXY((menus[0]).positionX, screenH - (menus[0]).height - 4);
                        baseForm.setAboutForm(subForm);
                    }
                } else if (baseForm.getCurrentFocusForm().getName().equals("menu")) {
                    UIForm subForm;
                    UIRim frame;
                    UIRim rimTitle;
                    UILabel lblTitle;
                    UILabel lblCancel;
                    switch (menus[0].getCurrentPointer()) {
                        case 0:
                            curTitleID = titleID[tables[0].getCurrentPointer()];
                            baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                            ni.send(134219776);
                            break;
                        case 1:
                            subForm = new UIForm(0, 0, (short) screenW, (short) screenH, "detail");
                            frame = new UIRim(0, 0, (short) (screenW - 1), (short) (screenH - 1), (byte) 4);
                            subForm.setBackGround((byte) 9);
                            rimTitle = new UIRim(0, 10, 160, 20, (byte) 0);
                            lblTitle = new UILabel(0, (short) (rimTitle.positionY + 2), 0, 0, titleName[tables[0].getCurrentPointer()], 15718814, (byte) 1, (byte) 0);
                            lblCancel = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 1, (byte) 0);
                            textArea[0] = new UITextArea(0, 30, 160, 158, titleDesc[tables[0]
                                    .getCurrentPointer()].trim());
                            textArea[0].setColor(15718814);
                            subForm.addComponent(frame);
                            subForm.addComponentInCenter(rimTitle, (byte) 2);
                            subForm.addComponentInCenter(lblTitle, (byte) 2);
                            subForm.addComponentInCenter(textArea[0], (byte) 2);
                            subForm.addComponentInCenter(lblCancel, (byte) 6);
                            baseForm.setAboutForm(subForm);
                            break;
                    }
                } else if (baseForm.getCurrentFocusForm().getName().equals("msg")) {
                    baseForm.setAboutForm((UIForm) null);
                }
            }
        }
    }

    private void keyInUIManTitle() {
        UIComponent cmd = baseForm.getCommand();
        if (!"waiting".equals(baseForm.getCurrentFocusForm().getName())) {
            if (isKeyPress(10)) {
                if (titleCurrentPage > 0) {
                    titleCurrentPage--;
                    baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                    ni.send(134219008);
                }
            } else if (isKeyPress(12)) {
                if (titleCurrentPage < titleTotalPage - 1) {
                    titleCurrentPage++;
                    baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                    ni.send(134219008);
                }
            } else if (isKeyPress(18)) {
                if (baseForm == baseForm.getCurrentFocusForm()) {
                    setGameState((byte) 1);
                    setRightMenuSubState(-1);
                    baseForm = null;
                } else if (baseForm.getCurrentFocusForm().getName().equals("menu")) {
                    baseForm.setAboutForm((UIForm) null);
                } else if (baseForm.getCurrentFocusForm().getName().equals("detail")) {
                    baseForm.setAboutForm((UIForm) null);
                }
            } else if (!actionInForm(cmd) && (isKeyPress(17) || isKeyPress(14))) {
                if (baseForm == baseForm.getCurrentFocusForm()) {
                    if (titleID.length > 0) {
                        String[] strs = {"更改称号", "查看称号", "关闭所有"};
                        menus[0] = new UIMenu(35, 0, 80, 0, null, strs);
                        UIForm subForm = new UIForm(0, 0, screenW - 1, screenH - 1, "menu");
                        subForm.setBackGround((byte) 9);
                        subForm.addComponent(menus[0]);
                        menus[0].setXY((menus[0]).positionX, screenH - (menus[0]).height - 4);
                        if (Player.getInstance().getTitle() == null
                                || Player.getInstance().getTitle().equals("")) {
                            menus[0].setNoUse((byte) 2);
                        }
                        if (titleName[tables[0].getCurrentPointer()].equals(Player.getInstance().getTitle())) {
                            menus[0].setNoUse((byte) 0);
                        }
                        baseForm.setAboutForm(subForm);
                    }
                } else if (baseForm.getCurrentFocusForm().getName().equals("menu")) {
                    UIForm subForm;
                    UIRim frame;
                    UIRim rimTitle;
                    UILabel lblTitle;
                    UILabel lblCancel;
                    switch (menus[0].getCurrentPointer()) {
                        case 0:
                            curTitleID = titleID[tables[0].getCurrentPointer()];
                            baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                            ni.send(134219520);
                            break;
                        case 2:
                            curTitleID = 0;
                            baseForm.addAboutForm("waiting", "请稍候…", (byte) 0, screenW - 30, 0);
                            ni.send(134219520);
                            break;
                        case 1:
                            subForm = new UIForm(0, 0, (short) screenW, (short) screenH, "detail");
                            frame = new UIRim(0, 0, (short) (screenW - 1), (short) (screenH - 1), (byte) 4);
                            subForm.setBackGround((byte) 9);
                            rimTitle = new UIRim(0, 10, 160, 20, (byte) 0);
                            lblTitle = new UILabel(0, (short) (rimTitle.positionY + 2), 0, 0, titleName[tables[0].getCurrentPointer()], 15718814, (byte) 1, (byte) 0);
                            lblCancel = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 1, (byte) 0);
                            textArea[0] = new UITextArea(0, 30, 160, 158, titleDesc[tables[0]
                                    .getCurrentPointer()].trim());
                            textArea[0].setColor(15718814);
                            subForm.addComponent(frame);
                            subForm.addComponentInCenter(rimTitle, (byte) 2);
                            subForm.addComponentInCenter(lblTitle, (byte) 2);
                            subForm.addComponentInCenter(textArea[0], (byte) 2);
                            subForm.addComponentInCenter(lblCancel, (byte) 6);
                            baseForm.setAboutForm(subForm);
                            break;
                    }
                } else if (baseForm.getCurrentFocusForm().getName().equals("msg")) {
                    baseForm.setAboutForm((UIForm) null);
                }
            }
        }
    }

    public void popCheatCheckForm(int remainTimes, Image img) {
        mc.baseForm = null;
        mc.topForm = null;
        setGameState((byte) 0);
        releaseUI();
        mc.setGameState((byte) 2);
        mc.setLeftMenuSubState(7);
        texts[2] = null;
        texts[2] = new UIText(0, 25, 80, 0, 10, (byte) 2, "");
        texts[2].setMaxNumber(99L);
        baseForm = new UIForm(8, screenH - 70 >> 1, 160, 35, "");
        baseForm.setBackGround((byte) 9);
        baseForm.addInputForm("cheat_check", "输错" + remainTimes + "次踢出", texts[2], 100, img);
    }

    public void keyInCheatCheck() {
        actionInForm(texts[2]);
        if (isKeyPress1(17)) {
            PCLogin.cheatCheckValue = (byte) texts[2].getNumber();
            baseForm.setAboutForm((UIForm) null);
            ni.send(16782592);
            mc.setGameState((byte) 0);
            setLeftMenuSubState(-1);
            releaseUI();
        }
    }
}
