package gui;

import api.Algo;
import api.DirectedWeightedGraphAlgorithms;
import api.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MyLabel extends JFrame implements ActionListener {
    JLabel lbl_status;
    JLabel l;

    Map<String, JButton> btns;
    Map<String, JTextField> results;
    JFileChooser fileChooser = new JFileChooser();
    String[] secondary_txt_btns = { "shortest path", "center", "tsp", "is connected" };
    DirectedWeightedGraphAlgorithms graph = null;
    FrameExtend frameExtend = null;

    public static void main(String[] args) {
        new MyLabel();
    }

    public MyLabel() throws HeadlessException {
        setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 700);
        this.lbl_status = new JLabel();
        this.lbl_status.setBounds(50, 50, 400, 20);
        this.add(lbl_status);

        String[] txt_btns = { "load", "save", "edit", "run", "run algorithm",
                "shortest path", "center", "tsp", "is connected" };
        btns = new HashMap<>();
        results = new HashMap<>();
        int offset = 0;
        for (String txt_btn : txt_btns) {
            JButton btn = new JButton(txt_btn);
            btn.setBounds(50, 150 + offset, 300, 20);
            this.add(btn);

            btns.put(txt_btn, btn);
            offset += 50;
        }
        offset = 0;
        for (String txt_btn : secondary_txt_btns) {
            JButton btn = btns.get(txt_btn);
            btn.setBounds(100, 390 + offset, 300, 20);
            this.add(btn);

            JTextField txt_result = new JTextField();
            txt_result.setBounds(420, 390 + offset, 300, 20);
            this.add(txt_result);
            results.put(txt_btn, txt_result);

            offset += 40;
        }
        nullify();

        //b1.addActionListener(this);
        l = new JLabel("Enter you choice:");
        l.setBounds(50, 100, 500, 20);
        this.add(l);

        this.init_actions();
        this.setVisible(true);
    }

    private void nullify() {
        this.lbl_status.setText("<file is not loaded yet>");
        this.lbl_status.setToolTipText(null);
        graph = null;
        for (String txt_btn : secondary_txt_btns) {
            btns.get(txt_btn).setVisible(false);
            results.get(txt_btn).setVisible(false);
            results.get(txt_btn).setText("");
        }
        if ( frameExtend != null ) {
            frameExtend.setVisible(false);
            frameExtend.dispose();
        }
    }

    private void init_actions() {
        add_action_to_load();
        add_action_to_run();
        add_action_to_save();
        add_action_to_edit();
        add_action_to_run_algo();

        add_action_to_algo_is_connected();
        add_action_to_algo_shortest_path();
        add_action_to_algo_center();
        add_action_to_algo_tsp();
    }
    //region normal buttons
    private void add_action_to_run() {
        btns.get("run").addActionListener(e -> {
            if ( this.graph == null ) {
                this.lbl_status.setText("Error: can't run if graph is not loaded.");
                return;
            }
            this.frameExtend = new FrameExtend(graph.getGraph());
        });
    }

    private void add_action_to_load() {
        btns.get("load").addActionListener(e -> {
            nullify();
            int result = fileChooser.showOpenDialog(this);
            System.out.println(result);
            if ( result == JFileChooser.APPROVE_OPTION ) {
                File selectedFile = fileChooser.getSelectedFile();
                String graph_file = selectedFile.getAbsolutePath();

                graph = new Algo();
                graph.init(new Graph());
                graph.load(graph_file);
                this.lbl_status.setToolTipText(graph_file);
                this.lbl_status.setText(selectedFile.getName());
            }
        });
    }

    private void add_action_to_save() {
        btns.get("save").addActionListener(e -> {
            if ( this.graph == null ) {
                this.lbl_status.setText("Error: can't save if graph is not loaded.");
                return;
            }
            // todo: implement me
            System.out.println("clicked SAVE button");  // todo: delete me
        });
    }

    private void add_action_to_run_algo() {
        btns.get("run algorithm").addActionListener(e -> {
            if ( this.graph == null ) {
                this.lbl_status.setText("Error: can't run algo if graph is not loaded.");
                return;
            }

            for (String txt_btn : secondary_txt_btns) {
                btns.get(txt_btn).setVisible(!btns.get(txt_btn).isVisible());
                results.get(txt_btn).setVisible(!results.get(txt_btn).isVisible());
            }
        });
    }

    private void add_action_to_edit() {
        btns.get("save").addActionListener(e -> {
            if ( this.graph == null ) {
                this.lbl_status.setText("Error: can't edit if graph is not loaded.");
                return;
            }
            // todo: implement me
            System.out.println("clicked EDIT button");  // todo: delete me
        });
    }
    //endregion
    //region alg buttons
    private void add_action_to_algo_is_connected() {
        btns.get("is connected").addActionListener(e -> {
            boolean is_connected = graph.isConnected();
            results.get("is connected").setText(is_connected + "");
        });
    }
    private void add_action_to_algo_shortest_path() {
        btns.get("shortest path").addActionListener(e -> {
            String str = results.get("shortest path").getText();
            String[] parts = str.split("[\\D]+", 0);
            if (parts.length!=2){
                results.get("shortest path").setText("Please enter two nodes numbers like: '1,2', you entered:"+results.get("shortest path").getText());
                return;
            }
            try {
                double result = graph.shortestPathDist(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
                results.get("shortest path").setText(result + "");
            }
            catch (Exception ignored){

                results.get("shortest path").setText("Something bad happened.");
            }
        });
    }
    private void add_action_to_algo_center() {
        btns.get("center").addActionListener(e -> {
            // todo: implement me
            results.get("center").setText("not implemented yet");
        });
    }
    private void add_action_to_algo_tsp() {
        btns.get("tsp").addActionListener(e -> {
            // todo: implement me
            results.get("tsp").setText("not implemented yet");
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String host = lbl_status.getText();

        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    //endregion



}