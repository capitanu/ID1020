

import java.io.IOException;
import java.io.InputStreamReader;


class Assignment6v3{


    public static void main(String[] args) throws IOException {
	InputStreamReader in = new InputStreamReader(System.in);
	int index = 0;
	int c = ' ';
	int check = 0;
	char data;
	int sum = 0;
	while(c != -1){
	    c =  in.read();
	    data = (char) c;
	    check = 1;
	    if(c == args[0].charAt(0)){
		for(int i = 1; i < args[0].length(); i++){
		    if((c = (char)in.read()) != args[0].charAt(i)){
			check = 0;
			break;
		    }
		    
		}
		if(check == 1) {
		    sum++;
		    System.out.println("Index: " + index);
		}
		index += args[0].length();
	    }
	    index++;
	    
	}
	System.out.println("The word was found " + sum + " times");

    }
}


    
