/**
 * Tree Traversal solution
 * 
 * In this sol2, we make use of the len of leftmost path in left and right subtrees.
 * If lens are same, then left subtrees must be perfect, right subtrees may be imperfect (our input may be a 
 * perfect tree as well), so we can safely use 2^h - 1 to get the size of left subtree then jump to right subtree
 * If lens are different, then right subtrees must be perfect and left subtrees must be imperfect, so we can 
 * safely use 2^h - 1 to get the size of right subtree then jump to left subtree
 * 
 * Time complexity: O(H), our loop will be executed O(H) times
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jun 1, 2016 10:21:43 PM
 */
public class Count_Complete_Tree_Nodes_p222_sol2 {
    public int countNodes(TreeNode root) {
        //Tree traversal solution, Make use len of leftmost path in left and right subtrees
        
        int result = 0;
        
        while(root != null){
            //get the lef of leftmost path in left subtree
            int left = getLenLeftPath(root.left);
            //get the lef of leftmost path in right subtree
            int right = getLenLeftPath(root.right);
            
            if(left == right){
                //if left == right, then left tree must be perfect tree, use 2^h - 1 to get the size
                //since we need to count curr root as well we will add 1 to result, so 2^h - 1 + 1 = 2^h
                result += (1 << left);
                //go to right subtree
                root = root.right;
            }else{
                //if left != right, then right tree must be perfect tree, use 2^h - 1 to get the size
                //since we need to count curr root as well we will add 1 to result, so 2^h - 1 + 1 = 2^h
                result += (1 << right);
                //go to left subtree
                root = root.left;                
            }
        }
        
        return result;
    }
    
    public int getLenLeftPath(TreeNode root){
        //get the len of leftmost path
        int len = 0;
        while(root != null){
            root = root.left;
            len++;
        }
        return len;
    }
}
