import java.util.*;

/*Course Schedule
 * 
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, 
 * which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to 
 * finish all courses?
 * 
 * For example:
 *      2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. 
 * So it is possible.
 * 
 *      2, [[1,0],[0,1]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0, 
 * and to take course 0 you should also have finished course 1. So it is impossible.
 * 
 * Note:
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. 
 * Read more about how a graph is represented.
 * 
 * click to show more hints.
 * 
 * Hints:
 * 
 *  - This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, 
 *    no topological ordering exists and therefore it will be impossible to take all courses.
 *
 *  - Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic 
 *    concepts of Topological Sort. (https://class.coursera.org/algo-003/lecture/52)
 *
 *  - Topological sort could also be done via BFS. (http://en.wikipedia.org/wiki/Topological_sorting#Algorithms)
 * 
 */

/**
 * BFS + topological sort
 * 
 * We use BFS way to visit the topological order of the input array, if there exists a cycle, then we will never able to put all courses
 * into the queue. So in the end, we just need to check if all nodes have been put into the queue, and it can tell us whether there
 * is cycle in the input array.
 * 
 * We use two container in this solution. hs is used to record the course and courses depend on it. degrees[] is used to record the 
 * num of incoming edges. We firstly update these two containers based on input array, then we put all courses with zero incoming edge
 * into the queue. Then we visit course in the queue, and cut their outgoing edges, if we can have some new courses that have zero
 * incoming edges, then we can just add them to queue. Finally we just need to check if all courses have been put into the queue once.
 * 
 * Time complexity: assume m is the input size(edge size), then it costs O(m) time to update degrees[] and hashTable.
 * For the BFS, we will visit each edge once to cut the edge, so the time complexity is still O(m)
 * 
 * Space complexity: assume m is the input size(edge size), n is the number of courses(numCourses), then degrees[] is O(n) and hs is O(m)
 * que is O(n), so the total space complexity should be O(2n + m) 
 * 
 * 
 * Remark:
 * 
 * Sol1 is iterative BFS approach
 * Sol2 is recursive DFS approach
 * 
 * Due to the extra cost on queue and the early return in DFS, sol1 is slower than sol2
 * 
 * @author hpPlayer
 * @date Nov 15, 2015 8:35:59 PM
 */
public class Course_Schedule_I_p207_sol1 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //BFS solution, each time we only put course with zero incoming edge into the queue
        
        //map that records the course and its outgoing edges, so after we are done with a course, we can remove
        //its outgoing edges and generate new nodes with zero incoming edge
        Map<Integer, List<Integer>> hs = new HashMap<Integer, List<Integer>>();
        
        //array that records the course and num of its incoming edges
        int[] degrees = new int[numCourses];
        
        //update hs and degrees based on prerequisites
        
        for(int[] pair : prerequisites){
            //in this solution, we assume advanced course is on the right and basic course in on the left,
            //so  a->b means taking course b we must take course a first
            if(!hs.containsKey(pair[1])){
                hs.put(pair[1], new ArrayList<Integer>());
            }
            
            //update basic courses with their outgoing edges
            hs.get(pair[1]).add(pair[0]);
            //update advanced courses with num of their incoming edges
            degrees[pair[0]]++;
        }
        
        //queue that helps us scan the prerequisites in BFS approach
        Queue<Integer> que = new LinkedList<Integer>();
        
        //scan the degrees array and put course with zero incoming edge into the queue
        for(int i = 0; i < numCourses; i++){
            if(degrees[i] == 0) que.offer(i);
        }
        
        //count variable is used to count how many courses have been put into the queue
        //if we have cycle in input, we will never put all courses into the queue as the nodes in cycle
        ///will never have zero incoming edeges
        int count = 0;
        
        //use BFS approach to visit the prerequisites
        while(!que.isEmpty()){
            int size = que.size();
            
            for(int i = 0; i < size; i++){
                count++;   
                int curr = que.poll();
                //we only put course with outgoing edges into the hashMap, while it is possible that we have 
                //some individual courses that do not have outgoing edges, so we need check if first
                if(hs.containsKey(curr)){
                    for(int temp : hs.get(curr)){
                        //cut the outgoing edge, so the corresponding course will have one less incoming edge
                        //if the incoming edge becomes 0, then we add it into queue
                        if(--degrees[temp] == 0){
                            que.offer(temp);
                        }
                    }                    
                }
            }
        }
        
        //finally we just check if we can put all courses into the queue
        return count == numCourses;
    }
}
