
public class Application {
		
	public static void main(String[] args){
		
		Node a = new Node("a");
		Node b = new Node("b");
		Node c = new Node("c");
		Node d = new Node("d");
		Node e = new Node("e");
		Node f = new Node("f");
		
		a.connect(e, 1);
		a.connect(b, 3);
		b.connect(e, 4);
		b.connect(c, 5);
		c.connect(e, 6);
		c.connect(d, 2);
		d.connect(e, 7);
		
		Graph g = new Graph(5, false);
		
		g.addNode(a);
		g.addNode(b);
		g.addNode(c);
		g.addNode(d);
		g.addNode(e);
		
		g.printGraph();
		System.out.println(a.canReach(f));
	}
}
