import java.util.*;
/*
Max Points on a Line

Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
*/

/**
 * 1. Don't use division to get slope, otherwise we will have to deal with decimal numbers and may cause problems in precision
 * 2. Here we use diffX + "#" + diffY to mark slope. DiffX and DiffY are processed with GCD, so they will be in their simplest form.
 * We use Euclid's method to get GCD, then using GCD to process diffX and diffY
 * 3. Don't forget points that duplicate with start point. In such case, we will count it separately
 * 
 * @author hpPlayer
 * @date Nov 2, 2015 3:48:41 PM
 */

public class Max_Points_on_a_Line_p149_sol1 {
    public int maxPoints(Point[] points) {
        
        int max = 0;
        
        for(int i = 0; i < points.length; i++){
            //key is slope in the form of "diffX + "#" + diffY", value is number of lines with same slop and from same start point
            //reset hashMap for each new start point
            Map<String, Integer> hs = new HashMap<String, Integer>();
            
            //count duplicate start points
            int duplicates = 1;//set its inital val to 1 to include the inital start points
            int localMax = 0;
            //to avoid duplicate calculation, we only look points after current start point
            for(int j = i  + 1; j < points.length; j++){
            	Point a = points[i];
            	Point b = points[j];
                
                if(a.x == b.x && a.y == b.y){
                    duplicates ++;
                    continue;
                }
                
                int diffX = a.x - b.x;
                int diffY = a.y - b.y;
                int offset = GCD(diffX, diffY);
                
                //process diffX and diffY so we can get the simplest form of diffX and diffY
                //like "8 4" and "4 2" have same slope, after process, both of them will be "2 1"
                String key = diffX/offset + "#" + diffY/offset;
                if(!hs.containsKey(key)){
                    hs.put(key, 1);
                    localMax = Math.max(localMax, 1);
                }else{
                    int val = hs.get(key) + 1;
                    localMax = Math.max(val, localMax);
                    hs.put(key, val);
                }
            }
            
            //update global result
            max = Math.max(max, localMax + duplicates);
        }
        
        return max;
    }
    
    
    //Euclid method to get GCD
    public int GCD(int a, int b){
        while(b != 0){
            int temp = b;
            b = a%b;
            a = temp;
        }
        
        return a;
    }
}
