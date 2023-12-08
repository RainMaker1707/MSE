package GUI;

import javax.swing.*;

public class Frame {

    public static JFrame frame;
    public static JPanel lastPanel;

    public static void createFrame(){
        frame = new JFrame();
        frame.setTitle(Constants.TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Constants.TOTAL_WIDTH, Constants.TOTAL_HEIGHT);
        frame.setVisible(true);
    }

}
