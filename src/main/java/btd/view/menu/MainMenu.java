package btd.view.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * The MainMenu class represents a graphical user interface panel for displaying the main menu. This
 * panel provides buttons for playing the game, accessing the leaderboard, and exiting the game.
 */
public class MainMenu extends JPanel {

  private static final int WIDTH = 1200;
  private static final int HEIGHT = 720;

  private final Logger logger = Logger.getLogger(getClass().getName());
  private BufferedImage menuBackground;
  private final JButton playButton;
  private final JButton leaderboardButton;
  private final JButton exitButton;

  /**
   * Constructs a MainMenu object. Initializes the panel layout, loads menu background image, and
   * sets up UI components.
   */
  public MainMenu() {
    try {
      this.menuBackground =
          ImageIO.read(
              Objects.requireNonNull(getClass().getResource("/menuSprite/mainMenuSprite.png")));
    } catch (IOException e) {
      logger.severe("Error on loading image.");
    }

    playButton = createButton("/menuSprite/icons/playSprite.png", Color.RED);
    leaderboardButton = createButton("/menuSprite/icons/leaderboardSprite.png", Color.BLUE);
    exitButton = createButton("/menuSprite/icons/exitSprite.png", Color.RED);

    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setOpaque(false);
    buttonsPanel.setLayout(new FlowLayout());
    buttonsPanel.add(playButton);
    buttonsPanel.add(leaderboardButton);
    buttonsPanel.add(exitButton);

    JLabel gameTitle = createLabel(new Font("Arial", Font.BOLD, 64));
    setLayout(new BorderLayout(0, 200));
    setOpaque(false);
    add(gameTitle, BorderLayout.NORTH);
    add(buttonsPanel, BorderLayout.CENTER);
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
  }

  /**
   * Creates a JButton with an image icon and specific background color.
   *
   * @param imagePath the path to the image file
   * @param backgroundColor the background color of the button
   * @return the created JButton
   */
  private JButton createButton(final String imagePath, final Color backgroundColor) {
    JButton button = new JButton();
    try {
      Image img = ImageIO.read(Objects.requireNonNull(getClass().getResource(imagePath)));
      button.setIcon(new ImageIcon(img.getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
      button.setBackground(backgroundColor);
      buttonProperties(button);
    } catch (IOException e) {
      logger.severe("Error on loading image.");
    }
    return button;
  }

  /**
   * Creates a JLabel with specified text and font properties.
   *
   * @param font the font to use for the label
   * @return the created JLabel
   */
  private JLabel createLabel(final Font font) {
    JLabel label = new JLabel("BLOONS TD");
    label.setFont(font);
    label.setForeground(Color.WHITE);
    label.setHorizontalAlignment(SwingConstants.CENTER);
    return label;
  }

    /**
     * Paints the background image of the panel.
     *
     * @param g the graphics context
     */
  @Override
  public void paintComponent(final Graphics g) {
    super.paintComponent(g);
    g.drawImage(this.menuBackground, 0, 0, WIDTH, HEIGHT, null);
  }

  /**
   * Configures common properties for buttons.
   *
   * @param button the button to configure
   */
  private void buttonProperties(final JButton button) {
    button.setPreferredSize(new Dimension(250, 250));
    button.setOpaque(false);
    button.setContentAreaFilled(false);
    button.setBorderPainted(false);
  }

  /**
   * Retrieves the "Play" button component.
   *
   * @return the "Play" button
   */
  public JButton getPlayButton() {
    return this.playButton;
  }

  /**
   * Retrieves the "Leaderboard" button component.
   *
   * @return the "Leaderboard" button
   */
  public JButton getLeaderboardButton() {
    return this.leaderboardButton;
  }

  /**
   * Retrieves the "Exit" button component.
   *
   * @return the "Exit" button
   */
  public JButton getExitButton() {
    return this.exitButton;
  }
}
