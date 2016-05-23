import java.util.*;

/*
349. Intersection of Two Arrays

Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

Note:
Each element in the result must be unique.
The result can be in any order.
*/

/**
 * HashSet solution
 * 
 * Using one hashSet to include all elements from one array. Then scan another array and find intersection
 * based on this hashSet. 
 * 
 * Using second hashSet to store elements in intersection, then convert to array
 * 
 * Time complexity: O(m+n)
 * Space complexity: O(max(m, n))
 * 
 * Remark:
 * This problem is new and I have not seen better solution.
 * Will come back and update the solution if I find a better one in future
 * 
 * @author hpPlayer
 * @date May 17, 2016 10:44:47 PM
 */
public class Intersection_of_Two_Arrays_I_p349_sol1 {
    public int[] intersection(int[] nums1, int[] nums2) {
        //HashSet solution. using a HashSet to guarantee unique element in result and check an element in
    	//intersection in O(1) time
        Set<Integer> visited = new HashSet<Integer>();
        
        for(int num : nums1){
            visited.add(num);
        }
        
        Set<Integer> intersection = new HashSet<Integer>();
        
        for(int num : nums2){
            if(visited.contains(num)) intersection.add(num);
        }
        
        int[] result = new int[intersection.size()];
        
        int index = 0;
        
        for(int num : intersection){
            result[index++] = num;
        }
        
        return result;
    }
}
