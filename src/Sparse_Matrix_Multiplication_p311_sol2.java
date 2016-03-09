import java.util.*;
/**
 * Math solution
 * 
 * We firstly preprocess the input, and we only record the non-zeros in data-structure
 * Here we use an Arraylist to record non-zeros. For each non-zeros, we will record the index and values
 * Then we fill the result[][], we will skip loops, if we do not have non-zeros in current row in A or current col in B
 * For other rows, we will only do the multiplication on the none-zeros that have same index that we previously recorded
 * 
 * 
 * Time complexity: 
 * Compress A: O(ab), where a is A.len, b is A[0].len
 * Compress B: O(cd), where c is B.len, d is B[0].len
 * fill result[][]: O(abd) or O(acd)
 * total time: O(ab) + O(cd) + O(acd) but due to the fact that inputs are sparse, and our optimization will skip zero cells, the real time complexity should be smaller
 * If the inputs are extreme sparse, then sol2 should run very fast
 * 
 * Space complexity:
 * same as above, the total space complexity would be O(ab) + O(cd) + O(acd), but we only record non-zeros in rowsA and colsB, the real space complexity should be smaller
 * 
 * @author hpPlayer
 * @date Mar 8, 2016 12:44:27 PM
 */
public class Sparse_Matrix_Multiplication_p311_sol2 {
    public int[][] multiply(int[][] A, int[][] B) {
        //Math approach, we only record the non-zero cells in each input
        
        //boundary check
        if(A.length == 0 || B.length == 0) throw new IllegalArgumentException();
        //to do the matrix multiplication, we need A[0].length = B.length, otherwise it is an invalid input
        if(A[0].length != B.length) throw new IllegalArgumentException();
        
        //firstly compress matrix A
        //since we will multiple A row by row, we will use rowsA as the name
        //rowA[0] means the points in row 0 that are non-zero
        //HashMap<a, b> means we have a none-zero value at index a with value b
        List<HashMap<Integer, Integer>> rowsA = new ArrayList<HashMap<Integer, Integer>>();
        for(int i = 0; i < A.length; i++){
            HashMap<Integer, Integer> hs = new HashMap<Integer, Integer>();
            for(int j = 0; j < A[0].length; j++){
            	//record the index in current row, i.e. the col number
                if(A[i][j] != 0) hs.put(j, A[i][j]);
            }
            rowsA.add(hs);
        }
        
        //secondly compress matrix B
        //since will multiple B col by col, we will use colsB as the name
        List<HashMap<Integer, Integer>> colsB = new ArrayList<HashMap<Integer, Integer>>();
        //we need visit the B col by col
        //Notice: We need visit A and B in different ways in order to match the way we do the multiplication!!!!!!!!!!!!!!!!!!!
        for(int i = 0; i < B[0].length; i++){
            HashMap<Integer, Integer> hs = new HashMap<Integer, Integer>();
            for(int j = 0; j < B.length; j++){
            	//record the index in current col, i.e. the row number
                if(B[j][i] != 0) hs.put(j, B[j][i]);
            }
            colsB.add(hs);
        }        
        
        
        //then fill the result[][] only with the values from hashMap
        int[][] result = new int[A.length][B[0].length];
        
        //we can use naive way to fill the result[][], but we use rowsA and colsB to skip zero-cells
        //result.length == A.length
        for(int i = 0; i < result.length; i++){
            
            //if we found A does not have non-zero values in row i, then we skip the rest
            if(rowsA.get(i).isEmpty()) continue;
            
            //result[0].length == B[0].length
            for(int j = 0; j < result[0].length; j++){
                //if we found B does not have non-zero values in col j, then we skip the rest
                if(colsB.get(j).isEmpty()) continue;
                
                //Based on the math rule, we should have A[0].len == B.len
                for(int k = 0; k < A[0].length; k++){
                    //we only do multiplication on the non-values that have same index k
                	//if both rowsA and colsB have index k, then we do the multiplication on those two cells and add to result
                    if(rowsA.get(i).containsKey(k) && colsB.get(j).containsKey(k)) result[i][j] += rowsA.get(i).get(k) * colsB.get(j).get(k);
                }
            }
        }
        
        return result;
    }
}
