import java.util.*;

/*
291. Word Pattern II

Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Examples:
pattern = "abab", str = "redblueredblue" should return true.
pattern = "aaaa", str = "asdasdasdasd" should return true.
pattern = "aabb", str = "xyzabcxzyabc" should return false.
Notes:
You may assume both pattern and str contains only lowercase letters.
*/

/**
 * Backtracking solution
 * 
 * We use backtracking to try each possible matches with char in pattern and substring in str input. Each backtracking will check a char from pattern and match it
 * with a substring in str input
 * 
 * It is better to directly use char-string mapping here. Why? Since this time we don't know how to split the input string. Therefore we need
 * two indexes to find the next substring. One index points to the start of current substring, one index points to the end of current substring
 * So thing becomes complicated, which index should we keep in the HashMap? and how can we make this index be consistent with index in pattern?
 * Therefore, it is better to directly record the char and string in the Map
 * 
 * So we use a char and string map here. We also use a HashSet to record the relationship in reverse.
 * If char in pattern has been used before, then we will make use of hashMap to check match between char and substring
 * if char in pattern has not been used before, then we will try all possible substrings in str input to match it, we require this substring is not used before so
 * HashSet should not contain this substring
 * 
 * 
 * Time complexity: recursion, masters theorem, but must be exponential since each char in pattern can be matched to all substrings in str
 * Space complexity: same as above
 * 
 * @author hpPlayer
 * @date Mar 3, 2016 10:30:57 AM
 */

public class Word_Pattern_II_p291_sol1 {
	public static void main(String[] args){
		String pattern = "aabb";
		String str = "xyzabcxzyabc";
		System.out.println(new Word_Pattern_II_p291_sol1().wordPatternMatch(pattern, str));
	}
    public boolean wordPatternMatch(String pattern, String str) {
        //backtracking solution. Since we don't know how to split str in the beginning, we have to try all possible substrings in input str
        //to match pattern. Each recursion will try match a substring with current char in pattern.
        //In this solution, we use a small optimization that we will stop match if we found remaining pattern chars are larger than the remaining chars in str
        return DFS(pattern, str, new HashMap<Character, String>(), new HashSet<String>());
    }
    
    public boolean DFS(String pattern, String str, HashMap<Character, String> hs, HashSet<String> used){
        //hs is used to record one on one relationship from char to string, "used" is used to check if we have used current substring before
        
        //both reach bottom, return true
        if(pattern.length() == 0 && str.length() == 0) return true;
        //only one reach bottom, return false
        if(pattern.length() == 0 || str.length() == 0) return false;
        
        char c = pattern.charAt(0);
        if(hs.containsKey(c)){
            //case 1, we have match this char before
            
            String s = hs.get(c);
            
            //we may match c with a very long string before, so before we compare two strings we need to make sure the substring from str
            //has enough length
            //if length is ok, then we need to check if start substring in str matched with s
            if( s.length() > str.length() || !s.equals(str.substring(0, s.length()))) return false;
            //if match is ok, then we continue
            return DFS(pattern.substring(1), str.substring(s.length()), hs, used);
        }else{
            //case 2, we have not match this char before
            
            //optimization here, we will stop the loop if we found remaining chars in str are less than remaining chars in pattern
            for(int i = 1; i <= str.length() && str.length() - i >= pattern.length()-1; i++){
                //firstly check if current substring has been used before
                String s = str.substring(0, i);
                if(used.contains(s)) continue;
                
                //otherwise we try and use current match then continue
                hs.put(c, s);
                used.add(s);
                if(DFS(pattern.substring(1), str.substring(i), hs, used)) return true;
                //match not succeed, try next match
                hs.remove(c);
                used.remove(s);
            }
            
            //all matches tried, still not succeed, return false
            return false;
        }
    }
}
