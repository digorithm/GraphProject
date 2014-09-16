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
import java.util.List;

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
  
  
  //TO-DO: error treatment. 
  // while this treatment doesn't exist: BE EXTREMELY CAREFUL WITH GRAPH.TXT
  // the right syntax is:
  //line (1) :1,2,...,n 
  //line (2) :1-2
  //line (n) :2-n
  //line (n+1) :-1
  //where the first line you must declare the nodes, each one separated by comma
  //the second til the n-th line you must declare the connections between the nodes declared 
  //in the first line
  //after the last connection, you must end with a -1
  // if you don't do this you computer may explode
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
              
              
          edge = new Edge(Integer.toString(n),vertex_aux1, vertex_aux2, 0);
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
  
  public void printGraph() {
      for (Vertex node: this.getNodes()){
             System.out.println(node.getName());
      }
      
      for (Edge edge : this.getEdges() ){
             System.out.println(edge.getSource().getId() +"->"+edge.getDestination().getId());
      }
      
    }
  
  
  
  
}
