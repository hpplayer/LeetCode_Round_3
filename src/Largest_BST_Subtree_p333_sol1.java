/*
333. Largest BST Subtree

Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree
with largest number of nodes in it.

Note:
A subtree must include all of its descendants.
Here's an example:
    10
    / \
   5  15
  / \   \ 
 1   8   7
The Largest BST Subtree in this case is the highlighted one. 
The return value is the subtree's size, which is 3.
Hint:

You can recursively use algorithm similar to 98. Validate Binary Search Tree at each node of the tree,
which will result in O(nlogn) time complexity.

Follow up:
Can you figure out ways to solve it with O(n) time complexity?
*/	

/**
 * BST validation + post-order traversal + observation solution
 * 
 * Here is the basic idea:
 * we validate BST backward, and push min and max limit from bottom to top. The problems requires a valid BST contains
 * all subtree nodes, therefore we need to store the information whether subtree rooted in curr tree is valid. We also
 * need such information from left and right subtrees. Besides, in case curr node is not the root of BST, but one
 * of its subtrees may be the largest BST, therefore we need another variable to hold the max subtree size we have 
 * encountered so far.
 * So we will return four variables from the traversal function, min and max limit from subtrees, whether subtrees
 * are valid BST and the size of largest BST we have encountered so far. To wrap them up, we will use an external class
 * To visit the tree bottom-up style, we need apply post-order traversal
 * 
 * 
 * Time complexity: O(N) Each node will be visited at most twice, once forward once backward.
 * Space complexity: O(N) same as above
 * 
 * Remark:
 * In Validate_Binary_Search_Tree_p98_sol1, we use top-down way to validate BST since we want to return as early
 * as possible if we found curr node does not belong to a BST 
 * However in curr solution, we want to get the max size of BST. Even if curr node does not belong to a BST, we still
 * want to go deeper search its subtree. Therefore it is better to build the BST down-top way, so we even don't need
 * to bother validate nodes when we are visiting the tree forward
 * 
 * @author hpPlayer
 * @date Mar 13, 2016 4:35:47 PM
 */
public class Largest_BST_Subtree_p333_sol1 {
    public int largestBSTSubtree(TreeNode root) {
        //BST validation + pos-torder traversal + observation solution
        //The basic idea is to traversal the tree and record the largest BST subtree. In this solution, we 
        //will use post-order traversal i.e. to validate the BST subtree from bottom-up. This way will help us
        //record the size of BST as well as validate BST subtree. To validate a subtree, we need to know if curr
        //root node matches BST rule, and we also need to know if its left and right subtree is a valid BST as well.
        //Therefore we need multiple information returned by subtrees, including max and min limit and
    	//whether we have counted invalid BST already
        return DFS(root).size;
    }
    
    public MyNode DFS(TreeNode root){
        //reach null boundary node. To make the leaf node valid, we will make sure leaf node's val < min returned 
        //by right null node and node's val > max returned by left null node. So we will intialize null's min to be
    	//int_max and null's max to be int_min
        if(root == null) return new MyNode(Integer.MAX_VALUE, Integer.MIN_VALUE, 0, true);
        
        //use post-order traversal to visit the tree
        MyNode left = DFS(root.left);
        MyNode right = DFS(root.right);
        
        int size = 0;
        boolean isValid = false;
        
        if(root.val > left.max && root.val < right.min && left.isValid && right.isValid){
        //if curr node is a valid BST root, which means its value is good and its left and right subtrees are valid BST
        //then we will update size and isValid accordingly            
            isValid = true;
            size = left.size + 1 + right.size;
        }else{
            //otherwise, curr node will not make up a BST, and we only update the size variable to be the max size
            //returned by subtrees
            size = Math.max(left.size, right.size);
        }
        
        //in general case, update min value to be left.min, if we don't have left.min (leaf node),
        //then update min to be itself
        int mn = root.left == null? root.val : left.min;
        //same to max
        int mx = root.right == null? root.val : right.max;
        
        return new MyNode(mn, mx, size, isValid);
    }
    
    private class MyNode{
        //to validate a BST, we need two conditions: curr node matches BST rule, and its child node matches BST rule
        
        //variable max and min will help us decide if current tree matches BST,
        //variable isValid will help us decide if child nodes matche BST
        //size is the variable that recorcds the largest BST we have so far
        
        int size, min, max;
        boolean isValid;
        
        MyNode(int mn, int mx, int s, boolean isV){
            size = s;
            min = mn;
            max = mx;
            isValid = isV;
        }
    }
}
