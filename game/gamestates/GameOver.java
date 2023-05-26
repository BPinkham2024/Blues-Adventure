package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import core.UserPanel;
import utils.LoadSave;

public class GameOver extends State implements StateMethods {

    public GameOver(UserPanel game) {
        super(game);
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(LoadSave.GetMap(LoadSave.GAME_OVER_IMG), 0, 0, null);
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
