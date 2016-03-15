import java.util.*;

/*
336. Palindrome Pairs

Given a list of unique words. Find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words,
i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]
Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
*/

/**
 * HashMap solution
 * 
 * We firstly use a hashMap to record all words in the input. Then we scan the input, and split each word in all possible ways. In each split way,
 * we will check map to see if we have the reverse version of left and right side. If we have, and the other part is a valind palindrome substring,
 * then we can add curr pair into the result list
 * 
 * Ex: 
 * abb a
 * When we split abb into a and bb, we found there is a reverse version of a while bb is a valid palindrome, so abba is a valid pair
 * 
 * This problem includes some boundary cases:
 * We may have "" in the input which makes self-palindrome substring to be valid palindrome pair. And add "" before or after self-palindrome will
 * both get a valid palindrome. Therefore we should allow the self-palindrome be left part right part once, so each part should have len from 0 to len
 * However this would cause problem, lets say we have inputs["ab" "ba"]
 * 
 * in following 4 cases, we will check left part and right part individually, which means there will be totally 8 checks need to be done
 * and we will skip two checks, i.e. we will skip cases where the right part is whole string!!!!!!!!!!!
 * when look at "ab":
 * 1 when left part = "", we have right part = "ab", (checking right part in case 1 will get same result with checking left part in case 4)
 * 2 when left part = "ab", we have right part = ""
 * when look at "ba"
 * 3 when left part = "", we have right part = "ba" (checking right part in case 3 will get same result with checking left part in case 2)
 * 4 when left part = "ba", we have right part = ""
 * We will add [0, 1] twice, and add [1, 0] twice, to avoid that, when looking at whole string, we will not check right part = whole string and only
 * add the case that left part = whole string 
 * 
 * 
 * In case we have inputs ["a", ""]
 * when look at "a":
 * 1 when left part = "", right part = "a"
 * 2 when left part = "a", right part = ""
 * we will not check right part in case 1. and we add ["a", ""] into result
 * when check right part in case 2, which is not whole string, we will also add ["", "a"] into result 
 * 
 * Time complexity: O(nk^2), where k is avg len of word, n is len of input list. we need split the string in k ways, in each way, we will use O(k) time
 * to validate if the substring is palindrome
 * 
 * Space complexity: O(N)
 * 
 * Remark:
 * 1. Intuitive O(k*N^2) solution will give LTE 
 * 2. This problem can also be solved by a trie, but I have not seen a good implementation online, will update it soon
 * 
 * @author hpPlayer
 * @date Mar 14, 2016 7:24:09 PM
 */
public class Palindrome_Pairs_p336_sol1 {
	public static void main(String[] args){
		String[] words = {"a",""};
		System.out.println(new Palindrome_Pairs_p336_sol1().palindromePairs(words));
	}
	
    public List<List<Integer>> palindromePairs(String[] words) {
        //hashMap solution, we use a HashMap to record all strings and their index. Then we use another loop to check each substring
        //of each word, and see if we have a recorded string in the map that can make it a palindrome
        
        Map<String, Integer> hs = new HashMap<String, Integer>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        //first step, put all words into hashMap
        for(int i = 0; i < words.length; i++) hs.put(words[i], i);
        
        //second step, rescan the input list, and split each word in all possible ways
        for(int i = 0; i < words.length; i++){
            String word = words[i];
            //split word. To cover the "" case as well as whole string case, the substring len should from 0 to len
            for(int j = 0; j <= word.length(); j++){
                String left = new StringBuilder(word.substring(0, j)).reverse().toString();
                String right = new StringBuilder(word.substring(j, word.length())).reverse().toString();
                //search reverse part of left/right
                //a valid pair should have same boundaries, mid is palin, and two indexes are not same
                if( hs.containsKey(left) && hs.get(left) != i && isPalin(right) ){
                    //found a reverse version of left boundary i.e. curr word + other word => palin
                    result.add( Arrays.asList(i, hs.get(left) ));
                }
                
                //in case the whole string can be front and back part of palin, then it will appears in left once and right once,  
                //If we have its counterpart in the list, then including the appearences in the counterpart, we will add same results
                //twice. To avoid that, we will control the output, we will skip the case that whole string is in right part. 
                //So when whole string is in left part we will add one result, and add another result during the scan of counterpart
                
                //skip the case whole string is in right part (j != 0)
                if(j != 0 && hs.containsKey(right) && hs.get(right) != i && isPalin(left)){
                    //found a reverse part of right boundary i.e. other word + curr word => palin
                    result.add(Arrays.asList(hs.get(right), i));
                }
            }
        }
        
        return result;
        
    }
    
    public boolean isPalin(String s){
        //use len/2 instead of left-right pointers to make code clean
        for(int i = 0; i < s.length()/2; i++){
            if(s.charAt(i) != s.charAt(s.length() - 1 - i)) return false;
        }
        return true;
    }
}
