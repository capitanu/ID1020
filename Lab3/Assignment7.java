/*
  Calin Capitanu 
  23 September 2019
  Input: A text file
  Output: The most frequenly used word and the number of times it has been used.

  This program contains two different classes, each for a different type of hashing: one for array-chain-hashing and one for hashing sequentially with two layers of Linked Lists (matrix in a way)

  I have not implemented proper iterators, but instead I have just manually iterated through every data structure myself in a way or another.
  !!! I have based most of my code on the one that is available in the book.
 */
import java.util.Scanner;

class Assignment7 {


    public static class SeparateChainingHashST<Key extends Comparable<Key>, Value extends Comparable<Value>>{ //This hash map uses an two layers of linked lists for indexing every register.
	private int N;
	private int M;
	private SequentialSearchST<Key,Value>[] st;

	
	public class SequentialSearchST<Key extends Comparable<Key>,Value>{
	    public Node head;
	    public class Node{
		Node next;
		Key key;
		Value value;
		public Node(Key key, Value val){
		    this.key = key;
		    this.value = val;
		}
	    }

	    public Value get(Key key){
		Node n = head;
		while(n != null && key.compareTo(n.key) != 0)
		    n = n.next;
		if(n == null)
		    return null;
		return n.value;
	    }

	    public void put(Key key, Value val){
		if(head == null){
		    head = new Node(key,val);
		    return;
		}
		if(head.key.equals(key)){
		    Node n = head;
		    head = new Node(key,val);
		    head.next = n.next;
		    return;
		}
		Node n = head;
		while(n != null){
		    if(n.next == null)
			n.next = new Node(key,val);
		    if(n.key.equals(key)){
			n.value = val;
			return;
		    }
		    n = n.next;
		   
		    
		}
		printElements();
	    }
	    public void printElements(){
		Node n = head;
		while(n.next != null){
		    System.out.println(n.key.toString() + n.value);
		    n = n.next;
		}

	    }

	}
	
	public SeparateChainingHashST(){
	    this(997);

	}

	public SeparateChainingHashST(int M){
	    this.M = M;
	    st = (SequentialSearchST<Key,Value>[]) new SequentialSearchST[M];
	    for(int i = 0; i < M; i++)
		st[i] = new SequentialSearchST();
	}

	private int hash(Key key){ //The hash method is gonna return a value that the hashcode maps it to from 0 to M-1
	    return (key.hashCode() & 0x7fffffff) % M;
	}

	public Value get(Key key){
	    return (Value) st[hash(key)].get(key);
	}

	public void put(Key key, Value val){
	    maxVal = val;
	    st[hash(key)].put(key,val);
	}

	private Value maxVal;
	private Key maxKey;
	public Key printMax(){
	    
	    for(int i = 0; i < M; i++){
		SequentialSearchST.Node n;
		for(n = st[i].head; n != null; n = n.next)
		    if(maxVal.compareTo((Value)n.value) < 0){
			maxVal = (Value) n.value;
			maxKey = (Key) n.key;
		    }

	    }
	    return maxKey;
	}

	


    }

    public static class LinearProbingHashST<Key,Value extends Comparable<Value>>{ //Linear Probing is just using an array and hashcodes are mapped to an index. if that index is already taken, it goes forward until it finds a proper spot.
	private int N = 0;
	private int M = 16;
	private Key[] keys;
	private Value[] values;

	public LinearProbingHashST(int n){
	    keys = (Key[]) new Object[n];
	    values = (Value[]) new Comparable[n];
	    M = n;
	}
	public LinearProbingHashST(){
	    this(16);
	}

	private int hash(Key key){ //Same hashing method that maps a key to the size of the array (from 0 to M-1)
	    return (key.hashCode() & 0x7fffffff) % M;
	}

	public void resize(int n){ // In this case, we need resizing as we would never know how many hashes we're gonna have.
	    LinearProbingHashST<Key, Value> t;
	    t = new LinearProbingHashST<Key,Value>(n);
	    for(int i = 0; i < M; i++)
		if(keys[i] != null)
		    t.put(keys[i],values[i]);
	    keys = t.keys;
	    values = t.values;
	    M = t.M;
	}
	public void put(Key key, Value val){ //Put just maps the hash to an index and then it checks if that index is free, if it is, it pushes the key, if not, it goes forward until it gets some free spot.
	    maxVal = val;
	     if(N >= M/2)
	    	resize(2*M);
	    int i;
	    for(i = hash(key)%M; keys[i] != null; i = (i + 1) % M)
		if(keys[i].equals(key)){
		    values[i] = val;
		    return;
		}
	    keys[i] = key;
	    values[i] = val;
	    N++;
	}

	public Value get(Key key){
	    for( int i = hash(key)%M; keys[i] != null; i = (i + 1) % M){
		if(keys[i].equals(key))
		    return values[i];
	    }
	    return null;
	}

	private Key maxKey;
	private Value maxVal;
	public Key getMax(){
	    for(int i = 0; i < M; i++){
		if(keys[i] == null)
		    continue;
		if(get(keys[i]).compareTo(maxVal) > 0){
		    maxVal = get(keys[i]);
		    maxKey = keys[i];
		}
		    

	    }
	    return maxKey;

	}

    }


    public static void testSeparateChainingHashST(int minlen){
	SeparateChainingHashST<String, Integer> sch = new SeparateChainingHashST<String,Integer>();
	Scanner in = new Scanner(System.in);
	while(in.hasNext()){
	    String word = in.next();
	    if(word.length() < minlen)
		continue;
	    if(sch.get(word) == null)
		sch.put(word, 1);
	    else
		sch.put(word, sch.get(word) + 1);
	}
	System.out.println("Word with moth frequency is: " + sch.printMax() + " - "+ sch.get(sch.printMax()));
    }

    public static void testLinearProbingHashST(int minlen){
	LinearProbingHashST<String,Integer> lph = new LinearProbingHashST<String,Integer>();
	Scanner in = new Scanner(System.in);
	while(in.hasNext()){
	    String word = in.next();
	    if(word.length() < minlen)
		continue;
	    if(lph.get(word) == null)
		lph.put(word, 1);
	    else
		lph.put(word, lph.get(word) + 1);
	}
	System.out.println("Word with moth frequency is: " + lph.getMax() + " - "+ lph.get(lph.getMax()));
    }




    

    public static void main(String[] args){
	//testSeparateChainingHashST(Integer.parseInt(args[0])); //time = 0.282
	testLinearProbingHashST(Integer.parseInt(args[0])); // time = 0.254
    }

}

