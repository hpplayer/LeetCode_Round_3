import java.util.*;
/*
352. Data Stream as Disjoint Intervals

Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a list of disjoint intervals.

For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:

[1, 1]
[1, 1], [3, 3]
[1, 1], [3, 3], [7, 7]
[1, 3], [7, 7]
[1, 3], [6, 7]
Follow up:
What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?
*/

/**
 * TreeMap solution
 * 
 * Since this problem is new, I haven't seen a better solution than the TreeMap solution. So I only list TreeMap
 * solution here.
 * 
 * We use a TreeMap to help us sort the inputs, and query Interval in O(1) time
 * 
 * here I list several useful methods in TreeMap
 * 
 * 1)The lowerKey(K key) method is used to return the greatest key strictly less than the given key, or null if there is no such key.
 * 2) ThehigherKey(K key) method is used to return the least key strictly greater than the given key, or null if there is no such key.
 * 3) The values() method is used to return a Collection view of the values contained in this map.
 * The collection's iterator returns the values in ascending order of the corresponding keys.
 * 
 * Assume low is the starting value of lower interval, high is the starting value of higher interval
 * 
 * There are only three cases that we can merge curr input into an existing interval
 * 1) low.end + 1 = curr and curr + 1 = high
 * 2) low.end > curr > low.start or curr = low.end + 1
 * 3) curr + 1 = high
 * 
 * Time complexity: O(N) for getIntervals(), O(logN) for addNum()
 * Space complexity: O(N) in the worst case, we have to store all values in TreeMap
 * 
 * @author hpPlayer
 * @date Jun 9, 2016 10:34:54 PM
 */
public class Data_Stream_as_Disjoint_Intervals_p352_sol1 {
    //TreeMap solution. We use a treeMap to help us record prev inputs and manage Intervals
    
    //key is starting value of the interval
    //val is the interval
    TreeMap<Integer, Interval> tree;
    
    /** Initialize your data structure here. */
    public Data_Stream_as_Disjoint_Intervals_p352_sol1() {
        tree = new TreeMap<Integer, Interval>();
    }
    
    public void addNum(int val) {
        //we have early pruning here, in case we already have val in TreeMap, we will not check it again
        
        if( tree.containsKey(val) ) return;
        
        Integer low = tree.lowerKey(val);
        Integer high = tree.higherKey(val);
        
        if(low != null && high != null &&  tree.get(low).end + 1 == val && val + 1 == high ){
            //case 1, we can connect two intervals
            tree.get(low).end = tree.get(high).end;
            tree.remove(high);
        }else if( low != null && tree.get(low).end + 1 >= val ){
            //case 2, input is in smaller interval
            tree.get(low).end = Math.max( tree.get(low).end, val );
        }else if( high != null && val + 1 == high) {
            //case 3, input + 1 = high, input is in larger interval 
            tree.put( val,  new Interval( val, tree.get(high).end )  );
            tree.remove(high);
        }else{
            //otherwise, we have to create a new interval for curr input
            tree.put( val, new Interval(val, val)  );
        }
        
    }
    
    public List<Interval> getIntervals() {
        return new ArrayList<Interval>(tree.values());
    }
}
/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * List<Interval> param_2 = obj.getIntervals();
 */