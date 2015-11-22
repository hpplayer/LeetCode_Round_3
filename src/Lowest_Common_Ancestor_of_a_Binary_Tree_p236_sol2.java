import java.util.*;
/**
 * Iterative version of sol1
 * 
 * We use an extra class (MyNode) to create such a node that having parent pointer
 * 
 * We use a list in MyNode to hold result from children nodes.
 * If both children nodes are not null, then current node must be LCA
 * If one of the children nodes are not null, we just keep return this node
 * If both nodes are null, then we return null
 * 
 * Remark:
 * We can't simply check if a node has two non-null children results to say if this is LCA or not.
 * This is because we may have a such case that a node itself is one of the target nodes, while the other target node is a 
 * children of it 
 * 
 * @author hpPlayer
 * @date Nov 21, 2015 11:41:10 PM
 */
public class Lowest_Common_Ancestor_of_a_Binary_Tree_p236_sol2 {
    class MyNode{
        //use isVisited to control the flow of traversal
        boolean isVisited;
        TreeNode node;
        //have pointer points to parent
        MyNode parent;
        //have children list, so we can know info from nodes below
        List<TreeNode> children = new ArrayList<TreeNode>();
        
        MyNode(TreeNode node, MyNode parent){
            this.node = node;
            this.parent = parent;
        }
        
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //iterative version of post-order traversal, but we need parent pointer
        
        //boundary check
        if(root == null) return null;
        
        //dummyNode will be the parent of MyRoot, therefore it will contain information from root Node, so we can check the information below root node
        MyNode dummyNode = new MyNode(null, null);
        
        MyNode MyRoot = new MyNode(root, dummyNode);
        
        Stack<MyNode> stack = new Stack<MyNode>();
        stack.push(MyRoot);
        
        while(!stack.isEmpty()){
            //post-order traversal, we will whether pop or hold currNode in stack based on whether we have finished
            //its children nodes
            MyNode currNode = stack.peek();
            
            if(currNode.node == null || currNode.node == p || currNode.node == q){
                //boundary condition, need turn back
                currNode.parent.children.add(currNode.node);
                stack.pop();
            }else if (!currNode.isVisited){
                //new Node that not have been visited, we need visit its children nodes
                //visit left first, so we need push left on top of stack
                currNode.isVisited = true;
                stack.push(new MyNode(currNode.node.right, currNode));
                stack.push(new MyNode(currNode.node.left, currNode));
            }else{
                //visited node, we have done its children nodes, so lets handle different cases
                stack.pop();
                if(currNode.children.get(0) != null && currNode.children.get(1) != null){
                    //both children are not null, current node will be LCA
                    currNode.parent.children.add(currNode.node);
                }else if(currNode.children.get(0) != null){
                    //one children not null, return this node
                    currNode.parent.children.add(currNode.children.get(0));
                }else if(currNode.children.get(1) != null){
                    //one children not null, return this node
                    currNode.parent.children.add(currNode.children.get(1));     
                }else{
                    //return null
                    currNode.parent.children.add(null);
                }
            }
        }
        
        return dummyNode.children.get(0);
    }
}
