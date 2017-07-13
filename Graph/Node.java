import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Node{
	private String _name;
	private int _weight;
	private boolean _directed;
	
	private List<Link> connections = new ArrayList<Link>();
	
	public Node(String name, int weight, boolean directed){
		_name = name;
		_weight = weight;
		_directed = directed;
	}
	
	public Node(String name){
		_name = name;
		_weight = 0;
		_directed = false;
	}
	
	//Directed
	public void connectDir(Node node, int cost){
		Link link = new Link(this, node, cost);
		connections.add(link);
	}
	
	//Non-Directed
	public void connect(Node node, int cost){
		Link link = new Link(this, node, cost);
		connections.add(link);
		node.connections.add(link);
	}
	
	public boolean connectsTo(Node node){
		boolean flag = false;
		for(Link link: connections){
			if(!link.getConnectionFound()){
				if(link.getEnd().equals(node))
					flag = true;
					link.setConnectionFoun();
			}
		}
		return flag;
	}
	
	public boolean canReach(Node end){
		if(this.connectsTo(end)){
			return true;
		} else{
			for(Link link: connections){
				if(link.getStart().connectsTo(end))
					return true;
			}
		}
		return false;
	}
	
	public void orgPathCost(){
		connections.sort(Comparator.comparing(Link::getPathCost));
	}
	
	public String getName(){ return _name; }
	public int getWeight(){	return _weight;	}
	public boolean isDirected(){ return _directed; }
	public List<Link> getLinks(){ return connections; }
}