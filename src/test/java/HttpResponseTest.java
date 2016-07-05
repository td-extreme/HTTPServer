import com.td.HttpServer.*;
import java.util.*;

public class HttpResponseTest extends junit.framework.TestCase {

  private String responseLine;
  private int responseCode;
  private byte[] body;
  private HashMap<String, String> headers;
  private HttpResponse response;

  protected void setUp() {
    responseLine = "HTTP/1.1 200 OK";
    body = "This".getBytes();
    headers = new HashMap<String, String>();
    response = new HttpResponse();
  }

  public void testResponseLine() {
    assertEquals(responseLine, response.responseLine());
  }

  public void testBody() {
    response.setBody(body);
    assertEquals(body, response.body());
  }

  public void testHeaders() {
    response.addHeader("Server", "java server");
    assertTrue(response.headers().contains("Server:"));
  }
}
