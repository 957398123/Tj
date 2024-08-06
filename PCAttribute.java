
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class PCAttribute {

    public static void parse(int commID, byte[] data) {
        String hexID = Integer.toHexString(commID);
        System.out.println("[处理命令]:命令ID:0x" + hexID + ",命令类型：PCAttribute");
        try {
            byte task, attr1, attr2;
            Image img;
            Graphics gg;
            int rimOffW, offX;
            byte temp;
            String str;
            short pp;
            int i;
            byte count, result;
            ByteArray execDataIn = new ByteArray(data);
            switch (commID) {
                case 134218624:
                    MainCanvas.isHaveAttributes = false;
                    MainCanvas.isHaveFinishedTask = false;
                    task = execDataIn.readByte();
                    attr1 = execDataIn.readByte();
                    attr2 = execDataIn.readByte();
                    if (task > 0) {
                        MainCanvas.isHaveFinishedTask = true;
                    }
                    if (attr1 > 0 || attr2 > 0) {
                        MainCanvas.isHaveAttributes = true;
                    }
                    break;
                case 134218880:
                    if (Cons.newPlayerHelp && (Player.getInstance()).level <= 15) {
                        MainCanvas.setPop((byte) 7);
                    }
                    break;
                case 134218112:
                    MainCanvas.mc.releaseUI();
                    img = Image.createImage(18, 30);
                    gg = img.getGraphics();
                    gg.setColor(0);
                    gg.fillRect(0, 0, 18, 30);
                    UIGameRun.getInstance().drawMenuPeople(gg, 9, 30, (Player.getInstance()).originalImgID);
                    MainCanvas.mc.baseForm = new UIForm(0, 0, MainCanvas.screenW, MainCanvas.screenH, "");
                    MainCanvas.mc.baseForm.setStyle((byte) 0);
                    MainCanvas.mc.rims[0] = new UIRim(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1, (byte) 4);
                    rimOffW = 8;
                    MainCanvas.mc.rims[1] = new UIRim(8, 6, 39 - rimOffW, 36, (byte) 1);
                    MainCanvas.mc.rims[2] = new UIRim(52 - rimOffW, 6, 117 + rimOffW, 36, (byte) 1);
                    MainCanvas.mc.rims[3] = new UIRim(60, 106, 109, 85, (byte) 0);
                    MainCanvas.mc.labels[8] = new UILabel(0, 0, 0, 0, "加点", 15718815, (byte) 0, (byte) 0);
                    MainCanvas.mc.labels[9] = new UILabel(0, 0, 0, 0, "下页", 15718815, (byte) 0, (byte) 0);
                    MainCanvas.mc.mImages[0] = new UIMImage((MainCanvas.mc.rims[1]).positionX + 6, (MainCanvas.mc.rims[1]).positionY + 1, 0, 0, new MImage(img), (byte) 0);
                    offX = 6;
                    MainCanvas.mc.labels[0] = new UILabel((MainCanvas.mc.rims[2]).positionX + 8 - offX, (MainCanvas.mc.rims[2]).positionY + 5, 0, 0, execDataIn
                            .readUTF(), ((Player.getInstance()).isReincarnation == 1) ? 1745905 : 15718814, (byte) 0, (byte) 0);
                    temp = execDataIn.readByte();
                    str = null;
                    if (temp < 4) {
                        str = Cons.STR_PLAYERS[temp] + " " + Cons.STR_CAMP[0];
                    } else {
                        str = Cons.STR_PLAYERS[temp] + " " + Cons.STR_CAMP[1];
                    }
                    MainCanvas.mc.labels[1] = new UILabel((MainCanvas.mc.labels[0]).positionX, (MainCanvas.mc.labels[0]).positionY + (MainCanvas.mc.labels[0]).height - 8, 0, 0, str, 10321225, (byte) 0, (byte) 0);
                    MainCanvas.mc.labels[2] = new UILabel((MainCanvas.mc.labels[0]).positionX + 108 + 3, (MainCanvas.mc.labels[0]).positionY, 0, 0, execDataIn.readByte() + "级", 10321225, (byte) 0, (byte) 0);
                    MainCanvas.mc.labels[2].setXY(MainCanvas.screenW - 110, (MainCanvas.mc.labels[0]).positionY);
                    MainCanvas.mc.tables[0] = new UITable(6, 47, 163, 57, 4, 6, 4, (byte) 0, (byte) 0);
                    MainCanvas.mc.tables[0].setSingleWH((short) 27, (byte) 14, false);
                    MainCanvas.mc.tables[0].setItem("HP", 0, 10321225);
                    MainCanvas.mc.tables[0].setItem(execDataIn.readInt() + "/" + execDataIn
                            .readInt(), 1, 15132098);
                    MainCanvas.mc.tables[0].setItem("攻击", 12, 10321225);
                    MainCanvas.mc.tables[0].setItem(execDataIn.readShort() + "-" + execDataIn.readShort(), 13, 15132098);
                    MainCanvas.mc.tables[0].setItem("MP", 6, 10321225);
                    MainCanvas.mc.tables[0].setItem(execDataIn.readInt() + "/" + execDataIn
                            .readInt(), 7, 15132098);
                    MainCanvas.mc.tables[0].setItem("防御", 18, 10321225);
                    MainCanvas.mc.tables[0].setItem(execDataIn.readShort() + "", 19, 15132098);
                    MainCanvas.mc.tables[0].setItem("法术", 10, 10321225);
                    MainCanvas.mc.tables[0].setItem(execDataIn.readShort() + "", 11, 15132098);
                    MainCanvas.mc.tables[0].setItem("命中", 16, 10321225);
                    MainCanvas.mc.tables[0].setItem(execDataIn.readShort() + "", 17, 15132098);
                    MainCanvas.mc.tables[0].setItem("回避", 4, 10321225);
                    MainCanvas.mc.tables[0].setItem(execDataIn.readShort() + "", 5, 15132098);
                    MainCanvas.mc.tables[0].setItem("暴击", 22, 10321225);
                    MainCanvas.mc.tables[0].setItem((execDataIn.readShort() / 10) + "%", 23, 15132098);
                    Player.setCurrentXp(execDataIn.readInt());
                    Player.setMaxXp(execDataIn.readInt());
                    MainCanvas.mc.tables[1] = new UITable(6, 106, 52, 85, 3, 1, 3, (byte) 0, (byte) 1);
                    MainCanvas.mc.tables[1].setSingleWH((short) 37, (byte) 20, false);
                    MainCanvas.mc.tables[1].setTitle("抗性");
                    MainCanvas.mc.tables[1].setItem(MainCanvas.mImgUI[20], (byte) 0, execDataIn
                            .readInt() + "", 0, 15132098);
                    MainCanvas.mc.tables[1].setItem(MainCanvas.mImgUI[20], (byte) 1, execDataIn
                            .readInt() + "", 1, 15132098);
                    MainCanvas.mc.tables[1].setItem(MainCanvas.mImgUI[20], (byte) 2, execDataIn
                            .readInt() + "", 2, 15132098);
                    MainCanvas.mc.rbs[0] = new UIRadioButton(78, 107, 0, 0, "体力", (byte) 1);
                    MainCanvas.mc.rbs[0].setMinNumber(execDataIn.readShort());
                    MainCanvas.mc.rbs[1] = new UIRadioButton(78, (MainCanvas.mc.rbs[0]).positionY + 14, 0, 0, "力量", (byte) 1);
                    MainCanvas.mc.rbs[1].setMinNumber(execDataIn.readShort());
                    MainCanvas.mc.rbs[2] = new UIRadioButton(78, (MainCanvas.mc.rbs[1]).positionY + 14, 0, 0, "智慧", (byte) 1);
                    MainCanvas.mc.rbs[2].setMinNumber(execDataIn.readShort());
                    MainCanvas.mc.rbs[3] = new UIRadioButton(78, (MainCanvas.mc.rbs[2]).positionY + 14, 0, 0, "敏捷", (byte) 1);
                    MainCanvas.mc.rbs[3].setMinNumber(execDataIn.readShort());
                    MainCanvas.mc.rbs[4] = new UIRadioButton(78, (MainCanvas.mc.rbs[3]).positionY + 14, 0, 0, "幸运", (byte) 1);
                    MainCanvas.mc.rbs[4].setMinNumber(execDataIn.readShort());
                    MainCanvas.mc.labels[4] = new UILabel(78, (MainCanvas.mc.rbs[4]).positionY + 14, 0, 0, "剩余点数", 10321225, (byte) 0, (byte) 0);
                    pp = execDataIn.readShort();
                    MainCanvas.mc.rbs[0].setFreePoint(pp);
                    MainCanvas.mc.texts[0] = new UIText(132, (MainCanvas.mc.labels[4]).positionY - 3, 0, 0, 5, (byte) 2, MainCanvas.mc.rbs[0]
                            .getFreePoint() + "");
                    MainCanvas.mc.rbs[0].setAroundComponent(MainCanvas.mc.rbs[1], (byte) 2);
                    MainCanvas.mc.rbs[1].setAroundComponent(MainCanvas.mc.rbs[2], (byte) 2);
                    MainCanvas.mc.rbs[2].setAroundComponent(MainCanvas.mc.rbs[3], (byte) 2);
                    MainCanvas.mc.rbs[3].setAroundComponent(MainCanvas.mc.rbs[4], (byte) 2);
                    for (i = 0; i < 4; i++) {
                        MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.rims[i]);
                    }
                    for (i = 0; i < 5; i++) {
                        MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.rbs[i]);
                    }
                    MainCanvas.mc.baseForm.addComponentInCenter(MainCanvas.mc.labels[8], (byte) 5);
                    MainCanvas.mc.baseForm.addComponentInCenter(MainCanvas.mc.labels[9], (byte) 6);
                    MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.labels[0]);
                    MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.labels[1]);
                    MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.labels[2]);
                    MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.labels[4]);
                    MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.texts[0]);
                    MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.mImages[0]);
                    MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.tables[0]);
                    MainCanvas.mc.baseForm.addComponent(MainCanvas.mc.tables[1]);
                    MainCanvas.mc.baseForm.setFocus(true);
                    MainCanvas.mc.tables[2] = new UITable(6, 54, 163, 65, 11, 2, 9, (byte) 0, (byte) 0);
                    MainCanvas.mc.tables[2].setSingleWH((short) 65, (byte) 15, false);
                    MainCanvas.mc.tables[2].setItem("经验", 0, 10321225);
                    MainCanvas.mc.tables[2].setItem((Player.getInstance()).exp + "/"
                            + (Player.getInstance()).maxExp, 1, 15132098);
                    MainCanvas.mc.tables[2].setItem("金钱", 2, 10321225);
                    MainCanvas.mc.tables[2].setItem(execDataIn.readInt() + "", 3, 15132098);
                    execDataIn.readInt();
                    MainCanvas.mc.tables[2].setItem("声望", 4, 10321225);
                    MainCanvas.mc.tables[2].setItem(execDataIn.readInt() + "", 5, 15132098);
                    MainCanvas.mc.tables[2].setItem("荣誉击杀", 6, 10321225);
                    MainCanvas.mc.tables[2].setItem(execDataIn.readInt() + "", 7, 15132098);
                    MainCanvas.mc.tables[2].setItem("伴侣", 8, 10321225);
                    MainCanvas.mc.tables[2].setItem(execDataIn.readUTF(), 9, 15132098);
                    MainCanvas.mc.tables[2].setItem("竞技场点数", 10, 10321225);
                    MainCanvas.mc.tables[2].setItem(execDataIn.readInt() + "", 11, 15132098);
                    MainCanvas.mc.tables[2].setItem("战队点数", 12, 10321225);
                    MainCanvas.mc.tables[2].setItem(execDataIn.readInt() + "", 13, 15132098);
                    MainCanvas.mc.tables[2].setItem("胜负场次", 14, 10321225);
                    MainCanvas.mc.tables[2].setItem(execDataIn.readUTF() + "", 15, 15132098);
                    MainCanvas.mc.tables[2].setItem("声誉击杀", 16, 10321225);
                    MainCanvas.mc.tables[2].setItem(execDataIn.readInt() + "", 17, 15132098);
                    MainCanvas.mc.tables[2].setItem("氏族贡献", 18, 10321225);
                    MainCanvas.mc.tables[2].setItem(execDataIn.readInt() + "", 19, 15132098);
                    MainCanvas.mc.tables[2].setItem("是否转生", 20, 10321225);
                    MainCanvas.mc.tables[2].setItem(((Player.getInstance()).isReincarnation == 1) ? "是" : "否", 21, 15132098);
                    MainCanvas.mc.setGameState((byte) 1);
                    MainCanvas.mc.setRightMenuSubState(1);
                    MainCanvas.mc.baseForm.setMessage(Cons.ROLL_MASSAGE[0], false);
                    break;
                case 134218368:
                    (Player.getInstance()).curHp = execDataIn.readInt();
                    (Player.getInstance()).maxHp = execDataIn.readInt();
                    MainCanvas.mc.tables[0].setItem((Player.getInstance()).curHp + "/" + (Player.getInstance()).maxHp, 1, 15132098);
                    (Player.getInstance()).attack = execDataIn.readShort();
                    MainCanvas.mc.tables[0].setItem((Player.getInstance()).attack + "-" + execDataIn.readShort(), 13, 15132098);
                    (Player.getInstance()).curMp = execDataIn.readInt();
                    (Player.getInstance()).maxMp = execDataIn.readInt();
                    MainCanvas.mc.tables[0].setItem((Player.getInstance()).curMp + "/"
                            + (Player.getInstance()).maxMp, 7, 15132098);
                    (Player.getInstance()).defend = execDataIn.readShort();
                    MainCanvas.mc.tables[0].setItem((Player.getInstance()).defend + "", 19, 15132098);
                    MainCanvas.mc.tables[0].setItem(execDataIn.readShort() + "", 11, 15132098);
                    (Player.getInstance()).accuracy = execDataIn.readShort();
                    MainCanvas.mc.tables[0].setItem((Player.getInstance()).accuracy + "", 17, 15132098);
                    MainCanvas.mc.tables[0].setItem(execDataIn.readShort() + "", 5, 15132098);
                    MainCanvas.mc.tables[0].setItem((execDataIn.readShort() / 10) + "%", 23, 15132098);
                    MainCanvas.mc.baseForm.setAboutForm(null);
                    MainCanvas.mc.rbs[0].setMinNumber((MainCanvas.mc.rbs[0]).baseNumber);
                    MainCanvas.mc.rbs[1].setMinNumber((MainCanvas.mc.rbs[1]).baseNumber);
                    MainCanvas.mc.rbs[2].setMinNumber((MainCanvas.mc.rbs[2]).baseNumber);
                    MainCanvas.mc.rbs[3].setMinNumber((MainCanvas.mc.rbs[3]).baseNumber);
                    MainCanvas.mc.rbs[4].setMinNumber((MainCanvas.mc.rbs[4]).baseNumber);
                    MainCanvas.mc.rbs[0].setFreePoint(MainCanvas.mc.rbs[0].getFreePoint());
                    break;
                case 134219136:
                    Player.getInstance().setTitle(execDataIn.readUTF());
                    Player.getInstance().setTitleDesc(execDataIn.readUTF());
                    MainCanvas.mc.titleTotal = execDataIn.readInt();
                    MainCanvas.mc.titleCurrentPage = execDataIn.readInt();
                    count = execDataIn.readByte();
                    if (count > 0) {
                        MainCanvas.mc.titleID = new short[count];
                        MainCanvas.mc.titleName = new String[count];
                        MainCanvas.mc.titleDesc = new String[count];
                        for (int j = 0; j < count; j++) {
                            MainCanvas.mc.titleID[j] = execDataIn.readShort();
                            MainCanvas.mc.titleName[j] = execDataIn.readUTF();
                            MainCanvas.mc.titleDesc[j] = execDataIn.readUTF();
                        }
                        MainCanvas.mc.baseForm = null;
                        MainCanvas.mc.setRightMenuSubState(24);
                        break;
                    }
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "列表为空");
                    break;
                case 134219648:
                    result = execDataIn.readByte();
                    if (result == 0 && MainCanvas.mc.curTitleID != 0) {
                        for (int j = 0; j < MainCanvas.mc.titleName.length; j++) {
                            MainCanvas.mc.tables[0].setItem(MainCanvas.mc.titleName[j], j, 15718815);
                        }
                        MainCanvas.mc.tables[0].setItem(MainCanvas.mc.titleName[MainCanvas.mc.tables[0]
                                .getCurrentPointer()] + "(使用中)", MainCanvas.mc.tables[0]
                                .getCurrentPointer(), 15718815);
                        Player.getInstance()
                                .setTitle(new String(MainCanvas.mc.titleName[MainCanvas.mc.tables[0]
                                .getCurrentPointer()]));
                        Player.getInstance()
                                .setTitleDesc(new String(MainCanvas.mc.titleDesc[MainCanvas.mc.tables[0]
                                .getCurrentPointer()]));
                    } else if (result == 0 && MainCanvas.mc.curTitleID == 0) {
                        for (int j = 0; j < MainCanvas.mc.titleName.length; j++) {
                            MainCanvas.mc.tables[0].setItem(MainCanvas.mc.titleName[j], j, 15718815);
                        }
                        Player.getInstance().setTitle(null);
                        Player.getInstance().setTitleDesc(null);
                    }
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, execDataIn
                            .readUTF());
                    break;
                case 134219392:
                    count = execDataIn.readByte();
                    if (count > 0) {
                        MainCanvas.mc.titleID = new short[count];
                        MainCanvas.mc.titleName = new String[count];
                        MainCanvas.mc.titleDesc = new String[count];
                        for (int j = 0; j < count; j++) {
                            MainCanvas.mc.titleID[j] = execDataIn.readShort();
                            MainCanvas.mc.titleName[j] = execDataIn.readUTF();
                            MainCanvas.mc.titleDesc[j] = execDataIn.readUTF();
                        }
                        MainCanvas.mc.baseForm = null;
                        MainCanvas.mc.setNPCSubState((byte) 83);
                        break;
                    }
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "列表为空");
                    break;
                case 134219904:
                    result = execDataIn.readByte();
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, execDataIn
                            .readUTF());
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] compress(int commID) {
        byte i;
        ByteArray execDataOut = new ByteArray();
        switch (commID) {
            case 134218240:
                for (i = 0; i < 5; i = (byte) (i + 1)) {
                    execDataOut.writeShort((MainCanvas.mc.rbs[i]).baseNumber - (MainCanvas.mc.rbs[i]).minNumber);
                }
                execDataOut.writeShort(Short.parseShort(MainCanvas.mc.texts[0].getLabel()));
                break;
            case 134219008:
                execDataOut.writeInt(MainCanvas.mc.titleCurrentPage);
                break;
            case 134219520:
                execDataOut.writeShort(MainCanvas.mc.curTitleID);
                break;
            case 134219264:
                if (ObjManager.currentTarget.type == 3) {
                    execDataOut.writeInt(ObjManager.currentTarget.objID);
                }
                break;
            case 134219776:
                execDataOut.writeShort(MainCanvas.mc.curTitleID);
                break;
        }
        return execDataOut.toByteArray();
    }
}
