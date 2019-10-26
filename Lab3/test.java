


import java.lang.StringBuilder;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.IOException;
class test{

    public static void main(String[] args) throws IOException{
	Scanner in = new Scanner(System.in);
	InputStreamReader input = new InputStreamReader(System.in);
	String word = in.next();
       
	char c = (char) input.read();
	char t = (char) input.read();
	String word1 = in.next();

	System.out.println(word);
	System.out.println(c);
	System.out.println(t);
	System.out.println(word1);

    }

}
