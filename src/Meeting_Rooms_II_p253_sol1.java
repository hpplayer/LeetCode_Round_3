import java.util.*;
/*
253. Meeting Rooms II

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2.
*/

/**
 * Priority Queue (heap) solution
 * 
 * In this solution, we firstly sort the input array based on the start time, so that in later program we don't need to worry about the start order
 * Then we use a heap to sort the intervals based on the end time. We will put the interval whose end time is the earliest on the top, so that we can
 * update the room availability as soon as a meeting end. If incoming interval has start time later than this end time, then we need open another room
 * otherwise we can use the same room and update the interval in the heap accordingly. Finally we just need to count the num of rooms in the heap to
 * find how many rooms we need
 * 
 * Sort the array:O(NlogN)
 * use heap to update intervals: O(NlogN) so the time should be O(NlogN)
 * 
 * Space complexity: O(N)
 * 
 * Sol2 uses a hashMap solution
 * 
 * @author hpPlayer
 * @date Feb 9, 2016 9:17:58 PM
 */


public class Meeting_Rooms_II_p253_sol1 {
	   public int minMeetingRooms(Interval[] intervals) {
	    	if(intervals.length <= 1) return intervals.length;
	    	
	    	Arrays.sort(intervals, new Comparator<Interval>(){
	    		public int compare(Interval a, Interval b){
	    			return a.start - b.start;
	    		}
	    	});
	    	
	        PriorityQueue<Interval> pq = new PriorityQueue<Interval>(new Comparator<Interval>(){
	        	public int compare(Interval a, Interval b){
	        			return a.end - b.end;
	        	}
	        });
	        
	        pq.offer(intervals[0]);
	        
	        for(int i = 1; i <intervals.length; i++){
	            /* Mark off, since now wo have sorted the array based on start time, we would not have case the next interval has end time
	            //less then current start time
	        	if(intervals[i].end <= pq.peek().start){
	        		Interval temp = pq.poll();
	        		pq.offer(new Interval(intervals[i].start, temp.end));
	        	}else 
	        	*/
	        	if(pq.peek().end > intervals[i].start){
	        		pq.offer(intervals[i]);
	        	}else{
	        		Interval temp = pq.poll();
	        		pq.offer(new Interval(Math.min(temp.start, intervals[i].start), Math.max(temp.end, intervals[i].end)));
	        	}
	        }
	        
	        return pq.size(); 
	    }
}
