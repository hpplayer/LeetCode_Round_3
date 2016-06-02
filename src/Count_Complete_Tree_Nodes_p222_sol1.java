/*
222. Count Complete Tree Nodes

Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as
possible. It can have between 1 and 2h nodes inclusive at the last level h.
*/

/**
 * Tree Traversal solution
 * 
 * We need to use the property of complete tree to speed up the counting process. If a tree is a perfect tree, then we can just 
 * use 2^h - 1 to get the total nodes (h is 1 based). 
 * In the complete tree, we can use 2^h - 1 to get the size of each subtree except for the subtree that have a imbalanced leaf
 * nodes. 
 * 
 * In sol1, we will firstly detect the lengths of leftmost path and rightmost path. If they are same, then curr tree is a perfect
 * tree we just use the length we got to get the number of tree nodes. If they are different, then we need do recursions on its
 * left and right subtrees, so that we can still use 2^h - 1 to calculate the num of nodes for the perfect left subtree. We recursively
 * do this until we reach the bottom. Then we accumulate results and return the sum
 * 
 * Time complexity: O(H) we will not check all nodes, instead we will go through layers until we reach leaf node
 * Space complexity: O(H)
 * 
 * Sol1 is the Tree Traversal solution, that make use len of leftmost and rightmost paths on current tree
 * Sol2 is another Tree traversal solution that make use of the len of leftmost path on left and right subtrees
 * 
 * @author hpPlayer
 * @date Jun 1, 2016 9:59:45 PM
 */
public class Count_Complete_Tree_Nodes_p222_sol1 {
    public int countNodes(TreeNode root) {
        //Tree traversal solution. Make use of the len of leftmost and rightmost path on current subtree
        
        return DFS(root);
    }
    
    public int DFS(TreeNode root){
        //boundary case
        if(root == null) return 0;
        
        TreeNode left = root, right = root;
        int height = 0;
        
        //we will detect the len(height) of left and right subtree, and stops when they are not in same level
        //Since input is a complete tree, we are guaranteed if tree is imperfect, we will stop at leaf level
        //and right node will reach null first in that case
        while(left != null && right != null){
            //find the len of leftmost path
            left = left.left;
            //find the len of rightmost path
            right = right.right;
            height++;
        }
        
        //if both pointer reaches null, then current tree is perfect tree, we use 2^h - 1 to get the size
        if(left == right) return (1 << height) - 1;
        
        //otherwise do recursion on left and right subtree separately
        return DFS(root.left) + 1 + DFS(root.right);
    }
}
