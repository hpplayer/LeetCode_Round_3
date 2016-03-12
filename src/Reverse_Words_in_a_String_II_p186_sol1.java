/*
Reverse Words in a String II

Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.

The input string does not contain leading or trailing spaces and the words are always separated by a single space.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Could you do it in-place without allocating extra space?
*/

/**
 * Two pointer + array/list rotation problem
 * 
 * We still use two pointers to locate the range of a word.
 * But now we need reverse the array in place. So we just use the method from programming pearl.
 * We firstly reverse each word, then reverse the the array as a whole.
 * How about spaces? 
 * No worry. We reverse the word in place, so the index and length of each word will not change. Then we reverse the input[] as a whole, 
 * so space will be reversed as well. Therefore they will be rotated to the correct place.
 * 
 * Time complexity: reverse each word in input array. O(n), reverse the whole array O(n), so the total time complexity is still O(n)
 * Space complexity: we solve the problem in place, so the space complexity is O(1)
 * 
 * Remark:
 * The way we used to rotate string can also be used in Rotate_Array_p189_sol1
 * @author hpPlayer
 * @date Nov 13, 2015 1:19:05 AM
 */
public class Reverse_Words_in_a_String_II_p186_sol1 {
    public void reverseWords(char[] s) {
        
        //use two pointers to identify a word
        int left = 0;
        
        //we firstly reverse each word in s
        for(int right = 0; right <= s.length; right++){
            //word is delimited by space except the last word
            //so we just need to check space and right boundary to identify a word
            if(right == s.length || s[right] == ' '){
                reverse(left, right - 1, s);
                //update left to be the start index of next word
                left = right + 1;
            }
        }
        
        //we secondly reverse the input[] as a whole
        reverse(0, s.length - 1, s);
    }
    
    public void reverse(int start, int end, char[] s){
        //a general function to reverse subpart in input array
        while(start < end){
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }
}
