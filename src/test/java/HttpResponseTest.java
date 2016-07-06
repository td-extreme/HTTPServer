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
  }

  public void testDefaultResponseLine() {
    assertEquals(200, response.responseCode());
  }

  public void testGetHeaders() {
    assertEquals("testValue", response.headers().get("testKey"));
  }

  public void testGetValue() {
    assertEquals("testValue", response.getValueForHeader("testKey"));
  }

  public void testAddHashMapToHeaders() {
    response.addHeaders(testMap);
    assertEquals("value02", response.getValueForHeader("key02"));
  }
}
