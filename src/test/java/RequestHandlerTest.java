import com.td.HTTPServer.*;

class MockVerifier implements HTTPVerifierInterface {
  public boolean isValidHTTP(String request) {
    return request.equals("valid");
  }
}

public class RequestHandlerTest extends junit.framework.TestCase {
    RequestHandler handler;
    MockVerifier verifier;

  protected void setUp() {
    verifier = new MockVerifier();
    handler = new RequestHandler(verifier);
  }

  public void testValidRequest() {
    assertEquals("200", handler.processRequest("valid"));
  }

  public void testInvalidRequest() {
    assertEquals("400", handler.processRequest("invalid"));
  }
}
