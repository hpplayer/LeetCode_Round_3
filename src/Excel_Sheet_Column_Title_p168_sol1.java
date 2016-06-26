/*
168. Excel Sheet Column Title

Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
*/

/**
 * Math solution
 * 
 * Inputs are 26-hex and starting from 1
 * To convert it to our 0-based math world, we need deduct 1 from input in each manipulation. So that we can 
 * handle digits properly(like n -1 == 26, then we will get an extra digit ahead, while n -1 < 26, we can 
 * just transfer it to Title, similar to n = 10 vs n < 9)
 * 
 * Then use %26 to get value in curr index and use /26 to reduce input until input < 1, which means <= 0 in 0-based
 * math world
 * 
 * Time complexity: O(N), where N = n/26
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jun 25, 2016 6:47:07 PM
 */
public class Excel_Sheet_Column_Title_p168_sol1 {
    public String convertToTitle(int n) {
        //Math solution, we need convert base 
        
        StringBuilder sb = new StringBuilder();
        
        while(n >= 1){
            //since we read input from right to left, we will build the string from right to left too
            //deduct 1 from n to convert to 0 based index 
            
            sb.insert( 0, (char) ( (n-1)%26 + 'A')  );   
            
            n = (n-1)/26;
        }
        
        return sb.toString();
    }
}
