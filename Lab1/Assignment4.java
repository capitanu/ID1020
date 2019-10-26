// Calin Capitanu
// 7 - Septmeber - 2019
/*      Input: From the test method (main) using methods specifically or from the API created.
	Output: Every time a change in the list is done, the whole data is printed to stdout
	This double linked list is able to add and remove elemnts from both the back and the front of the data structure
	It is using an Iterable interface in order to iterate through all the objects and print them when needed.
	

 */



import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.Scanner;

public class Assignment4<Ver> implements Iterable<Ver>{


    
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
    

    
    public void printElements(){
	for(Ver v : this)
	    System.out.print("[" +v + "], ");
	System.out.println();
    }


    public void addElementFront(Ver value){
        Node<Ver> added = new Node<>(value);
	if(head == null){
	    head = new Node<>();
	    head.next = head;
	    head.prev = head;
	    

	}
	added.next = head.next;
	added.prev = head;
	head.next.prev = added;
	head.next = added;
	
	printElements();
    }
    public void addElementBack(Ver value){

	Node<Ver> added = new Node<>(value);
	if(head == null){
	    head = new Node<>(value);
	    head.next = head;
	    head.prev = head;

	}
	added.next = head;
	added.prev = head.prev;
	head.prev.next = added;
	head.prev = added;
	
	printElements();

    }
   
    public void removeElementFront(){
	head.next.next.prev = head;
	head.next = head.next.next;
	printElements();
    }
    public void removeElementBack(){
	head.prev.prev.next = head;
	head.prev = head.prev.prev;
	printElements();
    }



    
    
    public static void main(String[] args){
	Assignment4<Integer> list = new Assignment4<>();
	Scanner in = new Scanner(System.in);
	int input;
	while(true){
	    System.out.println("Input what you want to do:");
	    System.out.println("0 to exit");
	    System.out.println("1 to add the element to the front");
	    System.out.println("2 to add the element to the back");
	    System.out.println("3 to remove the element in the front");
	    System.out.println("4 to remove the element in the back");
	    input = in.nextInt();
	    if(input == 0)
		break;
	    switch(input){
	    case 1:
		System.out.print("Input the element: ");
		list.addElementFront(in.nextInt());
		break;
	    case 2:
		System.out.print("Input the element: ");
		list.addElementBack(in.nextInt());
		break;
	    case 3:
		list.removeElementFront();
		break;
	    case 4:
		list.removeElementBack();
		break;
	    default:
		break;
	    }
		    

	}


    }


}

 
    
