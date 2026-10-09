package brickBreakerGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class gameInfo extends JFrame implements ActionListener{
	
	JTextArea text;
    JScrollPane scrollPane;
    JMenuBar menu;
	JMenu Back;
	JMenuItem back1;
    
    gameInfo(){
   	 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   	 this.setSize(600,600);
   	 this.setResizable(false);
   	 this.setLocationRelativeTo(null);
   	 this.getContentPane().setBackground(new Color(20, 12, 40));
   	 this.setTitle("Game Information");
   	 this.setLayout(null);
   	 
   	 
   	 
   	 
   	
   	text=new JTextArea();
       text.setEditable(false);
       text.setLineWrap(true);
       text.setWrapStyleWord(true);
       text.setMargin(new java.awt.Insets(24,24,24,24));
       text.setForeground(Color.WHITE);
       text.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 17));
       text.setBackground(new Color(28, 18, 48));
   	text.setText("Brick Breaker is a classic 2D arcade-style game built using Java Swing.\r\n"
   			+ "The objective of the game is simple yet challenging: control a paddle to bounce a ball and break all the bricks without letting the ball "
   			+ "fall off the bottom of the screen.\r\n"
   			+ "\n\nControls: Left and right arrow keys.\n\nAbidurahman Niazi\r\n");
   	 
   	scrollPane = new JScrollPane(text);
   	scrollPane.setBounds(35, 35, 515, 460);
   	scrollPane.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(102, 61, 117)));
   	
   	
   	menu=new JMenuBar();
	Back=new JMenu("Back");
	back1=new JMenuItem("Back to Menu");
	back1.addActionListener(this);
	
	 this.setJMenuBar(menu);
	 menu.add(Back);
	 Back.add(back1);
   	 this.add(scrollPane);
   	 this.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==back1) {
			dispose();
			Menu menu=new Menu();
		}
	}


}
