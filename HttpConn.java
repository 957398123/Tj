//
// Source code recreated from MainEntry .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Image;

public class HttpConn implements Runnable {
    public static String[][] GprsContent = (String[][])null;
    public static int[] showTitle = null;
    public static String passportid = "";
    public static String[] valueDetail = new String[]{"", "1", "1"};
    public final String[] valueSign = new String[]{"SMSPRICE", "PROVICE_NO_FOUND", "MOBILE_NO_FOUND"};
    private static String bindDetail = "";
    public static String resultInfo = null;
    public static String suggestion = "";
    public static String localPlace = "";
    public static String vipID = "";
    public String serverListString = "";
    private static String ChargeDetil = "";
    private static String ChargeCode = "";
    public final byte CONNECT_WAP = 0;
    public final byte CONNECT_NET = 1;
    private final String URL_WAPROOT_IP = "http://10.0.0.172";
    private Thread thread;
    private String aimIP;
    private String aimURL;
    private byte[] content;
    private boolean firstWap;
    private boolean connectionOk;
    private boolean connectionEnd;
    private static String userId;
    private static String key;
    private byte askType;
    public static int kongFirstResult = -1;
    public byte connectState;
    public final byte CONNECT_WAIT = 0;
    public final byte CONNECT_SUCCESS = 1;
    public final byte CONNECT_FAIL = 2;
    public static String servercontent = "";
    public static String sms_bond = "";
    public static String sms_seek = "";
    public static String sms_del = "";
    public static String sms_coin = "";
    int headMark = 0;
    public byte loginServerType = 0;
    public int validateType = 0;
    long length;
    private String tmpStr = "";
    String[][] bodyValue = (String[][])null;
    String[] allinfo = null;
    public boolean isFirstLogin = true;
    static String jadUrl = "";
    static long giStartTime;
    private static Thread giThread = null;

    public HttpConn(String ip, String address, boolean isWap, int type, int logontype) {
        this.validateType = type;
        this.loginServerType = (byte)logontype;
        this.aimIP = ip;
        this.aimURL = address;
        this.askType = 1;
        this.thread = new Thread(this);
        this.isFirstLogin = true;
    }

    public void start() {
        if (this.thread != null) {
            this.thread.start();
            this.connectionEnd = false;
        }

    }

    public void run() {
        this.ProcessConn();
        switch (this.validateType) {
            case 12:
            case 13:
            case 14:
                return;
            default:
                this.setContents(this.loginServerType);
                if (UIGameRun.encryptImg == null) {
                    MainCanvas.mc.aMidlet.exitMIDlet();
                }

        }
    }

