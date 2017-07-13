
public class Tile {
	private int value;
	private boolean canMerge = true;
	
	public Tile(int tileValue){
		value = tileValue;
	}
	
	public int getValue(){
		return value;
	}
	
	//TODO Implementation up for debate
	public void setMerged(){
		canMerge = false;
	}
	
	public boolean getMerge(){
		return canMerge;
	}
	
	public boolean mergeQuery(Tile tile){
		/* A Tile is considered mergeable if:
		 * 		-> it is allowed to merge
		 * 		-> the tile it's trying to merge is allowed to do so
		 * 		-> both tile's values are the same
		 * 		-> tiles aren't null
		 * */
		return canMerge && tile.getMerge() && value == tile.getValue() && tile != null;
	}
	
	public void merge(Tile tile){
		if(mergeQuery(tile)){
			value *= 2;
			setMerged();
		}
	}
}
