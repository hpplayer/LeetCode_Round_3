import java.util.*;
/*
Largest Rectangle in Histogram

(figures are in file p084)
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
find the area of largest rectangle in the histogram.
             6         
         5   __   
         __ |  |
        |  ||  | 2    3
 2   1  |  ||  |     __
 __     |  ||  | __ |  |
|  | __ |  ||  ||  ||  |
|  ||  ||  ||  ||  ||  |
Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
             6         
         5   __   
         __ |  |
        |\\||\\| 2    3
 2   1  |\\||\\|     __
 __     |\\||\\| __ |  |
|  | __ |\\||\\||  ||  |
|  ||  ||\\||\\||  ||  |

The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given height = [2,1,5,6,2,3],
return 10.
*/

/**
 * Stack problem
 * 
 * We use a stack to hold non-descending bars. It means we can still extend rectangles from those bars. If incoming bar is low bar,
 * then we will start pop all taller bars from stack while updating result if necessary. To calculate the area of rectangle, we need
 * the height of popped bar and the index of the bar before the popped bar in the stack. To cover boundary cases, we will stop the loop
 * when i > height.length
 * 
 * Remark:
 * Time complexity: Each bar will be in the stack once, so the time complexity should be O(n)
 * Space complexity: O(n) in case all incoming bars are non-descending.
 * 
 * This solution is similar to Remove_Duplicate_Letters_p316_sol1, where we also need to maintain a stack
 * with certain order
 * 
 * @author hpPlayer
 * @date Nov 6, 2015 12:57:36 AM
 */
public class Largest_Rectangle_in_Histogram_p84_sol1 {
    public int largestRectangleArea(int[] height) {
        //we use stack to store non-descending bar
        //to calculate the length, we need store index in the stack
        Stack<Integer> stack = new Stack<Integer>();
        int result = 0;
        
        //to include boundary case, we let loop goes when i == height.length
        for(int i = 0; i <= height.length; i++){
            //get current height
            int ht = i == height.length? 0 : height[i];
            if(stack.isEmpty() || height[stack.peek()] <= ht){
                //we will put non-descending bar into the stack
                stack.push(i);
            }else{
                //if current bar is a tall bar, we will pop out all taller bar from stack
                while(!stack.isEmpty() && height[stack.peek()] > ht){
                    //get the height of poped bar
                    int tallBar = height[stack.pop()];
                    //get width of poped bar
                    int width = stack.isEmpty()? i : i - stack.peek() -1;
                    //update result if necessary
                    result = Math.max(result, width * tallBar);
                }
                //no we can push i to stack and still make non-descending order
                stack.push(i);
            }
        }
        
        return result;
    }
}
