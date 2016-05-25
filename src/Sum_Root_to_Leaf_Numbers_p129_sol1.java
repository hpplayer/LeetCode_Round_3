/*
129. Sum Root to Leaf Numbers

Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.
*/

/**
 * Recursive DFS solution
 * 
 * The idea is intuitive, we accumulate sum every time we reach a leaf node.
 * Here we use a gobal sum to record the accumulated path sum
 * The difficulty part is to be clear with this logic (top-down), and dont be mixed with down-top way
 * 
 * Say we have a tree like this
 * 
 * 1
 *  \
 *   3
 *  / \
 * 6   7
 * 
 * We cant build numbers from down to top, since at root 1, we don't know how to handle values from bottom
 * should we just use 173, or should we do something else
 * The correct way is building number from top to down. So at leaf 6 we have 136 and at leaf 7 we have 137
 * we just sum them up to get correct solution
 * 
 * Time complexity: O(N) as each node will be visited once
 * Space complexity: O(N) in case input tree is a list-like style, we have O(N) nested DFS
 * 
 * Remark:
 * we can also solve this problem by iterative way, where we use an inner class to hold curr node and curr path
 * sum. But it has similar idea to recursive way and also similar code body to previous iterative DFS tree solution
 * so I did not list it here
 * 
 * @author hpPlayer
 * @date May 24, 2016 11:07:01 PM
 */
public class Sum_Root_to_Leaf_Numbers_p129_sol1 {
    public int sumNumbers(TreeNode root) {
        //build number top-down, and accumulate sum at leaf node
        sum = 0;
        DFS(root, 0);
        return sum;
    }
    int sum;
    
    public void DFS(TreeNode root, int pathSum){
        //boundary condition
        if(root == null) return;
        
        //update pathSum to include curr node
        pathSum = pathSum * 10 + root.val;
        
        //if curr node is leaf node, then we add it to global sum variable
        if(root.left == null && root.right == null){
            sum += pathSum;
            return;
        }
        
        //otherwise we visit subtrees and accumulate sum there
        if(root.left != null) DFS(root.left, pathSum);
        if(root.right != null) DFS(root.right, pathSum);
    }
}
