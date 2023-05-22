package gamestates;

import java.awt.Color;
import java.awt.Font;
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
        // g.setColor(new Color(64,179,215));
        // g.fillRect(0, 0, UserPanel.GAME_WIDTH, UserPanel.GAME_HEIGHT);
        g.drawImage(LoadSave.GetMap(LoadSave.GAME_OVER_IMG), 0, 0, null);
        // g.setFont(new Font("Norwester", 0, 20));
        // g.setColor(Color.WHITE);
        // g.drawString("Press the Start Button", 560, 580);
            
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
