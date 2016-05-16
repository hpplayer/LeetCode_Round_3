/*
74. Search a 2D Matrix

Write an efficient algorithm that searches for a value in an m x n matrix.
This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.
*/

/**
 * Binary search solution
 * 
 * Since the input matrix is sorted in row and col. We can do two binary searches. First binary search will locate
 * the row target value at, and second binary search will locate the col target value at.
 * 
 * Time complexity: O(logM*logN)
 * Space complexity: O(1)
 * 
 * Sol2 is the stepwise solution
 * 
 * @author hpPlayer
 * @date May 15, 2016 11:00:45 PM
 */
public class Search_a_2D_Matrix_I_p74_sol1 {
    public boolean searchMatrix(int[][] matrix, int target) {
        //two binary search solution
        
        //boundary check
        if(matrix.length == 0) return false;
        
        //first binary search, which can locate the row
        int top = 0, bot = matrix.length - 1;
        
        //scan each row
        while(top <= bot){
            int mid = top + (bot - top)/2;
            
            if(matrix[mid][0] == target){
                //target found
                return true;
            }else if(matrix[mid][0] > target){
                //too large
                bot = mid - 1;
            }else{
                //too small
                top = mid + 1;
            }
        }
        
        //if target is not in matrix[i][0], then we need to search curr row that pointed by "bot"
        //since matrix[bot][0] will be the last start cell that have value < target
        
        //Another boundary check to make sure cur "bot" row is valid
        //Notice: all values in martix may > target, so "bot" row may not be valid now!!!!!!!!!!
        if(bot < 0) return false;
        
        //second binary search, which can locate the col    
        int left = 0, right = matrix[0].length - 1;
        
        //scan each col
        while(left <= right){
            int mid = left + (right - left)/2;
            if(matrix[bot][mid] == target){
                //target found
                return true;
            }else if(matrix[bot][mid] > target){
                //too largest
                right = mid - 1;
            }else{
                //too small
                left = mid + 1;
            }
        }
        
        //can't find the target in input matrix
        return false;
    }
}
