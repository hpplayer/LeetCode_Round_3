import java.util.*;

/**
 * HashMap solution
 * 
 * Instead of recording index, here I record the real string and chars as the values.
 * But it makes the check process more complicated
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Mar 2, 2016 10:08:49 PM
 */


public class Word_Pattern_p290_sol2 {
    public boolean wordPattern(String pattern, String str) {
        HashMap<Character, String> hs = new HashMap<Character, String>();
        HashMap<String, Character> hs2 = new HashMap<String, Character>();
        
        String[] strs = str.split(" ");
        
        if(pattern.length() != strs.length) return false;
        
        for(int i = 0; i < pattern.length(); i++){
            char c = pattern.charAt(i);
            
            if(!hs.containsKey(c)){
                if (hs2.containsKey(strs[i]) ) return false;
                hs.put(c, strs[i]);
                hs2.put(strs[i], c);
            }else{
                if(!hs2.containsKey(strs[i]) || !hs.get(c).equals(strs[i]) || hs2.get(strs[i]) != c ) return false;
            }
        }
        
        return true;
    }
}
