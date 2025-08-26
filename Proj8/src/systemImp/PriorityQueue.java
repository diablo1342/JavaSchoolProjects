package systemImp;

public class PriorityQueue<T extends Comparable<T>> {
    private T[] heap;
    private int size;
    private boolean isMinHeap;

    public PriorityQueue(T[] arr, boolean isMinHeap) {
    	//check edge cases
        if (arr == null || arr.length <= 1 || arr[0] != null) {
            throw new IllegalArgumentException("Invalid array: must be non-null, length > 1, and have null at index 0.");
        }
        this.heap = arr;
        this.isMinHeap = isMinHeap;
        this.size = arr.length - 1;

        
        HeapUtils.heapify(heap, isMinHeap, null);
    }

    public T peek() {
    	//check edge cases
        if (size == 0) {
            throw new IllegalStateException("PriorityQueue is empty.");
        }
        return heap[1]; 
    }

    public void insert(T item, StringBuffer log) {
    	//check edge cases
        if (size >= heap.length - 1) {
            throw new IllegalStateException("PriorityQueue is full. Cannot insert.");
        }
        size++;               
        //Increase size
        heap[size] = item;    
        //Add item
        siftUp(size, log);    
        //siftUp to maintain heap property
        if (log != null) {
            logArray(log, "insertion"); 
        }
    }

    public T remove(StringBuffer log) {
    	//check edge case
        if (size == 0) {
            throw new IllegalStateException("PriorityQueue is empty.");
        }

        T removed = heap[1];     
        swap(1, size, log);      
        heap[size] = null;       
        size--;                  
        //reduce size
        siftDown(1, log);        
        //restore heap property
        if (log != null) {
            logArray(log, "removal"); 
        }
        return removed;
    }
    
    
    //method for sifting up
    private void siftUp(int index, StringBuffer log) {
        while (index > 1) {
            int parent = index / 2;

            int cmp;
            if (isMinHeap) {
                cmp = heap[index].compareTo(heap[parent]); //minHeap, child < parnet
            } else {
                cmp = heap[parent].compareTo(heap[index]); //maxHeap parent < child
            }

            if (cmp > 0) {
                break; //Heap property satisfied
            } else {
                swap(index, parent, log); //Swap with parent
                index = parent;           //Move up the tree
            }
        }
    }

    //method for sifting down
    private void siftDown(int index, StringBuffer log) {
        while (2 * index <= size) {
            int child = 2 * index;

            //If right child exists, pick the minHeap or maxHeap child
            if (child + 1 <= size) {
                int cmp;
                if (isMinHeap) {
                    cmp = heap[child + 1].compareTo(heap[child]);
                } else {
                    cmp = heap[child].compareTo(heap[child + 1]);
                }

                if (cmp < 0) {
                    child++; 
                }
            }

            int cmpDown;
            if (isMinHeap) {
                cmpDown = heap[child].compareTo(heap[index]); 
            } else {
                cmpDown = heap[index].compareTo(heap[child]); 
            }

            if (cmpDown < 0) {
                swap(index, child, log); //Swap with child and then move down tree
                index = child;           
            } else {
                break; //Heap property restored
            }
        }
    }

    
    //method for swapping
    private void swap(int i, int j, StringBuffer log) {
        if (log != null) {
            log.append("Swapped index ")
               .append(i).append(" (").append(heap[i])
               .append(") with index ").append(j).append(" (").append(heap[j]).append(")\n");
        }
        T temp = heap[i];
        heap[i] = heap[j]; //swap
        heap[j] = temp;
    }
    
    //method for logging
    private void logArray(StringBuffer log, String operation) {
        log.append("Array after ").append(operation).append(": [");
        for (int i = 0; i < heap.length; i++) {
            log.append(heap[i]);
            if (i < heap.length - 1) {
                log.append(", ");
            }
        }
        log.append("]\n");
    }
}
