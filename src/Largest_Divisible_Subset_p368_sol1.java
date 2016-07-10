import java.util.*;

/*
368. Largest Divisible Subset

Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements
in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.

If there are multiple solutions, return any subset is fine.

Example 1:

nums: [1,2,3]

Result: [1,2] (of course, [1,3] will also be ok)
Example 2:

nums: [1,2,4,8]

Result: [1,2,4,8]
*/

/**
 * DP + observation solution
 * 
 * To add a new num into one of the existing subset, we don't need to check each num in existing subset,
 * that is too costly and not necessary, instead we just need to check the smallest and largest num in 
 * existing subset. The new incoming num should either divide smallest num without remainder (Ex: 2->[4] )or
 * be divided by largest num without remainder (ex: 32->[4,16]). However if we can sort the the input,
 * then we just need to check one end either largest or smallest num. 
 * 
 * Here is the basic idea. We firstly sort the array,  then create a dp array. The value in dp[] means how 
 * long the subset would be with ending at this cell. The for each new input, we just need to check all prev
 * dp values to find the cell that can accept this new input while has largest len. Since we sort the input
 * from small to large, the only way the new input can be accepted is that it can divide the largest num in
 * this subset. Since we need to finally return the List<String>, we have to use a HashMap to record each subset
 * But that is ok, since the size of largest set is just 31. Why? The new incoming input is at least 2* the largest
 * num in prev array. We have 2^31 num in integer range. so the space complexity is still O(31N)
 * 
 * Time complexity: O(N^2)
 * Space complexity: O(N)
 * 
 * Remark:
 * we can avoid dp[] and query hs.get(i).size() directly, but that would cost the speed a little slower
 * 
 * @author hpPlayer
 * @date Jun 27, 2016 11:04:51 PM
 */
public class Largest_Divisible_Subset_p368_sol1 {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        //boundary check
        if(nums.length == 0) return new ArrayList<Integer>();
        
        //sort the input first, so we can quickly get the largest num in each subset and check if new incoming
        //input can be added
        Arrays.sort(nums);
        
        //key is input index, value is subset ended at this index
        HashMap<Integer, List<Integer>> hs = new HashMap<>();
        //dp[i] means the the len of DivisibleSubset ended at this index i
        int[] dp = new int[nums.length];
        
        int global_index = 0;
        int global_max_len = 1;
        
        dp[0] = 1;
        hs.put(0, Arrays.asList(nums[0]));
        
        for(int i = 1; i < nums.length; i++){
            int max_len = 1;
            int index = i;
            for(int j = 0; j < i; j++){
                if( nums[i]%nums[j] == 0 && dp[j] + 1 > max_len  ){
                    //if we found a prev DivisibleSubset that curr input can divide the largest num in that subset
                    //without remainder and the new DivisibleSubset size is max we found so far locally 
                    max_len = dp[j] + 1;
                    index = j;
                }
            }
            
            //all prev results checked, update hs, dp[i] and global variables if possible
            dp[i] = max_len;
            
            if( max_len == 1 ){
                hs.put( i, Arrays.asList(nums[i]) );
            }else{
                List<Integer> temp = new ArrayList<Integer>(hs.get(index));
                temp.add(nums[i]);
                hs.put(i, temp);
            }
            
            if( max_len > global_max_len ){
                global_max_len = max_len;
                global_index = i;
            }
        }
        
        
        return hs.get(global_index);
    }
}
