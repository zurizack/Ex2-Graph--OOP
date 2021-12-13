package api;

import java.io.*;
import java.util.*;
import java.util.List;
import com.google.gson.*;

public class Algo implements DirectedWeightedGraphAlgorithms {
    private DirectedWeightedGraph graph;

    public Algo() {
        graph = new Graph();
    }

    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph copyGraph = new Graph(graph);
        return copyGraph;
    }

    @Override
    public boolean isConnected() {
        if (this.graph.nodeSize() == 0 || this.graph.nodeSize() == 1) {
            return true;
        }
        if (this.graph.edgeSize() == 0 || this.graph.nodeSize() > this.graph.edgeSize() + 1) {
            return false;
        }
        NodeData node = null;
        for (Iterator<NodeData> n = this.graph.nodeIter(); n.hasNext(); ) {
            node = n.next();
            break;
        }
        return BFSalgo(node);
    }

    public boolean BFSalgo(NodeData srcNode) {
        for (Iterator<NodeData> it = this.graph.nodeIter(); it.hasNext(); ) {
            NodeData n = it.next();
            n.setTag(-1);
        }
        int count = 0;
        NodeData temp = null;
        Queue<NodeData> queue = new LinkedList<NodeData>();
        queue.add(srcNode);
        srcNode.setTag(1);
        count++;
        while (!queue.isEmpty()) {
            if (queue.peek() != null) {
                temp = queue.poll();
            }
            for (Iterator<EdgeData> edge = this.graph.edgeIter(temp.getKey()); edge.hasNext(); ) {
                EdgeData p = edge.next();
                NodeData n2 = graph.getNode(p.getDest());
                if (n2.getTag() == -1) {
                    count++;
                    if (this.graph.edgeIter(n2.getKey()) != null) {
                        queue.add(n2);
                        n2.setTag(1);
                    }
                }
            }
        }
        if (count != graph.nodeSize()) {
            return false;
        }
        count = 0;
        temp = null;
        queue = new LinkedList<NodeData>();
        queue.add(srcNode);
        srcNode.setTag(2);
        count++;
        while (!queue.isEmpty()) {
            if (queue.peek() != null) {
                temp = queue.poll();
            }
            if (graph instanceof Graph) {
                for (Iterator<EdgeData> e = ((Graph) (this.graph)).edgeIter(temp.getKey()); e.hasNext(); ) {
                    EdgeData r = e.next();
                    NodeData node = graph.getNode(r.getDest());
                    if (node.getTag() == 1) {
                        count++;
                        if (((Graph) (this.graph)).edgeIter(node.getKey()) != null) {
                            queue.add(node);
                            node.setTag(2);
                        }
                    }
                }
            }
        }

        return count == graph.nodeSize();
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        // check if the vertex are exsists
        if (graph.getNode(src) != null && graph.getNode(dest) != null) {
            //check if the dest and src is the same vertex if yes return 0
            if (src == dest) {
                return 0;
            }
            //if not call to dijkstra to check the shortest path and set it in the dest weight
            dijkstra(graph.getNode(src));
            //after dijkstra finish make shour it is set the weight if yes return the cost
            if (graph.getNode(dest).getWeight() != Integer.MAX_VALUE) {
                return graph.getNode(dest).getWeight();
            }
        }
        return -1;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        //create the list where we add the road and return it in the end
        LinkedList<NodeData> path = new LinkedList<NodeData>();
        //create the stack just to save the list in the right order
        Stack<NodeData> temp = new Stack<NodeData>();
        //check the src,dest node are exsists
        if (graph.getNode(src) != null && graph.getNode(dest) != null) {
            //check if the dest and src is the same vertex if yes add to list and return
            if (src == dest) {
                path.add(graph.getNode(src));
                return path;
            }

            dijkstra(graph.getNode(src));
            //check if the gragh is not connected betwin the src and the dest
            if ((graph.getNode(dest).getWeight() == Integer.MAX_VALUE)) {
                return null;
            }
            //save the dest node evry time
            NodeData destNode = graph.getNode(dest);
            try {
                //go back from dest to src by the info because the dijkstra save there the father node every time
                //and add it to the stack

                while (graph.getNode(src) != destNode) {
                    temp.add(destNode);
                    destNode = graph.getNode(Integer.parseInt(destNode.getInfo()));
                }
                temp.add(graph.getNode(src));

            }
            //try to catch an eror with the casting to int from string
            catch (NumberFormatException e) {
                System.out.println("houston we have a problem");
                return null;
            } catch (Exception e) {
                System.out.println("houston we have a problem");
                return null;
            }
            //poll from stack and add to list
            while (!temp.isEmpty()) {
                path.add(temp.pop());
            }
        }


        return path;
    }

    @Override
    public NodeData center() {
        /***הפונקציה הזאת אמורה להחזיר את הקודקוד שיש ממנו את המסלול המינימלי לכל הקודקודים
         * מה הפונקציה עושה :
         *  כל פעם עבור כל קודקוד אני בודקת את כל המסלולים לכל שאר הקודקודים בגרף, זאת אומרת עבור כל איטרציה של הלולאה הראשית שבוחרת לי את הקודקוד מקור
         *  יש עוד לולאה בפנים שבכל איטרציה שלה בוחרת לי את היעדים וכך בעצם מחשבת את כל המסלולים לשאר הקודקוד
         *  באיטרציה הבאה של הלולאה הראשית הקוד קוד המקור מתחלף ושוב עושה בדיקת מסלול עבור כל המסלולים עם יעידים שונים
         *  לבסוף אני בוחרת את המסלול המינמילי ואת קוד קוד המקור המותאם למסלול המינמלי ואני מחזירה אותו.
         *  איך שמרתי את הקודקוד?
         *  עשיתי משתנה מסוג DirectedWeightedGraph והשתמשתי בפונקציה שבהינתן איידי של קודקוד מחזירה את המידע על כל הקודקוד
         *  ,ואז את המשתנה הזה אני מכניסה לקודקוד מסוג NODEDATA ומחזירה אותו בסוף
         *
         *
         */


        double minPath = Integer.MAX_VALUE;
        NodeData answer = null;
        if (isConnected()) {
            for (Iterator<NodeData> n = this.graph.nodeIter(); n.hasNext(); ) {
                NodeData e = n.next();
                double path = 0;
                for (Iterator<NodeData> destNode = this.graph.nodeIter(); destNode.hasNext(); ) {
                    NodeData temp = destNode.next();
                    path += shortestPathDist(e.getKey(), temp.getKey());

                }
                if (minPath > path) {
                    minPath = path;
                    answer = e;
                }
            }
        }
        return answer;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        //create a linklist for return
        LinkedList<NodeData> thePath = new LinkedList<NodeData>();
        //create an array for taging to know if we use a vertex in the list allready
        //it so agly and not profeshional to do like that but I had a problem to use tag bicuse the shortestpath use it
        boolean[] useTag = new boolean[cities.size()];
        //initializing the arr with false
        Arrays.fill(useTag, false);
        //every time we add vertex to the list the counter is +1
        int counter = 0;
        //the curent src vertex we start random from the first in the given list
        int src = 0;
        //run till we add all the vertex to the list
        while (counter < cities.size()) {

            //the curent dest vertex we will go for loop on all vertex and save the lowest cost
            int tempdest = cities.get(0).getKey();
            //the lowest cost from the curent src to the next dest
            double path = Double.MAX_VALUE;
            //run to check the cost from src to all the vertex in the given list and save the lowest cost and his vertex
            for (int dest = 0; dest < cities.size() && useTag[dest] != true; dest++) {
                if (src != dest) {
                    if (shortestPathDist(cities.get(src).getKey(), cities.get(dest).getKey()) == -1) {
                        return null;
                    }
                    double tempPath = shortestPathDist(cities.get(src).getKey(), cities.get(dest).getKey());
                    if (tempPath < path) {
                        path = tempPath;
                        tempdest = cities.get(dest).getKey();

                    }
                }
            }
            //change the flag to know we use this vertex allready
            useTag[tempdest] = true;
            //add the vertex to the list
            thePath.add(graph.getNode(tempdest));
            //change the src to be the last dst
            src = tempdest;
            //size up the counter
            counter++;

        }


        return thePath;
    }

    @Override
    public boolean save(String file) {
        Gson gsonFile = new GsonBuilder().create();
        JsonObject jsonObject = new JsonObject();
        JsonArray vertex = new JsonArray();
        JsonArray edge = new JsonArray();
        JsonObject savedNode = new JsonObject();
        JsonObject savedEdge = new JsonObject();
        NodeData nodeForIterator = null;
        EdgeData edgeForIterator = null;
        for (Iterator<NodeData> node = this.graph.nodeIter(); node.hasNext(); ) {
            nodeForIterator = node.next();
            double x = nodeForIterator.getLocation().x(), y = nodeForIterator.getLocation().y(), z = nodeForIterator.getLocation().z();
            savedNode.addProperty("pos", x + "," + y + "," + z);
            savedNode.addProperty("id", nodeForIterator.getKey());
            vertex.add(savedNode);
            savedNode = new JsonObject();
            for (Iterator<EdgeData> edge1 = this.graph.edgeIter(); edge1.hasNext(); ) {
                edgeForIterator = edge1.next();
                savedEdge.addProperty("src", edgeForIterator.getSrc());
                savedEdge.addProperty("w", edgeForIterator.getWeight());
                savedEdge.addProperty("dest", edgeForIterator.getDest());
                edge.add(savedEdge);
                savedEdge = new JsonObject();
            }
        }
        jsonObject.add("Edges", edge);
        jsonObject.add("Nodes", vertex);
        File f = new File(file);
        try {
            FileWriter fileWriter = new FileWriter(f);
            fileWriter.write(gsonFile.toJson(jsonObject));
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean load(String file) {
        DirectedWeightedGraph loadGraph = new Graph();
        JsonObject jsonObject;
        File f = new File(file);
        try {
            FileReader fileReader = new FileReader(f);
            jsonObject = new JsonParser().parse(fileReader).getAsJsonObject();
            JsonArray edges = jsonObject.getAsJsonArray("Edges");
            JsonArray vertices = jsonObject.getAsJsonArray("Nodes");

            for (JsonElement v : vertices) {
                int key = ((JsonObject) v).get("id").getAsInt();
                String pos = ((JsonObject) v).get("pos").getAsString();
                GeoLocation g = new GeoLoc(pos);
                NodeData n = new Vertex(key);
                n.setLocation(g);
                loadGraph.addNode(n);
            }
            for (JsonElement edge : edges) {
                int src = ((JsonObject) edge).get("src").getAsInt();
                int dest = ((JsonObject) edge).get("dest").getAsInt();
                double weight = ((JsonObject) edge).get("w").getAsDouble();
                EdgeData e = new Edge(src, dest, weight);
                loadGraph.connect(e.getSrc(), e.getDest(), e.getWeight());
            }
            this.graph = loadGraph;
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


    public int dijkstra(NodeData srcNode) {
        //this will be the lowest cost in a specific moment
        double tempWeight = 0;
        NodeData curentVertex;
        //this Queue will hold the vertex to check their weight
        PriorityQueue<NodeData> neighborQueue = new PriorityQueue<NodeData>(new CompereVertexWeight());
        /*
         *run on all the vertex with the iterator
         * set their info to be empty becuse their we init the father vertex key
         *  set tag to be -1 to know if we path their allready
         *  set weight to 0 for the beginning becuse we will add the edge weight with the weight till their
         */
        for (Iterator<NodeData> it = graph.nodeIter(); it.hasNext(); ) {
            NodeData e = it.next();
            e.setWeight(Integer.MAX_VALUE);
            e.setTag(-1);
            e.setInfo("");
        }
        //set the src data for beginning and add him to Queue
        if (graph.getNode(srcNode.getKey()) != null) {
            srcNode.setWeight(0);
            srcNode.setInfo("" + srcNode.getKey());
            srcNode.setTag(1);
            neighborQueue.add(srcNode);
        }
        //if we didnt finish to run on all the vertex in the gragh
        while (!neighborQueue.isEmpty()) {
            if (neighborQueue.peek() != null) {
                //the curent veretex that we check all his next stop we run on all the edge are go out from him
                curentVertex = neighborQueue.poll();
                if (curentVertex != null) {
                    for (Iterator<EdgeData> it = graph.edgeIter(curentVertex.getKey()); it.hasNext(); ) {
                        EdgeData e = it.next();
                        //save the dest vertex just for easy syntax to write
                        NodeData dstVertex = graph.getNode(e.getDest());
                        //this is the weigt till we get to this vertex + the edge weight
                        tempWeight = curentVertex.getWeight() + e.getWeight();
                        /*
                         *we check if allready check this vertex or the weight in last time we check cost more than this path
                         */
                        if (dstVertex.getTag() < 0 || tempWeight < dstVertex.getWeight()) {
                            neighborQueue.add(dstVertex);
                            dstVertex.setInfo("" + curentVertex.getKey());
                            dstVertex.setWeight(tempWeight);
                            dstVertex.setTag(1);
                        }
                    }
                }
            }
        }
        return 0;
    }

    public double[][] floydWarshall(DirectedWeightedGraph a) {
        double[][] matrix = new double[a.nodeSize()][a.nodeSize()];
        for (Iterator<NodeData> it = a.nodeIter(); it.hasNext(); ) {
            NodeData e = it.next();
            matrix[e.getKey()][e.getKey()]=0;
        }

        for (Iterator<EdgeData> it = a.edgeIter(); it.hasNext(); ) {
            EdgeData e = it.next();
            matrix[e.getSrc()][e.getDest()]=e.getWeight();
        }

        for (int i = 0; i < a.nodeSize(); i++) {
            for (int j = 0; j < a.nodeSize(); j++) {
                for (int k = 0; k < a.nodeSize(); k++) {
                    if (matrix[j][k] > matrix[j][i] + matrix[i][k]){
                        matrix[j][k] = matrix[j][i] + matrix[i][k];
                    }
                }

            }
            
        }

        return matrix;

    }
}
