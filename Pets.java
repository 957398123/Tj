//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Vector;

public class Pets {
    public static short maxvires;
    public static short curvires;
    public static short needvires;
    public static int tempdex;
    public static short tempvires;
    public static int tempmoney;
    public byte material;
    public static byte[] material_num;
    public byte forgetId = 0;
    public byte imageID = 0;
    public String petName = null;
    public byte petLevel = 0;
    public int maxPetExp = 0;
    public int curPetExp = 0;
    public short maxPetMp = 0;
    public short curPetMp = 0;
    public short needPetMp = 0;
    public byte hunger = 0;
    public byte loyalty = 0;
    public PetSkill[] skills = null;
    public PetSkill curPetSkill = null;
    public short quantity = 0;
    public byte stuffImageId = 0;
    public String stuffName = null;
    Vector compose = null;

    public Pets() {
    }

    public void addComposeDetail(byte quantity, String name) {
        if (this.compose == null) {
            this.compose = new Vector();
        }

        this.compose.addElement(name);
        this.compose.addElement(new Byte(quantity));
    }

    public String[] getSkillNames() {
        if (this.skills != null) {
            String[] names = new String[4];

            for(int i = 0; i < this.skills.length; ++i) {
                if (this.skills[i] != null) {
                    names[i] = this.skills[i].skillName;
                }
            }

            return names;
        } else {
            return null;
        }
    }

    public void addSkillAndLevel(byte id1, byte id, String name, byte level, int curSkillExp, int maxSkillExp, byte imId) {
        if (this.skills == null) {
            this.skills = new PetSkill[4];
        }

        if (this.skills[id1] == null) {
            this.skills[id1] = new PetSkill(id, name, level, curSkillExp, maxSkillExp, imId);
        } else {
            this.skills[id1].skillID = id;
            this.skills[id1].skillName = name;
            this.skills[id1].skillLevel = level;
            this.skills[id1].curSkillExp = curSkillExp;
            this.skills[id1].maxSkillExp = maxSkillExp;
            this.skills[id1].imageID = imId;
        }

    }

    public void setCurSkillAndLevel(String name, byte level) {
        if (this.skills != null) {
            this.setCurSkill(name);
            if (this.curPetSkill != null) {
                this.curPetSkill.setItemLevel(level);
            }
        }
    }

    public void setCurSkill(String name) {
        if (this.skills != null) {
            for(int i = 0; i < this.skills.length; ++i) {
                if (this.skills[i] != null && name == this.skills[i].skillName) {
                    this.curPetSkill = this.skills[i];
                    break;
                }
            }

        }
    }

    public String getCurSkillName() {
        return this.curPetSkill != null ? this.curPetSkill.skillName : null;
    }

    public short getCurSkillID() {
        return this.curPetSkill != null ? (short)this.curPetSkill.skillID : -1;
    }

    public void addSkillDetail(short id, String name) {
        if (this.curPetSkill != null) {
            this.curPetSkill.addItem(id, name);
        }

    }

    public String[] getSkillDetail() {
        return this.curPetSkill == null ? null : this.curPetSkill.itemNames;
    }

    public String getPetSkillDetailName(short id) {
        return this.curPetSkill == null ? null : this.curPetSkill.getPetSkillDetailName(id);
    }

    public short getPetSkillDetailID(String name) {
        return this.curPetSkill == null ? -1 : this.curPetSkill.getPetSkillDetailID(name);
    }

    public void setSkillIndexAndLevelIndex(int skillIndex, int levelIndex) {
        this.curPetSkill = this.skills[skillIndex];
        this.curPetSkill.setItemLevel((byte)levelIndex);
    }

    public short getCurSkillDetailID() {
        return this.curPetSkill.curItemId;
    }

    public void addComposite(byte id1, byte id, String name, byte level, int maxdex, int curdex, byte imId) {
        if (this.skills == null) {
            this.skills = new PetSkill[4];
        }

        if (this.skills[id1] == null) {
            System.out.println("---------test ------- addComposite");
            this.skills[id1] = new PetSkill(id, name, level, curdex, maxdex, imId);
        } else {
            this.skills[id1].skillID = id;
            this.skills[id1].skillName = name;
            this.skills[id1].skillLevel = level;
            this.skills[id1].curSkillExp = curdex;
            this.skills[id1].maxSkillExp = maxdex;
            this.skills[id1].imageID = imId;
        }

    }

    public class PetSkill {
        public String skillName = null;
        public byte skillID = 0;
        public byte imageID = 0;
        public int maxSkillExp = 0;
        public int curSkillExp = 0;
        public byte skillLevel = 0;
        byte itemLevel = 0;
        String[] itemNames = null;
        short[] itemIDs = null;
        short curItemId = 0;
        int curIndex = 0;

        public PetSkill(byte id, String name, byte level, int curSkillExp, int maxSkillExp, byte imId) {
            this.skillName = name;
            this.skillID = id;
            this.skillLevel = level;
            this.curSkillExp = curSkillExp;
            this.maxSkillExp = maxSkillExp;
            this.imageID = imId;
        }

        void setItemLevel(byte level) {
            if (level > 0 && level <= this.skillLevel) {
                this.itemLevel = level;
                this.itemNames = null;
                this.itemIDs = null;
                this.itemNames = new String[60];
                this.itemIDs = new short[60];
            }

        }

        void addItem(short id, String name) {
            this.itemNames[this.curIndex] = name;
            this.itemIDs[this.curIndex] = id;
            ++this.curIndex;
        }

        String getPetSkillDetailName(short id) {
            if (this.itemIDs == null) {
                return null;
            } else {
                for(int i = 0; i < this.itemIDs.length && this.itemIDs[i] != 0; ++i) {
                    if (id == this.itemIDs[i]) {
                        return this.itemNames[i];
                    }
                }

                return null;
            }
        }

        short getPetSkillDetailID(String name) {
            if (this.itemNames == null) {
                return -1;
            } else {
                for(int i = 0; i < this.itemNames.length && this.itemNames != null; ++i) {
                    if (this.itemNames[i] == name) {
                        return this.itemIDs[i];
                    }
                }

                return -1;
            }
        }
    }
}
