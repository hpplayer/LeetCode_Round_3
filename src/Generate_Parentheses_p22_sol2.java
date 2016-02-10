import java.util.*;

/**
 * DP solution
 * 
 * In this solution, we build the target list from bottom to top. We starts with "", then add "()" each time.
 * Suppose now we are looking at i layer, i.e. i pairs of "()"
 * We will look all combinations that gives i - 1 ()s, then add them to two positions.
 * ( 1 ) 2 there are two positions that we can add combinations. 
 * We don't add combination before new (), so there will be no duplicate, and we will always get new combination
 * 
 * Time complexity: O(n^3)
 * 
 * @author hpPlayer
 * @date Feb 10, 2016 7:49:25 PM
 */
public class Generate_Parentheses_p22_sol2 {
	public static void main(String[] args){
			System.out.println(generateParenthesis(3));
	}
	
   public static List<String> generateParenthesis(int n) {
        //dp solution, we fix the new ( ) in front, then there are two positions to add old combinations
        //(1)2, we cannot add old combinations before new (), otherwise we will get duplicate like: ()()
        
        //In this solution, we build result from 0  to n, for each layer, we try all possible sub-combinations and add them to position 1 and 2 as indicated above
        List<List<String>> dp = new ArrayList<List<String>>();
        
        //base case, a list contains ""
        dp.add(Arrays.asList(""));
        
        for(int i = 1; i <= n; i++){
            List<String> temp = new ArrayList<String>();
            
            for(int j = 0; j < i; j++){
                //try all possible sub-combinations and add them to position 1 and 2
                for(String left : dp.get(j)){
                    //new () count as 1 pair, then we need search sub-comnbinations to compose i - 1 pair
                    for(String right : dp.get(i-1-j)){
                        temp.add("(" + left +")" + right);
                    }
                }
            }
            
            dp.add(temp);
        }
        
        return dp.get(n);
    }
}
