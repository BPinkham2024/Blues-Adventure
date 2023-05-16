

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utils.LoadSave;

public class LevelHandler {
    private UserPanel game;
    private BufferedImage[] levelMap;

    private int scale = 4;
    private int tileSize = 16;
    
    public LevelHandler(UserPanel game) {
        this.game = game;
        importLevelMap();
    }

    private void importLevelMap() {
        BufferedImage map = LoadSave.GetMap(LoadSave.TERRAIN_MAP);
        levelMap = new BufferedImage[24];

        int count = 0;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 3; j++) {
                levelMap[count] = map.getSubimage(i * tileSize, j * tileSize, tileSize, tileSize);
                count++;
                System.out.println(count);
            }
        }


    }

    public void draw(Graphics g) {
        int count = 0;
        for(BufferedImage img : levelMap) {
            g.drawImage(img, count * tileSize * scale, 0, tileSize * scale, tileSize * scale, null);
            count++;
        }
        
    }
    public void update() {

    }

}
