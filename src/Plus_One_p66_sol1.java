/*
Plus One

Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.
*/

/**
 * Math problem
 * 
 * We scan the digits[] backward. If we found some cells can consume carry 1, we can return early.
 * Otherwise, we will put an extra 1 in front and return a longer array
 * 
 * @author hpPlayer
 * @date Nov 4, 2015 2:00:56 AM
 */
public class Plus_One_p66_sol1 {
    public int[] plusOne(int[] digits) {
        //since most significant digit is at index 0,
        //we will visit the digits[] backward to update array following math rule 
        for(int i = digits.length -1; i>=0; i--){
            digits[i] += 1;
            
            //if current cell can consume the carry 1, then we return result directly
            if(digits[i] < 10) return digits;
            
            //otherwise its value must be 0, we need set it to 0, and pass carry 1
            digits[i] = 0;
        }
        
        //if we can't consume the carry 1 even we reach the heading cell
        //then we have to add 1 in head and build a longer array
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }
}
