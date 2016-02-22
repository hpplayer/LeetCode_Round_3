/*
275. H-Index II

Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?

Hint:

Expected runtime complexity is in O(log n) and the input is sorted.
*/

/**
 * Naive approach
 * 
 * This solution is similar to H_Index_p274_sol1, where we sort the array first and find the first len - i index that gives citations[i] >= len - i
 * i.e. the last node in the graph that above the 45 degree diagonal.
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * But since the input is sorted, actually we can use binary search to find the H-index which could improve the time complexity to O(logN)
 * This binary-search approach can be found in sol2
 * 
 * @author hpPlayer
 * @date Feb 21, 2016 2:00:08 PM
 */
public class H_Index_II_p275_sol1 {
    public int hIndex(int[] citations) {
        //naive approach, find the first len - i index that gives citations[i] >= len - i where citations[i] is the citation val
    	//while len - i is the num of papers aka the index in paper axis
        
        int len = citations.length;
        
        for(int i = 0; i < len; i++){
            if(citations[i] >=  len - i) return len - i;
        }
        
        //can't find H-index in given paper axis, therefore we need return 0
        return 0; 
    }
}
