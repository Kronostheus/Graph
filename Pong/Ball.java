import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JOptionPane;

public class Ball {
	
	private final static int ballWidth = 25;
	private final static int ballHeight = 25;
		
	private int xCoord;
	private int yCoord;
	
	private int xA = 1;
	private int yA = 1;
	
	private Pong game;
	
	public Ball(Pong pong){
		game = pong;
		xCoord = game.getWidth() / 2;
		yCoord = game.getHeight() / 2;
	}
	
	private Board gamePanel(){
		return game.getPanel();
	}
	
	private Player getPlayer(Board.playerID player){
		return gamePanel().getPlayer(player);
	}
	
	private void move(){
		xCoord += xA;
		yCoord += yA;
	}
	
	private void checkUpperBottomWalls(){
		if(yCoord < 0 || yCoord > game.getHeight() - ballHeight - 29)
			yA = - yA;
	}
	
	private void scored(Board.playerID player){
		gamePanel().increaseScore(player);
		xCoord = game.getWidth() / 2;
		xA = -xA;
	}
	
	private void checkSideWalls(){
		if(xCoord < 0)
			scored(Board.playerID.ONE);
		else if(xCoord > game.getWidth() - 7)
			scored(Board.playerID.TWO);
	}
	
	private void checkWallCollisions(){
		checkUpperBottomWalls();
		checkSideWalls();
	}
	
	public void checkScores(){
		for(Board.playerID player: Board.playerID.values()){
			if(getPlayer(player).getScore() == 10){
				int confirm = JOptionPane.showConfirmDialog(null, getPlayer(player).getMsg(), "PONG", JOptionPane.PLAIN_MESSAGE);
				if(confirm == JOptionPane.OK_OPTION)		
					System.exit(0);
			}
		}
	}
	
	public void checkBallToPlayerCollision(){
		for(Board.playerID player: Board.playerID.values()){
			if(gamePanel().getPlayerBounds(player).intersects(getBounds()))
				xA = -xA;
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle(xCoord, yCoord, ballWidth, ballHeight);
	}
	
	public void paint(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(xCoord, yCoord, ballWidth, ballHeight);
	}
	
	public void update(){
		move();
		checkWallCollisions();
		checkScores();
	}
}
