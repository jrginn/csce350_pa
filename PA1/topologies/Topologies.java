package PA1.topologies;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Topologies {

    public static int[][] readAdjmat() {
        // creates scanner to read input file
        File f = new File("./PA1/topologies/input.txt");
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
            lines.add(scan.nextLine().split(" "));
        }
        // ensures file has at least one entry
        if(lines.size() < 1) {
            System.out.println("Invalid file");
            scan.close();
            return null;
        }
        // converts ArrayList of string arrays to int 2d array
        int n = lines.get(0).length;
        int[][] adjmat = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                adjmat[i][j] = Integer.parseInt(lines.get(i)[j]);
            }
        }
        scan.close();
        return adjmat;
    }
    
    public static void printMatrix(int[][] mat) {
        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j]);
                if(j != mat[i].length - 1) {
                    System.out.print(" ");
                }
            }
            if(i != mat.length - 1) {
                System.out.print("\n");
            }
        }
    }

    public static boolean checkMesh(int[][] mat) {
        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat[0].length; j++) {
                if((mat[i][j] == 0 && i != j) || (mat[i][j] == 1 && i == j)) {
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        //printMatrix(readAdjmat());
        System.out.println(checkMesh(readAdjmat()));
    }
}
