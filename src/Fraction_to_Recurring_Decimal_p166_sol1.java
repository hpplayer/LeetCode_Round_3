import java.util.HashMap;

/*
166. Fraction to Recurring Decimal

Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".
*/

/**
 * Math + HashMap solution
 *  
 * Things need attention: 1) overflow problem 2) pos/neg sign 3)recurring digits may be 1, 2, 3, etc.(ex: 1/99, we should get 0.(01))
 * 
 * To get the repeating part, we must have repeating remainder.
 * We firstly preprocess the inputs to avoid issue 1) and 2).
 * Then we use a HashMap to record repeating remainder. Why we use a Map not set? because we need to insert "(" before the repeating part. The part
 * may have several digits. As stated above, repeating part is coming from the repeating remainder, so we can record the index that we insert the 
 * repeating part in the HashMap, then insert "(" in this position
 * 
 * Time complexity: O(N) where N is the length of decimal part
 * Space complexity: O(N) where N is the len of decimal part
 * 
 * Remark:
 * 1)This problem is similar to problem Divide_Two_Integers_p29_sol1
 * 2)Since we are inserting "(" in the mid of stringBuilder, therefore we need use insert() function not append()
 * @author hpPlayer
 * @date Feb 22, 2016 8:03:23 PM
 */
public class Fraction_to_Recurring_Decimal_p166_sol1 {
	public static void main(String[] args){
		System.out.println(new Fraction_to_Recurring_Decimal_p166_sol1().fractionToDecimal(7, -12));
	}
   public String fractionToDecimal(int numerator, int denominator) {
        //Math problem. We need to realize that if remainder is recurring, then division result will recur too 
        //We need a HashMap to record the recurring remainder and curr str length.
        //So when we found the recurring part is multiple digits, we can add "(" into proper position
        
        //1) deal with attention 1: convert to long type to avoid overflow problem
        long newNum = numerator, newDenom = denominator;
        
        StringBuilder sb= new StringBuilder();

        //2) deal with attention 2: check sign
        //we can't use "/", as we may get 0 if abs(newNum) < abs(newDenom)
        if(newNum * newDenom < 0){
            sb.append("-");
            newNum = Math.abs(newNum);
            newDenom = Math.abs(newDenom);
        }
       
        sb.append(newNum/newDenom);
        
        long remainder = newNum % newDenom; 
  
        //if inputs are dividable, then we return result directly
        if(remainder == 0) return sb.toString();
        
        //otherwise, add "."
        sb.append(".");
        
        //3) deal with attention 3: Using hashMap to record recurring remainder and its index, so we can deal with multiple digits
        //key is remainder, val is its index
        HashMap<Long, Integer> hs = new HashMap<Long, Integer>();
        
        //while we have not got recurring remainder
        while(!hs.containsKey(remainder)){
            //we firstly record curr index before recurring
            hs.put(remainder, sb.length());
            
            //10 times remainder as we did in math
            remainder *= 10;
            
            sb.append(remainder/newDenom);
            remainder %= newDenom;
            //if we found remainder == 0, then inputs are dividable, we return result directly
            if(remainder == 0) return sb.toString();
        }

        //we found recurring remainder, we add () based on hashMap
        sb.insert(hs.get(remainder), "(").append(")");
        
        return sb.toString();
    }
}
