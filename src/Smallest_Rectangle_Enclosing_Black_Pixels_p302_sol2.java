
public class Smallest_Rectangle_Enclosing_Black_Pixels_p302_sol2 {
	public static void main(String[] args){
		char[][] image = {{'0', '0', '1', '0'}, {'0', '1', '1', '0'},{'0', '1', '0', '0'}};
		System.out.println(new Smallest_Rectangle_Enclosing_Black_Pixels_p302_sol2().minArea(image, 0, 2));
	}
	
    public int minArea(char[][] image, int x, int y) {
        left = right = y;
        top = bot = x;
        
        getRow(image, x, y);
        System.out.println(top);
        System.out.println(bot);
        getCol(image, x, y);
        
        return (right - left) * (bot - top);
    }
    
    int top, bot;
    int left, right;
    
    public void getRow(char[][] image, int x, int y){
        //get left first
        int start = 0, end = x;
        while(start < end){
            int mid = (start + end) /2;
            int k = 0;
            while(k < image[0].length && image[mid][k] == '0') k++;
            if(k == image[0].length){
                //white, go right
                start = mid + 1;
            }else{
                //black, go left
                end = mid;
            }
        }
        top = start;
        //get right second
        start = x + 1;
        end = image.length - 1;
        while(start < end){
            int mid = (start + end) /2;
            int k = 0;
            while(k < image[0].length && image[mid][k] == '0') k++;
            if(k == image[0].length){
                //white, go right
                start = mid + 1;
            }else{
                //black, go left
                end = mid;
            }            
        }
        System.out.println(start);
        bot = start;
    }
    
   public void getCol(char[][] image, int x, int y){
        //get left first
        int start = 0, end = y;
        while(start < end){
            int mid = (start + end) /2;
            int k = 0;
            while(k < image.length && image[k][mid] == '0') k++;
            if(k == image.length){
                //white, go bot
                start = mid + 1;
            }else{
                //black, go top
                end = mid;
            }
        }
        
        top = start;
        
        //get right second
        start = y + 1;
        end = image[0].length - 1;
        while(start < end){
            int mid = (start + end) /2;
            int k = 0;
            while(k < image.length && image[k][mid] == '0') k++;
            if(k == image.length){
                //white, go right
                start = mid + 1;
            }else{
                //black, go left
                end = mid;
            }            
        }
        end = start;
    }    
}
