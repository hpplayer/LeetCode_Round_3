/*
265. Paint House II

There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Follow up:
Could you solve it in O(nk) runtime?
*/


/**
 * DP solution
 * 
 * This is an advanced version of Paint_House_I_p256_sol1, but the basic idea is same. 
 * We will update current rows' dp value based on the last rows' dp value
 * We record the min dp value and second min dp value from last row, we also record the index for min dp value
 * In the process of updating current row, for the index that gives min dp value in last row, we will use the second min dp value
 * for other indexes, we will update dp value based on the min dp value from last row
 * 
 * Time complexity: O(nk)
 * Space complexity: O(1)
 * @author hpPlayer
 * @date Mar 2, 2016 6:38:19 PM
 */
public class Paint_House_II_p265_sol1 {
    public int minCostII(int[][] costs) {
        //dp solution. Advanced version of Paint_House_I_p256_sol1.
        //we will record the smallest and second smallest number in last row, say smallest color is "a", second smallest color is "b"
        //in current row for all other colors, we will update dp value based on the dp value from "a"
        //for color "a", we will update dp value based on the dp value from "b"
        //Since we only care about two colors and the index of smallest color, we can use three variables to finish the job
        
        //boundary check
        if(costs.length == 0) return 0;
        
        int m = costs.length, n = costs[0].length;
        
        //we set initial min value to be 0 and index to be -1, so we can correctly update first row
        int prevMin1 = 0;
        int prevMin2 = 0;
        int prevIndex = -1;
        
        for(int i = 0; i < m; i++){
            //to avoid the impact from early update, we use three new variables to hold temp values in current row
            //in order to update currMin1 and currMin2 correctly, we set their initial value to be int.max
            int currMin1 = Integer.MAX_VALUE, currMin2 = Integer.MAX_VALUE, currIndex = -1;
            
            for(int j = 0; j < n; j++){
                //if color j is smallest color in last row, we will add with prevMin2, otherwise add with prevMin1
                int val = costs[i][j] + (j == prevIndex? prevMin2 : prevMin1);
                
                if(val < currMin1){
                    //update smallest color in current row
                    currMin2 = currMin1;
                    currMin1 = val;
                    currIndex = j;
                }else if(val < currMin2){
                    //update second smallest color in current row
                    currMin2 = val;
                }
            }
            
            //update three prev variables
            prevMin1 = currMin1;
            prevMin2 = currMin2;
            prevIndex = currIndex;
        }
        
        return prevMin1;
    }
}
