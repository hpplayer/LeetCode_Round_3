import java.util.*;

/*
Insert Interval

Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
*/

/**
 * LinkedList + pointer problem
 * 
 * We use a pointer to scan the input lists. We will skip all intervals before newInterval, then merge all interfered intervals to the newInterval.
 * Then we just insert newInterval into the input lists
 * 
 * Time complexity: O(n)
 * Space complexity: O(1)
 * 
 * Remark:
 * since we will remove interfered intervals after merged to newInterval, we need to use interval.size() to get the real-time size of list!!!!!!!!!!!
 * 
 * @author hpPlayer
 * @date Nov 7, 2015 2:18:33 AM
 */
public class Insert_Interval_p57_sol1 {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        //pointer in input list
        int index = 0;
        int size = intervals.size();
        //skip heading intervals
        while(index < size && intervals.get(index).end < newInterval.start) index ++;
        
        //merge all interfered intervals
        while(index < intervals.size() && intervals.get(index).start <= newInterval.end){
            //interfered interval may have smaller start value
            newInterval.start = Math.min(newInterval.start, intervals.get(index).start);
            //interfered interval may have larger end value
            newInterval.end = Math.max(newInterval.end, intervals.get(index).end);
            //remove interfered interval
            intervals.remove(index);
        }
        
        //we have merged all interfered intervals, now we just insert newInterval into proper position
        intervals.add(index, newInterval);
        
        return intervals;
    }
}
