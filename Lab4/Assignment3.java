/*
    Calin Capitanu
    7 October 2019
    Input: the database.txt file (nr of vertices and then all edges)
    Output: Shortest path total weight and the verties traversed.
    Data structures are similar to ones in previous labs, so there is no need to comment those
    Some classes and parts of the code have been inspired by the code in the book.
    
    This code finds the shortest path (if it exists) between two vertices in a graph then prints out the weight of the path and also all the edges that are taken (and their weight) on the way.

 */


import java.util.*;

class Assignment3{

    public static class MinPQ<Key, Value extends Comparable<Value>>{

	public Node head = null;

	private class Node{
	    public Value value;
	    public Key key;
	    public Node next,prev;
	    public Node(Key key, Value value){
		this.key = key;
		this.value = value;
		this.next = null;
	    }
	    
	}

	public void enqueue(Key key, Value value){
	    Node n = new Node(key, value);
	    n.next = head;
	    if(head != null){
		head.prev = n;
	    }
	    head = n;
	}

	public void change(Key key, Value value){
	    Node n = head;
	    while(n.key != key){
		n = n.next;
	    }
	    n.value = value;

	}
	
	public boolean contains(Key key){
	    if(head == null)
		return false;
	    Node n = head;
	    while(n != null){
		if(n.key == key)
		    return true;
		n = n.next;
	    }
	    return false;
	}

	public boolean isEmpty(){
	    return head == null;
	}
	public Key dequeue(){
	    Key temp = head.key;
	    head = head.next;
	    return temp;
	    
	}
	public void insertSorted(Key key, Value value){
	    Node n = new Node(key, value);
	    if(head == null){
		head = n;
	    }
	    Node temp = head;
	    while(value.compareTo(temp.value) < 0 && temp.next != null){
		temp = temp.next;
	    }

	    if(temp.next == null){
		temp.next = new Node(key,value);
		return;
	    }
	    else{
		n.next = temp.next;
		temp.next = n;
		return;
	    }
	}

    }
    
    public static class Bag<Value> implements Iterable<Value>{
	public Node head;
	public class Node{
	    Node next;
	    Value value;
	    public Node(Value n){
		this.value = n;
		this.next = null;
	    }
	}

	public void add(Value val){
	    if(head == null){
		head = new Node(val);
		return;
	    }

	    Node n = new Node(val);
	    Node oldfirst = head;
	    n.next = oldfirst;
	    head = n;
	}

	public Value remove(){
	    Value v = head.value;
	    head = head.next;
	    return v;
	}

	public Iterator<Value> iterator(){
	    return new Iterator<Value>(){
		Node current = head;

		public boolean hasNext(){
		    return current != null;
		}

		public Value next(){
		    Node cod = current;
		    current = current.next;
		    return cod.value;
		}
	    };

	}

    }
    


    //An edge now is a class of its own and is used for creating the graph

    public static class Edge implements Comparable<Edge>{

	private int v;
	private int w;
	private double weight; //edges now have weight (could be considered as distance)
	
	public Edge(int v, int w, double weight){
	    this.v = v;
	    this.w = w;
	    this.weight = weight;
	}

	public double weight(){
	    return weight;
	}

	public int either(){
	    return v;
	}
	//either end of the edge (vertex) - undirected graph
	public int other(int other){
	    if(other == v)
		return w;
	    if(other == w)
		return v;
	    throw new RuntimeException("Inconsistent edge");
	}
	//comparable in terms of weights
	public int compareTo(Edge that){
	    if(this.weight < that.weight)
		return -1;
	    else if(this.weight > that.weight)
		return 1;
	    else return 0;
	}
	// not useful because of String - Integer conversion.
	public String toString(){
	    return String.format("%d-%d %.2f", v, w, weight);
	}
	

    }
    //Edge weighted Graph is still unidrected but every edge has a weight (unique number - ex: distance)
    public static class EdgeWeightedGraph{
	private int V;
	private int E;
	private Bag<Edge>[] adj; //adj list.

	public EdgeWeightedGraph(int V){
	    this.V = V;
	    this.E = 0;
	    adj = (Bag<Edge>[]) new Bag[V];
	    for(int i = 0; i < V; i++)
		adj[i] = new Bag<Edge>();
	}
	//initializing them is pretty similar
	public EdgeWeightedGraph(Scanner in){
	    this(in.nextInt());
	    int E = in.nextInt();
	    for( int i = 0; i < E; i++){
		int v = in.nextInt();
		int w = in.nextInt();
		double weight = in.nextDouble();
		Edge e = new Edge(v,w,weight);
		this.addEdge(e);
	    }
	}
	//adding could be used either like this or directly with an object of type edge. new parameter: weight
	public void addEdge(int v, int w, double weight){
	    Edge e = new Edge(v,w,weight);
	    addEdge(e);
	}
	
