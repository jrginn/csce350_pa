package PA2.heap;

public class HeapTest {
    
    public static void main(String[] args) {
        // int[] arr = {10, 7, 15, 17, 12, 20, 6, 32};
        // MaxHeap mxheap = new MaxHeap(arr);
        // System.out.println(mxheap);
        // int[] arr2 = {10, 7, 15, 17, 12, 20, 6, 32};
        // MinHeap mnheap = new MinHeap(arr2);
        // System.out.println(mnheap);
        int[] arr = new int[10];
        int failCount = 0;
        for(int i = 0; i < 999; i++) {
            for(int j = 0; j < arr.length; j++) {
                arr[j] = (int)(Math.random() * 100);
            }
            MaxHeap heap = new  MaxHeap(arr);
            if(!checkMaxHeap(heap)) {
                failCount++;
            }
            else if(i % 5 == 0) {
                System.out.println(heap);
            }
        }
        System.out.println(failCount);
    }

    public static boolean checkMaxHeap(MaxHeap mh) {
        int[] heap = mh.getHeap();
        for(int i = 0; 2 * i + 2 < heap.length; i++) {
            if(heap[i] < heap[2 * i + 1] || heap[i] < heap[2 * i + 2]) {
                return false;
            }
        }
        return true;
    }

}
