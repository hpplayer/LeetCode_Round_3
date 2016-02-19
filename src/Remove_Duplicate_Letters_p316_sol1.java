import java.util.*;
/*
316. Remove Duplicate Letters

Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once.
You must make sure your result is the smallest in lexicographical order among all possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"
*/

/**
 * Stack solution
 * 
 * The key of this problem is that we need to make sure our result is the smallest in lexicographical order
 * So we need define a rule to keep that. For building a non-duplicate string, we can use a count table to achieve that.
 * For building the smallest string, we have to use a stack to keep the order. If new char is smaller than top char in stack, we will
 * keep pop chars from stack until new top char is <= new char or we don't have duplicate top char in remaining string
 * 
 * Time complexity: each char will be visited at most twice (insertion and removal from deque), so the time complexity should be O(n)
 * Space complexity: O(1), as our stack will be at most O(26) long
 * 
 * @author hpPlayer
 * @date Feb 18, 2016 11:53:09 AM
 */
public class Remove_Duplicate_Letters_p316_sol1 {
	public static void main(String[] args){
		String s = "baa";
		System.out.println(removeDuplicateLetters(s));
	}
    public static String removeDuplicateLetters(String s) {
        //stack solution, we use a stack to hold the characters such that we put small chars on the bottom of stack and large chars on top
        //if new incoming char is larger than the top character, we will keep pop chars from stack until the next top char is smaller than
        //incoming char or we don't have any duplicate top char in the remaining string
        
        //we wanna push chars to the end while build result string from front, so deque would be a perfect data structure
        Deque<Character> deq = new LinkedList<Character>();
        
        //count table records the appearances of each char
        int[] count = new int[256];
        //inDeq table records whether we have a char in the deq already, so we don't nee to scan teh deq to find the answer
        boolean[] inDeq = new boolean[256];
        
        //scan the string first and update count[]
        for(int i = 0; i < s.length(); i++) count[s.charAt(i)]++;
        
        //we add a sentinel into the deq, so we can apply program to all cases
        deq.offerLast('\0');
        
        //the scan the string and add chars to deq based on the rule we defined above
        for(int i = 0; i < s.length(); i++){
            char c= s.charAt(i);
            count[c] --;
            
            //if we already have char c in deq that means at least we have some chars > c that are following char c in the deq now
            //so we will not add c here
            //In other words, we wanna keep small chars as left as possible
            if(inDeq[c]) continue;
            
            //otherwise we will add c to the deq and pop chars if possible
            while(c < deq.peekLast() && count[deq.peekLast()] > 0) inDeq[deq.pollLast()] = false;
            
            deq.offerLast(c);
            inDeq[c] = true;
        }
        
        StringBuilder sb = new StringBuilder();
        //remove sentinel
        deq.pollFirst();
        //start build string
        while(!deq.isEmpty()) sb.append(deq.pollFirst());
        
        return sb.toString();
    }
}
