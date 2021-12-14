package api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeoLocTest {
    public static DirectedWeightedGraph g;
    public static GeoLoc p;



    @BeforeEach
     void createGraphs(){
//        start=new Date().getTime();
        g = new Graph();
        for(int i=0;i< 12;i++){
            NodeData n = new Vertex(i) ;
            g.addNode(n);
        }
        g.connect(1,8,5);
        g.connect(1,3,2);
        g.connect(2,4,1);
        g.connect(5,6,4);
        g.connect(11,10,3);
        g.connect(6,7,3);
        g.connect(7,8,2);
        g.connect(0,1,3);
        g.connect(8,9,2);
        g.connect(1,2,1);
        g.connect(4,2,1);

    }

    @Test
    void x() {
        p = new GeoLoc(13,12,11);
        p.x = 13;
        p.y = 12;
        p.z = 11;
        System.out.println("G.key of 1 "+g.getNode(1));
        g.getNode(1).setLocation(p);
        assertEquals(13, g.getNode(1).getLocation().x());
        assertNotEquals(12, g.getNode(1).getLocation().x());


    }

    @Test
    void y() {
        p = new GeoLoc(13,12,11);
        p.x = 13;
        p.y = 12;
        p.z = 11;
        g.getNode(1).setLocation(p);
        assertEquals(12, g.getNode(1).getLocation().y());
        assertNotEquals(87, g.getNode(1).getLocation().y());
    }

    @Test
    void z() {
        p = new GeoLoc(13,12,11);
        p.x = 13;
        p.y = 12;
        p.z = 11;
        g.getNode(1).setLocation(p);
        assertEquals(11, g.getNode(1).getLocation().z());
        assertNotEquals(874, g.getNode(1).getLocation().z());
    }

    @Test
    void distance() {
        p = new GeoLoc(13,12,11);
        double x = 6;
        double y = 7;
        double z = 8;
        p.x = 13;
        p.y = 12;
        p.z = 11;
        g.getNode(1).setLocation(p);
        assertEquals(0,Math.floor(g.getNode(1).getLocation().distance(p)));

    }
}