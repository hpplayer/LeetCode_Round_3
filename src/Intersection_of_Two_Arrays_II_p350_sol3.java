import java.util.*;

/**
 * HashMap solution
 * 
 * Using a hashMap to store all elements in shorter input, then scan longer input to find intersect
 * This solution does not need sorting input, but using extra spaces
 * 
 * Time complexity: O(m + n)
 * Space complexity: O(min(len(nums1, nums2)))
 * 
 * @author hpPlayer
 * @date May 22, 2016 11:20:41 PM
 */
public class Intersection_of_Two_Arrays_II_p350_sol3 {
    public int[] intersect(int[] nums1, int[] nums2) {
        //HashMap solution, we use a hashMap to store and count elements in shorter input
        
        //to save space, we will build hashMap for shorter input
        //here we assume nums1 is shorter, if not reverse input order
        if(nums1.length > nums2.length) return intersect(nums2, nums1);
        
        List<Integer> list = new ArrayList<Integer>();
        HashMap<Integer, Integer> hs = new HashMap<Integer, Integer>();
        
        for(int num : nums1){
            if(!hs.containsKey(num)) hs.put(num, 0);
            hs.put(num, hs.get(num) + 1);
        }
        
        for(int num : nums2){
            if(!hs.containsKey(num)) continue;
            //if count in hs > 0, then we know it is a part of intersect
            if(hs.get(num) > 0) list.add(num);
            hs.put(num, hs.get(num) - 1);
        }        
        
        int[] result = new int[list.size()];
        for(int i = 0; i < result.length; i++){
            result[i] = list.get(i);
        }
        
        return result;
    }
}
