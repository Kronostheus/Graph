import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Game extends JPanel{
	 private final static int target = 2048;
	 
	 private int highest;
	 private int score;
	 
	 private boolean checking;
	 private boolean moved;
	 
	 private Tile[][] tiles;
	 private int side = 4;
	 private int total = 16;
	 
	 private Random randNum;
 
	 
	 enum gameState{
		 start, won, over, running
	 }
	 
	 final Color[] colorTable = {
		        new Color(0x701710), new Color(0xFFE4C3), new Color(0xfff4d3),
		        new Color(0xffdac3), new Color(0xe7b08e), new Color(0xe7bf8e),
		        new Color(0xffc4c3), new Color(0xE7948e), new Color(0xbe7e56),
		        new Color(0xbe5e56), new Color(0x9c3931), new Color(0x701710)
		        };
	 
	 private Color gridColor = new Color(0xBBADA0);
	 private Color emptyColor = new Color(0xCDC1B4);
	 private Color startColor = new Color(0xFFEBCD);
	 
	 private static gameState state = gameState.start;
	 
	 
	 public Game(){
		 		 
		 setPreferredSize(new Dimension(900, 700));
		 setBackground(new Color(0xFAF8EF));
		 setFont(new Font("SansSerif", Font.BOLD, 48));
		 setFocusable(true);
		 
		 addMouseListener(new MouseAdapter() {
	            @Override
	            public void mousePressed(MouseEvent e) {
	                start();
	                repaint();
	            }
	        });
	 
	        addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyPressed(KeyEvent e) {
	                switch (e.getKeyCode()) {
	                    case KeyEvent.VK_UP:
	                        moveUp();
	                        break;
	                    case KeyEvent.VK_DOWN:
	                        moveDown();
	                        break;
	                    case KeyEvent.VK_LEFT:
	                        moveLeft();
	                        break;
	                    case KeyEvent.VK_RIGHT:
	                        moveRight();
	                        break;
	                }
	                repaint();
	            }
	        });
	 }
	 
	 public void start(){
		 if(state != gameState.running){
			 state = gameState.running;
			 tiles = new Tile[side][side];
			 fillBoard();
			 score = 0;
			 highest = 0;
			 randomTile();
		     randomTile();
			 drawBoard();
		 }
	 }
	 
	 @Override
	 public void paintComponent(Graphics gg) {
	        super.paintComponent(gg);
	        Graphics2D g = (Graphics2D) gg;
	        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);
	 
	        drawGrid(g);
	    }
	 
	 public void drawGrid(Graphics2D g) {
	        g.setColor(gridColor);
	        g.fillRoundRect(200, 100, 499, 499, 15, 15);
	 
	        if (state == gameState.running) {
	 
	            for (int r = 0; r < side; r++) {
	                for (int c = 0; c < side; c++) {
	                    if (tiles[r][c].getValue() == 0) {
	                        g.setColor(emptyColor);
	                        g.fillRoundRect(215 + c * 121, 115 + r * 121, 106, 106, 7, 7);
	                    } else {
	                        drawTile(g, r, c);
	                    }
	                }
	            }
	        } else {
	            g.setColor(startColor);
	            g.fillRoundRect(215, 115, 469, 469, 7, 7);
	 
	            g.setColor(gridColor.darker());
	            g.setFont(new Font("SansSerif", Font.BOLD, 128));
	            g.drawString("2048", 310, 270);
	 
	            g.setFont(new Font("SansSerif", Font.BOLD, 20));
	 
	            if (state == gameState.won) {
	                g.drawString("you made it!", 390, 350);
	 
	            } else if (state == gameState.over)
	                g.drawString("game over", 400, 350);
	 
	            g.setColor(gridColor);
	            g.drawString("click to start a new game", 330, 470);
	            g.drawString("(use arrow keys to move tiles)", 310, 530);
	        }
	    }
	 
	 public void drawTile(Graphics2D g, int row, int col){
		 int val = tiles[row][col].getValue();
		 
		 g.setColor(colorTable[(int)(Math.log(val) / Math.log(2)) + 1]);
		 
		 g.fillRoundRect(215 + col * 121, 115 + row * 121, 106, 106, 7, 7);
		 
		 g.setColor(val < 128 ? colorTable[0] : colorTable[1]);
		 
		 FontMetrics fm = g.getFontMetrics();
	     int asc = fm.getAscent();
	     int dec = fm.getDescent();
	     
	     String s = String.valueOf(val);
	     int x = 215 + col * 121 + (106 - fm.stringWidth(s)) / 2;
	     int y = 115 + row * 121 + (asc + (106 - (asc + dec)) / 2);
	 
	     g.drawString(s, x, y);
	 }
	 
	 private void fillBoard(){
		 for(int x = 0; x < side; x++){
			 for(int y = 0; y < side; y++){
				 if(tiles[x][y] == null)
					 tiles[x][y] = new Tile(0);
			 }
		 }
	 }
	 
	 public void randomTile(){
		 randNum = new Random();
		 int pos = (randNum.nextInt(total) + 1) % total; // Random position 0-15

		 int row = pos / side;
		 int col = pos % side;
		 
		 //Checks if position is empty
		 if(tiles[row][col].getValue() == 0){
			 int val = randNum.nextInt(5) == 0 ? 4 : 2;	// Give 1/5 chance of a 4 appearing instead of 2
			 tiles[row][col].setValue(val);
		 } else
			 randomTile();
	 }
	 
	 private void moveTiles(int rInc, int cInc){
		 Tile[][] prevBoard = tiles;
		 moved = false;
		 
		 for(int r = 0; r < side; r++){
			 for(int c = 0; c < side; c++){
				 Tile curr = tiles[r][c];
				 
				 if(curr.getValue() == 0)
					 continue;
				 
				 lookAhead(r, c, rInc, cInc);
				 
			 }
		 }	
		 
		 
		 
		 if(compareBoards(prevBoard)){
			 checkState();	
			 System.out.println("---------");
			 drawBoard();
		 }
	 }
	 
	 
	 
	 private void lookAhead(int row, int col, int rInc, int cInc){
		 int nR = row + rInc;
		 int nC = col + cInc;
		 
		 if(withinRange(nR,nC)){
			 Tile next = tiles[nR][nC];
			 Tile curr = tiles[row][col];
			 if(next.getValue() != 0){
				 if(checking)
					 return;
				 if(next.getValue() == tiles[row][col].getValue() && next.getMerge()){
					 next.merge(curr);
					 if(next.getValue() > highest)
						 highest = next.getValue();
					 score += next.getValue();
					 moved = true;
				 }
			 }
			 else{
				 if(checking)
					 return;
				 
				 tiles[nR][nC].setValue(tiles[row][col].getValue());
				 tiles[row][col].setValue(0);
				 
				 if(withinRange(row-rInc, col-cInc)){
					 rememberMe(row, col, rInc, cInc);
				 }
				 
				 moved= true;
				 
			 }
			 lookAhead(nR, nC, rInc, cInc);
		 } 
	 }
	 
	 
	 private void rememberMe(int row, int col, int rInc, int cInc){
		 
		 while (withinRange(row, col)){
			 if(withinRange(row-rInc, col-cInc))
				 break;
	
			 if(tiles[row-rInc][col-cInc].getValue() != 0){
				 
				 tiles[row][col].setValue(tiles[row-rInc][col-cInc].getValue());
				 tiles[row-rInc][col-cInc].setValue(0);
				 row -= rInc;
				 col -= cInc;
			 }else
				 break;
		 }
		 
	 }
	 
	 
	 
	 private boolean compareBoards(Tile[][] board){
		 for(int x = 0; x < side; x++){
			 for(int y = 0; y < side; y++){
				 if(board[x][y] != tiles[x][y])
					 return false;
			 }
		 }
		 return true;
	 }
	 
	 private void checkState(){
		 if(score == target)
			 state = gameState.won;
		 else{
			 clearMerges();
			 if(moved)
				 randomTile();
		 }
	 }
	 
	 private void clearMerges(){
		 for(int x = 0; x < side; x++){
			 for(int y = 0; y < side; y++){
				tiles[x][y].setMerged(true);
			 }
		 }
	 }
	 
	 private boolean withinRange(int row, int col){
		 return row >= 0 && row < side && col >= 0 && col < side;
	 }
	 
	 private void moveUp(){ 
		 moveTiles(-1, 0);
	 }
	 private void moveDown(){
		 moveTiles(1, 0);
	 }
	 private void moveLeft(){
		 moveTiles(0, -1);
	 }
	 private void moveRight(){
		 moveTiles(0, 1);
	 }
	 
	 public void play(int move){
		 switch(move){
		 case 8:
			 moveUp();
			 break;
		 case 2:
			 moveDown();
			 break;
		 case 4:
			 moveLeft();
			 break;
		 case 6:
			 moveRight();
			 break;
		 }
	 }
	 
	 public void drawBoard(){
		 for(int x = 0; x < side; x++){
			 for(int y = 0; y < side; y++){
					 System.out.print(tiles[x][y].getValue() + " ");
			 }
			 System.out.println();
		 }
	 }
	 
	 public static void main(String[] args) {
		 	SwingUtilities.invokeLater(() -> {
	            JFrame f = new JFrame();
	            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            f.setTitle("2048");
	            f.setResizable(true);
	            f.add(new Game(), BorderLayout.CENTER);
	            f.pack();
	            f.setLocationRelativeTo(null);
	            f.setVisible(true);
	        });
	 }
}
