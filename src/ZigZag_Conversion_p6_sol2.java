import java.util.Arrays;

/**
 * Three-pointer solution
 * 
 * In this solution we will firstly create a zig-zag graph like the one in problem description, then we build 
 * the final string by reading graph row by row.
 * 
 * To generate the zig-zag graph, we need three pointers. One pointer reads the char from input string,
 * one pointer indicates the row that we are currently at, and one pointer represents the increment that we used
 * to get next row number. When going down in each col, the increment should be +1, while going up between
 * two cols, the increment should be -1
 * 
 * To make the solution more convenient we use string to represent chars in each row. 
 * 
 * Time complexity: O(2N), where N is len of input. Each char will be read twice
 * Space complexity: O(2N), since we need a O(N) graph and a O(N) stringBuilder
 * 
 * This solution is slower and more space-consuming than sol1, but is more intuitive and does not involve math
 * and observation
 * 
 * @author hpPlayer
 * @date Apr 10, 2016 10:43:21 PM
 */
public class ZigZag_Conversion_p6_sol2 {
    public String convert(String s, int numRows) {
        //three-pointer solution. In this approach, we will firstly fill the grap and put char into correct place
        //then use an extra loop to build the final string
        
        //boundary check
        if(numRows <= 1 || numRows >= s.length()) return s;
        
        //We will use a string to store chars in each row. So the graph will be represented by a string array
        //where string in each cell represents chars in this row
        String[] rows = new String[numRows];
        //intialize string[]
        Arrays.fill(rows, "");
        
        //curr row number
        int row = 0;
        //increment to get next row number
        int step = 1;
        
        for(int i = 0; i < s.length(); i++){
            rows[row] += s.charAt(i);
            
            //reach top row
            if(row == 0) step = 1;
            //reach bot row
            if(row == numRows - 1) step = -1;
            
            //use step to get next row
            row += step;
        }
        
        //use an extra loop to generate final string
        StringBuilder sb = new StringBuilder();
        for(String str : rows) sb.append(str);
        
        return sb.toString();
    }
}
