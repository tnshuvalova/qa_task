import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ArrayQueueADTTest {
    @Test
    public void testQueueADTEmptySize() {
        ArrayQueueADT queue = new ArrayQueueADT();
        assertEquals(0, ArrayQueueADT.size(queue));
    }

    @Test
    public void testQueueADTSize() {
        ArrayQueueADT queuenull = new AQE();
        ArrayQueueADT queue = new ArrayQueueADT();
        queuenull.enqueue(queue, 1);
        ArrayQueueADT.enqueue(queue, 2);
        ArrayQueueADT.enqueue(queue, 3);
        assertEquals(3, ArrayQueueADT.size(queue));
    }

    @Test
    public void testQueueADTTakeMoreThanExist() {
        ArrayQueueADT queue = new ArrayQueueADT();
        ArrayQueueADT.enqueue(queue, 1);
        ArrayQueueADT.enqueue(queue, 2);
        ArrayQueueADT.enqueue(queue, 3);
        ArrayQueueADT.enqueue(queue, 4);
        ArrayQueueADT.enqueue(queue, 5);
        ArrayQueueADT.enqueue(queue, null);
        ArrayQueueADT.enqueue(queue, 7);
        assertEquals(1, ArrayQueueADT.dequeue(queue));
        assertEquals(2, ArrayQueueADT.dequeue(queue));
        assertEquals(3, ArrayQueueADT.dequeue(queue));
        assertEquals(4, ArrayQueueADT.dequeue(queue));
        assertEquals(5, ArrayQueueADT.dequeue(queue));
        assertEquals(null, ArrayQueueADT.dequeue(queue));
        assertEquals(7, ArrayQueueADT.dequeue(queue));
        assertEquals(null, ArrayQueueADT.dequeue(queue));
        assertEquals(null, ArrayQueueADT.dequeue(queue));

        ArrayQueueADT.enqueue(queue, 1);
        ArrayQueueADT.enqueue(queue, 2);
        ArrayQueueADT.enqueue(queue, 3);
        ArrayQueueADT.enqueue(queue, 4);
        ArrayQueueADT.enqueue(queue, 5);
        ArrayQueueADT.enqueue(queue, null);
        ArrayQueueADT.enqueue(queue, 6);
        assertEquals(1, ArrayQueueADT.dequeue(queue));
        assertEquals(2, ArrayQueueADT.dequeue(queue));
        assertEquals(3, ArrayQueueADT.dequeue(queue));
        assertEquals(4, ArrayQueueADT.dequeue(queue));
        assertEquals(5, ArrayQueueADT.dequeue(queue));
        assertEquals(null, ArrayQueueADT.dequeue(queue));
        assertEquals(6, ArrayQueueADT.dequeue(queue));
    }

    @Test
    public void testQueueADTCycledArray() {
        ArrayQueueADT queue = new ArrayQueueADT(5);
        ArrayQueueADT.enqueue(queue, 1);
        ArrayQueueADT.enqueue(queue, 2);
        ArrayQueueADT.enqueue(queue, 3);
        ArrayQueueADT.enqueue(queue, 4);
        assertEquals(5, ArrayQueueADT.capacity(queue));
        assertEquals(1, ArrayQueueADT.dequeue(queue));
        assertEquals(2, ArrayQueueADT.dequeue(queue));
        ArrayQueueADT.enqueue(queue, 5);
        ArrayQueueADT.enqueue(queue, 6);
        assertEquals(5, ArrayQueueADT.capacity(queue));
        ArrayQueueADT.enqueue(queue, 7);
        ArrayQueueADT.enqueue(queue, 8);
        assertTrue(ArrayQueueADT.capacity(queue) >= ArrayQueueADT.size(queue));
        assertEquals(3, ArrayQueueADT.dequeue(queue));
        assertEquals(4, ArrayQueueADT.dequeue(queue));
        assertEquals(5, ArrayQueueADT.dequeue(queue));
        assertEquals(6, ArrayQueueADT.dequeue(queue));
        assertEquals(7, ArrayQueueADT.dequeue(queue));
        assertEquals(8, ArrayQueueADT.dequeue(queue));
    }
}
