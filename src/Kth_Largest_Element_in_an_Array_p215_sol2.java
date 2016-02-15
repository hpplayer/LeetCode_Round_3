import java.util.PriorityQueue;

/**
 * PriorityQueue solution
 * 
 * We maintain a priorityQueue with size k. If size of heap < k or num > pq.peek(), then we need add curr num into heap
 * If we found the size >= k, then we remove top num in heap, which is kth largest number we visited so far, then add the new num
 * 
 * In worst case, we need add all nums into the heap, including the adding/deleting cost, it is O(NLogK)
 * Time complexity: O(k)
 * 
 * @author hpPlayer
 * @date Feb 13, 2016 1:13:29 AM
 */
public class Kth_Largest_Element_in_an_Array_p215_sol2 {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        
        for(int num : nums){
            if(pq.size() < k || num >= pq.peek()){
                if(pq.size() >= k){
                    //if full remove top, add new one
                    pq.poll();
                }
                pq.offer(num);
            }
        }
        
        return pq.peek();
    }
}
