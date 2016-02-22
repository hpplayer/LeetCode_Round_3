/**
 * pre-order traversal
 * 
 * The main body of this solution is similar with sol1, but now we ask DFS to return maxLen as return value, so we can avoid use global variable
 * For each node, the max path may end here, or somewhere in the left or right subtree. So when we return the maxLen value, we need compare three
 * values
 * 
 * time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Feb 21, 2016 3:59:14 PM
 */
public class Binary_Tree_Longest_Consecutive_Sequence_p298_sol2 {
    public int longestConsecutive(TreeNode root) {
        //null input, return 0
        if(root == null) return 0;
        
        //otherwise return the max count 
        
        return Math.max(DFS(root.left, root, 1), DFS(root.right, root, 1));
    }
    
    public int DFS(TreeNode curr, TreeNode prev, int currLen){
        //reach boundary and return current Len
        if(curr == null) return currLen;
        
        currLen = curr.val == prev.val + 1? currLen + 1 : 1;
        
        int left = DFS(curr.left, curr, currLen);
        int right = DFS(curr.right, curr, currLen);
        
        //max path may end in curr Node or its subtree, so have to compare three variables
        return Math.max(currLen, Math.max(left, right));
    }
}
