// Calin Capitanu
// 30 - August - 2019
// This short program is going to print an array of elements in reverse order
// Input: Takes the length of the array first and then the array itself from stdin
// Output: Prints only the array in reverse order than the input. Printed to stdout
// Tests are conducted from the main method itself
// Does not exclude errors such as negative length of array. Works only for proper inputs

import java.util.Scanner;


class Assignment2
{
    public static void main(String[] args){
	Scanner in = new Scanner(System.in);
	int nrElements = in.nextInt();
	if(nrElements < 0)
	    System.out.println("The number you have entered can not be used as a length of array");
	int[] intarr = new int[nrElements];
	for(int i = 0; i < nrElements; i++)
	    intarr[i] = in.nextInt();
	for(int i = nrElements - 1; i >= 0; i--)
	    System.out.println(intarr[i]);
	in.close();
		
    }
    

}
    
