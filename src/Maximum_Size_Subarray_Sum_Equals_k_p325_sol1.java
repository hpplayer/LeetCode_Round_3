import java.util.HashMap;

/*
325. Maximum Size Subarray Sum Equals k

Given an array nums and a target value k, find the maximum length of a subarray that sums to k.
If there isn't one, return 0 instead.

Example 1:
Given nums = [1, -1, 5, -2, 3], k = 3,
return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)

Example 2:
Given nums = [-2, -1, 2, 1], k = 1,
return 2. (because the subarray [-1, 2] sums to 1 and is the longest)

Follow Up:
Can you do it in O(n) time?
*/		

/**
 * hashMap solution
 * 
 * We use a HashMap to calculate all previous calculated sum. Meanwhile, we also use a variable to record the 
 * accumulated sum ended at curr index (call it sum_i). Lets say we found a sum_j in hashMap that sum_i - sum_j == k,
 * then it means subarray from j + 1 to i (both inclusive) give a sum of k, we just need to record its length.
 * 
 * Here comes a question: do we need to update the index of sum in hashMap?
 * The answer is no, we want keep the index in hashMap as lower as possible so that we can get a longer subarray
 * length
 * 
 * Time complexity: O(N) as we scan the input list only once
 * Space complexity: O(N) as we would finally have O(N) tuples in hashMap
 * 
 * Remark:
 * This solution is similar to Two_Sum_I_p1_sol1, where we also use a HashMap to cache all prev results, then
 * look up counter part in O(1) time
 * 
 * @author hpPlayer
 * @date Jun 5, 2016 4:11:41 PM
 */
public class Maximum_Size_Subarray_Sum_Equals_k_p325_sol1 {
    public int maxSubArrayLen(int[] nums, int k) {
        //hashMap solution. Use a hashMap to record all previous accumulated sum, then playing around with sum
        //to get max size subarray
        
        //boundary check
        if(nums.length == 0) return 0;
        
        int result = 0;
        
        //sum will record the accumulated sum end at curr index
        int sum = 0;
        //hashMap, key is accumulated sum, value is its corresponding index
        HashMap<Integer, Integer> hs = new HashMap<Integer, Integer>();
        //we put sum 0 here so in case sum = k at some index i, we can treat it as a general case
        hs.put(0, -1);
        
        for(int i = 0; i < nums.length; i++){
            //update sum to include curr element
            sum += nums[i];
            
            //check if we have a subarray ended at index i that can give sum of k
            if(hs.containsKey(sum - k)){
                //hs.get(k-sum) is end index of the subarray before our target subarray
                //therefore len of curr subarray = i - hs.get(k-sum)
            	result = Math.max(result,  i - hs.get(sum - k));
            }
            
            //we dont update a sum if it is already in HashMap so that we can always get maxSubarray if we have one
            if(!hs.containsKey(sum)){
                hs.put(sum, i);
            }
        }
        
        return result;
    }
}
