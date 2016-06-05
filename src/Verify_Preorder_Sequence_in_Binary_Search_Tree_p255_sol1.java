import java.util.*;

/*
255. Verify Preorder Sequence in Binary Search Tree

Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Follow up:
Could you do it using only constant space complexity?
*/

/**
 * Preorder traversal + observation solution
 * 
 * We need to observe that if the array is built based on preorder traversal, then we will have a lower_bound
 * that all later inputs should not be smaller than it. This is because after we finish left BST subtree, all
 * later nodes will larger than this value
 * 
 * So the basic idea of this solution is iteratively updating the lower_bound and check later inputs based on
 * this bound. In sol1, we use a stack to hold nodes and help us visit the tree.
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * Sol1 we use an extra stack to help us visit the tree
 * Sol2 we use the input array as stack and visit the tree
 * 
 * Remark:
 * An array can be represented as several forms of BST, but our solution makes sure there at least one of the BST
 * represent would be a valid representation
 * 
 * ex: 3,4,5
 *   3            3    
 *  / \     or     \
 * 4   5            4
 *                   \
 *                    5
 * @author hpPlayer
 * @date Jun 4, 2016 4:04:02 PM
 */
public class Verify_Preorder_Sequence_in_Binary_Search_Tree_p255_sol1 {
    public boolean verifyPreorder(int[] preorder) {
        //Preorder traversal + observation solution, use a stack to visit the tree and use a lower_bound to
        //check if next input is valid
        
        Stack<Integer> stack = new Stack<Integer>();
        int lower_bound = Integer.MIN_VALUE;
        
        for(int num : preorder){
            //if curr input is smaller lower_bound, then we return false directly
            if(num < lower_bound) return false;
            
            //if we starts visiting right subtree, then we update lower_bound to be the root of whole subtree
            //This root is the last value in stack that < curr input
            while(!stack.isEmpty() && stack.peek() < num){
                lower_bound = stack.pop();
            }
            
            //include curr node into stack
            stack.push(num);
        }
        
        //all nodes checked without problem
        return true;
    }
}
