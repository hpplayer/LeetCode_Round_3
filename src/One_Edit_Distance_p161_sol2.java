/**
 * Two pointer solution
 * 
 * Same with sol1, but now we use built-in equals() to compare the rest substring after match char pairs
 * 
 * Time complexity: O(N) where n is the length of shorter string
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Mar 3, 2016 5:05:24 PM
 */
public class One_Edit_Distance_p161_sol2 {
    public boolean isOneEditDistance(String s, String t) {
        //two pointer solution, but we use built-in equals() to compare substrings after one mismatch
        
        int m = s.length(), n = t.length();
        
        //for convenience, we always let len(s) < len(t)
        if(m > n) return isOneEditDistance(t, s);
        
        if(n - m > 1) return false;
        
        for(int i = 0; i < m; i++){
            if(s.charAt(i) != t.charAt(i)){
                //one mismatch found
                //there will be two cases
                if(m == n){
                    //case 1, same len, then we have to replace chars so we can only have one edit distance
                    return s.substring(i+1).equals(t.substring(i+1));
                }else{
                    //case 2, diff len, then we have to add char to shorter len or remove char from longer len to have one edit distance
                    //in either case, we keep pointer in shorter str standstill and move pointer in longer str
                    return s.substring(i).equals(t.substring(i+1));
                }
            }
        }
        
        
        //we have taken care of 1 mismatch case, we will be here only because we don't have mismatch case above
        //therefore we must require two inputs have diff len with 1
        return n - m == 1;
    }
}
