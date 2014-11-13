/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GraphModels;

import static java.lang.Integer.MAX_VALUE;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author daniel-almeida
 */
public class BellmanFordShortestPath {
  private final List<Vertex> nodes;
  private final List<Edge> edges;

  //list with the predecessors of the nodes
  private Map<Vertex, Vertex> predecessors;
  //list of the distance of each node
  private Map<Vertex, Integer> distance;
  private Graph graph;

  public BellmanFordShortestPath(Graph graph) {
    // create a copy of the array so that we can operate on this array
    this.nodes = new ArrayList<Vertex>(graph.getNodes());
    this.edges = new ArrayList<Edge>(graph.getEdges());
    this.graph = graph;
  }

  public void execute(Vertex source) {
    distance = new HashMap<Vertex, Integer>();
    predecessors = new HashMap<Vertex, Vertex>();
    

    // Init
    for (Vertex v : nodes) {
      if (v == source) {
        distance.put(v, 0);
      } else {
        distance.put(v, MAX_VALUE);
        predecessors.put(source, null);
      }
    }

    // Relaxation
    for(int i = 0; i < nodes.size() - 1; i++) {
        for (Edge edge : edges) {
            if (distance.get(edge.getDestination()) > distance.get(edge.getSource()) + edge.getWeight()) {
                distance.put(edge.getDestination(), distance.get(edge.getSource()) + edge.getWeight());
                predecessors.put(edge.getDestination(), edge.getSource());
            }
        }
    }

    // Negative cycle?
    for (Edge edge : edges) {
        if (distance.get(edge.getDestination()) > distance.get(edge.getSource()) + edge.getWeight()) {
            throw new RuntimeException("Negative cycle"); // Negative cycle. Return false or empty predecessors?
        }
    }
    
    // Print distance to each possible destination
//    for (Map.Entry<Vertex, Integer> entry : distance.entrySet()) {
//        System.out.println("Destination = " + entry.getKey() + ", Distance = " + entry.getValue());
//    }
    // Print each predecessor
//    for (Map.Entry<Vertex, Vertex> entry : predecessors.entrySet()) {
//        if (entry.getValue() != null) {
//          System.out.println(entry.getValue().getName() + " => " + entry.getKey().getName());
//        } 
//    }

  }

  private int getShortestDistance(Vertex destination) {
    Integer d = distance.get(destination);
    if (d == null) {
      return Integer.MAX_VALUE;
    } else {
      return d;
    }
  }

  /*
   * This method returns the path from the source to the selected target and
   * NULL if no path exists
   */
  public LinkedList<Vertex> getPath(Vertex target) {
    LinkedList<Vertex> path = new LinkedList<Vertex>();
    Vertex step = target;
    // check if a path exists
    if (predecessors.get(step) == null) {
      return null;
    }
    path.add(step);
    while (predecessors.get(step) != null) {
      step = predecessors.get(step);
      path.add(step);
    }
    // Put it into the correct order
    Collections.reverse(path);
   
    return path;
  }

}
