/*
38. Count and Say

The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.
*/

/**
 * String solution
 * 
 * This solution is the intuitive solution that we build the result string one by one until we get the nth string
 * Each iteration, we will count num of consecutive same chars then append count and char to the temp string
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Mar 7, 2016 1:57:17 PM
 */

public class Count_and_Say_p38_sol1 {
    public String countAndSay(int n) {
        //intuitive string soltuion, we just build the result string one by one. Each iteration we generate curr string by 
        //counting and appending chars 
        
        //boundary check
        if(n <= 0) return "";
        
        //initalize start num
        String num = "1";
        
        for(int i = 1; i < n; i++){
            num = getStr(num);
        }
        
        return num;
     }
     
     public String getStr(String s){
         StringBuilder sb = new StringBuilder();
         
         for(int i = 0; i < s.length(); i++){
             //current char contributes 1 to count
             int count = 1;
             while(i + 1 < s.length() && s.charAt(i) == s.charAt(i+1)){
                 //as long as we found consectuive same chars, we will increase count and index
                 count++;
                 i++;
             }
             sb.append(count).append(s.charAt(i));    
         }
         
         return sb.toString();
     }
}
