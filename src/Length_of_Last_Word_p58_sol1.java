/*
58. Length of Last Word

Given a string s consists of upper/lower-case alphabets and empty space characters ' ',
return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

For example, 
Given s = "Hello World",
return 5.
*/

/**
 * String solution
 * 
 * We scan the input string backward, so we don't need to touch the previous part of string
 * In extreme case we may only have one word and a bunch of trailing spaces. sol1 may be slow in such
 * cases, but generally it should work faster than scan forward
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * Remark:
 * Here we use for loop to jump over trailing spaces instead of s.trim() since we don't want to touch
 * heading spaces 
 * 
 * @author hpPlayer
 * @date May 12, 2016 9:16:37 PM
 */
public class Length_of_Last_Word_p58_sol1 {
    public int lengthOfLastWord(String s) {
        //scan backward, so that we don't need to touch the previous part of input string
        
        int i = s.length() - 1;
        
        //skip over empty trailing spaces
        while(i >= 0 && s.charAt(i) == ' ') i--;
        
        //scan last word until we reach the space before it
        int len = 0;
        while(i >= 0 && s.charAt(i) != ' '){
            i--;
            len++;
        }
        return len;
    }
}
