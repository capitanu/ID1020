/*
    Calin Capitanu
    7 October 2019
    Input: the task7.txt file which contains edges
    Output: The vertices placed in topological order
    Data structures are similar to ones in previous labs, so there is no need to comment those
    Some classes and parts of the code have been inspired by the code in the book.


    The topological order is made such that if placed in a column, all edges point in the same direction (either up or down)

 */



import java.util.*;

public class Assignment7{
    
    public static class ST<Key extends Comparable<Key>, Value> {

	private Node root; 

	private class Node {
	    private Key key; 
	    private Value val; 
	    private Node left, right; 
	    private int N; 

	    public Node(Key key, Value val, int N) {
		this.key = key;
		this.val = val;
		this.N = N;
	    }
	}

	public int size() {
	    return size(root);
	}

	private int size(Node x) {
	    if (x == null) return 0;
	    else return x.N;
	}

	public Value get(Key key) {
	    return get(root, key);
	}

	private Value get(Node x, Key key) {
	    if (x == null) return null;
	    int cmp = key.compareTo(x.key);
	    if (cmp < 0) return get(x.left, key);
	    else if (cmp > 0) return get(x.right, key);
	    else return x.val;
	}

	public void put(Key key, Value val) {
	    root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) {
	   
	    if (x == null) return new Node(key, val, 1);
	    int cmp = key.compareTo(x.key);
	    if (cmp < 0) x.left = put(x.left, key, val);
	    else if (cmp > 0) x.right = put(x.right, key, val);
	    else x.val = val;
	    x.N = size(x.left) + size(x.right) + 1;
	    return x;
	}

	public int rank(Key key)
	{ return rank(key, root); }
	private int rank(Key key, Node x)
	{ 
	    if (x == null) return 0;
	    int cmp = key.compareTo(x.key);
	    if (cmp < 0) return rank(key, x.left);
	    else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
	    else return size(x.left);
	}

	public boolean contains(Key key) {
	    if (key == null) {
		throw new IllegalArgumentException("Argument to contains() cannot be null");
	    }
	    return get(key) != null;
	}

	public Iterable<Key> keys() {
	    return keys(min(), max());
	}

	public Iterable<Key> keys(Key lo, Key hi) {
	    Queue<Key> queue = new Queue<Key>();
	    keys(root, queue, lo, hi);
	    return queue;
	}

	private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
	    if (x == null)
		return;
	    int cmplo = lo.compareTo(x.key);
	    int cmphi = hi.compareTo(x.key);
	    if (cmplo < 0)
		keys(x.left, queue, lo, hi);
	    if (cmplo <= 0 && cmphi >= 0)
		queue.add(x.key);
	    if (cmphi > 0)
		keys(x.right, queue, lo, hi);
	}

        public Key min ()
        {
            return min(root).key;
        }
        private Node min (Node x)
        {
            if (x.left == null) return x;
            return min(x.left);
        }

        public Key max ()
        {
            return max(root).key;
        }
        private Node max (Node x)
        {
            if (x.right == null) return x;
            return max(x.right);
        }
        public Key floor (Key key)
        {
            Node x = floor(root, key);
            if (x == null) return null;
            return x.key;
        }
        private Node floor (Node x, Key key)
        {
            if (x == null) return null;
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x;
            if (cmp < 0) return floor(x.left, key);
            Node t = floor(x.right, key);
            if (t != null) return t;
            else return x;
        }
    }
    public static class Stack implements Iterable<Integer>{
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
	
	public Digraph reverse(){
	    Digraph R = new Digraph(V);
	    for (int v = 0; v < V; v++)
		for (int w : adj(v))
		    R.addEdge(w, v);
	    return R;
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

    public static class DirectedCycle{
	private boolean[] marked;
	private int[] edgeTo;
	private Stack cycle;
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
		    cycle = new Stack();
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

    //This class traverses the Digraph in different ways
    public static class DepthFirstOrder{
	private boolean[] marked;

	private Queue<Integer> pre;
	private Queue<Integer> post;
	private Stack reversePost;

	public DepthFirstOrder(Digraph G){
	    pre = new Queue<Integer>();
	    post = new Queue<Integer>();
	    reversePost = new Stack();

	    marked = new boolean[G.vertexCount()];

	    for(int i = 0; i < G.vertexCount(); i++)
		if(!marked[i])
		    dfs(G, i);
	}

	private void dfs(Digraph G, int v){
	    pre.add(v);

	    marked[v] = true;

	    for(int w : G.adj(v))
		if(!marked[w])
		    dfs(G, w);
	    post.add(v);
	    reversePost.add(v);
	}

	public Iterable<Integer> pre(){ return pre; }
	public Iterable<Integer> post(){ return post; }
	public Iterable<Integer> reversePost(){ return reversePost; }
    }

    //This class makes an iterable object that will be sorted in topological order using directed cycles.
    public static class Topological{
	private Iterable<Integer> order;

	public Topological(Digraph G){
	    DirectedCycle cyclefinder = new DirectedCycle(G);
	    if(!cyclefinder.hasCycle()){
		DepthFirstOrder dfs = new DepthFirstOrder(G);
		order = dfs.reversePost();
	    }
	}

	public Iterable<Integer> order(){
	    return order;
	}

	public boolean isDAG(){
	    return order == null;
	}

	

    }
    //This class is here to transform from indexes to strings as it was asked in the task
    public static class SymbolGraph{
	private ST<String,Integer> st;
	private String[] keys;
	private Digraph G;

	public SymbolGraph(String stream, String sp){
	    st = new ST<String, Integer>();
	    Scanner in = new Scanner(stream);
	    while(in.hasNextLine()){
		String[] a = in.nextLine().split(sp);
		for(int i = 0; i < a.length; i++)
		    if(!st.contains(a[i]))
			st.put(a[i], st.size());
	    }
	    keys = new String[st.size()];
	    for(String name : st.keys())
		keys[st.get(name)] = name;
	    G = new Digraph(st.size());
	    in = new Scanner(stream);
	    while(in.hasNextLine()){
		String[] a = in.nextLine().split(sp);
		int v = st.get(a[0]);
		for(int i = 1; i < a.length; i++)
		    G.addEdge(v, st.get(a[i]));
	    }

	}

	public boolean contiains(String s){
	    return st.contains(s);
	}

	public int index(String s){
	    return st.get(s);
	}

	public String name(int v){
	    return keys[v];
	}

	public Digraph G(){
	    return G;
	}


    }
    //Testing method:
    public static void main(String[] args){
	
	Scanner in = new Scanner(System.in);
	StringBuilder sb = new StringBuilder();
	while(in.hasNextLine()){
	    sb.append(in.nextLine());
	    sb.append('\n');
	}
	String out = sb.toString();
	
	SymbolGraph SG = new SymbolGraph(out, " ");
	Topological top = new Topological(SG.G());
	for (int v : top.order())
	    System.out.println(SG.name(v));
    }
}
