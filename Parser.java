import java.io.*;
import java.util.*;

/*
 * Parser : class that handles parsing of the binary PPM file
 */
public class Parser {

    private static final int ASCII_P = 80;
    private static final int ASCII_6 = 54;
    private static final int ASCII_SPACE = 32;
    private static final int ASCII_NEWLINE = 10;

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
     * returns key parameters, such as the width and the height of the
     * ppm image, all enclosed in a Parameter class
     */
    public Parameters verifyHeaders(InputStreamReader isr) throws IOException {
        int magic1 = isr.read();
        int magic2 = isr.read();

        // look for P6 bit pattern (binary PPM)
        if (magic1 != ASCII_P || magic2 != ASCII_6) {
            System.err.println("ERROR: MAGIC # P6 not found in header");
            return null;
        }

        // consume newline
        isr.read();

        this.width = parseNumber(isr, ASCII_SPACE);
        this.height = parseNumber(isr, ASCII_NEWLINE);
        this.maxVal = parseNumber(isr, ASCII_NEWLINE);

        return new Parameters(this.width, this.height, (int)this.maxVal);
    }

    private int parseNumber(InputStreamReader isr, int delimeter) throws IOException {
        int res = 0;

        int cur = isr.read();
        while (cur != delimeter) {
            res = res * 10 + cur - '0';
            cur = isr.read();
        }

        return res;
    }

}
