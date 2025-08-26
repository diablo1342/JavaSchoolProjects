package systemImp;

public class SearchAndSortUtil {

    public static <T extends Comparable<T>> int bidirectionalLinearSearch(T[] arr, T key, int left, int right, StringBuilder log) {
        //if left > right, then that means the element is not in the array, so we return -1.
        if (left > right) {
            return -1; 
        }

        log.append("Checking index (left): ").append(left).append(", value: ").append(arr[left]).append("\n");

        //if they are equal, then that means condition is satisfied
        if (arr[left].equals(key)) {
            return left;
        }

        /*left occurs before right, so if left is equal to right, toString would return the same thing for
         right as it would for left, which isn't what's in the correct output. Because of this, we only add
         the value of arr[right] to the log if right != left.
         */
        if (left != right) {
            log.append("Checking index (right): ").append(right).append(", value: ").append(arr[right]).append("\n");
        }

        //if they are equal, then that means condition is satisfied
        if (arr[right].equals(key)) {
            return right;
        }

        //if we don't return anything, we call the function recursively again
        return bidirectionalLinearSearch(arr, key, left + 1, right - 1, log);
    }

    public static <T extends Comparable<T>> String bidirectionalBubbleSort(T[] arr) {
        String log = ""; 
        
        
        boolean swapped = true;
        int left = 0;
        int right = arr.length - 1;
        
        //while swapped is true, the body of the while loop will continue
        while (swapped) {
            swapped = false;
            
            
            for (int i = left; i < right; i++) {
            	//if no swaps occur, then swapped stays false
                if (arr[i].compareTo(arr[i + 1]) > 0) {
                    T temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                    log += "Swapped " + arr[i] + " and " + arr[i + 1] + ": [" + 
                    arrayToStringRecursiveHelper(arr, 0, "") + "]\n"; 
                }
            }
            /*we have determined the largest element in the array and put it at the end, so we dont need right to be
            at the end of the array anymore*/
            right--; 

            for (int i = right; i > left; i--) {
            	//pass through the array again because its more efficient
                if (arr[i].compareTo(arr[i - 1]) < 0) {
                    T temp = arr[i];
                    arr[i] = arr[i - 1];
                    arr[i - 1] = temp;
                    swapped = true;
                    log += "Swapped " + arr[i] + " and " + arr[i - 1] + ": [" + 
                    arrayToStringRecursiveHelper(arr, 0, "") + "]\n"; 
                }
            }
            /*we have determined the smallest element in the array and put it at the front, so we dont need left to be
             at the start of the array anymore.*/
            left++; 
            
            //if swapped doesn't change, it means that the array is already in order and thus the while loop ends
        }

        return log; 
    }

    public static <T extends Comparable<T>> void recursiveBidirectionalSelectionSort
    (T[] arr, int left, int right, StringBuilder log) {
    	//if left >= right, that means the sort is finished
        if (left >= right) {
            return; 
        }

        int minIndex = left;
        int maxIndex = right;

        for (int i = left; i <= right; i++) {
        	//sets minIndex to the smallest element in the array
            if (arr[i].compareTo(arr[minIndex]) < 0) {
                minIndex = i;
            }
            
            //sets maxIndex to the biggest element in the array
            if (arr[i].compareTo(arr[maxIndex]) > 0) {
                maxIndex = i;
            }
        }
        
        //if minIndex is not equal to the leftmost index of the array, we swap arr[minIndex] and arr[left]
        if (minIndex != left) {
            T temp = arr[minIndex];
            arr[minIndex] = arr[left];
            arr[left] = temp;

            log.append("Swapped ")
               .append(arr[minIndex].toString())
               .append(" and ")
               .append(temp.toString()).append(": ")
               .append("[")
               .append(arrayToStringRecursiveHelper(arr, 0, ""))
               .append("]")
               .append("\n");
        }
        
        //if the maxIndex was at the same index as left, then it would now be at minIndex since we swapped minIndex and left
        //Because of this, we have to set maxIndex to minIndex
        if (maxIndex == left) {
            maxIndex = minIndex;
        }
        
        //if maxIndex is not equal to the rightmost index of the array, we swap arr[maxIndex] and arr[right]
        if (maxIndex != right) {
            T temp = arr[maxIndex];
            arr[maxIndex] = arr[right];
            arr[right] = temp;

            log.append("Swapped ")
               .append(arr[maxIndex].toString())
               .append(" and ")
               .append(temp.toString()).append(": ")
               .append("[")
               .append(arrayToStringRecursiveHelper(arr, 0, ""))
               .append("]")
               .append("\n");
        }
        
        //if nothing is returned, call the method recursively again
        recursiveBidirectionalSelectionSort(arr, left + 1, right - 1, log);
    }

    
    //have to create this method in order to convert array to string because we can't import or use loops
    private static <T> String arrayToStringRecursiveHelper(T[] arr, int index, String s) {
    	//if index reaches the end of the array, stop the method
        if (index >= arr.length) {
            return s; 
        }

        //Add the current element to the string
        s += arr[index].toString();

        //add a comma only if its not the last element of the array
        if (index < arr.length - 1) {
            s += ", ";
        }

        //call the function again until base case is fulfilled
        return arrayToStringRecursiveHelper(arr, index + 1, s);
    }
}