import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class Download implements CommandListener {
  String WAP_SOHU = "http://wap.sohu.com/game?ch=kz_001";
  
  public static String bbsUrl = "";
  
  public static final String defaultURL = "S60.jar";
  
  private static final String defaultURL_1 = "http://wap.monternet.com/reversesubscribe?SessionID=\036SPID=900579\037ServiceID=08090005&SPURL=http://jp.kong.net/menu/test/tianjie/index.jsp";
  
  private static String defaultURL_2 = "http://61.135.154.46/passport/coinmanage.pass?do=search";
  
  private final String TEST_CHARGE_URL = "http://61.135.147.34/test_passport/coinpay.pass?do=doDefault";
  
  private final String WAP_CHARGE_URL = "http://61.135.147.34/passport/coinpay.pass?do=doMain&cointype=mmb";
  
  final String WAP_PASSPORT_SCORE_URL = "http://61.135.154.46/passport/recommend.pass?do=index";
  
  String wap_url = "";
  
  private static byte type;
  
  String strRGN = "";
  
  public static String strGtype = "";
  
  private static Download me = null;
  
  static Command cmdOK = new Command("确定", 4, 0);
  
  static Command cmdCancel = new Command("退出", 3, 1);
  
  static Command cmdBack = new Command("返回", 2, 1);
  
  static MainEntry owner;
  
  static Form f;
  
  public static Download getInstance() {
    if (me == null)
      me = new Download(); 
    return me;
  }
  
  public static void gotoURL(MainEntry o, byte _type) {
    String s1, s2;
    type = _type;
    if (type == 0) {
      s1 = "新版客户端下载";
      s2 = "点击“确定”下载新的客户端，点击“返回”返回游戏。";
    } else if (type == 1) {
      s1 = "内测号注册";
      s2 = "点击“确定”注册内测帐号，点击“返回”返回游戏。";
    } else if (type == 2) {
      s1 = "明细查询";
      s2 = "点击“确定”转到查询明细网页，点击“返回”返回游戏";
    } else if (type == 3) {
      s1 = "天劫论坛";
      s2 = "点击“确定”转到天劫论坛网页，点击“返回”返回游戏";
    } else if (type == 4 || type == 14) {
      s1 = "大额充值";
      if (type == 4) {
        s2 = "充值有些许延迟，请充值查询后再购买。点击“确定”转到大额充值网页，点击“返回”返回游戏";
      } else {
        s2 = "网络连接有些延迟，请点击“确定”进入大额充值网页进行快速充值操作，点击“返回”返回游戏";
      } 
    } else if (type == 5) {
      s1 = "骏网一卡通充值";
      s2 = "点击“确定”转到骏网一卡通充值网页，点击“返回”返回游戏";
    } else if (type == 6) {
      s1 = "当乐平台";
      s2 = "更多精彩游戏尽在当乐网D.cn";
    } else if (type == 7) {
      s1 = "天劫论坛";
      s2 = "点击“确定”转到天劫论坛网页，点击“退出”退出游戏";
    } else if (type == 8) {
      s1 = "我的积分";
      s2 = "点击“确定”转到我的积分网页，点击“返回”返回游戏";
    } else if (type == 9) {
      s1 = "搜狐游戏中心";
      s2 = "点击“确定”转到搜狐游戏中心，点击“返回”返回游戏";
    } else if (type == 10) {
      s1 = Cons.exittitle;
      s2 = Cons.exitinfo;
    } else if (type == 13) {
      s1 = "退出游戏";
      s2 = "点击“确定”转到更多游戏功能，点击“退出”退出游戏";
    } else {
      s1 = "未知类型";
      s2 = "";
    } 
    try {
      owner = o;
      f = new Form(s1);
      f.deleteAll();
      f.append(s2);
      f.addCommand(cmdOK);
      if (type == 1 || type == 6 || type == 7 || type == 10 || type == 13) {
        f.addCommand(cmdCancel);
      } else {
        f.addCommand(cmdBack);
      } 
      f.setCommandListener(getInstance());
      owner.display.setCurrent((Displayable)f);
    } catch (Exception exception) {}
  }
  
  public void commandAction(Command command, Displayable displayable) {
    this.wap_url = "";
    if (command == cmdOK) {
      try {
        if (type == 0) {
          owner.platformRequest(PCLogin.downloadAddress);
          owner.destroyApp(true);
        } else if (type == 1) {
          owner.platformRequest("http://wap.monternet.com/reversesubscribe?SessionID=\036SPID=900579\037ServiceID=08090005&SPURL=http://jp.kong.net/menu/test/tianjie/index.jsp");
          owner.destroyApp(true);
        } else if (type == 2) {
          defaultURL_2 += "&lpuname=" + MainCanvas.mc.userName + "&lpupass=" + MainCanvas.mc.userPassword;
          owner.platformRequest(defaultURL_2);
        } else if (type == 3) {
          owner.platformRequest(bbsUrl);
          System.out.println(bbsUrl);
        } else if (type == 4 || type == 14) {
          if (Cons.isCmobile) {
            this.strRGN = "CMCC";
          } else {
            this.strRGN = "KONG";
          } 
          if (strGtype == null)
            strGtype = ""; 
          this
            .wap_url = (Cons.isWapCharge ? "http://61.135.147.34/test_passport/coinpay.pass?do=doDefault" : "http://61.135.147.34/passport/coinpay.pass?do=doMain&cointype=mmb") + "&lpuname=" + MainCanvas.mc.userName + "&lpupass=" + MainCanvas.mc.userPassword + "&gtype=" + strGtype + "&k_ftp=" + MainCanvas.mc.jarFrom + "&RGN=" + this.strRGN + "&CCODE=" + (Player.getInstance()).objID;
          owner.platformRequest(this.wap_url);
        } else if (type == 5) {
          owner.platformRequest(bbsUrl);
        } else if (type == 6) {
          owner.platformRequest("http://peer.downjoy.cn/3906");
          owner.destroyApp(true);
        } else if (type == 7) {
          if (MainCanvas.tempName != null && !"".equals(MainCanvas.tempName[0].trim())) {
            owner.platformRequest("http://202.108.44.145/wap/wapa.php?&wun=" + MainCanvas.tempName[0] + "&wpw=" + MainCanvas.tempName[1] + "&fid=6");
          } else {
            owner.platformRequest("http://wy.kong.net/wap/");
          } 
          owner.destroyApp(true);
        } else if (type == 8) {
          if (Cons.isCmobile) {
            this.strRGN = "CMCC";
          } else {
            this.strRGN = "KONG";
          } 
          if (strGtype == null)
            strGtype = ""; 
          this
            .wap_url = "http://61.135.154.46/passport/recommend.pass?do=index&lpuname=" + MainCanvas.mc.userName + "&lpupass=" + MainCanvas.mc.userPassword + "&gtype=" + strGtype + "&k_ftp=" + MainCanvas.mc.jarFrom + "&RGN=" + this.strRGN + "&CCODE=" + (Player.getInstance()).objID;
          owner.platformRequest(this.wap_url);
        } else if (type == 9) {
          owner.platformRequest(this.WAP_SOHU);
          owner.destroyApp(true);
        } else if (type == 10) {
          owner.platformRequest(Cons.exiturl);
          owner.destroyApp(true);
        } else if (type == 13) {
          owner.display.setCurrent((Displayable)MainCanvas.mc);
          f = null;
          MainCanvas.mc.setState((byte)33);
          AdvertiseSplash.getInstance().getClass();
          AdvertiseSplash.getInstance().setMoreGameState(2);
        } 
      } catch (Exception exception) {}
    } else if (command == cmdBack) {
      if (type == 0 || type == 9) {
        MainCanvas.closeConnection();
        owner.display.setCurrent((Displayable)MainCanvas.mc);
        MainCanvas.mc.setState((byte)4);
        f = null;
      } else if (type == 2 || type == 3 || type == 4 || type == 5 || type == 8 || type == 14) {
        if (MainCanvas.mc.baseForm.getSubForm() != null)
          MainCanvas.mc.baseForm.setAboutForm(null); 
        f = null;
        owner.display.setCurrent((Displayable)MainCanvas.mc);
      } 
    } else if (command == cmdCancel) {
      if (type == 13)
        try {
          owner.platformRequest("http://g.ko.cn/gk/mtwap/push/index.jsp");
        } catch (Exception exception) {} 
      owner.destroyApp(true);
    } 
    PCLogin.downloadAddress = null;
  }
  
  Image imgBg = null;
  
  int strbgW = 40;
  
  int strbgH = 18;
  
  final byte state_none = 0;
  
  final byte state_wap = 1;
  
  byte state = 0;
  
  Image[] imgButton = new Image[2];
  
  public void drawExit(Graphics g) {
    if (this.imgBg == null)
      try {
        this.imgBg = Image.createImage("/lastbg.png");
        this.imgButton[0] = Image.createImage("/buttong.png");
        this.imgButton[1] = Image.createImage("/buttonr.png");
      } catch (Exception exception) {} 
    if (this.imgBg != null)
      g.drawImage(this.imgBg, 0, 0, 20); 
    switch (this.state) {
      case 0:
        g.drawImage(this.imgButton[0], 3, MainCanvas.screenH - 3, 36);
        g.drawImage(this.imgButton[1], MainCanvas.screenW - 3, MainCanvas.screenH - 3, 40);
        g.setColor(16777215);
        g.drawString("确认退出", MainCanvas.screenW >> 1, MainCanvas.screenH >> 1, 17);
        g.drawString("否", 13, MainCanvas.screenH - 5, 36);
        g.drawString("是", MainCanvas.screenW - 13, MainCanvas.screenH - 5, 40);
        break;
      case 1:
        g.drawImage(this.imgButton[1], 3, MainCanvas.screenH - 3, 36);
        g.drawImage(this.imgButton[0], MainCanvas.screenW - 3, MainCanvas.screenH - 3, 40);
        g.setColor(16777215);
        g.drawString("更多精彩游戏", MainCanvas.screenW >> 1, (MainCanvas.screenH >> 1) - 20, 17);
        g.drawString("尽在游戏频道", MainCanvas.screenW >> 1, MainCanvas.screenH >> 1, 17);
        g.drawString("wap.xjoys.com", MainCanvas.screenW >> 1, (MainCanvas.screenH >> 1) + 20, 17);
        g.drawString("确认", 9, MainCanvas.screenH - 5, 36);
        g.drawString("退出", MainCanvas.screenW - 9, MainCanvas.screenH - 5, 40);
        break;
    } 
  }
  
  void releseAll() {
    this.imgBg = null;
    for (int i = 0; i < this.imgButton.length; i++)
      this.imgButton[i] = null; 
  }
  
  public void keyInExit() {
    switch (this.state) {
      case 0:
        if (MainCanvas.isKeyPress(17)) {
          MainCanvas.mc.setState((byte)4);
          releseAll();
          break;
        } 
        if (MainCanvas.isKeyPress(18))
          this.state = 1; 
        break;
      case 1:
        if (MainCanvas.isKeyPress(17)) {
          try {
            MainCanvas.mc.aMidlet.platformRequest("http://go.i139.cn/gcomm1/portal/spchannel.do?url=http://gamepie.i139.cn/wap/s.do?j=3channel");
            MainCanvas.mc.aMidlet.destroyApp(true);
          } catch (Exception exception) {}
          releseAll();
          break;
        } 
        if (MainCanvas.isKeyPress(18)) {
          MainCanvas.mc.stop();
          MainCanvas.mc.aMidlet.exitMIDlet();
        } 
        break;
    } 
  }
}
