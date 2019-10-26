
import java.io.InputStreamReader;
import java.io.IOException;


public class Assignment2recursive {
    static InputStreamReader in = new InputStreamReader(System.in);

    public static void recursive() throws IOException{
	char c;
	if((c = (char) in.read()) != '\n') recursive();
	System.out.print(c);
    }

    
    public static void main(String[] args) throws IOException{
	recursive();
	System.out.println();
    }

}
