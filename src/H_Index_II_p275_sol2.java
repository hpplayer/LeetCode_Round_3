/**
 * Binary ssearch version
 * 
 * compare len - mid index with citations[mid] value
 * if too large, we need get smaller index, then move end to mid - 1
 * if too small, we need get larger index, then move start to mid + 1
 * else return result
 * 
 * If we can't find index in input, then we return len - start, as start in the last will point to the last index that gives citations[start] > len - start
 * 
 * In this solution, I provide iterative and recursive solutions
 * 
 * Time complexity: O(LogN)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Feb 21, 2016 2:23:42 PM
 */
public class H_Index_II_p275_sol2 {
   public int hIndex(int[] citations) {
        //binary search approach, we will try to find the index that gives citations[i] == len - i;
        //if we still can't find such index in array, then we will return len - start as "start" will point to the last index that gives
	   //citations[i] >= len - i
        
        int len = citations.length;
        int start = 0, end = citations.length - 1;
        
        while(start <= end){
            int mid = start + (end - start)/2;
            
            if(citations[mid] == len - mid){
                //found the node on diagonal line, return directly
                return len - mid;
            }else if (citations[mid] > len - mid){
                //index too large, get smaller index
                end = mid - 1;
            }else{
                //index too small, get larger index
                start = mid + 1;
            }
        }
        
        //If we can't find citations[mid] == len - mid in the binary search
        //then we will return len - start. If H-index = 0, then start should point to end + 1 i.e. len
        //if H-index is a node not in diagonal, then start should point to the last index that gives citations[mid] > len - mid
        return len - start;
    }
	   
	   
	    public int hIndex2(int[] citations) {
	        //recursive version
	    	return binarySearch(0, citations.length - 1, citations);
	    } 
	    
	    public int binarySearch(int start, int end, int[] citations){
	        int len = citations.length;
	        //reach end, return len - start
	        if(start > end) return len - start;
	        
	        int mid = start + (end - start)/2;
	        
	        //found match, return index
	        if(citations[mid] == len - mid) return len - mid;
	        //too large, go smaller index
	        if(citations[mid] > len - mid) return binarySearch(start, mid - 1, citations);
	        
	        //too small, go larger index
	        return binarySearch(mid + 1, end, citations);
	    }
}
