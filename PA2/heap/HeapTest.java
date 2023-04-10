package PA2.heap;

public class HeapTest {
    
    public static void main(String[] args) {
        int[] arr = {35, 33, 42, 10, 14, 19, 27, 44, 26, 31};
        MaxHeap mxheap = new MaxHeap(arr);
        System.out.println(mxheap);
        MinHeap mnheap = new MinHeap(arr);
        System.out.println(mnheap);
    }

}
