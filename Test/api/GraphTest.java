package api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void getNode() {
    }

    @Test
    void getEdge() {
    }

    @Test
    void addNode() {
    }

    @Test
    void connect() {
    }

    @Test
    void nodeIter() {
    }

    @Test
    void edgeIter() {
    }

    @Test
    void testEdgeIter() {
    }

    @Test
    void removeNode() {
        DirectedWeightedGraphAlgorithms a = new Algo();
        DirectedWeightedGraph b = new Graph();
        a.init(b);
        a.load("G1.json");
        System.out.println("before: " + a.getGraph().getEdge(3,1));
        a.getGraph().removeNode(3);
        System.out.println("after: "+a.getGraph().getEdge(2,3).getSrc());
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