import java.util.*;

/**
 * DFS+ topological sort solution
 * 
 * We use DFS way to detect cycle in the graph. If our current course has been visited before in the same cycle detection process, 
 * it means we found a cycle. We use courses[] to map the relationship between course number and course object. 
 *  
 * We use two variable to indicate if current course is clean or dirty, so we won't visit previously visited path. 
 * 
 * Time complexity: 
 * Each edge will be visited once so O(E), we also need to initialize the the course object for each course O(V). So the time complexity
 * should be O(E + V)
 * 
 * Space complexity:
 * all edge relations will be recorded O(E), courses[] will be O(V), so the space complexity would be O(E + V)
 * 
 * @author hpPlayer
 * @date Nov 15, 2015 9:29:41 PM
 */

public class Course_Schedule_I_p207_sol2 {  
    class Course{
        //if a course is "isFinished", then it is a clean course without cycle
        boolean isFinished = false;
        //if we found a course is "isVisited" during the process of detecting cycle, then it means we got a cycle
        boolean isVisited = false;
        //courses include prerequisites for current course object
        List<Course> courses = new ArrayList<Course>();
        
        boolean hasCycle(){
            //clean course, return false
            if(isFinished) return false;
            //dirty course, return true
            if(isVisited) return true;
            isVisited = true;
            
            //detect cycle recursively
            for(Course course : courses){
                if(course.hasCycle()) return true;
            }
            
            //no cycle detected, clean node
            isVisited = false;
            isFinished = true;
            return false;
        }
        
        void addCourse(Course course){
            courses.add(course);
        }
    }
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //recursive way to detect cycle in input, in this solution we use an extra class to represent course
        
        
        //we use courses[] as a HashMap to hold the relationship between course number and course object
        Course[] courses = new Course[numCourses];
        
        //Analyze the courses[]
        for(int i = 0; i < numCourses; i++){
            courses[i] = new Course();
        }
        
        //update courses list in each course object based on input
        for(int[] pair : prerequisites){
            //we add basic course into the courses list of advanced course
            courses[pair[0]].addCourse(courses[pair[1]]);
        }
        
        //check each course in the courses[] to see if there exist a cycle
        for(Course c : courses){
            if(c.hasCycle()) return false;
        }
        
        //no cycle is detected after all courses have been checked, so we return true
        return true;
    }
}
