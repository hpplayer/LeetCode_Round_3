import java.util.*;
/**
 * HashMap + bit manipulation solution
 * 
 * This solution is an optimization version of sol1. Here, instead of building key for each substring in a for loop, we use bit manipulation to remove the 
 * old bits in head and add new bits in tail
 * 
 * Our valid key should have len of 20 digits, and 0xF = 0b1111, so in order to get 20 digits of 1 in binary, we will use 5 of Fs i.e. 0xFFFFF
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 */

public class Repeated_DNA_Sequences_p187_sol2 {
    public List<String> findRepeatedDnaSequences(String s) {
        //hashMap + bit manipulation solution, in this solution we use a mark 0xFFFFF to avoid building whole string 
        
        //boundary check
        if(s.length() < 10) return new ArrayList<String>();
        
        int[] map = new int[256];
        map['A'] = 0;
        map['T'] = 1;
        map['C'] = 2;
        map['G'] = 3;
        
        List<String> result = new ArrayList<String>();
        
        //this set contains string that occurs before
        Set<Integer> visited = new HashSet<Integer>();
        //this set contains string that we have inserted into result
        Set<Integer> inResult = new HashSet<Integer>();
        
        int key = 0;
        
        //Since we only partially modify the key, we need to firstly initialize it
        for(int i = 0; i < 9; i++){
            key <<= 2;
            key |= map[s.charAt(i)];
        }
        
        for(int i = 9; i < s.length(); i++){
            key <<= 2;
            key |= map[s.charAt(i)];
            //our valid key has 20 digits, we use 0xFFFFF to remove digits that beyong this range
            key &= 0xFFFFF;
            
            String temp = s.substring(i - 9, i+1);
            
            //if this is not the first time we visit this substring
            if(!visited.add(key)){
                //if this is the first time we add this substring into result
                if(inResult.add(key)){
                    result.add(temp);
                }
            }
        }
        
        return result;
    }
}
