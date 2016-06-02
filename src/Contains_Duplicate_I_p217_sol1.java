import java.util.HashSet;

/*
Given an array of integers, find if the array contains any duplicates.
Your function should return true if any value appears at least twice in the array,
and it should return false if every element is distinct.
*/

/**
 * HashSet solution
 * 
 * Just use a Hashset to cache all visited elements
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Jun 1, 2016 9:19:14 PM
 */
public class Contains_Duplicate_I_p217_sol1 {
    public boolean containsDuplicate(int[] nums) {
        //hashSet solution. Use a Hashset to record all visited input, and look update duplicate in O(1)
        HashSet<Integer> hs = new HashSet<Integer>();
        for(int num : nums){
            if(!hs.add(num)) return true;
        }
        
        return false;
    }
}
