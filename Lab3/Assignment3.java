/*
  Calin Capitanu 
  23 September 2019
  Input: A text file
  Output: The most frequently used words starting from the n-th most frequent to the n+x-th most used element

  This program uses the array-type data structure from the previous assignment to do the proper searching. Some minor changes were made in order to print the specific words.
 */


import java.util.Scanner;

class Assignment3{

    public static class BinarySearchST<Key extends Comparable<Key>, Value> {
	public Key[] keys;
	private Value[] values;
	private int N;

	public BinarySearchST(int capacity){
	    //@SuppressWarnings("unchecked")
	    this.keys = (Key[]) new Comparable[capacity];
	    //@SuppressWarnings("unchecked")
	    this.values = (Value[]) new Object[capacity];
	}

	public Value get(Key key){
	    if(keys == null) return null;
	    int i = rank(key);
	    if( i < N && keys[i].compareTo(key) == 0) return values[i];
	    else return null;
	}

	
	public int rank(Key key)
	{
	    int lo = 0, hi = N-1;
	    while (lo <= hi)
		{
		    int mid = lo + (hi - lo) / 2;
		    int cmp = key.compareTo(keys[mid]);
		    if(cmp < 0) hi = mid - 1;
		    else if (cmp > 0) lo = mid + 1;
		    else return mid;
		}
	    return lo;
	}
	public void put(Key key, Value val)
	{ 
	    int i = rank(key);
	    if (i < N && keys[i].compareTo(key) == 0)
		{ values[i] = val; return; }
	    for (int j = N; j > i; j--)
		{ keys[j] = keys[j-1]; values[j] = values[j-1]; }
	    keys[i] = key; values[i] = val;
	    N++;
	}

        public Value returnValue(int i){
	    if(keys[i] != null)
		return get(keys[i]);
	    else return null;
	}

	public Key returnKey(int i){
	    return keys[i];
	}
	

    }

    


    

    public static void testBinarySearchST(int minlen, int n, int x){
	int size = 100000;
        BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>(size);
	Scanner in = new Scanner(System.in);
	while(in.hasNext()){
	    String word = in.next();
	    if(word.length() < minlen)
		continue;
	    if(st.get(word) == null)
		st.put(word, 1);
	    else
		st.put(word, st.get(word) + 1);
	}

	


	//Still getting the most frequent word overall
	String max = "";
	st.put(max, 0);
	
	for(int i = 0; i < size; i++){
 	    if(st.returnValue(i) == null)
		continue;
	    if(st.returnValue(i) > st.get(max))
		max = st.returnKey(i);
	}

	System.out.println("The x most frequent elements starting from the n-th are: ");
	//If input asks to start from the most frequently used one, we just print it here.
	if( n == 1){
	    System.out.println('"' + max +'"'  + " - " + st.get(max));
	    n++;
	    x--;
	}
	//Here we start going through the array once again, making the top boundery be the previous most frequent word. I am decrementing x every time.
	while(x-- != 0){
	    int n1 = n;
	    while(--n != 0){
		String max1 = "";
		st.put(max1, 0);
		for(int i = 0; i < size; i++){
		    if(st.returnValue(i) == null)
			continue;
		    if(st.returnValue(i) < st.get(max) && st.returnValue(i) > st.get(max1))
			max1 = st.returnKey(i);
		}
		max = max1;
	    }
	    n = n1;

	    System.out.println('"' + max +'"'  + " - " + st.get(max));
	}
	

    }
   
	



    public static void main(String[] args)
    {
	testBinarySearchST(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));

    }
   

   

}
