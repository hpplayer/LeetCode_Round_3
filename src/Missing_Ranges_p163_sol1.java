import java.util.*;

/*
Missing Ranges

Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.

For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
*/

/**
 * Two pointer solution
 * 
 * we use two pointers to check two neighbor values. If they are consecutive, then we just move to next pair. If not, we found a
 * missing range. 
 * 
 * To cover boundary case lower/upper, we let left pointer starts from lower - 1 and let right pointer ends at upper + 1. So we
 * can include two boundary cases 
 * 
 * Remark:
 * Our main loop will move right pointer automatically, but don't forget updating left pointer as well!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * 
 * Time complexity: O(n)
 * Space complexity: O(n)
 * 
 * This problem is similar to problem Summary_Ranges_p228_sol1 where we also need two pointers to check range
 * 
 * @author hpPlayer
 * @date Nov 18, 2015 9:52:45 PM
 */
public class Missing_Ranges_p163_sol1 {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<String>();
        
        //we use two pointers to search for a range.
        //prev is the prev value and curr is the curr value
        //if they are not consecutive, we found a missing range 
        
        //to include lower/upper, we have to set prev start with prev - 1 and let curr end with upper + 1
        int prev = lower-1;
        
        for(int i = 0; i <= nums.length; i++){
            int curr = i == nums.length? upper + 1 : nums[i];
            if(prev + 1 != curr){
                //if prev and curr are not consectuive
                //prev and curr should be values from our array, so numbers between them should be the missing range
                //ex1: prev = 0, curr = 2, then we miss 1
                //ex2: prev = 0, curr = 3, then we miss 1->2
                result.add( buildString(prev + 1, curr - 1) );
            }
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!DONT FORGET UPDATE PREV AS WELL!!!!!!!!!!!!!!!!!!!!!!!
            prev = curr;
        }
        
        return result;
    }
    
    public String buildString(int start, int end){
        return start == end? start + "" : start + "->" + end;
    }
}
