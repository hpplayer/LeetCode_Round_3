/*
360. Sort Transformed Array

Given a sorted array of integers nums and integer values a, b and c.
Apply a function of the form f(x) = ax2 + bx + c to each element x in the array.

The returned array must be in sorted order.

Expected time complexity: O(n)

Example:
nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,

Result: [3, 9, 15, 33]

nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5

Result: [-23, -5, 1, 7]
*/

/**
 * Math + two pointer solution
 * 
 * The given function is parabola, and the input is sorted, therefore we have trend for the output
 * If a < 0, then the y values of sorted x would be small -> large -> small
 * if a > 0, then the y values of sorted x would be large -> small -> large
 * Therefore we just need use two pointer plus the sign of "a", then we can easily output a sorted result
 * In the case a = 0, we can either treat it as a special case of a < 0 or a > 0. As long as we put correct
 * value in the correct order, our output would be valid
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * Remark:
 * sortTransformedArray() below gives a more intuitive representation of this solution
 * sortTransformedArray2() below gives a concise representation of this solution
 * 
 * @author hpPlayer
 * @date Jun 18, 2016 3:16:03 PM
 */
public class Sort_Transformed_Array_p360_sol1 {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        //Math + Two pointer solution
        
        int[] result = new int[nums.length];
        int left = 0, right = nums.length - 1;
        //we treat a = 0 as a special case of a > 0
        int index = a < 0? 0 : nums.length - 1;
        
        while(left <= right){
            //get y value of left x
            int l = getF(nums[left], a, b, c);
            //get y value of right x
            int r = getF(nums[right], a, b, c);
            
            //the order of output depends on sign of a
            if( a < 0 ){
                //a < 0, we get the smaller y value first
                if( l < r ){
                    result[index] = l;
                    left++;
                }else{
                    result[index] = r;
                    right--;
                }
                index++;                
            }else{
                 //a >= 0, we get the larger y value first
                if( l > r ){
                    result[index] = l;
                    left++;
                }else{
                    result[index] = r;
                    right--;
                }
                index--;                
            }
        }
        
        return result;
    }
    
    public int getF(int x, int a, int b, int c){
        return a * x * x + b * x + c;
    }
    
    public int[] sortTransformedArray2(int[] nums, int a, int b, int c) {
        //Math + Two pointer solution
        
        int[] result = new int[nums.length];
        int left = 0, right = nums.length - 1;
        //we treat a = 0 as a special case of a > 0
        int index = a < 0? 0 : nums.length - 1;
        
        while(left <= right){
            //the order of output depends on sign of a
            if( a < 0 ){
                //a < 0, we get the smaller y value first
                result[index] = getF(nums[left], a, b, c) < getF(nums[right], a, b, c)? getF(nums[left++], a, b, c) : getF(nums[right--], a, b, c);
                index++;                
            }else{
                //a >= 0, we get the larger y value first
                result[index] = getF(nums[left], a, b, c) > getF(nums[right], a, b, c)? getF(nums[left++], a, b, c) : getF(nums[right--], a, b, c);
                index--;                
            }
        }
        
        return result;
    }
}
