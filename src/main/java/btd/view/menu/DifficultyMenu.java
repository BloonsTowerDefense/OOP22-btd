package btd.view.menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class DifficultyMenu extends JPanel {

    private BufferedImage menuBackground;

    //Default game difficulty set to 1
    private Integer difficulty = 1;
    private final JButton startButton = new JButton("Start");

    public DifficultyMenu() {
        setLayout(new GridLayout(4, 1));

        // Label at the top center
        JLabel titleLabel = new JLabel("Choose Game Difficulty");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 64));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel);

        // Create panel that holds difficulty buttons
        GridLayout gridLayout = new GridLayout(1, 3);
        gridLayout.setHgap(10);
        JPanel gridPanel = new JPanel(gridLayout);

        JButton easyButton = createButton("Easy");
        JButton mediumButton = createButton("Medium");
        JButton hardButton = createButton("Hard");

        // Changes color only for Easy Button and sets "difficulty" variable to 1
        easyButton.addActionListener(e -> setDifficulty(1, easyButton, mediumButton, hardButton));
        easyButton.setBackground(Color.RED);
        // Changes color only for Medium Button and sets "difficulty" variable to 2
        mediumButton.addActionListener(e -> setDifficulty(2, easyButton, mediumButton, hardButton));

        // Changes color only for Hard Button and sets "difficulty" variable to 3
        hardButton.addActionListener(e -> setDifficulty(3, easyButton, mediumButton, hardButton));

        // Add all buttons to the panel
        gridPanel.add(easyButton);
        gridPanel.add(mediumButton);
        gridPanel.add(hardButton);
        gridPanel.setOpaque(false);
        add(gridPanel);

        // Set background image
        try {
            this.menuBackground = ImageIO.read(Objects.requireNonNull(getClass().getResource("/menuSprite/mainMenuSprite.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set start button properties
        JPanel startPanel = new JPanel();
        startButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
        startButton.setPreferredSize(new Dimension(100, 40));
        startPanel.setOpaque(false);
        startPanel.add(startButton);
        add(Box.createVerticalGlue());
        add(startPanel);

        // Set panel size and opacity
        setPreferredSize(new Dimension(1200, 720));
        setOpaque(false);
    }

    private void setDifficulty(int value, JButton easyButton, JButton mediumButton, JButton hardButton) {
        difficulty = value;
        easyButton.setBackground(value == 1 ? Color.RED : Color.WHITE);
        mediumButton.setBackground(value == 2 ? Color.RED : Color.WHITE);
        hardButton.setBackground(value == 3 ? Color.RED : Color.WHITE);
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public JButton getStartButton() {
        return startButton;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(menuBackground, 0, 0, 1200, 720, null);
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
        button.setOpaque(true);
        button.setBackground(Color.WHITE);
        button.setBorderPainted(false);
        return button;
    }
}