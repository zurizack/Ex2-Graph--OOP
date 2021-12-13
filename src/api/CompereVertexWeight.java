package api;

import java.util.Comparator;

public class CompereVertexWeight implements Comparator<NodeData>{
    @Override

    /***
     * compere between two vertex by there weight
     * if a bigger return 1
     * if b bigger return -1
     * if equals return 0
     */

    public int compare(NodeData a, NodeData b) {
        return Double.compare(a.getWeight(),b.getWeight()) ;
    }
}
