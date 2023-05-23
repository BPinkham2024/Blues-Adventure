package core;

import java.awt.Graphics;

import javax.swing.*;
import gamestates.*;
import ui.PauseOverlay;



public class UserPanel extends JPanel implements JavaArcade, Runnable {
    
    private int highScore;
    private int points;

    private boolean gameStarted;
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

    private Playing playing;
    private Menu menu;
    private GameOver gameOver;

    private GameStats gameStats;


    public UserPanel(int width, int height) {
        setBounds(0, 0, width, height);
        // setBackground(new Color(61, 213, 223));
        addKeyListener(new KeyInputs(this));
        initClasses();
        gameStats = new GameStats(this);
    }

    private void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
        gameOver = new GameOver(this);
    }


    public boolean running() {
        return gameRunning && !gamePaused && !gameStopped;
    }

    public void startGame() {
        if(!gameStopped) {
            gameRunning = true;
            gamePaused = false;
            if(!gameStarted) {
                startGameLoop();
                gameStarted = true;
            }
            Gamestate.state = Gamestate.PLAYING;
            // System.out.println("playing");
            PauseOverlay.GAME_PAUSED = false;  
        }

    }

    public String getGameName() {
        return "";
    }

    public void pauseGame() {
        // System.out.println("paused");
        PauseOverlay.GAME_PAUSED = true;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }
        gameRunning = false;
        gamePaused = true;
        
    }

    public String getInstructions() {
        return "Keybinds:\n\tA: Left\n\tD: Right\n\tSpace: Jump";
    }

    public String getCredits() {
        return "Made by Brayden Pinkham";
    }

    public String getHighScore() {
        return "" + highScore;
    }

    public void stopGame() {
        highScore = Math.max(highScore, points);
        Gamestate.state = Gamestate.GAME_OVER;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }

        gameRunning = false;
        gamePaused = false;
        gameStopped = true;
    }

    public void addPoints(int points) {
        this.points += points;
        // System.out.println(points);
        gameStats.setYourScore(this.points);

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
        switch(Gamestate.state) {
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            case GAME_OVER:
                gameOver.update();
                break;
            default:
                break;
        }
    }

    public void render(Graphics g) {
        switch(Gamestate.state) {
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            case GAME_OVER:
                gameOver.draw(g);
                break;
            default:
                break;
        }
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
            if(!gamePaused && !gameStopped) {
                

                deltaUpdates += (currentTime - prevTime) / timeUpdate;
                deltaFrames += (currentTime - prevTime) / frameTime;
                

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
            prevTime = currentTime;
        }
    }

    public Menu getMenu() {
        return menu;
    }
    public Playing getPlaying() {
        return playing;
    }

    public boolean isGamePaused() {
        return gamePaused;
    }
    public boolean isGameStarted() {
        return gameStarted;
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public boolean isGameStopped() {
        return gameStopped;
    }
    
}