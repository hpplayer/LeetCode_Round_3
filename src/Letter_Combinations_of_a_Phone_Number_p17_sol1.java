import java.util.*;
/*
17. Letter Combinations of a Phone Number

Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.



Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:
Although the above answer is in lexicographical order, your answer could be in any order you want.
*/

/**
 * BFS iterative solution
 * 
 * We use nested loops to create the result list. The outer loop is to scan each digit, the mid loop is to scan each previous created list
 * the inner loop is to insert all letters represented by current digit into previous created list
 * 
 * time complexity: O(n^2)
 * Space complexity: O(3^n)
 * 
 * sol1 is iterative solution, it is more like BFS
 * sol2 is recursive solution, it is more like DFS
 * 
 * @author hpPlayer
 * @date Mar 6, 2016 12:23:31 AM
 */
public class Letter_Combinations_of_a_Phone_Number_p17_sol1 {
    public List<String> letterCombinations(String digits) {
        //iterative BFS solution, we use three loops to update result list. The first loop is for each digit, the second loop is for 
        //each previous created list, the third loop is for the letters current digit represent
        
        //boundary check
        if(digits.length() == 0) return new LinkedList<String>();
        
        //use array to represent map
        String[] dict = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        
        LinkedList<String> result = new LinkedList<String>();
        result.offerFirst("");
        
        //the first loop is for each digit
        for(int i = 0; i < digits.length(); i++){
            String digit = dict[digits.charAt(i) - '0'];
            
            int size = result.size();
            //the second loop is for each prev created list
            for(int j = 0; j < size; j++){
                String temp = result.pollLast();
                //the third loop is for letters current digit represent
                for(int k = 0; k < digit.length(); k++){
                    result.offerFirst(temp + digit.charAt(k));
                }
            }
        }
        
        return result;
    }
}
