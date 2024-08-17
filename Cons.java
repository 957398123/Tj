public abstract class Cons {
  public static int VER = 83886592;
  
  public static boolean isStartNote = false;
  
  public static boolean isSohu;
  
  public static String url_mobile_ip = "";
  
  public static String url_mobile_jad = "";
  
  public static String url_mobile_notify = "";
  
  public static String url_mobile_jar = "";
  
  public static byte jadLogType = 0;
  
  public static String jadBody = "";
  
  public static boolean isHasJad = false;
  
  public static boolean isDL;
  
  public static final String[] STR_MAINMENU2 = new String[] { "登 录", "快速注册", "注 册", "充 值", "帮 助", "关 于", "退 出", "更多游戏", "设 置" };
  
  public static final String[] STR_MAINMENU_SH = new String[] { "登 录", "快速注册", "注 册", "绑 定", "帮 助", "关 于", "退 出", "设 置", "游 客", "搜狐游戏" };
  
  public static final String[] STR_MAINMENU = new String[] { "登 录", "快速注册", "更多游戏", "注 册", "绑 定", "帮 助", "关 于", "退 出", "设 置", "游 客" };
  
  public static final String[] STR_MAINMENU_DL = new String[] { "登 录", "快速注册", "注 册", "绑 定", "帮 助", "关 于", "退 出", "设 置", "游 客" };
  
  public static String exittitle = "信息";
  
  public static String exitinfo = "更多热门游戏下载";
  
  public static String exiturl = null;
  
  public static short channelOption = 0;
  
  public static boolean isLocal = false;
  
  /**
   * 是否为中国移动端
   */
  public static boolean isCmobile;
  
  public static boolean isTestC;
  
  public static boolean isWapCharge;
  
  public static String LOCAL_SERVER_LIST = "";
  
  public static final int MAX_MONEY_VALUE = 1999999999;
  
  public static final String ATTACTED = "你正在被攻击";
  
  public static final int MAN_FRAME_W = 32;
  
  public static final int MAN_FRAME_H = 40;
  
  public static final String WAIT_WORD = "请稍候…";
  
  public static String phoneType = "";
  
  public static final char[] acceptChars = new char[] { 
      ' ', ',', '.', ':', ';', '?', '!', '，', '。', ' ', 
      '；', '？', '！', '：' };
  
  public static final String[] MENU_CS = new String[] { "提交问题", "回答问题" };
  
  public static final String[] MENU_CS_QUIZ = new String[] { "用户端", "充值问题", "游戏问题", "地图问题", "道具问题", "游戏建议", "游戏漏洞" };
  
  public static final String[] MENU_CS_GRADE = new String[] { "很满意", "满意", "不满意-游戏设置不合理", "不满意-客服态度不满意" };
  
  public static final byte PET_LEARN_SKILLS_MAX_NUMBER = 4;
  
  public static final int AUCTION_COLOR_BORDER = 99999;
  
  public static final int AUCTION_BORDER_COLOR = 16739328;
  
  public static final int PACKAGE_Y = 113;
  
  public static final String[] ROLL_MASSAGE = new String[] { 
      "体力提升会增加HP和物理防御力。适合职业：剑客、医师。", "力量提升会增加物理攻击力。适合职业：剑客、刺客。", "智慧提升会增加MP和法系伤害值。适合职业：道士、医师。", "敏捷提升会增加防御、命中率和回避率。适合职业：剑客、刺客。", "幸运提升会增加暴击率，并对命中和回避有轻微影响。适合职业：刺客、道士、医师。", "升级可以累积技能点数，积累足够的点数可以学会相应的技能。", "人物周围的8个格子表示人物当前的装备，可从物品栏中选择装备更换，也可从装备栏中卸载装备。", "确定交易前请先锁定交易栏。", "宠物的灵力决定合成的次数，灵魄影响忠诚度需要靠宠物食物来补充，忠诚度决定宠物是否工作。", "按左软键增加或取下随信附加的物品或金钱。", 
      "在系统菜单设置项中选择技能键位，你可以将药品、技能等定义在数字键上，方向键控制选框移动，将选框放置在想要的功能上，从1~8中按一个喜欢的数字键定义即可。", "根据玩家不同的需求打开或者关闭各项开关，选择关闭就不可见了。", "猛犸币是《天劫》游戏中的特殊货币，可以用来购买增值道具，玩家使用增值道具能大力提升自己的各方面能力！猛犸币获取方式有短信、大额充值（神州行充值卡）。", "玩家可在游戏客户端中按照省市信息搜索出在同一服务器同一阵营的玩家。" };
  
  public static final String RESOURCE_NAME_1 = "/uimenu.pkg";
  
  public static final String RESOURCE_NAME_2 = "/uiuse.pkg";
  
  public static final String[] STR_POP = new String[] { "@PLAYERNAME，欢迎来到天劫的世界！你可以通过$方向键$移动，$按#键$切换锁定目标，然后用$左软键$或$中间键$对光标选中的目标进行各种操作，按$中间键$可以直接选中最近的一个目标。$右软键$可调出系统菜单对自己进行操作。按$0键$可以切换画质。", "冒险过程中收集到的物品会存在$背包$中，可以通过主菜单中的背包选项查询自己的物品。当你的血不多时就要及时补充，可以将药品放在$下方快捷键$上直接使用，也能在背包中使用。", "@PLAYERNAME，$按＊键$可以调出聊天菜单进行聊天，选中某位玩家后按$左软键$可以加其为好友，还可以组成队伍。一同去战斗吧！", "恭喜您升级了！每次升级您都会获得$4$点$属性点数$，用$右软键$打开系统菜单，选择$人物-属性$即可进入属性点分配界面。点击“关闭”直接进入属性分配界面。", "@PLAYERNAME，看到那个头顶$黄色叹号$的人了吗？以后看到$黄色叹号$说明可以从他那里接任务；$灰色叹号$说明这里有任务可接，但你还没有满足接任务的条件；看到$灰色问号$说明你的任务还没有完成；$闪亮的问号$就可以向他回复任务了。快去看看吧！", "文本框中直接用$数字键$输入数字，方向键$左$为清除。", "@PLAYERNAME，经过几次磨炼，你终于成长了，可以向XXX取得一只宠物。", "您当前没有绑定帐号，为了您的安全请点击菜单中的服务=〉帐号绑定。如果您已经绑定了当前账号并且收到确认短信，请您先退回主界面进行回馈确认后再更换游戏角色。", "恭喜您升级了！每次升级您还将获得$1$点$技能点数$，用$右软键$打开系统菜单，选择$人物-技能-技能树$，可消耗技能点数来学习新$技能$；选择$人物-技能-按键设置$，可以将各种技能定义在1～8的快捷键上。点击“关闭”直接进入技能学习。", "您已经$完成$了一个$任务$，请尽快找相应的NPC交任务。如果想$关闭此类提示$请按右软键调出$系统菜单$-->$设置$-->$显示设置$中关闭$新手帮助$即可。" };
  
  public static final byte POP_STARTE = 0;
  
  public static final byte POP_UPLEVEL = 1;
  
  public static final byte POP_EVENT = 2;
  
  public static final byte POP_INPUT = 3;
  
  public static final byte POP_PET = 4;
  
  public static final byte POP_BIND = 5;
  
  public static final byte POP_UPLEVEL2 = 6;
  
  public static final byte POP_QUEST = 7;
  
  public static final byte SHOP_GRID_MAX_NUMBER = 18;
  
  public static final byte PET_STUFF_LENGTH = 60;
  
  public static final int creatManRimX = 15;
  
  public static final int creatManRimY = 120;
  
  public static final int creatManRimW = 95;
  
  public static final int creatManRimH = 82;
  
  public static final String[] UI_HELP_MENU = new String[] { "天劫论坛", "客服中心", "帐号绑定", "游戏公告" };
  
  public static final String[] UI_HELP_MENU2 = new String[] { "客服中心", "帮助说明" };
  
  public static final String[] COMPOSITE_SKILL = new String[] { 
      "", "采集", "挖矿", "炼丹", "战系防具合成", "法系防具合成", "战系武器合成", "法系武器合成", "废弃", "材料合成", 
      "宝石合成", "饰品合成", "非法4", "非法5", "非法6" };
  
  public static final String[] compare_menu = new String[] { 
      "名称", "攻击", "防御", "耐久", "凹槽", "体力", "力量", "智慧", "敏捷", "幸运", 
      "精炼", "攻速", "职业", "需要", "烈风", "灵火", "神雷", "风抗", "火抗", "雷抗" };
  
  public static final String[] EQUIP_NAME = new String[] { "头盔", "铠甲", "裤子", "项链", "武器", "鞋子", "戒指", "戒指" };
  
  public static final byte MAX_WATER_ID = 28;
  
  public static final String cmGameWap = "http://go.i139.cn/gcomm1/portal/spchannel.do?url=http://gamepie.i139.cn/wap/s.do?j=3channel";
  
  public static final String cmGameExitWap = "http://go.i139.cn/gcomm1/portal/spchannel.do?url=http://gamepie.i139.cn/wap/s.do?j=3channel";
  
  public static final byte MAX_NUMBER_WORDS = 30;
  
  public static byte SINAL_ROW_HEIGHT = 15;
  
  public static final int[] STUFF_NAME_COLOR = new int[] { 16777215, 65280, 50943, 16731903, 16739328 };
  
  public static final String SAVE_USER_NAME = "userNW";
  
  public static final byte IMG_ID_DIVISION = 50;
  
  public static final int USE_STUFF_MIN_ID = 11000;
  
  public static final int USE_STUFF_MAX_ID = 12000;
  
  public static final byte PACKAGE_SIZE = 36;
  
  public static final int VIEW_SIZE = 80;
  
  public static final int FREEZE = 300;
  
  public static final int MAIL_X = 2;
  
  public static final int MAIL_Y = 221;
  
  public static final byte BUSINESS_TYPE = -1;
  
  public static final String[] STR_TEAM_OPERATE = new String[] { "踢人出队", "设定队长", "退出队伍" };
  
  public static final int[] chatChannalColor = new int[] { 16776960, 16776960, 16745472, 65535, 16777215, 65280, 16711680, 16777215, 16745472 };
  
  public static final String[] STR_DESCRIPTION = new String[] { "剑客有着强烈的使命感，也是满怀理想的武学家，他们终其一生学习武器与战斗技能。他们可使用大部分武器与防具，同时也拥有最强壮的身体、最高的物理攻击力和防御力。", "道士们天生就有着异于常人的超群智慧，身体柔弱却有着超高魔法伤害能力，他们不擅长使用野蛮的武力进行攻击，但是能够通过吟唱操纵威力无穷的灵气进行攻击，毁灭周围的一切。同时道士们强大的范围法术还可以同时伤害多个目标。", "医师是一群深谙治疗法术的人，他们能对其他人进行治疗，并且运用各种增益法术给队友的战斗以最大的辅助。同时医师能施展强大的烈风魔法给敌人致命打击，甚至精通禁断的上古神秘魔法——起死回生。", "刺客是个神秘的职业，他们拥有游戏中最高的闪避和命中能力，刺客们擅长的武器是短刀和弯刀，暗器的杀伤力绝对不容忽视。刺客为了追求灵巧身姿摒弃了厚重的铠甲，薄弱防御力往往使他们在行动过程中受到致命的伤害。" };
  
  public static final String[] STR_CHAT_OPERATION = new String[] { "其他玩家", "人物名字", "新手帮助", "喇叭显示" };
  
  public static final String[] UNION_NAMES = new String[] { "", "族长", "长老", "成员" };
  
  public static final String[] NPC_EXCHANGE_0 = new String[] { 
      "兑换煤块", "兑换青铜块", "兑换玄铁", "兑换玄钢", "兑换金块", "兑换银块", "兑换紫晶泪", "兑换红玉结晶", "兑换乌金石", "兑换太极博石", 
      "兑换赤邪玉", "兑换博石诀", "兑换幽冥宝石", "兑换彩纹凤石", "兑换白玉如意", "兑换五彩金石", "兑换神农玉", "兑换女娲石", "兑换碧玉结晶" };
  
  public static final String[] NPC_EXCHANGE_1 = new String[] { 
      "兑换棉线", "兑换棉绒", "兑换牛皮囊", "兑换麻布", "兑换麻线", "兑换虎皮毯", "兑换羊绒布", "兑换虎筋甲", "兑换影木丝", "兑换金蚕丝", 
      "兑换雪狐皮", "兑换奚鼠皮", "兑换鲲鹏锦", "兑换饕餮绫", "兑换血马纱", "兑换玄龟甲", "兑换彩凰翼", "兑换魍魉毡", "兑换麒麟毡" };
  
  public static final String[] NPC_EXCHANGE_2 = new String[] { "喂养指南换煤", "技能指南换煤", "小碧月果换煤", "大碧月果换煤", "小月牙果换煤", "大月牙果换煤", "小火龙果换煤", "大火龙果换煤" };
  
  public static final String[] NPC_SMITH = new String[] { "查看", "选中", "丢弃" };
  
  public static final String[] DRAMATIS_STORE = new String[] { "查看物品", "移动物品", "存入物品", "取出物品", "丢弃物品", "背包整理", "仓库整理", "存入金钱", "取出金钱" };
  
  public static final String[] PET_NAME = new String[] { "血炼神鸟", "瑞祥麒麟", "五彩天狐", "幽炎蝠虎" };
  
  public static final String[] PET_dETAIL = new String[] { "百鸟之王，其形如凤凰，炼狱中生，生性高洁，饮必择食，栖必择枝，是高贵之兽。", "麒麟是灵兽之首，其品性仁慈、口能吐火，声音如雷是济世瑞祥之物。只有法力无边之人方能驾驭。", "天地之兽，以世间灵气幻化而成。其五彩之尾夜如皓，晨如霞。", "蝠虎又称战神，是百兽之王，其形如虎，翅如蝠，生性好战是威武的象征。" };
  
  public static final byte[][] MOTIONS = new byte[][] { { 
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
        10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 
        20 }, { 
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
        10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 
        20 }, { 
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
        10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 
        20 }, { 
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
        10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 
        20 }, { 
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
        10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 
        20 }, { 
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
        10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 
        20 }, { 
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
        10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 
        20 }, { 
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
        10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 
        20 } };
  
  public static final String[] SKILL_MENU = new String[] { "技能树", "技能键位" };
  
  public static final String[] LEAVE_MENU = new String[] { "重选人物", "回主菜单" };
  
  public static final String[] BUY_MENU = new String[] { "买入", "查看", "卖出", "丢弃" };
  
  public static final String[] VENDUE_MENU = new String[] { "我要买", "我要卖", "我拍卖的物品" };
  
  public static final String[] BUSINESS_MENU = new String[] { "查看", "放置", "取回", "锁定", "重置", "交易", "丢弃", "拆分" };
  
  public static final String[] MAIL_MENU = new String[] { "查看邮件", "撰写邮件" };
  
  public static final byte EQUIP_USE = 0;
  
  public static final byte LOOKUP = 1;
  
  public static final byte DISCARD = 2;
  
  public static final byte MOVE = 3;
  
  public static final byte TRIM = 4;
  
  public static final byte COMPARE = 5;
  
  public static final String[] STUFF_MENU_0 = new String[] { "装备", "查看", "丢弃", "移动", "整理", "比较" };
  
  public static final String[] STUFF_MENU_1 = new String[] { "喂养", "查看", "丢弃", "移动", "整理" };
  
  public static final String[] STUFF_MENU_2 = new String[] { "使用", "查看", "丢弃", "移动", "整理" };
  
  public static final String[] STUFF_MENU_3 = new String[] { "查看", "卸下", "丢弃", "比较" };
  
  public static final String[] STUFF_MENU_4 = new String[] { "镶嵌", "查看", "丢弃", "移动", "整理" };
  
  public static final String[] PET_SKILL = new String[] { 
      "", "采集", "挖矿", "炼丹", "战系防具合成", "法系防具合成", "战系武器合成", "法系武器合成", "打折", "非法2", 
      "非法3", "非法4", "非法5", "非法6" };
  
  public static final byte[] PET_SKILL_IMAGE_ID = new byte[] { 
      0, 40, 38, 35, 26, 25, 4, 15, 43, 0, 
      0, 0, 0, 0 };
  
  public static final String[] STR_LEFT_MENU = new String[] { "观察", "加为好友", "组队邀请", "私聊", "交易", "切磋邀请", "跟随" };
  
  /**
   * 右键菜单选项
   */
  public static final String[] STR_RIGHT_MENU = new String[] { "1 挂机", "2 人物", "3 背包", "4 社交", "5 任务", "6 地图", "7 队伍", "8 设置", "9 服务", "# 离开" };
  
  public static final String[] STR_RUN_MENU_MAN = new String[] { "属性", "背包", "技能" };
  
  public static final String[] STR_LOCAL_PLACE = new String[] { "华北", "华中", "华东", "华南", "东北", "西北", "西南" };
  
  public static final String[] STR_GOOD_FRIENDS = new String[] { "查询玩家", "添加好友", "好友列表", "黑名单", "氏　　族", "聊　　天" };
  
  public static final String[] STR_MENU_SETUP = new String[] { "方向键位", "技能键位", "显示设置", "游戏设置" };
  
  public static final String[] STR_PET_LOOKUP = new String[] { "宠物信息", "宠物技能" };
  
  public static final String[] STR_COMMON = new String[] { "阵营", "种族", "职业", "性别", "姓名", "最多五个汉字", "确定" };
  
  public static final String[] STR_CAMP = new String[] { "天人界", "修罗界" };
  
  public static final String[] STR_GENDER = new String[] { "男性", "女性" };
  
  public static final String[] STR_RACE = new String[] { "人族", "仙族", "妖族", "魔族" };
  
  public static final String[] STR_PROFESSION = new String[] { "剑客", "道士", "医师", "刺客" };
  
  public static final byte WCK = 0;
  
  public static final byte WJK = 1;
  
  public static final byte WDS = 2;
  
  public static final byte WYS = 3;
  
  public static final byte BYS = 4;
  
  public static final byte BCK = 5;
  
  public static final byte BJK = 6;
  
  public static final byte BDS = 7;
  
  public static final String[] STR_PLAYERS = new String[] { "人族男刺客", "人族女剑客", "仙族男道士", "仙族女医师", "妖族男医师", "妖族女刺客", "魔族男剑客", "魔族女道士" };
  
  public static final String[][] SKILL_DETAIL_EX = new String[][] { { "红瓶", "使用背包中增加HP最多的物品，每次使用一个" }, { "蓝瓶", "使用背包中增加MP最多的物品，每次使用一个" }, { "紫瓶", "使用背包中增加HP和MP最多的物品，每次使用一个" }, { "近身攻击", "近身攻击" } };
  
  public static final String[] STR_SWORDMAN_SKILL = new String[] { 
      "近身攻击", "天翔剑", "致命打击", "雷霆重击", "嗜血咆哮", "战神怒吼", "雷动九天", "战系精通", "金刚御体", "强化战甲", 
      "灵魔护体", "明镜芷水", "狂牙剑气", "强化眩晕", "旋风霸斩" };
  
  public static final String[] STR_TAOIST_SKILL = new String[] { 
      "近身攻击", "雷星击", "紫极狂雷", "电光石火", "天雷神盾", "强化法暴", "炎暴术", "炙炎破", "狱炎之地", "焰舞火球", 
      "法术增效", "轮回之门", "金刚咒", "玄灵结界", "真元凝聚" };
  
  public static final String[] STR_APOTHECARY_SKILL = new String[] { 
      "近身攻击", "飓风之牙", "心疗术", "清心灵咒", "回春术", "天降甘霖", "神农心法", "猛毒蛊", "鬼灵地缚", "天破龙卷", 
      "疾风之翼", "黄帝内经", "灵甲真言", "起死回生", "愈合之契" };
  
  public static final String[] STR_ASSASSIN_SKILL = new String[] { 
      "近身攻击", "涂毒武器", "蚀骨刺心", "强化蚀骨", "漫天花雨", "精速利刃", "猛毒传染", "强化致命", "疾风震波", "刀剑精通", 
      "腐骨缠身", "黑暗偷袭", "妖影魔心", "迷踪幻影", "轻功瞬步" };
  
  public static final String[] STR_CHAT_CHANNEL = new String[] { "", "[私]", "[世]", "[族]", "[场]", "[队]", "", "", "[喇]" };
  
  public static final String[] STR_CHAT_FAST = new String[] { "私聊频道", "世界频道", "氏族频道", "场景频道", "组队频道", "喇叭频道" };
  
  public static String[] PRODUCT = new String[] { 
      "空中猛犸版权所有，中国地", "区独家发行", "空中猛犸是空中网旗下知名", "手机游戏品牌", "空中网(Nasdaq: KONG) 是中", "国手机娱乐先锋门户和中国", "领先的2.5G 无线增值服务提", "供商　", "www.kongzhong.com", "客服电话：", 
      "022-58113111", "客服手机：", "15900216764", "gamekefu@kongzhong.com", "", "工作室总经理", "Angel", "制作人", "燕赵", "主策", 
      "slash", "策划", "哆啦A梦    赵正义", "主程", " 星空 ", "程序", "娜林斯    看不见我", "DAVID    VAMPIRE", "LY    eastman", "Freud    风雨金刚", 
      "主美", "鸟孩子", "主测", "dragonafanti", "测试", "Seraph    DDING", "玖玥雅晓   大烧麦", "傻小子     落月随云" };
  
  public static final byte[] beginPlace = new byte[] { 0, 3, 14, 15 };
  
  public static final byte GROUP_NONE = 0;
  
  public static final byte GROUP_WHITE = 1;
  
  public static final byte GROUP_BLACK = 2;
  
  public static final byte RACE_NONE = 0;
  
  public static final byte RACE_HUMAN = 1;
  
  public static final byte RACE_ELF = 2;
  
  public static final byte RACE_EVIL = 3;
  
  public static final byte RACE_DEMON = 4;
  
  public static final byte PROFESSION_NONE = 0;
  
  public static final byte PROFESSION_SWORDMAN = 1;
  
  public static final byte PROFESSION_TAOIST = 2;
  
  public static final byte PROFESSION_APOTHECARY = 3;
  
  public static final byte PROFESSION_ASSASSIN = 4;
  
  public static final byte MALE = 1;
  
  public static final byte FEMALE = 2;
  
  public static final byte[][] PLAYERS = new byte[][] { { 1, 4, 1 }, { 1, 1, 2 }, { 2, 2, 1 }, { 2, 3, 2 }, { 3, 3, 1 }, { 3, 4, 2 }, { 4, 1, 1 }, { 4, 2, 2 } };
  
  public static final byte GAMEOBJ_NONE = 0;
  
  public static final byte GAMEOBJ_OTHERPLAYER = 1;
  
  public static final byte GAMEOBJ_MONSTER = 2;
  
  public static final byte GAMEOBJ_NPC = 3;
  
  public static final byte GAMEOBJ_PLAYER = 4;
  
  public static final byte GAMEOBJ_COLLECTION = 5;
  
  public static final byte DIRECT_NULL = 0;
  
  public static final byte DIRECT_UP = 1;
  
  public static final byte DIRECT_DOWN = 2;
  
  public static final byte DIRECT_LEFT = 3;
  
  public static final byte DIRECT_RIGHT = 4;
  
  public static final byte DIRECT_UP_LEFT = 5;
  
  public static final byte DIRECT_UP_RIGHT = 6;
  
  public static final byte DIRECT_DOWN_LEFT = 7;
  
  public static final byte DIRECT_DOWN_RIGHT = 8;
  
  public static final int RECT_W = 25;
  
  public static final int RECT_H = 24;
  
  public static final int PLAYER_POINT_X = 130;
  
  public static final int PLAYER_POINT_Y = 12;
  
  public static final byte UNION_NONE = 0;
  
  public static final byte UNION_ADMINISTRATOR = 1;
  
  public static final byte UNION_PRESBYTER = 2;
  
  public static final byte UNION_MEMBER = 3;
  
  public static final byte SKILL_BASIC = 1;
  
  public static final byte SKILL_ACTIVE = 3;
  
  public static final byte SKILL_PASSIVE = 2;
  
  public static final byte SKILL_TARGET_ALL = 1;
  
  public static final byte SKILL_TARGET_FRIEND = 2;
  
  public static final byte SKILL_TARGET_FRIEND_NOT_SELF = 3;
  
  public static final byte SKILL_TARGET_TEAM_SELF = 4;
  
  public static final byte SKILL_TARGET_TEAM_OTHER = 5;
  
  public static final byte SKILL_TARGET_SELF = 6;
  
  public static final byte SKILL_TARGET_ENEMY = 7;
  
  public static final byte SKILL_TARGET_AUTO = 8;
  
  public static final byte SKILL_USE_TYPE = 0;
  
  public static final byte SKILL_TARGET = 1;
  
  public static final byte SKILL_CAST_RADIUS = 2;
  
  public static final byte SKILL_MP_COST = 3;
  
  public static final byte SKILL_CAST_TIME = 4;
  
  public static final byte SKILL_CD_TIME = 5;
  
  public static final byte SKILL_MP_ADDITION = 6;
  
  public static final String[][][] SKILL_DETAIL = new String[][][] { { 
        { "", "" }, { "近战技能，给予一个敌人造成120%伤害。", "每升一级伤害增加6%" }, { "辅助技能，使剑客的暴击率提高20%，持续30秒。", "每升一级暴击率提高2%" }, { "近战技能，给予一个敌人造成些许伤害，50%几率击晕敌人。", "每升一级，伤害值增加5%" }, { "辅助技能，提高自己的攻击力20%并降低防御力65%，持续10秒。", "每升一级，攻击力增加5%，防御力少减3%，时间加1秒" }, { "辅助技能，提高自己周围友方玩家10%的攻击力，持续20秒。", "每升一级，攻击力增加1%，持续时间增加1秒" }, { "终极技能，给敌人造成很大伤害，并附加雷系伤害。", "每升一级，伤害增加2%，雷系伤害增加2%" }, { "被动技能，永久提高剑客2%的物理攻击力。", "每升一级，攻击力增加1%" }, { "辅助技能，使自己的防御力提高30%，持续5分钟。", "每升一级，防御力增加2%" }, { "被动技能，使自己所穿的战甲的防御力永久提高5%。", "每升一级，战甲的防御力提高1%" }, 
        { "辅助技能，使自己处于无敌状态，持续10秒。", "每升一级，持续时间增加2秒" }, { "被动技能，使自己的雷火抗性永久加4点，风抗加3点。", "每升一级，风雷火抗性增加1点" }, { "远程技能，给予敌人60%的远程伤害。", "每升一级，伤害增加5%" }, { "被动技能，在近战攻击时有6%的几率使敌人眩晕3秒。", "每升一级，眩晕几率增加1%" }, { "近战技能，使自己周围的近身敌人都会眩晕2秒。", "每升一级，眩晕时间增加1秒" } }, { 
        { "", "" }, { "远程技能，对单个敌人造成90%的雷系伤害。", "每升一级，雷系伤害值会增加3%" }, { "远程瞬发技能，对单个敌人造成38%的雷系伤害。", "每升一级，雷系伤害值会增加2%" }, { "范围瞬发技能，对周围的敌人造成18%雷系伤害。", "每升一级，雷系伤害值会增加1%" }, { "辅助技能，1分钟内吸收相当于自身法术伤害的140%的伤害值。", "每升一级，吸收的伤害增加15%" }, { "被动技能，使道士的暴击率永久提高2%。", "每升一级提高1%" }, { "远程技能，对单个敌人造成107%的火系伤害。", "每升一级，火系伤害值会增加2%" }, { "远程瞬发技能，造成30%火系伤害，100%几率击晕对方5秒。", "每升一级，火系伤害值会增加4%" }, { "远程技能，对火柱周围的敌人造成70%的火系伤害。", "每升一级，火系伤害值会增加2%" }, { "终极技能，对敌人造成150%的火系伤害，并溅射伤害到周围的敌人。", "每升一级，火系伤害值会增加10%" }, 
        { "被动技能，使道士的法术伤害永久提高2%。", "每升一级，法术伤害提高2%" }, { "辅助技能，使自己快速返回主城。", "施法时间较长" }, { "辅助技能，使自己的防御力提高100%，持续2分钟。", "每升一级，防御力提高20%" }, { "辅助技能，把伤害转化到自己的魔法值上消耗，持续30秒。", "每升一级，持续时间增加1秒" }, { "辅助技能，一段时间内提高友方或自己的法术伤害20%。", "每升一级，持续时间增加2秒，法术伤害增加2%" } }, { 
        { "", "" }, { "远程技能，对单个敌人造成86%的风系伤害。", "每升一级，风系伤害值会增加4%" }, { "恢复技能，给自己或友方恢复自身法术伤害125%的血量。", "每升一级，血量增加提高5%" }, { "辅助技能，消除一定范围内友方异常状态。", "" }, { "快速恢复技能，给自己或友方恢复自身法术伤害50%的血量。", "每升一级，血量增加提高2%" }, { "恢复技能，给周围的友方恢复自身法术伤害50%的血量。", "每升一级，血量增加提高2%" }, { "被动技能，30%几率使回春术的治疗效果翻倍。", "" }, { "辅助技能，对敌人造成10％的毒素伤害，持续12秒。", "每升一级，持续时间增加1秒，伤害增加2%" }, { "瞬发技能，30秒内降低敌人的所有抗性20点，使敌人减速10秒。", "每升一级，抗性减少10点，减速时间增加1秒" }, { "瞬发技能，对单个敌人造成50%的风系伤害。", "每升一级，风系伤害值会增加2%" }, 
        { "辅助技能，给自己或友方的移动速度提高1分钟。", "每升一级，持续时间增加30秒" }, { "瞬发技能，使友方的恢复魔法的速度提高，持续2秒。", "每升一级，持续时间增加2秒，魔法恢复量提高10%" }, { "辅助技能，给自己或友方的生命值上限提高6分钟。", "每升一级，持续时间增加2分钟，生命值上限增加2%" }, { "恢复技能，令死亡的友方人员原地复活。", "" }, { "恢复技能，在20秒内，每一跳恢复自身法术伤害12%的血量。", "每升一级，血量恢复提高2%" } }, { 
        { "", "" }, { "被动技能，使刺客在攻击时有4%的几率使对方中毒。", "每升一级，中毒几率提高1%，持续时间增加1秒" }, { "近战技能，瞬间对单个敌人造成100%的伤害。", "每升一级，伤害值会增加6%" }, { "被动技能，9%几率为蚀骨刺心增加减速效果。", "每升一级，几率增加3%" }, { "远程技能，使用暗器攻击，并有10%的几率使敌人中毒。", "每升一级，伤害值会增加5%，中毒几率增加1%" }, { "辅助技能，给自己增加攻击频率持续8秒。", "每升一级，持续时间增加3秒" }, { "终极技能，使自己周围的敌人中毒30秒，并给他们20%的伤害。", "每升一级，伤害增加1%" }, { "被动技能，永久增加刺客2%的暴击率。", "每升一级，暴击率提高2%" }, { "近战技能，风系攻击，对目标敌人造成86%的伤害。", "每升一级，伤害增加4%" }, { "被动技能，使自己所装备的武器的攻击力永久提高2%。", "每升一级，攻击力提高2%" }, 
        { "远程技能，使敌人在6秒内不能释放任何技能攻击。", "每升一级，持续时间增加1秒" }, { "远程技能，使远处的一个敌人眩晕4秒。", "每升一级，持续时间增加1秒" }, { "辅助技能，降低一个敌人的命中率30%，持续30秒。", "每升一级，持续时间增加2秒，降低增加2%" }, { "辅助技能，提高自己的回避率12%，持续15秒。", "每升一级，回避率增加3%，持续时间增加2秒" }, { "辅助技能，瞬间提高刺客的移动速度，持续20秒。", "每升一级，持续时间增加5秒" } } };
  
  public static short[][] SKILL_SWORDMAN = new short[][] { 
      { 1, 0, 0, 0, 0, 0, 0 }, { 3, 7, 23, 15, 0, 3, 2 }, { 3, 6, 23, 35, 0, 168, 2 }, { 3, 7, 23, 30, 0, 10, 5 }, { 3, 6, 0, 40, 1, 126, 0 }, { 3, 6, 0, 60, 0, 42, 0 }, { 3, 7, 23, 90, 0, 42, 5 }, { 1, 0, 0, 0, 0, 0, 0 }, { 3, 6, 0, 40, 1, 0, 4 }, { 1, 0, 0, 0, 0, 0, 0 }, 
      { 3, 6, 0, 40, 0, 126, 4 }, { 1, 0, 0, 0, 0, 0, 0 }, { 3, 7, 70, 25, 0, 14, 3 }, { 2, 0, 0, 0, 0, 0, 0 }, { 3, 6, 0, 60, 0, 84, 5 } };
  
  public static short[][] SKILL_TAOIST = new short[][] { 
      { 1, 0, 0, 0, 0, 0, 0 }, { 3, 7, 80, 35, 2, 0, 5 }, { 3, 7, 60, 50, 0, 10, 6 }, { 3, 6, 0, 80, 0, 1, 10 }, { 3, 6, 0, 45, 0, 42, 5 }, { 1, 0, 0, 0, 0, 0, 0 }, { 3, 7, 80, 40, 3, 0, 5 }, { 3, 7, 60, 55, 0, 17, 6 }, { 3, 7, 60, 90, 5, 0, 10 }, { 3, 7, 70, 125, 6, 0, 15 }, 
      { 1, 0, 0, 0, 0, 0, 0 }, { 3, 0, 0, 150, 10, 168, 0 }, { 3, 6, 0, 50, 3, 0, 5 }, { 3, 6, 0, 45, 0, 14, 10 }, { 3, 2, 0, 80, 3, 168, 15 } };
  
  /**
   * 技能数据
   * 0-
   * 1-技能类型
   * 2-施法距离
   * 3-
   * 4-施法长度 单位秒
   * 5-技能CD时间
   */
  public static short[][] SKILL_APOTHECARY = new short[][] { 
      { 1, 0, 0, 0, 0, 0, 0 }, { 3, 7, 80, 35, 2, 0, 5 }, { 3, 2, 80, 60, 2, 0, 10 }, { 3, 2, 80, 200, 2, 0, 10 }, { 3, 2, 80, 100, 1, 0, 10 }, { 3, 6, 0, 200, 5, 0, 15 }, { 1, 0, 0, 0, 0, 0, 0 }, { 3, 7, 80, 55, 0, 1, 5 }, { 3, 7, 80, 150, 0, 14, 15 }, { 3, 7, 60, 50, 0, 10, 6 }, 
      { 3, 5, 60, 100, 4, 0, 10 }, { 3, 3, 60, 100, 0, 168, 10 }, { 3, 5, 60, 60, 3, 0, 8 }, { 3, 3, 80, 200, 10, 0, 0 }, { 3, 5, 80, 65, 0, 2, 5 } };
  
  public static short[][] SKILL_ASSASSIN = new short[][] { 
      { 1, 0, 0, 0, 0, 0, 0 }, { 2, 0, 0, 0, 0, 0, 0 }, { 3, 7, 23, 25, 0, 7, 3 }, { 1, 0, 0, 0, 0, 0, 0 }, { 3, 7, 80, 30, 0, 14, 4 }, { 3, 6, 0, 45, 0, 168, 5 }, { 3, 7, 30, 100, 0, 84, 6 }, { 1, 0, 0, 0, 0, 0, 0 }, { 3, 7, 23, 25, 0, 7, 4 }, { 1, 0, 0, 0, 0, 0, 0 }, 
      { 3, 7, 80, 35, 0, 42, 5 }, { 3, 7, 80, 35, 0, 84, 5 }, { 3, 7, 0, 20, 0, 42, 4 }, { 3, 6, 0, 35, 0, 168, 5 }, { 3, 6, 0, 50, 0, 168, 2 } };
  
  public static final byte TEAM_NONE = 0;
  
  public static final byte TEAM_LEADER = 1;
  
  public static final byte TEAM_MEMBER = 2;
  
  public static final byte TEAM_INVITE = 1;
  
  public static final byte TEAM_EXPEL = 2;
  
  public static final byte TEAM_CHANGE = 3;
  
  public static final byte TEAM_EXIT = 4;
  
  public static final int BUFFER_NONE = 0;
  
  public static final int[] BUFFER_TYPE = new int[] { 
      1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 
      1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 
      1048576, 2097152, 1073741824 };
  
  public static final byte PET_SKILL_NONE = 0;
  
  public static final byte PET_SKILL_GATHER = 1;
  
  public static final byte PET_SKILL_MINING = 2;
  
  public static final byte PET_SKILL_COMPOSE_MEDICINE = 3;
  
  public static final byte PET_SKILL_WARRIOR_ARMMAKE = 4;
  
  public static final byte PET_SKILL_RABBI_ARMMAKE = 5;
  
  public static final byte PET_SKILL_WEAPONHEAVY = 6;
  
  public static final byte PET_SKILL_WEAPONLIGHT = 7;
  
  public static final byte PET_SKILL_ADVERTISEMENT = 8;
  
  public static final byte PET_SKILL_CLOTH = 9;
  
  public static final byte PET_SKILL_LEATHER = 10;
  
  public static final byte PET_SKILL_PLATE = 11;
  
  public static final byte PET_SKILL_VEND = 12;
  
  public static final byte PET_SKILL_PACK = 13;
  
  public static final byte CHANNEL_NONE = 0;
  
  public static final byte CHANNEL_PRIVATE = 1;
  
  public static final byte CHANNEL_WORLD = 2;
  
  public static final byte CHANNEL_GUILD = 3;
  
  public static final byte CHANNEL_SCENE = 4;
  
  public static final byte CHANNEL_TEAM = 5;
  
  public static final byte CHANNEL_SYSTEM = 6;
  
  public static final byte CHANNEL_INFO = 7;
  
  public static final byte CHANNEL_TRUMPET = 8;
  
  public static final byte MAP_TYPE_WHITE = 0;
  
  public static final byte MAP_TYPE_BLACK = 1;
  
  public static final byte MAP_TYPE_NOMAL = 2;
  
  public static final byte MAP_TYPE_WHITECITY = 3;
  
  public static final byte MAP_TYPE_BLACKCITY = 4;
  
  public static final byte MAP_TYPE_FB = 5;
  
  public static final int MILLIS_PER_TICK = 120;
  
  public static final String SOUND_RMS_NAME = "rms_sound";
  
  public static final int WAIT_TIME_1 = 400;
  
  public static final byte MAX_USER_PLAYERS = 3;
  
  public static final byte SKILL_NUM = 14;
  
  public static final byte NO_ATTACK_OBJ = -1;
  
  public static final byte CHAT_LIST_NUM = 20;
  
  public static final byte WEAPON_NULL = -1;
  
  public static final byte WEAPON_SWORD_1 = 1;
  
  public static final byte WEAPON_SWORD_2 = 2;
  
  public static final byte WEAPON_SWORD_3 = 3;
  
  public static final byte WEAPON_KNIFE_1 = 4;
  
  public static final byte WEAPON_KNIFE_2 = 5;
  
  public static final byte WEAPON_KNIFE_3 = 6;
  
  public static final byte WEAPON_AXE_1 = 7;
  
  public static final byte WEAPON_AXE_2 = 8;
  
  public static final byte WEAPON_AXE_3 = 9;
  
  public static final byte WEAPON_WAND_1 = 10;
  
  public static final byte WEAPON_WAND_2 = 11;
  
  public static final byte WEAPON_WAND_3 = 12;
  
  public static final byte WEAPON_PEARL_1 = 13;
  
  public static final byte WEAPON_PEARL_2 = 14;
  
  public static final byte WEAPON_PEARL_3 = 15;
  
  public static final byte WEAPON_SWORD_4 = 16;
  
  public static final byte WEAPON_KNIFE_4 = 17;
  
  public static final byte WEAPON_AXE_4 = 18;
  
  public static final byte WEAPON_WAND_4 = 19;
  
  public static final byte WEAPON_PEARL_4 = 20;
  
  public static final byte WEAPON_SWORD_5 = 21;
  
  public static final byte WEAPON_KNIFE_5 = 22;
  
  public static final byte WEAPON_AXE_5 = 23;
  
  public static final byte WEAPON_WAND_5 = 24;
  
  public static final byte WEAPON_PEARL_5 = 25;
  
  public static final short STUFF_BROADSWORD = 101;
  
  public static final short STUFF_SWORD = 102;
  
  public static final short STUFF_AX = 103;
  
  public static final short STUFF_WAND = 104;
  
  public static final short STUFF_BEAD = 105;
  
  public static final short STUFF_CAP = 111;
  
  public static final short STUFF_CLOTHES = 112;
  
  public static final short STUFF_TROUSERS = 113;
  
  public static final short STUFF_SHOES = 114;
  
  public static final short STUFF_NECKLACE = 121;
  
  public static final short STUFF_FINGER_RING = 122;
  
  public static final short STUFF_PETFOOD = 201;
  
  public static final short STUFF_ORE = 202;
  
  public static final short STUFF_MATERIAL = 203;
  
  public static final short STUFF_DIAMOND = 211;
  
  public static final short STUFF_TASK_PROP = 212;
  
  public static final short STUFF_CHANGE_SKIN = 213;
  
  public static final short STUFF_BLOOD = 221;
  
  public static final short STUFF_MAGIC = 222;
  
  public static final short STUFF_BLOOD_MAGIC = 223;
  
  public static final short STUFF_MEMORY_SCROLL = 232;
  
  public static final short STUFF_CHANGE_STONE = 233;
  
  public static final short STUFF_MONSTER_FALL = 234;
  
  public static final short STUFF_STRATEGY = 238;
  
  public static final short STUFF_METEMPSYCHOSIS_SCROLL = 239;
  
  public static final short STUFF_EXPERIENCE_CARD = 301;
  
  public static final short STUFF_CLEAN_CARD = 302;
  
  public static final short STUFF_LOTTERY = 303;
  
  public static final short STUFF_PROTECT_STONE = 304;
  
  public static final short STUFF_SOUL_STONE = 305;
  
  public static final short STUFF_INCREMENT_SMELT = 306;
  
  public static final short STUFF_INCREMENT_PET = 307;
  
  public static final short STUFF_INCREMENT_FEED = 308;
  
  public static final short STUFF_INCREMENT_COMPOSE = 309;
  
  public static final short STUFF_BACK_SCROLL = 310;
  
  public static final short STUFF_INCREMENT_BROADSWORD = 311;
  
  public static final short STUFF_INCREMENT_SWORD = 312;
  
  public static final short STUFF_INCREMENT_AX = 313;
  
  public static final short STUFF_INCREMENT_WAND = 314;
  
  public static final short STUFF_INCREMENT_BEAD = 315;
  
  public static final short STUFF_INCREMENT_CAP = 316;
  
  public static final short STUFF_INCREMENT_CLOSES = 317;
  
  public static final short STUFF_INCREMENT_TROUSERS = 318;
  
  public static final short STUFF_INCREMENT_SHOES = 319;
  
  public static final short STUFF_INCREMENT_NECKLACK = 320;
  
  public static final short STUFF_INCREMENT_RING = 321;
  
  public static final short STUFF_INCREMENT_OTHER = 322;
  
  public static final short STUFF_INCREMENT_DIAMOND = 323;
  
  public static final short STUFF_INCREMENT_PET_POINTER = 324;
  
  public static final short STUFF_INCREMENT_DECOMPOSE = 325;
  
  public static final short STUFF_KEY = 326;
  
  public static final short STUFF_BOX = 327;
  
  public static final short STUFF_COLOR_BOX = 328;
  
  public static final short STUFF_COLOR_KEY = 329;
  
  public static final short STUFF_INCREMENT_VIOLET_KEY = 330;
  
  public static final short STUFF_INCREMENT_EXIT_CLAN = 331;
  
  public static final short STUFF_INCREMENT_CLAN_BIG_PRESTIGE = 332;
  
  public static final short STUFF_INCREMENT_CLAN_MIDDLE_PRESTIGE = 333;
  
  public static final short STUFF_INCREMENT_CLAN_SMALL_PRESTIGE = 334;
  
  public static final short STUFF_INCREMENT_BIG_PACKAGE = 335;
  
  public static final short STUFF_BADGE = 236;
  
  public static final short STUFF_SILK = 337;
  
  public static final short STUFF_INCREMENT_EXPERIENCECARD = 338;
  
  public static final short STUFF_INCREMENT_PRESTIGECARD = 339;
  
  public static final short STUFF_INCREMENT_HONORCARD = 340;
  
  public static final short STUFF_INCREMENT_OFFTIMECARD = 341;
  
  public static final short STUFF_INCREMENT_ONTIMECARD = 342;
  
  public static final short STUFF_TRUMPET = 348;
  
  public static final short STUFF_INCREMENT_RELIVE = 349;
  
  public static final short STUFF_INCREMENT_NULL_GOURD = 350;
  
  public static final short STUFF_INCREMENT_FULL_GOURD = 351;
  
  public static final short STUFF_TRANSFER_STONE = 353;
  
  public static final short STUFF_TRANSFER_QUEST = 354;
  
  public static final short STUFF_INCREMENT_QUEST_CHARGECARD = 355;
  
  public static final short STUFF_TRANSFORMCARD = 357;
  
  public static final short STUFF_INCREMENT_TITLE = 365;
  
  public static final short STUFF_INCREMENT_MONTH_SWORD = 368;
  
  public static final short STUFF_INCREMENT_MONTH_STICK = 369;
  
  public static final byte BUFFER_DP_DOWN = 0;
  
  public static final byte BUFFER_SPEED_DOWN = 1;
  
  public static final byte BUFFER_FORBID = 2;
  
  public static final byte BUFFER_GIDDY = 3;
  
  public static final byte BUFFER_POISON = 4;
  
  public static final byte BUFFER_AP_DOWN = 5;
  
  public static final byte BUFFER_AIM_DOWN = 6;
  
  public static final byte RESIS_DOWN = 7;
  
  public static final byte BUFFER_RECOVER = 8;
  
  public static final byte BUFFER_INJURY_MP = 9;
  
  public static final byte BUFFER_MAXHP_UP = 10;
  
  public static final byte BUFFER_RATE_UP = 11;
  
  public static final byte BUFFER_DP_UP = 12;
  
  public static final byte BUFFER_AP_UP = 13;
  
  public static final byte BUFFER_SPEED_UP = 14;
  
  public static final byte BUFFER_AIM_UP = 15;
  
  public static final byte BUFFER_AP_MANA_UP = 16;
  
  public static final byte BUFFER_UNBEATABLE = 17;
  
  public static final byte BUFFER_ABSORB_SHIELD = 18;
  
  public static final byte BUFFER_RECOVER_MP = 19;
  
  public static final byte BUFFER_FLEE_UP = 20;
  
  public static final byte BUFFER_EXTRA_HIT_UP = 21;
  
  public static final byte[] BUFFER_SKETCH_MAP = new byte[] { 
      0, 7, 9, 10, 11, 2, 5, 19, 12, 13, 
      15, 16, 1, 3, 8, 6, 4, 17, 14, 18, 
      20, 16, 12 };
  
  public static final String[][] SOUND = new String[][] { { "/lev.mid", "/hp.wav", "/hew.wav", "/skill.wav" }, { "audio/midi", "audio/x-wav", "audio/x-wav", "audio/x-wav" } };
  
  public static final int[] SOUNDPriority = new int[] { 1, 1, 1, 1 };
  
  public static boolean audioOpen = true;
  
  public static byte nineShort = 0;
  
  public static boolean autoDistribute = false;
  
  public static boolean newPlayerHelp = true;
  
  public static boolean showOtherPlayer = true;
  
  public static boolean showName = true;
  
  public static boolean showEffect = true;
  
  public static boolean showTeamMate = true;
  
  public static boolean showSmallMap = true;
  
  public static byte zeroShort = 0;
  
  public static short zeroShortShow = -1;
  
  public static boolean showSpecial = true;
  
  public static boolean showNum = true;
  
  public static boolean showShortCut = true;
  
  public static boolean showExpBar = true;
  
  public static boolean use2468 = false;
  
  /**
   * 是否使用5键为攻击键
   */
  public static boolean use5 = false;
  
  public static boolean cmwap = false;
  
  public static final byte PHONETYPE_N7610 = 0;
  
  public static final byte PHONETYPE_N73 = 1;
  
  public static final byte PHONETYPE_N6170 = 2;
  
  public static final byte PHONETYPE_SEK750 = 3;
  
  public static final byte PHONETYPE_SEK700 = 4;
  
  public static final short[][] PHONE_TYPE = new short[][] { { 176, 208 }, { 240, 320 }, { 240, 320 }, { 176, 220 }, { 176, 220 } };
  
  public static final byte[][] FONT_SIZE = new byte[][] { { 12, 12 }, { 22, 18 }, { 12, 12 }, { 18, 16 }, { 18, 16 } };
  
  public static final String passportIpPort = "passport.ebsee.com:30005";
  
  public static final String serverIp = "g1.ebsee.com";
  
  public static final String URL_LCMOBLIE_IP = "passport.ebsee.com";
  
  public static final String URL_LCMOBILE_URL = "/judgeserver/LoginOnlineGame?game=tianjie";
  
  public static String URL_KONG_IP = "passport.ebsee.com:30005";
  
  public static String URL_KONG_IP2 = "/passport?ver=2&";
  
  public static final String URL_CMOBILE_IP = "gmp.i139.cn";
  
  public static final String URL_CMOBILE_URL = "/bizcontrol/LoginOnlineGame?sender=202&cpId=C00002&cpServiceId=120120438000&channelId=1000";
  
  public static final String URL_CKONG_IP = "passport.ebsee.com";
  
  public static final String URL_CKONG_IP2 = "/passport?ver=1&";
  
  public static String URL_SERVERLIST = "";
  
  public static final String URL_SERVERLIST_DEF = "socket://g1.ebsee.com:30000";
  
  public static final String URL_SERVERLIST_GAME = "socket://g1.ebsee.com:30000";
  
  public static final String URL_GUESTSERVER = " 游客,g1.ebsee.com,30000,6";
  
  public static String[] SERVICE_STR = new String[] { "地图问题", "战斗问题", "道具问题", "人物问题", "其他问题" };
  
  public static final String[] WM_NAMEDESC = new String[] { "主城", "中立领地", "副本", "新手村", "天人领地", "修罗领地" };
  
  public static final String[] MENU_DIVORCE = new String[] { "协议离婚", "强制离婚" };
  
  public static String[] BAD_STRING = new String[] { "队伍消息", "世界消息", "氏族消息" };
  
  public static final String[] MAP_MENU = new String[] { "地图", "NPC位置" };
  
  public static String[] strHelpMainMenu = new String[] { "游戏简介", "游戏规则", "操作说明" };
  
  public static String[] strHelpContentMainMenu = new String[] { "中国古典玄幻武侠即时型多人在线角色扮演类游戏，讲述一场新的天界大战！天之劫难即将到来！", "每个种族都有适合自己种族特色的不同职业，游戏中有多种职业可供玩家选择，人族有精通剑术的剑客和身手敏捷的刺客，仙族有擅长仙术的仙术师和救死扶伤的医师，魔族可以使用力大无穷的魔剑客和法术精湛的魔道士，妖族则能够选择疾风般迅猛的妖刺客和使用治疗法术的妖族医师，选择自己喜爱的游戏人物，赶快加入天劫的战场中去吧。", "菜单界面\n\n方向键上（或数字键2）：上移选择框\n方向键下（或数字键8）：下移选择框\n方向键左（或数字键4）：左移选择框\n方向键右（或数字键6）：右移选择框\n左侧软键（或数字键5）：确定\n右侧软键：返回\n\n 游戏中界面\n\n方向键上：向上移动\n方向键下：向下移动\n方向键左：向左移动\n方向键右：向右移动\n方向键中间：确认/攻击\n＃键：锁定\n＊键：聊天\n９键：切换２４６８为方向键/功能键\n０键：切换画质\n左侧软键：根据画面提示确认相应操作\n右侧软键：根据画面提示确认相应操作 " };
}
