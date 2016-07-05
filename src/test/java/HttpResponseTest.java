import com.td.HttpServer.*;
import java.util.*;

public class HttpResponseTest extends junit.framework.TestCase {

  private HttpResponse response;

  protected void setUp() {
    response = new HttpResponse();
  }

  public void testDefaultResponseLine() {
    assertEquals(response.responseLine(), "HTTP/1.1 200 OK");
  }

  public void testBadRequestResponseLine() {
    response.setCode(400);
    assertEquals(response.responseLine(), "HTTP/1.1 400 Bad Request");
  }

  public void testNotFoundResponseLine() {
    response.setCode(404);
    assertEquals(response.responseLine(), "HTTP/1.1 404 Not Found");
  }

  public void testHeadersFormatsCorrectly() {
    response.addHeader("Server", "java server");
    response.addHeader("Test", "This");
    assertTrue(response.headers().contains("Server: java server\r\nTest: This"));
  }

  public void testGenerateResponseForSendingOutToSocket() {
    response.setBody("This is the response body");
    response.addHeader("Test/Header", "test value");
    response.addHeader("Test/Header2", "test value2");
    String responseShouldBe = "HTTP/1.1 200 OK\r\nTest/Header: test value\r\nTest/Header2: test value2\r\n\r\nThis is the response body";
    String responseGenerated = new String(response.responseBytes());
    assertEquals(responseGenerated, responseShouldBe);
  }
}