	public void addEdge(Edge e){
	    int v = e.either();
	    int w = e.other(v);
	    adj[v].add(e);
	    adj[w].add(e);
	    E++;
	}

	public int vertexCount(){
	    return V;
	}

	public int edgeCount(){
	    return E;
	}

	public Iterable<Edge> adj(int v){
	    return adj[v];
	}

	//Iterating is really important for printing
	public Iterable<Edge> edges(){
	    Bag<Edge> temp = new Bag<Edge>();
	    for(int i = 0; i < V; i++)
		for(Edge e : adj[i])
		    if(e.other(i) > i)
			temp.add(e);
	    return temp;
	}

    }
    //This is Dijsktra's Shortest Path algorithm, translated from Digraph to Graph.
    //Holds arrays for weights between to vertices
    //Holds arrays for paths to vertices
    //Uses priority queue to keep track of which path is better.
    public static class ShortestPath{
	private Edge[] edgeTo;
	private double[] distTo;
	private MinPQ<Integer, Double> pq;
	private int s;

	public ShortestPath(EdgeWeightedGraph G, int s){
	    edgeTo = new Edge[G.vertexCount()];
	    distTo = new double[G.vertexCount()];
	    pq = new MinPQ<Integer,Double>();
	    this.s = s;
	    for(int i = 0; i < G.vertexCount(); i++)
		distTo[i] = Double.POSITIVE_INFINITY;
	    distTo[s] = 0.0;
	    pq.enqueue(s, 0.0);
	    //path from first to first is gonna be 0, initializing every array and queue and then relaxation is the way to fix this.
	    while(!pq.isEmpty())
		relax(G, pq.dequeue());
	    
	}

	private void relax(EdgeWeightedGraph G, int v){
	    //take each edge at once and start adding up the weights into each array and the vertices that can be reached and last use the priority queue to categorize the distances. (best path);
	    for(Edge e : G.adj(v)){
		int w = e.other(v);
		
		if(v == e.other(e.either()))
		    e = new Edge(v, e.either(), e.weight());
		
		if(distTo[w] > distTo[v] + e.weight()){
		    distTo[w] = distTo[v] + e.weight();
		    edgeTo[w] = e;
		    if(pq.contains(w))
			pq.change(w, distTo[w]);
		    else
			pq.insertSorted(w, distTo[w]);
		}
		
	    }

	    
	}


	public double distTo(int v){
	    return distTo[v];
	}

	public boolean hasPathTo(int v){
	    return distTo[v] < Double.POSITIVE_INFINITY;
	}
	//This iterator is going to iterate through all the edges used for the shortest path;
	public Iterable<Edge> pathTo(int v){
	    if(!hasPathTo(v))
		return null;
	    Bag<Edge> path = new Bag<Edge>();
	    for(Edge e = edgeTo[v]; e != null; e = edgeTo[e.either()])
		path.add(e);
	    return path;
	}

    }
    

    //Testing is done here
    public static void main(String[] args){
	//First part is basically only creating the graph
	Scanner in = new Scanner(System.in);
	int size = in.nextInt();
	EdgeWeightedGraph G = new EdgeWeightedGraph(size);
	int index = 0;
	Random rand = new Random();
	BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>(50);
	while(in.hasNext()){
	    String point1 = in.next();
	    if(!st.contains(point1))
		st.put(point1, index++);
	    String point2 = in.next();
	    if(!st.contains(point2))
		st.put(point2, index++);
	    double x = in.nextDouble();
	    G.addEdge(st.get(point1), st.get(point2),x);
	}
	int s = st.get(args[0]);

	//Here we create the object of type ShortestPath and initialize it with the vertex from where we want to go.
	ShortestPath sp = new ShortestPath(G, s);
	int i = st.get(args[1]);
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.print(st.getKey(s) + " to " + st.getKey(i));
	//After running the relaxation method in SP we know shortest distance to all the vertices so we can just read that from the array distTo[] with this method.
	System.out.printf(" (%4.2f) ", sp.distTo(i));
	System.out.println();

	//Iterating to print the shortest path's edges.
	for(Edge e : sp.pathTo(i))
	    System.out.println(st.getKey(e.v) + " - " + st.getKey(e.w) + "   " + e.weight);
	System.out.println();

	
    }
}
