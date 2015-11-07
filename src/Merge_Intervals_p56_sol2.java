import java.util.*;
/**
 * Merge Sort solution
 * 
 * We just use standard merge sort algorithm to split then merge the input lists. During the merge stage, we pop intervals based on start index
 * 
 * Remark:
 * In the merge2lists(), after one list is end, we cannot simply use addAll() to add another list, since we may need to merge the rest list with result list
 * as well
 * 
 * Time complexity: O(nlogn)
 * Space complexity: O(n) as we may finally add all intervals to result list
 * 
 * This solution is similar to solution The_Skyline_Problem_p218_sol2, we apply merge-sort algorithm to both solution
 * But differed from that solution, here we don't need to worry about the case we have same start value
 * In that solution, we have to worry about the same value cases, since we want to avoid unnecessary insertion of different edges at one axis
 * 
 * @author hpPlayer
 * @date Nov 7, 2015 12:55:07 AM
 */
public class Merge_Intervals_p56_sol2 {
    public List<Interval> merge(List<Interval> intervals) {
        //use standard merge sort algorithm to merge the intervals
        return mergeSort(0, intervals.size()-1, intervals);
    }
    
    public LinkedList<Interval> mergeSort(int start, int end, List<Interval> intervals){
        LinkedList<Interval> result = new LinkedList<Interval>();
        //boundary check
        if(start > end) return result;
        //look at a single interval
        if(start == end){
            result.add(intervals.get(start));
            return result;
        }
        
        //split to half, then merge together
        int mid = start + (end - start)/2;
        
        return merge2lists(mergeSort(start, mid, intervals), mergeSort(mid + 1, end, intervals));
    }
    
    public LinkedList<Interval> merge2lists(LinkedList<Interval> a, LinkedList<Interval> b){
        LinkedList<Interval> result = new LinkedList<Interval>();
        while(!a.isEmpty() || !b.isEmpty()){
            //we need use this loop to add all intervals, so we may cover all merge cases
            int start_a = a.isEmpty()? Integer.MAX_VALUE : a.peekFirst().start;
            int start_b = b.isEmpty()? Integer.MAX_VALUE : b.peekFirst().start;
            Interval temp = null;//the interval that will be inserted into the result list
            if(start_a < start_b){
                //get the smaller one
                temp = a.pollFirst();
            }else{
                //get the smaller one
                temp = b.pollFirst();
            }
            
            if(result.isEmpty() || result.peekLast().end < temp.start){
                //insert interval if result is empty or two intervals do not interfere
                result.add(temp);
            }else{
                //merge two lists
                result.peekLast().end = Math.max(result.peekLast().end, temp.end);
            }
        }
        
        return result;
    }
}
