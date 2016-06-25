/*
365. Water and Jug Problem

You are given two jugs with capacities x and y litres. There is an infinite amount of water supply available.
You need to determine whether it is possible to measure exactly z litres using these two jugs.

If z liters of water is measurable, you must have z liters of water contained within one or both buckets by the end.

Operations allowed:

Fill any of the jugs completely with water.
Empty any of the jugs.
Pour water from one jug into another till the other jug is completely full or the first jug itself is empty.
Example 1: (From the famous "Die Hard" example)

Input: x = 3, y = 5, z = 4
Output: True
Example 2:

Input: x = 2, y = 6, z = 5
Output: False
*/
/**
 * Pure Math solution
 * 
 * will update comments tmr
 * 
 * Ref:
 * https://en.wikipedia.org/wiki/Coprime_integers
 * https://en.wikipedia.org/wiki/B%C3%A9zout%27s_identity
 * 
 * @author hpPlayer
 * @date Jun 25, 2016 1:06:58 AM
 */
public class Water_and_Jug_Problem_p365_sol1 {
    public boolean canMeasureWater(int x, int y, int z) {
        //limit brought by the statement that water is finally in one or both buckets
        if(x + y < z) return false;
        //case x or y is zero, (in case z is zero, we can always achieve it by pouring all water)
        if( x == z || y == z || x + y == z ) return true;
        
        //get GCD, if inputs are coprime integers, then we can use the property of B¨¦zout's identity
        return GCD(x, y) == 1;
    }
    
    public int GCD(int a, int b){
        while(b != 0 ){
            int temp = b;
            b = a%b;
            a = temp;
        }
        return a;
    }
}
