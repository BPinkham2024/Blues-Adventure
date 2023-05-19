package core;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gamestates.Gamestate;

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
        switch(Gamestate.state) {
            case MENU:
                panel.getMenu().keyPressed(e);
                break;
            case PLAYING:
                panel.getPlaying().keyPressed(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(Gamestate.state) {
            case MENU:
                panel.getMenu().keyReleased(e);
                break;
            case PLAYING:
                panel.getPlaying().keyReleased(e);
                break;
            default:
                break;
        }
    }
    
}