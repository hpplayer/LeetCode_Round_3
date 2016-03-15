import java.util.Arrays;

/*
165. Compare Version Numbers

Compare two version numbers version1 and version2.
If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Here is an example of version numbers ordering:

0.1 < 1.1 < 1.2 < 13.37
*/

/**
 * String + reg solution
 * 
 * The tricky part of this problem is to find the fact that the delimiter "." has its meaning in reg expression. Therefore, we cannot direclty used it
 * int split(), otherwise it will be treated as reg expression. To skip interpret "." as reg expression, we will use "\\" sign.
 * So the basic idea is using "\\." to split two inputs into string segments and compare each segment. 
 * 
 * To make the program more concise, we let the loop only when we reach the end of longer string
 * 
 * 
 * Time complexity: O(max(len(v1), len(v2)))
 * Space complexity: O(len(v1) + len(v2))
 * 
 * @author hpPlayer
 * @date Mar 14, 2016 2:41:06 PM
 */
public class Compare_Version_Numbers_p165_sol1 {
    public int compareVersion(String version1, String version2) {
        //string + reg solution. The delimiter "." has reg meaning in split(), so we need to skip it and let java treat it as a delimiter
        //to skip a reg, we will use "\\". After we can split the strings appropriately, we can start compare the version number
        
        //use "//" to skip "."
        String[] str1 = version1.split("\\.");
        String[] str2 = version2.split("\\.");
        
        //to make the program more concise, we will let the loop continue even we have reached the end of one loop
        for(int i = 0; i < Math.max(str1.length, str2.length); i++){
            int a = i < str1.length? Integer.valueOf(str1[i]) : 0;
            int b = i < str2.length? Integer.valueOf(str2[i]) : 0;
            
            if(a == b) continue;
            
            return a > b? 1 : -1;
        }
        
        //all string segments are equal, return 0
        return 0;
    }
}
