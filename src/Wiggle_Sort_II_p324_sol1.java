import java.util.Arrays;
import java.util.Random;
<<<<<<< HEAD

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
=======

/**
 * input: 	1 1 1 4 5 6
 * 
 * after process:
 * virtual: 	 0 1 2 3 4 5
 * real:	     1 3 5 0 2 4
 * odd numbers followed by even numbers, so after sort
 * we can put larger number in ahead, which will be assigned to odd numbers
 * we can put smaller number in back, which will be assigned to even numbers
 * as for numbers = median, they will be assigned together in virtual array, but they will be non-adjacency when converted to real
 * array cuz adjacent cells in virtual array have space 1
 * 
 * val(w/o sort):1 4 6 1 1 5
 * val(w sort):  1 1 1 6 5 4
 * 
 * then in real array, we will get:
 * 6 1 5 1 4 1
 * 
 * @author hpPlayer
 * @date Feb 14, 2016 11:21:04 PM
 */

public class Wiggle_Sort_II_p324_sol1 {
	public static void main(String[] args){
		//System.out.println( (10|1) );
		int[] nums = {1,5,1,1,6,4};
		//int[] nums = {10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
		new Wiggle_Sort_II_p324_sol1().wiggleSort(nums);
		System.out.println(Arrays.toString(nums));
	}
	
	   public void wiggleSort(int[] nums) {
	        int i = 0, j = 0, k = nums.length- 1;
	        len = nums.length;
	        		
	        int mid = findMedian(nums);
>>>>>>> origin/master
	        
	        while( j <= k){
	            if(nums[A(j)] > mid){
	                swap( nums, A(i++), A(j++)  );
	            }else if( nums[A(j)] < mid){
	                swap( nums, A(j), A(k--)  );
	            }else{
	                j++;
	            }
	        }
<<<<<<< HEAD
	        System.out.println(System.nanoTime() - start);
=======
	        
>>>>>>> origin/master
	    }
	    
	    int len;
	    
	    public int A(int index){
	        return (1 + 2 * index) % (len | 1);
	    }
	    
	    public int findMedian(int[] nums){
<<<<<<< HEAD
	    	
	        int n = nums.length - 1;
	        /*
	        Random rand = new Random();
	        for(int i = n; i > 0; i--){
	            int index = rand.nextInt(i+1);
	            swap(nums, index, i);
	        }
	        */
=======
	        int n = nums.length - 1;
	        
	        
>>>>>>> origin/master
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
<<<<<<< HEAD
	                end--;
=======
	                end --;
>>>>>>> origin/master
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
