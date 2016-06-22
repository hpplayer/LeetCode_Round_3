import java.util.TreeSet;
/*
363. Max Sum of Rectangle No Larger Than K

Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.

Example:
Given matrix = [
  [1,  0, 1],
  [0, -2, 3]
]
k = 2
The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no larger than k (k = 2).

Note:
The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?
*/

/**
 * 2D Kadane's algorithm + 1D maxSum problem with sum limit k
 * 
 * This problem can be divided into two parts:
 * first part is to calculate the sum of rectangle
 * second part is to find the rectangle with largest sum < k
 * 
 * For first part, we can use 2D kadane's algorithm to calculate the sum
 * The basic idea is using two sweeping lines to fix the left and right boundaries, then accumulate sums
 * Each time we move right sweeping line, we will update sums by adding values from new right boundaries
 * Then we can use Kadane's algorithm to get the max sum of rectangle by checking those sums
 * ex:
 * First round
 * 		 sum
 * 1 -2   1    left: 0, right : 0 
 * 2  1   2
 * 3 -1   3  Using Kadane's alg to find the maxSum subarray which is 1+2+3 = 6
 * 
 * Second round
 * 		 sum
 * 1 -2   1-2 = -1  left: 0, right = 1
 * 2  1	  2+1 =  3	
 * 3 -1   3-1 =  2 Using Kadane's alg to find the maxSum subarray which is 2+3 = 5
 * 
 * Therefore the rectangle with MaxSum would be 1,2,3
 * 
 * For second part, we can use 1D maxSum problem with sum limit k to solve it
 * sums[i,j] = sums[i] - sums[j].
 * sums[i,j] is target subarray that needs to have sum <= k
 * sums[j] is known current cumulative sum
 * 
 * We use a TreeSet to store previous accumulated subarray sums. We just need to find the subarray that has the 
 * smallest sum >= curr accumulated sum - k.
 * We just need to apply this rule to first part when looking for the max subarray sum
 * 
 * Time complexity: O( Min(m, n)^2 * Max(m, n) * log(Max(m,n)) 
 * Space complexity: O( max(m,n))
 *  
 * Remark:
 * For the follow-up What if the number of rows is much larger than the number of columns?
 * We just need to do row scan in the outer loop and do col scan in inner loop. 
 * 
 * @author hpPlayer
 * @date Jun 21, 2016 10:47:04 PM
 */
public class Max_Sum_of_Rectangle_No_Larger_Than_K_p363_sol1 {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        //2D Kadane's algorithm + 1D maxSum problem with sum limit k
        //2D subarray Sum solution
        
        //boundary check
        if(matrix.length == 0) return 0;
        
        int m = matrix.length, n = matrix[0].length;
        int result = Integer.MIN_VALUE;
        
        //outer loop should use smaller axis
        //now we assume we have more rows than cols, therefore outer loop will be based on cols 
        for(int left = 0; left < n; left++){
            //array that accumulate sums for each row from left to right 
            int[] sums = new int[m];
            for(int right = left; right < n; right++){
                //update sums[] to include values in curr right col
                for(int i = 0; i < m; i++){
                    sums[i] += matrix[i][right];
                }
                
                //we use TreeSet to help us find the rectangle with maxSum <= k with O(logN) time
                TreeSet<Integer> set = new TreeSet<Integer>();
                //add 0 to cover the single row case
                set.add(0);
                int currSum = 0;
                
                //Doing 1D maxSum problem with sum limit k algorithm on sums[] to find the maxSum <= k
                for(int sum : sums){
                    currSum += sum;
                    //we use sum subtraction (curSum - sum) to get the subarray with sum <= k
                    //therefore we need to look for the smallest sum >= currSum - k
                    Integer num = set.ceiling(currSum - k);
                    if(num != null) result = Math.max( result, currSum - num );
                    set.add(currSum);
                }
            }
        }
        
        return result;
    }
}
