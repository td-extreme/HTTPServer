import com.td.HttpServer.*;

public class HttpVerifierTest extends junit.framework.TestCase {

  HttpVerifier checker;

  class MockHttpRequest implements HttpProtocal {
    private String method;
    private String path;
    private String version;

    public MockHttpRequest(String method_, String path_, String version_) {
      method = method_;
      path = path_;
      version = version_;
    }

    public String method() { return method; }
    public String path() { return path; }
    public String version() { return version; }
    public String requestLine() { return " "; }
  }


protected void setUp() {
    checker = new HttpVerifier();
  }

  // Tesing invalid requests
  public void testInvalidRequestType() {
    MockHttpRequest request = new MockHttpRequest("NOT", "/", "HTTP/1.1");
    assertFalse(checker.isValid(request));
  }

  public void testInvalidVersion() {
    MockHttpRequest request = new MockHttpRequest("GET", "/", "FTP/1.1");
    assertFalse(checker.isValid(request));
  }
  public void testInvalidPath() {
    MockHttpRequest request = new MockHttpRequest("GET", "G", "HTTP/1.1");
    assertFalse(checker.isValid(request));
  }

  // Testing for each valid request type
  public void testForGetRequest() {
    MockHttpRequest request = new MockHttpRequest("GET", "/", "HTTP/1.1");
    assertTrue(checker.isValid(request));
  }

  public void testForHeadRequest() {
    MockHttpRequest request = new MockHttpRequest("HEAD", "/", "HTTP/1.1");
    assertTrue(checker.isValid(request));
  }

  public void testForPostRequest() {
    MockHttpRequest request = new MockHttpRequest("POST", "/", "HTTP/1.1");
    assertTrue(checker.isValid(request));
  }

  public void testForPutRequest() {
    MockHttpRequest request = new MockHttpRequest("PUT", "/", "HTTP/1.1");
    assertTrue(checker.isValid(request));
  }

  public void testForDeleteRequest() {
    MockHttpRequest request = new MockHttpRequest("DELETE", "/", "HTTP/1.1");
    assertTrue(checker.isValid(request));
  }

  public void testForTraceRequest() {
    MockHttpRequest request = new MockHttpRequest("TRACE", "/", "HTTP/1.1");
    assertTrue(checker.isValid(request));
  }

  public void testForOptionsRequest() {
    MockHttpRequest request = new MockHttpRequest("OPTIONS", "/", "HTTP/1.1");
    assertTrue(checker.isValid(request));
  }

  public void testForConnectRequest() {
    MockHttpRequest request = new MockHttpRequest("CONNECT", "/", "HTTP/1.1");
    assertTrue(checker.isValid(request));
  }

  public void testForPatchRequest() {
    MockHttpRequest request = new MockHttpRequest("PATCH", "/", "HTTP/1.1");
    assertTrue(checker.isValid(request));
  }
}
