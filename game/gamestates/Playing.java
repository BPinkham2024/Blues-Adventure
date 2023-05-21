package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import core.UserPanel;
import entities.Player;
import level.LevelHandler;
import ui.PauseOverlay;
import utils.LoadSave;

public class Playing extends State implements StateMethods {

    private Player player;
    private LevelHandler levelHandler;
    private PauseOverlay pOverlay;
    private BufferedImage bgImage;

    private int xOffset;
    private int movingBorderL = (int) (0.2 * UserPanel.GAME_WIDTH);
    private int movingBorderR = (int) (0.8 * UserPanel.GAME_WIDTH);
    private int levelTilesWide = LoadSave.LevelData()[0].length;
    private int maxTileOffset = levelTilesWide - UserPanel.TILES_IN_WIDTH;
    private int maxOffset = maxTileOffset * UserPanel.SCALED_TILE_SIZE;

    public Playing(UserPanel game) {
        super(game);
        initClasses();

        bgImage = LoadSave.GetMap(LoadSave.BACKGROUND_IMG);
    }

    private void initClasses() {
        levelHandler = new LevelHandler(game);

        player = new Player(200, 200, UserPanel.SCALED_TILE_SIZE, UserPanel.SCALED_TILE_SIZE);
        player.setLevelData(levelHandler.getLevel().getLevelData());

        pOverlay = new PauseOverlay();
        
    }

    @Override
    public void update() {
        levelHandler.update();
        player.update();
        checkBorder();
    }

    private void checkBorder() {

        int playerX = (int) player.getHitbox().x;
        int difference = playerX - xOffset;

        if(difference > movingBorderR) {
            xOffset += difference - movingBorderR;
        } else if(difference < movingBorderL) {
            xOffset += difference - movingBorderL;
        }

        if(xOffset > maxOffset) {
            xOffset = maxOffset;
        } else if(xOffset < 0) {
            xOffset = 0;
        }
    }



    @Override
    public void draw(Graphics g) {
        g.drawImage(bgImage, 0, 0, UserPanel.GAME_WIDTH, UserPanel.GAME_HEIGHT,null);

        levelHandler.draw(g, xOffset);
        player.render(g, xOffset);
        pOverlay.draw(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.setUp(true);
                break;
            case KeyEvent.VK_A:
                player.setMovingLeft(true);
                break;
            case KeyEvent.VK_S:
                player.setDown(true);
                break;
            case KeyEvent.VK_D:
                player.setMovingRight(true);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.setUp(false);
                break;
            case KeyEvent.VK_A:
                player.setMovingLeft(false);
                break;
            case KeyEvent.VK_S:
                player.setDown(false);
                break;
            case KeyEvent.VK_D:
                player.setMovingRight(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(false);
        }
    }

    public Player getPlayer() {
        return player;
    }
    
}
