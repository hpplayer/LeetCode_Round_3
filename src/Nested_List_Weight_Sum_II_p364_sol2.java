import java.util.*;

/**
 * DFS + brainstorm solution 
 * 
 * In sol1, we find the maxDepth then multiple depth vs ints in that layer.
 * In this solution, we bring curr sum to following layers, and add to the sum of following layers
 * So we don't need to track the depth. The weight of sums have been included in the following sums
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Jul 10, 2016 12:26:40 AM
 */
public class Nested_List_Weight_Sum_II_p364_sol2 {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        return DFS(nestedList, 0);
    }
    
    private int DFS( List<NestedInteger> nestedList, int prevSum ){
        //reach boundary, return 0
        //leaf node will output empty list(), so return 0
        //ex: [1], we will have two DFS, the second DFS will have nestedList.isEmpty() = true, so return 0
        if( nestedList.isEmpty() ) return 0;
        
        //next list collects lists included in nestedList
        List<NestedInteger> nextList = new ArrayList<>();
        
        //each child DFS sum will include the sum from parent 
        int currSum = prevSum;
        
        for(NestedInteger temp : nestedList  ){
            if( temp.isInteger() ){
                currSum += temp.getInteger();
            }else{
                nextList.addAll(temp.getList() );
            }
        }
        
        //we add currSum here to include currSum in current layer
        //the weight * currSum has been calculated in following DFS
        return DFS(nextList, currSum) + currSum;
    }
}
