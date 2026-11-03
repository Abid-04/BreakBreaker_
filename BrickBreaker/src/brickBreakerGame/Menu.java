
package brickBreakerGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu extends JFrame implements ActionListener, MouseListener {

    JPanel panel;
    JButton startButton, InfoButton, exitButton;
    Clip clip5;
    Image backgroundImage;

    Menu() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setTitle("BrickBreaker");
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        backgroundImage = new ImageIcon(
            "src//BricksBreak//retro-futuristic-background-free-vector.jpg"
        ).getImage();

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(
                    backgroundImage,
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    this
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

        startButton.setBounds(125, 180, 350, 70);
        InfoButton.setBounds(125, 270, 350, 70);
        exitButton.setBounds(125, 360, 350, 70);

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
        // TODO Auto-generated method stub

        if (e.getSource() == startButton) {
            stopMusic();
            dispose();
            MyFrame frame = new MyFrame();
        }

        else if (e.getSource() == InfoButton) {
            stopMusic();
            dispose();
            gameInfo info = new gameInfo();
        }

        else if (e.getSource() == exitButton) {
            stopMusic();
            dispose();
            System.exit(0);
        }
    }

    public void playMusic(String filePath) {

        File file = new File(filePath);

        try {
            AudioInputStream audio1 =
                AudioSystem.getAudioInputStream(file);

            try {
                clip5 = AudioSystem.getClip();
                clip5.open(audio1);
                clip5.start();
                clip5.loop(Clip.LOOP_CONTINUOUSLY);

            } catch (LineUnavailableException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (UnsupportedAudioFileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void stopMusic() {
        if (clip5 != null) {
            clip5.stop();
            clip5.close();
        }
    }

    private void styleButton(JButton btn) {

        btn.setUI(new javax.swing.plaf.basic.BasicButtonUI());

        btn.setBackground(Color.WHITE);
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorderPainted(true);

        btn.setForeground(Color.BLACK);
        btn.setFont(new Font("Dialog", Font.BOLD, 25));

        btn.setFocusPainted(false);
        btn.setFocusable(false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JButton btn = (JButton) e.getSource();
        btn.setBackground(new Color(220, 220, 220));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JButton btn = (JButton) e.getSource();
        btn.setBackground(Color.WHITE);
    }
}
