import java.io.*;
import java.util.*;

/*
 * Parser : class that handles parsing of the binary PPM file
 */
public class Parser {

    private static final int ASCII_P = 80;
    private static final int ASCII_6 = 54;

    private int   width  = 0;
    private int   height = 0;
    private float maxVal = 0.0f;

    /*
     * Returns a mapping from Coordinate to Color. Every (x, y) coordinate 
     * will have a matching RGB Color, so that the canvas knows what 
     * color to use. 
     */
    public HashMap<Coordinate, RGBColor> fetchColorMap(InputStreamReader isr) throws IOException {
        HashMap<Coordinate, RGBColor> colormap = new HashMap<Coordinate, RGBColor>();
        // 3 bytes per pixel, width * height pixels
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                Coordinate curCoord = new Coordinate(j, i);

                int colRed   = isr.read();
                int colBlue  = isr.read();
                int colGreen = isr.read();

                RGBColor curColor = new RGBColor(colRed/maxVal, colBlue/maxVal, colGreen/maxVal);
                colormap.put(curCoord, curColor);
            }
        }

        return colormap;
    }

    /*
     * Verifies that we are indeed dealing with PPM (P6) file and 
     * returns key paramaters, such as the width and the height of the
     * ppm image, all enclosed in a Parameter class
     */
    public Parameters verifyHeaders(InputStreamReader isr) throws IOException {
        int magic1 = isr.read();
        int magic2 = isr.read();

        // look for P6 bit pattern (binary PPM)
        if (magic1 != ASCII_P && magic2 != ASCII_6) {
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

        // consume newline
        isr.read();
        
        this.width  = width;
        this.height = height;
        this.maxVal = (float) maxVal;

        return new Parameters(width, height, maxVal);
    }

}
