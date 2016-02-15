import java.util.Arrays;

public class Wiggle_Sort_II_p324_sol2 {

	public static void main(String[] args){
		int[] nums = {1, 1, 2, 1, 2, 2, 1};
		new Wiggle_Sort_II_p324_sol1().wiggleSort(nums);
		System.out.println(Arrays.toString(nums));
	}
	   public void wiggleSort(int[] nums) {
	        int i = 0, j = 0, k = nums.length- 1;
	        int len = nums.length;
	        
	        long start = System.nanoTime();
	        
	        int mid = findMedian(nums);
	        System.out.println(System.nanoTime() - start);
	        
	        start = System.nanoTime();
	   }
}
