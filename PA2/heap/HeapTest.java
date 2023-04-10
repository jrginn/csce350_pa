package PA2.heap;

public class HeapTest {
    
    public static void main(String[] args) {
        // int[] arr = {10, 7, 19, 17, 12, 20, 6, 2};
        // MaxHeap mxheap = new MaxHeap(arr);
        // System.out.println(mxheap);
        // System.out.println(checkMaxHeap(mxheap));
        // int[] arr2 = {10, 7, 15, 17, 12, 20, 6, 32};
        // MinHeap mnheap = new MinHeap(arr2);
        // System.out.println(mnheap);
        int[] arr = new int[10];
        int failCount = 0;
        for(int i = 0; i < 999; i++) {
            for(int j = 0; j < arr.length; j++) {
                arr[j] = (int)(Math.random() * 100);
            }
            MinHeap heap = new  MinHeap(arr);
            if(!checkMinHeap(heap)) {
                failCount++;
            }
        }
        System.out.println(failCount);
    }

    public static boolean checkMaxHeap(MaxHeap mh) {
        int[] heap = mh.getHeap();
        for(int i = 1; 2 * i + 1 < heap.length; i++) {
            if(heap[i] < heap[2 * i] || heap[i] < heap[2 * i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkMinHeap(MinHeap mh) {
        int[] heap = mh.getHeap();
        for(int i = 1; 2 * i + 1 < heap.length; i++) {
            if(heap[i] > heap[2 * i] || heap[i] > heap[2 * i + 1]) {
                return false;
            }
        }
        return true;
    }

}
