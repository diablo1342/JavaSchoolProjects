package systemImp;

/**
 * A class that implements a Queue using your custom Doubly Linked List.
 * All operations must be O(1).
 */
public class Queue<T extends Comparable<T>> {
    private DoublyLinkedList<T> list; // The doubly linked list that backs the queue.
    
    public Queue() {
    	list = new DoublyLinkedList<T>();
    }
    
    
    public void enqueue(T data) {
    	//Queue is fifo, so we add to the back
    	list.addLast(data);
    }
    
    public T dequeue() {
    	//check edge cases
    	if(list.getSize() == 0) {
    		throw new IllegalStateException("list is empty.");
    	}
    	
    	//Queue is fifo, so we remove from the front
    	T data = list.getFirst();
    	list.removeFirst();
    	return data;
    }
    
    public T peek() {
    	//check edge cases
    	if(list.getSize() == 0) {
    		throw new IllegalStateException("list is empty.");
    	}
    	
    	//Queue is fifo, so we peek from the front
    	return list.getFirst();
    }
    
    public boolean isEmpty() {
    	//Queue is empty if size is 0
    	return list.getSize() == 0;
    }
    
    public int size() {
    	return list.getSize();
    }
   
}

