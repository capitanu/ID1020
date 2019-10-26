
import java.io.InputStreamReader;

class Assignment7{
    public static void main(String[] args) throws Exception{
	InputStreamReader in = new InputStreamReader(System.in);
	int[] a = new int[6];
	for(int i = 0; i < 6; i ++)
	    a[i] = 0;
	char c;
	while((c = (char) in.read()) != '\n')
	    {
		switch (c){
		case '(':
		    a[0]++;
		    break;
		case ')':
		    a[1]++;
		    break;
		case '{':
		    a[2]++;
		    break;
		case '}':
		    a[3]++;
		    break;
		case '[':
		    a[4]++;
		    break;
		case ']':
		    a[5]++;
		    break;
		default:
		    break;
		    }
		  }
	if(a[0] == a[1])
	    System.out.println(" '(' and ')' are balanced");
	else
	    System.out.println(" '(' and ')' are not balanced");
		
	if(a[2] == a[3])
	    System.out.println(" '{' and '}' are balanced");
	else
	    System.out.println(" '{' and '}' are not balanced");
		
	if(a[4] == a[5])
	    System.out.println(" '[' and ']' are balanced");
	else
	    System.out.println(" '[' and ']' are not balanced");

    }
}
