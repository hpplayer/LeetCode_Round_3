import java.util.*;

/**
 * TreeSet + sliding window solution
 * 
 * We use two pointer to keep a sliding window of size k
 * Then we use TreeSet inside the window to find two values within t range
 * 
 * The main algorithm is similar to sol1, but due to the large input, we have to cast nums[i] to long when get the difference and compare
 * with t
 * 
 * Time complexity: O(nlogk), where logK is used to sort the numbers in window, we need do this n times
 * Space complexity: O(k)
 * 
 * @author hpPlayer
 * @date Nov 12, 2015 12:18:59 AM
 */

public class Contains_Duplicate_III_p220_sol2 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // we use a TreeSet to sort and hold numbers in nums
        TreeSet<Integer> set = new TreeSet<Integer>();
        int left = 0;
        
        for(int i = 0; i < nums.length; i++){
            if(i > k){
                //after we reach win size k, we have to move left pointer each time we move right pointer
                //so the win size can be maintained
                set.remove(nums[left]);
                left++;
            }
            
            //if set does not have ceiling/floor number of nums[i], it will return null
            Integer floor = set.floor(nums[i]);
            //if set contains the duplicate of nums[i], then set will return the duplicate in ceiling()   
            Integer ceil = set.ceiling(nums[i]);
            
            //to prevent overflow from large input, we have case the number to long
            if( (floor!= null && (long)nums[i] - floor <= t) || (ceil != null && ceil - (long) nums[i] <= t )){
                return true;
            }
            
            //add current input into the window
            set.add(nums[i]);
        }
        
        return false;
    }
}
