import com.td.HttpServer.*;
import java.util.*;

public class HttpResponseTest extends junit.framework.TestCase {

  private HttpResponse response;
  private HashMap<String, String> testMap;

  protected void setUp() {
    testMap = new HashMap<String, String>();
    testMap.put("Content-Type", "text/plain");
    response = new HttpResponse(200, testMap, "12345".getBytes());
  }

  public void testDefaultResponseLine() {
    assertEquals(response.responseCode(), 200);
  }

  public void testGetHeaders() {
    assertEquals(response.headers().get("Content-Type"), "text/plain");
  }

  public void testGetValue() {
    assertEquals(response.getValueForHeader("Content-Type"), "text/plain");
  }

  public void testContentLengthGetsSet() {
    assertEquals(response.getValueForHeader("Content-Length"), "5");
  }
}
