package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyLabel extends JFrame implements ActionListener {
    JTextField tf;
    JLabel l;
    JButton b1, b2, b3, b4;

    public MyLabel() throws HeadlessException {
        setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.tf = new JTextField();
        this.tf.setBounds(50, 50, 100, 20);
        this.add(tf);
        this.b1 = new JButton("load");
        this.b2 = new JButton("save");
        this.b3 = new JButton("edit");
        this.b4 = new JButton("run");
        b1.setBounds(50,150,70,20);
        b2.setBounds(50,200,70,20);
        b3.setBounds(50,250,70,20);
        b4.setBounds(50,300,70,20);
        this.add(b1);
        this.add(b2);
        this.add(b3);
        this.add(b4);
        ActionListener m = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String host = tf.getText();

                }catch (Exception ex){
                    System.out.println(ex);
                }
            }
        };

        b1.addActionListener(this);
        l = new JLabel("Enter you choice:");
        l.setBounds(50,100,500,20);
        this.add(l);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MyLabel();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            String host = tf.getText();
            //תשמעי את ההקלטה

        }catch (Exception ex){
            System.out.println(ex);
        }
    }


}