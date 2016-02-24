import java.util.*;
/*
294. Flip Game II

You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -,
you and your friend take turns to flip two consecutive "++" into "--".
The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".

Follow up:
Derive your algorithm's runtime complexity.
*/

/**
 * Backtracking solution
 * 
 * This problem is a little bit tricky because it is easy to get confused in the logic
 * The program we used to check if we have a next valid move is same with Flip_Game_p293_sol1.
 * We need to use backtracking to repeat the steps until one side cannot move forward. If this side is opponent, then player 1 wins.
 * So the logic is that we need to flip the return result from next layer. If next layer return true, then current layer should return false
 * If next layer return false, then we need to return true.
 * 
 * Our goal is to let p1 win, so we should found a way that p2 return false. To make p2 return false, we should found a way to make p1 true.
 * 
 * It is better to use a HashMap as memorization to speed up the program, the speed will be greatly improved
 * 
 * For the time complexity, it should be exponential but arguable: 
 * Say input is ++++++
 * we will flip two "++" from left to right, then we would have:
 * T(N) = T(--++++) +  T(+--+++) + T(++--++) + T(+++--+) + T(++++--)
 * T(N) = T(N-2) +  T(N-3) + (T(2) * T(N-4)) + (T(3) * T(N-3)) + T(N-2) = 2 * sum(T[i]) where i is (2, 3)
 * and 2^N will fit
 * 
 * Remark:
 * This problem can also solved by DP solution with Game-theory concepts. But I think it is too hard for an interview, so I did not put it here
 * 
 * @author hpPlayer
 * @date Feb 23, 2016 11:38:01 PM
 */
public class Flip_Game_II_p294_sol1 {
    public boolean canWin(String s) {
        //backtracking solution with memorization
        return DFS(s, new HashMap<String, Boolean>());
    }
    
    public boolean DFS(String s, HashMap<String, Boolean> hs){
        if(hs.containsKey(s)) return hs.get(s);
        
        //we always compare char at i and i + 1, therefore i would not be last one as we dont have char after it
        for(int i = 0; i + 1 < s.length(); i++){
            if(s.charAt(i) == '+' && s.charAt(i+1) == '+'){
                //found a place to flip
                if(!DFS(s.substring(0, i) + "--" + s.substring(i+2), hs)){
                    //we only care about the case that opponent can't move forward
                    //if we found such result, then current move will make current player win
                    hs.put(s, true);
                    return true;
                }
            }
        }
        
        //all steps tried, and still can't find a move that stop opponent move forward, therefore return false
        hs.put(s, false);
        return false;
    }
}
