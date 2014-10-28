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
        
        
        
        Graph graph = new Graph();
        graph.createGraph("/home/rodrigo/NetBeansProjects/livraria2/GraphProject/src/graphproject/graph.txt");
        
        graph.printGraph();
        graph.setAdjacencyMatrix();
        graph.printAdjacencyMatrix();
       
        
   
        
        
        
    }
    //end of graph creation procedure
 
    public static void main(String[] args) throws IOException {
       GraphProject gp = new GraphProject();
       
       gp.test();
       
     
    }
    
}
