package api;

//import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



class AlgoTest {


    public static DirectedWeightedGraph graph;
    public static DirectedWeightedGraph empty = new Graph();
    public static DirectedWeightedGraphAlgorithms graphAlgo;



    @Test
    void init() {

        this.graphAlgo = new Algo();
        this.graphAlgo.init(graph);

        assertEquals(19,graphAlgo.getGraph().edgeSize());
        assertEquals(17,graphAlgo.getGraph().nodeSize());
        assertEquals(graph.getMC(),graphAlgo.getGraph().getMC());

        graphAlgo.init(empty);

        assertEquals(0,graphAlgo.getGraph().edgeSize());
        assertEquals(0,graphAlgo.getGraph().nodeSize());
        assertEquals(empty.getMC(),graphAlgo.getGraph().getMC());
    }

    @Test
    void getGraph() {

        this.graphAlgo = new Algo();
        this.graphAlgo.init(graph);

        assertEquals(graph,graphAlgo.getGraph());
        assertEquals(graph.getMC(),graphAlgo.getGraph().getMC());
        assertEquals(graph.nodeSize(),graphAlgo.getGraph().nodeSize());
        assertEquals(graph.edgeSize(),graphAlgo.getGraph().edgeSize());

        graphAlgo.init(empty);

        assertEquals(empty,graphAlgo.getGraph());
        assertEquals(empty.getMC(),graphAlgo.getGraph().getMC());
        assertEquals(empty.nodeSize(),graphAlgo.getGraph().nodeSize());
        assertEquals(empty.edgeSize(),graphAlgo.getGraph().edgeSize());
    }

    @Test
    void copy() {
        graphAlgo.init(empty);
        DirectedWeightedGraph copy = graphAlgo.copy();
        assertEquals(empty.edgeSize(),copy.edgeSize());
        assertEquals(empty.getMC(),copy.getMC());
        assertEquals(empty.nodeSize(),copy.nodeSize());
        assertNotEquals(graph,copy);

        graphAlgo.init(graph);
        copy = graphAlgo.copy();
        assertEquals(graph.nodeSize(),copy.nodeSize());
        assertEquals(graph.getMC(),copy.getMC());
        assertEquals(graph.edgeSize(),copy.edgeSize());
        assertNotEquals(graph, copy);


    }

    @Test
    void isConnected() {



        this.graphAlgo = new Algo();
        this.graphAlgo.init(graph);

        assertTrue(graphAlgo.isConnected());
        graphAlgo.getGraph().removeEdge(14,15);
        assertFalse(graphAlgo.isConnected());
        graph.connect(14,15,1);
        assertTrue(graphAlgo.isConnected());
        NodeData n = new Vertex(18);
        graphAlgo.getGraph().addNode(n);
        assertFalse(graphAlgo.isConnected());
        graph.connect(18,1,5);
        graph.connect(14,18,3);
        assertTrue(graphAlgo.isConnected());




    }

    @Test
    void BFSalgo() {
    }

    @Test
    void shortestPathDist() {



        this.graphAlgo = new Algo();



        graphAlgo.init(graph);
        assertEquals(8,graphAlgo.shortestPathDist(0,7));
        assertEquals(808,graphAlgo.shortestPathDist(7,0),"expected 808");
        graphAlgo.getGraph().connect(7,0,1);
        assertEquals(1,graphAlgo.shortestPathDist(7,0),"shoud be change to 1");
        assertEquals(0,graphAlgo.shortestPathDist(7,7),"expected 0 same vertex");
        assertEquals(2,graphAlgo.shortestPathDist(3,5));
        assertEquals(408,graphAlgo.shortestPathDist(3,16),"expected 408");
        assertEquals(8,graphAlgo.shortestPathDist(0,7));
        assertEquals(-1,graphAlgo.shortestPathDist(0,18),"expected -1 there no VERTEX 18");



    }

    @Test
    void shortestPath() {

        this.graphAlgo = new Algo();
        this.graphAlgo.init(graph);

        double path = graphAlgo.shortestPathDist(1,8);
        double temp = 0;
        List<NodeData> thepath = graphAlgo.shortestPath(1,8);
        for (int i = 0; i < thepath.size(); i++) {
            System.out.print(","+thepath.get(i).getKey());
        }

        for (int i = 0; i < thepath.size()-1; i++) {
            temp += graphAlgo.getGraph().getEdge(thepath.get(i).getKey(),thepath.get(i+1).getKey()).getWeight();
        }
        assertEquals(temp,path);
    }

    @Test
    void center() {





        assertEquals(5,graphAlgo.center().getKey());

        NodeData n = new Vertex(17);

        graphAlgo.getGraph().addNode(n);
        for (int i = 0; i < 18 && i !=17; i++) {
            graphAlgo.getGraph().connect(17 , i,0.5);
            graphAlgo.getGraph().connect(i ,17,0.5);
        }

        assertEquals(17,graphAlgo.center().getKey());
        graphAlgo.getGraph().removeNode(17);

    }

    @Test
    void tsp() {

        this.graphAlgo = new Algo();
        this.graphAlgo.init(graph);
        NodeData a = graphAlgo.getGraph().getNode(1);
        NodeData b = graphAlgo.getGraph().getNode(3);
        NodeData c = graphAlgo.getGraph().getNode(2);
        List<NodeData> cities = new LinkedList<>();
        cities.add(a);
        cities.add(b);
        cities.add(c);
        List<NodeData> cities1 = new LinkedList<>();
        cities1 = graphAlgo.tsp(cities);
        assertEquals(cities.get(0).getKey(),cities1.get(0).getKey());
        assertEquals(cities.get(1).getKey(),cities1.get(2).getKey());
        assertEquals(cities.get(2).getKey(),cities1.get(1).getKey());
        this.graphAlgo.init(graph);
        
    }

    @Test
    void loadsave() {
        this.graphAlgo = new Algo();
        this.graphAlgo.init(graph);
        graphAlgo.save("test graph");
        DirectedWeightedGraphAlgorithms temp = new Algo();
        temp.load("test graph");
        //assertEquals(temp.getGraph(),graphAlgo.getGraph());


    }

    @BeforeEach
    void createGraphs () {
        graph = new Graph();

        for (int i = 0; i < 17; i++) {
            NodeData a = new Vertex(i);
            graph.addNode(a);
        }
        graph.connect(0,1,1);

        graph.connect(1,2,2);

        graph.connect(2,3,1);

        graph.connect(3,4,1);

        graph.connect(4,5,1);

        graph.connect(5,6,1);

        graph.connect(5,0,2);

        graph.connect(5,10,1);

        graph.connect(5,17,1);

        graph.connect(6,7,1);

        graph.connect(7,8,1);

        graph.connect(8,9,1);

        graph.connect(9,10,1);

        graph.connect(10,11,1);

        graph.connect(11,12,1);

        graph.connect(12,13,1);

        graph.connect(13,14,1);

        graph.connect(14,15,1);

        graph.connect(15,16,400);

        graph.connect(16,0,400);

    }

}