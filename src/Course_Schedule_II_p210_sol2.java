import java.util.*;

/**
 * This solution is very similar to Course_Schedule_I_p207_sol2, but now we need to update result[] during the cycle detection
 * Because we use recursive way to detect cycle, we will visit the most basic course in the end of recursion. Accordingly, we will
 * start fill result[] from the bottom of recursion. 
 * 
 * Time complexity: 
 * Each edge will be visited once so O(E), we also need to initialize the the course object for each course O(V). So the time complexity
 * should be O(E + V)
 * 
 * Space complexity:
 * all edge relations will be recorded O(E), courses[] will be O(V), so the space complexity would be O(E + V)
 * 
 * @author hpPlayer
 * @date Nov 15, 2015 10:35:59 PM
 */
public class Course_Schedule_II_p210_sol2 {
    int index;
    int[] result;
    
    class Course{
        //we need courseNum so we can fill result[] accordingly
        int courseNum;
        boolean isFinished = false;
        boolean isVisited = false;
        List<Course> courses = new ArrayList<Course>();
        
        Course(int val){
            courseNum = val;
        }
        
        boolean hasCycle(){
            //previously visited clean course, return false
            if(isFinished) return false;
            //previously visited course, return true
            if(isVisited) return true;
            isVisited = true;
            
            //detect cycle recursively
            for(Course c : courses){
                if(c.hasCycle()) return true;
            }
            
            //reach bottom, we begin fill the result[] during the backtracking
            result[index++] = courseNum;
            isFinished = true;
            isVisited = false;
            
            return false;
        }
        
        void addCourse(Course course){
            courses.add(course);
        }
    }
    
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //recursive DFS solution, this soultion is very similar to Course_Schedule_I_p207_sol2, but now we need to
        //use a global int[] and a global index variable to update result[] during the recursive call
        
        result = new int[numCourses];
        index = 0;
        
        //we use courses[] as a HashMap to hold the relationship between course number and course object
        Course[] courses = new Course[numCourses];
        
        for(int i = 0; i < numCourses; i++){
            //for each course object, we need assign course number in the initialzation, so we can fill result[]
            //during the cycle detection process
            courses[i] = new Course(i);
        }
        
        //update courses list in each course object based on input
        for(int[] pair : prerequisites){
            //we add basic course into the advanced course's prerequisites
            courses[pair[0]].addCourse(courses[pair[1]]);
        }
        
        //check cycle while filling result[]
        for(Course c : courses){
            //if cycle detected, return empty array
            if(c.hasCycle()) return new int[]{};
        }
        
        //otherwise return result[]
        return result;
    }
}
