package myDeque;
import java.lang.reflect.Array;

public class Util {
	public static boolean isPalindrome(String s) {
		CircularDeque<Character> deque = new CircularDeque<>(s.length());
		
		for(int i = 0; i < s.length(); i++) {
			//checks if the character is a letter or digit, as this method has to ignore non letters or digits
			if(Character.isLetterOrDigit(s.charAt(i))) {
				//adds the character to the deque if it is valid in order to model the string with a deque
				deque.addRear(Character.toLowerCase(s.charAt(i)));
			}
			
		}
		
		for(int i = 0; i < deque.size(); i++) {
			/*if the value at the front of the array does not equal the value at the back of the array, then
			the string is not a palindrome and because of this I return false.*/
			if(!deque.removeRear().equals(deque.removeFront())) {
				return false;
			}
		}
		
		//if it didn't return false at all throughout the for loop, that means the string is a palindrome.
		return true;
		
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Comparable<T>> T[] slidingWindowMax(T[] data, int k) {
		//Handles edge cases
		if (data == null || data.length == 0 || k <= 0) {
            return (T[]) Array.newInstance(data.getClass().getComponentType(), 0);
        }
		
		
		CircularDeque<Integer> deque = new CircularDeque<>(k);
		
		//2nd argument is data.length-k+1 because that is the common pattern in the documentation example
		T[] answer = (T[]) Array.newInstance(data.getClass().getComponentType(), data.length - k + 1);
		
		for(int i = 0; i < data.length; i++) {
			/*if the front index is < i-k+1, it means that the index is outside of the current window
			so we remove it. The current window is i-k+1 because that is the last k elements including i.
			Also, if the deque is empty you can't remove anything so that is why it is a condition.*/
			while (!deque.isEmpty() && deque.peekFront() < i - k + 1) {
                deque.removeFront();
            }
			
			//if the element at the rear is <= data[i], it cannot be the maximum, so we remove it.
			//Also, if the deque is empty you can't remove anything so that is why it is a condition.
			while (!deque.isEmpty() && data[deque.peekRear()].compareTo(data[i]) <= 0) {
                deque.removeRear();
            }

            //After first 2 steps, we add i to the deque.
            deque.addRear(i);

            //After everything, the front of the deque will have the index of the max, so we add it to answer.
            if (i >= k - 1) {
                answer[i - k + 1] = data[deque.peekFront()];
            }

		}
		
		return answer;
	
		
	}

	    
	


}


