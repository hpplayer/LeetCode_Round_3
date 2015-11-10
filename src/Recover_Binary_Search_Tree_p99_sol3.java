/**
 * Morris-traversal solution
 * 
 * Similar to iterative in-order traversal, but we build a back edge between rightmost node in left tree and root before we 
 * visiting the left tree. So we can go back to the root node done the left tree. Then we start visit left tree, after we reach
 * the rightmost node in left tree again, we can go back to the root node, then we locate the rightmost node in left tree again,
 * and cut off the back edge, and start visiting the right tree
 * 
 * The detection of incorrect is similar to other two solutions
 * 
 * Time complexity: O(n)
 * Space compleixty: O(1)
 * 
 * 
 * @author hpPlayer
 * @date Nov 9, 2015 5:29:13 PM
 */
public class Recover_Binary_Search_Tree_p99_sol3 {
    public void recoverTree(TreeNode root) {
    	//Morris-traversal
    	
        TreeNode first = null;
        TreeNode second = null;
        
        TreeNode pred = null; //rightmost node in left tree
        TreeNode prev = null; 
        
        TreeNode curr = root;
        
        while(curr != null){
            //for each node, we compare it with prev node as we did in in-order-traversal
            if(prev != null && curr.val <= prev.val){
                if(first == null) first = prev;
                second = curr;
            }
            
            if(curr.left != null){
                //got left tree, then let's locate its rightmost node in left tree
                pred = curr.left;
                //we may have visited the left tree before, and connect the rightmost node with curr node (root node)
                while(pred.right != null && pred.right != curr){
                    pred = pred.right;
                }
                
                if(pred.right == curr){
                    //if this left tree has been visited before, then we are done with it
                    //cut the connection with currNode and start visit curr's right tree
                    pred.right = null;
                    prev = curr;
                    curr = curr.right;
                }else{
                    //if this left tree has not been visited before, then we create a back edge from rightmost node
                    // to curr node, so we can return to the start point after done the left tree
                    pred.right = curr;
                    curr = curr.left;
                }
                
            }else{
                //no left tree, then just visit its right tree
                prev = curr;
                curr = curr.right;
            }
        }
        
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}
