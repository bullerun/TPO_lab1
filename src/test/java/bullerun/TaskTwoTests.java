package bullerun;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTwoTests {
    @Test
    void testInsertAndExtractMin() {
        BinomialHeap heap = new BinomialHeap();
        heap.insert(10);
        heap.insert(5);
        heap.insert(20);
        heap.insert(1);

        assertEquals(1, heap.extractMin());
        assertEquals(5, heap.extractMin());
        assertEquals(10, heap.extractMin());
        assertEquals(20, heap.extractMin());
    }

    @Test
    void testExtractMinFromEmptyHeap() {
        BinomialHeap heap = new BinomialHeap();
        assertEquals(-1, heap.extractMin());
    }

    @Test
    void testInsertAndGetMin() {
        BinomialHeap heap = new BinomialHeap();
        heap.insert(10);
        heap.insert(20);
        heap.insert(5);
        assertEquals(5, heap.getMin());
    }

    @Test
    void testExtractMin() {
        BinomialHeap heap = new BinomialHeap();
        heap.insert(10);
        heap.insert(20);
        heap.insert(5);
        assertEquals(5, heap.extractMin());
        assertEquals(10, heap.getMin());
    }


}
