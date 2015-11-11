import java.util.*;

/*
Sliding Window Maximum

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].

Note: 
You may assume k is always valid, ie: 1 ¡Ü k ¡Ü input array's size for non-empty array.

Follow up:
Could you solve it in linear time?

Hint:

How about using a data structure such as deque (double-ended queue)?
The queue size need not be the same as the window¡¯s size.
Remove redundant elements and the queue should store only elements that need to be considered.
*/


/**
 * Deq + sliding window problem
 * 
 * We use a deque to maintain a sliding window, in which values are kept in descending order. 
 * If new input number is smaller than the smallest value in deque, then we just insert it into deque.
 * If new input number is larger or equal to the smallest value in deque, then we will begin poll number from deque. So we can still maintain
 * descending order after inserting the new input num. Then we check if leftmost number in the deque has been skipped over, if it is then we 
 * will remove it. After that we will get a valid sliding window stop at current index i. 
 * Since numbers in deque are following descending order, the leftmost number in window will be the max number in window. We just
 * add it into the result.
 * 
 * We will totally push O(n) number into the dequeue, so the total running time would be O(n)
 * The space complexity will also be O(n)
 * 
 * @author hpPlayer
 * @date Nov 11, 2015 4:28:24 PM
 */
public class Sliding_Window_Maximum_p239_sol1 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        //boundary check
        if(k == 0 || nums.length == 0) return new int[]{};
        //for array of len n and window size k, there will only be n - k + 1 windows  
        int[] result = new int[nums.length - k + 1];
        //index in result[]
        int index = 0;
        
        //we use a deque to represent a window, we keep the vals in deque with descending order
        //the size of deque may not necessary = k, as the new input val may be large and we won't use
        //any past val anymore
        Deque<Integer> deq = new LinkedList<Integer>();
        
        for(int i = 0; i < nums.length; i++){
            if(deq.isEmpty() || nums[i] < nums[deq.peekFirst()]){
                //two cases that we can add i to the deq and still keep descending order in deq
                deq.offerFirst(i);
            }else{
                //otherwise we need pop values from deq to maintain descending order before we insert new num
                while(!deq.isEmpty() && nums[deq.peekFirst()] <= nums[i]){
                    deq.pollFirst();
                }
                deq.offerFirst(i);
            }
            
            //poll the leftmost index out if our window has skipped it 
            if(i - k >= deq.peekLast()) deq.pollLast();
            
            //update result[] with the max number in current window
            //since num in deq is following descending order, val in last will be the max one
            //we will start update result[] after we have scanned enough number in input array
            if(i >= k - 1) result[index++] = nums[deq.peekLast()];
        }
        
        return result;
    }
}
