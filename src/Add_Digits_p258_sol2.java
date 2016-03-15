/**
 * Intuitive solution
 * 
 * Just do the calculation based on problem description
 * But we need a loop here
 * 
 * Time complexity: O(1)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Mar 14, 2016 3:03:28 PM
 */
public class Add_Digits_p258_sol2 {
    public int addDigits(int num) {

        while(num / 10 > 0){
            int sum = 0;
            while(num > 0){
                sum += num % 10;
                num /= 10;
            }
            num = sum;
        }
        
        return num;
    }
}
