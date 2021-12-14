package gui;

import api.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class FrameExtend extends JFrame {
    MyPanel panel;


    public FrameExtend(DirectedWeightedGraph g) {
        super();
        //Graphics2D gg = (Graphics2D) g;
        //gg.scale(35,32);
        panel = new MyPanel(g);
        this.add(panel);

        this.setSize(1200, 1200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

    }


}
