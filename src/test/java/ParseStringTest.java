import com.td.HttpServer.*;

public class ParseStringTest extends junit.framework.TestCase {

  String testString = "This is a test String";
  String results[] = null;
  ParseString parser;

  protected void setUp() {
    parser = new ParseString();
    results = parser.parse(testString);
  }

  public void testCorrectLengthArray() {
    assertEquals(5, results.length);
  }

  public void testFirstParameter() {
    assertEquals("This", results[0]);
  }

  public void testSecondParameter() {
    assertEquals("is", results[1]);
  }
}
