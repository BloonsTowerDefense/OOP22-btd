package btd.model.menu;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class MainMenu extends JPanel{

    private final static int WIDTH = 1200;
    private final static int HEIGHT = 720;

    private BufferedImage menuBackground;
    private JButton playButton;
    private JButton leaderboard;
    private JButton createMap;
    private JButton exit;

    public MainMenu(){
        try {
            this.menuBackground = ImageIO.read(Objects.requireNonNull(getClass().getResource("/menuSprite/mainMenuSprite.png")));
        }catch (IOException e){
            e.printStackTrace();
        }

        this.playButton = new JButton();
        try {
            Image img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/menuSprite/icons/playSprite.png")));
            playButton.setIcon(new ImageIcon(img.getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
            playButton.setBackground(Color.RED);
            buttonProperties(playButton);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.leaderboard = new JButton();
        try {
            Image img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/menuSprite/icons/leaderboardSprite.png")));
            leaderboard.setIcon(new ImageIcon(img.getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
            leaderboard.setBackground(Color.BLUE);
            buttonProperties(leaderboard);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.createMap = new JButton();
        try {
            Image img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/menuSprite/icons/createMapSprite.png")));
            createMap.setIcon(new ImageIcon(img.getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
            createMap.setBackground(Color.RED);
            buttonProperties(createMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.exit = new JButton();
        try {
            Image img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/menuSprite/icons/exitSprite.png")));
            exit.setIcon(new ImageIcon(img.getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
            exit.setBackground(Color.RED);
            exit.setAction(null);
            buttonProperties(exit);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //GAME TITLE
        JLabel gameTitle = new JLabel("BLOONS TD");
        gameTitle.setFont(new Font("Arial", Font.BOLD, 64));
        gameTitle.setForeground(Color.WHITE);
        gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
        //BUTTONS PANEL
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(playButton);
        buttonsPanel.add(leaderboard);
        buttonsPanel.add(createMap);
        buttonsPanel.add(exit);
        //ADD ALL PANELS TO THE MAIN PANEL
        this.setLayout(new BorderLayout(0,300));
        this.setOpaque(false);
        this.add(gameTitle,BorderLayout.NORTH);
        this.add(buttonsPanel,BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setVisible(true);
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

    public void exitButtonHandler(){
        this.exit.addActionListener(actionEvent -> System.exit(0));
    }

    public JButton playButton(){
       return this.playButton;
    }

}
