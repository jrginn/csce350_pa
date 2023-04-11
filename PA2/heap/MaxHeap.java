package PA2.heap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MaxHeap {
    
    private int[] heap;

    public MaxHeap(int[] heap) {
        // Uses array of n + 1 length to account for 1-indexing
        int[] arr = new int[heap.length + 1];
        arr[0] = -1;
        for(int i = 1; i < arr.length; i++) {
            arr[i] = heap[i-1];
        }
        this.heap = heapify(arr);
    }

    public static int[] heapify(int[] heap) {
        // implements HeapBottomUp from textbook
        // Subtracts one from indices to account for book's one-indexing
        int n = heap.length - 1;
        for(int i = n/2; i >= 1; i--) {
            int k = i;
            int v = heap[k];
            boolean isHeap = false;
            // checks parental dominance until fully satisfied
            while(!isHeap && 2 * k <= n) {
                int j = 2 * k;
                // two children, finds largest
                if(j < n) {
                    if(heap[j] < heap[j + 1]) {
                        j++;
                    }
                }
                // parental dominance is satisfied
                if(v  >= heap[j]) {
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

    public int[] getHeapContents() {
        int[] nums = new int[heap.length -1];
        for(int i = 0; i < nums.length; i++) {
            nums[i] = heap[i + 1];
        }
        return nums;
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
        System.out.println("Time of heap creation in nanoseconds: " + (System.nanoTime() - start));
        start = System.nanoTime();
        int[] sorted = heapsort(heap);
        System.out.println("Time of heapsort in nanoseconds: " + (System.nanoTime() - start));
        File f = new File("./PA2/heap/max_output.txt");
        File g = new File("./PA2/heap/sort_output.txt");
        FileWriter outputWriter;
        try {
            outputWriter = new FileWriter(f);
            outputWriter.write(writeHeapArray(heap.getHeap()));
            outputWriter.close();
            outputWriter = new FileWriter(g);
            outputWriter.write(writeHeapArray(heap.getHeap()) + "\n");
            outputWriter.write(writeArray(sorted));
            outputWriter.close();
        }
        catch(IOException e) {
            System.out.println("max_output.txt does not exist");
            return;
        }
    }

}
