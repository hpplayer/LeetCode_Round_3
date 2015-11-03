import java.util.*;

/**
 * Using built-in LinkedHashMap.
 * 
 * Remember to use remove() and put() to force hs put curr Entry front
 * 
 * @author hpPlayer
 * @date Nov 2, 2015 8:20:50 PM
 */

public class LRU_Cache_p146_sol2 {
    LinkedHashMap<Integer, Integer> hs;
    
    public LRU_Cache_p146_sol2(int capacity) {
        hs = new LinkedHashMap<Integer, Integer>(){ 
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> e){
                return this.size() > capacity;
            }
        };
    }
    
    public int get(int key) {
        if(!hs.containsKey(key)){
            return -1;
        }else{
            int val = hs.get(key);
            hs.remove(key);
            hs.put(key, val);
            return val;
        }
    }
    
    public void set(int key, int value) {
        if(hs.containsKey(key)){
            hs.remove(key);
        }
        
        hs.put(key, value);
    }
}
