import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

public class Viewer {

    private static JLabel label = new JLabel("<File>");

    private static void createGUI() throws Exception {
        JFrame frame = new JFrame("PPMViewer");
        frame.setLayout(new BorderLayout());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton openBtn = new JButton("Open");
        openBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser openFile = new JFileChooser();
                if (openFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = openFile.getSelectedFile();
                    label.setText(file.getName());
                    try {
                        FileReader fr = new FileReader(file);
                        BufferedReader br = new BufferedReader(fr);
                        String line;
                        while ( (line = br.readLine()) != null )
                            System.out.println(line);
                        br.close();
                    } catch (Exception ex) {}
                } else {
                    System.err.println("Error opening file.");
                }

            }
        });

        frame.setPreferredSize(new Dimension(600, 600));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(label, BorderLayout.NORTH);
        frame.add(new PPMFrame());
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
