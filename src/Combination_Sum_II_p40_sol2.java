import java.util.*;

/**
 * Iterative version DFS/backtracking solution
 * 
 * We make use of stack and inner class to enumerate combinations
 * We also have early breaks to speed loops (not push node that already have sum > target)
 * here we are not allowing use same cell more than once, and also requires all combinations to be unique. 
 * So accordingly, we will update index to be index + 1 when creating new node, and skip duplicates cells to
 * avoid creating duplicate combinations
 * 
 * Time complexity: O(2^n), assume all inputs are 0 and target are 0, then we have O(2^n) combinations. But all
 * inputs are positive and we do not allow duplicates here, so we are bounded by O(2^n)
 * Space complexity: O(2^n)
 * 
 * Remark:
 * Since all inputs are positive, we can avoid the boundary cases like negative inputs and 0, which may force
 * us to look further even if our curr sum = target
 * @author hpPlayer
 * @date May 11, 2016 11:04:58 PM
 */
public class Combination_Sum_II_p40_sol2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //iterative version of DFS/backtracking. Making use of inner class and stack to enumerate combinations
        Arrays.sort(candidates);
        
        Stack<MyNode> stack = new Stack<MyNode>();
        
        for(int i = 0; i < candidates.length; i++){
            //early break to avoid adding unnecessary cell into stack
            if(candidates[i] > target) break;
            List<Integer> list = Arrays.asList(candidates[i]);
            //we need initialize node with i + 1 index, so we will not use a cell more than once
            stack.push(new MyNode(candidates[i], i+1, list));
            //skip duplicates
            while(i + 1 < candidates.length && candidates[i] == candidates[i+1]) i++;
        }
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        while(!stack.isEmpty()){
            MyNode node = stack.pop();

            if(node.sum == target){
                result.add(node.list);
                continue;
            }
            
            for(int i = node.index; i < candidates.length; i++){
                int sum = node.sum + candidates[i];
                //early break here to avoid adding unnecessary cell into stack
                if(sum > target) break;
                List<Integer> list = new ArrayList<Integer>(node.list);
                list.add(candidates[i]);
                //we need initialize node with i + 1 index, so we will not use a cell more than once
                stack.push(new MyNode(sum, i + 1, list));
                //skip duplicates
                while(i + 1 < candidates.length && candidates[i] == candidates[i+1]) i++;
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
