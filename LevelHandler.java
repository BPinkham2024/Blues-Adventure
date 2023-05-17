

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
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 3; j++) {
                levelMap[count] = map.getSubimage(i * tileSize, j * tileSize, tileSize, tileSize);
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
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 20; j++) {
                int index = level1.getIndex(j, i);
                g.drawImage(levelMap[index], i * 16, j * 16, 16, 16, null);
            }
        }

    }


    public void update() {

    }

}
