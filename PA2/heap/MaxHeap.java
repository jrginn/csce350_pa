package PA2.heap;

public class MaxHeap {
    
    private int[] heap;

    public MaxHeap(int[] heap) {
        this.heap = heapify(heap);
    }

    public static int[] heapify(int[] heap) {
        // implements HeapBottomUp from textbook
        // Subtracts one from indices to account for book's one-indexing
        for(int i = heap.length/2; i >= 0; i--) {
            int k = i;
            int v = heap[k];
            boolean isHeap = false;
            // checks parental dominance until fully satisfied
            while(!isHeap && 2 * k + 2 < heap.length) {
                int j = 2 * k + 1;
                // two children, finds largest
                if(j < heap.length) {
                    if(heap[j] < heap[j + 1]) {
                        j = j++;
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
        for(Integer x: heap) {
            str += x + " ";
        }
        return str.substring(0, str.length()-1);
    }

}
