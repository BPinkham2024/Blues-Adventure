package objects;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.time.YearMonth;
import java.util.ArrayList;

import core.UserPanel;
import gamestates.Playing;
import level.Level;
import utils.LoadSave;

import static utils.Constants.ObjectConstants.*;


public class ObjectHandler {

    private Playing playing;
    private BufferedImage[] coinMap;
    private ArrayList<Coin> coins;


    public ObjectHandler(Playing playing) {
        this.playing = playing;
        loadMap();
        coins = new ArrayList<Coin>();

        // coins.add(new Coin(300, 300, 1));
        // coins.add(new Coin(400, 300, 1));
    }


    public void checkObjectTouched(Rectangle2D.Float hitBox) {
        for(Coin c : coins) {
            if(c.isActive()) {
                if(hitBox.intersects(c.getHitBox())) {
                    c.setActive(false);
                    applyScore(c);
                }
            }
        }
    }

    public void applyScore(Coin c) {
        if(c.getObjType() == COIN_VALUE) {
            playing.addToScore(COIN_VALUE);
        }
    }

    public void loadObjects(Level level) {
        coins = level.getCoins();
    }


    private void loadMap() {
        BufferedImage img = LoadSave.GetMap(LoadSave.COIN_MAP);
        coinMap = new BufferedImage[8];

        for(int i = 0; i < coinMap.length; i++) {
            coinMap[i] = img.getSubimage(i * UserPanel.TILE_SIZE, 0, UserPanel.TILE_SIZE, UserPanel.TILE_SIZE);
        }
    }

    public void update() {
        for(Coin c : coins) {
            if(c.isActive()) {
                c.update();
            }
        }

    }

    public void draw(Graphics g, int xOffset) {

        drawCoins(g, xOffset);

    }

    private void drawCoins(Graphics g, int xOffset) {
        for(Coin c : coins) {
            if(c.isActive()) {
                if(c.getObjType() == COIN_VALUE) {
                    g.drawImage(coinMap[c.getAniIndex()], (int) c.getHitBox().x - c.getxOffset() - xOffset, (int) c.getHitBox().y - c.getyOffset(), UserPanel.SCALED_TILE_SIZE, UserPanel.SCALED_TILE_SIZE, null);
                }
            }
        }
    }
}
