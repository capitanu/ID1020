// Calin Capitanu
// 7 - September - 2019
/*      Input: Is written from the main method using methods to call specific functions in the queue (add/remove/removespecific)
	Output: Prints to stdout the content of the queue after each command
	This data structure is a queue implemented with a linked list that can add/remove elements as a normal queue and also remove a specific element with the function removeSpecificElement(int index) given an index

*/



class Assignment5v2<Object> {
    Node head;
    private class Node{
	Object item;
	Node next;
	public Node(Object value){
	    next = null;
	    this.item = value;
	}
    }
    
    public void printElements(){
	Node n = head;
	while(n != null){
	    System.out.print("[" + n.item.toString() + "], ");
	    n = n.next;
	}
	System.out.println();
    }

    public void addElement(Object element){
	if(head == null){
	    head = new Node(element);
	    return;
	}
	Node created = new Node(element);
	Node n = head;
	while(n.next != null)
	    n = n.next;
	n.next = created;
	printElements();
    }
    public void removeElement(){
	head = head.next;
	printElements();
    }

    public void removeSpecificElement(int k){
	if( k == 1 && head != null){
	    head = head.next;
	    printElements();
	    return;
	}
	    
	Node n = head;
	for(int i = 1; i < k-1; i++)
	    n = n.next;
	if(n == null)
	    throw new NullPointerException();
	if(n.next.next == null){
	    n.next = null;
	} else {
	    n.next = n.next.next;
	}
	printElements();

    }

    public static void main(String[] args) throws NullPointerException{
	Assignment5v2<Integer> queue = new Assignment5v2<>();
	queue.addElement(5);
	queue.addElement(6);
	queue.addElement(7);
	queue.addElement(8);
	queue.addElement(9);
	queue.removeElement();
	queue.removeSpecificElement(1);

    }
}
