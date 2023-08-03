package btd.view.menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ShopMenu extends JPanel {

    private final JButton blackAdam;

    private final JButton voldelife;

    private final JButton deadColossus;

    private final JButton rangeEnhancer;

    private final JButton powerEnhancer;

    public ShopMenu(){
        setLayout(new GridLayout(5,1));
        setBackground(Color.decode("#629D5A"));
        blackAdam = addTowerIcon("Black Adam", "/towers/blackAdam/Upgrade0/sprite0.png");
        voldelife = addTowerIcon("Voldelife", "/towers/voldelife/Upgrade0/sprite0.png");
        deadColossus = addTowerIcon("Dead Colossus", "/towers/deadColossus/Upgrade0/sprite0.png");
        rangeEnhancer = addTowerIcon("Range Enhancer", "/towers/rangeEnhancer/Upgrade0/sprite0.png");
        powerEnhancer = addTowerIcon("Power Enhancer", "/towers/powerEnhancer/Upgrade0/sprite0.png");
        add(blackAdam);
        add(voldelife);
        add(deadColossus);
        add(rangeEnhancer);
        add(powerEnhancer);
    }

    private JButton addTowerIcon(String towerName, String spritePath) {
        JButton button = new JButton(towerName);
        // Load the tower icon image
        try{
            BufferedImage sprite = ImageIO.read(Objects.requireNonNull(getClass().getResource(spritePath)));
            button.setIcon(new ImageIcon(sprite.getScaledInstance(70,80,Image.SCALE_DEFAULT)));
            button.setBackground(Color.WHITE);
            button.setToolTipText(towerName);
        }catch (IOException e){
            e.printStackTrace();
        }
        // Add the button to the shop panel
        return button;
    }

    public JButton getBlackAdam(){
        return this.blackAdam;
    }

    public JButton getVoldelife(){
        return this.voldelife;
    }

    public JButton getDeadColossus(){
        return this.deadColossus;
    }

    public JButton getRangeEnhancer(){
        return this.rangeEnhancer;
    }

    public JButton getPowerEnhancer(){
        return this.powerEnhancer;
    }
}
