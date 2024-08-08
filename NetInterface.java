
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;

public class NetInterface implements Runnable {

    private static final long SENDER_WAIT_TIME = 100L;
    private static final long RECEIVER_WAIT_TIME = 100L;
    private static final byte MAX_CHECKOUT_ID = 123;
    private static final byte HEAD_INFO_LENGTH = 4;
    private static final byte NO_NETCOMMD_ID = 8;
    static String strMD5 = null;
    static int firstResult;
    private SocketConnection socket = null;
    private DataInputStream socketInputStream = null;
    private DataOutputStream socketOutputStream = null;
    private Sender sender = null;
    private Vector senderPool = new Vector();
    private Receiver receiver = null;
    /**
     * 接受数据包池
     */
    private Vector receiverPool = new Vector();
    public byte checkoutID = -1;
    public boolean exit = false;
    private static NetInterface instance = null;
    boolean first = true;
    long startTime;
    /**
     * 是否继续收取数据
     */
    private boolean isReceiveData = true;
    /**
     * 是否继续发送数据
     */
    private boolean isSendData = true;

    private NetInterface() {
    }

    public static NetInterface getInstance() {
        if (instance == null) {
            instance = new NetInterface();
        }

        return instance;
    }

    public int getSendNum() {
        return this.senderPool.size();
    }

    public int getReNum() {
        return this.receiverPool.size();
    }

    private boolean buildConn() {
        try {
            socket = (SocketConnection) Connector.open(MainCanvas.serverUrl, 3, false);
            socket.setSocketOption((byte) 2, 0);
            socket.setSocketOption((byte) 0, 10);
            socket.setSocketOption((byte) 1, 5);
            socketInputStream = this.socket.openDataInputStream();
            socketOutputStream = this.socket.openDataOutputStream();
            sender = new Sender();
            sender.start();
            receiver = new Receiver();
            receiver.start();
        } catch (Exception var2) {
            Exception e = var2;
            MainCanvas.closeConnection();
            e.printStackTrace();
            return false;
        }

        this.exit = false;
        return true;
    }

    public void closeConn() {
        try {
            if (this.sender != null) {
                this.sender.stop();
                this.sender = null;
            }
        } catch (Exception var6) {
        }

        try {
            if (this.receiver != null) {
                this.receiver.stop();
                this.receiver = null;
            }
        } catch (Exception var5) {
        }

        try {
            if (this.socketInputStream != null) {
                if (!Cons.phoneType.startsWith("NokiaN95")) {
                    this.socketInputStream.close();
                }

                this.socketInputStream = null;
            }
        } catch (Exception var4) {
        }

        try {
            if (this.socketOutputStream != null) {
                if (!Cons.phoneType.startsWith("NokiaN95")) {
                    this.socketOutputStream.close();
                }

                this.socketOutputStream = null;
            }
        } catch (Exception var3) {
        }

        try {
            if (this.socket != null) {
                this.socket.close();
                this.socket = null;
            }
        } catch (Exception var2) {
        }

        this.checkoutID = -1;
    }

    public void run() {
        this.buildConn();
        if (UIGameRun.encryptImg == null) {
            MainCanvas.mc.aMidlet.exitMIDlet();
        }
    }

    public void send(int commID) {
        if (commID == 16777472) {
            this.senderPool.removeAllElements();
            this.receiverPool.removeAllElements();
        }
        byte[] buf;
        if (MainCanvas.byCurEncryptID != MainCanvas.byLastEncryptID) {
            if (commID == 16779264) {
                MainCanvas.byEncryptChainPosLast = MainCanvas.byEncryptChainPos;
                buf = this.enCode(commID);
                this.encrypt(buf, true);
                this.senderPool.addElement(buf);
                MainCanvas.byLastEncryptID = MainCanvas.byCurEncryptID;
                MainCanvas.byEncryptChainPos = 0;
                if (this.socket == null) {
                    try {
                        (new Thread(this)).start();
                    } catch (Exception var4) {
                    }
                }
                return;
            }

            MainCanvas.ni.send(16779264);
        }

        buf = this.enCode(commID);
        this.encrypt(buf, false);
        this.senderPool.addElement(buf);
        if (this.socket == null) {
            try {
                (new Thread(this)).start();
            } catch (Exception var5) {
            }
        }

    }

    private void receiveInfo() {
        try {
            // 获取数据长度
            short length = socketInputStream.readShort();
            if (length <= 0) {
                System.out.print("[recevie data]:receive invaild data length!");
            } else {
                byte[] data = new byte[length - 2];
                socketInputStream.readFully(data, 0, data.length);
                receiverPool.addElement(data);
            }
        } catch (Exception e) {
            receiver.stop();
            System.out.println("[recevie data]:receive data have a error occur!");
        }
    }

