/*
169. Majority Element

Given an array of size n, find the majority element. The majority element is the element that appears more than ( n/2 ) times.

You may assume that the array is non-empty and the majority element always exist in the array.
*/

/**
 * Moore Voting algorithm
 * 
 * Linear algorithm to find the majority element in input (must appears > n/2 times). 
 * Use a counter to count the appearance of an element. when counter == 0, then set candidate to curr element.
 * Since majority element appears > n/2 times, we must finally have counter > 0 for the majority element
 * 
 * Remark:
 * This algorithm can only find out the majority element if we assume input have one.
 * If input does not contain a such element, then this algorithm will return an incorrect value
 * In such case, we need another loop to validate result
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date May 30, 2016 10:17:12 PM
 */
public class Majority_Element_p169_sol1 {
    public int majorityElement(int[] nums) {
        //Moore voting algorithm
        
        //counter for curr candidate
        int count = 0;
        int candidate = 0;
        
        for(int num : nums){
            //set candidate if counter = 0
            //Notice: since we will set candidate to num if count = 0, we will never have a negative count
            if(count == 0) candidate = num;
            
            if(num != candidate){
                count--;
            }else{
                count++;
            }
        }
        
        return candidate;
    }
}
