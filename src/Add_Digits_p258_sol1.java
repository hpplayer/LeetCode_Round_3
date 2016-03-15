/*
Add Digits

Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

For example:

Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

Follow up:
Could you do it without any loop/recursion in O(1) runtime?

Hint:

A naive implementation of the above process is trivial. Could you come up with other methods?
What are all the possible results?
How do they occur, periodically or randomly?
You may find this Wikipedia article useful. (https://en.wikipedia.org/wiki/Digital_root)
*/

/**
 * Math + observation solution
 * 
 * We observed that there is a pattern in the problem:
 * 1 10 19 28 .. => will get 1 eventually
 * 2 11 20 29 .. => will get 2 eventually
 * 3 12 21 30 .. => will get 3 eventually 
 * 
 * We found that this is actually a 9-hex pattern
 * Each 9 numbers will create a loop, and go back to 0
 * 
 * So here is the basic idea:
 * Since each series starts with a single digit <= 9, and they are increased by 9 each time, so we can use %9 to get the result
 * But We need to check if the input is 0, since 0 and 9 series will both get 0 in %9 
 * 
 * Time complexity: O(1)
 * Space complexity: O(1)
 * 
 * Remark:
 * The intuitive solution where we solve the problem by loops can be found in sol2
 * 
 * @author hpPlayer
 * @date Mar 14, 2016 3:48:42 PM
 */
public class Add_Digits_p258_sol1 {
    public int addDigits(int num) {
        //math + observation solution, use %9 to get the first digit
        
        //firstly check if num is 0, since 0 and 9 series will both give 0 in the "%9" operation
        if(num == 0) return 0;
        
        //otherwise return "%9" result, and we need to return 9 when %9 gives 0
        return num%9 == 0? 9 : num%9;
    }
}
