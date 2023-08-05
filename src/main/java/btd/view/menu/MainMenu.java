package btd.view.menu;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class MainMenu extends JPanel{

    private final static int WIDTH = 1200;
    private final static int HEIGHT = 720;

    private BufferedImage menuBackground;
    private final JButton playButton;
    private final JButton leaderboardButton;
    private final JButton exitButton;

    public MainMenu() {
        try {
            this.menuBackground = ImageIO.read(Objects.requireNonNull(getClass().getResource("/menuSprite/mainMenuSprite.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.print("\n MainMenu dopo trycath");
        playButton = createButton("/menuSprite/icons/playSprite.png", Color.RED);
        leaderboardButton = createButton("/menuSprite/icons/leaderboardSprite.png", Color.BLUE);
        exitButton = createButton("/menuSprite/icons/exitSprite.png", Color.RED);

        // GAME TITLE
        JLabel gameTitle = createLabel("BLOONS TD", new Font("Arial", Font.BOLD, 64));

        // BUTTONS PANEL
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(playButton);
        buttonsPanel.add(leaderboardButton);
        buttonsPanel.add(exitButton);

        // ADD ALL PANELS TO THE MAIN PANEL
        setLayout(new BorderLayout(0, 200));
        setOpaque(false);
        add(gameTitle, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.CENTER);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    //Create Utils class to return edited JButton
    private JButton createButton(String imagePath, Color backgroundColor) {
        JButton button = new JButton();
        try {
            Image img = ImageIO.read(Objects.requireNonNull(getClass().getResource(imagePath)));
            button.setIcon(new ImageIcon(img.getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
            button.setBackground(backgroundColor);
            buttonProperties(button);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return button;
    }

    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(this.menuBackground,0,0,WIDTH,HEIGHT,null);
    }

    private void buttonProperties(JButton button){
        button.setPreferredSize(new Dimension(250,250));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
    }


    public JButton getPlayButton(){
        return this.playButton;
    }

    public JButton getLeaderboardButton(){
        return this.leaderboardButton;
    }

    public JButton getExitButton(){
        return this.exitButton;
    }
}