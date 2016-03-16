import java.util.*;

/**
 * LinkedList solution
 * 
 * The basic idea is same with sol1, but here we use an extra list to record result
 * 
 * We will firstly skip all intervals that do not interfere with newInterval, the end time of those intervals < newInterval.start
 * Then we merge all interfered intervals with newInterval, the start time of those intervals <= newInterval.end
 * Finally we add remaining intervals into result, the start time of those intervals > newInterval.end
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Mar 15, 2016 11:32:28 AM
 */

public class Insert_Interval_p57_sol2 {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<Interval>();
        // write your code here
        
        int index = 0;
        
        //firstly add all intervals that do not interfere with newInterval, those intervals have end time < newInterval.start
        while(index < intervals.size() && intervals.get(index).end < newInterval.start){
            result.add(intervals.get(index++));
        } 
        
        //secondly add all intervals that interfered with newInterval, those intervals have start time <= newInterval.end
        while(index < intervals.size() && intervals.get(index).start <= newInterval.end){
            newInterval.start = Math.min(intervals.get(index).start, newInterval.start);
            newInterval.end = Math.max(intervals.get(index).end, newInterval.end);
            index++;
        }
        
        result.add(newInterval);
        
        //thirdly add remaining intervals, those intervals have start time > newInterval.start
        while(index < intervals.size()){
            result.add(intervals.get(index++));
        }       
        
        return result;
    }
}
