package btd.view.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
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
 * The ShopMenu class represents a graphical user interface panel for the in-game shop menu. This
 * panel provides buttons for purchasing various towers.
 */
public class ShopMenu extends JPanel {

  private final JButton blackAdam;
  private final JButton voldelife;
  private final JButton deadColossus;
  private final JButton rangeEnhancer;
  private final JButton powerEnhancer;

  /**
   * Constructs a ShopMenu object. Initializes the panel layout and adds buttons for purchasing
   * towers.
   */
  public ShopMenu() {
    setLayout(new GridLayout(5, 1));
    setBackground(Color.decode("#629D5A"));

    blackAdam = addTowerIcon("Black Adam", "/towers/blackAdam/Upgrade0/sprite0.png", "100");
    voldelife = addTowerIcon("Voldelife", "/towers/voldelife/Upgrade0/sprite0.png", "200");
    deadColossus =
        addTowerIcon("Dead Colossus", "/towers/deadColossus/Upgrade0/sprite0.png", "300");
    rangeEnhancer =
        addTowerIcon("Range Enhancer", "/towers/rangeEnhancer/Upgrade0/sprite0.png", "100");
    powerEnhancer =
        addTowerIcon("Power Enhancer", "/towers/powerEnhancer/Upgrade0/sprite0.png", "200");

    add(blackAdam);
    add(voldelife);
    add(deadColossus);
    add(rangeEnhancer);
    add(powerEnhancer);
  }

  /**
   * Method used to create buttons with tower properties.
   *
   * @param towerName tower's name.
   * @param spritePath path to image's path.
   * @param towerPrice the price of the tower.
   */
  private JButton addTowerIcon(String towerName, String spritePath, String towerPrice) {
    Logger logger = Logger.getLogger(getClass().getName());
    JButton button = new JButton();
    button.setLayout(new BorderLayout());

    try {
      BufferedImage sprite =
          ImageIO.read(Objects.requireNonNull(getClass().getResource(spritePath)));
      button.setBackground(Color.WHITE);
      button.setToolTipText(towerName);
      button.add(
          BorderLayout.CENTER,
          new JLabel(new ImageIcon(sprite.getScaledInstance(70, 80, Image.SCALE_DEFAULT))));
      JLabel towerInfo = new JLabel(towerName + ": " + towerPrice + " coins");
      towerInfo.setHorizontalAlignment(SwingConstants.CENTER);
      towerInfo.setFont(new Font("Arial", Font.BOLD, 12));
      button.add(BorderLayout.SOUTH, towerInfo);
    } catch (IOException e) {
      logger.severe("Error on loading image.");
    }

    return button;
  }

  /**
   * Retrieves the "Black Adam" tower button component.
   *
   * @return the "Black Adam" tower button
   */
  public JButton getBlackAdam() {
    return this.blackAdam;
  }

  /**
   * Retrieves the "Voldelife" tower button component.
   *
   * @return the "Voldelife" tower button
   */
  public JButton getVoldelife() {
    return this.voldelife;
  }

  /**
   * Retrieves the "Dead Colossus" tower button component.
   *
   * @return the "Dead Colossus" tower button
   */
  public JButton getDeadColossus() {
    return this.deadColossus;
  }

  /**
   * Retrieves the "Range Enhancer" button component.
   *
   * @return the "Range Enhancer" button
   */
  public JButton getRangeEnhancer() {
    return this.rangeEnhancer;
  }

  /**
   * Retrieves the "Power Enhancer" button component.
   *
   * @return the "Power Enhancer" button
   */
  public JButton getPowerEnhancer() {
    return this.powerEnhancer;
  }
}
