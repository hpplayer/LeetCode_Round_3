import java.util.List;

/*
339. Nested List Weight Sum

Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)

Example 2:
Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)
*/

/**
 * Recursive DFS solution
 * 
 * We just do DFS on each List<NestedInteger>, and also pass the depth to calculate the weight
 * 
 * Time complexity: O(N) since each integer/list will be visited once
 * Space complexity: O(K) where k is deepest depth
 * 
 * Sol1 is recursive DFS solution
 * Sol2 is iterative BFS solution
 * 
 * @author hpPlayer
 * @date Jun 5, 2016 11:29:14 PM
 */
public class Nested_List_Weight_Sum_p339_sol1 {
    public int depthSum(List<NestedInteger> nestedList) {
        //recursive DFS solution. Code is self-explained
        return DFS(nestedList, 1);
    }
    
    public int DFS(List<NestedInteger> nestedList, int depth){
        
        int sum = 0;
        
        for(NestedInteger temp : nestedList){
            if(temp.isInteger()){
                sum += temp.getInteger() * depth;
            }else{
                sum += DFS(temp.getList(), depth + 1);
            }
        }
        
        return sum;
    }
}
