import java.util.TreeMap;

/**
 * HashMap solution
 * 
 * In this solution, we use a hashMap to count how many rooms we need at certain timestamp.
 * Firstly we scan the array and update the start and end point in HashMap accordingly. 
 * For start point, we add 1, for end point we minus 1.
 * Then we scan the HashMap key set, and find how many rooms we need.
 * In the last step, we need to scan the timestamp with order, and find the cumulative max point, therefore we need timestamp to be sorted in the hashMap
 * 
 * fill the hashMap: O(NlogN)
 * scan the hashMap: O(n)
 * so the total time complexity should be O(N + NlogN)
 * 
 * @author hpPlayer
 * @date Feb 9, 2016 9:28:36 PM
 */
public class Meeting_Rooms_II_p253_sol2 {
    public int minMeetingRooms(Interval[] intervals) {
        //use a TreeMap so that we can sort the key based on timestamp
        TreeMap<Integer, Integer> hs = new TreeMap<Integer, Integer>();
        
        for(Interval curr : intervals){
            if(!hs.containsKey(curr.start)) hs.put(curr.start, 0);
            if(!hs.containsKey(curr.end)) hs.put(curr.end, 0);
            
            //update room need at current timestamp
            //need a room we + 1, finish a roon we -1
            hs.put(curr.start, hs.get(curr.start) +1);
            hs.put(curr.end, hs.get(curr.end)-1);
        }
        
        //finally we scan the HashMap to check how many rooms we need
        int result = 0;
        int curr = 0;
        
        for(Integer key : hs.keySet()){
            curr += hs.get(key);
            result = Math.max(result, curr);
        }
        
        return result;
    }
}
