import java.util.*;

/*
346. Moving Average from Data Stream

Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

For example,
MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3
*/

/**
 * Queue solution
 * 
 * We maintain a queue with size k and use a sum to record the sum of curr window
 * Once an input make queue size > k, we will pop the head (oldest) element from queue and remove it from
 * sum, then get the average
 * 
 * Time complexity: O(N)
 * Size complexity: O(k)
 * 
 * @author hpPlayer
 * @date Jun 7, 2016 11:17:36 PM
 */
public class Moving_Average_from_Data_Stream_p346_sol1 {
    //queue solution, maintain a queue of size k
    
    Queue<Integer> que;
    int k, sum;
    
    /** Initialize your data structure here. */
    public Moving_Average_from_Data_Stream_p346_sol1(int size) {
        k = size;
        que = new LinkedList<Integer>();
    }
    
    public double next(int val) {
        que.offer(val);
        sum += val;
        if(que.size() > k){
            sum -= que.poll();
        }
        
        return (sum * 1.0) / que.size();
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */