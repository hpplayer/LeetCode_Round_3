import java.util.*;

/**
 * Iterative version DFS/backtracking solution
 * 
 * We make use stack and inner class to enumerate combinations
 * We also have early breaks to speed the loops (not push node that already have sum > target)
 * 
 * Time complexity: O(2^n), assume all inputs are 0 and target are 0, then we have O(2^n) combinations. But all
 * inputs are positive, so we are bounded by O(2^n)
 * Space complexity: O(2^n)
 * 
 * Sol2 is the iterative version of Backtracking/DFS
 * 
 * Remark:
 * Since all inputs are positive, we can avoid the boundary cases like negative inputs and 0, which may force
 * us to look further even if our curr sum = target
 * @author hpPlayer
 * @date May 11, 2016 10:27:15 PM
 */
public class Combination_Sum_I_p39_sol2 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //iterative version of sol1. Make use of stack and inner class to enumerate combinations
        
        //sort the input list first
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        Stack<MyNode> stack = new Stack<MyNode>();
        
        //we initialize the stack with single vals in input
        for(int i = 0; i < candidates.length; i++){
            //early break if single val already > target
            if(candidates[i] > target) break;
            List<Integer> list = Arrays.asList(candidates[i]);
            stack.push(new MyNode(candidates[i], i, list));
        }
        
        //iterative version of DFS
        while(!stack.isEmpty()){
            MyNode node = stack.pop();
            //Notice: since we have done early break in initialization and updating the stack
            //we can safely assume all nodes are valid candidates in stack
            
            if(node.sum == target){
                //found a valid combination
                result.add(node.list);
                continue;
            }    
            
            for(int i = node.index; i < candidates.length; i++){
                //early break if adding vals will not approach target
                if(candidates[i] + node.sum > target) break;
                List<Integer> list = new ArrayList<Integer>(node.list);
                list.add(candidates[i]);
                stack.push(new MyNode(candidates[i] + node.sum, i, list));
            }
        }
        
        return result;
    }
    
    private class MyNode{
        int sum;
        int index;
        List<Integer> list;
        MyNode(int sum, int index, List<Integer> list){
            this.sum = sum;
            this.index = index;
            this.list = list;
        }
    }
}
