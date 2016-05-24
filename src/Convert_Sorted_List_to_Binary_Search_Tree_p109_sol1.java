/*
109. Convert Sorted List to Binary Search Tree

Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
*/

/**
 * InOrder-traversal solution
 * 
 * We read input list from left to right. It matches the order if we build the target tree using in-order traversal.
 * In this way each node in input list will be visited only once.
 * 
 * Time complexity:O(N)
 * Space complexity: O(N)
 * 
 * Remark:
 * 1. We can also use fast and slow pointer to get the mid node, but in this way, we will visit same node several times. 1st time to find the mid node,
 * then revisit it when we look at that range again. This node may appears in multiple ranges, therefore we will visit this same node several times
 * 
 * @author hpPlayer
 * @date May 24, 2016 1:29:29 PM
 */
public class Convert_Sorted_List_to_Binary_Search_Tree_p109_sol1 {
    ListNode curr;
    public TreeNode sortedListToBST(ListNode head) {
        //inorder-traversal solution. We visit the input list from left to right and build target tree with inorder-traversal order.
        //In this way each node in input list will be visited only once
        
        //boundary check
        if(head == null) return null;
        
        curr = head;
        //use 0-based so that our final size will be real size - 1, which is correct initial value for right
        int size = 0;
        while(head.next != null){
            head = head.next;
            size++;
        }
        
        return DFS(0, size);
    }
    
    public TreeNode DFS(int left, int right){
        //boundary case
        //we will treat left == right as a special case inside general case, so we can move curr pointer correctly 
        if(left > right){
            return null;
        }
        
        int mid = left + (right - left)/2;
        
        //Using in-order traversal to build the tree, so we can read each node in input list only once
        TreeNode l = DFS(left, mid - 1);
        
        TreeNode root = new TreeNode(curr.val);
        curr = curr.next;    
        
        TreeNode r = DFS(mid + 1, right);
        
        root.left = l;
        root.right = r;
        
        return root;
    }
}
