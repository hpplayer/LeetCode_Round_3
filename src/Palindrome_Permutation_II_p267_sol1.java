import java.util.*;

/*
267. Palindrome Permutation II

Given a string s, return all the palindromic permutations (without duplicates) of it.
Return an empty list if no palindromic permutation could be form.

For example:

Given s = "aabb", return ["abba", "baab"].

Given s = "abc", return [].

Hint:

If a palindromic permutation exists, we just need to generate the first half of the string.
To generate all distinct permutations of a (half of) string, use a similar approach from: Permutations II or Next Permutation.
*/

/**
 * Backtracking solution
 * 
 * We use an array to count the occurrences of each char. Palindrome String can only have one char that occurs with odd times
 * Therefore we can have early return if two chars occur odd times. Then we start generate palindrome substring. Each time,
 * we just took a same char two times and append them to the two sides of generated string. We will recursively do this until
 * we found generated len = expected len
 * 
 * Time complexity: recursion, exponential, masters theorem
 * Space complexity: same as above
 * 
 * Remark:
 * we can use an inner class and a stack to write an iterative version. But it is similar to other iterative solutions, and 
 * is lengthy, therefore I did not put it here
 * 
 * @author hpPlayer
 * @date Jun 4, 2016 5:28:42 PM
 */

public class Palindrome_Permutation_II_p267_sol1 {
    public List<String> generatePalindromes(String s) {
        //backtracking solution. Use backtracking way to genereate all possible permutations
        
        //count table
        int[] chars = new int[256];
        
        //update count table
        for(char c : s.toCharArray()) chars[c]++;
        
        //check count table and make sure we have at most one char occurs with odd times
        int count = 0;
        //since index in array is also index in ASCII table, we can directly record the index as used as char
        char oddChar = 0;
        
        for(int i = 0; i < 256; i++){
            if( (chars[i]&1) == 1 ){
                count++;
                //we have two chars that occur odd times, therefore it is impossible to have palindrome permutation
                if(count > 1) return new ArrayList<String>();
                oddChar = (char) i;
                //update its occurrences since we will use a mid later, and this is fixed
                chars[i]--;
            }
        }
        
        List<String> result = new ArrayList<String>();
        
        if(count > 0){
            //we have a char with odd occurrences, we have to make it in middle
            DFS(result, oddChar + "", chars, s.length());
            return result;
        }
        
        //general cases that all chars appear even times
        DFS(result, "", chars, s.length());
        
        return result;
    }
    
    public void DFS(List<String> result, String s, int[] chars, int len){
        //we have generated a valid palindrome permutation 
        if(s.length() == len){
            result.add(s);
            return;
        }
        
        for(int i = 0; i < 256; i++){
            //skip chars with 0 occurrences
            if(chars[i] == 0) continue;
            
            char c = (char) i;
            //update count table as we used char i two times
            chars[i] -= 2;
            DFS(result, c + s + c, chars, len);
            //reset it back, so we won't affect later enumeration
            chars[i] += 2;
        }
        
    }
}
