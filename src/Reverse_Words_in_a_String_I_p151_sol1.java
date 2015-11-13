/*
Reverse Words in a String

Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Update (2015-02-12):
For C programmers: Try to solve it in-place in O(1) space.

Clarification:
What constitutes a word?
A sequence of non-space characters constitutes a word.

Could the input string contain leading or trailing spaces?

Yes. However, your reversed string should not contain leading or trailing spaces.

How about multiple spaces between two words?
Reduce them to a single space in the reversed string.
*/

/**
 * Two pointer solution
 * 
 * To solve this problem in one pass, we have to scan and build new string backward. We use left pointer to search for the 
 * head char of a word and use right pointer to locate the first space after word. Following this way, we can find all words
 * in input and build a nice output with one pass
 * 
 * Time complexity: O(n)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Nov 13, 2015 12:51:15 AM
 */
public class Reverse_Words_in_a_String_I_p151_sol1 {
    public String reverseWords(String s) {
        //use two pointers to read string backward so we can build new string forward with one pass
        //we just want to scan the string once, so we will not use split(" +") and trim()
        int right = s.length();
        StringBuilder sb = new StringBuilder();
        for(int left = s.length() - 1; left >= 0; left--){
            if(s.charAt(left) == ' '){
                //space char, we move right accordingly, so after we found a word, right pointer will point
                //to the first space after word
                right = left;
            }else{
                
                //we only add word when left pointer is at the beginning of a word that is left - 1 is space char
                //again or left - 1 is the boundary
                if(left == 0 || s.charAt(left - 1) == ' '){
                    //in new string, we need add a space before each word except the first word
                    //how do we find the first word? just left == 0? Not necessary, we may have corner case that
                    //first word still has spaces before it like " hello".
                    //so the way we used to check first word is checking the length of stringBuilder
                    //if it is empty, then we know current word is first word and we will not add space before it
                    if(sb.length() != 0) sb.append(" ");
                    sb.append(s.substring(left, right));
                }
            }
        }
        
        return sb.toString();
    }
}
