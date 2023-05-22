package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import core.UserPanel;
import utils.LoadSave;

public class PauseOverlay {

    private BufferedImage overlay, backing;
    private int ovX, ovY, ovWidth, ovHeight;

    public static boolean GAME_PAUSED;
    
    public PauseOverlay() {
        loadOverlay();
    }

    private void loadOverlay() {
        overlay = LoadSave.GetMap(LoadSave.PAUSED_OVERLAY);
        backing = LoadSave.GetMap(LoadSave.SHADOW_BACK);

        ovWidth = (int) (overlay.getWidth() * 1.5);
        ovHeight = (int) (overlay.getHeight() * 1.5);

        ovX = (int) (UserPanel.GAME_WIDTH / 2 - ovWidth / 2.5);
        ovY = 100;

    }

    public void update() {

    }

    public void draw(Graphics g) {
        if(GAME_PAUSED) {
            g.drawImage(backing, 0, 0, null);
            g.drawImage(overlay, ovX, ovY,ovWidth, ovHeight, null);
        }
    }
}
