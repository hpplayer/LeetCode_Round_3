import java.util.*;

/*
Strobogrammatic Number II

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

For example,
Given n = 2, return ["11","69","88","96"].

Hint:

Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.
*/

/**
 * Backtracking problem
 * 
 * Standard backtracking problem, we use a variable k to track how many digits we have added to result. We use two strings left
 * and right to hold the temp string for left and right part. In the case k == 1, we can only add 0/1/8, otherwise we can add
 * 0-0, 1-1, 8-8, 6-9 9-6 to two parts.
 * 
 * Remark:
 * 1. remember check 6-9 and 9-6 separately as they are different in the string
 * 2. remember heading 0s, we don't allow a valid result string with length > 1 and still has heading 0s
 * 
 * Time complexity: 
 * backtracking->not easy to analyze
 * Space complexity:
 * backtracking->not easy to analyze
 * 
 * Sol1 is recursive solution(from two sides to middle)
 * Sol2 is iterative solution(from middle to two sides)
 * 
 * @author hpPlayer
 * @date Nov 17, 2015 11:10:18 PM
 */
public class Strobogrammatic_Number_II_p247_sol1 {
    public List<String> findStrobogrammatic(int n) {
        //backtracking solution, we create string from two sides to middle
        List<String> result = new ArrayList<String>();
        DFS(result, "", "", n);
        return result;
    }
    
    public void DFS(List<String> result, String left, String right, int n){
        if(n == 0){
            //boundary case 1, even length
            result.add(left + right);
            return;
        }   
        
        if(n == 1){
            //boundary case 2, odd length, only 0/1/8 can be middle Strobogrammatic number
            result.add(left + 0 + right);
            result.add(left + 1 + right);
            result.add(left + 8 + right);
            return;
        }

         //general cases
         
         //to make code more clean we use array to read Strobogrammatic numbers
         int[][] base = {{0, 0}, {1, 1}, {8, 8}, {6, 9}, {9, 6}};
         
         //we can't add 0s to the head if our result string already >= 1
         for(int i = left.length() >= 1? 1 : 0; i < base.length; i++){
             DFS(result, left + base[i][0], right + base[i][1], n-2);
         }
    }
}
