package api;

import com.google.gson.Gson;

import java.io.File;

public class Vertex implements NodeData {
    int id;
    GeoLocation location;
    double weight;
    String info;
    int tag;


    public Vertex() {
        this.id = id;
        this.location = new GeoLoc(0,0,0);
        this.info = "";
        this.weight = 0.0;
        this.tag = -1;
    }
    public  Vertex(int key){
        this.id = key;
        this.tag = -1;
        this.info = "";
        this.weight = 0.0;
        location = new GeoLoc(0,0,0);
    }

    //Copy Constructor
    public Vertex(NodeData n){
        this.location = n.getLocation();
        this.id = n.getKey();
        this.tag = n.getTag();
        this.info=n.getInfo();
        this.weight=n.getWeight();
    }

    @Override
    public int getKey() {
        return id;
    }

    @Override
    public GeoLocation getLocation() {
        return location;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.location = p;

    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public void setWeight(double w) {

        this.weight = w;

    }

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public void setInfo(String s) {

        this.info = s;

    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {

        this.tag = t;
    }
//    public Vertex getObject(){
//        Gson gson = new Gson();
//        Vertex node = gson.fromJson("G1.json" , Vertex.class);
//        return node;
//    }
}

