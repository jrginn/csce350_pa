package PA2.heap;

public class HeapTest {
    
    public static void main(String[] args) {
        int[] arr = {1, 8, 6, 5, 3, 7, 4};
        MaxHeap heap = new MaxHeap(arr);
        System.out.println(heap);
    }

}
