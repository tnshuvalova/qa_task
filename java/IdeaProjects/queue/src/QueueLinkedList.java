public class QueueLinkedList<E> implements Queue<E> {
    private Node head;
    private Node tail;
    private int size = 0;

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void enqueue(E element) {
        Node new_node = new Node(null, null, element);
        if (head == null) {
            head = new_node;
            tail = new_node;
            size++;
        } else {
            tail.next = new_node;
            new_node.previous = tail;
            tail = new_node;
//            Node old_tail = tail;
//            tail = new_node;
//            tail.previous = old_tail;
//            old_tail.next = tail;
            size++;
        }
    }

    public E dequeue() {
        if (head == null) {
            return null;
        } else {
            Node<E> old_head = head;
            head = old_head.next;
            if (head == null){
                tail = null;
            } else {
                head.previous = null;
            }
            size--;
            return old_head.value;
        }
    }

    private class Node<E> {
        private E value;
        private Node next;
        private Node previous;

        private Node(Node<E> next, Node<E> previous, E value) {
            this.next = next;
            this.previous = previous;
            this.value = value;
        }
    }
}
