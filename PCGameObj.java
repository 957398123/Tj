
public class PCGameObj {

    public static boolean isPK = false;
    public static byte isRock = 0;
    public static GameObj PEObj = null;
    public static int targetId = 0;
    public static byte pkResponse = 0;

    public static void parse(int commID, byte[] data) {
        String hexID = Integer.toHexString(commID);
        System.out.println("[handle command]:id:0x" + hexID + ",tyepe:PCGameObj");
        int objID, reliveID;
        GameObj obj;
        int neighborID;
        byte type;
        GameObj neighbor = null;
        byte objType, transferType;
        int movingNeighborID, bufferState, targetX, bufferStatePlayer, targetY, i, bufferStateMonster;
        byte b1;
        int attackerID;
        byte attackerDirection;
        int defenderID;
        byte attackSkillID;
        GameObj attacker, defender;
        short atkHpForDisplay;
        int id, buffer, xp, tempWeapon, maxXp;
        byte level;
        int maxHp, maxMp;
        GameObj op;
        byte sign, tpb;
        StringBuffer sb;
        GameObj tmpNpc;
        int pkId;
        OtherPlayer tp;
        int pkObjID_1, pkObjID_2;
        ByteArray byteArray = new ByteArray(data);
        // 玩家对象
        Player player = Player.getInstance();
        // 其他玩家对象
        OtherPlayer otherPlayer;
        switch (commID) {
            case 0x2000480: {
                neighborID = byteArray.readInt();
                type = byteArray.readByte();
                if (MainCanvas.selectedManId == neighborID && type == 3) {
                    MainCanvas.cancelBusiness();
                    MainCanvas.mc.setGameState((byte) 0);
                    MainCanvas.mc.releaseUI();
                    PCChat.addChatScreen((byte) 7, "您选择的玩家已经离开您的视野");
                    UIGameRun.releaseChat();
                }
                ObjManager.getInstance().delObj(neighborID, type);
                break;
            }
            case 0x2000380: {  // 增加游戏对象
                objType = byteArray.readByte();
                switch (objType) {
                    case 1: {   //如果是其他玩家
                        transferType = 0;
                        neighbor = new OtherPlayer();
                        neighbor.init(byteArray);
                        bufferState = byteArray.readInt();
                        neighbor.setBuffer(bufferState);
                        neighbor.setState((byte) 0);
                        try {
                            neighbor.isReincarnation = byteArray.readByte();
                            transferType = (byte) byteArray.readInt();
                            changeTransferStatus((OtherPlayer) neighbor, transferType, false);
                            neighbor.isEnemy = (byteArray.readByte() == 1);
                            ((OtherPlayer) neighbor).setTitle(byteArray.readUTF());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    case 2: {  // 如果是怪物
                        neighbor = new Monster();
                        neighbor.init(byteArray);
                        bufferStateMonster = byteArray.readInt();
                        neighbor.setBuffer(bufferStateMonster);
                        neighbor.setState((byte) 0);
                        break;
                    }
                    case 3: {  // 如果是NPC
                        neighbor = new NPC();
                        neighbor.init(byteArray);
                        neighbor.setState((byte) 0);
                        break;
                    }
                    case 4: {  // 如果是玩家本身
                        transferType = 0;
                        neighbor = Player.getInstance();
                        neighbor.currentImgID = -1;
                        neighbor.init(byteArray);
                        bufferStatePlayer = byteArray.readInt();
                        for (i = 0; i < Player.skillLevels.length; i++) {
                            Player.skillLevels[i] = byteArray.readByte();
                        }
                        neighbor.setBuffer(bufferStatePlayer);
                        neighbor.setState((byte) 0);
                        if (!MainCanvas.isLogin) {
                            MainCanvas.isLogin = true;
                            ObjManager.addObj(neighbor);
                        }
                        (Player.getInstance()).isSendMoveMsg = true;
                        MainCanvas.chatFriendId = (Player.getInstance()).objID;
                        if (Cons.audioOpen) {
                            switch (Map.currentMapID) {
                                case 1:
                                case 4:
                                case 6:
                                case 10:
                                    if (Util.getRandom(10) % 2 == 0) {
                                        MainCanvas.sound.initSoleSound("/hum1.mid", "audio/midi");
                                        MainCanvas.sound.playSoleSound(1, true);
                                        break;
                                    }
                                    MainCanvas.sound.initSoleSound("/hum2.mid", "audio/midi");
                                    MainCanvas.sound.playSoleSound(1, true);
                                    break;
                                case 16:
                                case 18:
                                case 22:
                                case 93:
                                    if (Util.getRandom(10) % 2 == 0) {
                                        MainCanvas.sound.initSoleSound("/evil1.mid", "audio/midi");
                                        MainCanvas.sound.playSoleSound(1, true);
                                        break;
                                    }
                                    MainCanvas.sound.initSoleSound("/evil2.mid", "audio/midi");
                                    MainCanvas.sound.playSoleSound(1, true);
                                    break;
                            }
                        }
                        try {
                            neighbor.isReincarnation = byteArray.readByte();
                            transferType = (byte) byteArray.readInt();
                            changeTransferStatus(Player.getInstance(), transferType, true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    case 5: {  //如果是其它物品
                        neighbor = new Collection();
                        neighbor.init(byteArray);
                        break;
                    }
                }
                ObjManager.addObj(neighbor);
                break;
            }
            case 0x2000180: {
                movingNeighborID = byteArray.readInt();
                // 获取当前移动对象
                GameObj movingObj = ObjManager.getObj(movingNeighborID);
                // 目标X坐标
                targetX = byteArray.readInt();
                // 目标Y坐标
                targetY = byteArray.readInt();
                // 状态
                b1 = (byte) byteArray.readInt();
                // 是否敌对关系
                boolean isEnemy = byteArray.readByte() == 1;
                // 称号
                String tilte = byteArray.readUTF();
                // 名称
                String name = "???";
                if (movingObj != null) {
                    name = movingObj.name;
                }
                int aimRow = Map.getCurrentRow(targetY, targetX);
                int aimCol = Map.getCurrentColumn(targetY, targetX);
                System.out.println("[receive command]:command=0x" + hexID + ",class=PCGameObj,type=moveobj,gameobj=0x" + Integer.toHexString(movingNeighborID) + ",name=" + name + ",target=(" + aimCol + "," + aimRow + "),status=" + b1 + ",enemy=" + isEnemy + ",tilte=" + tilte);
                if (movingNeighborID == player.objID) {
                    changeTransferStatus(player, b1, true);
                } else if (movingObj != null && movingObj.type == 1) {  //如果已经加入并且是其它玩家
                    changeTransferStatus((OtherPlayer) movingObj, b1, true);
                }
                // 如果移动的不是玩家自身
                if (movingNeighborID != player.objID) {
                    boolean flag = false;
                    // 如果未找到move对象
                    if (movingObj == null) {
                        // 查找是都是团队成员
                        for (int j = 0; j < MainCanvas.mc.teamMates.size(); j++) {
                            if (((GameObj) MainCanvas.mc.teamMates.elementAt(j)).objID == movingNeighborID) {
                                movingObj = (GameObj) MainCanvas.mc.teamMates.elementAt(j);
                                flag = true;
                                break;
                            }
                        }
                        if (movingObj == null) {
                            return;
                        }
                    }
                    // 根据坐标设置目标单元格
                    if (targetY == -1 && targetX == -1) {
                        movingObj.aimRow = -1;
                        movingObj.aimColumn = -1;
                    } else {
                        movingObj.aimRow = aimRow;
                        movingObj.aimColumn = aimCol;
                    }
                    // 如果是团队成员，直接瞬移
                    if (flag) {
                        movingObj.row = movingObj.aimRow;
                        movingObj.col = movingObj.aimColumn;
                    }
                    // 如果不是玩家自身，更新状态
                    if (movingNeighborID != player.objID) {
                        movingObj.setState((byte) 1);
                    }
                    // 如果移动的是寻径目标，更新玩家寻径坐标
                    if (player.followAimID == movingObj.objID) {
                        if (movingObj.aimRow != -1 || movingObj.aimColumn != -1) {
                            player.setAimColRow(movingObj.aimColumn, movingObj.aimRow);
                        }
                    }
                    // 设置移动对象是否是敌人
                    movingObj.isEnemy = isEnemy;
                    if (ObjManager.currentTarget == movingObj) {
                        ObjManager.getInstance().setCurrentTarget(movingObj);
                    }
                    // 设置称号
                    movingObj.setTitle(tilte);
                }
                break;
            }
            case 0x2000580: {
                attackerID = byteArray.readInt();
                byteArray.readInt();
                byteArray.readInt();
                attackerDirection = byteArray.readByte();
                defenderID = byteArray.readInt();
                attackSkillID = byteArray.readByte();
                attacker = ObjManager.getObj(attackerID);
                if (attacker == null) {
                    return;
                }
                defender = ObjManager.getObj(defenderID);
                if (defender == null) {
                    return;
                }
                if (attackSkillID == 0) {
                    if (attacker == Player.getInstance() && attacker
                            .getState() == 7) {
                        break;
                    }
                    attacker.useSkill(defender, attackSkillID, attackerDirection);
                    break;
                }
                if (attackerID != (Player.getInstance()).objID) {
                    attacker.useSkill(defender, attackSkillID, attackerDirection);
                    if (attacker.type != 2) {
                        short[] tmpSkillData = Player.getSkillData(attacker.profession, attackSkillID);
                        attacker.curMp -= tmpSkillData[3];
                    }
                }
                break;
            }
            case 0x2000680: {
                objID = byteArray.readInt();
                obj = ObjManager.getObj(objID);
                if (obj == null) {
                    byteArray.readInt();
                    byteArray.readInt();
                    byteArray.readInt();
                    byteArray.readInt();
                    byteArray.readShort();
                    byteArray.readInt();
                    byteArray.readByte();
                    break;
                }
                obj.maxHp = byteArray.readInt();
                obj.curHp = byteArray.readInt();
                obj.maxMp = byteArray.readInt();
                obj.curMp = byteArray.readInt();
                atkHpForDisplay = byteArray.readShort();
                buffer = byteArray.readInt();
                tempWeapon = byteArray.readByte();
                obj.isBaoJi = (byteArray.readByte() > 0);
                obj.tickHpChange(atkHpForDisplay);
                obj.setBuffer(buffer);
                obj.bufferStateFlag = buffer;
                if (obj.currentImgID == -1) {
                    obj.setCurWeapon(tempWeapon);
                }
                break;
            }
            case 0x2000C80: {
                reliveID = byteArray.readInt();
                obj = ObjManager.getObj(reliveID);
                (Player.getInstance()).isSendMoveMsg = true;
                if (obj == null) {
                    if (reliveID == -1 && MainCanvas.mc.baseForm != null) {
                        MainCanvas.mc.baseForm.setAboutForm(null);
                    }
                    byteArray.readInt();
                    byteArray.readInt();
                    break;
                }
                obj.x = byteArray.readInt();
                obj.y = byteArray.readInt();
                obj.setState((byte) 0);
                changeTransferStatus((OtherPlayer) obj, obj.forDeadRockImgID, true);
                obj.forDeadRockImgID = -1;
                if (obj.type == 4) {
                    if (PCChangeMap.isCounterpart) {
                        MainCanvas.mc.setState((byte) 5);
                        MainCanvas.mc.setOtherSubState((byte) 2);
                    }
                    MainCanvas.mc.setGameState((byte) 0);
                }
                break;
            }
            case 0x2000780: {
                objID = byteArray.readInt();
                obj = ObjManager.getObj(objID);
                if (obj != null && (obj.type == 1 || obj.type == 4)) {
                    obj.forDeadRockImgID = obj.currentImgID;
                    changeTransferStatus((OtherPlayer) obj, (byte) -1, true);
                }
                if (obj == null) {
                    break;
                }
                if (obj.type == 4) {
                    byte objRock = byteArray.readByte();
                    System.out.println("objRock : " + objRock);
                    if (objRock == 0) {
                        MainCanvas.isDeadRock = 0;
                    } else if (objRock == 1) {
                        MainCanvas.isDeadRock = 1;
                    } else if (objRock == 2) {
                        MainCanvas.isDeadRock = 2;
                    }
                    MainCanvas.isDeadLoad = false;
                    obj.setState((byte) 5);
                    Player.getInstance().resetAimID();
                    MainCanvas.cancelBusiness();
                    (Player.getInstance()).path = (int[][]) null;
                    MainCanvas.mc.setGameState((byte) 8);
                    MainCanvas.mc.setOtherSubState((byte) 1);
                    break;
                }
                obj.setState((byte) 5);
                break;
            }
            case 0x2000B80: {
                objID = byteArray.readInt();
                obj = ObjManager.getObj(objID);
                if (obj == null) {
                    byteArray.readInt();
                    byteArray.readInt();
                    byteArray.readInt();
                    byteArray.readInt();
                    byteArray.readByte();
                    break;
                }
                obj.curHp = byteArray.readInt();
                obj.maxHp = byteArray.readInt();
                obj.curMp = byteArray.readInt();
                obj.maxMp = byteArray.readInt();
                if (obj.currentImgID == -1) {
                    obj.curWeapon = byteArray.readByte();
                    break;
                }
                byteArray.readByte();
                break;
            }
            case 0x2000980: {
                id = byteArray.readInt();
                xp = byteArray.readInt();
                maxXp = byteArray.readInt();
                level = byteArray.readByte();
                maxHp = byteArray.readInt();
                maxMp = byteArray.readInt();
                if (id == (Player.getInstance()).objID) {
                    (Player.getInstance()).level = level;
                    if (level == 2) {
                        MainCanvas.templevel = 1;
                        MainCanvas.setPop((byte) 1);
                    }
                    if (level == 3) {
                        MainCanvas.templevel = 2;
                        MainCanvas.setPop((byte) 6);
                    }
                    if (Cons.audioOpen) {
                        MainCanvas.sound.playSound(0, 1, true);
                    }
                    Player.setMaxXp(maxXp);
                    Player.setCurrentXp(xp);
                    (Player.getInstance()).maxHp = maxHp;
                    (Player.getInstance()).maxMp = maxMp;
                    Player.levelUp();
                    SpecialManager.getInstance().addSpecial(Player.getInstance(), 40);
                    if (Cons.autoDistribute) {
                        MainCanvas.ni.send(134218496);
                    }
                    break;
                }
                op = ObjManager.getObj(id);
                if (op != null) {
                    SpecialManager.getInstance().addSpecial(op, 40);
                }
                if (op == null) {
                    for (int j = 0; j < MainCanvas.mc.teamMates.size(); j++) {
                        GameObj temp = (GameObj) MainCanvas.mc.teamMates.elementAt(j);
                        if (temp.objID == id) {
                            op = temp;
                            break;
                        }
                    }
                }
                if (op != null) {
                    op.maxExp = maxXp;
                    op.exp = xp;
                    op.level = level;
                }
                break;
            }
            case 0x2000880: {
                sign = byteArray.readByte();
                if (sign == 1 || sign == 2) {
                    int gotExp = byteArray.readInt();
                    int gotMoney = byteArray.readInt();
                    int currecntExp = byteArray.readInt();
                    int maxExp = byteArray.readInt();
                    byte leaveGrid = byteArray.readByte();
                    Player.setCurrentXp(currecntExp);
                    Player.setMaxXp(maxExp);
                    StringBuffer stringBuffer = new StringBuffer();
                    byte itemsNumber = byteArray.readByte();
                    String[] itemNames = new String[itemsNumber];
                    for (int j = 0; j < itemsNumber; j++) {
                        itemNames[j] = byteArray.readUTF().trim();
                    }
                    String ss = byteArray.readUTF().trim();
                    if (ss != null && ss.length() == 0) {
                        byte b;
                        for (b = 0; b < itemsNumber; b = (byte) (b + 1)) {
                            stringBuffer.append(itemNames[b]);
                            stringBuffer.append(" ");
                        }
                        if (gotExp > 0) {
                            stringBuffer.append("经验").append(gotExp).append(" ");
                        } else if (gotExp < 0) {
                            stringBuffer.append("经验").append(gotExp * -1).append(" ");
                        }
                        if (gotMoney > 0) {
                            stringBuffer.append((sign == 1) ? "金钱" : "声望").append(gotMoney).append(" ");
                        }
                        if (stringBuffer.length() > 0 && gotExp >= 0) {
                            stringBuffer.insert(0, "您获得");
                            PCChat.addChatScreen((byte) 7, stringBuffer.toString());
                            UIGameRun.releaseChat();
                        } else if (stringBuffer.length() > 0 && gotExp < 0) {
                            stringBuffer.insert(0, "存入葫芦");
                            PCChat.addChatScreen((byte) 7, stringBuffer.toString());
                        }
                        if (leaveGrid <= 5) {
                            stringBuffer.delete(0, stringBuffer.length());
                            if (leaveGrid == 0) {
                                stringBuffer.append("背包已满!!");
                            } else {
                                stringBuffer.append("背包剩").append(leaveGrid).append("个空格，及时清理");
                            }
                            PCChat.addChatScreen((byte) 7, stringBuffer.toString());
                            UIGameRun.releaseChat();
                        }
                        break;
                    }
                    stringBuffer.append(ss);
                    PCChat.addChatScreen((byte) 7, stringBuffer.toString());
                    UIGameRun.releaseChat();
                    if (leaveGrid <= 5) {
                        stringBuffer.delete(0, stringBuffer.length());
                        if (leaveGrid == 0) {
                            stringBuffer.append("您的背包已满，请及时清理");
                        } else {
                            stringBuffer.append("您的背包还有").append(leaveGrid).append("个空位置，请及时清理");
                        }
                        PCChat.addChatScreen((byte) 7, stringBuffer.toString());
                        UIGameRun.releaseChat();
                    }
                    break;
                }
                PCChat.addChatScreen((byte) 7, byteArray.readUTF());
                UIGameRun.releaseChat();
                break;
            }
            case 0x2000A80: {
                tpb = byteArray.readByte();
                sb = new StringBuffer();
                sb.append("你的").append(Cons.EQUIP_NAME[tpb]).append("快要损坏了，请修理");
                PCChat.addChatScreen((byte) 7, sb.toString());
                UIGameRun.releaseChat();
                break;
            }
            case 0x2C00280: {
                tmpNpc = ObjManager.getObj(byteArray.readInt());
                if (tmpNpc == null) {
                    return;
                }
                tmpNpc.setEventState(byteArray.readByte());
                break;
            }
            case 0x10000180: {
                pkId = byteArray.readInt();
                tp = (OtherPlayer) ObjManager.getObj(pkId);
                if (tp == null) {
                    break;
                }
                pkResponse = MainCanvas.mc.canPk();
                if (pkResponse == 1) {
                    if (MainCanvas.mc.getLeftMenuSubState() == 4 && MainCanvas.mc
                            .getGameState() == 2) {
                        PCBusiness.isBusy = true;
                        MainCanvas.mc.bsnsOtherID = pkId;
                        MainCanvas.ni.send(218104064);
                        return;
                    }
                    UIForm aboutForm = UIForm.makeAboutForm("pkConfirm", tp.name + "要和你切磋", "同意", "拒绝", 170);
                    MainCanvas.mc.setTopForm(aboutForm);
                    break;
                }
                MainCanvas.ni.send(268435968);
                break;
            }
            case 0x10000280: {
                PCChat.addChatScreen((byte) 7, byteArray.readUTF());
                UIGameRun.releaseChat();
                break;
            }
            case 0x10000380: {
                PCChat.addChatScreen((byte) 7, "切磋开始了");
                isPK = true;
                UIGameRun.releaseChat();
                pkObjID_1 = byteArray.readInt();
                pkObjID_2 = byteArray.readInt();
                if (pkObjID_1 == (Player.getInstance()).objID) {
                    PEObj = ObjManager.getObj(pkObjID_2);
                } else {
                    PEObj = ObjManager.getObj(pkObjID_1);
                }
                Player.getInstance().setPkObj(PEObj);
                PEObj.setPkObj(Player.getInstance());
                ObjManager.getInstance().setCurrentTarget(PEObj);
                break;
            }
            case 0x10000480: {
                MainCanvas.mc.topForm = null;
                isPK = false;
                PCChat.addChatScreen((byte) 7, byteArray.readUTF());
                PCChat.addChat(MainCanvas.chatNowChannel, MainCanvas.chatNowString);
                UIGameRun.releaseChat();
                if ((Player.getInstance()).pkObj == PEObj) {
                    (Player.getInstance()).pkObj.setPkObj(null);
                    Player.getInstance().setPkObj(null);
                }
                PEObj = null;
                break;
            }
        }
    }

    public static byte[] compress(int commID) {
        short[] skillData;
        ByteArray byteArray = new ByteArray();
        Player player = Player.getInstance();
        switch (commID) {
            case Cmd.C_PLAYER_FIGHT_START: {
                // 如果当前选取对象不为空
                if (ObjManager.currentTarget != null) {
                    // 获取技能数据
                    skillData = Player.getSkillData(player.profession, player.skillIndex);
                    int objID = ObjManager.currentTarget.objID;
                    if (skillData[1] == 6) {
                        objID = player.objID;
                    }
                    System.out.println("[send command]:command=C_PLAYER_FIGHT_START,class=PCGameObj,type=fight,fightobj=0x" + Integer.toHexString(objID) + ",skillIndex=" + player.skillIndex);
                    byteArray.writeInt(objID);
                    byteArray.writeInt(player.x);
                    byteArray.writeInt(player.y);
                    byteArray.writeByte(player.direction);
                    byteArray.writeByte(player.skillIndex);
                    player.skillCDUse(player, player.skillIndex);
                    if (player.skillIndex == 11 && (player.originalImgID == 2 || player.originalImgID == 7)) {
                        MainCanvas.mc.setGameState((byte) 8);
                        MainCanvas.mc.setOtherSubState((byte) 3);
                    }
                    if ((Player.getInstance()).skillIndex == 0) {
                        Player.normalAttackCount = 0;
                    }
                }
                break;
            }
            case 33554688:
                byteArray.writeInt((Player.getInstance()).x);
                byteArray.writeInt((Player.getInstance()).y);
                break;
            case 33557760:
                byteArray.writeByte((Player.getInstance()).skillIndex);
                break;
            case 268435712:
                byteArray.writeInt(targetId);
                break;
            case 268435968:
                byteArray.writeByte(pkResponse);
                break;
            case 33557888:
                System.out.println("isRock : " + isRock);
                byteArray.writeByte(isRock);
                break;
        }
        return byteArray.toByteArray();
    }

    public static void changeTransferStatus(OtherPlayer obj, byte newstatus, boolean special) {
        if (newstatus == -1) {
            if (obj.currentImgID != newstatus) {
                if (special && obj.currentImgID != newstatus) {
                    SpecialManager.getInstance().addSpecial(obj, 21);
                }
                obj.currentImgID = -1;
                obj.imgID = obj.originalImgID;
                obj.setCurWeapon(obj.originalWeapon);
                obj.initEditorRes(obj.imgID);
            }
        } else if (newstatus < 8) {
            if (special && obj.currentImgID != newstatus) {
                SpecialManager.getInstance().addSpecial(obj, 21);
            }
            obj.currentImgID = newstatus;
            obj.imgID = obj.currentImgID;
            switch (obj.imgID) {
                case 0:
                case 1:
                case 5:
                case 6:
                    obj.setCurWeapon(1);
                    break;
                case 2:
                case 7:
                    obj.setCurWeapon(10);
                    break;
                case 3:
                case 4:
                    obj.setCurWeapon(13);
                    break;
            }
            obj.initEditorRes(obj.imgID);
        } else {
            if (special && obj.currentImgID != newstatus) {
                SpecialManager.getInstance().addSpecial(obj, 21);
            }
            obj.currentImgID = newstatus;
            obj.imgID = obj.currentImgID;
            obj.setCurWeapon(-1);
            obj.initEditorRes();
        }
    }
}
