/*
 *
Read N Characters Given Read4

The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function will only be called once for each test case.
*
*/

/**
 * Array problem 
 * 
 * this problem is a very practical problem, and we don't need to know much about the algorithm
 * 
 * @author hpPlayer
 * @date Nov 16, 2015 10:39:04 PM
 */
public class Read_N_Characters_Given_Read4_p157_sol1 {
	/* The read4 API is defined in the parent class Reader4.
    int read4(char[] buf); */
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        //there are two cases that we should return result
        //1) we have read n chars
        //2) we have reach the end of file before read n chars
        
        //for case 1
        int charRead = 0;
        //for case 2
        boolean EOF = false;//short for end of file
        
        //we use temp[] to read chars from read4
        char[] temp = new char[4];
        
        //keep reading chars until meet case 1 or case 2
        while(charRead < n && !EOF){
            int currRead = read4(temp);
            
            //if currRead < 4 then we have reach the end of file, i.e. we meet case 2
            if(currRead < 4) EOF = true;
            
            //how many chars we can write to buf[] depends on 1)how many chars we read from read4() (currRead)
            //2)how many chars we are allowed to write to buf[] (n- charRead)
            int canWrite = Math.min(currRead, n - charRead);
            
            for(int i = 0; i < canWrite; i++){
                buf[charRead + i] = temp[i];
            }
            
            //update chars we read
            charRead += canWrite;
        }
        
        //finally return total chars we read
        return charRead;
    }
    
    public int read4(char[] buff){
    	return 4;
    }
}
