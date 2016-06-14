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
 * We will pop a char from priorityQueue, append to result, then put it to queue. After the queue size >= k, should
 * we start to poll chars from queue. This action help us make sue the head char in queue will be k steps away
 * from its last appearance. 
 * 
 * In case we put all chars in the queue, and there is no more elements left in priorityQueue for us to use. We
 * need to return "" as there is not enough chars to rebuild string with k steps 
 * 
 * Time complexity: O(NlogN) as follows:
 * count frequency: O(N), build PriorityQueue: O(NlogN), rebuild string: O(NlogN)
 * Space complexity: O(NlogN)
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
        //each char appears k steps after last appearance. PriorityQueue: help us sort chars with frequecy
        
        //count table (hashMap) helps us record chars and frequency
        int[] count = new int[256];
        
        for(char c : str.toCharArray() ){
            count[c]++;
        }
        
        //priorityQueue sort chars based on frequency. Put chars with high frequency ahead
        PriorityQueue<Character> pq = new PriorityQueue<Character>(new Comparator<Character>(){
            public int compare(Character a, Character b){
                return count[b] - count[a];
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
        
        //build string.
        for(int i = 0; i < str.length(); i++ ){
            //if our pq is empty, then it indicates we could not rebuild the string.
            //even if we may have chars in queue, but we cannot release them due to the limit of k
            if( pq.isEmpty() ) return "";
            
            char c = pq.poll();    
            sb.append(c);
            count[c]--;
            //we add next candidate to pq
            //Notice now its remaining value may be 0, but its ok, we will check it value in the nested if()
            freeze.offer( c );
            
            //we need use > here in case k is = 0
            if( freeze.size() >= k ){
                //size >= k, at least we can release head char in queue
                if(count[freeze.peek()] > 0) pq.offer( freeze.poll() );
            }
            
        }
        
        return sb.toString();
        
    }
}
