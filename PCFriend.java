
import java.util.Vector;

public class PCFriend {

    public static byte gplus = 0;
    private static PCFriend instance;
    public static int leaderId = 0;
    public static String sendMessage = "";
    public static byte addTeamMateKind = 0;
    public static byte pullTeamerCount = 0;
    public static Vector tempFriendsList = new Vector();

    public static PCFriend getInstance() {
        if (instance == null) {
            instance = new PCFriend();
        }
        return instance;
    }

    public static void parse(int commID, byte[] data) {
        String hexID = Integer.toHexString(commID);
        System.out.println("[处理命令]:命令ID:0x" + hexID + ",命令类型：PCFriend");
        byte tempResult1, tempResult2, tempFriendCnt;
        int i, onlineCnt;
        byte tempBlackCnt;
        int k, startIndex, j;
        byte tempResult3;
        String[][] tmpfriendList;
        byte teamTemp;
        int[] tmpfriendListID;
        byte num;
        int m, id;
        byte state;
        int iCount, n;
        byte type;
        String tmps;
        ByteArray execDataIn = new ByteArray(data);
        switch (commID) {
            case 201329536:
                try {
                    PCIncrementService.sendMark = execDataIn.readByte();
                } catch (Exception e) {
                    e.toString();
                }
                if (PCIncrementService.sendMark == 1) {
                    MainCanvas.ni.send(201328384);
                } else if (PCIncrementService.sendMark == 2) {
                    MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm()
                            .setAboutForm(null);
                    MainCanvas.mc.baseForm.getCurrentFocusForm()
                            .addAboutForm("sendgplus", "如果您账户余额不足，系统将自动为您补足金额。", (byte) 2, 160, 0);
                }
                PCIncrementService.sendMark = 0;
                break;
            case 201326976:
                tempResult1 = execDataIn.readByte();
                if (tempResult1 == 1) {
                    int lg;
                    int i1;
                    switch (MainCanvas.friendAddType) {
                        case 0:
                            MainCanvas.mc.tables[0]
                                    .deleteItem(MainCanvas.friendSelectWhich);
                            lg = MainCanvas.blackListID.length;
                            for (i1 = MainCanvas.friendSelectWhich; i1 < lg - 1; i1++) {
                                MainCanvas.blackListID[i1] = MainCanvas.blackListID[i1 + 1];
                            }
                            MainCanvas.blackListID[lg - 1] = 0;
                            MainCanvas.mc.baseForm.setAboutForm(null);
                            break;
                        case 1:
                            MainCanvas.setMessage(MainCanvas.mc.baseForm, "添加好友成功！");
                            break;
                        case 2:
                            MainCanvas.mc.baseForm.setAboutForm(null);
                            MainCanvas.setMessage(MainCanvas.mc.baseForm, "添加好友成功！");
                            break;
                        case 3:
                            PCChat.addChatScreen((byte) 7, "添加好友成功");
                            UIGameRun.releaseChat();
                            break;
                        case 4:
                            MainCanvas.mc.baseForm.getSubForm().setAboutForm(null);
                            MainCanvas.mc.baseForm.getSubForm().addAboutForm("addFriend", "添加好友成功", (byte) 1, 210, 0);
                            break;
                    }
                    break;
                }
                if (tempResult1 == 0) {
                    switch (MainCanvas.friendAddType) {
                        case 0:
                        case 1:
                        case 2:
                            MainCanvas.mc.baseForm.setAboutForm(null);
                            MainCanvas.mc.baseForm.addAboutForm("msg", "添加好友失败", (byte) 1, MainCanvas.screenW - 20, 0);
                            break;
                        case 3:
                            PCChat.addChatScreen((byte) 7, "添加好友失败");
                            UIGameRun.releaseChat();
                            break;
                        case 4:
                            MainCanvas.mc.baseForm.getSubForm().setAboutForm(null);
                            MainCanvas.mc.baseForm.getSubForm().addAboutForm("addFriend", "添加好友失败", (byte) 1, 210, 0);
                            break;
                    }
                }
                break;
            case 201327232:
                tempResult2 = execDataIn.readByte();
                if (tempResult2 == 1) {
                    MainCanvas.mc.tables[0]
                            .deleteItem(MainCanvas.friendSelectWhich);
                    int lg = MainCanvas.friendListID.length;
                    for (int i1 = MainCanvas.friendSelectWhich; i1 < lg - 1; i1++) {
                        MainCanvas.friendListID[i1] = MainCanvas.friendListID[i1 + 1];
                        for (int i2 = 0; i2 < (MainCanvas.friendList[i1]).length; i2++) {
                            MainCanvas.friendList[i1][i2] = MainCanvas.friendList[i1 + 1][i2];
                        }
                    }
                    MainCanvas.friendListID[lg - 1] = 0;
                    MainCanvas.mc.baseForm.setAboutForm(null);
                    break;
                }
                if (tempResult2 == 0) {
                    MainCanvas.mc.baseForm.setAboutForm(null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "添加失败，请重试");
                }
                break;
            case 201327488:
                MainCanvas.requestFriendPlace = execDataIn.readByte();
                tempFriendCnt = execDataIn.readByte();
                MainCanvas.mc.offset = 0;
                MainCanvas.friendList = (String[][]) null;
                MainCanvas.friendListID = null;
                MainCanvas.friendList = new String[tempFriendCnt][9];
                MainCanvas.friendListID = new int[tempFriendCnt];
                for (i = 0; i < tempFriendCnt; i++) {
                    MainCanvas.friendListID[i] = execDataIn.readInt();
                    MainCanvas.friendList[i][0] = execDataIn.readUTF();
                    MainCanvas.friendList[i][1] = (execDataIn.readByte() == 0) ? "离线" : "在线";
                    MainCanvas.friendList[i][4] = execDataIn.readUTF();
                    MainCanvas.friendList[i][5] = execDataIn.readUTF();
                    if (MainCanvas.friendList[i][5].length() == 0) {
                        MainCanvas.friendList[i][5] = "无氏族";
                    }
                    MainCanvas.friendList[i][2] = Cons.STR_PLAYERS[execDataIn
                            .readByte()];
                    MainCanvas.friendList[i][3] = execDataIn.readByte() + "级";
                }
                for (i = 0; i < tempFriendCnt; i++) {
                    try {
                        MainCanvas.friendList[i][6] = execDataIn.readUTF();
                        MainCanvas.friendList[i][7] = (execDataIn.readByte() == 0) ? "开通" : "取消";
                        MainCanvas.friendList[i][8] = execDataIn.readUTF();
                    } catch (Exception exception) {
                    }
                }
                switch (MainCanvas.requestFriendPlace) {
                    case 0:
                        MainCanvas.mc
                                .setRightMenuSubState(3);
                        MainCanvas.mc.setUISocietyState((byte) 2);
                        MainCanvas.mc.releaseUI();
                        break;
                    case 1:
                    case 2:
                        if (tempFriendCnt != 0) {
                            byte count = (byte) MainCanvas.friendListID.length;
                            MainCanvas.mc.tables[1] = new UITable(0, 50, 100, 136, count, 1, (count > 10) ? 10 : count, (byte) 0, (byte) 3);
                            MainCanvas.mc.tables[1].setAutoHeight(true);
                            int color = 0;
                            for (int i1 = 0; i1 < count; i1++) {
                                if (MainCanvas.friendList[i1][1].equals("在线")) {
                                    color = 10981736;
                                } else {
                                    color = 8092539;
                                }
                                MainCanvas.mc.tables[1].addItem(MainCanvas.friendList[i1][0] + "  " + MainCanvas.friendList[i1][2], color);
                            }
                            UIForm subForm = new UIForm(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, "friends");
                            subForm.setBackGround((byte) 9);
                            MainCanvas.mc.tables[1].setAutoWidth();
                            subForm.addComponentInCenter(MainCanvas.mc.tables[1], (byte) 4);
                            MainCanvas.mc.baseForm.getCurrentFocusForm()
                                    .getParentForm().setAboutForm(null);
                            MainCanvas.mc.baseForm.getCurrentFocusForm().setAboutForm(subForm);
                            break;
                        }
                        MainCanvas.mc.baseForm.getCurrentFocusForm()
                                .getParentForm().addAboutForm("nofriends", "您还没有好友:)", (byte) 1, MainCanvas.screenW, 30);
                        break;
                    case 3:
                        PCArena.getInstance().selectReceiver((byte) 3);
                        break;
                    case 4:
                        if (tempFriendCnt == 0) {
                            MainCanvas.setMessage(MainCanvas.mc.baseForm, "您还没有好友:)");
                            MainCanvas.mc.transferstatus = false;
                            break;
                        }
                        onlineCnt = 0;
                        for (k = 0; k < tempFriendCnt; k++) {
                            if ("在线".equals(MainCanvas.friendList[k][1])) {
                                onlineCnt++;
                            }
                        }
                        if (onlineCnt == 0) {
                            MainCanvas.setMessage(MainCanvas.mc.baseForm, "当前没有好友在线，请稍后再试");
                            MainCanvas.mc.transferstatus = false;
                            break;
                        }
                        startIndex = 0;
                        tmpfriendList = (String[][]) null;
                        tmpfriendListID = null;
                        tmpfriendList = new String[onlineCnt][9];
                        tmpfriendListID = new int[onlineCnt];
                        for (m = 0; m < tempFriendCnt; m++) {
                            if ("在线".equals(MainCanvas.friendList[m][1])) {
                                tmpfriendListID[startIndex] = MainCanvas.friendListID[m];
                                tmpfriendList[startIndex][0] = MainCanvas.friendList[m][0];
                                tmpfriendList[startIndex][1] = MainCanvas.friendList[m][1];
                                tmpfriendList[startIndex][4] = MainCanvas.friendList[m][4];
                                tmpfriendList[startIndex][5] = MainCanvas.friendList[m][5];
                                tmpfriendList[startIndex][2] = MainCanvas.friendList[m][2];
                                tmpfriendList[startIndex][3] = MainCanvas.friendList[m][3];
                                tmpfriendList[startIndex][6] = MainCanvas.friendList[m][6];
                                tmpfriendList[startIndex][7] = MainCanvas.friendList[m][7];
                                tmpfriendList[startIndex][8] = MainCanvas.friendList[m][8];
                                startIndex++;
                            }
                        }
                        MainCanvas.friendList = tmpfriendList;
                        MainCanvas.friendListID = tmpfriendListID;
                        MainCanvas.mc
                                .setRightMenuSubState(3);
                        MainCanvas.mc
                                .setUISocietyState((byte) 2);
                        MainCanvas.mc.releaseUI();
                        break;
                    case 6:
                    case 7:
                    case 8:
                        if (tempFriendCnt == 0) {
                            MainCanvas.setMessage(MainCanvas.mc.baseForm, "您还没有好友:)");
                            break;
                        }
                        MainCanvas.mc.setRightMenuSubState(3);
                        MainCanvas.mc.setUISocietyState((byte) 2);
                        MainCanvas.mc.releaseUI();
                        break;
                }
                break;
            case 201327744:
                tempBlackCnt = execDataIn.readByte();
                MainCanvas.blackList = (String[][]) null;
                MainCanvas.blackListID = null;
                MainCanvas.blackList = new String[tempBlackCnt][4];
                MainCanvas.blackListID = new int[tempBlackCnt];
                for (j = 0; j < tempBlackCnt; j++) {
                    MainCanvas.blackListID[j] = execDataIn.readInt();
                    MainCanvas.blackList[j][0] = execDataIn.readUTF();
                    MainCanvas.blackList[j][1] = (execDataIn.readByte() == 0) ? "离线" : "在线";
                    MainCanvas.blackList[j][2] = Cons.STR_PLAYERS[execDataIn
                            .readByte()];
                    MainCanvas.blackList[j][3] = "" + execDataIn.readByte() + "级";
                }
                MainCanvas.mc
                        .setRightMenuSubState(3);
                MainCanvas.mc.setUISocietyState((byte) 3);
                MainCanvas.mc.releaseUI();
                break;
            case 201328000:
                tempResult3 = execDataIn.readByte();
                if (tempResult3 == 1) {
                    MainCanvas.mc.tables[0]
                            .deleteItem(MainCanvas.friendSelectWhich);
                    if (MainCanvas.deleteFriendType == 0) {
                        int lg = MainCanvas.friendListID.length;
                        for (int i1 = MainCanvas.friendSelectWhich; i1 < lg - 1; i1++) {
                            MainCanvas.friendListID[i1] = MainCanvas.friendListID[i1 + 1];
                            for (int i2 = 0; i2 < (MainCanvas.friendList[i1]).length; i2++) {
                                MainCanvas.friendList[i1][i2] = MainCanvas.friendList[i1 + 1][i2];
                            }
                        }
                        MainCanvas.friendListID[lg - 1] = 0;
                    } else {
                        int lg = MainCanvas.blackListID.length;
                        for (int i1 = MainCanvas.friendSelectWhich; i1 < lg - 1; i1++) {
                            MainCanvas.blackListID[i1] = MainCanvas.blackListID[i1 + 1];
                        }
                        MainCanvas.blackListID[lg - 1] = 0;
                    }
                    MainCanvas.mc.offset = (byte) (MainCanvas.mc.offset + 1);
                    MainCanvas.mc.baseForm.setAboutForm(null);
                    break;
                }
                if (tempResult3 == 0) {
                    MainCanvas.mc.baseForm.setAboutForm(null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "删除失败");
                }
                break;
            case 100663680:
                teamTemp = MainCanvas.mc.teamJob;
                MainCanvas.mc.teamJob = execDataIn.readByte();
                num = execDataIn.readByte();
                if (num == 0) {
                    byte flag = execDataIn.readByte();
                    String str = "";
                    switch (flag) {
                        case 3:
                            str = "队伍人数已满!";
                            break;
                        case 4:
                            str = execDataIn.readUTF() + "拒绝和你组队!";
                            break;
                        case 5:
                            str = "玩家不在线!";
                            break;
                        case 6:
                            str = "玩家在其它队伍中!";
                            break;
                        case 7:
                            str = "你不是队长!";
                            break;
                        case 8:
                            str = "对方正在忙!";
                            break;
                        case 9:
                            str = "对方不是队长！";
                            break;
                        case 10:
                            str = "参加氏族战争后不能再加新的队员";
                            break;
                        case 11:
                            str = "已经在队伍中";
                            break;
                        case 12:
                            str = execDataIn.readUTF();
                            break;
                        default:
                            str = "操作失败!";
                            break;
                    }
                    MainCanvas.mc.teamJob = teamTemp;
                    PCChat.addChatScreen((byte) 7, str);
                    UIGameRun.releaseChat();
                    pullTeamerCount = (byte) (pullTeamerCount - 1);
                    break;
                }
                if (num == -1) {
                    execDataIn.readByte();
                    int i1 = leaderId;
                    leaderId = execDataIn.readInt();
                    String name = execDataIn.readUTF();
                    byte flag = MainCanvas.mc.canGroup();
                    if (flag == 1) {
                        if (MainCanvas.mc.getLeftMenuSubState() == 4 && MainCanvas.mc
                                .getGameState() == 2) {
                            PCBusiness.isBusy = true;
                            MainCanvas.mc.bsnsOtherID = leaderId;
                            MainCanvas.ni.send(218104064);
                            return;
                        }
                        UIForm aboutForm = UIForm.makeAboutForm("topConfirm", name + "要和你组队，同意吗？", "同意", "拒绝", 170);
                        MainCanvas.mc.setTopForm(aboutForm);
                    } else {
                        MainCanvas.mc.teamAutoConfirm = flag;
                        MainCanvas.ni.send(100663552);
                        leaderId = i1;
                    }
                } else {
                    for (m = 0; m < num; m++) {
                        byte job = execDataIn.readByte();
                        byte race = execDataIn.readByte();
                        byte profession = execDataIn.readByte();
                        byte gender = execDataIn.readByte();
                        int i1 = execDataIn.readInt();
                        String name = execDataIn.readUTF();
                        byte level = execDataIn.readByte();
                        int curHp = execDataIn.readInt();
                        int curMp = execDataIn.readInt();
                        int maxHp = execDataIn.readInt();
                        int maxMp = execDataIn.readInt();
                        int aimRow = execDataIn.readInt();
                        int aimColumn = execDataIn.readInt();
                        if (job == 1) {
                            MainCanvas.mc.teamLeaderId = i1;
                        } else if (MainCanvas.mc.teamLeaderId == 0) {
                            MainCanvas.mc.teamLeaderId = (Player.getInstance()).objID;
                        }
                        GameObj op = ObjManager.getObj(i1);
                        if (op == null || !(op instanceof OtherPlayer)) {
                            op = new OtherPlayer();
                            op.race = race;
                            op.gender = gender;
                            op.profession = profession;
                            op.objID = i1;
                            op.name = name;
                            op.level = level;
                            op.curHp = curHp;
                            op.curMp = curMp;
                            op.maxHp = maxHp;
                            op.maxMp = maxMp;
                            op.aimRow = Map.getCurrentRow(aimColumn, aimRow);
                            op.aimColumn = Map.getCurrentColumn(aimColumn, aimRow);
                            op.row = op.aimRow;
                            op.col = op.aimColumn;
                            if (op.race == 1 && op.profession == 4 && op.gender == 1) {
                                op.imgID = 0;
                            }
                            if (op.race == 1 && op.profession == 1 && op.gender == 2) {
                                op.imgID = 1;
                            }
                            if (op.race == 2 && op.profession == 2 && op.gender == 1) {
                                op.imgID = 2;
                            }
                            if (op.race == 2 && op.profession == 3 && op.gender == 2) {
                                op.imgID = 3;
                            }
                            if (op.race == 3 && op.profession == 3 && op.gender == 1) {
                                op.imgID = 4;
                            }
                            if (op.race == 3 && op.profession == 4 && op.gender == 2) {
                                op.imgID = 5;
                            }
                            if (op.race == 4 && op.profession == 1 && op.gender == 1) {
                                op.imgID = 6;
                            }
                            if (op.race == 4 && op.profession == 2 && op.gender == 2) {
                                op.imgID = 7;
                            }
                            op.originalImgID = op.imgID;
                        }
                        MainCanvas.mc.teamMates.addElement(op);
                    }
                    if (MainCanvas.mc.teamJob == 1) {
                        PCChat.addChatScreen((byte) 7, "组队成功!");
                        UIGameRun.releaseChat();
                    }
                }
                MainCanvas.mc.teamLeaderOperat = 0;
                break;
            case 100663936:
                if (MainCanvas.mc.baseForm != null && "waiting"
                        .equals(MainCanvas.mc.baseForm
                        .getCurrentFocusForm().getName())) {
                    MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm()
                            .setAboutForm(null);
                }
                id = execDataIn.readInt();
                if (id == -1) {
                    if (MainCanvas.mc.baseForm != null && "waiting"
                            .equals(MainCanvas.mc.baseForm
                            .getCurrentFocusForm().getName())) {
                        MainCanvas.mc.baseForm.setAboutForm(null);
                        MainCanvas.mc.baseForm.addAboutForm("message", "操作失败!", (byte) 1, 160, 50);
                    } else {
                        PCChat.addChatScreen((byte) 7, "参加氏族战争后不能退出队伍！");
                        if (MainCanvas.mc.baseForm != null) {
                            MainCanvas.mc.baseForm.setAboutForm(null);
                        }
                    }
                } else if (id == -2) {
                    PCChat.addChatScreen((byte) 7, "竞技场和准备区不能退出队伍！");
                    if (MainCanvas.mc.baseForm != null) {
                        MainCanvas.mc.baseForm.setAboutForm(null);
                    }
                } else if (id == (Player.getInstance()).objID) {
                    MainCanvas.mc.teamMates.removeAllElements();
                    MainCanvas.mc.teamJob = 0;
                    MainCanvas.mc.teamLeaderId = 0;
                    pullTeamerCount = 0;
                } else {
                    for (int i1 = 0; i1 < MainCanvas.mc.teamMates.size(); i1++) {
                        if (((GameObj) MainCanvas.mc.teamMates.elementAt(i1)).objID == id) {
                            MainCanvas.mc.teamMates.removeElementAt(i1);
                            if (MainCanvas.mc.baseForm != null && "waiting"
                                    .equals(MainCanvas.mc.baseForm
                                    .getCurrentFocusForm().getName()) && MainCanvas.mc
                                    .getGameState() == 1 && MainCanvas.mc.teamLeaderId
                                    == (Player.getInstance()).objID) {
                                MainCanvas.mc.baseForm.setAboutForm(null);
                                MainCanvas.mc.baseForm.addAboutForm("message", "操作成功!", (byte) 1, 160, 50);
                            }
                            pullTeamerCount = (byte) (pullTeamerCount - 1);
                            break;
                        }
                    }
                    if (MainCanvas.mc.teamMates.size() == 0) {
                        MainCanvas.mc.teamJob = 0;
                        MainCanvas.mc.teamLeaderId = 0;
                        pullTeamerCount = 0;
                    }
                }
                MainCanvas.mc.teamLeaderOperat = 0;
                break;
            case 100664192:
                MainCanvas.mc.teamLeaderId = execDataIn.readInt();
                state = 1;
                state = execDataIn.readByte();
                if (MainCanvas.mc.teamLeaderId != 0 && state == 1) {
                    if (MainCanvas.mc.baseForm != null && "waiting"
                            .equals(MainCanvas.mc.baseForm
                            .getCurrentFocusForm().getName()) && MainCanvas.mc
                            .getGameState() == 1) {
                        MainCanvas.mc.baseForm.setAboutForm(null);
                        MainCanvas.mc.baseForm.addAboutForm("message", "操作成功!", (byte) 1, 160, 50);
                    }
                    if (MainCanvas.mc.teamLeaderId == (Player.getInstance()).objID) {
                        MainCanvas.mc.teamJob = 1;
                    } else {
                        MainCanvas.mc.teamJob = 2;
                    }
                } else if (MainCanvas.mc.baseForm != null && "waiting"
                        .equals(MainCanvas.mc.baseForm
                        .getCurrentFocusForm().getName())) {
                    String message = "";
                    if (state == 2) {
                        message = "你不是队长!";
                    } else if (state == 3) {
                        message = "玩家不在队伍";
                    } else {
                        message = "操作失败!";
                    }
                    MainCanvas.mc.baseForm.setAboutForm(null);
                    MainCanvas.mc.baseForm.addAboutForm("message", message, (byte) 1, 160, 50);
                }
                MainCanvas.mc.teamLeaderOperat = 0;
                break;
            case 201328256:
                iCount = execDataIn.readInt();
                for (n = 0; n < iCount; n++) {
                    tempFriendsList.addElement(new Integer(execDataIn.readInt()));
                    tempFriendsList.addElement(execDataIn.readUTF());
                    tempFriendsList.addElement(Cons.STR_PLAYERS[execDataIn
                            .readByte()]);
                }
                MainCanvas.mc
                        .setRightMenuSubState(3);
                MainCanvas.mc.setUISocietyState((byte) 0);
                MainCanvas.mc.releaseUI();
                break;
            case 201328512:
                MainCanvas.mc.baseForm.getCurrentFocusForm().getParentForm()
                        .setAboutForm(null);
                MainCanvas.mc.mcount = 0;
                type = execDataIn.readByte();
                tmps = "";
                switch (type) {
                    case 0:
                        tmps = "操作成功！";
                        break;
                    case 1:
                        tmps = "操作失败！";
                        break;
                }
                if (MainCanvas.mc.isSelectList == 2) {
                    MainCanvas.mc.baseForm.getCurrentFocusForm().addAboutForm("remsg", tmps, (byte) 1, 140, 0);
                    break;
                }
                MainCanvas.setMessage(MainCanvas.mc.baseForm, tmps);
                break;
        }
    }
    public static String friendName = null;

