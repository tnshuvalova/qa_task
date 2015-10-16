public interface Queue<E> {
    void enqueue(E o);
    E dequeue();
    int size();
    boolean isEmpty();
}
