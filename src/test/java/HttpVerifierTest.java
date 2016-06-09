import com.td.HttpServer.*;

public class HttpVerifierTest extends junit.framework.TestCase {

  HttpVerifier checker;

  class InvalidMethodRequest implements HttpProtocal {
    public String method() { return "XXXXX"; }
    public String path() { return "/"; }
    public String version() { return "HTTP/1.1"; }
    public String requestLine() { return " "; }
  }

  class InvalidPathRequest implements HttpProtocal {
    public String method() { return "GET"; }
    public String path() { return "^#"; }
    public String version() { return "HTTP/1.1"; }
    public String requestLine() { return " "; }
  }

  class InvalidVersionRequest implements HttpProtocal {
    public String method() { return "GET"; }
    public String path() { return "/"; }
    public String version() { return "FTP/1.1"; }
    public String requestLine() { return " "; }
  }

  class ValidGetRequest implements HttpProtocal {
    public String method() { return "GET"; }
    public String path() { return "/"; }
    public String version() { return "HTTP/1.1"; }
    public String requestLine() { return " "; }
  }

  class ValidHeadRequest implements HttpProtocal {
    public String method() { return "HEAD"; }
    public String path() { return "/"; }
    public String version() { return "HTTP/1.1"; }
    public String requestLine() { return " "; }
  }

  class ValidPostRequest implements HttpProtocal {
    public String method() { return "POST"; }
    public String path() { return "/"; }
    public String version() { return "HTTP/1.1"; }
    public String requestLine() { return " "; }
  }

  class ValidPutRequest implements HttpProtocal {
    public String method() { return "PUT"; }
    public String path() { return "/"; }
    public String version() { return "HTTP/1.1"; }
    public String requestLine() { return " "; }
  }

  class ValidDeleteRequest implements HttpProtocal {
    public String method() { return "DELETE"; }
    public String path() { return "/"; }
    public String version() { return "HTTP/1.1"; }
    public String requestLine() { return " "; }
  }

  class ValidTraceRequest implements HttpProtocal {
    public String method() { return "TRACE"; }
    public String path() { return "/"; }
    public String version() { return "HTTP/1.1"; }
    public String requestLine() { return " "; }
  }

  class ValidOptionsRequest implements HttpProtocal {
    public String method() { return "OPTIONS"; }
    public String path() { return "/"; }
    public String version() { return "HTTP/1.1"; }
    public String requestLine() { return " "; }
  }

  class ValidConnectRequest implements HttpProtocal {
    public String method() { return "CONNECT"; }
    public String path() { return "/"; }
    public String version() { return "HTTP/1.1"; }
    public String requestLine() { return " "; }
  }

  class ValidPatchRequest implements HttpProtocal {
    public String method() { return "PATCH"; }
    public String path() { return "/"; }
    public String version() { return "HTTP/1.1"; }
    public String requestLine() { return " "; }
  }

  protected void setUp() {
    checker = new HttpVerifier();
  }

  // Tesing invalid requests
  public void testInvalidRequestType() {
    InvalidMethodRequest request = new InvalidMethodRequest();
    assertFalse(checker.isValid(request));
  }

  public void testInvalidVersion() {
    InvalidVersionRequest request = new InvalidVersionRequest();
    assertFalse(checker.isValid(request));
  }

  public void testInvalidPath() {
    InvalidPathRequest request = new InvalidPathRequest();
    assertFalse(checker.isValid(request));
  }

  // Testing for each valid request type
  public void testForGetRequest() {
    ValidGetRequest request = new ValidGetRequest();
    assertTrue(checker.isValid(request));
  }

  public void testForHeadRequest() {
    ValidHeadRequest request = new ValidHeadRequest();
    assertTrue(checker.isValid(request));
  }

  public void testForPostRequest() {
    ValidPostRequest request = new ValidPostRequest();
    assertTrue(checker.isValid(request));
  }

  public void testForPutRequest() {
    ValidPutRequest request = new ValidPutRequest();
    assertTrue(checker.isValid(request));
  }

  public void testForDeleteRequest() {
    ValidDeleteRequest request = new ValidDeleteRequest();
    assertTrue(checker.isValid(request));
  }

  public void testForTraceRequest() {
    ValidTraceRequest request = new ValidTraceRequest();
    assertTrue(checker.isValid(request));
  }

  public void testForOptionsRequest() {
    ValidOptionsRequest request = new ValidOptionsRequest();
    assertTrue(checker.isValid(request));

  }

  public void testForConnectRequest() {
    ValidConnectRequest request = new ValidConnectRequest();
    assertTrue(checker.isValid(request));

  }

  public void testForPatchRequest() {
    ValidPatchRequest request = new ValidPatchRequest();
    assertTrue(checker.isValid(request));
  }
}
