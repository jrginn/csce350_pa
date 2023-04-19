package PA4.matching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Matching {

    public static ArrayList<Integer> getAdjVerts(int[][]mat, int vertex) {
        ArrayList<Integer> verts = new ArrayList<Integer>();
        for(int i = 0; i < mat.length; i++) {
            // checks if edge exists between vertex and i
            if(mat[vertex][i] == 1) {
                verts.add(i);
            }
        }
        return verts;
    }

    public static void revertLabels(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            arr[i] = -1;
        }
    }
    
    // set V is a, set W is b
    public static HashMap<Integer, Integer> maxMatch(HashSet<Integer> a, HashSet<Integer> b, int[][] mat) {
        // maps edges to each other to find maximum matching
        HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();
        Queue<Integer> q = new LinkedList<Integer>();
        int[] labels = new int[mat.length];
        revertLabels(labels);
        // initializes queue with V elements
        for(Integer v: a) {
            q.add(v);
        }
        // loops until queue is empty
        while(q.peek() != null) {
            int w = q.poll();
            // w is in V
            if(a.contains(w)) {
                // finds all adjacent vertices to w
                ArrayList<Integer> adjs = getAdjVerts(mat, w);
                for(Integer u: adjs) {
                    // u is free
                    if(!m.containsKey(u)) {
                        // augment
                        m.put(w, u);
                        int v = w;
                    }
                }
            }
        }
        return m;
    }


}
