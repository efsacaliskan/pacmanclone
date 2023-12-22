import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LeaderboardPage extends JFrame {

    public LeaderboardPage() {
        super("Leaderboard");
        setLayout(new BorderLayout());

        // Sample data for leaderboard
        String[] columnNames = {"Rank", "Player", "Score"};
        Object[][] data = {
                {"1", "A", "100"},
                {"2", "B", "50"},
                {"3", "C", "30"},

        };

        // Table for data
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        add(scrollPane, BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Home button
        JButton btnHome = new JButton("Home");
        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close leaderboard window
                try {
                    new HomePage(); // Open home page
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnHome.setBackground(Color.YELLOW);
        buttonPanel.add(btnHome);

        // Exit button
        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close leaderboard window
            }
        });
        btnExit.setBackground(Color.YELLOW);
        buttonPanel.add(btnExit);


        add(buttonPanel, BorderLayout.SOUTH);

        setSize(700, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
