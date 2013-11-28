import java.util.*;
import java.awt.*;
import javax.swing.*;

public class PPMFrame extends JPanel {

    private int width    = 600;
    private int height   = 600;
    private int x_offset = 50;
    private HashMap<Coordinate, RGBColor> colormap = null;

    public void setPPMImage(HashMap<Coordinate, RGBColor> colormap) {
        this.colormap = colormap;
        repaint();
    }

    protected void paintComponent(Graphics g) {
        System.out.println("hello");
        super.paintComponent(g);
        Color c = new Color(0.1f, 0.2f, 0.6f);
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if (colormap != null)  {
                    Coordinate curCoord = new Coordinate(j, i);
                    RGBColor rgbcolor   = colormap.get(curCoord);
                    Color curColor      = new Color(rgbcolor.getRed(), rgbcolor.getGreen(), rgbcolor.getBlue());
                    g.setColor(curColor);
                } else {
                    g.setColor(c);
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
