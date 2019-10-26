


import java.util.Scanner;

class Assignment4 {

    private static final boolean BLACK = true;
    private static final boolean RED = false;
    
    public static class RedBlackTree<Key extends Comparable<Key>, Value>{

	Node root, parent,grand,uncle,current;
	private class Node{
	    boolean colour;
	    Node right,left;
	    Key key;
	    Value value;

	    public Node(Key key, Value value){
		this.key = key;
		this.value = value;
		this.colour = BLACK;
		left = null;
		right = null;
	    }

	}

	private int compare(Key key, Node n){
	    if( n == root)
		return 1;
	    if( n == null)
		return 0;
	    return key.compareTo(n.key);
	}
	
	public void put(Key key, Value value){
	    if( root == null ){
		root = new Node(key, value);
		return;
	    }
	    current = root;
	    parent = root;
	    grand = root;
	    Node n = new Node(key, value);
	    while(compare(key, current) != 0){
		uncle = grand;
		grand = parent;
		parent = current;
		if(key.compareTo(current.key) < 0)
		    current = current.left;
		else
		    current = current.right;

		/*	if(current.left.colour == RED && current.right.colour == RED)
			reorient(key);*/
		    }

	    current = new Node(key, value);
	    if(compare(key, parent) < 0)
		parent.left = current;
	    else
		parent.right = current;
	    reorient(key);
	}

	
	public void reorient ( Key key){
	    current.colour = RED;
	    current.left.colour = BLACK;
	    current.right.colour = BLACK;

	    if(parent.colour == RED){
		grand.colour = RED;
		if((key.compareTo(grand.key) < 0) != (key.compareTo(parent.key) < 0))
		    parent = rotate(key, grand);
		current = rotate(key,uncle);
		current.colour = BLACK;
	    }
	    root.colour = BLACK;   
	}

	private Node rotate(Key key, Node parent){
	    if(key.compareTo(parent.key) < 0)
		if(key.compareTo(parent.left.key) < 0)
		    return rotateLeft(parent.left);
		else
		    return rotateRight(parent.right);
	    else
		if(key.compareTo(parent.right.key) < 0)
		    return rotateLeft(parent.right);
		else
		    return rotateRight(parent.left);
	}

	private Node rotateLeft(Node n){
	    Node temp = n.left;
	    n.left = temp.right;
	    temp.right = n;
	    return temp;
	}

	private Node rotateRight(Node n){
	    Node temp = n.right;
	    n.right = temp.left;
	    temp.left = n;
	    return temp;
	}

	public Value get(Key key){
	    Node n = root;
	    if(root == null)
		return null;
	    while(key.compareTo(n.key) != 0)
		{
		    if(key.compareTo(n.key) < 0)
			n = n.left;
		    else n = n.right;
		    if(n == null)
			return null;
		}
	    return n.value;


	}

    }
    
    

    public static void main(String[] args){
	RedBlackTree<String, Integer> rbt = new RedBlackTree<String, Integer>();
        Scanner in = new Scanner(System.in);
	int minlen = Integer.parseInt(args[0]);
	while(in.hasNext()){
	    String word = in.next();
	    if(word.length() < minlen)
		continue;
	    if(rbt.get(word) == null)
		rbt.put(word, 1);
	    else
		rbt.put(word, rbt.get(word) + 1);
	    System.out.println(rbt.get(word));
	}


    }

}
