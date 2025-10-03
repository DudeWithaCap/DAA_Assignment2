public class MaxHeap {
    private int[] heap;
    private int size;
    private int capacity;
    private int comparisons;
    private int swaps;
    private int arrayAccesses;
    private int memoryAllocations;
    private long totalTime;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
        this.memoryAllocations++;
        this.totalTime = 0;
    }

    public void insert(int key) {
        long start = System.nanoTime();

        if (size == capacity) {
            throw new IllegalStateException("Heap overflow");
        }
        heap[size] = key;
        arrayAccesses++;
        size++;
        heapifyUp(size - 1);

        totalTime += (System.nanoTime() - start);
    }

    public int getMax() {
        if (size <= 0) {
            throw new RuntimeException("Heap is empty");
        }
        return heap[0];
    }


    public int extractMax() {
        long start = System.nanoTime();

        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        int max = heap[0];
        heap[0] = heap[size - 1];
        arrayAccesses += 2;
        size--;
        heapifyDown(0);

        totalTime += (System.nanoTime() - start);
        return max;
    }

    public void increaseKey(int i, int newValue) {
        long start = System.nanoTime();

        if (newValue < heap[i]) {
            throw new IllegalArgumentException("New value is smaller");
        }
        heap[i] = newValue;
        arrayAccesses++;
        heapifyUp(i);

        totalTime += (System.nanoTime() - start);
    }

    private void heapifyUp(int i) {
        while (i > 0 && heap[parent(i)] < heap[i]) {
            comparisons++;
            swap(i, parent(i));
            i = parent(i);
        }
    }

    private void heapifyDown(int i) {
        int largest = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if (left < size && heap[left] > heap[largest]) {
            comparisons++;
            largest = left;
        }
        if (right < size && heap[right] > heap[largest]) {
            comparisons++;
            largest = right;
        }
        if (largest != i) {
            swap(i, largest);
            heapifyDown(largest);
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
        swaps++;
        arrayAccesses += 4;
    }

    public String getMetrics() {
        return "Comparisons=" + comparisons +
                ", Swaps=" + swaps +
                ", ArrayAccesses=" + arrayAccesses +
                ", MemoryAllocations=" + memoryAllocations +
                ", TotalTime(ns)=" + totalTime;
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int leftChild(int i) { return 2 * i + 1; }
    private int rightChild(int i) { return 2 * i + 2; }
    public int getSize() { return size; }

    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
}
