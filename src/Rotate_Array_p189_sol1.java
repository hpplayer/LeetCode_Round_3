/*
189. Rotate Array

Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

[show hint]

Hint:
Could you do it in-place with O(1) extra space?
Related problem: Reverse Words in a String II
*/

/**
 * Reversal alg
 * 
 * This is a known alg to rotate the array
 * We firstly reverse the first and second part of the input, here part means the part before len - k and part after len - k
 * Then we reverse the input as a whole
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * Remark:
 * The way we used to rotate array in this solution can also be used in Reverse_Words_in_a_String_II_p186_sol1
 * sol2 provides another famous array-rotation algorithm: block-swapping solution, which is also using constant space
 * 
 * @author hpPlayer
 * @date Mar 11, 2016 12:05:07 PM
 */
public class Rotate_Array_p189_sol1 {
    public void rotate(int[] nums, int k) {
        //classical reversal alg, firsly reverse first part and second, then reverse whole input array
        
        int len = nums.length;
        
        //in case input k > len, then we use k%len to get real nums that we need rotate to right
        k = k%len;
        
        //firstly reverse first and second parts
        //len and k are 1 based, now we need to convert to 0 based index, so we - 1
        reverse(0, len - k - 1, nums);
        reverse(len - k, len - 1, nums);
        
        //then reverse input as a whole
        reverse(0, len - 1, nums);
    }
    
    public void reverse(int start, int end, int[] nums){
        while(start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
