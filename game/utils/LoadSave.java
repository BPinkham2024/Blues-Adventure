package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadSave {

    public static final String SPRITE_MAP = "res/game_sprites.png";
    public static final String TERRAIN_MAP = "res/terrain_map.png";
    public static final String COIN_MAP = "res/coin.png";
    // public static final String LEVEL_ONE_DATA = "res/level_one_data.png";
    // public static final String LEVEL_ONE_DATA = "res/level_one_data_long.png";
    public static final String LEVEL_ONE_DATA = "res/level_one_with_coin.png";

    public static final String FULL_BACK_DATA = "res/full_back.png";

    public static final String GAME_OVER_IMG = "res/game_over_screen.png";
    public static final String BACKGROUND_IMG = "res/sunset_background.png";
    public static final String PAUSED_OVERLAY = "res/paused_screen.png";
    public static final String SHADOW_BACK = "res/shadow_back.png";
    public static final String TITLE = "res/title.png";


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

    public static int[][] LevelData() {
    
        BufferedImage img = GetMap(LEVEL_ONE_DATA);
        int[][] levelData = new int[img.getHeight()][img.getWidth()];
        // System.out.println(img.getWidth() + " " + img.getHeight());

        for(int i = 0; i < img.getHeight(); i++) {
            for(int j = 0; j < img.getWidth(); j++) {
                Color color = new Color(img.getRGB(j, i));
                int red = color.getRed() - 1;
                if(red <= 25) {
                    levelData[i][j] = red;
                } else if(red <= -1) {
                    levelData[i][j] = 6;
                } else {
                    levelData[i][j] = 6;
                }


                // System.out.println(levelData[i][j]);
            }
        }

        return levelData;
    }
}
