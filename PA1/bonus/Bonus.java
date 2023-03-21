package PA1.bonus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Bonus {


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
        int i = l;
        int j = r;
        while(i < j) {
            while(arr[i] <= p && i < r) {
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
    public static double[] readArray(String filepath) {
        File f = new File(filepath);
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
        DecimalFormat numberFormat = new DecimalFormat("#.###");
        for(Double d: arr) {
            str += numberFormat.format(d) + " ";
        }
        return str;
    }

    /**
     * Creates an array of random doubles and saves it to a file
     * @param size size of array to be generated
     * @param n number of the file in the specific folder
     */
    public static void writeRandomFile(int size, int n) {
        File f = new File("./PA1/bonus/" + size + "/" + n + ".txt");
        double[] arr = new double[size];
        for(int i = 0; i < size; i++) {
            arr[i] = Math.random() * 10;
        }
        String str = writeArray(arr);
        try {
            f.createNewFile();
            FileWriter writer = new FileWriter(f);
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates all random input files with sizes of powers of 10
     */
    public static void generateAllFiles() {
        for(int i = 10; i <= 100000; i *= 10) {
            for(int j = 0; j < 100; j++) {
                writeRandomFile(i, j);
            }
        }
    }

    /**
     * Uses previously generated input files to analyze average execution time
     * @return array of longs where avgs[i] = average execution time for array of size 10^{i+1}
     */
    public static long[] testQS() {
        long[] avgs = new long[5];
        for(int i = 1; i <= 5; i++) {
            int size = (int)Math.pow(10, i);
            long total = 0;
            for(int j = 0; j < 100; j++) {
                double[] arr = readArray("./PA1/bonus/" + size + "/" + j + ".txt");
                long start = System.nanoTime();
                quickSort(arr, 0, arr.length-1);
                total += (System.nanoTime() - start);
            }
            avgs[i-1] = total/100;
        }
        return avgs;
    }
    

    public static void main(String[] args) {
        long[] arr = testQS();
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
