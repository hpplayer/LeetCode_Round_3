/*
108. Convert Sorted Array to Binary Search Tree

Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
*/

/**
 * Recursive DFS solution
 * 
 * We push build range to array recursively, then start building the tree node during the backtracking
 * 
 * This kind of array-tree and range-push solution can be found in many problems like:
 * Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal_p106_sol1
 * Unique_Binary_Search_Trees_II_p95_sol1
 * Validate_Binary_Search_Tree_p98_sol1
 * 
 * Time complexity: O(N)
 * Space complexity: O(lgN) as the max depth is lgN and we build the tree based on depth
 * 
 * Sol1 is the recursive solution
 * Sol2 is the iterative solution
 * 
 * @author hpPlayer
 * @date May 23, 2016 12:24:50 AM
 */
public class Convert_Sorted_Array_to_Binary_Search_Tree_p108_sol1 {
    public TreeNode sortedArrayToBST(int[] nums) {
        //recursive DFS solution. Push ranges to next DFS and start build tree during backtracking
        return DFS(0, nums.length - 1, nums);
    }
    
    public TreeNode DFS(int left, int right, int[] nums){
        //boundary check
        if(left > right) return null;
        if(left == right) return new TreeNode(nums[left]);
        
        //create root, then using DFS to build left and right subtree
        int mid = left + (right - left)/2;
        TreeNode root = new TreeNode(nums[mid]);
        
        root.left = DFS(left, mid - 1, nums);
        root.right = DFS(mid + 1, right, nums);
        
        return root;
    }
}
