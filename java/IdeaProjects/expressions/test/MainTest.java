import org.junit.Test;

import java.sql.Time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class MainTest {
    @Test
    public void testVariableEvaluate() {
        Variable x = new Variable();
        assertEquals(5, x.evaluate(5));
        assertEquals(15, x.evaluate(15));
    }

    @Test
    public void testPlusEvaluate() {
        Plus p1 = new Plus(new Const(3), new Const(4));
        assertEquals(7, p1.evaluate(5));
        assertEquals(7, p1.evaluate(15));
    }

    @Test
    public void testExpression() {
        int res = new Minus(
            new Times(
                new Const(2),
                new Variable()
            ),
            new Const(3)
        ).evaluate(5);
        assertEquals(7, res);
    }

    @Test
    public void testExpressionWithMinus() {
        int res = new Minus(new Times(new Const(3), new Variable())).evaluate(4);
        assertEquals(-12, res);
        int res2 = new Minus(new Plus(new Minus(new Times(new Variable(), new Variable()),
                new Times(new Const(4), new Variable())), new Const(4))).evaluate(10);
        assertEquals(-64, res2);
    }

    @Test
    public void testSquare() {
        int res = new Plus(new Minus(new Times(new Variable(), new Variable()),
                new Times(new Const(4), new Variable())), new Const(4)).evaluate(10);
        assertEquals(64, res);
    }
}
