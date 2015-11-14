import java.util.*;
/*
Candy

There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?
*/

/**
 * Scanning the array forward and backward solution
 * 
 * For each cell, it has requirement from previous cells and later cells. So we just use a count[] to record the requirement for
 * each cell. Then we scan the array forward and update the requirement from previous cells. After that, we scan the array backward,
 * and update the requirement from later cells. Finally we just sum number in each cell and it will be our solution
 * 
 * This algorithm runs in O(n) time 
 * and costs O(n) space
 * 
 * Sol1 is a solution using a temp table with two pass algorithm
 * Sol2 is a solution using two temp variable with one pass algorithm but is hard to come up with
 * 
 * Solution Trapping_Rain_Water_p42_sol1 also uses similar approach that scanning the array forward and backward
 * 
 * @author hpPlayer
 * @date Nov 9, 2015 3:33:02 PM
 */
public class Candy_p135_sol1 {
    public int candy(int[] ratings) {
        int len = ratings.length;
        //use an array to record the requirement of each cell from previous cells and later cells 
        int[] count = new int[len];
        
        //Initialize the array, each cell will at least get 1 candy
        Arrays.fill(count, 1);
        
        //lets get the requirements for each cell from previous cells by scanning the array forward
        for(int i = 1; i < len; i++){
            //if current cell has higher rank than previous cell, it will get more candy than previous cell
            if(ratings[i] > ratings[i-1]) count[i] = count[i-1] + 1;
        }
        
        //lets get the requirements for each cell from later cells by scanning the array backward
        for(int i = len - 2; i >=0 ; i--){
        	 //if current cell has higher rank than later cell, it will get more candy than later cell
        	//since it may get an even higher requirement from forward sequence, we need use Math.max() here
            if(ratings[i] > ratings[i+1]) count[i] = Math.max(count[i], count[i+1]+1);
        }
        
        //finally, sum the count
        int result = 0;
        for(int num : count){
            result += num;
        }
        
        return result;
    }
}
