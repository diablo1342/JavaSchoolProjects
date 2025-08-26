package systemImp;

import java.util.Iterator;
import java.util.NoSuchElementException;



/**
 * A doubly linked list implementation for storing elements.
 * This list supports basic operations like adding and removing elements at both ends.
 * @class Inner class description.
 * @param <E> The type of elements in this list, which must extend Comparable.
 */
public class DoublyLinkedList<E extends Comparable<E>> implements Iterable<E> {
    private Node head; //points to the first node
    private Node tail; //points to the last node
    private int size;  //number of elements in the list

    /**
     * Node represents a single element in the doubly linked list.
     * It contains references to the data and the next and previous nodes in the list.
     */
    private class Node {
        E data;
        Node next, prev;

        /**
         * Constructor for creating a new node.
         * 
         * @param data The data to be stored in the node.
         */
        Node(E data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    
    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int getSize() { 
    	//size represents the size of th elist
    	return size; 
    }

    public E getFirst() {
    	//check edge cases
        if (head == null) {
        	throw new NoSuchElementException("List is empty!");
        }
        return head.data;
    }

    public E getLast() {
    	//check edge cases
        if (tail == null) {
        	throw new NoSuchElementException("List is empty!");
        }
        return tail.data;
    }

    public void addFirst(E e) {
    	//check edge cases
        if (e == null) {
        	throw new NullPointerException("Element cannot be null");
        }
        
        Node newNode = new Node(e);
        
        //if list is empty, set head and tail to newNode
        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else { //otherwise, make the head newNode and adjust the references
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void addLast(E e) {
    	//check edge cases
        if (e == null) {
        	throw new NullPointerException("Element cannot be null");
        }
        Node newNode = new Node(e);
        //if list is empty, set head and tail to newNode
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else { //otherwise, make the tail newNode and adjust the references
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void removeFirst() {
    	//check edge cases
        if (head == null) {
        	throw new NoSuchElementException("List is empty!");
        }
        
        //set head to head.next and adjust references acording to whether head.next is null or not
        //if head.next is null, then set tail to null as well because both head and tail have to be null if one is null
        head = head.next;
        if (head != null) {
        	head.prev = null;
        }
        else {
        	tail = null;
        }
        size--;
    }

    public void removeLast() {
    	//check edge cases
        if (tail == null) {
        	throw new NoSuchElementException("List is empty!");
        }
        
        //same logic as removeFirst, but for tail instead
        tail = tail.prev;
        if (tail != null) {
        	tail.next = null;
        }
        else {
        	head = null;
        }
        size--;
    }

    public void removeAllInRange(E start, E end) {
    	//check edge cases
        if (start == null || end == null) {
        	throw new NullPointerException();
        }
        Node current = head;
        boolean removed = false;
        
        //loop through the list
        while (current != null) {
        	//if the current nodes data is in range, remove it accordingly
        	//the adjusting of references changes based on whether its head, tail or other
            if (current.data.compareTo(start) >= 0 && current.data.compareTo(end) <= 0) {
                Node next = current.next;
                if (current.prev != null) {
                	current.prev.next = current.next;
                }
                if (current.next != null) {
                	current.next.prev = current.prev;
                }
                if (current == head) {
                	head = current.next;
                }
                if (current == tail) {
                	tail = current.prev;
                }
                size--;
                removed = true;
                current = next;
            } else {
                current = current.next;
            }
        }
        
        //if we didnt remove anything, throw nosuchelement exception
        if (!removed) {
        	throw new NoSuchElementException();
        }
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
        	//create variables for nextNode and the current node
            private Node nextNode = head;
            private Node current = null;

            public boolean hasNext() {
            	//if the next node isn't null, then true. otherwise false.
            	return nextNode != null; 
            }
            
            public E next() {
            	//if hasNext is false, throw nosuchelementexception
                if (!hasNext()) {
                	throw new NoSuchElementException();
                }
                //if current is null, set it to head. otherwise, iterate it once
                if(current == null) {
                	current = head;
                }else {
                	current = current.next;
                }
                
                //iterate nextNode and adjust pointers
                E data = nextNode.data;
                nextNode = nextNode.next;
                return data;
            }
            
            public void remove() {
            	//if current is null, throw illegalstateexception
                if (current == null) {
                	throw new IllegalStateException();
                }
                
                //adjust current references based on whether its head, tails or other and then set it to null
                if (current.prev != null) {
                	current.prev.next = current.next;
                }
                if (current.next != null) {
                	current.next.prev = current.prev;
                }
                if (current == head) {
                	head = current.next;
                }
                if (current == tail) {
                	tail = current.prev;
                }
                size--;
                current = null;
            }
        };
    }

    public String insertionSort() {
        if (size <= 1) {
            return toString();
        }

        String log = "";  
        Node sorted = null;
        Node current = head;

        while (current != null) {
            Node next = current.next;
            
            //if there is nothing at the sorted portion, or if sorted is greater than current, 
            //insert current at the beginning of the sorted portion
            if (sorted == null || sorted.data.compareTo(current.data) >= 0) {
                current.next = sorted;
                current.prev = null;
                if (sorted != null) {
                    sorted.prev = current;
                }
                sorted = current;
            } else {
                //otherwise, find the correct position to insert current into sorted
                Node temp = sorted;
                while (temp.next != null && temp.next.data.compareTo(current.data) < 0) {
                    temp = temp.next;
                }
                // Insert after temp
                current.next = temp.next;
                current.prev = temp;
                if (temp.next != null) {
                    temp.next.prev = current;
                }
                temp.next = current;
            }

            //update the head of sorted 
            head = sorted;
            
            //reinitialize the tail, as things might have changed when inserting
            Node temp = sorted;
            while (temp != null && temp.next != null) {
                temp = temp.next;
            }
            tail = temp;

            //add the changes to the string log
            String line = "";
            Node printNode = sorted;
            
            //loop through the sorted portion
            while (printNode != null && printNode != next) {
                line += printNode.data;
                if (printNode.next != null && printNode.next != next) {
                    line += " ";
                }
                printNode = printNode.next;
            }
            
            //if next isnt null, it means there is still an unsorted portion
            //because of this, we add a barrier and then loop through the unsorted portion
            if (next != null) {
                line += " | ";
                printNode = next;
                while (printNode != null) {
                    line += printNode.data;
                    if (printNode.next != null) {
                        line += " ";
                    }
                    printNode = printNode.next;
                }
            } else { //if next is null, we only add a barrier
                line += " |";
            }
            //after this, we are done with this step of sorting process, so we do \n
            log += line + "\n";

            current = next;
        }

        return log;
    }

    public String toString() {
    	//if list is empty, return "[]"
        if (size == 0) {
            return "[]";
        }
        
        String result = "[";
        Node current = head;
        
        //loop through list and add the data 
        while (current != null) {
            result += current.data;
            //if we are not at the end, add a comma
            if (current.next != null) {
                result += ", ";
            }
            current = current.next;
        }
        
        result += "]";
        return result;
    }
 }

   

