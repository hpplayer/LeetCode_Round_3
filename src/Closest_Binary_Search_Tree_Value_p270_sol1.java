/*
270. Closest Binary Search Tree Value

Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
*/

/**
 * Tree traversal solution
 * 
 * Nothing special, we just go through the BST tree based on the comparison result between root and target
 * We will record the node value that gives the min diff during the traversal. Since a tree may have several representations, we can't have early
 * return value unless diff is 0. After we have done the BST tree following rules above, we just return the node we record as result
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Feb 25, 2016 12:03:10 PM
 */

public class Closest_Binary_Search_Tree_Value_p270_sol1 {
    public int closestValue(TreeNode root, double target) {
        //Tree traversal solution. We just go through the tree based on the comparison between root and target
        //we will record the tree value in the traversal which gives the min diff. Finally we just return it
        //Notice: we can't early return result except for the case that diff = 0. Due to the property of BST, if root is already very 
        //close to target, it is not necessaily the solution must come from itself/left/right. The target node may located in left tree
        //or right tree but not root's immediate child
        
        //the problem states there is guaranteed a valid solution, so root must not be null
        int result = root.val;
        
        while(root != null){
            //if diff is 0, then we just return immediately
            if(root.val == target) return root.val;
            
            //if we found current node gives a smaller diff, then we update result variable
            if(Math.abs(result - target) > Math.abs(root.val - target)) result = root.val;
            
            //then go to next node based on comparsion
            root = root.val > target? root.left : root.right;
        }
        
        return result;
    }
}
