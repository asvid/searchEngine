import junit.framework.TestCase;
import org.junit.Test;

public class StringGeneratorTest extends TestCase {

    @Test
    public void testSay() throws Exception {
        assertEquals(StringGenerator.say(), "generatedText");
    }
}