/**
 * Binary search solution
 * 
 * 
 * Here is the basic idea:
 * 
 * We use the given index as the split point and use binary search to find the leftmost/rightmost/topmost/botmost black bars
 * We firstly search the topmost and botmost black bars. During the binary search, we need search each whole row to make sure this is not the 
 * boundary row that contains first/last black bar. So the time complexity would be NlogM where n is length of row and M is how many rows we got.
 * Then we search the leftmost and rightmost black bars, the main idea is similar to first part. 
 *  
 * 
 * Remark:
 * The time complexity is O(NlogM + MlogN)
 * 
 * @author hpPlayer
 * @date Jan 28, 2016 10:39:40 PM
 */


public class Smallest_Rectangle_Enclosing_Black_Pixels_p302_sol2 {
	public static void main(String[] args){
		char[][] image = {{'0', '0', '1', '0'}, {'0', '1', '1', '0'},{'0', '1', '0', '0'}};
		System.out.println(new Smallest_Rectangle_Enclosing_Black_Pixels_p302_sol2().minArea(image, 0, 2));
	}
	
    public int minArea(char[][] image, int x, int y) {
    	searchBoundaryRows(image, 0, x, true);
    	searchBoundaryRows(image, x+1, image.length - 1, false);
    	searchBoundaryCols(image, 0, y, true);
    	searchBoundaryCols(image, y+ 1, image[0].length - 1, false);
    	
    	//since top/bot/left/right are all indexes, we need + 1 to calcualte the size
    	return (right - left + 1) * (bot - top + 1);
    }
    
    int top, bot, left, right;
    
    public void searchBoundaryRows(char[][] image, int start, int end, boolean isTop){
        while(start <= end){
            int mid = (start + end) /2;
            int index = 0;
            while(index < image[0].length && image[mid][index] == '0') index++;
            
            if(index == image[0].length){
            	//if we are looking for top boundary and image[mid] is white row, then we need move down
            	start = isTop? mid + 1 : start;
            	//if we are looking for bot boundary and image[mid] is white row, then we need move up
            	end = isTop? end : mid - 1;
            }else{
            	//if we are looking for bot boundary and image[mid] is black row, then we need move down
            	start = isTop? start : mid + 1;
            	//if we are looking for top boundary and image[mid] is black row, then we need move up
            	end = isTop? mid - 1 : end;
            }
        }
        
        //since we exit while loop when start > end, and we move "end" to - 1 even the row is black, so "start" pointer is pointing to the first black row
        if(isTop) top = start;
       //since we exit while loop when start > end, and we move "start" to + 1 even the row is black, so "end" pointer is pointing to the last black row
        if(!isTop) bot = end; 
    }  
    
    public void searchBoundaryCols(char[][] image, int start, int end, boolean isLeft){
        while(start <= end){
            int mid = (start + end) /2;
            int index = 0;
            while(index < image.length && image[index][mid] == '0') index++;
            
            if(index == image.length){
            	//if we are looking for left boundary and image[mid] is white col, then we need move right
            	start = isLeft? mid + 1 : start;
            	//if we are looking for right boundary and image[mid] is white col, then we need move left
            	end = isLeft? end : mid - 1;
            }else{
            	//if we are looking for right boundary and image[mid] is black col, then we need move right
            	start = isLeft? start : mid + 1;
            	//if we are looking for left boundary and image[mid] is black col, then we need move left
            	end = isLeft? mid - 1 : end;
            }
        }
        
        //since we exit while loop when start > end, and we move "end" to - 1 even the row is black, so "start" pointer is pointing to the first black col
        if(isLeft) left = start;
       //since we exit while loop when start > end, and we move "start" to + 1 even the row is black, so "end" pointer is pointing to the last black col
        if(!isLeft) right = end; 
    }  
    
    
}
