
import java.util.Vector;

public class PCPet {

    public static byte needMaterial = 1;

    public static void parse(int commID, byte[] data) {
        String hexID = Integer.toHexString(commID);
        System.out.println("[处理命令]:命令ID:0x" + hexID + ",命令类型：PCPet");
        byte len;
        int j;
        byte b1;
        int i;
        StringBuffer sb;
        byte result;
        String tmp;
        ByteArray execDataIn = new ByteArray(data);
        switch (commID) {
            case 50332288:
                MainCanvas.mc.baseForm.setAboutForm(null);
                if (execDataIn.readByte() == 1) {
                    MainCanvas.mc.baseForm.addAboutForm("msg", "宠物学习技能成功：）", (byte) 1, MainCanvas.screenW, 0);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "宠物学习技能成功：）");
                    break;
                }
                MainCanvas.setMessage(MainCanvas.mc.baseForm, "学习条件不足或已学习");
                break;
            case 50333312:
                len = execDataIn.readByte();
                MainCanvas.pet.curPetSkill.curIndex = 0;
                for (j = 0; j < len; j++) {
                    MainCanvas.pet.addSkillDetail(execDataIn.readShort(), execDataIn
                            .readUTF());
                }
                MainCanvas.mc.setGameState((byte) 1);
                MainCanvas.mc.setRightMenuSubState(22);
                MainCanvas.mc.setUIPetState((byte) 2);
                MainCanvas.mc.releaseUI();
                break;
            case 50334592:
                MainCanvas.mc.menus[0].setNoUse(MainCanvas.mc.menus[0]
                        .getCurrentPointer());
                MainCanvas.mc.menus[0].checkMenu();
                MainCanvas.mc.baseForm.setAboutForm(null);
                break;
            case 50335104:
                Pets.maxvires = execDataIn.readShort();
                Pets.curvires = execDataIn.readShort();
                for (b1 = 0; b1 < 4; b1 = (byte) (b1 + 1)) {
                    byte id = execDataIn.readByte();
                    byte level = execDataIn.readByte();
                    int maxdex = execDataIn.readInt();
                    int curdex = execDataIn.readInt();
                    MainCanvas.pet.addComposite(b1, id, Cons.COMPOSITE_SKILL[id], level, maxdex, curdex, Cons.PET_SKILL_IMAGE_ID[id]);
                }
                MainCanvas.mc.setRightMenuSubState(23);
                MainCanvas.mc.setUIPetState((byte) 6);
                MainCanvas.mc.releaseUI();
                break;
            case 50335872:
                MainCanvas.mc.baseForm.setAboutForm(null);
                if (execDataIn.readByte() == 1) {
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "学习技能成功！");
                    break;
                }
                MainCanvas.setMessage(MainCanvas.mc.baseForm, "学习条件不足或已学习！");
                break;
            case 50335360:
                if (MainCanvas.pet.compose == null) {
                    MainCanvas.pet.compose = new Vector();
                } else {
                    MainCanvas.pet.compose.removeAllElements();
                }
                Pets.material_num = new byte[4];
                for (i = 0; i < 4; i++) {
                    byte temp = execDataIn.readByte();
                    if (temp > 0) {
                        temp = (byte) (temp - 1);
                    }
                    MainCanvas.pet.addComposeDetail(temp, execDataIn.readUTF());
                    Pets.material_num[i] = execDataIn.readByte();
                    System.out.println("Pets.material_num: " + Pets.material_num);
                    System.out.println("add componse");
                }
                MainCanvas.pet.quantity = execDataIn.readShort();
                if (MainCanvas.pet.quantity / 10 > 0) {
                    needMaterial = 2;
                } else {
                    needMaterial = 1;
                }
                Pets.curvires = execDataIn.readShort();
                Pets.needvires = execDataIn.readShort();
                MainCanvas.mc.setRightMenuSubState(22);
                if (MainCanvas.pet.curPetSkill.skillID == 3) {
                    MainCanvas.mc.setUIPetState((byte) 8);
                } else {
                    MainCanvas.mc.setUIPetState((byte) 7);
                }
                MainCanvas.mc.releaseUI();
                break;
            case 50335616:
                MainCanvas.pet.stuffImageId = execDataIn.readByte();
                if (MainCanvas.pet.stuffImageId == -1) {
                    execDataIn.readUTF();
                    execDataIn.readByte();
                } else {
                    MainCanvas.pet.stuffName = execDataIn.readUTF();
                    MainCanvas.pet.quantity = execDataIn.readShort();
                    Pets.tempdex = execDataIn.readInt();
                    Pets.tempvires = execDataIn.readShort();
                    Pets.tempmoney = execDataIn.readInt();
                    MainCanvas.pet.material = execDataIn.readByte();
                }
                if (MainCanvas.pet.stuffImageId == -1) {
                    MainCanvas.mc.baseForm.setAboutForm(null);
                    MainCanvas.setMessage(MainCanvas.mc.baseForm, "请选择合适的装备");
                    break;
                }
                if (MainCanvas.pet.stuffImageId == 0) {
                    MainCanvas.mc.baseForm.setAboutForm(null);
                    MainCanvas.mc.baseForm.addAboutForm("errorMsg", execDataIn.readUTF(), (byte) 1, 160, 0);
                    break;
                }
                MainCanvas.mc.setRightMenuSubState(22);
                MainCanvas.mc.setUIPetState((byte) 5);
                MainCanvas.mc.releaseUI();
                break;
            case 50336128:
                System.out.println("-----------------  back collection result!  -----------------");
                sb = new StringBuffer();
                result = execDataIn.readByte();
                switch (result) {
                    case 1:
                        sb.append("您获得了");
                        sb.append(execDataIn.readUTF());
                        tmp = execDataIn.readUTF();
                        sb.append(tmp.equals("") ? "" : ("，" + tmp));
                        sb.append("，获得熟练度" + execDataIn.readByte());
                        tmp = (execDataIn.readByte() == 1) ? "技能升级。" : "";
                        sb.append("，减少活力" + execDataIn.readShort() + "。");
                        sb.append(tmp);
                        break;
                    case 2:
                        sb.append("失败！您的采集技能级别太低！");
                        break;
                    case 3:
                        sb.append("失败！您的活力不足！");
                        break;
                    case 4:
                        sb.append("您的背包已满！");
                        break;
                    case 5:
                        sb.append("失败！您没有学习挖矿技能！");
                        break;
                    case 6:
                        sb.append("失败！您没有学习采集技能！");
                        break;
                    case 7:
                        sb.append("失败！您的采集量已经达到最大数量！");
                        break;
                    case -1:
                        sb.append("失败！物品不存在或已经被别人采集走！");
                        break;
                }
                System.out.println("-----------------    End ---------------------------");
                if (sb.length() > 0) {
                    PCChat.addChatScreen((byte) 7, sb.toString());
                    String ss = "系统:" + sb.toString();
                    PCChat.addChat((byte) 7, ss);
                }
                MainCanvas.chatNowString = sb.toString();
                break;
        }
    }

    public static byte[] compress(int commID) {
        ByteArray execDataOut = new ByteArray();
        switch (commID) {
            case 50331904:
                execDataOut.writeByte(MainCanvas.mc.petSkillId);
                break;
            case 50332928:
                execDataOut.writeByte(MainCanvas.pet.getCurSkillID());
                execDataOut.writeByte(MainCanvas.pet.curPetSkill.itemLevel);
                break;
            case 50333184:
                execDataOut.writeByte(MainCanvas.pet.getCurSkillID());
                execDataOut.writeByte(MainCanvas.pet.curPetSkill.itemLevel);
                execDataOut.writeShort(MainCanvas.pet.getCurSkillDetailID());
                break;
            case 50333440:
                if (MainCanvas.pet.curPetSkill.skillID == 3) {
                    execDataOut.writeByte(MainCanvas.pet.curPetSkill.skillID);
                    execDataOut.writeShort(MainCanvas.pet.curPetSkill.curItemId);
                    execDataOut.writeByte(1);
                    execDataOut.writeShort(MainCanvas.pet.quantity);
                    execDataOut.writeByte(0);
                    break;
                }
                execDataOut.writeByte(MainCanvas.pet.curPetSkill.skillID);
                execDataOut.writeShort(MainCanvas.pet.curPetSkill.curItemId);
                execDataOut.writeByte(needMaterial);
                execDataOut.writeShort(1);
                execDataOut.writeByte(MainCanvas.dramatisPackage.weaponPlace);
                break;
            case 50334720:
                execDataOut.writeByte(MainCanvas.pet.curPetSkill.skillID);
                execDataOut.writeShort(MainCanvas.pet.curPetSkill.curItemId);
                break;
            case 50334208:
                execDataOut.writeInt(ObjManager.currentTarget.objID);
                break;
            case 50334464:
                execDataOut.writeByte(MainCanvas.pet.forgetId);
                break;
        }
        return execDataOut.toByteArray();
    }
}
