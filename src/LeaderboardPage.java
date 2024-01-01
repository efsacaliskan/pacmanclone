import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LeaderboardPage extends JFrame {

    private DefaultTableModel model;
    private String fileName = "leaderboard.txt";

    public LeaderboardPage() {
        super("Leaderboard");
        setLayout(new BorderLayout());

        // Tablo modeli
        String[] columnNames = {"Rank", "Player", "Score"};
        model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        loadScores(); // Skorları yükle

        // Tabloya ekle
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        add(scrollPane, BorderLayout.CENTER);

        // Buton paneli
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addButtons(buttonPanel);

        add(buttonPanel, BorderLayout.SOUTH);

        setSize(700, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void loadScores() {
        List<String[]> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] scoreData = line.split(": ");
                scores.add(new String[]{scoreData[0], scoreData[1]});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Skorları büyükten küçüğe sırala
        Collections.sort(scores, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                int score1 = Integer.parseInt(o1[1]);
                int score2 = Integer.parseInt(o2[1]);
                return Integer.compare(score2, score1); // Ters sıralama için score2, score1
            }
        });

        // Sıralı skorları modele ekle
        for (int i = 0; i < scores.size(); i++) {
            String rank = Integer.toString(i + 1);
            String[] scoreData = scores.get(i);
            model.addRow(new Object[]{rank, scoreData[0], scoreData[1]});
        }
    }

    private void addButtons(JPanel buttonPanel) {
        // Home butonu
        JButton btnHome = createButton("Home", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    new HomePage(); // Anasayfayı aç
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        buttonPanel.add(btnHome);

        // Çıkış butonu
        JButton btnExit = createButton("Exit", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPanel.add(btnExit);
    }

    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        button.setBackground(Color.YELLOW);
        return button;
    }
}
