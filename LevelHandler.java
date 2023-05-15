

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utils.LoadSave;

public class LevelHandler {
    private UserPanel game;
    private BufferedImage[] levelMap;
    
    public LevelHandler(UserPanel game) {
        this.game = game;
        // levelMap = LoadSave.GetMap(LoadSave.LEVEL_MAP);
        importLevelMap();
    }

    private void importLevelMap() {
        // levelMap = new BufferedImage[];
    }

    public void draw(Graphics g) {
        // g.drawImage(levelMap, 0, 0, null);
    }
    public void update() {

    }

}
