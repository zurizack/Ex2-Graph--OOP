package api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    private static DirectedWeightedGraph graph;
    private static DirectedWeightedGraph graph2;
    private static DirectedWeightedGraph graph3;
    private static NodeData node;
    private static EdgeData edge;
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
    @Test
    void getNode() {
        graph2 = new Graph();
        assertEquals(null,graph2.getNode(1));
        assertEquals(null,graph2.getNode(3));
        assertEquals(12,graph.getNode(12).getKey());
        assertEquals(11,graph.getNode(11).getKey());
        assertEquals(13,graph.getNode(13).getKey());

    }

    @Test
    void getEdge() {
        graph2 = new Graph();
        assertEquals(null,graph2.getEdge(1,2));
        assertEquals(null,graph2.getEdge(14,15));
        assertEquals(null,graph2.getEdge(4,3));
        assertEquals(400,graph.getEdge(15,16).getWeight());
        assertEquals(400,graph.getEdge(16,0).getWeight());
        assertEquals(1,graph.getEdge(13,14).getWeight());
        assertEquals(1,graph.getEdge(8,9).getWeight());


    }

    @Test
    void addNode() {
        assertEquals(17, graph.nodeSize());
        NodeData n = new Vertex(50);
        graph.addNode(n);
        assertEquals(18, graph.nodeSize());
        assertFalse(graph.getNode(n.getKey()) == null);
        for (int i = 0; i < 10; i++) {
            n = new Vertex(90 + i);
            graph.addNode(n);
        }
        assertNotEquals(13, graph.nodeSize());
        assertEquals(28, graph.nodeSize());
    }

    @Test
    void connect() {
        graph.connect(2,3,1);
        graph.connect(4,6,1.3);
        graph.connect(6,7,1);
        graph.connect(8,4,9.01);
        assertEquals(1,graph.getEdge(2,3).getWeight());
        assertEquals(1.3,graph.getEdge(4,6).getWeight());
        assertEquals(1,graph.getEdge(6,7).getWeight());
        assertEquals(9.01,graph.getEdge(8,4).getWeight());

    }

    @Test
    void nodeIter() {
        node = new Vertex();
        graph2 = new Graph();
        graph3 = new Graph();
        assertEquals(false,graph2.nodeIter().hasNext());
        graph3.addNode(node);
        assertEquals(true,graph3.nodeIter().hasNext());
        graph3.removeNode(node.getKey());
        assertEquals(0,graph3.nodeSize());

    }

    @Test
    void edgeIter() {
        node = new Vertex();
        graph2 = new Graph();
        graph3 = new Graph();
        assertEquals(false,graph2.nodeIter().hasNext());
        graph3.addNode(node);
        assertEquals(true,graph3.nodeIter().hasNext());
        graph3.removeNode(node.getKey());
        assertEquals(0,graph3.nodeSize());

    }

    @Test
    void testEdgeIter() { //todo failed test , make another one
        edge = new Edge();
        graph2 = new Graph();
        graph3 = new Graph();
        assertEquals(false,graph2.edgeIter().hasNext());
//        graph3.connect(1,2,1);
//        graph3.connect(2,3,1);
//        assertEquals(false,graph3.edgeIter().hasNext());
        graph3.removeEdge(1,2);
        assertEquals(0,graph3.nodeSize());

    }

    @Test
    void removeNode() {
        int size = graph.nodeSize();
        graph.removeNode(1);
        int new_size = graph.nodeSize();
        assertEquals(size-1,new_size);
    }

    @Test
    void removeEdge() {
    }

    @Test
    void nodeSize() {
    }

    @Test
    void edgeSize() {
    }

    @Test
    void getMC() {
    }

}