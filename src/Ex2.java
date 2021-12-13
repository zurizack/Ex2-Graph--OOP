import gui.MyPanel;
import api.*;

import gui.*;


/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {


    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        DirectedWeightedGraph ans = new Graph();
        // ****** Add your code here ******
        //
        // ********************************
        return ans;
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms ans = new Algo();
        ans.init(getGrapg(json_file));
        ans.load(json_file);
        //ans.save(json_file);
        // ****** Add your code here ******
        //
        // ********************************
        return ans;
    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        new MyPanel(alg.getGraph());
        new FrameExtend(alg.getGraph());
        // ****** Add your code here ******
        //
        // ********************************
    }

    public static void main(String[]args){

        DirectedWeightedGraphAlgorithms a = getGrapgAlgo("G2.json");
        System.out.println(a.isConnected());
        System.out.println(a.shortestPathDist(0,1));
        System.out.println(a.getGraph().getNode(0).getKey());
        runGUI("G2.json");
//       DirectedWeightedGraphAlgorithms a = getGrapgAlgo("G1.json");
//
//        System.out.println(a.isConnected());
//        System.out.println(a.shortestPathDist(1,10));
//        System.out.println(a.getGraph().getNode(0).getKey());
//        List<NodeData> list = a.shortestPath(0,3);
//        System.out.println(Arrays.toString(list.toArray()));
//        NodeData b = a.center();
//        int d = b.getKey();
//        System.out.println(d);
//        new MyPanel(a.getGraph());

    }
}

