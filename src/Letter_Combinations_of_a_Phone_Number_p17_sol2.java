import java.util.*;

/**
 * Recursive DFS solution
 * 
 * Each recursion we will insert one letter to one digit, then we go to next digit
 * 
 * Time complexity: recursion, we need use masters theorem. but must be exponential, since each recursion we have several choices, and we need n recursions,
 * where n is length of digits
 * 
 * Space complexity: same as above
 * 
 * @author hpPlayer
 * @date Mar 6, 2016 12:39:43 AM
 */
public class Letter_Combinations_of_a_Phone_Number_p17_sol2 {
    public List<String> letterCombinations(String digits) {
        //backtracking solution. Each recursion will insert a letter to one digit
        
        //boundary check
        if(digits.length() == 0) return new ArrayList<String>();
        
        List<String> result = new ArrayList<String>();
        
        DFS(result, "", digits);
        
        return result;
    }
    
    String[] dict = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    
    public void DFS(List<String> result, String curr, String digits){
        if(digits.length() == 0){
            //reach boundary
            result.add(curr);
            return;
        }
        
        String digit = dict[digits.charAt(0) - '0'];
        
        //we insert letters represented by current digit to previous created string
        for(int i = 0; i < digit.length(); i++){
            //each time we inserted a new letter to one digit and go to next digit
            DFS(result, curr + digit.charAt(i), digits.substring(1));
        }
        
    }
}
