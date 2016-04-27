/*
9. Palindrome Number

Determine whether an integer is a palindrome. Do this without extra space.

click to show spoilers.

Some hints:
Could negative integers be palindromes? (ie, -1)

If you are thinking of converting the integer to string, note the restriction of using extra space.

You could also try reversing an integer. However, if you have solved the problem "Reverse Integer",
you know that the reversed integer might overflow. How would you handle such case?

There is a more generic way of solving this problem.
*/

/**
 * Math solution
 * 
 * We compare digits from head and tail to check if the input is palindrome or not. We use mathematical ways
 * to get digits and adjust input. Basically, we need to use % and / operations to achieve that.
 * Here we use /10 and %divisor to get the head and tail digits. So we need to get the divisor first before 
 * we can actually compare digits.
 * 
 * Time complexity: O(1) as input len is fixed
 * Space complexity: O(1) as we don't need extra space
 * 
 * @author hpPlayer
 * @date Apr 26, 2016 9:48:30 PM
 */
public class Palindrome_Number_p9_sol1 {
	public static void main(String[] args){
		Palindrome_Number_p9_sol1 test = new Palindrome_Number_p9_sol1();
		System.out.println(test.isPalindrome(121));
	}
    public boolean isPalindrome(int x) {
        //math solution. To avoid usage of extra space, we will compare digits from head and tail one by one.
        //To get tail digit, we just use %10. To get head digit, we need make use of divisor. We create a divisor
        //that correspond to input, then use /divisor to get the first digit
        
        //Here we define all negative inputs to be false
        if(x < 0) return false;
        
        int divisor = 1;
        
        //we don't want change input, so we will only update divisor and use x/divisor to check where to stop
        //we will let divisor has same len with input, default divisor has len 1, therefore our loop will keep
        //going until we get x/divisor < 10, where their length are same
        
        while(x/divisor >= 10){
            divisor *= 10;
        }
        
        //then we will compare digits in head and tail. we will stop comparison when x == 0. 
        //why not stop when we have only 1 digit left like 0 < x < 10? Thats because single digit does not necessary
        //means we only have one digit left to be compared. We may have case that 10000021, where we need to compare
        //heading zeros with non-zero digits, if we continue the loop, we will find left = 0 and right = some digits
        //so we should not stop the loop until x == 0, which really means there are no more digits need to be compared
        
        //we have filtered negative inputs above, so we are safely to use x > 0 here
        while(x > 0){
            int left = x / divisor;
            int right = x % 10;
            if(left != right) return false;
            
            //remove first digit and last digit in input
            x = (x%divisor)/10;
            //reduce divisor by 2 times
            divisor /= 100;
        }
        
        return true;
    }
}
