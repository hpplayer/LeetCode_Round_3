/*
 * 

302. Smallest Rectangle Enclosing Black Pixels

An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel.
he black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically.
Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

For example, given the following image:

[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2,
Return 6.
*
*/



/**
 * Naive DFS solution
 * 
 * 
 * Here is the basic idea:
 * This problem can be simply solved by naive DFS solution where we just need to find the leftmost/rightmost/topmost/botmost black bars, then 
 * calculate the area. 
 * 
 * Remark:
 * We got from code are indexes so we need + 1 when calculating area.
 * The time complexity is O(mn)
 * 
 * Sol2 is the binary search solution which is much faster.
 * 
 * @author hpPlayer
 * @date Jan 27, 2016 11:05:58 PM
 */
public class Smallest_Rectangle_Enclosing_Black_Pixels_p302_sol1 {
	public static void main(String[] args){
		char[][] image = {{'0', '0', '1', '0'}, {'0', '1', '1', '0'},{'0', '1', '0', '0'}};
		System.out.println(new Smallest_Rectangle_Enclosing_Black_Pixels_p302_sol1().minArea(image, 0, 2));
	}
    public int minArea(char[][] image, int x, int y) {
        
    	minX = maxX = x;
    	minY = maxY = y;
    	image[x][y] = '2';
    	checkArea(image, x, y);
        
        return (maxX - minX + 1) * (maxY - minY +1);
    }
    
    int minX , maxX;
    int minY, maxY;
    
    public void checkArea(char[][] image, int x, int y){
        int[] Xoffset = {1, -1, 0, 0};
        int[] Yoffset = {0, 0, 1, -1};
        
        for(int i = 0; i < 4; i++){
            int newX = Xoffset[i] + x;
            int newY = Yoffset[i] + y;
            
            if(newX >= 0 && newX < image.length && newY >= 0 && newY < image[0].length && image[newX][newY] == '1'){
                minX = Math.min(minX, newX);
                maxX = Math.max(maxX, newX);
                minY = Math.min(minY, newY);
                maxY = Math.max(maxY, newY);
                image[x][y] = '2';
                checkArea(image, newX, newY);
            }
        }
        
    }
}
