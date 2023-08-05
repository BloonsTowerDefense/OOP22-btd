package btd.view.menu;

import btd.model.entity.Tower;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class TowerUpgradeMenu extends JPanel{

    private final JButton upgradeButton = new JButton();

    public TowerUpgradeMenu(Tower tower){
        setPreferredSize(new Dimension(200,720));
        setBackground(Color.decode("#629D5A"));
        GridLayout mainLayout = new GridLayout(3,1);
        setLayout(mainLayout);

        BufferedImage currentTowerImage = tower.getTowerSprite();
        JLabel currentTowerLabel = new JLabel(new ImageIcon(currentTowerImage.getScaledInstance(90,100,Image.SCALE_DEFAULT)));
        tower.update();
        BufferedImage nextTowerImage = tower.getTowerSprite();
        JLabel nextTowerLabel = new JLabel(new ImageIcon(nextTowerImage.getScaledInstance(90,100,Image.SCALE_DEFAULT)));
        try{
            BufferedImage arrowDown = ImageIO.read(Objects.requireNonNull(getClass().getResource("/menuSprite/icons/arrow_down.png")));
            upgradeButton.setIcon(new ImageIcon(arrowDown.getScaledInstance(100,100,Image.SCALE_DEFAULT)));
            add(currentTowerLabel);
            add(upgradeButton);
            add(nextTowerLabel);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public JButton getUpgradeButton(){
        return this.upgradeButton;
    }
}
