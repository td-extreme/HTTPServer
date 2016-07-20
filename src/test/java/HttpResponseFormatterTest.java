import com.td.HttpServer.*;
import java.util.*;

public class HttpResponseFormatterTest extends junit.framework.TestCase {

  private HttpResponse response;
  private HttpResponseFormatter formatter;

  protected void setUp() {
    formatter = new HttpResponseFormatter();
    response = new HttpResponse();
  }

  public void testGenerateResponseForSendingOutToSocket() {
    response.setBody("This is the response body");
    response.addHeader("Test/Header", "test value");
    response.addHeader("Test/Header2", "test value2");
    String responseShouldBe = "HTTP/1.1 200 OK\r\nTest/Header: test value\r\nTest/Header2: test value2\r\n\r\nThis is the response body";
    String responseGenerated = new String(formatter.responseAsBytes(response));
    assertEquals(responseGenerated, responseShouldBe);
  }
}
