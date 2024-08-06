
public class PCBusiness {

    private static final byte INITATIVE = 1;
    private static final byte PASSIVENESS = 2;
    private static final byte BUSY = 3;
    public static boolean isBusy = false;

    public static void parse(int commID, byte[] data) {
        String hexID = Integer.toHexString(commID);
        System.out.println("[处理命令]:命令ID:0x" + hexID + ",命令类型：PCBusiness");
        int i, temp, j;
        byte br = 0;
        byte lastMsg = 0;
        ByteArray execDataIn = new ByteArray(data);
        switch (commID) {
            case 218104192:
                if (MainCanvas.mc.getGameState() == 0) {
                    return;
                }
                br = execDataIn.readByte();
                if (br == 0) {
                    MainCanvas.mc.setBusinessState(100);
                    MainCanvas.mc.releaseUI();
                    break;
                }
                if (br == 1) {
                    PCChat.addChatScreen((byte) 7, "对方正处于交易状态");
                    UIGameRun.releaseChat();
                    MainCanvas.mc.setBusinessState(3);
                    break;
                }
                if (br == 2) {
                    PCChat.addChatScreen((byte) 7, "对方不在线");
                    UIGameRun.releaseChat();
                    MainCanvas.mc.setBusinessState(3);
                    break;
                }
                if (br == 3) {
                    PCChat.addChatScreen((byte) 7, "对方拒绝交易");
                    UIGameRun.releaseChat();
                    MainCanvas.mc.setBusinessState(3);
                    break;
                }
                if (br == 4) {
                    PCChat.addChatScreen((byte) 7, "对方在规定时间内没有响应");
                    UIGameRun.releaseChat();
                    MainCanvas.mc.setBusinessState(3);
                    break;
                }
                if (br == 5) {
                    PCChat.addChatScreen((byte) 7, "对方超出范围");
                    UIGameRun.releaseChat();
                    MainCanvas.mc.setBusinessState(3);
                    break;
                }
                if (br == 6) {
                    PCChat.addChatScreen((byte) 7, "对方忙");
                    UIGameRun.releaseChat();
                    MainCanvas.mc.setBusinessState(3);
                }
                break;
            case 218104448:
                if (MainCanvas.mc.subBsnsState == 2 || MainCanvas.mc.subBsnsState == 100) {
                    MainCanvas.ni.send(218104832);
                    MainCanvas.mc.baseForm.setAboutForm(null);
                    MainCanvas.mc.subBsnsState = -1;
                    MainCanvas.mc.setGameState((byte) 0);
                    MainCanvas.mc.releaseUI();
                    return;
                }
                MainCanvas.mc.isOtherLock = true;
                MainCanvas.mc.bznsOtherMoney = execDataIn.readInt();
                MainCanvas.mc.texts[1].setLabel(String.valueOf(MainCanvas.mc.bznsOtherMoney));
                for (i = 0; i < 6; i++) {
                    (MainCanvas.mc.grids[1]).stuffId[i] = 100;
                    (MainCanvas.mc.grids[1]).stuffImageId[i] = execDataIn.readByte();
                    (MainCanvas.mc.grids[1]).stuffNumber[i] = execDataIn.readByte();
                    execDataIn.readUTF();
                }
                MainCanvas.mc.mImages[1].setCurrentFrame((byte) 0);
                break;
            case 218105216:
                MainCanvas.mc.isOtherLock = false;
                MainCanvas.mc.mImages[1].setCurrentFrame((byte) 0);
                MainCanvas.mc.texts[1].setLabel(String.valueOf(0));
                temp = (MainCanvas.mc.grids[1]).stuffId.length;
                for (j = 0; j < temp; j++) {
                    (MainCanvas.mc.grids[1]).stuffId[j] = 0;
                }
                break;
            case 218104704:
                lastMsg = execDataIn.readByte();
                if (lastMsg == 1) {
                    PCChat.addChatScreen((byte) 7, "交易成功");
                    UIGameRun.releaseChat();
                    MainCanvas.mc.setBusinessState(3);
                    break;
                }
                if (lastMsg == 0) {
                    MainCanvas.mc.setBusinessState(3);
                    break;
                }
                if (lastMsg == 2) {
                    MainCanvas.mc.setBusinessState(3);
                    break;
                }
                if (lastMsg == 3) {
                    PCChat.addChatScreen((byte) 7, "交易失败");
                    UIGameRun.releaseChat();
                    MainCanvas.mc.setBusinessState(3);
                    MainCanvas.mc.topForm = null;
                    break;
                }
                if (lastMsg == 4) {
                    if (MainCanvas.mc.baseForm.getCurrentFocusForm() != MainCanvas.mc.baseForm) {
                        MainCanvas.mc.baseForm.setAboutForm(null);
                    }
                    MainCanvas.mc.baseForm.addAboutForm("exit", "你的背包剩余空间不足，交易被取消", (byte) 1, MainCanvas.screenW - 30, 0);
                    break;
                }
                if (lastMsg == 5) {
                    if (MainCanvas.mc.baseForm.getCurrentFocusForm() != MainCanvas.mc.baseForm) {
                        MainCanvas.mc.baseForm.setAboutForm(null);
                    }
                    MainCanvas.mc.baseForm.addAboutForm("exit", "对方背包剩余空间不足，交易被取消", (byte) 1, MainCanvas.screenW - 30, 0);
                    break;
                }
                if (lastMsg == 6) {
                    if (MainCanvas.mc.getLeftMenuSubState() == 4 && MainCanvas.mc
                            .getGameState() == 2) {
                        PCChat.addChatScreen((byte) 7, "交易超时");
                        UIGameRun.releaseChat();
                        MainCanvas.mc.setBusinessState(3);
                    }
                    break;
                }
                if (lastMsg == 7) {
                    if (MainCanvas.mc.baseForm == null) {
                        PCChat.addChatScreen((byte) 7, "合法性验证失败，交易取消");
                        break;
                    }
                    if (MainCanvas.mc.baseForm.getCurrentFocusForm() != MainCanvas.mc.baseForm) {
                        MainCanvas.mc.baseForm.setAboutForm(null);
                    }
                    MainCanvas.mc.baseForm.addAboutForm("exit", "合法性验证失败，交易取消", (byte) 1, MainCanvas.screenW - 30, 0);
                }
                break;
            case 218104960:
                if (MainCanvas.mc.subBsnsState == 2 || MainCanvas.mc.subBsnsState == 100) {
                    MainCanvas.ni.send(218104832);
                    MainCanvas.mc.baseForm.setAboutForm(null);
                    MainCanvas.mc.subBsnsState = -1;
                    MainCanvas.mc.setGameState((byte) 0);
                    MainCanvas.mc.releaseUI();
                    return;
                }
                MainCanvas.mc.bsnsOtherID = execDataIn.readInt();
                MainCanvas.mc.bsnsOtherName = execDataIn.readUTF();
                if (MainCanvas.mc.baseForm != null || MainCanvas.mc.topForm != null) {
                    isBusy = true;
                    MainCanvas.ni.send(218104064);
                    return;
                }
                MainCanvas.mc.bsnsIsInitiate = false;
                MainCanvas.mc.setGameState((byte) 2);
                MainCanvas.mc.releaseUI();
                MainCanvas.mc.setLeftMenuSubState(4);
                MainCanvas.mc.setBusinessState(4);
                MainCanvas.mc.bsOtherImID = (ObjManager.getObj(MainCanvas.mc.bsnsOtherID)).originalImgID;
                break;
        }
    }