    private boolean sendInfo() {
        try {
            ++MainCanvas.sendTick;
            if (this.first) {
                this.startTime = System.currentTimeMillis();
                this.first = false;
            }

            if (this.senderPool.size() != 0) {
                for (; this.senderPool.size() != 0; this.senderPool.removeElementAt(0)) {
                    byte[] tmpBuf = (byte[]) ((byte[]) this.senderPool.elementAt(0));
                    if (tmpBuf != null) {
                        this.socketOutputStream.write(tmpBuf);
                        MainCanvas.serverTick = 0;
                    }
                }
            } else {
                ++MainCanvas.serverTick;
            }

            if (System.currentTimeMillis() - this.startTime >= 30000L) {
                Player.CDQ_T = MainCanvas.sendTick;
                MainCanvas.ni.send(16781056);
                this.first = true;
            } else if (System.currentTimeMillis() - this.startTime < 0L) {
                this.first = true;
            }

            this.socketOutputStream.flush();
            return true;
        } catch (Exception var2) {
            Exception e = var2;
            MainCanvas.mc.exitGame((byte) 1);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 解码服务器数据
     */
    public void deCode() {
        while (receiverPool.size() > 0) {
            // 暂时处理，即使处理当前数据包发生错误，也要删除当前数据包
            try {
                ParseCompress.parse((byte[]) (receiverPool.elementAt(0)));
            }catch(Exception e){
                e.printStackTrace();
                System.out.print("[decode command]:decode aommand have a error occur!");
            }
            receiverPool.removeElementAt(0);
        }
    }

    private byte[] enCode(int commID) {
        byte[] tmpData = null;
        short tmpLength = 0;
        byte[] temp = ParseCompress.compress(commID);
        if (temp != null) {
            tmpData = temp;
            tmpLength = (short) (tmpData.length + 4 + 4 + 1);
        }

        byte checkCode = 0;

        for (int n = 0; n < temp.length; ++n) {
            checkCode = (byte) ((checkCode + temp[n]) % 128);
        }

        checkCode = (byte) (~checkCode);
        ByteArray dos = new ByteArray();
        dos.writeShort(tmpLength);
        dos.writeByte((byte) 8);
        ++this.checkoutID;
        if (this.checkoutID > 123) {
            this.checkoutID = 0;
        }

        dos.writeByte(this.checkoutID);
        dos.writeInt(commID);
        dos.writeByte(checkCode);
        if (tmpData != null) {
            dos.writeByteArray(tmpData);
        }

        return dos.toByteArray();
    }

    private void encrypt(byte[] stream, boolean bUseLast) {
        if (MainCanvas.byEncryptChain != null || MainCanvas.byEncryptChainLast != null) {
            byte byOp = 0;
            byte byArg = 0;
            if (bUseLast) {
                if (MainCanvas.byEncryptChainLast == null) {
                    return;
                }

                try {
                    byArg = MainCanvas.byEncryptChainLast[MainCanvas.byEncryptChainPosLast];
                    byOp = MainCanvas.byEncryptChainLast[MainCanvas.byEncryptChainPosLast + 1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    byArg = MainCanvas.byEncryptChain[MainCanvas.byEncryptChainPos];
                    byOp = MainCanvas.byEncryptChain[MainCanvas.byEncryptChainPos + 1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }

                MainCanvas.byEncryptChainPos = (byte) (MainCanvas.byEncryptChainPos + 2);
                if (MainCanvas.byEncryptChainPos >= MainCanvas.byEncryptChain.length) {
                    MainCanvas.byEncryptChainPos = 0;
                }
            }

            ByteArray dis = new ByteArray(stream);
            short basecode = dis.readShort();
            dis = null;
            switch (byOp) {
                case 33:
                    basecode = (short) (basecode - byArg);
                    break;
                case 40:
                    basecode = (short) (basecode + byArg);
                    break;
                case 42:
                    basecode = (short) (basecode % byArg);
                    break;
                case 51:
                    basecode = (short) (basecode << byArg);
                    break;
                case 58:
                    basecode = (short) (basecode >> byArg);
            }

            byte basecode2 = (byte) (basecode & 255);
            stream[8] ^= stream[3];
            stream[3] ^= basecode2;
            stream[5] ^= stream[3];
            stream[7] ^= stream[3];
            stream[4] ^= stream[7];
            stream[6] ^= stream[5];
        }
    }

    private class Sender implements Runnable {

        private boolean isrunning = false;
        private Thread self = new Thread(this, "Net Sender");

        public void start() {
            if (!isrunning) {
                isSendData = true;
                self.start();
            }
        }

        public void recovery() {
            isSendData = true;
            synchronized (this) {
                this.notify();
            }
        }

        public void stop() {
            isSendData = false;
        }

        public void run() {
            while (true) {
                try {
                    synchronized (this) {
                        // 如果调用了暂时不处理数据，进入等待
                        if (!isSendData) {
                            this.wait();
                        }
                        // 继续处理数据
                        NetInterface.this.sendInfo();
                        // 线程休眠一会儿
                        this.wait(SENDER_WAIT_TIME);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class Receiver implements Runnable {

        private boolean isrunning = false;
        private Thread self = new Thread(this, "Net Receiver");

        public void start() {
            if (!isrunning) {
                isReceiveData = true;
                self.start();
            }
        }

        public void recovery() {
            isReceiveData = true;
            synchronized (this) {
                this.notify();
            }
        }

        public void stop() {
            isReceiveData = false;
        }

        public void run() {
            while (true) {
                try {
                    synchronized (this) {
                        // 如果调用了暂时不处理数据，进入等待
                        if (!isReceiveData) {
                            this.wait();
                        }
                        // 继续处理数据
                        NetInterface.this.receiveInfo();
                        // 线程休眠一会儿
                        this.wait(RECEIVER_WAIT_TIME);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
