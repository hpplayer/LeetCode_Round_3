/*
6. ZigZag Conversion

The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
(you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
*/

/**
 * Math + observation solution
 * 
 * Lets decompose the input into general graph:
 * assume numRows = n, then:
 * 				
 * 1							2n-1 <- we get 2n-1 here since we will jump n-1 rows from row "n" to here
 * 2					  2n-2  2n
 * 3				  2n-3 		2n+1		
 * .				.			.
 * .			  . 			.
 * .			.				.
 * n-2		n+2					3n-4
 * n-1  n+1						3n-3
 * n							3n-2 <- we get 3n-2 here since we will jump n-1 rows from row "2n-1" to here
 * 
 * From above graph, we observe several rules:
 * 1) there is a fixed len gap between each two col, the fixed len is 2n-2
 * 2) Each fixed len gap can be split into two smaller gaps, the left gap is shrinking and right gap is expanding
 * when we moving up
 * 3) the len of two smaller gaps can be calculated, the right gap can be got from i*2, where is row number (0 based)
 * the left gap can be got from deducting the right gap from the fixed len gap: 2n-2-2i
 * 4) there are two boundary conditions where first row does not have right gap and last row does not have left gap
 * 
 * So here is the basic idea:
 * We will build the result string row by row. In each row, we will make use of math equations to calculate 
 * the index of next char from the input, then we append next char to the result string. We iteratively doing
 * this until we reach the boundary of input.
 * 
 * Time complexity: O(N), where N is len of input since each char from the input will be visited once
 * Space complexity: O(N), we need finally build a string with N len
 * 
 * Sol1 is math+observation solution
 * Sol2 is more intuitive three-pointer solution,  it is slower and more space-consuming than sol1,
 * but is more intuitive and does not involve mathn and observation
 * 
 * @author hpPlayer
 * @date Apr 10, 2016 9:59:39 PM
 */
public class ZigZag_Conversion_p6_sol1 {
    public String convert(String s, int numRows) {
        //Math + observation approach, we observe that the gap between each two cols can be split into two
        //smaller gaps, and we can use equations to calculate the len of two gaps
        
        //boundary check
        if(numRows <= 1 || numRows >= s.length()) return s;
        
        //we will build final string row by row and using a stringBuilder to catch strings from each row
        StringBuilder sb = new StringBuilder();
        
        for(int row = 0; row < numRows; row++){
            //index is the pointer that we used to get all chars at current row from input string
            //the initial index will be set to the row number
            int index = row;
            
            //append first char in curr row
            sb.append(s.charAt(index));
            
            //the len of two small gaps will be fixed to each row, so we compute them here
            
            //the len of right gap is just 2 * row number 
            int gap2 = 2 * row;
            
            //the len of left gap will be the len of whole gap - gap2
            //whole gap between 2 columns will be 2n - 2 (assume head of curr col is 1.
            //one col has n rows, to get head of next col, we need jump back n - 1 rows,
            //therefore head of next col will be 2n-1, so the len of whole gap is 2n-1-1 = 2n-2)
            //i.e gap1 = (n + n - 1) - 1 - gap2
            //where (n + n -1) is whole gap, 1 is index of the head of first col.
            int gap1 = 2*numRows-2 - gap2;
            
            while(index < s.length()){
                index += gap1;
                
                //we need do condition check before we add the char
                //we wouldn't have gap1 in last row, also we wouldn't allow index >= s.length()
                if(row != numRows-1 && index < s.length()){
                    sb.append(s.charAt(index));
                }
                
                index += gap2;
                //we need do condition check before we add the char
                //we wouldn't have gap2 in first row, also we wouldn't allow index >= s.length()
                if(row != 0 && index < s.length()){
                    sb.append(s.charAt(index));
                }                
            }
  
        }        
        
        return sb.toString();
        
    }
}
