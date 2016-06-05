/*
299. Bulls and Cows

You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is.
Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both
digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows").
Your friend will use successive guesses and hints to eventually derive the secret number.

For example:

Secret number:  "1807"
Friend's guess: "7810"
Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to
indicate the cows. In the above example, your function should return "1A3B".

Please note that both secret number and friend's guess may contain duplicate digits, for example:

Secret number:  "1123"
Friend's guess: "0111"
In this case, the 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow, and your function should return "1A1B".
You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
*/

/**
 * hashMap solution
 * 
 * The tricky part is to cover "B" cases. There would be two kinds of "B" cases:
 * 1) we have target digit in prev part of secret
 * ex: secret: 1 1 2
 *     guess:  3 1 1
 * 2) we have target digit in prev part of guess
 * ex: secret: 2 1 1
 *     guess:  1 1 3
 * In this solution we use a trick to cover these two cases. The trick is using a single count table, and increase count if
 * the char is in secret and decrease count if the char is in guess    
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jun 5, 2016 12:17:26 AM
 */
public class Bulls_and_Cows_p299_sol1 {
    public String getHint(String secret, String guess) {
        //HashMap(count table) solution
        
        //since input only contains digits, we can create an array with len 10
        int[] count = new int[10];
        
        //counter for hint A
        int countA = 0;
        //counter for hint B
        int countB = 0;
        
        for(int i = 0; i < secret.length(); i++){
            //Although we converted char to index, this change wouldn't affect comparing them
            int a = secret.charAt(i) - '0';
            int b = guess.charAt(i) - '0';
            
            if(a == b){
                //match index and value, update countA
                countA++;
            }else{
                
                //if count[a] < 0 it means we have char a in prev guess, both prev and guess have this 
                //char but in different position, so we increase counterB
                if(count[a] < 0) countB++;
                //if count[b] > 0 it means we have char a in prev secret, both prev and guess have this 
                //char but in different position, so we increase counterB                
                if(count[b] > 0) countB++;
                
                //update table
                count[a]++;
                count[b]--;
            }
        }
        
        return countA + "A" + countB + "B";
    }
}
