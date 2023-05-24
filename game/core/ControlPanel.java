package core;
import java.awt.event.*;
import javax.swing.*;


public class ControlPanel extends JPanel implements ActionListener {
    private JavaArcade game;
    private GameStats gStats;
    private JButton startButton, pauseButton, stopButton, instructionsButton, creditsButton;

    // Constructor
    public ControlPanel(JavaArcade t, GameStats g) {
        game = t;
        gStats = g;

        instructionsButton = new JButton("Instructions");
        instructionsButton.addActionListener(this);
        add(instructionsButton);
        add(Box.createHorizontalStrut(80)); 
        startButton = new JButton("Start");
        startButton.addActionListener(this);

        add(startButton);
        
        pauseButton = new JButton("Pause");
        pauseButton.addActionListener(this);
        add(pauseButton);
        stopButton = new JButton("Stop");
        stopButton.addActionListener(this);
        add(stopButton);
        add(Box.createHorizontalStrut(80)); 
        creditsButton = new JButton("Credits");
        creditsButton.addActionListener(this);
        add(creditsButton);
        

    }

    // Called when the start button is clicked
    public void actionPerformed(ActionEvent e)
    {
        
        JButton button = (JButton)e.getSource();

        if (button == startButton) {

            if (!game.running()) {
            
                ((JPanel)(game)).requestFocus(); //need to provide the JPanel focus
                startButton.setText("Start");
                game.startGame();
                startButton.setEnabled(false);
                pauseButton.setEnabled(true);
                stopButton.setEnabled(true);
                gStats.update(((UserPanel) game).getGameStats().getYourScore());
                System.out.println(((UserPanel) game).getGameStats().getYourScore());
                gStats.repaint();       
            }
        }
        else if(button == pauseButton && !((UserPanel) game).isGameStopped()) {
            game.pauseGame();
            startButton.setText("Resume");
            startButton.setEnabled(true);
            pauseButton.setEnabled(false);
            stopButton.setEnabled(false);
            repaint();
        }
        else if(button == stopButton) {
            game.stopGame();
            startButton.setEnabled(false);
            pauseButton.setEnabled(false);
            stopButton.setEnabled(false);
            gStats.repaint();
            gStats.gameOver(((UserPanel) game).getGameStats().getYourScore());
            // System.out.println("game over");
            repaint();
        }  
        else if(button == creditsButton) {
            String credits = game.getCredits();
            JOptionPane.showMessageDialog(this, credits, "Game Credits", JOptionPane.PLAIN_MESSAGE);
    
        }      
        else if(button == instructionsButton) {
            String instructions = game.getInstructions();
            JOptionPane.showMessageDialog(this, instructions, "Game Rules", JOptionPane.PLAIN_MESSAGE);
        }
        ((JPanel)(game)).requestFocus();      
    }
}

