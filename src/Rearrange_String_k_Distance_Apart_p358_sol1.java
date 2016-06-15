import java.util.*;

/*
358. Rearrange String k Distance Apart

Given a non-empty string str and an integer k, rearrange the string such that the same characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

Example 1:
str = "aabbcc", k = 3

Result: "abcabc"

The same letters are at least distance 3 from each other.
Example 2:
str = "aaabc", k = 3 

Answer: ""

It is not possible to rearrange the string.
Example 3:
str = "aaadbbcc", k = 2

Answer: "abacabcd"

Another possible answer is: "abcabcda"

The same letters are at least distance 2 from each other.
*/

/**
 * Greedy + HashMap + Queue + PriorityQueue solution
 * 
 * Basically we want char with high frequency to appear as early as possible so that we can enough other chars
 * to fill the gap. To achieve that we use a PriorityQueue to sort the chars based on the frequency. We also
 * want to control the chars appear in the gap. To achieve that we use a queue to temporarily freeze chars we used/
 * We will pop a char from priorityQueue, append to result, then put it to queue. We will start release chars from queue
 * once we have added k chars to result. Therefore we use an inner loop that append k chars. 
 * This action help us make sue the head char in queue will be k steps away from its last appearance. 
 * Since we always put the char with higher occurrences ahead, our solution always give the best way to assign all chars
 * (after appending head chars, the second head in queue should also have k steps away with its first occurrence)
 * 
 * In case there is no more elements left in priorityQueue for us to use. We need to return "" as there is not enough
 * chars to rebuild string with k steps 
 * 
 * 
 * Time complexity: O(NlogN) as follows:
 * count frequency: O(N), build PriorityQueue: O(NlogN), rebuild string: O(NlogN)
 * Space complexity: O(NlogN)
 * 
 * Remark:
 * In case we get two chars that have same occurrences, then we need keep them in same order in appearance
 * ex: aabb, we want final string has abab not abba. So we will add this condition to comparator
 * 
 * @author hpPlayer
 * @date Jun 13, 2016 8:51:47 PM
 */
public class Rearrange_String_k_Distance_Apart_p358_sol1 {
	public static void main(String[] args){
		String str = "aabbcc";
		System.out.println(new Rearrange_String_k_Distance_Apart_p358_sol1().rearrangeString(str, 3) );
	}
    public String rearrangeString(String str, int k) {
        //Greedy + HashMap + Queue + PriorityQueue solution. Greedy: we want to put chars with high frequency 
        //as early as possible. HashMap: record chars and frequency. Queue: help use freeze chars and make sure
        //each char appears k steps after last appearance. PriorityQueue: help us sort chars with frequency
    	
        //boundary case
        if(k == 0) return str;
        
        //count table (hashMap) helps us record chars and frequency
        int[] count = new int[256];
        
        for(char c : str.toCharArray() ){
            count[c]++;
        }
        
        //priorityQueue sort chars based on frequency. Put chars with high frequency ahead
        PriorityQueue<Character> pq = new PriorityQueue<Character>(new Comparator<Character>(){
            public int compare(Character a, Character b){
            	if(count[a] == count[b]){
            		//keep same order for chars with same occurrences
            		return a - b;
            	}else{
            		return count[b] - count[a];
            	}
            }    
        });
        
        //push chars to pq
        for( int i = 0; i < 256; i++ ){
            if( count[i] > 0 ) pq.offer( (char) i );
        }
        
        //queue to temporarily store chars that we used before. We will release them once they have reached
        //k steps from last appearnce
        Queue<Character> freeze = new LinkedList<Character>(); 
        StringBuilder sb = new StringBuilder();
        int charsLeft = str.length();
        
        //build string.
        while(!pq.isEmpty()){
        	//inner loop will add k chars to target string, except for the last group
        	int len = Math.min(charsLeft, k);
        	
        	for(int i = 0; i < len; i++){
	            //if our pq is empty, then it indicates we could not rebuild the string.
	            //even if we may have chars in queue, but we cannot release them due to the limit of k
	            if( pq.isEmpty() ) return "";
	            
	            char c = pq.poll();    
	            sb.append(c);
	          //we add next candidate to pq if it still have remainings
	            if( --count[c] > 0) freeze.offer( c );
	            charsLeft--;
        	}
        	//after appending k chars, we can release chars from queue
        	while( !freeze.isEmpty() ) pq.offer(freeze.poll());
        }
        
        return sb.toString();
    }
}