    public static byte[] compress(int commID) {
        int targetId;
        ByteArray execDataOut = new ByteArray();
        switch (commID) {
            case 201326848:
                targetId = 0;
                switch (MainCanvas.friendAddType) {
                    case 0:
                        execDataOut.writeByte((byte) 0);
                        targetId = MainCanvas.blackListID[MainCanvas.friendSelectWhich];
                        execDataOut.writeInt(targetId);
                        break;
                    case 1:
                        execDataOut.writeByte((byte) 1);
                        targetId = ObjManager.currentTarget.objID;
                        execDataOut.writeByte((byte) 0);
                        execDataOut.writeInt(targetId);
                        break;
                    case 2:
                        execDataOut.writeByte((byte) 1);
                        targetId = MainCanvas.friendListID[MainCanvas.friendSelectWhich];
                        execDataOut.writeByte((byte) 0);
                        execDataOut.writeInt(targetId);
                        break;
                    case 3:
                        execDataOut.writeByte((byte) 1);
                        execDataOut.writeByte((byte) 1);
                        execDataOut.writeUTF(friendName);
                        break;
                    case 4:
                        execDataOut.writeByte((byte) 1);
                        execDataOut.writeByte((byte) 1);
                        execDataOut.writeUTF(friendName);
                        break;
                }
                break;
            case 201327104:
                execDataOut
                        .writeInt(MainCanvas.friendListID[MainCanvas.friendSelectWhich]);
                break;
            case Cmd.C_FRIEND_LISTFRIEND: {  // 查询好友列表
                execDataOut.writeByte(MainCanvas.requestFriendPlace);
                break;
            }
            case 201327872:
                execDataOut.writeByte(MainCanvas.deleteFriendType);
                if (MainCanvas.deleteFriendType == 0) {
                    execDataOut
                            .writeInt(MainCanvas.friendListID[MainCanvas.friendSelectWhich]);
                    break;
                }
                execDataOut
                        .writeInt(MainCanvas.blackListID[MainCanvas.friendSelectWhich]);
                break;
            case 100663552:
                execDataOut.writeByte(MainCanvas.mc.teamAutoConfirm);
                execDataOut.writeInt(leaderId);
                break;
            case 100663808:
                if (MainCanvas.mc.teamLeaderOperat == 0) {
                    break;
                }
                if (addTeamMateKind == 0 && MainCanvas.mc.teamTargetId == 0) {
                    break;
                }
                if (addTeamMateKind == 1 && (MainCanvas.mc.TeamTargetName == null || MainCanvas.mc.TeamTargetName
                        .trim().equals(""))) {
                    break;
                }
                pullTeamerCount = (byte) (pullTeamerCount + 1);
                execDataOut.writeByte(MainCanvas.mc.teamLeaderOperat);
                execDataOut.writeByte(addTeamMateKind);
                if (addTeamMateKind == 0) {
                    execDataOut.writeInt(MainCanvas.mc.teamTargetId);
                    break;
                }
                execDataOut.writeUTF(MainCanvas.mc.TeamTargetName);
                break;
            case 100664064:
                execDataOut.writeByte(MainCanvas.mc.teamLeaderOperat);
                break;
            case 201328384:
                execDataOut.writeByte(gplus);
                execDataOut.writeByte((MainCanvas.mc.isSelectList == 2) ? 2 : 1);
                execDataOut.writeByte(MainCanvas.mc.mcount);
                execDataOut.writeUTF(sendMessage);
                if (MainCanvas.mc.mcount > 0) {
                    for (int i = 0; i < MainCanvas.mc.mcount; i++) {
                        targetId = MainCanvas.SENDID[i];
                        execDataOut.writeInt(targetId);
                    }
                    break;
                }
                targetId = MainCanvas.friendListID[MainCanvas.friendSelectWhich];
                execDataOut.writeInt(targetId);
                break;
        }
        return execDataOut.toByteArray();
    }
}
