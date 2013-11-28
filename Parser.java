import java.io.*;

public class Parser {

    public Parameters verifyHeaders(InputStreamReader isr) throws IOException {
        int magic1 = isr.read();
        int magic2 = isr.read();

        // look for P6 bit pattern (binary PPM)
        if (magic1 != 80 && magic2 != 54) {
            System.err.println("ERROR: MAGIC # P6 not found in header");
            return null;
        }

        // consume newline
        isr.read();

        int widthHundredsPlace = isr.read() - '0';
        int widthTensPlace     = isr.read() - '0';
        int widthOnesPlace     = isr.read() - '0';

        int width = widthHundredsPlace * 100 + widthTensPlace* 10 + widthOnesPlace;

        // consume space
        isr.read();

        int heightHundredsPlace = isr.read() - '0';
        int heightTensPlace     = isr.read() - '0';
        int heightOnesPlace     = isr.read() - '0';

        int height = heightHundredsPlace * 100 + heightTensPlace * 10 + heightOnesPlace;

        // consume space
        isr.read();

        int maxValHundredsPlace = isr.read() - '0';
        int maxValTensPlace     = isr.read() - '0';
        int maxValOnesPlace     = isr.read() - '0';

        int maxVal = maxValHundredsPlace * 100 + maxValTensPlace * 10 + maxValOnesPlace;

        System.out.println("max val " + maxVal);

        // consume newline
        isr.read();

        return new Parameters(width, height, maxVal);
    }

}
