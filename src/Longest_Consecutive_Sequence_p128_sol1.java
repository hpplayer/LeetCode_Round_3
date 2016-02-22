import java.util.*;

/*
Longest Consecutive Sequence

Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
*/

/**
 * Hash problem
 * 
 * We just need to scan the array and add all number into a HashSet.
 * Then we scan the array once and search for the number that we don't have num - 1 in the HashSet. This number must be the start of a consecutive
 * sequence. We just search the sequence using HashSet. Then we update the longest consecutive sequence accordingly. Due to the O(1) cost of searching
 * in hashMap/hashSet, our algorithm can runs in O(n) time
 * 
 * The idea is hard to come up with, but the code will be simple
 * 
 * Time complexity: O(n)
 * Space complexity: O(n)
 * 
 * Remark:
 * In this problem, we does not include the appearances of duplicate into count, like [0,0], we say the max len of valid sequence is 1
 * In case the input array contains duplicates and we need to include that into count, we can use a HashMap instead to record the appearances
 * of each number, longestConsecutive2() below applies this idea. But like I discussed above, this function handles another case, so it will be 
 * not AC by the LeetCode
 * 
 * @author hpPlayer
 * @date Nov 11, 2015 10:47:22 PM
 */
public class Longest_Consecutive_Sequence_p128_sol1 {
	   public int longestConsecutive(int[] nums) {
	        //use a HashSet to hold nums and the search in hashSet is O(1), so the total time would be O(n)
	        //In this problem, we ignore the multi-appearances of duplicate, just count the duplicate number once
	        Set<Integer> set = new HashSet<Integer>();
	        
	        for(int num : nums){
	            set.add(num);
	        }
	        
	        int result = 0;
	        
	        for(int num : nums){
	            //we begin search the num which is the first one of a sequence
	            //those num would not have num -1 in the set. So we just search for that
	            if(!set.contains(num-1)){
	                //found one, lets count the length
	                int count = 1;
	                while(set.contains(num+1)){
	                    //as long as we have num + 1 in the set, we can continue search
	                    count++;
	                    num++;
	                }
	                //update result if necessary
	                result = Math.max(result, count);
	            }
	        }
	        
	        return result;
	    }
	
	//below program is based on another assumption, not working for this problem
    public int longestConsecutive2(int[] nums) {
        Map<Integer, Integer> hs = new HashMap<Integer, Integer>();
        
        for(int num : nums){
            if(!hs.containsKey(num)){
                hs.put(num, 1);
            }else{
                hs.put(num, hs.get(num)+1);
            }
        }
        
        int result = 0;
        
        for(int num : nums){
            if(!hs.containsKey(num-1)){
                int count = hs.get(num);
                while(hs.containsKey(++num)){
                    count += hs.get(num);
                }
                result = Math.max(count, result);
            }
        }
        
        return result;
    }
}
