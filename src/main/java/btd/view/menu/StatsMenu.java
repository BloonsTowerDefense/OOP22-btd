package btd.view.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * The StatsMenu class represents a graphical user interface panel for displaying game statistics.
 * This panel provides labels for displaying the player's remaining life and available money.
 */
public class StatsMenu extends JPanel {

  private final JLabel lifeLabel;
  private final JLabel moneyLabel;

  /**
   * Constructs a StatsMenu object. Initializes the panel layout, labels for displaying life and
   * money, and sets the background color.
   */
  public StatsMenu() {
    setLayout(new GridLayout(2, 2));

    lifeLabel = new JLabel("100");
    lifeLabel.setFont(new Font("Arial", Font.BOLD, 20));
    lifeLabel.setHorizontalAlignment(SwingConstants.CENTER);
    lifeLabel.setForeground(Color.WHITE);

    moneyLabel = new JLabel("100");
    moneyLabel.setFont(new Font("Arial", Font.BOLD, 20));
    moneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
    moneyLabel.setForeground(Color.WHITE);

    JLabel yourLife = new JLabel("Your life: ");
    yourLife.setHorizontalAlignment(SwingConstants.CENTER);
    yourLife.setForeground(Color.WHITE);

    JLabel yourMoney = new JLabel("Your money: ");
    yourMoney.setHorizontalAlignment(SwingConstants.CENTER);
    yourMoney.setForeground(Color.WHITE);

    add(yourLife);
    add(lifeLabel);
    add(yourMoney);
    add(moneyLabel);

    setBackground(Color.decode("#629D5A"));
    setPreferredSize(new Dimension(200, 100));
  }

  /**
   * Sets the text of the life label.
   *
   * @param life the value to be displayed as remaining life
   */
  public void setLifeLabel(String life) {
    this.lifeLabel.setText(life);
  }

  /**
   * Sets the text of the money label.
   *
   * @param money the value to be displayed as available money
   */
  public void setMoneyLabel(String money) {
    this.moneyLabel.setText(money);
  }

  /**
   * Retrieves the amount of money displayed in the money label.
   *
   * @return the available money as an Integer
   */
  public Integer getMoney() {
    return Integer.parseInt(this.moneyLabel.getText());
  }
}
