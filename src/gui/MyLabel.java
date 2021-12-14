package gui;

import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
import java.util.List;

public class MyLabel extends JFrame implements ActionListener {
	JLabel lbl_status;
	JLabel l;
	
	Map<String, JButton> btns;
	Map<String, JTextField> results;
	Map<String, HintTextField> inputs;
	JFileChooser fileChooser = new JFileChooser();
	
	String[] secondary_txt_btns = { "shortest path", "center", "tsp", "is connected", "add node", "add edge", "remove node", "remove edge" };
	DirectedWeightedGraphAlgorithms graph = null;
	FrameExtend frameExtend = null;
	String input_path = "";
	
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
		
		String[] txt_btns_old = { "load", "save", "edit", "run", "run algorithm"};
		String[] txt_btns = new String[txt_btns_old.length+secondary_txt_btns.length];
		int index = 0;
		for (; index < txt_btns_old.length; index++) {
			txt_btns[index] = txt_btns_old[index];
		}
		for (; index < txt_btns_old.length+secondary_txt_btns.length; index++) {
			txt_btns[index] = secondary_txt_btns[index-txt_btns_old.length];
		}
		
		btns = new HashMap<>();
		results = new HashMap<>();
		inputs = new HashMap<>();
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
			btn.setBounds(140, 420 + offset, 300, 20);
			this.add(btn);
			
			JTextField txt_result = new JTextField();
			txt_result.setBounds(460, 420 + offset, 300, 20);
			this.add(txt_result);
			results.put(txt_btn, txt_result);
			
			HintTextField txt_input = new HintTextField("");
			txt_input.setBounds(20, 420 + offset, 100, 20);
			this.add(txt_input);
			inputs.put(txt_btn, txt_input);
			
