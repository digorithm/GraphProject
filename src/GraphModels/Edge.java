/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package GraphModels;

/**
 *
 * @author rodrigo
 */
public class Edge {
    
     /* This Data Structure will be capable to represent a Edge,
    *    that will be used on a graph
    */
    
    public String id;
    public Vertex source;
    public Vertex destination;
    private final int weight;
    
    public Edge(String id, Vertex source, Vertex destination, int weight) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
    
    public String getId() {
        return id;
    }
    public Vertex getDestination() {
        return destination;
    }
    
    public Vertex getSource() {
        return source;
    }
    public int getWeight() {
        return weight;
    }
    
    @Override
    public String toString() {
        return source + " " + destination;
    }
    
    
} 
    
