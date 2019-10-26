// Calin Capitanu
// 30 - August - 2019
// The purpose of this program is to create a Linked List with circular motion.
// This means that every node can go to the previous one or to the next one
// Compared to the basic LinkedList where you can only go to the next one
// Input: None
// Output: Prints the database (integers) in order to stdandard output.
// The way this program is tested and used is from the main method where other methods are
// called in order to create new Nodes in the list and manipulate them (delete, insert in order or add)
// The test that is presented here is randomly created, calling different functions.




public class Assignment4b
{
    public Assignment4b(){};
    public static class Node{
	public int item;
	public Node next;
	public Node prev;
	public Node(int itemrec){
	    this.item = itemrec;
	    next = null;
	    prev = null;
	}
    }
    Node nodes = null;
    public static void main(String[] args){
	Assignment4b ll = new Assignment4b(); //This is the object reffered to as LinkedList
	Node first = new Node(3);
	Node node1 = new Node(7);
	Node node2 = new Node(1);
	Node node3 = new Node(17);
	Node node4 = new Node(25);
	Node node5 = new Node(21);
	ll.addFirst(first);
	ll.addItem(node1, first);
	ll.insertSorted(node2);
	ll.insertSorted(node3);
	ll.insertSorted(node4);
	ll.insertSorted(node5);
	Node removed = ll.removeElement(node5);
	ll.printElements();
    }
    
    public Node removeElement(Node nodeRemove){
	if(this.nodes == nodeRemove){
	    nodes.prev.next = nodes.next;
	    nodes.next.prev = nodes.prev;
	    nodes = nodes.next;
	    nodeRemove.prev = nodeRemove;
	    nodeRemove.next = nodeRemove;
	    return nodeRemove;
	}
	Node n = this.nodes.next;
	while(n != nodeRemove && n != nodes){
	    n = n.next;
	}
	
	if(n == this.nodes){
	    System.out.println("The node does not exist");
	    return nodeRemove;
	}
	n.next.prev = n.prev;
	n.prev.next = n.next;
	nodeRemove.next = nodeRemove;
	nodeRemove.prev = nodeRemove;
	return nodeRemove;
    }
    
    public void addFirst(Node node){
	this.nodes = node;
	this.nodes.next = node;
	this.nodes.prev = node;

    }

    public void addItem(Node newNode, Node nodeBefore){
	Node n = this.nodes;
	while(n != nodeBefore)
	    n = n.next;
	n.next.prev = newNode;
	newNode.next = n.next;
	n.next = newNode;
	newNode.prev = nodeBefore;

        
    }
    public void printElements(){
	if(this.nodes != null)
	  System.out.print(this.nodes.item + " ");
	Node n = this.nodes.next;
	while(n != null && n != nodes){
	    System.out.print(n.item + " ");
	    n = n.next;
	}
	System.out.println();
    }
    public void insertSorted(Node newnode){
	if(this.nodes == null){
	    this.nodes = newnode;
	    return;
	}
	if(nodes.item > newnode.item){
	    newnode.next = nodes;
	    newnode.prev = nodes.prev;
	    nodes.prev.next = newnode;
	    nodes = newnode;
	    return;
	}
	Node n = this.nodes;
	while(n.next != nodes && n.next.item < newnode.item)
	    n = n.next;
	if(n.next == nodes){
	    n.next = newnode;
	    newnode.prev = n;
	    newnode.next = nodes;
	    nodes.prev = newnode;
	    return;
	}
	newnode.next = n.next;
	n.next.prev = newnode;
	n.next = newnode;
	newnode.prev = n;

    }
    



}
