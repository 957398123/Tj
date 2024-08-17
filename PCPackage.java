
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class PCPackage {

    public static short itemID = 0;

    public static void parse(int commID, byte[] _data) {
        String hexID = Integer.toHexString(commID);
        System.out.println("[处理命令]:命令ID:0x" + hexID + ",命令类型：PCPackage");
        byte playerlv = (Player.getInstance()).level;
        byte itemlv = 0;
        try {
            byte i, j, splitbefore, tm, splitAfter;
            short sId;
            byte tempByte, smId;
            int tempHP;
            String sName;
            int tempMP;
            byte nameColorLevel;
            StringBuffer sb;
            short sType;
            String message;
            UIMImage[] starmg;
            int k;
            byte reincarnationType, b1;
            int tempMoney;
            UIForm subForm;
            String msg;
            byte tempPlace;
            UIRim rim;
            UITable table0;
            UIMImage mg;
            UILabel label1, label2;
            byte bb;
            StringBuffer info;
            byte baoshi, kong;
            int offset, d;
            String pro;
            byte[] bindSign;
            String[] baseAttributeName;
            byte length;
            short[] baseAttribute;
            byte b3, b2;
            String bewrite;
            byte chansi, samsara;
            UITextArea betExplain;
            byte b5, b4, mid, b7, b6, leave, business;
            short tmShort;
            byte item;
            String tss;
            int m;
            byte b8, tmp;
            Image img;
            Graphics gg;
            UIRim rim0, rim1, rim2;
            UILabel[] label;
            byte tp;
            String ss;
            short[] ts;
            int n;
            String str;
            int i2, i1, p[][];
            byte ic[], b9, sign;
            int deltaH;
            byte bt;
            UIMImage[] um;
            int i3;
            byte number, b10;
            int color;
            StringBuffer title;
            byte imgId;
            int i4, temp1;
            byte pnum;
            int temp2;
            byte b11;
            int tick;
            ByteArray execDataIn = new ByteArray(_data);
            switch (commID) {
                case 67109248:
                    MainCanvas.dramatisPackage = null;
                    MainCanvas.dramatisPackage = new UIGrid(0, 113, (byte) 4, (byte) 9, (byte) 4, MainCanvas.mImgStuff);
                    for (i = 0; i < 36; i = (byte) (i + 1)) {
                        MainCanvas.dramatisPackage.setGridDetail(i, execDataIn
                                .readShort(), execDataIn.readByte(), execDataIn
                                .readByte(), execDataIn.readUTF(), execDataIn
                                .readByte(), execDataIn.readShort(), execDataIn
                                .readByte(), execDataIn.readByte());
                        MainCanvas.dramatisPackage.setCanUse(i, true);
                    }
                    (Player.getInstance()).money = execDataIn.readInt();
                    (Player.getInstance()).money = ((Player.getInstance()).money > 1999999999) ? 1999999999 : (Player.getInstance()).money;
                    if (MainCanvas.mc.getGameState() == 2
                            && MainCanvas.mc.getLeftMenuSubState() == 4) {
                        MainCanvas.mc
                                .setBusinessState(0);
                        MainCanvas.mc.releaseUI();
                    }
                    switch (MainCanvas.packageSend) {
                        case 1:
                            MainCanvas.ni.send(67111168);
                            MainCanvas.ni.send(152043520);
                            break;
                        case 2:
                            MainCanvas.ni.send(160432128);
                            break;
                        case 3:
                            MainCanvas.ni.send(161480704);
                            break;
                        case 4:
                            MainCanvas.ni.send(67111168);
                            MainCanvas.ni.send(164691968);
                            break;
                        case 5:
                            MainCanvas.ni.send(67111168);
                            MainCanvas.ni.send(251670528);
                            break;
                        case 6:
                            MainCanvas.ni.send(50333184);
                            break;
                        case 7:
                            MainCanvas.ni.send(234882048);
                            break;
                    }
                    MainCanvas.packageSend = 0;
                    break;
                case 67109504:
                    MainCanvas.dramatisPackage = null;
                    MainCanvas.dramatisPackage = new UIGrid(0, 113, (byte) 4, (byte) 9, (byte) 4, MainCanvas.mImgStuff);
                    for (i = 0; i < 36; i = (byte) (i + 1)) {
                        MainCanvas.dramatisPackage.setGridDetail(i, execDataIn
                                .readShort(), execDataIn.readByte(), execDataIn
                                .readByte(), execDataIn.readUTF(), execDataIn
                                .readByte(), execDataIn.readShort(), execDataIn
                                .readByte(), execDataIn.readByte());
                    }
                    for (j = 0; j < 8; j = (byte) (j + 1)) {
                        MainCanvas.equipStuffId[j] = execDataIn.readShort();
                        MainCanvas.equipImageId[j] = execDataIn.readByte();
                        MainCanvas.equipSruffName[j] = execDataIn.readUTF();
                        MainCanvas.equipStuffNameLevel[j] = execDataIn.readByte();
                        MainCanvas.equipStuffType[j] = execDataIn.readShort();
                    }
                    (Player.getInstance()).money = execDataIn.readInt();
                    (Player.getInstance()).money = ((Player.getInstance()).money > 1999999999) ? 1999999999 : (Player.getInstance()).money;
                    if (Player.itemCount > 0) {
                        byte b;
                        for (b = 35; b >= 0; b = (byte) (b - 1)) {
                            if (MainCanvas.dramatisPackage.stuffType[b] == 221 || MainCanvas.dramatisPackage.stuffType[b] == 222 || MainCanvas.dramatisPackage.stuffType[b] == 223) {
                                MainCanvas.dramatisPackage.setCanUse(b, false);
                            }
                        }
                    }
                    MainCanvas.mc.setGameState((byte) 1);
                    MainCanvas.mc
                            .setRightMenuSubState(2);
                    MainCanvas.mc.releaseUI();
                    break;
                case 67109760:
                    switch (execDataIn.readByte()) {
                        case 0:
                            MainCanvas.mc.baseForm.setAboutForm(null);
                            MainCanvas.setMessage(MainCanvas.mc.baseForm, execDataIn.readUTF());
                            break;
                        case 1:
                            MainCanvas.dramatisPackage.changeStuff((short) MainCanvas.dramatisPackage.stuffPlace,
                                    (short) MainCanvas.dramatisPackage.getCurrentPointer());
                            MainCanvas.mc.baseForm.setAboutForm(null);
                            MainCanvas.dramatisPackage.isLock = false;
                            MainCanvas.stuffName
                                    .setColor(Cons.STUFF_NAME_COLOR[MainCanvas.dramatisPackage
                                    .getCurrentNameLevel()]);
                            MainCanvas.stuffName.setStr(MainCanvas.dramatisPackage
                                    .getCurrentName());
                            if (Player.itemCount > 0) {
                                byte b;
                                for (b = 35; b >= 0; b = (byte) (b - 1)) {
                                    if (MainCanvas.dramatisPackage.stuffType[b] == 221 || MainCanvas.dramatisPackage.stuffType[b] == 222 || MainCanvas.dramatisPackage.stuffType[b] == 223) {
                                        MainCanvas.dramatisPackage.setCanUse(b, false);
                                    } else {
                                        MainCanvas.dramatisPackage.setCanUse(b, true);
                                    }
                                }
                            }
                            break;
                        case 2:
                            splitbefore = execDataIn.readByte();
                            splitAfter = execDataIn.readByte();
                            MainCanvas.dramatisPackage.splitStuff((short) MainCanvas.dramatisPackage.stuffPlace,
                                    (short) MainCanvas.dramatisPackage.getCurrentPointer(), splitbefore, splitAfter);
                            MainCanvas.mc.baseForm.setAboutForm(null);
                            MainCanvas.dramatisPackage.isLock = false;
                            if (MainCanvas.stuffName != null) {
                                MainCanvas.stuffName.setColor(Cons.STUFF_NAME_COLOR[MainCanvas.dramatisPackage.getCurrentNameLevel()]);
                                MainCanvas.stuffName.setStr(MainCanvas.dramatisPackage.getCurrentName());
                            } else {
                                MainCanvas.mc.labels[2].setColor(Cons.STUFF_NAME_COLOR[MainCanvas.dramatisPackage.getCurrentNameLevel()]);
                                MainCanvas.mc.labels[2].setStr(MainCanvas.dramatisPackage.getCurrentName());
                            }
                            if (Player.itemCount > 0) {
                                byte b;
                                for (b = 35; b >= 0; b = (byte) (b - 1)) {
                                    if (MainCanvas.dramatisPackage.stuffType[b] == 221 || MainCanvas.dramatisPackage.stuffType[b] == 222 || MainCanvas.dramatisPackage.stuffType[b] == 223) {
                                        MainCanvas.dramatisPackage.setCanUse(b, false);
                                    } else {
                                        MainCanvas.dramatisPackage.setCanUse(b, true);
                                    }
                                }
                            }
                            break;
                    }
                    break;
                case 67110016:
                    tm = execDataIn.readByte();
                    switch (tm) {
                        case 0:
                            MainCanvas.mc.baseForm.setAboutForm(null);
                            sId = 0;
                            smId = 0;
                            sName = null;
                            nameColorLevel = 0;
                            sType = 0;
                            sId = MainCanvas.dramatisPackage.getCurrentId();
                            smId = MainCanvas.dramatisPackage.getCurrentImageId();
                            sName = MainCanvas.dramatisPackage.getCurrentName();
                            nameColorLevel = MainCanvas.dramatisPackage.getCurrentNameLevel();
                            sType = MainCanvas.dramatisPackage.getCurrentLittleType();
                            if (MainCanvas.equipStuffId[MainCanvas.mc.equipPlace] == 0) {
                                MainCanvas.dramatisPackage.setCurrentNull();
                                MainCanvas.stuffName.setStr("  ");
                            } else {
                                MainCanvas.dramatisPackage
                                        .setGridDetail(MainCanvas.dramatisPackage
                                        .getCurrentPointer(), MainCanvas.equipStuffId[MainCanvas.mc.equipPlace], MainCanvas.equipImageId[MainCanvas.mc.equipPlace], (byte) 0, MainCanvas.equipSruffName[MainCanvas.mc.equipPlace], MainCanvas.equipStuffNameLevel[MainCanvas.mc.equipPlace], MainCanvas.equipStuffType[MainCanvas.mc.equipPlace], (byte) 0, (byte) 0);
                            }
                            if (MainCanvas.dramatisPackage.getCurrentId() > 0) {
                                MainCanvas.dramatisPackage.stuffNumber[MainCanvas.dramatisPackage
                                        .getCurrentPointer()] = 1;
                            }
                            MainCanvas.equipStuffId[MainCanvas.mc.equipPlace] = sId;
                            MainCanvas.equipImageId[MainCanvas.mc.equipPlace] = smId;
                            MainCanvas.equipSruffName[MainCanvas.mc.equipPlace] = sName;
                            MainCanvas.equipStuffNameLevel[MainCanvas.mc.equipPlace] = nameColorLevel;
                            MainCanvas.equipStuffType[MainCanvas.mc.equipPlace] = sType;
                            for (k = 0; k < 8; k++) {
                                (MainCanvas.mc.mImages[k]).isLock = false;
                            }
                            MainCanvas.mc.mImages[6].setAroundComponent(MainCanvas.mc.mImages[2], (byte) 3);
                            MainCanvas.mc.mImages[7].setAroundComponent(MainCanvas.mc.mImages[5], (byte) 4);
                            MainCanvas.mc.baseForm
                                    .setComponentFocus(MainCanvas.dramatisPackage);
                            MainCanvas.dramatisPackage.isLock = false;
                            MainCanvas.stuffName
                                    .setColor(Cons.STUFF_NAME_COLOR[MainCanvas.dramatisPackage
                                    .getCurrentNameLevel()]);
                            MainCanvas.stuffName.setStr(MainCanvas.dramatisPackage
                                    .getCurrentName());
                            break;
                        case 1:
                            reincarnationType = 0;
                            try {
                                reincarnationType = execDataIn.readByte();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            msg = "装备失败";
                            if (reincarnationType == 1) {
                                msg = "您需要转生后才能装备";
                            }
                            MainCanvas.mc.baseForm.setAboutForm(null);
                            MainCanvas.setMessage(MainCanvas.mc.baseForm, msg);
                            MainCanvas.mc.baseForm
                                    .setComponentFocus(MainCanvas.dramatisPackage);
                            MainCanvas.dramatisPackage.isLock = false;
                            break;
                        case 2:
                            MainCanvas.mc.baseForm.setAboutForm(null);
                            MainCanvas.setMessage(MainCanvas.mc.baseForm, "您的职业无法装备此装备");
                            MainCanvas.mc.baseForm
                                    .setComponentFocus(MainCanvas.dramatisPackage);
                            MainCanvas.dramatisPackage.isLock = false;
                            break;
                        case 3:
                            MainCanvas.mc.baseForm.setAboutForm(null);
                            MainCanvas.setMessage(MainCanvas.mc.baseForm, "您的等级还不够");
                            MainCanvas.mc.baseForm
                                    .setComponentFocus(MainCanvas.dramatisPackage);
                            MainCanvas.dramatisPackage.isLock = false;
                            break;
                    }
                    break;
                case 67110272:
                    tempByte = execDataIn.readByte();
                    tempHP = execDataIn.readInt();
                    tempMP = execDataIn.readInt();
                    sb = new StringBuffer();
                    if (MainCanvas.useStuffPlace == 0) {
                        return;
                    }
                    switch (tempByte) {
                        case 0:
                            MainCanvas.mc.baseForm.setAboutForm(null);
                            break;
                        case 18:
                            switch (tempHP) {
                                case 0:
                                    PCGameObj.changeTransferStatus(Player.getInstance(), (byte) tempMP, true);
                                    MainCanvas.mc.releaseUI();
                                    MainCanvas.mc.setGameState((byte) 0);
                                    break;
                            }
                            MainCanvas.setMessage(MainCanvas.mc.baseForm, "使用失败");
                            break;
                        case 14:
                            if (tempHP == 1) {
                                MainCanvas.dramatisPackage.setCurrentNull();
                                MainCanvas.stuffName.setStr(" ");
                            }
                            return;
                        case 13:
                            switch (tempHP) {
                                case 0:
                                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "开启成功");
                                    break;
                                case 1:
                                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "开启失败");
                                    break;
                                case 2:
                                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "关闭成功");
                                    break;
                            }
                            break;
                        case 10:
                            switch (tempHP) {
                                case 0:
                                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "使用成功");
                                    MainCanvas.dramatisPackage.setCurrentNull();
                                    MainCanvas.stuffName.setStr(" ");
                                    break;
                                case 1:
                                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "您还没有加入氏族，使用退族卡失败");
                                    break;
                                case 2:
                                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "您是族长不能退出氏族，使用退族卡失败");
                                    break;
                            }
                            break;
                        case 11:
                            switch (tempHP) {
                                case 0:
                                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "使用成功");
                                    MainCanvas.dramatisPackage.setCurrentNull();
                                    MainCanvas.stuffName.setStr(" ");
                                    break;
                                case 1:
                                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "背包内剩余空位不足，大礼包无法打开");
                                    break;
                                case 2:
                                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "您的级别已经达到上限，无法使用");
                                    break;
                                case 3:
                                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "您尚未达到可以使用该物品的等级");
                                    break;
                                case 4:
                                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "无法使用转生道具：你今世机缘未到，需转生至来世方能使用此道具。");
                                    break;
                            }
                            MainCanvas.setMessage(MainCanvas.mc.baseForm, "使用失败");
                            break;
                        case 12:
                            message = "";
                            switch (tempHP) {
                                case 0:
                                    MainCanvas.mc.transferstatus = false;
                                    MainCanvas.mc.friendTransferTarget = 0;
                                    MainCanvas.mc.transferstatusQuest = false;
                                    MainCanvas.mc.questTargetID = 0;
                                    MainCanvas.mc.questTargetPlace = -1;
                                    MainCanvas.mc.questStoneStatus = -1;
                                    MainCanvas.mc.questNoPlace = false;
                                    return;
                                case 1:
                                    message = "您的等级不足，不能进入该好友所在地图，或该好友所在地图不在传送范围。";
                                    break;
                                case 2:
                                    message = "您的好友已经离线，不能传送";
                                    break;
                                case 3:
                                    message = "选中任务没有做任务的地图，不能传送";
                                    MainCanvas.mc.questNoPlace = true;
                                    break;
                                case 4:
                                    message = "任务传送石使用次数耗尽，请充值后再使用";
                                    MainCanvas.mc.transferstatusQuest = false;
                                    MainCanvas.mc.questTargetID = 0;
                                    MainCanvas.mc.questTargetPlace = -1;
                                    MainCanvas.mc.questStoneStatus = -1;
                                    break;
                                case 5:
                                    message = "您还有1次传送机会，请及时到增值充值";
                                    break;
                                case 6:
                                    message = "您还没有任务传送石，请先购买传送石";
                                    break;
                                case 7:
                                    message = "目标为特殊地图，不能传送";
                                    break;
                                default:
                                    message = "未知错误，不能传送";
                                    break;
                            }
                            MainCanvas.setMessage(MainCanvas.mc.baseForm, message);
                            break;
                        case 1:
                            if (tempHP != 0) {
                                sb.append("已增加HP").append(String.valueOf(tempHP))
                                        .append("  ");
                                (Player.getInstance()).curHp += tempHP;
                                (Player.getInstance()).curHp = ((Player.getInstance()).curHp > (Player.getInstance()).maxHp) ? (Player.getInstance()).maxHp : (Player.getInstance()).maxHp;
                            } else if ((Player.getInstance()).curHp == (Player.getInstance()).maxHp) {
                                sb.append("HP已满").append("  ");
                            }
                            if (tempMP != 0) {
                                sb.append("已增加MP").append(String.valueOf(tempMP))
                                        .append("  ");
                                (Player.getInstance()).curMp += tempMP;
                                (Player.getInstance()).curMp = ((Player.getInstance()).curMp > (Player.getInstance()).maxMp) ? (Player.getInstance()).maxMp : (Player.getInstance()).maxMp;
                            } else if ((Player.getInstance()).curMp == (Player.getInstance()).maxMp) {
                                sb.append("MP已满");
                            }
                            MainCanvas.mc.baseForm.setMessage(sb.toString(), false);
                            MainCanvas.mc.baseForm.setAboutForm(null);
                            if (MainCanvas.dramatisPackage.getCurrentNumber() == 1) {
                                MainCanvas.dramatisPackage.setCurrentNull();
                                MainCanvas.stuffName.setStr("  ");
                            } else {
                                MainCanvas.dramatisPackage.stuffNumber[MainCanvas.dramatisPackage
                                        .getCurrentPointer()] = (byte) (MainCanvas.dramatisPackage.stuffNumber[MainCanvas.dramatisPackage.getCurrentPointer()] - 1);
                            }
                            for (b1 = 35; b1 >= 0; b1 = (byte) (b1 - 1)) {
                                if (MainCanvas.dramatisPackage.stuffType[b1] == 221 || MainCanvas.dramatisPackage.stuffType[b1] == 222 || MainCanvas.dramatisPackage.stuffType[b1] == 223) {
                                    MainCanvas.dramatisPackage.setCanUse(b1, false);
                                    Player.itemCount = 300;
                                }
                            }
                            break;
                        case 2:
                            sb = null;
                            sb = new StringBuffer();
                            switch (tempHP) {
                                case 1:
                                    sb.append("您的种族不能使用此回城卷轴");
                                    MainCanvas.mc.baseForm.setAboutForm(null);
                                    MainCanvas.mc.baseForm.addAboutForm("msg", sb
                                            .toString(), (byte) 1, 140, 0);
                                    break;
                                case 2:
                                    sb.append("您的等级不够");
                                    MainCanvas.mc.baseForm.setAboutForm(null);
                                    MainCanvas.mc.baseForm.addAboutForm("msg", sb
                                            .toString(), (byte) 1, 140, 0);
                                    break;
                                case 3:
                                    sb.append("您所在的地图不能使用此物品");
                                    MainCanvas.mc.baseForm.setAboutForm(null);
                                    MainCanvas.mc.baseForm.addAboutForm("msg", sb
                                            .toString(), (byte) 1, 140, 0);
                                    break;
                                case 4:
                                    sb.append(execDataIn.readUTF());
                                    MainCanvas.mc.baseForm.setAboutForm(null);
                                    MainCanvas.mc.baseForm.addAboutForm("msg", sb.toString(), (byte) 1, 140, 0);
                                    break;
                            }
                            break;
                        case 3:
                            MainCanvas.mc.baseForm.setAboutForm(null);
                            switch (tempHP) {
                                case 1:
                                    sb.append("恭喜，您的装备已经完成扩孔");
                                    break;
                                case 2:
                                    sb.append("您装备上的附加孔已经抹平");
                                    break;
                                case 3:
                                    sb.append("您已成功为传送石充值20次");
                                    break;
                                default:
                                    sb.append("使用成功");
                                    break;
                            }
                            MainCanvas.mc.baseForm.addAboutForm("msg", sb.toString(), (byte) 1, 140, 0);
                            if (MainCanvas.dramatisPackage.getCurrentId() == 18056 || (MainCanvas.dramatisPackage
                                    .getCurrentLittleType() >= 338 && MainCanvas.dramatisPackage
                                    .getCurrentLittleType() <= 340) || (MainCanvas.dramatisPackage
                                    .getCurrentLittleType() >= 343 && MainCanvas.dramatisPackage
                                    .getCurrentLittleType() <= 347) || MainCanvas.dramatisPackage
                                    .getCurrentLittleType() == 355) {
                                MainCanvas.dramatisPackage.setCurrentNull();
                                MainCanvas.stuffName.setStr(" ");
                                break;
                            }
                            MainCanvas.dramatisPackage.setCurrentBindType((byte) 1);
                            break;
                        case 4:
                            sb = null;
                            sb = new StringBuffer();
                            sb.append("您已经死亡");
                            MainCanvas.mc.baseForm.addAboutForm("msg", sb.toString(), (byte) 1, 140, 0);
                            break;
                        case 5:
                        case 6:
                            if (MainCanvas.dramatisPackage.getCurrentId() == 18025) {
                                for (int i5 = 0; i5 < 9; i5++) {
                                    Player.defineKey(i5, -1);
                                }
                                Util.saveRecord(Player.userDefinedSkills,
                                        (Player.getInstance()).objID + "sc");
                            }
                            MainCanvas.mc.baseForm.addAboutForm("msg", "使用成功", (byte) 1, 140, 0);
                            if (MainCanvas.dramatisPackage.getCurrentNumber() == 1) {
                                MainCanvas.dramatisPackage.setCurrentNull();
                                MainCanvas.stuffName.setStr(" ");
                                break;
                            }
                            MainCanvas.dramatisPackage.stuffNumber[MainCanvas.dramatisPackage
                                    .getCurrentPointer()] = (byte) (MainCanvas.dramatisPackage.stuffNumber[MainCanvas.dramatisPackage.getCurrentPointer()] - 1);
                            break;
                        case 7:
                            MainCanvas.dramatisPackage.setCurrentNull();
                            MainCanvas.stuffName.setStr(" ");
                            tempMoney = execDataIn.readInt();
                            tempPlace = 0;
                            if (tempMoney != 0) {
                                (Player.getInstance()).money += tempMoney;
                                MainCanvas.mc.texts[0].setLabel(String.valueOf(
                                        (Player.getInstance()).money));
                            } else {
                                tempPlace = execDataIn.readByte();
                                MainCanvas.dramatisPackage.setGridDetail(tempPlace, execDataIn
                                        .readShort(), execDataIn.readByte(), execDataIn
                                        .readByte(), execDataIn.readUTF(), execDataIn
                                        .readByte(), execDataIn.readShort(), execDataIn
                                        .readByte(), execDataIn.readByte());
                                if (tempPlace == MainCanvas.dramatisPackage
                                        .getCurrentPointer()) {
                                    MainCanvas.stuffName
                                            .setStr(MainCanvas.dramatisPackage
                                            .getCurrentName());
                                    MainCanvas.stuffName
                                            .setColor(Cons.STUFF_NAME_COLOR[MainCanvas.dramatisPackage
                                            .getCurrentNameLevel()]);
                                }
                            }
                            sb = null;
                            sb = new StringBuffer();
                            sb.append("恭喜您得到");
                            if (tempMoney != 0) {
                                sb.append("金钱：" + tempMoney);
                            } else if (MainCanvas.dramatisPackage.getStuffId()[tempPlace] != 0) {
                                sb
                                        .append(MainCanvas.dramatisPackage
                                        .getStuffName()[tempPlace]);
                            }
                            MainCanvas.setMessage(MainCanvas.mc.baseForm, sb.toString());
                            break;
                        case 8:
                            switch (tempHP) {
                                case 0:
                                    sb.append("同类物品不能重复使用");
                                    break;
                                case 1:
                                    sb.append("您不能给这件装备打过多的孔，请更换适当装备再试");
                                    break;
                                case 2:
                                    sb.append("您当前装备无附加孔，请更换装备后再试");
                                    break;
                                case 3:
                                    sb.append("您当前的荣誉,不足以兑换经验");
                                    break;
                                case 4:
                                    sb.append("您当前的声望,不足以兑换经验");
                                    break;
                                case 5:
                                    sb.append("同名称药剂不能连续使用");
                                    break;
                                case 6:
                                    sb.append("精华凝结尚未冷却");
                                    break;
                            }
                            MainCanvas.mc.baseForm.addAboutForm("msg", sb.toString(), (byte) 1, 140, 0);
                            break;
                        case 9:
                            MainCanvas.mc.baseForm.addAboutForm("msg", "您还没有宠物", (byte) 1, 140, 0);
                            break;
                        case 19:
                            MainCanvas.setMessage(MainCanvas.mc.baseForm, execDataIn.readUTF());
                            if (tempHP == 1) {
                                MainCanvas.dramatisPackage.setCurrentNull();
                                MainCanvas.stuffName.setStr(" ");
                            }
                            break;
                    }
                    break;
                case 67110528:
                    tm = execDataIn.readByte();
                    switch (tm) {
                        case 38:
                            MainCanvas.mc.baseForm.setAboutForm(null);
                            MainCanvas.setMessage(MainCanvas.mc.baseForm, "背包已满，没有空位");
                            break;
                        case 39:
                            MainCanvas.mc.baseForm.setAboutForm(null);
                            MainCanvas.setMessage(MainCanvas.mc.baseForm, "卸载失败，请重试");
                            break;
                    }
                    MainCanvas.mc.baseForm.setAboutForm(null);
                    MainCanvas.dramatisPackage
                            .setGridDetail(tm, MainCanvas.equipStuffId[MainCanvas.mc.equipPlace], MainCanvas.equipImageId[MainCanvas.mc.equipPlace], (byte) 1, MainCanvas.equipSruffName[MainCanvas.mc.equipPlace], MainCanvas.equipStuffNameLevel[MainCanvas.mc.equipPlace], MainCanvas.equipStuffType[MainCanvas.mc.equipPlace], (byte) 0, (byte) 0);
                    MainCanvas.equipStuffId[MainCanvas.mc.equipPlace] = 0;
                    MainCanvas.equipImageId[MainCanvas.mc.equipPlace] = 0;
                    MainCanvas.equipSruffName[MainCanvas.mc.equipPlace] = "";
                    MainCanvas.equipStuffNameLevel[MainCanvas.mc.equipPlace] = 0;
                    MainCanvas.equipStuffType[MainCanvas.mc.equipPlace] = 0;
                    MainCanvas.stuffName.setStr(" ");
                    break;
                case 67111040:
                    MainCanvas.mc.baseForm.setAboutForm(null);
                    if (MainCanvas.mc.isPackage == 1) {
                        MainCanvas.dramatisPackage.setCurrentNull();
                        if (MainCanvas.mc.labels[0] != null && "商店："
                                .equals(MainCanvas.mc.labels[0].getStr()
                                .trim())) {
                            MainCanvas.mc.packageStuffPrice[MainCanvas.dramatisPackage
                                    .getCurrentPointer()] = 0;
                            MainCanvas.mc.texts[0].setLabel("0");
                        }
                        if (MainCanvas.stuffName != null) {
                            MainCanvas.stuffName.setStr(" ");
                        }
                        break;
                    }
                    if (MainCanvas.mc.isPackage == 0) {
                        MainCanvas.equipStuffId[UIMImage.sign] = 0;
                        MainCanvas.equipImageId[UIMImage.sign] = 0;
                        MainCanvas.equipSruffName[UIMImage.sign] = "";
                        MainCanvas.equipStuffNameLevel[UIMImage.sign] = 0;
                        if (MainCanvas.stuffName != null) {
                            MainCanvas.stuffName.setStr(" ");
                        }
                    }
                    break;
                case 67110672:
                    starmg = null;
                    subForm = null;
                    rim = null;
                    table0 = null;
                    mg = null;
                    label1 = null;
                    label2 = null;
                    subForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "detail");
                    subForm.setStyle((byte) 0);
                    subForm.setBackGround((byte) 9);
                    rim = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte) 4);
                    mg = new UIMImage(22, 18, 17, 17, MainCanvas.mImgStuff, (byte) 0);
                    mg.setHaveRim(true);
                    mg.setCanFocus(false);
                    label1 = new UILabel(mg.positionX + mg.width + 8, mg.positionY + 2, MainCanvas.screenW - mg.positionX + mg.width, 0, execDataIn.readUTF(), 47103, (byte) 0, (byte) 0);
                    bb = execDataIn.readByte();
                    if (bb >= Cons.STUFF_NAME_COLOR.length) {
                        bb = 0;
                    }
                    label1.setColor(Cons.STUFF_NAME_COLOR[bb]);
                    mg.setCurrentFrame((byte) (execDataIn.readByte() - 1));
                    label2 = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 0, (byte) 0);
                    table0 = new UITable(5, 40, 180, 40, 5, 2, 5, (byte) 0, (byte) 0);
                    table0.setSingleWH((short) 78, (byte) 14, false);
                    table0.setRim(false);
                    info = new StringBuffer();
                    baoshi = execDataIn.readByte();
                    kong = execDataIn.readByte();
                    offset = 0;
                    if (kong != 0) {
                        table0.setItem("扩孔卡" + baoshi + "/" + kong, 4, 16777215);
                        offset = 1;
                    }
                    info.append("伤害 " + execDataIn.readShort() + "-" + execDataIn
                            .readShort() + "\n");
                    info.append("凹槽 " + execDataIn.readByte() + "/" + execDataIn
                            .readByte() + "\n");
                    info.append("耐久度 " + execDataIn.readInt() + "/" + execDataIn
                            .readInt() + "\n");
                    info.append("攻速 " + execDataIn.readShort() + "\n");
                    itemlv = execDataIn.readByte();
                    table0.setItem("需要等级 " + itemlv, 0, (itemlv > playerlv) ? 16711680 : 16777215);
                    d = execDataIn.readByte();
                    pro = "";
                    if ((d & 0xF) == 15) {
                        pro = "全职业";
                    } else {
                        if ((d & 0x1) != 0) {
                            pro = pro + Cons.STR_PROFESSION[0] + " ";
                        }
                        if ((d & 0x2) != 0) {
                            pro = pro + Cons.STR_PROFESSION[3] + " ";
                        }
                        if ((d & 0x4) != 0) {
                            pro = pro + Cons.STR_PROFESSION[1] + " ";
                        }
                        if ((d & 0x8) != 0) {
                            pro = pro + Cons.STR_PROFESSION[2] + " ";
                        }
                    }
                    table0.setItem("职业 " + pro, 1, 16777215);
                    table0.setItem("精炼等级 " + execDataIn.readByte(), 2, 16777215);
                    bindSign = new byte[2];
                    bindSign[0] = execDataIn.readByte();
                    bindSign[1] = execDataIn.readByte();
                    switch (bindSign[1]) {
                        case 1:
                            table0.setItem((bindSign[0] == 0) ? "拾取绑定" : "已绑定", 3, 16777215);
                            break;
                        case 2:
                            table0.setItem((bindSign[0] == 0) ? "装备绑定" : "已绑定", 3, 16777215);
                            break;
                    }
                    baseAttributeName = new String[]{
                        "烈风 ", "灵火 ", "神雷 ", "风抗 ", "火抗 ", "雷抗 ", "力量 ", "智力 ", "体力 ", "敏捷 ",
                        "幸运 "};
                    length = (byte) baseAttributeName.length;
                    baseAttribute = new short[length];
                    for (b3 = 0; b3 < length; b3 = (byte) (b3 + 1)) {
                        baseAttribute[b3] = execDataIn.readShort();
                    }
                    for (b2 = 0; b2 < length; b2 = (byte) (b2 + 1)) {
                        if (baseAttribute[b2] != 0) {
                            if (baseAttribute[b2] > 0) {
                                info.append(baseAttributeName[b2] + "+" + baseAttribute[b2] + "\n");
                            } else {
                                info.append(baseAttributeName[b2] + baseAttribute[b2] + "\n");
                            }
                        }
                    }
                    bewrite = execDataIn.readUTF();
                    chansi = 0;
                    try {
                        chansi = execDataIn.readByte();
                    } catch (Exception exception) {
                    }
                    switch (chansi) {
                        case 1:
                            table0.setItem("已缠金丝", 5, 16777215);
                            offset = 1;
                            break;
                        case 2:
                            table0.setItem("已缠银丝", 5, 16777215);
                            offset = 1;
                            break;
                    }
                    samsara = 0;
                    try {
                        samsara = execDataIn.readByte();
                    } catch (Exception exception) {
                    }
                    switch (samsara) {
                        case 1:
                            if (offset == 0) {
                                table0.setItem("转生武器", 4, 16777215);
                            } else if (offset == 1) {
                                if (kong != 0) {
                                    table0.setItem("转生武器", 6, 16777215);
                                } else {
                                    table0.setItem("转生武器", 4, 16777215);
                                    offset--;
                                }
                            }
                            offset++;
                            break;
                        case 2:
                            if (offset == 0) {
                                table0.setItem("需要转生", 4, 16711680);
                            } else if (offset == 1) {
                                if (kong != 0) {
                                    table0.setItem("需要转生", 6, 16711680);
                                } else {
                                    table0.setItem("需要转生", 4, 16711680);
                                    offset--;
                                }
                            }
                            offset++;
                            break;
                    }
                    try {
                        byte compose = execDataIn.readByte();
                        if (compose == 1) {
                            info.append("获得熟练度 " + execDataIn.readInt() + "\n");
                            info.append("消耗活力 " + execDataIn.readShort() + "\n");
                            info.append("消耗金钱 " + execDataIn.readInt() + "\n");
                        }
                    } catch (Exception ex) {
                        byte compose = 0;
                    }
                    info.append("\n" + bewrite);
                    betExplain = new UITextArea(table0.positionX, 70 + offset * UIComponent.charH, 160, 113 - offset * UIComponent.charH, info.toString());
                    betExplain.setColor(16316576);
                    subForm.addComponent(rim);
                    subForm.addComponent(mg);
                    subForm.addComponentInCenter(betExplain, (byte) 2);
                    subForm.addComponent(table0);
                    subForm.addComponent(label1);
                    subForm.addComponentInCenter(label2, (byte) 6);
                    try {
                        byte starnum = execDataIn.readByte();
                        if (starnum != 0) {
                            starmg = new UIMImage[5];
                            for (int i5 = 0; i5 < 5; i5++) {
                                starmg[i5] = new UIMImage(MainCanvas.screenW / 2 - 55 + i5 * 10, mg.positionY + 5, 9, 9, MainCanvas.mImgUI[14], (byte) 0);
                                if (i5 < starnum) {
                                    starmg[i5].setCurrentFrame((byte) 1);
                                } else {
                                    starmg[i5].setCurrentFrame((byte) 0);
                                }
                                subForm.addComponent(starmg[i5]);
                            }
                        }
                    } catch (Exception ex) {
                        System.out.println("****error");
                    }
                    if (MainCanvas.mc.getRightMenuSubState() == 4 || MainCanvas.mc
                            .getNPCSubState() == 11) {
                        MainCanvas.mc.baseForm.getSubForm().setAboutForm(subForm);
                        break;
                    }
                    MainCanvas.mc.baseForm.setAboutForm(null);
                    MainCanvas.mc.baseForm.setAboutForm(subForm);
                    break;
                case 67110688:
                    subForm = null;
                    rim = null;
                    table0 = null;
                    mg = null;
                    label1 = null;
                    label2 = null;
                    subForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "detail");
                    subForm.setStyle((byte) 0);
                    subForm.setBackGround((byte) 9);
                    rim = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte) 4);
                    mg = new UIMImage(22, 18, 17, 17, MainCanvas.mImgStuff, (byte) 0);
                    mg.setHaveRim(true);
                    mg.setCanFocus(false);
                    label1 = new UILabel(mg.positionX + mg.width + 8, mg.positionY + 2, MainCanvas.screenW - mg.positionX + mg.width, 0, execDataIn.readUTF(), 47103, (byte) 0, (byte) 0);
                    label1.setColor(Cons.STUFF_NAME_COLOR[execDataIn.readByte()]);
                    mg.setCurrentFrame((byte) (execDataIn.readByte() - 1));
                    label2 = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 0, (byte) 0);
                    table0 = new UITable(5, 40, 166, 40, 5, 2, 5, (byte) 0, (byte) 0);
                    table0.setSingleWH((short) 78, (byte) 14, false);
                    table0.setRim(false);
                    info = new StringBuffer();
                    baoshi = execDataIn.readByte();
                    kong = execDataIn.readByte();
                    offset = 0;
                    if (kong != 0) {
                        table0.setItem("扩孔卡" + baoshi + "/" + kong, 4, 16777215);
                        offset = 1;
                    }
                    info.append("防御 " + execDataIn.readShort() + "\n");
                    info.append("凹槽 " + execDataIn.readByte() + "/" + execDataIn
                            .readByte() + "\n");
                    info.append("耐久度 " + execDataIn.readInt() + "/" + execDataIn
                            .readInt() + "\n");
                    itemlv = execDataIn.readByte();
                    table0.setItem("需要等级 " + itemlv, 0, (itemlv > playerlv) ? 16711680 : 16777215);
                    d = execDataIn.readByte();
                    pro = "";
                    if ((d & 0xF) == 15) {
                        pro = "全职业";
                    } else {
                        if ((d & 0x1) != 0) {
                            pro = pro + Cons.STR_PROFESSION[0] + " ";
                        }
                        if ((d & 0x2) != 0) {
                            pro = pro + Cons.STR_PROFESSION[3] + " ";
                        }
                        if ((d & 0x4) != 0) {
                            pro = pro + Cons.STR_PROFESSION[1] + " ";
                        }
                        if ((d & 0x8) != 0) {
                            pro = pro + Cons.STR_PROFESSION[2] + " ";
                        }
                    }
                    table0.setItem("职业 " + pro, 1, 16777215);
                    table0.setItem("精炼等级 " + execDataIn.readByte(), 2, 16777215);
                    bindSign = new byte[2];
                    bindSign[0] = execDataIn.readByte();
                    bindSign[1] = execDataIn.readByte();
                    switch (bindSign[1]) {
                        case 1:
                            table0.setItem((bindSign[0] == 0) ? "拾取绑定" : "已绑定", 3, 16777215);
                            break;
                        case 2:
                            table0.setItem((bindSign[0] == 0) ? "装备绑定" : "已绑定", 3, 16777215);
                            break;
                    }
                    length = 8;
                    baseAttribute = new short[length];
                    baseAttributeName = new String[]{"火抗 ", "风抗 ", "雷抗 ", "力量 ", "智力 ", "体力 ", "敏捷 ", "幸运 "};
                    for (b5 = 0; b5 < length; b5 = (byte) (b5 + 1)) {
                        baseAttribute[b5] = execDataIn.readShort();
                    }
                    for (b4 = 0; b4 < length; b4 = (byte) (b4 + 1)) {
                        if (baseAttribute[b4] != 0) {
                            if (baseAttribute[b4] > 0) {
                                info.append(baseAttributeName[b4] + "+" + baseAttribute[b4] + "\n");
                            } else {
                                info.append(baseAttributeName[b4] + baseAttribute[b4] + "\n");
                            }
                        }
                    }
                    chansi = 0;
                    try {
                        chansi = execDataIn.readByte();
                    } catch (Exception exception) {
                    }
                    switch (chansi) {
                        case 1:
                            table0.setItem("已缠金丝", 5, 16777215);
                            offset = 1;
                            break;
                        case 2:
                            table0.setItem("已缠银丝", 5, 16777215);
                            offset = 1;
                            break;
                    }
                    samsara = 0;
                    try {
                        samsara = execDataIn.readByte();
                    } catch (Exception exception) {
                    }
                    switch (samsara) {
                        case 1:
                            if (offset == 0) {
                                table0.setItem("转生防具", 4, 16777215);
                            } else if (offset == 1) {
                                if (kong != 0) {
                                    table0.setItem("转生防具", 6, 16777215);
                                } else {
                                    table0.setItem("转生防具", 4, 16777215);
                                    offset--;
                                }
                            }
                            offset++;
                            break;
                        case 2:
                            if (offset == 0) {
                                table0.setItem("需要转生", 4, 16711680);
                            } else if (offset == 1) {
                                if (kong != 0) {
                                    table0.setItem("需要转生", 6, 16711680);
                                } else {
                                    table0.setItem("需要转生", 4, 16711680);
                                    offset--;
                                }
                            }
                            offset++;
                            break;
                    }
                    try {
                        byte compose = execDataIn.readByte();
                        if (compose == 1) {
                            info.append("获得熟练度 " + execDataIn.readInt() + "\n");
                            info.append("消耗活力 " + execDataIn.readShort() + "\n");
                            info.append("消耗金钱 " + execDataIn.readInt() + "\n");
                        }
                    } catch (Exception ex) {
                        byte compose = 0;
                    }
                    betExplain = new UITextArea(table0.positionX, 70 + offset * UIComponent.charH, 160, 113 - offset * UIComponent.charH, info.toString());
                    betExplain.setColor(16316576);
                    subForm.addComponent(rim);
                    subForm.addComponent(mg);
                    subForm.addComponentInCenter(betExplain, (byte) 2);
                    subForm.addComponent(table0);
                    subForm.addComponent(label1);
                    subForm.addComponentInCenter(label2, (byte) 6);
                    try {
                        byte starnum = execDataIn.readByte();
                        if (starnum != 0) {
                            starmg = new UIMImage[5];
                            for (int i5 = 0; i5 < 5; i5++) {
                                starmg[i5] = new UIMImage(MainCanvas.screenW / 2 - 55 + i5 * 10, mg.positionY + 5, 9, 9, MainCanvas.mImgUI[14], (byte) 0);
                                if (i5 < starnum) {
                                    starmg[i5].setCurrentFrame((byte) 1);
                                } else {
                                    starmg[i5].setCurrentFrame((byte) 0);
                                }
                                subForm.addComponent(starmg[i5]);
                            }
                        }
                    } catch (Exception ex) {
                        System.out.println("****error");
                    }
                    if (MainCanvas.mc.getRightMenuSubState() == 4 || MainCanvas.mc
                            .getNPCSubState() == 11) {
                        MainCanvas.mc.baseForm.getSubForm().setAboutForm(subForm);
                        break;
                    }
                    MainCanvas.mc.baseForm.setAboutForm(null);
                    MainCanvas.mc.baseForm.setAboutForm(subForm);
                    break;
                case 67110704:
                    subForm = null;
                    rim = null;
                    table0 = null;
                    mg = null;
                    label1 = null;
                    label2 = null;
                    subForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "detail");
                    subForm.setStyle((byte) 0);
                    subForm.setBackGround((byte) 9);
                    rim = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte) 4);
                    mg = new UIMImage(22, 18, 17, 17, MainCanvas.mImgStuff, (byte) 0);
                    mg.setHaveRim(true);
                    mg.setCanFocus(false);
                    label1 = new UILabel(mg.positionX + mg.width + 8, mg.positionY + 2, MainCanvas.screenW - mg.positionX + mg.width, 0, execDataIn.readUTF(), 47103, (byte) 0, (byte) 0);
                    label1.setColor(Cons.STUFF_NAME_COLOR[execDataIn.readByte()]);
                    mid = (byte) (execDataIn.readByte() - 1);
                    mg.setCurrentFrame(mid);
                    label2 = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 0, (byte) 0);
                    table0 = new UITable(5, 40, 166, 14, 2, 2, 2, (byte) 0, (byte) 0);
                    table0.setSingleWH((short) 78, (byte) 14, false);
                    table0.setRim(false);
                    itemlv = execDataIn.readByte();
                    table0.setItem("需要等级 " + itemlv, 0, (itemlv > playerlv) ? 16711680 : 16777215);
                    bindSign = new byte[2];
                    bindSign[0] = execDataIn.readByte();
                    bindSign[1] = execDataIn.readByte();
                    switch (bindSign[1]) {
                        case 1:
                            table0.setItem((bindSign[0] == 0) ? "拾取绑定" : "已绑定", 1, 16777215);
                            break;
                        case 2:
                            table0.setItem((bindSign[0] == 0) ? "装备绑定" : "已绑定", 1, 16777215);
                            break;
                    }
                    info = new StringBuffer();
                    length = 8;
                    baseAttribute = new short[length];
                    baseAttributeName = new String[]{"火抗 ", "风抗 ", "雷抗 ", "力量 ", "智力 ", "体力 ", "敏捷 ", "幸运 "};
                    for (b7 = 0; b7 < length; b7 = (byte) (b7 + 1)) {
                        baseAttribute[b7] = execDataIn.readShort();
                    }
                    for (b6 = 0; b6 < length; b6 = (byte) (b6 + 1)) {
                        if (baseAttribute[b6] != 0) {
                            if (baseAttribute[b6] > 0) {
                                info.append(baseAttributeName[b6] + "+" + baseAttribute[b6] + "\n");
                            } else {
                                info.append(baseAttributeName[b6] + baseAttribute[b6] + "\n");
                            }
                        }
                    }
                    bewrite = execDataIn.readUTF();
                    offset = 0;
                    samsara = 0;
                    try {
                        samsara = execDataIn.readByte();
                    } catch (Exception exception) {
                    }
                    switch (samsara) {
                        case 1:
                            table0.setItem("转生饰品", 2, 16777215);
                            offset++;
                            break;
                        case 2:
                            table0.setItem("需要转生", 2, 16711680);
                            offset++;
                            break;
                        case 3:
                            table0.setItem("转生宝石", 2, 16777215);
                            offset++;
                            break;
                    }
                    try {
                        byte compose = execDataIn.readByte();
                        if (compose == 1) {
                            info.append("获得熟练度 " + execDataIn.readInt() + "\n");
                            info.append("消耗活力 " + execDataIn.readShort() + "\n");
                            info.append("消耗金钱 " + execDataIn.readInt() + "\n");
                        }
                    } catch (Exception ex) {
                        byte compose = 0;
                    }
                    info.append("\n" + bewrite);
                    betExplain = new UITextArea(table0.positionX, 75 - (1 - offset) * UIComponent.charH, 160, 115 + (1 - offset) * UIComponent.charH, info.toString());
                    betExplain.setColor(16316576);
                    subForm.addComponent(rim);
                    subForm.addComponent(mg);
                    subForm.addComponentInCenter(betExplain, (byte) 2);
                    subForm.addComponent(table0);
                    subForm.addComponent(label1);
                    subForm.addComponentInCenter(label2, (byte) 6);
                    try {
                        byte starnum = execDataIn.readByte();
                        if (starnum != 0) {
                            starmg = new UIMImage[5];
                            for (int i5 = 0; i5 < 5; i5++) {
                                starmg[i5] = new UIMImage(MainCanvas.screenW / 2 - 55 + i5 * 10, mg.positionY + 5, 9, 9, MainCanvas.mImgUI[14], (byte) 0);
                                if (i5 < starnum) {
                                    starmg[i5].setCurrentFrame((byte) 1);
                                } else {
                                    starmg[i5].setCurrentFrame((byte) 0);
                                }
                                subForm.addComponent(starmg[i5]);
                            }
                        }
                    } catch (Exception ex) {
                        System.out.println("****error");
                    }
                    if (MainCanvas.mc.getRightMenuSubState() == 4 || MainCanvas.mc
                            .getNPCSubState() == 11) {
                        MainCanvas.mc.baseForm.getSubForm().setAboutForm(subForm);
                        break;
                    }
                    MainCanvas.mc.baseForm.setAboutForm(null);
                    MainCanvas.mc.baseForm.setAboutForm(subForm);
                    break;
                case 67110720:
                    if (execDataIn.readByte() == 0) {
                        MainCanvas.setMessage(MainCanvas.mc.baseForm, "此物品已不存在");
                        MainCanvas.dramatisPackage.setCurrentNull();
                        MainCanvas.stuffName.setStr("  ");
                        return;
                    }
                    subForm = null;
                    rim = null;
                    table0 = null;
                    mg = null;
                    label1 = null;
                    label2 = null;
                    subForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "detail");
                    subForm.setStyle((byte) 0);
                    subForm.setBackGround((byte) 9);
                    rim = new UIRim(0, 0, MainCanvas.screenW, MainCanvas.screenH, (byte) 4);
                    mg = new UIMImage(0, 12, 18, 18, MainCanvas.mImgStuff, (byte) 0);
                    mg.setHaveRim(true);
                    mg.setCanFocus(false);
                    label1 = new UILabel(65, 39, MainCanvas.screenW, 0, execDataIn.readUTF(), 16777215, (byte) 1, (byte) 0);
                    label1.setColor(Cons.STUFF_NAME_COLOR[execDataIn.readByte()]);
                    mg.setCurrentFrame((byte) (execDataIn.readByte() - 1));
                    table0 = new UITable(5, 39 + label1.height, 166, 14, 3, 2, 3, (byte) 0, (byte) 0);
                    table0.setSingleWH((short) 80, (byte) 14, false);
                    table0.setRim(false);
                    leave = execDataIn.readByte();
                    business = execDataIn.readByte();
                    sb = new StringBuffer(3);
                    tmShort = 0;
                    tmShort = execDataIn.readShort();
                    item = 0;
                    if (tmShort > 0) {
                        sb.append("HP +").append(tmShort).append("  ");
                    }
                    tmShort = execDataIn.readShort();
                    if (tmShort > 0) {
                        sb.append("MP +").append(tmShort);
                    }
                    if (sb.length() > 0) {
                        table0.setItem(sb.toString(), 0, 65280);
                        item = (byte) (item + 1);
                    }
                    table0.setItem("需要等级 " + leave, item << 1, (leave > playerlv) ? 16711680 : 16777215);
                    if (business == 0) {
                        table0.setItem("不可交易物品 ", (item << 1) + 1, 16777215);
                    }
                    item = (byte) (item + 1);
                    execDataIn.readShort();
                    tss = execDataIn.readUTF();
                    if (!"".equals(tss)) {
                        table0.setItem(tss, item << 1, 16777215);
                        item = (byte) (item + 1);
                    }
                    label2 = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 0, (byte) 0);
                    info = new StringBuffer();
                    bewrite = execDataIn.readUTF();
                    samsara = 0;
                    try {
                        samsara = execDataIn.readByte();
                    } catch (Exception exception) {
                    }
                    switch (samsara) {
                        case 1:
                            table0.setItem("转生道具", item << 1, 16777215);
                            item = (byte) (item + 1);
                            break;
                        case 2:
                            table0.setItem("需要转生", item << 1, 16711680);
                            item = (byte) (item + 1);
                            break;
                    }
                    try {
                        byte compose = execDataIn.readByte();
                        if (compose == 1) {
                            info.append("消耗金钱 " + execDataIn.readInt() + "\n");
                        } else if (compose == 2) {
                            info.append("获得熟练度 " + execDataIn.readInt() + "\n");
                            info.append("消耗活力 " + execDataIn.readShort() + "\n");
                            info.append("消耗金钱 " + execDataIn.readInt() + "\n");
                        }
                    } catch (Exception ex) {
                        byte compose = 0;
                    }
                    info.append(bewrite);
                    betExplain = new UITextArea(9, table0.positionY + UIComponent.charH * item, 160, 125 - UIComponent.charH * item, info.toString());
                    betExplain.setColor(16316576);
                    subForm.addComponent(rim);
                    subForm.addComponentInCenter(mg, (byte) 2);
                    subForm.addComponentInCenter(betExplain, (byte) 2);
                    subForm.addComponent(table0);
                    subForm.addComponentInCenter(label1, (byte) 2);
                    subForm.addComponentInCenter(label2, (byte) 6);
                    try {
                        byte starnum = execDataIn.readByte();
                        if (starnum != 0) {
                            starmg = new UIMImage[5];
                            for (int i5 = 0; i5 < 5; i5++) {
                                starmg[i5] = new UIMImage(MainCanvas.screenW / 2 - 55 + i5 * 10, mg.positionY + 5, 9, 9, MainCanvas.mImgUI[14], (byte) 0);
                                if (i5 < starnum) {
                                    starmg[i5].setCurrentFrame((byte) 1);
                                } else {
                                    starmg[i5].setCurrentFrame((byte) 0);
                                }
                                subForm.addComponent(starmg[i5]);
                            }
                        }
                    } catch (Exception ex) {
                        System.out.println("****error");
                    }
                    if (MainCanvas.mc.getRightMenuSubState() == 4 || MainCanvas.mc
                            .getNPCSubState() == 11) {
                        MainCanvas.mc.baseForm.getSubForm().setAboutForm(subForm);
                        break;
                    }
                    MainCanvas.mc.baseForm.setAboutForm(null);
                    MainCanvas.mc.baseForm.setAboutForm(subForm);
                    break;
                case 67110736:
                    subForm = null;
                    rim = null;
                    table0 = null;
                    mg = null;
                    label1 = null;
                    label2 = null;
                    subForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "detail");
                    subForm.setStyle((byte) 0);
                    subForm.setBackGround((byte) 9);
                    rim = new UIRim(0, 0, MainCanvas.screenW, MainCanvas.screenH, (byte) 4);
                    mg = new UIMImage(0, 12, 18, 18, MainCanvas.mImgStuff, (byte) 0);
                    mg.setHaveRim(true);
                    mg.setCanFocus(false);
                    label1 = new UILabel(65, 39, MainCanvas.screenW, 0, execDataIn.readUTF(), 16777215, (byte) 1, (byte) 0);
                    label1.setColor(Cons.STUFF_NAME_COLOR[execDataIn.readByte()]);
                    mg.setCurrentFrame((byte) (execDataIn.readByte() - 1));
                    table0 = new UITable(5, 39 + label1.height, 166, 14, 2, 2, 2, (byte) 0, (byte) 0);
                    table0.setSingleWH((short) 80, (byte) 14, false);
                    table0.setRim(false);
                    itemlv = execDataIn.readByte();
                    table0.setItem("需要等级 " + itemlv, 0, (itemlv > playerlv) ? 16711680 : 16777215);
                    table0.setItem("不可交易物品 ", 1, 16777215);
                    label2 = new UILabel(0, 0, 0, 0, "返回", 15718814, (byte) 0, (byte) 0);
                    info = new StringBuffer();
                    info.append(execDataIn.readUTF());
                    item = 0;
                    samsara = 0;
                    try {
                        samsara = execDataIn.readByte();
                    } catch (Exception exception) {
                    }
                    switch (samsara) {
                        case 1:
                            table0.setItem("转生物品", 2, 16777215);
                            item = (byte) (item + 1);
                            break;
                        case 2:
                            table0.setItem("需要转生", 2, 16711680);
                            item = (byte) (item + 1);
                            break;
                    }
                    betExplain = new UITextArea(9, table0.positionY + (1 + item) * UIComponent.charH, 160, 125 - (1 + item) * UIComponent.charH, info.toString());
                    betExplain.setColor(16316576);
                    subForm.addComponent(rim);
                    subForm.addComponentInCenter(mg, (byte) 2);
                    subForm.addComponentInCenter(betExplain, (byte) 2);
                    subForm.addComponent(table0);
                    subForm.addComponentInCenter(label1, (byte) 2);
                    subForm.addComponentInCenter(label2, (byte) 6);
                    if (MainCanvas.mc.getRightMenuSubState() == 4 || MainCanvas.mc
                            .getNPCSubState() == 11) {
                        MainCanvas.mc.baseForm.getSubForm().setAboutForm(subForm);
                        break;
                    }
                    MainCanvas.mc.baseForm.setAboutForm(null);
                    MainCanvas.mc.baseForm.setAboutForm(subForm);
                    break;
                case 67111296:
                    for (m = 0; m < 36; m++) {
                        MainCanvas.mc.packageStuffPrice[m] = 0;
                        MainCanvas.mc.packageStuffPrice[m] = execDataIn.readInt();
                    }
                    break;
                case 67111552:
                    for (b8 = 0; b8 < 36; b8 = (byte) (b8 + 1)) {
                        MainCanvas.dramatisPackage.setGridDetail(b8, execDataIn
                                .readShort(), execDataIn
                                .readByte(), execDataIn
                                .readByte(), execDataIn
                                .readUTF(), execDataIn
                                .readByte(), execDataIn
                                .readShort(), execDataIn
                                .readByte(), execDataIn
                                .readByte());
                    }
                    if (Player.itemCount > 0) {
                        for (b8 = 35; b8 >= 0; b8 = (byte) (b8 - 1)) {
                            if (MainCanvas.dramatisPackage.stuffType[b8] == 221 || MainCanvas.dramatisPackage.stuffType[b8] == 222 || MainCanvas.dramatisPackage.stuffType[b8] == 223) {
                                MainCanvas.dramatisPackage.setCanUse(b8, false);
                            } else {
                                MainCanvas.dramatisPackage.setCanUse(b8, true);
                            }
                        }
                    }
                    MainCanvas.mc.baseForm.setAboutForm(null);
                    if (MainCanvas.mc.baseForm.focusComponent == MainCanvas.dramatisPackage) {
                        MainCanvas.stuffName.setStr(MainCanvas.dramatisPackage
                                .getCurrentName());
                        MainCanvas.stuffName
                                .setColor(Cons.STUFF_NAME_COLOR[MainCanvas.dramatisPackage
                                .getCurrentNameLevel()]);
                    }
                    break;
                case 67111808:
                    tmp = execDataIn.readByte();
                    if (tmp == 1) {
                        MainCanvas.mc.baseForm.setAboutForm(null);
                        MainCanvas.mc.baseForm.addAboutForm("succeed", "镶嵌成功！", (byte) 1, MainCanvas.screenW - 30, 30);
                        tmp = MainCanvas.dramatisPackage.stuffPlace;
                        MainCanvas.dramatisPackage.stuffNumber[tmp] = (byte) (MainCanvas.dramatisPackage.stuffNumber[tmp] - 1);
                        if (MainCanvas.dramatisPackage.stuffNumber[tmp] == 0) {
                            MainCanvas.dramatisPackage.stuffId[tmp] = 0;
                            MainCanvas.dramatisPackage.stuffImageId[tmp] = 0;
                            MainCanvas.dramatisPackage.stuffName[tmp] = "";
                            MainCanvas.dramatisPackage.stuffLevel[tmp] = 0;
                            MainCanvas.dramatisPackage.stuffType[tmp] = 0;
                        }
                        break;
                    }
                    if (tmp == 3) {
                        MainCanvas.mc.baseForm.setAboutForm(null);
                        MainCanvas.setMessage(MainCanvas.mc.baseForm, "镶嵌失败,此物品无凹槽！");
                        break;
                    }
                    if (tmp == 4) {
                        MainCanvas.mc.baseForm.setAboutForm(null);
                        MainCanvas.setMessage(MainCanvas.mc.baseForm, "镶嵌失败,此物品无凹槽！");
                        break;
                    }
                    if (tmp == 5) {
                        MainCanvas.mc.baseForm.setAboutForm(null);
                        MainCanvas.setMessage(MainCanvas.mc.baseForm, "您的装备等级太低，不能镶嵌！");
                        break;
                    }
                    if (tmp == 6) {
                        MainCanvas.mc.baseForm.setAboutForm(null);
                        MainCanvas.setMessage(MainCanvas.mc.baseForm, "未转生宝石不能插转生装备：今世的珠宝无法镶嵌在来生才能拥有的装备上。");
                    }
                    break;
                case 167772544:
                    if (execDataIn.readByte() == 0) {
                        MainCanvas.mc.setGameState((byte) 0);
                        MainCanvas.mc.releaseUI();
                        PCChat.addChatScreen((byte) 7, "您选择的玩家已经离开您的视野");
                        UIGameRun.releaseChat();
                        return;
                    }
                    MainCanvas.mc.releaseUI();
                    img = Image.createImage(18, 30);
                    gg = img.getGraphics();
                    gg.setColor(0);
                    gg.fillRect(0, 0, 18, 30);
                    UIGameRun.getInstance().drawMenuPeople(gg, 9, 30, ObjManager.currentTarget.originalImgID);
                    MainCanvas.mc.mImages[8] = new UIMImage((MainCanvas.screenW >> 1) - 10, 120, 0, 0, new MImage(img), (byte) 0);
                    MainCanvas.mc.baseForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "");
                    MainCanvas.mc.baseForm.setStyle((byte) 0);
                    rim0 = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte) 4);
                    rim1 = new UIRim(9, 6, 160, 103, (byte) 4);
                    rim2 = new UIRim(9, 115, 160, 78, (byte) 4);
                    MainCanvas.mc.tables[0] = new UITable(9, 6, 160, 103, 6, 4, 6, (byte) 4, (byte) 0);
                    MainCanvas.mc.tables[0].setSingleWH((MainCanvas.mc.tables[0]).singleWidth, (byte) 14, false);
                    MainCanvas.mc.baseForm.addComponent(rim0);
                    MainCanvas.mc.baseForm.addComponent(rim1);
                    MainCanvas.mc.baseForm.addComponent(rim2);
                    label = new UILabel[19];
                    label[0] = new UILabel(17, 9, 0, 0, execDataIn.readUTF(), (ObjManager.currentTarget.isReincarnation == 1) ? 1745905 : 15718814, (byte) 0, (byte) 0);
                    label[1] = new UILabel(100, 9, 0, 0, "等级", 10321225, (byte) 0, (byte) 0);
                    label[2] = new UILabel(130, 9, 0, 0, execDataIn
                            .readByte() + "", 15132098, (byte) 0, (byte) 0);
                    tp = execDataIn.readByte();
                    label[3] = new UILabel(17, 23, 0, 0, Cons.STR_PLAYERS[tp], 10321225, (byte) 0, (byte) 0);
                    ss = null;
                    if (tp < 4) {
                        ss = Cons.STR_CAMP[0];
                    } else {
                        ss = Cons.STR_CAMP[1];
                    }
                    ts = new short[7];
                    label[4] = new UILabel(100, 23, 0, 0, ss, 10321225, (byte) 0, (byte) 0);
                    label[5] = new UILabel(17, 53, 0, 0, "HP", 10321225, (byte) 0, (byte) 0);
                    label[6] = new UILabel(33, 53, 0, 0, execDataIn.readInt() + "/" + execDataIn
                            .readInt(), 15132098, (byte) 0, (byte) 0);
                    label[7] = new UILabel(17, 68, 0, 0, "MP", 10321225, (byte) 0, (byte) 0);
                    label[8] = new UILabel(33, 68, 0, 0, execDataIn.readInt() + "/" + execDataIn
                            .readInt(), 15132098, (byte) 0, (byte) 0);
                    ts[5] = execDataIn.readShort();
                    ts[6] = execDataIn.readShort();
                    label[9] = new UILabel(17, 86, 0, 0, "抗性:", 10321225, (byte) 0, (byte) 0);
                    label[10] = new UILabel(67, 86, 0, 0, execDataIn
                            .readInt() + "", 15132098, (byte) 0, (byte) 0);
                    label[11] = new UILabel(108, 86, 0, 0, execDataIn.readInt() + "", 15718814, (byte) 0, (byte) 0);
                    label[12] = new UILabel(149, 86, 0, 0, execDataIn
                            .readInt() + "", 15132098, (byte) 0, (byte) 0);
                    for (n = 0; n < 5; n++) {
                        ts[n] = execDataIn.readShort();
                    }
                    MainCanvas.mc.tables[0].addItem("命中", 10321225);
                    MainCanvas.mc.tables[0].addItem(ts[3] + "", 15132098);
                    MainCanvas.mc.tables[0].addItem("攻击", 10321225);
                    MainCanvas.mc.tables[0].addItem(ts[0] + "-" + ts[1], 15132098);
                    MainCanvas.mc.tables[0].addItem("暴击", 10321225);
                    MainCanvas.mc.tables[0].addItem((ts[4] / 10) + "%", 15132098);
                    MainCanvas.mc.tables[0].addItem("法术", 10321225);
                    MainCanvas.mc.tables[0].addItem(ts[2] + "", 15132098);
                    MainCanvas.mc.tables[0].addItem("防御", 10321225);
                    MainCanvas.mc.tables[0].addItem(ts[6] + "", 15132098);
                    MainCanvas.mc.tables[0].addItem("回避", 10321225);
                    MainCanvas.mc.tables[0].addItem(ts[5] + "", 15132098);
                    (MainCanvas.mc.tables[0]).isVisible = false;
                    str = execDataIn.readUTF();
                    if (str == null || str.length() == 0) {
                        str = "无";
                    }
                    label[13] = new UILabel(17, 38, 0, 0, "氏族：" + str, 10321225, (byte) 0, (byte) 0);
                    MainCanvas.mc.tables[0].addItem("荣誉击杀", 10321225);
                    MainCanvas.mc.tables[0].addItem("", 10321225);
                    MainCanvas.mc.tables[0].addItem(execDataIn.readInt() + "", 15132098);
                    MainCanvas.mc.tables[0].addItem("", 15132098);
                    MainCanvas.mc.tables[0].addItem("伴侣", 10321225);
                    MainCanvas.mc.tables[0].addItem(execDataIn.readUTF(), 15132098);
                    MainCanvas.mc.tables[0].addItem("", 10321225);
                    MainCanvas.mc.tables[0].addItem("", 10321225);
                    MainCanvas.mc.tables[0].addItem("转生", 10321225);
                    MainCanvas.mc.tables[0].addItem((ObjManager.currentTarget.isReincarnation == 1) ? "是" : "否", 15132098);
                    for (i2 = 0; i2 < 14; i2++) {
                        MainCanvas.mc.baseForm.addComponent(label[i2]);
                    }
                    if (MainCanvas.equipImageId == null) {
                        MainCanvas.equipImageId = new byte[8];
                    }
                    for (i1 = 0; i1 < 8; i1++) {
                        MainCanvas.equipImageId[i1] = execDataIn.readByte();
                        execDataIn.readUTF();
                        execDataIn.readByte();
                        if (MainCanvas.equipImageId[i1] == 0) {
                            MainCanvas.equipStuffId[i1] = 0;
                        } else {
                            MainCanvas.equipStuffId[i1] = 1111;
                        }
                    }
                    MainCanvas.mc.labels[9] = new UILabel(0, 0, 0, 0, "下页", 15718814, (byte) 0, (byte) 0);
                    label[18] = new UILabel(0, 0, 0, 0, "查看", 15718814, (byte) 0, (byte) 0);
                    MainCanvas.mc.baseForm.addComponentInCenter(MainCanvas.mc.labels[9], (byte) 6);
                    MainCanvas.mc.baseForm.addComponentInCenter(label[18], (byte) 5);
                    p = new int[][]{{33, 120}, {33, 142}, {33, 164}, {123, 120}, {123, 142}, {123, 164}, {63, 164}, {93, 164}};
                    ic = new byte[]{0, 1, 2, 3, 4, 5, 6, 6};
                    for (b9 = 0; b9 < 8; b9 = (byte) (b9 + 1)) {
                        MainCanvas.mc.mImages[b9] = new UIMImage(p[b9][0], p[b9][1], 18, 18, MainCanvas.mImgStuff, (byte) 0);
                        MainCanvas.mc.mImages[b9].setHaveRim(true);
                        MainCanvas.mc.mImages[b9].setBackVeins(MainCanvas.mImgUI[26], ic[b9]);
                        (MainCanvas.mc.mImages[b9]).index = b9;
                        MainCanvas.mc.mImages[b9]
                                .setCurrentFrame(MainCanvas.equipImageId[b9]);
                        MainCanvas.mc.baseForm
                                .addComponent(MainCanvas.mc.mImages[b9]);
                    }
                    MainCanvas.mc.mImages[0].setAroundComponent(MainCanvas.mc.mImages[3], (byte) 4);
                    MainCanvas.mc.mImages[0].setAroundComponent(MainCanvas.mc.mImages[1], (byte) 2);
                    MainCanvas.mc.mImages[3].setAroundComponent(MainCanvas.mc.mImages[4], (byte) 2);
                    MainCanvas.mc.mImages[1].setAroundComponent(MainCanvas.mc.mImages[2], (byte) 2);
                    MainCanvas.mc.mImages[4].setAroundComponent(MainCanvas.mc.mImages[5], (byte) 2);
                    MainCanvas.mc.mImages[1].setAroundComponent(MainCanvas.mc.mImages[4], (byte) 4);
                    MainCanvas.mc.mImages[2].setAroundComponent(MainCanvas.mc.mImages[6], (byte) 4);
                    MainCanvas.mc.mImages[6].setAroundComponent(MainCanvas.mc.mImages[7], (byte) 4);
                    MainCanvas.mc.mImages[7].setAroundComponent(MainCanvas.mc.mImages[5], (byte) 4);
                    MainCanvas.mc.baseForm.addComponentInCenter(MainCanvas.mc.mImages[8], (byte) 2);
                    MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.tables[0]);
                    MainCanvas.mc.baseForm.setFocus(true);
                    MainCanvas.mc.baseForm
                            .setComponentFocus(MainCanvas.mc.mImages[0]);
                    MainCanvas.mc.setLeftMenuSubState(0);
                    break;
                case 67112320:
                    sign = execDataIn.readByte();
                    if (sign == 0) {
                        MainCanvas.mc.baseForm.setAboutForm(null);
                        break;
                    }
                    subForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "compare");
                    subForm.setBackGround((byte) 9);
                    rim = new UIRim(2, 2, MainCanvas.screenW - 2, MainCanvas.screenH - 2, (byte) 1);
                    subForm.addComponent(rim);
                    deltaH = 35;
                    deltaH += 110;
                    MainCanvas.mc.tables[0] = new UITable(3, 30, 35, MainCanvas.screenH - deltaH, Cons.compare_menu.length, 1, 11, (byte) 1, (byte) 6);
                    MainCanvas.mc.tables[0].setSingleWH((short) (byte) (MainCanvas.mc.tables[0]).width, (byte) ((MainCanvas.mc.tables[0]).singleHeight - 8), false);
                    MainCanvas.mc.tables[0].setCanFocus(false);
                    MainCanvas.mc.tables[1] = new UITable(42, 30, 65, MainCanvas.screenH - deltaH, Cons.compare_menu.length, 1, 11, (byte) 1, (byte) 6);
                    MainCanvas.mc.tables[1].setSingleWH((short) (byte) (MainCanvas.mc.tables[1]).width, (byte) ((MainCanvas.mc.tables[1]).singleHeight - 8), false);
                    MainCanvas.mc.tables[1].setCanFocus(false);
                    MainCanvas.mc.tables[2] = new UITable(108, 30, 66, MainCanvas.screenH - deltaH, Cons.compare_menu.length, 1, 11, (byte) 1, (byte) 6);
                    MainCanvas.mc.tables[2].setSingleWH((short) (byte) (MainCanvas.mc.tables[2]).width, (byte) ((MainCanvas.mc.tables[2]).singleHeight - 8), false);
                    MainCanvas.mc.tables[2].setCanFocus(false);
                    um = new UIMImage[2];
                    um[0] = new UIMImage((MainCanvas.mc.tables[1]).positionX + ((MainCanvas.mc.tables[1]).width - 19 >> 1), 7, 19, 19, MainCanvas.mImgStuff, (byte) 0);
                    um[0].setCanFocus(false);
                    um[0].setHaveRim(true);
                    um[1] = new UIMImage((MainCanvas.mc.tables[2]).positionX + ((MainCanvas.mc.tables[2]).width - 19 >> 1), 7, 19, 19, MainCanvas.mImgStuff, (byte) 0);
                    um[1].setCanFocus(false);
                    um[1].setCurrentFrame((byte) 0);
                    um[1].setHaveRim(true);
                    label1 = new UILabel(0, 0, 0, 0, "返回", 14333046, (byte) 0, (byte) 0);
                    subForm.addComponent(MainCanvas.mc.tables[0]);
                    subForm.addComponent(MainCanvas.mc.tables[1]);
                    subForm.addComponent(MainCanvas.mc.tables[2]);
                    subForm.addComponent(um[0]);
                    subForm.addComponent(um[1]);
                    subForm.addComponentInCenter(label1, (byte) 6);
                    for (b10 = 0; b10 < Cons.compare_menu.length; b10 = (byte) (b10 + 1)) {
                        MainCanvas.mc.tables[0].setItem(Cons.compare_menu[b10], b10, 14333046);
                    }
                    sb = new StringBuffer();
                    color = 0;
                    imgId = 0;
                    if (MainCanvas.compare[0] == 0) {
                        sb.append(MainCanvas.equipSruffName[UIMImage.sign1]);
                        color = Cons.STUFF_NAME_COLOR[MainCanvas.equipStuffNameLevel[UIMImage.sign1]];
                        imgId = MainCanvas.equipImageId[UIMImage.sign1];
                    } else {
                        sb
                                .append(MainCanvas.dramatisPackage.stuffName[MainCanvas.compare[1]]);
                        color = Cons.STUFF_NAME_COLOR[MainCanvas.dramatisPackage.stuffLevel[MainCanvas.compare[1]]];
                        imgId = MainCanvas.dramatisPackage.stuffImageId[MainCanvas.compare[1]];
                    }
                    if (sb.length() > 4) {
                        sb.delete(3, sb.length()).append("…");
                    }
                    temp1 = 0;
                    temp2 = 0;
                    tick = 0;
                    imgId = (byte) (imgId - 1);
                    um[0].setCurrentFrame(imgId);
                    MainCanvas.mc.tables[1].setItem(sb.toString(), tick++, color);
                    temp1 = execDataIn.readShort();
                    temp2 = execDataIn.readShort();
                    MainCanvas.mc.tables[1].setItem((temp2 == 0) ? "" : (temp1 + "-" + temp2), tick++, 16777215);
                    temp1 = execDataIn.readShort();
                    MainCanvas.mc.tables[1].setItem((temp1 == 0) ? "" : (temp1 + ""), tick++, 16777215);
                    temp1 = execDataIn.readInt();
                    temp2 = execDataIn.readInt();
                    MainCanvas.mc.tables[1].setItem(temp1 + "/" + temp2, tick++, 16777215);
                    temp1 = execDataIn.readByte();
                    temp2 = execDataIn.readByte();
                    MainCanvas.mc.tables[1].setItem(temp1 + "/" + temp2, tick++, 16777215);
                    temp1 = execDataIn.readShort();
                    MainCanvas.mc.tables[1].setItem((temp1 == 0) ? "" : ("+" + temp1), tick++, 16777215);
                    temp1 = execDataIn.readShort();
                    MainCanvas.mc.tables[1].setItem((temp1 == 0) ? "" : ("+" + temp1), tick++, 16777215);
                    temp1 = execDataIn.readShort();
                    MainCanvas.mc.tables[1].setItem((temp1 == 0) ? "" : ("+" + temp1), tick++, 16777215);
                    temp1 = execDataIn.readShort();
                    MainCanvas.mc.tables[1].setItem((temp1 == 0) ? "" : ("+" + temp1), tick++, 16777215);
                    temp1 = execDataIn.readShort();
                    MainCanvas.mc.tables[1].setItem((temp1 == 0) ? "" : ("+" + temp1), tick++, 16777215);
                    MainCanvas.mc.tables[1].setItem(execDataIn
                            .readByte() + "级", tick++, 16777215);
                    temp1 = execDataIn.readShort();
                    MainCanvas.mc.tables[1].setItem((temp1 == 0) ? "" : ("" + temp1), tick++, 16777215);
                    d = execDataIn.readByte();
                    pro = "";
                    if ((d & 0xF) == 15) {
                        pro = "全职业";
                    } else {
                        if ((d & 0x1) != 0) {
                            pro = pro + Cons.STR_PROFESSION[0] + " ";
                        }
                        if ((d & 0x2) != 0) {
                            pro = pro + Cons.STR_PROFESSION[3] + " ";
                        }
                        if ((d & 0x4) != 0) {
                            pro = pro + Cons.STR_PROFESSION[1] + " ";
                        }
                        if ((d & 0x8) != 0) {
                            pro = pro + Cons.STR_PROFESSION[2] + " ";
                        }
                    }
                    MainCanvas.mc.tables[1].setItem(pro, tick++, 16777215);
                    MainCanvas.mc.tables[1].setItem(execDataIn
                            .readByte() + "级", tick++, 16777215);
                    temp1 = execDataIn.readShort();
                    MainCanvas.mc.tables[1].setItem((temp1 == 0) ? "" : ("+" + temp1), tick++, 16777215);
                    temp1 = execDataIn.readShort();
                    MainCanvas.mc.tables[1].setItem((temp1 == 0) ? "" : ("+" + temp1), tick++, 16777215);
                    temp1 = execDataIn.readShort();
                    MainCanvas.mc.tables[1].setItem((temp1 == 0) ? "" : ("+" + temp1), tick++, 16777215);
                    temp1 = execDataIn.readShort();
                    MainCanvas.mc.tables[1].setItem((temp1 == 0) ? "" : ("+" + temp1), tick++, 16777215);
                    temp1 = execDataIn.readShort();
                    MainCanvas.mc.tables[1].setItem((temp1 == 0) ? "" : ("+" + temp1), tick++, 16777215);
                    temp1 = execDataIn.readShort();
                    MainCanvas.mc.tables[1].setItem((temp1 == 0) ? "" : ("+" + temp1), tick++, 16777215);
                    sb = new StringBuffer();
                    color = 0;
                    imgId = 0;
                    tick = 0;
                    if (MainCanvas.compare[2] == 0) {
                        sb.append(MainCanvas.equipSruffName[UIMImage.sign]);
                        color = Cons.STUFF_NAME_COLOR[MainCanvas.equipStuffNameLevel[UIMImage.sign]];
                        imgId = MainCanvas.equipImageId[UIMImage.sign];
                    } else {
                        sb.append(MainCanvas.dramatisPackage.getCurrentName());
                        color = Cons.STUFF_NAME_COLOR[MainCanvas.dramatisPackage.getCurrentNameLevel()];
                        imgId = MainCanvas.dramatisPackage.getCurrentImageId();
                    }
                    if (sb.length() > 4) {
                        sb.delete(3, sb.length()).append("…");
                    }
                    imgId = (byte) (imgId - 1);
                    um[1].setCurrentFrame(imgId);
                    MainCanvas.mc.tables[2].setItem(sb.toString(), tick++, color);
                    temp1 = execDataIn.readShort();
                    temp2 = execDataIn.readShort();
                    MainCanvas.mc.tables[2].setItem((temp2 == 0) ? "" : (temp1 + "-" + temp2), tick++, 16777215);
                    temp1 = execDataIn.readShort();
                    MainCanvas.mc.tables[2].setItem((temp1 == 0) ? "" : (temp1 + ""), tick++, 16777215);
                    temp1 = execDataIn.readInt();
                    temp2 = execDataIn.readInt();
                    MainCanvas.mc.tables[2].setItem((temp2 == 0) ? "" : (temp1 + "/" + temp2), tick++, 16777215);
                    temp1 = execDataIn.readByte();
                    temp2 = execDataIn.readByte();
                    MainCanvas.mc.tables[2].setItem(temp1 + "/" + temp2, tick++, 16777215);
                    temp1 = execDataIn.readShort();
                    MainCanvas.mc.tables[2].setItem((temp1 == 0) ? "" : ("+" + temp1), tick++, 16777215);
                    temp1 = execDataIn.readShort();
                    MainCanvas.mc.tables[2].setItem((temp1 == 0) ? "" : ("+" + temp1), tick++, 16777215);
                    temp1 = execDataIn.readShort();
                    MainCanvas.mc.tables[2].setItem((temp1 == 0) ? "" : ("+" + temp1), tick++, 16777215);
                    temp1 = execDataIn.readShort();
                    MainCanvas.mc.tables[2].setItem((temp1 == 0) ? "" : ("+" + temp1), tick++, 16777215);
                    temp1 = execDataIn.readShort();
                    MainCanvas.mc.tables[2].setItem((temp1 == 0) ? "" : ("+" + temp1), tick++, 16777215);
                    MainCanvas.mc.tables[2].setItem(execDataIn
                            .readByte() + "级", tick++, 16777215);
                    temp1 = execDataIn.readShort();
                    MainCanvas.mc.tables[2].setItem((temp1 == 0) ? "" : ("" + temp1), tick++, 16777215);
                    d = execDataIn.readByte();
                    pro = "";
                    if ((d & 0xF) == 15) {
                        pro = "全职业";
                    } else {
                        if ((d & 0x1) != 0) {
                            pro = pro + Cons.STR_PROFESSION[0] + " ";
                        }
                        if ((d & 0x2) != 0) {
                            pro = pro + Cons.STR_PROFESSION[3] + " ";
                        }
                        if ((d & 0x4) != 0) {
                            pro = pro + Cons.STR_PROFESSION[1] + " ";
                        }
                        if ((d & 0x8) != 0) {
                            pro = pro + Cons.STR_PROFESSION[2] + " ";
                        }
                    }
                    MainCanvas.mc.tables[2].setItem(pro, tick++, 16777215);
                    MainCanvas.mc.tables[2].setItem(execDataIn
                            .readByte() + "级", tick++, 16777215);
                    temp1 = execDataIn.readShort();
                    MainCanvas.mc.tables[2].setItem((temp1 == 0) ? "" : ("+" + temp1), tick++, 16777215);
                    temp1 = execDataIn.readShort();
                    MainCanvas.mc.tables[2].setItem((temp1 == 0) ? "" : ("+" + temp1), tick++, 16777215);
                    temp1 = execDataIn.readShort();
                    MainCanvas.mc.tables[2].setItem((temp1 == 0) ? "" : ("+" + temp1), tick++, 16777215);
                    temp1 = execDataIn.readShort();
                    MainCanvas.mc.tables[2].setItem((temp1 == 0) ? "" : ("+" + temp1), tick++, 16777215);
                    temp1 = execDataIn.readShort();
                    MainCanvas.mc.tables[2].setItem((temp1 == 0) ? "" : ("+" + temp1), tick++, 16777215);
                    temp1 = execDataIn.readShort();
                    MainCanvas.mc.tables[2].setItem((temp1 == 0) ? "" : ("+" + temp1), tick++, 16777215);
                    MainCanvas.mc.baseForm.setAboutForm(null);
                    MainCanvas.mc.baseForm.setAboutForm(subForm);
                    break;
                case 67112576:
                    bt = execDataIn.readByte();
                    PCSkillTree.canUseRed = !((bt & 0x4) == 0);
                    PCSkillTree.canUseBlue = !((bt & 0x2) == 0);
                    PCSkillTree.canUsePurple = !((bt & 0x1) == 0);
                    for (i3 = 0; i3 < Player.userDefinedSkills.length; i3++) {
                        switch (Player.userDefinedSkills[i3]) {
                            case 15:
                                if (!PCSkillTree.canUseRed) {
                                    Player.canUseSkill[i3] = false;
                                    break;
                                }
                                if (Player.itemCount == 0) {
                                    Player.canUseSkill[i3] = true;
                                }
                                break;
                            case 16:
                                if (!PCSkillTree.canUseBlue) {
                                    Player.canUseSkill[i3] = false;
                                    break;
                                }
                                if (Player.itemCount == 0) {
                                    Player.canUseSkill[i3] = true;
                                }
                                break;
                            case 17:
                                if (!PCSkillTree.canUsePurple) {
                                    Player.canUseSkill[i3] = false;
                                    break;
                                }
                                if (Player.itemCount == 0) {
                                    Player.canUseSkill[i3] = true;
                                }
                                break;
                        }
                    }
                    break;
                case 67112832:
                    number = execDataIn.readByte();
                    if (number == 0) {
                        MainCanvas.mc.baseForm.addAboutForm("msg", "分解失败", (byte) 1, 150, 0);
                    } else {
                        MainCanvas.dramatisPackage
                                .setGridNull(MainCanvas.mc.decompose[0]);
                        MainCanvas.dramatisPackage
                                .setGridNull(MainCanvas.mc.decompose[1]);
                        byte[] s_place = new byte[number];
                        for (int i5 = 0; i5 < number; i5++) {
                            s_place[i5] = execDataIn.readByte();
                            MainCanvas.dramatisPackage.setGridDetail(s_place[i5], execDataIn
                                    .readShort(), execDataIn.readByte(), (byte) 1, execDataIn
                                    .readUTF(), execDataIn
                                    .readByte(), execDataIn.readShort(), (byte) 0, (byte) 0);
                        }
                        MainCanvas.stuffName.setStr(MainCanvas.dramatisPackage
                                .getCurrentName());
                        MainCanvas.stuffName
                                .setColor(Cons.STUFF_NAME_COLOR[MainCanvas.dramatisPackage
                                .getCurrentNameLevel()]);
                        if (number == 1 || (number == 2 && MainCanvas.dramatisPackage
                                .getStuffId()[s_place[0]] == MainCanvas.dramatisPackage
                                .getStuffId()[s_place[1]])) {
                            MainCanvas.mc.baseForm.addAboutForm("msg", "您得到" + MainCanvas.dramatisPackage
                                    .getStuffName()[s_place[0]], (byte) 1, 150, 0);
                        } else {
                            MainCanvas.mc.baseForm.addAboutForm("msg", "您得到" + MainCanvas.dramatisPackage
                                    .getStuffName()[s_place[0]] + "和" + MainCanvas.dramatisPackage
                                    .getStuffName()[s_place[1]], (byte) 1, 150, 0);
                        }
                    }
                    MainCanvas.dramatisPackage.isLock = false;
                    MainCanvas.dramatisPackage.isLock4 = false;
                    break;
                case 67113088:
                    title = new StringBuffer();
                    title.append("恭喜您得到");
                    i4 = execDataIn.readInt();
                    if (i4 == -1) {
                        switch (execDataIn.readByte()) {
                            case 0:
                                MainCanvas.mc.baseForm.addAboutForm("msg", "请整理您的背包，您使用的物品不存在", (byte) 1, 150, 0);
                                MainCanvas.dramatisPackage.isLock = false;
                                MainCanvas.dramatisPackage.isLock4 = false;
                                return;
                            case 1:
                                MainCanvas.mc.baseForm.addAboutForm("msg", "钥匙的冷却时间没到，您还不能使用", (byte) 1, 150, 0);
                                MainCanvas.dramatisPackage.isLock = false;
                                MainCanvas.dramatisPackage.isLock4 = false;
                                return;
                            case 2:
                                MainCanvas.mc.baseForm.addAboutForm("msg", "该类型的钥匙不能打开该类型的物品", (byte) 1, 150, 0);
                                MainCanvas.dramatisPackage.isLock = false;
                                MainCanvas.dramatisPackage.isLock4 = false;
                                return;
                            case 3:
                                MainCanvas.mc.baseForm.addAboutForm("msg", "您的背包容量不够，请整理您的背包", (byte) 1, 150, 0);
                                MainCanvas.dramatisPackage.isLock = false;
                                MainCanvas.dramatisPackage.isLock4 = false;
                                return;
                        }
                        MainCanvas.mc.baseForm.addAboutForm("msg", "使用失败", (byte) 1, 150, 0);
                        MainCanvas.dramatisPackage.isLock = false;
                        MainCanvas.dramatisPackage.isLock4 = false;
                        return;
                    }
                    pnum = (byte) (MainCanvas.dramatisPackage.getSomeoneNumber(MainCanvas.mc.decompose[0]) - 1);
                    if (pnum == 0) {
                        MainCanvas.dramatisPackage
                                .setGridNull(MainCanvas.mc.decompose[0]);
                    } else {
                        MainCanvas.dramatisPackage.setSomeoneNumber(MainCanvas.mc.decompose[0], pnum);
                    }
                    MainCanvas.dramatisPackage
                            .setGridNull(MainCanvas.mc.decompose[1]);
                    if (i4 != 0) {
                        (Player.getInstance()).money += i4;
                        MainCanvas.mc.texts[0].setLabel(""
                                + (Player.getInstance()).money);
                        title.append("金钱：" + i4);
                    }
                    b11 = execDataIn.readByte();
                    if (b11 != -1) {
                        MainCanvas.dramatisPackage.setGridDetail(b11, execDataIn
                                .readShort(), execDataIn.readByte(), execDataIn
                                .readByte(), execDataIn.readUTF(), execDataIn
                                .readByte(), execDataIn.readShort(), execDataIn
                                .readByte(), execDataIn.readByte());
                        if (i4 != 0) {
                            title.append(" 和 ");
                        }
                        title
                                .append(MainCanvas.dramatisPackage.getStuffName()[b11] + MainCanvas.dramatisPackage
                                .getStuffNumber()[b11] + "个");
                    }
                    if (i4 == 0 && b11 == -1) {
                        title.delete(0, title.length());
                        title.append("很遗憾此物品是空的");
                    }
                    MainCanvas.stuffName
                            .setColor(Cons.STUFF_NAME_COLOR[MainCanvas.dramatisPackage
                            .getCurrentNameLevel()]);
                    MainCanvas.stuffName.setStr(MainCanvas.dramatisPackage
                            .getCurrentName());
                    MainCanvas.mc.baseForm.addAboutForm("msg", title.toString(), (byte) 1, 150, 0);
                    MainCanvas.dramatisPackage.isLock = false;
                    MainCanvas.dramatisPackage.isLock4 = false;
                    break;
            }
        } catch (Exception e) {
            System.err.println("error in PCPackage.parse");
            e.printStackTrace();
        }
    }

    public static byte[] compress(int _commID) {
        try {
            byte i;
            ByteArray execDataOut = new ByteArray();
            switch (_commID) {
                case 67109632:
                    execDataOut.writeByte(MainCanvas.dramatisPackage.stuffPlace);
                    execDataOut.writeByte(MainCanvas.dramatisPackage
                            .getCurrentPointer());
                    execDataOut.writeByte(MainCanvas.mc.stNumber);
                    break;
                case 67109888:
                    execDataOut.writeByte(MainCanvas.mc.equipPlace);
                    execDataOut.writeByte(MainCanvas.dramatisPackage.stuffPlace);
                    break;
                case Cmd.C_STUFF_USE_STUFF: {
                    execDataOut.writeByte(MainCanvas.useStuffPlace);
                    if (MainCanvas.useStuffPlace == 0) {
                        execDataOut.writeShort(itemID);
                    } else {
                        execDataOut.writeShort(
                                (short) MainCanvas.dramatisPackage.getCurrentPointer());
                    }
                    if (MainCanvas.mc.transferstatus) {
                        execDataOut.writeInt(MainCanvas.mc.friendTransferTarget);
                        break;
                    }
                    if (MainCanvas.mc.transferstatusQuest) {
                        execDataOut.writeInt(MainCanvas.mc.questTargetID);
                        execDataOut.writeByte(MainCanvas.mc.questTargetPlace);
                        execDataOut.writeByte(MainCanvas.mc.questStoneStatus);
                    }
                    break;
                }
                case 67110400:
                    execDataOut.writeByte(MainCanvas.mc.equipPlace);
                    break;
                case 67110656:
                    if (MainCanvas.lookType == -1) {
                        MainCanvas.lookType = 1;
                        execDataOut.writeByte(MainCanvas.lookType);
                        execDataOut.writeByte(PCNPC.propsId[MainCanvas.mc.tables[1]
                                .getCurrentPointer()]);
                        execDataOut
                                .writeByte(PCNPC.propsNumber[MainCanvas.mc.tables[1]
                                .getCurrentPointer()]);
                        break;
                    }
                    execDataOut.writeByte(MainCanvas.lookType);
                    if (MainCanvas.lookType == 1) {
                        execDataOut.writeByte(MainCanvas.mc.isPackage);
                        execDataOut.writeByte(MainCanvas.lookStuffPlace);
                        break;
                    }
                    execDataOut.writeInt(ObjManager.currentTarget.objID);
                    execDataOut.writeByte(MainCanvas.lookStuffPlace);
                    break;
                case 67110912:
                    execDataOut.writeByte(MainCanvas.mc.isPackage);
                    if (MainCanvas.mc.isPackage == 1) {
                        execDataOut.writeByte(MainCanvas.dramatisPackage
                                .getCurrentPointer());
                        if (MainCanvas.stuffName != null) {
                            MainCanvas.stuffName.setStr(MainCanvas.dramatisPackage
                                    .getCurrentName());
                        }
                        break;
                    }
                    execDataOut.writeByte(UIMImage.sign);
                    break;
                case 67111680:
                    execDataOut.writeByte(MainCanvas.dramatisPackage.stuffPlace);
                    execDataOut.writeByte(MainCanvas.mc.isPackage);
                    execDataOut.writeByte(MainCanvas.mc.equipPlace);
                    break;
                case 167772416:
                    execDataOut.writeInt(ObjManager.currentTarget.objID);
                    break;
                case 67111936:
                    execDataOut.writeShort(MainCanvas.mc.selectedId);
                    break;
                case 67112192:
                    for (i = 0; i < MainCanvas.compare.length; i = (byte) (i + 1)) {
                        execDataOut.writeByte(MainCanvas.compare[i]);
                    }
                    break;
                case 67112704:
                    execDataOut.writeByte(MainCanvas.mc.decompose[0]);
                    execDataOut.writeByte(MainCanvas.mc.decompose[1]);
                    break;
                case 67112960:
                    execDataOut.writeByte(MainCanvas.mc.decompose[0]);
                    execDataOut.writeByte(MainCanvas.mc.decompose[1]);
                    break;
            }
            return execDataOut.toByteArray();
        } catch (Exception e) {
            System.err.println("error in PCPackage.compress");
            e.printStackTrace();
            return null;
        }
    }
}
