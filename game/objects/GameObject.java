package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import static utils.Constants.ObjectConstants.*;

public class GameObject {

    protected int x, y, objType;
    protected Rectangle2D.Float hitBox;

    protected boolean active = true;
    protected int aniTick, aniIndex, aniSpeed = 20;
    protected int xOffset, yOffset;

    public GameObject(int x, int y, int objType) {
        this.x = x;
        this.y = y;
        this.objType = objType;
    }

    protected void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= GetSpriteAmount(objType)) {
                aniIndex = 0;
            }
        }
    }

    public void reset() {
        aniIndex = 0;
        aniTick = 0;
        active = true;
    }

    protected void initHitbox(float width, float height) {
        hitBox = new Rectangle2D.Float(x, y, width, height);
    }

    // public void drawHitbox(Graphics g) {
    //     g.setColor(Color.PINK);
    //     g.drawRect((int) hitBox.x, (int) hitBox.y, (int) hitBox.width, (int) hitBox.height);
    // }
    
    public int getObjType() {
        return objType;
    }

    public Rectangle2D.Float getHitBox() {
        return hitBox;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public int getxOffset() {
        return xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public int getAniIndex() {
        return aniIndex;
    }

    

}
