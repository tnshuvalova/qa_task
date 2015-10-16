/**
 * Created by tshuvalova on 01.04.15.
 */
public class ArrayQueueADT {
    private Object[] queue;
    private int size;
    private int start;
    private int end;

    public ArrayQueueADT(int capacity) {
        queue = new Object[capacity];
        start = 0;
        end = 0;
        size = 0;
    }

    public ArrayQueueADT() {
        this(5);
    }

    public static int size(ArrayQueueADT self) {
        return self.size;
    }

    public static int capacity(ArrayQueueADT self) {
        return self.queue.length;
    }

    public static boolean isEmpty(ArrayQueueADT self) {
        return self.size == 0;
    }

    public static Object dequeue(ArrayQueueADT self) {
        if (self.size == 0)
            return null;
        Object el = self.queue[self.start];
        self.queue[self.start] = null;
        self.start = (self.start + 1) % self.queue.length;
        self.size--;
        return el;
    }

    public static Object peek(ArrayQueueADT self) {
        return self.queue[self.start];
    }

    public static void enqueue(ArrayQueueADT self, Object element) {
        self.queue[self.end] = element;
        self.end = (self.end + 1) % self.queue.length;
        if (self.end == self.start) {
            increaseCapacity(self);
        }
        self.size++;
    }

    private static void increaseCapacity(ArrayQueueADT self) {
        Object[] new_queue = new Object[self.queue.length*2];
        System.arraycopy(self.queue, self.start, new_queue, 0, self.queue.length - self.start);
        System.arraycopy(self.queue, 0, new_queue, self.queue.length - self.start, self.queue.length - self.end);
        self.start = 0;
        self.end = self.queue.length;
        self.queue = new_queue;
    }
}
