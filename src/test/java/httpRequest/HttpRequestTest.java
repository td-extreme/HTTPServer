import com.td.HttpServer.*;
import java.util.HashMap;
public class HttpRequestTest extends junit.framework.TestCase {

  HttpRequest request;
  String requestLine;
  HashMap<String, String> headers;

  protected void setUp() {
    requestLine = "GET / HTTP/1.1";
    headers = new HashMap<String, String>();
    headers.put("Content-Length", "30");
    request = new HttpRequest(requestLine, headers);
  }

  public void testMethodIsSetToGet() {
    assertEquals("GET", request.method());
  }

  public void testPathIsSetToFowardSlash() {
    assertEquals("/", request.path());
  }

  public void testPathDoesNotIncludeParameters() {
    request = new HttpRequest("GET /this&parameters HTTP/1.1", headers);
    assertEquals("/this", request.path());
  }

  public void testParametersIsEmptyStringIfNoParameters() {
    assertEquals("", request.parameters());
  }

  public void testParametersGetSet() {
    request = new HttpRequest("GET /this&parameters HTTP/1.1", headers);
    assertEquals("parameters", request.parameters());
  }

  public void testVersionIsHTTP1point1() {
    assertEquals("HTTP/1.1", request.version());
  }

  public void testRequestLine() {
    assertEquals("GET / HTTP/1.1", request.requestLine());
  }

  public void testContentLength() {
    assertEquals(30, request.contentLength());
  }

  public void testGetHeaders() {
    assertEquals("30", request.headers().get("Content-Length"));
  }

  public void testContentLengthIsZeroWhenNoContentLengthHeaderPresent() {
    headers = new HashMap<String, String>();
    request = new HttpRequest(requestLine, headers);
    assertEquals(0, request.contentLength());
  }
}
