/**
 * Two pointer solution
 * 
 * This approach use one pointer to scan the input array and use another pointer to point the virtual array which only
 * contains valid input. This approach will keep the order from input array
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * Sol1 provides a two-pointer approach which will keep update values in virtual array
 * Sol2 provides another approach which remove invalid values from input array and works better when
 * we have rare invalud elements in the input array
 * 
 * @author hpPlayer
 * @date May 4, 2016 11:15:49 PM
 */
public class Remove_Element_p27_sol1 {
    public int removeElement(int[] nums, int val) {
        //two pointer solution. One pointer points to curr writable cell, another pointer is used to scan array
        int index = 0;
        for(int i = 0; i < nums.length; i++){
            //we will put valid value into curr writable cell
            //if nums[i] is invalid, then we will not place it in curr writable cell
            if(nums[i] != val){
                nums[index++] = nums[i];
            }
        }
        return index;
    }
}
