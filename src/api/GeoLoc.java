package api;


public class GeoLoc implements GeoLocation {
    double x;
    double y;
    double z;
    double distance;
    String pos;

    public GeoLoc(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;

    }

    public GeoLoc(String pos) {
        double [] arr = new double[3];
        int temp = 0;
        double ans = 0;
        int key = 0;
        String str = "";
        for (int i = 0; i < pos.length(); i++) {
            if (pos.charAt(i) == ',' || i == pos.length()-1) {
                str = pos.substring(temp, (i));
                ans = Double.parseDouble(str);
                arr[key]=ans;
                key++;
                temp = i+1;
            }
        }
        this.x = arr[0];
        this.y = arr[1];
        this.z = arr[2];
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public double distance(GeoLocation g) {
        distance = Math.sqrt(Math.pow(g.x() - x, 2) + Math.pow(g.y() - y, 2) + Math.pow(g.z() - z, 2));
        return distance;
    }
}