package PA4.floyd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Floyd {
    
    /**
     * Takes input from input.txt to create adjacency matrix
     * @return 2d double adjacency matrix
     */
    public static double[][] readAdjmat() {
        // creates scanner to read input file
        File f = new File("./PA4/floyd/input.txt");
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
        int n = lines.get(0).length;
        double[][] adjmat = new double[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                adjmat[i][j] = Double.parseDouble(lines.get(i)[j]);
                if(i != j && adjmat[i][j] == 0) {
                    // accounts for infinity values
                    adjmat[i][j] = Double.MAX_VALUE;
                }
            }
        }
        scan.close();
        return adjmat;
    }

    /**
     * Prints an adjacency matrix row by row
     * @param mat adjacency matrix
     */
    public static void printMatrix(double[][] mat) {
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

    public static double[][] floydPaths(double[][] mat) {
        double[][] lengths = mat.clone();
        int n = lengths.length;
        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    lengths[i][j] = Math.min(lengths[i][j], lengths[i][k] + lengths[k][j]);
                }
            }
        }
        return lengths;
    }

    public static String adjMatString(double[][] adjmat) {
        String str = "";
        for(int i = 0; i < adjmat.length; i++) {
            String row = "";
            for(int j = 0; j < adjmat[i].length; j++) {
                row += adjmat[i][j] + " ";
            }
            str += row.substring(0, row.length()-1) + "\n";
        }
        return str.substring(0, str.length()-1);
    }

    public static void main(String[] args) {
        double[][] adjmat = readAdjmat();
        long start = System.nanoTime();
        double[][] pathMatrix = floydPaths(adjmat);
        System.out.println("Time of execution in nanoseconds: " + (System.nanoTime() - start));
        File f = new File("./PA4/floyd/output.txt");
        FileWriter outputWriter;
        try {
            outputWriter = new FileWriter(f);
            outputWriter.write(adjMatString(pathMatrix));
            outputWriter.close();
        } catch (IOException e) {
            System.out.println("output.txt does not exist");
            return;
        }
    }
}
