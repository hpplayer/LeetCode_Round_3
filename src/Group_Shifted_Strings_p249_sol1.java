import java.util.*;

/*
249. Group Shifted Strings

Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
Return:

[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
Note: For the return value, each inner list's elements must follow the lexicographic order.
*/

/**
 * HashMap solution
 * 
 * The problem requires us to group strings with same shift pattern together.
 * Shift pattern can be got from analyzing string itself.
 * The difference between chars are numbers from 0 to 25, so the shift pattern can actually be represented by a string
 * In this solution, I convert the difference to a small alphabet. So HashMap key will be a word consist from alphabets.
 * Of course we can use other ways like appending delimiter after each char like 1#25#13, etc
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Feb 25, 2016 3:35:06 PM
 */
public class Group_Shifted_Strings_p249_sol1 {
    public List<List<String>> groupStrings(String[] strings) {
        //HashMap solution, we use HashMap to group strings. Key is the representation of shift pattern, value is list of strings having this pattern
        
        //to avoid mixing each char's difference we use string as shift pattern representation
        HashMap<String, List<String>> hs = new HashMap<String, List<String>>();
        //sort the array first so all strings will be sorted in lexicographic order
        Arrays.sort(strings);
        
        for(String str : strings){
            String key = getKey(str);
            if(!hs.containsKey(key)) hs.put(key, new ArrayList<String>());
            hs.get(key).add(str);
        }
        
        //we have grouped strings based on pattern, now add it to result list
        List<List<String>> result = new ArrayList<List<String>>();
        
        for(String key : hs.keySet()){
            result.add(new ArrayList<String>(hs.get(key)));
        }
        
        return result;
    }
    
    public String getKey(String s){
        StringBuilder sb = new StringBuilder();
        
        //based on the diff between two chars, we can get the shift pattern
        for(int i = 0; i < s.length() - 1; i++){
            int diff = s.charAt(i+1) - s.charAt(i);
            //diff may be negative like za and ab share same pattern, but za has diff = -25
            //we need add 26 to convert it to positive. For positive diff, we leave it as it is 
            if(diff < 0) diff += 26;
            
            //we convert diff to a small alphabet, so we can distinguish each diff
            sb.append('a' + diff);
        }
        return sb.toString();
    }
}
