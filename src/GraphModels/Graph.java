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
    
   private  List<Vertex> nodes;
   private  List<Edge> edges;
   public Vertex node;
   public Edge edge;
   private String path;
   private int [][] adjacencyMatrix;
   private Map nodesMatrixMap;
   private List<Vertex> adjacentNodes;
   public int degree;
   public int MinDegree;
   public int MaxDegree;
   public int AvgDegree;
   public DijkstraShortestPath dijkstra;
   

   public Graph() {
    
  }


  public List<Vertex> getNodes() {
    return nodes;
  }

  public List<Edge> getEdges() {
    return edges;
  }
  
  public void setNodes(List<Vertex> vertex){
      this.nodes = vertex;
  }
  
  public void setEdges(List<Edge> edges){
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
  
  public List<Vertex> getAdjacentNodes(Vertex node){
      adjacentNodes = new ArrayList<Vertex>();
      
      for (Vertex currentNode : nodes){
          if (hasEdge(node, currentNode)){
              adjacentNodes.add(currentNode);
          }
      }
       
      return adjacentNodes;
  }
  
  public int getMinDegree(){
      minDegree = 99999999;
      
      for (Vertex node : nodes){
          if (this.getDegree(node)<minDegree){
              minDegree = this.getDegree(node);
          }
      }
      
      
      return minDegree;
  }
  public int getMaxDegree(){
      maxDegree = 0;
      
      for (Vertex node : nodes){
          if (this.getDegree(node)>maxDegree){
              maxDegree = this.getDegree(node);
          }
      }

      return maxDegree;
  }
  public int getAvgDegree(){
      avgDegree = 0;
      
      for (Vertex node : nodes){
          avgDegree += this.getDegree(node);
      }
      avgDegree = avgDegree/nodes.size();
      
      return avgDegree;
  }
  
 
  //modified for our needs   
  public int breadthFirstSearch(Vertex rootNode){
      
      int visitedNodes = 0;
      Queue q = new LinkedList();
      q.add(rootNode);

      while (!q.isEmpty()){
          Vertex currentNode = (Vertex) q.peek();
          for (Vertex node : this.getAdjacentNodes(currentNode)){
              if (!node.visited){   
                  node.visited = true;
                  q.add(node);
                  visitedNodes++;
              }
          }
          q.remove();   
      }
      //reseting all flags
      for (Vertex node : nodes){
          node.visited = false;
      }

      return visitedNodes;
    
  }
  
  
  public void shortestPath(Vertex source, Vertex destination ){
      
      dijkstra = new DijkstraShortestPath(this);
      dijkstra.execute(source);
      LinkedList<Vertex> path = dijkstra.getPath(destination);
      System.out.println(path);
  }
  
  
  public int getMinValue(int [] list){
      Arrays.sort(list);
      for (int i : list){
          
              return i;
          
      }
      return -1;
  }
  
  public boolean isConnected(){
      int numberOfNodes = nodes.size();
      
      for (Vertex node : nodes){
          if (!(this.breadthFirstSearch(node) == numberOfNodes)){
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
  public void createGraph(String path) throws FileNotFoundException, IOException{
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
      for (String nodeInString : nodesInString){

          node = new Vertex(nodeInString, "node " + nodeInString);
          nodes.add(node);
          
      }
      
      //create the list of edges
      String edgeIntoString;
      Vertex vertex_aux1 = new Vertex("-1","-1");
      Vertex vertex_aux2 = new Vertex("-1","-1");;
      int n = 0;
      //varre a linha que tem x-y
      while (!(edgeIntoString = textReader.readLine()).equals("-1")){
          
          String[] nodesConnected = edgeIntoString.split("\\s*-\\s*");
          //varre os dois nodes
         
          int peso = Integer.parseInt(textReader.readLine());
              
              //verifica se x e y passados no txt estão instanciados em nodes 
              for (Vertex node : nodes){
         
                  if (nodesConnected[0].equals(node.getId())){
                      //se o node do txt foi achado em nodes
                      
                      vertex_aux1 = new Vertex(node.getId(), node.getName());
                  }
              }
              for (Vertex node : nodes){
         
                  if (nodesConnected[1].equals(node.getId())){
                     
                      
                      vertex_aux2 = new Vertex(node.getId(), node.getName());
                  }
              }
              
              
          edge = new Edge(Integer.toString(n),vertex_aux1, vertex_aux2, peso);
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
  
  public int getDegree(Vertex node){
      
      degree = 0;
      
      for (Vertex CurrentNode : nodes){
          if (hasEdge(node, CurrentNode)){
              degree++;
          }
      }
  
      return degree;
  }
  
  public void printGraph() {
   
      for (Vertex node: this.getNodes()){
          System.out.println(node.getName());
          System.out.print("Adjacent nodes: ");
          adjacentNodes = getAdjacentNodes(node);
          for (Vertex adjacentNode : adjacentNodes){
              System.out.print(adjacentNode.getName() + " ");
          }

          System.out.println(" ");
          System.out.println("Degree of this node: " + getDegree(node));
          System.out.println(" "); 
      }
      System.out.println("- - - - - - - - - - - - - - - - ");
      System.out.println(" ");
      System.out.println("Min Degree of this Graph: " + getMinDegree());
      System.out.println(" ");
      System.out.println("Max Degree of this Graph: " + getMaxDegree());
      System.out.println(" ");
      System.out.println("Average Degree of this Graph: " + getAvgDegree());
      System.out.println(" ");
      System.out.println("Graph is connected: " + this.isConnected());
      System.out.println(" ");
      System.out.println("- - - - - - - - - - - - - - - - ");
      System.out.println("Connections: ");
      System.out.println(" ");
      for (Edge edge : this.getEdges() ){
          System.out.print(edge.getSource().getId() +"<->"+edge.getDestination().getId()+" ");
          System.out.println("weight: " + edge.getWeight());
      }
      System.out.println(" ");
      System.out.println("- - - - - - - - - - - - - - - - ");
      this.setAdjacencyMatrix();
      this.shortestPath(this.nodes.get(0), this.nodes.get(4));
     
    }
  
  public void setAdjacencyMatrix() {
    
    initializeAdjacencyMatrix();
    int weigth;
    int source;
    int destination;
    for (Edge currentEdge : this.getEdges() ){
        weigth = currentEdge.getWeight();
        source = (int) nodesMatrixMap.get(currentEdge.getSource().getName());
        destination = (int) nodesMatrixMap.get(currentEdge.getDestination().getName());

        adjacencyMatrix[source][destination] = weigth; 
        //must be symmetric
        adjacencyMatrix[destination][source] = weigth; 
    }
  }
  
  public void initializeAdjacencyMatrix(){
      int numNodes = this.nodes.size();
      adjacencyMatrix = new int[numNodes][numNodes];
      nodesMatrixMap = new HashMap();
      for (int i = 0; i < numNodes; i++) {
          nodesMatrixMap.put(this.nodes.get(i).getName(), i);
          for (int j = 0; j < numNodes; j++) {
              adjacencyMatrix[i][j] = -1;
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
              if (adjacencyMatrix[source][dest] == -1) {
                  System.out.print("- ");
              } else {
                  System.out.print(adjacencyMatrix[source][dest] + " ");
              }
          }
          System.out.print("\n");
      }
  }
  
}
