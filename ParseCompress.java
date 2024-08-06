
public class ParseCompress {

    private static final byte NO_NETCOMMD_ID = 8;
    private static final byte HEAD_INFO_LENGTH = 4;
    public static final byte TYPE_LOGIN = 1;
    public static final byte TYPE_GAMEOBJ = 2;
    public static final byte TYPE_PET = 3;
    public static final byte TYPE_PACKAGE = 4;
    public static final byte TYPE_CHAT = 5;
    public static final byte TYPE_TEAM = 6;
    public static final byte TYPE_SKILLTREE = 7;
    public static final byte TYPE_ATTRIBUTE = 8;
    public static final byte TYPE_NPC = 9;
    public static final byte TYPE_MAIL = 11;
    public static final byte TYPE_FRIEND = 12;
    public static final byte TYPE_BUSINESS = 13;
    public static final byte TYPE_AUCTION = 14;
    public static final byte TYPE_CLAN = 15;
    public static final byte TYPE_OTHER = 10;
    public static final byte TYPE_PK = 16;
    public static final byte TYPE_MAP = 32;
    public static final byte TYPE_MARRIAGE = 64;
    public static final byte TYPE_SERVER = 80;
    public static final byte TYPE_TOP = 96;
    public static final byte TYPE_AWARD = 112;
    private static final byte TYPE_ARENA = 17;
    public static final byte TYPE_GENS = 18;

    public static boolean parse(byte[] data) {
        ByteArray dis = new ByteArray(data);
        // 前两个应该是魔法数
        int magic = dis.readByte();
        dis.readByte();
        boolean r = false;
        if (magic == 8) {
            // 读取命令ID
            int commID = dis.readInt();
            // 读取命令内容
            byte[] tmpData = dis.readByteArray(data.length - 4);
            // 回收dis
            dis = null;
            byte type = getType(commID);
            switch (type) {
                case 1: {
                    PCLogin.parse(commID, tmpData);
                    break;
                }
                case 32: {
                    PCChangeMap.parse(commID, tmpData);
                    break;
                }
                case 2:
                case 16: {
                    PCGameObj.parse(commID, tmpData);
                    break;
                }
                case 3: {
                    PCPet.parse(commID, tmpData);
                    break;
                }
                case 4:
                case 10: {
                    PCPackage.parse(commID, tmpData);
                    break;
                }
                case 7: {
                    PCSkillTree.parse(commID, tmpData);
                    break;
                }
                case 8: {
                    PCAttribute.parse(commID, tmpData);
                    break;
                }
                case 13: {
                    PCBusiness.parse(commID, tmpData);
                    break;
                }
                case 9:
                case 15:
                case 96:
                case 112: {
                    PCNPC.parse(commID, tmpData);
                    break;
                }
                case 14: {
                    PCAuction.parse(commID, tmpData);
                    break;
                }
                case 6:
                case 12: {
                    PCFriend.parse(commID, tmpData);
                    break;
                }
                case 11: {
                    PCMail.parse(commID, tmpData);
                    break;
                }
                case 5: {
                    PCChat.parse(commID, tmpData);
                    break;
                }
                case 80: {
                    PCIncrementService.parse(commID, tmpData);
                    break;
                }
                case 64: {
                    PCMarriage.parse(commID, tmpData);
                    break;
                }
                case 17: {
                    PCArena.getInstance().parse(commID, tmpData);
                    break;
                }
                case 18: {
                    ClanWar.getInstance().parse(commID, tmpData);
                    break;
                }
            }
        }else{
            System.out.println("[处理命令]:接收到无效命令！");
        }
        return r;
    }

    public static byte[] compress(int commID) {
        System.out.println("发送 command:" + Integer.toHexString(commID));
        byte[] tmp = null;
        switch (getType(commID)) {
            case 1:
                tmp = PCLogin.compress(commID);
                break;
            case 32:
                tmp = PCChangeMap.compress(commID);
                break;
            case 2:
            case 16:
                tmp = PCGameObj.compress(commID);
                break;
            case 3:
                tmp = PCPet.compress(commID);
                break;
            case 4:
            case 10:
                tmp = PCPackage.compress(commID);
                break;
            case 7:
                tmp = PCSkillTree.compress(commID);
                break;
            case 8:
                tmp = PCAttribute.compress(commID);
                break;
            case 13:
                tmp = PCBusiness.compress(commID);
                break;
            case 9:
            case 15:
            case 96:
            case 112:
                tmp = PCNPC.compress(commID);
                break;
            case 14:
                tmp = PCAuction.compress(commID);
                break;
            case 6:
            case 12:
                tmp = PCFriend.compress(commID);
                break;
            case 11:
                tmp = PCMail.compress(commID);
                break;
            case 5:
                tmp = PCChat.compress(commID);
                break;
            case 80:
                tmp = PCIncrementService.compress(commID);
                break;
            case 64:
                tmp = PCMarriage.compress(commID);
                break;
            case 17:
                tmp = PCArena.getInstance().compress(commID);
                break;
            case 18:
                tmp = ClanWar.getInstance().compress(commID);
                break;
        }
        return tmp;
    }

    /**
     * 解析命令类型
     *
     * @param num
     * @return
     */
    private static byte getType(int num) {
        return (byte) (num >> 24);
    }
}
