// Calin Capitanu
// 7 - Septmeber - 2019
/*      Input: Adding integers to the list is done with the methods in the main method after creating an object of type Assignment5
	Output: Every time an operation is done, the whole data structure is going to be printed in order to stdout
	This data structure is a stack implemented with an array that can add/remove elements. Whenever an element is added, it is added 
	in ascending order. Based on Assignment 5.

 */



class Assignment6 {
    int size = 0;
    int[] arr = null;
    public Assignment6(){}
    public void increaseSize(){
	if(arr == null)
	    arr = new int[50];
	else
	    arr = new int[arr.length*2];
    }
    public void printElements(){
	for(int i = 0; i < size; i++){
	    System.out.print("[" + arr[i] + "], ");
	}
	System.out.println();

    }

    public void addElement(int element){
	if(arr == null || size == arr.length - 1)
	    increaseSize();
	int i;
	for(i = 0; i < size; i++ ){
	    if(element < arr[i])
		break;
	}
	for(int j = size-1; j >= i; j--)
	    arr[j+1] = arr[j];
	arr[i] = element;
		
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
	Assignment6 queue = new Assignment6();
	queue.addElement(5);
	queue.addElement(14);
	queue.addElement(6);
	queue.addElement(8);
	queue.addElement(2);
	queue.removeElement();
	queue.removeSpecificElement(2);
    }
}
