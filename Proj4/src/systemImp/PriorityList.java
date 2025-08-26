package systemImp;

import java.util.Comparator;

public class PriorityList<T extends Comparable<T>> {
    private T[] elements;
    private int size;
    private final int capacity;
    private final boolean useComparator;
    private final Comparator<T> comparator;

    @SuppressWarnings("unchecked")
    public PriorityList(int capacity, boolean useComparator, Comparator<T> comparator) {
        this.capacity = capacity;
        this.useComparator = useComparator;
        this.comparator = comparator;
        this.elements = (T[]) new Comparable[capacity];
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int index) {
    	//handle edge case
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return elements[index];
    }

    public int binarySearchInsertionPoint(T item) {
        int low = 0;
        int high = size - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int diff;
            //if true use custom comparator
            if (useComparator) {
                diff = comparator.compare(item, elements[mid]);
            } else { //otherwise use natural ordering
                diff = item.compareTo(elements[mid]);
            }

            if (diff < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public void add(T item) {
        if (isFull()) {
            throw new IllegalStateException("Priority List is full");
        }
        
        //find where to add the item
        int insertionPoint = binarySearchInsertionPoint(item);
        //Shift elements to the right to make space for the new item
        for (int i = size; i > insertionPoint; i--) {
            elements[i] = elements[i - 1];
        }
        elements[insertionPoint] = item;
        size++;
    }

    
    public int binarySearchFind(T item) {
        int low = 0;
        int high = size - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int diff;
            if (useComparator) {
                diff = comparator.compare(item, elements[mid]); 
            } else {
                diff = item.compareTo(elements[mid]); 
            }

            if (diff == 0) {
                //Use equals to confirm the item is found
                if (item.equals(elements[mid])) {
                    return mid; 
                } else {
                    high = mid - 1; 
                }
            } else if (diff < 0) {
                high = mid - 1; // Search in the left half because the item is less than elements[mid]
            } else {
                low = mid + 1; // Search in the right half because the item is greater than elements[mid]
            }
        }
        return -1; 
    }

    public boolean remove(T item) {
    	//find what index to remove the item
        int index = binarySearchFind(item);
        if (index == -1) {
            return false;
        }

        //Shift elements to the left to fill the gap
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null; //Clear the last element
        size--;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]).append(" ");
        }
        sb.append("]");
        return sb.toString();
    }
}