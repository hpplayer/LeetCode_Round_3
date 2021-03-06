import java.util.*;
/*
359. Logger Rate Limiter

Design a logger system that receive stream of messages along with its timestamps,
each message should be printed if and only if it is not printed in the last 10 seconds.

Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the
given timestamp, otherwise returns false.

It is possible that several messages arrive roughly at the same time.

Example:

Logger logger = new Logger();

// logging string "foo" at timestamp 1
logger.shouldPrintMessage(1, "foo"); returns true; 

// logging string "bar" at timestamp 2
logger.shouldPrintMessage(2,"bar"); returns true;

// logging string "foo" at timestamp 3
logger.shouldPrintMessage(3,"foo"); returns false;

// logging string "bar" at timestamp 8
logger.shouldPrintMessage(8,"bar"); returns false;

// logging string "foo" at timestamp 10
logger.shouldPrintMessage(10,"foo"); returns false;

// logging string "foo" at timestamp 11
logger.shouldPrintMessage(11,"foo"); returns true;
*/

/**
 * HashMap solution
 * 
 * The basic idea is that we only update timestamp in HashMap if we gonna update it
 * 
 * Time complexity: O(1) for shouldPrintMessage()
 * Space complexity: O(N) for whole program
 * 
 * @author hpPlayer
 * @date Jun 15, 2016 10:04:19 PM
 */
public class Logger_Rate_Limiter_p359_sol1 {
    Map<String, Integer> hs;
    
    /** Initialize your data structure here. */
    public Logger_Rate_Limiter_p359_sol1() {
        hs = new HashMap<String, Integer>();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false. The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if( !hs.containsKey(message) ){
            hs.put( message, timestamp );
            return true;
        }
        
        boolean result = (timestamp - hs.get( message )) >= 10;
        //we only update hashMap if we are going to print it
        if(result) hs.put(message, timestamp);
        return result;
    }
}
/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */