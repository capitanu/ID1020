
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.StringBuilder;
import java.util.Scanner;
class Assignment6v2{

    public static class LinkedList{
	Node head;
	public static class Node{
	    String word;
	    int spacesAfter;
	    Node next;
	    public Node(String s){
		this.word = s;
		this.spacesAfter = 0;
	    }
	    public Node(int size){
		this.word = null;
		this.spacesAfter = size;
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
	public void add(int s){
	    if(head == null){
		head = new Node(s);
		return;
	    }
	    Node n = head;
	    while(n.next != null)
		n = n.next;
	    n.next = new Node(s);
	}
    


    
	public static void main1() throws IOException{
	    InputStreamReader in = new InputStreamReader(System.in);
	    StringBuilder sb = new StringBuilder();
	    char c = 0;
	    int sum = 0,check = 0;;
	    LinkedList ll = new LinkedList();
	    while(c != '}'){
		c = (char) in.read();
		if(c != '\n' && c != ' '){
		    if(check == 1){
			ll.add(sb.toString());
			sb = new StringBuilder();    
		
		    }
		    sb.append(c);
		    sum = 0;
		    check = 0;
		}
		else {
		    if(check == 0)
			ll.add(sum);
		    check = 1;
		    sum++;
		}
		
	    }
	    in.close();

	    String word = "mere";
	    Node n = ll.head;
	    int size = 0;
	    while(n != null){
		if(n.word.equals(word))
		    System.out.println("Index: " + size);
		if(n.word == null)
		    size += n.spacesAfter;
		else
		    size += n.word.length();

		n = n.next;

	    }
	
	    
		
	}

    }
    public static void main(String[] args) throws IOException{
	LinkedList.main1();
    }
    
}
