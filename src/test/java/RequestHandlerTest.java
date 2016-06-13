import com.td.HttpServer.*;

class MockVerifierValid implements IValidator {
  public boolean isValid(String[] request) {
    return true;
  }
}

class MockVerifierInvalid implements IValidator {
  public boolean isValid(String[] request) {
    return false;
  }
}
public class RequestHandlerTest extends junit.framework.TestCase {


  RequestHandler handler;
  MockVerifierValid valid;
  MockVerifierInvalid invalid;
  String[] request = { "Dummy" };


  protected void setUp() {
    valid = new MockVerifierValid();
    invalid = new MockVerifierInvalid();
  }

  public void testValidRequest() {
    handler = new RequestHandler(valid);
    assertEquals("200", handler.processRequest(request));
  }

  public void testInvalidRequest() {
    handler = new RequestHandler(invalid);
    assertEquals("400", handler.processRequest(request));
  }
}
