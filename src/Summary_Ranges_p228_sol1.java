import java.util.*;

/*
Summary Ranges

Given a sorted integer array without duplicates, return the summary of its ranges.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
*/

/**
 * Two pointer solution
 * 
 * We use right pointer to search for the range and use left pointer to mark the start number
 * 
 * 
 * Time complexity: each num in array will be visited once, so O(n)
 * Space complexity: We need an extra list to hold ranges so O(n)
 * 
 * This problem is similar to Missing_Ranges_p163_sol1 where we also need two pointer to check range 
 * 
 * @author hpPlayer
 * @date Nov 18, 2015 8:31:30 PM
 */
public class Summary_Ranges_p228_sol1 {
    public List<String> summaryRanges(int[] nums) {
        //two pointer solution, left pointer marks the start num, right pointer search for the end num
        List<String> result = new ArrayList<String>();
        
        //boundary check
        if(nums == null || nums.length == 0) return result;
        
        //right pointer always points to the end of current range
        //so in each new search, left pointer should be + 1 after last right pointer
        //The end condition should be that we have no new search range left, which means left pointe meets end
        for(int left = 0, right = 0; left < nums.length; left = right + 1){
            //move right pointer to new search
            right = left;
            //move right pointer if nums[right] + 1 = nums[right+1]
            while(right + 1 < nums.length && nums[right + 1] == nums[right] + 1) right++;
            
            //we may have two cases 1) only one num in range 2) several nums in range
            if(nums[right] == nums[left]){
                //case 1
                result.add(nums[right] + "");
            }else{
                //case 2
                result.add(nums[left] + "->" + nums[right]);
            }
        }
        
        return result;
    }
}
