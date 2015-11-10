/**
 * Peak problem
 * 
 * Here we use a variable to hold the len of descending part. Since we only require higher ranking child has 1 more candy than its neighbor
 * child, we can use the length of descending part to calculate the candy requirement for descending part. We also need use another variable
 * "peak" to record the candy requirement for previous cell or the candy requirement for the cell before the descending part. So we can set
 * candy requirement for current cell accordingly. In the end, we may have a corner case that the trailing sequence is descending as well,
 * so we need an extra check in the end to cover such case 
 * 
 * Running time: O(n)
 * Space complexity: O(1)
 * 
 * 
 * @author hpPlayer
 * @date Nov 9, 2015 4:07:47 PM
 */
public class Candy_p135_sol2 {
    public int candy(int[] ratings) {
        //we use a variable to track descending sequence so we can avoid two-pass scan
        
        //boundary check
        if(ratings.length == 0) return 0;
        
        //the len of descending sequnce
        //that start of desLen is actually from the second cell in descending part
        int desLen = 0;
        int result = 1;
        
        //peak is the candy we need to have for prev cell
        int peak = 1;
        
        for(int i = 1; i < ratings.length; i++){
            if(ratings[i] < ratings[i-1]){
                //if it is descending sequence
                desLen ++;
            }else{
                //non descending sequence, where we need to update the result
                if(desLen > 0){
                    //if we have descending part ahead, we need calcualte the result from descending part
                    
                    //we assign the lowest point (index: i - 1) to have one candy 1
                    //and add 1 more candy for each previous cells in descending part
                    //so we can get the candy required for descending part
                    result += (1 + desLen) * desLen /2;
                    
                    //We start increase desLen in the second cell in descending part
                    //The first cell is the ending cell for previous non-descending sequence, and the value
                    //is already included in result. But now we need to check if we need to update the first cell
                    //value as well, since we may have higher requirement from the descending part, i.e. the backwawrd direction
                    
                    //desLen is the requirement for the second cell, so the first cell should have desLen + 1 candy
                    if(desLen + 1 > peak) result += desLen + 1 - peak;
                    
                    //we finish the calculation in descending part, reset variables
                    
                    //reset peak to be 1
                    peak = 1;
                    desLen = 0;
                }
                
                //update peak
                peak = (ratings[i] == ratings[i-1])? 1 : peak + 1;
                
                //update result
                result += peak;
            }
        }
        
        //since we always calcualte the result in non-descending part, we may have the case that trailing sequence
        //is also a descending part, so we need cover this case as well
        
        if(desLen > 0){
            result += (1 + desLen) * desLen /2;
            if(desLen + 1 > peak) result += desLen + 1 - peak;
        }
        
        return result;
    }
}
