package core;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GameStats extends JPanel {
    private int yourScore;
    private JLabel yourScoreText, hScore;
    private JavaArcade game;

    // Constructor
    public GameStats(JavaArcade t) {
        super(new GridLayout(2, 4, 10, 0));
        setBorder(new EmptyBorder(0, 0, 5, 0));
        Font gameNameFont = new Font("Norwester", Font.BOLD, 24);

        JLabel gName = new JLabel(" " + t.getGameName());
        
        gName.setForeground(new Color(64,179,215));
        gName.setFont(gameNameFont);
        add(gName);

        gameNameFont = new Font("Norwester", Font.PLAIN, 15);

        hScore = new JLabel(" Current High Score:   " + t.getHighScore());
        hScore.setFont(gameNameFont);

        add(hScore); //new JLabel(" Current High Score:   " + t.getHighScore()));

        add(new JLabel(" "));
        yourScoreText = new JLabel(" Your Score: " + yourScore);
        yourScoreText.setFont(gameNameFont);


        add(yourScoreText);
        // Font displayFont = new Font("Norwester", Font.BOLD, 16);
        game = t;

        
    }

    public void setYourScore(int yourScore) {
        this.yourScore = yourScore;
        update(this.yourScore);
    }

    public int getYourScore() {
        return yourScore;
    }

    public void update(int points){
        yourScoreText.setText(" Score: " + points);
    }

    public void gameOver(int points) {
        if(points > Integer.parseInt(game.getHighScore())){
            game.setHighScore(points);
            yourScoreText.setText("Score: " + points);
            yourScoreText.setForeground(Color.BLUE);
            // JOptionPane.showInputDialog(this, "You are the new high scorer. Congratulations!\n Enter your name: ", "High Score", JOptionPane.PLAIN_MESSAGE, null, null,"name");
            hScore.setText(" Current High Score:   " + points);
        }
        
    }
}
