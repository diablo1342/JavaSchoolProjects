package tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import systemImp.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class StudentTests {
	 
	 @Test
	 public void testEnhancedForLoop() {
		 //this test is for testing the enhanced for loop
		 DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		 
		 String result = "";
		 list.addLast(10);
		 list.addLast(5);
		 list.addLast(3);
		 list.addLast(7);
		 list.addLast(8);
		 list.addLast(9);
		 
		 for(Integer i : list) {
			 result += i.toString();
		 }
		 
		 System.out.print(result);
		 assertEquals("1053789", result);
	 }
	 @Test
	 public void testStack() {
		 //this test is for testing stack operations
		 Stack<Integer> list = new Stack<Integer>();
		 String result = "";
		 
		 list.push(5);
		 result += list.peek();
		 
		 list.push(3);
		 result += list.peek();
		 list.push(4);
		 
		 result += list.peek();
		 result += list.pop();
		 result += list.peek();
		 
		 System.out.print(result);
		 assertEquals("53443", result);
		 
		 
	 }
	 
	 @Test
	 public void testQueue() {
		 //this test is for testing queue operations
		 Queue<Integer> list = new Queue<Integer>();
		 
		 String result = "";
		 list.enqueue(2);
		 result += list.peek();
		 
		 list.enqueue(3);
		 list.dequeue();
		 result += list.peek();
		 
		 list.enqueue(4);
		 result += list.peek();
		 
		 result += list.dequeue();
		 result += list.peek();
		 
		 System.out.print(result);
		 assertEquals("23334", result);
	 }
	 
	 @Test
	 public void testDeque() {
		 //this test is for testing deque operations
		 Deque<Integer> list = new Deque<Integer>();
		 
		 String result = "";
		 list.addFirst(2);
		 list.addLast(5);
		 list.addFirst(3);
		 list.addLast(7);
		 list.removeFirst();
		 list.removeLast();
		 
		 result += list.peekFirst();
		 result += list.peekLast();
		 result += list.removeFirst();
		 result += list.removeLast();
		 result += list.size();
		 
		 
		 System.out.print(result);
		 assertEquals(result, "25250");
		 
	 }
	 
	 @Test
	 public void testLinkedListBasicOperations() {
		 //this test is for testing the base operatons for linked list
		 DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		 
		 
		 list.addFirst(5);
		 list.addLast(6);
		 list.addFirst(3);
		 list.addLast(10);
		 list.addLast(5);
		 list.addLast(3);
		 list.addLast(7);
		 list.addLast(8);
		 list.addLast(9);
		 
		 list.removeFirst();
		 list.removeLast();
		 
		 int sizeResult = list.getSize();
		 int getFirstResult = list.getFirst();
		 int getLastResult = list.getLast();
		 String result = list.toString();
		 System.out.print(result);
		 
		 assertEquals(7, sizeResult);
		 assertEquals(5, getFirstResult);
		 assertEquals(8, getLastResult);
		 assertEquals("[5, 6, 10, 5, 3, 7, 8]", result);
		 
	 }
	 
	 @Test
	 public void testRemoveAllInRange() {
		 //this test is for testing removeAllInRange()
		 DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		 
		 list.addFirst("a");
		 list.addFirst("b");
		 list.addFirst("c");
		 list.addFirst("d");
		 list.addFirst("e");
		 list.addFirst("f");
		 list.addFirst("g");
		 list.addFirst("h");
		 list.addFirst("i");
		 
		 list.removeAllInRange("d", "h");
		 System.out.print(list.toString());
		 
		 assertEquals(list.toString(), "[i, c, b, a]");
	 }
	 
	 @Test
	 public void testIteratorNextAndHasNext() {
		 //this test is for testing hasNext and next functions of the iterator function
		 DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		 
		 String result = "";
		 list.addLast(10);
		 list.addLast(5);
		 list.addLast(3);
		 list.addLast(7);
		 list.addLast(8);
		 list.addLast(9);
		 
		 Iterator<Integer> iterator = list.iterator();
		 
		 while(iterator.hasNext()) {
			 result += iterator.next().toString();
		 }
		 
		 System.out.print(result);
		 assertEquals(result, "1053789");
	 }
	 
	 @Test
	 public void testIteratorRemove() {
		 //this test is for testing the remove function of the iterator
		 DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		 
		 
		 list.addLast(10);
		 list.addLast(5);
		 list.addLast(3);
		 list.addLast(7);
		 list.addLast(8);
		 list.addLast(9);
		 
		 Iterator<Integer> iterator = list.iterator();
		 
		 iterator.next();
		 iterator.remove();
		 iterator.next();
		 iterator.remove();
		 iterator.next();
		 iterator.remove();
		 
		 
		 System.out.print(list.toString());
		 assertEquals(list.toString(), "[7, 8, 9]");
	 }
	 
	 @Test
	 public void testInsertionSort() {
		 //this test is for testing the insertionSort function
		 DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		 
		 list.addLast(10);
		 list.addLast(5);
		 list.addLast(3);
		 list.addLast(7);
		 list.addLast(8);
		 list.addLast(9);
		 
		 assertEquals(list.insertionSort(), "10 | 5 3 7 8 9\n"
		 		+ "5 10 | 3 7 8 9\n"
		 		+ "3 5 10 | 7 8 9\n"
		 		+ "3 5 7 10 | 8 9\n"
		 		+ "3 5 7 8 10 | 9\n"
		 		+ "3 5 7 8 9 10 |\n");
	 }
	 
	 @Test
	 public void testExceptions() {
		 //this test is for testing if exceptions are being thrown correctly
		 DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		 
		 try {
		        list.removeLast();
		        fail("NoSuchElementException wasn't thrown");
		    } catch (NoSuchElementException e) {
		        assertEquals("List is empty!", e.getMessage());
		    }
		 
		 try {
		        list.removeFirst();
		        fail("NoSuchElementException wasn't thrown");
		    } catch (NoSuchElementException e) {
		        assertEquals("List is empty!", e.getMessage());
		    }
		 
		 try {
		        list.getFirst();
		        fail("NoSuchElementException wasn't thrown");
		    } catch (NoSuchElementException e) {
		        assertEquals("List is empty!", e.getMessage());
		    }
		 
		 try {
		        list.getLast();
		        fail("NoSuchElementException wasn't thrown");
		    } catch (NoSuchElementException e) {
		        assertEquals("List is empty!", e.getMessage());
		    }
		 
		 try {
		        list.addFirst(null);
		        fail("NullPointerException wasn't thrown");
		    } catch (NullPointerException e) {
		        assertEquals("Element cannot be null", e.getMessage());
		    }
		 
		 try {
		        list.addLast(null);
		        fail("NullPointerException wasn't thrown");
		    } catch (NullPointerException e) {
		        assertEquals("Element cannot be null", e.getMessage());
		    }
		 
		 try {
		        list.removeAllInRange(5,7);
		        fail("NoSuchElementException wasn't thrown");
		    } catch (NoSuchElementException e) {
		        assertTrue(true);
		    }
		 
		 
		 Iterator<Integer> iterator = list.iterator();
		 
		 try {
		        iterator.next();
		        fail("NoSuchElementException wasn't thrown");
		    } catch (NoSuchElementException e) {
		        assertTrue(true);
		    }
		 
		 try {
		        iterator.remove();
		        fail("IllegalStateException wasn't thrown");
		    } catch (IllegalStateException e) {
		        assertTrue(true);
		    }
		 
		 
	 }
	 
	 
}