/*
343. Integer Break

Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers.
Return the maximum product you can get.

For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).

Note: you may assume that n is not less than 2.

Hint:

There is a simple O(n) solution to this problem.
You may check the breaking results of n ranging from 7 to 10 to discover the regularities.
*/

/**
 * Math solution
 * 
 * We observe that all max products are from the product of 2 and 3.
 * Why is that?
 * Firstly lets focus on factors >= 4, we call it n. Then we have:
 * n = (n-2) + 2. The product of this is (n-2) * 2 = 2n - 4
 * if n >= 4, then we have 2n - 4 >= n. That means if a number has a factor n, then we can replace this factor with 2 and n - 2 to get a larger product.
 * We recursively do this to get larger product until n < 4. 
 * We have 1, 2, 3 that are positive and smaller than 4. For 1,2,3 we can just hard-code their max product which is intuitive
 * 
 * We observe that for n > 4, we should use as many 3 as possible to replace 2s
 * Why is that?
 * For 2, 3, 1, we wouldn't use 1, as (n-1) * 1 gives a smaller product than n but for n >= 4, we can use (n-2) * 2 to get a product > n
 * we noticed that when n > 4, we can use 2 3s to replace 3 2s to get a larger product:
 * Here we list some n > 4, and decompose them into product of ony 2s and 3s
 * 5: 2 * 3, one 2 one 3, no changes
 * 6: 2 * 2 * 2 vs 3 * 3, we use 3^2 to replace 2^3
 * 7: 2 * 2 * 3, we do nothing
 * 8: 2 * 2 * 2 * 2 vs 3 * 3 * 2 
 * 9: 2 * 2 * 2 * 3 vs 3 * 3 * 3 
 * ..
 * So we wouldn't have three 2s in max product, if we have, then we will replace it with two 3s to get a larger product
 * 
 * So the basic idea is that we iteratively take 3s from input, until n <= 4.
 * 
 * Time complexity: O(1) as integer max range is fixed.
 * Space complexity: O(1)
 * 
 * Remark:
 * This problem can also be solved by DP solution where we cached all prev calculated results, and parse on all factors. But that would cause
 * extra space and O(n^2) time which is not good as this Math solution. So I did not list it here
 * 
 * @author hpPlayer
 * @date Jun 7, 2016 12:33:23 PM
 */
public class Integer_Break_p343_sol1 {
    public int integerBreak(int n) {
        //Math solution, we just greedily take 3 from n and add it to the product. We iteratively do this until n <= 4
        
        //boundary case, we hard-coded them
    	//Since we have to decompose input at least once, we have to decompose them which will give a smaller value than itself
    	//However, if we have decomposed the input, and have those boundary values in the remaining input, then we can just leave it as it is
    	//So that we can get a larger product
        //for 1, the maxProduct is 0, for 2, the maxProduct is 1, for 3 the maxProduct is 2 
        if(n < 4) return n - 1;
        
        int maxProduct = 1;
        
        while(n > 4){
            //we iteratively take 3 from input and add to product
            maxProduct *= 3;
            n -= 3;
        }
        
        //for the remaining n, we dont need to further decompose it, since all nums < 4 have max product < num.
        //Therefore, differed from boundary case above where we have to decompose them once, here we use use those remaining n as it is
        return maxProduct * n; 
    }
}
