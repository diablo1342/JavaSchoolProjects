package systemImp;

import java.util.LinkedList; 
import java.util.ArrayList; //ONLY use for return value of getValues method
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A HashMap implementation using separate chaining with LinkedLists.<br>
 * 
 * - Stores Integer keys and String values. <br>
 * - Uses a hash function with compression: ((A * key + B) % P) % table.length<br>
 * - Rehashes when the average chain length exceeds a threshold.<br>
 * - Does not allow duplicate keys (updates value instead), and keys must be 4-digit integers from 1000 (inclusive) to 9999 (inclusive).<br>
 * - Does not allows null values.<br>
 * - Uses an initial size of 2 to encourage collisions.<br>
 * - Since keys are 4-digit numbers,  integer overflow is not a concern.<br>
 */
public class ChainedHashMap implements Iterable<ChainedHashMap.Entry> {
    
    /** A large prime number for hashing */
    private static final int P = 104729;
    /** Large prime multiplier for hashing */
    private static final int A = 2347;
    /** Large prime offset for hashing */
    private static final int B = 7919;

    /** Initial size of the hash table */
    private static final int INITIAL_SIZE = 2;
    /** Threshold for rehashing: when avg chain length exceeds this, we rehash */
    private static final int RESIZE_THRESHOLD = 3;
    /** Prime sizes for resizing */
    private static final int[] PRIMES = {2, 5, 11, 23, 47, 97, 197, 397};

    /** The hash table, where each index contains a linked list of entries */
    private LinkedList<Entry>[] table;
    /** The number of key-value pairs stored */
    private int size;
    /** The index of the current prime in PRIMES */
    private int primeIndex;

    /**
     * Entry class for storing key-value pairs in the linked list.<br>
     * Do NOT MAKE ANY CHANGES to  public static class Entry
     */
    public static class Entry {
        int key;
        String value;
        
        Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
        public int getKey() {  //used for testing only.  NOT needed in code you write
            return key;
        }
    }
    
    @SuppressWarnings("unchecked")
	public ChainedHashMap() {
    	table = (LinkedList<ChainedHashMap.Entry>[]) new LinkedList[INITIAL_SIZE];
    	for (int i = 0; i < table.length; i++) {
    		//initialize linked list for every index in the table
            table[i] = new LinkedList<ChainedHashMap.Entry>();
        }
    	size = 0;
    	primeIndex = 0;
    }
    
    private int hash(int key) {
    	return ((A * key + B) % P) % table.length;   
    }
    
    @SuppressWarnings("unchecked")
    private void rehash() {
        if (primeIndex >= PRIMES.length - 1) {
            return; 
        }
        
        //make a copy of the old table, so that we can add it to the new table after resizing
        LinkedList<Entry>[] temp = table;
        
        //update primeIndex
        primeIndex++;
        
        
        // Create new table
        table = (LinkedList<Entry>[]) new LinkedList[PRIMES[primeIndex]];
        for (int i = 0; i < table.length; i++) {
        	//initialize a linkedlist for every index of the table
            table[i] = new LinkedList<Entry>();
        }
        
        //rehash everything and add to new table
        for (LinkedList<Entry> bucket : temp) {
            for (Entry entry : bucket) {
                int index = hash(entry.key); 
                table[index].add(entry);
            }
        }
    }
    
    public int getSize() {
    	return size;
    }
    
    public int getTableLength() {
    	return table.length;
    }
    
    private boolean isValidKey(int key) {
    	return key >= 1000 && key <= 9999;
    }
    
    
    public boolean containsKey(int key) {
    	//check if key is valid
    	if(!isValidKey(key)) {
    		throw new IllegalArgumentException("Invalid key.");
    	}
    	
    	//find the index by hashing the key
    	int index = hash(key);
    	LinkedList<Entry> bucket = table[index];
    	
    	//check if a key is in each bucket
    	for(Entry entry : bucket) {
    		if(entry.key == key) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public void put(int key, String value) {
    	//check if the key and string are valid
    	if(!isValidKey(key) || value == null) {
    		throw new IllegalArgumentException("Invalid key or value.");
    	}
    	
    	//find the index by hashing the key
    	int index = hash(key);
    	
    	//if the key is already present, just find where the key is change the value of the entry
    	if(containsKey(key)) {
    		
    		LinkedList<Entry> bucket = table[index];
        	
        	for(Entry entry : bucket) {
        		if(entry.key == key) {
        			entry.value = value;
        			return;
        		}
        		
        	}
    	}else { //otherwise, just add a new entry to the linkedList at table[index]
    		Entry entry = new Entry(key, value);
    		table[index].add(entry);
    		size++;
    	}
    	
    	//check if average number of entries per bucket is greater than RESIZE_THRESHOLD
    	if((double) size/table.length > RESIZE_THRESHOLD) {
    		rehash();
    	}
    	
    	
    }
    
    public String get(int key) {
    	//check if key is valid
    	if(!isValidKey(key)) {
    		throw new IllegalArgumentException("Invalid key");
    	}
    	
    	int index = hash(key);
    	LinkedList<Entry> bucket = table[index];
    	//if we find the key, return the value at that key
    	for(Entry entry : bucket) {
    		if(entry.key == key) {
    			return entry.value;
    		}
    	}
    	return null;
    }
    
    public ArrayList<Integer> getValues(String target){
    	//check if target value is valid
    	if(target == null) {
    		throw new IllegalArgumentException("Invalid target.");
    	}
    	
    	//create ArrayList
    	ArrayList<Integer> values = new ArrayList<Integer>();
    	
    	//loop through table array and add keys that correspond to values which match the target
    	for(int i = 0; i < table.length; i++) {
    		LinkedList<Entry> bucket = table[i];
        	
        	for(Entry entry : bucket) {
        		if(entry.value.equals(target)) {
        			values.add(entry.key);
        		}
        	}
    	}
    	
    	return values;
    }
    
    public void remove(int key) {
    	//check if key is valid
        if (!isValidKey(key)) {  
            throw new IllegalArgumentException("Key must be a 4-digit number");
        }

        int index = hash(key);
        Iterator<Entry> iterator = table[index].iterator();
        
        //have to use iterator because we are removing stuff from the array, which would mess with for each loop
        while (iterator.hasNext()) {
            Entry entry = iterator.next();
            //remove the entry if they have the same key as the parameter
            if (entry.key == key) {
                iterator.remove();  
                size--;
                return;            
            }
        }
    }
    
    public String toString() {
    	String answer = "";
    	for(int i = 0; i < table.length; i++) {
    		
    		LinkedList<Entry> bucket = table[i];
        	answer += (i + "  -> ");
        	for(Entry entry : bucket) {
        		answer += ("(" + entry.key + ", " + entry.value +") ");
        	}
        	answer += "\n";
    	}
    	return answer;
    }
    
    public Iterator<Entry> iterator() {
        return new Iterator<Entry>() {
            private int bucketIndex = 0;
            private Iterator<Entry> bucketIterator = table[0].iterator();

            @Override
            public boolean hasNext() {
            	//if current bucket has no more elements, it moves to the next bucket
                while (!bucketIterator.hasNext() && bucketIndex < table.length - 1) {
                    bucketIndex++;
                    bucketIterator = table[bucketIndex].iterator();
                }
                
                //returns hasNext on the next bucket since we moved over to that
                return bucketIterator.hasNext();
            }

            @Override
            public Entry next() {
            	//check if theres more elements
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in the hash map");
                }
                return bucketIterator.next();
            }
        };
    }

    
}
