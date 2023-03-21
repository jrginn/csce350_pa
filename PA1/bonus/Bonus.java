package PA1.bonus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import PA1.quicksort.*;

public class Bonus {

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
        String str = QS.writeArray(arr);
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
                double[] arr = QS.readArray("./PA1/bonus/" + size + "/" + j + ".txt");
                long start = System.nanoTime();
                QS.quickSort(arr, 0, arr.length-1);
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
