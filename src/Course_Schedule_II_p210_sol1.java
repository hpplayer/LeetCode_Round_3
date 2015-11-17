import java.util.*;

/*
 * Course Schedule II 
 * 
 * 
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, 
 * which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses 
 * you should take to finish all courses.
 * 
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to 
 * finish all courses, return an empty array.
 * 
 * For example:
 *      2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. 
 * So the correct course order is [0,1]
 * 
 *      4, [[1,0],[2,0],[3,1],[3,2]]
 * There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. 
 * Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. 
 * Another correct ordering is[0,2,1,3].
 * 
 * Note:
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. 
 * Read more about how a graph is represented.
 * 
 * click to show more hints.
 * 
 * Hints:
 * 
 *  - This problem is equivalent to finding the topological order in a directed graph. If a cycle exists, 
 *    no topological ordering exists and therefore it will be impossible to take all courses.
 *
 *  - Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining 
 *    the basic concepts of Topological Sort.
 *
 *  - Topological sort could also be done via BFS.
 * 
 *               
 */

/**
 * BFS + topological sort
 * 
 * This solution is almost same with Course_Schedule_I_p207_sol1.
 * But now we need to add course into result[] after we poll it from queue.
 * Finally we just check if all courses have been put into the queue once.
 * If yes, then return result[], if no then return empty array
 * 
 * 
 * Time complexity: assume m is the input size(edge size), then it costs O(m) time to update degrees[] and hashTable.
 * For the BFS, we will visit each edge once to cut the edge, so the time complexity is still O(m)
 * 
 * Space complexity: assume m is the input size(edge size), n is the number of courses(numCourses), then degrees[] is O(n) and hs is O(m)
 * que is O(n), so the total space complexity should be O(2n + m) 
 * 
 * Remark:
 * 
 * Sol1 is iterative BFS approach
 * Sol2 is recursive DFS approach
 * 
 * Due to the extra cost on queue and the early return in DFS, sol1 is slower than sol2
 * 
 * @author hpPlayer
 * @date Nov 15, 2015 10:21:49 PM
 */
public class Course_Schedule_II_p210_sol1 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //BFS solution, exactly same with Course_Schedule_I_p207_sol1, but now we add course number into result[]
        //after we poll it from the queue
        
        int[] result = new int[numCourses];
        int index = 0;
        
        //map records the outgoing edges with each basic course
        Map<Integer, List<Integer>> hs = new HashMap<Integer, List<Integer>>();
        //num of incoming edges of each course
        int[] degrees = new int[numCourses];
        
        //update hs and degrees[] based on input array
        for(int[] pair : prerequisites){
            //we update outgoing edges with each basic course
            if(!hs.containsKey(pair[1])){
                hs.put(pair[1], new ArrayList<Integer>());
            }
            
            hs.get(pair[1]).add(pair[0]);
            degrees[pair[0]]++;
        }
        
        Queue<Integer> que = new LinkedList<Integer>();
        
        //scan the degrees array and put course with zero incoming edge into the queue
        for(int i = 0; i < numCourses; i++){
            if(degrees[i] == 0) que.offer(i);
        }
        
        int count = 0;
        
        while(!que.isEmpty()){
            int size = que.size();
            
            for(int i = 0; i < size; i++){
                int curr = que.poll();
                //we add course into the result after we poll it from queue
                result[index++] = curr;
                count++;
                
                if(hs.containsKey(curr)){
                    for(int temp : hs.get(curr)){
                        if(--degrees[temp] == 0){
                            que.offer(temp);
                        }
                    }
                }
            }
        }
        
        //check if the input is valid, if it is we return result[], otherwise return an empty array
        return count == numCourses? result : new int[]{};
    }
}
