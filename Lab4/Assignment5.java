/*
    Calin Capitanu
    7 October 2019
    Input: the database.txt file (nr of vertices and then all edges)
    Output: true or false, depending if there is a path or not

    Data structures are similar to ones in previous labs, so there is no need to comment those
    Some classes and parts of the code have been inspired by the code in the book.
    
    This code uses Digraph which is a graph with directions on edges.

 */




import java.util.Scanner;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Assignment5{
    
    public static class LinkedList implements Iterable<Integer>{
	public Node head;
	public class Node{
	    Node next;
	    int value;
	    public Node(int n){
		this.value = n;
		this.next = null;
	    }
	}

	public void add(int val){
	    if(head == null){
		head = new Node(val);
		return;
	    }

	    Node n = new Node(val);
	    Node oldfirst = head;
	    n.next = oldfirst;
	    head = n;
	}

	public boolean isConnected(int val){
	    Node n = head;
	    while(n != null){
		if(n.value == val)
		    return true;
		n = n.next;
	    }
	    return false;
	}

	public Iterator<Integer> iterator(){
	    return new Iterator<Integer>(){
		Node current = head;

		public boolean hasNext(){
		    return current != null;
		}

		public Integer next(){
		    Node cod = current;
		    current = current.next;
		    return cod.value;
		}
	    };

	}

    }

    


    //Digraphs are pretty similar to basic Graphs, but when adding an edge to the adj array of linked list, it is only done one way (i.e. if we add V1 -> V2, we add V2 to the linked list of V1, but don't add V1 to the linked list of V2.
    public static class Digraph{
	public int V;
	public int E;
	public LinkedList[] adj;

	public Digraph(int V){
	    this.V = V;
	    this.E = 0;
	    adj = new LinkedList[V];
	    for(int i = 0; i < V; i++)
		adj[i] = new LinkedList();
	}

	public Digraph(Scanner in){
	    this(in.nextInt());
	    int E = in.nextInt();
	    for(int i = 0; i < E; i++){
		int v = in.nextInt();
		int w = in.nextInt();
		addEdge(v,w);
	    }
	}
	

	public void addEdge(int v, int w){
	    E++;
	    adj[v].add(w);
	}

	public int edgeCount(){
	    return E;
	}

	public int vertexCount(){
	    return V;
	}

	public Iterable<Integer> adj(int v){
	    return adj[v];
	    
	}
	

    }

    //This class works pretty similar to DFS of unidrected graphs.
    public static class DirectedDFS{
	private boolean[] marked;

	public DirectedDFS(Digraph G, int s){
	    marked = new boolean[G.vertexCount()];
	    dfs(G, s);
	}

	//Goes through all the vertices and marks them if they are reached.
	private void dfs(Digraph G, int v){
	    marked[v] = true;
	    for(int w : G.adj(v))
		if(!marked[w])
		    dfs(G,w);
	}

	public boolean marked(int v){
	    return marked[v];
	}
	    

    }
    
    //testing method:
    public static void main(String[] args){
	// Writing the digraph form database.txt
	Scanner in = new Scanner(System.in);
	int size = in.nextInt();
	BinarySearchST<String,Integer> st = new BinarySearchST<String, Integer>(50);
	int index = 0;
	Digraph G = new Digraph(size);
	while(in.hasNext()){
	    String point1 = in.next();
	    if(!st.contains(point1))
		st.put(point1, index++);
	    String point2 = in.next();
	    if(!st.contains(point2))
		st.put(point2, index++);
	    G.addEdge(st.get(point1), st.get(point2));
	}
	int s = st.get(args[0]);
	int t = st.get(args[1]);
	//Only need to instatiate the DirectedDFS and it will mark all the vertices that it can reach into an array of booleans
	DirectedDFS ddfs = new DirectedDFS(G,s);
	//Print that specific index for the vertex that you want to check it reaches.
	System.out.println(ddfs.marked(t));

    }
}
