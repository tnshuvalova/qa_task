import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ArrayQueueSingletonTest {
    @Test
    public void testQueueSingletonEmptySize() {
        ArrayQueueSingleton.resetQueue(5);
        assertEquals(0, ArrayQueueSingleton.size());
    }

    @Test
    public void testQueueSingletonSize() {
        ArrayQueueSingleton.resetQueue(5);
        ArrayQueueSingleton.enqueue(1);
        ArrayQueueSingleton.enqueue(2);
        ArrayQueueSingleton.enqueue(3);
        assertEquals(3, ArrayQueueSingleton.size());
    }

    @Test
    public void testArrayTakeMoreThanExist() {
        ArrayQueueSingleton.resetQueue(5);
        ArrayQueueSingleton.enqueue(1);
        ArrayQueueSingleton.enqueue(2);
        ArrayQueueSingleton.enqueue(3);
        ArrayQueueSingleton.enqueue(4);
        ArrayQueueSingleton.enqueue(5);
        ArrayQueueSingleton.enqueue(null);
        ArrayQueueSingleton.enqueue(7);
        assertEquals(1, ArrayQueueSingleton.dequeue());
        assertEquals(2, ArrayQueueSingleton.dequeue());
        assertEquals(3, ArrayQueueSingleton.dequeue());
        assertEquals(4, ArrayQueueSingleton.dequeue());
        assertEquals(5, ArrayQueueSingleton.dequeue());
        assertEquals(null, ArrayQueueSingleton.dequeue());
        assertEquals(7, ArrayQueueSingleton.dequeue());
        assertEquals(null, ArrayQueueSingleton.dequeue());
        assertEquals(null, ArrayQueueSingleton.dequeue());

        ArrayQueueSingleton.enqueue(1);
        ArrayQueueSingleton.enqueue(2);
        ArrayQueueSingleton.enqueue(3);
        ArrayQueueSingleton.enqueue(4);
        ArrayQueueSingleton.enqueue(5);
        ArrayQueueSingleton.enqueue(null);
        ArrayQueueSingleton.enqueue(6);
        assertEquals(1, ArrayQueueSingleton.dequeue());
        assertEquals(2, ArrayQueueSingleton.dequeue());
        assertEquals(3, ArrayQueueSingleton.dequeue());
        assertEquals(4, ArrayQueueSingleton.dequeue());
        assertEquals(5, ArrayQueueSingleton.dequeue());
        assertEquals(null, ArrayQueueSingleton.dequeue());
        assertEquals(6, ArrayQueueSingleton.dequeue());
    }

    @Test
    public void testArrayCycledArray() {
        int capacity = 5;
        ArrayQueueSingleton.resetQueue(capacity);
        ArrayQueueSingleton.enqueue(1);
        ArrayQueueSingleton.enqueue(2);
        ArrayQueueSingleton.enqueue(3);
        ArrayQueueSingleton.enqueue(4);
//        assertEquals(capacity, ArrayQueueSingleton.capacity());
        assertEquals(1, ArrayQueueSingleton.dequeue());
        assertEquals(2, ArrayQueueSingleton.dequeue());
        ArrayQueueSingleton.enqueue(5);
        ArrayQueueSingleton.enqueue(6);
//        assertEquals(capacity, ArrayQueueSingleton.capacity());
        ArrayQueueSingleton.enqueue(7);
        ArrayQueueSingleton.enqueue(8);
        assertTrue(ArrayQueueSingleton.capacity() >= ArrayQueueSingleton.size());
        assertEquals(3, ArrayQueueSingleton.dequeue());
        assertEquals(4, ArrayQueueSingleton.dequeue());
        assertEquals(5, ArrayQueueSingleton.dequeue());
        assertEquals(6, ArrayQueueSingleton.dequeue());
        assertEquals(7, ArrayQueueSingleton.dequeue());
        assertEquals(8, ArrayQueueSingleton.dequeue());
    }
}
