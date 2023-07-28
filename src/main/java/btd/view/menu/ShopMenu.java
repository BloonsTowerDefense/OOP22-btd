package btd.view.menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ShopMenu extends JPanel {

    public ShopMenu(){
        setLayout(new GridLayout(2,2));
        addTowerIcon("Tower1","/towers/tower1/Upgrade0/tower0.png");
        addTowerIcon("Tower2","/towers/tower2/Upgrade0/tower0.png");
    }

    private void addTowerIcon(String towerName, String imageName) {
        JButton button = new JButton();
        // Load the tower icon image
        try{
            BufferedImage sprite = ImageIO.read(Objects.requireNonNull(getClass().getResource(imageName)));
            button.setIcon(new ImageIcon(sprite.getScaledInstance(70,80,Image.SCALE_DEFAULT)));
            button.setBackground(Color.WHITE);
            button.setToolTipText(towerName);
        }catch (IOException e){
            e.printStackTrace();
        }
        // Add the button to the shop panel
        this.add(button);
    }
}
