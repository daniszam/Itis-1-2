

import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.LinkedList;


/**
 *
 * @author danis
 */
public class MaxFlow {
    private int size;
    
    public MaxFlow(int[][] graph){
        size = graph.length;
    }
       

    private boolean bfs(int rGraph[][], int s, int t, int parent[]) {
        boolean visited[] = new boolean[size];
        for (int i = 0; i < size; ++i) {
            visited[i] = false;
        }
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s); 
        visited[s] = true;
        parent[s] = -1;
        while (queue.size() != 0) { // пока есть элементы ищем всевозможные пути
            int u = queue.poll();
            for (int i = 0; i < size; i++) {
                if (visited[i] == false && rGraph[u][i] > 0) {
                    queue.add(i);
                    parent[i] = u;
                    visited[i] = true;
                }
            }
        }

        return (visited[t] == true);
    }

    public int fordFulkerson(int graph[][], int s, int t) {
        int rGraph[][] = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                rGraph[i][j] = graph[i][j];
            }
        }
        int parent[] = new int[size];
        int  maxFlow = 0;
        int u;
        while (bfs(rGraph, s, t, parent)) {
            int pathFlow = Integer.MAX_VALUE; // бесконечность
            for (int i = t; i != s; i = parent[i]) {
                u = parent[i];
                pathFlow = Math.min(pathFlow, rGraph[u][i]);
            }
            for (int i = t; i != s; i = parent[i]) {
                u = parent[i];
                rGraph[u][i] -= pathFlow;
                rGraph[i][u] += pathFlow;
            }
            maxFlow += pathFlow;
        }

        return  maxFlow;
    }

    public static void main(String[] args) throws java.lang.Exception {
        int graph[][] = new int[][]{{0, 6, 0, 0, 2, 0, 0, 0},
                                    {0, 0, 5, 4, 0, 7, 0 , 0},
                                    {0, 0, 0, 0, 0, 0, 0 ,8},
                                    {0, 0, 0, 0, 9, 0, 0, 0},
                                    {0, 0, 9, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 6, 0,0 ,11},
                                    {5, 0, 0, 10, 0, 0, 0 , 0},
                                    {0, 0, 0, 0, 0, 0, 0 , 0},
                                    };
        MaxFlow m = new MaxFlow( graph);

        System.out.println("Максмальный flow "
                + m.fordFulkerson(graph, 6, 7));

    }
}
