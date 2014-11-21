/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphModels;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author rodrigo
 */
public class Graph {

  /* This Data Structure will be capable to represent a Graph,
   *   where G = (V, E)
   */
  private List<Vertex> nodes;
  private List<Edge> edges;
  public Vertex node;
  public Edge edge;
  private String path;
  private int[][] adjacencyMatrix;
  private Map nodesMatrixMap;
  private List<Vertex> adjacentNodes;
  public int degree;
  public int minDegree;
  public int maxDegree;
  public int avgDegree;
  public DijkstraShortestPath dijkstra;
  public BellmanFordShortestPath bellmanFord;
  public FloydShortestPath floyd;

  public Graph() {

  }

  public List<Vertex> getNodes() {
    return nodes;
  }

  public List<Edge> getEdges() {
    return edges;
  }

  public void setNodes(List<Vertex> vertex) {
    this.nodes = vertex;
  }

  public void setEdges(List<Edge> edges) {
    this.edges = edges;
  }

  // Find an edge
  // TO-DO: Change == to equals
  public Edge getEdge(Vertex source, Vertex destination) {
    for (Edge edge : this.getEdges()) {
      if ((edge.getSource().getId() == source.getId() && edge.getDestination().getId() == destination.getId()) || (edge.getSource().getId() == destination.getId() && edge.getDestination().getId() == source.getId())) {
        return edge;
      }
    }
    return null;
  }

  // Check if there is an edge for two given vertices
  public boolean hasEdge(Vertex source, Vertex destination) {
    Edge result = this.getEdge(source, destination);
    if (result != null) {
      return true;
    } else {
      return false;
    }
  }

  public List<Vertex> getAdjacentNodes(Vertex node) {
    adjacentNodes = new ArrayList<Vertex>();

    for (Vertex currentNode : nodes) {
      if (hasEdge(node, currentNode)) {
        adjacentNodes.add(currentNode);
      }
    }

    return adjacentNodes;
  }

  public int getminDegree() {
    minDegree = 99999999;

    for (Vertex node : nodes) {
      if (this.getDegree(node) < minDegree) {
        minDegree = this.getDegree(node);
      }
    }

    return minDegree;
  }

  public int getmaxDegree() {
    maxDegree = 0;

    for (Vertex node : nodes) {
      if (this.getDegree(node) > maxDegree) {
        maxDegree = this.getDegree(node);
      }
    }

    return maxDegree;
  }

  public int getavgDegree() {
    avgDegree = 0;

    for (Vertex node : nodes) {
      avgDegree += this.getDegree(node);
    }
    avgDegree = avgDegree / nodes.size();

    return avgDegree;
  }

  //modified for our needs   
  public int breadthFirstSearch(Vertex rootNode) {

    int visitedNodes = 0;
    Queue q = new LinkedList();
    q.add(rootNode);

    while (!q.isEmpty()) {
      Vertex currentNode = (Vertex) q.peek();
      for (Vertex node : this.getAdjacentNodes(currentNode)) {
        if (!node.visited) {
          node.visited = true;
          q.add(node);
          visitedNodes++;
        }
      }
      q.remove();
    }
    //reseting all flags
    for (Vertex node : nodes) {
      node.visited = false;
    }

    return visitedNodes;

  }

  public void shortestPathDijkstra(Vertex source, Vertex destination) {

    dijkstra = new DijkstraShortestPath(this);
    dijkstra.execute(source);
    LinkedList<Vertex> path = dijkstra.getPath(destination);
    System.out.println(path);
  }

  public void shortestPathBellmanFord(Vertex source, Vertex destination) {

    bellmanFord = new BellmanFordShortestPath(this);
    bellmanFord.execute(source);
    LinkedList<Vertex> path = bellmanFord.getPath(destination);
    System.out.println(path);
  }

  public void shortestPathFloyd(Vertex source, Vertex destination) {

    floyd = new FloydShortestPath();
    int[][] result = floyd.floydWarshall(adjacencyMatrix);
    
    /*for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result.length; j++) {
        System.out.print(result[i][j] + " ");
      }
      System.out.println("");
      System.out.println("");
    }*/
    floyd.getPath(Integer.parseInt(source.id), Integer.parseInt(destination.id));
    //System.out.println("Adjacency Matrix after Floyd Algorithm: ");
    //this.printAdjacencyMatrix();

  }

  public int getMinValue(int[] list) {
    Arrays.sort(list);
    for (int i : list) {

      return i;

    }
    return -1;
  }

