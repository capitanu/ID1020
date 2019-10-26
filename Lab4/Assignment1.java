/*
    Calin Capitanu
    7 October 2019
    Input: the database.txt file (nr of vertices and then all edges)
    Output: true or false if they are connected plus the path. 
    Data structures are similar to ones in previous labs, so there is no need to comment those
    Some classes and parts of the code have been inspired by the code in the book.

    This code finds out if there is a path between two vertices. To do that, it uses the method depthfirstsearch.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Assignment1{

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

    //Graph class has number of fertices and edges and an array of linked lists in which it saves the adjecency relations.
    //Each vertex has a linked list and each item in the linked list means that the vertex and the one owning the list are connected.
       public static class Graph{
	public int V;
	public int E;
	public LinkedList[] ll;

	public Graph(int V){
	    this.V = V;
	    this.E = 0;
	    ll = new LinkedList[V];
	    for(int i = 0; i < V; i++){
		ll[i] = new LinkedList();
	    }
	}
	   //This helps initializing the graph from standard input.
	public Graph(Scanner in){
	    this(in.nextInt());
	    int E = in.nextInt();
	    for( int i = 0; i < E; i++){
		int v = in.nextInt();
		int w = in.nextInt();
		addEdge(v,w);
	    }
	}

	public int edgeCount(){
	    return E;
	}

	public int vertexCount(){
	    return V;
	}
	   

	public void addEdge(int v, int w){
	    ll[v].add(w);
	    ll[w].add(v);
	    E++;
	}

	public Iterable<Integer> adj(int v){
	    return ll[v];
	}

    }


    //This class basically finds if two points in a graph are connected with a path
    public static class DepthFirstSearch{
	private boolean[] marked;
	private int count;
	private int s;
	private int[] edgeTo;

	//It holds this marked boolean array where it says if the vertex which was initialized to the class and the one whose index we are checking for is connected
	public DepthFirstSearch(Graph G, int s){
	    marked = new boolean[G.vertexCount()];
	    edgeTo = new int[G.vertexCount()];
	    this.s = s;
	    dfs(G,s);
	}
	//Goes through all the vertices and first sees 1st order connection and keeps going until all the vertices have been checked.
	private void dfs(Graph G, int v){
	    marked[v] = true;
	    count++;
	    for( int w : G.adj(v) ){
		if(!marked[w]){
		    edgeTo[w] = v;
		    dfs(G,w);
		}
	    }
	}

	public boolean marked(int w){
	    return marked[w];
	}

	public int count(){
	    return count;
	}

	public boolean hasPathTo(int v){
	    return marked[v];
	}
	//Iterator for the path
	public Iterable<Integer> pathTo(int v)
	{
	    if (!hasPathTo(v))
		return null;
	    LinkedList path = new LinkedList();
	    for (int x = v; x != s; x = edgeTo[x])
		path.add(x);
	    path.add(s);
	    return path;
	}


    }

    public static void main(String[] args){
	Scanner in = new Scanner(System.in);
	int index = 0;
	int vertices = in.nextInt();
	Graph g = new Graph(vertices);
	BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>(50); //This is some sort of symbol graph that converts Strings into Integers indexes and uses them as keys in the graph and then to print back we convert back to Strings.
	while(in.hasNext()){ //Read from file
	    String point1 = in.next();
	    if(!st.contains(point1))
		st.put(point1, index++);
	    String point2 = in.next();
	    if(!st.contains(point2))
		st.put(point2, index++);
	    //Add the edge to the graph
	    g.addEdge(st.get(point1), st.get(point2)); 
	}
	String name1 = args[0]; 
	String name2 = args[1];
	int s = st.get(name1);
	int v = st.get(name2);
	DepthFirstSearch dfs = new DepthFirstSearch(g, s);
	System.out.println(dfs.marked(v));
	for(int w : dfs.pathTo(v)){
	    System.out.print(st.getKey(w) + ", ");
	}
	System.out.println();
	
	
    }

    
}

