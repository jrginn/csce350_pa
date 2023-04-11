package PA2.heap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HeapSort {
    
     /**
     * Creates a int array based on the contents of input.txt
     * @return array with ints in input.txt
     */
    public static int[] readArray(String filepath) {
        File f = new File(filepath);
        Scanner scan;
        try {
            scan = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("input.txt could not be found");
            return null;
        }
        int n = Integer.parseInt(scan.nextLine());
        String[] nums = scan.nextLine().split("\\s");
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(nums[i]);
        }
        scan.close();
        return arr;
    }

    /**
     * Adds each element of arr to a line excluding first element
     * @param arr array of ints
     * @return space-separated string containing arr elements
     */
    public static String writeHeapArray(int[] arr) {
        String str = "";
        for(int i = 1; i < arr.length; i++) {
            str += arr[i] + " ";
        }
        return str;
    }

    public static int[] removeRoot(int[] arr) {
        int[] ret = new int[arr.length - 1];
        for(int i = 0; i < ret.length; i++) {
            ret[i] = arr[i+1];
        }
        return ret;
    }

    public static int[] heapsort(MaxHeap heap) {
        int[] arr = heap.getHeapContents();
        int[] sorted = new int[arr.length];
        for(int i = 0; i < sorted.length; i++) {
            sorted[i] = arr[0];
            heap = new MaxHeap(removeRoot(arr));
            arr = heap.getHeapContents();
        }
        return sorted;
    }

    // Used to test the validity of the sorting
    public static boolean checkSorted(int[] arr) {
        for(int i = 0; i < arr.length - 1; i++) {
            if(arr[i] < arr[i+1])
            {
                return false;
            }  
        }
        return true;
    }

    /**
     * Adds each element of arr to a line
     * @param arr array of doubles
     * @return space-separated string containing arr elements
     */
    public static String writeArray(int[] arr) {
        String str = "";
        for(int i = 0; i < arr.length; i++) {
            str += arr[i] + " ";
        }
        return str;
    }

    public static void main(String[] args) {
        int[] arr = readArray("./PA2/heap/input.txt");
        long start = System.nanoTime();
        MaxHeap heap = new MaxHeap(arr);
        int[] sorted = heapsort(heap);
        System.out.println("Time of execution in nanoseconds: " + (System.nanoTime() - start));
        File f = new File("./PA2/heap/sort_output.txt");
        FileWriter outputWriter;
        try {
            outputWriter = new FileWriter(f);
            outputWriter.write(writeHeapArray(heap.getHeap()) + "\n");
            outputWriter.write(writeArray(sorted));
            outputWriter.close();
        }
        catch(IOException e) {
            System.out.println("sort_output.txt does not exist");
            return;
        }
        // Tests if sorting works
        // int[] arr = new int[10];
        // int failCount = 0;
        // for(int i = 0; i < 999; i++) {
        //     for(int j = 0; j < arr.length; j++) {
        //         arr[j] = (int)(Math.random() * 100);
        //     }
        //     MaxHeap heap = new MaxHeap(arr);
        //     int[] sorted = heapsort(heap);
        //     if(!checkSorted(sorted)) {
        //         failCount++;
        //     }
        // }
        // System.out.println(failCount);
    }

}
