package api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {
    private static DirectedWeightedGraph g;



    @BeforeEach
     void createGraphs() {
//        start=new Date().getTime();
        g = new Graph();
        for (int i = 0; i < 12; i++) {
            NodeData n = new Vertex(i);
            g.addNode(n);
        }
        g.connect(1, 8, 5);
        g.connect(1, 3, 2);
        g.connect(2, 4, 1);
        g.connect(5, 6, 4);
        g.connect(11, 10, 3);
        g.connect(6, 7, 3);
        g.connect(7, 8, 2);
        g.connect(0, 1, 3);
        g.connect(8, 9, 2);
        g.connect(1, 2, 1);
        g.connect(4, 2, 1);

    }

    @Test
    void getSrc() {
        assertEquals(1, g.getEdge(1, 3).getSrc());
        assertEquals(2, g.getEdge(2, 4).getSrc());
        assertEquals(0, g.getEdge(0, 1).getSrc());
        assertNotEquals(1, g.getEdge(8, 9).getSrc());

    }

    @Test
    void getDest() {
        assertEquals(1, g.getEdge(0, 1).getDest());
        assertEquals(4, g.getEdge(2, 4).getDest());
        assertEquals(6, g.getEdge(5, 6).getDest());
        assertNotEquals(1, g.getEdge(8, 9).getDest());
    }

    @Test
    void getWeight() {
        assertEquals(5, g.getEdge(1, 8).getWeight());
        assertEquals(2, g.getEdge(7, 8).getWeight());
        assertEquals(1, g.getEdge(1, 2).getWeight());
        assertEquals(5, g.getEdge(1, 8).getWeight());
        assertNotEquals(7, g.getEdge(1, 3).getWeight());
    }

    @Test
    void getInfo() {
        g.getEdge(8, 9).setInfo("8,9");
        g.getEdge(1, 2).setInfo("1,2");
        g.getEdge(1, 8).setInfo("1,8");
        assertEquals("8,9", g.getEdge(8, 9).getInfo());
        assertEquals("1,2", g.getEdge(1, 2).getInfo());
        assertNotEquals("12,19", g.getEdge(1, 8).getInfo());


    }

    @Test
    void setInfo() {
        g.getEdge(8, 9).setInfo("8,9");
        g.getEdge(1, 2).setInfo("1,2");
        g.getEdge(1, 8).setInfo("1,8");
        assertEquals("8,9", g.getEdge(8, 9).getInfo());
        assertEquals("1,2", g.getEdge(1, 2).getInfo());
        assertNotEquals("12,19", g.getEdge(1, 8).getInfo());
    }

    @Test
    void getTag() {
        g.getNode(1).setTag(-1);
        g.getNode(5).setTag(2);
        g.getNode(10).setTag(14);
        g.getNode(6).setTag(3);
        g.getNode(7).setTag(1);
        assertEquals(-1, g.getNode(1).getTag());
        assertEquals(2, g.getNode(5).getTag());
        assertEquals(14, g.getNode(10).getTag());
        assertEquals(3, g.getNode(6).getTag());
        assertEquals(1, g.getNode(7).getTag());
    }

    @Test
    void setTag() {
        g.getNode(1).setTag(-1);
        g.getNode(5).setTag(2);
        g.getNode(11).setTag(14);
        g.getNode(6).setTag(3);
        g.getNode(7).setTag(1);
        assertEquals(-1, g.getNode(1).getTag());
        assertEquals(2, g.getNode(5).getTag());
        assertEquals(14, g.getNode(11).getTag());
        assertEquals(3, g.getNode(6).getTag());
        assertEquals(1, g.getNode(7).getTag());
    }
}