import java.util.*;
/**
 * Since we use each bit as a mask. We require input array must have len <= 32
 * 
 * @author hpPlayer
 * @date Feb 23, 2016 11:43:18 AM
 */
public class Generalized_Abbreviation_p320_sol2 {
	public static void main(String[] args){
		String word = "word";
		System.out.println(new Generalized_Abbreviation_p320_sol2().generateAbbreviations(word));
	}
	
    public List<String> generateAbbreviations(String word) {
        List<String> result = new ArrayList<String>();
        
        char[] chars = word.toCharArray();
        //use 32 bit mask to include all 2^n possible combinations
        //if n > 32, then the testcase would be too large
        int size = 1 << word.length();
        
        for(int i = 0; i < size; i++){
            int count = 0;
            StringBuilder sb = new StringBuilder();
            
            for(int j = 0; j < chars.length; j++){
                //use i >> j to move jth char to the last index
                if( (i>>j&1) == 1){
                    //in this solution n&1 = 1 means we need compress
                    count++;
                }else{
                    //add char directly, but we need to finish previous count
                    if(count > 0) sb.append(count);
                    //reset count
                    count = 0;
                    sb.append(chars[j]);
                }
            }
            //before we add the string, we need check if count variable is zero
            if(count > 0) sb.append(count);
            result.add(sb.toString());
        }
        
        return result;
    }
}
