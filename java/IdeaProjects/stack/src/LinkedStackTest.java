/**
 * Created by tshuvalova on 09.03.15.
 */
public class LinkedStackTest {
    public static void dump(LinkedStack stack) {
        System.out.println(stack.size());
        while(stack.size() != 0){
            System.out.println(stack.peek() + " " + stack.pop());
        }
    }
    public static void fill(LinkedStack stack) {
        for (int i = 0; i < 10; i++) {
            stack.push("e" + i);
        }
    }
    public static void main(String[] args) {
        LinkedStack stack = new LinkedStack();
        dump(stack);
        fill(stack);
        dump(stack);
    }

}
