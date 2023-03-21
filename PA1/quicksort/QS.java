package PA1.quicksort;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class QS {

    /**
     * Implements quicksort according to the texbook
     * @param arr array of doubles to be sorted
     * @param l left subarray bound
     * @param r right subarray bound
     */
    public static void quickSort(double[] arr, int l, int r) {
        if(l < r) {
            int s = partition(arr, l, r);
            quickSort(arr, l, s-1);
            quickSort(arr, s+1, r);
        }
    }

    /**
     * Partitions subarray arr[l, ..., r] with pivot arr[l]
     * @param arr array to be sorted
     * @param l left bound on subarray
     * @param r right bound on subarray
     * @return split index of partition's correct place
     */
    public static int partition(double[] arr, int l, int r) {
        double p = arr[l];
        int i = l+1;
        int j = r;
        while(i < j) {
            while(arr[i] <= p && i <= r) {
                i++;
            }
            
            while(arr[j] > p && i >= l) {
                j--;
            }
            swap(arr, i, j);
        }
        swap(arr, i, j);
        swap(arr, l, j);
        return j;
    }

    /**
     * Swaps arr[a] with arr[b]
     * @param arr array of doubles
     * @param a index to swap
     * @param b other index to swap
     */
    public static void swap(double[] arr, int a, int b) {
        double temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    
    /**
     * Creates a double array based on the contents of input.txt
     * @return array with doubles in input.txt
     */
    public static double[] readArray() {
        File f = new File("./PA1/quicksort/input.txt");
        Scanner scan;
        try {
            scan = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("input.txt could not be found");
            return null;
        }
        String[] words = scan.nextLine().split("\\s");
        double[] arr = new double[words.length];
        for(int i = 0; i < words.length; i++) {
            arr[i] = Double.parseDouble(words[i]);
        }
        scan.close();
        return arr;
    }

    /**
     * Adds each element of arr to a line
     * @param arr array of doubles
     * @return space-separated string containing arr elements
     */
    public static String writeArray(double[] arr) {
        String str = "";
        for(Double d: arr) {
            //System.out.print(d + " ");
            str += d + " ";
        }
        return str;
    }

    public static void main(String[] args) {
        double[] arr = readArray();
        long start = System.nanoTime();
        quickSort(arr, 0, arr.length-1);
        System.out.println("Time of execution in nanoseconds: " + (System.nanoTime() - start));
        File f = new File("./PA1/quicksort/output.txt");
        FileWriter outputWriter;
        try {
            outputWriter = new FileWriter(f);
            outputWriter.write(writeArray(arr));
            outputWriter.close();
        }
        catch(IOException e) {
            System.out.println("output.txt does not exist");
            return;
        }
    }
}
