package utils;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import core.UserPanel;
import objects.Coin;

import static utils.Constants.ObjectConstants.*;

public class Helpers {
    
    public static boolean CanMove(float x, float y, float width, float height, int[][] levelData) {

        // System.out.println(0);
        if(!TileSolid(x, y, levelData)) {
            // System.out.println(1);
            if(!TileSolid(x + width, y + height, levelData)) {
                // System.out.println(2);
                if(!TileSolid(x + width, y, levelData)) {
                    // System.out.println(3);
                    if(!TileSolid(x, y + height, levelData)) {
                        // System.out.println(4);
                        return true;
                    }
                }
            }
        }
        
        return false;

    }

    public static boolean TileSolid(float x, float y, int[][] levelData) {
        int maxWidth = levelData[0].length * UserPanel.SCALED_TILE_SIZE;

        if(x < 0 || x >= maxWidth) {
            return true;
        }
        if(y < 0 || y >= UserPanel.GAME_HEIGHT) {
            return true;
        }

        float xInd = x / UserPanel.SCALED_TILE_SIZE;
        float yInd = y / UserPanel.SCALED_TILE_SIZE;

        // System.out.println(xInd + ", " + yInd);

        int val = levelData[(int) yInd][(int) xInd];
        // System.out.println(val);
        // int val = 6;

        if(val == -1) {
            return false;
        }
        if(val >= 24 || val < 0 || val != 6) {
            return true;
        }
        
        return false;
    }

    public static float GetPlayerXPosOnWall(Rectangle2D.Float hitBox, float xSpeed) {
        int currentTile = (int) hitBox.x / 64;

        if(xSpeed > 0) {
            int tileX = currentTile * 64;
            int xOffset = (int) (64 - hitBox.width);
            return tileX + xOffset - 1;
        } else {
            return currentTile * 64;
        }
    }

    public static float GetPlayerYPos(Rectangle2D.Float hitBox, float airSpeed) {
        int currentTile = (int) hitBox.y / 64;

        if(airSpeed > 0) {
            int tileY = currentTile * 64;
            int yOffset = (int) (64 - hitBox.height);
            return tileY + yOffset - 1;
        } else {
            return currentTile * 64;
        }
    }

    public static boolean IsOnFloor(Rectangle2D.Float hitBox, int[][] levelData) {
        if(!TileSolid(hitBox.x, hitBox.y + hitBox.height + 1, levelData)) {
            if(!TileSolid(hitBox.x + hitBox.width, hitBox.y + hitBox.height + 1, levelData)) {
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Coin> GetCoins(BufferedImage img) {
        ArrayList<Coin> list = new ArrayList<Coin>();
        // System.out.println(img.getWidth());
        for(int i = 0; i < img.getHeight(); i++) {
            for(int j = 0; j < img.getWidth(); j++) {
                // System.out.println("x: " + j + " y: " + i);
                Color blue = new Color(img.getRGB(j, i));
                int val = blue.getBlue();
                if(val == COIN_VALUE) {
                    list.add(new Coin(j* UserPanel.SCALED_TILE_SIZE, i * UserPanel.SCALED_TILE_SIZE, COIN_VALUE));
                }
            }
        }

        return list;
    }

}
