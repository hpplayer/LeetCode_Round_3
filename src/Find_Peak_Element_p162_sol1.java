/*
162. Find Peak Element

A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ¡Ù num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -¡Þ.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

click to show spoilers.

Note:
Your solution should be in logarithmic complexity.
*/

/**
 * Binary search solution
 * 
 * The tricky part is to realize that binary-search can be widely used as long as we have a fix rule to guide the search
 * In this problem we will guide binary search based on the comparison value between nums[mid] vs nums[mid + 1]
 * 
 * So we use two pointers to do binary search in the array. In each search, we will compare nums[i] with nums[n+1], then move pointer smartly 
 * based on the comparison result. We will return result when there is only element left in our scope, and it must be the peak
 * 
 * Time complexity: O(logN)
 * Space complexity: O(1)
 * 
 * Sol2 is my original AC three-pass solution, the time complexity is O(N)
 * 
 * @author hpPlayer
 * @date Feb 24, 2016 2:35:53 PM
 */
public class Find_Peak_Element_p162_sol1 {
	public static void main(String[] args){
		int[] nums = {1, 5, 4, 2, 9, 3};
		System.out.println(findPeakElement(nums));
	}
	
    public static int findPeakElement(int[] nums) {
        //use binary search to find the peak. Because we always pick the mid element, we will finally get the one of the peaks
        //The peak we get may not be tallest peak, but it can still be our solution
    	//Ex: {1, 5, 4, 2, 9, 3}, we will return index 1 which is 5, though it is not as tall as peak 9, but it is still larger than its neighbors
    	//so it is a valid peak
        
        int start = 0, end = nums.length - 1;
        
        //we just need to find the peak element, it is guaranteed to be in our array
        //so as long as we get the last element left, we can return it as result
        while(start < end){
            int mid = start + (end - start)/2;
            
            //why we compare mid with mid + 1? thats because mid will always be mid or mid left element
            //like [1, 2], mid will be index 0, therefore we need to compare mid with mid + 1
            if(nums[mid] > nums[mid + 1]){
                //mid may be solution, so keep mid
                //we are in down slope, so we need go left
                end = mid;
            }else if(nums[mid] < nums[mid + 1]){
               //1) we wouldn't have nums[mid] = nums[mid+1], as states in problem num[i] ¡Ù num[i+1]
               //2) now mid < mid + 1, then mid will not be solution, so skip it
               //we are in up slope, so we need go right
               start = mid + 1;
            }
        }
        
        //since we exit the loop when start == end, therefore we can either return start or end
        return start;
    }
}
