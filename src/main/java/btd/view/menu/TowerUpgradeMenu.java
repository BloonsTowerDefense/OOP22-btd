package btd.view.menu;

import btd.model.entity.Tower;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Represents a menu for upgrading a tower in a game. This panel displays the current tower image,
 * an upgrade button, and the upgraded tower image.
 */
public class TowerUpgradeMenu extends JPanel {

  private final JButton upgradeButton = new JButton();

  /**
   * Constructs a TowerUpgradeMenu object for a specific tower.
   *
   * @param tower The tower to be upgraded.
   */
  public TowerUpgradeMenu(final Tower tower) {
    Logger logger = Logger.getLogger(getClass().getName());
    setPreferredSize(new Dimension(200, 720));
    setBackground(Color.decode("#629D5A"));
    GridLayout mainLayout = new GridLayout(3, 1);
    setLayout(mainLayout);

    BufferedImage currentTowerImage =
        tower.getTowerSpriteManager().getUpgradeSprites(tower.getName(), 0).get(0);
    JLabel currentTowerLabel =
        new JLabel(
            new ImageIcon(currentTowerImage.getScaledInstance(90, 100, Image.SCALE_DEFAULT)));
    BufferedImage nextTowerImage =
        tower.getTowerSpriteManager().getUpgradeSprites(tower.getName(), 1).get(0);
    JLabel nextTowerLabel =
        new JLabel(new ImageIcon(nextTowerImage.getScaledInstance(90, 100, Image.SCALE_DEFAULT)));
    try {
      BufferedImage arrowDown =
          ImageIO.read(
              Objects.requireNonNull(getClass().getResource("/menuSprite/icons/arrow_down.png")));
      upgradeButton.setLayout(new BorderLayout());
      upgradeButton.add(
          BorderLayout.CENTER,
          new JLabel(new ImageIcon(arrowDown.getScaledInstance(100, 100, Image.SCALE_DEFAULT))));
      add(currentTowerLabel);
      add(upgradeButton);
      add(nextTowerLabel);
    } catch (Exception e) {
      logger.severe("Error on loading image");
    }
  }

  /**
   * Get the upgrade button associated with this menu.
   *
   * @return The JButton representing the upgrade button.
   */
  public JButton getUpgradeButton() {
    return this.upgradeButton;
  }
}
