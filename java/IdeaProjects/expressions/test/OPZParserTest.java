import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class OPZParserTest {

    @Test
    public void testOPZParseSimple() {
        OPZParser opz_parser = new OPZParser();
        assertEquals("1 2 +", "1 + 2");
    }
}
