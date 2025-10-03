import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MaxHeapTest {

    @Test
    void testInsertAndExtract() {
        MaxHeap heap = new MaxHeap(10);
        heap.insert(5);
        heap.insert(10);
        heap.insert(1);

        assertEquals(10, heap.extractMax());
        assertEquals(5, heap.extractMax());
        assertEquals(1, heap.extractMax());
    }

    @Test
    void testIncreaseKey() {
        MaxHeap heap = new MaxHeap(10);
        heap.insert(10);
        heap.insert(20);
        heap.insert(15);

        heap.increaseKey(3, 25);

        assertEquals(25, heap.getMax());
    }

    @Test
    void testEmptyHeap() {
        MaxHeap heap = new MaxHeap(5);
        assertThrows(RuntimeException.class, heap::extractMax);
        assertThrows(RuntimeException.class, heap::getMax);
    }

    @Test
    void testDuplicateValues() {
        MaxHeap heap = new MaxHeap(5);
        heap.insert(10);
        heap.insert(10);
        heap.insert(10);

        assertEquals(10, heap.extractMax());
        assertEquals(10, heap.extractMax());
        assertEquals(10, heap.extractMax());
    }

    @Test
    void testCapacityOverflow() {
        MaxHeap heap = new MaxHeap(2);
        heap.insert(1);
        heap.insert(2);
        assertThrows(RuntimeException.class, () -> heap.insert(3));
    }
}
