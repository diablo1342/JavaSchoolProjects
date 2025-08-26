package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;


import  myDeque.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class StudentTests {
	
	@Test
    public void testAddFront() {
	   	CircularDeque<Integer> deque = new CircularDeque<>(5);
	    deque.addFront(10); 
	    deque.addFront(20); 
	    deque.addFront(30); 
	    System.out.println(deque.toString());
	    assertTrue(deque.toString().equals("CircularDeque [deque=[null, null, 30, 20, 10], "
	    		+ "front=2, rear=0, size=3, capacity=5]"));
	}
	
	@Test
    public void testAddRear() {
        CircularDeque<Integer> deque = new CircularDeque<>(5);
        deque.addRear(10); 
        deque.addRear(20); 
        deque.addRear(30); 
        System.out.println(deque.toString());
        assertTrue(deque.toString().equals("CircularDeque [deque=[10, 20, 30, null, null], "
        		+ "front=0, rear=3, size=3, capacity=5]"));
    }
	
	@Test
	public void testIllegalState() {
		CircularDeque<Integer> deque = new CircularDeque<>(1);
		
		try {
            deque.removeFront();
            fail("Expected IllegalStateException when removing from empty deque");
        } catch (IllegalStateException e) {
            assertEquals("Deque is empty.", e.getMessage());
        }
		
		try {
            deque.removeRear();
            fail("Expected IllegalStateException when removing from empty deque");
        } catch (IllegalStateException e) {
            assertEquals("Deque is empty.", e.getMessage());
        }
		
		deque.addFront(5);
		
		try {
            deque.addFront(2);
            fail("Expected IllegalStateException when removing from empty deque");
        } catch (IllegalStateException e) {
            assertEquals("Deque is full.", e.getMessage());
        }
		
		try {
            deque.addRear(2);
            fail("Expected IllegalStateException when removing from empty deque");
        } catch (IllegalStateException e) {
            assertEquals("Deque is full.", e.getMessage());
        }
	}
	
	@Test
	public void testClear() {
		CircularDeque<Integer> deque = new CircularDeque<>(1);
	    deque.addFront(10); 
	    deque.clear();
	    System.out.println(deque.toString());
	    assertTrue(deque.toString().equals("CircularDeque [deque=[10], front=0, rear=0, size=0, capacity=1]"));
	    
	}
	
	@Test
	public void testSlidingWindowMaxString() {
		String[] words = {"and", "banana", "cat", "dog", "eat"};
        int k = 3;
    
        String[] expected = {"cat", "dog", "eat"};

        String[] result = Util.slidingWindowMax(words, k); 
        
        for(int i = 0; i<3; i++) {
        	System.out.println(result[i]);
        }
        
        assertArrayEquals(expected, result);
        
        
	}
	
	@Test
	public void testIsPalindrome() {
		String s = "racecar";
		CircularDeque<Character> deque = new CircularDeque<>(s.length());
		
		for(int i = 0; i<s.length(); i++) {
			//checks if the character is a letter or digit, as this method has to ignore non letters or digits
			if(Character.isLetterOrDigit(s.charAt(i))) {
				//adds the character to the deque if it is valid in order to model the string with a deque
				deque.addRear(Character.toLowerCase(s.charAt(i)));
			}
			
		}
		
		System.out.println(deque.toString());
	}
}