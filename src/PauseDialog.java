import javax.swing.*;
import java.awt.*;

public class PauseDialog extends JDialog {
    public PauseDialog(JFrame owner) {

        super(owner, "Game Paused", true);
        getContentPane().setBackground(Color.BLACK);
        setLayout(new GridBagLayout());
        setSize(300, 200);
        JLabel pauseLabel = new JLabel("Game Paused");
        pauseLabel.setFont(new Font("Consolas", Font.BOLD, 24));
        pauseLabel.setForeground(Color.YELLOW);


         ImageIcon icon = new ImageIcon("src/images/pause.jpg");

         JLabel imageLabel = new JLabel(icon);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridy = 0;
        add(imageLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(pauseLabel, gbc);

        pack();
        setLocationRelativeTo(owner);
    }
}
