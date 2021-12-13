package api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.support.hierarchical.Node;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    private static DirectedWeightedGraph graph;
    private static DirectedWeightedGraph graph2;
    private static DirectedWeightedGraph graph3;
    private static NodeData node;
    private static EdgeData edge;

    @BeforeEach
    void createGraphs() {
        graph = new Graph();

        for (int i = 0; i < 17; i++) {
            NodeData a = new Vertex(i);
            graph.addNode(a);
        }
//        graph.connect(0,1,1);
//
//        graph.connect(1,2,2);
//
//        graph.connect(2,3,1);
//
//        graph.connect(3,4,1);
//
//        graph.connect(4,5,1);
//
//        graph.connect(5,6,1);
//
//        graph.connect(5,0,2);
//
//        graph.connect(5,10,1);
//
//        graph.connect(5,17,1);
//
//        graph.connect(6,7,1);
//
//        graph.connect(7,8,1);
//
//        graph.connect(8,9,1);
//
//        graph.connect(9,10,1);
//
//        graph.connect(10,11,1);
//
//        graph.connect(11,12,1);
//
//        graph.connect(12,13,1);
//
//        graph.connect(13,14,1);
//
//        graph.connect(14,15,1);
//
//        graph.connect(15,16,400);
//
//        graph.connect(16,0,400);
    }

    @Test
    void getNode() {
        graph2 = new Graph();
        NodeData t = new Vertex(1);
        graph2.addNode(t);
        assertEquals(t, graph2.getNode(1));

    }

    @Test
    void getEdge() {
        graph2 = new Graph();
        NodeData t1 = new Vertex(1);
        NodeData t2 = new Vertex(2);
        graph2.addNode(t1);
        graph2.addNode(t2);
        graph2.connect(t1.getKey(), t2.getKey(), 4);
        EdgeData e = new Edge(1, 2, 4);
        assertEquals(e.getSrc(), graph2.getEdge(t1.getKey(), t2.getKey()).getSrc());
        assertEquals(e.getDest(), graph2.getEdge(t1.getKey(), t2.getKey()).getDest());
        assertEquals(e.getWeight(), graph2.getEdge(t1.getKey(), t2.getKey()).getWeight());

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
        graph.connect(2, 3, 1);
        graph.connect(4, 6, 1.3);
        graph.connect(6, 7, 1);
        graph.connect(8, 4, 9.01);
        assertEquals(1, graph.getEdge(2, 3).getWeight());
        assertEquals(1.3, graph.getEdge(4, 6).getWeight());
        assertEquals(1, graph.getEdge(6, 7).getWeight());
        assertEquals(9.01, graph.getEdge(8, 4).getWeight());

    }

    @Test
    void nodeIter() {
        node = new Vertex();
        graph2 = new Graph();
        graph3 = new Graph();
        assertEquals(false, graph2.nodeIter().hasNext());
        graph3.addNode(node);
        assertEquals(true, graph3.nodeIter().hasNext());
        graph3.removeNode(node.getKey());
        assertEquals(0, graph3.nodeSize());

    }

    @Test
    void edgeIter() {
        EdgeData edge = new Edge(20, 21, 4);
        NodeData node = new Vertex(20);
        NodeData node1 = new Vertex(21);
        graph.addNode(node);
        graph.addNode(node1);
        graph.connect(node.getKey(), node1.getKey(), 4);
        Iterator<EdgeData> m = graph.edgeIter();
        List<EdgeData> l = new LinkedList<>();
        l.add(edge);
        EdgeData a = m.next();
        assertEquals(l.get(0).getSrc(), a.getSrc());
        assertEquals(l.get(0).getDest(), a.getDest());
        assertEquals(l.get(0).getWeight(), a.getWeight());


    }

    @Test
    void testEdgeIter() {
        EdgeData edge = new Edge(20, 21, 4);
        NodeData node = new Vertex(20);
        NodeData node1 = new Vertex(21);
        graph.addNode(node);
        graph.addNode(node1);
        graph.connect(node.getKey(), node1.getKey(), 4);
        Iterator<EdgeData> m = graph.edgeIter(20);
        List<EdgeData> l = new LinkedList<>();
        l.add(edge);
        EdgeData a = m.next();
        assertEquals(l.get(0).getSrc(), a.getSrc());
        assertEquals(l.get(0).getDest(), a.getDest());
        assertEquals(l.get(0).getWeight(), a.getWeight());


    }

    @Test
    void removeNode() {
        graph2 = new Graph();
        NodeData e = new Vertex(1);
        graph2.addNode(e);
        assertEquals(e, graph2.removeNode(1));
    }

    @Test
    void removeEdge() {
        graph2 = new Graph();
        EdgeData e = new Edge(1, 2, 3);
        NodeData n1 = new Vertex(1);
        NodeData n2 = new Vertex(2);
        graph2.addNode(n1);
        graph2.addNode(n2);
        graph2.connect(1, 2, 3);
        EdgeData a = graph2.removeEdge(1, 2);
        assertEquals(e.getSrc(), a.getSrc());
        assertEquals(e.getDest(), a.getDest());

    }

    @Test
    void nodeSize() {
        graph2 = new Graph();
        NodeData n1 = new Vertex(1);
        NodeData n2 = new Vertex(2);
        NodeData n3 = new Vertex(3);
        graph2.addNode(n1);
        graph2.addNode(n2);
        graph2.addNode(n3);
        assertEquals(3, graph2.nodeSize());
    }

    @Test
    void edgeSize() {
        graph2 = new Graph();
        NodeData n1 = new Vertex(1);
        NodeData n2 = new Vertex(2);
        graph2.addNode(n1);
        graph2.addNode(n2);
        graph2.connect(n1.getKey(), n2.getKey(), 3);
        assertEquals(1, graph2.edgeSize());


    }

    @Test
    void getMC() {
        graph2 = new Graph();
        NodeData n1 = new Vertex(1);
        NodeData n2 = new Vertex(2);
        graph2.addNode(n1);
        graph2.addNode(n2);
        graph2.connect(n1.getKey(), n2.getKey(), 3);
        assertEquals(3, graph2.getMC());


    }

}