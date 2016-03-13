
public class Largest_BST_Subtree_p333_sol1 {
    private class MyNode{
        int min, max, localSize, globalSize;
        boolean isValidSubTree;
        MyNode(int mn, int mx, int ls, int gs, boolean isValid){
            min = mn;
            max = mx;
            localSize = ls;
            globalSize = gs;
            isValidSubTree = isValid;
        }
        
    }
    
    public int largestBSTSubtree(TreeNode root) {
       return DFS(root).globalSize;
    }
    
    public MyNode DFS(TreeNode root){
    	if(root == null) return new MyNode(Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 0, true);
    	
    	MyNode left = DFS(root.left);
    	MyNode right = DFS(root.right);
    	
    	int localSize = 0;
    	boolean isValid = false;
    	
    	if(root.val > left.max && root.val < right.min && left.isValidSubTree && right.isValidSubTree){
    		isValid = true;
    		localSize = left.localSize + 1 + right.localSize;
    	}
    	
    	int min = Math.min(root.val, left.min);
    	int max = Math.max(root.val, right.max);
    	int globalSize = Math.max(localSize, Math.max(left.globalSize, right.globalSize));
    	
    	return new MyNode(min, max, localSize, globalSize, isValid);
    }
}
