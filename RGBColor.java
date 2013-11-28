/*
 * RGBColor : A class that represents a color, given in RGB percentages
 */
public class RGBColor {

    private float red;
    private float green;
    private float blue; 

    public RGBColor(float red, float green, float blue) {
        this.red   = red;
        this.green = green;
        this.blue  = blue;
    }

    public float getRed()   { return red; }
    public float getGreen() { return green; }
    public float getBlue()  { return blue; }
}
