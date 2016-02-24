import java.util.*;

/*
293. Flip Game

You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to compute all possible states of the string after one valid move.

For example, given s = "++++", after one move, it may become one of the following states:

[
  "--++",
  "+--+",
  "++--"
]
If there is no valid move, return an empty list [].
*/

/**
 * Naive solution
 * 
 * Just scan the input and look for two consecutive "++", then flip it
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Feb 23, 2016 8:58:41 PM
 */
public class Flip_Game_p293_sol1 {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> result = new ArrayList<String>();
        
        for(int i = 0; i < s.length() - 1; i++){
            if(s.charAt(i) == '+' && s.charAt(i+1) == '+'){
                result.add( s.substring(0, i) + "--" + s.substring(i+2));   
            }
        }
        
        return result;
    }
}
