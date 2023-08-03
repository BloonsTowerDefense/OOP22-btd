package btd.view.menu;

import btd.model.entity.Tower;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class TowerUpgradeMenu extends JPanel{

    private JButton upgradeButton;

    public TowerUpgradeMenu(Tower tower){
        GridLayout mainLayout = new GridLayout(4,1);
        setLayout(mainLayout);

        BufferedImage currentTowerImage = tower.getTowerSprite();
        JLabel currentTowerLabel = new JLabel(new ImageIcon(currentTowerImage));
        tower.update();
        BufferedImage nextTowerImage = tower.getTowerSprite();
        JLabel nextTowerLabel = new JLabel(new ImageIcon(nextTowerImage));
        try{
            BufferedImage arrowDown = ImageIO.read(Objects.requireNonNull(getClass().getResource("/menuSprite/icons/arrow_down.png")));
            JLabel arrowDownLabel = new JLabel(new ImageIcon(arrowDown));
            add(currentTowerLabel);
            add(arrowDownLabel);
            add(nextTowerLabel);
            add(upgradeButton);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public JButton getUpgradeButton(){
        return this.upgradeButton;
    }
}
