/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graphproject;
import GraphModels.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rodrigo
 */
public class GraphProject {
    
    //graph creation procedure
    private List<Vertex> nodes;
    private List<Edge> edges;
    private Graph graph;
    public Vertex node;
    public Edge edge;
    
    public void printGraph(Graph graph) {
      for (Vertex node: graph.getNodes()){
             System.out.println(node.toString());
      }
      
      for (Edge edge : graph.getEdges() ){
             System.out.println(edge.toString());
             System.out.println(" ");
      }
      
    }
    
    public void test() throws IOException{
        
        /*
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();

        for (Integer n = 0; n<11; n++){
            node = new Vertex(n.toString(), "node " + n.toString());
            nodes.add(node);
        }

        edge = new Edge("id", nodes.get(0), nodes.get(1), 0);
        edges.add(edge);
        edge = new Edge("id", nodes.get(1), nodes.get(2), 0);
        edges.add(edge);
        edge = new Edge("id", nodes.get(2), nodes.get(0), 0);
        edges.add(edge);

        System.out.println("Graph - Initial state");
        graph = new Graph(nodes, edges); 
        printGraph(graph);
        graph.addEdge(nodes.get(3), nodes.get(4));
        graph.addEdge(nodes.get(3), nodes.get(5));
        graph.addEdge(nodes.get(3), nodes.get(6));
        graph.addEdge(nodes.get(5), nodes.get(6));
        System.out.println("Graph - Second state: new edges");
        printGraph(graph);
        graph.removeEdge(nodes.get(3), nodes.get(5));
        graph.removeEdge(nodes.get(3), nodes.get(6));
        System.out.println("Graph - Third state: removing 3-5 and 3-6");
        printGraph(graph);
        */
        //new way to build graph
        
        Graph graph = new Graph();
        graph.createGraph("/home/rodrigo/NetBeansProjects/livraria2/GraphProject/src/graphproject/graph.txt");
        
        graph.printGraph();
        
        
        
    }
    //end of graph creation procedure
 
    public static void main(String[] args) throws IOException {
       GraphProject gp = new GraphProject();
       
       gp.test();
       
     
    }
    
}
