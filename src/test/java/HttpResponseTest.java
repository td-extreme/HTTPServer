import com.td.HttpServer.*;
import java.util.*;

public class HttpResponseTest extends junit.framework.TestCase {

  private String responseLine;
  private byte[] body;
  private HashMap<String, String> headers;
  private HttpResponse response;

  protected void setUp() {
    responseLine = "HTTP/1.1 OK 200";
    body = "This".getBytes();
    headers = new HashMap<String, String>();
    headers.put("testKey", "testValue");
    response = new HttpResponse(responseLine, body, headers);
  }

  public void testResponseCode() {
    assertEquals(responseLine, response.responseLine());
  }

  public void testBody() {
    assertEquals(body, response.body());
  }

  public void testHeaders() {
    assertTrue(response.headers().contains("testKey: testValue"));
  }
}
