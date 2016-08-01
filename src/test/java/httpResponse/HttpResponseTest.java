import com.td.HttpServer.*;
import java.util.*;

public class HttpResponseTest extends junit.framework.TestCase {

  private HttpResponse response;
  private HashMap<String, String> testMap;

  protected void setUp() {
    response = new HttpResponse();
    response.addHeader("testKey", "testValue");
    testMap = new HashMap<String, String>();
    testMap.put("key01", "value01");
    testMap.put("key02", "value02");
    response.setBody("12345", "text/plain");
  }

  public void testDefaultResponseLine() {
    assertEquals(response.responseCode(), 200);
  }

  public void testGetHeaders() {
    assertEquals(response.headers().get("testKey"), "testValue");
  }

  public void testGetValue() {
    assertEquals(response.getValueForHeader("testKey"), "testValue");
  }

  public void testAddHashMapToHeaders() {
    response.addHeaders(testMap);
    assertEquals(response.getValueForHeader("key02"), "value02");
  }

  public void testContentLengthGetsSet() {
    assertEquals(response.getValueForHeader("Content-Length"), "5");
  }

  public void testContentTypeGetsSet() {
    assertEquals(response.getValueForHeader("Content-Type"), "text/plain");
  }
}
