// Calin Capitanu
// 30 - August - 2019
// This program is going to create a class of type BinaryTree which is basically
// a database format. It is going to store items of type integer.
// Input: none
// Output: The BinaryTree is going to be printed in the desire format to the standard output.
// This program can only be tested from the main method by creating an object of type BinaryTree
// which in this case will be called Assgnment5 and starting to create Nodes and manipulate them
// in the desired way (delete, add, search for specific ones and return the result of the search)


class Assignment5{
    public Assignment5(){};
    public static class Node{
	public int item;
	public Node left;
	public Node right;
	public Node(int itemrec){
	    this.item = itemrec;
	    this.left = null;
	    this.right = null;
	}
    }
    Node root = null;
    int check = 0;
    public static void main(String[] args){
	Assignment5 bt = new Assignment5();
	Node n1 = new Node(4);
	Node n2 = new Node(7);
	Node n3 = new Node(9);
	Node n4 = new Node(11);
	Node n5 = new Node(15);
	Node n6 = new Node(29);
	Node n7 = new Node(27);
	bt.addItem(n3, bt.root);
	bt.addItem(n2, bt.root);
	bt.addItem(n1, bt.root);
	bt.addItem(n6, bt.root);
	bt.addItem(n4, bt.root);
	bt.prefix(bt.root);	
	System.out.println();
	System.out.println(bt.findKey(bt.root,6));
    }


    public boolean findKey(Node head, int key){
	if(head.item == key){
	    this.check = 1;
	    //The need of this check value is because of the way recursive functions work. Otherwise, the only returned value is going to be the one reffering to the head of the binary tree. So, the check value is a variable included in the object and can then be read by any method (including the last one in which the output is going to be returned).
	    return true;
	    
	}
	if(head.item > key){
	    if(head.left != null)
		findKey(head.left, key);}
	else
	    if(head.right != null)
		findKey(head.right, key);
	if(check == 1)
	    return true;
	return false;
    }
    
    public void addItem(Node newNode, Node head){
	if(head == null){
	    this.root = newNode;
	    return;
	}
	if(head.item > newNode.item)
	    if(head.left == null)
		head.left = newNode;
	    else addItem(newNode, head.left);
	else if(head.right == null)
	    head.right = newNode;
	else addItem(newNode, head.right);
    }

    public void prefix(Node head){
	if(head != null){
	    System.out.print(head.item + " ");
	    prefix(head.left);
	    prefix(head.right);
	}
    }
    
    public void infix(Node head){
	if(head != null){
	    infix(head.left);
	    System.out.print(head.item + " ");
	    infix(head.right);
	}  
    }
    
    public void postfix(Node head){
	if(head != null){
	    postfix(head.left);
	    postfix(head.right);
	    System.out.print(head.item + " ");
	}  
    }
    


}
