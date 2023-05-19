
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputs implements KeyListener {

    UserPanel panel;

    public KeyInputs(UserPanel panel) {
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
                panel.getPlayer().setUp(true);
                break;
            case KeyEvent.VK_A:
                panel.getPlayer().setMovingLeft(true);
                break;
            case KeyEvent.VK_S:
                panel.getPlayer().setDown(true);
                break;
            case KeyEvent.VK_D:
                panel.getPlayer().setMovingRight(true);
                break;
            case KeyEvent.VK_SPACE:
                panel.getPlayer().setJump(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
                panel.getPlayer().setUp(false);
                break;
            case KeyEvent.VK_A:
                panel.getPlayer().setMovingLeft(false);
                break;
            case KeyEvent.VK_S:
                panel.getPlayer().setDown(false);
                break;
            case KeyEvent.VK_D:
                panel.getPlayer().setMovingRight(false);
                break;
            case KeyEvent.VK_SPACE:
                panel.getPlayer().setJump(false);
        }
    }
    
}