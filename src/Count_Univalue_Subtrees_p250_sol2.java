import java.util.*;

/**
 * Iterative post-order solution
 * 
 * The basic idea is similar to sol1. But here we will use a stack and a HashMap to visit the tree with post-order
 * traversal. 
 * In sol1, each recursion needs an input node and an expected value, and return boolean result if subtree
 * rooted at curr node is a Univalue subtree
 * Here, we use an inner class to contain expectedValue and returned boolean value
 * 
 * Time complexity: O(N) each node will be visited once
 * Space complexity: O(N) our final hashMap will contain each node
 * 
 * @author hpPlayer
 * @date Jun 4, 2016 1:39:30 PM
 */

public class Count_Univalue_Subtrees_p250_sol2 {
    public int countUnivalSubtrees(TreeNode root) {
        //iterative post-order traversal solution
        
        //boundary check
        if(root == null) return 0;
        
        //hashMap
        HashMap<TreeNode, MyNode> hs = new HashMap<TreeNode, MyNode>();
        
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        //we don't have expected value for root node, so it can be any node
        hs.put(root, new MyNode(-1));
        
        int count = 0;
        
        while(!stack.isEmpty()){
            TreeNode curr = stack.peek();
            
            if( (curr.left == null || hs.containsKey(curr.left)) && (curr.right == null || hs.containsKey(curr.right)) ){
                //both left and right subtrees are visited, we can check curr node
                
                //remove curr node from stack
                stack.pop();
                
                boolean left = curr.left == null? true : hs.get(curr.left).isUnivalSubtrees;
                boolean right = curr.right == null? true : hs.get(curr.right).isUnivalSubtrees;
                
                //if left or subtree is not UnivalSubtree, then curr node will not be in a UnivalSubtree
                //we just update its isUnivalSubtrees value to be false (by default it is false already,
                //but we still update it here to make the logic more clear)
                if( !left || !right){
                    hs.get(curr).isUnivalSubtrees = false;
                    continue;
                }
                
                //otherwise curr node is in a UnivalSubtree, we update counter accordingly
                count++;
                
                //check if node == expectedValue, which will help us update curr's parent node
                hs.get(curr).isUnivalSubtrees = curr.val == hs.get(curr).expectedValue;
            }else{
                
                if( (curr.left != null && !hs.containsKey(curr.left) )){
                    //if curr has left subtree and we have not visited it
                    stack.push(curr.left);
                    //we always insert a new node into hs when we put in the stack, so we can set expected value
                    hs.put( curr.left, new MyNode(curr.val) );
                }else{
                    //if curr has right subtree and we have not visited it
                    stack.push(curr.right);
                    //we always insert a new node into hs when we put in the stack, so we can set expected value
                    hs.put( curr.right, new MyNode(curr.val) );                    
                }
            }
        }
        
        return count;
    }
    
    private class MyNode{
        int expectedValue;
        boolean isUnivalSubtrees;
        //we will only set expectedValue during initialization
        //we will update isUnivalSubtrees during the backtracking
        MyNode(int e){
            expectedValue = e;
        }
    }
}
