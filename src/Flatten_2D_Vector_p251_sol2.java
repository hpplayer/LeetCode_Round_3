import java.util.*;

/**
 * Pointer solution
 * 
 * The main part is similar to Flatten_2D_Vector_p251_sol1, but now we need to move pointers manually and also col pointer will always be initialized
 * In next(), we still only return next int, but we also need to move col pointer as well
 * All other operations will be done in hasNext()
 * In hasNext(), we will firstly check if col reaches curr list's end. If it is, then we will move row and reset col
 * Then we check if now col pointer can successfully read next int
 * As stated above, col pointer will always be initialized, therefore in case input vec is empty, we need check row < vec.size() to cover this case
 * 
 * Time complexity: O(m*n)
 * Space complexity: O(m*n)
 * 
 * @author hpPlayer
 * @date Feb 24, 2016 12:06:12 PM
 */

public class Flatten_2D_Vector_p251_sol2 {
    int row;
    int col;
    List<List<Integer>> vec;
    
    public Flatten_2D_Vector_p251_sol2(List<List<Integer>> vec2d) {
        //two pointer solution, similar to Flatten_2D_Vector_p251_sol1, we need two pointer one for vec2D, one for curr list
        vec = vec2d;
        row = 0;
        col = 0;
    }

    public int next() {
        //like Flatten_2D_Vector_p251_sol1, next() will only return next int, but now we need to move col as well, as pointer is not smart as iterator
        //other operations like check empty or change row will be done in hasNext()
        return vec.get(row).get(col++);
    }

    public boolean hasNext() {
        //Since pointer is int, it will be always initialized, therefore we only need to check if col pointer reaches current list's size
        //before we change row pointer
        while(row < vec.size() && col >= vec.get(row).size()){
            row ++;
            //reset col each time we change row
            col = 0;
        }
        
        //in case input vec2D is empty, we need to check row vs vec.size(). This step is similar to Flatten_2D_Vector_p251_sol1 where 
        //we need to check if curr iterator == null
        //Of course, we also need to check if we have next int. If we still have int left, then above while loop should always help us
        //get those int
        return row < vec.size() && col < vec.get(row).size();
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */