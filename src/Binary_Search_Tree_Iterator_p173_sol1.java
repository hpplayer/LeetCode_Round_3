import java.util.*;

/*
173. Binary Search Tree Iterator

Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
*/

/**
 * Stack Problem
 * 
 * Here is the basic idea: we use a global stack to store nodes.
 * We firstly insert the sequence of leftmost nodes into the stack.
 * Then when we call next(), we will pop a node from stack and go to its right child subtree, then insert the sequence of leftmost node in this subtree
 * 
 * 
 * Each level we will only include one node in the stack, so the space complexity will be O(n)
 * The time complexity will be O(n)
 * 
 * @author hpPlayer
 * @date Feb 9, 2016 10:29:50 AM
 */
public class Binary_Search_Tree_Iterator_p173_sol1 {
	   Stack<TreeNode> stack;
	    
	    public Binary_Search_Tree_Iterator_p173_sol1(TreeNode root) {
	        stack = new Stack<TreeNode>();
	        
	        //Insert the sequence of leftmost nodes
	        TreeNode curr = root;
	        while(curr != null){
	            stack.push(curr);
	            curr = curr.left;
	        }
	        
	    }

	    /** @return whether we have a next smallest number */
	    public boolean hasNext() {
	        return !stack.isEmpty();
	    }

	    /** @return the next smallest number */
	    public int next() {
	        //pop the smallest node from stack, and go to its right child
	        //with the sequence of leftmost nodes
	        
	        TreeNode temp = stack.pop();
	        TreeNode curr = temp.right;
	        
	        while(curr != null){
	            stack.push(curr);
	            curr = curr.left;
	        }
	        
	        return temp.val;
	    }
}
/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */