package PA2.heap;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MaxDriver {

    public static void main(String[] args) {
        int[] arr = IOHelper.readArray("./PA2/heap/input.txt");
        long start = System.nanoTime();
        MaxHeap heap = new MaxHeap(arr);
        System.out.println("Time of execution in nanoseconds: " + (System.nanoTime() - start));
        File f = new File("./PA2/heap/max_output.txt");
        FileWriter outputWriter;
        try {
            outputWriter = new FileWriter(f);
            outputWriter.write(IOHelper.writeHeapArray(heap.getHeap()));
            outputWriter.close();
        }
        catch(IOException e) {
            System.out.println("max_output.txt does not exist");
            return;
        }
    }
}
