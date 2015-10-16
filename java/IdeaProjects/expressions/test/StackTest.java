import org.junit.Test;

import java.sql.Time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class StackTest {
    @Test
    public void testStackOneElement() {
        Stack stack = new StackList();
        assertTrue(stack.isEmpty());
        stack.push(1);
        assertEquals(1, stack.pop());
    }

    @Test
    public void testStackTwoElements() {
        Stack stack = new StackList();
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }
    
    @Test
    public void testStackManyElements() {
        Stack stack = new StackList();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(null);
        stack.push(7);
        assertEquals(7, stack.pop());
        assertEquals(null, stack.pop());
        assertEquals(5, stack.pop());
        assertEquals(4, stack.pop());
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertEquals(null, stack.pop());
        assertEquals(null, stack.pop());

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(null);
        stack.push(6);
        assertEquals(6, stack.pop());
        assertEquals(null, stack.pop());
        assertEquals(5, stack.pop());
        assertEquals(4, stack.pop());
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }
}
