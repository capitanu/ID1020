/*
  Calin Capitanu 
  23 September 2019
  Input: A text file
  Output: The most frequently used word.

  This is a mod for the basic Binary Tree. The Red-Black Tree helps the tree be more balanced after each input by using this special colours for each node and the rotate methods (will explain later);

  !!!I did use the code from the book as a base for my code.

 */


import java.util.Scanner;

class Assignment4v2{

    public static class BinaryST<Key extends Comparable<Key>, Value extends Comparable<Value>>{
	public Node root;

	public final boolean BLACK = true; //Generic for easier use later
	public final boolean RED = false;
	
	
	private class Node{
	    private Key key;
	    private Value value;
	    private Node left, right;
	    private int N;
	    private boolean colour; //This has been added as a requirement for the Red-Black Tree
	    public Node(Key key, Value value, int N, boolean colour){
		this.key = key;
		this.value = value;
		this.N = N;
		this.colour = colour;

	    }
	}

	public Node rotateLeft(Node n){ //Symmetric with rotateRight, just swap the words around.
	    Node temp = n.right;
	    n.right = temp.left;
	    temp.left = n;
	    temp.colour = n.colour;
	    n.colour = RED;
	    temp.N = n.N;
	    n.N = 1 + size(n.left) + size(n.right);
	    return temp;
	}

	public Node rotateRight(Node n){ //This makes the left children of the node n become its parent. n will now be the right children and the left sub-tree of n will move to the right of the left children of n
	    Node temp = n.left;
	    n.left = temp.right;
	    temp.right = n;
	    temp.colour = n.colour;
	    n.colour = RED;
	    temp.N = n.N;
	    n.N = 1 + size(n.left) + size(n.right);
	    return temp;
	}

	public void flipColour(Node n){ //This new function is used after inserting a node or rotating another node if needed in order to maintain the balance of Red-Black-Red.... and so on with the constrain that root has to be Black and all the follow-up children that have not been created have a Black connection.
	    n.colour = RED;
	    n.left.colour = BLACK;
	    n.right.colour = BLACK;

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
	    root.colour = BLACK;
	}

	public boolean isRed(Node n){
	    if(n == null) return false;
	    return n.colour == RED;
	}

	private Node put(Node x, Key key, Value val){
	    if(x == null)
		return new Node(key, val, 1, RED);
	    int cmp = key.compareTo(x.key);
	    if(cmp < 0)
		x.left = put(x.left, key, val);
	    else if(cmp > 0)
		x.right = put(x.right, key,val);
	    else x.value = val;

	    if(isRed(x.right) && !isRed(x.left)) //Here we check all three of the special cases in which we would need to do some specific operations on the Tree (rotating or fliping colouts)
		x = rotateLeft(x);
	    if(isRed(x.left) && isRed(x.left.left))
		x = rotateRight(x);
	    if(isRed(x.left) && isRed(x.right))
		flipColour(x);
  
	    x.N = size(x.left) + size(x.right) + 1;
	    return x;
	}

	public Value get(Key key){
	    return get(root, key);
	}

	private Value get(Node x, Key key){
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
	
	public void max(Node head){
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

    
    public static void testBST(int minlen){ //Basically the same test function
	BinaryST<String, Integer> st = new BinaryST<String, Integer>();
	Scanner in = new Scanner(System.in);
	while(in.hasNext()){
	    String word = in.next();
	    if(word.length() < minlen)
		continue;
	    if(st.get(word) == null)
		st.put(word, 1);
	    else
		st.put(word, st.get(word) + 1);
	}

	long startTime = System.nanoTime();
	st.max(st.root);
	long endTime = System.nanoTime();



	System.out.println("Word with most frequency: " + st.maxKey + " - " + st.get(st.maxKey));
			   
	System.out.println("Time to run: " + (double) (endTime - startTime) / 1000000.0 + " ms");	

    }

    public static void main(String[] args)
    {
	testBST(Integer.parseInt(args[0]));
    }
   

   

}
