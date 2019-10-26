/*
  Calin Capitanu
  19 September 2019
  Input: One by one, an integer to be set into the priority queue or 0 to exit
  Output: The queue every time a node is created with ascending order
  This program uses a linked list in order to create the priority queue.
 */

import java.util.Scanner;

class Assignment7{
    private class Node{
	int item;
	Node next;
	public Node(int elem){
	    this.item = elem;
	    next = null;
	}
    }
    public Node first = null;
    
    public void addNode(int value){
	if(this.first == null){
	    first = new Node(value);
	    return;
	}
	if(value < first.item){
	    Node n = new Node(value);
	    n.next = first;
	    first = n;
	    return;
	    


	}
	    
	Node n = first;
	while(n.next != null && n.next.item < value)
	    n = n.next;
	if(n.next == null)
	    n.next = new Node(value);
	else
	    {
		Node pt = new Node(value);
		pt.next = n.next;
		n.next = pt;
	    }
	return;

    }

    public void printElements(){
	Node n = first;
	while(n.next != null){
	    System.out.print(n.item + ", ");
	    n = n.next;
		}
	System.out.println(n.item);
    }


	
    public static void main(String[] args){
	Assignment7 list = new Assignment7();
	Scanner in = new Scanner(System.in);
	int input;
	while(true){
	    System.out.println("Input the value for the new node or 0 to exit");
	    input = in.nextInt();
	    if(input == 0)
		break;
	    else list.addNode(input);
	    list.printElements();

	}
		    

    }
}

