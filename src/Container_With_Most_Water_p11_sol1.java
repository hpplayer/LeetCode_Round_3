/*
11. Container With Most Water

Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
n vertical lines are drawn such that the two end points of line i is at (i, ai) and (i, 0).
Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container.
*/

/**
 * Greedy + two pointer solution
 * 
 * We use two pointers as two boundaries of the container
 * To get the area of largest container, we will always move the pointer with lower height, as it is the bottleneck of the container.
 * Replacing lower edge will more likely give us a larger container
 * 
 * Time complexity: 
 * O(N) as we need scan input array once
 * Space complexity:
 * O(1) as we don't need extra space
 * 
 * Remark:
 * The problem defines the bottom of container to be the gap between two pointers. So in case left pointer = 0 and right pointer = 1,
 * the bottom len would be 1 not 2
 * @author hpPlayer
 * @date Apr 26, 2016 10:22:53 PM
 */
public class Container_With_Most_Water_p11_sol1 {
    public int maxArea(int[] height) {
        //greedy + two pointer solution. To trap most water, we will always move the pointer that has lower height,
    	//as it is the bottleneck of the container
        
        int result = 0;
        
        int left = 0, right = height.length - 1;
        
        //the problem defines the bottom of container to be the gap between two pointers, so the shortest gap would
        //be left + 1 = right
        while(left < right){
            //update result if possible
            //Notice: we use (right - left) to get the bottom of container
            result = Math.max(result, Math.min(height[left], height[right]) * (right - left)  );
            //to get a larger container based on current status, it is more likely to be got by replacing the boundary with lower height
            //for equal case, we can move either boundary
            if(height[left] < height[right]){
                left++;
            }else{
                right--;
            }
        }
        
        return result;
    }
}
