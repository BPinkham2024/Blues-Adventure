
import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import core.*;

public class Arcade extends JFrame {
    public Arcade() {
        super("Blue's Adventure");

        JavaArcade game = new UserPanel(UserPanel.SCALED_TILE_SIZE * UserPanel.TILES_IN_WIDTH, UserPanel.SCALED_TILE_SIZE * UserPanel.TILES_IN_HEIGHT);

        GameStats display = new GameStats(game); //passing in a JavaArcade, therefore I know I can call getHighScore(), getScore()
        ((UserPanel) game).setGameStats(display);
            
        ControlPanel controls = new ControlPanel(game, display); //Also passing in JavaArcade to ControlPanel, I know you will respond to buttons
        
        game.setDisplay(display); //provides game ability to update display
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(0, 5, 0, 5));
        panel.add(display, BorderLayout.NORTH);
        panel.add((JPanel) game, BorderLayout.CENTER);
        panel.requestFocus();
        panel.add(controls, BorderLayout.SOUTH);

        Container c = getContentPane();
        c.add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Arcade window = new Arcade();
        // window.setBounds(100, 100, UserPanel.GAME_WIDTH, UserPanel.GAME_HEIGHT + (UserPanel.SCALED_TILE_SIZE * 3));
        window.setBounds(100, 100, 20 * 64, 12 * 64);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
    }
}

