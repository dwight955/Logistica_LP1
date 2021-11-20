package com.logistica.utils;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class jpColor extends javax.swing.JPanel {
	private Color color1 = new Color(0,0,0);
    private Color color2 = new Color(0,0,0);
    
    public jpColor(){
		 
	}
	public jpColor(Color c1, Color c2) {
		this.color1 = c1;
		this.color2 = c2;
	}
	@Override
    public void paint(Graphics g) {    
        Graphics2D g2 = (Graphics2D) g.create();
        Rectangle clip = g2.getClipBounds();                        
        g2.setPaint(new GradientPaint(0.0f, 0.0f, color1.darker(),0.0f ,getHeight(), color2.darker()));        
        g2.fillRect(clip.x, clip.y, clip.width, clip.height);        
    }
    
    public void setRandomColor(){
        Random rand = new Random();
        this.color1 = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
        this.color2 = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
    }
    
    public void setVistaColor(){        
        this.color1 = new Color(66,111,166);
        this.color2 = new Color(103,174,118);        
    }
}
