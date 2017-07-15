import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener, KeyListener{
	
	private Player player1;
	private Player player2;
	private Ball ball;
	
	private Pong game;
	
	public enum playerID{
		ONE, TWO;
	}
	
	public Board(Pong game){
		setBackground(Color.BLACK);
		this.game = game;
		
		player1 = new Player(game, 1, game.getWidth() - 36, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
		player2 = new Player(game, 2, 20, KeyEvent.VK_W, KeyEvent.VK_S);
		ball = new Ball(game);
		
		Timer timer = new Timer(5, this);
		timer.start();
		
		addKeyListener(this);
		setFocusable(true);
	}
	
	public Player getPlayer(playerID player){
		if(player == playerID.ONE)
			return player1;
		else
			return player2;
	}
	
	public int getScore(playerID player){
		return getPlayer(player).getScore();
	}
	
	public void increaseScore(playerID player){
		getPlayer(player).increaseScore();
	}
	
	public Rectangle getPlayerBounds(playerID player){
		return getPlayer(player).getBounds();
	}
	
	public void update(){
		player1.update();
		player2.update();
		ball.update();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		update();
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		player1.keyDown(e.getKeyCode());
		player2.keyDown(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		player1.keyUp(e.getKeyCode());
		player2.keyUp(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawString(getScore(playerID.ONE) + " : " + getScore(playerID.TWO), game.getWidth() / 2, 10);
		ball.paint(g);
		player1.paint(g);
		player2.paint(g);
	}
}
