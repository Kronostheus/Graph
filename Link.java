
public class Link {
	private Node a;
	private Node b;
	private int pathCost;
	private boolean foundContain = false;
	private boolean parsed = false;
	
	public Link(Node a, Node b, int cost){
		this.a = a;
		this.b = b;
		pathCost = cost;
	}
	
	public void setConnectionFoun(){ foundContain = true; }
	public void setParsed(){ parsed = true; } 
	
	public Node getStart(){	return a; }
	public Node getEnd(){ return b; }
	public int getPathCost(){ return pathCost; }
	public boolean getConnectionFound(){ return foundContain; }
	public boolean getParsed(){ return parsed; }
}
