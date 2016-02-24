import java.util.*;
/*
251. Flatten 2D Vector

Implement an iterator to flatten a 2d vector.

For example,
Given 2d vector =

[
  [1,2],
  [3],
  [4,5,6]
]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].

Hint:

How many variables do you need to keep track?
Two variables is all you need. Try with x and y.
Beware of empty rows. It could be the first few rows.
To write correct code, think about the invariant to maintain. What is it?
The invariant is x and y must always point to a valid point in the 2d vector. Should you maintain your invariant ahead of time or right when you need it?
Not sure? Think about how you would implement hasNext(). Which is more complex?
Common logic in two different places should be refactored into a common method.

Follow up:
As an added challenge, try to code it using only iterators in C++ or iterators in Java.

*/

/**
 * Iterator solution
 * 
 * We use two iterators. One for vec2D, and one for curr list.
 * We will do main operations like change row / check empty in hasNext()
 * next() will only return next int
 * 
 * Since the input vec2D maybe empty which means we may not able to initialize second iterator at all, we have to check curr == null as well in hasNext()
 * 
 * Time complexity: O(m*n)
 * Space complexity: O(m*n)
 * 
 * Sol2 is two pointers solution
 * 
 * @author hpPlayer
 * @date Feb 24, 2016 11:50:18 AM
 */
public class Flatten_2D_Vector_p251_sol1 {

    public static void main(String[] args){
    	List<List<Integer>> vec = new ArrayList<List<Integer>>();
    	vec.add(Arrays.asList(1,2));
    	vec.add(Arrays.asList());
    	
    	Flatten_2D_Vector_p251_sol1 i = new Flatten_2D_Vector_p251_sol1(vec);
    	while(i.hasNext()) System.out.println(i.next());
    	
    }
    
    Iterator<List<Integer>> it;
    Iterator<Integer> curr;
    
    public Flatten_2D_Vector_p251_sol1(List<List<Integer>> vec2d) {
        //iterator solution, we need two iterators, one for vec2d (it) and one for curr list (curr)
        it = vec2d.iterator();
    }

    public int next() {
        //next() will only return next int.=
        //Other operations like change curr iterator, check if we have int left will all be done in hasNext()
        return curr.next();
    }

    public boolean hasNext() {
        //there are two cases that we need change curr iterator
        //1) we have not initialized curr iterator i.e. curr == null
        //2) curr iterator has been done for current list i.e. !curr.hasNext()
        while(it.hasNext() && (curr == null || !curr.hasNext())){
            curr = it.next().iterator();
        }
        
        //if input list is empty at all (like vec2d = []), then we would not even initialize curr iterator, so we need check curr != null
        //otherwise we can check curr.hasNext() to see if we still have int left
        return curr != null && curr.hasNext();
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */