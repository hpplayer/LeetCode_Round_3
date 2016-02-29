/**
 * Count Sort solution
 * 
 * This solution is based on the definition that H-index k means we have k papers have citation k and n - k papers have citations < k
 * Instead of scanning the citations[], we actually convert array to papers[], each cell's index is the citation value and its val means 
 * the papers it has. Therefore now we no longer need sort() function to sort the array, and we can get our solution within O(N) time
 * 
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Feb 20, 2016 11:16:40 PM
 */
public class H_Index_I_p274_sol2 {
    public int hIndex(int[] citations) {
        //count sort solution. Instead of using built-in sort function, now we build a new array with citation value
        //as the index, and assign each paper to its corresponding cell.
        //Citation values can vary, but our H-index will only have range from 0 to len, where 0 means no index meets
        //requirement while n means the the last index meets requirement, so we will create an array with n + 1 len
        //to include each possible H-index value. For citation val >= n + 1, we will assign them to last index
        //indicating index n can include their impacts
        //Notice: don't use graph to analyze this soltuion as we have modified the citation value based on the 
        //word definition so the graph will not be same as it should to be 
        
        int len = citations.length;
        
        //use len + 1 to include 0 to len possible H-indexes(both inclusive)
        int[] papers = new int[len + 1];
        
        //update papers array
        for(int citation : citations){
            if(citation >= len +1) papers[len]++;
            else papers[citation]++;
        }
        
        //scan the array backward so we can accumulate paper numbers to smaller citation val
        for(int i = len; i >= 0; i--){
            //accumulate papers when we are visiting last second and ahead cells
            if(i < len) papers[i] += papers[i+1];
            
            //based on the definition, if we found the max possible index that we have k papers with k citations,
            //then it would be our H-index
            //now if at index i we have papers[i] papers >= citations i, and it is the max index, then it must 
            //be the H-index
            if(papers[i] >= i) return i;
        }
        
        //can't find H-index, return -1
        //Theoretically, it is not possible
        throw new IllegalArgumentException("not possible");
    }
}
