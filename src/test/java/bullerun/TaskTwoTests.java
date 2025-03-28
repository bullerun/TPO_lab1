package bullerun;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTwoTests {
    private BinomialHeap heap;

    @BeforeEach
    void setUp() {
        heap = new BinomialHeap();
    }

    @Test
    void testInsert() {
        heap.insert(10);
        assertEquals(10, heap.getMin());
    }


    @Test
    void testInsertAndExtractMin() {
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
        assertEquals(-1, heap.extractMin());
    }


    @ParameterizedTest
    @MethodSource("heapArgument")
    public void testMinInList(int[] input, int[] expected) {
        for (int i : input) {
            heap.insert(i);
        }
        LinkedList<BinomialHeap.Node> head = heap.getHeap();
        assertEquals(head.size(), expected.length);
        for (int i : expected) {
            assertEquals(i, head.getFirst().key);
            head.removeFirst();
        }
    }

    static Stream<Arguments> heapArgument() {
        return Stream.of(
                Arguments.of(new int[]{10}, new int[]{10}),
                Arguments.of(new int[]{10, 5}, new int[]{5}),
                Arguments.of(new int[]{10, 5, 20}, new int[]{20, 5}),

                Arguments.of(new int[]{10, 15, 20, 5}, new int[]{5}),
                Arguments.of(new int[]{10, 15, 20, 5, 3}, new int[]{3, 5}),
                Arguments.of(new int[]{10, 15, 20, 5, 3, 2}, new int[]{2, 5}),
                Arguments.of(new int[]{10, 15, 20, 5, 3, 2, 1}, new int[]{1, 2, 5}),
                Arguments.of(new int[]{10, 15, 20, 5, 3, 2, 1, 4}, new int[]{1})
        );
    }
}
