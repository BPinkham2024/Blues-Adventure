package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import core.UserPanel;

public class Menu extends State implements StateMethods {

    public Menu(UserPanel game) {
        super(game);
    }

    @Override
    public void update() {
        
    }

    @Override
    public void draw(Graphics g) {
        g.fillRect(0, 0, 100, 300);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            Gamestate.state = Gamestate.PLAYING;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    
}
