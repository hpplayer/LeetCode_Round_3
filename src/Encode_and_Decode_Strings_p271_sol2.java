import java.util.*;

/**
 * String solution
 * 
 * In this solution, we use another delimiter with form of len +"#" + str
 * Since we record the len of each string, we can scan the encoded string and knowing where to stop.
 * We use string.indexOf("#", index) to find the next index of "#", the content before "#" will be the len of string after "#"
 * The content after "#" will be the string that in original input. 
 * 
 * In this solution, we only scan the input strinng once during the decoding process, so it is faster than sol1, but the time complexity is still O(N)
 * 
 * 
 * @author hpPlayer
 * @date Feb 10, 2016 5:44:55 PM
 */
public class Encode_and_Decode_Strings_p271_sol2 {
	public static void main(String[] args){
		List<String> list = new ArrayList<String>();
		list.add("");
		//list.add("b");
		
		
		System.out.println(decode(encode(list)));
	}
	
    // Encodes a list of strings to a single string.
    public static String encode(List<String> strs) {
        //use len + "#" + str to encode each string, so that later we can scan the encoded string while knowing where to split string
        StringBuilder sb = new StringBuilder();
        
        for(String str :strs){
            sb.append(str.length()).append("#").append(str);
        }
        
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public static List<String> decode(String s) {
        List<String> result = new ArrayList<String>();
        
        //pointer we used to scan input string
        int index = 0;
        
        while(index < s.length()){
            //we use indexOf("#", index) to find next "#" after current index
            //i is used to point where is "#"
            int i = s.indexOf("#", index);
            //with i, we can find the length of current string
            int len = Integer.valueOf(s.substring(index, i));
            
            result.add(s.substring(i+1, i+1+len));
            
            //update index to be the first char after current stirng
            index = i + len + 1;
        }
        
        return result;
    }
}
