import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/*
49. Group Anagrams

Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:
For the return value, each inner list's elements must follow the lexicographic order.
All inputs will be in lower-case.
*/

/**
 * HashMap + sort solution
 * 
 * We use HashMap to store strings that are belong to same group. The key is sorted string, ex: "zxa"->key will be "axz"
 * We then output the entries in HashMap, but before we insert them into result list, we need to sort them to follow the requirement that inner list should
 * follow lexicographic order
 * 
 * Time complexity:
 * Sort and group inputs: O(N) where we assume the string len can be ignored when comparing to input length
 * Sort and output: O(NlogM) where we assume the each group will have average size of M. We will have N/M groups, sorting each group costs O(MlogM), so we have O(NlogM)
 * Total time: O(N + NlogM)
 * 
 * Space complexity:
 * O(N) where each input string itself is a group
 * 
 * Remark:
 * We can also sort the entire input first, so that the order we insert string into list will be sorted, and we don't need to sort again
 * But this way will slow our program:
 * In this way, our sort part will cost: O(NlogN)
 * In sol1 way: assume on average, each entry of hashMap has O(M) size, and we will have O(N/M) entries, sort each entry will cost O(MlogM) time
 * Therefore we will have O(NlogM) time in the sort part where M <= n, so sol1 should be faster than this way, therefore I did not list this solution here
 * 
 * @author hpPlayer
 * @date Mar 7, 2016 4:47:54 PM
 */
public class Group_Anagrams_p49_sol1 {
    public List<List<String>> groupAnagrams(String[] strs) {
        //hashMap + sort solution. We firstly sort each input str, then use sorted string to get the entry in the HashMap
        //When we are done the input list, and begin generate result, we need to sort the entry so that inner list will follow lexicographic order
        
        HashMap<String, List<String>> hs = new HashMap<String, List<String>>();    
    
        for(String str : strs){
            //we convert str to charArray, so that we can sort them use built-in function
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String newStr = new String(chars);
            
            if(!hs.containsKey(newStr)){
                hs.put(newStr, new ArrayList<String>());
            }
            
            hs.get(newStr).add(str);
        }
        
        List<List<String>> result = new ArrayList<List<String>>();
        
        //we have done the input, now we will output result
        for(String str : hs.keySet()){
            List<String> list = hs.get(str);
            //we need to sort the list to meet the problem's requirement
            Collections.sort(list);
            result.add(list);
        }
        
        return result;
    }
}
