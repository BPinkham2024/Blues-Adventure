package utils;

import core.UserPanel;

public class Constants {

    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class ObjectConstants {
        public static final int COIN_VALUE = 1;

        public static final int COIN_HEIGHT = (int) UserPanel.TILE_SIZE * UserPanel.SCALE;
        public static final int COIN_WIDTH = (int) UserPanel.TILE_SIZE * UserPanel.SCALE;

        public static final int GetSpriteAmount(int object_type) {
            switch(object_type) {
                case COIN_VALUE:
                    return 5;
                
            }

            return 1;
        }
    }

    public static class PlayerConstants {
        public static final int MOVING_RIGHT = 0;
        public static final int MOVING_LEFT = 1;
        public static final int IDLE = 2;
        public static final int JUMPING_R = 3;
        public static final int JUMPING_L = 4;

        public static int GetSpriteAmount(int player_action) {
            switch(player_action) {
                case MOVING_RIGHT:
                    return 4;
                case MOVING_LEFT:
                    return 4;
                case IDLE:
                    return 4;
                case JUMPING_R:
                    return 1;
                case JUMPING_L:
                    return 1;
                default:
                    return 4;
            }
        } 
    }

}