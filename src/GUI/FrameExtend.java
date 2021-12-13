package GUI;

import api.DirectedWeightedGraph;

import javax.swing.*;
import java.awt.*;

public class FrameExtend extends JFrame {
    MyPanel panel;

    public FrameExtend(DirectedWeightedGraph g) {
        super();

        panel = new MyPanel(g);
        this.add(panel);

        this.setSize(1000, 1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

    }

}