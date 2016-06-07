import com.td.HTTPServer.*;

public class RequestHandlerTest extends junit.framework.TestCase {
    RequestHandler handler;
    RequestVerifier realVerifier;

  protected void setUp() {
    realVerifier = new RequestVerifier();
    handler = new RequestHandler(realVerifier);
  }

  public void testValidRequest() {
    assertEquals("200", handler.processRequest("GET / HTTP/1.1"));
  }

  public void testInvalidRequest() {
    assertEquals("400", handler.processRequest("jkljd"));
  }
}
