import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*; //UI
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class JavaGUIMusicPlayerJFrame extends JFrame implements ActionListener {
    private JProgressBar progressBar;
    private JLabel timeLabel;
    private JTextField filePathField;
    private JButton playButton;
    private JButton pauseButton;
    private JButton chooseButton;
    private JButton loopButton;
    private boolean isPaused;
    private boolean isLooping = false;
    private JFileChooser fileChooser;
    private Clip clip;


    public JavaGUIMusicPlayerJFrame(){
        super("Music Player Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        progressBar= new JProgressBar();
        filePathField = new JTextField(20);
        timeLabel = new JLabel("00:00 / 00:00");
        playButton = new JButton("play");
        pauseButton = new JButton("pause");
        chooseButton = new JButton("Choose file");
        loopButton = new JButton("loop");
        isPaused = false;
        isLooping = false;



        //for each button, add the ability to detect that it is being clicked by adding an 'action listener'
        playButton.addActionListener(this);
        pauseButton.addActionListener(this);
        chooseButton.addActionListener(this);
        loopButton.addActionListener(this);

        //adding a textfield to the JFrame so it is visible
        add(progressBar);
        add(filePathField);
        add(chooseButton);
        add(playButton);
        add(pauseButton);
        add(loopButton);
        add(timeLabel);

        //set the default file path of the JFilechooser to be what the directory of the project
        fileChooser = new JFileChooser(".");
        fileChooser.setFileFilter(new FileNameExtensionFilter("WAV Files", "wav"));

        //setting the window options
        setSize(500,150); //size of the window
        setLocationRelativeTo(null); //position of the jframe window on screen
        setVisible(true); //whether you can see the window or not (i.e it is hidden)

        //setting up the progress bar
        progressBar.setValue(0);
        progressBar.setBounds(0,0,500, 150);



    }
    @Override
    public void actionPerformed(ActionEvent event){
        if(event.getSource() == playButton){ playMusic(); }
        else if (event.getSource()== pauseButton){ pauseMusic(); }
        else if(event.getSource() == chooseButton){ chooseFile();  }
        else if (event.getSource()== loopButton){ toggleLoop();  }

    }
    public void playMusic() {
        //if we're playing music, we want the music to stop
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
        try{
            File file = new File(filePathField.getText());
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(file);
            clip =  AudioSystem.getClip();
            clip.open(audioInput);

            if(isLooping){
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            clip.start();
            startProgressBar();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public void pauseMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            isPaused = true;
            pauseButton.setText("Resume");
        } else if (clip != null && isPaused) {
            clip.start();
            startProgressBar();

            if (isLooping) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            isPaused = false;
            pauseButton.setText("Pause");
        }

    }
    public void chooseFile() {
        fileChooser.setCurrentDirectory(new File("."));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            filePathField.setText(selectedFile.getAbsolutePath());
        }
    }

    public void toggleLoop () {
        isLooping = !isLooping;
        if (isLooping) {
            loopButton.setText("Stop Loop");
            if (clip.isRunning()) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }

        } else {
            loopButton.setText("Loop");
            if (clip.isRunning()) {
                clip.loop(0);
            }
        }
    }

    //implementing a progress bar into the program, call it in the playMusic metho
    public void startProgressBar() {
        new Thread(() -> {
            try {
                while (clip != null) {
                    long current = clip.getMicrosecondPosition();
                    long total = clip.getMicrosecondLength();
                    long positionInLoop = current % total;

                    int progress = (int) ((positionInLoop * 100) / total);
                    progressBar.setValue(progress);

                    Thread.sleep(1000);

                    long totalLengthInMicros = clip.getMicrosecondLength();
                    long totalLength = totalLengthInMicros/1_000_000;
                    long currentSecond = positionInLoop/1_000_000;

                    //get to display minute:second (ie 01:23)
                    long currentMinuteCount = currentSecond/60;
                    long currentSecondCount = currentSecond % 60;

                    long totalMinuteCount = totalLength / 60;
                    long totalSecondCount = totalLength % 60;

                    String timeText = String.format("%02d:%02d / %02d:%02d", currentMinuteCount, currentSecondCount, totalMinuteCount, totalSecondCount);

                    timeLabel.setText(timeText);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }



    public static void main (String[]args){
            new JavaGUIMusicPlayerJFrame();
        }
    }