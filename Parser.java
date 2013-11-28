import java.io.*;
import java.util.*;

public class Parser {

    public HashMap<Coordinate, RGBColor> fetchColorMap(int width, int height, InputStreamReader isr) throws IOException {
        HashMap<Coordinate, RGBColor> colormap = new HashMap<Coordinate, RGBColor>();

        // 3 bytes per pixel, width * height pixels
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                Coordinate curCoord = new Coordinate(j, i);

                int colRed   = isr.read();
                int colBlue  = isr.read();
                int colGreen = isr.read();

                RGBColor curColor = new RGBColor(colRed/255.0f, colBlue/255.0f, colGreen/255.0f);
                colormap.put(curCoord, curColor);
            }
        }

        return colormap;
    }


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
