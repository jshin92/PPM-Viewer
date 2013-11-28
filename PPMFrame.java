import java.util.*;
import java.awt.*;
import javax.swing.*;

/*
 * PPMFrame : Handles the drawing the actual PPM image
 */ 
public class PPMFrame extends JPanel {

    private int width    = 600;
    private int height   = 600;
    private int x_offset = 50;
    private HashMap<Coordinate, RGBColor> colormap = null;

    private static final Color STARTUP_COLOR = new Color(0.1f, 0.2f, 0.6f);

    // obtain the colormap from the parser
    public void setPPMImage(HashMap<Coordinate, RGBColor> colormap) {
        this.colormap = colormap;
        repaint();
    }

    // draw the ppm image
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(STARTUP_COLOR);

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if (colormap != null)  {
                    Coordinate curCoord = new Coordinate(j, i);
                    // fetch the rgb color of the image at (j, i)
                    RGBColor rgbcolor   = colormap.get(curCoord);
                    Color curColor      = new Color(rgbcolor.getRed(), rgbcolor.getGreen(), rgbcolor.getBlue());
                    g.setColor(curColor);
                } 
                g.drawRect(i + x_offset, j, 1,1 );
            }
        }
    }

    public void setDimensions(int width, int height) {
        this.width  = width;
        this.height = height;
        repaint();
    }

    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

}
