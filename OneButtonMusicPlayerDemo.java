import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;


public class OneButtonMusicPlayerDemo {
    static void main() {
        String fileName = "son whysdm, the black star (demo + other showcase).wav";
        playMusic(fileName);
        // to stop the thread from ending before it actually gets to play any music

    }
    public static void playMusic(String fileLocation){
        try {
            File filepath = new File(fileLocation);
            if (filepath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(filepath);
                Clip audioclip = AudioSystem.getClip();
                audioclip.open(audioInput);
                audioclip.start();
                //add functionality for autolooping
                audioclip.loop(6); //specify the number of times you want to loop something
                audioclip.loop(Clip.LOOP_CONTINUOUSLY);//loops an infinite number of times
                //add pause/play functions
                JOptionPane.showMessageDialog(null, "Hit OK to pause");
                long timeStamp = audioclip.getMicrosecondPosition();
                audioclip.stop(); //stops the audio at the timestamp
                JOptionPane.showMessageDialog(null, "Hit OK to resume");
                audioclip.setMicrosecondPosition(timeStamp); //sets the start time stamp of the clip to where it was paused
                audioclip.start();
                JOptionPane.showMessageDialog(null, "press 'ok' to stop playing!");

            } else {
                System.out.println("Can't find file");
            }
        }

        catch(Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
    }


}

//at the moment the UI itself is handling everything so im going to split it up
