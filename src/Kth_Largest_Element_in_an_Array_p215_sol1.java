import java.util.*;

public class Kth_Largest_Element_in_an_Array_p215_sol1 {
	public static void main(String[] args){
		int[] nums = {2, 1, 3};

		System.out.println(new Kth_Largest_Element_in_an_Array_p215_sol1().findKthLargest(nums, 2));
	}
	
    public int findKthLargest(int[] nums, int k) {
        //sol1 quickSort solution. We use quickSort to sort the array meanwhile return result if we already found kth largest num
        int start = 0, end = nums.length - 1;
        
        while(true){
        	
            int p = quickSort(nums, start, end);
            if(p == k - 1){
                return nums[p];
            }else if (p > k - 1){
                end = p - 1;
            }else{
                start = p +1;
            }
        }
    }
    
    public int quickSort(int[] nums, int start, int end){
    	
        //for convenience, we pick the nums[start] as pivotal
        Random rand = new Random();
        int index = rand.nextInt(end - start + 1) + start;
        
        System.out.println(index);
        
        int pivotal = nums[index];
        
        nums[index] = nums[end];
        nums[end] = pivotal;
        System.out.println(Arrays.toString(nums));	
        end --;		
        
        while(start <= end){
            if(nums[start] < pivotal && nums[end] > pivotal){
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start ++;
                end --;
            }else if(nums[start] >  pivotal){
                start ++;
            }else if (nums[end] <= pivotal){
                end --;
            }
        }
        
        //System.out.println(start);
        nums[end + 1] = nums[start];
        nums[start] = pivotal;
        
        System.out.println(Arrays.toString(nums));	
        return start;
        
    }
}
