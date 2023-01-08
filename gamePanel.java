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

/**
 *
 * @author foldesdaniel
 */
public class gamePanel {

    public JPanel panel;

    private int[] values;
    private JFrame frame;
    private int count;
    private GUI gui;

    public gamePanel(JFrame frame, GUI gui) {
        values = new int[] {9,9,9,9,9,9,9,9,9};
        count = 0;
        this.gui = gui;
        this.frame = frame;

        int[][] a = new int[][]{{0, 1, 3, 4}, {1, 2, 4, 5}, {3, 4, 6, 7}, {4, 5, 7, 8}};
        for (int ii = 0; ii < 20; ++ii) {
            for (int i = 0; i < 4; ++i) {
                int r = (int) (Math.random() * 11) + 1;
                for (int j = 0; j < r; ++j) {
                    step(a[i], true);
                }
            }
        }
        
        //TEST VALUES
        /*for(int i = 0; i < 9; ++i) {
            values[i] = 9;
        }
        values[0] = 7;
        values[1] = 7;
        values[3] = 7;
        values[4] = 7;
        */
         
        panel = createGraphics();

        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                panel.removeAll();
                Rectangle r = frame.getBounds();
                int h = r.height;
                int w = r.width;
                int c = (h / 11 + w / 11);
                JButton b1 = new JButton();
                JButton b2 = new JButton();
                JButton b3 = new JButton();
                JButton b4 = new JButton();
                b1.setBounds(w / 3 - 40, h / 3, 50, 50);
                b2.setBounds(w - w / 3 - 25, h / 3, 50, 50);
                b3.setBounds(w / 3 - 40, h - h / 3 - 50, 50, 50);
                b4.setBounds(w - w / 3 - 25, h - h / 3 - 50, 50, 50);

                b1.setFocusPainted(false);
                b2.setFocusPainted(false);
                b3.setFocusPainted(false);
                b4.setFocusPainted(false);

                b1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        step(a[0], false);
                    }
                });

                b2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        step(a[1], false);
                    }
                });

                b3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        step(a[2], false);
                    }
                });

                b4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        step(a[3], false);
                    }
                });

                b1.setBackground(new java.awt.Color(102, 255, 225));
                b1.setOpaque(true);
                b1.setBorderPainted(false);

                b2.setBackground(new java.awt.Color(102, 255, 225));
                b2.setOpaque(true);
                b2.setBorderPainted(false);

                b3.setBackground(new java.awt.Color(102, 255, 225));
                b3.setOpaque(true);
                b3.setBorderPainted(false);

                b4.setBackground(new java.awt.Color(102, 255, 225));
                b4.setOpaque(true);
                b4.setBorderPainted(false);

                //WIN: values[n] == 9 osszesre
                panel.add(b1);
                panel.add(b2);
                panel.add(b3);
                panel.add(b4);
            }
        });
    }

    /*
        A value tömb értékeit változtatja meg.
        
        @param t indexek lesznek, a value tömb ezeken az indexeken fog értéket növelni
        @param random megnézi, hogy most csak random generál-e a kör kezedetén
     */
    private void step(int[] t, boolean random) {
        for (int i = 0; i < t.length; ++i) {
            if (values[t[i]] == 11) {
                values[t[i]] = 0;
            } else {
                values[t[i]]++;
            }
        }
        if(!random) {
            ++count;
            checkWin();
        }
    }

    /*
        Megnézi, hogy nyert-e a játékos. Ha igen, akkor lesz felugró ablak. Azt bezárva újra keződik a játék.
     */
    private void checkWin() {
        boolean b = true;
        for (int i = 0; i < 9 && b; ++i) {
            b = values[i] == 9;
        }
        if (b) {
            JOptionPane.showMessageDialog(frame, "Number of steps: " + String.valueOf(count));
            gui.generateGameGUI();
        }
    }

    /*
        A játéknak legenerálja a grafikus felületét.
     */
    private JPanel createGraphics() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D gg = (Graphics2D) g;
                gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                gg.setStroke(new BasicStroke(10));
                gg.setColor(new java.awt.Color(65, 105, 225));
                Rectangle r = frame.getBounds();
                int h = r.height;
                int w = r.width;
                int c = (h / 11 + w / 11);

                //CIRCLES
                int ox1 = w / 4 - (c / 2) - (w / 10);
                int oy1 = c / 2;
                int ox2 = w / 2 - (c / 2);
                int oy2 = h / 2 - (c / 2);
                int ox3 = w - (w / 4) + (w / 10) - (c / 2);
                int oy3 = h - c - (c / 2);

                //LINES
                int lx1 = ox1 + (c / 2);
                int ly1 = oy1 + (c / 2);
                int lx2 = ox2 + (c / 2);
                int ly2 = oy2 + (c / 2);
                int lx3 = ox3 + (c / 2);
                int ly3 = oy3 + (c / 2);

                int[] ox = new int[]{ox1, ox2, ox3};
                int[] oy = new int[]{oy1, oy2, oy3};

                int[] lx = new int[]{lx1, lx2, lx3};
                int[] ly = new int[]{ly1, ly2, ly3};

                double len = (c / 2) - 40;

                for (int i = 0; i < ox.length; ++i) {
                    for (int j = 0; j < oy.length; ++j) {
                        gg.drawOval(ox[i], oy[j], c, c);
                    }
                }

                gg.setColor(Color.ORANGE);
                gg.setStroke(new BasicStroke(4));

                double cpad = (c / 2 - 15);

                for (int i = 0; i < lx.length; ++i) {
                    for (int j = 0; j < ly.length; ++j) {
                        gg.setColor(Color.ORANGE);
                        gg.draw(new Line2D.Double(lx[j], ly[i], lx[j] + Math.cos((Math.PI / 6) * values[(i == 0 ? j : i * 3 + j)]) * len, ly[i] + Math.sin((Math.PI / 6) * values[(i == 0 ? j : i * 3 + j)]) * len));
                        for (int k = 0; k < 12; ++k) {
                            gg.setColor(Color.WHITE);
                            if (k == 9) {
                                gg.setColor(Color.RED);
                                gg.fillOval((int) (lx[j] + Math.cos(Math.PI / 6 * k) * cpad - 4), (int) (ly[i] + Math.sin(Math.PI / 6 * k) * cpad), 8, 8);
                                gg.setColor(Color.WHITE);
                            } else {
                                gg.fillOval((int) (lx[j] + Math.cos(Math.PI / 6 * k) * cpad - 2.5), (int) (ly[i] + Math.sin(Math.PI / 6 * k) * cpad), 5, 5);
                            }
                        }
                    }
                }

                repaint();
            }
        };
    }
}
