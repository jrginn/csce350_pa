package PA2.heap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MinHeap {
    
    private int[] heap;

    public MinHeap(int[] heap) {
        int[] arr = new int[heap.length + 1];
        arr[0] = -1;
        for(int i = 1; i < arr.length; i++) {
            arr[i] = heap[i-1];
        }
        this.heap = heapify(arr);
    }

    public static int[] heapify(int[] heap) {
        // implements HeapBottomUp from textbook for MinHeap
        int n = heap.length - 1;
        for(int i = n/2; i >= 1; i--) {
            int k = i;
            int v = heap[k];
            boolean isHeap = false;
            // checks parental dominance until not present
            while(!isHeap && 2 * k <= n) {
                int j = 2 * k;
                // two children, finds smallest
                if(j < n) {
                    if(heap[j] > heap[j + 1]) {
                        j++;
                    }
                }
                // parental dominance is not satisfied
                if(v  <= heap[j]) {
                    isHeap = true;
                }
                else {
                    // swaps with larger child
                    heap[k] = heap[j];
                    k = j;
                }
            }
            heap[k] = v;
        }
        return heap;
    }

    public int[] getHeap() {
        return this.heap;
    }

    public String toString() {
        String str = "";
        for(int i = 1; i < heap.length; i++) {
            str += heap[i] + " ";
        }
        return str.substring(0, str.length()-1);
    }

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

    public static void main(String[] args) {
        int[] arr = readArray("./PA2/heap/input.txt");
        long start = System.nanoTime();
        MinHeap heap = new MinHeap(arr);
        System.out.println("Time of execution in nanoseconds: " + (System.nanoTime() - start));
        File f = new File("./PA2/heap/min_output.txt");
        FileWriter outputWriter;
        try {
            outputWriter = new FileWriter(f);
            outputWriter.write(writeHeapArray(heap.getHeap()));
            outputWriter.close();
        }
        catch(IOException e) {
            System.out.println("min_output.txt does not exist");
            return;
        }
    }

}
