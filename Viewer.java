import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.Charset;

public class Viewer {

    private static JLabel label = new JLabel("<File>");
    private static final int WINDOW_WIDTH  = 700;
    private static final int WINDOW_HEIGHT = 700;
    public static final int EXIT_FAILURE   = 1;

    private static void createGUI() throws Exception {
        JFrame frame = new JFrame("PPMViewer");
        frame.setLayout(new BorderLayout());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JButton openBtn = new JButton("Open");
        final Parser p = new Parser();
        openBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser openFile = new JFileChooser();
                if (openFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = openFile.getSelectedFile();
                    label.setText(file.getName());
                    InputStreamReader isr = null;
                    try {
                        isr = new InputStreamReader(new FileInputStream(file.getAbsolutePath()), "ISO-8859-1");
                    } catch (Exception fnf) { System.err.println("Error initializing data input stream."); } 
                        try {
                            // fetch the parameters given in the header
                            Parameters params = p.verifyHeaders(isr);
                            if (params == null) {
                                System.exit(EXIT_FAILURE);
                            }
                            System.out.println("PARAMS: ");
                            System.out.println(params);

                        } catch (IOException exc) { System.err.println("IOError with isr read()"); }
                } else {
                    System.err.println("Error opening file.");
                }

            }
        });

        frame.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(label, BorderLayout.NORTH);
        PPMFrame pf = new PPMFrame();
        frame.add(pf);
        frame.add(openBtn, BorderLayout.SOUTH);


        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args)  {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createGUI();
                } catch (Exception e) {}
            }
        });
    }



}
