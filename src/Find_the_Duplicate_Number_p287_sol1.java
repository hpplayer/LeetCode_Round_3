/*
Find the Duplicate Number

Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate element must exist. Assume that there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant extra space.
Your runtime complexity should be less than O(n2).
*/

/**
 * Cycle detection problem
 * 
 * This problem is very similar to problem Linked List Cycle II (p142).
 * 
 * Here is the idea:
 * We use the value in the array as the index of next step. We move two pointers (fast and slow) in the array. Fast pointer moves two times
 * faster than slow pointer. We will stop the movement when they meet for the first time. Then we reset the fast pointer, and begin move 
 * faster pointer same speed with slow pointer. The next meeting point will be the duplicate number 
 * 
 * Here is the analysis:
 * The value in array starts from 1 to n and we have n + 1 values. This statement firstly means we will at least have one number repeats
 * twice since we have n+1 holes but we only have n different balls. This statements also implies that we will never get stuck in the first
 * movement since we don't have value 0 in the array. But the most important information we can get from this statement is that we will have a 
 * cycle in the array if we use the value as the index of next movement and the index contains duplicate will be the entry point to the cycle.
 * This is because we are looking for next index with the value in current index. With duplicate, after we pass current duplicate, we will
 * always reach the next duplicate number and looks like we go through a cycle and reach the same point.
 *  
 * For example like below index 1 - 3 forms a cycle, and the entry point is indicated by index 0 
 * index 0 1 2 3    
 * value 2 2 3 1
 * For example like below, we also have cycles with entry point indicated by duplicates
 * index 0 1 2 3  or index 0 1 2 3 or index 0 1 2 3
 * value 2 3 1 3     value 2 3 1 1	  value 2 2 1 1
 * 
 * This algorithm runs in O(n) time and costs O(1) space
 * 
 * 
 * Sol1 is the cycle detection method which is faster but harder to come up with
 * Sol2 is the binary search method which is slower but easier to come up with
 * 
 * @author hpPlayer
 * @date Jan 18, 2016 3:16:03 PM
 */
public class Find_the_Duplicate_Number_p287_sol1 {
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        
        //step 1. find the meeting point in loop
        do{
         slow = nums[slow];
         fast = nums[nums[fast]];
        }while(slow != fast);
        
        //step 2. reset fast pointer, so the next meeting point will be the entry point which is the duplicate num
        fast = 0;
        
        while(slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        
        return slow;
    }
}
