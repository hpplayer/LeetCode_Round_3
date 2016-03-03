/*
256. Paint House

There are a row of n houses, each house can be painted with one of the three colors: red, blue or green.
The cost of painting each house with a certain color is different.
You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on...
Find the minimum cost to paint all houses.

Note:
All costs are positive integers.
*/

/**
 * DP solution
 * 
 * For each row (house), we just need to update each color based on the prev house.
 * If we choose color i, then we choose the min dp value from prev house in other two colors.
 * Finally we just need to check which color gives the min value
 * Since we only have 3 colors in this problem. We use three variables to hold the dp value.
 * To avoid update too early, we also use other three variables to help update dp value
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * sol2 is the matrix version which costs O(N) space. I leave it here as a naive reference
 * 
 * @author hpPlayer
 * @date Mar 2, 2016 6:18:00 PM
 */
public class Paint_House_I_p256_sol1 {
    public int minCost(int[][] costs) {
        //DP solution. For cell j in house i. Its dp value should be the min value from other two colors + its value
        //Since we only need last line to update dp value in current line, we can avoid the use of dp matrix, and use three variables instead 
        
        int n = costs.length;
        
        //three variables to hold dp value from last line    
        //set initial value to be 0, so we can correctly update first row
        int prevRed = 0;
        int prevBlue = 0;
        int prevGreen = 0;
        
        for(int i = 0; i < n; i++){
            //to avoid update dp value too early, we use other 3 variables to hold new dp value       
            int currRed = Math.min(prevBlue, prevGreen) + costs[i][0];
            int currBlue = Math.min(prevRed, prevGreen) + costs[i][1];
            int currGreen = Math.min(prevBlue, prevRed) + costs[i][2];
            
            //then we update three prev varibles
            prevRed = currRed;
            prevBlue = currBlue;
            prevGreen = currGreen;
            
        }
        
        return Math.min(prevRed, Math.min(prevBlue, prevGreen));
    }
}
