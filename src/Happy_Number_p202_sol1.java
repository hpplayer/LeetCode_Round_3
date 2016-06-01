import java.util.HashSet;

/*
202. Happy Number

Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example: 19 is a happy number

1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1
*/

/**
 * HashSet solution
 * 
 * We use a hashset to store all previously generated numbers. If next input is in set, then we just need to check if 
 * this number is 1. If it is, then return true, otherwise return false
 * 
 * Time complexity: O(1), it is hard to determine the time complexity, but it seems all numbers will be finally
 * converted to 1 or infinite loop in exponential time, and the max len of input is known (int_max), so we can use O(1) here
 * Space complexity: O(1) same as above
 * 
 * Remark:
 * Infinite loop will be same in each "unhappy" number, so we don't need to store previous generated numbers, we can
 * just use a known and a small number (ex: 4) in the infinite loop to check if the input is happy or unhappy.
 * But this is tricky if we don't know this before. So I did not list related solution here
 * 
 * Sol1 is hashSet solution
 * Sol2 is slow and faster pointer solution
 * 
 * @author hpPlayer
 * @date May 31, 2016 11:55:42 PM
 */
public class Happy_Number_p202_sol1 {
    public boolean isHappy(int n) {
        //Hashset solution. We use a HashSet to record all generated nums
        HashSet<Integer> hs = new HashSet<Integer>();
        
        //We will stop the loop once we are visiting a visited n, which means we have fallen into a loop
        while(!hs.contains(n)){
            hs.add(n);
            n = getNumber(n);
        }
        
        return n == 1;
    }
    
    public int getNumber(int n){
        int result = 0;
        
        while(n > 0){
            result += (n%10) * (n%10);
            result /= 10;
        }
        
        return result;
    }
}
