import com.td.HTTPServer.*;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class RequestHandlerTest extends junit.framework.TestCase {
    RequestHandler handler;
    HTTPVerifierInterface verifier;

  protected void setUp() {
    verifier = mock(HTTPVerifierInterface.class);
    handler = new RequestHandler(verifier);
  }

  public void testValidRequest() {
    when(verifier.isValidHTTP(anyString())).thenReturn(true);
    assertEquals("200", handler.processRequest("GET / HTTP/1.1"));
  }

  public void testInvalidRequest() {
    when(verifier.isValidHTTP(anyString())).thenReturn(false);
    assertEquals("400", handler.processRequest("jkljd"));
  }
}
