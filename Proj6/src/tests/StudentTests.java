package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import systemImp.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class StudentTests {
	
	@Test
	public void testPutAndHashMapToString() {
	    // Create a ChainedHashMap to test
	    ChainedHashMap map = new ChainedHashMap();
	    
	    map.put(1357, "Apple");
	    map.put(2222, "Banana");
	    map.put(3923, "Cherry");
	    map.put(4340, "Date");

	    
	    String result = map.toString();
	    System.out.print(result);
	    assertEquals(result, "0  -> (1357, Apple) (2222, Banana) (4340, Date) " + "\n" + "1  -> (3923, Cherry) " + "\n");
	}
	
	 @Test
	 public void testSizeAndTableLength() {
		 ChainedHashMap map = new ChainedHashMap();
		    
		 map.put(1357, "Apple");
		 map.put(2222, "Banana");
		 map.put(3923, "Cherry");
		 map.put(4340, "Date");
		 
		 assertEquals("Map should have 4 elements", 4, map.getSize());
		 assertEquals("Table length should be 2", 2, map.getTableLength());
	 }
	 
	 @Test
	 public void testContainsKeyAndRemove() {
		 ChainedHashMap map = new ChainedHashMap();
		    
		 map.put(1357, "Apple");
		 map.put(2222, "Banana");
		 map.put(3923, "Cherry");
		 map.put(4340, "Date");
		 
		 assertEquals("Map does contain 1357", true, map.containsKey(1357));
		 
		 map.remove(1357);
		 
		 assertEquals("Map does not contain 1357", false, map.containsKey(1357));
	 }
	 
	 @Test
	 public void testGetAndGetValues() {
		 ChainedHashMap map = new ChainedHashMap();
		    
		 map.put(1357, "Apple");
		 map.put(1356, "Apple");
		 map.put(1355, "Apple");
		 map.put(2222, "Banana");
		 map.put(3923, "Cherry");
		 map.put(4340, "Date");
		 
		 assertEquals("Should return Apple", "Apple", map.get(1357));
		 
		 ArrayList<Integer> u = new ArrayList<Integer>();
		 u.add(1357);
		 u.add(1355);
		 u.add(1356);
		 
		 assertEquals("Should return same as u", u, map.getValues("Apple"));
		 
		 
	 }
	 
	 @Test
	 public void testIterator() {
	        ChainedHashMap map = new ChainedHashMap();
	        Iterator<ChainedHashMap.Entry> iterEmpty = map.iterator();
	        map.put(1000, "Apple");
	        map.put(2000, "Banana");
	        map.put(3000, "Cherry");
	        map.put(4000, "Date");


	        Iterator<ChainedHashMap.Entry> iter = map.iterator();
	        ArrayList<Integer> u = new ArrayList<Integer>();
	        
	        while (iter.hasNext()) {
	            ChainedHashMap.Entry entry = iter.next();
	            u.add(entry.getKey());
	        }
	        
	        ArrayList<Integer> answer = new ArrayList<Integer>();
	        
	        answer.add(3000);
	        answer.add(4000);
	        answer.add(1000);
	        answer.add(2000);
	        
	        assertEquals("u should be an arrayList of all keys", answer, u );
	        
	       
	 }
	 
	 @Test
	 public void testAddAndToString() {
	        
	        MyHashSet set = new MyHashSet();

	        
	        set.add("apple");
	        set.add("banjo");
	        set.add("cat");
	        set.add("date");
	        assertEquals(set.toString(), "Index 0: null\n"
	        		+ "Index 1: cat\n"
	        		+ "Index 2: null\n"
	        		+ "Index 3: null\n"
	        		+ "Index 4: null\n"
	        		+ "Index 5: date\n"
	        		+ "Index 6: null\n"
	        		+ "Index 7: null\n"
	        		+ "Index 8: apple\n"
	        		+ "Index 9: banjo\n"
	        		+ "Index 10: null\n"
	        		+ "");


	 }
	 
	
	@Test
	 public void testSetAndGetLoadFactor() {
		 MyHashSet set = new MyHashSet();

	        
	     set.add("apple");
	     set.add("banjo");
	     set.add("cat");
	     set.add("date");
	     
	     set.setLoadFactorThreshold(0.45);
	     double a = 0.363636363636365;
	     double b = set.getLoadFactor();
	     double c = a-b;
	     
	     if (Math.abs(c) <= 0.00001) {
	    	 assertTrue(true);
	     }else {
	    	 assertFalse(true);
	     }
	        
	        
	 }
	
	@Test
	public void testMyHashCode() {
		MyHashSet set = new MyHashSet();

        
	     set.add("apple");
	     set.add("banjo");
	     set.add("cat");
	     set.add("date");
	     
	     assertEquals(set.myHashCode("apple"), 13451930);
	}
	
	@Test
	public void testHashSetRemoveAndSize() {
		MyHashSet set = new MyHashSet();

        
	     set.add("apple");
	     set.add("banjo");
	     set.add("cat");
	     set.add("date");
	     
	     assertEquals(set.size(), 4);
	     set.remove("date");
	     assertEquals(set.size(), 3);
	}
	
	@Test
	public void testHashSetContainsAndContainsAll() {
		MyHashSet set = new MyHashSet();
		String[] words = {"apple", "banjo"};
        
	     set.add("apple"); 
	     set.add("banjo");
	     set.add("cat");
	     set.add("date");
	     
	     assertTrue(set.contains("cat"));
	     assertTrue(set.containsAll(words));
	}
	 
	 
}