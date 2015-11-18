/**
 * HashMap + two pointer solution
 * 
 * This solution is similar to sol1, but we use string to replace hashMap
 * 
 * Time complexity: O(n)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Nov 17, 2015 10:47:09 PM
 */
public class Strobogrammatic_Number_I_p246_sol2 {
    public boolean isStrobogrammatic(String num) {
        int start = 0, end = num.length()-1;
        
        //we need to check each char in input
        while(start <= end){
            //Since num of pairs is countable, we use below string to replace hashMap
            if(!"11#00#88#696".contains(num.charAt(start) + "" + num.charAt(end))) return false;
            start++;
            end--;
        }
        
        return true;
    }
}
