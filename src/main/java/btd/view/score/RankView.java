package btd.view.score;

import javax.swing.*;

import btd.controller.score.RankController;

import java.awt.*;
import java.util.Iterator;
import java.util.Map;

public class RankView extends JPanel {
    private RankController controller;

    public RankView(RankController controller) {
        this.controller = controller;
        setPreferredSize(new Dimension(1200, 720));
        setLayout(new BorderLayout());

        // North section with label
        JLabel titleLabel = new JLabel("Migliori punteggi BTD");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setPreferredSize(new Dimension(1200, 150));
        northPanel.setBackground(Color.RED); // Set background color for North section
        northPanel.add(titleLabel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);

        // West section
        JPanel westPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension(150, 720));
        add(westPanel, BorderLayout.WEST);

        // East section
        JPanel eastPanel = new JPanel();
        eastPanel.setPreferredSize(new Dimension(150, 720));
        add(eastPanel, BorderLayout.EAST);

        // South section
        JPanel southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(1200, 300));
        add(southPanel, BorderLayout.SOUTH);

        //Center
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(5, 2));
        Iterator<Map.Entry<Integer, String>> it = this.controller.getRank().entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Integer, String> en = it.next();
            String name = en.getValue();
            int score = en.getKey();
            JLabel nameLabel = new JLabel(name);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
            nameLabel.setHorizontalAlignment(JLabel.CENTER);
            JLabel scoreLabel = new JLabel(Integer.toString(score));
            scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
            scoreLabel.setHorizontalAlignment(JLabel.CENTER);
            centerPanel.add(nameLabel);
            centerPanel.add(scoreLabel);
        }

        if(this.controller.getRank().size() < 3){
            addPadding(centerPanel);
        }
        add(centerPanel);
    }

    private void addPadding(JPanel panel){
        int delta = 3 - this.controller.getRank().size();
        for(int i = 0; i < delta; i++){
            JLabel tmp = new JLabel();
            JLabel tmp2 = new JLabel();
            panel.add(tmp);
            panel.add(tmp2);
        }
    }
}
