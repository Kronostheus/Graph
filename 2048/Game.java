import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Game {
	 private final static int target = 2048;
	 
	 private int highest;
	 private int score;
	 
	 private Tile[][] tiles;
	 private int side = 4;
	 private int total = 16;
	 
	 private Random randNum = new Random();
	 private List<Integer> emptyPos = new ArrayList<Integer>();
	 
	 public Game(){
		 
	 }
	 
	 public void start(){
		 for(int i = 1; i <= total; i++)
			 emptyPos.add(i);
	 }
	 
	 private void randomTile(){
		 int index = randNum.nextInt(emptyPos.size()); // Random index from empty positions list
		 int pos = emptyPos.get(index);
		 
		 //TODO get row and col
		 
		 int val = randNum.nextInt(5) == 0 ? 4 : 2;
	     tiles[row][col] = new Tile(val);
	 }
	 
	 public static void main(String[] args) {
		 	Game game = new Game();
		 	game.start();
	        game.randomTile();
	    }
}
