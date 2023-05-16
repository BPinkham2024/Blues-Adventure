package utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadSave {

    public static final String SPRITE_MAP = "res/game_sprites.png";
    public static final String TERRAIN_MAP = "res/terrain_map.png";   

    public static BufferedImage GetMap(String fileName) {

        BufferedImage spriteMap = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);

        try {
            spriteMap = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return spriteMap;
    }
}
