package api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VertexTest {
    private static DirectedWeightedGraph graph;
    private static GeoLocation g;
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
    void getKey() {
        assertEquals(1,graph.getNode(1).getKey());
        assertEquals(3,graph.getNode(3).getKey());
        assertEquals(7,graph.getNode(7).getKey());
        assertEquals(6,graph.getNode(6).getKey());
        assertEquals(4,graph.getNode(4).getKey());
        assertEquals(2,graph.getNode(2).getKey());
    }

    @Test
    void getLocation() {
        g = new GeoLoc(13,12,11);
        graph.getNode(1).setLocation(g);
        assertEquals(13,graph.getNode(1).getLocation().x());
        assertEquals(12,graph.getNode(1).getLocation().y());
        assertEquals(11,graph.getNode(1).getLocation().z());
        assertNotEquals(98,graph.getNode(1).getLocation().x());
    }

    @Test
    void setLocation() {
        g = new GeoLoc(14,15,16);
        graph.getNode(1).setLocation(g);
        assertEquals(14,graph.getNode(1).getLocation().x());
        assertEquals(15,graph.getNode(1).getLocation().y());
        assertEquals(16,graph.getNode(1).getLocation().z());
        assertNotEquals(98,graph.getNode(1).getLocation().x());

    }

    @Test
    void getWeight() {
        graph.getNode(1).setWeight(1.67);
        graph.getNode(2).setWeight(1.55);
        graph.getNode(3).setWeight(1.78);
        graph.getNode(4).setWeight(1.12);
        graph.getNode(5).setWeight(1.32);
        assertEquals(1.67,graph.getNode(1).getWeight());
        assertEquals(1.55,graph.getNode(2).getWeight());
        assertEquals(1.78,graph.getNode(3).getWeight());
        assertEquals(1.12,graph.getNode(4).getWeight());
        assertEquals(1.32,graph.getNode(5).getWeight());

    }

    @Test
    void setWeight() {
        graph.getNode(1).setWeight(1.67);
        graph.getNode(2).setWeight(1.55);
        graph.getNode(3).setWeight(1.78);
        graph.getNode(4).setWeight(1.12);
        graph.getNode(5).setWeight(1.32);
        assertEquals(1.67,graph.getNode(1).getWeight());
        assertEquals(1.55,graph.getNode(2).getWeight());
        assertEquals(1.78,graph.getNode(3).getWeight());
        assertEquals(1.12,graph.getNode(4).getWeight());
        assertEquals(1.32,graph.getNode(5).getWeight());
    }

    @Test
    void getInfo() {
        graph.getNode(1).setInfo("node1");
        graph.getNode(2).setInfo("node2");
        graph.getNode(3).setInfo("node3");
        graph.getNode(4).setInfo("node4");
        graph.getNode(5).setInfo("node5");
        graph.getNode(6).setInfo("node6");
        assertEquals("node1",graph.getNode(1).getInfo());
        assertEquals("node2",graph.getNode(2).getInfo());
        assertEquals("node3",graph.getNode(3).getInfo());
        assertEquals("node4",graph.getNode(4).getInfo());
        assertEquals("node5",graph.getNode(5).getInfo());
        assertEquals("node6",graph.getNode(6).getInfo());
        assertNotEquals("node1yanaoskin",graph.getNode(1).getInfo());
    }

    @Test
    void setInfo() {
        graph.getNode(1).setInfo("node1");
        graph.getNode(2).setInfo("node2");
        graph.getNode(3).setInfo("node3");
        graph.getNode(4).setInfo("node4");
        graph.getNode(5).setInfo("node5");
        graph.getNode(6).setInfo("node6");
        assertEquals("node1",graph.getNode(1).getInfo());
        assertEquals("node2",graph.getNode(2).getInfo());
        assertEquals("node3",graph.getNode(3).getInfo());
        assertEquals("node4",graph.getNode(4).getInfo());
        assertEquals("node5",graph.getNode(5).getInfo());
        assertEquals("node6",graph.getNode(6).getInfo());
        assertNotEquals("node1yanaoskin",graph.getNode(1).getInfo());
    }

    @Test
    void getTag() {
        graph.getNode(1).setTag(1);
        graph.getNode(2).setTag(2);
        graph.getNode(3).setTag(3);
        graph.getNode(4).setTag(4);
        graph.getNode(5).setTag(5);
        assertEquals(1,graph.getNode(1).getTag());
        assertEquals(2,graph.getNode(2).getTag());
        assertEquals(3,graph.getNode(3).getTag());
        assertEquals(4,graph.getNode(4).getTag());
        assertEquals(5,graph.getNode(5).getTag());
        assertNotEquals(14,graph.getNode(1).getTag());



    }

    @Test
    void setTag() {
        graph.getNode(1).setTag(1);
        graph.getNode(2).setTag(2);
        graph.getNode(3).setTag(3);
        graph.getNode(4).setTag(4);
        graph.getNode(5).setTag(5);
        assertEquals(1,graph.getNode(1).getTag());
        assertEquals(2,graph.getNode(2).getTag());
        assertEquals(3,graph.getNode(3).getTag());
        assertEquals(4,graph.getNode(4).getTag());
        assertEquals(5,graph.getNode(5).getTag());
        assertNotEquals(14,graph.getNode(1).getTag());
    }
}