/*
240. Search a 2D Matrix II

Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
For example,

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.
*/	

/**
 * Divide-and-conquer and Binary-Search solution
 * 
 * We use divide-and-conquer method to split the matrix into four parts, then we use the mid col with binary search to check if target is not there.
 * If not, we can discard two parts, then we recursively do this until we found target or reach out of boundary
 * 
 * Time complexity: not easy to analyze, as we have recursion here
 * Space complexity: same as above
 * 
 * Sol2 provides a easy step-wise solution, which is O(mn) time
 * 
 * @author hpPlayer
 * @date Feb 15, 2016 8:55:32 PM
 */
public class Search_a_2D_Matrix_II_p240_sol1 {
    public boolean searchMatrix(int[][] matrix, int target) {
        //We firstly pick the mid col in current part, then search the first num in mid col >= target, if = target, we will return result
        //if > target, then we will split current part into 4 small parts, based on the middle part in this part, we can discard left top
        //part and right bot part. Then we recursively do this until we are run out of boundary or we found the target
        
    	return searchInPart(0, matrix[0].length - 1, 0, matrix.length - 1, target, matrix);
    }
    
    public boolean searchInPart(int left, int right, int top, int bot, int target, int[][] matrix){
        //check boundary
        if(left > right || top > bot) return false;
        
        //we find the mid col
        int mid = (left + right)/2;
        //then use binary search to find the first num >= target
        int row = binarySearch(mid, top, bot, target, matrix);
        
        //check return value
        //Notice: in case target is in boundary row, we need make sure return value is valid
        if(row < matrix.length && matrix[row][mid] == target) return true;
        
        //target not found, continue search
        //we can discard two parts, cuz matrix[row - 1][mid] is smaller than target and it is the largest number in top-left part
        //matrix[row][mid] is larger than target and it is the smallest number in bot-right part
        //so, we can continue search in top-right and bot-left part
        
        //search bot-left part and top-right part, not including mid col, as target is not found in mid col
        //for bot-left, we still need search in "row" row, as left elements in "row" row may still = target
        //for top-right, we don't need search in "row" row, as right elemetns in "row" row must > target
        return searchInPart(left, mid - 1, row, bot, target, matrix) || searchInPart(mid + 1, right, top, row - 1, target, matrix);
    }
    
    public int binarySearch(int col, int top, int bot, int target, int[][] matrix){
        while(top <= bot){
            int mid = top + (bot - top)/2;
            if(matrix[mid][col] == target){
                //found target, return mid row
                return mid;
            }else if(matrix[mid][col] > target){
                //too large, change bot
                bot = mid - 1;
            }else{
                //too small, change top
                top = mid + 1;
            }
        }
        
        //if can't find target in current col, return the first num > target, which is pointed by top
        return top;
    }
}
