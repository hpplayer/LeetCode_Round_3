/*
223. Rectangle Area

Find the total area covered by two rectilinear rectangles in a 2D plane.

Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.

Rectangle Area
Assume that the total area is never beyond the maximum possible value of int.
*/

/**
 * Observation solution
 * 
 * The tricky part is dealing with the shared part.
 * The shared part may be the overlapping part, or one of the input rectangle, or may not we don't have shared part at all!
 * 
 * Since we don't know the location and size of input rectangles, we use math.max() and math.min() to get the corner
 * values. For overlapped part, we can use Math.min(right/top values) - Math.max(left/bot values) to get the axis of left-bot and right-top values.
 * However doing this way will let us miss the case that we don't have shared part at all.
 * We can imagine that two rectangle were initially overlapped, then use max() and min() to get the corner axis would
 * be correct. Then we start pulling two rectangles apart horizontally, now left boundary of shared part will approach
 * right boundary of shared part. Finally left boundary will be on the right side of its original right boundary.
 * In that case min() - max() will output an negative value. To cover this case, we can just use an extra Math.max()
 * to reset right boundary value to left boundary boundary value, then when we do right - left, we will get 0 for 
 * such case
 * 
 * Time complexity: O(1)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jun 1, 2016 11:08:34 PM
 */
public class Rectangle_Area_p223_sol1 {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        //Observation value, use max() and min() to help us get the correct order of axis
        
        //shared part
        int left = Math.max(A, E);
        //for right boundary, we use an extra Math.max(left, right) to catch the case that two rectangles are
        //actually separated
        int right = Math.max( left, Math.min(C, G) );
        int bot = Math.max(B, F);
        //for top boundary, we use an extra Math.max(bot, top) to catch the case that two rectangles are
        //actually separated
        int top = Math.max(bot, Math.min(D, H));
        
        //result = area of recA - shared part + recB 
        return (C-A) * (D-B) - (top - bot) * (right - left) + (G-E) * (H-F); 
        
    }
}
