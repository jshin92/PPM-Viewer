import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.Charset;

/*
 * Viewer : The class that puts it all together : the JFrame, labels, open button
 */
public class Viewer {

    private static JLabel label = new JLabel("<File>");
    private static final int WINDOW_WIDTH   = 700;
    private static final int WINDOW_HEIGHT  = 700;
    private static final int EXIT_FAILURE   = 1;
    private static PPMFrame pf   = null;
    private static Parser parser = null;

    public static void main(String[] args)  {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createGUI();
                } catch (Exception e) {
                    Error("Error creating GUI", e);
                }
            }
        });
    }

    /*
     * Creates the application's GUI. 
     */
    private static void createGUI() throws Exception {
        JFrame frame = new JFrame("PPMViewer");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        parser = new Parser();
        JButton openBtn = new JButton("Open");
        openBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser openFile = new JFileChooser();
                loadData(openFile);   
            }
        });

        frame.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(label, BorderLayout.NORTH);
        pf = new PPMFrame();
        frame.add(pf);
        frame.add(openBtn, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    /*
     * Loads the binary file *.ppm by parsing the headers for the width, height, and maxVal. 
     * It then fetches the color map and passes that to the PPMFrame so that it can be drawn. 
     */
    private static void loadData(JFileChooser openFile) {
         if (openFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = openFile.getSelectedFile();
            label.setText(file.getName());
            InputStreamReader isr = null;

            try {
                isr = new InputStreamReader(new FileInputStream(file.getAbsolutePath()), "ISO-8859-1");
            } catch (Exception fnf) { 
                Error("Error initializing data input stream.", fnf); 
            }

            try {
                // fetch the parameters given in the header
                Parameters params = parser.verifyHeaders(isr);
                if (params == null) {
                    // wrong magic number, P6 not found
                    System.exit(EXIT_FAILURE);
                }
                System.out.println("--PARAMS--");
                System.out.println(params);
                pf.setPPMImage(parser.fetchColorMap(isr));

            } catch (IOException exc) { 
                Error("IOError with isr read()", exc); 
            }
        } else {
            Error("Error opening file.");
        }
    }


    private static void Error(String msg, Exception e) {
        System.err.println(msg);
        if (e != null) 
            e.printStackTrace();
        System.exit(EXIT_FAILURE);
    }

    private static void Error(String msg) {
        Error(msg, null);
    }


}
