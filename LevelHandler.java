

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utils.LoadSave;

public class LevelHandler {
    private UserPanel game;
    private BufferedImage[] levelMap;
    private Level level1;

    private int scale = UserPanel.SCALE;
    private int tileSize = UserPanel.TILE_SIZE;
    
    public LevelHandler(UserPanel game) {
        this.game = game;
        importLevelMap();
        level1 = new Level(LoadSave.LevelData());
    }

    private void importLevelMap() {
        BufferedImage map = LoadSave.GetMap(LoadSave.TERRAIN_MAP);
        levelMap = new BufferedImage[24];

        int count = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 8; j++) {
                levelMap[count] = map.getSubimage(j * tileSize, i * tileSize, tileSize, tileSize);
                count++;
                // System.out.println(count);
            }
        }
    }

    public void draw(Graphics g) {
        // int count = 0;
        // for(BufferedImage img : levelMap) {
        //     g.drawImage(img, count * tileSize * scale, (count % 8) * tileSize * scale, tileSize * scale, tileSize * scale, null);
        //     count++;
        // }
        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 10; j++) {
                int index = level1.getIndex(i, j);
                g.drawImage(levelMap[index], i * UserPanel.SCALED_TILE_SIZE, j * UserPanel.SCALED_TILE_SIZE, UserPanel.SCALED_TILE_SIZE, UserPanel.SCALED_TILE_SIZE, null);
            }
        }
        // g.drawImage(levelMap[6], 0, 0, 64, 64, null);

    }

    public Level getLevel() {
        return level1;
    }


    public void update() {

    }

}
