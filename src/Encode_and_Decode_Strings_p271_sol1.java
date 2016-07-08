import java.util.*;

/*
271. Encode and Decode Strings

Design an algorithm to encode a list of strings to a string.
The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:

string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}
Machine 2 (receiver) has the function:
vector<string> decode(string s) {
  //... your code
  return strs;
}
So Machine 1 does:

string encoded_string = encode(strs);
and Machine 2 does:

vector<string> strs2 = decode(encoded_string);
strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.

Note:
The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.
*/

/**
 * String solution
 * 
 * In this solution, we firstly modify the input string, and replace each "#" with "##". So the input string will normal contain single "#"
 * Then we add a delimiter that contains single "#", like "/#/". This sign will be unique and only appear in the delimiter
 * In the decode process, we just split the string by "/#/", then replace "##" with "#" to recover string
 * 
 * Remark:
 * To cover empty input case, we will use split("/#/", -1), which will preserve the empty string after split
 * 
 * Time complexity: O(N)
 * 
 * Sol2 uses a different delimiter(len + "#" + str), which will be faster, as we don't use split() to process the string
 * 
 * @author hpPlayer
 * @date Feb 10, 2016 5:23:06 PM
 */



public class Encode_and_Decode_Strings_p271_sol1 {
	public static void main(String[] args){
		List<String> list = new ArrayList<String>();
		//list.add("");
		//list.add("b");
		
		
		System.out.println(decode(encode(list)));
	}
	
    // Encodes a list of strings to a single string.
    public static String encode(List<String> strs) {
        //for encoding, we double "#" in input, then add a delimiter like "/#/"
        //Since unique "#" will only come from delimiter, we can use "/#/"" to split the string
        
        StringBuilder sb = new StringBuilder();
        
        for(String str : strs){
            sb.append(str.replace("#", "##")).append("/#/");
        }
        
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public static List<String> decode(String s) {
        //if argument is non-positive (0 or -1), then split will be applied as many as possible (basically, it will help cover empty stirng)
        //0 will remove trailing empty strings while -1 will keep them
    	//If argument n is greater than zero then the pattern will be applied at most n - 1 times,
    	//the array's length will be no greater than n, and the array's last entry will contain all input beyond the last matched delimiter.
    	
    	/*
    	String: "boo:and:foo"
    	o	5	{ "b", "", ":and:f", "", "" }
    	o	-2	{ "b", "", ":and:f", "", "" }
    	o	0	{ "b", "", ":and:f" }
    	
        String a = "#";
	    System.out.println(Arrays.toString(a.split("#", -1)));
	    System.out.println(Arrays.toString(a.split("#", 0)));
	    System.out.println(Arrays.toString(a.split("#", 1)));
	    System.out.println(Arrays.toString(a.split("#")));
	    [, ] len:2
		[] len: 0
		[#] len: 1
		[] len: 0
    	 */
        String[] strs = s.split("/#/", -1);
        
        List<String> result = new ArrayList<String>();
        
        //the trailing empty string is from the delimiter we add after last string, and we can ignore it
        for(int i = 0; i < strs.length - 1; i++){
            result.add(strs[i].replace("##", "#"));    
        }
        
        return result;
    }
}
