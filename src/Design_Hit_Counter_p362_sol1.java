import java.util.*;

/*
362. Design Hit Counter

Design a hit counter which counts the number of hits received in the past 5 minutes.

Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.

It is possible that several hits arrive roughly at the same time.

Example:
HitCounter counter = new HitCounter();

// hit at timestamp 1.
counter.hit(1);

// hit at timestamp 2.
counter.hit(2);

// hit at timestamp 3.
counter.hit(3);

// get hits at timestamp 4, should return 3.
counter.getHits(4);

// hit at timestamp 300.
counter.hit(300);

// get hits at timestamp 300, should return 4.
counter.getHits(300);

// get hits at timestamp 301, should return 3.
counter.getHits(301); 
Follow up:
What if the number of hits per second could be very large? Does your design scale?
*/
/**
 * Queue solution
 * 
 * We maintain a queue with size < 300. We store timestamps in the queue. We will make sure the elements in the queue
 * are within 5 min range with incoming timestamp
 *  
 * Therefore the num of hits is just the size of queue
 * 
 * Time complexity: O(1) for hits() and O(1) for getHits() since the que size will not be larger than 300.
 * Time complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jun 21, 2016 8:35:01 PM
 */
public class Design_Hit_Counter_p362_sol1 {
    Queue<Integer> que;
    
    /** Initialize your data structure here. */
    public Design_Hit_Counter_p362_sol1() {
        que = new LinkedList<Integer>();    
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        
        //pop all elements that not within 300 limit
        while(!que.isEmpty() && que.peek() <= timestamp - 300){
            que.poll();
        }
        
        //add curr timestamp
        que.offer( timestamp );
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        
        //since the input timestamp can be an arbitrary value, we still need check queue here
        while(!que.isEmpty() && que.peek() <= timestamp - 300){
            que.poll();
        }
        
        //que size is the num of hits
        return que.size();
    }
}
/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */