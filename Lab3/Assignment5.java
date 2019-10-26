
/*
  Calin Capitanu 
  23 September 2019
  Input: A text file
  Output: The bounderies in which all the hash codes are + the number of total repeating pairs.

  This program uses a Linked List to store all the hash codes in a sorted fashion.

 */

import java.util.Scanner;

class Assignment5 {

    public static class LinkedList{
	public Node head;
	public int size = 0;
	public class Node{
	    Node next;
	    int value;
	    public Node(int value){
		this.value = value;
		next = null;
	    }
	}

	public void insert(int value){ //Insert each new hash code in the proper place
	    Node n = new Node(value);
	    size++;
	    if(head == null){
		head = n;
		return;
	    }
	    if(n.value < head.value){
		n.next = head;
		head = n;
		return;
	    }
	    Node current = head;
	    while(current.next != null){
		if(n.value < current.value){
		    n.next = current.next;
		    current.next = n;
		    return;
		}
		current = current.next;
	    }
	    current.next = n;
	}

	public void minMax(){  //First element in the linked list will be the smallest and the last will be the greatest, so just print those two.
	    System.out.println("Hashes start from " + head.value);
	    Node n = head;
	    while(n.next != null)
		n = n.next;
	    System.out.println("Up to " + n.value);
	}

	public void pairs(){ //As the linked list is ordered, all the pairs are adjecent, so just check if two consecutive hashcodes are the same and add that up to the sum.
	    Node n = head;
	    int pairs = 0;
	    while(n.next != null){
		if(n.value == n.next.value)
		    pairs++;
		n = n.next;
	    }
	    System.out.println("And there are " + pairs + " pairs of safe hashCodes!");

	}
        
    }



    public static void main(String[] args){ //Testing is done here, it just uses the API of the Linked List implemented above to check the hash codes for all the words in the text.
	Scanner in = new Scanner(System.in);
	LinkedList ll = new LinkedList();
	while(in.hasNext()){
	    String word = in.next();
	    ll.insert(word.hashCode());
	}
	System.out.println("From a total of: " + ll.size + " hashcodes.");
	ll.minMax();
	ll.pairs();

    }
}
