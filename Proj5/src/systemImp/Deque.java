package systemImp;

/**
 * A class that implements a Deque (double-ended queue) using a custom Doubly Linked List.
 * All operations (insertion and removal from both ends) must be O(1).
 */
public class Deque<T extends Comparable<T>> {
    private DoublyLinkedList<T> list; // The doubly linked list backing the deque.
    
    public Deque() {
    	list = new DoublyLinkedList<T>();
    }
    
    
    public void addFirst(T data) {
    	//addFirst here corresponds to addfirst in the doublylinkedlist functions
    	list.addFirst(data);
    }
    
    public void addLast(T data) {
    	//addLast here corresponds to addlast in the doublylinkedlist functions
    	list.addLast(data);
    }
    
    public T removeFirst() {
    	//check edge cases
    	if(list.getSize() == 0) {
    		throw new IllegalStateException("list is empty.");
    	}
    	
    	//removefirst corresponds to removefirst in doublylinkedlist functions
    	T data = list.getFirst();
    	list.removeFirst();
    	return data;
    }
    
    public T removeLast() {
    	//check edge cases
    	if(list.getSize() == 0) {
    		throw new IllegalStateException("list is empty.");
    	}
    	
    	//remove last corresponds to removelast in doublylinkedlist functions
    	T data = list.getLast();
    	list.removeLast();
    	return data;
    }
    
    public T peekFirst() {
    	//check edge cases
    	if(list.getSize() == 0) {
    		throw new IllegalStateException("list is empty.");
    	}
    	
    	//peekfirst corresponds to getfirst in doublylinkedlist functions
    	return list.getFirst();
    }
    
    public T peekLast() {
    	//check edge cases
    	if(list.getSize() == 0) {
    		throw new IllegalStateException("list is empty.");
    	}
    	
    	//peeklast correpsonds to getlast in doublylinkedlist functions
    	return list.getLast();
    }
    
    public boolean isEmpty() {
    	return list.getSize() == 0;
    }
    
    public int size() {
    	return list.getSize();
    }
}