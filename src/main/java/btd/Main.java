//Batusha
package btd;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import btd.model.map.MapPanel;
import btd.view.menu.MainMenu;

//import java.io.File;
//import java.net.URISyntaxException;
//import java.net.URL;
import javax.swing.*;

import btd.model.Game;
//import btd.model.entity.Bloon;
//import btd.model.entity.BloonImpl;
//import btd.model.entity.BloonType;
//import btd.model.map.PathImpl;
//import btd.model.map.Path;

public class Main extends JFrame{

    private JPanel contentPane;
    private CardLayout cardLayout;

    private MainMenu mainMenu;
    private MapPanel mapPanel;
    private Game game;


    public Main(){
        cardLayout = new CardLayout();
        contentPane = new JPanel(cardLayout);

        mainMenu = new MainMenu();
        mapPanel = new MapPanel();
        game = new Game();

        contentPane.add(mainMenu, "MENU");
        contentPane.add(mapPanel, "MAP");

        JButton playButton = mainMenu.getPlayButton();
        playButton.addActionListener(actionEvent -> {
            game.start();
            System.out.println("Start");

            cardLayout.show(contentPane, "MAP");

            mapPanel.startGameThread();
            pack();
        });

        JButton exitButton = mainMenu.getExitButton();
        exitButton.addActionListener(actionEvent -> {
            System.exit(0);
        });

        setContentPane(contentPane);
        pack();
        setVisible(true);
    }

   public static void main(String[] args) {
        new Main();
    }
}
