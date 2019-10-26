// Calin Capitanu
// 30 - August - 2019
// The purpose of this program is to create a Linked List with the rule 
// Last In First Out.
// This is one simple database that can store integers and manipulate them.
// Input: None
// Output: Prints the database (integers) in order.
// The way this program is tested and used is from the main method where other methods are
// called in order to create new Nodes in the list and manipulate them (delete, insert in order or add)
// The test that is presented here is randomly created, calling different functions.

public class Assignment4a
{
    public Assignment4a(){};
    private static class Node{ //This is going to be the main entity in the LinkedList
	public int item;
	public Node next;
	public Node(int itemrec){
	    this.item = itemrec;
	    next = null;
	}
    }
    Node nodes = null;
    public static void main(String[] args){
	Assignment4a ll = new Assignment4a(); //Creating and object of LinkedList (named Assignment4a here)
	ll.addItem(3);
	ll.addItem(7);
	ll.addItem(15);
	ll.addItem(25);
	ll.addItem(27);
	ll.printElements();
	ll.removeLast();
	ll.printElements();
	ll.addItem(30);
	ll.addItem(31);
	ll.printElements();
	ll.removeFirst();
	ll.printElements();
	ll.addItem(60);
	Node ps = new Node(1);
	ll.insertSorted(ps);
	Node ps1 = new Node(17);
	ll.insertSorted(ps1);
	Node ps2 = new Node(19);
	ll.insertElement(ps2,ps1);
	ll.printElements();
    }
	
    public void removeFirst(){
	System.out.println(nodes.item);
	nodes = nodes.next;
    }
    public void removeLast(){
	if(nodes == null)
	    return;
	Node n = this.nodes;
	while(n.next != null)
	    n = n.next;
	System.out.println(n.item);
	n = null;
    }
	// I created two different functions named intuitively in order to make sure that the 
	// requirements are met. It was written "A method that removes the first elements"
	// Which could be interpreted as the first in the list (bottom of the stack)
	// Or the first on the top of the stack.

    public void addItem(int item){
	Node newnode = new Node(item);
	if(this.nodes == null){
	    this.nodes = newnode;
	    return;
	}
	Node n = this.nodes;
	while(n.next != null)
	    n = n.next;
	n.next = newnode;
    }
    public void printElements(){
	Node n = this.nodes;
	while(n != null){
	    System.out.print(n.item + " ");
	    n = n.next;
	}
	System.out.println();
    }
    public void insertElement(Node n, Node p){
	n.next = p.next;
	p.next = n;
    }
    public void insertSorted(Node newnode){
	if(this.nodes == null){
	    this.nodes = newnode;
	    return;
	}
	if(nodes.item > newnode.item){
	    newnode.next = nodes;
	    nodes = newnode;
	    return;
	}
	Node n = this.nodes;
	while(n.next != null && n.next.item < newnode.item)
	    n = n.next;
	if(n == null){
	    n = newnode;
	    return;
	}
	newnode.next = n.next;
	n.next = newnode;

    }
    



}