  public boolean isConnected() {
    int numberOfNodes = nodes.size();

    for (Vertex node : nodes) {
      if (!(this.breadthFirstSearch(node) == numberOfNodes)) {
        return false;
      }
    }
    return true;
  }

  //TO-DO: error treatment. 
  // while this treatment doesn't exist: BE EXTREMELY CAREFUL WITH GRAPH.TXT
  // the right syntax is:
  //line (1) :1,2,...,n 
  //line (2) :1-2
  //line (3) : weight of the previous connection
  //line (n) :2-n
  //line (n+1) : weight of the previous connection
  //line (n+2) :-1
  //where the first line you must declare the nodes, each one separated by comma
  //the second til the n-th line you must declare the connections between the nodes declared 
  //in the first line
  //after every connection, in the next line, you must declare the weight of the connection
  // leave everything zero to create a unweighted graph
  //after the last connection, you must end with a -1
  // if you don't do this your computer may explode lol
  //...
  //...
  //...
  // i don't know, i did never try that.
  public void createGraph(String path) throws FileNotFoundException, IOException {
    this.path = path;

    nodes = new ArrayList<Vertex>();
    edges = new ArrayList<Edge>();

    FileReader fr = new FileReader(path);
    BufferedReader textReader = new BufferedReader(fr);

    //gets the first line, which are the nodes that must be created
    String TextData = textReader.readLine();

    //separate the string with numbers
    String[] nodesInString = TextData.split("\\s*,\\s*");

    //create the list of nodes
    for (String nodeInString : nodesInString) {

      node = new Vertex(nodeInString, "node " + nodeInString);
      nodes.add(node);

    }

    //create the list of edges
    String edgeIntoString;
    Vertex vertex_aux1 = new Vertex("-1", "-1");
    Vertex vertex_aux2 = new Vertex("-1", "-1");;
    int n = 0;
    //varre a linha que tem x-y
    while (!(edgeIntoString = textReader.readLine()).equals("-1")) {

      String[] nodesConnected = edgeIntoString.split("\\s*-\\s*");
      //varre os dois nodes

      int peso = Integer.parseInt(textReader.readLine());

      //verifica se x e y passados no txt estão instanciados em nodes 
      for (Vertex node : nodes) {

        if (nodesConnected[0].equals(node.getId())) {
          //se o node do txt foi achado em nodes

          vertex_aux1 = new Vertex(node.getId(), node.getName());
        }
      }
      for (Vertex node : nodes) {

        if (nodesConnected[1].equals(node.getId())) {

          vertex_aux2 = new Vertex(node.getId(), node.getName());
        }
      }

      edge = new Edge(Integer.toString(n), vertex_aux1, vertex_aux2, peso);
      edges.add(edge);
    }

  }

  // Add a new edge
  public boolean addEdge(Vertex source, Vertex destination) {
    Edge newEdge = new Edge("id", source, destination, 0);
    return this.edges.add(newEdge);
  }

  // Remove an edge
  public boolean removeEdge(Vertex source, Vertex destination) {

    Edge result = this.getEdge(source, destination);

    if (result != null) {
      return this.edges.remove(result);
    } else {
      System.out.println("Couldn't find edge");
      return false;
    }
  }

  public int getDegree(Vertex node) {

    degree = 0;

    for (Vertex CurrentNode : nodes) {
      if (hasEdge(node, CurrentNode)) {
        degree++;
      }
    }

    return degree;
  }

