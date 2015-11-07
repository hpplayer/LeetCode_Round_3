/*
Valid Palindrome

Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.
*/

/**
 * two pointer solution
 * 
 * We use two pointer, one scan the string from left and the other pointer scan the string from right
 * We will skip all invalid chars and only compare valid chars
 * 
 * Remark:
 * we need to convert the input string to lower case and remove heading and trailing whitespaces
 * 
 * Time complexity: O(n)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Nov 7, 2015 2:49:21 AM
 */
public class Valid_Palindrome_p125_sol1 {
    public boolean isPalindrome(String s) {
        //remove heading and trailing spaces
        s = s.trim().toLowerCase();
        int left = 0, right = s.length()-1;
        
        while(left < right){
            //scan whole string
            
            //skip all ignoring chars
            while(left < right && !Character.isLetterOrDigit(s.charAt(left))) left ++;
            while(left < right && !Character.isLetterOrDigit(s.charAt(right))) right --;
            
            //not matched, return false
            if(s.charAt(left) != s.charAt(right)) return false;
            left ++;
            right --;
        }
        
        return true;
    }
}
