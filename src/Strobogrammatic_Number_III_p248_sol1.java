/*
Strobogrammatic Number III

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

For example,
Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.

Note:
Because the range might be a large number, the low and high numbers are represented as string.
*/

/**
 * Backtracking solution
 * 
 * This solution is an advanced version of Strobogrammatic_Number_III_p248_sol1. We just need to generate all Strobogrammatic
 * numbers between low and high. To achieve that we need to scan each length between len(low) and len(high), and generate 
 * Strobogrammatic numbers accordingly.
 * 
 * So should we include all numbers we generated? No, we generate Strobogrammatic numbers based on length. So if given len is 
 * same with len(low), we may have numbers smaller than low. Similarly if given len is same with len(high), we may have numbers
 * larger than high. So we need to be careful with two cases.
 * 
 * The input format is string, so we need to use string.compareTo() to compare numbers. We need be careful that string.compareTo()
 * only compare the lexicographical order that's say if we have string a "5" and string b "11111", then a > b in this situation.
 * So we need firstly check if the length is same then use string.compareTo() to compare the value
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
 * @date Nov 18, 2015 1:32:59 AM
 */
public class Strobogrammatic_Number_III_p248_sol1 {
    public int strobogrammaticInRange(String low, String high) {
        //the basic idea is generating all Strobogrammatic Numbers between low and high
        //the way we used to generate numbers in same with Strobogrammatic Number II
        
        count = 0;
        min = low;
        max = high;
        
        //check each length among len(low) and len(high) and generate all Strobogrammatic number with this len
        for(int i = low.length(); i <= high.length(); i++){
            DFS("", "", i);
        }
        
        return count;
    }
    
    int count;
    String min;
    String max;
    
    public void DFS(String left, String right, int n){
        if(n == 0){
            //boundary case 1, even length
            String temp = left + right;
            
            if(isValid(temp)) count++;
            
            return;
        }
        
        if(n == 0){
            //boundary case 2, odd length
            int[] offset = {0, 1, 8};
            for(int i = 0; i < 3; i++){
                if(isValid(left + offset[i] + right)) count++;
            }
            return;
        }        
        
        //use int[][] matrix to save space
        int[][] base = { {0, 0}, {1, 1}, {8, 8}, {6, 9}, {9, 6} };
        
        //we build string from two sides to mid
        //to avoid heading 0s, we only begin add 0s if we have chars in left
        for(int i = left.length() > 0? 0 : 1; i < base.length; i++){
            DFS(left + base[i][0], base[i][1] + right, n);
        }
        
    }
    
    
    public boolean isValid(String s){
        //what kind of number should we filter out? number that has same length with low/high but smaller/larger than low/high
        
        //same length with min, but smaller than it
        boolean rej1 = min.length() == s.length() && s.compareTo(min) < 0;
        //same length with max, but larger than it
        boolean rej2 = max.length() == s.length() && s.compareTo(max) > 0;    
        
        //if s is valid not meeting requirement in rej1 and rej2, return true
        if(!rej1 && !rej2){
            return true;
        }        
        return false;
    }
}
