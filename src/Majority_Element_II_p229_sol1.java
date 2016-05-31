import java.util.*;

/**
 * Modified moores' voting algorithm
 * 
 * Firstly we need to identify how many candidates can satisfy the requirement that it appears more than n/3 times
 * Actually there at most will be two candidates meet this requirement. If we add third candidate, then all of them can
 * only appear n/3 times, which does not meet the requirement
 * 
 * Secondly we need initialize two counters and two candidates, and update them based on the input.
 * here is the tricky part, we may have several corner cases that make update not intuitive.
 * 1) we need make sure two candidate does not affect the other. If input matches a candidate, then we should just 
 * update the counter of this candidate
 * 2) Even though we may be careful with updates on counter/candidate, we may still finally got two duplicate candidates
 * Why? since we have to initialize candidates, the initial number may actually be a candidate number. In this solution,
 * the initial number is 0. If an input = 0, then we directly jump to counter-update step, and update counter accordingly
 * 
 * Thirdly, we need to verify if our candidates are real majority elements. 
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date May 30, 2016 11:57:05 PM
 */
public class Majority_Element_II_p229_sol1 {
    public List<Integer> majorityElement(int[] nums) {
        //modified moores' voting algorithm
        
        //we will at most have two candidates
        int candidate1 = 0;
        int candidate2 = 0;
        
        int count1 = 0;
        int count2 = 0;
        
        //1) update counters and candidates
        for(int num : nums){
            if(count1 == 0 && candidate2 != num){
                //we will only reset candidate1 if candidate2 != num (we may have counter1 = 0 in mid, but num = candidate2,
            	//in such case, we should not reset candidate1 to this input)
                //in case num = 0, we can simply skip reset steps and directly jump to counter update steps
                candidate1 = num;
                count1++;
            }else if(count2 == 0 && candidate1 != num){
                //we will only reset candidate2 if candidate1 != num
                //in case num = 0, we can simply skip reset steps and directly jump to counter update steps
                candidate2 = num;
                count2++;
            }else if(num == candidate1){
                //update counter1
                count1++;
            }else if(num == candidate2){
                //update counter2
                count2++;
            }else{
                //non-match input, decrease both counters
                count1--;
                count2--;
            }
        }
        
        //2) validation
        
        count1 = 0;
        count2 = 0;
        
        for(int num : nums){
            if(num == candidate1) count1++;
            if(num == candidate2) count2++;
        }
        
        List<Integer> result = new ArrayList<Integer>();
        
        //for candidate1, we just need to check its appearance
        if(count1 > nums.length/3) result.add(candidate1);
        //for candidate1, in addition to its appearance, we need also check if it is duplicate with candidate1 (coming from intialization)
        if(count2 > nums.length/3 && candidate1 != candidate2) result.add(candidate2); 
        
        return result;
    }
}
