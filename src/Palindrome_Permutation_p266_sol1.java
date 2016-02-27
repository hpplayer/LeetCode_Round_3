/*
266. Palindrome Permutation

Given a string, determine if a permutation of the string could form a palindrome.

For example,
"code" -> False, "aab" -> True, "carerac" -> True.

Hint:

1. Consider the palindromes of odd vs even length. What difference do you notice?
2. Count the frequency of each character.
3. If each character occurs even number of times, then it must be a palindrome. How about character which occurs odd number of times?
*/

/**
 * Count table solution
 * 
 * We use a count table to record the occurrences of each char. The valid input should have one char with odd occurrences
 * 
 * Time complexity: O(N)
 * Space complexity:O(N)
 * 
 * @author hpPlayer
 * @date Feb 25, 2016 2:14:31 PM
 */
public class Palindrome_Permutation_p266_sol1 {
    public boolean canPermutePalindrome(String s) {
        //count table solution. We use a count table to record the occurences of each char
        //a string that has palindrome permutation should at most have one char with odd occurence
        
        int count[] = new int[256];
        
        //odd let us know how many chars have odd occurence
        int odd = 0;
        
        for(char c : s.toCharArray()){
            //to avoid scan the array twice, we will change odd variable on-fly
            odd += ( ++count[c]&1) == 1? 1 : -1;
        }
        
        //check if odd < 2
        return odd < 2;
    }
}
