package PA2.heap;

public class MinHeap {
    
    private int[] heap;

    public MinHeap(int[] heap) {
        this.heap = heapify(heap);
    }

    public static int[] heapify(int[] heap) {
        // implements HeapBottomUp from textbook for MinHeap
        // Subtracts one from indices to account for book's one-indexing
        for(int i = heap.length/2; i >= 1; i--) {
            int k = i;
            int v = heap[k - 1];
            boolean isHeap = false;
            // checks parental dominance until fully satisfied
            while(!isHeap && 2 * k <= heap.length) {
                int j = 2 * k;
                // two children, finds smallest
                if(j < heap.length) {
                    if(heap[j - 1] > heap[j]) {
                        j = j++;
                    }
                }
                // parental dominance is violated (good for MinHeap)
                if(v  <= heap[j - 1]) {
                    isHeap = true;
                }
                else {
                    // swaps with smaller child
                    heap[k - 1] = heap[j - 1];
                    k = j;
                }
            }
            heap[k - 1] = v;
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
