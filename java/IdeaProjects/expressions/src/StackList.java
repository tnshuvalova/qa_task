public class StackList<E> implements Stack<E> {
    int size = 0;
    Node head;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(E element) {
        Node previous = head;
        head = new Node(element);
        head.next = null;
        head.previous = previous;
        size++;
    }

    public E pop(){
        if( size == 0 ) return null;
        Node<E> element = head;
        head = element.previous;
        size--;
        return element.value;
    }

    private class Node<E> {
        E value;
        Node next;
        Node previous;
        private Node(E value){
            this.value = value;
            this.next = null;
        }
    }

}
