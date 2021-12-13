package gui;
import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;


public class MyPanel extends JPanel {
    private DirectedWeightedGraph graph;


    public MyPanel(DirectedWeightedGraph g) {

        super();
        this.setBackground(Color.white);
        this.graph = g;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g.setFont(new Font("name", Font.PLAIN, 20));
        g.setColor(Color.BLACK);
        g2.setColor(Color.blue);
        int x1;
        int y1;
        NodeData node = null;
        EdgeData edge = null;
        double maxX = highX();
        double maxY = highY();
        double minY = lowY();
        double minX = lowX();

        double sumX = maxX - minX;
        double scaleX = 600 / sumX;
        double sumY = maxY - minY;
        double scaleY = 600 / sumY;
        //double distcurx;
        //double distcury;
        int y2;
        int x2;

        for (Iterator<NodeData> n = this.graph.nodeIter(); n.hasNext(); ) {
            node = n.next();
            x2 =(int) ((node.getLocation().x() - lowX())*scaleX);
            y2 = (int)((node.getLocation().y()- lowY())*scaleY);
            g2.drawOval(x2+55,y2+55,15,15);
//            g.setColor(Color.black);
//            double distX = maxX - node.getLocation().x();
//            if (distX == 0) {
//                distcurx = 0;
//            } else {
//                distcurx = distX / scaleX;
//            }
//            x1 = 600 - distcurx;//*1000 -35000;
//
//            double distY = maxY - node.getLocation().y();
//            if (distY == 0) {
//                distcury = 0;
//            } else {
//                distcury = distY / scaleY;
//            }
//            y1 = 600 - distcury;//*1000 -32000;
//            g.drawOval((int) x1,(int) y1,15,15);
//            g2.draw(new Ellipse2D.Double(x1, y1, 15, 15));


        }
        for (Iterator<NodeData> n = this.graph.nodeIter(); n.hasNext(); ) {
            node = n.next();

            for (Iterator<EdgeData> e = this.graph.edgeIter(node.getKey()); e.hasNext(); ) {
                edge = e.next();
                double weight = edge.getWeight();
                int id = node.getKey();
                x2 = (int)((graph.getNode(edge.getDest()).getLocation().x() - lowX()) * scaleX);
                y2 = (int)((graph.getNode(edge.getDest()).getLocation().y() - lowY()) * scaleY);
                x1 = (int)((node.getLocation().x() - lowX()) * scaleX);
                y1 = (int)((node.getLocation().y() - lowY()) * scaleY);

                g2.drawLine(x1 + 60,  y1 + 60, x2 + 60, y2 + 60);
                g2.setFont(new Font("zur",Font.PLAIN , 10));
                g2.drawString("id:" + id,  x1+50,  y1+40);

            }
        }
    }

    public double highX() {

        double highLoc = 0;
        for (Iterator<NodeData> n = this.graph.nodeIter(); n.hasNext(); ) {
            NodeData iter = n.next();
            if (this.graph.getNode(iter.getKey()).getLocation().x() > highLoc) {
                highLoc = this.graph.getNode(iter.getKey()).getLocation().x();

            }
        }
        return highLoc;
    }

    public double highY() {

        double highLoc = 0;
        for (Iterator<NodeData> n = this.graph.nodeIter(); n.hasNext(); ) {
            NodeData iter = n.next();
            if (this.graph.getNode(iter.getKey()).getLocation().y() > highLoc) {
                highLoc = this.graph.getNode(iter.getKey()).getLocation().y();

            }
        }
        return highLoc;
    }

    public double lowX() {

        double lowLoc = Double.MAX_VALUE;
        for (Iterator<NodeData> n = this.graph.nodeIter(); n.hasNext(); ) {
            NodeData iter = n.next();
            if (this.graph.getNode(iter.getKey()).getLocation().x() < lowLoc) {
                lowLoc = this.graph.getNode(iter.getKey()).getLocation().x();
            }
        }
        return lowLoc;
    }

    public double lowY() {

        double lowLoc = Double.MAX_VALUE;
        for (Iterator<NodeData> n = this.graph.nodeIter(); n.hasNext(); ) {
            NodeData iter = n.next();
            if (this.graph.getNode(iter.getKey()).getLocation().y() < lowLoc) {
                lowLoc = this.graph.getNode(iter.getKey()).getLocation().y();

            }
        }
        return lowLoc;
    }
}