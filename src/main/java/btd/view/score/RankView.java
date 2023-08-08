package btd.view.score;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import btd.controller.score.RankController;
import btd.utils.RankElement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * This class represents the GUI for displaying a ranked list of scores.
 * It receives data from the {@link RankController} and displays it in a JPanel.
 */
public class RankView extends JPanel {
    private final RankController controller;
    private JButton backButton;
    private final int panelWidth = 1200;
    private final int panelHeight = 720;

    /**
     * Standard cosntructor for RankView instance with a given RankController.
     *
     * @param controller the RankController instance associated with this view.
     */
    public RankView(RankController controller) {
        this.controller = controller;
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setLayout(new BorderLayout());
        backButton = new JButton();
    }

    /**
     * Paints the graphical components onto the panel, displaying the ranked list of scores.
     * This method creates various sections for displaying the scores, titles, and navigation.
     */
    public void paintPanel() {
        int fontTitleDim = 30;
        int northHeight = 150;
        int backButtonDim = 80;
        int westAndEastWidth = 150;
        int westAndEastEight = 720;
        int southWidth = 1200;
        int southEight = 300;
        int gridCol = 2;
        int fontNormalLabelDim = 20;

        // North section with label
        JLabel titleLabel = new JLabel("Migliori punteggi BTD");
        titleLabel.setFont(new Font("Arial", Font.BOLD, fontTitleDim));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setPreferredSize(new Dimension(panelWidth, northHeight));
        northPanel.setBackground(Color.RED); // Set background color for North section

        try {
            BufferedImage backIcon = ImageIO.read(Objects.requireNonNull(getClass().
                getResource("/menusprite/icons/backButton.png")));
            backButton.setIcon(new ImageIcon(backIcon.getScaledInstance(backButtonDim, backButtonDim, Image.SCALE_DEFAULT)));
            backButton.setBorderPainted(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        northPanel.add(backButton, BorderLayout.WEST);
        northPanel.add(titleLabel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);

        if (this.controller.getRankDimension() == 0) {
            System.out.println("Lista vuota");
            JLabel noScoreLabel = new JLabel("No score present");
            noScoreLabel.setFont(new Font("Arial", Font.BOLD, fontTitleDim));
            noScoreLabel.setHorizontalAlignment(JLabel.CENTER);
            add(noScoreLabel, BorderLayout.CENTER);
            return;
        }

        // West section
        JPanel westPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension(westAndEastWidth, westAndEastEight));
        add(westPanel, BorderLayout.WEST);

        // East section
        JPanel eastPanel = new JPanel();
        eastPanel.setPreferredSize(new Dimension(westAndEastWidth, westAndEastEight));
        add(eastPanel, BorderLayout.EAST);

        // South section
        JPanel southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(southWidth, southEight));
        add(southPanel, BorderLayout.SOUTH);

        //Center
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(this.controller.getLimit(), gridCol));
        if (this.controller.getRank() != null) {
            System.out.println("Rank non è null");
            Iterator<RankElement> it = this.controller.getRank().iterator();
            while (it.hasNext()) {
                RankElement en = it.next();
                String name = en.getUser();
                int score = en.getScore();
                System.out.println("Ora aggiungo: " + name + " " + score);
                JLabel nameLabel = new JLabel(name);
                nameLabel.setFont(new Font("Arial", Font.BOLD, fontNormalLabelDim));
                nameLabel.setHorizontalAlignment(JLabel.CENTER);
                JLabel scoreLabel = new JLabel(Integer.toString(score));
                scoreLabel.setFont(new Font("Arial", Font.BOLD, fontNormalLabelDim));
                scoreLabel.setHorizontalAlignment(JLabel.CENTER);
                centerPanel.add(nameLabel);
                centerPanel.add(scoreLabel);
            }
            if (this.controller.getRank().size() < 3) {
            addPadding(centerPanel);
            }
        }
        //centerPanel.revalidate();
        //centerPanel.repaint();
        add(centerPanel);
    }

    public void resetPanel(){
        this.removeAll();
    }

    /**
     * Returns the "Back" button.
     *
     * @return "Back" button used for navigation in {@link StartingMenu}.
     */
    public JButton getBackButton() {
        return this.backButton;
    }

    private void addPadding(final JPanel panel) {
        int delta = 3 - this.controller.getRank().size();
        for (int i = 0; i < delta; i++) {
            JLabel tmp = new JLabel();
            JLabel tmp2 = new JLabel();
            panel.add(tmp);
            panel.add(tmp2);
        }
    }
}
