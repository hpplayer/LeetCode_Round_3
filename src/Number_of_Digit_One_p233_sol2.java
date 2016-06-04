/**
 * Math solution
 * 
 * In this solution we count ones from digit to digit
 * For digit at each index i, we will divide curr input into three parts
 * left part to i -1, i, i to right part
 * left part would have (i-1) * base ones (base has i+1 len, if i starts with 0)
 * Ones in left part will depend on value of digit
 * right part, we have counted it before
 * 
 * ex: input is 1032, we index it from right to left to be 0 to 3 then
 * 
 * At index 0, digit is 2, 2 > 1, then we have (103 + 1) * 1 ones at this index
 * At index 1, digit is 3, 3 > 1, then we have (10 + 1) * 10 ones at this index
 * At index 2, digit is 0, 3 < 1, then we have (1) * 100 ones at this index
 * At index 3, digit is 1, 1 == 1, then we have 0 * 1000 + 1032%1000 + 1 ones at this index (we added 1 to include
 * starting number, i.e. 1000 here)
 * 
 * Time complexity: O(L), where L is len of input
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jun 3, 2016 11:28:25 PM
 */
public class Number_of_Digit_One_p233_sol2 {
    public int countDigitOne(int n) {
        //Math solution. We count ones in input from digit to digit
        
        int result = 0;
        
        //to prevent overflow, we have to use long here, then case back to int
        //We cant use n/base >= 10 as condition here, otherwise we wouldn't handle cases that n/base < 10
        for(long base = 1; base <= n; base *= 10){
            //get numbers for left + mid part
            int leftMid = (int) (n/base);
            //get mid part
            int mid = leftMid%10;
            
            if(mid > 1){
                result += (leftMid/10 + 1) * base;
            }else if(mid == 1){
                //add 1 to include the starting number ended with 0
                result += (leftMid/10) * base + n%base + 1;
            }else{
                result += (leftMid/10) * base;
            }
        }
        
        return result;
    }
}
