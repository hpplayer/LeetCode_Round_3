/*
344. Reverse String

Write a function that takes a string as input and returns the string reversed.

Example:
Given s = "hello", return "olleh".
*/

/**
 * Two pointer solution
 * 
 * There are many ways to solve this problem. But two pointer solution is the fastest one.
 * We iteratively swap chars at left and right index until they meet.
 * 
 * Time complexity: O(n/2)
 * Space complexity: O(n)
 * 
 * We can also use built-in reverse() with one line solution: return new StringBuilder(s).reverse().toString();
 * 
 * @author hpPlayer
 * @date May 12, 2016 9:05:44 PM
 */
public class Reverse_String_p344_sol1 {
    public String reverseString(String s) {
        //Two pointer solution. swap chars at left and right pointer
        
        char[] strs = s.toCharArray();
        
        int left = 0, right = strs.length - 1;
        
        while(left < right){
            char temp = strs[left];
            strs[left] = strs[right];
            strs[right] = temp;
            left++;
            right--;
        }
        
        return new String(strs);
    }
}
