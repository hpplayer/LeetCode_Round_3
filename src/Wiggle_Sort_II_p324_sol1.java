import java.util.Arrays;

public class Wiggle_Sort_II_p324_sol1 {
	public static void main(String[] args){
		System.out.println( (10|1) );
		int[] nums = {2,1, 2,3};
		new Wiggle_Sort_II_p324_sol1().wiggleSort(nums);
		System.out.println(Arrays.toString(nums));
	}
    public void wiggleSort(int[] nums) {
    	int[] copy = Arrays.copyOf(nums, nums.length);
    	int index = copy.length - 1;;
    	
    	Arrays.sort(copy);
    	
    	for(int i = 1; i <nums.length; i+=2){
    		nums[i] = copy[index--];
    	}
    	
    	for(int i = 0; i < nums.length; i+=2){
    		nums[i] = copy[index-- ];
    	}
    	
    }
}
