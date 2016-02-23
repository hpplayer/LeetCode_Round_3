import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * For each char, we will firstly check previous char.
 * If previous char is int, then we can either add char directly or add an abbreviation (need update the count)
 * If previous char is char, then we can either add char directly or add an abbreviation (first count as 1)
 * 
 * Time complexity: O(n^2)
 * Space complexity: O(2^n)
 * 
 * @author hpPlayer
 * @date Feb 22, 2016 5:30:48 PM
 */
public class Generalized_Abbreviation_p320_sol2 {
	   public List<String> generateAbbreviations(String word) {
	        LinkedList<String> result = new LinkedList<String>();
	        if(word.length() == 0) return Arrays.asList("");
	        
	        result.add(word.charAt(0) + "");
	        result.add(1 + "");
	        
	        for(int i = 1; i < word.length(); i++){
	        	int size = result.size();
	        	for(int j = 0; j < size; j++){
	        		String temp = result.pollFirst();
	        		char c = temp.charAt(temp.length()-1);
	        		if(Character.isDigit(c)){
	        			String temp2 = temp.substring(0, temp.length() -1) + (c - '0' + 1);
	        			String temp3 = temp + word.charAt(i);
	        			result.add(temp2);
	        			result.add(temp3);
	        		}else{
	        			String temp3 = temp + word.charAt(i);
	        			String temp4 = temp + 1;
	        			result.add(temp3);
	        			result.add(temp4);
	        		}
	        		
	        	}
	        }
	        
	        return result;
	    }
}
