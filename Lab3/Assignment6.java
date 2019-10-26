/*
  Calin Capitanu 
  23 September 2019
  Input: A text file
  Output: The index(es) at which the word indicated can be found.
  Even though in the task we were allowed to use arraying from java libraries, as there were no restrictions, I found that the easiest way is to not even use any data structure, but instead just iterate through the file while adding the lengths of each word.
 */

import java.util.Scanner;
import java.io.InputStreamReader;
import java.lang.StringBuilder;
class Assignment6{


    public static class LinkedList{
	Node head;
	public class Node{
	    String line;
	    Node next;
	    public Node(String s){
		this.line = s;
	    }
	}

	public void add(String s){
	    if(head == null){
		head = new Node(s);
		return;
	    }
	    Node n = head;
	    while(n.next != null)
		n = n.next;
	    n.next = new Node(s);
	}
    


    public static void main(String[] args){
	Scanner in = new Scanner(System.in);
	InputStreamReader input = new InputStreamReader(System.in);
	LinkedList ll = new LinkedList();
	// System.out.print("Input the word you are looking for: ");
	String search = "Project"; //This is the word we are looking for
	System.out.println();
	System.out.println("The word can be found on the following indexes: ");
	long sum = 0;
	long sum1 = 0;
	while(in.hasNext()){
	    String word = in.next();
	    char c = (char) input.read();
	    
	    if(word.equals(search)){
		System.out.println(sum);
		sum1++;
	    }
	    sum += word.length() + 1; //The "+1" here is supposed to take place for every space in between words. This means that if there are more spaces, this will result in faulty results.
	}
	System.out.println("This can be found "+ sum1 + "times");
	
    }

}
