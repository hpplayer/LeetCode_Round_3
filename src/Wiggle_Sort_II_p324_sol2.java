import java.util.Arrays;
/**
 * Naive Sort solution
 * 
 * We firstly make a copy of input, then sort it
 * Then we read it from large to small,  when reading large half, we will assign those values to odd indexes
 * when reading small half, we will assign those values to even indexes
 * 
 * Is it possible that we can do reversely? Read from small to large, then assign even indexes first?
 * The answer is no, in this way we will have:
 * 
 * small->large(->median)
 * 		small(->median)->large
 * we can see that two numbers that are close to median may be put together
 * 
 * However in this solution we will have:
 *   	large->small(->median)
 * large(->median)-> small
 * we can see that two numbers that are close to median are not together 		
 * 
 * an example is [4, 5, 5, 6].
 * 
 * Time complexity: O(NlogN) due to sort
 * Space complexity: O(N) since we need an extra array to store numbers
 * 
 * @author hpPlayer
 * @date Feb 15, 2016 2:52:40 PM
 */
public class Wiggle_Sort_II_p324_sol2 {

	public static void main(String[] args){
		int[] nums = {1, 5, 1, 1, 6, 4};
		new Wiggle_Sort_II_p324_sol2().wiggleSort(nums);
		System.out.println(Arrays.toString(nums));
	}
	
    public void wiggleSort(int[] nums) {
        //firstly make a copy
    	int[] copy = Arrays.copyOf(nums, nums.length);
        //then sort the array
        Arrays.sort(copy);
        int index = nums.length - 1;
        
        //firstly we assign larger numbers to odd numbers
        for(int i = 1; i < nums.length; i+=2){
        	nums[i] = copy[index--];
        }
        
        //secondly we assign smaller numbers to even numbers
        for(int i = 0; i < nums.length; i+= 2){
        	nums[i] = copy[index--];
        }
    }
}
