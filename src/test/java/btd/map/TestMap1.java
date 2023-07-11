package btd.map;

import javax.swing.JFrame;

import org.junit.jupiter.api.Test;

import btd.model.map.MapPanel;
import btd.view.menu.MainMenu;

public class TestMap1 {
    @Test
    public static void main(String[] args) {
        JFrame frame = new JFrame("BTD");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        MainMenu mainMenu = new MainMenu();
        mainMenu.exitButtonHandler();
        MapPanel mp = new MapPanel();
        frame.add(mp);
        frame.add(mainMenu);
        frame.pack();

        frame.setVisible(true);
        
        mp.startGameThread();
    }
}
