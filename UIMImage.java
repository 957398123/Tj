import javax.microedition.lcdui.Graphics;

public class UIMImage extends UIComponent {
    public static byte sign = 0;
    public static byte sign1 = 0;
    public static final byte MIMG_STOP = 0;
    public static final byte MIMG_MOVE = 1;
    private byte type = 0;
    public MImage mImg = null;
    private MImage mImageBack = null;
    private byte backFrame = 0;
    private byte currentFrame = 0;
    private byte count = 0;
    private byte cell = 2;
    private boolean isHaveRim = false;
    private boolean isBackVeins = false;
    private boolean isMirror = false;
    private UIMenu subMenu = null;
    private String name = null;
    private byte[] frames;
    public boolean isLock = false;
    public boolean isLock3 = false;
    public byte index = -1;
    private byte number = 0;
    private boolean showNumber = true;

    public UIMImage(int x, int y, int w, int h, MImage mImg, byte type) {
        super(x, y, w, h);
        this.type = type;
        this.setImage(mImg);
    }

    public void setNumberVisiable(boolean flag) {
        this.showNumber = flag;
    }

    public void setImage(MImage mImg) {
        this.mImg = mImg;
        if (mImg != null) {
            this.width = mImg.frame_w * CURR_W / 176 + 1;
            this.height = mImg.frame_h * CURR_H / 208 + 1;
        }

    }

    public void setHaveRim(boolean ri) {
        this.isHaveRim = ri;
    }

    public void setBackVeins(MImage mb, byte bf) {
        this.isBackVeins = true;
        this.mImageBack = mb;
        this.backFrame = bf;
    }

    public void setFrequency(byte ce) {
        if (ce > 0) {
            this.cell = ce;
        }
    }

    public void setFrames(byte[] frms) {
        this.frames = frms;
    }

    public void setCurrentFrame(byte frame) {
        this.currentFrame = frame;
    }

    public byte getCurrentFrame() {
        return this.currentFrame;
    }

    public void draw(Graphics g) {
        if (++this.count >= 120) {
            this.count = 0;
        }

        g.setClip(0, 0, MainCanvas.screenW, MainCanvas.screenH);
        switch (this.type) {
            case 0:
                if (this.isHaveRim) {
                    UIRim.drawRim(g, this.positionX, this.positionY, this.width, this.height, (byte)0);
                }

                if (this.isBackVeins && this.mImageBack != null) {
                    this.mImageBack.draw(g, this.positionX + (this.width - this.mImg.frame_w >> 1) + 1, this.positionY + (this.height - this.mImg.frame_h >> 1) + 1, this.backFrame, false);
                    if (this.mImg != null && MainCanvas.equipStuffId[this.index] > 0) {
                        this.mImg.draw(g, this.positionX + (this.width - this.mImg.frame_w >> 1) + 1, this.positionY + (this.height - this.mImg.frame_h >> 1) + 1, MainCanvas.equipImageId[this.index] - 1, false);
                    }
                } else if (this.mImg != null && this.currentFrame >= 0) {
                    this.mImg.draw(g, this.positionX + (this.width - this.mImg.frame_w >> 1) + 1, this.positionY + (this.height - this.mImg.frame_h >> 1) + 1, this.currentFrame, this.isMirror);
                }

                if (this.number > 0 && this.showNumber) {
                    Util.drawNumber(g, this.number, this.positionX + this.width - 8, this.positionY + this.height - 7);
                }

                if (this.isLock3) {
                    g.setColor(15396515);
                    g.drawRect(this.positionX, this.positionY, this.width, this.height);
                }
                break;
            case 1:
                if (this.count % this.cell == 0) {
                    ++this.currentFrame;
                }

                if (this.currentFrame >= this.frames.length) {
                    this.currentFrame = 0;
                }

                if (this.mImg != null) {
                    if (this.frames[this.currentFrame] < 0) {
                        this.mImg.draw(g, this.positionX, this.positionY, -this.frames[this.currentFrame], true);
                    } else {
                        this.mImg.draw(g, this.positionX, this.positionY, this.frames[this.currentFrame], false);
                    }
                }
        }

        if (this.focus) {
            g.setColor(16770362);
            if (this.count % 2 == 0) {
                g.drawRect(this.positionX, this.positionY, this.width, this.height);
            } else {
                g.drawRect(this.positionX + 1, this.positionY + 1, this.width - 2, this.height - 2);
            }
        }

        if (this.subMenu != null) {
            this.subMenu.draw(g);
        }

    }

    public void setSubMenu(UIMenu sonMenu) {
        this.subMenu = sonMenu;
        if (this.subMenu != null) {
            int x1 = this.positionX + this.width - 20;
            int x2 = 176 - this.subMenu.width - 5;
            int y1 = this.positionY + 6 + charH;
            int y2 = MainCanvas.screenH - charH - 7 - this.subMenu.height;
            this.subMenu.setXY(x1 > x2 ? x2 : x1, y1 < y2 ? y1 : y2);
            this.subMenu.setFocus(true);
        }
    }

    public UIComponent getAroundComponent(byte direction) {
        switch (direction) {
            case 1:
                if (this.subMenu != null) {
                    this.subMenu.up();
                } else {
                    if (!this.isLock) {
                        return this.upComponent;
                    }

                    if (this.upComponent instanceof UIMImage) {
                        return this.upComponent;
                    }
                }
                break;
            case 2:
                if (this.subMenu != null) {
                    this.subMenu.down();
                } else {
                    if (!this.isLock) {
                        return this.downComponent;
                    }

                    if (this.downComponent instanceof UIMImage) {
                        return this.downComponent;
                    }
                }
                break;
            case 3:
                if (this.subMenu != null) {
                    return null;
                }

                if (!this.isLock) {
                    return this.leftComponent;
                }

                if (this.leftComponent instanceof UIMImage) {
                    return this.leftComponent;
                }
                break;
            case 4:
                if (this.subMenu != null) {
                    return null;
                }

                if (!this.isLock) {
                    return this.rightComponent;
                }

                if (this.rightComponent instanceof UIMImage) {
                    return this.rightComponent;
                }
        }

        return null;
    }

    public UIMenu getSubMenu() {
        return this.subMenu;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public void setNumber(byte number) {
        this.number = number;
    }

    public byte getNumber() {
        return this.number;
    }

    public void setMirror(boolean b) {
        this.isMirror = b;
    }
}
