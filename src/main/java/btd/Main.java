//Batusha
package btd;

import javax.swing.JFrame;

import btd.model.map.MapPanel;

public class Main {
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
