/*
318. Maximum Product of Word Lengths

Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters.
You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

Example 1:
Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
Return 16
The two words can be "abcw", "xtfn".

Example 2:
Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
Return 4
The two words can be "ab", "cd".

Example 3:
Given ["a", "aa", "aaa", "aaaa"]
Return 0
No such pair of words.
*/

/**
 * Bit manipulation solution
 * 
 * an interesting problem. The main approach is naive. We compare each pair of strings and return the pair that gives max length. But the way 
 * we used to compare strings is using bit-manipulation. We observed that an integer has 32-bits while we only have 26 chars in each string.
 * Therefore, we can use an integer to represent a string with the indication that whether we have a given char in this string. Therefore comparison
 * of two strings becomes O(1)
 * 
 * Time complexity: O(n^2 + n) = O(n^2)
 * Space complexity: O(n)
 * 
 * @author hpPlayer
 * @date Feb 19, 2016 2:12:22 PM
 */

public class Maximum_Product_of_Word_Lengths_p318_sol1 {
    public int maxProduct(String[] words) {
        //bit manipulation solution, the approach is simple. We compare each string one by one, and find the pairs that has max length
        //The optimization of this solution is in the way we compare strings. Instead of comparing each char one by one. We firstly 
        //preprocess the input array and use an integer to represent the chars it have. How? there are totally 26 chars in the string,
        //and an integer has 32 bits, so we use 0-25 bit to indicate the occurence of 0-25 letters. Then we use & operator to compare
        //two strings to see if they share a common char
        
        int n = words.length;
        
        int[] masks = new int[n];
        
        //get mask for each string
        for(int i = 0; i < n; i++){
            masks[i] = getMask(words[i]);
        }
        
        int result = 0;
        
        //then compare each pair of strings
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if( (masks[i]&masks[j] )== 0){
                    //if two string does not have common char, then their & result should be 0
                    result = Math.max(result, words[i].length() * words[j].length() );
                }
            }
        }
        
        return result;
    }
    
    public int getMask(String str){
        int mask = 0;
        
        for(int i = 0; i < str.length(); i++){
            //use the char number to leftshift 1. Therefore each bit now becomes an indicator that whether the input string has certain char
            mask |= 1 << (str.charAt(i) - 'a'); 
        }
        
        return mask;
    }
}
