import java.util.*;
/*
Strobogrammatic Number

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

For example, the numbers "69", "88", and "818" are all strobogrammatic.
*/

/**
 * HashMap + two pointer solution
 * 
 * It is an intuitive solution. There are only following possible pairs: 0-0, 1-1, 8-8, 6-9. So we just use two pointers to scan
 * the string from head and tail and check if char pair is in above pairs
 * 
 * Time complexity: O(n)
 * Space complexity: O(1)
 * 
 * Sol1 is standard HashMap solution
 * Sol2 is a similar solution but use string to replace sol1
 * 
 * @author hpPlayer
 * @date Nov 17, 2015 10:41:29 PM
 */

public class Strobogrammatic_Number_I_p246_sol1 {
    public boolean isStrobogrammatic(String num) {
        Map<Character, Character> hs = new HashMap<Character, Character>();
        hs.put('1', '1');
        hs.put('0', '0');
        hs.put('8', '8');
        hs.put('6', '9');
        hs.put('9', '6');
        
        int start = 0, end = num.length()-1;
        
        while(start <= end){
            char a = num.charAt(start);
            char b = num.charAt(end);
            
            if(hs.containsKey(a) && b == hs.get(a)){
                start++;
                end--;
            }else{
                return false;
            }
        }
        
        return true;
    }
}
