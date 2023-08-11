package btd.view.score;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import btd.controller.score.RankController;
import btd.model.score.RankModel;
import btd.utils.RankElement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents the GUI for displaying a ranked list of scores.
 * It receives data from the {@link RankController} and displays it in a panel.
 */
public class RankView extends JPanel {
    private static final long serialVersionUID = 100L;
    private final transient RankController controller;
    private final JButton backButton;
    private static final int TITLE_LABEL_FONT_DIM = 30;
    private static final int SCREEN_WIDTH = 1200;
    private static final int SCREEN_HEIGHT = 720;
    private static final int NORTH_PANEL_HEIGHT = 150;
    private static final int BACK_BUTTON_DIM = 80;
    private static final int STANDARD_FONT_DIM = 20;
    private static final String FONT_NAME = "Arial"; 
    private static final Logger LOGGER = Logger.getLogger(RankView.class.getName());

    /**
     * Standard constructor for RankView instance with a given RankController.
     *
     * @param controller the RankController instance associated with this view.
     */
    public RankView(final RankController controller) {
        this.controller = controller;
        this.backButton = new JButton();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setLayout(new BorderLayout());
        paintPanel();
    }

     /**
     * Paints the GUI components on the panel. At the bottom paints a title. On the left side and on the 
     * right side paints the 2 ranking.
     */
    public final void paintPanel() {
        // North section with label
        final JLabel titleLabel = new JLabel("Migliori punteggi BTD");
        titleLabel.setFont(new Font(FONT_NAME, Font.BOLD, TITLE_LABEL_FONT_DIM));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        final JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setPreferredSize(new Dimension(SCREEN_WIDTH, NORTH_PANEL_HEIGHT));
        northPanel.setBackground(Color.RED); // Set background color for North section

        try {
            final BufferedImage backIcon = ImageIO
                    .read(Objects.requireNonNull(getClass().getResource("/menusprite/icons/backButton.png")));
            this.backButton.setIcon(
                    new ImageIcon(backIcon.getScaledInstance(BACK_BUTTON_DIM, BACK_BUTTON_DIM, Image.SCALE_DEFAULT)));
            this.backButton.setBorderPainted(false);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Exception", e);
        }
        northPanel.add(backButton, BorderLayout.WEST);

        northPanel.add(titleLabel, BorderLayout.CENTER);
        this.add(northPanel, BorderLayout.NORTH);

        // West section
        final JPanel westPanel = new JPanel();
        westPanel.add(costumizePanel(westPanel, "map01"), BorderLayout.CENTER);
        this.add(westPanel, BorderLayout.WEST);

        // East section
        final JPanel eastPanel = new JPanel();
        eastPanel.add(costumizePanel(eastPanel, "map02"), BorderLayout.CENTER);
        this.add(eastPanel, BorderLayout.EAST);
    }

    /**
     * Resets the panel.
     */
    public void resetPanel() {
        this.removeAll();
    }

    /**
     * Returns the back button component.
     *
     * @return the JButton representing the back button.
     */
     public JButton getBackButton() {
        return backButton;
    }

    private JPanel costumizePanel(final JPanel panel, final String mapName) {
        panel.setPreferredSize(new Dimension(SCREEN_WIDTH / 2, SCREEN_HEIGHT));
        panel.setLayout(new BorderLayout());
        final JLabel panelTitle = new JLabel(mapName + " ranking");
        panelTitle.setFont(new Font(FONT_NAME, Font.BOLD, TITLE_LABEL_FONT_DIM));
        panelTitle.setHorizontalAlignment(JLabel.CENTER);
        panel.add(panelTitle, BorderLayout.NORTH);
        final JPanel rankPanel = new JPanel(new GridLayout(RankModel.LIMIT_SCORE, 2));
        final List<RankElement> rank = this.controller.getRank().get(mapName);
        if (rank != null) {
            final Iterator<RankElement> it = rank.iterator();
            while (it.hasNext()) {
                final RankElement en = it.next();
                final String name = en.getUser();
                final int score = en.getScore();
                final JLabel nameLabel = new JLabel(name);
                nameLabel.setFont(new Font(FONT_NAME, Font.BOLD, STANDARD_FONT_DIM));
                nameLabel.setHorizontalAlignment(JLabel.CENTER);
                final JLabel scoreLabel = new JLabel(Integer.toString(score));
                scoreLabel.setFont(new Font(FONT_NAME, Font.BOLD, STANDARD_FONT_DIM));
                scoreLabel.setHorizontalAlignment(JLabel.CENTER);
                rankPanel.add(nameLabel);
                rankPanel.add(scoreLabel);
            }
            if (rank.size() < 3) {
                addPadding(rankPanel);
            }
        } else {
            final JLabel noScoreLabel = new JLabel("No score present");
            noScoreLabel.setFont(new Font(FONT_NAME, Font.BOLD, TITLE_LABEL_FONT_DIM));
            noScoreLabel.setHorizontalAlignment(JLabel.CENTER);
            rankPanel.add(noScoreLabel, BorderLayout.CENTER);
        }
        return rankPanel;
    }

    private void addPadding(final JPanel panel) {
        final int delta = 3 - this.controller.getRank().size();
        for (int i = 0; i <= delta; i++) {
            final JLabel tmp = new JLabel();
            final JLabel tmp2 = new JLabel();
            panel.add(tmp);
            panel.add(tmp2);
        }
    }
}
