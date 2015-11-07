import java.util.*;
/**
 * Merge sort solution
 * 
 * We use merge sort to split and merge edges. We keep doing split until we are looking at one building, then we create two edges and add to list.
 * Here we use a trick to assign the height value. For left edge, we will assign the height to be normal value, so taller left edge will be added.
 * For right edge, we will assign the height to be 0, so we will avoid adding edge with same height twice (from same building), and now we will add
 * the height of another edge if we have a valid one.
 * During the merge, we will add the edge with smaller x-axis. We will always compare the current height from two lists, and only set new edge's height 
 * to be taller height.  If we found the list is empty or the last edge in the list has a different height, then we will add this new edge to result list
 * 
 * Time complexity: here we use a standard way to implement merge-sort, the time costs therefore would be O(nlogN).
 * Space complexity: O(n)
 * 
 * Remark:
 * Due to the overlap at same x-axis, we need to treat a.x = b.x as a special case in Merge(), otherwise we may add edge at same x-axis several times!!!!
 * @author hpPlayer
 * @date Nov 6, 2015 10:47:49 PM
 */
public class The_Skyline_Problem_p218_sol2 {
    public List<int[]> getSkyline(int[][] buildings) {
        //use merge sort to add edge to result list.
        return mergeSort(0, buildings.length-1, buildings);
    }
    
    public LinkedList<int[]> mergeSort(int left, int right, int[][] buildings){
        LinkedList<int[]> result = new LinkedList<int[]>();
        //boundary check
        if(left > right) return result;
        
        //if we are looking at a single house 
        if(left == right){
            int[] building = buildings[left];
            //for left boundary, we set height to be normal height, so taller left boundary will get update
            result.add(new int[]{building[0], building[2]});
            //for right boundary, we set height to be 0, so we will get the height of another building
            result.add(new int[]{building[1], 0});
            return result;
        }
        
        //general case, split in half, then merge
        int mid = left + (right - left)/2;
        
        return Merge(mergeSort(left, mid, buildings), mergeSort(mid+1, right, buildings));
    }
    
    public LinkedList<int[]> Merge(LinkedList<int[]> a, LinkedList<int[]> b){
        //merge two lists of edges
        LinkedList<int[]> result = new LinkedList<int[]>();
        
        //current height of each list
        int height_a = 0;
        int height_b = 0;
        
        while(!a.isEmpty() && !b.isEmpty()){
            //new_x and new_y are the x-axis and height of the poped edge
            int new_x = 0, new_y = 0;
            if(a.peekFirst()[0] < b.peekFirst()[0]){
                //if top edge in a has smaller x axis, then we will pop it out from a
                int[] building = a.pollFirst();
                height_a = building[1];
                new_x = building[0]; 
                new_y = Math.max(height_a, height_b);
            }else if(a.peekFirst()[0] > b.peekFirst()[0]){
                //if top edge in b has smaller x axis, then we will pop it out from b
                int[] building = b.pollFirst();
                height_b = building[1];
                new_x = building[0]; 
                new_y = Math.max(height_a, height_b);                
            }else{
                //same x axis, we will pop edge from both lists, and update new_y to be the taller edge
            	//In this solution, we need handle special case when x axis are same, otherwise we may add several height at one x axis multiple times!!!!
            	//just think about the heap solution, we also need to treat the a.x = b.x as a special case 
                int[] building1 = a.pollFirst();
                int[] building2 = b.pollFirst();
                new_x = building1[0]; 
                height_a = building1[1];
                height_b = building2[1];
                new_y = Math.max(height_a, height_b);                   
            }
            
            //we add edge to empty result list, or the edge has different height with last edge in the list
            if(result.isEmpty() || result.peekLast()[1] != new_y){
                result.add(new int[]{new_x, new_y});
            }
        }
        
        if(!a.isEmpty()) result.addAll(a);
        if(!b.isEmpty()) result.addAll(b);
        
        return result;
    }
}
