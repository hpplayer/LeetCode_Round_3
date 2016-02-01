import java.util.*;

/*
317. Shortest Distance from All Buildings

You want to build a house on an empty land which reaches all buildings in the shortest amount of distance.
You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.

For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.

Note:
There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
*/	


/**
 * BFS solution
 * 
 * Here is the basic idea:
 * 
 * We use two maps to help us get the target empty land
 * One map(count) is used to check if an empty land can reach all buildings and help us check if an empty land has been visited in current BFS
 * One map(dist) is used to record the total travel distance
 * 
 * We update dist table by doing BFS on each building. At the same time we update count map
 * More specifically, starting from a building, we use BFS to find all accessible empty lands on the given grid. Then the steps we reach
 * each empty land would be the len of shortest path from current building. We repeatedly do this to all buildings then we can get the 
 * total travel distance for all buildings. As regard to the problem that whether all buildings can each current empty land, we should use
 * count map to track. Each BFS we will update count value by one. At the end of program, the count value should be the num of buildings 
 * we have in the given grid. So we just need to check this count value in count map and compare the value of total traversal distance, 
 * then we can easily get the solution
 * 
 * Time complexity: 
 * Initialization: O(mn)
 * BFS: O(mn) * t, where t is the num of buildings in given grid
 * Finding result: O(mn)
 * 
 * So the total time complexity would be O(mn) * t
 * 
 * @author hpPlayer
 * @date Jan 31, 2016 10:08:52 PM
 */
public class Shortest_Distance_from_All_Buildings_p317_sol1 {
    public int shortestDistance(int[][] grid) {
        //use a dist Map to record the total dist sum for all empty lands
        //We use BFS starting from each building to fill the Dist Map
        //finally pick the land that gives the smallest distance
        
        int m = grid.length, n = grid[0].length;
        
        //count map helps us determin if current land can access to all buildings
        //We are going to visit building one by one, for building i, unvisited lands in current run should have
        //value i in count map.
        int[][] count = new int[m][n]; 
        
        //dist map helps us record the total travel distance from all buildings if we choose current empty land
        //finally we are going to pick the land that gives the smallest total travel distance
        int[][] dist = new int[m][n];
        
        //lists that collect building indexes
        List<Point> buildings = new ArrayList<Point>();
        
        //update building list and initalize count map to exclude buildings and obstacles
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] != 0){
                    if(grid[i][j] == 1){
                        buildings.add(new Point(i, j, 0));
                    }
                    //for buildings and obstacles, mark count map value as int.min
                    count[i][j] = Integer.MIN_VALUE;
                }
            }
        }
        
        //do BFS starting from all buildings, and get the total travel distance for each empty land
        for(int i = 0; i < buildings.size(); i++){
            BFS(buildings.get(i), i, count, dist);
        }
        
        int result = -1;
        
        //check all empty land and find the one can access all buildings and has the smallest total travel distance
        //access all buildings means it has count value in count map = buildings.size() (remember we always + 1 after each BFS)
        //smallest distance means it has the smallest value in dist map
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(count[i][j] == buildings.size() && (result == -1 || dist[i][j] < result)){
                    result = dist[i][j];
                }
            }       
        }    
    
        return result;
        
    }
    
    public void BFS(Point point, int index, int[][] count, int[][] dist){
        int[] xOffSet = {0, 0, 1, -1};
        int[] yOffSet = {1, -1, 0, 0};
        
        Queue<Point> que = new LinkedList<Point>();
        que.offer(point);
        
        while(!que.isEmpty()){
            Point curr = que.poll();
            //update shortest path distance from current building to current empty land
            dist[curr.x][curr.y] += curr.dist;
            
            for(int i = 0; i < 4; i++){
                int newX = xOffSet[i] + curr.x;
                int newY = yOffSet[i] + curr.y;
                
                //add empty land to queue and update total travel distance
                //for each BFS, each empty land shall only be visited once
                //Since we will increase each empty land in count map only once each BFS, we just need to check
                //if count[x][y] == index. If it is, then it is still unvisited otherwise it has been visited or
                //it is a building/obstacle
                if(newX >= 0 && newX < count.length && newY >= 0 && newY < count[0].length && count[newX][newY] == index){
                    count[newX][newY]++;
                    que.offer(new Point(newX, newY, curr.dist + 1));
                }
            }
        }   
    }
    
    class Point{
        int x;
        int y;
        int dist;
        
        public Point(int x, int y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
