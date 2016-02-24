/**
 * Iterative solution
 * 
 * Similar to sol1, but now we use iterative version
 * Each iteration will divide input by one divisor until we cannot forward
 * If the input is an ugly number, then result should be 1
 * 
 * Time complexity: O(1)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Feb 25, 2016 10:23:34 AM
 */
public class Ugly_Number_p263_sol2 {
    public boolean isUgly(int num) {
        //Iterative solution
        
        if(num <= 0) return false;
        
        
        for(int divisor : new int[]{2, 3, 5}){
            //divide input by all possible divisor
            while(num%divisor == 0){
                num /= divisor;
            }
        }
        
        //check if result is 1
        return num == 1;
    }
}
