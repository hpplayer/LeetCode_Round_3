/*
276. Paint Fence

There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color.

Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.
*/

/**
 * DP solution
 * 
 * First of all, we need to be clear about what the problem asks. The problem states that we need to paint n fences with k colors, but only allows at 
 * most two adjacent fence to have same color. That means we don't have three adjacent fence having same color, but we may have multiple two adjacent
 * fences having same color
 * 
 * Similar to problem House_Robber_p198. Similarly, now we have two choices for each fence, we can whether paint it with same color or different color
 * So we use two variable to count the ways we get if we paint it with same color or with different color
 * If we need paint same color, then we require previous fence have different color with its previous one
 * If we need paint different color, then we don't have restriction, we can pick both ways
 * 
 * Then the total number of ways is just the sum of this two variables
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Feb 25, 2016 11:08:28 AM
 */

public class Paint_Fence_p276_sol1 {
    public int numWays(int n, int k) {
        //This problem asks us to calculate the ways to paint fence that allows at most two adjacent fences having same color
        //which means we can have RGB or RRG or RGG, etc. So math equation will not work here 
        //DP solution. Our program will follow n, we need to handle n = 0, 1, 2 respectively. For n >= 3, our program will work
        
        //if n == 0, then return 0
        if(n == 0) return 0;
        //if n == 1, then return 1
        if(n == 1) return k;
        
        //if n == 2, then we have two cases:
        //so the total num of ways is just the sum of the way we paint current fence either same color or different color
        int sameColor = k;
        int diffColor = k * (k-1);
        
        //for n = 3 or above
        for(int i = 3; i <= n; i++){
            //since we will update sameColor next step, but we still need it later, so we have to back it up
            int temp = sameColor;
            
            //if we want current fence to have same color with last fence, then we can only choose diffColor ways from last fence
            sameColor = diffColor;
            
            //if we choose pick different color, then we can pick both ways from last fence
            diffColor = (temp + sameColor) * (k-1);
        }
        
        //total ways is just the sum
        return diffColor + sameColor; 
    }
}
