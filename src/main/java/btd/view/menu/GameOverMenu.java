package btd.view.menu;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class GameOverMenu extends JPanel {

    private final static int WIDTH = 1200;
    private final static int HEIGHT = 720;

    private BufferedImage menuBackground;

    private final JTextField playerName;

    private final JButton saveScore = new JButton("Save Score");

    public GameOverMenu(){
        GridLayout mainLayout = new GridLayout(4,1);
        mainLayout.setVgap(40);
        setLayout(mainLayout);

        try {
            this.menuBackground = ImageIO.read(Objects.requireNonNull(getClass().getResource("/menuSprite/mainMenuSprite.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel gameOver = new JLabel("GAME OVER");
        gameOver.setFont(new Font("Arial",Font.BOLD,80));
        gameOver.setForeground(Color.RED);
        gameOver.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel insertName = new JLabel("Insert your name to save score");
        insertName.setHorizontalAlignment(SwingConstants.CENTER);
        insertName.setForeground(Color.GREEN);
        insertName.setFont(new Font("Arial",Font.ITALIC,60));

        JPanel playerNamePanel = new JPanel();
        playerName = new JTextField(20);
        playerNamePanel.add(playerName);
        playerNamePanel.setOpaque(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveScore);
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(100,40));

        add(gameOver);
        add(insertName);
        add(playerNamePanel);
        add(buttonPanel);
        setOpaque(false);
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
    }
    public void requestFocusForPlayerName() {
        this.playerName.requestFocus();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(this.menuBackground,0,0,WIDTH,HEIGHT,null);
    }

    public String getPlayerName(){
        return this.playerName.getText();
    }

    public JButton getSaveScore() {
        return this.saveScore;
    }
}
