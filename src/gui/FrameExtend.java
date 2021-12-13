package gui;

import api.DirectedWeightedGraph;

import javax.swing.*;

public class FrameExtend extends JFrame {
    MyPanel panel;

    public FrameExtend(DirectedWeightedGraph g) {
        super();

        panel = new MyPanel(g);
        this.add(panel);

        this.setSize(1000, 1000);
        this.setVisible(true);

    }

}