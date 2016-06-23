package javaapplication2;

import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author Suraj
 *
 */
public class SignatureRegenerate extends JFrame {

    public BufferedImage image = null;
    private final JPanel canvas = new JPanel();
    private final JPanel colorPanel = new JPanel();
    private Point lastPos = null;
    private SignaturePoint sp = null;

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

        generateButton.setText("Regenerate");
        getContentPane().add(generateButton, BorderLayout.SOUTH);
        generateButton.addActionListener((ActionEvent e) -> {

            try {
                java.lang.reflect.Type listOfPointsType = new TypeToken<List<SignaturePoint>>() {
                }.getType();
                String str = new String(Files.readAllBytes(Paths.get("/Users/rbertematti/Downloads/out.json")));
                System.out.println("hell" + str);
                List<SignaturePoint> array = gson.fromJson(str, listOfPointsType);
                array.stream().forEach((p) -> {
                    gc.drawLine(p.getLx(), p.getLy(), p.getMx(), p.getMy());
                    imageG.drawLine(p.getLx(), p.getLy(), p.getMx(), p.getMy());
                });
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SignatureRegenerate.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ie) {
                Logger.getLogger(SignatureRegenerate.class.getName()).log(Level.SEVERE, null, ie);
            }

        });

    }

    public SignatureRegenerate() {
        init();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SignatureRegenerate p = new SignatureRegenerate();
    }
}
