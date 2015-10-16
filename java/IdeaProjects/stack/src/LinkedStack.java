public class LinkedStack extends AbstractStack {
    private Node head;
    public LinkedStack(){
        size = 0;
    }
    public void push(Object element) {
        head = new Node(head, element);
        size++;
    }
    public Object pop(){
        if (head == null) {
            return null;
        }
        Object res = head.value;
        head = head.next;
        size--;
        return res;
    }
    public Object peek(){
        return head.value;
    }
    private class Node {
        private Object value;
        private Node next;
        private Node(Node next, Object value){
            this.next = next;
            this.value = value;
        }
    }
}
