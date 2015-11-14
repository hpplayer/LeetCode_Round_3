/**
 * Two pointer solution
 * 
 * We use two pointers to scan the array from two directions. We use another two variables to record the height of
 * max bar in two directions. We should discard Math.min(max_left, max_right) first as it blocks further bars to
 * trap water. So we always try to move pointer from the direction that has shorter max bar. Before we move pointer,
 * we need to check the bar height in current cell, if it is shorter than the shorter max bar, then we can trap water
 * otherwise we have to update max bar height, and couldn't trap water
 * 
 * So this solution uses one-pass to solve the problem and does not require extra space. Therefore it is better than
 * sol1 
 * 
 * Time complexity: O(n)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Nov 14, 2015 12:38:11 AM
 */
public class Trapping_Rain_Water_p42_sol2 {
    public int trap(int[] height) {
        //boundary check
        if(height.length == 0) return 0;
        
        int n = height.length;
        
        //two pointers
        int left = 1;
        int right = n - 2;
        
        //two variables to hold the max bar height from left/right
        int maxLeft = height[0];
        int maxRight = height[n - 1];
        
        int result = 0;
        
        //scan each cell in height
        while(left <= right){
            if(maxLeft < maxRight){
                //max left bar is shorter, we want to skip it so it won't block further bars to trap water
                if(maxLeft < height[left]){
                    //can't trap water
                    maxLeft = height[left];
                }else{
                    //can trap water
                    result += maxLeft - height[left];
                }
                //skip this shorter bar
                left ++;
            }else{
               //max right bar is shorter, we want to skip it so it won't block further bars to trap water  
                if(maxRight < height[right]){
                   //can't trap water
                   maxRight = height[right];
                }else{
                  //can trap water
                  result += maxRight - height[right];
                }
                //skip this shorter bar
                right --;
            }
        }
        
        return result;
    }
}
