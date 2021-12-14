# Directed Weighted Graph
![hF3mQ](https://user-images.githubusercontent.com/69717074/145678997-b852bd55-2fd1-4991-9e46-34643d89c76a.png)

Let’s start with a little review, what exactly is a directed and weighted graph.
In mathematics, and more specifically in graph theory, a directed graph (or digraph) is a graph that is made up of a set of vertices connected by directed edges often called arcs.
In this project, we were asked to implement graph interfaces, which include several algorithms: adding a vertex, deleting a vertex, connecting two vertices with a side, deleting a side, calculating the most central vertex - that is, the vertex with the smallest distance from each vertex, checking whether The graph is strongly connected and the TSP problem. Our graph works with JSON files.
Vertex is a class that implements the NodeData interface - in this class the vertex is built, with its identification tag, information contained within it, marking weight and its coordinates.
The department contains builders and a copyist builder

Edge is a class that implements the EdgeData interface, which contains a source vertex identification tag, a target vertex identification tag, weight, markup, and information. The class contains builders, a copy builder.

GeoLoc is a class that implements the GeoLocation interface, in which it receives values ​​of coordinates X Y Z, contains constructors, distance function and converts the information of the coordinate from string to DOUBLE.

Graph is a class that implements the DirectedWeightedGraph interface, in which we basically build the graph by Hash Map for all the information. I.e. HashNap for vertices and HashMap for ribs.
In this class we are required to perform several functions such as - connecting the vertices by side, deleting vertex, deleting side, running iterators on vertices and sides and in addition we have an MC function that basically every action we perform on the graph, we count and return the count.
Algo is a class that implements the DirectedWeightedGraphAlgorithms interface, which basically has builders and a copy builder as well as all the main algorithms that we want to elaborate on in the following sections.
shortestPathDist - This is a function, which should return the shortest path from the source vertex to the destination vertex. In this function we use the Dijkstra algorithm.
Dijkstra Algorithm- We took the explanation of the algorithm from several different sources:
- https://www.baeldung.com/java-dijkstra


Our algorithm will work like this:
* run on all the vertex with the iterator
  * set their info to be empty becuse their we init the father vertex key
  * set tag to be -1 to know if we path their allready
  * set weight to 0 for the beginning becuse we will add the edge weight with the weight till their
  * / set the src data for beginning and add him to Queue
  if we didnt finish to run on all the vertex in the gragh
  the curent veretex that we check all his next stop we run on all the edge are go out from him
  save the dest vertex just for easy syntax to write
  this is the weigt till we get to this vertex + the edge weight
  we check if allready check this vertex or the weight in last time we check cost more than this path


shortestPath- Returns a list of vertices with the shortest trajectory.

![Graph_Condensation svg](https://user-images.githubusercontent.com/69717074/145679809-0ce1d5ac-62c6-48c8-a741-35ba58a7d0ac.png)




isConnected - This is a function that checks the graph of the graph. Is he strongly connected.
In this function, we use the BFS algorithm, which we operated according to this video:
https://www.youtube.com/watch?v=YhU2EvAoKOI
What the algorithm actually performs is a test of whether it passes through all the vertices in the graph, that is,
whether there is a path from vertex to vertex along the entire graph. With our iterator we go over the vertices and change the notation of all vertices to be -1,
to know which are the vertices we have not yet passed. We then take the first vertex and change its notation, to be 1, and put all its target vertices into a queue,
then again and again, we will take the vertex out of the queue and decide that it is now the source and look for the destinations connected to it with ribs.
And of course we will change the markings of the source to be 1 after we have gone through them.
We will repeat the operation until we finish going through the whole graph and at the same time we run a count, every time we go through a vertex and change its check,
we raise the count by 1. At the end we return, true or false value if the count matches the size of the graph.
In the isConnected function itself, we check extreme cases if we have no vertices or there is only one vertex, then we return true that the graph is a link,
and its opposite extreme case if the number of vertices is greater than the number of sides, meaning there is nothing connecting them. Then we select by iterator,
a random vertex and run on it the algorithm that will run for us on the whole group starting from that vertex.


![NF3iA](https://user-images.githubusercontent.com/69717074/145679117-887a41b0-0236-4d0c-9690-f67a1655121a.png)



Center - This is a function, which finds a vertex, to which the distance from all other vertices is minimal. The function uses the Floyd Warshall algorithm algorithm.
Each time we take a vertex and check the distances to it from the other vertices. Each time we will keep the minimum distance and the vertex itself.
We will make track-to-track comparisons and finally return the vertex.
Floyd Warshal Algorithm - https://youtu.be/4OQeCuLYj-4
The explanation of the algorithm is taken from -> https://www.geeksforgeeks.org/floyd-warshall-algorithm-dp-16/
We initialize the solution matrix same as the input graph matrix as a first step. Then we update the solution matrix by considering all vertices as an intermediate vertex. The idea is to one by one pick all vertices and updates all shortest paths which include the picked vertex as an intermediate vertex in the shortest path. When we pick vertex number k as an intermediate vertex, we already have considered vertices {0, 1, 2, .. k-1} as intermediate vertices. For every pair (i, j) of the source and destination vertices respectively, there are two possible cases.
1) k is not an intermediate vertex in shortest path from i to j. We keep the value of dist[i][j] as it is.
2) k is an intermediate vertex in shortest path from i to j. We update the value of dist[i][j] as dist[i][k] + dist[k][j] if dist[i][j] > dist[i][k] + dist[k][j]
   The following figure shows the above optimal substructure property in the all-pairs shortest path problem.


![traveling_salesman-Medium-930x620](https://user-images.githubusercontent.com/69717074/145679050-3da1cfe3-e6b8-4935-bdae-c80039fe00dd.png)



TSP - this function, should find us the shortest route, when it starts at a certain vertex and has to go through the whole graph.
Our algorithm works like this:
create a linklist for return
create an array for taging to know if we use a vertex in the list allready
initializing the arr with false
every time we add vertex to the list the counter is +1
run till we add all the vertex to the list
the curent src vertex we start randomly from the first in the given list
the curent dest vertex we will go for loop on all vertex and save the lowest cost
the lowest cost from the curent src to the next dest
run to check the cost from src to all the vertex in the given list and save the lowest cost and his vertex
change the flag to know we use this vertex already
add the vertex to the list
change the src to be the last dst
size up the counter
And the two load and save functions, which load our JSON files and save them.
- https://www.youtube.com/watch?v=XaXsJJh-Q5Y
- https://www.youtube.com/watch?v=cY4HiiFHO1o

We also did tests in all the classes. This will check if each of our functions is also working properly
Also we checked our center algorithm on graphs in size 1000, 10000 , 100000.
# For Graph With 1000 nodes:
center - 3.648 seconds
tsp - 0.380 seconds
isConnected - 0.245 seconds
shortestPathDist -0.388 seconds
# For Graph With 10000 nodes: 
center - 17 minutes
tsp - 1.308 seconds
isConnected - 0.23 seconds
shortestPathDist -0.865 seconds


We used our GUI in Java Swing, we used JPanel, Jlabel, JFrame
JFrame- created for us the screen, its size and set it to close and visibility on the screen.
JPanel- In this class, we actually built our graph. During the construction of the graph we encountered a problem of resolution, because the vertex was given with a difference of about decimal it at first seemed to be drawn on top of each other, so we decided to change it by Scale, basically redefined the boundaries of the graph and built it within the vertices of each graph. We will be able to adjust ourselves to the screen in a way that we see all the vertices in a normal way and not on top of each other.

JLabel - In this class we build the buttons and link them to functions and algorithms. We use Action Listener and for each button we create a function that links it to the function it needs.

*For that matter, this is what our menu and graph look like:

![menu](https://user-images.githubusercontent.com/69717074/145894169-36644c8f-f862-41bf-a37f-67a6d6a930d3.png)
![menu 2](https://user-images.githubusercontent.com/69717074/145894174-bf905ece-4419-474c-8246-689d81670e9a.png)
![graph](https://user-images.githubusercontent.com/69717074/145894182-73ecff4d-4ba8-4dee-ad37-b3b06ddf581e.png)
