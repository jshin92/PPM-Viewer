/*
 * Represents the parameters that can be fetched
 * from the header (e.g. magic number {P6}, width, height)
 */ 
public class Parameters {

    private int width  = 0;
    private int height = 0;
    private int maxVal = 0;

    public int getWidth()  { return width; }
    public int getHeight() { return height; }
    public int getMaxVal() { return maxVal; }

    public void setWidth(int width)   { this.width  = width;  }
    public void setHeight(int height) { this.height = height; }
    public void setMaxVal(int maxVal) { this.maxVal = maxVal; }

    public Parameters(int width, int height, int maxVal) {
        this.width  = width;
        this.height = height;
        this.maxVal = maxVal;
    }

    public String toString() {
        return new String("[width : " + width + "] | [height : " + height + "] | [maxVal : " + maxVal + "]");
    }

}
