import java.util.PriorityQueue;

public class Super_Ugly_Number_p313_sol2 {
	public static void main(String[] args){
		int[] primes = {2,3,11,13,19,29,31,37,41,47};
		System.out.println(nthSuperUglyNumber(95, primes));
	}
    public static int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<Long> pq = new PriorityQueue<Long>();
        pq.offer(1l);
        
        for(int i = 1; i < n; i++){
            Long currMin = pq.poll();
            while(!pq.isEmpty() && currMin.equals(pq.peek())){
            	pq.poll();
            }
            for(int j = 0;  j < primes.length; j++){
                pq.offer(currMin * primes[j]);
            }   
        }
        
        return pq.peek().intValue();
    }
}
