import java.util.*;

/**
 * Iterative backtracking solution
 * 
 * In this solution we build new strings from middle to two sides. We repeatedly use the result list. We poll an old string from
 * list, then add two new digits in two sides and put new string back to the result list. In the boundary case, where we need add
 * add the last pair of digits, we have to skip 0s to avoid heading 0s issue.
 * 
 * Time complexity: not easy to analyze, but should be exponential
 * Space complexity: not easy to analyze
 * 
 * @author hpPlayer
 * @date Nov 18, 2015 12:05:01 AM
 */

public class Strobogrammatic_Number_II_p247_sol2 {
    public List<String> findStrobogrammatic(int n) {
        //iterative solution, we create string from middle to two sides
        
        //we will dynamically expanding string in result, so it is better to use a LinkedList
        LinkedList<String> result = new LinkedList<String>();
        
        if( (n&1) == 1){
            //odd length, only 0/1/8 can be middle Strobogrammatic number
            result.addAll(Arrays.asList(new String[]{"0", "1", "8"}));
            //len -1
            n--;
        }else{
            //even length
            result.add("");
        }
        
        //to make code more clean we use array to read Strobogrammatic numbers
        int[][] base = {{0, 0}, {1, 1}, {8, 8}, {6, 9}, {9, 6}};
        
        while(n > 0){
        	//since we need check boundary condition later, we must firstly decrease n!!!!!!!!!!!!!!!!!!!
            n-=2;
            
            int size = result.size();
            //we poll old string from first and offer new string to the last
            for(int i = 0; i < size; i++){
                String temp = result.pollFirst();
                //we build new string from middle to two sides, so if we are in the boundary case (two sides)
                //we won't add 0s in front and in back
                for(int j = n == 0? 1 : 0; j < base.length; j++){
                    result.offerLast(base[j][0] + temp + base[j][1]);   
                }
            }
        }
        
        return result;
    }
}
