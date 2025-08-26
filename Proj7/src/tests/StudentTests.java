package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import systemImp.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class StudentTests {
	
	//check if add and toString work
	@Test
	public void testAddAndToString() {
		
		//add items to tree
	    BinarySearchTree<Integer> bst = new BinarySearchTree<>();
	    bst.add(40);
	    bst.add(20);
	    bst.add(60);
	    
	    
	    //check if items were added and if toString is correct
	    assertTrue(TestsSupport.isCorrect("studentTestAddToStringRemove.txt", bst.toString()));
	    
	}
	
	//test to check if remove works
	@Test
	public void testRemove() {

	    BinarySearchTree<Integer> bst = new BinarySearchTree<>();
	    bst.add(40);
	    bst.add(20);
	    bst.add(60);
	    bst.add(10);
	    bst.add(30);
	    bst.add(50);
	    
	    
	    
	    //remove elements from the list using both preferRight and preferLEft
	    bst.remove(30, false);
	    bst.remove(50, true);
	    bst.remove(10, false);
	    
	    //if the items were removed, toString should be same as the previous test
	    assertTrue(TestsSupport.isCorrect("studentTestAddToStringRemove.txt", bst.toString()));
	    
	    
	}
	
	//check if both inOrder functions work
	@Test
	public void testInOrder() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
	    bst.add(40);
	    bst.add(20);
	    bst.add(60);
	    bst.add(10);
	    bst.add(30);
	    bst.add(50);
	    
	    //in order should be equal to this string
	    assertEquals("10 20 30 40 50 60 ", bst.inOrder());
	    assertEquals("10 20 30 40 50 60 ", bst.inorderNonRecursive());
	    
	}
	
	//check if both postOrder functions work
	@Test
	public void testPostOrder() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
	    bst.add(40);
	    bst.add(20);
	    bst.add(60);
	    bst.add(10);
	    bst.add(30);
	    bst.add(50);
	    
	    //post order should be equal to this
	    assertEquals("10 30 20 50 60 40 ", bst.postOrder());
	    assertEquals("10 30 20 50 60 40 ", bst.postorderNonRecursive());
	}
	
	//check if both preOrder functions work
	@Test
	public void testpreOrder() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
	    bst.add(40);
	    bst.add(20);
	    bst.add(60);
	    bst.add(10);
	    bst.add(30);
	    bst.add(50);
	    
	    //pre order should be equal to this

	    assertEquals("40 20 10 30 60 50 ", bst.preOrder());
	    assertEquals("40 20 10 30 60 50 ", bst.preorderNonRecursive());
	}
	
	//check if min works
	@Test
	public void testMin() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
	    bst.add(40);
	    bst.add(20);
	    bst.add(60);
	    bst.add(10);
	    bst.add(30);
	    bst.add(50);
	    
	    //min is 10, check if it is
	    assertTrue(bst.min() == 10);
	}
	
	//check if max works
	@Test
	public void testMax() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
	    bst.add(40);
	    bst.add(20);
	    bst.add(60);
	    bst.add(10);
	    bst.add(30);
	    bst.add(50);
	    
	    //max is 60, check if it is
	    assertTrue(bst.max() == 60);
	}
	
	//test to check isPerfect
	@Test
	public void testIsPerfect() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
	    bst.add(40);
	    bst.add(20);
	    bst.add(60);
	    bst.add(30);
	    bst.add(10);
	    bst.add(70);
	    bst.add(50);
	    
	    //if isPerfect returns true, it works
	    assertTrue(bst.isPerfect());
	    
	    bst.remove(70, false);
	    //after removing, isPerfect should be false
	    assertTrue(!bst.isPerfect());
	}
	
	//test to check isFull
	@Test
	public void testIsFull() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
	    bst.add(40);
	    bst.add(20);
	    bst.add(60);
	    bst.add(70);
	    bst.add(50);
	    
	  //if isFull returns true, it works
	    assertTrue(bst.isFull());
	    
	    bst.remove(70, false);
	    //after removing, isFull should be false
	    assertTrue(!bst.isFull());
	}
	
	//test to check isComplete
	@Test
	public void testIsComplete() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
	    bst.add(40);
	    bst.add(20);
	    bst.add(60);
	    bst.add(30);
	    bst.add(50);
	    bst.add(10);
	    
	    //if isComplete returns true, it works
	    assertTrue(bst.isComplete());
	    
	    bst.remove(10, false);
	    //after removing, isComplete should be false
	    assertTrue(!bst.isComplete());
	}
}