/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GraphModels;

import java.util.List;

/**
 *
 * @author rodrigo
 */
public class Graph {
    
    /* This Data Structure will be capable to represent a Graph,
    *   where G = (V, E)
    */
    
   private final List<Vertex> nodes;
   private final List<Edge> edges;
   
   public Graph(List<Vertex> nodes, List<Edge> edges) {
    this.nodes = nodes;
    this.edges = edges;
  }

  public List<Vertex> getNodes() {
    return nodes;
  }

  public List<Edge> getEdges() {
    return edges;
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
  
}
