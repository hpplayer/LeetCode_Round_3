import java.util.*;
/*
15. 3Sum

Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:
Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ¡Ü b ¡Ü c)
The solution set must not contain duplicate triplets.
    For example, given array S = {-1 0 1 2 -1 -4},

    A solution set is:
    (-1, 0, 1)
    (-1, -1, 2)
*/

/**
 * Three pointer solution
 * 
 * We firstly sort the array, then use three pointers to control the sum of current sequence.
 * First pointer help us get non-duplicate sequence with different start number
 * Second and third pointers help us control the sum of current sequence
 * 
 * Time complexity: O(n^2) as outer loop costs O(n), each loop costs O(n)
 * Space complexity: O(1) excluding the result list
 * 
 * Remark:
 * For such 3 sum problems, we can also solve with 2-sum style approach. i.e. we create a HashMap to store all possible 2-sums.
 * Then we scan the input list to check if we get its corresponding sum available.
 * But this approach costs extra space while time complexity is still O(n^2), so I did not list it here
 * 
 * @author hpPlayer
 * @date Feb 17, 2016 6:59:35 PM
 */
public class _3Sum_p15 {
	public static void main(String[] args){
		int[] nums = {-1, 0, 1, 2, -1, -4};
		
		for(List<Integer> list : new _3Sum_p15().threeSum(nums)){
			System.out.println(list);
		}
	}
    public List<List<Integer>> threeSum(int[] nums) {
        //three pointer solution Firstly sort the array, so later we can move pointer smartly
        //we use first pointer to pick different sequences and use second/third pointer to control the sum of sequence
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        Arrays.sort(nums);
        
        //i is our first pointer, we will stop when there is not enough 2 cells left in the array
        for(int i = 0; i + 2 < nums.length; i++){
            //start is 2nd pointer and end is third pointer
            int start = i + 1, end = nums.length - 1;
            
            while(start < end){
                int sum = nums[i] + nums[start] + nums[end];
                
                if(sum == 0){
                    //found one pair
                    result.add(Arrays.asList(nums[i], nums[start], nums[end]));
                    start++;
                    //skip duplicate start
                    while(start < end && nums[start] == nums[start-1]) start++;    
                }else if(sum < 0){
                    //too small, move start
                    start++;
                }else{
                    //too large, move end
                    end--;
                }
            }
            
            //let pointer i skip duplicate cells
            while(i + 2 < nums.length && nums[i] == nums[i+1]) i++;
        }
        
        return result;
    }
}
