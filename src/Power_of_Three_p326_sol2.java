/**
 * Math solution
 * 
 * isPowerOfThree() uses the fact that if n is power of 3, then it will be a factor of largest n with power of 3 in integer range, which is 3^19
 * isPowerOfThree()2 uses the fact that if n is power of 3, then log3N will give us an integer result. We don't have log3() in Java, therefore we just
 * use log10() to change the base and check result. We cannot use log() due to the precision problem
 * 
 * Time and Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Feb 25, 2016 4:42:51 PM
 */
public class Power_of_Three_p326_sol2 {
    public boolean isPowerOfThree(int n) {
        //Math solution. If n is power of 3, since 3 is prime, then n will be a factor of largest number from power of 3
        //How to find this number? we just power 3 until we reach the int.max. This number is 3^19
        return n >= 0 && (Math.pow(3, 19)%n == 0);
    }
    
    public boolean isPowerOfThree2(int n) {
        //Math solution, if n is power of 3,then we know that log3N will give an integer
        //Here we just use formula to change the base of log which is logb(a) = logc(a)/logc(b)
        //we cannot directly choose log() with e base due to precision problem
        //we have to use log10() with 10 base.
        
        //use %1 to check if log3N will give an integer
        return (Math.log10(n)/Math.log10(3) % 1) == 0;
    }
}
