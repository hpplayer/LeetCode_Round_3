import java.util.PriorityQueue;

/**
 * Heap solution
 * 
 * We use a min heap to store all generated ugly numbers. Then we pop top value from heap, and generate three following ugly numbers and insert into
 * heap. We will keep doing this until we have n - 1 loops
 * 
 * 
 * Time complexity: O(NlogK) where N means our loop will go N times, logK means each loop, we will offer k elements into the heap
 * now k = 3, so we still get O(N) time complexity
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Feb 25, 2016 9:59:52 AM
 */
public class Ugly_Number_II_p264_sol2 {
    public int nthUglyNumber(int n) {
        //heap solution. We use a min heap to store produced ugly number. Each time we will pop the smallest ugly number, then create
        //three following ugly numbers by multiplying 2, 3 and 5 respectively and insert them into heap.
        
        //to prevent overflow, we use Long type
        PriorityQueue<Long> pq = new PriorityQueue<Long>();
        //initialization
        pq.offer(1l);
        
        for(int i = 1; i < n; i++){
            long min = pq.poll();
            
            //we may have several duplciates of min in pq as well. To avoid generating duplicate ugly numbers, we will pop all top value
            //that has same value with min
            //use equals() to compare values since Long is object
            while(!pq.isEmpty() && pq.peek().equals(min)) pq.poll();
            
            //generate three following ugly numbers from this min value
            pq.offer(min * 2);
            pq.offer(min * 3);
            pq.offer(min * 5);
        }
        
        return pq.poll().intValue();
    }
}
