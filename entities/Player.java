package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utils.LoadSave;

import static utils.Constants.PlayerConstants.*;
import static utils.Helpers.*;

public class Player extends Entity {

    private BufferedImage[][] animations;
    private int[][] levelData;

    private boolean movingLeft;
    private boolean movingRight;
    private boolean up;
    private boolean down;
    private boolean jump;
    private float playerSpeed = 2.0F;


    private int playerAction = IDLE;
    private boolean isMoving;

    private int aniTick;
    private int aniIndex;
    private int aniSpeed = 30;
    
    private int spriteSize = 16;
    private int scale = 4;

    private float XBoxOffset = 3 * scale;
    private float YBoxOffset = 1 * scale;


    
    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        initHitbox(x, y, (spriteSize - 6)* scale, (spriteSize - 3) * scale);
    }

    public void update() {
        updateAnimationTick();
        setAnimation();
        updatePos();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int) (hitBox.x - XBoxOffset), (int) (hitBox.y - YBoxOffset), spriteSize * scale, spriteSize * scale, null);
        drawHitbox(g);
    }


    private void setAnimation() {
        // movement directions messed up but just changing left and right vars seems to fix it
        if(isMoving) {
            if(movingLeft) {
                playerAction = MOVING_RIGHT;
            } else if(movingRight) {
                playerAction = MOVING_LEFT;
            } else if(movingLeft) {
                playerAction = JUMPING_L;
            } else {
                playerAction = JUMPING_R;
            }
            
            // System.out.println(isMoving);
        } else {
            playerAction = IDLE;
        }
    }

    private void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
            }
        }
    }

    public void setLevelData(int[][] levelData) {
        this.levelData = levelData;
    }

    private void updatePos() {
        isMoving = false;

        if(!movingLeft && !movingRight && !up && !down) {
            return;
        }

        float xSpeed = 0;
        float ySpeed = 0;

        if(movingLeft && !movingRight) {
            xSpeed = -playerSpeed;
        } else if(movingRight && !movingLeft) {
            xSpeed = playerSpeed;
        }

        if(up && !down) {
            ySpeed = -playerSpeed;
        } else if(down && !up) {
            ySpeed = playerSpeed;
        }

        // if(CanMove(x + xSpeed, y + ySpeed, width, height, levelData)) {
        //     // System.out.println("here");
        //     this.x += xSpeed;
        //     this.y += ySpeed;
        //     isMoving = true;
        // }

        if(CanMove(hitBox.x + xSpeed, hitBox.y + ySpeed, hitBox.width, hitBox.height, levelData)) {
            hitBox.x += xSpeed;
            hitBox.y += ySpeed;
            isMoving = true;
        }
    }

    private void loadAnimations() {
        BufferedImage spriteMap = LoadSave.GetMap(LoadSave.SPRITE_MAP);

        animations = new BufferedImage[5][4];

        for(int i = 0; i < animations.length - 2; i++) {
            for(int j = 0; j < animations[i].length; j++) {
                animations[i][j] = spriteMap.getSubimage(j * spriteSize, i * spriteSize, spriteSize, spriteSize);
            }
        }

        // idle ani fix
        animations[2][0] = spriteMap.getSubimage(spriteSize, 2 * spriteSize, spriteSize, spriteSize);
        animations[2][2] = spriteMap.getSubimage(spriteSize, 2 * spriteSize, spriteSize, spriteSize);
        animations[2][3] = spriteMap.getSubimage(0, 2 * spriteSize, spriteSize, spriteSize);


        // jumping right ani
        animations[3][0] = spriteMap.getSubimage(spriteSize, spriteSize, spriteSize, spriteSize);
        animations[3][1] = spriteMap.getSubimage(spriteSize, spriteSize, spriteSize, spriteSize);


        // jumping left ani
        animations[4][0] = spriteMap.getSubimage(spriteSize, 0, spriteSize, spriteSize);
        animations[4][1] = spriteMap.getSubimage(spriteSize, 0, spriteSize, spriteSize);

    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }


    
}
