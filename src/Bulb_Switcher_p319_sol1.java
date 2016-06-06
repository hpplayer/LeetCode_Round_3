/*
319. Bulb Switcher

There are n bulbs that are initially off. You first turn on all the bulbs.
Then, you turn off every second bulb. On the third round, you toggle every third bulb (turning on if it's off or turning
off if it's on). For the ith round, you toggle every i bulb. For the nth round, you only toggle the last bulb.
Find how many bulbs are on after n rounds.

Example:

Given n = 3. 

At first, the three bulbs are [off, off, off].
After first round, the three bulbs are [on, on, on].
After second round, the three bulbs are [on, off, on].
After third round, the three bulbs are [on, off, off]. 

So you should return 1, because there is only one bulb is on.
*/		
		

/**
 * Math + Observation solution
 * 
 * First of all we need to understand what the problem is asking for.
 * The problem asks us to return the num of lights that is still on after nth round.
 * For each ith round, we will toggle (on or off) for each bulb that is divided by i
 * Thats why in first round, all bulbs are on since all nums are divided by 1
 * 
 * Then lets look at the input. Each number has a pair of factors, unless the number can be root_squared
 * Ex: input 6: 1, 6, 2, 3; input 9: 1, 9, 3
 * Based on the rule defined above. all those non-root_squared input will be turned off finally as they will be 
 * toggled in even times. And those root_squarable bulbs will be kept on finally since they will be toggled in 
 * odd times. Therefore the goal is to count how many root_squarable are there <= n. 
 * 
 * We noticed that this count value is just = n^0.5. The n^0.5 would be the numbers of root_squarable <= n
 * (Notice: our index starts from 1, not 0, otherwise indexing would be an issue)
 * 
 * Time complexity: O(1)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jun 5, 2016 3:25:24 PM
 */
public class Bulb_Switcher_p319_sol1 {
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
}
