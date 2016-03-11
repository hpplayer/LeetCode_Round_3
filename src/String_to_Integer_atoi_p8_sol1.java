/*

8. String to Integer (atoi)

Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

Update (2015-02-10):
The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload
button  to reset your code definition.

spoilers alert... click to show requirements for atoi.

Requirements for atoi:
The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character,
takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or
it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or
INT_MIN (-2147483648) is returned.
*/


/**
 * String + observation solution
 * 
 * We have following cases that need to be handle
 * 
 * Overflow: we still use int type, since we build int result digit by digit, we will catch the result before we reach the limit len
 * invalid inputs before digits: we don't allow them exists, if we found there is invalid char before digit char then we will return 0
 * invalid inputs after digits: we ignore them, once we have valid digits and found invalid char again, we will stop there and return result
 * positive/negative sign: if input have sign char, then we use a variable sign to keep the positive and negative
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Mar 10, 2016 5:02:09 PM
 */
public class String_to_Integer_atoi_p8_sol1 {
    public int myAtoi(String str) {
        //string + observation problem. We will only read valid inputs, chars except for signs and digits will be treated as invalid.
    	//keep positive/negative and handle overflow problem
        
        //we want make the input as clean as possible, so we firstly remove heading and trailing white spaces
        str = str.trim();
        
        //boundary check
        if(str.length() == 0) return 0;      
        
        int index = 0;
        int sign = 1;
        
        //record the positive/negative, manually skip chars that are not digits, therefore the remaining non-digit chars will be invalid chars
        if(str.charAt(0) == '+'){
            sign = 1;
            index++;
        }else if(str.charAt(0) == '-'){
            sign = -1;
            index++;
        }
        
        int result = 0;
        
        //we only look at valid digit, we will stop conversion once we found the invalid char appears
        while(index < str.length() && Character.isDigit(str.charAt(index))){
            //Notice: we move index here!!!!!!!!
            int digit = str.charAt(index++) - '0';
            
            //to prevent overflow, we will check boundary before we reach the boundary len
            //Int.max and int.min have same prefix except for the last digit
            if( (result > Integer.MAX_VALUE/10) || (result == Integer.MAX_VALUE/10 && digit > 7)){
                //int_max has 7 in last digit while int_min has 8 in last digit, their prefix is same
                //if curr result already > prefix, then adding new digit will definitely give overflow, no matter in max or min
                //if curr result == prefix, and last digit > 7, then we will have overflow in int_max, in int_min we may have overflow
                //or just equals to int_min, therefore return boundary value is also fine
                return sign == 1? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            
            result = result * 10 + digit;
        }
        
        //return result, * sign to keep the positive/negative
        return result * sign;
    }
}
