/**
 * Binary Indexed Tree
 * 
 * In this solution, we build a BIT based on input.
 * BIT is built based on the binary representation of index. The root node of BIT is a dummy node which has 
 * index 0 and it does not store any value. 
 * 
 * To get the parent node, we need use n -= n & -n. (used in querySum)
 * To get the next node, we need use n += n & -n. (used in update)
 * 
 * In this problem we use an array to represent the tree. To make things more convenient, our indexes in BIT[]
 * start with 1, and we do not store values in cell 0. So we need convert input index to BIT index by adding 1
 * 
 * Time complexity: 
 * Build Tree O(NlogN)
 * Update Tree O(logN)
 * SumQuery O(logN)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Apr 21, 2016 11:05:46 PM
 */
public class Range_Sum_Query_Mutable_p307_sol2 {
	public static void main(String[] args){
		Range_Sum_Query_Mutable_p307_sol2 test = new Range_Sum_Query_Mutable_p307_sol2(new int[]{1,3,5});
		System.out.println(test.sumRange(0, 2));
		test.update(0, 3);
		System.out.println(test.sumRange(0, 2));
	}
	
    //using BIT to update and querySum from input
    
    int[] BIT;
    int[] input;
    int n;    
    
    public Range_Sum_Query_Mutable_p307_sol2(int[] nums) {
        n = nums.length;
        input = nums;
        //we use an array to represent BIT, the array has len n + 1, cell 0 will be used as dummy cell, which means
        //we will only update cells starts from 1
        BIT = new int[n+1];
        buildTree();
    }

    public void buildTree(){
        for(int i = 0; i < n; i++){
            int j = i + 1;
            //update is from top to bottom left to right
            while(j <= n){
                //some cells may already have updated values
                BIT[j] += input[i];
                j += (j & -j); 
            }
        }
    }

    void update(int i, int val) {
        int diff = val - input[i];
        int j = i + 1;
        //we need update initial cell which may be used later
        input[i] = val;
        while(j <= n){
            //same as we initialize the BIT[]
            BIT[j] += diff;
            j += (j & -j);
        }
    }

    public int sumRange(int i, int j) {
        //getSum will get sum from 0 to index i or j
        //to get the sum in range i and j, we need get sum in i - 1 and j, then get the diff
        return getSum(j) - getSum(i-1);
    }
    
    public int getSum(int i){
        int j = i + 1;
        int sum = 0;
        //sumQuery is from bot to top from right to left
        while(j > 0){
            sum += BIT[j];
            j -= (j & -j);
        }
        return sum;
    }
}
//Your NumArray object will be instantiated and called as such:
//NumArray numArray = new NumArray(nums);
//numArray.sumRange(0, 1);
//numArray.update(1, 10);
//numArray.sumRange(1, 2);