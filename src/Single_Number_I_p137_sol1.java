/*
137. Single Number II

Given an array of integers, every element appears three times except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*/

/**
 * Bit manipulation solution
 * 
 * This solution is very problem specific and very hard to write it correct
 * Overall we want to write a program such that update three variables based on input
 * Ones will be set to contain input when this input had appeared odd times
 * Twos will be set to contain input when this input has appeared two times
 * Threes will be set to contain input only when this input appears three times
 * 
 * When threes are set, then we make use threes to remove input from ones and twos. And input in threes will be removed
 * in next round since ones and twos have been reset
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * Sol1 is the bit manipulation solution with finite-machine idea
 * Sol2 is the bit manipulation solution with more intuitive approach but works slower (time: O(32N))
 * 
 * @author hpPlayer
 * @date May 25, 2016 11:50:30 PM
 */
public class Single_Number_I_p137_sol1 {
    public int singleNumber(int[] nums) {
        //bit manipulation solution
        
        int ones = 0, twos = 0, threes = 0;
        
        for(int num : nums){
            //we set twos first, since ones will be updated when an element appears twice
            //we use |= to set twos so that when an element appears third time, we can still keep twos value
            twos |= ones & num;
            
            //we will set ones once an element appears in odd number, more specifically first time and third time
            ones ^= num;
            
            //we set threes once twos and ones are both valid. We use = to set its value, since once we have
            //set threes a value, in the next round, we want to reset it to 0 (at that time ones and twos will be invalid
            //, so threes can be reset)
            threes = ones & twos;
            
            //in the first and second time, threes will always be 0, so we keep ones and twos stay still
            //but once we got value in threes, we want reset ones and twos to 0
            //Thats why we use ^(threes) here.
            //Notice: we can also use &(~threes) to reset ones and twos to 0!!!!!!!!!
            //Notice: when I say 0 here, it actually means 0 appearance of the element, in other words, it equals
            //to the case that we only have one element in the input and it appears three times like [2,2,2]
            ones ^= threes;
            twos ^= threes;
        }
        
        //we will reset each number in ones to be 0 when it appeared three times, so left value in ones will
        //be the one appears only once
        return ones;
    }
}
