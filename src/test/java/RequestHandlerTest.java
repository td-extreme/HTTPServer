import com.td.HttpServer.*;

class MockRequest implements HttpProtocal {
  public String method() { return " "; }
  public String path() { return " "; }
  public String version() { return " "; }
  public String requestLine() { return " "; }
}

class MockVerifierValid implements Validator {
  public boolean isValid(HttpProtocal request) {
    return true;
  }
}

class MockVerifierInvalid implements Validator {
  public boolean isValid(HttpProtocal request) {
    return false;
  }
}

public class RequestHandlerTest extends junit.framework.TestCase {
    RequestHandler handlerValid;
    RequestHandler handlerInvalid;
    MockVerifierValid valid;
    MockVerifierInvalid invalid;
    MockRequest request;

  protected void setUp() {
    valid = new MockVerifierValid();
    invalid = new MockVerifierInvalid();
    handlerValid = new RequestHandler(valid);
    handlerInvalid = new RequestHandler(invalid);
    request = new MockRequest();
  }

  public void testValidRequest() {
    assertEquals("200", handlerValid.processRequest(request));
  }

  public void testInvalidRequest() {
    assertEquals("400", handlerInvalid.processRequest(request));
  }
}
