
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
import entities.Player;


public class UserPanel extends JPanel implements JavaArcade, Runnable {

    private int width;
    private int height;
    private int highScore;
    private int points;

    private boolean gameRunning;
    private boolean gamePaused;
    private boolean gameStopped;

    private long lastTime;
    private int frames;

    private Thread thread;
    private final int FPS = 120;
    private final int UPS = 150;

    public final static int TILE_SIZE = 16;
    public final static int SCALE = 4;
    public final static int TILES_IN_WIDTH = 20;
    public final static int TILES_IN_HEIGHT = 10;
    public final static int SCALED_TILE_SIZE = TILE_SIZE * SCALE;
    public final static int GAME_WIDTH = SCALED_TILE_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = SCALED_TILE_SIZE * TILES_IN_HEIGHT;
    
    private Player player;
    private LevelHandler levelHandler;

    public UserPanel(int width, int height) {
        setBounds(0, 0, width, height);
        this.width = width;
        this.height = height;
        setBackground(new Color(61, 213, 223));
        addKeyListener(new KeyInputs(this));
        initClasses();
    }

    private void initClasses() {
        player = new Player(100, 100);
        levelHandler = new LevelHandler(this);
    }


    public boolean running() {
        return gameRunning && !gamePaused && !gameStopped;
    }

    public void startGame() {
        gameRunning = true;
        gamePaused = false;
        gameStopped = false;
        startGameLoop();
    }

    public String getGameName() {
        return "";
    }

    public void pauseGame() {
        gameRunning = false;
        gamePaused = true;
    }

    public String getInstructions() {
        return "these are the instructions";
    }

    public String getCredits() {
        return "Made by Brayden Pinkham";
    }

    public String getHighScore() {
        return "" + highScore;
    }

    public void stopGame() {
        gameRunning = false;
        gamePaused = false;
        gameStopped = true;

        highScore = Math.max(highScore, points);
    }

    public int getPoints() {
        return points;
    }

    public void setDisplay(GameStats d) {

    }

// **************Game Code********************



    private void startGameLoop() {
        thread = new Thread(this);
        thread.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);  
        render(g);
    }

    public void updateGame() {
        player.update();
        levelHandler.update();
    }

    public void render(Graphics g) {
        levelHandler.draw(g);
        player.render(g);
    }


    @Override
    public void run() {

        double frameTime = 1000000000.0 / FPS;
        double timeUpdate = 1000000000.0 / UPS;
        long prevTime = System.nanoTime();
        int updates = 0;

        double deltaUpdates = 0;
        double deltaFrames = 0;

        while(true) {
            long currentTime = System.nanoTime();

            deltaUpdates += (currentTime - prevTime) / timeUpdate;
            deltaFrames += (currentTime - prevTime) / frameTime;
            prevTime = currentTime;

            if(deltaUpdates >= 1) {
                updateGame();
                updates++;
                deltaUpdates--;
            }

            if(deltaFrames >= 1) {
                repaint();
                frames++;
                deltaFrames--;

            }
            if(System.currentTimeMillis() - lastTime >= 1000) {
                lastTime = System.currentTimeMillis();
                System.out.println("FPS: " + frames + "| UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public Player getPlayer() {
        return player;
    }
}