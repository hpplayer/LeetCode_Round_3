/**
 * Two-pointer approach
 * 
 * Sol1 will always update values in the virtual valid array. In case we have an invalid cell in index 0,
 * all later values will be left shifted. Order is kept, but we have done many unnecessary steps
 * Sol2 here basically just remove the invalid cell from input cell, and replace its value by the curr last
 * valid cell value. So the number of operations = the number of invalid cells in our input array
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * Remark:
 * The idea here is a little bit similar to Sort_Colors_p75_sol1, as we both need to swap curr values
 * with values from tail, and then recheck new value in curr index again
 * 
 * @author hpPlayer
 * @date May 4, 2016 11:23:41 PM
 */
public class Remove_Element_p27_sol2 {
    public int removeElement(int[] nums, int val) {
        //two-pointer approach, we will swap invalid cell with last valid cell, so we dont need to touch valid cells
        
        int len = nums.length;
        int i = 0;
        
        //while we still have unvisited cell in the input
        while(i < len){
            if(nums[i] == val){
                //if curr cell is invalid, swap it with last valid cell
                //since we don't care cells beyond new array, we just reset invalud cell value to be the value of last valid cell
                nums[i] = nums[len - 1];
                //len of valid array - 1
                len--;
            }else{
                //otherwise we just leave valid cell untouched
                i++;
            }
        }
        
        return len;
    }
}
