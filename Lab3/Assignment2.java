/*
  Calin Capitanu 
  23 September 2019
  Input: A text file
  Output: The word that appears most frequent and the number of appearances

  This program has two integrated classes, one Search Tree that is implemented with array and one that is a Binary Tree with nodes.
  There is also some time checking for each of the two types of data structures and searches.
  Each search has its own test method, although they are pretty similar.


  !!! I did get the help of the code that was available in the book for better understanding and simpler code.
 */



import java.util.Scanner;

class Assignment2{

    public static class BinarySearchST<Key extends Comparable<Key>, Value> { // Search Tree with array
	public Key[] keys;
	private Value[] values;
	private int N;

	public BinarySearchST(int capacity){ //Constructor
	    //@SuppressWarnings("unchecked")
	    this.keys = (Key[]) new Comparable[capacity];
	    //@SuppressWarnings("unchecked")
	    this.values = (Value[]) new Object[capacity];
	}

	public Value get(Key key){
	    if(keys == null) return null;
	    int i = rank(key); // returned key is used to check if it is the same with the one asked for and returns the value located at that key.
	    if( i < N && keys[i].compareTo(key) == 0) return values[i];
	    else return null;
	}

	
	public int rank(Key key) //Binary Searches the array for the element key.
	{
	    int lo = 0, hi = N-1;
	    while (lo <= hi)
		{
		    int mid = lo + (hi - lo) / 2;
		    int cmp = key.compareTo(keys[mid]);
		    if(cmp < 0) hi = mid - 1;
		    else if (cmp > 0) lo = mid + 1;
		    else return mid;
		}
	    return lo;
	}
	public void put(Key key, Value val) // Places a new element in the array
	{ 
	    int i = rank(key);
	    if (i < N && keys[i].compareTo(key) == 0)
		{ values[i] = val; return; }
	    for (int j = N; j > i; j--)
		{ keys[j] = keys[j-1]; values[j] = values[j-1]; }
	    keys[i] = key;
	    values[i] = val;
	    N++;
	}

        public Value returnValue(int i){
	    if(keys[i] != null)
		return get(keys[i]);
	    else return null;
	}

	public Key returnKey(int i){
	    return keys[i];
	}
	

    }

    public static class BinaryST<Key extends Comparable<Key>, Value extends Comparable<Value>>{ //This second implementation is using a proper Binary Tree with Nodes as the data structure. This method is faster for the input needed.
	public Node root;

	private class Node{ //There is need for a subclass of type Node which will hold each value and key, plus the refference to two chiildren nodes and the height until that specific point.
	    private Key key;
	    private Value value;
	    private Node left, right;
	    private int N;

	    public Node(Key key, Value value, int N){
		this.key = key;
		this.value = value;
		this.N = N;
		left = null;
		right = null;

	    }
	}

	public int size(){
	    return size(root);
	}

	private int size(Node x){
	    if(x == null)
		return 0;
	    else return x.N;
	}
	
	public void put(Key key, Value value){
	    maxVal = value;
	    root = put(root, key, value);
	}

	private Node put(Node x, Key key, Value val){ //Inserting a register here is a little different, we use the fact that every left child should have lower key value than the parent and every right child should have a greater value.
	    if(x == null)
		return new Node(key, val, 1);
	    int cmp = key.compareTo(x.key);
	    if(cmp < 0)
		x.left = put(x.left, key, val);
	    else if(cmp > 0)
		x.right = put(x.right, key,val);
	    else x.value = val;
	    x.N = size(x.left) + size(x.right) + 1;
	    return x;
	}

	public Value get(Key key){
	    return get(root, key);
	}

	private Value get(Node x, Key key){ //For getting a value out we start searching from the root down with the specifications above, comparing the current node.key with the asked key every time.
	    if(x == null)
		return null;
	    int cmp = key.compareTo(x.key);
	    if(cmp < 0)
		return get(x.left, key);
	    else if(cmp > 0)
		return get(x.right, key);
	    else return x.value;
		    

	}

	private Value maxVal;
	public Key maxKey;
	
	public void max(Node head){ // Because I did not implement an Iterator, my class is neither Iterable, I have decided to manually iterate through all of the classes in this Lab with different implementations.
	    if(head != null){
		max(head.left);
		max(head.right);
		if(head.value.compareTo(maxVal) > 0){
		    maxVal = head.value;
		    maxKey = head.key;
		    
		}
		
	    }
     	}
	
    }


    

    public static void testBinarySearchST(int minlen){ //Test function for the array data type with binary search.
	int size = 100000; //Will not use resizing for this.
        BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>(size);
	Scanner in = new Scanner(System.in);
	int counter = 0;
	while(in.hasNext()){
	    String word = in.next();
	    if(counter++ >= size && size != 0)
		break;
	    counter++;
	    if(word.length() < minlen)
		continue;
	    if(st.get(word) == null) //Key inserted first time
		st.put(word, 1);
	    else
		st.put(word, st.get(word) + 1); //Key was already found
	}

	String max = "";
	st.put(max, 0);
	for(int i = 0; i < size; i++){
	    if(st.returnValue(i) == null)
		continue;
	    if(st.returnValue(i) > st.get(max))
		max = st.returnKey(i);}
	System.out.println("Word with most frequency: " + max + " - " + st.get(max));

    }

    
    public static void testBST(int minlen, int size){
	//Mostly the same method is used for the binary tree as it was for the array-type
	BinaryST<String, Integer> st = new BinaryST<String, Integer>();
	Scanner in = new Scanner(System.in);
	int counter = 0;
	while(in.hasNext()){
	    String word = in.next();
	    if(counter >= size && size != 0)
		break;
	    counter++;
	    if(word.length() < minlen)
		continue;
	    if(st.get(word) == null)
		st.put(word, 1);
	    else
		st.put(word, st.get(word) + 1);
	}
	long startTime = System.nanoTime(); //time for only searching.
	st.max(st.root);
	long endTime = System.nanoTime();
	System.out.println("Word with most frequency: " + st.maxKey + " - " + st.get(st.maxKey));
			   
	System.out.println("Time to run: " + (double) (endTime - startTime) / 1000000.0 + "ms");		

    }

    public static void main(String[] args)
    {
	//testBinarySearchST(Integer.parseInt(args[0]),Integer.parseInt(args[1])); // time == 1.272
	testBST(Integer.parseInt(args[0]), Integer.parseInt(args[1])); // time == 0.365
    }
}
