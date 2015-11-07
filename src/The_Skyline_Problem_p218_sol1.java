import java.util.*;

public class The_Skyline_Problem_p218_sol1 {
    class Edge{
        boolean isLeft;
        int h;
        int x;
        
        Edge(int x, int h, boolean isLeft){
            this.x = x;
            this.h = h;
            this.isLeft = isLeft;
        }
    }
    
    
    public List<int[]> getSkyline(int[][] buildings) {
        List<Edge> edges = new ArrayList<Edge>();
        
        for(int[] building : buildings){
            Edge left = new Edge(building[0], building[2], true);
            Edge right = new Edge(building[1], building[2], false);
            edges.add(left);
            edges.add(right);
        }
        
        edges.sort(new Comparator<Edge>(){
        	public int compare(Edge a, Edge b){
        		if(a.x != b.x){
        			return a.x - b.x;
        		}else{
        			if(a.isLeft && b.isLeft){
        				return b.h - a.h;
        			}else if(!a.isLeft && !b.isLeft){
        				return a.h - b.h;
        			}else{
        				return a.isLeft? -1 : 1;
        			}
        		}
        	}
        });
        
        //max heap
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(10, Collections.reverseOrder());
        
        List<int[]> result = new ArrayList<int[]>();
        
        for(Edge edge : edges){
        	if(edge.isLeft){
            	if(pq.isEmpty() || edge.h > pq.peek()){
            		result.add(new int[]{edge.x, edge.h});
            	}	
            	pq.offer(edge.h);
        	}else{
        		//poll the corresponding left edge out
        		pq.poll();
        		if(pq.isEmpty()){
        			result.add(new int[]{ edge.x, 0});
        		}else if(pq.peek() < edge.h){
            		result.add(new int[]{edge.x, pq.peek()});
        		}
        	}
        }
        
        return result;
    }
}
