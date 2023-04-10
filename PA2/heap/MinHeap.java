package PA2.heap;

public class MinHeap {
    
    private int[] heap;

    public MinHeap(int[] heap) {
        this.heap = heapify(heap);
    }

    public static int[] heapify(int[] heap) {
        // implements HeapBottomUp from textbook for MinHeap
        // Subtracts one from indices to account for book's one-indexing
        for(int i = heap.length/2; i >= 0; i--) {
            int k = i;
            int v = heap[k];
            boolean isHeap = false;
            // checks parental dominance until fully satisfied
            while(!isHeap && 2 * k + 2 < heap.length) {
                int j = 2 * k + 1;
                // two children, finds smallest
                if(j < heap.length) {
                    if(heap[j] > heap[j + 1]) {
                        j++;
                    }
                }
                // parental dominance is violated (good for MinHeap)
                if(v  <= heap[j]) {
                    isHeap = true;
                }
                else {
                    // swaps with smaller child
                    heap[k] = heap[j];
                    k = j;
                }
            }
            heap[k] = v;
        }
        return heap;
    }

    public String toString() {
        String str = "";
        for(Integer x: heap) {
            str += x + " ";
        }
        return str.substring(0, str.length()-1);
    }

}
