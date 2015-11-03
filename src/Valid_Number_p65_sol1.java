/*
Valid Number

Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
Note: It is intended for the problem statement to be ambiguous.
You should gather all requirements up front before implementing one.
*/

/**
 * This problem has so many boundary cases, we need to care about all of them.
 * 
 * boundary cases include
 * 1. sign "+/-"   (we allow in both parts, if it appears in mid, we need to make sure we have "e" before it, otherwise it is invalid)
 * 2. Whitespace " " (we allow it before first part or after second part, but not in mid)
 * 3. decimal point "." (we only allow it existing in first part, and we allow no digits before it or after it, but not no digits in both)
 * 4.  special sign "e" (it indicates we have two parts in the input, if we have "e", we must have digits before AND after "e")
 * @author hpPlayer
 * @date Nov 2, 2015 3:07:36 PM
 */

public class Valid_Number_p65_sol1 {
    public boolean isNumber(String s) {
        if(s == null || s.length() == 0) return false;
        
        //remove heading and trailing whitespaces
        s = s.trim();
        
        int index = 0;
        int len = s.length();
        boolean hasDigit = false;
        boolean hasE = false;
        
        
        //first part, the complete form is "+/- xxx.xxx", and we allow only part of them appear
        
        //check "+/-"
        if(index < len && (s.charAt(index) == '+' || s.charAt(index) == '-')){
            index ++;
        }
        
        //check "xxx" before "."
        while(index < len && Character.isDigit(s.charAt(index))){
            index ++;
            hasDigit = true;
        }
        
        //check "."
        if(index < len && s.charAt(index) == '.'){
            index ++;
        }
        
        //check "xxx" after "."
        while(index < len && Character.isDigit(s.charAt(index))){
            index ++;
            hasDigit = true;
        }   
        
        
        //check splitter "e", if we get splitter, then require first part must contains digit
        if(index < len && s.charAt(index) == 'e'){
            if(!hasDigit) return false;
            //since we must have digits in second part, we reset hasDigit to false to begin new check
            hasDigit = false;
            hasE = true;
            index ++;
        }
        
        //second part, the complete form is "+/-xxx", we don't allow decimal here
        
        
        //check "+/-"
        if(index < len && hasE && (s.charAt(index) == '+' || s.charAt(index) == '-')){
            index ++;
        }        
        
        while(index < len && Character.isDigit(s.charAt(index))){
            hasDigit = true;
            index ++;
        }
        
        
        //reach end, if we got two parts, then both parts should have digits
        //we also restrict the char we encountered, if we stop due to another char, we will not move our pointer
        //so by checking index == len && hasDigit = true, we can check valid number
        
        return hasDigit && index == s.length();
    }
}
