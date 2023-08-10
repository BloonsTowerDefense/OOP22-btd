package btd.view.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * A graphical user interface component that represents
 * a menu for selecting game difficulty and map.
 * The menu provides options to choose from different
 * game difficulties and maps, along with a start button
 * to begin the game.
 */
public class DifficultyMenu extends JPanel {

  private final Logger logger = Logger.getLogger(getClass().getName());

  private static final Color SELECTED_COLOR = Color.decode("#FD8D14");

  private static final Color UNSELECTED_COLOR = Color.WHITE;

  private BufferedImage menuBackground;

  private String difficulty = "easy";

  private String map = "map01";

  private final JButton startButton = new JButton("Play");

  private final JButton backButton = new JButton();

  /**
  * Constructs a new DifficultyMenu instance.
  * Sets up the layout, adds buttons for difficulty
  * and map selection, and initializes the menu background image.
  */
  public DifficultyMenu() {
    GridLayout mainLayout = new GridLayout(5, 1);
    mainLayout.setVgap(50);
    setLayout(mainLayout);

    JLabel titleLabel = new JLabel("Choose Game Difficulty");
    titleLabel.setForeground(Color.decode("#CECE5A"));
    titleLabel.setFont(new Font("Arial", Font.BOLD, 64));
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    try {
      BufferedImage backIcon = ImageIO.read(Objects.requireNonNull(getClass()
              .getResource("/menusprite/icons/backButton.png")));
      backButton.setIcon(new ImageIcon(backIcon.getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
      backButton.setBorderPainted(false);
    } catch (Exception e) {
      logger.severe("Error loading image.");
    }
    JPanel northPanel = new JPanel(new BorderLayout());

    northPanel.add(backButton, BorderLayout.WEST);
    northPanel.add(titleLabel, BorderLayout.CENTER);
    northPanel.setOpaque(false);
    add(northPanel);

    JButton easyButton = createDifficultyButton("Easy");
    easyButton.setFocusPainted(false);
    JButton mediumButton = createDifficultyButton("Medium");
    mediumButton.setFocusPainted(false);
    JButton hardButton = createDifficultyButton("Hard");
    hardButton.setFocusPainted(false);


    easyButton.addActionListener(e -> setDifficulty("easy", easyButton, mediumButton, hardButton));
    easyButton.setBackground(Color.decode("#FD8D14"));
    mediumButton.addActionListener(e ->
            setDifficulty("medium", easyButton, mediumButton, hardButton));
    hardButton.addActionListener(e -> setDifficulty("hard", easyButton, mediumButton, hardButton));


    JButton map1 = createMapButton("/map/map01/map1.png");
    JButton map2 = createMapButton("/map/map02/map2.png");

    map1.addActionListener(e -> {
      setMap("map01", map1, map2);
    });
    map1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
    map1.setFocusPainted(false);

    map2.addActionListener(e -> {
      setMap("map02", map1, map2);
    });
    map2.setFocusPainted(false);

    GridLayout gridLayout = new GridLayout(1, 3);
    gridLayout.setHgap(10);
    JPanel difficultyPanel = new JPanel(gridLayout);

    GridLayout gridLayout1 = new GridLayout(1, 2);
    gridLayout1.setHgap(20);

    difficultyPanel.add(easyButton);
    difficultyPanel.add(mediumButton);
    difficultyPanel.add(hardButton);
    JPanel mapsPanel = new JPanel(gridLayout1);
    mapsPanel.add(map1);
    mapsPanel.add(map2);
    difficultyPanel.setOpaque(false);
    mapsPanel.setOpaque(false);
    add(difficultyPanel);
    add(mapsPanel);

    try {
      this.menuBackground = ImageIO.read(Objects.requireNonNull(getClass()
              .getResource("/menuSprite/mainMenuSprite.png")));
    } catch (IOException e) {
      logger.severe("Error loading image.");
    }


    JPanel startPanel = new JPanel();
    startButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
    startButton.setPreferredSize(new Dimension(100, 40));
    startPanel.setOpaque(false);
    startPanel.add(startButton);
    add(Box.createVerticalGlue());
    add(startPanel);

    setPreferredSize(new Dimension(1200, 720));
    setOpaque(false);
  }

  /**
  * Sets the selected game difficulty and updates the appearance
  * of the difficulty buttons accordingly.
  *
  * @param value The new difficulty value ("easy", "medium", or "hard").
  * @param easyButton The button for selecting easy difficulty.
  * @param mediumButton The button for selecting medium difficulty.
  * @param hardButton The button for selecting hard difficulty.
  */
  private void setDifficulty(String value, JButton easyButton,
                             JButton mediumButton, JButton hardButton) {
    difficulty = value;
    easyButton.setBackground(Objects.equals(value, "easy") ? SELECTED_COLOR : UNSELECTED_COLOR);
    mediumButton.setBackground(Objects.equals(value, "medium") ? SELECTED_COLOR : UNSELECTED_COLOR);
    hardButton.setBackground(Objects.equals(value, "hard") ? SELECTED_COLOR : UNSELECTED_COLOR);
  }

  /**
  * Sets the selected game map and updates the appearance
  * of the map buttons accordingly.
  *
  * @param value The new map value ("map01" or "map02").
  * @param map1 The button representing map 1.
  * @param map2 The button representing map 2.
  */
  private void setMap(String value, JButton map1, JButton map2) {
    map = value;

    if (Objects.equals(value, "map01")) {
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

  /**
  * Creates a new button for selecting a game difficulty.
  *
  * @param label The label text for the button.
  * @return A JButton instance representing the difficulty selection button.
  */
  private JButton createDifficultyButton(String label) {
    JButton button = new JButton(label);
    button.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
    button.setOpaque(true);
    button.setBackground(Color.WHITE);
    button.setBorderPainted(false);
    return button;
  }

  /**
  * Creates a new button for selecting a game map.
  *
  * @param mapPath The path to the map image resource.
  * @return A JButton instance representing the map selection button.
  */
  private JButton createMapButton(String mapPath) {
    JButton button = new JButton();
    try {
      BufferedImage mapIcon = ImageIO.read(Objects.requireNonNull(getClass().getResource(mapPath)));
      button.setIcon(new ImageIcon(mapIcon.getScaledInstance(400, 300, Image.SCALE_DEFAULT)));
      button.setBorder(BorderFactory.createEmptyBorder());
      button.setOpaque(true);
      button.setBackground(Color.decode("#629D5A"));
    } catch (Exception e) {
      logger.severe("Error loading image.");
    }
    return button;
  }

  /**
  * Gets the selected game difficulty.
  *
  * @return The selected difficulty ("easy", "medium", or "hard").
  */
  public String getDifficulty() {
    return difficulty;
  }

  /**
  * Gets the selected game map.
  *
  * @return The selected map ("map01" or "map02").
  */
  public String getMap() {
    return map;
  }

  /**
  * Gets the start button used to begin the game.
  *
  * @return The JButton instance for starting the game.
  */
  public JButton getStartButton() {
    return startButton;
  }

  /**
  * Gets the back button used to navigate back within the menu.
  *
  * @return The JButton instance for navigating back.
  */
  public JButton getBackButton() {
    return this.backButton;
  }
}