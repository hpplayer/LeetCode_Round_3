/*
50. Pow(x, n)

Implement pow(x, n).
*/

/**
 * Each loop we will reduce power by half, and multiple x by x
 * Two points need attention: 1) input power may be negative 2) input power may be an odd number
 * In case power is negative, it is better for us to convert it to positive in last recursion. Why? if the input power is Integer.MIN_VALUE, then 
 * convert it will give overflow
 * In case power is odd number, we will still half power, but we will multiple an extra input in front
 * 
 * Time complexity and space complexity: O(1)
 * 
 * 
 * Sol1 is recursive version
 * Sol2 is iterative version, but 
 * 
 * @author hpPlayer
 * @date Mar 1, 2016 9:54:23 PM
 */
public class Pow_p50_sol1 {
	public static void main(String[] args){
		System.out.println(myPow(2, -3));
	}

    public static double myPow(double x, int n) {
        //recursive solution, each recursion we will reduce n by half and multiply x by itself
        
        //boundary check
    	//power 0 will always give 0, no matter base is positive or negative
        if(n == 0) return 1;
        if(n == 1) return x;
        
        //if power is negative, we should convert it in last step to prevent overflow
        if(n == -1) return 1/x;


        //check if power is odd or even    
        //if n is odd, then we need times an extra base, and subtract 1 from power!!!!!!!!
        //we use & instead of % here, so we can freely compare the result with 0 or 1 
        //if we use %here: then we can only compare result with == 0, since the remainder may either positive or negative.
        //so we cannot compare it with 1
        return ( (n&1) == 0)? myPow(x*x, n/2) : x * myPow(x*x, (n-1)/2);
    }
}
