package api;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class Graph implements DirectedWeightedGraph {
    private HashMap<Integer, HashMap<Integer, EdgeData>> edges;
    private HashMap<Integer, NodeData> vertices;
    private HashMap<Integer, HashMap<Integer, EdgeData>> oppositeEdges;
    private int numofvertices = 0;
    private int numofedges = 0;
    private int counterMc = 0;

    //CONSTRUCTOR
    public Graph() {
        this.numofvertices = 0;
        this.numofedges = 0;
        this.counterMc = 0;
        this.vertices = new HashMap<Integer, NodeData>();
        this.edges = new HashMap<Integer, HashMap<Integer, EdgeData>>();
        this.oppositeEdges = new HashMap<Integer, HashMap<Integer, EdgeData>>();

    }

    public Graph(DirectedWeightedGraph copyGraph) {
        NodeData n = null;
        NodeData n2 = null;
        EdgeData e = null;
        double weight = 0.0;
        this.vertices = new HashMap<Integer, NodeData>();
        this.edges = new HashMap<Integer, HashMap<Integer, EdgeData>>();
        this.oppositeEdges = new HashMap<Integer, HashMap<Integer, EdgeData>>();
        for (Iterator <NodeData> n1 = copyGraph.nodeIter(); n1.hasNext();) {
            n = n1.next();
            NodeData tempNode = new Vertex(n);
            this.vertices.put(tempNode.getKey(), tempNode);
            HashMap tempMap = new HashMap<Integer, EdgeData>();
            HashMap tempMap2 = new HashMap<Integer, EdgeData>();
            this.edges.put(n.getKey(), tempMap);
            this.oppositeEdges.put(n.getKey(), tempMap2);
        }
        for (Iterator <NodeData> n1 = copyGraph.nodeIter(); n1.hasNext();) {
            n2 = n1.next();
            for (Iterator<EdgeData> e2 = copyGraph.edgeIter(n2.getKey());e2.hasNext();) {
                e = e2.next();
                EdgeData copyE = new Edge(e);
                this.connect(copyE.getSrc(), copyE.getDest(), copyE.getWeight());
            }
        }
        this.numofvertices = copyGraph.nodeSize();
        this.numofedges = copyGraph.edgeSize();
        this.counterMc= copyGraph.getMC();
    }

    @Override
    ///This function we put an id of the node and it returns us all the data of the node
    public NodeData getNode(int key) {
        return vertices.get(key);
    }


    @Override
    public EdgeData getEdge(int src, int dest) {//Should be O(1)
        return edges.get(src).get(dest);
    }

    @Override
    public void addNode(NodeData n) {//Should be O(1)
        /*check this vertex is not exsist yet*/
        if (!this.vertices.containsKey(n.getKey())){
            /*init the vertex to the hashmap*/
            this.vertices.put(n.getKey(), n);
            /*
             * initializing the edge hash with the vertex id
             * pot an empty hash with him
             * do it also for the opposite edge
             * we save this data of opposite edge for easier delete a vertex
             */
            if (!this.edges.containsKey(n.getKey())) {
                HashMap edgeHashmap = new HashMap<Integer, Edge>();
                HashMap oppositeEdgeHashmap = new HashMap<Integer, Edge>();
                this.edges.put(n.getKey(), edgeHashmap);
                this.oppositeEdges.put(n.getKey() , oppositeEdgeHashmap);
            }
            this.numofvertices++;
            this.counterMc++;

        }
    }
    @Override
    public void connect(int src, int dest, double w) {//Should be O(1)
        /* check the src and the dst are not the same vertex */
        if(src != dest){
            /* check if the src and the dst are exsisting vertex */
            if (this.vertices.containsKey(src) && this.vertices.containsKey(dest)){
                /* check if allready existing edge */
                if (!this.edges.get(src).containsKey(dest)){
                    /* create the new edge and the opposite*/
                    Edge newEdge = new Edge(src , dest , w);
                    Edge oppositEdge = new Edge(dest , src , w);
                    /* update them in the hashes */
                    this.edges.get(src).put(dest , newEdge);
                    this.oppositeEdges.get(dest).put(src , oppositEdge);
                    this.numofedges++;
                    this.counterMc++;
                }
            }
        }
    }
    @Override
    public Iterator<NodeData> nodeIter() {
        return this.vertices.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        HashMap<Integer, EdgeData> allEdges = new HashMap<Integer, EdgeData>();
        int i=0;
        for (HashMap<Integer, EdgeData> e:this.edges.values()) {
            for (EdgeData n:e.values()) {
                allEdges.put(i,n);
                i++;
            }
        }
        return allEdges.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return this.edges.get(node_id).values().iterator();
    }

    //Functions that remove data from the graph
    @Override //removeNode should be in O(1)
    public NodeData removeNode(int key) {
        /* check if the vertex exsist */
        if (this.vertices.containsKey(key)){
            /*
             * delete all the edges are there dst is the deleted vertex
             * we do it easyly becuse we save all the opposite edge so they all be in the vertex id key in the oppositeEdges hash
             */
            for (Iterator<EdgeData> it = edgeIter(key); it.hasNext(); ){
                EdgeData n = it.next();
                oppositeEdges.remove(n.getDest());
                this.numofedges--;
                this.counterMc++;

            }
            this.edges.get(key).clear();
            /* delete all the edges are there src is the deleted vertex*/
            for (Iterator<EdgeData> it = edgeIter(key); it.hasNext(); ){
                EdgeData n = it.next();
                edges.remove(n.getDest());
                this.numofedges--;
                this.counterMc++;
            }
            this.oppositeEdges.get(key).clear();
            this.edges.remove(key);
            this.oppositeEdges.remove(key);
            /* save the vertex for returning */
            NodeData deleted = vertices.get(key);
            this.vertices.remove(key);
            this.numofvertices--;
            this.counterMc++;
            return deleted;
        }
        return null;
    }

    @Override//removeEdge should be in O(1)
    public EdgeData removeEdge(int src, int dest) {
        EdgeData e = null;
        if (this.edges.get(src).containsKey(dest)){
            e = this.edges.get(src).remove(dest);
            this.edges.get(src).remove(dest);
            this.oppositeEdges.get(dest).remove(src);
            this.numofedges--;
            this.counterMc++;
        }
        return e;
    }

    /**
     * Returns the number of vertices (nodes) in the graph.
     * Note: this method should run in O(1) time.
     *
     * @return
     */
    @Override
    public int nodeSize() {
        return this.numofvertices;
    }

    /**
     * Returns the number of edges (assume directional graph).
     * Note: this method should run in O(1) time.
     *
     * @return
     */
    @Override
    public int edgeSize() {
        return this.numofedges;
    }

    @Override
    public int getMC() {
        return this.counterMc;
    }





}