import java.util.*;

/**
 * BFS approach
 * 
 * We use BFS approach here, starting from 0, we will add one perfect square to the number in queue,
 * so it is like we create a node one step from current level. 
 * 
 * We also need a boolean[] here to avoid adding duplicates into the queue
 * 
 * Time complexity: we at most need O(n) time to find the target number, and each number will at most have O(n^0.5) neighbors
 * in next level. so total time complexity: O(n * n^0.5)
 * 
 * Space complexity: O(n) as we need O(n) boolean[] and may have at most O(n) nodes in queue
 * 
 * @author hpPlayer
 * @date Nov 16, 2015 4:42:50 PM
 */
public class Perfect_Squares_p279_sol2 {
    public int numSquares(int n) {
        //BFS approach, we will return result as long as we find n. So we won't waste time on other unnecessary number,
    	//therefore this approach is expected to be faster than dp approach
        
        //boundary check
        if(n <= 0) return 0;
        
        Queue<Integer> que = new LinkedList<Integer>();
        
        //add 0 as starting case
        que.offer(0);
        int level = 0;
        
        //there may be several ways to reach same num. We don't want to add same num into que twice,
        //so we will use a boolean[] 
        //Of course we can use HashSet as well, but result showed boolean[] is much faster than HashSet
        boolean[] visited = new boolean[n+1];
        
        while(!que.isEmpty()){
            int size = que.size();
            //update level variable
            level++;
            for(int i = 0; i < size; i++){
                int temp = que.poll();
                //check all perfect squares and see if current temp can reach n with one perfect square
                for(int j = 1; j * j + temp <= n; j++){
                    //found result
                    if(j*j + temp == n) return level;
                    
                    if(!visited[j*j+temp]){
                        //unvisited number
                        visited[j*j+temp] = true;
                        que.offer(j*j + temp);
                    }
                }
            }
        }
        
        //invalid input
        return -1;
    }
}
