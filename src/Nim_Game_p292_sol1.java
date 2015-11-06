/*
Nim Game

You are playing the following Nim Game with your friend: There is a heap of stones on the table, each time one of you take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner. You will take the first turn to remove the stones.

Both of you are very clever and have optimal strategies for the game. Write a function to determine whether you can win the game given the number of stones in the heap.

For example, if there are 4 stones in the heap, then you will never win the game: no matter 1, 2, or 3 stones you remove, the last stone will always be removed by your friend.

Hint:

If there are 5 stones in the heap, could you figure out a way to remove the stones such that you will always be the winner?
*/

/**
 * Math problem
 * 
 * 4 is death number, who got 4 first will be doomed 
 * so if we got 5,6,7, we can have a way to make opponent to get the death number
 * 8 is death number again
 * 
 * So the conclusion is the multiples of 4 will lose for sure. For other inputs, we will win for sure
 * 
 * Time complexity: O(1)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Nov 6, 2015 3:51:00 PM
 */
public class Nim_Game_p292_sol1 {
    public boolean canWinNim(int n) {
    	return n%4 != 0;
    }
}
