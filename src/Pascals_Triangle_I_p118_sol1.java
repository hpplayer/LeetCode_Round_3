import java.util.*;

/*
118. Pascal's Triangle

Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/		

/**
 * DP + Observation solution
 * 
 * We need create array for curr row based on array we created for curr -1 row
 * element of Index i in curr row is sum of elements in index i and i - 1 in prev row
 * For each row, we will add 1 to first and last index
 * 
 * Time complexity: O(N^2), ith row has i elements, therefore in last row we need go through n elements
 * Space complexity: O(N^2)
 * 
 * @author hpPlayer
 * @date May 24, 2016 8:51:55 PM
 */
public class Pascals_Triangle_I_p118_sol1 {
    public List<List<Integer>> generate(int numRows) {
        //DP solution. We will make use array from numRow - 1 to create array for numRow
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        //list we created for numRow - 1 row
        List<Integer> prev = null;

        for(int i = 1; i <= numRows; i++){
            //list we will create for numRow row
            List<Integer> curr = new ArrayList<Integer>();
            
            for(int j = 0; j < i; j++){
                if(j == 0 || j == i-1){
                    //for each new row, we will add 1 to first and last index
                    curr.add(1);
                }else{
                    //For other indexes, we will add them based on information from above
                    //element at index i is sum of element at i and i - 1 in prev row
                    curr.add(prev.get(j-1) + prev.get(j));
                }
            }
            prev = curr;
            result .add(curr);
        }
        
        return result;
    }
}
