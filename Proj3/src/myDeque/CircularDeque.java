package myDeque;

public class CircularDeque<T> {
    private T[] deque; 
    private int front;
    private int rear;
    private int size;
    private int capacity;

    
    @SuppressWarnings("unchecked")
    public CircularDeque(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0.");
        }
        this.capacity = capacity;
        this.deque = (T[]) new Object[capacity]; 
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    
    public void addFront(T element) {
        if (isFull()) {
            throw new IllegalStateException("Deque is full.");
        }
        //I shift front before setting deque[front] because that is the best way I found after running tests.
        //if front is > 0, this moves front to the left. If front is 0, this moves front to the last index which is capacity - 1.
        if(front == 0) {
        	front = capacity-1;
        }else {
        	front--;
        }
        
        
        deque[front] = element;
        size++;
    }

    
    public void addRear(T element) {
        if (isFull()) {
            throw new IllegalStateException("Deque is full.");
        }
        
        deque[rear] = element;
        
        /*I shift rear after setting deque[rear] because that is the best way I found after running tests.
        Same logic as front, but the opposite. if rear is capacity-1, it moves rear to 0. 
        Otherwise moves it to right by 1.*/
        
        if(rear == capacity - 1) {
        	rear = 0;
        }else {
        	rear++;
        }
        
        
        size++;
    }

    
    public T removeFront() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty.");
        }
        
        T element = deque[front]; 
        
        /*same logic as used in addRear, as removeFront basically makes the same changes to 
        front as addRear does to rear.*/
        if(front == capacity - 1) {
        	front = 0;
        }else {
        	front++;
        }
        
        
        size--;
        return element;
    }

    
    public T removeRear() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty.");
        }
        /*same logic as used in addFront, as removeRear basically makes the same changes to
        rear as addFront does to front.*/
        
        if(rear == 0) {
        	rear = capacity - 1;
        } else {
        	rear--;
        }
        
        //after testing, I found that I have to get the value of the element after editing rear
        T element = deque[rear]; 
        size--;
        return element;
    }

    
    public T peekFront() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty.");
        }
        return deque[front];
    }

    
    public T peekRear() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty.");
        }
        
        /*because of my implementation, rear represents the next available position, not the current rear,
        so I have to return the index to the left of what my rear variable is equal to instead of the
        rear variable.*/
        int rearIndex;
        if(rear == 0) {
        	rearIndex = capacity - 1;
        	
        }else {
        	rearIndex = rear - 1;
        }
        return deque[rearIndex];
    }

    
    public boolean isEmpty() {
    	//deque is empty when size is 0.
        return size == 0;
    }

    
    public boolean isFull() {
    	//deque is full when size = capacity.
        return size == capacity;
    }

    
    public int size() {
        return size;
    }

    
    public void clear() {
    	//clear does not set everything to null, only sets front, rear, and size to 0 because its more efficient.
        front = 0;
        rear = 0;
        size = 0;
    }

    
    @Override
    public String toString() {
        String dequeContents = "[";
        for (int i = 0; i < capacity; i++) {
        	//if i = capacity + 1, do not add comma because its the end of the array
            dequeContents += deque[i];
            if (i < capacity - 1) {
                dequeContents += ", ";
            }
        }
        dequeContents += "]";

        return "CircularDeque [deque=" + dequeContents +  ", front=" + front + ", rear=" + rear + 
               ", size=" + size + ", capacity=" + capacity + "]";
    }
}