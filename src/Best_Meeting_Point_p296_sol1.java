import java.util.*;

/*
296. Best Meeting Point

A group of two or more people wants to meet and minimize the total travel distance.
You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group.
The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

For example, given three people living at (0,0), (0,4), and (2,2):

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.

Hint:

Try to solve it in one dimension first. How can this solution apply to the two dimension case?
*/		

/**
 * Math + observation solution
 * 
 * In 1D version, we need to observe that the best meeting point is the median.
 * Then we can extend conclusion to 2D version. The meeting point will be the median of x and y axis
 * After that we just need to calculate Manhattan Distance.
 * Say median meeting point is at (x,y), and we have people at 1, 2, .., n-1, n, then we need to get ( (x-1) + (x-2) ....(n-1-x) +
 * (n-x) ) + ( (y-1) + (y-2) ..(n-1-y) + (n-y) ). Since x is median point, we can optimize the distance equation to be
 * (n-1 + n-1-2 + ..) + (n-1 + n-1-2 +..). This means we can calculate the sum of distance without knowing median point
 * 
 * Time complexity: O(mnlg(mn)) due to sorting
 * Space complexity: O(mn)
 * 
 * Remark:
 * 1. Attachment has an official solution which explains solutions very well
 * 2. Median is able to minimize the sum of distances for 1D data. This is a well-know conclusion in statistics
 * see:
 * https://en.wikipedia.org/wiki/Geometric_median and 
 * http://math.stackexchange.com/questions/113270/the-median-minimizes-the-sum-of-absolute-deviations
 * 
 * @author hpPlayer
 * @date Jun 4, 2016 10:34:19 PM
 */
public class Best_Meeting_Point_p296_sol1 {
    public int minTotalDistance(int[][] grid) {
        //Math + observation solution. We need to know that median point can minimize the sum of distance
        
        //rows and cols store indexes that have people
        List<Integer> rows = new ArrayList<Integer>();
        List<Integer> cols = new ArrayList<Integer>();
        
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        
        //since outer loop is based on rows, rows list is sorted by default, we just need to sort the cols list
        Collections.sort(cols);
        
        return getDistance(rows) + getDistance(cols);
    }
    
    public int getDistance(List<Integer> list){
        //use two pointers to get the sum of distance
        int result = 0;
        int left = 0, right = list.size() - 1;
        
        while(left < right){
            result += list.get(right) - list.get(left);
            left++;
            right--;
        }
        
        return result;
    }
}
