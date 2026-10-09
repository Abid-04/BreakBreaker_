package brickBreakerGame;

import javax.swing.JFrame;

public class MyFrame extends JFrame{
	
	
	MyPanel panel=new MyPanel();
	
	   MyFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Brick Breaker");
		this.setResizable(false);
		
		
		
		
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
