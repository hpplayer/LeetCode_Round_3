import java.util.Arrays;

/*
43. Multiply Strings

Given two numbers represented as strings, return multiplication of the numbers as a string.

Note: The numbers can be arbitrarily large and are non-negative.
*/

/**
 * Math solution
 * 
 * We create an array to represent the final product. Then we do multiplication for each digit from input 1 and 2. We will accumulate sum in same index
 * Since we read the input backward, we will reverse two inputs for convenience.
 * Then we process the product array to output an valid product number
 * 
 * Time complexity: O(mn), where m is len of input1, n is len of input2
 * Space complexity: O(m+n)
 * 
 * @author hpPlayer
 * @date Mar 3, 2016 7:06:25 PM
 */

public class Multiply_Strings_p43_sol1 {
	public static void main(String[] args){
		String num1 = "5";
		String num2 = "5";
		System.out.println(new Multiply_Strings_p43_sol1().multiply(num1, num2));
	}
    public String multiply(String num1, String num2) {
        //math solution, we observe that len(num1) * len(num2) will give a product no more than len(num1) + len(num2), think about 9 * 9 gives four digits..
        //so we can create a product array and fill nums in it.
        //we also observe that during the multiplication, the product of val at index1 from num1 with val at index2 from num2 will accumulate
        //at index:index1 + index2, so we use this rule to update the product array
        
        
        //in multiplication, we read backward, so we want the last digit to have index 0. Reverse string is a good way
        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();
        
        //create result array with length of (len(num1) + len(num2))
        int[] result = new int[num1.length() + num2.length()];
        
        //update result[]
        for(int i = 0; i < num1.length(); i++){
            for(int j = 0; j < num2.length(); j++){
            	//same as we did in multiplication, we take the sum of digit multiplication in same index
                result[i + j] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
        }
        
        //now we process result[] to make each digit valid, in the same time we will create result string
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        
        for(int i = 0; i < result.length; i++){
            result[i] += carry;
            carry = result[i]/10;
            result[i] %= 10;
            //we read array forward, so actually we are inserting digit backward
            sb.insert(0, result[i]);
        }
        
        //our result string may have heading zeros, so we need remove them
        //Notice: in case the product is 0, we need keep the last zero if length = 1
        while( sb.length() > 1 && sb.charAt(0) == '0') sb.deleteCharAt(0);
     
        return sb.toString();   
    }
}
