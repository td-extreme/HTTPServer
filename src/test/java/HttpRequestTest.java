import com.td.HttpServer.*;

public class HttpRequestTest extends junit.framework.TestCase {

  HttpRequest request;
  HttpRequestBuilder requestBuilder;
  protected void setUp() {
    String message = "GET / HTTP/1.1";
    request = new HttpRequest(message);
  }

  public void testMethodIsSetToGet() {
    assertEquals("GET", request.method());
  }

  public void testPathIsSetToFowardSlash() {
    assertEquals("/", request.path());
  }

  public void testVersionIsHTTP1point1() {
    assertEquals("HTTP/1.1", request.version());
  }

  public void testRequestLine() {
    assertEquals("GET / HTTP/1.1", request.requestLine());
  }
}