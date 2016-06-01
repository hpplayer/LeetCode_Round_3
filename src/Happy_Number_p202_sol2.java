/**
 * Slow and fast pointer solution
 * 
 * Similar idea to sol1, we need use a method to detect loop. Here we use slow and fast pointer solution
 * 
 * Time complexity: O(1) it is hard to determine the time complexity, but it seems all numbers will be finally
 * converted to 1 or infinite loop in exponential time, and the max len of input is known (int_max), so we can use O(1) here
 * space complexity: O(1), we don'st need extra space here
 * 
 * @author hpPlayer
 * @date Jun 1, 2016 12:12:21 AM
 */
public class Happy_Number_p202_sol2 {
    public boolean isHappy(int n) {
        //slow and fast pointer solution
        
        int slow = n;
        int fast = n;
        
        do{
            slow = getNumber(slow);
            fast = getNumber(getNumber(fast));
        }while(slow != fast);
        
        return slow == 1;
    }
    
    public int getNumber(int n){
        int result = 0;
        while(n > 0){
            result += (n%10) * (n%10);
            n /= 10;
        }
        return result;
    }
}
