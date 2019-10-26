// Calin Capitanu
// 30 - August - 2019
// This short program is going to print an array of elements in reverse order
// Input: Takes the length of the array first and then the array itself from a file
// File name is task2 (without the .txt extension as it is created in Linux)
// File should be placed in the same folder as the project itself
// Output: Prints only the array in reverse order than the input. Printed to stdout
// Tests are conducted from the main method itself
// Does not exclude errors such as negative length of array. Works only for proper inputs



import java.util.Scanner;
import java.io.*; //For BufferedReader and FileReader. Needed to read from a file

class Assignment2file
{
    public static void main(String[] args){
	BufferedReader br = null;
	try{
	    br = new BufferedReader(new FileReader("task2"));
	    int nrElements = Integer.parseInt(br.readLine());
	    int[] arrint = new int[nrElements];
	    for(int i = 0; i < nrElements; i++)
		arrint[i] = Integer.parseInt(br.readLine());
	    for(int i = nrElements-1; i >= 0; i--)
		System.out.println(arrint[i]);
				    

	}
	catch(IOException e){
	    e.printStackTrace();
	}
	finally {
	    try{
	    br.close();
	    }
	    catch(IOException e){
		e.printStackTrace();
	    }


	    
	}
	
    }
    

}
    
