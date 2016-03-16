import java.util.*;
/**
 * Heap problem
 * 
 * we use two heaps to contain the first half and second half of input numbers. To be more specific, we will use a maxHeap to contain
 * the first half of input numbers and use a minHeap to contain the second half of input numbers so median number will always on top
 * of two heaps. For each incoming number, we will firstly insert it into minQ, then poll an element from minQ to maxQ, so the order
 * in two queues will be maintained. If we just do this, then all elements will remain in maxQ, so we will put elements back to minQ
 * if we found maxQ.size() > minQ.size(). This means elements in maxQ will always less than or equal to elements in minQ. If we need
 * to return median from odd len of inputs, we just need to return the top element in minQ
 * 
 * Time complexity: for addNum() each new element will cost O(logN) to balance queues, for findMedian(), each new element will cost O(1)
 * to get the median
 * Space complexity: O(N) as we will store all inputs into heap
 * 
 * Sol2 lists a solution that use minQ to store the first half if elements and use maxQ to store the second half of elements
 * Two solutions are nearly identical, but with a minor difference in preprocessing the input data before inserting into pq
 * @author hpPlayer
 * @date Nov 4, 2015 7:08:39 PM
 */

public class Find_Median_from_Data_Stream_p295_sol1 {
    
    //minQ uses min heap to contain second half of elements
    PriorityQueue<Integer> minQ = new PriorityQueue<Integer>();
    //maxQ uses max heap to contain first half of elements
    PriorityQueue<Integer> maxQ = new PriorityQueue<Integer>();
    
    
    // Adds a number into the data structure.
    public void addNum(int num) {
        //put num in minQ, then poll an element from maxQ
        //so we can balance two heaps 
        minQ.offer(num);
        //to be convenient, we still use minHeap, but we reverse the value of input
        //so we can have the same effect of maxHeap
        maxQ.offer(-minQ.poll());
        
        //if we just do operations above, all elements will remain in maxQ
        //so we need to put top element in maxQ back to minQ to maintain size is balanced
        if(minQ.size() < maxQ.size()) minQ.offer(-maxQ.poll());
    }

    // Returns the median of current data stream
    public double findMedian() {
        if(minQ.size() == maxQ.size()){
            //if two size equal, then we have even number of inputs
            //so take top elements from both queue and return average
            return (minQ.peek() - maxQ.peek()) / 2.0;
        }else{
            //odd inputs, since we not allow maxQ.size() > minQ.size()
            //the extra element will be on the top of minQ
            return minQ.peek() * 1.0;
        }
    }
}

//Your MedianFinder object will be instantiated and called as such:
//MedianFinder mf = new MedianFinder();
//mf.addNum(1);
//mf.findMedian();