    public static byte[] compress(int commID) {
        int i;
        ByteArray execDataOut = new ByteArray();
        switch (commID) {
            case 218104064:
                if (isBusy) {
                    execDataOut.writeInt(MainCanvas.mc.bsnsOtherID);
                    execDataOut.writeByte((byte) 3);
                    isBusy = false;
                    break;
                }
                if (MainCanvas.mc.bsnsIsInitiate) {
                    execDataOut.writeInt(MainCanvas.selectManId);
                    execDataOut.writeByte((byte) 1);
                    MainCanvas.mc.bsnsOtherName = ObjManager.currentTarget.name;
                    break;
                }
                execDataOut.writeInt(MainCanvas.mc.bsnsOtherID);
                execDataOut.writeByte((byte) 2);
                break;
            case 218104832:
                MainCanvas.mc.setBusinessState(3);
                break;
            case 218104320:
                execDataOut.writeInt(MainCanvas.mc.bznsMyMoney);
                for (i = 0; i < MainCanvas.mc.bsnsMyPackage.length; i++) {
                    execDataOut.writeByte(MainCanvas.mc.bsnsMyPackage[i]);
                    if (MainCanvas.mc.bsnsMyPackage[i] == 100) {
                        MainCanvas.mc.bsnsSinalNumber[i] = 0;
                    }
                    execDataOut.writeByte(MainCanvas.mc.bsnsSinalNumber[i]);
                }
                MainCanvas.mc.isLock = true;
                break;
            case 218105088:
                if (MainCanvas.mc.grids[1] != null) {
                    execDataOut.writeByte(MainCanvas.lookStuffPlace);
                }
                break;
        }
        return execDataOut.toByteArray();
    }
}
