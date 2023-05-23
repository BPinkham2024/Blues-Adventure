package objects;

import core.UserPanel;

public class Coin extends GameObject {

    public Coin(int x, int y, int objType) {
        super(x, y, objType);
        initHitbox(16, 16);
        xOffset = (int) (2 * UserPanel.SCALE);
        yOffset = (int) (2 * UserPanel.SCALE);
    }

    public void update() {
        updateAnimationTick();
    }
    
}
