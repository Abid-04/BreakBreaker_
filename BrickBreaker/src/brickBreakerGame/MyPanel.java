package brickBreakerGame;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import java.io.*;

public class MyPanel extends JPanel implements ActionListener{
	
	
	Image gameBackground,paddle,ball,brick;
	JLabel label1,label2,highestScore;
	Timer timer,timer2;
	Action right,left;
	Rectangle rec1,rec2,rec3;
	String[][] bricks; 
	Random random=new Random();
	Clip backgroundClip,smashClip;
	Scanner scanner;
	int score=0;
	
	
	
	int brickWidth=150;
	int brickHeight=40;
	
	int panel_width=1480;
	int panel_height=850;
	
	int paddleX=660;
	int paddleY=630;
	int movement=50;
	
	int ballX=500;
	int ballY=450;
	int BXmovement=1;
	int BYmovement=4;
	
	int score1 = 0;
	int highScore = 0;
	
	
	
	MyPanel(){
		this.setPreferredSize(new Dimension(panel_width,panel_height));
	    this.setLayout(null);
	    
	    highScore=readHighScore();
	    
	    
		
		gameBackground=new ImageIcon("src//BricksBreak//retro-futuristic-background-free-vector.jpg").getImage();
		paddle=new ImageIcon("src//BricksBreak//firewall (1).png").getImage();
		ball=new ImageIcon("src//BricksBreak//pet-ball.png").getImage();
		brick=new ImageIcon("src//BricksBreak//brick.png").getImage();
		
		
		
		
		timer=new Timer(random.nextInt(10, 16),this);
		
		
		String name=JOptionPane.showInputDialog("Write player's name: ");
		 if(name==null || name.isBlank()) name="Player";
		 if(!(name.isEmpty()||name.isBlank())) {
			timer.start();
		}
		label1=new JLabel();
		label1.setText("Player: "+name);
		label1.setForeground(Color.WHITE);
		label1.setBounds(24, 212, 430, 32);
		label1.setFont(new Font(Font.SANS_SERIF,Font.BOLD, 20));
		
		label2=new JLabel();
		label2.setText("Score: 0");
		label2.setBounds(24, 250, 220, 32);
		label2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		label2.setForeground(Color.WHITE);
		
		highestScore=new JLabel();
		highestScore.setText("Highest Score: "+highScore);
		highestScore.setBounds(1135, 212, 320, 32);
		highestScore.setForeground(Color.WHITE);
		highestScore.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		
		playMusic("src//BricksBreak//glass-gardens-loop-371924.wav");
		if(backgroundClip!=null) backgroundClip.start();
		rec2=new Rectangle(ballX,ballY,ball.getWidth(null),ball.getHeight(null));
		bricks=new String[][]{{"1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"}
		                    ,{"1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"}
		                    ,{"1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"}};
		
		
		
		right=new rightMovement();
		left=new leftMovement();
		
		
		
		this.add(label1);
		this.add(label2);
		this.add(highestScore);
		this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
		this.getActionMap().put("rightAction", right);
		this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "leftAction");
		this.getActionMap().put("leftAction", left);
		
		
	}
	
	
	public void paintComponent(Graphics g)  {
		
		super.paintComponent(g);
		
		Graphics2D g2D=(Graphics2D) g;
		g2D.drawImage(gameBackground, 0, 0, null);
        g2D.setColor(new Color(16, 10, 35, 185));
        g2D.fillRoundRect(12, 200, 460, 92, 12, 12);
        g2D.fillRoundRect(1120, 200, 348, 56, 12, 12);
        g2D.setColor(new Color(238, 215, 246));
        g2D.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        g2D.drawString("LEFT / RIGHT   Move the paddle", 24, 818);
		g2D.drawImage(paddle, paddleX, paddleY, null);
		g2D.drawImage(ball, ballX, ballY, null);
		
		
		g2D.setPaint(Color.DARK_GRAY);
		
		
		
		
		for(int i=0; i<bricks.length; i++) {
			
			for(int j=0; j<bricks[i].length; j++) {
				if(bricks[i][j].equals("1")) {
					g2D.drawImage(brick,j*brick.getWidth(null), i*brick.getHeight(null), null);
					rec3=new Rectangle(j*brick.getWidth(null), i*brick.getHeight(null),brick.getWidth(null),brick.getHeight(null));
					if(rec2.intersects(rec3)) {
						BYmovement = -BYmovement;
		                bricks[i][j] = "0";
		                score+=10;
		                label2.setText("Score: "+score);
		               playMusic2("src//BricksBreak//snowball-smash-1-36743.wav");
		               if(smashClip!=null) smashClip.start();
				}
					
					}
			}
		}
		
		

		
		
	}
	
	
	public class rightMovement extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			paddleX+=movement;
		}
		
	}
	public class leftMovement extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			paddleX-=movement;
		}
		
	}
	
	
	public void playMusic(String filePath) {
		
		File file=new File(filePath);
		try {
			AudioInputStream audio1=AudioSystem.getAudioInputStream(file);
			try {
				backgroundClip=AudioSystem.getClip();
				backgroundClip.open(audio1);
				if(backgroundClip!=null) backgroundClip.start();
				backgroundClip.loop(backgroundClip.LOOP_CONTINUOUSLY);
			} catch (LineUnavailableException | IllegalArgumentException e) {
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
	
	public void playMusic2(String filePath2) {
		
		File file=new File(filePath2);
		try {
			AudioInputStream audio2=AudioSystem.getAudioInputStream(file);
			try {
				smashClip=AudioSystem.getClip();
				smashClip.open(audio2);
				if(smashClip!=null) smashClip.start();
			} catch (LineUnavailableException | IllegalArgumentException e) {
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
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		ballX+=BXmovement;
		ballY-=BYmovement;
		
		
		
		if(paddleX<0 || paddleX>panel_width) {
			paddleX=0;
		}
		
		if(ballX<0 || ballX>panel_width) {
			BXmovement=-BXmovement;	
		}
		
		if(ballY<0) {
			BYmovement=-BYmovement;	
		}
		if(ballY>panel_height+ball.getHeight(null)) {
            timer.stop();
			 if(backgroundClip!=null) backgroundClip.stop();
			 if (score > highScore) {
			        highScore = score;
			        saveHighScore(highScore);
			        JOptionPane.showMessageDialog(null, 
			            "New High Score! " + highScore);
			    }
			int answer=JOptionPane.showConfirmDialog(null, "Do you want to continue?",  "Play again?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(answer==JOptionPane.NO_OPTION) {
				System.exit(0);
			}
			else if(answer==JOptionPane.YES_OPTION) {
				javax.swing.SwingUtilities.getWindowAncestor(this).dispose();
				Menu menu=new Menu();
			}
		}
		
		rec1=new Rectangle(paddleX,paddleY,paddle.getWidth(null),paddle.getHeight(null));
		rec2=new Rectangle(ballX,ballY,ball.getWidth(null),ball.getHeight(null));
		
		
		
		if(rec2.intersects(rec1)) {
			BXmovement=-BXmovement;
			BYmovement=-BYmovement;
			BXmovement=random.nextInt(-1, 4);
            BYmovement=random.nextInt(2,7);
		}
		
		
       
		repaint();
		
	}
	
	public int readHighScore() {
		   try {
			   File file=new File("highScore.txt");
			   if(!file.exists()) {
				   return 0;
			   }
			   
			   BufferedReader reader=new BufferedReader(new FileReader(file));
			   int highScore=Integer.parseInt(reader.readLine());
			   reader.close();
			   return highScore;
		   }
		   catch(Exception e) {
			   e.printStackTrace();
		        return 0;
		   }
		
	  }
	   
	   public void saveHighScore(int newScore) {
		   
		   try {
			   BufferedWriter writer=new BufferedWriter(new FileWriter("highScore.txt"));
			   writer.write(String.valueOf(newScore));
			   writer.close();
		   }
		   catch(Exception e) {
			   e.printStackTrace();
		   }
	   }

}
