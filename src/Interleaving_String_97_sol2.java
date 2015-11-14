import java.util.*;

/**
 * Backtracking + memorization
 * 
 * This problem requires us to find a way to build s3 from s1 and s2. So backtracking can also work. But intuitive backtracking will give
 * LTE, so we have to use memorization to avoid duplicate process. In each recursion, we firstly check if current combination has been checked
 * before, then we check several boundary conditions including where all string reach end, all len(s1) + len(s2) != s3. After that we can
 * start our match, we check if current first char in s1 or s2 are same with s3, then do next recursion accordingly. 
 * 
 * Time complexity: hard to analyze
 * Space complexity: hard to analyze
 * 
 * @author hpPlayer
 * @date Nov 13, 2015 10:37:09 PM
 */
public class Interleaving_String_97_sol2 {
    Set<String> deathGroup = new HashSet<String>();
    
    public boolean isInterleave(String s1, String s2, String s3) {
        //to make input string unique, we use following way to generate key
        String key = s1 + "#" + s2 + "#" + s3;
        
        //check cache first, avoid duplicate processing
        if(deathGroup.contains(key)) return false;
        
        //boundary check
        //if found valid match
        if(s1.isEmpty() && s2.isEmpty() && s3.isEmpty()) return true;
        //if remaining chars in s1 and s3 not equal to s3, then current match is invalid
        if(s1.length() + s2.length() != s3.length()) return false;
        
        //otherwise, continue matching
        if(s1.length() != 0 && s1.charAt(0) == s3.charAt(0)){
            if(isInterleave(s1.substring(1), s2, s3.substring(1))) return true;
        }
        
        if(s2.length() != 0 && s2.charAt(0) == s3.charAt(0)){
            if(isInterleave(s1, s2.substring(1), s3.substring(1))) return true;
        }        
        
        
        //match not successful, add current key into deathGroup and return false
        deathGroup.add(key);
        
        return false;
    }
}
