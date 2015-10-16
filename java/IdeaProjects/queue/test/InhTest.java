import org.junit.Test;
import use_inheritance.AChild;
import use_inheritance.InheritanceIntro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class InhTest {
    @Test
    public void testInhFirst() {
        InheritanceIntro ii = new InheritanceIntro();
        assertEquals("hello!!!!", ii.sayHi());
    }
    public void testAChild() {

    }
}
