
public class PCAuction {

    public static final byte PER_PAGE = 7;
    public static byte pageDirect = 2;
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
    public static int currentPage = 0;
    public static int totalPage = 0;
    public static short[] formulaPara = new short[3];
    public static final byte[] TIME_PERSISTANCE = new byte[]{2, 5, 12, 16, 24};
    public static String[] items = new String[]{"非常短 ", " 短 ", " 中 ", " 长 ", "非常长"};

    public static void parse(int commID, byte[] data) {
        String hexID = Integer.toHexString(commID);
        System.out.println("[处理命令]:命令ID:0x" + hexID + ",命令类型：PCAuction");
        byte tempB;
        String msg;
        int tmpTotalNum, tmpCurIndex;
        String tempName;
        int i, cmd, p;
        short t;
        int index;
        byte tempA;
        ByteArray execDataIn = new ByteArray(data);
        switch (commID) {
            case 234881664:
                tempB = 0;
                tempB = execDataIn.readByte();
                (Player.getInstance()).money = execDataIn.readInt();
                MainCanvas.mc.texts[3].setStr((Player.getInstance()).money + "");
                msg = null;
                if (tempB == 1) {
                    msg = "出价被接受！";
                    int j = MainCanvas.mc.tblAuction.getCurrentPointer();
                    MainCanvas.mc.auctionParams[j][2] = MainCanvas.mc.texts[4].getNumber();
                    MainCanvas.mc.texts[0].setLabel(MainCanvas.mc.auctionParams[j][2] + "");
                    if (MainCanvas.mc.auctionParams[j][2] > 99999) {
                        MainCanvas.mc.texts[0].setColor(16739328);
                    } else {
                        MainCanvas.mc.texts[0].setDefaultColor();
                    }
                } else if (tempB == 2) {
                    msg = "一口价被接受！";
                } else if (tempB == 3) {
                    msg = "该物品已卖出！";
                } else if (tempB == 4) {
                    msg = "出价不够高！";
                } else if (tempB == 5) {
                    msg = "该物品是你拍卖的!";
                } else if (tempB == 6) {
                    msg = "你是当前最高出价者!";
                }
                MainCanvas.mc.baseForm.addAboutForm("message", msg, (byte) 1, 140, 50);
                break;
            case 234881920:
                MainCanvas.auctionCanSell = execDataIn.readByte();
                MainCanvas.auctionMoney = execDataIn.readInt();
                if (MainCanvas.auctionCanSell != 1 && MainCanvas.subAuction == 0) {
                    MainCanvas.mc.baseForm.setAboutForm(null);
                    MainCanvas.mc.setNPCSubState((byte) 0);
                    MainCanvas.mc.txtMoney = null;
                    MainCanvas.NPCMenu.setSubMenu(null);
                    MainCanvas.mc.releaseUI();
                    break;
                }
                if (MainCanvas.subAuction != 0) {
                    MainCanvas.mc.setAuctionState((byte) -1);
                }
                break;
            case 234881408:
                tmpTotalNum = 0;
                tmpCurIndex = 0;
                MainCanvas.auctionMoney = execDataIn.readInt();
                tmpTotalNum = execDataIn.readInt();
                tmpCurIndex = execDataIn.readInt();
                if (MainCanvas.mc.auctionNames == null) {
                    MainCanvas.mc.auctionNames = new String[7];
                }
                if (MainCanvas.mc.auctionParams == null) {
                    MainCanvas.mc.auctionParams = new int[7][6];
                }
                if (MainCanvas.mc.auctionId == null) {
                    MainCanvas.mc.auctionId = new long[7];
                }
                tempName = null;
                for (i = 0; i < 7; i++) {
                    MainCanvas.mc.auctionId[i] = execDataIn.readLong();
                    tempName = execDataIn.readUTF();
                    if (tempName.length() > 4) {
                        tempName = tempName.substring(0, 4) + "...";
                    }
                    MainCanvas.mc.auctionNames[i] = tempName;
                    MainCanvas.mc.auctionParams[i][0] = execDataIn.readByte();
                    MainCanvas.mc.auctionParams[i][5] = execDataIn.readByte();
                    MainCanvas.mc.auctionParams[i][1] = execDataIn.readByte();
                    MainCanvas.mc.auctionParams[i][2] = execDataIn.readInt();
                    MainCanvas.mc.auctionParams[i][3] = execDataIn.readInt();
                    MainCanvas.mc.auctionParams[i][4] = execDataIn.readShort();
                }
                if (MainCanvas.mc.typeID == 17) {
                    MainCanvas.mc.setAuctionState((byte) -2);
                } else {
                    MainCanvas.mc.setAuctionState((byte) 1);
                }
                currentPage = getPage(tmpCurIndex) + 1;
                totalPage = getPage(tmpTotalNum);
                MainCanvas.mc.releaseUI();
                break;
            case 234882432:
                cmd = execDataIn.readByte();
                p = execDataIn.readInt();
                t = execDataIn.readShort();
                if (cmd == 0) {
                    MainCanvas.mc.baseForm.addAboutForm("message", "拍卖物品已不存在!", (byte) 1, 160, 50);
                    break;
                }
                index = MainCanvas.mc.tblAuction.getCurrentPointer();
                MainCanvas.mc.auctionParams[index][2] = p;
                MainCanvas.mc.auctionParams[index][4] = t;
                MainCanvas.mc.texts[0].setLabel(MainCanvas.mc.auctionParams[index][2] + "");
                MainCanvas.mc.texts[1].setLabel(MainCanvas.mc.auctionParams[index][3] + "");
                if (MainCanvas.mc.auctionParams[index][2] > 99999) {
                    MainCanvas.mc.texts[0].setColor(16739328);
                } else {
                    MainCanvas.mc.texts[0].setDefaultColor();
                }
                MainCanvas.mc.texts[2].setStr(getTime(MainCanvas.mc.auctionParams[index][4]));
                MainCanvas.mc.baseForm.setAboutForm(null);
                break;
            case 234882176:
                tempA = 0;
                tempA = execDataIn.readByte();
                msg = "";
                if (tempA == 1) {
                    msg = "拍卖成功!";
                    (Player.getInstance()).money -= MainCanvas.auctionPrice;
                    MainCanvas.mc.txtMoney.setLabel("" + (Player.getInstance()).money);
                    if (MainCanvas.dramatisPackage.stuffNumber[MainCanvas.auctionIndex] == MainCanvas.mc.mImages[0].getNumber()) {
                        MainCanvas.dramatisPackage.setGridNull(MainCanvas.auctionIndex);
                    } else {
                        MainCanvas.dramatisPackage.stuffNumber[MainCanvas.auctionIndex] = (byte) (MainCanvas.dramatisPackage.stuffNumber[MainCanvas.auctionIndex] - MainCanvas.mc.mImages[0].getNumber());
                    }
                } else if (tempA == 0) {
                    msg = "保证金不足！";
                } else if (tempA == 2) {
                    msg = "物品不可以拍卖！";
                }
                MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm().setAboutForm(null);
                MainCanvas.mc.baseForm.getCurrentFocusForm().addAboutForm("result", msg, (byte) 1, 140, 50);
                break;
            case 234883200:
                formulaPara[0] = execDataIn.readShort();
                formulaPara[1] = execDataIn.readShort();
                formulaPara[2] = execDataIn.readShort();
                MainCanvas.mc.baseForm.setAboutForm(null);
                break;
        }
    }

