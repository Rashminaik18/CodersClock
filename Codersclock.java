import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;

public class Codersclock implements ActionListener
{
    JButton button = new JButton();//creating a button called play
    JLabel label = new JLabel("CLICK ON THE BUTTON TO START THE TIMER");//Label to display message, it can be updated later
    JPanel buttonPanel = new JPanel();//new add
    JPanel panel = new JPanel();//panel to add the label into
    JFrame frame = new JFrame("CODER'S CLOCK");//create a window
    JButton resetbutton = new JButton();
    File soundFile = new File("C:/Users/user/OneDrive/Desktop/project/doorbell.wav");
    File audioFile = new File("C:/Users/user/OneDrive/Desktop/project/audios/welcome.wav");
    File readFile=new File("C:/Users/user/OneDrive/Desktop/project/audios/read.wav");
    File click=new File("C:/Users/user/OneDrive/Desktop/project/audios/click.wav");
    File breakSound = new File("C:/Users/user/OneDrive/Desktop/project/audios/break.wav");
    File hintSound=new File("C:/Users/user/OneDrive/Desktop/project/audios/hint.wav");
    File solutionSound=new File("C:/Users/user/OneDrive/Desktop/project/audios/solution.wav");
    File endSound=new File("C:/Users/user/OneDrive/Desktop/project/audios/end.wav");
 Timer timer;
    Timer breakTimer;
    Timer hintTimer;
    int hintTime = 0;
    Timer breakTimer2;
    int breakTime2 = 0;
    int elapsedTime = 0;//to calculate elapsed time
    int breakTime = 0;
    Timer solutionTimer;
    int solTime = 0;

    public static void main(String[] args) 
    {
        Codersclock object = new Codersclock();
        System.out.println(object.soundFile.getAbsolutePath());
        if (!object.soundFile.exists()) {
            System.out.println("not found");
        }
        object.clocklogic();

    }

    @Override
    public void actionPerformed(ActionEvent ex) {
        button.setIcon(null);
        button.setFont(new Font("ARIAL", Font.BOLD, 50));
        button.setText("LET'S START");
        this.startTimer1();
    }

