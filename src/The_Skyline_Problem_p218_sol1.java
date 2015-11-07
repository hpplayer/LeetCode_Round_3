import java.util.*;
/*
 * 
 * The Skyline Problem
 * 
 * 
* A city's skyline is the outer contour of the silhouette formed by all the buildings 
* in that city when viewed from a distance. Now suppose you are given the locations and 
* height of all the buildings as shown on a cityscape photo (Figure A), write a program 
* to output the skyline formed by these buildings collectively (Figure B).
* 
*  ^                                        ^                                                                   
*  |                                        |                                                                   
*  |                                        |                                                                   
*  |    +-----+                             |    O-----+                                                        
*  |    |     |                             |    |     |                                                        
*  |    |     |                             |    |     |                                                        
*  |    |  +--+------+                      |    |     O------+                                                 
*  |    |  |         |                      |    |            |                                                 
*  |  +-+--+----+    |   +------+           |  O-+            |   O------+                                      
*  |  |         |    |   |      |           |  |              |   |      |                                      
*  |  |         |    |   |    +-+--+        |  |              |   |      O--+  
*  |  |         |    |   |    |    |        |  |              |   |         |                                   
*  |  |         |    |   |    |    |        |  |              |   |         |                                   
*  |  |         |    |   |    |    |        |  |              |   |         |                                   
*  |  |         |    |   |    |    |        |  |              |   |         |                                   
*  +--+---------+----+---+----+----+--->    +--+--------------O---+---------O--->                               
*  
*   https://leetcode.com/static/images/problemset/skyline1.jpg  
*   https://leetcode.com/static/images/problemset/skyline2.jpg  
* 
* The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], 
* where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, 
* and Hi is its height. It is guaranteed that 0 ¡Ü Li, Ri ¡Ü INT_MAX, 0 , and Ri - Li > 0. 
* You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
* 
* For instance, the dimensions of all buildings in Figure A are recorded as: 
*  [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
* 
* The output is a list of "key points" (red dots in Figure B) in the format of 
* [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. 
* A key point is the left endpoint of a horizontal line segment. 
*
* Note that the last key point, where the rightmost building ends, is merely used to mark 
* the termination of the skyline, and always has zero height. Also, the ground in between 
* any two adjacent buildings should be considered part of the skyline contour.
* 
* For instance, the skyline in Figure B should be represented as:
*  [ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
* 
* Notes:
* 
*  - The number of buildings in any input list is guaranteed to be in the range [0, 10000].
*  - The input list is already sorted in ascending order by the left x position Li. 
*  - The output list must be sorted by the x position. 
*  - There must be no consecutive horizontal lines of equal height in the output skyline. 
*    For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; 
*    the three lines of height 5 should be merged into one in the final output as such: 
*    [...[2 3], [4 5], [12 7], ...]
* 
*/

/**
 * Heap solution
 * 
 * We firstly define an edge class, then create and edge from each building to a temp list. Then we sort the temp list so that taller left edge will be left
 * possible and taller right will be right possible. With this rule, we can avoid duplicate height of edge to the lists. Then we use a max heap to store 
 * all edges in current scope. Then we begin pop edge from temp list to this heap. For left heap, if its height is taller than the tallest edge in heap,
 * we will add it to result. For right edge, it means the end of a building, so if we found it is the tallest edge, then after remove it, we will add 
 * an edge with second height to the result
 * 
 * Time complexity: Create and sort the list costs O(nlogn), each edge will then be in the heap once and removed once. So it also costs O(nlogn).
 * So the total running time would be O(nlogn)
 * 
 * Space complexity: O(n)
 * 
 * Sol1 is a max-heap + sort solution
 * Sols is a merge sort solution
 * 
 * Both of them are running with O(nlogn) time. But the running time of sol1 is a multiple of nlogn, also we have extra cost on max-heap. So sol2 is much
 * faster.
 * 
 * @author hpPlayer
 * @date Nov 6, 2015 11:14:42 PM
 */

public class The_Skyline_Problem_p218_sol1 {
    public class Edge{
        boolean isLeft;
        //x index
        int x;
        //height (y index)
        int y;
        
        Edge(int x, int y, boolean isLeft){
            this.x = x;
            this.y = y;
            this.isLeft = isLeft;
        }
    }
    
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<int[]>();
        //boundary check
        if(buildings.length == 0) return result;
        
        //create a edge list and sort each edge based on x-axis and height
        List<Edge> edges = new ArrayList<Edge>();
        
        for(int[] building : buildings){
            Edge left = new Edge(building[0], building[2], true);
            Edge right = new Edge(building[1], building[2], false);
            edges.add(left);
            edges.add(right);
        }
        
        edges.sort(new Comparator<Edge>(){
            public int compare(Edge a, Edge b){
                if(a.x != b.x){
                    //put edge with smaller x axis first
                    return a.x - b.x;
                }else{
                    if(a.isLeft && b.isLeft){
                        //both left, we want put taller edge first, so we will not add lower edges in the same x-axis 
                        return b.y - a.y;
                    }else if (!a.isLeft && !b.isLeft){
                        //both right, we want put lower edge first, so we will not add lower edges before we pop taller edge
                        return a.y - b.y;
                    }else{
                        //one left, one right, put left edge first, so we will not add left edge before we pop right edge
                        return a.isLeft? -1 : 1;
                    }
                }
            }   
        });
        
        //max-heap where we put edges in current scope
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(10, Collections.reverseOrder());
        
        for(Edge edge : edges){
            if(edge.isLeft){
                //left edge
                //if current scope is empty or incoming edge is taller, we will add it to result
                if(pq.isEmpty() || pq.peek() < edge.y){
                    result.add(new int[]{edge.x, edge.y});
                }
                //put this edge to current scope
                pq.offer(edge.y);
            }else{
                //right edge
                
                //we firstly remove corresponding left edge from pq, which means we are done with this building
                pq.remove(edge.y);
                
                if(pq.isEmpty()){
                    //if current scope becomes empty, then we will add a new edge with height 0, based on the given requirement
                    result.add(new int[]{edge.x, 0});
                }else{
                    //if the edge we removed was the tallest edge in the current scope, then we will add a new edge with second height
                    if(edge.y > pq.peek()){
                        result.add(new int[]{edge.x, pq.peek()});
                    }
                }
            }
        }
        
        return result;
    }
}
