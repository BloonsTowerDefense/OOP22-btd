package btd.map;

import javax.swing.JFrame;

import org.junit.jupiter.api.Test;

import btd.model.map.MapPanel;

public class TestMap1 {
    @Test
    public static void main(String[] args) {
        JFrame frame = new JFrame("BTD");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        MapPanel mp = new MapPanel();
    
        frame.add(mp);
        frame.pack();

        frame.setVisible(true);
        
        mp.startGameThread();
    }
}
