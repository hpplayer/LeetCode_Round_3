import java.util.HashSet;

/*
219. Contains Duplicate II

Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the
array such that nums[i] = nums[j] and the difference between i and j is at most k.
*/

/**
 * Sliding window solution
 * 
 * use a Hashset as the container for sliding window. Set size is the distance between cur element and first
 * element in set. 
 * Before hashset.size() > k, we can just fill in the 
 * inputs. After Hashset.size() > k, then we need to remove (i - k) previous element from hashset
 * 
 * Time complexity: O(N)
 * Space complexity: O(min(n,k))
 * 
 * @author hpPlayer
 * @date Jun 1, 2016 9:09:15 PM
 */
public class Contains_Duplicate_II_p219_sol1 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        //Sliding window solution. We use a hashset to keep a sliding window
        HashSet<Integer> hs = new HashSet<Integer>();
        
        for(int right = 0; right < nums.length; right++){
            //set size is like the distance between curr input and first element in set
            
            //if we found duplicate with k distance, return true
            if(hs.contains(nums[right])) return true;
            
            //otherwise add curr into set
            hs.add(nums[right]);
            
            //make sure set size is <= k, remove first element in set, which is nums[i - k]
            if(hs.size() > k) hs.remove(nums[right-k]);
        }
        
        //all checked, no duplicates found
        return false;
    }
}