    private void ProcessConn() {
        try {
            Exception ioe;
            try {
                ioe = null;
                String tempAddress;
                if (this.askType == 0) {
                    tempAddress = "http://10.0.0.172" + this.aimURL;
                } else {
                    tempAddress = "http://" + this.aimIP + this.aimURL;
                }

                HttpConnection hconn = (HttpConnection)Connector.open(tempAddress);
                hconn.setRequestProperty("X-Online-Host", this.aimIP);
                this.setHeadInfo(hconn);
                if (this.validateType == 13) {
                    this.setPost(hconn);
                }

                int resCode;
                for(resCode = hconn.getResponseCode(); resCode == 302 && this.validateType == 20; resCode = hconn.getResponseCode()) {
                    this.tmpStr = hconn.getHeaderField("location");
                    String[] temp = Util.splitToken(this.tmpStr, '/');
                    PCIncrementService.logAddress = this.tmpStr;
                    this.aimIP = temp[2];
                    this.aimURL = "/" + this.tmpStr.substring(this.tmpStr.indexOf(temp[3]));
                    hconn = (HttpConnection)Connector.open(this.tmpStr);
                    hconn.setRequestProperty("X-Online-Host", this.aimIP);
                    if (!Cons.isCmobile) {
                        this.setHeadInfo(hconn);
                    }
                }

                if (resCode == 200) {
                    this.length = hconn.getLength();

                    try {
                        this.tmpStr = hconn.getHeaderField("Content-Type");
                        if (this.tmpStr != null && this.tmpStr.indexOf("vnd.wap.wml") != -1) {
                            this.firstWap = true;
                        }

                        String str = hconn.getHeaderField("content-type");
                        if (str != null && str.indexOf("vnd.wap.wml") != -1) {
                            this.firstWap = true;
                        }
                    } catch (Exception var9) {
                    }

                    if (this.firstWap) {
                        hconn.close();
                        hconn = null;
                        HttpConnection hconn_1 = (HttpConnection)Connector.open("http://10.0.0.172" + this.aimURL, 3);
                        hconn_1.setRequestProperty("X-Online-Host", this.aimIP);
                        this.setHeadInfo(hconn_1);
                        if (this.validateType == 13) {
                            this.setPost(hconn);
                        }

                        if (hconn_1.getResponseCode() == 200) {
                            switch (this.validateType) {
                                case 12:
                                    loginRewardJad(13);
                                    return;
                                case 13:
                                    Cons.isHasJad = true;
                                    if (Cons.jadLogType == 2) {
                                        loginRewardJad(14);
                                    }

                                    return;
                                case 14:
                                    Cons.isHasJad = true;
                                    return;
                                default:
                                    this.length = hconn_1.getLength();
                                    this.getResultContent(hconn_1, (int)this.length);
                                    this.connectionOk = true;
                            }
                        } else {
                            this.connectionOk = false;
                        }
                    } else {
                        switch (this.validateType) {
                            case 12:
                                loginRewardJad(13);
                                return;
                            case 13:
                                Cons.isHasJad = true;
                                if (Cons.jadLogType == 2) {
                                    loginRewardJad(14);
                                }

                                return;
                            case 14:
                                Cons.isHasJad = true;
                                return;
                            default:
                                this.getResultContent(hconn, (int)this.length);
                                this.connectionOk = true;
                        }
                    }
                } else {
                    this.connectionOk = false;
                }
            } catch (Exception var10) {
                ioe = var10;
                this.connectionOk = false;
                ioe.printStackTrace();
            }
        } finally {
            this.connectionEnd = true;
        }

    }

    private void setHeadInfo(HttpConnection hc) throws IOException {
        String platform = "";
        String gameName = "tianjie1";
        String userAgent = "";
        int ual = 0;

        try {
            platform = System.getProperty("microedition.platform").trim();
        } catch (Exception var7) {
        }

        if (this.validateType != 12 && this.validateType != 14) {
            if (this.validateType == 13) {
                ual = Cons.jadBody.length();
                hc.setRequestProperty("User-Agent", userAgent);
                hc.setRequestProperty("content-length", "" + ual);
                hc.setRequestProperty("content-type", "text/plain");
                hc.setRequestProperty("accept", "*/*");
            } else {
                hc.setRequestProperty("mammoth_ua", platform);
                hc.setRequestProperty("from", MainCanvas.mc.jarFrom);
                hc.setRequestProperty("game-service", gameName);
                if (this.validateType == 20) {
                    hc.setRequestProperty("User-Agent", "NokiaN73");
                    hc.setRequestProperty("accept-encoding", "deflate,gzip,x-gzip");
                }
            }
        } else {
            userAgent = Cons.url_mobile_jad.substring(Cons.url_mobile_jad.indexOf("uaStr=") + "uaStr=".length(), Cons.url_mobile_jad.length());
            hc.setRequestProperty("User-Agent", userAgent);
        }

    }

