import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class QueueArrayTest {

    @Test
    public void testArraySizeOfEmpty() {
        Queue q = new QueueArray();
        assertEquals(0, q.size());
    }

    @Test
    public void testArrayEmptyReturnNull() {
        Queue q = new QueueArray();
        assertNull(q.dequeue());
    }

    @Test
    public void testArrayOneElement() {
        Queue q = new QueueArray();
        q.enqueue(1);
        assertEquals(1, q.size());
        assertEquals(1, q.dequeue());
    }

    @Test
    public void testArrayTwoElements() {
        Queue q = new QueueArray();
        q.enqueue(1);
        q.enqueue(2);
        assertEquals(2, q.size());
        assertEquals(1, q.dequeue());
        assertEquals(1, q.size());
    }

    @Test
    public void testArrayElementNull() {
        Queue q = new QueueArray();
        q.enqueue(null);
        assertEquals(1, q.size());
        assertEquals(null, q.dequeue());
    }

    @Test
    public void testArrayPeekElement() {
        QueueArray q = new QueueArray();
        q.enqueue(1);
        assertEquals(1, q.size());
        assertEquals(1, q.peek());
        assertEquals(1, q.size());
    }

    @Test
    public void testArrayThreeElementsTakeTwo() {
        Queue q = new QueueArray();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        assertEquals(3, q.size());
        assertEquals(1, q.dequeue());
        assertEquals(2, q.dequeue());
    }

    @Test
    public void testArrayPutTwoTakeThree() {
        Queue q = new QueueArray();
        q.enqueue(1);
        q.enqueue(2);
        assertEquals(2, q.size());
        assertEquals(1, q.dequeue());
        assertEquals(2, q.dequeue());
        assertEquals(null, q.dequeue());
        assertEquals(null, q.dequeue());
        assertEquals(0, q.size());
    }

    @Test
    public void testArrayAddCapacity() {
        QueueArray q = new QueueArray();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        assertEquals(5, q.size());
        q.enqueue(6);
        assertEquals(10, q.capacity());
    }

    @Test
    public void testArrayPutTakeAndAddCapacity() {
        Queue q = new QueueArray();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        assertEquals(5, q.size());
        q.enqueue(6);
        assertEquals(1, q.dequeue());
        assertEquals(2, q.dequeue());
        assertEquals(3, q.dequeue());
    }

//        List l = new ArrayList();
//        l.add("x");
//        List sl = Collections.synchronizedList(l);
//        List ul = Collections.unmodifiableList(l);
//
//        System.out.println(ul.get(0));
//        ul.add("y");
//    }

    @Test
    public void testArraySingleElement() {
        Queue q = new QueueArray();
        q.enqueue(1);
        assertEquals(1, q.dequeue());
    }

    @Test
    public void testArrayThreeElements() {
        Queue q = new QueueArray();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        assertEquals(1, q.dequeue());
    }

    @Test
    public void testArrayElementsMoreThanLength() {
        QueueArray q = new QueueArray();
        for (int i = 0; i < 10; i++) {
            q.enqueue(i+1);
        }
        q.enqueue(11);
        assertEquals(20, q.capacity());
    }

    @Test
    public void testArrayElementsThreePutTwoTake() {
        Queue q = new QueueArray();
        q.enqueue(1);
        q.enqueue(2);
        assertEquals(1, q.dequeue());
        assertEquals(2, q.dequeue());
    }


//    @Test
//    public void testList() {
//        LinkedList q = new LinkedList();
//        q.push(1);
//        q.push(2);
//        q.push(3);
//        assertEquals(1, q.pop());
//        assertEquals(2, q.pop());
//    }


    @Test
    public void testListManyElementsEnqueueDequeueMixed() {
        QueueArray q = new QueueArray();
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

    @Test
    public void testArrayTakeMoreThanExist() {
        Queue q = new QueueArray();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        q.enqueue(null);
        q.enqueue(7);
        assertEquals(1, q.dequeue());
        assertEquals(2, q.dequeue());
        assertEquals(3, q.dequeue());
        assertEquals(4, q.dequeue());
        assertEquals(5, q.dequeue());
        assertEquals(null, q.dequeue());
        assertEquals(7, q.dequeue());
        assertEquals(null, q.dequeue());
        assertEquals(null, q.dequeue());

        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        q.enqueue(null);
        q.enqueue(6);
        assertEquals(1, q.dequeue());
        assertEquals(2, q.dequeue());
        assertEquals(3, q.dequeue());
        assertEquals(4, q.dequeue());
        assertEquals(5, q.dequeue());
        assertEquals(null, q.dequeue());
        assertEquals(6, q.dequeue());
    }

    @Test
    public void testArrayCycledArray() {
        int capacity = 5;
        QueueArray q = new QueueArray(capacity);
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        assertEquals(capacity, q.capacity());
        assertEquals(1, q.dequeue());
        assertEquals(2, q.dequeue());
        q.enqueue(5);
        q.enqueue(6);
        assertEquals(capacity, q.capacity());
        q.enqueue(7);
        q.enqueue(8);
        assertTrue(q.capacity() >= q.size());
        assertEquals(3, q.dequeue());
        assertEquals(4, q.dequeue());
        assertEquals(5, q.dequeue());
        assertEquals(6, q.dequeue());
        assertEquals(7, q.dequeue());
        assertEquals(8, q.dequeue());
    }
}
