import java.util.Arrays;
import java.util.Random;

public class Wiggle_Sort_II_p324_sol1 {
	public static void main(String[] args){
		int[] nums = {1, 1, 2, 1, 2, 2, 1};
		new Wiggle_Sort_II_p324_sol1().wiggleSort(nums);
		System.out.println(Arrays.toString(nums));
	}
	   public void wiggleSort(int[] nums) {
	        int i = 0, j = 0, k = nums.length- 1;
	        len = nums.length;
	        
	        long start = System.nanoTime();
	        
	        int mid = findMedian(nums);
	        System.out.println(System.nanoTime() - start);
	        
	        start = System.nanoTime();
	        
	        while( j <= k){
	            if(nums[A(j)] > mid){
	                swap( nums, A(i++), A(j++)  );
	            }else if( nums[A(j)] < mid){
	                swap( nums, A(j), A(k--)  );
	            }else{
	                j++;
	            }
	        }
	        System.out.println(System.nanoTime() - start);
	    }
	    
	    int len;
	    
	    public int A(int index){
	        return (1 + 2 * index) % (len | 1);
	    }
	    
	    public int findMedian(int[] nums){
	    	
	        int n = nums.length - 1;
	        /*
	        Random rand = new Random();
	        for(int i = n; i > 0; i--){
	            int index = rand.nextInt(i+1);
	            swap(nums, index, i);
	        }
	        */
	        int IndexOfmedian = n / 2, start = 0, end = n;    
	        
	        while(true){
	            int index = quickSort(nums, start, end);
	            if(index == IndexOfmedian){
	                return nums[index];
	            }else if(index > IndexOfmedian){
	            	 end = index - 1;
	            }else if(index < IndexOfmedian){
	            	start = index + 1;
	            }
	        }
	    }
	    
	    public int quickSort(int[] nums, int start, int end){
	        int pivot = nums[start];
	        int index = start;
	        start++;
	        
	        while(start <= end){
	            if(nums[start] < pivot && nums[end] > pivot){
	                swap(nums, start, end);
	                start++;
	                end--;
	            }else if(nums[start] >= pivot){
	                start++;
	            }else if(nums[end] <= pivot){
	                end--;
	            }
	        }
	        
	        swap(nums, end, index);
	        return end;
	    }
	    
	    
	    
	    public void swap(int[] nums, int start, int end){
	        int temp = nums[end];
	        nums[end] = nums[start];
	        nums[start] = temp;
	    }
}
