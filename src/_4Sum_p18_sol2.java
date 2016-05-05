import java.util.*;

/**
 * Two-sum like approach. 
 * 
 * We use the basic idea from Two_Sum_p1_sol1 and cache sums of all prev pairs.
 * For each element n, we will do two things:
 * 1) Generate new pair with n and elements after it, then check prev pairs to see if we can get the result combination
 * 2) Generate new pair with n and elements before it, then cache sums of those pairs.
 * 
 * Part 1) helps us found the result combination that n is used as elements in second pair(assume 4sum can be divided into two pairs)
 * Part 2) helps us found the result combination that n is used as elements in first pair
 * 
 * Time and space complexity is not intuitive,
 * Time complexity: maybe O(N^2), as two inner loops have n len in total, and outer loop has n len
 * Space complexity: maybe O(N^2), as we have N^2 pairs from input 
 * 
 * @author hpPlayer
 * @date May 4, 2016 10:55:01 PM
 */
public class _4Sum_p18_sol2 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        //two-sum like approach, we will cache all sum value for each two elements. For each new element, we will calculate its sum with following elements, then look for target-sum pair from previous search
        //After that, we will cache sum of new elements with previous elements
        
        
        Arrays.sort(nums);
        //For each sum of prev pairs, we don't want store duplicate pairs, so we use set here
        //using set to cache pairs can help us save time
        Map<Integer, Set<Pair>> hs = new HashMap<Integer, Set<Pair>>();
        //for convenience, we use another Hashset to avoid adding duplicate result
        Set<List<Integer>> visited = new HashSet<List<Integer>>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        
        //we only have 2 nested loop here since we have cached sum of each pair of previous elements
        //we will enumerate all pairs
        for(int i = 0; i + 1 < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                int sum = nums[i] + nums[j];
                if(hs.containsKey(target - sum)){
                    //if prev pairs have target-sum value, then we found a result combination 
                    for(Pair pair : hs.get(target - sum)){
                        //if result combination is new, then we add it to result list
                        List<Integer> list = Arrays.asList(pair.left, pair.right, i, j);
                        if(visited.add(list)) result.add(list);
                    }
                }
            }
            
            //For each new pair (i, j)s that composed of nums[i] and next elements, we have compared them with all prev sum to find target elements
            //Now we need create new pairs from prev elements with nums[i], which can be used by later new pairs
            //Each index i will be scanned, so that our loops will cover all cases
            for(int j = 0; j < i; j++){
                int sum = nums[j] + nums[i];
                if(!hs.containsKey(sum)) hs.put(sum, new HashSet<Pair>());
                hs.get(sum).add(new Pair(nums[j], nums[i]));
            }
        }
        
        return result;
    }
    
    private class Pair{
        int left, right;
        Pair(int l, int r){
            left = l;
            right = r;
        }
    }
}
