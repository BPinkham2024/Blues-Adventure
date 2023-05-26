package level;


import java.awt.Graphics;
import java.awt.image.BufferedImage;

import core.UserPanel;
import utils.LoadSave;


public class LevelHandler {
    private BufferedImage[] levelMap;
    private Level level1;
    private UserPanel game;

    private int tileSize = UserPanel.TILE_SIZE;
    
    public LevelHandler(UserPanel game) {
        this.game = game;
        importLevelMap();
        level1 = new Level(LoadSave.LevelData());
    }

    public void loadLevel() {
        game.getPlaying().getObjectHandler().loadObjects(level1);
    }

    private void importLevelMap() {
        BufferedImage map = LoadSave.GetMap(LoadSave.TERRAIN_MAP);
        levelMap = new BufferedImage[24];

        int count = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 8; j++) {
                levelMap[count] = map.getSubimage(j * tileSize, i * tileSize, tileSize, tileSize);
                count++;
            }
        }
    }

    public void draw(Graphics g, int xOffset) {
        for(int i = 0; i < level1.getLevelData()[0].length; i++) {
            for(int j = 0; j < UserPanel.TILES_IN_HEIGHT; j++) {
                int index = level1.getIndex(i, j);
                if(index >= 0) {
                    g.drawImage(levelMap[index], i * UserPanel.SCALED_TILE_SIZE - xOffset, j * UserPanel.SCALED_TILE_SIZE, UserPanel.SCALED_TILE_SIZE, UserPanel.SCALED_TILE_SIZE, null);
                }
            }
        }

    }

    public Level getLevel() {
        return level1;
    }


    public void update() {

    }

}
