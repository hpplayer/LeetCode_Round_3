import java.util.*;

/*
350. Intersection of Two Arrays II

Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

Note:
Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
Follow up:
What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to num2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements
into the memory at once?
*/		
		
/**
 * Two pointer solution
 * 
 * We firstly sort two inputs, then move two pointers on each input smartly.
 * We will end loop as long as any of the input list reaches the end. Therefore size of nums1 and nums2
 * does not matter here. 
 * 
 * Time complexity:
 * O(mlgm) +O(nlgn) + O(max(m, n))
 * Sorting: O(mlgm) +O(nlgn)
 * moving pointer: O(max(m, n))
 * 
 * Space complexity: O(max(m, n)), i.e. no extra space is needed except for the result container
 * 
 * Follow up:
 * 1) If given array is sorted, then we just need to move pointer smartly, our program will run faster
 * 2) size of inputs does not affect our program
 * 3) If that is the case, then we need to load partial part of nums2, and load remaining part of nums2, once pointer2
 * reaches the end of partial part in memory 
 * 
 * Remark:
 * In two pointer solution, we scan two input arrays linearly. However, we can use binary search to help us find the 
 * range, which may improve the speed
 * 
 * Sol1 is the solution that uses intuitive two pointer solution
 * Sol2 is the solution that uses similar algorithm as sol1 but using binary_search to move pointers
 * Sol3 is the HashMap solution that needs extra space for HashMap
 * 
 * @author hpPlayer
 * @date May 22, 2016 10:07:18 PM
 */
public class Intersection_of_Two_Arrays_II_p350_sol1 {
    public int[] intersect(int[] nums1, int[] nums2) {
        //two pointer solution. Sort inputs, then move two pointers in inputs smartly
        
        //sort two inputs
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        int index1 = 0;
        int index2 = 0;
        
        List<Integer> list = new ArrayList<Integer>();
        
        //Move two pointers smartly
        while(index1 < nums1.length && index2 < nums2.length){
            if(nums1[index1] == nums2[index2]){
                list.add(nums1[index1]);
                index1++;
                index2++;
            }else if(nums1[index1] < nums2[index2]){
                index1++;
            }else{
                index2++;
            }
        }
        
        int[] result = new int[list.size()];
        
        for(int i = 0; i < result.length; i++){
            result[i] = list.get(i);
        }
        
        return result;
    }
}
