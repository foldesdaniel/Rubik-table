/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.imageio.*;
import java.io.*;

/**
 *
 * @author foldesdaniel
 */
public class GUI {

    private JFrame frame;
    private JPanel panel;

    public GUI() {
        frame = new JFrame("Rubik's clock");
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        
        generateGameGUI();
    }
    
    /*
        Legenerálja az alkalmazás grafikus megjelenítését és alkalmazza is azt.
    */
    public void generateGameGUI() {
        frame.getContentPane().removeAll();
        frame.repaint();
        
        gamePanel p = new gamePanel(frame, this);
        panel = p.panel;

        panel.setLayout(null);
        panel.setForeground(Color.WHITE);

        panel.setBackground(new java.awt.Color(30, 30, 30));
        panel.setPreferredSize(new Dimension(frame.getBounds().width, frame.getBounds().height));

        frame.add(panel);

        frame.pack();
        frame.setVisible(true);
    }
}
