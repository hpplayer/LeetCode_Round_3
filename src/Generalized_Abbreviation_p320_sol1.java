import java.util.*;

/*
320. Generalized Abbreviation

Write a function to generate the generalized abbreviations of a word.

Example:
Given word = "word", return the following list (order does not matter):
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
*/	

/**
 * 
 * The difficulty is that we need to consider compress the word multiple times. Ex: "word" -> "w1r1" or "word" -> "w2d"
 * The solution is to use a count variable to help us record how many chars we have abbreviated. In each position we have two choices 1) use char from
 * the input 2) use the count variable to abbreviate current char
 * 
 * Sol1 is recursive backtracking solution. Each layer will handle one char. For each char, we can either make it an abbreviation, update count, or 
 * we can make it as original char and insert it directly. For choice two, we need to finish previous count if count > 0
 * 
 * Time complexity: we have recursions, so master theory should be applied
 * Space complexity: same as above
 * 
 * Remark:
 * Sol2 uses 32bit int mask as the indicator that whether we need to compress char or add char directly, but it will not work for input array that 
 * has len > 32. For those long input array, it is better to use recursive version like sol1
 * 
 * Sol3 is my first attempt solution. Instead of using count variable, I just check the last char in the result. If it is integer, then I can either 
 * add a char from input or abbreviate current char and accumulate the count variable. If it is char, then we will do the same thing
 * 
 * This problem is very similar to problem subsets.
 * Sol1 is similar to Subsets_p78_sol1
 * Sol2 is similar to Subsets_p78_sol3
 * Sol3 is similar to Subsets_p78_sol2
 * 
 * Here we totally have 2^n possible abbreviation ways. That's because each char can either be abbreviated or kept as it is therefore, 
 * there are totally 2^n ways to abbreviate the input.
 * 
 * @author hpPlayer
 * @date Feb 22, 2016 10:37:59 AM
 */
public class Generalized_Abbreviation_p320_sol1 {
	public static void main(String[] args){
		String str = "woo";
		
		System.out.println(new Generalized_Abbreviation_p320_sol1().generateAbbreviations(str));
	}
	
    public List<String> generateAbbreviations(String word) {
        //backtracking solution. Use count variable to build new String
        List<String> result = new ArrayList<String>();
        DFS(word, "", 0, 0, result);
        
        return result;
    }
    
    public void DFS(String word, String curr, int index, int count, List<String> result){
        //boundary condition
        if(index == word.length()){
            if(count > 0) curr += count;
            result.add(curr);
            return;
        }
        
        //for each char, we can either treat it as abbreviation or add it as original
        //when adding as original, we need check if we have count before
        
        //treat it as abbreviation
        DFS(word, curr, index+1, count+1, result);
        //treat it as original char
        //we need firstly finish prev count, then add current char.
        //Besides, we need reset count to 0 indicating we have finished prev count
        DFS(word, curr + (count == 0? "" : count) + word.charAt(index), index + 1, 0, result); 
    }
}