  public void printGraph() {

    for (Vertex node : this.getNodes()) {
      System.out.println(node.getName());
      System.out.print("Adjacent nodes: ");
      adjacentNodes = getAdjacentNodes(node);
      for (Vertex adjacentNode : adjacentNodes) {
        System.out.print(adjacentNode.getName() + " ");
      }

      System.out.println(" ");
      System.out.println("Degree of this node: " + getDegree(node));
      System.out.println(" ");
    }
    System.out.println("- - - - - - - - - - - - - - - - ");
    System.out.println(" ");
    System.out.println("Min Degree of this Graph: " + getminDegree());
    System.out.println(" ");
    System.out.println("Max Degree of this Graph: " + getmaxDegree());
    System.out.println(" ");
    System.out.println("Average Degree of this Graph: " + getavgDegree());
    System.out.println(" ");
    System.out.println("Graph is connected: " + this.isConnected());
    System.out.println(" ");
    System.out.println("- - - - - - - - - - - - - - - - ");
    System.out.println("Connections: ");
    System.out.println(" ");
    for (Edge edge : this.getEdges()) {
      System.out.print(edge.getSource().getId() + "<->" + edge.getDestination().getId() + " ");
      System.out.println("weight: " + edge.getWeight());
    }
    System.out.println(" ");
    System.out.println("- - - - - - - - - - - - - - - - ");

    this.setAdjacencyMatrix();
    this.printAdjacencyMatrix();
    System.out.println(" ");
    System.out.println("- - - - - - - - - - - - - - - - ");
    System.out.println("Bellman-Ford Shortest Path ");
    this.shortestPathBellmanFord(this.nodes.get(0), this.nodes.get(2));
    System.out.println("Bellman-Ford Shortest Path ");
    this.shortestPathBellmanFord(this.nodes.get(0), this.nodes.get(4));
    System.out.println("Bellman-Ford Shortest Path ");
    this.shortestPathBellmanFord(this.nodes.get(0), this.nodes.get(5));
    System.out.println(" ");
    System.out.println("- - - - - - - - - - - - - - - - ");
    System.out.println("Dijkstra Shortest Path ");
    this.shortestPathDijkstra(this.nodes.get(0), this.nodes.get(2));
    System.out.println("Dijkstra Shortest Path ");
    this.shortestPathDijkstra(this.nodes.get(0), this.nodes.get(4));
    System.out.println("Dijkstra Shortest Path ");
    this.shortestPathDijkstra(this.nodes.get(0), this.nodes.get(5));
    System.out.println(" ");
    System.out.println("- - - - - - - - - - - - - - - - ");
    System.out.println("Floyd Shortest Path :");
    System.out.print("[");
    this.shortestPathFloyd(this.nodes.get(0), this.nodes.get(1));
    System.out.print("]");
    System.out.println("");
    System.out.println(" ");
    System.out.println("- - - - - - - - - - - - - - - - ");
    this.breakdownComponents();

  }

  public void setAdjacencyMatrix() {

    initializeAdjacencyMatrix();
    int weigth;
    int source;
    int destination;
    for (Edge currentEdge : this.getEdges()) {
      weigth = currentEdge.getWeight();
      source = (int) nodesMatrixMap.get(currentEdge.getSource().getName());
      destination = (int) nodesMatrixMap.get(currentEdge.getDestination().getName());

      adjacencyMatrix[source][destination] = weigth;
      //must be symmetric
      adjacencyMatrix[destination][source] = weigth;
    }
  }

  public void initializeAdjacencyMatrix() {
    int numNodes = this.nodes.size();
    adjacencyMatrix = new int[numNodes][numNodes];
    nodesMatrixMap = new HashMap();
    for (int i = 0; i < numNodes; i++) {
      nodesMatrixMap.put(this.nodes.get(i).getName(), i);
      for (int j = 0; j < numNodes; j++) {
        adjacencyMatrix[i][j] = Integer.MAX_VALUE;
      }
    }
  }

  public void printAdjacencyMatrix() {

    String sourceName;
    String destName;
    int source;
    int dest;

    System.out.println(" ");
    System.out.println("Adjacency Matrix: ");
    System.out.println(" ");

    for (int i = 0; i < this.nodes.size(); i++) {
      sourceName = this.nodes.get(i).getName();
      source = (int) nodesMatrixMap.get(sourceName);
      for (int j = 0; j < this.nodes.size(); j++) {
        destName = this.nodes.get(j).getName();
        dest = (int) nodesMatrixMap.get(destName);
        if (adjacencyMatrix[source][dest] == Integer.MAX_VALUE) {
          System.out.print("- ");
        } else {
          System.out.print(adjacencyMatrix[source][dest] + " ");
        }
      }
      System.out.print("\n");
    }
  }
  
  public void breakdownComponents() {
    int numComponents = 0;
    int numVertexes;
    Map<String, LinkedList<Vertex>> components = new HashMap<String, LinkedList<Vertex>>();
    LinkedList<Vertex> listVertex = new LinkedList<Vertex>();
    for (Vertex node : this.getNodes()) {
      node.visited = false;
    }
    
    for (Vertex node : this.getNodes()) {
      listVertex.clear();
      if (!node.visited) {
        numComponents++;
        numVertexes = 1;
        System.out.println("-------------------");
        System.out.println("Component " + numComponents + ":");
        System.out.println(node.getName());
        node.visited = true;

        for (Vertex nodeDest : this.getNodes()) {
          if (!nodeDest.visited) {
            this.dijkstra.execute(node);
            if (this.dijkstra.getPath(nodeDest) != null) {
              System.out.println(nodeDest.getName());
              nodeDest.visited = true;
              numVertexes++;
            }
          }
        }
        
        System.out.println("Component degree: " + numVertexes);

      }
    }
    
  }

}
