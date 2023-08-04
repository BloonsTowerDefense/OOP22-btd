package btd.view.menu;

import btd.model.Game;
import btd.view.GameCondition;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

public class DifficultyMenu extends JPanel {
    private static final Color SELECTED_COLOR = Color.decode("#FD8D14");
    private static final Color UNSELECTED_COLOR = Color.WHITE;

    private BufferedImage menuBackground;

    //Default game difficulty set to 1
    private String difficulty = "easy";
    private String map = "Map 1";
    private final JButton startButton = new JButton("Play");

    public DifficultyMenu() {
        System.out.print("\n inizio difficulty menu");
        GridLayout mainLayout = new GridLayout(5,1);
        mainLayout.setVgap(50);
        setLayout(mainLayout);

        // Label at the top center
        JLabel titleLabel = new JLabel("Choose Game Difficulty");
        titleLabel.setForeground(Color.decode("#CECE5A"));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 64));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel);

        // Create panel that holds difficulty buttons
        GridLayout gridLayout = new GridLayout(1, 3);
        gridLayout.setHgap(10);
        JPanel difficultyPanel = new JPanel(gridLayout);

        JButton easyButton = createDifficultyButton("Easy");
        easyButton.setFocusPainted(false);
        JButton mediumButton = createDifficultyButton("Medium");
        mediumButton.setFocusPainted(false);
        JButton hardButton = createDifficultyButton("Hard");
        hardButton.setFocusPainted(false);

        // Changes color only for Easy Button and sets "difficulty" variable to 1
        easyButton.addActionListener(e -> setDifficulty("easy", easyButton, mediumButton, hardButton));
        easyButton.setBackground(Color.decode("#FD8D14"));
        // Changes color only for Medium Button and sets "difficulty" variable to 2
        mediumButton.addActionListener(e -> setDifficulty("medium", easyButton, mediumButton, hardButton));

        // Changes color only for Hard Button and sets "difficulty" variable to 3
        hardButton.addActionListener(e -> setDifficulty("hard", easyButton, mediumButton, hardButton));

        GridLayout gridLayout1 = new GridLayout(1,2);
        gridLayout1.setHgap(20);
        JPanel mapsPanel = new JPanel(gridLayout1);

        JButton map1 = createMapButton("/map/map01/map1.png");
        JButton map2 = createMapButton("/map/map02/map2.png");

        map1.addActionListener(e -> setMap("Map 1",map1,map2));
        map1.setBorder(BorderFactory.createLineBorder(Color.WHITE,4));
        map1.setFocusPainted(false);
        map2.addActionListener(e -> setMap("Map 2",map1,map2));
        map2.setFocusPainted(false);

        // Add all buttons to the panel
        difficultyPanel.add(easyButton);
        difficultyPanel.add(mediumButton);
        difficultyPanel.add(hardButton);
        mapsPanel.add(map1);
        mapsPanel.add(map2);
        difficultyPanel.setOpaque(false);
        mapsPanel.setOpaque(false);
        add(difficultyPanel);
        add(mapsPanel);

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
        System.out.print("\n fine difficulty menu");
    }

    private void setDifficulty(String value, JButton easyButton, JButton mediumButton, JButton hardButton) {
        difficulty = value;
        easyButton.setBackground(Objects.equals(value, "easy") ? SELECTED_COLOR : UNSELECTED_COLOR);
        mediumButton.setBackground(Objects.equals(value, "medium") ? SELECTED_COLOR : UNSELECTED_COLOR);
        hardButton.setBackground(Objects.equals(value, "hard") ? SELECTED_COLOR : UNSELECTED_COLOR);
    }

    private void setMap(String value, JButton map1,JButton map2){
        map = value;

        if (Objects.equals(value, "Map 1")) {
            map1.setBorderPainted(true);
            map2.setBorderPainted(false);
            map1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        } else {
            map2.setBorderPainted(true);
            map1.setBorderPainted(false);
            map2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(menuBackground, 0, 0, 1200, 720, null);
    }

    private JButton createDifficultyButton(String label) {
        JButton button = new JButton(label);
        button.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
        button.setOpaque(true);
        button.setBackground(Color.WHITE);
        button.setBorderPainted(false);
        return button;
    }

    private JButton createMapButton(String mapPath) {
        JButton button = new JButton();
        try {
            BufferedImage mapIcon = ImageIO.read(Objects.requireNonNull(getClass().getResource(mapPath)));
            button.setIcon(new ImageIcon(mapIcon.getScaledInstance(400,300, Image.SCALE_DEFAULT)));
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setOpaque(true);
            button.setBackground(Color.decode("#629D5A"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return button;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getMap(){
        return map;
    }

    public JButton getStartButton() {
        return startButton;
    }
}