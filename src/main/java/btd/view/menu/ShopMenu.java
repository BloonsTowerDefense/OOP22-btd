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
        blackAdam = addTowerIcon("Black Adam", "/towers/blackAdam/Upgrade0/sprite0.png","100");
        voldelife = addTowerIcon("Voldelife", "/towers/voldelife/Upgrade0/sprite0.png","200");
        deadColossus = addTowerIcon("Dead Colossus", "/towers/deadColossus/Upgrade0/sprite0.png","300");
        rangeEnhancer = addTowerIcon("Range Enhancer", "/towers/rangeEnhancer/Upgrade0/sprite0.png","100");
        powerEnhancer = addTowerIcon("Power Enhancer", "/towers/powerEnhancer/Upgrade0/sprite0.png","200");
        add(blackAdam);
        add(voldelife);
        add(deadColossus);
        add(rangeEnhancer);
        add(powerEnhancer);
    }

    private JButton addTowerIcon(String towerName, String spritePath, String towerPrice) {
        JButton button = new JButton();
        button.setLayout(new BorderLayout());
        // Load the tower icon image
        try{
            BufferedImage sprite = ImageIO.read(Objects.requireNonNull(getClass().getResource(spritePath)));
            button.setBackground(Color.WHITE);
            button.setToolTipText(towerName);
            button.add(BorderLayout.CENTER,new JLabel(new ImageIcon(sprite.getScaledInstance(70,80,Image.SCALE_DEFAULT))));
            JLabel towerInfo = new JLabel(towerName+ ": "+towerPrice+" coins");
            towerInfo.setHorizontalAlignment(SwingConstants.CENTER);
            towerInfo.setFont(new Font("Arial",Font.BOLD,12));
            button.add(BorderLayout.SOUTH,towerInfo);
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
