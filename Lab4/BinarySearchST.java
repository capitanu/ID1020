

import java.util.LinkedList;

public class BinarySearchST<Key extends Comparable<Key>, Value>  {

    private Key[] keysArray;
    private Value[] valuesArray;
    private int numberOfElementsInArrays = 0;

    public BinarySearchST(int capacity) {
        keysArray = (Key[]) new Comparable[capacity];
        valuesArray = (Value[]) new Object [capacity];
    }
    public int size() {
        return numberOfElementsInArrays;
    }
    public boolean isEmpty() {
        return size() == 0;
    }
    public boolean contains(Key key) {
        return get(key) != null;
    }
    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < numberOfElementsInArrays && keysArray[i].compareTo(key) == 0) {
            return valuesArray[i];
        } else {
            return null;
        }
    }
    public void put(Key key, Value val) {
        if(keysArray.length == numberOfElementsInArrays) {
            resize(keysArray.length * 2);
        }

        int i = rank(key);
        if (i < numberOfElementsInArrays && keysArray[i].compareTo(key) == 0) {
            valuesArray[i] = val;
            return;
        }
        for (int j = numberOfElementsInArrays; j > i; j--) {
            keysArray[j] = keysArray[j - 1];
            valuesArray[j] = valuesArray[j - 1];
        }
        keysArray[i] = key;
        valuesArray[i] = val;
        numberOfElementsInArrays++;
    }
    public int rank(Key key) {
        int lo = 0, hi = numberOfElementsInArrays - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keysArray[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            }
            else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }
    private void resize(int max) {
        Key[] keyTemp = (Key[]) new Comparable[max];
        Value[] valueTemp = (Value[]) new Object[max];

        for (int i = 0; i < numberOfElementsInArrays; i++) {
            keyTemp[i] = this.keysArray[i];
            valueTemp[i] = this.valuesArray[i];
        }
        this.keysArray = keyTemp;
        this.valuesArray = valueTemp;
    }

    public Key min() {
        return keysArray[0]; }

    public Key max() {
        return keysArray[numberOfElementsInArrays-1]; }

    public Key select(int k) {
        return keysArray[k]; }

    public Key ceiling(Key key) {
        int i = rank(key);
        return keysArray[i];
    }
    public Iterable<Key> keys() {
        LinkedList<Key> linkedListOfKeys = new LinkedList<>();
        for (int i = 0; i < numberOfElementsInArrays; i++) {
            linkedListOfKeys.push(keysArray[i]);
        }
        return linkedListOfKeys;
    }

    public void printArray() {
        System.out.println("-----begin------");
        for(int i = 0; i < keysArray.length; i++){
            System.out.println("Key: " + keysArray[i] + " " + "Value : " + valuesArray[i]);
        }
        System.out.println(numberOfElementsInArrays);
        System.out.println("------end--------");
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        LinkedList<Key> linkedListOfKeys = new LinkedList<>();
        for (int i = rank(lo); i < rank(hi); i++) {
            linkedListOfKeys.push(keysArray[i]);
        }
        if (contains(hi))
            linkedListOfKeys.push(keysArray[rank(hi)]);
        return linkedListOfKeys;
    }

    public Key getKey(Value value) {
        for (Key key : this.keys()) {
            if(get(key) == value) {
                return key;
            }
        }
        return null;
    }
}
