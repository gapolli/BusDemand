package busdemand;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Singleton class that calculates the shortest path in a set of coordinates.
 * 
 * @author meira
 */
public class AllPairShortestPath implements Serializable {
    Graph g = Graph.getInstance();
    private final int n = g.n;
    double[][] dist = new double[n][n];
    int[][] next = new int[n][n];
    private final AbsCoordinate Predecessor[][] = new AbsCoordinate[n][n];
    private static final int MAX = Integer.MAX_VALUE / 1000;
    private static AllPairShortestPath INST = null;

    /**
     * Gets the current instance.
     * 
     * @return the instance.
     */
    public static AllPairShortestPath getInstance() {
        if (null == INST) {
            INST = new AllPairShortestPath();
        }
        return INST;
    }

    /**
     * The constructor of the class.
     */
    private AllPairShortestPath() {
        init();
        computeDist();
    }

    /**
     * Initializes the variables.
     */
    private void init() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    dist[i][j] = MAX;
                } else {
                    dist[i][j] = 0;
                }
                next[i][j] = -1;
            }
        }
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                dist[u][v] = dist[v][u] = g.w[u][v];
                next[u][v] = v;
                next[v][u] = u;
            }
        }
    }

    /**
     * Calculates distances between two given values. This values may represent
     * coordinates, per example.
     */
    private void computeDist() {
        for (int k = 0; k < n; k++) {
            System.out.println(k + "/" + n);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != MAX
                            && dist[k][j] != MAX
                            && dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }
    }

    /**
     * Prints the calculated distance.
     */
    public void print() {
        assert (false);
        System.out.println("W\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("\t" + dist[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Gets the distance between i and j.
     * 
     * @param i the first value.
     * @param j the second value.
     * @return the distance.
     */
    public double getDist(int i, int j) {
        return this.dist[i][j];
    }

    /**
     * Calculates the path in a graph.
     * 
     * @param u the first value.
     * @param v the final value.
     * @return the path.
     */
    public ArrayList<Integer> path(int u, int v) {
        ArrayList<Integer> path = new ArrayList<>();
        if (next[u][v] == -1) {
            return path;
        }
        path.add(u);
        while (u != v) {
            u = next[u][v];
            path.add(u);
        }
        return path;
    }
}