    private byte[] getResultContent(HttpConnection hconn, int lg) {
        try {
            if (hconn != null) {
                ChargeDetil = hconn.getHeaderField("result");
                ChargeCode = hconn.getHeaderField("resultstatus");
                resultInfo = hconn.getHeaderField("resultmsg");
                resultInfo = this.unicode2String(resultInfo);
                localPlace = hconn.getHeaderField("REGION");
                if (localPlace == null) {
                    localPlace = "";
                } else {
                    localPlace = this.unicode2String(localPlace);
                }

                String attr;
                if (this.validateType == 20) {
                    StringBuffer sb = new StringBuffer();
                    this.headMark = 0;

                    while(true) {
                        attr = hconn.getHeaderFieldKey(this.headMark);
                        String value = null;
                        if (attr != null) {
                            value = hconn.getHeaderField(attr);
                            sb.append("" + attr + "|" + value + "~");
                        }

                        if (hconn.getHeaderFieldKey(this.headMark) == null) {
                            PCIncrementService.getInstance().connectLoghead = sb.toString();
                            this.headMark = 0;
                            break;
                        }

                        ++this.headMark;
                    }
                }

                DataInputStream is = hconn.openDataInputStream();
                if (lg > 0) {
                    this.content = new byte[lg];

                    for(int i = 0; i < lg; ++i) {
                        this.content[i] = (byte)is.read();
                    }
                } else {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int ch = 0;
                    while((ch = is.read()) != -1) {
                        baos.write(ch);
                    }

                    this.content = null;
                    this.content = baos.toByteArray();
                    baos.close();
                    attr = null;
                }

                is.close();
                is = null;
                hconn.close();
                hconn = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            hconn = null;
            this.connectionOk = false;
        }

        return null;
    }

    public boolean getConnectionState() {
        return this.connectionOk;
    }

    public boolean getConnectionEnd() {
        return this.connectionEnd;
    }

    public byte[] getContents() {
        return this.content;
    }

    public void setContets(byte[] s) {
        this.content = s;
    }

    public byte getConnectState() {
        return this.connectState;
    }

    public static String getUserId() {
        return userId == null ? "" : userId;
    }

    public static String getKey() {
        return key == null ? "" : key;
    }

    void setBodyData(String s) {
        this.allinfo = Util.splitToken(s, '\n');
        this.bodyValue = new String[this.allinfo.length][2];

        for(int i = 0; i < this.allinfo.length; ++i) {
            if (this.allinfo[i].indexOf(58) != -1) {
                this.bodyValue[i][0] = this.allinfo[i].substring(0, this.allinfo[i].indexOf(58)).trim();
                this.bodyValue[i][1] = this.allinfo[i].substring(this.allinfo[i].indexOf(58, 0) + 1).trim();
            }
        }

    }

    String getBodyValue(String mark) {
        try {
            for(int i = 0; i < this.allinfo.length; ++i) {
                if (mark.equals(this.bodyValue[i][0])) {
                    return this.bodyValue[i][1];
                }
            }

            return "";
        } catch (Exception var3) {
            return "";
        }
    }

    void getBodyInfo() {
        try {
            kongFirstResult = Integer.parseInt(this.getBodyValue("result"));
        } catch (Exception var5) {
            kongFirstResult = -1;
            this.connectState = 2;
        }

        resultInfo = this.getBodyValue("resultmsg");
        System.out.println(resultInfo);
        MainCanvas.mc.kongMD5 = this.getBodyValue("md5");

        for(int i = 0; i < valueDetail.length; ++i) {
            valueDetail[i] = this.getBodyValue(this.valueSign[i]);
        }

        try {
            String[] ss = Util.splitToken(valueDetail[0], '.');
            int[] s = new int[ss.length];

            for(int i = 0; i < ss.length; ++i) {
                s[i] = Integer.parseInt(ss[i]);
            }

            if (s[0] == 0 && s[1] == 0) {
                s[0] = 2;
                s[1] = 0;
            }

            PCIncrementService.mammothNumbers = 10 * s[0] + s[1];
        } catch (Exception var6) {
            valueDetail[0] = "2";
            PCIncrementService.mammothNumbers = 20;
        }

        servercontent = this.getBodyValue("SERVERCONTENT");
        bindDetail = this.getBodyValue("PASSPORT_BOND");
        vipID = this.getBodyValue("MOBILE_VIP");
        PCLogin.downloadAddress = this.getBodyValue("download");
        suggestion = this.getBodyValue("suggestion");
        this.getBodyValue("charged");
        passportid = this.getBodyValue("PASSPORT_ID");
        localPlace = this.getBodyValue("REGION");
        MainCanvas.quickName = this.getBodyValue("PASS_NAME");
        MainCanvas.quickPSW = this.getBodyValue("PASS_WORD");
        sms_coin = this.getBodyValue("SMS_COIN");
        sms_bond = this.getBodyValue("SMS_BOND");
        sms_del = this.getBodyValue("SMS_DEL");
        sms_seek = this.getBodyValue("SMS_SEEK");
        String gPort = this.getBodyValue("GHEART");
        if (!"".equals(gPort)) {
            try {
                String[] gPortStr = Util.splitToken(gPort, ':');
                MainCanvas.gi.setGIP(gPortStr[0]);
                MainCanvas.gi.setGPort(gPortStr[1]);
                MainCanvas.gi.setGJumpId(gPortStr[2]);
            } catch (Exception var4) {
            }
        }

        this.serverListString = this.getBodyValue("body");
    }

    public void setContents(byte serverType) {
        if (this.connectionEnd) {
            if (this.connectionOk) {
                this.connectState = 1;
            } else {
                this.connectState = 2;
                if (!Cons.cmwap && this.loginServerType == 1) {
                    this.connectState = 1;
                }
            }
        }

        label102:
        switch (this.connectState) {
            case 0:
            default:
                break;
            case 1:
                ByteArrayInputStream bs;
                DataInputStream is;
                Exception e;
                int ts;
                int tuLength;
                int idStart;
                switch (serverType) {
                    case 1:
                        try {
                            StringBuffer tmpStr = new StringBuffer();

                            for(int i = 0; i < this.content.length; ++i) {
                                tmpStr.append((char)this.content[i]);
                            }

                            String sb = tmpStr.toString();
                            idStart = sb.indexOf("userId=");
                            if (idStart == -1) {
                                kongFirstResult = -1;
                                key = "";
                                userId = "";
                            } else {
                                int idEnd = sb.indexOf("\r", idStart);
                                userId = sb.substring(idStart + 7, idEnd);
                                ts = sb.indexOf("key=", idEnd);
                                tuLength = sb.indexOf("\r", ts);
                                key = sb.substring(ts + 4, tuLength);
                                kongFirstResult = -1;
                                if (!"".equals(userId) && !MainCanvas.startTickUser) {
                                    MainCanvas.startTickUser = true;
                                }
                            }
                        } catch (Exception var12) {
                            var12.printStackTrace();
                        }
                        break label102;
                    case 2:
                        kongFirstResult = -1;
                        bs = new ByteArrayInputStream(this.content);
                        is = new DataInputStream(bs);

                        try {
                            String tmp = is.readUTF();
                            this.setBodyData(tmp);
                            this.getBodyInfo();
                            e = null;
                            is.close();
                            bs.close();
                        } catch (Exception var11) {
                            kongFirstResult = -1;
                            this.connectState = 2;
                        }
                        break label102;
                    case 3:
                        kongFirstResult = -1;
                        if (ChargeDetil != null && !ChargeDetil.equals("1")) {
                            if (ChargeCode == null) {
                                this.connectState = 2;
                            } else if (ChargeCode.equals("1100")) {
                                kongFirstResult = 1;
                            } else {
                                kongFirstResult = 100;
                            }
                        } else {
                            this.connectState = 2;
                        }
                    case 4:
                    default:
                        break label102;
                    case 5:
                        kongFirstResult = -1;
                        bs = new ByteArrayInputStream(this.content);
                        is = new DataInputStream(bs);

                        try {
                            idStart = is.readInt();
                            byte[] bb = new byte[idStart];
                            ts = 0;

                            for(tuLength = bb.length; ts < tuLength; ++ts) {
                                bb[ts] = is.readByte();
                                bb[ts] = (byte)(bb[ts] - 29);
                            }

                            AdvertiseSplash.httpUrl = new String(bb);
                            ts = is.readInt();
                            AdvertiseSplash.allImage = new Image[ts];
                            byte[] tuData = null;

                            for(int i = 0; i < ts; ++i) {
                                tuLength = is.readInt();
                                tuData = new byte[tuLength];

                                for(int j = 0; j < tuLength; ++j) {
                                    tuData[j] = is.readByte();
                                }

                                AdvertiseSplash.allImage[i] = Image.createImage(tuData, 0, tuLength);
                            }

                            AdvertiseSplash.isGetUrl = true;
                            is.close();
                            bs.close();
                            kongFirstResult = 1;
                        } catch (Exception var13) {
                            e = var13;
                            e.printStackTrace();
                        }
                        break label102;
                }
            case 2:
                key = null;
                userId = null;
        }

        this.processCmobileLogin(MainCanvas.mc, this.validateType);
    }

    public static String getChargeDetil() {
        return ChargeDetil == null ? "lose" : ChargeDetil;
    }

    public static String getChargeCode() {
        return ChargeCode == null ? "loseCode" : ChargeCode;
    }

    private String unicode2String(String s) {
        if (s == null) {
            return null;
        } else {
            StringBuffer result = new StringBuffer();

            for(int i = 0; i < s.length(); ++i) {
                int ch;
                if ((ch = s.charAt(i)) == 37) {
                    int temp = i++;
                    if (s.length() >= i + 4) {
                        ch = Integer.parseInt(s.substring(i, i + 4), 16);
                        i += 3;
                    } else {
                        i = temp;
                    }
                }

                result.append((char)ch);
            }

            return result.toString();
        }
    }

    public static boolean getBindDetail() {
        return bindDetail == null ? false : bindDetail.toLowerCase().equals("true");
    }

    public static void setBindDetail(String bindDetail) {
        HttpConn.bindDetail = bindDetail;
    }

    void setPost(HttpConnection hc) throws IOException {
        OutputStream os = null;
        hc.setRequestMethod("POST");
        os = hc.openOutputStream();
        if (Cons.jadBody != null) {
            byte[] bytes = Cons.jadBody.getBytes();

            for(int i = 0; i < bytes.length; ++i) {
                os.write(bytes[i]);
            }

            os.close();
            os = null;
        }
    }

    public void processCmobileLogin(MainCanvas mainCanvas, int type) {
        if (mainCanvas.baseForm != null || type == 4 || type == 10 || type == 16 || type == 15) {
            byte tempState = this.getConnectState();
            switch (tempState) {
                case 0:
                    if (type != 8 && type != 9 && type != 10 && type != 20 && type != 16 && type != 15 && (type != 4 || !mainCanvas.timeCheck)) {
                        mainCanvas.baseForm.addAboutForm("waiting", "等待认证中...", (byte)0, 160, 0);
                    }
                    break;
                case 1:
                    switch (type) {
                        case 0:
                            if (this.isFirstLogin) {
                                if (Cons.isCmobile) {
                                    MainCanvas.userID = getUserId();
                                    MainCanvas.userKey = getKey();
                                    mainCanvas.kong_url = mainCanvas.getURL(true, (byte)0);
                                    mainCanvas.httpConn = null;
                                    mainCanvas.httpConn = new HttpConn(mainCanvas.url_ip, mainCanvas.kong_url, Cons.cmwap, 0, 2);
                                    mainCanvas.httpConn.start();
                                }

                                this.isFirstLogin = false;
                            }
                            break;
                        case 1:
                            if (this.isFirstLogin) {
                                if (Cons.isCmobile) {
                                    MainCanvas.userID = getUserId();
                                    MainCanvas.userKey = getKey();
                                    mainCanvas.kong_url = mainCanvas.getURL(true, (byte)1);
                                    mainCanvas.httpConn = null;
                                    mainCanvas.httpConn = new HttpConn(mainCanvas.url_ip, mainCanvas.kong_url, Cons.cmwap, 1, 2);
                                    mainCanvas.httpConn.start();
                                }

                                this.isFirstLogin = false;
                            }
                            break;
                        case 2:
                        case 6:
                        case 7:
                        case 9:
                        case 12:
                        case 13:
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                        case 18:
                        case 19:
                        default:
                            if (this.isFirstLogin) {
                                this.isFirstLogin = false;
                            }
                            break;
                        case 3:
                            if (this.isFirstLogin) {
                                MainCanvas.userID = getUserId();
                                MainCanvas.userKey = getKey();
                                mainCanvas.kong_url = "/judgeserver/charge?&userId=" + MainCanvas.userID + "&key=" + MainCanvas.userKey + "&game=tianjie" + "&money=" + PCIncrementService.sum + "&from=kong";
                                mainCanvas.httpConn = null;
                                mainCanvas.httpConn = new HttpConn("tianjie.elive.com.tw", mainCanvas.kong_url, Cons.cmwap, 3, 3);
                                mainCanvas.httpConn.start();
                                this.isFirstLogin = false;
                            }
                            break;
                        case 4:
                            if (this.isFirstLogin) {
                                MainCanvas.userID = getUserId();
                                MainCanvas.userKey = getKey();
                                if (mainCanvas.timeCheck) {
                                    MainCanvas.ni.send(16781312);
                                    mainCanvas.timeCheck = false;
                                }

                                if (!"".equals(MainCanvas.userID) && MainCanvas.gi.getGJumpId() != null && !"".equals(MainCanvas.gi.getGJumpId())) {
                                    giStartTime = System.currentTimeMillis();
                                    giPerMinute();
                                }

                                mainCanvas.isGetingUserID = false;
                                this.isFirstLogin = false;
                            }

                            return;
                        case 5:
                        case 8:
                            if (this.isFirstLogin) {
                                this.isFirstLogin = false;
                            }

                            return;
                        case 10:
                            if (this.isFirstLogin) {
                                mainCanvas.getURLConnect = this.getContents();
                                this.setContets((byte[])null);
                                this.isFirstLogin = false;
                            }

                            return;
                        case 11:
                            if (this.isFirstLogin) {
                                if (Cons.isCmobile) {
                                    MainCanvas.userID = getUserId();
                                    MainCanvas.userKey = getKey();
                                    mainCanvas.kong_url = mainCanvas.getURL(true, (byte)5);
                                    mainCanvas.httpConn = null;
                                    mainCanvas.httpConn = new HttpConn("tianjie.elive.com.tw", mainCanvas.kong_url, Cons.cmwap, 11, 2);
                                    mainCanvas.httpConn.start();
                                }

                                this.isFirstLogin = false;
                            }
                            break;
                        case 20:
                            if (this.isFirstLogin) {
                                PCIncrementService.max_BodySend = 0;
                                if (this.getContents() != null) {
                                    if (this.getContents().length % 256 != 0) {
                                        PCIncrementService.max_BodySend = this.getContents().length / 256 + 1;
                                    } else {
                                        PCIncrementService.max_BodySend = this.getContents().length / 256;
                                    }
                                }

                                MainCanvas.ni.send(Cmd.C_VALUEADDED_RENT);
                                this.isFirstLogin = false;
                            }

                            return;
                    }

                    if (type != 9 && type != 16 && type != 15) {
                        mainCanvas.baseForm.addAboutForm("waiting", "等待认证中...", (byte)0, 160, 0);
                    }

                    if (kongFirstResult != -1) {
                        this.processKongZhongLogin(type);
                    }
                    break;
                case 2:
                    if (mainCanvas.twoSelect) {
                        Cons.cmwap = false;
                        if (this.validateType == 0) {
                            mainCanvas.Login();
                        } else if (this.validateType == 1) {
                            mainCanvas.Logon();
                        } else if (this.validateType == 11) {
                            mainCanvas.quickLogin();
                        } else if (this.validateType == 15 || this.validateType == 16) {
                            mainCanvas.getSMS_Content(this.validateType);
                        }

                        mainCanvas.twoSelect = false;
                        return;
                    }

                    this.isFirstLogin = false;
                    if (type == 4) {
                        mainCanvas.isGetingUserID = false;
                        MainCanvas.userID = getUserId();
                        MainCanvas.userKey = getKey();
                        return;
                    }

                    if (type != 5 && type != 8 && type != 10) {
                        if (type == 9) {
                            AdvertiseSplash var5 = AdvertiseSplash.getInstance();
                            AdvertiseSplash.getInstance().getClass();
                            var5.setMoreGameState(3);
                            return;
                        }

                        if (type == 20) {
                            MainCanvas.setMessage(mainCanvas.baseForm, "联网失败，请求失败,请重试");
                            mainCanvas.isInBaoyueWait = false;
                            return;
                        }

                        if (type == 16) {
                            mainCanvas.setState((byte)31);
                            PCIncrementService var10000 = PCIncrementService.getInstance();
                            PCIncrementService.getInstance().getClass();
                            var10000.sendState = 4;
                            return;
                        }

                        if (type == 15) {
                            PCBindService.bindState = 2;
                            mainCanvas.setState((byte)29);
                            mainCanvas.releaseUI();
                            return;
                        }

                        String temStr = "";
                        if (this.loginServerType == 1) {
                            temStr = "卓望计费平台";
                        } else {
                            if (this.loginServerType != 3) {
                                if (MainCanvas.loseMark == 1) {
                                    mainCanvas.loseLog(MainCanvas.loseMark);
                                    MainCanvas.loseMark = 0;
                                }

                                mainCanvas.baseForm.addAboutForm("err", "连接验证服务器失败,请到设置中选择连接方式,配置方法详见官方网站,手机登录wy.kong.net", (byte)1, 220, 0);
                                break;
                            }

                            temStr = "充值服务";
                        }

                        MainCanvas.setMessage(mainCanvas.baseForm, "连接" + temStr + "失败," + (Cons.isCmobile ? "请确认cmwap连接再重试" : "请重试"));
                        break;
                    }

                    return;
                default:
                    MainCanvas.setMessage(mainCanvas.baseForm, "数据错误,请重试");
            }

        }
    }

    public void processKongZhongLogin(int type) {
        if (MainCanvas.mc.baseForm != null || type == 16 || type == 15) {
            byte tempState = this.getConnectState();
            switch (tempState) {
                case 0:
                    if (type != 16 && type != 15) {
                        MainCanvas.mc.baseForm.addAboutForm("waiting", "等待认证中...", (byte)0, 160, 0);
                    }
                    break;
                case 1:
                    switch (kongFirstResult) {
                        case -1:
                            MainCanvas.setMessage(MainCanvas.mc.baseForm, "未知错误，请重试！");
                            break;
                        case 1:
                            byte[] agree = new byte[]{1, 0};
                            PCIncrementService.hasAgree = true;
                            agree[1] = PCIncrementService.sendCount;
                            if (type == 0) {
                                MainCanvas.tempName[0] = MainCanvas.mc.texts[0].getLabel().trim();
                                MainCanvas.tempName[1] = MainCanvas.mc.texts[1].getLabel().trim();
                                Util.saveRecord(MainCanvas.tempName, "userNW");
                                Util.saveRecord(agree, "agreeOp");
                                MainCanvas.mc.baseForm.addAboutForm("succeed", resultInfo, (byte)1, 140, 0);
                            } else if (type == 1) {
                                if (Cons.isLocal) {
                                    MainCanvas.mc.processServerDiv(Cons.LOCAL_SERVER_LIST);
                                } else {
                                    MainCanvas.mc.processServerDiv(MainCanvas.mc.httpConn.serverListString);
                                }

                                Util.saveRecord(agree, "agreeOp");
                                if (!PCLogin.downloadAddress.equals("")) {
                                    MainCanvas.logon3_state = 9;
                                    MainCanvas.mc.setState((byte)12);
                                    MainCanvas.mc.releaseUI();
                                    return;
                                }

                                MainCanvas.mc.setState((byte)11);
                                MainCanvas.mc.releaseUI();
                                NetInterface.getInstance().closeConn();
                            } else if (type == 2) {
                                MainCanvas.mc.baseForm.addAboutForm("succeed1", resultInfo, (byte)1, 140, 0);
                                MainCanvas.mc.userPassword = MainCanvas.mc.texts[1].getLabel().trim();
                            } else if (type == 3) {
                                UIForm var5 = MainCanvas.mc.baseForm;
                                StringBuffer var10001 = (new StringBuffer()).append("您已经成功充入").append(PCIncrementService.sum).append("元，可以得到");
                                PCIncrementService.getInstance().getClass();
                                MainCanvas.setMessage(var5, var10001.append(100 * PCIncrementService.sum).append("个点数").toString());
                            } else if (type == 9) {
                                MainCanvas.mc.getURLConnect = this.getContents();
                                this.setContets((byte[])null);
                                AdvertiseSplash.getInstance().AnalysisUrl();
                                AdvertiseSplash var4 = AdvertiseSplash.getInstance();
                                AdvertiseSplash.getInstance().getClass();
                                var4.setMoreGameState(2);
                                if (AdvertiseSplash.getInstance().iskeyUP) {
                                    AdvertiseSplash.getInstance().goUP();
                                    AdvertiseSplash.getInstance().aSplashImageTick = AdvertiseSplash.getInstance().aSplashImageNum - 1;
                                } else {
                                    AdvertiseSplash.getInstance().goDown();
                                    AdvertiseSplash.getInstance().aSplashImageTick = AdvertiseSplash.getInstance().firstPicNum;
                                }
                            } else if (type == 11) {
                                MainCanvas.mc.texts[0].setStr(MainCanvas.quickName);
                                MainCanvas.mc.texts[1].setStr(MainCanvas.quickPSW);
                                MainCanvas.mc.baseForm.addAboutForm("log", "快速注册成功，您的账号为：" + MainCanvas.quickName + ",密码为：" + MainCanvas.quickPSW + ",按确定登录游戏", (byte)1, 180, 0);
                            } else {
                                if (type != 15) {
                                    if (type == 16) {
                                        MainCanvas.mc.setState((byte)31);
                                        PCIncrementService var10000 = PCIncrementService.getInstance();
                                        PCIncrementService.getInstance().getClass();
                                        var10000.sendState = 0;
                                    }
                                    break;
                                }

                                if (sms_coin != null && !"".equals(sms_coin.trim())) {
                                    PCBindService.bindState = 1;
                                } else {
                                    PCBindService.bindState = 2;
                                }

                                MainCanvas.mc.setState((byte)29);
                                MainCanvas.mc.releaseUI();
                            }
                            break;
                        case 4:
                            if (!"".equals(suggestion)) {
                                MainCanvas.setMessage(MainCanvas.mc.baseForm, resultInfo + ",建议您修改帐号为：" + suggestion);
                                MainCanvas.mc.texts[0].setStr(suggestion);
                            } else {
                                MainCanvas.setMessage(MainCanvas.mc.baseForm, resultInfo);
                            }
                            break;
                        case 11:
                            if (!PCLogin.downloadAddress.equals("")) {
                                MainCanvas.logon3_state = 5;
                                MainCanvas.mc.setState((byte)12);
                                MainCanvas.mc.releaseUI();
                            } else {
                                MainCanvas.setMessage(MainCanvas.mc.baseForm, resultInfo);
                            }
                            break;
                        case 100:
                            MainCanvas.setMessage(MainCanvas.mc.baseForm, "充值操作失败");
                            break;
                        default:
                            if (resultInfo == null) {
                                resultInfo = "未知错误，请重试！";
                            }

                            MainCanvas.setMessage(MainCanvas.mc.baseForm, resultInfo);
                    }

                    if (kongFirstResult != 1) {
                        kongFirstResult = -1;
                    }
                    break;
                case 2:
                    if (MainCanvas.loseMark == 1) {
                        MainCanvas.mc.loseLog(MainCanvas.loseMark);
                        MainCanvas.loseMark = 0;
                    }

                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "连接登录服务器失败,请重试");
                    break;
                default:
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "连接错误，请重试");
            }

        }
    }

    public static void loginRewardJad(final int type) {
        if (Cons.cmwap && Cons.jadLogType != 0) {
            if (!Cons.isHasJad || Cons.jadLogType == 2) {
                switch (type) {
                    case 12:
                        jadUrl = Cons.url_mobile_jad;
                        break;
                    case 13:
                        jadUrl = Cons.url_mobile_notify;
                        break;
                    case 14:
                        jadUrl = Cons.url_mobile_jar;
                }

                Thread t = new Thread(new Runnable() {
                    public void run() {
                        HttpConn pp = new HttpConn(Cons.url_mobile_ip, HttpConn.jadUrl, Cons.cmwap, type, 0);
                        pp.start();
                    }
                });
                t.start();
            }
        }
    }

    public static String getSMSNumber(String sms) {
        return sms.substring(sms.indexOf(64) + 1, sms.length());
    }

    static void giPerMinute() {
        if (giThread == null) {
            giThread = new Thread(new Runnable() {
                public void run() {
                    MainCanvas.gi.send(1);
                    MainCanvas.gi.send(0);

                    while(true) {
                        long curTime = System.currentTimeMillis();
                        if (curTime - HttpConn.giStartTime >= 60000L) {
                            MainCanvas.gi.send(0);
                            HttpConn.giStartTime = System.currentTimeMillis();
                        }

                        try {
                            Thread.sleep(2000L);
                        } catch (Exception var4) {
                            Exception e = var4;
                            e.printStackTrace();
                        }
                    }
                }
            });
            giThread.start();
        }

    }
}
