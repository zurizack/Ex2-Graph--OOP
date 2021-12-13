package gui;
import api.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import api.DirectedWeightedGraphAlgorithms;
import api.DirectedWeightedGraph;

public class MyLabel extends JFrame implements ActionListener {
    private static DirectedWeightedGraph graph1;

    JTextField tf;
    JLabel l;
    JButton b1, b2, b3, b4;
    private static DirectedWeightedGraphAlgorithms graph = new Algo() ;
     public MyLabel(){
         graph.init(graph1);
     }

    public MyLabel(DirectedWeightedGraphAlgorithms graph) throws HeadlessException {
        setLayout(null);
        this.graph = graph;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.tf = new JTextField();
        this.tf.setBounds(50, 50, 100, 20);
        this.add(tf);
        this.b1 = new JButton("load");
        this.b2 = new JButton("save");
        this.b3 = new JButton("run graph");
        this.b4 = new JButton("run tsp");
        b1.setBounds(50,150,70,20);
        b2.setBounds(50,200,70,20);
        b3.setBounds(50,250,70,20);
        b4.setBounds(50,300,70,20);
        this.add(b1);
        this.add(b2);
        this.add(b3);
        this.add(b4);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You want to load your graph?");
                MyLabel.this.graph.load("G1.json");
            }
        });
       b2.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               System.out.println("Save your graph");
            MyLabel.this.graph.save("G1.json");
           }
       });
       b3.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               System.out.println("Show graph");
               MyLabel.this.graph.getGraph();
           }
       });

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            MyLabel.this.graph.center();
            }
        });
        l = new JLabel("Enter you choice:");
        l.setBounds(50,100,500,20);
        this.add(l);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
e.getActionCommand();
    }

    public static void main(String[] args) {
        new MyLabel(graph);
    }
}
