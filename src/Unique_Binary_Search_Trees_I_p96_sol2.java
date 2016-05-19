/**
 * Iterative solution
 * 
 * In this solution, we make use of the property of input that input number is also the size of target tree.
 * Since we have numbers from 1 to n, we can build subtrees for any given number <= n. 
 * Therefore the numbers of ways to build unique BSTs is just the numbers of ways we can split the size
 * To split the size, we know left subtree can be any size from 0 to i - 1 (excluding root itself), accordingly the right tree can be
 * any size from i - 1 to 0.
 * 
 * In short:
 * Starts with boundary case 0 and 1, we can iteratively build the trees by accumulating previous results from all numbers < i
 * 
 * Time complexity: O(n^2)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date May 19, 2016 1:42:21 AM
 */
public class Unique_Binary_Search_Trees_I_p96_sol2 {
    public int numTrees(int n) {
        //iterative solution. We build the tree based on size instead of range
        //Input n is also the size of tree. Since we have all numbers from 1 to n. we can build subtree for each number < n 
        //this iterative solution makes use of this fact, and relate nums of ways to build trees with the way to split size of tree 
        
        //boundary check
        if(n == 0 || n == 1) return 1;
        
        //create a dp array with n + 1 to include 0 case
        int[] dp = new int[n+1]; 
        dp[0] = dp[1] = 1;
        
        for(int i = 2; i <= n; i++){
            //for new subtree of i size, we can split it into following ways
            for(int j = 0; j < i; j++){
                //j is the size of left subtree, excluding root i, i - 1 - j is the size of right subtree
                dp[i] += dp[j] * dp[i-1-j];
            } 
        }
        
        return dp[n];
    }
}
