package api;

import java.util.Objects;

public class Edge implements EdgeData {
    int src;
    int dest;
    double weight;
    String info;
    int tag;

    public Edge(int src, int dest, double weight) {
        this.tag = -1;
        this.src = src;
        this.dest = dest;
        this.weight = weight;
        this.info = "";
    }

    public Edge(EdgeData e) {
        this.src = e.getSrc();
        this.dest = e.getDest();
        this.weight = e.getWeight();
        this.info = e.getInfo();
        this.tag = e.getTag();
    }

    @Override
    public int getSrc() {
        return this.src;
    }

    @Override
    public int getDest() {
        return this.dest;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public String getInfo() {
        return this.info;
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


}