import java.awt.Color;

import javax.swing.JFrame;

public class Pong extends JFrame{
	
	private final static int gameWidth = 700;
	private final static int gameHeight = 450;
	
	private Board panel;
	
	public Pong(){
		super("PONG");
		setSize(gameWidth, gameHeight);
		setBackground(Color.WHITE);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new Board(this);
		add(panel);
		
	}
	
	public int getWidth(){
		return gameWidth;
	}
	
	public int getHeight(){
		return gameHeight;
	}
	
	public Board getPanel(){
		return panel;
	}
	
	public static void main(String[] args){
		new Pong();
	}
	
}
