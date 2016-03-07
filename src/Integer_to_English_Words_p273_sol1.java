/*
273. Integer to English Words

Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

For example,
123 -> "One Hundred Twenty Three"
12345 -> "Twelve Thousand Three Hundred Forty Five"
1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
Hint:

1. Did you see a pattern in dividing the number into chunk of words? For example, 123 and 123000.
2. Group the number by thousands (3 digits). You can write a helper function that takes a number less than 1000 and convert just that chunk to words.
3. There are many edge cases. What are some good test cases? Does your code work with input such as 0? Or 1000010?
(middle chunk is zero and should not be printed out)
*/

/**
 * Math and String solution
 * 
 * The basic idea is split the input into groups with 3 digits. Then we add group name (like "hundred") after 3 digits if needed.
 * We use math technique get digits in each index.
 * 
 * Inside the group with 3 digits:
 * We observed that numbers < 20 will have specific names. For numbers >= 20 and < 100, we will have a specific name for
 * the first digit (like "thirty"). For numbers > 100, we just need to get the first digit then add "hundred", then deal
 * with remaining numbers as stated above 
 * So we create two arrays, one array for num < 20, one array for nums >= 20 and < 100
 * 
 * Considering the group as a whole part
 * For numbers >= 1000, we just need to firstly get the English name of three digits, then add appropriate group name after it.
 * (like "one hundred twenty one Thousand")
 * 
 * Notice:
 * 1. The problem asks us to insert " " between each English name. In this solution, we don't have spaces inside 
 * array, so we need insert " " manually, we need to add " " space each time we insert a new English word.
 * But we may have the case that the current range of input does not have values, in such case we wouldn't even create 
 * English words for this range. So each time before we create English words for current range, we need check if we really
 * have digits in current range. 
 * 2. Since we handle each case in the same way. We may finally get a string with a trailing " ". Therefore, before we 
 * return result, we need use trim() to remove it.
 * 
 * Time complexity: O(N), where n is len of input
 * Space complexity: O(N), where n is len of input
 * 
 * @author hpPlayer
 * @date Mar 6, 2016 3:01:36 PM
 */
public class Integer_to_English_Words_p273_sol1 {
    //numbers smaller than 20 have specific names
    //Notice: no name for 0 index
    String[] LessThan20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven",
    	"Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    //numbers larger than 20 and smaller than 100 have specific start name like thiry, forty, etc
    //Notice: this list starts with 20, so for first two indexes, we have ""
    String[] MoreThan20 = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    //specific unit name for each 3 digits
    //Notice: for num < 1000, i.e. at index 0, we don't have any unit name
    String[] MoreThan1000 = {"", "Thousand", "Million", "Billion", "Trillion"};
    
    public String numberToWords(int num) {
        //String and Math solution, we will split the input in the unit of 3 digits.
        //We can create a specific program for 3 digits, and add add a unit name after each 3 digits
        //We use "/n" to get the digit in target index and use "%n" to get the remaining num
        
        //the only case that we need return "zero" is input is 0
        if(num == 0) return "Zero";
        
        String result = "";
        //this function will split input into parts with 3 digits and add unit name after each three digits. 
        //we will begin with the rightmost 3 digits. Therefore we need an index to read MoreThan1000[] forward
        int i = 0;
        
        
        while(num > 0){
            //we only handle 3 digits specifically if we have real number in it
        	//same rule applied to case < 3 digits as well, as we can see in ThreeDigits()
        	//Notice: we will always have space in the result from ThreeDigits(), so no need to add an extra space 
        	//before the unit name
            if(num%1000 != 0) result = ThreeDigits(num%1000) + MoreThan1000[i] + " " + result;
            i++;
            //finish rightmost 3 digits, shift next 3 digits to rightmost position
            num /= 1000;
        }
        
        //we wouldn't specifically handle boundary case, therefore we need manually remove trailing zeros
        return result.trim();
    }

    //a specific function to generate English with 3 digits
    public String ThreeDigits(int num){
        if(num < 20){
            //reach boundary, return nothing 
            return LessThan20[num] + " ";
        }else if(num < 100){
            //100 > input > 20, we firstly get the tenth digit(use num / 10) then the last digit(use num % 10)
            return MoreThan20[num/10] + " " + (num%10 == 0? "" : ThreeDigits(num%10));
        }else{
            //input > 100, we firstly get the hundred digit (use num/100), then the remaining digits (num%100)
            return LessThan20[num/100] + " Hundred " + (num%100 == 0? "" : ThreeDigits(num%100));
        }
    }
}
