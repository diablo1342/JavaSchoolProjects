package tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import systemImp.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class StudentTests {
	
	@Test
	public void testMultipleSearches() {
    	
    	String[] words = new String[]{"mango", "apple", "quince", "banana", "strawberry",
                 "date", "kiwi", "peach", "fig", "raspberry",
                 "cherry", "nectarine", "lemon", "grape", "orange"};

    	
        StringBuilder log = new StringBuilder();
        
        int index1 = SearchAndSortUtil.bidirectionalLinearSearch(words, "peach", 0, words.length - 1, log);
        assertEquals("Searching for 'peach' should return index 7", 7, index1);
        int index2 = SearchAndSortUtil.bidirectionalLinearSearch(words, "mango", 0, words.length - 1, log);
        assertEquals("Searching for 'peach' should return index 0", 0, index2);
        int index3 = SearchAndSortUtil.bidirectionalLinearSearch(words, "fig", 0, words.length - 1, log);
        assertEquals("Searching for 'peach' should return index 8", 8, index3);
 
        
    }
	
	@Test
    public void testStringBidirectionalBubbleSort() {
		String[] words = new String[]{"mango", "apple", "quince"};
        

        // Perform sorting
        String result = SearchAndSortUtil.bidirectionalBubbleSort(words);
        result+= Arrays.toString(words);
        
        String[] answer = new String[] {"apple", "mango", "quince"};

        // Verify expected output
        assertArrayEquals(answer, words);
    }
	
	 @Test
	 public void testRecursiveBidirectionalSelectionSortWithInt() {
	        Integer[] arr = new Integer[] {5,6,7,8,88,99,65,2};
	        
	        StringBuilder log = new StringBuilder();
	        SearchAndSortUtil.recursiveBidirectionalSelectionSort(arr, 0, arr.length-1, log);
	        
	        Integer[] answer = {2,5,6,7,8,65,88,99};
	        assertArrayEquals(answer, arr);
	 }
	 
	 @Test
	 public void testInsertionPoint() {
		 PriorityList<Integer> list1 = new PriorityList<>(10, false, null);
		 list1.add(5);
         list1.add(15);
         list1.add(25);
         list1.add(35);
         list1.add(45);
		 list1.add(65);
         list1.add(75);
         list1.add(85);
         list1.add(95);
         
         int num = list1.binarySearchInsertionPoint(55);
         assertEquals(5, num);
	 }
	 
	 @Test
	 public void testRemove() {
		 PriorityList<Integer> list1 = new PriorityList<>(10, false, null);
		 
		 boolean answer = list1.remove(5);
		 assertEquals(false, answer);
		 
		 list1.add(5);
		 boolean answer2 = list1.remove(5);
		 assertEquals(true, answer2);
	 }
}