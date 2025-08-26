package systemImp;

/**
 * A simple implementation of a HashSet using open addressing with linear probing.<br>
 * This HashSet works specifically with Strings.<br>
 * 
 * It enforces the following constraints:<br>
 * - Strings must not be null, no longer than 5 characters, and only use a to z, otherwise an exception is thrown.<br>
 * - A custom hash code function uses hash codes based on the prime number 19.<br>
 * - The initial size of the table is 2, and it dynamically resizes to the next prime when necessary.<br>
 * 
 * <p>
 * The set uses open addressing and linear probing for collision resolution.<br>
 * When the load factor is >= a customized threshold (default is 0.75), the table is rehashed to a larger prime size.</p>
 * 
 * <p>
 * This class ensures that the set operates efficiently and does not allow duplicate entries.
 * </p>
 */
public class MyHashSet {

    private static final String DELETED = "DELETED";  // Sentinel for deleted entries
    private static final String EMPTY = null;         // Sentinel for empty entries
    
    // Array of primes for resizing the hash table
    // These primes are chosen to ensure that the table size grows by a "double prime" strategy.
    private static final int[] PRIMES = {2, 5, 11, 23, 47, 97, 197, 397, 797, 1597};
    
    private String[] table;      // The actual table where the strings are stored
    private int size;            // The current number of elements in the set
    private int primeIndex;      // Index of the current prime number used for table size
    private double loadFactorThreshold;  // The threshold at which to rehash the table
    
    public MyHashSet() {
    	table = new String[2];
    	primeIndex = 0;
    	loadFactorThreshold = 0.75;
    	size = 0;
    }
    
    public void setLoadFactorThreshold(double threshold) {
    	if(!(threshold == 0.45 || threshold == 0.60 || threshold == 0.75 || threshold == 0.85)) {
    		throw new IllegalArgumentException("Invalid value");
    	}
    	loadFactorThreshold = threshold;
    }
    
    public double getLoadFactor() {
    	return size / (double) table.length;
    }
    
    private boolean isValidKey(String key) {
    	if(key == null || key.length() > 5) {
    		return false;
    	}
    	
    	for(int i = 0; i<key.length(); i++) {
    		char c = key.charAt(i);

            if (c < 'a' || c > 'z') {
                return false;
            }
    	}
    	return true;
    	
    	
    }
    
    public int myHashCode(String key) {
    	if (!isValidKey(key)) {
	        throw new IllegalArgumentException("Key must be non-null and ≤ 5 characters");
	    }
	    
	    
	    int hash = 0;
	    
	    //summate the hash based on the formula given
	    for (int i = 0; i < key.length(); i++) {
	        char c = key.charAt(i);
	        hash = (hash * 19) + (int)c;
	    }
	    
	    return hash;
    }
    
    private int hash(String key) {
    	return myHashCode(key) % table.length;
    }
    
    public void add(String key) {
    	//check if key is valid
        if (!isValidKey(key)) {
            throw new IllegalArgumentException("Invalid key");
        }
        
        int index = hash(key);
        int firstDeleted = -1;
        
        for (int i = 0; i < table.length; i++) {
        	//current is the current table index
            int current = (index + i) % table.length;
            String entry = table[current];
            
            
            
            if (entry == EMPTY) {
            	//if entry is null, and we've deleted something, set table[firstDeleted] to key to replace what we've deleted
                if (firstDeleted != -1) {
                    table[firstDeleted] = key;
                } else {
                	//otherwise, insert the key normally
                    table[current] = key;
                }
                size++;
                
                //rehash if needed
                if (getLoadFactor() >= loadFactorThreshold) {
                    rehash();
                }
                return;
            } else if (entry.equals(DELETED)) { //if the entry.equals(DELETED), that means we know something has been deleted and we can update firstDeleted
                if (firstDeleted == -1) {
                    firstDeleted = current;
                }
            } else if (entry.equals(key)) { //if entry.equals(key), we end the function as key has already been added
                return;
            }
        }
        
        //if something has been deleted, update size and rehash if needed
        if (firstDeleted != -1) {
            table[firstDeleted] = key;
            size++;
            
            if (getLoadFactor() >= loadFactorThreshold) {
                rehash();
            }
        }
    }
    
    public void remove(String key) {
        if (!isValidKey(key)) {
            throw new IllegalArgumentException("Invalid key");
        }
        
        int index = hash(key);
        
        for (int i = 0; i < table.length; i++) {
            int current = (index + i) % table.length;
            String entry = table[current];
            //if entry is empty, we stop the function because that means there are no more entries
            if (entry == EMPTY) {
                return;
            } else if (entry.equals(key)) { //otherwise, we check if it equals key and if it does, we remove it
                table[current] = DELETED;
                size--;
                return;
            }
        }
    }
    
    private void rehash() {
    	//check if primeIndex is at the last valid prime index
        if (primeIndex >= PRIMES.length - 1) {
            throw new IllegalStateException("Cannot resize further");
        }
        
        //update prime index
        primeIndex++;
        
        //create a variable to store the old table
        String[] temp = table;
        //resize table
        table = new String[PRIMES[primeIndex]];
        size = 0;
        
        //add entries from the old table to the new table
        for (String entry : temp) {
            if (entry != null && !entry.equals(DELETED)) {
                add(entry);
            }
        }
    }
    
    public boolean contains(String key) {
    	//check if key is valid
        if (!isValidKey(key)) {
            throw new IllegalArgumentException("Invalid key");
        }
        
        
        int index = hash(key);
        
        for (int i = 0; i < table.length; i++) {
            int current = (index + i) % table.length;
            String entry = table[current];
            //if entry is empty, that means we've reached the end so we return false
            if (entry == EMPTY) {
                return false;
            } else if (entry.equals(key)) { //if entry equals key, return true
                return true;
            }
        }
        
        return false;
    }
    
    public boolean containsAll(String[] keys) {
        if (keys == null) {
            return false;
        }
        
        for (String key : keys) {
        	//if a key isnt valid or isnt in the set, return false
            if (!isValidKey(key) || !contains(key)) {
                return false;
            }
        }
        
        return true;
    }
    
    public int size() {
        return size;
    }
    
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < table.length; i++) {
            result += "Index " + i + ": ";
            if (table[i] == EMPTY) {
                result += "null";
            } else if (table[i].equals(DELETED)) {
                result += "DELETED";
            } else {
                result += table[i];
            }
            result += "\n";
        }
        return result;
    }
}

