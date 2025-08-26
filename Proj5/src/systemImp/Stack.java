package systemImp;

/**
 * A class that implements a Stack using your custom Doubly Linked List.  All operations must be O(1).
 */
public class Stack<T extends Comparable<T>> {
    private DoublyLinkedList<T> list; // The doubly linked list that will back the stack.

    public Stack() {
    	list = new DoublyLinkedList<T>();
    }
    
    public void push(T data) {
    	//push corresponds to addfirst since stack is lifo
    	list.addFirst(data);
    }
    
    public T pop() {
    	//check edge cases
    	if(list.getSize() == 0) {
    		throw new IllegalStateException("list is empty.");
    	}
    	
    	//pop corresponds to removefirst since stack is lifo
    	T data = list.getFirst();
    	list.removeFirst();
    	return data;
    }
    
    public T peek() {
    	//check edge cases
    	if(list.getSize() == 0) {
    		throw new IllegalStateException("list is empty.");
    	}
    	
    	//peek corresponds to getfirst since stack is lifo
    	T data = list.getFirst();
    	return data;
    }
    
    public boolean isEmpty() {
    	return list.getSize() == 0;
    }
    
    public int size() {
    	return list.getSize();
    }
 
}

