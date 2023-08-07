package btd.view.score;
import javax.imageio.ImageIO;
import javax.swing.*;

import btd.controller.score.RankController;
import btd.utils.RankElement;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.Objects;

/**
 * This class represents the GUI for displaying a ranked list of scores.
 * It receives data from the {@link RankController} and displays it in a JPanel.
 */
public class RankView extends JPanel {
    private RankController controller;
    private JButton backButton;

    /**
     * Standard cosntructor for RankView instance with a given RankController.
     *
     * @param controller the RankController instance associated with this view.
     */
    public RankView(RankController controller) {
        this.controller = controller;
        setPreferredSize(new Dimension(1200, 720));
        setLayout(new BorderLayout());
         backButton = new JButton();
    }

    /**
     * Paints the graphical components onto the panel, displaying the ranked list of scores.
     * This method creates various sections for displaying the scores, titles, and navigation.
     */
    public void paintPanel(){
        // North section with label
        JLabel titleLabel = new JLabel("Migliori punteggi BTD");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setPreferredSize(new Dimension(1200, 150));
        northPanel.setBackground(Color.RED); // Set background color for North section

       
        try {
            BufferedImage backIcon = ImageIO.read(Objects.requireNonNull(getClass().getResource("/menusprite/icons/backButton.png")));
            backButton.setIcon(new ImageIcon(backIcon.getScaledInstance(80,80,Image.SCALE_DEFAULT)));
            backButton.setBorderPainted(false);
        }catch (Exception e){
            e.printStackTrace();
        }
        northPanel.add(backButton,BorderLayout.WEST);
        northPanel.add(titleLabel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);

        if(this.controller.getRankDimension() == 0){
            System.out.println("Lista vuota");
            JLabel noScoreLabel = new JLabel("No score present");
            noScoreLabel.setFont(new Font("Arial", Font.BOLD, 30));
            noScoreLabel.setHorizontalAlignment(JLabel.CENTER);
            add(noScoreLabel, BorderLayout.CENTER);
            return;
        }

        // West section
        JPanel westPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension(150, 720));
        add(westPanel, BorderLayout.WEST);

        // East section
        JPanel eastPanel = new JPanel();
        eastPanel.setPreferredSize(new Dimension(150, 720));
        add(eastPanel, BorderLayout.EAST);

        // South section
        JPanel southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(1200, 300));
        add(southPanel, BorderLayout.SOUTH);

        //Center
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(5, 2));
        if(this.controller.getRank() != null){
            System.out.println("Rank non è null");
            Iterator<RankElement> it = this.controller.getRank().iterator();
            while (it.hasNext()) {
                RankElement en = it.next();
                String name = en.getUser();
                int score = en.getScore();
                JLabel nameLabel = new JLabel(name);
                nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
                nameLabel.setHorizontalAlignment(JLabel.CENTER);
                JLabel scoreLabel = new JLabel(Integer.toString(score));
                scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
                scoreLabel.setHorizontalAlignment(JLabel.CENTER);
                centerPanel.add(nameLabel);
                centerPanel.add(scoreLabel);
            }
            if(this.controller.getRank().size() < 3){
            addPadding(centerPanel);
            }
        }
        add(centerPanel);
    }

    /**
     * Returns the "Back" button.
     *
     * @return "Back" button used for navigation in {@link StartingMenu}.
     */
    public JButton getBackButton(){
        return this.backButton;
    }

    private void addPadding(JPanel panel){
        int delta = 3 - this.controller.getRank().size();
        for(int i = 0; i < delta; i++){
            JLabel tmp = new JLabel();
            JLabel tmp2 = new JLabel();
            panel.add(tmp);
            panel.add(tmp2);
        }
    }
}
