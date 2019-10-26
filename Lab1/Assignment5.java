// Calin Capitanu
// 7 - Septmeber - 2019
/*      Input: Adding integers to the list is done with the methods in the main method after creating an object of type Assignment5
	Output: Every time an operation is done, the whole data structure is going to be printed in order to stdout
	This data structure is a stack implemented with an array that can add/remove elements and also remove a specific element from a k-th position

 */


import java.util.ArrayList;

class Assignment5<Object> {
    int size = 0;
    Object[] arr = null;
    public Assignment5(){}
    
    public void increaseSize(){
	if(arr == null)
	    arr = new Object[50];
	else {
	    Object[] arr2 = new Object[arr.length + 50];
	    for(int i = 0; i < size; i++ )
		arr2[i] = arr[i];
	    arr = arr2;

	}
	   
    }
    public void printElements(){
	for(int i = 0; i < size; i++){
	    System.out.print("[" + arr[i] + "], ");
	}
	System.out.println();

    }

    public void addElement(Object element){
	if(arr == null)
	    increaseSize();
	if(size == arr.length - 1)
	    increaseSize();
	arr[size] = element;
	size++;
	printElements();
	}

    public void removeSpecificElement(int k){
	for(int j = k-1; j < size - 1; j ++)
	    arr[j] = arr[j+1];
	size--;
	printElements();

    }
    public void removeElement(){
	for(int i = 0; i < size - 1; i ++)
	    arr[i] = arr[i+1];
	size--;
	printElements();
	
    }
    


    public static void main(String[] args){
	Assignment5<Integer> queue = new Assignment5<>();
	queue.addElement(5);
	queue.addElement(6);
	queue.addElement(7);
	queue.addElement(8);
	queue.addElement(9);
	queue.removeElement();
	queue.removeSpecificElement(2);
    }
}
