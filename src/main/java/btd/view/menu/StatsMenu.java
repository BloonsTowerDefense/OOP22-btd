package btd.view.menu;

import javax.swing.*;
import java.awt.*;

public class StatsMenu extends JPanel {

    private final JLabel lifeLabel;

    private final JLabel moneyLabel;

    public StatsMenu(){
        setLayout(new GridLayout(2,2));

        lifeLabel = new JLabel("100");
        lifeLabel.setFont(new Font("Arial",Font.BOLD,20));
        lifeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        moneyLabel = new JLabel("100");
        moneyLabel.setFont(new Font("Arial",Font.BOLD,20));
        moneyLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(new JLabel("Your life: "));
        add(lifeLabel);
        add(new JLabel("Your money: "));
        add(moneyLabel);
        setBackground(Color.decode("#629D5A"));
        setPreferredSize(new Dimension(200,100));
    }

    public void setLifeLabel(String life){
        this.lifeLabel.setText(life);
    }

    public void setMoneyLabel(String money) {
        this.moneyLabel.setText(money);
    }

    public Integer getMoney(){
        return Integer.parseInt(this.moneyLabel.getText());
    }
}
