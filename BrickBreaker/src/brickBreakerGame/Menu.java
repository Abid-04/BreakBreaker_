package brickBreakerGame;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;
public class Menu extends JFrame implements ActionListener, MouseListener {

    JPanel panel;
    JButton startButton, InfoButton, exitButton;
    Clip clip5;
    Image backgroundImage;

    Menu() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setTitle("Brick Breaker");
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        backgroundImage = new ImageIcon(
                "src//BricksBreak//retro-futuristic-background-free-vector.jpg"
        ).getImage();

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                g.setColor(new Color(12, 8, 30, 85));
                g.fillRect(0, 0, getWidth(), getHeight());

                g.setColor(Color.WHITE);
                g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
                String title = "BRICK BREAKER";
                g.drawString(
                        title,
                        (getWidth() - g.getFontMetrics().stringWidth(title)) / 2,
                        120
                );

                g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
                String hint = "Use the arrow keys to move the paddle";
                g.drawString(
                        hint,
                        (getWidth() - g.getFontMetrics().stringWidth(hint)) / 2,
                        160
                );
            }
        };
        panel.setLayout(null);

        playMusic("src//BricksBreak//glass-gardens-loop-371924.wav");

        startButton = new JButton("Start the game");
        InfoButton = new JButton("About");
        exitButton = new JButton("Exit");

        styleButton(startButton);
        styleButton(InfoButton);
        styleButton(exitButton);

        startButton.setBounds(160, 210, 280, 58);
        InfoButton.setBounds(160, 288, 280, 58);
        exitButton.setBounds(160, 366, 280, 58);

        startButton.addActionListener(this);
        InfoButton.addActionListener(this);
        exitButton.addActionListener(this);

        startButton.addMouseListener(this);
        InfoButton.addMouseListener(this);
        exitButton.addMouseListener(this);

        panel.add(startButton);
        panel.add(InfoButton);
        panel.add(exitButton);

        this.add(panel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      

        if (e.getSource() == startButton) {
            if (clip5 != null) clip5.close();
            dispose();
            MyFrame frame = new MyFrame();
        } else if (e.getSource() == InfoButton) {
            if (clip5 != null) clip5.close();
            dispose();
            gameInfo info = new gameInfo();
        } else if (e.getSource() == exitButton) {
            if (clip5 != null) clip5.close();
            dispose();
            System.exit(0);
        }
    }

    public void playMusic(String filePath) {
        File file = new File(filePath);

        try {
            AudioInputStream audio1 = AudioSystem.getAudioInputStream(file);

            try {
                clip5 = AudioSystem.getClip();
                clip5.open(audio1);
                clip5.start();
                clip5.loop(clip5.LOOP_CONTINUOUSLY);
            } catch (LineUnavailableException | IllegalArgumentException e) {
              
                e.printStackTrace();
            }
        } catch (UnsupportedAudioFileException e) {
 
            e.printStackTrace();
        } catch (IOException e) {
          
            e.printStackTrace();
        }
    }

    private void styleButton(JButton btn) {
        btn.setBackground(new Color(0, 0, 0, 45)); // Transparent black
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorderPainted(true);
        btn.setFocusPainted(false);
        btn.setForeground(Color.GRAY);
        btn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        btn.setFocusable(false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
          }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       

        if (e.getSource() == startButton) {
            startButton.setForeground(new Color(255, 190, 232));
            startButton.setBounds(160, 210, 280, 58);
            startButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        }

        if (e.getSource() == InfoButton) {
            InfoButton.setForeground(new Color(255, 190, 232));
            InfoButton.setBounds(160, 288, 280, 58);
            InfoButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        }

        if (e.getSource() == exitButton) {
            exitButton.setForeground(new Color(255, 190, 232));
            exitButton.setBounds(160, 366, 280, 58);
            exitButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
       

        startButton.setBounds(160, 210, 280, 58);
        InfoButton.setBounds(160, 288, 280, 58);
        exitButton.setBounds(160, 366, 280, 58);

        startButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        InfoButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        exitButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        startButton.setForeground(Color.GRAY);
        InfoButton.setForeground(Color.GRAY);
        exitButton.setForeground(Color.GRAY);
    }
}