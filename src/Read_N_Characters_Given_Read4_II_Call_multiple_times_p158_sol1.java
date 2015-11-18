/*
 * 
Read N Characters Given Read4 II - Call multiple times

The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function may be called multiple times.
*/

/**
 * Array problem 
 * 
 * this problem is an advanced version of Read_N_Characters_Given_Read4_I_p157_sol1, and we don't need to know much about the algorithm
 * 
 * The difficulty in this problem is that we need to realize we may have chars left from last read, so before we use read4() to start
 * a new read, we need firstly read chars left from last read. So in this problem we need use a global array and two global variables to indicate
 * where and how much chars left from last read. In case last read is not full read, we need tempLength to tell us such information
 * 
 * Besides that, we always do the same thing with Read_N_Characters_Given_Read4_I_p157_sol1. But in the end of program, we also need to update tempStart
 * and tempLength accordingly. Updating tempLength is easy, we just check the difference between realRead and canWrite i.e. the number of chars we left
 * in temp[]. Updating tempStart is tricky, as we need to reset tempStart to 0 if we reach the end of temp[]. So it is better for us to use %4 operation
 * to get the tempStart, since if we reach the end of temp[], %4 will help us reset tempStart to be 0
 * 
 * Time complexity: O(n/4)* cost in read4() as we need read n chars in total
 * Space complexity: O(1) as we use an array of len 4 to be the temp buffer
 * 
 * @author hpPlayer
 * @date Nov 17, 2015 9:49:54 PM
 */
public class Read_N_Characters_Given_Read4_II_Call_multiple_times_p158_sol1 {
	/* The read4 API is defined in the parent class Reader4.
    int read4(char[] buf); */
	
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
     
    //the difference between basic and advanced version is that we need use a temp array and two variables to
    //indicate how many chars left from last read
    
    char[] temp = new char[4];
    //where we stop in last read, same as the start in next read 
    int tempStart = 0;
    //how many chars left in last read.
    //why we not use len - tempStart to get the length? That's because we may not have a full read in last read
    //therefore we need tempLen to tell us the real length in temp[]
    int tempLen = 0;
     
    public int read(char[] buf, int n) {
        //two conditions to end our read
        //1) we reach the end of file
        boolean EOF = false;
        //2) we have read n chars
        int charRead = 0;
        
        while(!EOF && charRead < n){
            //check chars we read, it may either come from chars left in last read or a new chars in this read
            int realRead = tempLen == 0? read4(temp) : tempLen;
            
            //only when we use read4() to read the file and couldn't get 4 chars, should we report EOF
            if(tempLen == 0 && realRead < 4) EOF = true;
            
            //check chars we can write
            int canWrite = Math.min(realRead, n - charRead);
            
            for(int i = 0; i < canWrite; i++){
                //in temp[] we should start read from tempStart
                buf[charRead + i] = temp[tempStart + i];
            }
            
            //record how many chars remain unread
            tempLen = realRead - canWrite;
            //if tempStart + canWrite <= 3, then tempStart should stay in the place tempStart + canWrite
            //if tempStart + canWrite == 4, then we should reset tempStart to be the index 0
            //so it is perfect to use %4 operation            
            tempStart = (tempStart + canWrite)%4;
            
            charRead += canWrite;
        }
        
        return charRead;
    }
    
    public int read4(char[] buf){
    	return 4;
    }
}
