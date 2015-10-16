public class QueueArray implements Queue{
    private Object[] queue;
    private int size = 0;
    private int start = 0;
    private int end = 0;

    public QueueArray(int initCapacity) {
        queue = new Object[initCapacity];
    }

    public QueueArray() {
        this(5);
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return queue.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Object dequeue() {
        if (size == 0)
            return null;
        Object el = queue[start];
        queue[start] = null;
        start = (start + 1) % queue.length;
        size--;
        return el;
    }

    public Object peek() {
        return queue[start];
    }

    public void enqueue(Object element) {
        queue[end] = element;
        end = (end + 1) % queue.length;
        if (end == start) {
            increaseCapacity();
        }
        size++;
    }

    private void increaseCapacity() {
        Object[] new_queue = new Object[queue.length * 2];
        System.arraycopy(queue, start, new_queue, 0, queue.length - start);
        System.arraycopy(queue, 0, new_queue, queue.length - start, queue.length - end);
        start = 0;
        end = queue.length;
        queue = new_queue;
    }
}
