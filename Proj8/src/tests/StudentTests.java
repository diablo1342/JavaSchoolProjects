package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import systemImp.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class StudentTests {
	
	//this test is to test heapify max heap
	@Test
    public void testHeapifyMaxHeap() {
		StringBuffer log = new StringBuffer(); 

		Integer[] arr = new Integer[11];
		arr[0] = null;  
		arr[1] = 50;
		arr[2] = 20;
		arr[3] = 60;
		arr[4] = 10;
		arr[5] = 30;
		arr[6] = 70;
		arr[7] = 80;
		arr[8] = 5;
		arr[9] = 40;
		arr[10] = 90;

		
		HeapUtils.heapify(arr, false, log);
		
		assertTrue(TestsSupport.isCorrect("studentTestTestHeapifyMaxHeap.txt", log.toString()));
				
	}
	
	//this test is to test heapify min heap
	@Test
    public void testHeapifyMinHeap() {
		StringBuffer log = new StringBuffer(); 

		Integer[] arr = new Integer[11];
		arr[0] = null;  
		arr[1] = 50;
		arr[2] = 20;
		arr[3] = 60;
		arr[4] = 10;
		arr[5] = 30;
		arr[6] = 70;
		arr[7] = 80;
		arr[8] = 5;
		arr[9] = 40;
		arr[10] = 90;

		
		HeapUtils.heapify(arr, true, log);
		
		assertTrue(TestsSupport.isCorrect("studentTestTestHeapifyMinHeap.txt", log.toString()));
				
	}
	
	
	//this test is to test heapsort
	@Test
	public void testHeapSort() {
		StringBuffer log = new StringBuffer(); 

		Integer[] arr = new Integer[11];
		arr[0] = null;  
		arr[1] = 50;
		arr[2] = 20;
		arr[3] = 60;
		arr[4] = 10;
		arr[5] = 30;
		arr[6] = 70;
		arr[7] = 80;
		arr[8] = 5;
		arr[9] = 40;
		arr[10] = 90;
		
		HeapUtils.heapSort(arr, log);
		assertTrue(TestsSupport.isCorrect("studentTestTestHeapSort.txt", log.toString()));
	}
	
	//this test is to test peak
	@Test
	public void testPeek() {
		 	StringBuffer log = new StringBuffer(); // For capturing swaps

	        // Create the heap array
	        Integer[] arr = new Integer[11];
	        arr[0] = null; // index 0 must be null
	        arr[1] = 22;
	        arr[2] = 5;
	        arr[3] = 17;
	        arr[4] = 3;
	        arr[5] = 14;
	        arr[6] = 9;
	        arr[7] = 30;
	        arr[8] = 8;
	        arr[9] = 12;
	        arr[10] = 7;

	        // Create the PriorityQueue (min-heap)
	        PriorityQueue<Integer> pq = new PriorityQueue<>(arr, true);
	        assertEquals(pq.peek(), (Integer) 3);
	}
	
	//this test is to test remove
	@Test
	public void testRemove() {
		 	StringBuffer log = new StringBuffer(); // For capturing swaps

	        // Create the heap array
	        Integer[] arr = new Integer[11];
	        arr[0] = null; // index 0 must be null
	        arr[1] = 22;
	        arr[2] = 5;
	        arr[3] = 17;
	        arr[4] = 3;
	        arr[5] = 14;
	        arr[6] = 9;
	        arr[7] = 30;
	        arr[8] = 8;
	        arr[9] = 12;
	        arr[10] = 7;

	        // Create the PriorityQueue (min-heap)
	        PriorityQueue<Integer> pq = new PriorityQueue<>(arr, true);
	        pq.remove(log);
	        assertEquals(pq.peek(), (Integer)5);
	}
	
	//this test is to test insert
	@Test
	public void testInsert() {
		StringBuffer log = new StringBuffer(); // For capturing swaps

        // Create the heap array
        Integer[] arr = new Integer[11];
        arr[0] = null; // index 0 must be null
        arr[1] = 22;
        arr[2] = 5;
        arr[3] = 17;
        arr[4] = 3;
        arr[5] = 14;
        arr[6] = 9;
        arr[7] = 30;
        arr[8] = 8;
        arr[9] = 12;
        arr[10] = 7;

        // Create the PriorityQueue (min-heap)
        PriorityQueue<Integer> pq = new PriorityQueue<>(arr, true);
        pq.remove(log);
        pq.insert(2, log);
        assertEquals(pq.peek(), (Integer) 2);
	}
	
	@Test
	public void testLog() {
		StringBuffer log = new StringBuffer(); // For capturing swaps

        // Create the heap array
        Integer[] arr = new Integer[11];
        arr[0] = null; // index 0 must be null
        arr[1] = 22;
        arr[2] = 5;
        arr[3] = 17;
        arr[4] = 3;
        arr[5] = 14;
        arr[6] = 9;
        arr[7] = 30;
        arr[8] = 8;
        arr[9] = 12;
        arr[10] = 7;

        // Create the PriorityQueue (min-heap)
        PriorityQueue<Integer> pq = new PriorityQueue<>(arr, true);
        pq.remove(log);
        pq.insert(2, log);
        assertTrue(TestsSupport.isCorrect("studentTestTestLog.txt", log.toString()));

	}
	
}