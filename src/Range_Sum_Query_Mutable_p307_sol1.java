import java.util.Arrays;

public class Range_Sum_Query_Mutable_p307_sol1 {
	public static void main(String[] args){
		Range_Sum_Query_Mutable_p307_sol1 test = new Range_Sum_Query_Mutable_p307_sol1(new int[]{1,3,5} );
		test.sumRange(0, 2);
	}
    //Segment tree solution (array version)
    //We can use segment tree to organize sums of subarray, and only update affected sums.
    
    //use array to represent segment tree
    int[] tree;
    //use n to store the len of array
    int n;
    
    public Range_Sum_Query_Mutable_p307_sol1(int[] nums) {
        n = nums.length;
        tree = new int[n * 2];
        buildTree(nums);
    }
    
    public void buildTree(int[] nums){
        //we will build the tree bottom-up, therefore we firstly fill the right part of tree[], which contains leaf nodes
        for(int i = n, j = 0; i < 2 * n; j++, i++){
            tree[i] = nums[j];
        }
        //then we will build tree layer by layer. Inside each layer, we will build the node from right to left
        //so overall the tree[] is updated from right to left
        for(int i = n-1; i >= 0; i--){
            //For each two child node, we will build a parent node in mid, therefore if two child nodes are 2i and 2i +1, then the root node will be i
            //we let left child to be even index and right child to be odd index
            tree[i] = tree[i*2] + tree[i*2 + 1];
        }
    }
    
    void update(int i, int val) {
        //we will update the segment tree bot-up, therefore we firstly update the leaf node
        i += n;
        tree[i] = val;
        
        //then update all affected ancestor nodes
        //array[0] does not represent any node(think about case that we have only one node in segment tree while the array still has len 2) 
        while(i >= 1){
            //define left and right child
            int left = i;
            int right = i;
            //based on index i, we will find if it is left or right child, therefore we can find the other child
            if( (i&1) == 0){
                //i is left child, then i + 1 is right
                right++;
            }else{
                //i is right child, then i - 1 is left
                left--;
            }
            
            //update parent node
            tree[i/2] = tree[left] + tree[right];
            //update index
            i/=2;
        }
    }

    public int sumRange(int i, int j) {
        //the given i and j defines the search range in leaf layer, then we can just get the sum by scanning the tree bot-up
        int sum = 0;
        i += n;
        j += n;
        
        //we will update sum inside the look therefore we need include i == j case 
        while(i <= j){
            //if i is right child of a subtree, then we will stop going up in this subtree, we just add i to sum, and move to its right sibling tree
            if( (i&1) == 1 ){
                sum += tree[i];
                i++;
            }
            
            //if j is left child of a subtree, then we will stop going up in this subtree, we just add j to sum, and move to its left sibling tree
            if( (j&1) == 0){
                sum += tree[j];
                j--;
            }
            
            //otherwise we continue going up to the parent node whose value contains sum of curr subtree
            i /= 2;
            j /=2;
        }
        
        return sum;
    }
}
