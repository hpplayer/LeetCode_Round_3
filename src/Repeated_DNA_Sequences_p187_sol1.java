import java.util.*;

/*
187. Repeated DNA Sequences

All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA,
it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].
*/

/**
 * HashMap + bit manipulation solution
 * 
 * Since we only have 4 chars, we can convert each char to a two digit binary representation, and use left shift to build the string
 * So the space will be greatly reduced!!!!
 * 
 * If a DNA sequence appears more than one, then we only need to record it once. So we use an extra set to check if the string has
 * been added to result already
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * We observed that each time we only need to remove the first two binary digits in head and add two new binary digits in tail, so we don't need to build the
 * whole string each time. We can use & 0xFFFFF to achieve that, see sol2 for implementation
 * 
 * @author hpPlayer
 * @date Mar 8, 2016 8:26:46 PM
 */
public class Repeated_DNA_Sequences_p187_sol1 {
	public List<String> findRepeatedDnaSequences(String s) {
	    //HashMap + bit manipulation solution. We will convert each char to two-digit binary representation
	    
	    //map char with its binary representation
	    int[] maps = new int[256];
	    maps['A'] = 0;
	    maps['T'] = 1;
	    maps['C'] = 2;
	    maps['G'] = 3;
	    
	    List<String> result = new ArrayList<String>();
	    //visited set includes all 10-len substring that we have visited
	    Set<Integer> visited = new HashSet<Integer>();
	    //inResult set includes all substring that we have inserted into result to avoid insert a same string more than once
	    Set<Integer> inResult = new HashSet<Integer>();
	    
	    //substring with 10 len will start after index 9
	    for(int i = 9; i < s.length(); i++){
	        String subStr = s.substring(i-9, i+1);
	        //key is the integer representation of subStr
	        int key = 0;
	        for(int j = 0; j < 10; j++){
	            key <<= 2;
	            //use |= to update key
	            key |= maps[subStr.charAt(j)];
	        }
	        
	        //if this is not the first time we face this substring
	        if(!visited.add(key)){
	            //if this is the first time that we insert this substring into result list
	            if(inResult.add(key)){
	                result.add(subStr);
	            }
	        }
	    }
	    
	    return result;
	 }
}
