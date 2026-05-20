import java.awt.*;
import javax.swing.*;

public class ProgressBarDemo {
    JFrame frame = new JFrame();
    JProgressBar bar = new JProgressBar();
    //this is just creating the frame for the progressbar
    public ProgressBarDemo(){
        bar.setValue(0);
        bar.setBounds(0,0,420, 100);
        frame.add(bar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 100);
        frame.setLayout(null);
        frame.setVisible(true);

        fill();



    }
    //this is the actual code for the progress bar
    public void fill(){
        int counter = 0;
        while(counter<=100) {
            bar.setValue(counter);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            counter += 10;

        }
    }

}
