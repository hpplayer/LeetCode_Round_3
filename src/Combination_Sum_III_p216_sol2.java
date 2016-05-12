import java.util.*;

/**
 * iterative version of DFS/backtracking.
 * 
 * basically it is same with recursive version, and now we need four fields in the inner class
 * start variable that tells us where to start in new node
 * sum variable that tells us sum of curr combinations
 * count variable that tells us count of curr combinations
 * list variable that holds up all nums in curr combinations
 * 
 * Time complexity: O(1), since each number will be used only once, and candidates are from 1 to 9.
 * So we would have O(9!) loops at max, which is constant
 * Space complexity: same as above
 * 
 * @author hpPlayer
 * @date May 12, 2016 12:01:24 AM
 */
public class Combination_Sum_III_p216_sol2 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        //iterative version of DFS/backtracking. basically it is same with recursive version
        
        Stack<MyNode> stack = new Stack<MyNode>();
        
        for(int i = 1; i <= 9; i++){
            //early break here to avoid adding unnecessary num
            if(i > n) break;
            stack.push(new MyNode(i, 1, i + 1, Arrays.asList(i)));    
        }
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        while(!stack.isEmpty()){
            MyNode node = stack.pop();
            //found a valid combination
            if(node.sum == n && node.count == k){
                result.add(node.list);
                continue;
            }
            //we have done precheck when pushing node into stack so all nodes are valid candidate for result
            for(int i = node.start; i <= 9; i++){
                //early break here to avoid adding unnecessary num
                if(i + node.sum > n || 1 + node.count > k) break;
                List<Integer> list = new ArrayList<Integer>(node.list);
                list.add(i);
                stack.push(new MyNode(i + node.sum, node.count + 1, i + 1, list));
            }
        }
        
        return result;
    }
    
    private class MyNode{
        int sum;
        int count;
        int start;
        List<Integer> list;
        MyNode(int sum, int count, int start, List<Integer> list){
            this.sum = sum;
            this.count = count;
            this.start = start;
            this.list = list;
        }
    }
}
