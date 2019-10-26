/*
    Calin Capitanu
    7 October 2019
    Input: the database.txt file (nr of vertices and then all edges)
    Output: true or false if they are connected or not and the path between them
     Data structures are similar to ones in previous labs, so there is no need to comment those
    Some classes and parts of the code have been inspired by the code in the book.


 */

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Assignment2{

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

	    Node n = head;
	    while(n.next != null)
		n = n.next;
	    n.next = new Node(val);
	}

	public int dequeue(){
	    int x = head.value;
	    head = head.next;
	    return x;
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

    //The difference for this code is the way we are searching. This code checks for groups of vertices found on the same distance from the main vertex.
    //This goes into chunks, and checks multiple vertices "at the same time".
    
    public static class BreadthFirstPath{
	private boolean[] marked;
	private int[] edgeTo;
	private int s;

	public BreadthFirstPath(Graph G, int s){
	    marked = new boolean[G.vertexCount()];
	    edgeTo = new int[G.vertexCount()];
	    this.s = s;
	    bfs(G, s);
	}

	private void bfs(Graph G, int s){
	    LinkedList queue = new LinkedList();
	    marked[s] = true;
	    queue.add(s);
	    while(queue.head != null){
		int v = queue.dequeue();
		for( int w : G.adj(v))
		    if(!marked[w]){
			edgeTo[w] = v;
			marked[w] = true;
			queue.add(w);
		    }
	    }
	}

	public boolean marked(int w){
	    return marked[w];
	}

	
	public Iterable<Integer> pathTo(int v)
	{
	    if (!marked(v))
		return null;
	    LinkedList path = new LinkedList();
	    for (int x = v; x != s; x = edgeTo[x])
		path.add(x);
	    path.add(s);
	    return path;
	}

	


    }
    //Same testing method.
    public static void main(String[] args){
	Scanner in = new Scanner(System.in);
	int index = 0;
	int vertices = in.nextInt();
	Graph g = new Graph(vertices);
	BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>(50);
	while(in.hasNext()){
	    String point1 = in.next();
	    if(!st.contains(point1))
		st.put(point1, index++);
	    String point2 = in.next();
	    if(!st.contains(point2))
		st.put(point2, index++);
	    g.addEdge(st.get(point1), st.get(point2));
	}
	String name1 = args[0];
	String name2 = args[1];
	int s = st.get(name1);
	int v = st.get(name2);
	BreadthFirstPath dfs = new BreadthFirstPath(g, s);
	System.out.println(dfs.marked(v));
	for(int w : dfs.pathTo(v)){
	    System.out.print(st.getKey(w) + ", ");
	}
	System.out.println();
	
	
    }

    
}
