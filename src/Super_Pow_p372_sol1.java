/*
372. Super Pow

Your task is to calculate ab mod 1337 where a is a positive integer and b is an extremely large positive integer
given in the form of an array.

Example1:

a = 2
b = [3]

Result: 8
Example2:

a = 2
b = [1,0]

Result: 1024
*/

/**
 * Math solution
 * 
 * Math 1: 
 * x^abc = (x^ab)^10 * x^c as we have ab * 10 + c = abc
 * So we can repeatedly reduce input to smaller powers
 * 
 * Math 2:
 * (x^abc)%1337 = (((x^ab)^10)%1337 * ( (x^c)%1337) )%1337 as we have ab%1337 = ((a%1337) * (b%1337))%1337
 * so we can use keep value correct while reduce input
 * 
 * Math 3:
 * we can have a specific function to calculate (x^ab)%1337 and (x^c)%1997. since we always attach mod operation
 * here, our number will never exceed 1336
 * 
 * We apply above maths to solve this problem
 * 
 * Time complexity: O(N) Since we always keep result value < 1337, and power number always <= 10.
 * Space complexity: O(1)
 * @author hpPlayer
 * @date Jul 8, 2016 9:58:08 PM
 */
public class Super_Pow_p372_sol1 {
    public int superPow(int a, int[] b) {
        int result = 1;
        
        for(int c : b){
            //power prev result by 10, and add a^c%1337 to it
            //use (a*b)%1337 = (a%1337 * (b%1337))%1337 to split inputs
            result = (pow(result, 10) * pow(a, c)) %1337;
        }
        
        return result;
    }
    
    public int pow(int x, int a){
        //x is base, a is power
        
        int result = 1; 
        
        for(int i = 0; i < a; i++){
            //multiple base x (i) times
            //since result always < 1337, actually we can replace result%1337 by result
            //I list it here to make it more clear
            result = ((result%1337) * (x%1337)) %1337;
        }
        
        return result;
    }
}
