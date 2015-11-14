/*
* Trapping Rain Water 

* Given n non-negative integers representing an elevation map where the width of each bar is 1,
* compute how much water it is able to trap after raining.
*
* For example, 
* Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
*     ^                                             
*     |                                             
*   3 |                       +--+                  
*     |                       |  |                  
*   2 |          +--+xxxxxxxxx|  +--+xx+--+         
*     |          |  |xxxxxxxxx|  |  |xx|  |         
*   1 |   +--+xxx|  +--+xxx+--+  |  +--+  +--+      
*     |   |  |xxx|  |  |xxx|  |  |  |  |  |  |      
*   0 +---+--+---+--+--+---+--+--+--+--+--+--+----->
*       0  1   0  2  1   0  1  3  2  1  2  1        
* 
* The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 
* 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
*     
*/

/**
 * Scanning the array forward and backward solution
 * 
 * For each bar, we need get the height of left max bar and right max bar. Whether current bar can trap water depend on
 * the min height of above two max bars. 
 * 
 * In this solution, we scan the array forward and backward to get the max left/right bar for each cell. So then we can
 * calculate how much water we can trap for each bar
 * 
 * Remark:
 * If we really scan the array three times (forward/backward/calculating trapping water), we will get LTE. So in this solution
 * we combine forward/backward scan into one loop to save space
 * 
 * Time complexity: O(n)
 * Space complexity: O(n)
 * 
 * Solution Candy_p135_sol1 also uses the similar approach that scanning the array forward and backward to get left/right boundary
 * 
 * Sol1 is two-pass with extra array solution
 * Sol2 is one-pass with constant space solution
 * 
 * so sol2 is better
 * 
 * @author hpPlayer
 * @date Nov 14, 2015 12:03:26 AM
 */
public class Trapping_Rain_Water_p42_sol1 {
    public int trap(int[] height) {
        //boundary check
        if(height.length == 0) return 0;
        
        int n = height.length;
        
        int[] left = new int[n];
        int[] right = new int[n];
        
        //Initialize left[0] and right[n-1]
        left[0] = height[0];
        right[n-1] = height[n-1];
        
        //combine updating left/right arrray together to save time
        //for each array, we just need update n -1 cells
        for(int i = 1; i < n; i++){
            //get index in right
            int j = n - 1 -i;
            //update cell value to be the max height so far
            left[i] = Math.max(left[i-1], height[i]);
            right[j] = Math.max(right[j+1], height[j]);
        }
        
        int result = 0;
        
        //scan the height[] one more time to calculate trapping water
        for(int i = 1; i < n - 1; i++){
            //get the min height of max left/right bar
            int min = Math.min(left[i-1], right[i+1]);
            //if height[i] < min, then we can collect water on top of current bar
            if(height[i] < min) result += min - height[i];
        }
        
        return result;
    }
}
