package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.*;

import core.UserPanel;
import utils.LoadSave;

public class Menu extends State implements StateMethods {

    public Menu(UserPanel game) {
        super(game);
    }

    @Override
    public void update() {
        
    }

    @Override
    public void draw(Graphics g) {

            g.setColor(new Color(64,179,215));
            g.fillRect(0, 0, UserPanel.GAME_WIDTH, UserPanel.GAME_HEIGHT);
            g.drawImage(LoadSave.GetMap(LoadSave.TITLE), 0, 0, null);
            g.setFont(new Font("Norwester", 0, 20));
            g.setColor(Color.WHITE);
            g.drawString("Press the Start Button", 560, 580);
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
