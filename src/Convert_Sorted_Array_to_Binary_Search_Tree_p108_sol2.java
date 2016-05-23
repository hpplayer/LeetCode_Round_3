import java.util.Stack;

/**
 * Iterative DFS solution
 * 
 * We use a stack and an inner class to build the tree from input array iteratively. To avoid duplicate calculation
 * of mid index, we store mid index in inner class as well. Besides, inner class also includes indexes of 
 * left and right boundary and corresponding TreeNode
 * 
 * Time complexity: O(N)
 * Space complexity: O(lgN) as the max depth is lgN and we build the tree based on depth
 * 
 * @author hpPlayer
 * @date May 23, 2016 12:41:40 AM
 */
public class Convert_Sorted_Array_to_Binary_Search_Tree_p108_sol2 {
    public TreeNode sortedArrayToBST(int[] nums) {
        //iterative DFS solution. Using stack and inner class to build the tree iteratively
        
        //boundary check
        if(nums.length == 0) return null;
        
        int mid = (nums.length - 1)/2;
        TreeNode root = new TreeNode(nums[mid]);
        
        Stack<MyNode> stack = new Stack<MyNode>();
        stack.push(new MyNode(0, nums.length - 1, mid, root));
        
        while(!stack.isEmpty()){
            MyNode curr = stack.pop();
            
            //we only build trees for valid range
            
            if(curr.left < curr.mid){
                //if left range is valid
                
                //get mid index in left range
                int m = (curr.left + (curr.mid - 1))/2;
                TreeNode left = new TreeNode(nums[m]);
                curr.node.left = left;
                //continuously explore the left range
                stack.push(new MyNode(curr.left, curr.mid - 1, m, left));
            }
            
            if(curr.right > curr.mid){
                //if right range is valid
                
                //get mid index in right range
                int m = (curr.right + (curr.mid + 1))/2;
                TreeNode right = new TreeNode(nums[m]);
                curr.node.right = right;
                //continuously explore the right range
                stack.push(new MyNode(curr.mid + 1, curr.right, m, right));
            }
        }
        
        return root;
    }
    
    private class MyNode{
        int left;
        int right;
        //we also include mid variable here to avoid duplicate calculation of mid index
        int mid;
        TreeNode node;
        MyNode(int l, int r, int m, TreeNode n){
            left = l;
            right = r;
            mid = m;
            node = n;
        }
    }
}
