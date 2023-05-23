package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gamestates.Playing;
import utils.LoadSave;

import static utils.Constants.PlayerConstants.*;
import static utils.Helpers.*;

public class Player extends Entity {

    private BufferedImage[][] animations;
    private int[][] levelData;

    private int spriteSize = 16;
    private int scale = 4;

    private boolean movingLeft;
    private boolean movingRight;
    private boolean up;
    private boolean down;
    private boolean jump;

    private float playerSpeed = 2.0F;
    private float airSpeed = 0.0F;
    private float jumpSpeed = -2.0F * scale;
    private float gravityStrength = .03F * scale;
    private float collisionFallSpeed = 0.4F * scale;

    private boolean inAir;
    private int playerAction = IDLE;
    private boolean isMoving;

    private int aniTick;
    private int aniIndex;
    private int aniSpeed = 30;


    private float XBoxOffset = 3 * scale;
    private float YBoxOffset = 1 * scale;

    private Playing playing;


    
    public Player(float x, float y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.playing = playing;
        loadAnimations();
        initHitbox(x, y, (spriteSize - 6)* scale, (spriteSize - 2) * scale);

    }

    public void update() {
        updateAnimationTick();
        setAnimation();
        updatePos();

        if(isMoving) {
            checkCoinTouched();
        }
    }

    private void checkCoinTouched() {
        playing.checkCoinTouched(hitBox);
    }

    public void render(Graphics g, int xOffset) {
        g.drawImage(animations[playerAction][aniIndex], (int) (hitBox.x - XBoxOffset) - xOffset, (int) (hitBox.y - YBoxOffset), spriteSize * scale, spriteSize * scale, null);
        // drawHitbox(g);
    }


    private void setAnimation() {
        // movement directions messed up but just changing left and right vars seems to fix it
        if(isMoving) {
            if(movingLeft) {
                playerAction = MOVING_RIGHT;
                if(inAir) {
                    playerAction = JUMPING_L;
                }
            } else if(movingRight) {
                playerAction = MOVING_LEFT;
                if(inAir) {
                    playerAction = JUMPING_R;
                }
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
        if(!IsOnFloor(hitBox, levelData)) {
            inAir = true;
        }
    }

    private void updatePos() {
        isMoving = false;
        if(jump) {
            jump();
        }
        // if(!movingLeft && !movingRight && !inAir) {
        //     return;
        // }

        if(!inAir) {
            if((!movingLeft && !movingRight) || (movingLeft && movingRight)) {
                return;
            }
        }

        float xSpeed = 0;
        // float ySpeed = 0;

        if(movingLeft) {
            xSpeed -= playerSpeed;
        }
        if(movingRight) {
            xSpeed += playerSpeed;
        }

        if(!inAir) {
            if(!IsOnFloor(hitBox, levelData)) {
                inAir = true;
            }
        }

        if(inAir) {

            if(CanMove(hitBox.x, hitBox.y + airSpeed, hitBox.width, hitBox.height, levelData)) {
                hitBox.y += airSpeed;
                airSpeed += gravityStrength;
                updateXPos(xSpeed);
            } else {
                hitBox.y = GetPlayerYPos(hitBox, airSpeed);
                if(airSpeed > 0) {
                    resetInAir();
                } else {
                    airSpeed = collisionFallSpeed;
                }

                updateXPos(xSpeed);
            }

        } else {
            updateXPos(xSpeed);
        }

        isMoving = true;
    }

    private void updateXPos(float xSpeed) {
        if(CanMove(hitBox.x + xSpeed, hitBox.y, hitBox.width, hitBox.height, levelData)) {
            hitBox.x += xSpeed;
        } else {
            hitBox.x = GetPlayerXPosOnWall(hitBox, xSpeed);
        }
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0F;
    }

    private void jump() {
        if(inAir) {
            return;
        }

        inAir = true;
        airSpeed = jumpSpeed;
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

    public void setJump(boolean jump) {
        this.jump = jump;
    }


    
}