    public void startTimer1() {

        try
        {
             AudioInputStream audioIn=AudioSystem.getAudioInputStream(Codersclock.this.soundFile);
            Clip clip=AudioSystem.getClip();
            clip.open(audioIn);
           clip.start();
           Thread.sleep(clip.getMicrosecondLength()/1000);
          clip.close();
        }
        catch(Exception e)
        {
             e.printStackTrace();

        }
    label.setText("READ AND UNDERSTAND THE PROBLEM");
    try 
    {
    AudioInputStream audioStream = AudioSystem.getAudioInputStream(readFile);
    Clip clip = AudioSystem.getClip();
    clip.open(audioStream);
    clip.start();
    Thread.sleep(clip.getMicrosecondLength() / 1000);
    }
    catch (Exception e) 
    {
    System.out.println("Error playing audio: " + e.getMessage());
    }
       
        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                elapsedTime++;
                int minutes = elapsedTime / 60;
                int seconds = elapsedTime % 60;
                button.setText(String.format("%02d:%02d", minutes, seconds));
                if (elapsedTime >= 10) 
                {
                    timer.stop();
                    Codersclock.this.breakTimer();

                }
            }
        });
        timer.start();
    }

    public void breakTimer() 
    {
        button.setText("BREAK");
        label.setText("IT'S A BREAK");
        try 
        {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(Codersclock.this.soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            Thread.sleep(clip.getMicrosecondLength() / 500);
            clip.close();
        } 
        catch (Exception e)
        {
            e.printStackTrace();

        }
        try {
            
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(breakSound);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Error playing break sound: " + e.getMessage());
        }
        this.breakTimer = new Timer(1000, new ActionListener() {
            @Override
            
            public void actionPerformed(ActionEvent a) {
                breakTime++;
                int minutes = breakTime / 60;
                int seconds = breakTime % 60;
                button.setText(String.format("%02d:%02d", minutes, seconds));
                if (breakTime >= 10)        
                {                       
                    breakTimer.stop();    
                    Codersclock.this.hintTimer();
                }

            }
        });
        breakTimer.start();
                           
    }

    public void hintTimer() {
       
        button.setText("HINT TIME!!");
        label.setText("ASK FOR HINTS, BUT DONT LOOK FOR THE SOLUTION.");
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(Codersclock.this.soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            Thread.sleep(clip.getMicrosecondLength() / 500);
            clip.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        try 
    {
    AudioInputStream audioStream = AudioSystem.getAudioInputStream(hintSound);
    Clip clip = AudioSystem.getClip();
    clip.open(audioStream);
    clip.start();
    Thread.sleep(clip.getMicrosecondLength() / 1000);
    }
    catch (Exception e) 
    {
    System.out.println("Error playing audio: " + e.getMessage());
    }

            this.hintTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                hintTime++;
                int minutes = hintTime / 60;
                int seconds = hintTime % 60;
                button.setText(String.format("%02d:%02d", minutes, seconds));
                if (hintTime >= 10) {
                    hintTimer.stop();
                    Codersclock.this.breakTimer2();
                }

            }
        });
        hintTimer.start();
    }

    public void breakTimer2() {
        button.setText("RELAX");
        label.setText("IT'S A BREAK BUDDY");
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(Codersclock.this.soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            Thread.sleep(clip.getMicrosecondLength() / 500);
            clip.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        try {
            
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(breakSound);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Error playing break sound: " + e.getMessage());
        }

        this.breakTimer2 = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                breakTime2++;
                int minutes = breakTime2 / 60;
                int seconds = breakTime2 % 60;
                button.setText(String.format("%02d:%02d", minutes, seconds));
                if (breakTime2 >= 10) {
                    breakTimer2.stop();

                    Codersclock.this.solutionTimer();
                }

            }
        });
        breakTimer2.start();

    }

    public void solutionTimer() {
        button.setText("SOLUTION TIME!!");
        label.setText("LET'S HAVE A LOOK AT THE SOLUTION");
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(Codersclock.this.soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            Thread.sleep(clip.getMicrosecondLength() / 500);
            clip.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        try 
    {
    AudioInputStream audioStream = AudioSystem.getAudioInputStream(solutionSound);
    Clip clip = AudioSystem.getClip();
    clip.open(audioStream);
    clip.start();
    Thread.sleep(clip.getMicrosecondLength() / 1000);
    }
    catch (Exception e) 
    {
    System.out.println("Error playing audio: " + e.getMessage());
    }

        this.solutionTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                solTime++;
                int minutes = solTime / 60;
                int seconds = solTime % 60;
                button.setText(String.format("%02d:%02d", minutes, seconds));
                if (solTime >= 10) {
                    solutionTimer.stop();
                    label.setText("WE did it");
                    
                    try {
                        AudioInputStream audioIn = AudioSystem.getAudioInputStream(Codersclock.this.soundFile);
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioIn);
                        clip.start();
                        Thread.sleep(clip.getMicrosecondLength() / 500);
                        clip.close();
                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                    try 
    {
    AudioInputStream audioStream = AudioSystem.getAudioInputStream(endSound);
    Clip clip = AudioSystem.getClip();
    clip.open(audioStream);
    clip.start();
    Thread.sleep(clip.getMicrosecondLength() / 1000);
    }
    catch (Exception e) 
    {
    System.out.println("Error playing audio: " + e.getMessage());
    }
                    

                }

            }
        });
        solutionTimer.start();

    }

    public void clocklogic() {
        frame.setSize(600, 400);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//setDefaultCloseOperation() is a method of the JFrame class that sets the behavior when the user attempts to close the window.
        frame.setLayout(new GridBagLayout());

        File imageFile = new File("C:\\Users\\user\\OneDrive\\Desktop\\icon.png");

        ImageIcon originalIcon = new ImageIcon(imageFile.getAbsolutePath());
        Image image = originalIcon.getImage();
        Image resizedImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        label.setHorizontalAlignment(SwingConstants.CENTER);//centering the text in the label
        label.setFont(new Font("Arial", Font.BOLD, 40));//setting the font of the text in the label
        label.setOpaque(true);
        //panel.setLayout(new BorderLayout());//the contents in layout need to be arranged in Border layout fashion,i.e north south,east,west,centre)
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        frame.add(label, gbc);
        gbc.gridy = 1;
        gbc.weighty = 0.5;
        frame.add(new JLabel(), gbc);

        button.setIcon(resizedIcon);
        button.setFont(new Font("ARIAL", Font.PLAIN, 100));//setting font of the button
        button.setPreferredSize(new Dimension(400, 300));//setting dimensions of the button according to the frame size
        button.addActionListener(this);
        button.setBackground(new Color(255, 255, 255));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(button, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.NONE;
        frame.add(new JLabel(), gbc);
        frame.setVisible(true);
         try {
            
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

         
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

        
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        

        resetbutton.setText("RESET");
        resetbutton.setFont(new Font("Arial", Font.BOLD, 30));
        resetbutton.setPreferredSize(new Dimension(200, 80));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.SOUTH;
        frame.add(resetbutton, gbc);

        resetbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Stop all running timers
                if (timer != null) {
                    timer.stop();
                }
                if (breakTimer != null) {
                    breakTimer.stop();
                }
                if (hintTimer != null) {
                    hintTimer.stop();
                }
                if (breakTimer2 != null) {
                    breakTimer2.stop();
                }
                if (solutionTimer != null) {
                    solutionTimer.stop();
                }

                // Reset all timer values
                elapsedTime = 0;
                breakTime = 0;
                hintTime = 0;
                breakTime2 = 0;
                solTime = 0;

                // Reset button text and label
                button.setText("LET'S START");
                label.setText("CLICK ON THE BUTTON TO START THE TIMER");
            }
        });
    }

}
