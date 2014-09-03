/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graphproject;
import GraphModels.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rodrigo
 */
public class GraphProject {
    private List<Vertex> nodes;
    private List<Edge> edges;
    private Graph graph;
    public Vertex node;
    public Edge edge;
    
   public void test(){
       nodes = new ArrayList<Vertex>();
       edges = new ArrayList<Edge>();
       
       for (Integer n = 0; n<3; n++){
           node = new Vertex(n.toString(), "node " + n.toString());
           nodes.add(node);
       }
       
       edge = new Edge("1", nodes.get(0), nodes.get(1), 0);
       edges.add(edge);
       edge = new Edge("1", nodes.get(1), nodes.get(2), 0);
       edges.add(edge);
       edge = new Edge("1", nodes.get(2), nodes.get(0), 0);
       edges.add(edge);
       
       graph = new Graph(nodes, edges);
       
       for (Edge edge : graph.getEdges() ){
           System.out.println(edge.toString());
           System.out.println(" ");
       }
       
       for (Vertex node: graph.getNodes()){
           System.out.println(node.toString());
       }
       
       
   }
 
    public static void main(String[] args) {
       GraphProject gp = new GraphProject();
       
       gp.test();
    }
    
}
