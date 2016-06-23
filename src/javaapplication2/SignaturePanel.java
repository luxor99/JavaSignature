/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import com.google.gson.Gson;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author deepak.m
 */
public class SignaturePanel extends JPanel {

    public BufferedImage image = null;
    Graphics2D imageG = null;
    private Graphics gc;
    private final JPanel colorPanel = new JPanel();
    private Point lastPos = null;
    private SignaturePoint sp = null;
    private final ArrayList<SignaturePoint> array = new ArrayList<>();
    Gson gson = new Gson();

    public SignaturePanel(int width, int height) {
        setSize(width, height);
        
        image = new BufferedImage(getWidth(), getHeight(),
                BufferedImage.TYPE_INT_ARGB);

        imageG = image.createGraphics();
        colorPanel.setLayout(new GridLayout());

        imageG.setBackground(Color.GRAY);
        imageG.setColor(Color.GRAY);
        imageG.fillRect(0, 0, image.getWidth(), image.getHeight());
        imageG.setColor(Color.BLACK);

//        gc = this.getGraphics();
//        this.setBackground(Color.GRAY);
//        gc.setColor(Color.GRAY);
//        gc.fillRect(0, 0, image.getWidth(), image.getHeight());
//        gc.setColor(Color.BLACK);
        this.addMouseMotionListener(new MouseMotionAdapter() {
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

        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                lastPos = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                lastPos = null;
                System.out.println("var sig=" + gson.toJson(array) + ";");
            }

        });
    }

}