			offset += 25;
		}
		inputs.get("shortest path").hint = "ex: 2, 3";
		inputs.get("tsp").hint = "ex: 0, 2, 5";
		
		
		hide_input("is connected");
		hide_input("center");
		hide_input("add node");
		nullify();
		
		//b1.addActionListener(this);
		l = new JLabel("Enter you choice:");
		l.setBounds(50, 100, 500, 20);
		this.add(l);
		
		this.init_actions();
		this.setVisible(true);
	}
	
	private void hide_input(String node_name) {
		inputs.get(node_name).setBounds(2000000, 2000000, 0, 0);
	}
	
	private void nullify() {
		this.lbl_status.setText("<file is not loaded yet>");
		this.lbl_status.setToolTipText(null);
		graph = null;
		for (String txt_btn : secondary_txt_btns) {
			btns.get(txt_btn).setVisible(false);
			results.get(txt_btn).setVisible(false);
			results.get(txt_btn).setText("");
			inputs.get(txt_btn).setVisible(false);
			inputs.get(txt_btn).setText("");
			inputs.get(txt_btn).focusLost(null);
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
		add_action_add_node();
		add_action_add_edge();
		add_action_remove_node();
		add_action_remove_edge();
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
				input_path = graph_file;
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
			String path = input_path.substring(0, input_path.length() - 5) + ".saved.json";
			graph.save(path);
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
				inputs.get(txt_btn).setVisible(!inputs.get(txt_btn).isVisible());
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
			String str = inputs.get("shortest path").getText();
			String[] parts = str.split("[\\D]+", 0);
			if ( parts.length != 2 ) {
				results.get("shortest path").setText("Please enter two nodes numbers like: '1,2'");
				return;
			}
			try {
				double result = graph.shortestPathDist(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
				results.get("shortest path").setText(result + "");
			}
			catch (Exception ignored) {
				
				results.get("shortest path").setText("Something bad happened.");
			}
		});
	}
	
	private void add_action_to_algo_center() {
		btns.get("center").addActionListener(e -> {
			results.get("center").setText(graph.center().getKey() + "");
		});
	}

	private void add_action_remove_edge(){

		btns.get("remove edge").addActionListener(e -> {
			String str = inputs.get("add edge").getText();
			String[] parts = str.split("[\\D]+", 0);
			if ( parts.length != 2 ) {
				results.get("remove edge").setText("Please enter 'src,dest' nodes numbers  like: '1,2'");
				return;
			}
			try {
				graph.getGraph().removeEdge(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]));
				results.get("remove edge").setText("the edge has been removed from src:" + (int) Double.parseDouble(parts[0]) +" to dest: " + (int)Double.parseDouble(parts[1]) );
			}
			catch (Exception ignored) {

				results.get("remove edge").setText("Something bad happened.");
			}
		});
	}

	private void add_action_remove_node(){

		btns.get("remove node").addActionListener(e -> {
			String str = inputs.get("remove node").getText();
			String[] parts = str.split("[\\D]+", 0);
			if ( parts.length != 1 ) {
				results.get("remove node").setText("Please enter node id to remove");
				return;
			}
			try {
				graph.getGraph().removeNode(Integer.parseInt(parts[0]));
				results.get("remove node").setText("node: " +Integer.parseInt(parts[0] + "has been removed"));
			}
			catch (Exception ignored) {

				results.get("remove node").setText("Something bad happened.");
			}
		});
	}


	
	private void add_action_add_edge() {
		btns.get("add edge").addActionListener(e -> {
			String str = inputs.get("add edge").getText();
			String[] parts = str.split("[^\\d.]+", 0);
			if ( parts.length != 3 ) {
				results.get("add edge").setText("Please enter 'src,dest,weight' nodes numbers  like: '1,2,3.343'");
				return;
			}
			try {
				EdgeData edge = new Edge((int) Double.parseDouble(parts[0]), (int)Double.parseDouble(parts[1]) ,Double.parseDouble(parts[2]));
				graph.getGraph().connect(edge.getSrc(), edge.getDest() ,edge.getWeight());
				results.get("add edge").setText("the edge has been added from src:" + (int) Double.parseDouble(parts[0]) +" to dest: " + (int)Double.parseDouble(parts[1]) + " with wight:" + Double.parseDouble(parts[2]));
			}
			catch (Exception ignored) {

				results.get("add edge").setText("Something bad happened.");
			}
		});
	}

	private void add_action_add_node() {
		btns.get("add node").addActionListener(e -> {
			String str = inputs.get("add node").getText();
			results.get("add node").setText("");
			try {
				NodeData vertex = new Vertex(graph.getGraph().nodeSize()-1);
				graph.getGraph().addNode(vertex);
				results.get("add node").setText("the node:"+(graph.getGraph().nodeSize()) + "has been added");
			}
			catch (Exception ex) {
				results.get("add node").setText("something bad happened");
			}
		});
	}

	private void add_action_to_algo_tsp() {
		btns.get("tsp").addActionListener(e -> {
			String str = inputs.get("tsp").getText();
			results.get("tsp").setText("");
			try {
				List<NodeData> lst = getListForTsp(str);
				List<NodeData> result = graph.tsp(lst);
				if ( result == null ) {
					results.get("tsp").setText("No path could found");
					return;
				}
				for (NodeData node : result) {
					results.get("tsp").setText(results.get("tsp").getText() + ", " + node.getKey());
				}
				results.get("tsp").setText(results.get("tsp").getText().substring(2));
			}
			catch (Exception ex) {
				results.get("tsp").setText("something bad happened");
			}
		});
	}
	
	private List<NodeData> getListForTsp(String str) {
		String[] parts = str.split("[\\D]+", 0);
		int[] node_values = new int[parts.length];
		for (int i = 0; i < parts.length; i++) {
			node_values[i] = Integer.parseInt(parts[i]);
		}
		List<NodeData> lst = new LinkedList<>();
		for (int node_value : node_values) {
			NodeData node = graph.getGraph().getNode(node_value);
			lst.add(node);
		}
		return lst;
	}
	//endregion
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String host = lbl_status.getText();
			
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
	}
}
