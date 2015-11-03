/*
Climbing Stairs

You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
*/

/**
 * A classic DP problem.
 * It is similar to Fibonacci  sequence, we just keep two variables to hold previous calculated results
 * @author hpPlayer
 * @date Nov 2, 2015 11:17:28 PM
 */
public class Climbing_Stairs_p70_sol1 {
    public int climbStairs(int n) {
        if(n == 1) return 1;
        if(n == 2) return 2;
        
        int a = 1;
        int b = 2;
        
        for(int i = 3; i <= n; i++){
            int temp = a;
            a = b;
            b = temp + a;
        }
        
        return b;
    }
}
