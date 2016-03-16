import java.util.*;

/**
 * PQ solution
 * 
 * The main code is same with sol1, but here we use minPQ a max heap to store the first half of elements and use maxPQ a min heap to store the 
 * second half of elements
 * 
 * Time complexity: for addNum() each new element will cost O(logN) to balance queues, for findMedian(), each new element will cost O(1)
 * to get the median
 * Space complexity: O(N) as we will store all inputs into heap
 * 
 * @author hpPlayer
 * @date Mar 15, 2016 4:31:19 PM
 */
public class Find_Median_from_Data_Stream_p295_sol2 {
    
    //minPQ stores first half
    PriorityQueue<Integer> minPQ = new PriorityQueue<Integer>();
    //maxPQ stores second half
    PriorityQueue<Integer> maxPQ = new PriorityQueue<Integer>();
    
    // Adds a number into the data structure.
    public void addNum(int num) {
        //to make minPQ behaves like maxPQ, we offer negative value of num
        minPQ.offer(-num);
        //to place num into correct position, we will update two pq for each input
        //After we insert input into minPQ, we will move a data from minPQ to maxPQ
        maxPQ.offer(-minPQ.poll());
        //to make two pq balanced, if maxPQ.size > minPQ then we will move a data from maxPQ back to minPQ
        if(maxPQ.size() > minPQ.size()) minPQ.offer(-maxPQ.poll());
    }

    // Returns the median of current data stream
    public double findMedian() {
        if(minPQ.size() == maxPQ.size()){
            return (maxPQ.peek()-minPQ.peek()) / 2.0;
        }else{
            return -minPQ.peek() * 1.0;
        }
    }
}
