/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

/**
 *
 * @author deepak.m
 */
public class SignaturePoint {
    private int lx, ly, mx, my;

    public SignaturePoint(int lx, int ly, int mx, int my) {
        this.mx = mx;
        this.my = my;
        this.lx = lx;
        this.ly = ly;
    }

    
    public int getMx() {
        return mx;
    }

    public void setMx(int mx) {
        this.mx = mx;
    }

    public int getMy() {
        return my;
    }

    public void setMy(int my) {
        this.my = my;
    }

    public int getLx() {
        return lx;
    }

    public void setLx(int lx) {
        this.lx = lx;
    }

    public int getLy() {
        return ly;
    }

    public void setLy(int ly) {
        this.ly = ly;
    }
    
}
