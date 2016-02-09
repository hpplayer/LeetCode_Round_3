import java.util.*;
/*
Meeting Rooms

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
determine if a person could attend all meetings.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return false.
*/

/**
 * Sort problem
 * 
 * We just use the built-in sort feature to sort the intervals based on start time, then check if next meeting start after(inclusive) the end time of 
 * last meeting
 * 
 * Time complexity: O(NlogN + N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Feb 9, 2016 7:13:20 PM
 */
public class Meeting_Rooms_p252_sol1 {
    public boolean canAttendMeetings(Interval[] intervals) {
    	
    	//boundary check
    	if(intervals.length <= 1) return true;
    	
        Arrays.sort(intervals, new Comparator<Interval>(){
        	public int compare(Interval a, Interval b){
                return a.start - b.start;
        	}
        });
        
        int end = intervals[0].end;
        for(int i = 1; i < intervals.length; i++){
        	int start = intervals[i].start;
        	if(start < end) return false;
        	end = intervals[i].end;
        }
        
        return true;
    }
}
