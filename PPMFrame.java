import java.awt.*;
import javax.swing.*;

public class PPMFrame extends JPanel {
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        //g.drawPolygon(x, y, x.length);
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                g.drawRect(j, i, 1,1 );
            }
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }

}
