import java.util.Iterator;
import java.util.NoSuchElementException;
public class Assignment3<Vertex> implements Iterable<Vertex>{
    Node head;
    public Assignment3(){
	head = new Node(0);

    }

    public Iterator<Vertex> iterator(){
	return new Iterator<Vertex>() {
	    Node current = head;

	    public boolean hasNext(){
		return current.next != null;
	    }

	    public Vertex next(){
		if(current == head)
		    throw new NoSuchElementException();
		Node cod = current;
		current = current.next;
		return cod;

	    }
	      
      
	};
    }
    private class Node{
	Node next, prev;
	int item;
	public Node(int smth)
	    
	{
	    next = null;
	    prev = null;
	    this.item = smth;
	}
    }
    
    public void printElements(){
	for(Vertex n : this)
	    System.out.print(n.item + " ");
    }
    
    public void addElement(int value){
	Node added = new Node(value);
	added.next = list.head.next;
	added.prev = list.head;
	list.head.next.prev = added;
	list.head.next = added;
	list.printElements();
	    
    }
    public void removeElement(){
	list.head.next.next.prev = list.head;
	list.head.next = list.head.next.next;
	list.printElements();

    }
    
    public static void main(String[] args){
	Assignment3<Node> list = new Assignment3<>();

	
	list.head.next = list.head;
	list.head.prev = list.head;
	list.addElement(3);
	list.addElement(4);
	list.addElement(5);
	list.removeElement();
	list.addElement(6);
    }
}
