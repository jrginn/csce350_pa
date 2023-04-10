package PA2.heap;

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

    public String toString() {
        String str = "";
        for(int i = 1; i < heap.length; i++) {
            str += heap[i] + " ";
        }
        return str.substring(0, str.length()-1);
    }

}
