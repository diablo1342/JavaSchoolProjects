package systemImp;

public class HeapUtils {

    public static <T extends Comparable<T>> void heapify(T[] arr, boolean isMinHeap, StringBuffer log) {
        int n = arr.length - 1; 
        for (int i = n / 2; i >= 1; i--) {
            //Start from last internal node and sift down each to restore heap property
            sift(arr, i, n, isMinHeap, log, "Heapify");
        }
    }

    public static <T extends Comparable<T>> void heapSort(T[] arr, StringBuffer log) {
        
        heapify(arr, false, null);
        int n = arr.length - 1;

        //Repeatedly move the root to the end and re-heapify
        for (int i = n; i >= 2; i--) {
            swap(arr, 1, i, log, "HeapSort"); //Move max to end
            sift(arr, 1, i - 1, false, log, "HeapSort"); //Restore max-heap in reduced array
        }
    }

    
    //method that sifts downwards
    private static <T extends Comparable<T>> void sift(T[] arr, int i, int n, boolean isMinHeap, StringBuffer log, String operationName) {
        int current = i;

        while (2 * current <= n) {
            int child = 2 * current; //Left child

            //choose either minHeap or maxHeap child
            boolean shouldSwapLeft = false;
            if (child + 1 <= n) { 
                int cmp;
                if (isMinHeap) {
                    cmp = arr[child + 1].compareTo(arr[child]);
                } else {
                    cmp = arr[child + 1].compareTo(arr[child]) * -1;
                }

                if (cmp < 0) {
                    shouldSwapLeft = true; //Right child is higher priority if cmp < 0
                }
            }

            if (shouldSwapLeft) {
                child++; //Use right child
            }

            //Compare selected child with current node
            int cmp;
            if (isMinHeap) {
                cmp = arr[child].compareTo(arr[current]);
            } else {
                cmp = arr[child].compareTo(arr[current]) * -1;
            }

            if (cmp < 0) {
                //Only swap if elements are not equal
                if (!arr[child].equals(arr[current])) {
                    swap(arr, current, child, log, operationName);
                }
                current = child; //Continue sifting down
            } else {
                break;
            }
        }
    }

    
    //Swaps elements at indices i and j and logs the swap.
    private static <T> void swap(T[] arr, int i, int j, StringBuffer log, String operationName) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        if (log != null) {
            log.append(operationName).append(" swap: Swapped index ")
               .append(i).append(" (").append(arr[j]).append(") with index ")
               .append(j).append(" (").append(arr[i]).append(")\n");
        }
    }
}
