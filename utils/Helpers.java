package utils;

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
        if(x < 0 || x >= 64 * 20) {
            return true;
        }
        if(y < 0 || y >= 64 * 10) {
            return true;
        }

        float xInd = x / 64;
        float yInd = y / 64;

        // System.out.println(xInd + ", " + yInd);

        int val = levelData[(int) yInd][(int) xInd];
        // System.out.println(val);
        // int val = 6;

        if(val >= 24 || val < 0 || val != 6) {
            return true;
        }
        
        return false;
    }

}
