/**
 * Recursive pretorder & inorder traversal
 * 
 * We use preorder[] to build nodes, and use inorder[] to build the tree
 * Similar to Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal_p106_sol1, but now we have preorder[] instead of postorder[], and we will read
 * it forward since the root of tree is the head cell.
 * After found the root node, then we will search the inorder[] to find the size of left subtree and right subtree so we can determine next search range
 * in preorder[] 
 * 
 * 
 * Time complexity: recursion, masters theorem, but the num of recursion is the num of subtrees we have, which would be O(N), and each recursion, we will
 * scan the inorder[] and find the target root index. In worst, the root may be in the rightmost index in inorder[], which means this tree only have left
 * subtrees. Therefore the time complexity is around O(N*N)
 * 
 * Space complexity: same as above, but may be O(N) at most
 * 
 * Sol1 is a recursive solution, and will always build look at a whole tree in the array, then break it down smaller subtrees, so it is top-down
 * Sol1 is more intuitive but slower
 * 
 * Sol2 is an iterative solution that will look at subtrees, then build a larger tree, so it is down-top
 * Sol2 is more complicated but faster
 * 
 * @author hpPlayer
 * @date Mar 14, 2016 4:29:13 PM
 */
public class Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal_p105_sol1 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //preorder + inorder recursive solution, similar to Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal_p106_sol1
        //We still create nodes from preorder and build tree from inorder, but this time we will read preorder[] forward
        return DFS(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }
    
    public TreeNode DFS(int[] preorder, int a, int b, int[] inorder, int c, int d){
        //boundary check
        if(a > b) return null;
        if(a == b) return new TreeNode(preorder[a]);
        
        //root of curr tree is leftmost cell
        TreeNode curr = new TreeNode(preorder[a]);
        
        //find the tree structure from inorder[]
        
        //search in inorder[] range and find the subtree size
        int index = c;
        for(; index <= d; index++){
            if(inorder[index] == preorder[a]) break;
        }
        
        //With the root index and left & right boundary of current tree in inorder[]
        //we can determine the size of left and right subtree
        int leftSize = index - c;
        
        //Based on the size of left subtree, we can get the next search range in preorder[]
        //In preorder[] the tail index of left subtree would be left index (a + 1) + size(leftSize) - 1 = a + leftSize
        curr.left = DFS(preorder, a + 1, a + leftSize, inorder, c, index - 1);
        //In preorder[] the head index of right subtree would be left index(a + 1) + size(leftSize) = a + 1 + leftSize
        curr.right = DFS(preorder, a + leftSize + 1, b, inorder, index + 1, d);
        
        return curr;
    }
}
