import java.util.*;

/*
364. Nested List Weight Sum II

Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Different from the previous question where weight is increasing from root to leaf, now the weight is
defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

Example 1:
Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)

Example 2:
Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)
*/

/**
 * HashMap solution
 * 
 * We have to find the maxDepth before we can do the sum calculation. So we use a HashMap to record the 
 * integer we visited so far. After we visited all inputs and got the maxDepth, we can start to calculate the sum.
 * 
 * In the HashMap we don't need to record all integers we visited, we just need to record the sum of integers
 * in current depth
 * 
 * Time complexity: O(N), N is num of inputs we have
 * Space complexity: O(H), H is maxDepth
 * 
 * Sol1 is intuitive DFS solution with Hashmap and global depth
 * Sol2 is non-intuitive DFS solution without hashMap nor global depth
 * 
 * @author hpPlayer
 * @date Jun 22, 2016 8:11:20 PM
 */

public class Nested_List_Weight_Sum_II_p364_sol1 {
    int maxDepth = 0;
    
    public int depthSumInverse(List<NestedInteger> nestedList) {
        //HashMap solution. We use HashMap to store nums in each depth before we find the maxDepth
        //we will do the sum calculation in the last
        
        HashMap<Integer, Integer> hs = new HashMap<Integer, Integer>();
        
        DFS( nestedList, 1, hs );
        
        int sum = 0;
        
        //get sum 
        for( int i = 1; i <= maxDepth; i++ ){
            //put a checker here in case we dont have integer in one layer
            if( hs.containsKey(i ) ) sum += hs.get(i) * (maxDepth + 1 -i);
        }
        
        return sum;
    }
    
    private void DFS(List<NestedInteger> nestedList, int depth,  HashMap<Integer, Integer> hs ){
        //boundary check
        
        if(nestedList.isEmpty()) return;
        
        //update maxDepth if possible
        maxDepth = Math.max(maxDepth, depth);
        
        for( NestedInteger temp : nestedList ){
            if( temp.isInteger() ){
                //if temp is integer
                if( !hs.containsKey(depth) ){
                    hs.put( depth, temp.getInteger()  );
                }else{
                    hs.put( depth, hs.get(depth) + temp.getInteger()  );
                }
            }else{
                //if temp is list
                DFS(  temp.getList(), depth + 1, hs  );
            }
        }
    }
}
