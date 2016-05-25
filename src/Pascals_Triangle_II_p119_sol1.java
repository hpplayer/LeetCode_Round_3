import java.util.*;

/*
119. Pascal's Triangle II

Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?
*/

/**
 * DP + rolling row solution
 * 
 * We just use a list repeatedly and use set() to update values
 * We will read and update this array leftward, so we don't need extra variable to hold updated value
 * 
 * Remark:
 * Of course we can update rolling row rightward by using an extra variable, but it does not give extra performance
 * So we read array leftward to avoid the use of extra variable
 * 
 * Time complexity: O(k^2) as last row have k elements
 * Space complexity: O(K)
 * 
 * @author hpPlayer
 * @date May 24, 2016 9:44:34 PM
 */
public class Pascals_Triangle_II_p119_sol1 {
    public List<Integer> getRow(int rowIndex) {
        //DP solution with rolling row technique
        
        List<Integer> result = new ArrayList<Integer>();
        
        //rowIndex starts with 0
        for(int i = 0; i <= rowIndex; i++){
            //add tail 1
            result.add(1);
            
            //update elements between boundary 1s, which appears since 2th row
            //i + 1 is the size of ith row, i is the last index in each row
            //i - 1 is the last index of elements before tail 1 and 1 is the first index of elements after head 1
            for(int j = i - 1; j > 0; j--){
                //by read/update array leftward, we won't worry about overwrite old value
                result.set(j,  result.get(j) + result.get(j-1) );
            }
        }
        
        return result;
    }
}
