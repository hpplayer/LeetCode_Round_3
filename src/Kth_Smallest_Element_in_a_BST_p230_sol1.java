/*
230. Kth Smallest Element in a BST

Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
You may assume k is always valid, 1 ¡Ü k ¡Ü BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

Hint:

Try to utilize the property of a BST.
What if you could modify the BST node's structure?
The optimal runtime complexity is O(height of BST).
*/

/**
 * In-order traversal
 * 
 * In this solution, we use a recursive in-order traversal to visit the BST. we let return result be the target node's val and use a global count
 * to track how many small nodes we have accumulated. 
 * 
 * Time complexity: O(n)
 * Space complexity: O(n)
 * 
 * Follow up: 
 * we modify the node definition and add a variable to count how many nodes are there in left subtree. If we found k is smaller than this num, then
 * we will search in left subtree, otherwise we will search (k - num)th nodes in right subtree
 * This method should give us a time complexity of O(H), where H is the height of BST
 * 
 * Sol2 uses a similar idea but in iterative way
 * @author hpPlayer
 * @date Feb 15, 2016 11:07:38 PM
 */
public class Kth_Smallest_Element_in_a_BST_p230_sol1 {
    int count = 0;
    public int kthSmallest(TreeNode root, int k) {
        //recursive version of in-order traversal
        //in-order traversal will exacetly return nodes from small->large in BST
        //we use a global count to track how many nodes we have visited
        
        //boundary check
        if(root == null) return 0;
        
        int left = kthSmallest(root.left, k);
        
        //check if left subtree already give us the kth element in BST
        if(count == k) return left;
        
        //check if current node is the kth elemetn in BST
        if(++count == k) return root.val;
        
        //otherwise return the search result from right tree
        return kthSmallest(root.right, k);
    }
}
