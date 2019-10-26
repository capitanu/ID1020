/*
    Calin Capitanu
    7 October 2019
    Input: the database.txt file (nr of vertices and then all edges)
    Output: true or false if it has a cycle or not plus the iteration of the cycle it found.

    Data structures are similar to ones in previous labs, so there is no need to comment those
    Some classes and parts of the code have been inspired by the code in the book.

    This code searches through a digraph for a cycle using most of the classes before.
*/




import java.util.Scanner;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class Assignment6{
    
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

	public int remove(){
	    int t = head.value;
	    head = head.next;
	    return t;
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

    public static class DirectedDFS{
	private boolean[] marked;

	public DirectedDFS(Digraph G, int s){
	    marked = new boolean[G.vertexCount()];
	    dfs(G, s);
	}

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

    //The only new thing here is the DirectedCycle class which basically marks all vertices that are reached from the selected vertex(takes all of them one-by-one) and sees if it can get back to itself using DirectedDFS from the assignment before.
    public static class DirectedCycle{
	private boolean[] marked;
	private int[] edgeTo;
	private LinkedList cycle;
	private boolean[] onStack;

	public DirectedCycle(Digraph G){
	    onStack = new boolean[G.vertexCount()];
	    edgeTo = new int[G.vertexCount()];
	    marked = new boolean[G.vertexCount()];
	    for(int i = 0; i < G.vertexCount(); i++)
		if(!marked[i])
		    dfs(G, i);
	}

	private void dfs(Digraph G, int v){
	    onStack[v] = true;
	    marked[v] = true;
	    for(int w : G.adj(v))
		if(this.hasCycle())
		    return;
		else if(!marked[w]){
		    edgeTo[w] = v;
		    dfs(G, w);
		}
		else if(onStack[w]){
		    cycle = new LinkedList();
		    for(int x = v; x != w; x = edgeTo[x])
			cycle.add(x);
		    cycle.add(w);
		    cycle.add(v);
		}
	    onStack[v] = false;
	}

	public boolean hasCycle(){
	    return cycle != null;
	}

	public Iterable<Integer> cycle(){
	    return cycle;
	}

    }
    
    //Testing method:
    public static void main(String[] args){
	//Initializing the digraph.
	Scanner in = new Scanner(System.in);
	int size = in.nextInt();
	Digraph G = new Digraph(size);
	int index = 0;
	BinarySearchST<String,Integer> st = new BinarySearchST<String,Integer>(50);
	while(in.hasNext()){
	    String point1 = in.next();
	    if(!st.contains(point1))
		st.put(point1, index++);
	    String point2 = in.next();
	    if(!st.contains(point2))
		st.put(point2, index++);
	    G.addEdge(st.get(point1), st.get(point2));
	}


	//Initializing the cycle class and it already does check for cycles and saves into arrays
	DirectedCycle dc = new DirectedCycle(G);
	//Prints if it does have a cycle
	System.out.println(dc.hasCycle());
	//If it does, then iterator implemented will help iterate through the cycle.
	if(dc.hasCycle())
	    for(int i : dc.cycle())
		System.out.print(st.getKey(i) + ", ");
	System.out.println();

    }
}
