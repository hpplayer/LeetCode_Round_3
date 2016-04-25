import java.util.*;

/*
You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number
of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0].
*/

/**
 * Divide and Conquer solution
 * 
 * Here is the basic idea:
 * We just do merge sort on the input array. During the merge step, we merge the array backward. For each value from left part, we compare
 * it with corresponding value from right part, if it is larger than right part, then we add index(right) to the count(left), which means
 * there are index(right) number of values in right part is smaller than left value. So with merging backward, we can recursively update 
 * the count[] without extra effort. For values before current value in left part, we don't need to worry about them as their numbers have
 * already been counted before we put them there, i.e. we have scanned them in down layers before.
 * 
 * The smaller numbers on the right of a number are exactly those that jump from its right to its left during the merge sort.
 * So in our solution, we only update count[] when we found smaller numbers are from right side (curr val in left[] > curr val in right[])
 * 
 * Remark:
 * 1) This problem is similar to problem Count_of_Range_Sum_p327_sol1
 * 2) The main idea is still a standard merge sort solution so the time complexity would be O(NLogN)
 * 
 * @author hpPlayer
 * @date Jan 24, 2016 10:46:59 PM
 */

public class Count_of_Smaller_Numbers_After_Self_p315_sol1 {
    public List<Integer> countSmaller(int[] nums) {
        //use merge sort to sort the input array.
        //we can count the smaller number during the merge process
        //In other words we can count the number of nums that we need to put before current value during the merge step
        
        MyNode[] nodes = new MyNode[nums.length];
        for(int i = 0; i < nodes.length; i++) nodes[i] = new MyNode(i, nums[i]);
        
        int[] count = new int[nums.length];
        
        mergeSort(nodes, count);
        
        List<Integer> result = new ArrayList<Integer>();
        for(int c : count) result.add(c);
        
        return result;
    }
    
    
    public MyNode[] mergeSort(MyNode[] nodes, int[] count){
        //boundary case, include < case to cover empty[] case
    	if(nodes.length <= 1) return nodes;
        
        int mid = nodes.length / 2;
        
        MyNode[] left = mergeSort(Arrays.copyOfRange(nodes, 0, mid), count);
        MyNode[] right = mergeSort(Arrays.copyOfRange(nodes, mid, nodes.length), count);

        
        //begin merge process backward (from large to small)
        //count the num of smaller values will be a free gift from backward merge step without extra step
        
        //to make count accurate, we will let index start with length(remember real size always be 1 larger than index)
        int left_index = left.length;
        int right_index = right.length;

        for(int i = nodes.length-1; i >= 0; i--){
            if(right_index == 0 || (left_index > 0 && left[left_index-1].val > right[right_index-1].val)){
                //we need place val from left[] to nodes[]. Since we scan the array from large to small, if 
                //current left[left_index] > right[right_index], then we can guarantee all values before right_index
                //will also be smaller than left[left_index], so we can update count[left] accordingly
                
                //We also need to be careful with index, since left_index start with left.length, we need -1 to get the current index 
                count[left[--left_index].idx] += right_index;
                nodes[i] = left[left_index];
            }else{
                //we need place val from right[] to nodes[]. We do nothing to count[], since no larger values found for left[left_index]
                nodes[i] = right[--right_index];
            }
        }
        
        
        return nodes;
    }
    public class MyNode{
        //we need a special MyNode class to store the index and value of input
        //so after we sort the array we still know the original index of sorted value
        int idx;
        int val;
        
        MyNode(int i, int v){
            idx = i;
            val = v;
        }
    }
    
	public static void main(String[] args){
		/* large test 
		int sampleNum = 10000;
		int[] nums = new int[test];
		Random rand = new Random();
		
		for(int i = 0; i < test; i++){
			nums[i] = rand.nextInt(1000) + 0;
		}
		
		
		long tStart = System.currentTimeMillis();
		System.out.println(new Count_of_Smaller_Numbers_After_Self_p315_sol1().countSmaller(nums));
		long tEnd = System.currentTimeMillis();
		
		long tDelta = tEnd - tStart;
		double elapsedSeconds = tDelta / 1000.0;
		System.out.println(elapsedSeconds);
		*/
		
		int[] nums = {5, 2, 6, 1};
		System.out.println(new Count_of_Smaller_Numbers_After_Self_p315_sol1().countSmaller(nums));
		
	}
}
