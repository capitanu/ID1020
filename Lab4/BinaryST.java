
import java.util.LinkedList;

public class BinaryST<Key extends Comparable<Key>, Value extends Comparable<Value>>{ //This second implementation is using a proper Binary Tree with Nodes as the data structure. This method is faster for the input needed.
   
    
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

    public Key min(){
	return min(root).key;
    }

    public Key max(){
	return max(root).key;
    }

    private Node min(Node x){
	if(x.left == null)
	    return x;
	return min(x.left);
    }

    private Node max(Node x){
	if(x.right == null)
	    return x;
	return min(x.right);
    }

    private int size(Node x){
	if(x == null)
	    return 0;
	else return x.N;
    }
	
    public void put(Key key, Value value){
	root = put(root, key, value);
    }

    public boolean contains(Key key){
	return get(key) != null;

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


    public Key getKey(Value value){
	for(Key g : keys())
	    if(get(g) == value)
		return g;
	return null;
    }
     
    private Value get(Node x, Key key){ //For getting a value out we start searching from the root down with the specifications above, comparing the current node.key with the asked key every time.
	if(x == null)
	    return null;
	if(key.equals(x.key))
	    return x.value;
	int cmp = key.compareTo(x.key);
	if(cmp < 0)
	    return get(x.left, key);
	else if(cmp > 0)
	    return get(x.right, key);
	else return x.value;
		    

    }

    public Iterable<Key> keys(){
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi){
	LinkedList<Key> list = new LinkedList<Key>();
	keys(root,list,lo,hi);
	return list;
    }

    private void keys(Node x, LinkedList<Key> list, Key lo, Key hi){
	if(x == null)
	    return;
	int cmplo = lo.compareTo(x.key);
	int cmphi = hi.compareTo(x.key);

	if(cmplo < 0)
	    keys(x.left, list , lo , hi);
	if(cmplo <= 0 && cmphi >= 0)
	    list.push(x.key);
	if(cmphi > 0)
	    keys(x.right, list, lo , hi);
    }
    
}
