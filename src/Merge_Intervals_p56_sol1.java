import java.util.*;
/*
Merge Intervals

Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].
*/	

/**
 * Sort solution
 * 
 * Use built-in comparator to sort the input list based on start value
 * then we will build result list by scanning the sorted input lists. We will merge two intervals if we found last interval.end <= curr interval.start
 * In such case, we just update last interval.end accordingly. In other cases, we will add a new interval
 * 
 * Time complexity: sorting O(nlogn), building result, O(n), so the running time should still be O(nlogn)
 * Space complexity: O(n) as we may build a result with O(n) length
 * 
 * Sol1 is using built-in comparator to sort the input list, then merge the list
 * Sol2 use standard merge-sort to do the merge and sort at the same time, so it is much faster
 * @author hpPlayer
 * @date Nov 7, 2015 12:32:58 AM
 */
public class Merge_Intervals_p56_sol1 {
    public List<Interval> merge(List<Interval> intervals) {
        //use built-in comparator to sort input intervals based on start value
        
        List<Interval> result = new ArrayList<Interval>();
        
        //boundary check
        if(intervals.isEmpty()) return result;
        
        intervals.sort(new Comparator<Interval>(){
           public int compare(Interval a, Interval b){
               //sort intervals with non-descending order
               return a.start - b.start;
           } 
        });
        
        result.add(intervals.get(0));
        
        for(int i = 1; i < intervals.size(); i++){
            //get last interval in list
            Interval last = result.get(result.size()-1);
            Interval curr = intervals.get(i);
            if(last.end < curr.start){
                //we need add a new interval
                result.add(curr);
            }else{
                //we can merge two intervals, update end of last
                last.end = Math.max(last.end, curr.end);
            }
        }
        
        return result;
    }
}
