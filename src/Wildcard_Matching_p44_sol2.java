/**
 * Iterative backtracking solution
 * 
 * We use two pointers to scan two input strings
 * 
 * For general case, we just move both pointers
 * For special case ("*"), we use extra two variables to back up current indexes of pointer. Then try to use different number 
 * of "*". This is achieved by moving pointer in S further and further while keep pointer in P stay still
 * 
 * Our main loop is based on string s. If we can reach the end, we will check if pointer in p also reach end (we may have to 
 * skip trailing "*" in p if we have. If we can, then return true, otherwise return false
 * @author hpPlayer
 * @date Nov 2, 2015 10:02:17 PM
 */
public class Wildcard_Matching_p44_sol2 {
    public boolean isMatch(String s, String p) {
        int indexP = 0;
        int indexS = 0;
        
        int backupS = -1;
        int backupP = -1;
        
        int m = s.length();
        int n = p.length();
        
        while(indexS < m){
            //we try to match each char in s
            if(indexP < n && ( s.charAt(indexS) == p.charAt(indexP) || p.charAt(indexP) == '?')){
                //found match char, continue 
                indexP ++;
                indexS ++;
            }else if(indexP < n && p.charAt(indexP) == '*'){
                //found "*", we can use backupP and backupS to back up current indexP and indexS
                //so we can try different number of "*" in match
                
                backupS = indexS;
                backupP = indexP + 1;
                
                //firstly try use 0 num of "*", which means we use next char in p to match curr char in s
                indexP ++;
            }else{
                //not matched pair found
                if(backupS == -1){
                    //if no "*" before, then it is a dead matched pari, return false directly
                    return false;
                }
                
                //otherwise, we reset indexP and indexS, to use one more *
                indexS = ++backupS; 
                indexP = backupP;
            }
        }
        
        //skip trailing "*"s
        while(indexP < n && p.charAt(indexP) == '*') indexP ++;
        
        return indexP == p.length();
    }
}
