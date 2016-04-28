import java.util.HashMap;

/*
13. Roman to Integer 

Given a Roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.

*/

/**
 * Math solution
 * 
 * In Roman numerals, we have fixed symbol representations for numbers starting with 1, 5.
 * So we can read symbols from input and accumulate the value to generate output. 
 * For general cases where smaller numerals are on the right, we just add the value we get.
 * For special cases where smaller numerals are on the left(numbers one unit smaller than symbols), we just subtract 
 * the value we get
 * 
 * Remark:
 * We read Roman string from right to left since each Roman numerals are interpreted as right as possible.
 * But we build Roman string from left to right, since we are reading integer input in this case, and we need get 
 * to know the number of repeats for each digit at its index, therefore we can only read input from left to right,
 * otherwise, if we read reverse way, we will get too many repeats for digits at smaller indexes
 * 
 * Time complexity: O(1) as input len is fixed
 * Space complexity: O(1) as range of input is fixed
 * 
 * @author hpPlayer
 * @date Apr 27, 2016 8:10:27 PM
 */
public class Roman_to_Integer_P13_sol1 {
    public int romanToInt(String s) {
        //Math solution. In Roman numerals, we always put smaller numerals on right except for 
        //special cases where numerals are just one unit smaller than symbols
        //Besides, we always interpret numerals as right as possible. For example XVI, V should be interpreted 
        //with I first, then X, so XVI is 16. Therefore we need read input string from right to left, and 
        //compare each pair of numerals, if left >= right, then we add left, if left < right, then we subtract left
        
        //we read input and interpret each digit directly
        HashMap<Character, Integer> hs = new HashMap<Character, Integer>();
        hs.put('I', 1);
        hs.put('V', 5);
        hs.put('X', 10);
        hs.put('L', 50);
        hs.put('C', 100);
        hs.put('D', 500);
        hs.put('M', 1000);
        
        int result = 0;
        //We set right = 0, so that rightmost digit in input can be added correctly (right > left)
        int right = 0;
        
        //we read input from right to left thats how Roman numerals are defined 
        for(int i = s.length() - 1; i >= 0; i--){
            int left = hs.get(s.charAt(i));
            //if left < right, then we subtract left, if left >= right, then we add left
            result += left < right?  -left : left;
            right = left;
        }
        
        return result;
    }
}
