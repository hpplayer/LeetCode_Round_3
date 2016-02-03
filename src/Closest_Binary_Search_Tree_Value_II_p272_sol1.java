import java.util.*;

public class Closest_Binary_Search_Tree_Value_II_p272_sol1 {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Stack<TreeNode> min = new Stack<TreeNode>();
        Stack<TreeNode> max = new Stack<TreeNode>();
        
        TreeNode curr = root;
        
        while(min.size() + max.size() < k){
            if(curr.val < target){
                max.push(curr);
                curr = curr.right;     
            }else if(curr.val > target){
                min.push(curr);
                curr = curr.left;
            }else{
                if(curr.left == null && curr.right == null){
                    curr = root.right;
                }else if(curr.left != null){
                    curr = curr.left;
                }else{
                    curr = curr.right;
                }
            }        
        }
        
        
        List<Integer> result = new LinkedList<Integer>();
        while(!min.isEmpty()){
            result.add(min.pop().val);
        }
        
        while(!max.isEmpty()){
            result.add(max.pop().val);
        }
        
        return result;
    }


}
