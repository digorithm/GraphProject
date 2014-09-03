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
}
