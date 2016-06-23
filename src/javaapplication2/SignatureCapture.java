package javaapplication2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;
import com.google.gson.Gson;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author Suraj
 *
 */
public class SignatureCapture extends JFrame {

    public BufferedImage image = null;
    private final JPanel canvas = new JPanel();
    private final JPanel colorPanel = new JPanel();
    private Point lastPos = null;
    private SignaturePoint sp = null;
    private final ArrayList<SignaturePoint> array = new ArrayList<>();
    //private Button captureButton = new Button("Capture");
    private Graphics gc;
    Graphics2D imageG = null;
    Gson gson = new Gson();
    JButton generateButton = new JButton();

    public void init() {

        setSize(300, 255);

        image = new BufferedImage(getWidth(), getHeight(),
                BufferedImage.TYPE_INT_ARGB);

        imageG = image.createGraphics();
        colorPanel.setLayout(new GridLayout());
        canvas.setSize(getWidth(), getHeight());

        getContentPane().add(canvas, BorderLayout.CENTER);
        setVisible(true);

        gc = canvas.getGraphics();
        canvas.setBackground(Color.WHITE);
        gc.setColor(Color.WHITE);
        gc.fillRect(0, 0, image.getWidth(), image.getHeight());
        gc.setColor(Color.BLACK);

        imageG.setBackground(Color.GRAY);
        imageG.setColor(Color.GRAY);
        imageG.fillRect(0, 0, image.getWidth(), image.getHeight());
        imageG.setColor(Color.BLACK);

        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent m) {
                Point p = m.getPoint();
                gc.drawLine(lastPos.x, lastPos.y, p.x, p.y);
                imageG.drawLine(lastPos.x, lastPos.y, p.x, p.y);
                sp = new SignaturePoint(lastPos.x, lastPos.y, p.x, p.y);
                array.add(sp);
                lastPos = p;
            }

        });

        canvas.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                lastPos = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                lastPos = null;
            }

        });

        generateButton.setText("Generate");
        getContentPane().add(generateButton, BorderLayout.SOUTH);
        generateButton.addActionListener((ActionEvent e) -> {
            try (PrintWriter pw = new PrintWriter("/Users/rbertematti/Downloads/out.json")){
                System.out.println("var sig=" + gson.toJson(array) + ";");
                pw.write(gson.toJson(array)); ;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SignatureCapture.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
        });
        
    }

    public SignatureCapture() {
        init();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SignatureCapture p = new SignatureCapture();
    }
}
