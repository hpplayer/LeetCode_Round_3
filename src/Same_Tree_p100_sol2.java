import java.util.Stack;

/**
 * Iterative DFS solution
 * 
 * Similar idea to sol1, but now we use a stack to visit two trees iteratively.
 * We can use two stacks to hold nodes from two inputs, but to save space, here we use one stack and an inner 
 * NodePair class to visit two trees
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date May 22, 2016 11:39:41 PM
 */
public class Same_Tree_p100_sol2 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        //iterative DFS solution. To save space, use an inner class to hold node pairs
        
        Stack<NodePair> stack = new Stack<NodePair>();
        
        stack.push(new NodePair(p, q));
        
        while(!stack.isEmpty()){
            NodePair curr = stack.pop();
            
            //boundary case    
            if(curr.p == null || curr.q == null){
                if(curr.p != curr.q) return false;
                else continue;
            }
            
            //different node return false
            if(curr.p.val != curr.q.val) return false;
            
            stack.push(new NodePair(curr.p.left, curr.q.left));
            stack.push(new NodePair(curr.p.right, curr.q.right));
        }
        
        return true;
    }
    
    private class NodePair{
        TreeNode p;
        TreeNode q;
        
        NodePair(TreeNode p, TreeNode q){
            this.p = p;
            this.q = q;
        }
    }
}
