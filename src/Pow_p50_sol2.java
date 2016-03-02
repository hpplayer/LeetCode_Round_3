/**
 * Iterative version.
 * 
 * Similar to sol1, each iteration we will times base by itself and half the power.
 * But now we will convert power to long type. Why? If power is int_min, then our iteration program needs modification to avoid overflow and behave
 * same the power is positive. It is not easy to do that, so we will convert type and get an easy way
 * 
 * Time complexity and space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Mar 1, 2016 10:42:29 PM
 */
public class Pow_p50_sol2 {
	public static void main(String[] args){
		System.out.println(new Pow_p50_sol2().myPow(8.88023, 3));
	}
    public double myPow(double x, int n) {
        //iterative solution, convert power type to long to avoid overflow
        
        //boundary check
        if(n == 0) return 1;
        
        //convert type
        long m = n;
        
        //check for negative
        if(m < 0){
            m = -m;
            x = 1/x;
        }
        
        double result = 1.0;
        
        //do the iteration
        //each iteration half the power and increase the base
        for(; m > 0; m/=2, x *= x){
            //if power is odd, then we need multiply base an extra once
            //we will stop the loop when m = 1 or m = -1
            //therefore it guarantees that result value will be updated finally
            if( (m&1) == 1) result *= x;
        }
        
        return result;
    }
}
