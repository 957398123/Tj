import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

public final class MainEntry extends MIDlet {

    public Display display = null;
    private MainCanvas mc = null;
    
    /**
     * 程序入口
     */
    protected void startApp() {
        // 获取当前显示
        display = Display.getDisplay(this);
        try {
            if (mc == null) {
                // 创建主画面
                mc = new MainCanvas(this);
                // 启动游戏主逻辑
                mc.begin();
            }
            // 设置显示画面
            display.setCurrent(mc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void pauseApp() {
    }
    
    protected void destroyApp(boolean unconditional) {
        exitMIDlet();
    }

    public void exitMIDlet() {
        notifyDestroyed();
    }
}
