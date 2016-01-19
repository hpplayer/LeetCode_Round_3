/**
 * Binary search
 * 
 * Here is the idea:
 * We use binary search to locate the duplicates. The binary search is based on the counting result we did for the array.
 * So each time we scan the array and count how many numbers are smaller or equal to current value. Then based on the 
 * result we can narrow our next search range by half. 
 * 
 * Here is the analysis:
 * The value ranges from 1 to n, so we totally have n candidates. To narrow the search range, each time we need to scan the array
 * and count the numbers that are smaller or equal to current value. If count <= current value, then we know the duplicates must exist
 * in the larger numbers, so we need to search the larger half range in next run. If count > current value, then we know the duplicates
 * must exist in the smaller numbers including current number, so we need to search the smaller half range inclusive in next run
 * 
 * Remark:
 * 1. We will never have an input array with only one element which violates the definition gave by the problem.
 * 2. Time complexity is O(NLogN) and space complexity is O(1)
 * 
 * 
 * @author hpPlayer
 * @date Jan 18, 2016 4:27:40 PM
 */
public class Find_the_Duplicate_Number_p287_sol2 {
    public int findDuplicate(int[] nums) {
        //search range is from 1 to n as defined by the problem
        int start = 1, end = nums.length - 1;
        
        while(start < end){
            //get current number
            int mid = start + (end - start)/2;
            
            //count how many numbers in array are smaller or equal to current number 
            int count = 0;
            for(int num : nums){
                if(num <= mid) count++;
            }
            
            //narrow the search range by half based on the countint result
            if(count <= mid){
                //if count <= mid, then smaller half is ok, we need search larger half excluding current number
                start = mid + 1;
            }else{
                //if count > mid, then smaller half is not ok, we need search smaller half including current number
                end = mid;
            }
        }
        
        //found target, return result
        return start;
    }
}
