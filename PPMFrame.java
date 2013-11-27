import java.awt.*;
import javax.swing.*;

public class PPMFrame extends JPanel {

    private int width    = 600;
    private int height   = 600;
    private int x_offset = 50;

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width ; i++) {
                g.drawRect(i + x_offset, j, 1,1 );
            }
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

}
