import java.util.*;
import java.io.*;



class Traces {

    private static String[] buf;
    private static int index;
    
    static { 
        buf = new String[99];
        index = 0;
    }
    
    static void addLine( String line ) {
        Traces.buf[Traces.index] = line;
        Traces.index++;
        if ( Traces.index >= Traces.buf.length ) {
            Traces.index = 0;
        }        
    }
    
    static String[] getLines() {
        int i = Traces.index;
        int k = 0;
        String[] lines = new String[i];
        
        while (i>0) {
            i--;
            lines[k] = Traces.buf[i];
            k++;
        }                
        
        return lines;
    }
}
