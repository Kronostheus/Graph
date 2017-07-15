import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player {
	private final static int playerWidth = 10;
	private final static int playerHeight = 60;
	
	private int xCoord;
	private int yCoord;
	private int yA;
	
	private int up;
	private int down;
	
	private int score = 0;
	
	private String msg;
	
	private Pong game;
	
	public Player(Pong pong, int number, int x, int upKey, int downKey){
		game = pong;
		msg = "Player " + number + "Wins!";
		
		xCoord = x;
		yCoord = game.getHeight() / 2;
		
		up = upKey;
		down = downKey;
	}
	
	public void keyDown(int keyCode){
		yA = up == keyCode ?  -1 : 1;
	}
	
	public void keyUp(int keyCode){
		if(up == keyCode || down == keyCode)
			yA = 0;
	}
	
	public String getMsg(){
		return msg;
	}
	
	public int getScore(){
		return score;
	}
	
	public void increaseScore(){
		score++;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(xCoord, yCoord, playerWidth, playerHeight);
	}
	
	public void paint(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(xCoord, yCoord, playerWidth, playerHeight);
	}
	
	public int ceiling(){
		return game.getHeight() - playerHeight - 29;
	}
	
	public boolean withinBounds(){
		return yCoord > 0 && yCoord <  ceiling(); 
	}
	
	public void checkBounds(){
		if(withinBounds())
			yCoord += yA;
		else if(yCoord == ceiling())
			yCoord++;
		else
			yCoord--;
	}
	
	public void update(){
		checkBounds();
	}
}
