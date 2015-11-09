/*
Implement strStr()

Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
*/

/**
 * String + two pointer problem
 * 
 * we just use a nested loop to move two pointers on two input strings. If we found pointer reaches the end of string needle, then we found
 * a matching substring in haystack, just return i. If we found pointer reaches the tail of string haystack and we still have not reached the
 * end of needle, then we know there is no matching substring, just return -1. Otherwise we compare corresponding chars in two input strings.
 * 
 * Remark:
 * we will not put ending condition in the for loop unless we reach two cases mentioned above, so it is like a while(true) loop,
 * we couldn't write any statments after the for loop!!!!!!!!!!!!
 * Time complexity: O(mn)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Nov 8, 2015 3:08:12 PM
 */
public class Implement_strStr_p28_sol1 {
    public int strStr(String haystack, String needle) {
        //boundary check
        if(needle.length() > haystack.length()) return -1;
        
        for(int i = 0; ; i++){
            for(int j = 0; ;j++){
                //if we reach the end of needle, then we found match, just return i
                if(j == needle.length()) return i;
                //if we reach the end of haystack and still not found the match, return -1
                if(i + j == haystack.length()) return -1;
                //otherwise we compare current char in two strings
                //Notice: the index of two pointers are checked in above two if conditions
                if(haystack.charAt(i + j) != needle.charAt(j)) break;
            }
        }
    }
}
