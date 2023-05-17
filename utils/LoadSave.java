package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.text.AbstractDocument.LeafElement;

public class LoadSave {

    public static final String SPRITE_MAP = "res/game_sprites.png";
    public static final String TERRAIN_MAP = "res/terrain_map.png";
    public static final String LEVEL_ONE_DATA = "res/level_one_data.png";   

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
        int[][] levelData = new int[10][20];
        BufferedImage img = GetMap(LEVEL_ONE_DATA);
        // System.out.println(img.getWidth() + " " + img.getHeight());

        for(int i = 0; i < img.getHeight(); i++) {
            for(int j = 0; j < img.getWidth(); j++) {
                Color color = new Color(img.getRGB(j, i));
                int red = color.getRed();
                if(red <= 24) {
                    levelData[i][j] = red;
                } else {
                    levelData[i][j] = 7;
                }
            }
        }

        return levelData;
    }
}
