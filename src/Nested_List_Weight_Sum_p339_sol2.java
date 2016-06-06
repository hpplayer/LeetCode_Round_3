import java.util.*;

/**
 * Iterative BFs solution
 * 
 * The basic idea is similar to sol1, but now we use BFS to iteratively visit list
 * 
 * Time complexity: O(N), since each element (list/Integer) will be visited once
 * Space complexity: O(k), the size of largest layer
 * 
 * @author hpPlayer
 * @date Jun 5, 2016 11:35:08 PM
 */
public class Nested_List_Weight_Sum_p339_sol2 {
    public int depthSum(List<NestedInteger> nestedList) {
        //iterative BFS solution
        
        //boundary check
        if(nestedList == null) return 0;
        
        Queue<List<NestedInteger>> que = new LinkedList<List<NestedInteger>>();
        que.offer(nestedList);
        int depth = 1;
        int sum = 0;
        
        while(!que.isEmpty()){
            int size = que.size();
            for(int i = 0; i < size; i++){
                List<NestedInteger> list = que.poll();
                for(NestedInteger temp : list){
                    if(temp.isInteger()){
                        sum += temp.getInteger() * depth;
                    }else{
                        que.offer( temp.getList()  );
                    }
                }
            }
            depth++;
        }
        
        return sum;
    }
}
