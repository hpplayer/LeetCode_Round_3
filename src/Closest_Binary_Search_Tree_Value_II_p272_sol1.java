import java.util.*;
/*
Closest Binary Search Tree Value II

Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.


Note:
Given target value is a floating point.
You may assume k is always valid, that is: k ¡Ü total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.

Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

Hint:

1. Consider implement these two helper functions:
	getPredecessor(N), which returns the next smaller node to N.
	getSuccessor(N), which returns the next larger node to N.
2. Try to assume that each node has a parent pointer, it makes the problem much easier.
3. Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
4. You would need two stacks to track the path in finding predecessor and successor node separately.
*/

/**
 * Stack problem
 * 
 * We use two stacks to store nodes smaller/larger than target respectively
 * We use in-order or reversed in-order traversal to visit the tree so the trend is kept
 * Then we begin collect nodes from two stacks to the result list. We will add the one that gives smaller difference with target into
 * the result list. We repeat do this until the size reach k
 * 
 * 
 * Time complexity: O(N + k)
 * O(N) to traverse the tree and O(k) to fill the result list
 * 
 * Sol2 uses a similar idea, but with optimization, so sol2 runs faster
 * 
 * @author hpPlayer
 * @date Feb 8, 2016 2:27:29 PM
 */
public class Closest_Binary_Search_Tree_Value_II_p272_sol1 {
	   public List<Integer> closestKValues(TreeNode root, double target, int k) {
	        Stack<Integer> max = new Stack<Integer>();
	        Stack<Integer> min = new Stack<Integer>();
	        
	        inOrder(root, max, target, true);
	        inOrder(root, min, target, false);
	        
	        List<Integer> result = new ArrayList<Integer>();
	        
	        while(result.size() < k){
	            if(min.isEmpty()){
	                result.add(max.pop());
	            }else if(max.isEmpty()){
	                result.add(min.pop());
	            }else{
	                if(target - min.peek() < max.peek() - target){
	                    result.add(min.pop());
	                }else{
	                    result.add(max.pop());
	                }
	            }
	        }
	        
	        return result;
	    }
	    
	    public void inOrder(TreeNode root, Stack<Integer> maxOrMin, double target, boolean isMax){
	        Stack<TreeNode> stack = new Stack<TreeNode>();
	        
	        TreeNode curr = root;
	        
	        while(!stack.isEmpty() || curr != null){
	            if(curr != null){
	                stack.push(curr);
	                curr = isMax? curr.right : curr.left;
	            }else{
	                TreeNode temp = stack.pop();
	                if( (isMax && temp.val <= target) || (!isMax && temp.val > target) ){
	                    return;
	                }
	                
	                maxOrMin.add(temp.val);
	                curr = isMax ? temp.left : temp.right;
	            }
	        }
	        
	    }
}
