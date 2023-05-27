package objects;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import core.UserPanel;
import gamestates.Playing;
import level.Level;
import utils.LoadSave;

import static utils.Constants.ObjectConstants.*;


public class ObjectHandler {

    private Playing playing;
    private BufferedImage[] coinMap;
    private BufferedImage[] gemMap;
    private ArrayList<Coin> coins;
    private ArrayList<Gem> gems;


    public ObjectHandler(Playing playing) {
        this.playing = playing;
        loadMap();
        coins = new ArrayList<Coin>();
        gems = new ArrayList<Gem>();

        // coins.add(new Coin(300, 300, 1));
        // coins.add(new Coin(400, 300, 1));
        gems.add(new Gem(300, 300, GEM_BLUE));
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

        for(Gem g : gems) {
            if(g.isActive()) {
                if(hitBox.intersects(g.getHitBox())) {
                    g.setActive(false);
                    applyScore(g);
                }
            }
        }
    }

    public void applyScore(Coin c) {
        if(c.getObjType() == COIN_BLUE) {
            playing.addToScore(COIN_VALUE);
        }
    }

    public void applyScore(Gem g) {
        if(g.getObjType() == GEM_BLUE) {
            playing.addToScore(GEM_VALUE);
        }
    }

    public void loadObjects(Level level) {
        coins = level.getCoins();
        gems = level.getGems();
    }


    private void loadMap() {
        BufferedImage cImg = LoadSave.GetMap(LoadSave.COIN_MAP);
        BufferedImage gImg = LoadSave.GetMap(LoadSave.GEM_MAP);
        coinMap = new BufferedImage[8];
        gemMap = new BufferedImage[5];

        for(int i = 0; i < coinMap.length; i++) {
            coinMap[i] = cImg.getSubimage(i * UserPanel.TILE_SIZE, 0, UserPanel.TILE_SIZE, UserPanel.TILE_SIZE);
        }

        for(int i = 0; i < gemMap.length; i++) {
            gemMap[i] = gImg.getSubimage(i * UserPanel.TILE_SIZE, 0, UserPanel.TILE_SIZE, UserPanel.TILE_SIZE);
        }
    }

    public void update() {
        for(Coin c : coins) {
            if(c.isActive()) {
                c.update();
            }
        }

        for(Gem g : gems) {
            if(g.isActive()) {
                g.update();
            }
        }

    }

    public void draw(Graphics g, int xOffset) {
        drawObjects(g, xOffset);
    }

    private void drawObjects(Graphics g, int xOffset) {
        for(Coin c : coins) {
            if(c.isActive()) {
                if(c.getObjType() == COIN_BLUE) {
                    g.drawImage(coinMap[c.getAniIndex()], (int) c.getHitBox().x - c.getxOffset() - xOffset, (int) c.getHitBox().y - c.getyOffset(), UserPanel.SCALED_TILE_SIZE, UserPanel.SCALED_TILE_SIZE, null);
                }
            }
        }
        for(Gem gem : gems) {
            if(gem.isActive()) {
                if(gem.getObjType() == GEM_BLUE) {
                    g.drawImage(gemMap[gem.getAniIndex()], (int) gem.getHitBox().x - gem.getxOffset() - xOffset, (int) gem.getHitBox().y - gem.getyOffset(), UserPanel.SCALED_TILE_SIZE, UserPanel.SCALED_TILE_SIZE, null);
                }
            }  
        }
    }
}
