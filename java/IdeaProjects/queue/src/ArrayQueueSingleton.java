/**
 * Created by tshuvalova on 31.03.15.
 */
public class ArrayQueueSingleton {
    private static Object[] queue;
    private static int size;
    private static int start;
    private static int end;

    private ArrayQueueSingleton() {

    }

    public static void resetQueue(int capacity) {
        queue = new Object[capacity];
        start = 0;
        end = 0;
        size = 0;
    }

    public static int size() {
        return size;
    }

    public static int capacity() {
        return queue.length;
    }

    public static boolean isEmpty() {
        return size == 0;
    }

    public static Object dequeue() {
        if (size == 0)
            return null;
        Object el = queue[start];
        queue[start] = null;
        start = (start + 1) % queue.length;
        size--;
        return el;
    }

    public static Object peek() {
        return queue[start];
    }

    public static void enqueue(Object element) {
        queue[end] = element;
        end = (end + 1) % queue.length;
        if (end == start) {
            increaseCapacity();
        }
        size++;
    }

    private static void increaseCapacity() {
        Object[] new_queue = new Object[queue.length*2];
        System.arraycopy(queue, start, new_queue, 0, queue.length - start);
        System.arraycopy(queue, 0, new_queue, queue.length - start, queue.length - end);
        start = 0;
        end = queue.length;
        queue = new_queue;
    }
}
