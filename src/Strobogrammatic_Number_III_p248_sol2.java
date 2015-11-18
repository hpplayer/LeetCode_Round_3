import java.util.*;
/**
 * Iterative backtracking solution
 * 
 * This solution is similar to Strobogrammatic_Number_II_p247_sol2
 * 
 * But now we need check the result list and increase the count if the string is valid 
 * 
 * @author hpPlayer
 * @date Nov 18, 2015 1:52:04 AM
 */
public class Strobogrammatic_Number_III_p248_sol2 {
    public int strobogrammaticInRange(String low, String high) {
        //similar idea with Strobogrammatic_Number_III_p248_sol1
        //but now we generate Strobogrammatic numbers iteratively
        
        count = 0;
        min = low;
        max = high;
        
        //check each length between len(low) and len(high) and increase the count if generated num is valid
        for(int i = low.length(); i <= high.length(); i++){
            helper(i);    
        }
        
        return count;
    }
    
    int count;
    String min;
    String max;
    
    public void helper(int n){
        //use a double-end queue to make generation more easily
        Deque<String> deq = new LinkedList<String>();
        
        if( (n&1) == 1){
            //odd length
            deq.addAll(Arrays.asList(new String[]{"0", "1", "8"}));
            n--;
        }else{
            //odd length
            deq.add("");
        }
        
        //we use int[][] to make the code more clean
        int[][] base = { {0, 0}, {1, 1}, {8, 8}, {6, 9}, {9, 6} };
        
        while(n > 0){
            //since we need check boundary condition later, we must firstly decrease n!!!!!!!!!!!!!!!!!!!
            n -= 2;
            
            int size = deq.size();
            //poll old string from first, add new string into last
            for(int i = 0; i < size; i++){
                String temp = deq.pollFirst();
                //create new string, in this solution, we generate new string from mid to two sides, so we can't 
                //add 0s if n == 0 where we have reached the boundary of two side
                for(int j = n == 0? 1 : 0; j < base.length; j++){
                    deq.offerLast(base[j][0] + temp + base[j][1]);
                }
            }
        }
        
        //finally check result strings, if it is valid then we increase count accordingly
        for(String temp : deq){
            //too small
            boolean rej1 = temp.length() == min.length() && temp.compareTo(min) < 0;
            //too large
            boolean rej2 = temp.length() == max.length() && temp.compareTo(max) > 0;
            
            //if not too small nor too large, it is a valid number, then we will increase the count
            if(!rej1 && !rej2){
                count++;
            }
        }
    }
}
