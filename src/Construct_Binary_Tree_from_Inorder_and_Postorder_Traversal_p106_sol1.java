/*
106. Construct Binary Tree from Inorder and Postorder Traversal

Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
*/

/**
 * Post-order and in-order traversal
 * 
 * We recursively shrink the ranges in inorder[] and postorder[].
 * We use postorder[] to find the vale of node, and we use inorder[] to find the whole tree.
 * Each recursion will handle a whole tree. The root of the tree will be the last cell in the curr range in postorder[]
 * Then we search this cell in inorder[] to find the index. Then we can easily get the size of left subtree and right subtree of this tree
 * Then we shrink the search range in two arrays, and recursively build next subtree
 * 
 * We can do this because there is no duplicates in the tree. This condition is also required otherwise if all nodes have same value, then we wouldn't
 * be able to get the structure
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
 * @date Mar 14, 2016 12:00:58 PM
 */
public class Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal_p106_sol1 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        //we will recursively build the tree from postorder[], and use inorder[] to guide us check the structure
        return DFS(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1);
    }
    
    public TreeNode DFS(int[] postorder, int a, int b, int[] inorder, int c, int d){
        //boundary case
        if(a > b) return null;
        if(a == b) return new TreeNode(postorder[a]);
        
        //based on the inorder[], we will always split the postorder[] into a whole subtree, therefore the last cell in curr range
        //will be the root node of curr subtree
        TreeNode root = new TreeNode(postorder[b]);
        
        //find the root node in inorder[], so we can recursively split the tree into left and right subtrees
        //use i to find the index of cell in inorder[] that has same value with curr root val
        int i = c;
        for(; i <= d; i++){
            if(inorder[i] == postorder[b]) break;
        }
        
        //calculate the left size, we need left size to locate the boundary between left subtree and right subtree in postorder[]
        //of course we can also use right_size to achieve the same goal!!!!!!!!!!!!!
        int left_size = i - c;
        
        //build left subtree
        //In inorder[], i is index of root.val, the left subtree's range will be c to i-1, the right subtree's rang will be i + 1 to d
        //In postorder[], based on the left and right size, the left subtree's rang will be a to a + leftSize - 1, the right subtree's range will be a + leftSize - 1 + 1 to b - 1
        root.left = DFS(postorder, a, a + left_size - 1, inorder, c, i-1);
        root.right = DFS(postorder, a + left_size - 1 + 1, b - 1, inorder, i + 1, d);
        
        return root;    
    }
}
