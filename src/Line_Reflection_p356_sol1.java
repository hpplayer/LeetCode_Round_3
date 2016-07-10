import java.util.*;

/*
356. Line Reflection

Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given set of points.

Example 1:
Given points = [[1,1],[-1,1]], return true.

Example 2:
Given points = [[1,1],[-1,-1]], return false.

Follow up:
Could you do better than O(n2)?

Hint:

Find the smallest and largest x-value for all points.
If there is a line then it should be at y = (minX + maxX) / 2.
For each point, make sure that it has a reflected point in the opposite side.
*/

/**
 * Sorting + observation solution
 * 
 * We observed that the reflection means we have points in two side of target line, and all points in one side
 * can have corresponding points in the other side.
 * 
 * So we firstly scan the input to find the target line, which is just (minX + maxX)/2. Since the target line
 * may not in an integer x, we define it as double
 * 
 * Then we sort the inputs, and use two points to find those corresponding points. In the sorting, we define 
 * that points before target line will have ascending trend in both x and y while points after target line
 * will have ascending in x and descending in y
 * 
 * Time complexity: O(nlogN) + O(N) => O(nlogN)
 * Space complexity: O(1)
 * 
 * Remark:
 * This problem is new, I haven't seen better solution. I will update it later if I found one
 * @author hpPlayer
 * @date Jun 11, 2016 6:03:43 PM
 */
public class Line_Reflection_p356_sol1 {
    public boolean isReflected(int[][] points) {
        //observation + sorting solution. We use observation to find the target line and use sorting to check points
        
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        
        //find the min and max Xs to locate the target line
        for(int[] point : points){
            minX = Math.min( minX, point[0] );
            maxX = Math.max( maxX, point[0] );
        }
        
        //x value of targetLine is just (minX + maxX)/2, we define it as double to be more accurate
        double targetLine = (minX + maxX)/2.0;
        
        //we sort the input, for points before targetLine, we let both x and y ascend
        //for points after targetLine, we let x ascend but y descend
        Arrays.sort( points, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                if( a[0] != b[0]){
                    // x is always ascending
                    return a[0] - b[0];
                }else{
                    //y is ascending before targetLine, and start descending after targetLine
                    if( a[0] < targetLine ){
                        return a[1] - b[1];
                    }else{
                        return b[1] - a[1];
                    }
                }
            }
        } );
        
        //then use two pointers to check all points
        int left = 0, right = points.length - 1;
        
        while( left <= right ){
            //if the middle line is not targetLine, return false
            if( (points[left][0] + points[right][0]) / 2.0 !=  targetLine ) return false;
            
            //if middle line is targetLine, then we check y values. For points at different x, y should be same
            //if at same x, then y can be different. So we just need to check first case to make sure y is same
            
            if( (points[left][0] != points[right][0]) && (points[left][1] != points[right][1]) ) return false;
            
            //skip duplicate points. We allow duplicate points match one point
            while(left + 1 <= right && Arrays.equals(points[left + 1], points[left]))  left++;
            while(left <= right - 1 && Arrays.equals(points[right - 1], points[right]))  right--;
            
            left++;
            right--;
        }
        
        //all points checked, no problem
        return true;
    }
}
