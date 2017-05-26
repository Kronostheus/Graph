
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Graph{
	private int size = 0;
	private boolean directed;
	
	private List<Node> graph;
	private List<Link> links;
	

	public Graph(int size, boolean directed){
		this.size = size;
		this.directed = directed;
		graph = new ArrayList<Node>(size);
		links = new ArrayList<Link>();
	}
	
	public Graph(){
		graph = new ArrayList<Node>();
		links = new ArrayList<Link>();
	}
	
	public void addNode(Node node){
		if((node.isDirected() && this.directed) || (!node.isDirected() && !this.directed)){
			graph.add(node);
			links.addAll(node.getLinks());
		}	
		else{
			if(node.isDirected())
				System.out.println("Tried to add directed node to non-directed graph");
			else if(this.directed)
				System.out.println("Tried to add non-directed node to directed graph");
		}	
	}
	
	public int getSize(){
		return size;
	}
	
	public void orgPathCost(){
		for(Node node: graph){
			for(Link link: node.getLinks()){
				links.add(link);
			}
		}
		links.sort(Comparator.comparing(Link::getPathCost));
	}
	
	public void printGraph(){
		BufferedWriter bw = null;
		FileWriter fw = null;
		
		try{
			fw = new FileWriter("results.txt");
			bw = new BufferedWriter(fw);
			for(Link link: links){
				String start = link.getStart().getName();
				String end = link.getEnd().getName();
				bw.write(start + " " + link.getPathCost() + " " + end + "\n");
			}
		} catch (IOException e){
			e.printStackTrace();
		} finally{
			try{
				if(bw != null)
					bw.close();
				
				if(fw != null)
					fw.close();
			} catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
}
