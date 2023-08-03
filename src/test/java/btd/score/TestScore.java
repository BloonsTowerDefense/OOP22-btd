package btd.score;

import javax.swing.JFrame;

import btd.controller.score.RankController;
import btd.model.score.RankModel;
import btd.view.score.RankView;

public class TestScore {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        RankModel model = new RankModel();
        RankController controller = new RankController(model);
        RankView panel = new RankView(controller);

        JFrame frame = new JFrame("Classifica");
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
