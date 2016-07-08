import java.util.*;

public class Word_Pattern_II_p291_sol2 {
    public boolean wordPatternMatch(String pattern, String str) {
        HashMap<Character, String> hs1 = new HashMap<>();
        HashMap<String, Character> hs2 = new HashMap<>();
        
        return DFS( hs1, hs2, pattern, str );
    }
    
    public boolean DFS( HashMap<Character, String> hs1, HashMap<String, Character> hs2, String pattern, String str){
        
        if(pattern.length() == 0 && str.length() == 0){
           return true; 
        }
        
        if(pattern.length() == 0 || str.length() == 0) return false;
        
        char c = pattern.charAt(0);
        
        for(int i = 1; i <= str.length(); i++){
            String s = str.substring(0, i);
            
            if( hs1.containsKey(c) && hs2.containsKey(s) ){
                if(hs1.get(c).equals(s) && hs2.get(s) == c ){
                    return DFS( hs1, hs2, pattern.substring(1), str.substring(i) );
                }
            }
            
            if( !hs1.containsKey(c) && !hs2.containsKey(s)  ){
                hs1.put(c, s);
                hs2.put(s, c);
                if( DFS( hs1, hs2, pattern.substring(1), str.substring(i) ) ) return true;
                hs1.remove(c);
                hs2.remove(s);
            }
        }
        
        return false;
    }
}
