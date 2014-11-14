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
public class FloydShortestPath {

  /**
   * Floyd-Warshall algorithm. Finds all shortest paths among all pairs of nodes
   *
   * @param d matrix of distances (Integer.MAX_VALUE represents positive
   * infinity)
   * @return matrix of predecessors
   */
  int[][] p;
  public int[][] floydWarshall(int[][] d) {
    p = constructInitialMatixOfPredecessors(d);
    for (int k = 0; k < d.length; k++) {
      for (int i = 0; i < d.length; i++) {
        for (int j = 0; j < d.length; j++) {
          if (d[i][k] == Integer.MAX_VALUE || d[k][j] == Integer.MAX_VALUE) {
            continue;
          }

          if (d[i][j] > d[i][k] + d[k][j]) {
            d[i][j] = d[i][k] + d[k][j];
            p[i][j] = p[k][j];
          }

        }
      }
    }
    return p;
  }

  /**
   * Constructs matrix P0
   *
   * @param d matrix of lengths
   * @return P0
   */
  public int[][] constructInitialMatixOfPredecessors(int[][] d) {
    int[][] p = new int[d.length][d.length];
    for (int i = 0; i < d.length; i++) {
      for (int j = 0; j < d.length; j++) {
        if (d[i][j] != 0 && d[i][j] != Integer.MAX_VALUE) {
          p[i][j] = i;
        } else {
          p[i][j] = -1;
        }
      }
    }
    return p;
  }

  public void getPath(int i, int j) {
    if (i != j) {
      getPath(i, p[i][j]);
    }
    
    System.out.print("node " + j + " ");
  
  }
}
