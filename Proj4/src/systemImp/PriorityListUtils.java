package systemImp;

public class PriorityListUtils {

   
    public static <T extends Comparable<T>> PriorityList<T> mergePriorityLists(PriorityList<T> list1, PriorityList<T> list2) {
        
    	//check if list 1 is sorted
        for (int i = 1; i < list1.size(); i++) {
        	
            if (list1.get(i - 1).compareTo(list1.get(i)) > 0) {
                throw new IllegalArgumentException("One or both lists are not sorted using Comparable");
            }
        }

        //check if list 2 is sorted
        for (int i = 1; i < list2.size(); i++) {
            if (list2.get(i - 1).compareTo(list2.get(i)) > 0) {
                throw new IllegalArgumentException("One or both lists are not sorted using Comparable");
            }
        }

        
        int size = list1.size() + list2.size();
        PriorityList<T> mergedList = new PriorityList<>(size, false, null);

        
        int i = 0, j = 0;
        
        
        while (i < list1.size() && j < list2.size()) {
            T item1 = list1.get(i);
            T item2 = list2.get(j);
            
            //if item1 is less than item2, add item1
            if (item1.compareTo(item2) <= 0) {
                mergedList.add(item1);
                i++;
            } else { //otherwise, add item2
                mergedList.add(item2);
                j++;
            }
        }

        //if list1 had more elements than list 2, then this adds the remaining elements to the merged list
        while (i < list1.size()) {
            mergedList.add(list1.get(i));
            i++;
        }

        //if list2 had more elements than list 1, then this adds the remaining elements to the merged list
        while (j < list2.size()) {
            mergedList.add(list2.get(j));
            j++;
        }

        return mergedList;
    }
}