import java.util.*;

/*
241. Different Ways to Add Parentheses

Given a string of numbers and operators, return all possible results from computing all the different possible
ways to group numbers and operators. The valid operators are +, - and *.


Example 1
Input: "2-1-1".

((2-1)-1) = 0
(2-(1-1)) = 2
Output: [0, 2]


Example 2
Input: "2*3-4*5"

(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
Output: [-34, -14, -10, -10, 10]
*/

/**
 * Backtracking solution
 * 
 * To make us more convenient, we will decompose input based on the "sign"
 * Otherwise we have to deal with multiple signs in a single recursion
 * To improve the speed, we will cache all previous results, therefore it is also a DP solution
 * 
 * Time complexity: recursion, exponential, masters theorem
 * 
 * @author hpPlayer
 * @date Jun 4, 2016 11:56:55 AM
 */

public class Different_Ways_to_Add_Parentheses_p241_sol1 {
    public List<Integer> diffWaysToCompute(String input) {
        return DFS(input, new HashMap<String, List<Integer>>());
    }
    
    public List<Integer> DFS(String input, HashMap<String, List<Integer>> hs){
        //previous calculated input, return directly
        if(hs.containsKey(input)) return hs.get(input);
        
        List<Integer> result = new ArrayList<Integer>();
        
        for(int i = 0; i < input.length(); i++){
            //digit char, skipping
            if(Character.isDigit(input.charAt(i))) continue;
            
            char sign = input.charAt(i);
            
            //calculated results from left part
            List<Integer> left = DFS(input.substring(0, i), hs);
            //calculated results from right part
            List<Integer> right = DFS(input.substring(i+1), hs);
            
            //generate all combinations
            for(Integer l : left){
                for(Integer r : right){
                    int temp = 0;
                    switch(sign){
                    case '+':
                        temp = l + r;
                        break;
                    case '-':
                        temp = l - r;
                        break;
                    case '*':
                        temp = l * r;
                        break;
                    }
                    result.add(temp);
                }
            }
        }
        
        //if result is empty, then input is pure integer, we reach the bottom, return
        if(result.isEmpty()){
            result.add(Integer.valueOf(input));
        }
        
        hs.put(input, result);
        
        return result;
    }
}
