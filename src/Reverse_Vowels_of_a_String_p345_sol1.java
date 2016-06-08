/*
345. Reverse Vowels of a String

Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".
*/

/**
 * Two pointer solution
 * 
 * This problem is similar to Reverse_String_p344_sol1 and Valid_Palindrome_p125_sol1
 * 
 * We firstly convert input string to a char array, then use two pointers to scan the input string
 * We firstly move left pointer
 * If we found curr char is not a vowel, then we will skip it, as we don't need to move it
 * If we found curr char is a vowel, then we stop moving left pointer
 * Then we move right pointer
 * If we found curr char is not a vowel, then we will skip it, as we don't need to move it
 * If we found curr char is a vowel, then we stop moving right pointer
 * 
 * Now left and right pointer both point to vowel chars, we just swap them.
 * However we may also have the case that we have only one vowel or no vowel at all at this point
 * Then swap won't hurt the correctness 
 * 
 * 1) Using char[] instead of two StringBuilder can greatly improve the speed from 200ms to 20ms
 * 2) Using string.indexOf(char) instead of string.contains(string) can also improve the speed a bit
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Jun 7, 2016 9:26:14 PM
 */
public class Reverse_Vowels_of_a_String_p345_sol1 {
    public String reverseVowels(String s) {
        //two pointer solution, and we use string.indexOf(char) to check if a char is a vowel
        
        String vowels = "aeiouAEIOU";
        //we convert s to charArray so that we don't need to move non-vowel chars
        char[] chars = s.toCharArray();
        int left = 0, right = s.length() - 1;
        
        while(left < right){
            //we iteratively move left pointer until left pointer points to a vowel char
            while(left < right && vowels.indexOf(chars[left]) == -1 ) left++;
            //we iteratively move right pointer until right pointer points to a vowel char
            while(left < right && vowels.indexOf(chars[right]) == -1 ) right--;
            
            //swap chars pointered by left and right pointers
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        
        return new String(chars);
    }
}
