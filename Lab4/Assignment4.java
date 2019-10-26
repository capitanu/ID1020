/*
    Calin Capitanu
    7 October 2019
    Input: the database.txt file (nr of vertices and then all edges)
    Output: All the edges that make up the Minimum Spanning Tree plus their weight and the total weight of the tree

    Data structures are similar to ones in previous labs, so there is no need to comment those
    Some classes and parts of the code have been inspired by the code in the book.


 */



import java.util.*;

class Assignment4{

    public static class MinPQ<Val extends Comparable<Val>>{

	public Node head = null;

	private class Node{
	    public Val edge;
	    public Node next,prev;
	    public Node(Val e){
		this.edge = e;
		this.next = null;
	    }
	    
	}

	public boolean isEmpty(){
	    return head == null;
	}
	public Val dequeue(){
	    Val e = head.edge;
	    head = head.next;
	    return e;
	    
	}
	public void insertSorted(Val e){
	    Node n = new Node(e);
	    if(head == null){
		head = n;
	    }
	    Node temp = head;
	    while(temp.edge.compareTo(e) < 0  && temp.next != null){
		temp = temp.next;
	    }

	    if(temp.next == null){
		temp.next = new Node(e);
		return;
	    }
	    else{
		n.next = temp.next;
		temp.next = n;
		return;
	    }
	}

    }

    
    public static class Queue<Value> implements Iterable<Value>{
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
	    Value t = head.value;
	    head = head.next;
	    return t;
	}

	public boolean isConnected(Value val){
	    Node n = head;
	    while(n != null){
		if(n.value == val)
		    return true;
		n = n.next;
	    }
	    return false;
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
    




    public static class Edge implements Comparable<Edge>{

	public int v;
	public int w;
	public double weight;
	
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

	public int other(int other){
	    if(other == v)
		return w;
	    if(other == w)
		return v;
	    throw new RuntimeException("Inconsistent edge");
	}

	public int compareTo(Edge that){
	    if(this.weight < that.weight)
		return -1;
	    else if(this.weight > that.weight)
		return 1;
	    else return 0;
	}

	public String toString(){
	    return String.format("%d-%d %.2f", v, w, weight);
	}
	

    }

    public static class EdgeWeightedGraph{
	private int V;
	private int E;
	private Queue<Edge>[] adj;

	public EdgeWeightedGraph(int V){
	    this.V = V;
	    this.E = 0;
	    adj = (Queue<Edge>[]) new Queue[V];
	    for(int i = 0; i < V; i++)
		adj[i] = new Queue<Edge>();
	}

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

	public void addEdge(int v, int w, double weight){
	    addEdge(new Edge(v,w,weight));
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

	public Iterable<Edge> edges(){
	    Queue<Edge> temp = new Queue<Edge>();
	    for(int i = 0; i < V; i++)
		for(Edge e : adj[i])
		    if(e.other(i) > i)
			temp.add(e);
	    return temp;
	}

    }

    //This is the only new class here.
    //Marked will hold an array of boolean values which can tell whether or not a vertex has been visited.
    //The queue is going to actually be the final tree
    //Priority queue helps choosing the paths with least weight.
    public static class MST{

	private boolean[] marked;
	private Queue<Edge> mst;
	private MinPQ<Edge> pq;

	//The constructor here actually does most of the job:
	//It loads edges in the priirty queue and check if they have been visited or not
	//If not, it will do visit those.
	public MST(EdgeWeightedGraph G){
	    pq = new MinPQ<Edge>();
	    marked = new boolean[G.vertexCount()];
	    mst = new Queue<Edge>();

	    visit(G, 0);
	    while(!pq.isEmpty()){
		Edge e = pq.dequeue();
		int v = e.either();
		int w = e.other(v);
		if(marked[v] && marked[w])
		    continue;
		mst.add(e);
		if(!marked[v])
		    visit(G,v);
		if(!marked[w])
		    visit(G,w);
	    }
	}
	//Sorts the edges in the priority queue and marks them when visited.
	private void visit(EdgeWeightedGraph G, int v){
	    marked[v] = true;
	    for(Edge e : G.adj(v))
		if(!marked[e.other(v)])
		    pq.insertSorted(e);
	}
	
	public Iterable<Edge> edge(){
	    return mst;
	}

	//Add up all the weights.
	public double weight(){
	    double sum = 0;
	    for(Edge e : this.edge())
		sum += e.weight();
	    return sum;
	    
	}

    }
    

    //Testing method:
    public static void main(String[] args){
	//Initializing the Graph from the database.txt file
	Scanner in = new Scanner(System.in);
	int size = in.nextInt();
	EdgeWeightedGraph G = new EdgeWeightedGraph(size);
	int index = 0;
	Random rand = new Random();
	BinarySearchST<String,Integer> st = new BinarySearchST<String, Integer>(50);
	System.out.println();
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

	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println("The minimum spanning tree's edges are : ");
	//When creating the MST, it will automatically call the visit function and it will create itself so we can then just iterate and print it.
	MST mst = new MST(G);
	for(Edge e : mst.edge())
	    System.out.println( st.getKey(e.v) + "-" + st.getKey(e.w)+ "   "+ e.weight);
	System.out.println("Weight of the tree: " + mst.weight());

    }
}
