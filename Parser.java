import java.io.*;

public class Parser {

    public boolean verifyHeaders(InputStreamReader isr) throws IOException {
        int magic1 = isr.read();
        int magic2 = isr.read();

        // look for P6 bit pattern (binary PPM)
        if (magic1 != 80 && magic2 != 54) {
            System.err.println("ERROR: MAGIC # P6 not found in header");
            return false;
        }

        return true;
    }

}
