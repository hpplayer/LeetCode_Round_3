/**
 * Preorder traversal + observation solution
 * 
 * The basic idea of this solution is similar to sol1, but here we make use of input array.
 * Since after we are done with a subtree we will no longer need it, we can make use those part of input array to
 * be a stack. Unvisited part will not be affected, visited part will be updated as a stack
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jun 4, 2016 4:25:21 PM
 */
public class Verify_Preorder_Sequence_in_Binary_Search_Tree_p255_sol2 {
    public boolean verifyPreorder(int[] preorder) {
        //Preorder traversal + observation solution. Basic idea is similar to sol1, but now we make use of visited
        //part of input array and use it as a stack.
        
        int lower_bound = Integer.MIN_VALUE;
        int index = 0;
        
        for(int num : preorder){
            
            //found invalid input
            if(num < lower_bound) return false;
            
            //check if we have done a subtree with curr num
            //our back search starts with index - 1, ends at root node of whole subtree
            while(index > 0 && preorder[index-1] < num ){
                lower_bound = preorder[index-1];
                index--;
            }
            
            //if num is root node in right subtree, then we have updated index to be the root cell
            //preorder[index] is used to contain root node, now we update it to be curr node
            //if num is still a node in left subtree, then index would be same with its original index
            //the below code will keep it say still
            preorder[index++] = num;
        }
        
        return true;
    }
}
