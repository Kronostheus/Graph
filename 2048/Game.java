import java.util.Random;
import java.util.Scanner;

public class Game {
	 private final static int target = 2048;
	 
	 private int highest;
	 private int score;
	 
	 private boolean checking= false;
	 
	 private Tile[][] tiles;
	 private int side = 4;
	 private int total = 16;
	 
	 private Random randNum = new Random();
 
	 
	 enum gameState{
		 start, won, over
	 }
	 private static gameState state = gameState.start;
	 
	 
	 public Game(){
		 
	 }
	 
	 public void start(){
		 tiles = new Tile[side][side];
		   
		 fillBoard();
		 randomTile();
	     randomTile();
		 drawBoard();
	 }
	 
	 private void fillBoard(){
		 for(int x = 0; x < side; x++){
			 for(int y = 0; y < side; y++){
				 if(tiles[x][y] == null)
					 tiles[x][y] = new Tile(0);
			 }
		 }
	 }
	 
	 private void randomTile(){
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
	 
	 private void move(int rInc, int cInc){
		 Tile[][] prevBoard = tiles;
		 for(int r = 0; r < side; r++){
			 for(int c = 0; c < side; c++){
				 Tile curr = tiles[r][c];
				 
				 if(curr.getValue() == 0)
					 continue;
				 
				 lookAhead(r, c, rInc, cInc);
				 
			 }
		 }		
		 if(!compareBoards(prevBoard) && !checking)
			 checkState();	 
	 }
	 
	 
	 
	 private void lookAhead(int row, int col, int rInc, int cInc){
		 int nR = row + rInc;
		 int nC = col + cInc;
		 
		 if(withinRange(nR,nC)){
			 Tile next = tiles[nR][nC];
			 Tile curr = tiles[row][col];
			 if(next.getValue() != 0){
				 if(next.getValue() == tiles[row][col].getValue() && next.getMerge()){
					 next.merge(curr);
					 if(next.getValue() > highest)
						 highest = next.getValue();
					 score += next.getValue();
				 }
				 lookAhead(nR, nC, rInc, cInc);
			 }
			 else{
				 tiles[nR][nC].setValue(tiles[row][col].getValue());
				 tiles[row][col].setValue(0);
				 if(withinRange(row-rInc, col-cInc)){
					 rememberMe(row, col, rInc, cInc);
				 }					 
				 lookAhead(nR, nC, rInc, cInc);
			 }
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
			 randomTile();
			 if(!canMove()){
				 state = gameState.over;
			 }
		 }
	 }
	 
	 private void clearMerges(){
		 for(int x = 0; x < side; x++){
			 for(int y = 0; y < side; y++){
				 //if(tiles[x][y] != null)
					 tiles[x][y].setMerged(true);
			 }
		 }
	 }
	 
	 private boolean canMove(){
		 checking = true;
		 boolean result;
		 Tile[][] prevBoard = tiles;
		 moveUp();
		 moveDown();
		 moveLeft();
		 moveRight();
		 result = compareBoards(prevBoard);
		 checking = false;
		 tiles = prevBoard;
		 return result;
	 }
	 
	 private boolean withinRange(int row, int col){
		 return row >= 0 && row < side && col >= 0 && col < side;
	 }
	 
	 private void moveUp(){ 
		 move(-1, 0);
	 }
	 private void moveDown(){
		 move(1, 0);
	 }
	 private void moveLeft(){
		 move(0, -1);
	 }
	 private void moveRight(){
		 move(0, 1);
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
				 //if(tiles[x][y] != null)
					 System.out.print(tiles[x][y].getValue() + " ");
				 /*else
					 System.out.print(0 + " ");*/
			 }
			 System.out.println();
		 }
	 }
	 
	 public static void main(String[] args) {
		 	Game game = new Game();
		 	game.start();
	        Scanner scan = new Scanner(System.in);
	        while(state != gameState.over || state != gameState.won){
	        	game.play(scan.nextInt());
	        	game.drawBoard();
	        	//game.checkState();
	        }
	        scan.close();
	    }
}
