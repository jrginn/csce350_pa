package PA2.heap;

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
        for(Integer x: heap) {
            str += x + " ";
        }
        return str.substring(0, str.length()-1);
    }

}
