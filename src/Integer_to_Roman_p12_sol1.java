/*
12. Integer to Roman

Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.
*/

/**
 * Math solution
 * 
 * This most important part of this problem is to get understand how roman numerals are defined.
 * Basically we have several rules:
 * 1) There are several symbols defined in roman numerals, which can be treated as the delimiter:
 * 1-I, 5-V, 10-X, 50-L, 100-C, D-500, M-1000
 * 2) for general numbers, we just put larger numeral on left and smaller numeral on right like 15->XV
 * 3) Sometimes, we may need to repeat smaller numeral several times to get target roman number like 30->XXX
 * 4) we always have special cases before each delimiters like 4, 9, 40, 90, 400, 900. In those special cases,
 * we will put smaller numerals on left like 9->IX.
 * 
 * So in this solution, we will create a table and hard-code those delimiter symbols (1) along with symbols of
 * special numbers (4), and use another table to store values of symbols
 * Then we read the input from left to right i.e. from largest numerals to smallest numerals to create target
 * string. To satisfy rule 3) we need use an inner loop to append those symbols several times
 * 
 * Time complexity: O(1) fixed len
 * Space complexity: O(1) fixed len
 * 
 * Remark: since we need build the string from left to right, we need to create the table which keep symbols/values 
 * in descending order
 * 
 * @author hpPlayer
 * @date Apr 26, 2016 11:06:35 PM
 */
public class Integer_to_Roman_p12_sol1 {
    public String intToRoman(int num) {
        //Math + brainstorming solution. We hard code special roman strings with its values in two arrays respectively.
    	//Then convert input to target string, by reading those two arrays
        
        //We have 5 special symbols in roman numerals within input range: V, X, L, C, D, M 
        //1)Generally, we just put larger numeral on the left to build the target numbers like: 6-> VI, 60->LX
        //2)We may need to repeat smaller numeral several times to get target numbers: like 3-> III, 7->VII
        //3)But we have special cases that is one unit smaller than those special symbols like 4, 40, 90, etc.
        //In those special cases we put smaller numeral on the left like 4 -> IV, 90 -> XC.
        //For 1) and 3) we need hard code them in two tables, for 2) we can use a loop to append them
        int[] vals = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        
        StringBuilder sb = new StringBuilder();
        
        //we will build the string by reading input from left to right, accordingly, we will read two tables from
        //left to right as well, therefore we need a pointer to point where we are at table
        int index = 0;
        
        //read input from left to right and generate output from left to right as well
        while(num > 0){
            //Get the times we need to repeat in 2)
            int times = num / vals[index];
            //append curr numerals to target string
            for(int i = 0; i < times; i++) sb.append(symbols[index]);
            //then we update input by removing part that we have appended
            num -= times * vals[index];
            //move index pointer to next index
            index++;
        }
        
        return sb.toString();
    }
}
