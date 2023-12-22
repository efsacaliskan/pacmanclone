
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {

    public HomePage() {
        super("Game Home Page");
        setLayout(new BorderLayout());

        // Load the image
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\cefsa\\Desktop\\sample_game_project\\sample_game_project\\src\\pacman\\main2.jpg");
        JLabel imageLabel = new JLabel(imageIcon);



        // Add imageLabel to the frame
        add(imageLabel, BorderLayout.CENTER);


        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnLeaderboard = new JButton("Leaderboard");
        JButton btnPlay = new JButton("Play");
        JButton btnExit = new JButton("Exit");

        // Set button colors
        btnLeaderboard.setBackground(Color.YELLOW);
        btnPlay.setBackground(Color.YELLOW);
        btnExit.setBackground(Color.YELLOW);


        btnLeaderboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Play clicked");
                dispose();
                JFrame gameFrame = new JFrame("Game");
                GamePanel gamePanel = new GamePanel();

                gameFrame.setContentPane(gamePanel);
                gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameFrame.pack();
                gameFrame.setLocationRelativeTo(null);
                gameFrame.setVisible(true);
                // Starts the game
                gamePanel.startGameThread();
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(btnLeaderboard);
        buttonPanel.add(btnPlay);
        buttonPanel.add(btnExit);

        // Add buttonPanel to the frame
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(700, 450); // Adjust the size as needed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HomePage();
            }
        });
    }
}
