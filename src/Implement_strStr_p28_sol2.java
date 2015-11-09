/**
 * Standard KMP solution.
 * 
 * We firstly build the KMP table, then start matching. If current char matching, then we move pointer in both strings, if we found 
 * pointer in needle reach the end, then we found result, just return i in string haystack. If not matching, and we our pointer has 
 * moved in needle string, we just assign the pointer's index to be table[j-1], keep index i stay still, and try to match again.
 * 
 * The algorithm costs O(len(haystack) + len(needle) time.
 * and O(len(needle)) space
 * 
 * @author hpPlayer
 * @date Nov 8, 2015 5:48:57 PM
 */
public class Implement_strStr_p28_sol2 {
    public int strStr(String haystack, String needle) {
        //use KMP algorithm to found the match substring
        
        //boundary check
        if(haystack.length() < needle.length()) return -1;
        if(needle.length() == 0) return 0;
        
        int[] table = getTable(needle);
        int j = 0;
        for(int i = 0; i < haystack.length(); i++){
            if(haystack.charAt(i) == needle.charAt(j)){
                j++;
                //Found match, return starting index
                if(j == needle.length()) return i - needle.length() + 1;
            }else{
                //not match
                
                //if we have scanned more than 1 char in string needle
                if(j-1 >= 0){
                  //move string needle to last matched substring
                  //and match again
                  j = table[j-1];
                  i--;
                }
                //otherwise, skip current char in haystack
            }
            
        }
        
        return -1;
    }
    
    public int[] getTable(String s){
        int index = 0;
        int[] table = new int[s.length()];
        for(int i = 1; i < s.length(); i++){
            if(s.charAt(i) == s.charAt(index)){
                index ++;
                table[i] = index;
            }else{
                index = table[i-1];
                while(index > 0 && s.charAt(i) != s.charAt(index)){
                    //shrinking the match length and found the matched substring
                    index = table[index-1];
                }
                
                if(s.charAt(i) == s.charAt(index)){
                    index ++;
                    table[i] = index;
                }
            }
        }
        
        return table;
    }
}
