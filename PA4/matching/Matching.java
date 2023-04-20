package PA4.matching;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Matching {

    public static HashSet<Integer> getV() {
        HashSet<Integer> v = new HashSet<Integer>();
        File f = new File("./PA4/matching/input.txt");
        Scanner scan;
        try {
            scan = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("input.txt could not be found");
            return null;
        }
        String[] nums = scan.nextLine().split("\\s");
        //int[] vals = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            v.add(Integer.parseInt(nums[i]));
        }
        scan.close();
        return v;
    }

    public static HashSet<Integer> getU() {
        HashSet<Integer> u = new HashSet<Integer>();
        File f = new File("./PA4/matching/input.txt");
        Scanner scan;
        try {
            scan = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("input.txt could not be found");
            return null;
        }
        //skips first line (V)
        scan.nextLine();
        String[] nums = scan.nextLine().split("\\s");
        //int[] vals = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            u.add(Integer.parseInt(nums[i]));
        }
        scan.close();
        return u;
    }

    /**
     * Takes input from input.txt to create adjacency matrix
     * @return 2d double adjacency matrix
     */
    public static int[][] readAdjmat() {
        // creates scanner to read input file
        File f = new File("./PA4/matching/input.txt");
        Scanner scan;
        try {
            scan = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("input.txt could not be found");
            return null;
        }
        ArrayList<String[]> lines = new ArrayList<>();
        while(scan.hasNextLine()) {
            // adds array of 0s and 1s to lines
            lines.add(scan.nextLine().split("\\s"));
        }
        // ensures file has at least one entry
        if(lines.size() < 1) {
            System.out.println("Invalid file");
            scan.close();
            return null;
        }
        // converts ArrayList of string arrays to int 2d array
        int n = lines.get(2).length;
        int[][] adjmat = new int[n][n];
        // skips V and U lines
        for(int i = 2; i < n; i++) {
            for(int j = 0; j < n; j++) {
                adjmat[i-2][j] = Integer.parseInt(lines.get(i)[j]);
            }
        }
        scan.close();
        return adjmat;
    }

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
                    if(!m.containsValue(u)) {
                        // augment
                        m.put(w, u);
                        m.put(u,w);
                        int v = w;
                        while(labels[v] != -1) {
                            u = labels[v];
                            m.remove(v);
                            m.remove(u);
                            v = labels[u];
                            m.put(v, u);
                            m.put(u, v);
                        }
                        revertLabels(labels);
                        // reinitializes queue with free verts
                        q.clear();
                        for(Integer f: a) {
                            if(!m.containsKey(f)) {
                                q.add(f);
                            }
                        }
                        break;
                    }
                    else {
                        if((!m.containsKey(w) || m.get(w) != u) && labels[u] == -1) {
                            labels[u] = w;
                            q.add(u);
                        }
                    }
                }
            }
            else {
                labels[m.get(w)] = w;
                q.add(m.get(w));
            }
        }
        return m;
    }

    public static void writeMatching(HashMap<Integer, Integer> match, HashSet<Integer> v) {
        File f = new File("./PA4/matching/output.txt");
        FileWriter outputWriter;
        try {
            outputWriter = new FileWriter(f);
            String vMatches = "";
            String uMatches = "";
            for(Integer a: v) {
                if(match.containsKey(a)) {
                    vMatches += a + " ";
                    uMatches += match.get(a) + " ";
                }
            }
            outputWriter.write(vMatches + "\n");
            outputWriter.write(uMatches);
            outputWriter.close();
        } catch (IOException e) {
            System.out.println("output.txt does not exist");
            return;
        }
    }

    public static void main(String[] args) {
        HashSet<Integer> v = getV();
        HashSet<Integer> u = getU();
        int[][] adjMat = readAdjmat();
        long start = System.nanoTime();
        HashMap<Integer, Integer> match = maxMatch(v, u, adjMat);
        System.out.println("Time of execution in nanoseconds: " + (System.nanoTime() - start));
        writeMatching(match, v);
    }

}