    public static byte[] compress(int commID) {
        int i, index;
        ByteArray execDataOut = new ByteArray();
        switch (commID) {
            case 234881536:
                execDataOut.writeByte(MainCanvas.mc.typeID);
                break;
            case 234881792:
                execDataOut.writeLong(MainCanvas.mc.auctionId[MainCanvas.mc.tblAuction
                        .getCurrentPointer()]);
                execDataOut.writeInt(MainCanvas.mc.texts[4].getNumber());
                break;
            case 234881280:
                execDataOut.writeByte(MainCanvas.auctionIndex);
                execDataOut.writeByte(MainCanvas.mc.mImages[0].getNumber());
                execDataOut.writeInt(MainCanvas.mc.texts[0].getNumber());
                execDataOut.writeInt(MainCanvas.mc.texts[1].getNumber());
                i = MainCanvas.mc.rbs[0].getChooseItemIndex();
                execDataOut.writeByte(TIME_PERSISTANCE[i]);
                break;
            case 234882304:
                execDataOut.writeByte(MainCanvas.mc.typeID);
                execDataOut.writeByte(pageDirect);
                break;
            case 234882560:
                index = MainCanvas.mc.tblAuction.getCurrentPointer();
                execDataOut.writeLong(MainCanvas.mc.auctionId[index]);
                break;
            case 234882816:
                execDataOut.writeLong(MainCanvas.mc.auctionId[MainCanvas.mc.tblAuction
                        .getCurrentPointer()]);
                break;
        }
        return execDataOut.toByteArray();
    }

    private static int getPage(int aNum) {
        int page = 0;
        page = aNum / 7;
        if (aNum % 7 != 0) {
            page++;
        }
        return page;
    }

    public static String getTime(int minutes) {
        String s = null;
        for (int i = 0; i < TIME_PERSISTANCE.length; i++) {
            if (minutes < TIME_PERSISTANCE[i] * 60) {
                s = items[i];
                break;
            }
        }
        if (s == null) {
            s = items[items.length - 1];
        }
        return s;
    }

    public static int getAuctionPrice() {
        int time = TIME_PERSISTANCE[MainCanvas.mc.rbs[0].getChooseItemIndex()];
        int result = 0;
        result += formulaPara[0] * time * time;
        result += formulaPara[1] * time;
        result += formulaPara[2];
        return result;
    }
}
