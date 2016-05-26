/*
134. Gas Station

There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next
station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.
*/

/**
 * Greedy solution
 * 
 * To make our car travel the circle once, the accumulative sum of gas[] should >= cost[].
 * The problem states we only have a single solution, then the start point must be the index just
 * after the min gap between accumulative sum of gas and cost. It means if we start anywhere
 * else, we wouldn't accumulate enough gas for this gap. Notice that we call it min gap, it means we have 
 * smallest difference between accumulative sum of gas and cost (lowest gas and highest cost), which means
 * at that point we have lowest remaining gas in the tank
 * 
 * We can prove this. Assume we can start any index else, then it means our gas is more than cost,
 * so we can start anywhere in the trip, or it means we have not found the max gap yet.
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date May 25, 2016 10:21:38 PM
 */
public class Gas_Station_p134_sol1 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        //find the max gap between gas and cost, i.e. the point that remaining gas in the tank is lowest
        
        //remaining gas records the remaining gas in the tank after our traveral is over
        int remainingGas = 0;
        //gap records the min accumulative sum difference between gas and cost
        //min difference will be reached when we have lowest gas but highest cost
        int gap = 0;
        //record the index of the gap end point
        int start = 0;
        
        for(int i = 0; i < gas.length; i++){
            remainingGas += gas[i] - cost[i];
            
            //if we found remainingGas is lower than gap, then we can update gap value, it means
            //we need fill more gas before entering this gap
            if(remainingGas < gap){
                gap = remainingGas;
                start = i + 1;
            }
        }
        
        //before return start index, we need firstly make sure if we can make this travel
        return remainingGas >= 0? start : -1;
    }
}
