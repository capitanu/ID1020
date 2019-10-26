// Calin Capitanu
// 7 - Septmeber - 2019
/*      Input: Written from the test method (main) using each method
	Output: It is going to print the whole data structure every time something is changed
	This program is usign a circular double linked list and adds or removes nodes using methods specifically for that called from the main method
	It also implements the Iterable interface in order to iterate through all nodes and print them.

*/



import java.util.NoSuchElementException;
import java.util.Iterator;

public class Assignment3v2<Ver> implements Iterable<Ver>{


    private class Node<Ver> {
	Ver item;
	Node<Ver> next, prev;
	public Node(Ver item){
	    this.item = item;
	    next = null;
	    prev = null;	
	}

	public Node(){}
    }

    Node<Ver> head;

    public Iterator<Ver> iterator(){
	return new Iterator<Ver>(){
	    Node<Ver> current = head.next;

	    public boolean hasNext(){
		return current != head;
      	    }

	    public Ver next(){
		Ver item = current.item;
		current = current.next;
		return item;
	    }
     	};


    }
    

    
    public void addElement(Ver value){
	Node<Ver> added = new Node<>(value);
	if(head == null){
	    head = new Node<>(value);
	    head.next = head;
	    head.prev = head;

	}
	added.next = head.next;
	added.prev = head;
	head.next.prev = added;
	head.next = added;
	printElements();
    }
    public void removeElement(){
	head.next.next.prev = head;
	head.next = head.next.next;
	printElements();
    }
    
    public void printElements(){
	for(Ver v : this)
	    System.out.print(v + " ");
	System.out.println();
    }





    
    public static void main(String[] args){
	Assignment3v2<Integer> list = new Assignment3v2<>();
       
	list.addElement(32);
	list.addElement(22);
	list.addElement(6);
	list.removeElement();
	list.addElement(18);

    }
}
