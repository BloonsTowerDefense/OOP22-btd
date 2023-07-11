//Batusha
package btd;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import btd.model.map.MapPanel;
import btd.view.menu.MainMenu;

public class Main extends JFrame{

    private JPanel contentPane;
    private CardLayout cardLayout;

    public Main(){
        cardLayout = new CardLayout();
        contentPane = new JPanel(cardLayout);

        MainMenu mainMenu = new MainMenu();
        MapPanel mp = new MapPanel();

        contentPane.add(mainMenu,"MENU");
        contentPane.add(mp, "MAP");

        mainMenu.playButton().addActionListener(actionEvent -> {
            cardLayout.show(contentPane, "MAP");
            pack();
        });

        setContentPane(contentPane);
        pack();
        setVisible(true);
    }

   public static void main(String[] args) {
        new Main();
    }
}
