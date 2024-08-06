//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Enumeration;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public class UISkillTree extends UIComponent {
    public static short[][] SKILL_TREE = new short[][]{{1, 2, 7, 6, 11, 16, 12, 8, 19, 18, 17, 13, 0, 5}, {6, 11, 16, 21, 15, 8, 13, 18, 23, 19, 5, 7, 12, 17}, {7, 12, 22, 16, 15, 21, 8, 23, 18, 13, 19, 5, 10, 17}, {7, 12, 11, 17, 22, 21, 8, 13, 18, 23, 24, 9, 14, 6}};
    public static byte[][] SKILL_IMAGE = new byte[][]{{28, 12, 14, 3, 0, 15, 11, 10, 23, 13, 30, 34, 19, 35}, {34, 22, 24, 10, 19, 8, 9, 2, 1, 33, 31, 17, 23, 27}, {35, 7, 30, 33, 25, 4, 20, 18, 34, 32, 27, 10, 13, 3}, {21, 29, 20, 26, 28, 18, 15, 34, 11, 5, 17, 3, 24, 32}};
    public static final byte STYLE_TREE = 0;
    public static final byte STYLE_TABLE = 1;
    private final int cols = 5;
    private final int rows = 5;
    private int perWidth = 0;
    private int perHeight = 0;
    private int cellWidth = 0;
    private int cellHeight = 0;
    private int humanLevel = 0;
    private Vector skills = new Vector();
    private Vector arrows = new Vector();
    private Enumeration e = null;
    private MImage arrowImg = null;
    private Skill focusSkill = null;
    private MImage skillImg = null;
    private short skillPointNum = 0;
    private byte kind = 0;
    private short skillBasePoint = 0;
    private static final byte DXY = 3;
    private byte WH;

    public UISkillTree(int x, int y, int w, int h, int humanLevel) {
        super(x, y, w, h);
        this.WH = (byte)(18 * CURR_W / 176);
        this.humanLevel = humanLevel;
        if (this.WH > 18) {
            ++this.WH;
        }

    }

    public void addCell(int skillId, byte skillImgIndex, int skillLevel, int preSkillLevel, int preSkillId, int needHumanLevel, int index) {
        Skill skill = new Skill(skillId, skillImgIndex, skillLevel, preSkillId, preSkillLevel, needHumanLevel, index);
        this.skills.addElement(skill);
    }

    public void addCell(int skillId, byte skillImgIndex, int skillLevel, int preSkillLevel, int preSkillId, int needHumanLevel, int index, byte maxLevel) {
        Skill skill = new Skill(skillId, skillImgIndex, skillLevel, preSkillId, preSkillLevel, needHumanLevel, index, maxLevel);
        this.skills.addElement(skill);
    }

    public void insertCell(int istIndex, int skillId, byte skillImgIndex, int skillLevel, int preSkillLevel, int preSkillId, int needHumanLevel, int index) {
        Skill skill = new Skill(skillId, skillImgIndex, skillLevel, preSkillId, preSkillLevel, needHumanLevel, index);
        this.skills.insertElementAt(skill, istIndex);
    }

    public void setArrowImage(MImage mimg) {
        if (mimg != null) {
            this.arrowImg = mimg;
        }

    }

    public void setSkillPoints(int num) {
        if (num >= 0) {
            this.skillPointNum = (short)num;
        }
    }

    public void setBaseSkillPoint() {
        this.skillBasePoint = this.skillPointNum;
    }

    public int getCurrSkillLevel() {
        return this.focusSkill == null ? 0 : this.focusSkill.skillLevel;
    }

    public int getMaxSkillLevel() {
        return this.focusSkill == null ? 0 : this.focusSkill.maxLevel;
    }

    public int getCurrSkillId() {
        return this.focusSkill.skillId + 1;
    }

    public boolean isCurrSkillValid() {
        return this.focusSkill == null ? false : this.focusSkill.valid;
    }

    public void setStyle(byte style) {
        this.kind = style;
        if (this.kind == 0 && this.skills.size() == 18) {
            this.skills.removeElementAt(0);
            this.skills.removeElementAt(this.skills.size() - 1);
            this.skills.removeElementAt(this.skills.size() - 1);
            this.skills.removeElementAt(this.skills.size() - 1);
            this.focusSkill = null;
        }

    }

    public byte[] getAllSkillsLevel() {
        byte[] data = new byte[this.skills.size()];

        for(int i = 0; i < data.length; ++i) {
            data[i] = (byte)((Skill)this.skills.elementAt(i)).skillLevel;
        }

        return data;
    }

    public short getSkillPoints() {
        return this.skillPointNum;
    }

    public void setSkillImage(MImage mimg) {
        if (mimg != null) {
            this.skillImg = mimg;
        }

    }

    public boolean addCurrentSkillLevel() {
        if (this.focusSkill == null) {
            return false;
        } else if (this.focusSkill.skillLevel >= this.focusSkill.maxLevel) {
            return false;
        } else if (!this.focusSkill.valid) {
            return false;
        } else if (this.skillPointNum < 1) {
            return false;
        } else {
            ++this.focusSkill.skillLevel;
            --this.skillPointNum;
            this.refreshTree();
            return true;
        }
    }

    public boolean removeCurrentSkillLevel() {
        if (this.focusSkill == null) {
            return false;
        } else if (this.focusSkill.skillLevel <= 0) {
            return false;
        } else if (!this.focusSkill.valid) {
            return false;
        } else if (this.focusSkill.skillLevel <= this.focusSkill.skillBaseLevel) {
            return false;
        } else {
            --this.focusSkill.skillLevel;
            ++this.skillPointNum;
            this.refreshTree();
            return true;
        }
    }

    public void draw(Graphics g) {
        this.drawCell(g);
        if (this.kind == 0) {
            this.drawArrow(g);
        }

        g.setColor(16776960);
        if (this.focus && this.focusSkill != null) {
            g.drawRect(this.focusSkill.positionX - 1, this.focusSkill.positionY - 1, this.perWidth + 2, this.perHeight + 2);
        }

    }

    public void refreshTree() {
        this.e = this.skills.elements();

        while(true) {
            while(this.e.hasMoreElements()) {
                Skill s = (Skill)this.e.nextElement();
                if (s.preSkill == null) {
                    if (this.humanLevel >= s.needHumanLevel) {
                        s.valid = true;
                    }
                } else if (s.preSkill.skillLevel >= s.preSkillLevel && this.humanLevel >= s.needHumanLevel) {
                    s.valid = true;
                } else {
                    s.valid = false;
                    if (s.preSkill.skillLevel < s.preSkillLevel) {
                        this.skillPointNum = (short)(this.skillPointNum + s.skillLevel);
                        s.skillLevel = 0;
                    }
                }
            }

            if (this.kind == 1) {
                short[][] data = (short[][])null;
                switch (Player.getInstance().profession) {
                    case 1:
                        data = Cons.SKILL_SWORDMAN;
                        break;
                    case 2:
                        data = Cons.SKILL_TAOIST;
                        break;
                    case 3:
                        data = Cons.SKILL_APOTHECARY;
                        break;
                    case 4:
                        data = Cons.SKILL_ASSASSIN;
                }

                for(int i = 0; i < this.skills.size(); ++i) {
                    Skill s = (Skill)this.skills.elementAt(i);
                    if (s.skillId == -1) {
                        s.valid = true;
                    } else if (s.skillId >= 14) {
                        switch (s.skillId) {
                            case 14:
                                s.valid = PCSkillTree.canUseRed;
                                break;
                            case 15:
                                s.valid = PCSkillTree.canUseBlue;
                                break;
                            case 16:
                                s.valid = PCSkillTree.canUsePurple;
                        }
                    } else if (s.valid && (s.skillLevel == 0 || data[s.skillId + 1][0] == 2 || data[s.skillId + 1][0] == 1)) {
                        s.valid = false;
                    }
                }
            }

            return;
        }
    }

    public void setXY(int x, int y) {
        this.positionX = x;
        this.positionY = y;
        this.initTree();
    }

    public UIComponent getAroundComponent(byte direction) {
        switch (direction) {
            case 1:
                if (this.kind == 0) {
                    if (this.focusSkill.index >= 5) {
                        this.findIndex(this.focusSkill.index - 5);
                    }
                } else if (this.kind == 1) {
                    if (this.focusSkill.skillId < 8) {
                        return this.upComponent;
                    }

                    this.focusSkill = (Skill)this.skills.elementAt(this.focusSkill.skillId + 1 - 9);
                }

                return null;
            case 2:
                if (this.kind == 0) {
                    if (this.focusSkill.index < 20) {
                        this.findIndex(this.focusSkill.index + 5);
                    }
                } else if (this.kind == 1) {
                    if (this.focusSkill.skillId >= 8) {
                        return this.downComponent;
                    }

                    this.focusSkill = (Skill)this.skills.elementAt(this.focusSkill.skillId + 1 + 9);
                }

                return null;
            case 3:
                if (this.kind == 0) {
                    if (this.focusSkill.index % 5 != 0) {
                        this.findIndex(this.focusSkill.index - 1);
                    }
                } else if (this.kind == 1) {
                    if (this.focusSkill.skillId < 0) {
                        return this.leftComponent;
                    }

                    this.focusSkill = (Skill)this.skills.elementAt(this.focusSkill.skillId);
                }

                return null;
            case 4:
                if (this.kind == 0) {
                    if (this.focusSkill.index % 5 != 4) {
                        this.findIndex(this.focusSkill.index + 1);
                    }
                } else if (this.kind == 1) {
                    if (this.focusSkill.skillId >= 16) {
                        return this.rightComponent;
                    }

                    this.focusSkill = (Skill)this.skills.elementAt(this.focusSkill.skillId + 2);
                }

                return null;
            default:
                return null;
        }
    }

    private boolean findIndex(int index) {
        this.e = this.skills.elements();

        Skill s;
        do {
            if (!this.e.hasMoreElements()) {
                return false;
            }

            s = (Skill)this.e.nextElement();
        } while(s.index != index);

        this.focusSkill = s;
        return true;
    }

    public boolean isChange() {
        return this.skillBasePoint != this.skillPointNum;
    }

    private void initTree() {
        this.perWidth = this.WH - 1;
        this.perHeight = this.WH - 1;
        this.cellWidth = this.width / 5;
        this.cellHeight = this.height / 5;
        if (this.width < this.perWidth * 5) {
            this.width = this.perWidth * 5;
        }

        if (this.height < this.perHeight * 5) {
            this.height = this.perHeight * 5;
        }

        if (!this.arrows.isEmpty()) {
            this.arrows.removeAllElements();
        }

        this.e = this.skills.elements();

        while(this.e.hasMoreElements()) {
            Skill s = (Skill)this.e.nextElement();
            int dx = this.cellWidth - this.perWidth;
            int dy = this.cellHeight - this.perHeight;
            s.setXY(s.index % 5 * this.cellWidth + (dx >> 1) + this.positionX, s.index / 5 * this.cellHeight + (dy >> 1) + this.positionY);
            if (s.preSkillId <= 0) {
                if (this.focusSkill == null) {
                    this.focusSkill = s;
                }
            } else {
                if (this.kind == 0) {
                    s.setPreSkill((Skill)this.skills.elementAt(s.preSkillId - 1));
                } else {
                    s.setPreSkill((Skill)this.skills.elementAt(s.preSkillId));
                }

                short[] data = new short[3];
                switch (s.index - s.preSkill.index) {
                    case -1:
                        data[0] = (short)(s.positionX + this.perWidth + 3);
                        data[1] = (short)(s.positionY + (this.perHeight - this.arrowImg.frame_h >> 1));
                        data[2] = 0;
                        break;
                    case 1:
                        data[0] = (short)(s.positionX - this.arrowImg.frame_w - 2);
                        data[1] = (short)(s.positionY + (this.perHeight - this.arrowImg.frame_h >> 1));
                        data[2] = 2;
                        break;
                    case 5:
                        data[0] = (short)(s.positionX + (this.perWidth - this.arrowImg.frame_w >> 1) + 1);
                        data[1] = (short)(s.positionY - this.arrowImg.frame_h - 2);
                        data[2] = 1;
                }

                this.arrows.addElement(data);
            }
        }

        if (this.kind == 1) {
            if (this.skills.size() == 18) {
                for(int i = 0; i < 18; ++i) {
                    Skill s = (Skill)this.skills.elementAt(i);
                    s.positionX = this.positionX + i % 9 * this.WH + 3;
                    s.positionY = this.positionY + 3 + i / 9 * this.WH;
                }
            }

            this.focusSkill = (Skill)this.skills.firstElement();
        }

        this.refreshTree();
    }

    private void drawCell(Graphics g) {
        if (this.kind == 1) {
            UIRim.drawRim(g, this.positionX, this.positionY, 9 * this.WH + 4, 2 * this.WH + 4, (byte)0);
            g.setClip(this.positionX + 1, this.positionY, 9 * this.WH + 2, 2 * this.WH + 3);
            g.drawLine(this.positionX + 2, this.positionY + 2, 9 * this.WH + 8, this.positionY + 2);
            g.drawLine(this.positionX + 2, this.positionY + this.WH + 2, 9 * this.WH + 8, this.positionY + this.WH + 2);
            g.drawLine(this.positionX + 2, this.positionY + this.WH * 2 + 2, 9 * this.WH + 8, this.positionY + this.WH * 2 + 2);

            for(int i = 0; i < 10; ++i) {
                g.drawLine(this.positionX + 2 + i * this.WH, this.positionY + 3, this.positionX + 2 + i * this.WH, this.positionY + this.WH * 2 + 2);
            }
        }

        this.e = this.skills.elements();

        while(this.e.hasMoreElements()) {
            Skill s = (Skill)this.e.nextElement();
            if (this.kind == 0) {
                UIRim.drawRim(g, s.positionX, s.positionY, this.perWidth, this.perHeight, (byte)1);
            }

            if (s.skillId < 14) {
                if (this.skillImg != null) {
                    this.skillImg.draw(g, s.positionX + 1 + 3, s.positionY + 1 + 3, s.indexImg, false);
                }

                if (s.valid && s.skillLevel != 0 && s.skillLevel != 99) {
                    Util.drawNumber(g, (byte)s.skillLevel, s.positionX + this.perWidth - 9, s.positionY + this.perHeight - 7);
                }
            } else if (MainCanvas.mImgStuff != null) {
                MainCanvas.mImgStuff.draw(g, s.positionX + 1 + 3, s.positionY + 1 + 3, s.indexImg, false);
            }

            if (!s.valid) {
                if (UIGameRun.imgSkillCannotUse == null) {
                    UIGameRun.imgSkillCannotUse = Util.loadImage(Util.readPKG("/uiuse.pkg", "skillpanle.png"));
                }

                g.drawImage(UIGameRun.imgSkillCannotUse, s.positionX + 2 + 3, s.positionY + 2 + 3, 20);
            }
        }

    }

    private void drawArrow(Graphics g) {
        if (this.arrowImg != null) {
            this.e = this.arrows.elements();

            while(this.e.hasMoreElements()) {
                short[] data = (short[])((short[])this.e.nextElement());
                if (this.arrowImg != null) {
                    g.setColor(8947554);
                    byte dx = 5;
                    byte dy = 11;
                    switch (data[2]) {
                        case 0:
                            g.drawLine(data[0] + 9, data[1] + 3, data[0] + 9 + dx, data[1] + 3);
                            g.drawLine(data[0] + 9, data[1] + 6, data[0] + 9 + dx, data[1] + 6);
                            break;
                        case 1:
                            g.drawLine(data[0] + 3, data[1], data[0] + 3, data[1] - dy);
                            g.drawLine(data[0] + 6, data[1], data[0] + 6, data[1] - dy);
                            break;
                        case 2:
                            g.drawLine(data[0] - dx, data[1] + 3, data[0], data[1] + 3);
                            g.drawLine(data[0] - dx, data[1] + 6, data[0], data[1] + 6);
                    }

                    this.arrowImg.draw(g, data[0], data[1], data[2], false);
                }
            }

        }
    }

    public void resetLevels() {
        Skill skill;
        for(this.e = this.skills.elements(); this.e.hasMoreElements(); skill.skillLevel = skill.skillBaseLevel) {
            skill = (Skill)this.e.nextElement();
        }

        this.skillPointNum = this.skillBasePoint;
    }

    public void updateLevels() {
        Skill skill;
        for(this.e = this.skills.elements(); this.e.hasMoreElements(); skill.skillBaseLevel = skill.skillLevel) {
            skill = (Skill)this.e.nextElement();
        }

        this.skillBasePoint = this.skillPointNum;

        for(int i = 0; i < 14; ++i) {
            skill = (Skill)this.skills.elementAt(i);
            Player.skillLevels[i] = (byte)skill.skillLevel;
        }

    }

    public String getCurrentSkillCondition() {
        if (this.focusSkill == null || this.focusSkill.preSkillLevel == 0 && this.focusSkill.needHumanLevel == 0) {
            return "";
        } else {
            StringBuffer sb = new StringBuffer();
            if (this.focusSkill.needHumanLevel != 0) {
                sb.append("\n需人物");
                sb.append(this.focusSkill.needHumanLevel);
                sb.append("级");
            }

            if (this.focusSkill.preSkillLevel != 0) {
                sb.append("\n需");
                sb.append(this.focusSkill.preSkill.skillName);
                sb.append(" ");
                sb.append(this.focusSkill.preSkillLevel);
                sb.append("级");
            }

            return sb.toString();
        }
    }

    private class Skill {
        int positionX;
        int positionY;
        int skillId;
        int preSkillId;
        int preSkillLevel;
        int needHumanLevel;
        int skillLevel;
        int skillBaseLevel;
        boolean valid;
        Skill preSkill;
        int index;
        byte indexImg;
        String skillName;
        byte maxLevel;

        public Skill(int Id, byte skillImgIndex, int skilllevel, int preId, int preLevel, int humanLevel, int Index) {
            this.positionX = 0;
            this.positionY = 0;
            this.skillId = 0;
            this.preSkillId = 0;
            this.preSkillLevel = 0;
            this.needHumanLevel = 0;
            this.skillLevel = 0;
            this.skillBaseLevel = 0;
            this.valid = false;
            this.preSkill = null;
            this.index = 0;
            this.indexImg = 0;
            this.skillName = null;
            this.maxLevel = 0;
            this.skillId = Id;
            this.preSkillId = preId;
            this.preSkillLevel = preLevel;
            this.needHumanLevel = humanLevel;
            this.index = Index;
            this.indexImg = skillImgIndex;
            this.skillLevel = skilllevel;
            this.skillBaseLevel = this.skillLevel;
            if (this.skillLevel != 0) {
                this.valid = true;
            }

            String[] skillNames = null;
            switch (Player.getInstance().profession) {
                case 1:
                    skillNames = Cons.STR_SWORDMAN_SKILL;
                    break;
                case 2:
                    skillNames = Cons.STR_TAOIST_SKILL;
                    break;
                case 3:
                    skillNames = Cons.STR_APOTHECARY_SKILL;
                    break;
                case 4:
                    skillNames = Cons.STR_ASSASSIN_SKILL;
            }

            if (skillNames == null) {
                this.skillName = "";
            } else if (this.skillId < 14) {
                this.skillName = skillNames[this.skillId + 1];
            }

        }

        public Skill(int Id, byte skillImgIndex, int skilllevel, int preId, int preLevel, int humanLevel, int Index, byte maxLevel) {
            this(Id, skillImgIndex, skilllevel, preId, preLevel, humanLevel, Index);
            this.maxLevel = maxLevel;
        }

        public void setXY(int x, int y) {
            this.positionX = x;
            this.positionY = y;
        }

        public void setPreSkill(Skill pre) {
            if (pre != null) {
                this.preSkill = pre;
            }
        }
    }
}
