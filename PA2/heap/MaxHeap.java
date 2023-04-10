package PA2.heap;

public class MaxHeap {
    
    private int[] heap;

    public MaxHeap(int[] heap) {
        this.heap = heap;
        for(int i = heap.length/2; i >= 1; i--) {
            int k = i;
            int v = heap[k - 1];
            boolean isHeap = false;
            while(!isHeap && 2 * k <= heap.length) {
                int j = 2 * k;
                // two children
                if(j < heap.length) {
                    if(heap[j - 1] < heap[j]) {
                        j = j++;
                    }
                }
                if(v  >= heap[j - 1]) {
                    isHeap = true;
                }
                else {
                    heap[k - 1] = heap[j - 1];
                    k = j;
                }
                heap[k - 1] = v;
            }
        }
    }

    public String toString() {
        String str = "";
        for(Integer x: heap) {
            str += x + " ";
        }
        return str.substring(0, str.length()-1);
    }

}
