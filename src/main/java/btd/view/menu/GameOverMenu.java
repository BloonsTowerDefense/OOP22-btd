package btd.view.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * The GameOverMenu class represents a graphical user interface panel for displaying the game over
 * menu. This panel allows players to save their scores by entering their names.
 */
public class GameOverMenu extends JPanel {

  private static final int WIDTH = 1200;

  private static final int HEIGHT = 720;

  private BufferedImage menuBackground;

  private final JTextField playerName;

  private final JButton saveScore = new JButton("Save Score");

  /**
   * Constructs a GameOverMenu panel. Initializes the panel layout, loads menu background image,
   * and sets up UI components.
   */
  public GameOverMenu() {
    Logger logger = Logger.getLogger(getClass().getName());
    GridLayout mainLayout = new GridLayout(4, 1);
    mainLayout.setVgap(40);
    setLayout(mainLayout);

    try {
      this.menuBackground =
          ImageIO.read(
              Objects.requireNonNull(getClass().getResource("/menuSprite/mainMenuSprite.png")));
    } catch (IOException e) {
      logger.severe("Error on loading image");
    }

    JLabel gameOver = new JLabel("GAME OVER");
    gameOver.setFont(new Font("Arial", Font.BOLD, 80));
    gameOver.setForeground(Color.RED);
    gameOver.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel insertName = new JLabel("Insert your name to save score");
    insertName.setHorizontalAlignment(SwingConstants.CENTER);
    insertName.setForeground(Color.GREEN);
    insertName.setFont(new Font("Arial", Font.ITALIC, 60));

    JPanel playerNamePanel = new JPanel();
    playerName = new JTextField(20);
    playerNamePanel.add(playerName);
    playerNamePanel.setOpaque(false);

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(saveScore);
    buttonPanel.setOpaque(false);
    buttonPanel.setPreferredSize(new Dimension(100, 40));

    add(gameOver);
    add(insertName);
    add(playerNamePanel);
    add(buttonPanel);
    setOpaque(false);
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
  }

  /**
   * Requests focus for the player name text field, allowing the user to start typing.
   */
  public void requestFocusForPlayerName() {
    this.playerName.requestFocus();
  }

  @Override
  public void paintComponent(final Graphics g) {
    super.paintComponent(g);
    g.drawImage(this.menuBackground, 0, 0, WIDTH, HEIGHT, null);
  }

  /**
   * Retrieves the player's name entered in the text field.
   *
   * @return the player's name
   */
  public String getPlayerName() {
    return this.playerName.getText();
  }

  /**
   * Retrieves the "Save Score" button component.
   *
   * @return the "Save Score" button
   */
  public JButton getSaveScore() {
    return this.saveScore;
  }
}
