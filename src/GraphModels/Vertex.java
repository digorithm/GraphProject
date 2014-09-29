
package GraphModels;

/**
 *
 * @author rodrigo
 */

public class Vertex {
    /* This Data Structure will be capable to represent a Vertex,
    * which is a Node that will be used on a graph
    */
    
    public String id;
    public String name;
    public boolean visited = false;
    
    public Vertex(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
      @Override
  public String toString() {
    return name;
  }
    
}
