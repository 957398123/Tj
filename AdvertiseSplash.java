import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class AdvertiseSplash {
  private int urlNum = 0;
  
  private static AdvertiseSplash as = null;
  
  private byte loadTick = 0;
  
  private String imagePath = "/asi_";
  
  int picNum;
  
  int firstPicNum;
  
  private int pageNum;
  
  private int selectedPageNum = 0;
  
  private int selectedPic = 0;
  
  private static Image[] aSplashImage = null;
  
  int aSplashImageNum = 0;
  
  int aSplashImageTick = 0;
  
  private int numTmp = 0;
  
  private boolean isUp = false;
  
  private boolean isDown = false;
  
  public static String httpUrl = "";
  
  public static Image[] allImage = null;
  
  public static boolean isGetUrl = false;
  
  private byte screenMode = 0;
  
  private Vector picNumVec = new Vector();
  
  private int fullPage = 0;
  
  boolean iskeyUP = false;
  
  public static void clearAS() {
    if (as != null) {
      releaseImage();
      as = null;
    } 
  }
  
  private void loadPic() {
    if (aTitleImage == null)
      aTitleImage = loadImage("/asi_logo.png"); 
    if (aInfoImage == null)
      aInfoImage = loadImage("/asi_info.png"); 
    if (asiBackImg == null)
      asiBackImg = loadImage("/asi_back.png"); 
    if (asiExitImg == null)
      asiExitImg = loadImage("/asi_exit.png"); 
    if (asiDownloadImg == null)
      asiDownloadImg = loadImage("/asi_download.png"); 
    if (upA == null)
      upA = loadImage("/asi_ua.png"); 
    if (downA == null)
      downA = loadImage("/asi_da.png"); 
    if (aLoadImage == null)
      aLoadImage = loadImage("/asi_load.png"); 
  }
  
  public static AdvertiseSplash getInstance() {
    if (as == null)
      as = new AdvertiseSplash(); 
    return as;
  }
  
  public void drawMoreGameBG(Graphics g) {
    g.setColor(255);
    g.fillRect(0, 0, MainCanvas.screenW, MainCanvas.screenH);
    int xx1 = 0, yy1 = 0;
    int xx2 = MainCanvas.screenW, yy2 = MainCanvas.screenH;
    int rr1 = 212, gg1 = 234, bb1 = 196;
    int rr2 = 140, gg2 = 206, bb2 = 100;
    g.setClip(0, 0, MainCanvas.screenW, MainCanvas.screenH);
    for (int i = yy1; i < yy2; i++) {
      int rr = rr1 - (rr1 - rr2) * (i - yy1) / (yy2 - yy1);
      int gg = gg1 - (gg1 - gg2) * (i - yy1) / (yy2 - yy1);
      int bb = bb1 - (bb1 - bb2) * (i - yy1) / (yy2 - yy1);
      g.setColor(rr, gg, bb);
      g.drawLine(xx1, i, xx2, i);
    } 
    if (MainCanvas.mc.baseForm == null) {
      MainCanvas.mc.baseForm = new UIForm(0, 0, -1, -1, "");
      MainCanvas.mc.baseForm.setStyle((byte)0);
    } 
    MainCanvas.mc.baseForm.draw(g);
  }
  
  private static Image aTitleImage = null;
  
  private static Image aInfoImage = null;
  
  private static Image aLoadImage = null;
  
  String[] strs;
  
  private static final String exitStr = "连接验证服务器失败,请到设置中选择连接方式,配置方法详见官方网站,手机登录wy.kong.net";
  
  String StrUrl;
  
  private int yTmp;
  
  private int moreGameState;
  
  public final int STATE_NONE;
  
  public final int STATE_MOREGAME;
  
  public final int STATE_LOAD;
  
  public final int STATE_EXIT;
  
  public final int STATE_WAIT;
  
  public void drawTitle(Graphics g) {
    g.drawImage(aTitleImage, (MainCanvas.screenW - aTitleImage.getWidth()) / 2, 2, 20);
    g.drawImage(aInfoImage, (MainCanvas.screenW - aInfoImage.getWidth()) / 2, 2 + aTitleImage.getHeight(), 20);
  }
  
  public void moreGamePaint(Graphics g) {
    drawMoreGameBG(g);
    switch (getMoreGameState()) {
      case 1:
        switch (this.screenMode) {
          case 0:
            drawAdvImageF(g);
            break;
          case 1:
            drawAdvImage(g);
            break;
        } 
        drawOkAndCancel(g);
        drawTitle(g);
        drawArrow(g);
        break;
      case 2:
      case 4:
        drawLoading(g);
        break;
      case 3:
        drawOkAndCancel(g);
        drawExit(g);
        break;
    } 
  }
  
  private AdvertiseSplash() {
    this.strs = null;
    this.StrUrl = "";
    this.yTmp = 10;
    this.moreGameState = 0;
    this.STATE_NONE = 0;
    this.STATE_MOREGAME = 1;
    this.STATE_LOAD = 2;
    this.STATE_EXIT = 3;
    this.STATE_WAIT = 4;
    this.b = true;
    this.arrowTick = 0;
    this.zhenFu = 6;
    this.url = null;
    this.readTxtStr = null;
    AnalysisTxt();
    loadPic();
  }
  
  private void drawExit(Graphics g) {
    g.setColor(16711680);
    this.strs = Util.wrapText("连接验证服务器失败,请到设置中选择连接方式,配置方法详见官方网站,手机登录wy.kong.net", MainCanvas.screenW - 30, MainCanvas.font[1]);
    byte lg = (byte)this.strs.length;
    for (byte i = 0; i < lg; i = (byte)(i + 1))
      g.drawString(this.strs[i], 15, 34 + i * (MainCanvas.CHARH + 5), 20); 
  }
  
  public void moreGameTick() {
    keyInMoreGameRun();
    arrowTick();
  }
  
  private void keyInMoreGameRun() {
    switch (getMoreGameState()) {
      case 1:
        switch (this.screenMode) {
          case 0:
            keyInDislayF();
            break;
          case 1:
            keyInDislay();
            break;
        } 
        break;
      case 2:
        loadMoreGame();
        break;
      case 3:
        keyInExit();
        break;
    } 
  }
  
  private void keyInExit() {
    if (MainCanvas.isKeyPress(18)) {
      clearAS();
      MainCanvas.mc.setState((byte)4);
      MainCanvas.mc.releaseUI();
    } 
  }
  
  void goUP() {
    this.isDown = true;
    this.isUp = false;
    this.selectedPageNum--;
    this.screenMode = 0;
    if (this.selectedPageNum < 0)
      this.selectedPageNum = this.pageNum - 1; 
    if (this.selectedPageNum == 0) {
      this.screenMode = 1;
      this.selectedPic = this.firstPicNum - 1;
    } else {
      this.selectedPic = this.numTmp - 1;
    } 
  }
  
  void goDown() {
    this.isUp = true;
    this.isDown = false;
    this.selectedPageNum++;
    this.screenMode = 0;
    if (this.selectedPageNum > this.pageNum - 1)
      this.selectedPageNum = 0; 
    if (this.selectedPageNum == 0)
      this.screenMode = 1; 
    this.selectedPic = 0;
  }
  
  private void keyInDislayF() {
    if (MainCanvas.isKeyPress(11)) {
      this.aSplashImageTick--;
      if (this.aSplashImageTick < 0)
        this.aSplashImageTick = this.aSplashImageNum - 1; 
      if (this.selectedPic == 0) {
        goUP();
        setMoreGameState(2);
      } else {
        this.isDown = false;
        this.selectedPic--;
      } 
    } else if (MainCanvas.isKeyPress(13)) {
      this.aSplashImageTick++;
      if (this.aSplashImageTick > this.aSplashImageNum - 1)
        this.aSplashImageTick = 0; 
      if (this.selectedPic == this.numTmp - 1) {
        goDown();
        setMoreGameState(2);
      } else {
        this.isUp = false;
        this.selectedPic++;
      } 
    } else if (MainCanvas.isKeyPressOk()) {
      try {
        MainCanvas.mc.aMidlet.platformRequest(this.url[this.aSplashImageTick]);
      } catch (Exception exception) {}
    } else if (MainCanvas.isKeyPress(18)) {
      outAs();
    } 
    MainCanvas.resetKey();
  }
  
  private void keyInDislay() {
    if (MainCanvas.isKeyPress(11)) {
      this.aSplashImageTick -= 2;
      this.selectedPic -= 2;
      if (this.selectedPic < 0)
        if (isGetUrl) {
          goUP();
          setMoreGameState(2);
        } else {
          this.iskeyUP = true;
          setMoreGameState(4);
          try {
            setADcontent(MainCanvas.mc.getURLConnect);
            AnalysisUrl();
            setMoreGameState(2);
            goUP();
          } catch (Exception e) {
            MainCanvas.mc.ADUrlConnect(9);
          } 
          this.aSplashImageTick = this.aSplashImageNum - 1;
        }  
    } else if (MainCanvas.isKeyPress(13)) {
      this.aSplashImageTick += 2;
      this.selectedPic += 2;
      if (this.selectedPic > ((Integer)this.picNumVec.elementAt(this.selectedPageNum)).intValue() - 1) {
        if (isGetUrl) {
          goDown();
          setMoreGameState(2);
        } else {
          this.iskeyUP = false;
          setMoreGameState(4);
          try {
            setADcontent(MainCanvas.mc.getURLConnect);
            AnalysisUrl();
            setMoreGameState(2);
            goDown();
          } catch (Exception e) {
            MainCanvas.mc.ADUrlConnect(9);
          } 
        } 
        this.aSplashImageTick = this.firstPicNum;
      } 
    } else if (MainCanvas.isKeyPress(10)) {
      if (this.aSplashImageTick < this.aSplashImageNum && this.selectedPic % 2 == 1) {
        this.selectedPic--;
        this.aSplashImageTick--;
      } 
    } else if (MainCanvas.isKeyPress(12)) {
      if (this.aSplashImageTick < this.aSplashImageNum - 1 && this.selectedPic % 2 == 0) {
        this.selectedPic++;
        this.aSplashImageTick++;
      } 
    } else if (MainCanvas.isKeyPressOk()) {
      try {
        MainCanvas.mc.aMidlet.platformRequest(this.url[this.aSplashImageTick]);
      } catch (Exception exception) {}
    } else if (MainCanvas.isKeyPress(18)) {
      outAs();
    } 
    MainCanvas.resetKey();
  }
  
  String getUrl() {
    return this.StrUrl;
  }
  
  void SetUrl(String s) {
    this.StrUrl = s;
  }
  
  public void setADcontent(byte[] tempb) {
    ByteArrayInputStream bs = new ByteArrayInputStream(tempb);
    DataInputStream is = new DataInputStream(bs);
    try {
      int tmpLength = is.readInt();
      byte[] bb = new byte[tmpLength];
      for (int j = 0, m = bb.length; j < m; j++) {
        bb[j] = is.readByte();
        bb[j] = (byte)(bb[j] - 29);
      } 
      httpUrl = new String(bb);
      int ts = is.readInt();
      allImage = new Image[ts];
      int tuLength = 0;
      byte[] tuData = null;
      for (int i = 0; i < ts; i++) {
        tuLength = is.readInt();
        tuData = new byte[tuLength];
        for (int k = 0; k < tuLength; k++)
          tuData[k] = is.readByte(); 
        allImage[i] = Image.createImage(tuData, 0, tuLength);
      } 
      isGetUrl = true;
      is.close();
      bs.close();
    } catch (Exception e) {
      MainCanvas.mc.ADUrlConnect(9);
      e.printStackTrace();
    } 
  }
  
  private void outAs() {
    MainCanvas.mc.setState((byte)4);
    as = null;
    httpUrl = "";
    allImage = null;
    isGetUrl = false;
    releaseImage();
  }
  
  private void drawAdvImageF(Graphics g) {
    int hh = aTitleImage.getHeight() + aInfoImage.getHeight();
    int x = 0;
    int y = 0;
    int w = 0;
    int h = 0;
    g.setColor(0);
    for (int i = 0, ii = this.numTmp; i < ii; i++)
      g.drawImage(aSplashImage[i], MainCanvas.screenW >> 1, hh + (aSplashImage[i].getHeight() + 7) * i + this.yTmp, 17); 
    x = (MainCanvas.screenW - aSplashImage[this.selectedPic].getWidth() >> 1) - 2;
    y = hh + (aSplashImage[this.selectedPic].getHeight() + 7) * this.selectedPic - 2 + this.yTmp;
    w = aSplashImage[this.selectedPic].getWidth() + 3;
    h = aSplashImage[this.selectedPic].getHeight() + 3;
    g.setColor(15823877);
    g.drawRect(x, y, w, h);
    g.drawRect(x - 1, y - 1, w + 2, h + 2);
    g.drawRect(x - 2, y - 2, w + 4, h + 4);
  }
  
  private void drawAdvImage(Graphics g) {
    int hh = aTitleImage.getHeight() + aInfoImage.getHeight();
    int x = 0;
    int y = 0;
    int w = 0;
    int h = 0;
    g.setColor(0);
    int curNum = 0;
    int i;
    for (i = 0; i < this.selectedPageNum; i++)
      curNum += ((Integer)this.picNumVec.elementAt(i)).intValue(); 
    int ii;
    for (i = 0, ii = this.numTmp; i < ii; i++)
      g.drawImage(aSplashImage[i], MainCanvas.screenW / 4 + i % 2 * MainCanvas.screenW / 2, hh + (aSplashImage[i].getHeight() + 7) * i / 2 + this.yTmp, 17); 
    x = (MainCanvas.screenW / 2 - aSplashImage[this.selectedPic].getWidth()) / 2 - 2 + this.selectedPic % 2 * MainCanvas.screenW / 2;
    y = hh + (aSplashImage[this.selectedPic].getHeight() + 7) * this.selectedPic / 2 - 2 + this.yTmp;
    w = aSplashImage[this.selectedPic].getWidth() + 3;
    h = aSplashImage[this.selectedPic].getHeight() + 3;
    x = (MainCanvas.screenW / 2 - aSplashImage[this.selectedPic].getWidth()) / 2 - 2 + this.selectedPic % 2 * MainCanvas.screenW / 2;
    y = hh + (aSplashImage[this.selectedPic].getHeight() + 7) * this.selectedPic / 2 - 2 + this.yTmp;
    g.setColor(15823877);
    g.drawRect(x, y, w, h);
    g.drawRect(x - 1, y - 1, w + 2, h + 2);
    g.drawRect(x - 2, y - 2, w + 4, h + 4);
  }
  
  public int getMoreGameState() {
    return this.moreGameState;
  }
  
  public void setMoreGameState(int moreGameState) {
    this.moreGameState = moreGameState;
  }
  
  public void loadMoreGame() {
    int i;
    int ii;
    switch (this.screenMode) {
      case 0:
        switch (this.loadTick) {
          case 1:
            if (this.selectedPageNum + 1 == this.pageNum) {
              if (this.urlNum % this.picNum != 0) {
                this.numTmp = this.urlNum % this.picNum;
              } else {
                this.numTmp = this.picNum;
              } 
            } else {
              this.numTmp = this.picNum;
            } 
            aSplashImage = new Image[this.numTmp];
            if (this.isDown)
              this.selectedPic = this.numTmp - 1; 
            if (this.isUp)
              this.selectedPic = 0; 
            break;
          case 3:
            for (i = 0, ii = this.numTmp; i < ii; i++)
              aSplashImage[i] = allImage[this.selectedPageNum * this.picNum + i - this.picNum]; 
            break;
        } 
        this.loadTick = (byte)(this.loadTick + 1);
        if (this.loadTick == 5) {
          this.loadTick = 0;
          setMoreGameState(1);
        } 
        break;
      case 1:
        switch (this.loadTick) {
          case 1:
            this.numTmp = this.firstPicNum;
            aSplashImage = new Image[this.numTmp];
            if (this.isDown)
              if (this.selectedPageNum == 0) {
                this.selectedPic = this.numTmp - 1;
              } else {
                this.selectedPic = this.numTmp / 2 - 1;
              }  
            if (this.isUp)
              this.selectedPic = 0; 
            break;
          case 3:
            for (i = 0; i < this.firstPicNum; i++)
              aSplashImage[i] = loadImage(this.imagePath + i + ".png"); 
            break;
        } 
        this.loadTick = (byte)(this.loadTick + 1);
        if (this.loadTick == 5) {
          this.loadTick = 0;
          setMoreGameState(1);
        } 
        break;
    } 
  }
  
  private void drawLoading(Graphics g) {
    g.setColor(16777215);
    g.drawImage(aLoadImage, (MainCanvas.screenW - aLoadImage.getWidth()) / 2, (MainCanvas.screenH - aLoadImage.getHeight()) / 2, 20);
  }
  
  public final Image loadImage(String path) {
    Image img = null;
    try {
      img = Image.createImage(path);
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return img;
  }
  
  private static Image asiBackImg = null;
  
  private static Image asiExitImg = null;
  
  private static Image asiDownloadImg = null;
  
  private void drawOkAndCancel(Graphics g) {
    if (getMoreGameState() != 3)
      g.drawImage(asiDownloadImg, 0, MainCanvas.screenH - asiDownloadImg.getHeight(), 20); 
    g.drawImage(asiBackImg, MainCanvas.screenW - asiBackImg.getWidth(), MainCanvas.screenH - asiBackImg.getHeight(), 20);
  }
  
  private static Image upA = null;
  
  private static Image downA = null;
  
  boolean b;
  
  private int arrowTick;
  
  private int zhenFu;
  
  private String[] url;
  
  String readTxtStr;
  
  public static final int KEY_VALUE = 29;
  
  private void drawArrow(Graphics g) {
    drawUpArrow(g);
    drawDownArrow(g);
  }
  
  private void arrowTick() {
    if (this.b) {
      if (this.arrowTick < this.zhenFu) {
        this.arrowTick++;
      } else {
        this.b = false;
      } 
    } else {
      this.arrowTick--;
      if (this.arrowTick < 0)
        this.b = true; 
    } 
  }
  
  private void drawUpArrow(Graphics g) {
    g.drawImage(upA, MainCanvas.screenW >> 1, aTitleImage.getHeight() + aInfoImage.getHeight() - downA.getHeight() * 3 + this.arrowTick + this.yTmp + 3, 17);
  }
  
  private void drawDownArrow(Graphics g) {
    g.drawImage(downA, MainCanvas.screenW >> 1, MainCanvas.screenH - asiDownloadImg.getHeight() - downA.getHeight() - this.arrowTick + this.yTmp - 0, 17);
  }
  
  private String loadTextMe(String yourfilename) {
    byte b = 0;
    Vector v = new Vector();
    InputStream is = null;
    try {
      Class c = getClass();
      is = c.getResourceAsStream(yourfilename);
      DataInputStream dos = new DataInputStream(is);
      while ((b = dos.readByte()) != -1)
        v.addElement(new Byte(b)); 
      dos.close();
    } catch (Exception exception) {}
    byte[] bb = new byte[v.size()];
    for (int i = 0; i < v.size(); i++) {
      bb[i] = ((Byte)v.elementAt(i)).byteValue();
      bb[i] = (byte)(bb[i] - 29);
    } 
    String ssss = new String(bb);
    return ssss;
  }
  
  public void AnalysisUrl() {
    int s;
    int i;
    int ii;
    try {
      String str = "";
      str = httpUrl;
      char c1 = str.charAt(0);
      char c2 = str.charAt(1);
      char c3 = str.charAt(2);
      if (c3 != '#') {
        String numStr = (new Character(c1)).toString() + (new Character(c2)).toString();
        this.picNum = Byte.parseByte(numStr);
        this.screenMode = Byte.parseByte(String.valueOf(c3));
      } else {
        this.picNum = Byte.parseByte(String.valueOf(c1));
        this.screenMode = Byte.parseByte(String.valueOf(c2));
      } 
      char[] cA = str.toCharArray();
      for (int k = 0, m = cA.length; k < m; k++) {
        if (cA[k] == '#')
          this.urlNum++; 
      } 
      String[] strArr = new String[this.urlNum];
      this.aSplashImageNum = this.urlNum;
      int[] bz = new int[this.urlNum - this.firstPicNum];
      int j = 0;
      int n, i1;
      for (n = 0, i1 = cA.length; n < i1; n++) {
        if (cA[n] == '#') {
          bz[j] = n;
          j++;
        } 
      } 
      for (n = 0; n < this.firstPicNum; n++)
        strArr[n] = this.url[n]; 
      for (n = this.firstPicNum, i1 = strArr.length; n < i1; n++) {
        if (n == strArr.length - 1) {
          strArr[n] = str.substring(bz[n - this.firstPicNum] + 1, cA.length);
        } else {
          strArr[n] = str.substring(bz[n - this.firstPicNum] + 1, bz[n - this.firstPicNum + 1]);
        } 
      } 
      this.url = strArr;
    } catch (Exception e) {
      e.printStackTrace();
    } 
    switch (this.screenMode) {
      case 0:
        s = 0;
        if (this.picNum < this.firstPicNum)
          s = 1; 
        if (this.urlNum % this.picNum != 0) {
          this.pageNum = this.urlNum / this.picNum + 1 - s;
          break;
        } 
        this.pageNum = this.urlNum / this.picNum - s;
        break;
      case 1:
        this.fullPage = this.aSplashImageNum / this.picNum;
        for (i = 0, ii = this.fullPage; i < ii; i++)
          this.picNumVec.addElement(new Integer(this.picNum)); 
        this.pageNum = this.picNumVec.size();
        break;
    } 
  }
  
  public void AnalysisTxt() {
    String str = "";
    str = loadTextMe("/s.ad");
    char c1 = str.charAt(0);
    char c2 = str.charAt(1);
    char c3 = str.charAt(2);
    if (c3 != '#') {
      String numStr = (new Character(c1)).toString() + (new Character(c2)).toString();
      this.firstPicNum = Byte.parseByte(numStr);
      this.screenMode = Byte.parseByte(String.valueOf(c3));
    } else {
      this.firstPicNum = Byte.parseByte(String.valueOf(c1));
      this.screenMode = Byte.parseByte(String.valueOf(c2));
    } 
    char[] cA = str.toCharArray();
    for (int i = 0, ii = cA.length; i < ii; i++) {
      if (cA[i] == '#')
        this.urlNum++; 
    } 
    String[] strArr = new String[this.urlNum];
    this.aSplashImageNum = this.urlNum;
    int[] bz = new int[this.urlNum];
    int j = 0;
    int k, m;
    for (k = 0, m = cA.length; k < m; k++) {
      if (cA[k] == '#') {
        bz[j] = k;
        j++;
      } 
    } 
    for (k = 0, m = strArr.length; k < m; k++) {
      if (k == strArr.length - 1) {
        strArr[k] = str.substring(bz[k] + 1, cA.length);
      } else {
        strArr[k] = str.substring(bz[k] + 1, bz[k + 1]);
      } 
    } 
    this.url = strArr;
    switch (this.screenMode) {
      case 0:
        if (this.urlNum % this.firstPicNum != 0) {
          this.pageNum = this.urlNum / this.firstPicNum + 1;
          break;
        } 
        this.pageNum = this.urlNum / this.firstPicNum;
        break;
      case 1:
        this.fullPage = this.aSplashImageNum / this.firstPicNum;
        for (k = 0, m = this.fullPage; k < m; k++)
          this.picNumVec.addElement(new Integer(this.firstPicNum)); 
        this.pageNum = this.picNumVec.size();
        break;
    } 
  }
  
  private static void releaseImage() {
    if (aTitleImage != null)
      aTitleImage = null; 
    if (aInfoImage != null)
      aInfoImage = null; 
    if (asiBackImg != null)
      asiBackImg = null; 
    if (asiExitImg != null)
      asiExitImg = null; 
    if (asiDownloadImg != null)
      asiDownloadImg = null; 
    if (aLoadImage != null)
      aLoadImage = null; 
    if (upA != null && downA != null)
      upA = downA = null; 
    if (allImage != null && 
      aSplashImage[0] != null)
      for (int i = 0, ii = aSplashImage.length; i < ii; i++)
        aSplashImage[i] = null;  
  }
}
