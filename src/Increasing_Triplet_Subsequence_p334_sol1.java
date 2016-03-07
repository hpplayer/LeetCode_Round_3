/*
334. Increasing Triplet Subsequence

Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:
Return true if there exists i, j, k 
such that arr[i] < arr[j] < arr[k] given 0 ¡Ü i < j < k ¡Ü n-1 else return false.
Your algorithm should run in O(n) time complexity and O(1) space complexity.

Examples:
Given [1, 2, 3, 4, 5],
return true.

Given [5, 4, 3, 2, 1],
return false.
*/	

/**
 * Trick solution
 * 
 * We use two variables to hold the two min numbers. 
 * We always update firstMin first, so if we can update secondMin, then we already have two elements in increasing order
 * Accordingly, in order to update these two elements, we need firstly update firstMin. Update firstMin will not affect
 * our result since we use secondMin to look for the third element. In case we have to two new mins that can replace old 
 * firstMin and SecondMin, then we can update our old increasing subsequence accordingly. 
 * If we found current cell has a number > SecondMin, then we must have three elements in increasing order, and we can
 * safely return true
 * 
 * time complexity: O(N) one pass
 * Space complexity: O(1)
 * 
 * Remark:
 * This problem can also be solved by using left and right array, but it needs at least two pass and extra space
 * So I did not list it here
 * 
 * @author hpPlayer
 * @date Mar 6, 2016 7:52:55 PM
 */
public class Increasing_Triplet_Subsequence_p334_sol1 {
    public boolean increasingTriplet(int[] nums) {
        //Trick solution. We use two variables to record the past two small numbers. The first variable will always
        //record the min value in the past, we will use it to start our new increasing Triplet. It is always smaller
        //than second variable, but many not necessary before it.
        //The second variable tell us the second min value, if we have a valid value in second variable, then 
        //we at least have a increasing sequence with two cells. But as states above, since first variable can be
        //updated to a new cell. Second variable may not always behind first variable, but we are sure there must
        //be a smaller cell than second variable before it
        //so if found a cell > its value, then we have found the increasing Triplet
        
        int firstMin = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;
        
        for(int num : nums){
            if(num > secondMin){
                //if we found a num > secondMin, then we have found a Triplet 
                return true;
            }else if(num <= firstMin){
                //we always update firstMin to find the start of the new increasing subsequence which has smaller
                //value therefore more easier to find the third cell
                //Notice: we need to catch num < firstMin, otherwise secondMin will have same value with firstMin
                //and violate our rule
                firstMin = num;
            }else if(num < secondMin){
                //since we use secondMin to find the result, we need update it very carefully
                //we only update secondMin if current cell is larger than firstMin and smaller than previous secondMin, i.e. a new two-element subsequence is found
                secondMin = num;
            }
        }
        
        //can't find result, return false
        return false;
    }
}
