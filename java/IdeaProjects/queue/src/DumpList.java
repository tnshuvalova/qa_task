/**
 * Created by tshuvalova on 12.03.15.
 */
public class DumpList {
    public static void fill(QueueLinkedList list) {
        for(int i=0; i<10; i++) {
            list.enqueue("el"+i);
            System.out.println("el"+i+" added");
        }
    }

    public static void show(QueueLinkedList list) {
        for(int i=0; i<list.size(); i++) {
            System.out.println(list.dequeue());
        }
    }

    public static void main(String[] args) {
        QueueLinkedList list = new QueueLinkedList();
        fill(list);
        show(list);
    }
}
