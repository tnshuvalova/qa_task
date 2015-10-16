import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class QueueLinkedListTest {

    @Test
    public void testListIsEmpty() {
        Queue q = new QueueLinkedList();
        assertTrue(q.isEmpty());
    }

    @Test
    public void testListEmptyDequeue(){
        QueueLinkedList q = new QueueLinkedList();
        assertNull(q.dequeue());
        assertNull(q.dequeue());
        assertEquals(0, q.size());
    }

    @Test
    public void testListSingleElementSize() {
        QueueLinkedList q = new QueueLinkedList();
        q.enqueue(1);
        assertEquals(1, q.size());
    }

    @Test
    public void testListSingleElementDequeu() {
        QueueLinkedList q = new QueueLinkedList();
        q.enqueue("first");
        assertEquals("first", q.dequeue());
        assertEquals(0, q.size());
    }

    @Test
    public void testListTwoElementsSize() {
        QueueLinkedList q = new QueueLinkedList();
        q.enqueue(1);
        q.enqueue(2);
        assertEquals(2, q.size());
    }


    @Test
    public void testListTwoElementsDequeue() {
        QueueLinkedList q = new QueueLinkedList();
        q.enqueue("first");
        q.enqueue("second");
        assertEquals("first", q.dequeue());
    }

    @Test
    public void testListManyElementsDequeueOne() {
        QueueLinkedList q = new QueueLinkedList();
        q.enqueue("first");
        q.enqueue("second");
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(true);
        assertEquals("first", q.dequeue());
    }

    @Test
    public void testListManyElementsDequeueAll() {
        QueueLinkedList q = new QueueLinkedList();
        q.enqueue("first");
        q.enqueue("second");
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(true);
        assertEquals("first", q.dequeue());
        assertEquals("second", q.dequeue());
        assertEquals(3, q.dequeue());
        assertEquals(4, q.dequeue());
        assertEquals(true, q.dequeue());
    }

    @Test
    public void testListManyElementsEnqueueDequeueMixed() {
        QueueLinkedList q = new QueueLinkedList();
        q.enqueue("first");
        q.enqueue("second");
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(true);
        assertEquals("first", q.dequeue());
        assertEquals("second", q.dequeue());
        q.enqueue(5);
        q.enqueue(6);
        assertEquals(3, q.dequeue());
        q.enqueue(7);
        q.enqueue(8);
        assertEquals(4, q.dequeue());
        assertEquals(true, q.dequeue());
        assertEquals(4, q.size());
        assertEquals(5, q.dequeue());
        assertEquals(6, q.dequeue());
        assertEquals(7, q.dequeue());
        assertEquals(8, q.dequeue());
        assertNull(q.dequeue());
        assertEquals(0, q.size());
    }

}
