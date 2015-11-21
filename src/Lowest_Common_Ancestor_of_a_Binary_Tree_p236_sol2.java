import java.util.*;

public class Lowest_Common_Ancestor_of_a_Binary_Tree_p236_sol2 {
   class MyNode{
        boolean isVisited;
        MyNode parent;
        TreeNode node;
        List<TreeNode> result = new ArrayList<TreeNode>();
        
        MyNode(TreeNode node, MyNode parent){
            this.parent = parent;
            this.node = node;
        }
        
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        MyNode dummy = new MyNode(null, null);
        MyNode RootNode = new MyNode(root, dummy);
        Stack<MyNode> stack = new Stack<MyNode>();
        stack.push(RootNode);
        
        
        while(!stack.isEmpty()){
            MyNode curr = stack.peek();
            TreeNode node = curr.node;
            MyNode parent = curr.parent;
            
            if(node == null || node == p || node == q){
                parent.result.add(node);
                stack.pop();
            }else if(!curr.isVisited){
                curr.isVisited = true;
                stack.push(new MyNode(node.right, curr));
                stack.push(new MyNode(node.left, curr));
            }else if(curr.isVisited){
                TreeNode left = curr.result.get(0);
                TreeNode right = curr.result.get(1);
                if(left != null && right != null){
                    parent.result.add(node);
                }else if(left != null){
                    parent.result.add(left);
                }else if(right != null){
                    parent.result.add(right);
                }else{
                    parent.result.add(null);
                }
                stack.pop();
            }
        }
        
        return dummy.result.get(0);
    }
}
