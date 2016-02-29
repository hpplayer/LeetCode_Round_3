import java.util.*;

/*
179. Largest Number

Given a list of non negative integers, arrange them such that they form the largest number.

For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

Note: The result may be very large, so you need to return a string instead of an integer.
*/		

/**
 * String sort solution
 * 
 * We use built-in sort feature to sort the nums. We let Java help us sort the input nums. 
 * So that from left to right we will have a1 + a2 > a2 + a1. Therefore, in the end, we just need to build the 
 * string by scanning the sorted array
 * 
 * Time complexity: O(NlogN) sort, building result O(N), so total time complexity still be O(NlogN)
 * Space complexity: O(N) as we need convert int[] to string[]
 * 
 * @author hpPlayer
 * @date Feb 28, 2016 10:43:38 PM
 */

public class Largest_Number_p179_sol1 {
    public String largestNumber(int[] nums) {
        //string comparsion problem. We just use built in sort feature to sort the input array based on s1 + s2 value
        
        //to be convenient we will convert int[] to strings[]
        String[] strs = new String[nums.length];
        
        for(int i = 0; i < nums.length; i++) strs[i] = nums[i] + "";
        
        Arrays.sort(strs, new Comparator<String>(){
           public int compare(String a, String b){
                //we want order the array so that a + b > b + a
                //descending order, so return 1 to make larger string front
                return (b + a).compareTo(a + b);
           } 
        });
        
        //if the largest string is 0, then we just need return 0
        if(strs[0].equals("0")) return "0";
        
        StringBuilder sb = new StringBuilder();
        
        for(String str : strs){
            sb.append(str);
        }
        
        return sb.toString();
    }
}
